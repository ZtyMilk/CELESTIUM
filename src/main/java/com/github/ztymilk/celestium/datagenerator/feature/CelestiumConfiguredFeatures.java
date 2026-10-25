package com.github.ztymilk.celestium.datagenerator.feature;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumRedSandalBlocks;
import com.github.ztymilk.celestium.registry.worldgenerator.CelestiumTreeFeatures;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class CelestiumConfiguredFeatures {
    // celestial_grass
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CELESTIAL_GRASS_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            Celestium.id("patch_celestial_grass"));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(
                context,
                PATCH_CELESTIAL_GRASS_KEY,
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        32,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(
                                                CelestiumFloraBlocks.CELESTIAL_GRASS)))));
        FeatureUtils.register(
                context,
                CelestiumTreeFeatures.RED_SANDAL_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(CelestiumRedSandalBlocks.RED_SANDAL_LOG),
                        new StraightTrunkPlacer(5, 2, 1), // 树干：高 5~8 格
                        BlockStateProvider.simple(CelestiumRedSandalBlocks.RED_SANDAL_LEAVES),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), // 树冠：半径 2，高 3
                        new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    }
}
