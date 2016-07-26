package com.tmtravlr.cp;

import com.tmtravlr.cp.dimension.ColourfulWorldProvider;
import com.tmtravlr.cp.init.ColourfulBlocks;
import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.init.ColourfulTileEntities;
import com.tmtravlr.cp.proxy.ICommonProxy;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CPLib.MODID, name = CPLib.NAME, version = CPLib.VERSION/*, acceptedMinecraftVersions = ""*/)
public class CP {


	@Instance
	public static CP cpInstance;

	@SidedProxy(clientSide = CPLib.CLIENT_PROXY_CLASS, serverSide = CPLib.SERVER_PROXY_CLASS)
	public static ICommonProxy proxy;
	
	public DimensionType colourfulDimensionType;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println(CPLib.MODID + " preinitialized");
		ColourfulItems.init();
		ColourfulBlocks.init();
		ColourfulTileEntities.init();
		proxy.preInit();
		colourfulDimensionType = DimensionType.register("ColourfulPortalHub", "colourfulportals:", 17, ColourfulWorldProvider.class, true);      					
		
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		System.out.println(CPLib.MODID + " initialized");
		proxy.init();
		proxy.registerEventHandlers();
		DimensionManager.registerDimension(17, DimensionType.OVERWORLD);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		System.out.println(CPLib.MODID + " postinitialized");
	}

}
