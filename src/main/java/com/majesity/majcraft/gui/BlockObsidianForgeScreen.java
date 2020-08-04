package com.majesity.majcraft.gui;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.obsidianForge.container.BlockObsidianForgeContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlockObsidianForgeScreen extends BlockIronFurnaceScreenBase<BlockObsidianForgeContainer> {


    public BlockObsidianForgeScreen(BlockObsidianForgeContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.GUI = new ResourceLocation(MajCraft.MOD_ID + ":" +"textures/gui/obsidian_forge.png");
    }
}