package com.imeguras.falter.core;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.HashMap;

import com.imeguras.falter.Falter;
import com.imeguras.falter.core.client.gui.GuiRenderBar;
import com.imeguras.falter.core.config.ConfigManual;
import com.imeguras.falter.core.network.NetworkHandler;
import com.imeguras.falter.core.network.PlayerPacket;
import com.imeguras.falter.core.player.PlayerStats;
import com.imeguras.falter.core.player.ThirstHandler;
import com.imeguras.falter.proxy.ClientProxy;
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

	@SubscribeEvent
	public void onPlayerRightClick(PlayerInteractEvent event){
		
		//TODO: Isn't there a more specific event for this? Also i sorted by "probability" so this wouldnt be such a "performance hog"
		if(event.world.getBlock(event.x, event.y, event.z) == net.minecraft.init.Blocks.water &&
		event.entityPlayer.getHeldItem() == null &&
		event.entityPlayer.isSneaking() &&
		event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK
		){
			ThirstHandler t=CommonProxy.getPlayerThirst(event.entityPlayer);
			t.stats.thirstLevel+=5; 
			//TODO: REMOVE THIS
			if(t.stats.thirstLevel>20)
				t.stats.thirstLevel=20;
			
			NetworkHandler.networkWrapper.sendTo(new PlayerPacket(t.stats), (EntityPlayerMP) event.entityPlayer);
			event.setCanceled(true);
			
		}
			
		
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
						//get client proxy
						
						GuiRenderBar.renderThirst(ConfigManual.getInstance(), width, height);
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
		temp.readNBTData();
		CommonProxy.putPlayerStats(event.player, temp);
		NetworkHandler.networkWrapper.sendTo(new PlayerPacket(temp.stats), (EntityPlayerMP)event.player);
	}	
	@SubscribeEvent
	public void onLogout(PlayerLoggedOutEvent event) {
		CommonProxy.getPlayerThirst(event.player).writeData();
	
		CommonProxy.removePlayerStats(event.player);
	}
	

}
