package com.imeguras.falter.items;

import com.imeguras.falter.Falter;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemLoader {
	public static Item filter = new ItemFilter().setCreativeTab(Falter.tabFalter).setUnlocalizedName("filter");
	public static Item dirty_filter = new ItemFilter(filterType.DIRTY_FILTER).setCreativeTab(Falter.tabFalter).setUnlocalizedName("dirty_filter");
	public static Item charcoal_filter = new ItemFilter(filterType.CHARCOAL_FILTER).setCreativeTab(Falter.tabFalter).setUnlocalizedName("charcoal_filter");

	public ItemLoader() {
		registerItem(filter);
		registerItem(dirty_filter);
		registerItem(charcoal_filter); 
	}
	private void registerItem(Item i) {
		String name = i.getUnlocalizedName().replace("item.", "");
		GameRegistry.registerItem(i, name);
		
	}
}
