package com.imeguras.falter.items;

import net.minecraft.item.Item;

public class ItemFilter extends Item {
	
	//0 = filter, 1 = dirty filter, 2 = charcoal filter
	public ItemFilter(filterType type) {
		this.setNoRepair();
	}
	public ItemFilter() {
		this(filterType.FILTER);
	}
}
enum filterType{
	FILTER(0),
	DIRTY_FILTER(1),
	CHARCOAL_FILTER(2);
	int value =0;
	filterType(int i) {
		value =i; 
	}

}