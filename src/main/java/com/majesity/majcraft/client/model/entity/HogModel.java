package com.majesity.majcraft.client.model.entity;
// Made with Blockbench 3.6.3

import com.majesity.majcraft.entities.HogEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class HogModel<T extends HogEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer rotation;
    private final ModelRenderer body_sub_1;
    private final ModelRenderer head;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer backLeftLeg;
    private final ModelRenderer backRightLeg;
    private final ModelRenderer frontLeftLeg;
    private final ModelRenderer frontRightLeg;

    public HogModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 11.0F, 2.0F);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(22, 21).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 17.0F, 9.0F, 0.0F, false);

        rotation = new ModelRenderer(this);
        rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(rotation);
        setRotationAngle(rotation, 1.5708F, 0.0F, 0.0F);


        body_sub_1 = new ModelRenderer(this);
        body_sub_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        rotation.addChild(body_sub_1);


        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 12.0F, -6.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(16, 16).addBox(-2.0F, -1.0F, -9.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(60, 0).addBox(2.0F, 0.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(60, 2).addBox(-3.0F, 0.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(2.5F, 1.5F, -11.0F);
        head.addChild(bone);
        setRotationAngle(bone, -0.2182F, 0.0F, 0.0F);
        bone.setTextureOffset(54, 0).addBox(-0.5F, -1.921F, -0.1556F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(-2.5F, 1.0553F, -10.9392F);
        head.addChild(bone2);
        setRotationAngle(bone2, -0.2182F, 0.0F, 0.0F);
        bone2.setTextureOffset(54, 3).addBox(-0.5F, -1.4737F, -0.1187F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        backLeftLeg = new ModelRenderer(this);
        backLeftLeg.setRotationPoint(3.0F, 18.0F, 7.0F);
        backLeftLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        backRightLeg = new ModelRenderer(this);
        backRightLeg.setRotationPoint(-3.0F, 18.0F, 7.0F);
        backRightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        frontLeftLeg = new ModelRenderer(this);
        frontLeftLeg.setRotationPoint(3.0F, 18.0F, -5.0F);
        frontLeftLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        frontRightLeg = new ModelRenderer(this);
        frontRightLeg.setRotationPoint(-3.0F, 18.0F, -5.0F);
        frontRightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.backRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.frontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.frontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        backLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        backRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        frontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        frontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}