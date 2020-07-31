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
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class BirdNest extends Block {

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(4, 0, 2, 12, 4, 3),
            Block.makeCuboidShape(13, 0, 4, 14, 4, 12),
            Block.makeCuboidShape(2, 0, 4, 3, 4, 12),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(4, 0, 13, 12, 4, 14),
            Block.makeCuboidShape(3, 1, 3, 4, 4, 4),
            Block.makeCuboidShape(12, 1, 3, 13, 4, 4),
            Block.makeCuboidShape(12, 1, 12, 13, 4, 13),
            Block.makeCuboidShape(3, 1, 12, 4, 4, 13)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(4, 0, 2, 12, 4, 3),
            Block.makeCuboidShape(13, 0, 4, 14, 4, 12),
            Block.makeCuboidShape(2, 0, 4, 3, 4, 12),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(4, 0, 13, 12, 4, 14),
            Block.makeCuboidShape(3, 1, 3, 4, 4, 4),
            Block.makeCuboidShape(12, 1, 3, 13, 4, 4),
            Block.makeCuboidShape(12, 1, 12, 13, 4, 13),
            Block.makeCuboidShape(3, 1, 12, 4, 4, 13)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(4, 0, 2, 12, 4, 3),
            Block.makeCuboidShape(13, 0, 4, 14, 4, 12),
            Block.makeCuboidShape(2, 0, 4, 3, 4, 12),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(4, 0, 13, 12, 4, 14),
            Block.makeCuboidShape(3, 1, 3, 4, 4, 4),
            Block.makeCuboidShape(12, 1, 3, 13, 4, 4),
            Block.makeCuboidShape(12, 1, 12, 13, 4, 13),
            Block.makeCuboidShape(3, 1, 12, 4, 4, 13)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(4, 0, 2, 12, 4, 3),
            Block.makeCuboidShape(13, 0, 4, 14, 4, 12),
            Block.makeCuboidShape(2, 0, 4, 3, 4, 12),
            Block.makeCuboidShape(3, 0, 3, 13, 1, 13),
            Block.makeCuboidShape(4, 0, 13, 12, 4, 14),
            Block.makeCuboidShape(3, 1, 3, 4, 4, 4),
            Block.makeCuboidShape(12, 1, 3, 13, 4, 4),
            Block.makeCuboidShape(12, 1, 12, 13, 4, 13),
            Block.makeCuboidShape(3, 1, 12, 4, 4, 13)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();;

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

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

    public BirdNest() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                        .hardnessAndResistance(0.5f,0.5f)
                        .sound(SoundType.WOOD)
                        .harvestLevel(0)
                        .harvestTool(ToolType.AXE)
                //.setLightLevel(value -> 15)
        );
    }

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
