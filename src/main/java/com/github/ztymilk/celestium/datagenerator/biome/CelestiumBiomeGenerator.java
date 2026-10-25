package com.github.ztymilk.celestium.datagenerator.biome;

import com.github.ztymilk.celestium.datagenerator.feature.CelestiumPlacedFeatures;
import com.github.ztymilk.celestium.registry.CelestiumBiomes;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class CelestiumBiomeGenerator {
    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(CelestiumBiomes.CELESTIAL_PLAINS_KEY, celestialPlains(context));
    }

    private static Biome celestialPlains(BootstrapContext<Biome> context) {
        MobSpawnSettings spawnSettings = new MobSpawnSettings.Builder().build();
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                        placedFeatures.getOrThrow(CelestiumPlacedFeatures.PATCH_CELESTIAL_GRASS_PLACED_KEY))
                .build();
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .waterColor(0xf6c85f)
                .foliageColorOverride(0xf6c85f)
                .build();
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.0F)
                .specialEffects(effects)
                .mobSpawnSettings(spawnSettings)
                .generationSettings(generationSettings)
                .build();
    }
}
