package com.tmtravlr.cp.init;

import com.tmtravlr.cp.block.ColourfulAltar;
import com.tmtravlr.cp.block.ColourfulPillar;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ColourfulBlocks {

	//public static BlockColourfulWater cwater;
	public static ColourfulAltar caltar;
	public static ColourfulPillar cpillar;
	
	public static void init(){
		caltar = new ColourfulAltar();
		cpillar = new ColourfulPillar();
	}
	public static void initModels(){
		caltar.initModel();
		cpillar.initModel();
	}
	
}
