package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.RubyBlock;
import com.majesity.majcraft.blocks.obsidianForge.container.BlockObsidianForgeContainer;
import com.majesity.majcraft.crafting.recipe.ObsidianForgeRecipe;
import com.majesity.majcraft.crafting.recipe.ObsidianForgeRecipeSerializer;
import com.majesity.majcraft.crafting.recipe.RecipeSubscriber;
import net.minecraft.block.Block;
import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

import static com.majesity.majcraft.init.ModUtil.Null;

@ObjectHolder(MajCraft.MOD_ID)
public class ModRecipeSerializers {

    // public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MajCraft.MOD_ID);


    // public static final RegistryObject<ObsidianForgeRecipeSerializer<ObsidianForgeRecipe>> OBSIDIAN_FORGE_RECIPES = RECIPES.register("obsidian_forge",new ObsidianForgeRecipeSerializer<>(ObsidianForgeRecipe::new));


    public static final ObsidianForgeRecipeSerializer<ObsidianForgeRecipe> OBSIDIAN_FORGE = Null();
}

final class ModUtil {
    @SuppressWarnings({"ConstantConditions", "SameReturnValue"})
    public static <T> T Null() {
        return null;
    }
}