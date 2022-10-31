package com.imeguras.falter.core;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import net.minecraftforge.client.event.RenderGameOverlayEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.HashMap;

import com.imeguras.falter.core.client.gui.GuiRenderBar;
import com.imeguras.falter.core.config.ConfigManual;
import com.imeguras.falter.core.player.PlayerStats;
import com.imeguras.falter.core.player.ThirstHandler;
import com.imeguras.falter.proxy.CommonProxy;


public class EventSystem implements IGuiHandler{
	private static ConfigManual configMan = ConfigManual.getInstance();
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		return null;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderBar(RenderGameOverlayEvent event) {
		
		int width = event.resolution.getScaledWidth();
	    int height = event.resolution.getScaledHeight();
		if(event.type != null) {
			switch(event.type) {
				case FOOD: {
					if (!Minecraft.getMinecraft().thePlayer.isRidingHorse()) {
						GuiRenderBar.renderThirst(configMan, width, height);
					}
					break;
				}
				default: break;
			}
		}
	}
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		ThirstHandler temp= new ThirstHandler(event.player);
		CommonProxy.putPlayerStats(event.player, temp);

	}
	@SubscribeEvent
	public void onLogout(PlayerLoggedOutEvent event) {
		CommonProxy.getPlayerThirst(event.player).writeData();
	
		CommonProxy.removePlayerStats(event.player);
	}
	
	@SideOnly(Side.CLIENT)
	public PlayerStats getPlayerStats(EntityPlayer player) {

		return CommonProxy.getPlayerStats(player);
	}


}
