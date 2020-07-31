package com.majesity.majcraft.events;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static com.majesity.majcraft.MajCraft.getKeyBindings;
import static com.majesity.majcraft.MajCraft.keyBindings;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyPressEvent {

    private static boolean alreadyJumped = false;

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        int key = event.getKey();
        event.getAction();
        KeyBinding keyBinding = keyBindings[1];
        if(Minecraft.getInstance() != null && !(Minecraft.getInstance().currentScreen instanceof Screen)) {
        PlayerEntity player = Minecraft.getInstance().player;
            if (event.getKey() == GLFW.GLFW_KEY_SPACE && event.getAction() == 1) {
                if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null) {
                    if (player.inventory.armorItemInSlot(0).getItem().equals(ModItems.AVIAN_BOOTS.get().asItem()) && player.inventory.armorItemInSlot(1).getItem().equals(ModItems.AVIAN_LEGGINGS.get().asItem()) && player.inventory.armorItemInSlot(2).getItem().equals(ModItems.AVIAN_CHESTPLATE.get().asItem()) && player.inventory.armorItemInSlot(3).getItem().equals(ModItems.AVIAN_HELMET.get().asItem())) {
                        if (!player.isOnGround() && alreadyJumped == false) {
                            player.setVelocity(0, 0.7, 0);
                            alreadyJumped = true;
                        }
                    }
                }
            }
       /* else if(event.getKey() == GLFW.GLFW_KEY_LEFT_SHIFT && event.getAction() == 1) {
            if(player.inventory.armorItemInSlot(0)!=null && player.inventory.armorItemInSlot(1)!=null && player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(3)!=null) {
                if(player.inventory.armorItemInSlot(0).getItem().equals(ModItems.FOREST_BOOTS.get().asItem()) && player.inventory.armorItemInSlot(1).getItem().equals(ModItems.FOREST_LEGGINGS.get().asItem()) && player.inventory.armorItemInSlot(2).getItem().equals(ModItems.FOREST_CHESTPLATE.get().asItem()) && player.inventory.armorItemInSlot(3).getItem().equals(ModItems.FOREST_HELMET.get().asItem())) {
                    MajCraft.LOGGER.info("Adding effect");
                    player.addPotionEffect(new EffectInstance(Effects.SPEED,60,1,false,false));
                }
            }
        }
        else if(event.getKey() == GLFW.GLFW_KEY_LEFT_SHIFT && event.getAction() == 0) {
            if(player.inventory.armorItemInSlot(0)!=null && player.inventory.armorItemInSlot(1)!=null && player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(3)!=null) {
                if(player.inventory.armorItemInSlot(0).getItem().equals(ModItems.FOREST_BOOTS.get().asItem()) && player.inventory.armorItemInSlot(1).getItem().equals(ModItems.FOREST_LEGGINGS.get().asItem()) && player.inventory.armorItemInSlot(2).getItem().equals(ModItems.FOREST_CHESTPLATE.get().asItem()) && player.inventory.armorItemInSlot(3).getItem().equals(ModItems.FOREST_HELMET.get().asItem())) {
                    MajCraft.LOGGER.info("Removing effect");
                    player.removeActivePotionEffect(Effects.SPEED);
                }
            }
        }*/
        }
    }

    // this is for the avian set, negates fall damage and allows double jump again
    @SubscribeEvent
    public static void onLand(LivingFallEvent event) {
        if(event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            if(player.inventory.armorItemInSlot(0)!=null && player.inventory.armorItemInSlot(1)!=null && player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(3)!=null) {
                if (player.inventory.armorItemInSlot(0).getItem().equals(ModItems.AVIAN_BOOTS.get().asItem()) && player.inventory.armorItemInSlot(1).getItem().equals(ModItems.AVIAN_LEGGINGS.get().asItem()) && player.inventory.armorItemInSlot(2).getItem().equals(ModItems.AVIAN_CHESTPLATE.get().asItem()) && player.inventory.armorItemInSlot(3).getItem().equals(ModItems.AVIAN_HELMET.get().asItem())) {
                    event.setDistance(0.0F);
                    alreadyJumped = false;
                }
            }
        }
    }
    /*@SubscribeEvent
    public static void sneakBoost(InputUpdateEvent event) {
        PlayerEntity player = event.getPlayer();
        if(event.getMovementInput().sneaking) {
            if(player.inventory.armorItemInSlot(0)!=null && player.inventory.armorItemInSlot(1)!=null && player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(3)!=null) {
                if(player.inventory.armorItemInSlot(0).getItem().equals(ModItems.FOREST_BOOTS.get().asItem()) && player.inventory.armorItemInSlot(1).getItem().equals(ModItems.FOREST_LEGGINGS.get().asItem()) && player.inventory.armorItemInSlot(2).getItem().equals(ModItems.FOREST_CHESTPLATE.get().asItem()) && player.inventory.armorItemInSlot(3).getItem().equals(ModItems.FOREST_HELMET.get().asItem())) {
                    player.addPotionEffect(new EffectInstance(Effects.REGENERATION,60,1,false,false));
                }
            }
        }
    }*/

}
