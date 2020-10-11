package com.majesity.majcraft.util.enums;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    // ruby_layer_1 , ruby_layer_2 , etc
    RUBY(MajCraft.MOD_ID + ":ruby", 20, new int[] { 20 , 500 , 6000 , 2 }, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> { return Ingredient.fromItems(ModItems.RUBY.get()); }, getKnockbackResistance(0)),

    AVIAN(MajCraft.MOD_ID + ":avian", 15, new int[] { 2 , 4 , 3 , 1 }, 20,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> { return Ingredient.fromItems(ModItems.BEAK.get()); }, getKnockbackResistance(0)),
// was 7
    FOREST(MajCraft.MOD_ID + ":forest", 1, new int[] { 1 , 2 , 2 , 1 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> { return Ingredient.fromItems(ModItems.FOREST_TANGLE.get()); }, getKnockbackResistance(0.1F)),

    MELON(MajCraft.MOD_ID + ":melon", 1, new int[] { 1 , 1 , 1 , 1 }, 1,
            SoundEvents.ENTITY_SLIME_SQUISH, 0.0F, () -> { return Ingredient.fromItems(Items.MELON); }, getKnockbackResistance(0)),

    MOLTEN(MajCraft.MOD_ID + ":molten", 16, new int[] { 2 , 5 , 4 , 2 }, 18,
    SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> { return Ingredient.fromItems(ModItems.MOLTEN_INGOT.get()); }, getKnockbackResistance(0));

// enchantability: leather 15, chainmail 12, gold 25, iron 9, diamond 10

    // for new armor sets, copy and paste and change the values accordingly

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 11 , 16 , 15 , 13 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;
    private final float knockbackResistance;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial, float knockbackResistance) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public static float getKnockbackResistance(float amount) {
        return amount;
    }
}
