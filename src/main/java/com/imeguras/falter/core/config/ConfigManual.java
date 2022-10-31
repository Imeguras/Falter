package com.imeguras.falter.core.config;

//A class with defaults for config values, it help making config handler clean.
public class ConfigManual {
	private static ConfigManual INSTANCE; 
	/* General flags */
	public boolean FLAG_THIRST = true;
	/* UI related flags */
	public boolean FLAG_METER_ON_LEFT= false;
	/* Gameplay flags */
	public boolean FLAG_DEATH_FROM_THIRST=true;
	public boolean FLAG_DISABLE_THIRST_LOSS_FROM_SLEEP=false;

	/* Gameplay rates */
	public float BASE_GENERAL_THIRST_LOSS_RATE=1f;
	public float BASE_NIGHT_RATE=0.4f;
	public float BASE_DESERT_RATE=2f;
	public float BASE_IN_WATER_RATE=0.03f;
	public float BASE_WALKING_RATE=0.01f;
	public float BASE_RUNNING_RATE=0.1f;
	public float BASE_JUMP_RATE=0.03f;
	
	public boolean load(){
		try{
			FLAG_THIRST = ConfigHandler.config.get("General", "flag_t_thirst", FLAG_THIRST, "If true, it will enable the thirst component, otherwise overwrites every other flag").getBoolean(FLAG_THIRST);
			FLAG_METER_ON_LEFT=ConfigHandler.config.get("GUI","flag_t_ui",FLAG_METER_ON_LEFT,"If true, thirst meter will be on the left side of the screen.").getBoolean();
			
			FLAG_DEATH_FROM_THIRST=ConfigHandler.config.get("Gameplay","flag_t_deadly",FLAG_DEATH_FROM_THIRST,"If true, player will die from thirst.").getBoolean();
			FLAG_DISABLE_THIRST_LOSS_FROM_SLEEP=ConfigHandler.config.get("Gameplay","flag_t_while_sleep",FLAG_DISABLE_THIRST_LOSS_FROM_SLEEP,"If true, player will not lose thirst while sleeping.").getBoolean();
			
			BASE_GENERAL_THIRST_LOSS_RATE=ConfigHandler.config.getFloat("general_t_rate","Base Rates",BASE_GENERAL_THIRST_LOSS_RATE,0,10,"Base rate for thirst loss.");
			BASE_NIGHT_RATE=ConfigHandler.config.getFloat("night_t_rate", "Base Rates", BASE_NIGHT_RATE, 0, 100, "The rate at which thirst will decrease at night.");
			BASE_DESERT_RATE=ConfigHandler.config.getFloat("desert_t_rate", "Base Rates", BASE_DESERT_RATE, 0, 100, "The rate at which thirst will decrease in desert biomes.");
			BASE_IN_WATER_RATE=ConfigHandler.config.getFloat("water_t_rate", "Base Rates", BASE_IN_WATER_RATE, 0, 100, "The rate at which thirst will decrease while in water.");
			BASE_WALKING_RATE=ConfigHandler.config.getFloat("walking_t_rate", "Base Rates", BASE_WALKING_RATE, 0, 100, "The rate at which thirst will decrease while walking.");
			BASE_RUNNING_RATE=ConfigHandler.config.getFloat("running_t_rate", "Base Rates", BASE_RUNNING_RATE, 0, 100, "The rate at which thirst will decrease while running.");
			BASE_JUMP_RATE=ConfigHandler.config.getFloat("jumping_t_rate", "Base Rates", BASE_JUMP_RATE, 0, 100, "The rate at which thirst will decrease while jumping.");

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
