package com.tmtravlr.cp.proxy;

import org.apache.logging.log4j.Level;

import com.tmtravlr.cp.CP;

import net.minecraftforge.common.config.Configuration;

public class Config {
	private static final String CATEGORY_GENERAL = "general";
    public static boolean dummy = true;
    
    public static void readConfig() {
        Configuration cfg = ServerProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            CP.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        dummy = cfg.getBoolean("goodTutorial", CATEGORY_GENERAL, dummy, "am i a dummy or not?");

    }
}
