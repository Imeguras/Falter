package com.imeguras.falter.core.client.gui;
import java.util.Random;

import com.imeguras.falter.core.PilotManual;
import com.imeguras.falter.core.config.ConfigManual;
import com.imeguras.falter.core.player.PlayerStats;
import com.imeguras.falter.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiRenderBar {
	public static Minecraft mc = Minecraft.getMinecraft();
	public static Random rand = new Random();
	public static int updateCounter;
	
	public static int left_height = 39;
    public static int right_height = 39;

    public static void renderThirst(ConfigManual config, int horizontalMargin, int verticalMargin) {
		if(!config.FLAG_THIRST){
			System.out.println("Thirst is disabled, no UI drawn");
			return;
		}
		bind(new ResourceLocation(PilotManual.ThirstBarIcons));

		PlayerStats stats = CommonProxy.getPlayerStats(mc.thePlayer);

		GuiIngame ingameGUI = mc.ingameGUI;
		updateCounter = ingameGUI.getUpdateCounter();
	
		for (int i = 0; i < 10; i++) {
			int width = config.FLAG_METER_ON_LEFT ? ((horizontalMargin / 2) - 91) + (i * 8) : ((horizontalMargin / 2) + 91) - (i * 8) - 9;
			int height = verticalMargin - 49;
			int xStart = 1;
			int yStart = 1;
			int yEnd = 9;
			int xEnd = 7;
			//jiggle animation
			if (mc.thePlayer.getFoodStats().getSaturationLevel() <=0.0 && updateCounter % (stats.thirstLevel * 3 + 1) == 0){
				height += rand.nextInt(3) - 1;
			}
			
			
			ingameGUI.drawTexturedModalRect(width, height, xStart, yStart, xEnd, yEnd);
			
			if(i * 2 + 1 < stats.thirstLevel) {
				ingameGUI.drawTexturedModalRect(width, height, xStart + 8, yStart, xEnd, yEnd);
			} else if(i * 2 + 1 == stats.thirstLevel) {
				ingameGUI.drawTexturedModalRect(width, height, xStart + 16, yStart, xEnd, yEnd);
			}
			/*} else {
				if (i * 2 + 1 < stats.level) {
					ingameGUI.drawTexturedModalRect(width, height, xStart + 24, yStart, xEnd, yEnd);
				} else if (i * 2 + 1 == stats.level) {
					ingameGUI.drawTexturedModalRect(width, height, xStart + 32, yStart, xEnd, yEnd);
				}
			} */
		}
		bind(Gui.icons);
	}
    
    /*public static void drawTemperature(int w, int h) {
    	float temp = ClientStats.getInstance().temperature;
    	GuiIngame ingameGUI = mc.ingameGUI;
    	ingameGUI.drawCenteredString(mc.fontRendererObj, Float.toString(temp), 200, 100, 0xffffff);
    }*/

	private static void bind(ResourceLocation res) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(res);
	}
}

