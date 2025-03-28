package com.example.woodWolf.entities.ai;

import java.util.EnumSet;

import com.example.woodWolf.entities.BaseWolfEntity;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class AIBegGoal extends Goal {
	private final BaseWolfEntity wolf;
	private PlayerEntity player;
	private final World world;
	private final float minPlayerDistance;
	private int timeoutCounter;
	private final EntityPredicate playerPredicate;

	public AIBegGoal(BaseWolfEntity wolf, float minDistance) {
		this.wolf = wolf;
		this.world = wolf.world;
		this.minPlayerDistance = minDistance;
		this.playerPredicate = (new EntityPredicate()).setDistance((double)minDistance).allowInvulnerable().allowFriendlyFire().setSkipAttackChecks();
		this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		this.player = this.world.getClosestPlayer(this.playerPredicate, this.wolf);
		return this.player == null ? false : this.hasTemptationItemInHand(this.player);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		if (!this.player.isAlive()) {
			return false;
		} else if (this.wolf.getDistanceSq(this.player) > (double)(this.minPlayerDistance * this.minPlayerDistance)) {
			return false;
		} else {
			return this.timeoutCounter > 0 && this.hasTemptationItemInHand(this.player);
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.wolf.setBegging(true);
		this.timeoutCounter = 40 + this.wolf.getRNG().nextInt(40);
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by another one
	 */
	public void resetTask() {
		this.wolf.setBegging(false);
		this.player = null;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		this.wolf.getLookController().setLookPosition(this.player.getPosX(), this.player.getPosYEye(), this.player.getPosZ(), 10.0F, (float)this.wolf.getVerticalFaceSpeed());
		--this.timeoutCounter;
	}

	/**
	 * Gets if the Player has the Bone in the hand.
	 */
	private boolean hasTemptationItemInHand(PlayerEntity player) {
		for(Hand hand : Hand.values()) {
			ItemStack itemstack = player.getHeldItem(hand);
			if (this.wolf.isTamed() && itemstack.getItem() == Items.BONE) {
				return true;
			}

			if (this.wolf.isBreedingItem(itemstack)) {
				return true;
			}
		}

		return false;
	}

}
