package com.majesity.majcraft.blocks;

import com.majesity.majcraft.init.SoundInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Screaming extends Block {


    public Screaming() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(5.0f,6.0f)
                .sound(SoundType.METAL)
                //.setLightLevel(value -> 15)
        );
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if(entityIn instanceof PlayerEntity) {
            worldIn.playSound((PlayerEntity)entityIn, pos, SoundInit.AMBIENT.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
            super.onEntityWalk(worldIn, pos, entityIn);
        }
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        worldIn.playSound(player, pos, SoundInit.AMBIENT.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        super.onBlockClicked(state, worldIn, pos, player);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        worldIn.playSound(player, pos, SoundInit.AMBIENT.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
