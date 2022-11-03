package com.imeguras.falter.proxy;

import com.imeguras.falter.core.client.gui.GuiRenderBar;
import com.imeguras.falter.core.config.ConfigManual;
import com.imeguras.falter.core.player.PlayerStats;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ClientProxy extends CommonProxy {
	public static PlayerStats ps; 
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		if(ps==null){
			ps=new PlayerStats();
		}
	}
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
	}
	@Override
	public void postInit(FMLPostInitializationEvent event){
		super.postInit(event);
	}
	@Override
	protected void playerClientTick(){
		
	}

	@Override
	public boolean isClientSided(){
		return true;
	}
	
}
	

