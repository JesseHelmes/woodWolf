package com.example.woodWolf.tileentity;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.woodWolf.init.ModTileEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;

public class BlackStoneTileEntity extends TileEntity implements ITickableTileEntity{

	public BlackStoneTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public BlackStoneTileEntity() {
		this(ModTileEntityTypes.BLACK_STONE.get());
	}
	private static final Logger LOGGER = LogManager.getLogger();
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		int radius = 12;
		LOGGER.info("adasd");
		if(!this.world.isRemote) {
			//double d0 = (double)(5);
			LOGGER.info("if");
			List<Entity> entities = this.world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius));
			for(Entity entity : entities) {
				if(entity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity)entity;
					player.clearActivePotions();
				}
			}
		}
	}

}
