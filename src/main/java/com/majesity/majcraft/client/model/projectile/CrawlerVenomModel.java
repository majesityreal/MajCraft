package com.majesity.majcraft.client.model.projectile;
// Made with Blockbench 3.6.6

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.entities.CrawlerEntity;
import com.majesity.majcraft.entities.projectile.CrawlerVenomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CrawlerVenomModel<T extends CrawlerVenomEntity> extends EntityModel<CrawlerVenomEntity> {
	private final ModelRenderer renderer;
	private float ageInTicks;

	public CrawlerVenomModel() {
		textureWidth = 16;
		textureHeight = 16;
		this.ageInTicks = 0.0f;

		renderer = new ModelRenderer(this);
		renderer.setRotationPoint(0.0F, 24.0F, 0.0F);
		renderer.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		renderer.setTextureOffset(7, 9).addBox(-1.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		renderer.setTextureOffset(4, 8).addBox(-3.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		renderer.setTextureOffset(0, 8).addBox(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		renderer.setTextureOffset(7, 7).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		renderer.setTextureOffset(4, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		renderer.setTextureOffset(0, 6).addBox(1.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		renderer.render(matrixStack, buffer, packedLight, packedOverlay);
		setRotationAngle(this.renderer,(float) Math.PI * MathHelper.cos(ageInTicks*0.05f), 0.0f,(float) Math.PI * MathHelper.cos(ageInTicks*0.05f));
		ageInTicks++;
		if (ageInTicks>=1000.0f) {
			this.ageInTicks = 0.0f;
		}
		MajCraft.LOGGER.info("age in ticks: " + this.ageInTicks);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(CrawlerVenomEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// this.renderer.rotateAngleX = (float) Math.PI * MathHelper.cos(ageInTicks*0.05f);
		// renderer.rotateAngleY += MathHelper.cos(ageInTicks);
		// this.renderer.rotateAngleZ = (float) Math.PI * MathHelper.cos(ageInTicks*0.05f);
	}

}