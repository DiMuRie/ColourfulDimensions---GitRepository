package com.tmtravlr.cp.proxy;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*This is the server proxy class
 * for the Colourful Portals Mod
 * Created by DiMuRie
 */
public class ServerProxy implements ICommonProxy {

    public static Configuration config;

	@Override
	public void registerEventHandlers() {
	}

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "modtut.cfg"));
        Config.readConfig();
        
	}

	@Override
	public void init(FMLInitializationEvent e) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		if (config.hasChanged()) {
            config.save();
        }
	}

}
