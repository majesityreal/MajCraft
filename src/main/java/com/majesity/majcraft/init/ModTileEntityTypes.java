package com.majesity.majcraft.init;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.tileentity.EndForgeTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MajCraft.MOD_ID)
public class ModTileEntityTypes {
    public static final TileEntityType<?> INFUSER = null;

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES,MajCraft.MOD_ID);

    //
    public static final RegistryObject<TileEntityType<EndForgeTileEntity>> END_FORGE = TILE_ENTITY_TYPES.register("end_forge", () -> TileEntityType.Builder.create(EndForgeTileEntity::new,ModBlocks.END_FORGE).build(null));

}