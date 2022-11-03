package com.imeguras.falter.proxy;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Nullable;

import com.imeguras.falter.core.config.ConfigHandler;
import com.imeguras.falter.core.player.PlayerStats;
import com.imeguras.falter.core.player.ThirstHandler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;


public class CommonProxy implements IProxy{
	private static HashMap<UUID, ThirstHandler> playerStats;
	

	@Override
	public void preInit(FMLPreInitializationEvent event){
		//Ready the config stuff
		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		
	}

	@Override
	public void init(FMLInitializationEvent event){
		playerStats=new HashMap<UUID, ThirstHandler>();
		
		

	}

	@Override
	public void postInit(FMLPostInitializationEvent event){
	}
	
	@Override
	public void spawnClientParticle(World world, String type, double xPos, double yPos, double zPos, double motionX, double motionY, double motionZ){
		if(!world.isRemote)
			return;
		EntityFX particle = null;
		//Custom particles
		if(particle != null){
			Minecraft.getMinecraft().effectRenderer.addEffect(particle);
		}
	}

	@Override
	@Nullable
	public EntityPlayer getClientMinecraftPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public boolean isClientSided(){
		return false;
	}

	@Override
	@Nullable
	public Boolean isClientConnectedToServer() {
		if(Minecraft.getMinecraft().isSingleplayer()||Minecraft.getMinecraft().getNetHandler().getNetworkManager().isChannelOpen()){
			return true;
		}
		
		return false;
	}

	public void serverAboutToStart(FMLServerAboutToStartEvent event) {
		
	}

	public void serverStarting(FMLServerStartingEvent event) {
		
	}

	public static void putPlayerStats(EntityPlayer player, ThirstHandler hand){
		//Avoid boogaloo
		removePlayerStats(player);
		playerStats.put(player.getUniqueID(), hand);
	}
	//TOFACTOR: better name
	public static ThirstHandler getPlayerThirst(EntityPlayer player){
		ThirstHandler stats;
		try{
			if(!playerStats.containsKey(player.getUniqueID())){
				throw new Exception("Such Entity isn't present in the server");
			}
			//.clone()
			stats=playerStats.get(player.getUniqueID());
		}catch(Exception e){
			
			stats=new ThirstHandler();
			e.printStackTrace();
		}
		return stats;
		
	}
	//TOCHECK: 5 hours of sleep doesnt do good to coherency
	public static PlayerStats getPlayerStats(EntityPlayer player){
		PlayerStats stats;
		try{
			if(!playerStats.containsKey(player.getUniqueID())){
				throw new Exception("Such Entity isn't present in the server");
			}
			stats=playerStats.get(player.getUniqueID()).stats.clone();
		}catch(Exception e){
			stats=new PlayerStats();
			e.printStackTrace();
		}
		return stats;
		
	}
	public static void removePlayerStats(EntityPlayer player){
		if(playerStats.containsKey(player.getUniqueID())){
			playerStats.remove(player.getUniqueID());
		}
	}
	@SubscribeEvent

	public void playerTick(PlayerTickEvent event) {
	
		if(event.side==Side.SERVER){
			//System.out.println("GEX! HAAAAAAAAAAAAAAAAAA"+event.player.getUniqueID());
			playerServerTick(event.player);
		}else{
			playerClientTick();
		}
	
	}	
	protected void playerClientTick() {
		
	}
	private void playerServerTick(EntityPlayer player) {
		ThirstHandler t=getPlayerThirst(player);
		t.onTick();
	}
	
}