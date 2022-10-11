package com.imeguras.falter;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.imeguras.falter.core.PilotManual;
import com.imeguras.falter.proxy.IProxy;
@Mod(modid=PilotManual.MOD_ID, name=PilotManual.MOD_NAME, version=PilotManual.VERSION)
public class Falter{
	
    @Mod.Instance(PilotManual.MOD_ID)
    public static Falter instance;

	public static Logger logger = LogManager.getLogger("Falter");
    
	@SidedProxy(clientSide = "ClientProxy", serverSide = "ServerProxy")
	public static IProxy proxy;
	
	@EventHandler
    public void init(FMLInitializationEvent event){
		
		// some example code
        //System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
		
	}
	

}
