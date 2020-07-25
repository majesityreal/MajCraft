package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.entities.BirdEntity.BirdEntity;
import com.majesity.majcraft.entities.HogEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

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

}
