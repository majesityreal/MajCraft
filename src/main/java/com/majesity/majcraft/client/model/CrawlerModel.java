package com.majesity.majcraft.client.model;
// Made with Blockbench 3.6.6

import com.majesity.majcraft.entities.CrawlerEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CrawlerModel<T extends CrawlerEntity> extends EntityModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer neck;
	private final ModelRenderer body;
	private final ModelRenderer leftLeg1;
	private final ModelRenderer leftLeg1Ext;
	private final ModelRenderer rightLeg1;
	private final ModelRenderer rightLeg1Ext;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer leftLeg2Ext;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer rightLeg2Ext;
	private final ModelRenderer leftLeg3;
	private final ModelRenderer leftLeg3Ext;
	private final ModelRenderer rightLeg3;
	private final ModelRenderer rightLeg3Ext;
	private final ModelRenderer leftLeg4;
	private final ModelRenderer leftLeg4Ext;
	private final ModelRenderer rightLeg4;
	private final ModelRenderer rightLeg4Ext;

	public CrawlerModel() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 15.0F, -3.0F);
		head.setTextureOffset(0, 22).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 15.0F, 0.0F);
		neck.setTextureOffset(30, 40).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 15.0F, 9.0F);
		body.setTextureOffset(0, 0).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 10.0F, 12.0F, 0.0F, false);

		leftLeg1 = new ModelRenderer(this);
		leftLeg1.setRotationPoint(4.0F, 15.0F, 2.0F);
		leftLeg1.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg1Ext = new ModelRenderer(this);
		leftLeg1Ext.setRotationPoint(6.0F, 0.0F, 0.0F);
		leftLeg1.addChild(leftLeg1Ext);
		setRotationAngle(leftLeg1Ext, 0.0F, 0.0F, 0.829F);
		leftLeg1Ext.setTextureOffset(30, 52).addBox(-0.799F, -2.0885F, -1.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg1 = new ModelRenderer(this);
		rightLeg1.setRotationPoint(-4.0F, 15.0F, 2.0F);
		rightLeg1.setTextureOffset(36, 8).addBox(-7.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg1Ext = new ModelRenderer(this);
		rightLeg1Ext.setRotationPoint(-6.0F, 0.0F, 0.0F);
		rightLeg1.addChild(rightLeg1Ext);
		setRotationAngle(rightLeg1Ext, 0.0F, 0.0F, -0.829F);
		rightLeg1Ext.setTextureOffset(48, 12).addBox(-12.201F, -2.0885F, -1.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg2 = new ModelRenderer(this);
		leftLeg2.setRotationPoint(4.0F, 15.0F, 1.0F);
		leftLeg2.setTextureOffset(36, 4).addBox(-9.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg2Ext = new ModelRenderer(this);
		leftLeg2Ext.setRotationPoint(0.0F, 0.0F, 1.0F);
		leftLeg2.addChild(leftLeg2Ext);
		setRotationAngle(leftLeg2Ext, 0.0F, 0.0F, 0.829F);
		leftLeg2Ext.setTextureOffset(0, 52).addBox(3.2546F, -6.5121F, -2.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg2 = new ModelRenderer(this);
		rightLeg2.setRotationPoint(-4.0F, 15.0F, 1.0F);
		rightLeg2.setTextureOffset(36, 0).addBox(-7.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg2Ext = new ModelRenderer(this);
		rightLeg2Ext.setRotationPoint(-6.0F, 0.0F, 0.0F);
		rightLeg2.addChild(rightLeg2Ext);
		setRotationAngle(rightLeg2Ext, 0.0F, 0.0F, -0.829F);
		rightLeg2Ext.setTextureOffset(0, 48).addBox(-12.201F, -2.0885F, -1.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg3 = new ModelRenderer(this);
		leftLeg3.setRotationPoint(4.0F, 15.0F, 0.0F);
		leftLeg3.setTextureOffset(30, 36).addBox(-9.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg3Ext = new ModelRenderer(this);
		leftLeg3Ext.setRotationPoint(0.0F, 0.0F, 2.0F);
		leftLeg3.addChild(leftLeg3Ext);
		setRotationAngle(leftLeg3Ext, 0.0F, 0.0F, 0.829F);
		leftLeg3Ext.setTextureOffset(48, 40).addBox(3.2546F, -6.5121F, -3.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg3 = new ModelRenderer(this);
		rightLeg3.setRotationPoint(-4.0F, 15.0F, 0.0F);
		rightLeg3.setTextureOffset(32, 28).addBox(-7.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg3Ext = new ModelRenderer(this);
		rightLeg3Ext.setRotationPoint(-6.0F, 0.0F, 0.0F);
		rightLeg3.addChild(rightLeg3Ext);
		setRotationAngle(rightLeg3Ext, 0.0F, 0.0F, -0.829F);
		rightLeg3Ext.setTextureOffset(46, 20).addBox(-12.201F, -2.0885F, -1.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg4 = new ModelRenderer(this);
		leftLeg4.setRotationPoint(4.0F, 15.0F, -1.0F);
		leftLeg4.setTextureOffset(32, 32).addBox(-9.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg4Ext = new ModelRenderer(this);
		leftLeg4Ext.setRotationPoint(0.0F, 0.0F, 3.0F);
		leftLeg4.addChild(leftLeg4Ext);
		setRotationAngle(leftLeg4Ext, 0.0F, 0.0F, 0.829F);
		leftLeg4Ext.setTextureOffset(48, 16).addBox(3.2546F, -6.5121F, -4.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg4 = new ModelRenderer(this);
		rightLeg4.setRotationPoint(-4.0F, 15.0F, -1.0F);
		rightLeg4.setTextureOffset(24, 24).addBox(-7.0F, -2.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		rightLeg4Ext = new ModelRenderer(this);
		rightLeg4Ext.setRotationPoint(-6.0F, 0.0F, 0.0F);
		rightLeg4.addChild(rightLeg4Ext);
		setRotationAngle(rightLeg4Ext, 0.0F, 0.0F, -0.829F);
		rightLeg4Ext.setTextureOffset(0, 44).addBox(-12.201F, -2.0885F, -1.0F, 13.0F, 2.0F, 2.0F, 0.0F, false);
	}

	// when you rotate it by pi, it shifts the cos function to the opposite direction
	// limbSwing goes around the circle from 1 to -1, so cos ensures that all the angles are in a half arc
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// this should separate the legs to look better
		/* this.leftLeg1.rotateAngleZ = ((float)Math.PI / 4);
		this.leftLeg2.rotateAngleZ = -((float)Math.PI / 4);
		this.leftLeg3.rotateAngleZ = ((float)Math.PI / 4);
		this.leftLeg4.rotateAngleZ = -((float)Math.PI / 4); */

		// this controls the side to side movement of the legs as the spider walks
		this.leftLeg1.rotateAngleY = -MathHelper.cos(limbSwing);
		this.leftLeg2.rotateAngleY = -MathHelper.cos(limbSwing);
		this.leftLeg3.rotateAngleY = -MathHelper.cos(limbSwing);
		this.leftLeg4.rotateAngleY = -MathHelper.cos(limbSwing);

		// this is where the legs will be at rest
		this.rightLeg1.rotateAngleY = (float)(Math.PI/4);
		this.rightLeg2.rotateAngleY = (float)(Math.PI/8);
		this.rightLeg3.rotateAngleY = -(float)(Math.PI/8);
		this.rightLeg4.rotateAngleY = -(float)(Math.PI/4);

		// this actually rotates the legs as it is moving
		float f1 = 2.0F * MathHelper.cos(limbSwing+(0.25F*(float)Math.PI))*limbSwingAmount;
		float f2 = 0.75F * MathHelper.cos(limbSwing+(-0.25F*(float)Math.PI))*limbSwingAmount;
		float f3 = 0.75F * MathHelper.cos(limbSwing+(0.25F*(float)Math.PI))*limbSwingAmount;
		float f4 = 2.0F * MathHelper.cos(limbSwing+(-0.25F*(float)Math.PI))*limbSwingAmount;
		this.rightLeg1.rotateAngleY += f1; //2.0F * MathHelper.cos(limbSwing+(0.25F*(float)Math.PI))*limbSwingAmount;
		this.rightLeg2.rotateAngleY += f2; //0.75F * MathHelper.cos(limbSwing+(-0.25F*(float)Math.PI))*limbSwingAmount;
		this.rightLeg3.rotateAngleY += f3; //0.75F * MathHelper.cos(limbSwing+(0.25F*(float)Math.PI))*limbSwingAmount;
		this.rightLeg4.rotateAngleY += f4; //2.0F * MathHelper.cos(limbSwing+(-0.25F*(float)Math.PI))*limbSwingAmount;
//		this.rightLeg3.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F); slows the right side down
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		neck.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg3.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg3.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg4.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg4.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}