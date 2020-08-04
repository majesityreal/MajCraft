package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.crafting.recipe.ObsidianForgeRecipe;
import com.majesity.majcraft.crafting.recipe.ObsidianForgeRecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;

import static com.majesity.majcraft.init.ModUtil.Null;

@ObjectHolder(MajCraft.MOD_ID)
public class ModRecipeSerializers {

    public static final ObsidianForgeRecipeSerializer<ObsidianForgeRecipe> OBSIDIAN_FORGE = Null();
}

final class ModUtil {
    @SuppressWarnings({"ConstantConditions", "SameReturnValue"})
    public static <T> T Null() {
        return null;
    }
}