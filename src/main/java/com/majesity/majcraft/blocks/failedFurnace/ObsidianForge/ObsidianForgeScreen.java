package com.majesity.majcraft.blocks.failedFurnace.ObsidianForge;

import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ObsidianForgeScreen extends AbstractFurnaceScreen<ObsidianForgeContainer> {
    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/furnace.png");

    public ObsidianForgeScreen(ObsidianForgeContainer p_i51089_1_, PlayerInventory p_i51089_2_, ITextComponent p_i51089_3_) {
        super(p_i51089_1_, new FurnaceRecipeGui(), p_i51089_2_, p_i51089_3_, FURNACE_GUI_TEXTURES);
    }
}
