package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.ObsidianForge.old.ObsidianForgeTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MajCraft.MOD_ID);

    // Tile Entities here
    public static final RegistryObject<TileEntityType<ObsidianForgeTileEntity>> OBSIDIAN_FORGE_BLOCK_TILE = TILES.register("obsidian_forge", () -> TileEntityType.Builder.create(ObsidianForgeTileEntity::new, ModBlocks.OBSIDIAN_FORGE.get()).build(null));

}
