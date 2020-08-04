package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.obsidianForge.container.BlockObsidianForgeContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MajCraft.MOD_ID);

    public static final RegistryObject<ContainerType<BlockObsidianForgeContainer>> OBSIDIAN_FORGE_CONTAINER = CONTAINERS.register("obsidian_forge", () -> IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
        return new BlockObsidianForgeContainer(windowId, MajCraft.proxy.getClientWorld(), pos, inv, MajCraft.proxy.getClientPlayer());
    }));

}
