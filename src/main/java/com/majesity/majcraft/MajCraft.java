package com.majesity.majcraft;

import com.majesity.majcraft.capabilities.*;
import com.majesity.majcraft.entities.BirdEntity.BirdEntity;
import com.majesity.majcraft.entities.HogEntity;
import com.majesity.majcraft.init.ModEntityTypes;
import com.majesity.majcraft.init.ModItems;
import com.majesity.majcraft.init.ModBlocks;
import com.majesity.majcraft.util.SoundInit;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("majcraft")
public class MajCraft
{
    // Directly reference a log4j logger.
    public  static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "majcraft";
    // key bindings :)
     public static KeyBinding[] keyBindings;

    public MajCraft() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);


        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityTypes.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        SoundInit.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(IPlayerData.class, new PlayerDataStorage(), PlayerDataFactory::new);

        // This registers the hog itself
        DeferredWorkQueue.runLater(() -> {
            // func_233813_a_() --> .create()
            GlobalEntityTypeAttributes.put(ModEntityTypes.HOG.get(), HogEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.BIRD.get(), BirdEntity.setCustomAttributes().create());
        });
        // THIS REGISTERS THE ENTITY AND ITS SPAWNING BIOMES
        // weight list (animals): 20=33%, 10=20%, 2=4.7%, 0.2=0.5% (golden bunny or something)
        registerEntityWorldSpawn(ModEntityTypes.HOG.get(), 12, 3, 10, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU);
        registerEntityWorldSpawn(ModEntityTypes.BIRD.get(), 12, 3, 10, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.FLOWER_FOREST);
        // some preinit code
        // LOGGER.info("HELLO FROM PREINIT");
        // LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        keyBindings = new KeyBinding[2];
        keyBindings[0] = new KeyBinding("key.abilities.doublejump", GLFW.GLFW_KEY_SPACE, "key.majcraft.armorAbilities");
        keyBindings[1] = new KeyBinding("key.abilities.crouch", GLFW.GLFW_KEY_LEFT_SHIFT, "key.majcraft.armorAbilities");
        // GLFW.GLFW_KEY_LEFT_SHIFT
        for (int i = 0; i < keyBindings.length; ++i)
        {
            ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
        // do something that can only be done on the client
        // LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    // this creates the custom creative tab with the ruby as the icon
    public static final ItemGroup TAB = new ItemGroup("tutorialTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.RUBY.get());
        }
    };

    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroup, int maxGroup, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroup, maxGroup));
            }
        }
    }

    public static KeyBinding[] getKeyBindings() {
        return keyBindings;
    }

    /*

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    } */
}
