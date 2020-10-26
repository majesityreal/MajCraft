package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.RubyBlock;
import com.majesity.majcraft.world.biome.EndCanyon;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.BiomeDictionary.Type;

public class ModBiomes {

    public static DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MajCraft.MOD_ID);

    public static final RegistryObject<Biome> END_CANYONS = BIOMES.register("end_canyon", EndCanyon::new);

    public static void registerBiomes() {
        registerBiome(END_CANYONS.get(),Type.PLAINS, Type.OVERWORLD);
    }

    public static void registerBiome(Biome biome, Type... types) {
        BiomeDictionary.addTypes(biome,types);
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
    }

}
