package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.items.Chocolate;
import com.majesity.majcraft.items.ItemBase;
import com.majesity.majcraft.items.WandFireball;
import com.majesity.majcraft.items.WandSnowball;
import com.majesity.majcraft.items.armor.AvianArmor;
import com.majesity.majcraft.items.armor.ForestArmor;
import com.majesity.majcraft.items.armor.MelonArmor;
import com.majesity.majcraft.items.armor.MoltenArmor;
import com.majesity.majcraft.util.enums.ModArmorMaterial;
import com.majesity.majcraft.util.enums.ModItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MajCraft.MOD_ID);

    public void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);
    public static final RegistryObject<Chocolate> CHOCOLATE = ITEMS.register(("chocolate"), Chocolate::new);
    public static final RegistryObject<Item> WIRE_MESH = ITEMS.register("wire_mesh", ItemBase::new);
    public static final RegistryObject<WandFireball> FIREBALL_WAND = ITEMS.register("fireball_wand", WandFireball::new);
    public static final RegistryObject<WandSnowball> SNOWBALL_WAND = ITEMS.register("snowball_wand", WandSnowball::new);
    public static final RegistryObject<Item> BEAK = ITEMS.register("beak", ItemBase::new);
    public static final RegistryObject<Item> FOREST_TANGLE = ITEMS.register("forest_tangle", ItemBase::new);
    public static final RegistryObject<Item> FIN = ITEMS.register("fin", ItemBase::new);
    public static final RegistryObject<Item> MOLTEN_POWDER = ITEMS.register("molten_powder", ItemBase::new);
    public static final RegistryObject<Item> MOLTEN_INGOT = ITEMS.register("molten_ingot", ItemBase::new);

    // Block Items
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () ->
            new BlockItem(ModBlocks.RUBY_BLOCK.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> SIFTER_BLOCK_ITEM = ITEMS.register("sifter", () ->
            new BlockItem(ModBlocks.SIFTER.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> SCREAMING_ITEM = ITEMS.register("screaming", () ->
            new BlockItem(ModBlocks.SCREAMING.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> BIRD_NEST_ITEM = ITEMS.register("bird_nest", () ->
            new BlockItem(ModBlocks.BIRD_NEST.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> FIN_ORE_ITEM = ITEMS.register("fin_ore", () ->
            new BlockItem(ModBlocks.FIN_ORE.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> OBSIDIAN_FORGE = ITEMS.register("obsidian_forge", () ->
            new BlockItem(ModBlocks.OBSIDIAN_FORGE.get(), new Item.Properties().group(MajCraft.TAB)));

    // Tools - base speed is 4.0F
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword",() ->
            new SwordItem(ModItemTier.RUBY, 2, -2.4F, new Item.Properties().group(MajCraft.TAB)));

    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",() ->
            new PickaxeItem(ModItemTier.RUBY, 0, -3.0F, new Item.Properties().group(MajCraft.TAB)));
    /*
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel",() ->
            new ShovelItem(ModItemTier.RUBY, 0, -3.0F, new Item.Properties().group(MajCraft.TAB)));

    public static final RegistryObject<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe",() ->
            new AxeItem(ModItemTier.RUBY, 0, -3.0F, new Item.Properties().group(MajCraft.TAB)));

    public static final RegistryObject<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe",() ->
            new HoeItem(ModItemTier.RUBY, 0, -3.0F, new Item.Properties().group(MajCraft.TAB)));

     */

    // Armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET, new Item.Properties().group(MajCraft.TAB)));

    public static final RegistryObject<AvianArmor> AVIAN_HELMET = ITEMS.register("avian_helmet", () ->
            new AvianArmor(ModArmorMaterial.AVIAN, EquipmentSlotType.HEAD, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<AvianArmor> AVIAN_CHESTPLATE = ITEMS.register("avian_chestplate", () ->
            new AvianArmor(ModArmorMaterial.AVIAN, EquipmentSlotType.CHEST, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<AvianArmor> AVIAN_LEGGINGS = ITEMS.register("avian_leggings", () ->
            new AvianArmor(ModArmorMaterial.AVIAN, EquipmentSlotType.LEGS, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<AvianArmor> AVIAN_BOOTS = ITEMS.register("avian_boots", () ->
            new AvianArmor(ModArmorMaterial.AVIAN, EquipmentSlotType.FEET, new Item.Properties().group(MajCraft.TAB)));

    public static final RegistryObject<ForestArmor> FOREST_HELMET = ITEMS.register("forest_helmet", () ->
            new ForestArmor(ModArmorMaterial.FOREST, EquipmentSlotType.HEAD, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<ForestArmor> FOREST_CHESTPLATE = ITEMS.register("forest_chestplate", () ->
            new ForestArmor(ModArmorMaterial.FOREST, EquipmentSlotType.CHEST, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<ForestArmor> FOREST_LEGGINGS = ITEMS.register("forest_leggings", () ->
            new ForestArmor(ModArmorMaterial.FOREST, EquipmentSlotType.LEGS, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<ForestArmor> FOREST_BOOTS = ITEMS.register("forest_boots", () ->
            new ForestArmor(ModArmorMaterial.FOREST, EquipmentSlotType.FEET, new Item.Properties().group(MajCraft.TAB)));

    public static final RegistryObject<MelonArmor> MELON_HELMET = ITEMS.register("melon_helmet", () ->
            new MelonArmor(ModArmorMaterial.MELON, EquipmentSlotType.HEAD, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<MelonArmor> MELON_CHESTPLATE = ITEMS.register("melon_chestplate", () ->
            new MelonArmor(ModArmorMaterial.MELON, EquipmentSlotType.CHEST, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<MelonArmor> MELON_LEGGINGS = ITEMS.register("melon_leggings", () ->
            new MelonArmor(ModArmorMaterial.MELON, EquipmentSlotType.LEGS, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<MelonArmor> MELON_BOOTS = ITEMS.register("melon_boots", () ->
            new MelonArmor(ModArmorMaterial.MELON, EquipmentSlotType.FEET, new Item.Properties().group(MajCraft.TAB)));

    public static final RegistryObject<MoltenArmor> MOLTEN_HELMET = ITEMS.register("molten_helmet", () ->
            new MoltenArmor(ModArmorMaterial.MOLTEN, EquipmentSlotType.HEAD, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<MoltenArmor> MOLTEN_CHESTPLATE = ITEMS.register("molten_chestplate", () ->
            new MoltenArmor(ModArmorMaterial.MOLTEN, EquipmentSlotType.CHEST, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<MoltenArmor> MOLTEN_LEGGINGS = ITEMS.register("molten_leggings", () ->
            new MoltenArmor(ModArmorMaterial.MOLTEN, EquipmentSlotType.LEGS, new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<MoltenArmor> MOLTEN_BOOTS = ITEMS.register("molten_boots", () ->
            new MoltenArmor(ModArmorMaterial.MOLTEN, EquipmentSlotType.FEET, new Item.Properties().group(MajCraft.TAB)));
}
