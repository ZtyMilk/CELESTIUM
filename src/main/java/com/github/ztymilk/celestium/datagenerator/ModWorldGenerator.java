package com.github.ztymilk.celestium.datagenerator;

import com.github.ztymilk.celestium.datagenerator.biome.CelestiumBiomeGenerator;
import com.github.ztymilk.celestium.datagenerator.dimension.CelestiumDimensionGenerator;
import com.github.ztymilk.celestium.datagenerator.feature.CelestiumConfiguredFeatures;
import com.github.ztymilk.celestium.datagenerator.feature.CelestiumPlacedFeatures;
import com.github.ztymilk.celestium.datagenerator.noise.CelestiumSurfaceRuleGenerator;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ModWorldGenerator {
    public static void bootstrap(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.DIMENSION_TYPE, CelestiumDimensionGenerator::bootstrapType);
        registryBuilder.add(Registries.BIOME, CelestiumBiomeGenerator::bootstrap);
        registryBuilder.add(Registries.NOISE_SETTINGS, CelestiumSurfaceRuleGenerator::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, CelestiumConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, CelestiumPlacedFeatures::bootstrap);
    }
}
