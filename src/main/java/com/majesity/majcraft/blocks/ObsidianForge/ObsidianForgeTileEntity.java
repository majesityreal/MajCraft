package com.majesity.majcraft.blocks.ObsidianForge;

import com.majesity.majcraft.blocks.ObsidianForge.ObsidianForgeContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ObsidianForgeTileEntity extends AbstractFurnaceTileEntity {
    public ObsidianForgeTileEntity() {
        super(TileEntityType.FURNACE, IRecipeType.SMELTING);
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.furnace");
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return new ObsidianForgeContainer(id, player, this, this.furnaceData);
    }
}