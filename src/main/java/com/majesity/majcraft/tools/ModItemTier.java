package com.majesity.majcraft.tools;

import com.majesity.majcraft.util.RegistryHandler;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {
    // harvestLevel, maxUses, efficiency, baseDamage, enchantability, repairMaterial
    RUBY(4, 800, 7.0F, 3.0F, 12, () -> {
        return Ingredient.fromItems(RegistryHandler.RUBY.get());
    });

    // to make a new tier:
    /*
    QUARTZ(4, 800, 7.0F, 3.0F, 12, () -> {
        return Ingredient.fromItems(RegistryHandler.QUARTZ.get());
    });
     */

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    // 1 + baseDamage + addedDamage
    ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }
}
