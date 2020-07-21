package com.majesity.majcraft.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class Sifter extends Block {

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(13, 0, 1, 15, 10, 3), Block.makeCuboidShape(1, 0, 1, 3, 10, 3),
            Block.makeCuboidShape(1, 0, 13, 3, 10, 15), Block.makeCuboidShape(13, 0, 13, 15, 10, 15),
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15), Block.makeCuboidShape(15, 11, 0, 16, 13, 16),
            Block.makeCuboidShape(1, 11, 15, 15, 13, 16), Block.makeCuboidShape(1, 11, 0, 15, 13, 1),
            Block.makeCuboidShape(0, 11, 0, 1, 13, 16)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(1, -0.7222222222222197, 13, 3, 9.27777777777778, 15), Block.makeCuboidShape(13, -0.7222222222222197, 13, 15, 9.27777777777778, 15),
            Block.makeCuboidShape(13, -0.7222222222222197, 1, 15, 9.27777777777778, 3), Block.makeCuboidShape(1, -0.7222222222222197, 1, 3, 9.27777777777778, 3),
            Block.makeCuboidShape(1, 9.27777777777778, 1, 15, 10.27777777777778, 15), Block.makeCuboidShape(0, 10.27777777777778, 0, 1, 12.27777777777778, 16),
            Block.makeCuboidShape(1, 10.27777777777778, 0, 15, 12.27777777777778, 1), Block.makeCuboidShape(1, 10.27777777777778, 15, 15, 12.27777777777778, 16),
            Block.makeCuboidShape(15, 10.27777777777778, 0, 16, 12.27777777777778, 16)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(13, -0.7222222222222197, 13, 15, 9.27777777777778, 15), Block.makeCuboidShape(1, -0.7222222222222197, 13, 3, 9.27777777777778, 15),
            Block.makeCuboidShape(1, -0.7222222222222197, 1, 3, 9.27777777777778, 3), Block.makeCuboidShape(13, -0.7222222222222197, 1, 15, 9.27777777777778, 3),
            Block.makeCuboidShape(1, 9.27777777777778, 1, 15, 10.27777777777778, 15), Block.makeCuboidShape(15, 10.27777777777778, 0, 16, 12.27777777777778, 16),
            Block.makeCuboidShape(1, 10.27777777777778, 0, 15, 12.27777777777778, 1), Block.makeCuboidShape(1, 10.27777777777778, 15, 15, 12.27777777777778, 16),
            Block.makeCuboidShape(0, 10.27777777777778, 0, 1, 12.27777777777778, 16)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(13, -0.7222222222222197, 13, 15, 9.27777777777778, 15), Block.makeCuboidShape(1, -0.7222222222222197, 13, 3, 9.27777777777778, 15),
            Block.makeCuboidShape(1, -0.7222222222222197, 1, 3, 9.27777777777778, 3), Block.makeCuboidShape(13, -0.7222222222222197, 1, 15, 9.27777777777778, 3),
            Block.makeCuboidShape(1, 9.27777777777778, 1, 15, 10.27777777777778, 15), Block.makeCuboidShape(15, 10.27777777777778, 0, 16, 12.27777777777778, 16),
            Block.makeCuboidShape(1, 10.27777777777778, 0, 15, 12.27777777777778, 1), Block.makeCuboidShape(1, 10.27777777777778, 15, 15, 12.27777777777778, 16),
            Block.makeCuboidShape(0, 10.27777777777778, 0, 1, 12.27777777777778, 16)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    public Sifter() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                        .hardnessAndResistance(0.5f,0.5f)
                        .sound(SoundType.WOOD)
                        .harvestLevel(0)
                        .harvestTool(ToolType.AXE)
                //.setLightLevel(value -> 15)
        );
    }


    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        // 0 will get more shadow, 1 removes shadow. float value
        return 0.8F;
    }
}
