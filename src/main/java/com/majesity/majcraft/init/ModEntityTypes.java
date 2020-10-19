package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.entities.BirdEntity.BirdEntity;
import com.majesity.majcraft.entities.CrawlerEntity;
import com.majesity.majcraft.entities.HogEntity;
import com.majesity.majcraft.entities.projectile.AbstractCrawlerVenomEntity;
import com.majesity.majcraft.entities.projectile.CrawlerVenomEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes<T extends Entity> extends net.minecraftforge.registries.ForgeRegistryEntry<EntityType<?>> {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MajCraft.MOD_ID);

    // Entity Types
    public static final RegistryObject<EntityType<HogEntity>> HOG = ENTITY_TYPES.register("hog",
            () -> EntityType.Builder.create(HogEntity::new, EntityClassification.CREATURE)
                    .size(1.0f,1.0f)
                    .build(new ResourceLocation(MajCraft.MOD_ID, "hog").toString())
    );
    public static final RegistryObject<EntityType<BirdEntity>> BIRD = ENTITY_TYPES.register("bird",
            () -> EntityType.Builder.create(BirdEntity::new, EntityClassification.CREATURE)
                    .size(0.4f,0.4f)
                    .build(new ResourceLocation(MajCraft.MOD_ID, "bird").toString())
    );
    public static final RegistryObject<EntityType<CrawlerEntity>> CRAWLER = ENTITY_TYPES.register("crawler",
            () -> EntityType.Builder.create(CrawlerEntity::new, EntityClassification.MONSTER)
                    .size(1.5f,0.6f)
                    .build(new ResourceLocation(MajCraft.MOD_ID, "crawler").toString())
    );

    // Projectile Entity Types
      public static final RegistryObject<EntityType<CrawlerVenomEntity>> CRAWLER_VENOM = ENTITY_TYPES.register("crawler_venom",
            () -> EntityType.Builder.<CrawlerVenomEntity>create(CrawlerVenomEntity::new, EntityClassification.MISC)
                    .setCustomClientFactory(CrawlerVenomEntity::new)
                    .size(1.0f,1.0f)
                    .build(new ResourceLocation(MajCraft.MOD_ID, "crawler_venom").toString())
    );



}
