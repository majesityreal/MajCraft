package com.majesity.majcraft.client.render.projectile;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.client.model.entity.CrawlerModel;
import com.majesity.majcraft.client.model.projectile.CrawlerVenomModel;
import com.majesity.majcraft.entities.CrawlerEntity;
import com.majesity.majcraft.entities.projectile.CrawlerVenomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class CrawlerVenomRenderer extends EntityRenderer<CrawlerVenomEntity> implements IEntityRenderer<CrawlerVenomEntity, CrawlerVenomModel<CrawlerVenomEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MajCraft.MOD_ID,"textures/entity/projectile/crawler_venom.png");
    private CrawlerVenomModel<CrawlerVenomEntity> entityModel = new CrawlerVenomModel<>();

    public CrawlerVenomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        MajCraft.LOGGER.info("crawler venom Renderer constructor called");
    }

    @Override
    public CrawlerVenomModel<CrawlerVenomEntity> getEntityModel() {
        MajCraft.LOGGER.info("FETCHING CRAWLER VENOM MODEL MY LAD");
        return entityModel;
    }

    @Override
    public ResourceLocation getEntityTexture(CrawlerVenomEntity entity) {
        MajCraft.LOGGER.info("FETCHING CRAWLER VENOM TEXTURE MY LAD");
        return TEXTURE;
    }

    public void render(CrawlerVenomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        // matrixStackIn.push();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.entityModel.getRenderType(TEXTURE));
        matrixStackIn.translate(0.0,-1.0,0.0);
        this.entityModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        // matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}



