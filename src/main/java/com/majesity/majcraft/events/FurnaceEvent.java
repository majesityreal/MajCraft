package com.majesity.majcraft.events;
/*
import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.crafting.recipe.ObsidianForgeRecipe;
import com.majesity.majcraft.crafting.recipe.ObsidianForgeRecipeSerializer;
import com.majesity.majcraft.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID)
public class FurnaceEvent {

     This actually sets the burn time of FUEL, not the item. Used for overriding vanilla fuel or your own
    @SubscribeEvent
    public static void onFurnaceFuel(FurnaceFuelBurnTimeEvent event) {
        if(event.getItemStack().getItem().equals(ModItems.FIN_ORE_ITEM)) {
            MajCraft.LOGGER.info("The item is a fine ore");
        }
        else {
            MajCraft.LOGGER.info("The item is NOT NOT NOT a fine ore");
        }
    }

    @SubscribeEvent
    public static void onSmelt(final PlayerEvent.ItemSmeltedEvent event) {
        if(event.getSmelting().getItem().equals(ModItems.FIN.get())) {
            MajCraft.LOGGER.info("You have smelted a fin!");
            event.getEntityLiving().getEntityWorld().createExplosion(EntityType.TNT.create(event.getEntityLiving().getEntityWorld()),1.0,1.0,1.0,2.0F, Explosion.Mode.BREAK);
        }
        else {
            MajCraft.LOGGER.info("You have NOT smelted a fin!");
        }
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        MajCraft.LOGGER.info("block broken");
        if(event.getPlayer().isServerWorld()) {
            MajCraft.LOGGER.info("it is a server world");
            event.getState().getBlock();
            List<ItemStack> list = event.getState().getBlock().getDrops(event.getState(), (ServerWorld) event.getPlayer().getEntityWorld(),event.getPos(),event.getPlayer().getEntityWorld().getTileEntity(event.getPos()));
            if(list.contains(ModItems.FIN.get().getDefaultInstance())) {
            MajCraft.LOGGER.info("HAS FIN HAS FIN");
            for(ItemStack item:list) {
                if (item.equals(ModItems.FIN.get().getDefaultInstance())) {
                    list.remove(item);
                    MajCraft.LOGGER.info("Removed fin from the furnace drops");
                }
            }
            }
        }
    }

    @SubscribeEvent
      public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().register(new ObsidianForgeRecipeSerializer<>(ObsidianForgeRecipe::new));
      }

}
*/