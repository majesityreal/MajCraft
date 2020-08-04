package com.majesity.majcraft.blocks.failedFurnace.ObsidianForge;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;

public class ObsidianForgeContainer extends AbstractFurnaceContainer {
    public ObsidianForgeContainer(int id, PlayerInventory playerInventoryIn) {
        super(ContainerType.FURNACE, IRecipeType.SMELTING, id, playerInventoryIn);
    }

    public ObsidianForgeContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50083_4_) {
        super(ContainerType.FURNACE, IRecipeType.SMELTING, id, playerInventoryIn, furnaceInventoryIn, p_i50083_4_);
    }
}