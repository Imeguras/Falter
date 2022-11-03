package com.imeguras.falter.core.player;

import com.imeguras.falter.Falter;
import com.imeguras.falter.core.PilotManual;
import com.imeguras.falter.core.config.ConfigManual;
import com.imeguras.falter.core.network.NetworkHandler;
import com.imeguras.falter.core.network.PlayerPacket;
import com.imeguras.falter.libs.Constants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;

public class ThirstHandler implements Cloneable{
	public EntityPlayer player;
	public PlayerStats stats;
	public DamageSource thirstSource;
	private ConfigManual configMan = ConfigManual.getInstance();
	public float timer; 
	
	//No reading io stuff here
	public ThirstHandler(){
		this.stats = new PlayerStats();
		this.thirstSource = new DamageSource("thirst");
	}
	public ThirstHandler(EntityPlayer player){
		this();
		this.player = player;	
		
	}
	public void onTick() {
		EnumDifficulty difficultyID =player.worldObj.difficultySetting;
		float accumulator= 0.0F;
		switch (difficultyID) {
			case EASY:
				accumulator+=2;
				break; 
			case NORMAL:
				accumulator+=4;
				break;
			case HARD:
				accumulator+=8;
				break;
			case PEACEFUL:
			default:
				accumulator++; 
				break;
		}
		if(timer >= Constants.thirstTimerTarget) {
			dryPlayer();
			timer = 0;
		}
		accumulator*=(configMan.BASE_GENERAL_THIRST_LOSS_RATE);
		timer+=accumulator;
	}
	public void dryPlayer(){
		stats.thirstSaturation-=1*stats.temperature/(player.experienceLevel+1)*Constants.thirstTempXPweight;
		if(stats.thirstSaturation<=0){
			stats.thirstLevel-=1;
			stats.thirstSaturation=0;
		}
		if(stats.thirstLevel<=0){
			if(player.getHealth()>1 || configMan.FLAG_DEATH_FROM_THIRST){
				player.attackEntityFrom(thirstSource, 1);
			}
			stats.thirstLevel=0;
		}
		System.out.println("SERVER Thirst: "+stats.toString());
		EntityPlayerMP t = (EntityPlayerMP) player;
		NetworkHandler.networkWrapper.sendTo(new PlayerPacket(stats), t);
		this.writeData();
	}
	public void readNBTData() {
		if (player != null) {
			NBTTagCompound oldnbt = player.getEntityData();
			NBTTagCompound nbt = oldnbt.getCompoundTag(PilotManual.MOD_ID);
			if (nbt.hasKey("thirstLevel")) {
				stats.thirstLevel = nbt.getInteger("thirstLevel");
				//thirstExhaustion = nbt.getFloat("exhaustion");
				stats.thirstSaturation = nbt.getDouble("thirstSaturation");
				timer = nbt.getFloat("thirstTimer");
				
			}else{
				// Print out a warning message
				Falter.logger.warn("It appears that either theres no party or we weren't invited to this one, No NBT data found for thirstLevel");
			}
		}
	}
	public void writeData() {
		if (player != null) {
			NBTTagCompound oldNBT = player.getEntityData();
			NBTTagCompound nbt = oldNBT.getCompoundTag(PilotManual.MOD_ID);
			if (!oldNBT.hasKey(PilotManual.MOD_ID)) {
				oldNBT.setTag(PilotManual.MOD_ID, nbt);
			}
			nbt.setInteger("thirstLevel", stats.thirstLevel);
			
			nbt.setDouble("thirstSaturation", stats.thirstSaturation);
			nbt.setFloat("thirstTimer", timer);
		} 
	}
	@Override
	public ThirstHandler clone() {
		ThirstHandler clone = new ThirstHandler();
		clone.player = this.player;
		clone.stats = this.stats;
		clone.thirstSource = this.thirstSource;
		clone.timer = this.timer;
		return clone;
	}
}
