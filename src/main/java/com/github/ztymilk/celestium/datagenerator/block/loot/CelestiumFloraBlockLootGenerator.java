package com.github.ztymilk.celestium.datagenerator.block.loot;

import com.github.ztymilk.celestium.datagenerator.block.CelestiumBlockLootGenerator;
import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;

public class CelestiumFloraBlockLootGenerator {

    public static void generate(CelestiumBlockLootGenerator generator) {
        generator.dropSelf(CelestiumFloraBlocks.CELESTIAL_GRASS_BLOCK);
    }
}
