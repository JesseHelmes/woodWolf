package com.example.woodWolf.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DarkStoneBlockItem extends BlockItem{

	public DarkStoneBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		PlayerEntity player = (PlayerEntity) entity;

		double distance = 10.0;
		if (entity instanceof PlayerEntity) {
			if (player.isInRangeToRenderDist(distance)){
				player.clearActivePotions();
			}
		}
	}
}
