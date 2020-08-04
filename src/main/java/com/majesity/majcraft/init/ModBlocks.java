package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.*;
import com.majesity.majcraft.blocks.failedFurnace.EndForge.EndForge;
import com.majesity.majcraft.blocks.failedFurnace.Furnace.BlockInventoryFurnace;
import com.majesity.majcraft.blocks.obsidianForge.BlockObsidianForge;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MajCraft.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);
    public static final RegistryObject<Block> SIFTER = BLOCKS.register("sifter", Sifter::new);
    public static final RegistryObject<Block> SCREAMING = BLOCKS.register("screaming", Screaming::new);
    public static final RegistryObject<Block> BIRD_NEST = BLOCKS.register("bird_nest", BirdNest::new);
    public static final RegistryObject<Block> FIN_ORE = BLOCKS.register("fin_ore", FinOre::new);
    public static final RegistryObject<Block> OBSIDIAN_FORGE = BLOCKS.register("obsidian_forge", BlockObsidianForge::new);
    public static final RegistryObject<Block> FURNACE = BLOCKS.register("furnace", BlockInventoryFurnace::new);
    public static final RegistryObject<Block> END_FORGE = BLOCKS.register("end_forge", EndForge::new);


}
