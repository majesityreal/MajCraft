package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.items.Chocolate;
import com.majesity.majcraft.items.ItemBase;
import com.majesity.majcraft.items.WandFireball;
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

    // Block Items
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () ->
            new BlockItem(ModBlocks.RUBY_BLOCK.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> SIFTER_BLOCK_ITEM = ITEMS.register("sifter", () ->
            new BlockItem(ModBlocks.SIFTER.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> SCREAMING_ITEM = ITEMS.register("screaming", () ->
            new BlockItem(ModBlocks.SCREAMING.get(), new Item.Properties().group(MajCraft.TAB)));

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



}
