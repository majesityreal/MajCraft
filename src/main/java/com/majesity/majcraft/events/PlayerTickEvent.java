package com.majesity.majcraft.events;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerTickEvent {

    @SubscribeEvent
    public static void onShift(TickEvent.PlayerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            PlayerEntity player = event.player;
            if(player.isCrouching()) {
                if(player.inventory.armorItemInSlot(0)!=null && player.inventory.armorItemInSlot(1)!=null && player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(3)!=null) {
                    if(player.inventory.armorItemInSlot(0).getItem().equals(ModItems.FOREST_BOOTS.get().asItem()) && player.inventory.armorItemInSlot(1).getItem().equals(ModItems.FOREST_LEGGINGS.get().asItem()) && player.inventory.armorItemInSlot(2).getItem().equals(ModItems.FOREST_CHESTPLATE.get().asItem()) && player.inventory.armorItemInSlot(3).getItem().equals(ModItems.FOREST_HELMET.get().asItem())) {
                        if(!player.isPotionActive(Effects.REGENERATION)) { // !player.getActivePotionEffects().contains(Effects.REGENERATION) &&
                            MajCraft.LOGGER.info("Adding effect");
                            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60, 0, false, false));
                        }
                    }
                }
            }
        }
    }

}
