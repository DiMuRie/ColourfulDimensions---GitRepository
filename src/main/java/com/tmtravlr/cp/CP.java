package com.tmtravlr.cp;

import java.util.LinkedList;

import org.apache.logging.log4j.Logger;

import com.tmtravlr.cp.init.ColourfulBlocks;
import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.init.ColourfulTileEntities;
import com.tmtravlr.cp.proxy.ICommonProxy;

import net.minecraft.world.DimensionType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CPLib.MODID, name = CPLib.NAME, version = CPLib.VERSION/*, acceptedMinecraftVersions = ""*/)
public class CP {


	@Instance
	public static CP cpInstance;
	
	public static Logger logger;
	
	@SidedProxy(clientSide = CPLib.CLIENT_PROXY_CLASS, serverSide = CPLib.SERVER_PROXY_CLASS)
	public static ICommonProxy proxy;
	
	public DimensionType colourfulDimensionType;
	
	static LinkedList<String> list = new LinkedList();
	
	static{
		list.add("thanks for");
		list.add("using");
		list.add("the");
		list.add("colourful");
		list.add("portals");
		list.add("mod!!!!!");
	}
	int i = 0;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println(CPLib.MODID + " preinitialized");
		logger = e.getModLog();
		ColourfulItems.init();
		ColourfulBlocks.init();
		ColourfulTileEntities.init();
		proxy.preInit(e);      
		for(i=0; i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		System.out.println(CPLib.MODID + " initialized");
		proxy.init(e);
		proxy.registerEventHandlers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		System.out.println(CPLib.MODID + " postinitialized");
		proxy.postInit(e);
	}

}
