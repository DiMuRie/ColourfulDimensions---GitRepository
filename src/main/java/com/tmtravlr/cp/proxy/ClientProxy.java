package com.tmtravlr.cp.proxy;

import com.tmtravlr.cp.CPLib;
import com.tmtravlr.cp.init.ColourfulItems;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*This is the client proxy class
 * for the Colourful Portals Mod
 * Created by DiMuRie
 */
public class ClientProxy implements ICommonProxy {
	public static int renderPass;

	@Override
	public void registerEventHandlers() {
	}

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		OBJLoader.INSTANCE.addDomain(CPLib.MODID);
		
	}

	@Override
	public void init(FMLInitializationEvent e) {
		ColourfulItems.initModels();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		
	}

}
