package com.github.ztymilk.celestium.registry.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BlockSubclasses {
    public static class ModBushBlock extends BushBlock {
        public ModBushBlock(BlockBehaviour.Properties properties) {
            super(properties);
        }

        @Override
        protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
            if (state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND)) {
                return true;
            }
            String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
            return path.contains("turf") || path.contains("loam") || path.contains("dirt")
                    || path.contains("stone") || path.contains("jade") || path.contains("grass_block")
                    || path.contains("cloud");
        }
    }

    public static class ModSaplingBlock extends SaplingBlock {
        public ModSaplingBlock(TreeGrower treeGrower, BlockBehaviour.Properties properties) {
            super(treeGrower, properties);
        }

        @Override
        protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
            if (state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND)) {
                return true;
            }
            String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
            return path.contains("turf") || path.contains("loam") || path.contains("dirt")
                    || path.contains("stone") || path.contains("jade") || path.contains("grass_block");
        }
    }

    public static class ModStairBlock extends StairBlock {
        public ModStairBlock(BlockState baseState, BlockBehaviour.Properties properties) {
            super(baseState, properties);
        }
    }
}
