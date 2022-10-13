package com.imeguras.falter.core.config;

//A class with defaults for config values, it help making config handler clean.
public class ConfigManual {
	private static ConfigManual INSTANCE; 
	public boolean FLAG_METER_ON_LEFT= false;
	public boolean FLAG_DEATH_FROM_THIRST=true;
	public boolean FLAG_DISABLE_THIRST_LOSS_FROM_SLEEP=false;

	public float BASE_NIGHT_RATE=0.4f;
	public float BASE_DESERT_RATE=2f;
	public float BASE_IN_WATER_RATE=0.03f;
	public float BASE_WALKING_RATE=0.01f;
	public float BASE_RUNNING_RATE=0.1f;
	public float BASE_JUMP_RATE=0.03f;
	
	public boolean load(){
		try{
			FLAG_METER_ON_LEFT=ConfigHandler.config.get("GUI","Flip ThirstGUI",FLAG_METER_ON_LEFT,"If true, thirst meter will be on the left side of the screen.").getBoolean();
			FLAG_DEATH_FROM_THIRST=ConfigHandler.config.get("General","Deadly Thirst",FLAG_DEATH_FROM_THIRST,"If true, player will die from thirst.").getBoolean();
			FLAG_DISABLE_THIRST_LOSS_FROM_SLEEP=ConfigHandler.config.get("General","Disable Thirst Loss From Sleep",FLAG_DISABLE_THIRST_LOSS_FROM_SLEEP,"If true, player will not lose thirst while sleeping.").getBoolean();
			BASE_NIGHT_RATE=ConfigHandler.config.getFloat("Night Rate", "Base Rates", BASE_NIGHT_RATE, 0, 100, "The rate at which thirst will decrease at night.");
			BASE_DESERT_RATE=ConfigHandler.config.getFloat("Desert Rate", "Base Rates", BASE_DESERT_RATE, 0, 100, "The rate at which thirst will decrease in desert biomes.");
			BASE_IN_WATER_RATE=ConfigHandler.config.getFloat("In Water Rate", "Base Rates", BASE_IN_WATER_RATE, 0, 100, "The rate at which thirst will decrease while in water.");
			BASE_WALKING_RATE=ConfigHandler.config.getFloat("Walking Rate", "Base Rates", BASE_WALKING_RATE, 0, 100, "The rate at which thirst will decrease while walking.");
			BASE_RUNNING_RATE=ConfigHandler.config.getFloat("Running Rate", "Base Rates", BASE_RUNNING_RATE, 0, 100, "The rate at which thirst will decrease while running.");
			BASE_JUMP_RATE=ConfigHandler.config.getFloat("Jumping Rate", "Base Rates", BASE_JUMP_RATE, 0, 100, "The rate at which thirst will decrease while jumping.");

			if(ConfigHandler.config.hasChanged())
				ConfigHandler.config.save();
		}catch(Exception e){
			System.err.println("Error loading config values: " + e.getMessage());
			return false;
		}
		return true; 
	}
	
	public static ConfigManual getInstance(){
		if(INSTANCE==null){
			INSTANCE=new ConfigManual();
		}
		return INSTANCE;
	}
	public ConfigManual(){
		if(INSTANCE==null){
			INSTANCE=this;
		}
	}
}
