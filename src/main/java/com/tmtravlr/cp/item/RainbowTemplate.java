package com.tmtravlr.cp.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.tmtravlr.cp.init.ColourfulItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RainbowTemplate extends Item {

	public RainbowTemplate(){
        setRegistryName("rainbow_template");
        setUnlocalizedName("rainbow_template");     
        setCreativeTab(ColourfulItems.cpTab);
        setMaxStackSize(63);
        GameRegistry.register(this);
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> list, boolean par4)
    {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "This is a crafting item");
            list.add(TextFormatting.WHITE + "Suround with it in a crafting grid a water bucket");
            list.add(TextFormatting.WHITE + "To get a bucket of colourful water.");
            list.add(TextFormatting.WHITE + "Max stack size is 63 ;)");
        } else {
            list.add(TextFormatting.WHITE + "Hold Shift for info");
        }
    }
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
