package com.majesity.majcraft.client.render;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.client.model.CrawlerModel;
import com.majesity.majcraft.client.model.HogModel;
import com.majesity.majcraft.entities.CrawlerEntity;
import com.majesity.majcraft.entities.HogEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CrawlerRenderer extends MobRenderer<CrawlerEntity, CrawlerModel<CrawlerEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MajCraft.MOD_ID,"textures/entity/crawler.png");


    public CrawlerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CrawlerModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(CrawlerEntity entity) {
        return TEXTURE;
    }
}
