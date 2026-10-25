package com.github.ztymilk.celestium.datagenerator.noise;

import java.util.List;

import com.github.ztymilk.celestium.registry.CelestiumDimensions;
import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class CelestiumSurfaceRuleGenerator {
    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        var densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        var noises = context.lookup(Registries.NOISE);
        var surfaceRule = makeRules();
        context.register(CelestiumDimensions.CELESTIUM_NOISE_SETTINGS_KEY,
                new NoiseGeneratorSettings(
                        NoiseSettings.create(0, 256, 2, 1),
                        Blocks.STONE.defaultBlockState(),
                        Blocks.WATER.defaultBlockState(),
                        NoiseRouterData.floatingIslands(densityFunctions, noises),
                        surfaceRule,
                        List.of(),
                        -64,
                        false,
                        false,
                        false,
                        true));
    }

    private static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.state(CelestiumFloraBlocks.CELESTIAL_GRASS_BLOCK.defaultBlockState())),
                SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.state(CelestiumFloraBlocks.CELESTIAL_DIRT.defaultBlockState())));
    }
}
