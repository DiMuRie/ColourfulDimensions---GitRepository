package com.tmtravlr.cp.init;

import com.tmtravlr.cp.te.ColourfulPortalTE;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ColourfulTileEntities {

	public static void init() {
        GameRegistry.registerTileEntity(ColourfulPortalTE.class, "colourful_portal_te");
    }
	
}
