package com.example.woodWolf.client.render;

import com.example.woodWolf.WoodWolf;
import com.example.woodWolf.client.model.WolfModel;
import com.example.woodWolf.entities.WoodWolfEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWolf extends MobRenderer<WoodWolfEntity, WolfModel<WoodWolfEntity>> {
	private final static String wolf_path = "textures/entity/wolf";
	private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation(WoodWolf.MOD_ID, wolf_path + "/wolf.png");
	private static final ResourceLocation TAMED_WOLF_TEXTURES = new ResourceLocation(WoodWolf.MOD_ID, wolf_path + "/wolf_tame.png");
	private static final ResourceLocation ANRGY_WOLF_TEXTURES = new ResourceLocation(WoodWolf.MOD_ID, wolf_path + "/wolf_angry.png");

	public RenderWolf(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new WolfModel<>(), 0.55F);
		//this.addLayer(new WolfCollarLayer(this));
	}

	protected float handleRotationFloat(WoodWolfEntity livingBase, float partialTicks) {
		return livingBase.getTailRotation();
	}

	public void render(WoodWolfEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if (entityIn.isWolfWet()) {
			float f = entityIn.getBrightness() * entityIn.getShadingWhileWet(partialTicks);
			this.entityModel.setTint(f, f, f);
		}
		matrixStackIn.scale(1.05F, 1.05F, 1.05F);//0.4
		if(entityIn.isChild()) {
			//matrixStackIn.scale(2.05F, 5.05F, 7.05F);
			matrixStackIn.scale(1.18F, 1.18F, 1.18F);//1.1
		}

		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		if (entityIn.isWolfWet()) {
			this.entityModel.setTint(1.18F, 1.18F, 1.18F);
		}
	}

	@Override
	public ResourceLocation getEntityTexture(WoodWolfEntity entity) {
		if (entity.isTamed()) {
			return TAMED_WOLF_TEXTURES;
		} else {
			return entity.isAngry() ? ANRGY_WOLF_TEXTURES : WOLF_TEXTURES;
		}
	}

}