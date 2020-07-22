package com.majesity.majcraft.util;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.capabilities.PlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {
    public static final ResourceLocation PLAYER_DATA = new ResourceLocation(MajCraft.MOD_ID, "player data");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(!(event.getObject() instanceof PlayerEntity)) {
            return; }
        event.addCapability(PLAYER_DATA, new PlayerData());
    }


}
