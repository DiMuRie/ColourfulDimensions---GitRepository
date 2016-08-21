package com.tmtravlr.cp.init;

import com.tmtravlr.cp.item.Framer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulItems {
	
	public static CreativeTabs cpTab = new CreativeTabs("colourfulPortals")
	{
		public Item getTabIconItem()
		{
			return rainbowTemplate;
		}
	};
	
	public static Framer rainbowTemplate;
	
	public static void init(){
		rainbowTemplate = new Framer();
	}
	
	public static void initModels(){
		rainbowTemplate.initModel();
	}
	
}
