package com.tmtravlr.cp.block;

import java.util.Random;

import com.tmtravlr.cp.init.ColourfulBlocks;
import com.tmtravlr.cp.init.ColourfulItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulAltar extends Block{

	public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("connected_north");
	public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("connected_south");
	public static final PropertyBool CONNECTED_WEST = PropertyBool.create("connected_west");
	public static final PropertyBool CONNECTED_EAST = PropertyBool.create("connected_east");
	
	public ColourfulAltar(){
		super(Material.ROCK);
		setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("colourful_altar");
		setRegistryName("colourful_altar");
		this.setHardness(3);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED_EAST, Boolean.FALSE).withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
    	if(world.isRemote){
			state = state.getBlock().getActualState(state, world, pos);
			if(state.getBlock() == ColourfulBlocks.caltar) {
				if(state.getValue(ColourfulAltar.CONNECTED_NORTH)){
					world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX()+1D, (double)pos.getY(), pos.getZ()+1D, 1.0, 1.0, 1.0, new int[0]);}
				if(state.getValue(ColourfulAltar.CONNECTED_EAST)){
					world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX()+1D, (double)pos.getY(), pos.getZ()-1D, 1.0, 1.0, -1.0, new int[0]);;}
				if(state.getValue(ColourfulAltar.CONNECTED_SOUTH)){
					world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX()-1D, (double)pos.getY(), pos.getZ()-1D, -1.0, 1.0, -1.0, new int[0]);;}
				if(state.getValue(ColourfulAltar.CONNECTED_WEST)){
					world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX()-1D, (double)pos.getY(), pos.getZ()+1D, -1.0, 1.0, 1.0, new int[0]);;}
			}
    	}
    }
    
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public int getMetaFromState (IBlockState state) {
	     
	    return 0;
	}
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(CONNECTED_NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north(2).east(2)))).withProperty(CONNECTED_EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos.east(2).south(2)))).withProperty(CONNECTED_SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south(2).west(2)))).withProperty(CONNECTED_WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos.west(2).north(2))));
    }
	private boolean isSideConnectable (IBlockAccess world, BlockPos pos, EnumFacing side) {
	     
	    final IBlockState state = world.getBlockState(pos.offset(side));
	    return (state == null) ? false : state.getBlock() == ColourfulBlocks.cpillar;
	}
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        return block == ColourfulBlocks.cpillar;
    }
    @Override
	protected BlockStateContainer createBlockState () {
	     
	    return new BlockStateContainer(this, new IProperty[] { CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST });
	}
    World worldIn; BlockPos pos; IBlockState state; Random rand;
    public ColourfulAltar(World world, BlockPos pos, IBlockState state, Random rand){
    	super(Material.ROCK);
    	this.worldIn=world;
    	this.pos=pos;
    	this.state=state;
    	this.rand=rand;
    }
    @Override public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {return true;}
    
}

