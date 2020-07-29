package com.majesity.majcraft.client.model;
// Made with Blockbench 3.6.3

import com.majesity.majcraft.entities.BirdEntity.BirdEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class BirdModel<T extends BirdEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_wing;
    private final ModelRenderer left_wing;
    private final ModelRenderer chin;
    private final ModelRenderer tail;

    public BirdModel() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 15.0F, -4.0F);
        head.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 16.0F, 0.0F);
        body.setTextureOffset(0, 9).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(2.0F, 19.0F, 1.0F);
        right_leg.setTextureOffset(26, 0).addBox(-2.0F, -1.0F, -3.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(-1.0F, 19.0F, 1.0F);
        left_leg.setTextureOffset(37, 0).addBox(-2.0F, -1.0F, -3.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);

        right_wing = new ModelRenderer(this);
        right_wing.setRotationPoint(-2.0F, 14.0F, -1.0F);
        right_wing.setTextureOffset(24, 13).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);

        left_wing = new ModelRenderer(this);
        left_wing.setRotationPoint(2.0F, 14.0F, -1.0F);
        left_wing.setTextureOffset(38, 13).addBox(0.0F, 0.0F, -2.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);

        chin = new ModelRenderer(this);
        chin.setRotationPoint(0.0F, 15.0F, -4.0F);
        chin.setTextureOffset(14, 4).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(-0.5F, 15.0F, 4.0F);
        setRotationAngle(tail, 0.0F, -1.5708F, 0.0F);
        tail.setTextureOffset(35, 7).addBox(-2.0F, -1.0F, -1.5F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    }
    
    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.chin.rotateAngleX = this.head.rotateAngleX;
        this.chin.rotateAngleY = this.head.rotateAngleY;
        this.body.rotateAngleX = ((float) Math.PI / 2F);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        if(entity.getIsBirdFlying()) {
            this.right_wing.rotateAngleZ = ageInTicks;
            this.left_wing.rotateAngleZ = -ageInTicks;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        right_wing.render(matrixStack, buffer, packedLight, packedOverlay);
        left_wing.render(matrixStack, buffer, packedLight, packedOverlay);
        chin.render(matrixStack, buffer, packedLight, packedOverlay);
        tail.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}