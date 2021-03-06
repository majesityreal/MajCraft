package com.majesity.majcraft.events;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.capabilities.IPlayerData;
import com.majesity.majcraft.capabilities.PlayerDataProvider;
import com.majesity.majcraft.init.ModBlocks;
import com.majesity.majcraft.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MajCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onFurnaceFuel(FurnaceFuelBurnTimeEvent event) {
        if(event.getItemStack().getItem().equals(ModItems.FIN_ORE_ITEM)) {
            MajCraft.LOGGER.info("The item is a fine ore");
        }
    }

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

    // THIS WORKS FOR SOME REASON ON THE CLIENT
   /* @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        MajCraft.LOGGER.info("HEY HEY" + event.getMessage());
    } */

    // this fires twice
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof EnderDragonEntity) {
            MajCraft.LOGGER.info("ENDER DRAGON HAS DIED!!! FIRE EVENTS, LIVING");
            // if world isn't hardmode, then do this stuff and set it to hardmode
        }
    }

    @SubscribeEvent
    public static void onItemDrop(ItemTossEvent event) {
        ItemEntity thrownItem = event.getEntityItem();
        if(thrownItem.getItem().getItem().equals(ModItems.FIN_ORE_ITEM.get().getItem())) {
            BlockPos itemPos = thrownItem.getPosition();
            PlayerEntity player = event.getPlayer();
            Vector3d eyePos = player.getEyePosition(1.0F);
            Vector3d lookVec = player.getLookVec();
            double addX = Math.min(Math.abs(lookVec.getX()*100),1);
            double addY = Math.min(Math.abs(lookVec.getY()*100),1);
            double addZ = Math.min(Math.abs(lookVec.getZ()*100),1);
            itemPos.add(addX,addY,addZ);
            MajCraft.LOGGER.info("Position: " + itemPos.toString());
            MajCraft.LOGGER.info("Block: " + player.getEntityWorld().getBlockState(itemPos).getBlock().toString());
            if(player.getEntityWorld().getBlockState(itemPos).equals(Blocks.END_GATEWAY.getDefaultState())) {
                MajCraft.LOGGER.info("end gateway from thrown!");
            }
            BlockRayTraceResult result = player.getEntityWorld().rayTraceBlocks(new RayTraceContext(eyePos, lookVec.mul(5,5,5).add(eyePos), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, player));
            if(result.getType() == RayTraceResult.Type.BLOCK) {
                MajCraft.LOGGER.info("result location: " + result.getPos().toString());
                if(player.getEntityWorld().getBlockState(result.getPos()).equals(Blocks.END_GATEWAY.getDefaultState())) {
                    MajCraft.LOGGER.info("Yes it is the end gateway!");
                }
                if(player.getEntityWorld().getBlockState(result.getPos()).equals(Blocks.END_STONE.getDefaultState())) {
                    MajCraft.LOGGER.info("it is end stone");
                }
            }
        }
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