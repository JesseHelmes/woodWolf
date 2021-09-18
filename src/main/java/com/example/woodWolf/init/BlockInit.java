package com.example.woodWolf.init;

import com.example.woodWolf.WoodWolf;
import com.example.woodWolf.blocks.ModButtonBlock;
import com.example.woodWolf.blocks.ModPressurePlateBlock;
import com.example.woodWolf.blocks.SpecialBlock;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, WoodWolf.MOD_ID);

	//hardnessAndResistance
		//moet lastige worden om block te breken dus langer duren
	public static final RegistryObject<Block> DARK_STONE = BLOCKS.register("dark_stone",
			() -> new SpecialBlock(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5f, 15.0f)
					.sound(SoundType.STONE)));

	public static final RegistryObject<Block> DARK_STONE_STAIRS = BLOCKS.register("dark_stone_stairs",
			() -> new StairsBlock(() -> BlockInit.DARK_STONE.get().getDefaultState(),
					Block.Properties.from(BlockInit.DARK_STONE.get())));

	public static final RegistryObject<Block> DARK_STONE_FENCE = BLOCKS.register("dark_stone_fence",
			() -> new FenceBlock(Block.Properties.from(BlockInit.DARK_STONE.get())));

	public static final RegistryObject<Block> DARK_STONE_FENCE_GATE = BLOCKS.register("dark_stone_fence_gate",
			() -> new FenceGateBlock(Block.Properties.from(BlockInit.DARK_STONE.get())));

	public static final RegistryObject<Block> DARK_STONE_WALL = BLOCKS.register("dark_stone_wall",
			() -> new WallBlock(Block.Properties.from(BlockInit.DARK_STONE.get())));

	public static final RegistryObject<Block> DARK_STONE_SLAB = BLOCKS.register("dark_stone_slab",
			() -> new SlabBlock(Block.Properties.from(BlockInit.DARK_STONE.get())));

	//bekijk video moet misschien class maken voor deze 2
	public static final RegistryObject<Block> DARK_STONE_BUTTON = BLOCKS.register("dark_stone_button",
			() -> new ModButtonBlock(Block.Properties.from(BlockInit.DARK_STONE.get())));

	public static final RegistryObject<Block> DARK_STONE_PRESSURE_PLATE = BLOCKS.register("dark_stone_pressure_plate",
			() -> new ModPressurePlateBlock(Sensitivity.EVERYTHING,
					Block.Properties.from(BlockInit.DARK_STONE.get())));
}
