package com.imeguras.falter;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import cpw.mods.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.imeguras.falter.core.EventSystem;
import com.imeguras.falter.core.PilotManual;
import com.imeguras.falter.core.network.NetworkHandler;
import com.imeguras.falter.items.ItemLoader;
import com.imeguras.falter.proxy.CommonProxy;
import com.imeguras.falter.proxy.IProxy;
@Mod(modid=PilotManual.MOD_ID, name=PilotManual.MOD_NAME, version=PilotManual.VERSION)
public class Falter{
	
    @Mod.Instance(PilotManual.MOD_ID)
    public static Falter instance;
	
	public static EventSystem eventHook = new EventSystem();
	
	public static Logger logger = LogManager.getLogger("Falter");
    
	@SidedProxy(clientSide=PilotManual.PROXY_CLIENT, serverSide=PilotManual.PROXY_COMMON)
	public static IProxy proxy;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		FMLCommonHandler.instance().bus().register(eventHook);
		MinecraftForge.EVENT_BUS.register(eventHook);

		FMLCommonHandler.instance().bus().register(proxy);
		MinecraftForge.EVENT_BUS.register(proxy);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, eventHook);
		proxy.preInit(event);
	}
	@EventHandler
    public void init(FMLInitializationEvent event){
		proxy.init(event);
		new ItemLoader(); 
		new NetworkHandler();
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}
	//TODO ORGANIZE THIS LATER
	public static CreativeTabs tabFalter = new CreativeTabs("tabFalter"){
		@Override
		public Item getTabIconItem(){
			return Items.apple;
		}
	};
	
	

}
