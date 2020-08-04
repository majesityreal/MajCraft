package com.majesity.majcraft.blocks.obsidianForge;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ObsidianForgeRecipes
{

    //The object containing recipe information
    static class recipeResult
    {
        ItemStack output;
        int time;
        int xp;
        recipeResult(Item item, int quantity, int time, int xp)
        {
            this.output = new ItemStack(item, quantity);
            this.time = time;
            this.xp = xp;
        }
    }

    //Map linking input items to the output items and stuff
    protected static Map<Item, recipeResult> recipes = new HashMap<Item, recipeResult>();

    //registers a new recipe with the given information
    public static void registerRecipe(Item input, Item output, int outputQuantity, int time, int xp)
    {
        recipes.put(input, new recipeResult(output, outputQuantity, time, xp));
    }

    //Returns the smelting result of an input item, null if the item is not going to smelt
    public static ItemStack getRecipeResult(ItemStack input)
    {
        if(recipes.containsKey(input.getItem())) {
            return recipes.get(input.getItem()).output;
        }
        else {
            return null;
        }
    }

}
