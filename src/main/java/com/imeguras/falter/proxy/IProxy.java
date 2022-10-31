package com.imeguras.falter.proxy;

import javax.annotation.Nullable;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;

public interface IProxy 
{
	public void preInit(FMLPreInitializationEvent event);
	
	public void init(FMLInitializationEvent event);
	
	public void postInit(FMLPostInitializationEvent event);
	
	
	
	@Nullable
	public EntityPlayer getClientMinecraftPlayer();
	
	public boolean isClientSided();
	
	@Nullable
	public Boolean isClientConnectedToServer();
	
	public void spawnClientParticle(World world, String type, double xPos, double yPos, double zPos, double motionX, double motionY, double motionZ);
}
