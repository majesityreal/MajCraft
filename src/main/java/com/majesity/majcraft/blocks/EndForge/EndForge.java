package com.majesity.majcraft.blocks.EndForge;

import com.majesity.majcraft.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class EndForge extends ContainerBlock {


    public EndForge() {
        super(Properties.create(Material.IRON)
                        .hardnessAndResistance(40.0f,9.0f)
                        .sound(SoundType.METAL)
                        .harvestLevel(3)
                        .harvestTool(ToolType.PICKAXE)
                        .setRequiresTool()
                //.setLightLevel(value -> 15)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.END_FORGE.get().create();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }
}