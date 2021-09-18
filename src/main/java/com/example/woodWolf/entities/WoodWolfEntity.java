package com.example.woodWolf.entities;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.woodWolf.init.ItemInit;
import com.example.woodWolf.init.ModEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class WoodWolfEntity extends BaseWolfEntity {
	private static final Logger LOGGER = LogManager.getLogger();
	private boolean isAdult = false;

	// zit met de animatie wanneer die dood gaat en hem weer laten spawnen
	
	//kijk in canSpawn() methode in MobEntity class zit er 1

//https://github.com/DaRealTurtyWurty/1.15-Tut-Mod/blob/master/src/main/java/com/turtywurty/tutorialmod/entities/ExampleEntity.java
	public WoodWolfEntity(EntityType<? extends WoodWolfEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub

		this.setBoneItem(ItemInit.example_item.get());

		setGender(Gender.values()[this.rand.nextInt(2)]);
		//LOGGER.info(getGender());
	}

	public void livingTick() {
		super.livingTick();
		if (this.isAlive()) {
			//random wild breeding: 'heat'
			if(!this.isInLove() && !this.isTamed() && !this.isChild()) {
				if(this.rand.nextInt(100000) <= 25) {
					this.setInLove(2000000000);
					LOGGER.info("\n" + this.getGender() + " i am in heat~\ni need to breed you~");
				}
			}

			if(!this.isChild() && !isAdult) {
				this.targetSelector.addGoal(4, new NonTamedTargetGoal<>(this, PlayerEntity.class, false, null));
				this.targetSelector.addGoal(4, new NonTamedTargetGoal<>(this, CreeperEntity.class, false, null));
				//this.targetSelector.removeGoal(task);
				isAdult = true;
			}
		}
	}

	protected boolean isDespawnPeaceful() {
		 return !this.isTamed();
	}

	@Override
	public boolean canMateWith(AnimalEntity otherAnimal) {
		BaseWolfEntity wolfentity = (BaseWolfEntity)otherAnimal;
		if(!wolfentity.isTamed() && !this.isTamed() && !wolfentity.isChild() && !this.isChild() && wolfentity.getGender() != this.getGender()) {
			return this.isInLove() && wolfentity.isInLove();
		}
		else {
			return super.canMateWith(otherAnimal);
		}
	}

	//is dit wat zorgt dat player niet kan slapen? door dat er een mob in de buurt is zo ja dan doe if isWild
	public boolean isPreventingPlayerRest(PlayerEntity playerIn) {
		return true;
	}

	@Override
	public BaseWolfEntity createChild(AgeableEntity parent) {
		WoodWolfEntity child = new WoodWolfEntity(ModEntityTypes.WOODWOLF_ENTITY.get(), this.world);
		LOGGER.info("\n" + getGender() + " puppy :D");
		child.experienceValue = 1000000;//test van child breed exp
		return createChild(parent, child);
	}

	// gebruik dit voor item merge?...
	public void respawn(WoodWolfEntity woodWolfEntity, DamageSource cause) {
		WoodWolfEntity entity = new WoodWolfEntity(ModEntityTypes.WOODWOLF_ENTITY.get(), this.world);
		entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)),
				SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
		// entity.setGlowing(true);
		entity.setLocationAndAngles(woodWolfEntity.lastTickPosX, woodWolfEntity.lastTickPosY,
				woodWolfEntity.lastTickPosZ, woodWolfEntity.prevRotationYaw, woodWolfEntity.prevRotationPitch);
		entity.setAttackTarget(woodWolfEntity.getAttackTarget());

		// voor baasje van mijn cute timber wolfs
		UUID uuid = woodWolfEntity.getOwnerId();
		if (uuid != null) {
			entity.setOwnerId(uuid);
			entity.setTamed(true);
			entity.canBeRidden(entity);
		}
		LOGGER.info("i will die " + entity.dead);
		super.onDeath(cause);
		// deze wacht tijd werkt niet
		//new wait time
		entity.setIdleTime(100);
		LOGGER.info(entity.dead);
		this.world.addEntity(entity);
	}

	// test van wolf dead and respawn
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		// createChild(this);
		// respawn(this, cause);
	}

	// test van bang maken van creepers

	@Override
	public void onStruckByLightning(LightningBoltEntity lightningBolt) {
		this.setAngry(true);
	}

}
