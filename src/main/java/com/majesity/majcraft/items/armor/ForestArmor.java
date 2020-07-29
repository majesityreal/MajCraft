package com.majesity.majcraft.items.armor;

import com.majesity.majcraft.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ForestArmor extends ArmorItem {


    public ForestArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties properties) {
        super(materialIn, slot, properties);
    }

    // 0 boots, 1 legs, 2 chest, 3 head
     @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if(player.inventory.armorItemInSlot(0)!=null && player.inventory.armorItemInSlot(1)!=null && player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(3)!=null) {
            if(!(player.inventory.armorItemInSlot(0).getItem().equals(ModItems.FOREST_BOOTS.get().asItem()) && player.inventory.armorItemInSlot(1).getItem().equals(ModItems.FOREST_LEGGINGS.get().asItem()) && player.inventory.armorItemInSlot(2).getItem().equals(ModItems.FOREST_CHESTPLATE.get().asItem()) && player.inventory.armorItemInSlot(3).getItem().equals(ModItems.FOREST_HELMET.get().asItem()))) {
                player.removeActivePotionEffect(Effects.REGENERATION);
            }
        }
    }

}
