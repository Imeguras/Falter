package com.imeguras.falter.core.player;


import com.imeguras.falter.libs.Annotations.Defaulted;

import io.netty.buffer.ByteBuf;

import com.imeguras.falter.libs.Annotations.Clamped;

public class PlayerStats implements Cloneable{
	/* Default Thirst */
	@Defaulted(defaulted=20)
	@Clamped(min = 0, max = 20)
	public int thirstLevel;

	@Defaulted(defaulted=5.0d)
	@Clamped(min = 0, max = 5.0d)
	public double thirstSaturation;
	
	@Defaulted(defaulted=300)
	@Clamped(min = 0, max = 9000)
	public double temperature;

	/* Syndrome Component */
	@Defaulted(defaulted = 310)
	@Clamped(min = 0, max = 325)
	public double bodyTemperature;
	/* Advanced Nutrient Component */
	public PlayerStats(){
		//TODO remove publics and generate the getters and setters by annotation, all clamped and defaulted, unless im missing something lombok wont suffice
		thirstLevel=20;
		thirstSaturation=5.0d;
		temperature=300;
		bodyTemperature=310;

	}
	@Override
    public PlayerStats clone(){
        PlayerStats clone=new PlayerStats();
		clone.thirstLevel=this.thirstLevel;
		clone.thirstSaturation=this.thirstSaturation;
		clone.temperature=this.temperature;
		clone.bodyTemperature=this.bodyTemperature;
		return clone;
	}
	public void unserialize(ByteBuf buf){
		thirstLevel=buf.readInt();
		thirstSaturation=buf.readDouble();
		temperature=buf.readDouble();
		bodyTemperature=buf.readDouble();
	}
	public void serialize(ByteBuf buf){
		buf.writeInt(thirstLevel);
		buf.writeDouble(thirstSaturation);
		buf.writeDouble(temperature);
		buf.writeDouble(bodyTemperature);
	}
	
}
