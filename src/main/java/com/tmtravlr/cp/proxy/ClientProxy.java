package com.tmtravlr.cp.proxy;

import com.tmtravlr.cp.CPLib;
import com.tmtravlr.cp.init.ColourfulItems;

import net.minecraftforge.client.model.obj.OBJLoader;

/*This is the client proxy class
 * for the Colourful Portals Mod
 * Created by DiMuRie
 */
public class ClientProxy implements ICommonProxy {
	public static int renderPass;

	@Override
	public void init() {
		ColourfulItems.initModels();
	}

	@Override
	public void registerEventHandlers() {
	}

	@Override
	public void preInit() {
		OBJLoader.INSTANCE.addDomain(CPLib.MODID);
		
	}

}
