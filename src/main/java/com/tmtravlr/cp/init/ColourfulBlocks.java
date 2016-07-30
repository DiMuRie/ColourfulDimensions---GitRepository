package com.tmtravlr.cp.init;

import com.tmtravlr.cp.block.Block000x;
import com.tmtravlr.cp.block.Block00x;
import com.tmtravlr.cp.block.Block0x;
import com.tmtravlr.cp.block.Blockx;
import com.tmtravlr.cp.block.ColourfulAltar;
import com.tmtravlr.cp.block.ColourfulPillar;
import com.tmtravlr.cp.block.ColourfulPortal;

public class ColourfulBlocks {

	public static ColourfulAltar caltar;
	public static ColourfulPillar cpillar;
	public static ColourfulPortal cportal;
	public static Blockx x;
	public static Block0x zx;
	public static Block00x zzx;
	public static Block000x zzzx;
	
	public static void init(){
		caltar = new ColourfulAltar();
		cpillar = new ColourfulPillar();
		cportal = new ColourfulPortal();
		x = new Blockx();
		zx = new Block0x();
		zzx = new Block00x();
		zzzx = new Block000x();
	}
	public static void initModels(){
		caltar.initModel();
		cpillar.initModel();
		cportal.initModel();
		x.initModel();
		zx.initModel();
		zzx.initModel();
		zzzx.initModel();
	}
	
}
