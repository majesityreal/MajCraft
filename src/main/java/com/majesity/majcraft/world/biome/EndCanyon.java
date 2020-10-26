package com.majesity.majcraft.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public final class EndCanyon extends Biome {
    public EndCanyon() {
        super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.1F).scale(0.2F).temperature(0.7F).downfall(0.8F).func_235097_a_((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).setMoodSound(MoodSoundAmbience.field_235027_b_).build()).parent((String)null));
        DefaultBiomeFeatures.func_235196_b_(this);
        this.func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_STANDARD);
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addDoubleFlowers(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addForestTrees(this);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        DefaultBiomeFeatures.addGrass(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.IRON_GOLEM, 8, 4, 10));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.BOAT, 8, 4, 10));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.HORSE, 8, 4, 10));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 300, 5, 10));
    }
}