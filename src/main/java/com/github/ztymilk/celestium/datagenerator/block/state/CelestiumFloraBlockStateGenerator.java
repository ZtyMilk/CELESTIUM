package com.github.ztymilk.celestium.datagenerator.block.state;

import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;

import net.minecraft.client.data.models.BlockModelGenerators;

public class CelestiumFloraBlockStateGenerator {
    public static void generate(BlockModelGenerators generator) {
        generator.createGrassLikeBlock(
            CelestiumFloraBlocks.CELESTIAL_GRASS_BLOCK,
            null, null);
    }
}
