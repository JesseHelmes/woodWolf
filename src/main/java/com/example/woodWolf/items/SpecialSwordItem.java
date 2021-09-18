package com.example.woodWolf.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpecialSwordItem extends SwordItem {

	public SpecialSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.setDamage(0);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (!worldIn.isRemote && (double) state.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.setDamage(0);
		}
		return true;
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		PlayerEntity player = (PlayerEntity) entity;

		double distance = 10.0;
		if (entity instanceof PlayerEntity) {
			if (player.isInRangeToRenderDist(distance)){
				player.clearActivePotions();
			}

			//player.removeActivePotionEffect(Effects.BLINDNESS);
		}
	}

}
