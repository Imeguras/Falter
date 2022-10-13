package com.imeguras.falter.proxy;

import javax.annotation.Nullable;

import com.imeguras.falter.core.config.ConfigHandler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IProxy{
	@Override
	public void preInit(FMLPreInitializationEvent event){
		//Ready the config stuff
		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());

	}

	@Override
	public void init(FMLInitializationEvent event){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event){
		// TODO Auto-generated method stub
		
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
}