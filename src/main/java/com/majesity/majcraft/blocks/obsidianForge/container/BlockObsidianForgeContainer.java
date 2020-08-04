package com.majesity.majcraft.blocks.obsidianForge.container;

import com.majesity.majcraft.init.ModBlocks;
import com.majesity.majcraft.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockObsidianForgeContainer extends BlockIronFurnaceContainerBase {

    public BlockObsidianForgeContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ModContainers.OBSIDIAN_FORGE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public BlockObsidianForgeContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(ModContainers.OBSIDIAN_FORGE_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), playerEntity, ModBlocks.OBSIDIAN_FORGE.get());
    }
}