package com.majesity.majcraft.util;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.armor.ModArmorMaterial;
import com.majesity.majcraft.blocks.BlockItemBase;
import com.majesity.majcraft.blocks.RubyBlock;
import com.majesity.majcraft.blocks.Screaming;
import com.majesity.majcraft.blocks.Sifter;
import com.majesity.majcraft.items.Chocolate;
import com.majesity.majcraft.items.ItemBase;
import com.majesity.majcraft.items.WandFireball;
import com.majesity.majcraft.tools.ModItemTier;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.omg.DynamicAny.DynEnumHelper;


public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MajCraft.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MajCraft.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);
    public static final RegistryObject<Chocolate> CHOCOLATE = ITEMS.register(("chocolate"), Chocolate::new);
    public static final RegistryObject<Item> WIRE_MESH = ITEMS.register("wire_mesh", ItemBase::new);
    public static final RegistryObject<WandFireball> FIREBALL_WAND = ITEMS.register("fireball_wand", WandFireball::new);


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

    // Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);
    public static final RegistryObject<Block> SIFTER = BLOCKS.register("sifter", Sifter::new);
    public static final RegistryObject<Block> SCREAMING = BLOCKS.register("screaming", Screaming::new);


    // Block Items
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () -> new BlockItem(RUBY_BLOCK.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> SIFTER_BLOCK_ITEM = ITEMS.register("sifter", () -> new BlockItem(SIFTER.get(), new Item.Properties().group(MajCraft.TAB)));
    public static final RegistryObject<Item> SCREAMING_ITEM = ITEMS.register("screaming", () -> new BlockItem(SCREAMING.get(), new Item.Properties().group(MajCraft.TAB)));

}
