package com.github.ztymilk.celestium.datagenerator.feature;

import com.github.ztymilk.celestium.Celestium;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CelestiumPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PATCH_CELESTIAL_GRASS_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, Celestium.id("patch_celestial_grass_placed"));

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                PATCH_CELESTIAL_GRASS_PLACED_KEY,
                configuredFeatures.getOrThrow(CelestiumConfiguredFeatures.PATCH_CELESTIAL_GRASS_KEY),
                // 噪波控制密度：噪波值大于 -0.8 时生成 5 簇，否则生成 10 簇（形成自然的疏密斑块）
                NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE, // 贴合最高地表
                BiomeFilter.biome()                     // 仅在当前群系内
        );
    }
}