package com.github.ztymilk.celestium.registry;

import com.github.ztymilk.celestium.Celestium;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class CelestiumDimensions {
    public static final ResourceKey<DimensionType> CELESTIUM_TYPE_KEY = ResourceKey
            .create(Registries.DIMENSION_TYPE, Celestium.id("celestium_type"));
    public static final ResourceKey<NoiseGeneratorSettings> CELESTIUM_NOISE_SETTINGS_KEY = ResourceKey
            .create(Registries.NOISE_SETTINGS, Celestium.id("celestium_floating_islands"));
}
