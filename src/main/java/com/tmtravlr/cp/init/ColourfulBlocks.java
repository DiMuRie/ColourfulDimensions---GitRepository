package com.tmtravlr.cp.init;

import com.tmtravlr.cp.block.ColourfulPortal;
import com.tmtravlr.cp.block.PortalFrame;

public class ColourfulBlocks {

	public static PortalFrame cpillar;
	public static ColourfulPortal clayp;
	
	public static void init(){
		cpillar = new PortalFrame();
		clayp = new ColourfulPortal();
	}

	public static void initModels(){
		cpillar.initModel();
		clayp.initModel();
	}
	
}
