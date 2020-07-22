package com.majesity.majcraft.events;
/*
import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.util.RegistryHandler;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent // LivingEntity#func_233480_cy_() --> LivingEntity#getPosition()
    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event) {
        LivingEntity player = event.getEntityLiving();
        if(player.getHeldItemMainhand().getItem() == Items.STICK) {
            World world = player.getEntityWorld();
            world.setBlockState(player.func_233580_cy_().add(0,-1,0), RegistryHandler.RUBY_BLOCK.get().getDefaultState());
        }
    }

    @SubscribeEvent
    public static void onCraftingTableOpen(GuiOpenEvent event) {
        if (event.isCancelable()) {
            if(event.getGui() instanceof CraftingScreen) {
                // this cancels the event
                event.setCanceled(true);
            }
        }
    }



}
*/