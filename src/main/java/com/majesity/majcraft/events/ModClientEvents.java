package com.majesity.majcraft.events;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.capabilities.IPlayerData;
import com.majesity.majcraft.capabilities.PlayerDataProvider;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent // LivingEntity#func_233480_cy_() --> LivingEntity#getPosition()
    public static void onJump(LivingEvent.LivingJumpEvent event) {

        if(event.getEntityLiving() instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity)event.getEntityLiving();

            IPlayerData data = player.getCapability(PlayerDataProvider.capability).orElseThrow(IllegalStateException::new);
            MajCraft.LOGGER.info("amount of health " + data.getHealth());

            /*
            LazyOptional<IPlayerData> data = player.getCapability(PlayerDataProvider.capability);
            data.ifPresent((test) -> {
                MajCraft.LOGGER.info("testing lol?" + test.getStrength());
            }); */

        }
        /*
        if(player.getPersistentData().contains("strength")) {
            int amount = player.getPersistentData().getInt("strength")+1;
            player.getPersistentData().putInt("strength",amount);
            System.out.println("test" + amount);
        }
        else {
            player.getPersistentData().putInt("strength",1);
        } */

        // YES THIS WORKS
         //IPlayerData test = player.getCapability(PlayerData.INSTANCE).orElse(null);
         //test.getCapability();
         //MajCraft.LOGGER.info("amount of health " + test.getHealth());

        //IPlayerData data = player.getCapability(PlayerData.INSTANCE).orElse(null);






        /* if(player.getHeldItemMainhand().getItem() == Items.STICK) {
            World world = player.getEntityWorld();
            world.setBlockState(player.func_233580_cy_().add(0,-1,0), RegistryHandler.RUBY_BLOCK.get().getDefaultState());
        } */
    }

    /* @SubscribeEvent
    public static void onCraftingTableOpen(GuiOpenEvent event) {
        if (event.isCancelable()) {
            if(event.getGui() instanceof CraftingScreen) {
                // this cancels the event
                event.setCanceled(true);
            }
        }
    } */



}