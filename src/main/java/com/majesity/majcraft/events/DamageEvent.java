package com.majesity.majcraft.events;

import com.majesity.majcraft.MajCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DamageEvent {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void damagedEvent(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        if(livingEntity instanceof PlayerEntity) {
            livingEntity.getPersistentData().putInt("strength",1);
            int totalArmor = 0;
            for(ItemStack armorItem : livingEntity.getArmorInventoryList()) {
                if(armorItem.getItem() instanceof ArmorItem) {
                    ArmorItem armorWorn = (ArmorItem)armorItem.getItem();
                    totalArmor += armorWorn.getDamageReduceAmount();
                }
               // final Collection<AttributeModifier> modifiers = armorItem.getAttributeModifiers(EquipmentSlotType.LEGS).get(attribute.getAttributeUnlocalizedName());
                // totalArmor += armorItem.getAttributeModifiers(EquipmentSlotType.LEGS).get(Attributes.ARMOR).size();
            }
            int reduction = (totalArmor/2);
            float amount = event.getAmount()-reduction;
            if(amount>0.0F) {
                event.setAmount(event.getAmount() - reduction);
            } else {
                event.setAmount(0.0F);
            }

            /* System.out.println("Total armor: " + totalArmor);
            System.out.println("Pre amount: " + event.getAmount());
            System.out.println("Reduction: " + reduction);
            System.out.println("Post amount: " + event.getAmount()); */

            //if(livingEntity.getAttribute(Attributes.MAX_HEALTH).getBaseValue()!=200)
              //  livingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(200);
        }
        // ArmorItem armor = RegistryHandler.RUBY_BOOTS.get();
       // armor.getAttributeModifiers(EquipmentSlotType.FEET);//.getAttributeModifiers(); getAttributeModifiers().get(Attributes.ARMOR).toString();
        // System.out.println("The number is " + armor.getDamageReduceAmount());
        // EntityDataManager.DataEntry test = new EntityDataManager.DataEntry<>();
    }

   /* @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void damagedEvent(LivingDamageEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        MajCraft.LOGGER.info("damage damage thing is run after");
    } */

}
