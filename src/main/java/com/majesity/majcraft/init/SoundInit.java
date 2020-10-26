package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MajCraft.MOD_ID);

    public static final RegistryObject<SoundEvent> AMBIENT = SOUNDS.register("block.screaming.ambient",
            () -> new SoundEvent(new ResourceLocation(MajCraft.MOD_ID, "block.screaming.ambient")));

}
