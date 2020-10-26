package com.majesity.majcraft.util;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.client.render.entity.BirdRenderer;
import com.majesity.majcraft.client.render.entity.CrawlerRenderer;
import com.majesity.majcraft.client.render.entity.HogRenderer;
import com.majesity.majcraft.client.render.projectile.CrawlerVenomRenderer;
import com.majesity.majcraft.init.ModBiomes;
import com.majesity.majcraft.init.ModEntityTypes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid= MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HOG.get(), HogRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BIRD.get(), BirdRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CRAWLER.get(), CrawlerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CRAWLER_VENOM.get(), CrawlerVenomRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        ModBiomes.registerBiomes();
    }

}
