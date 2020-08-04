package com.majesity.majcraft.crafting.recipe;

import com.majesity.majcraft.MajCraft;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MajCraft.MOD_ID)
@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeSubscriber {
    public static final ObsidianForgeRecipeSerializer<ObsidianForgeRecipe> crusher = null;

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();
        registry.register(new ObsidianForgeRecipeSerializer<>(ObsidianForgeRecipe::new).setRegistryName(MajCraft.MOD_ID, "obsidian_forge"));
    }
}