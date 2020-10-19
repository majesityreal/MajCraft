package com.majesity.majcraft.client.render.projectile;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.client.model.entity.CrawlerModel;
import com.majesity.majcraft.entities.CrawlerEntity;
import com.majesity.majcraft.entities.projectile.CrawlerVenomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class CrawlerVenomRenderer<CrawlerVenomEntity extends Entity> extends EntityRenderer<CrawlerVenomEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MajCraft.MOD_ID,"");


    public CrawlerVenomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(CrawlerVenomEntity entity) {
        MajCraft.LOGGER.info("FETCHING TEXTURE MY LAD");
        return TEXTURE;
    }

    public void render(CrawlerVenomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        entityIn.setGlowing(true);
        entityIn.setFire(100);
        MajCraft.LOGGER.info("Renderer called");
    }

}



