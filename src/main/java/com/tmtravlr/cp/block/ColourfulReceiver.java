package com.tmtravlr.cp.block;

import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.te.ColourfulReceiverTE;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulReceiver extends Block implements ITileEntityProvider {

	public BlockPos pos;
	
	public ColourfulReceiver(){
		super(Material.CLAY);
		setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("colourful_receiver");
		setRegistryName("colourful_receiver");
		setBlockUnbreakable();
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ColourfulReceiverTE();
	}
	
}
