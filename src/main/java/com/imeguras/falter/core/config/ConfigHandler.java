package com.imeguras.falter.core.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

import com.imeguras.falter.core.PilotManual;


import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
public final class ConfigHandler {
	public static Configuration config;
	
	public static void loadConfig(File configFile) {
		config = new Configuration(configFile);
		config.load();

		load();

		FMLCommonHandler.instance().bus().register(new ChangeListener());
	}
	public static void load() {
		//Get a singleton/instance
		ConfigManual configMan= ConfigManual.getInstance();
		//load config values
		if(configMan.load()){
			System.out.println("Configuration Loaded Successfully");
		}
		
	}
	
	public static class ChangeListener {
		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.modID.equals(PilotManual.MOD_ID))
				load();
		}
	
	}
}
