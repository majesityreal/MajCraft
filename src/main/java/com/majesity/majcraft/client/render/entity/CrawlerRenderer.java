package com.majesity.majcraft.client.render.entity;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.client.model.entity.CrawlerModel;
import com.majesity.majcraft.entities.CrawlerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CrawlerRenderer extends MobRenderer<CrawlerEntity, CrawlerModel<CrawlerEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MajCraft.MOD_ID,"textures/entity/projectile/crawler_venom.png");


    public CrawlerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CrawlerModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(CrawlerEntity entity) {
        MajCraft.LOGGER.info("FETCHING CRAWLER TEXTURE MY LAD");

        return TEXTURE;
    }
}


