package com.majesity.majcraft.tileentity;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.failedFurnace.ObsidianForge.ObsidianForgeTileEntity;
import com.majesity.majcraft.blocks.obsidianForge.ObsidianForgeRecipes;
import com.majesity.majcraft.blocks.obsidianForge.container.BlockObsidianForgeContainer;
import com.majesity.majcraft.crafting.recipe.ObsidianForgeRecipe;
import com.majesity.majcraft.init.ModTileEntities;
import com.majesity.majcraft.items.augment.ItemAugmentBlasting;
import com.majesity.majcraft.items.augment.ItemAugmentFuel;
import com.majesity.majcraft.items.augment.ItemAugmentSmoking;
import com.majesity.majcraft.items.augment.ItemAugmentSpeed;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class BlockObsidianForgeTile extends BlockIronFurnaceTileBase {

    private RecipeWrapper recipeWrapper;
    public ItemStackHandler inputSlot;

    public BlockObsidianForgeTile() {
        super(ModTileEntities.OBSIDIAN_FORGE_TILE.get());
        inputSlot = new ItemStackHandler();
        recipeWrapper = new RecipeWrapper(inputSlot);
    }

    @Override
    protected int getCookTimeConfig() {
       // return Config.netheriteFurnaceSpeed.get();
        return 5;
    }

    @Override
    public String IgetName() {
        return stringToBlack("Obsidian Forge");
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockObsidianForgeContainer(i, world, pos, playerInventory, playerEntity, this.fields);
    }

    // make this zero?
    // fact: slot 2 is the output slot, slot 0 is the input slot (non fuel)
    @Override
    protected int getCookTime() {
        ItemStack stack = this.getStackInSlot(0);
       // ObsidianForgeRecipe recipe = world.getRecipeManager().getRecipe(ObsidianForgeRecipe.obsidian_forge, inventory, world).orElse(null);
       // ObsidianForgeRecipe recipe = world.getRecipeManager().getRecipe(ObsidianForgeRecipe.obsidian_forge, recipeWrapper, world).orElse(null);
        // MajCraft.LOGGER.info("Result: "+ recipe.getRecipeOutput().toString());
        if (!stack.isEmpty()) {
            if(ObsidianForgeRecipes.getRecipeResult(stack)!=null) {
                return ObsidianForgeRecipes.getBurnTime(stack);
            }
            MajCraft.LOGGER.info("The stack is not empty but the recipe did not register, defaulting to 0");
            return 80;
        }
        return 80;
    }

    private static String stringToBlack(String parString)
    {
        int stringLength = parString.length();
        if (stringLength < 1)
        {
            return "";
        }
        String outputString = "";
        for (int i = 0; i < stringLength; i++)
        {
            outputString = outputString+TextFormatting.BLACK+parString.substring(i, i+1);
        }
        return parString+TextFormatting.BLACK;
    }



}
