package com.tmtravlr.cp.block;

import java.util.Random;

import com.tmtravlr.cp.CPLib;
import com.tmtravlr.cp.te.ColourfulPortalTE;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulPortal extends Block implements ITileEntityProvider {
	
	public static final PropertyInteger MAX_RADIUS = PropertyInteger.create("i", 0, 31);
	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);
	boolean portalCreated = false;
	
	public ColourfulPortal() {
		super(Material.CLOTH);
		 this.setDefaultState(this.blockState.getBaseState().withProperty(MAX_RADIUS, Integer.valueOf(0)));
		setUnlocalizedName("colourful_portal");
		setRegistryName("colourful_portal");
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ColourfulPortalTE();
	}
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, facing.getAxis());
    }
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
      return this.getDefaultState();
    }
	
	static World world;
	
	@Override
	public int getMetaFromState(IBlockState state){
		return 0;
	}
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AXIS, MAX_RADIUS});
    }

}
