package com.tmtravlr.cp.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.tmtravlr.cp.init.ColourfulBlocks;
import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.te.PortalFrameTE;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Framer extends Item {
	
	public Framer(){
        setRegistryName("framer");
        setUnlocalizedName("framer");     
        setCreativeTab(ColourfulItems.cpTab);
        setMaxStackSize(1);
        this.setMaxDamage(256);
        GameRegistry.register(this);
	}
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> list, boolean par4)
    {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "right Click a block to frame it");
            list.add(TextFormatting.WHITE + "has 256 durability");
        } else {
            list.add(TextFormatting.WHITE + "Hold Shift for info");
        }
    }
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		if(worldIn.getBlockState(pos).getBlock()==Blocks.AIR){
		return EnumActionResult.FAIL;
		}
		else{
		IBlockState iblockstate = worldIn.getBlockState(pos);
        IBlockState frame = ColourfulBlocks.cpillar.state;
        //PortalFrameTE te = new PortalFrameTE();
       // te.setStack(iblockstate.getBlock().getItem(worldIn, pos, iblockstate));
        worldIn.setBlockState(pos, frame, 10);
        TileEntity te = worldIn.getTileEntity(pos);
        if(te instanceof PortalFrameTE){
        	((PortalFrameTE)te).setStack(iblockstate.getBlock().getItem(worldIn, pos, iblockstate));
        }
		return EnumActionResult.SUCCESS;
		}
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		stack = stack.copy();
		stack.attemptDamageItem(1, itemRand);
		return stack;
	}
	
}
