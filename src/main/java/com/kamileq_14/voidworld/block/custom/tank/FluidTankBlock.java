package com.kamileq_14.voidworld.block.custom.tank;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.stream.Stream;

public class FluidTankBlock extends Block {

    public FluidTankBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return shape;

    }

    static final VoxelShape shape = Stream.of(
                Block.box(3, 1, 3, 13, 15, 13),
                Block.box(4, 16, 4, 12, 17, 12),
                Block.box(2, 1, 2, 3, 15, 3),
                Block.box(2, 1, 13, 3, 15, 14),
                Block.box(13, 1, 13, 14, 15, 14),
                Block.box(13, 1, 2, 14, 15, 3),
                Block.box(1, 15, 1, 15, 16, 15),
                Block.box(1, 0, 1, 15, 1, 15)

        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();





    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }







}













