package com.majesity.majcraft.crafting.recipe;

import com.majesity.majcraft.init.ModBlocks;
import com.majesity.majcraft.init.ModRecipeSerializers;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;


public class ObsidianForgeRecipe implements IRecipe<RecipeWrapper> {

    public static final IRecipeType<ObsidianForgeRecipe> obsidian_forge = IRecipeType.register("obsidian_forge");

    private final IRecipeType<?> type;
    private final ResourceLocation id;
    final String group;
    final Ingredient ingredient;
    final ItemStack result;
    final int caskTime;

    public ObsidianForgeRecipe(ResourceLocation resourceLocation, String group, Ingredient ingredient, ItemStack result, int caskTime) {
        type = obsidian_forge;
        id = resourceLocation;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.caskTime = caskTime;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.result.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.OBSIDIAN_FORGE;
    }

    @Override
    public IRecipeType<?> getType() {
        return type;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.OBSIDIAN_FORGE.get());
    }

    public int getCaskTime() {
        return caskTime;
    }
}