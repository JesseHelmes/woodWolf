package com.example.woodWolf.world.gen;

import com.example.woodWolf.init.ModEntityTypes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityGen {

	//https://www.youtube.com/watch?v=Vcz4Adcu5bY&ab_channel=TechnoVision
	public static void spawnEntityGen() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS) {
				biome.getSpawns(EntityClassification.MISC)
					.add(new Biome.SpawnListEntry(ModEntityTypes.WOODWOLF_ENTITY.get(), 10, 1, 4));
			}
		}
	}
}
