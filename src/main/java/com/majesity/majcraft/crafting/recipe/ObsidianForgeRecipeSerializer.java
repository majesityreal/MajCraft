package com.majesity.majcraft.crafting.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.majesity.majcraft.MajCraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ObsidianForgeRecipeSerializer<T extends ObsidianForgeRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final ObsidianForgeRecipeSerializer.IFactory<T> factory;

    public ObsidianForgeRecipeSerializer(ObsidianForgeRecipeSerializer.IFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    @Nonnull
    public T read(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
        // group
        String groupString = JSONUtils.getString(json, "group", "");

        // ingredient
        JsonElement ingredientJSON = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJSON);

        // result
        ItemStack resultItemStack;
        if (!json.has("result")) {
            resultItemStack = ItemStack.EMPTY;
        }
        else if (json.get("result").isJsonObject()) {
            resultItemStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
        } else {
            String resultString = JSONUtils.getString(json, "result");
            ResourceLocation resultRS = new ResourceLocation(resultString);
            resultItemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(resultRS));
        }

        // cookTime
        int cookTime = JSONUtils.getInt(json, "furnaceTime", 200);

        return this.factory.create(recipeId, groupString, ingredient, resultItemStack, cookTime);
    }

    @Nullable
    @Override
    public T read(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
        // group
        String groupString = buffer.readString(32767);

        // ingredient
        Ingredient ingredient = Ingredient.read(buffer);

        // result
        ItemStack itemstack = buffer.readItemStack();

        // cookTime
        int cookTime = buffer.readVarInt();

        return this.factory.create(recipeId, groupString, ingredient, itemstack, cookTime);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        // group
        buffer.writeString(recipe.group);

        // ingredient
        recipe.ingredient.write(buffer);

        // result
        buffer.writeItemStack(recipe.result);

        // cookTime
        buffer.writeVarInt(recipe.cookTime);
    }

    public interface IFactory<T extends ObsidianForgeRecipe> {
        T create(ResourceLocation resourceLocation, String group, Ingredient ingredient, ItemStack result, int cookTime);
    }

    public ObsidianForgeRecipeSerializer.IFactory<T> getFactory() {
        return factory;
    }
}