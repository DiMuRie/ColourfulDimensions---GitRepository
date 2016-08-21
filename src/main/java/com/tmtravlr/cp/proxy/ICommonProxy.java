package com.tmtravlr.cp.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*This is the interface for the server and client proxy class
 * for the Colourful Portals Mod
 * Created by DiMuRie
 */
public interface ICommonProxy {

	public void preInit(FMLPreInitializationEvent e);
	
	public void init(FMLInitializationEvent e);
	
	public void postInit(FMLPostInitializationEvent e);

	public void registerEventHandlers();
	
}
