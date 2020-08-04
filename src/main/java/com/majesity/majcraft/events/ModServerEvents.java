package com.majesity.majcraft.events;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.init.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ModServerEvents {

   /* @SubscribeEvent
    public static void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) {
        TileEntityType<?> type = TileEntityType.Builder.create(ObsidianForgeTileEntity::new, ModBlocks.OBSIDIAN_FORGE.get()).build(null);
        type.setRegistryName("mymod", "myte");
        evt.getRegistry().register(type);
    } */




    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        MajCraft.LOGGER.info("HEY HEY" + event.getMessage());
    }

   /* @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof EnderDragonEntity || event.getEntity() instanceof EnderDragonPartEntity) {
            MajCraft.LOGGER.info("ENDER DRAGON HAS DIED!!! FIRE EVENTS");

        }
        if (event.getEntityLiving() instanceof EnderDragonEntity) {
            MajCraft.LOGGER.info("ENDER DRAGON HAS DIED!!! FIRE EVENTS, LIVING");
        }
    } */

}
