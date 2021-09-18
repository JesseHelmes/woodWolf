package com.example.woodWolf.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpecialItem extends Item {

	public SpecialItem(Properties properties) {
		super(properties);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 1300;//6 items
	}

}
