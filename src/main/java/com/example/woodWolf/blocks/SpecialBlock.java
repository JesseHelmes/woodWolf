package com.example.woodWolf.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpecialBlock extends Block{

	/*
	biome ling hive met anti magic ore als je in de buurt komt verlies je magie(of alleen block
	anders wordt het misschien te veel? lag) dit kan nog niet omdat forge de methode nog niet heeft geupdated
	 */
	public SpecialBlock(Properties properties) {
		super(properties);
	}

	//dit is doe ik want de tick gaat voor lagg zorgen
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		//changeling block
		if(entityIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)entityIn;
			player.clearActivePotions();
		}
	}

}
