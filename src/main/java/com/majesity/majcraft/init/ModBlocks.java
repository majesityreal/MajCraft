package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.BirdNest;
import com.majesity.majcraft.blocks.RubyBlock;
import com.majesity.majcraft.blocks.Screaming;
import com.majesity.majcraft.blocks.Sifter;
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

}
