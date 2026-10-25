package com.github.ztymilk.celestium.registry.worldgenerator;

import com.github.ztymilk.celestium.Celestium;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CelestiumTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SANDAL_TREE = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, Celestium.id("red_sandal_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDAL_TREE = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, Celestium.id("sandal_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> XIMU_TREE = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, Celestium.id("ximu_tree"));
}
