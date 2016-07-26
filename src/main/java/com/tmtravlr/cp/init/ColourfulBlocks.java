package com.tmtravlr.cp.init;

import com.tmtravlr.cp.block.ColourfulAltar;
import com.tmtravlr.cp.block.ColourfulPillar;
import com.tmtravlr.cp.block.ColourfulPortal;
import com.tmtravlr.cp.block.ColourfulReceiver;

public class ColourfulBlocks {

	//public static BlockColourfulWater cwater;
	public static ColourfulAltar caltar;
	public static ColourfulPillar cpillar;
	public static ColourfulPortal cportal;
	public static ColourfulReceiver crec;
	
	public static void init(){
		caltar = new ColourfulAltar();
		cpillar = new ColourfulPillar();
		cportal = new ColourfulPortal();
		crec = new ColourfulReceiver();
	}
	public static void initModels(){
		caltar.initModel();
		cpillar.initModel();
		cportal.initModel();
		crec.initModel();
	}
	
}
