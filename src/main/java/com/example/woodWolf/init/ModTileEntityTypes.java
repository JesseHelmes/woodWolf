package com.example.woodWolf.init;

import com.example.woodWolf.WoodWolf;
import com.example.woodWolf.tileentity.BlackStoneTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, WoodWolf.MOD_ID);

	public static final RegistryObject<TileEntityType<BlackStoneTileEntity>> BLACK_STONE = 
			TILE_ENTITY_TYPES.register("dark_stone", 
					() -> TileEntityType.Builder.create(BlackStoneTileEntity::new, BlockInit.DARK_STONE.get()).build(null));
}