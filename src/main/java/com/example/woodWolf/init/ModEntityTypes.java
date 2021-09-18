package com.example.woodWolf.init;

import com.example.woodWolf.WoodWolf;
import com.example.woodWolf.entities.WoodWolfEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			WoodWolf.MOD_ID);

	public static final RegistryObject<EntityType<WoodWolfEntity>> WOODWOLF_ENTITY = ENTITY_TYPES
			.register("woodwolf",
					() -> EntityType.Builder.<WoodWolfEntity>create(WoodWolfEntity::new, EntityClassification.MISC)
							.size(0.6F, 0.85f)
							.build(new ResourceLocation(WoodWolf.MOD_ID, "woodwolf").toString()));
}
