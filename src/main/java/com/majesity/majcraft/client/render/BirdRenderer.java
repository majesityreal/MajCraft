package com.majesity.majcraft.client.render;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.client.model.BirdModel;
import com.majesity.majcraft.entities.BirdEntity.BirdEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class BirdRenderer extends MobRenderer<BirdEntity, BirdModel<BirdEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MajCraft.MOD_ID,"textures/entity/bird.png");


    public BirdRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BirdModel<>(), 0.25F);
    }

    // returns the texture of the bird
    @Override
    public ResourceLocation getEntityTexture(BirdEntity entity) {
        return TEXTURE;
    }

    /*
    @Override
    protected float handleRotationFloat(BirdEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1F) * f1;
    } */


}
