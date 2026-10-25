package com.github.ztymilk.celestium.datagenerator.dimension;

import com.github.ztymilk.celestium.registry.CelestiumDimensions;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class CelestiumDimensionGenerator {
    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        var attributes = EnvironmentAttributeMap.builder()
                .set(EnvironmentAttributes.SKY_COLOR, 0x78A7FF)
                .set(EnvironmentAttributes.FOG_COLOR, 0x78A7FF)
                .build();
        context.register(CelestiumDimensions.CELESTIUM_TYPE_KEY, new DimensionType(
                false,
                true,
                false,
                3.0,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                1.0F,
                new DimensionType.MonsterSettings(UniformInt.of(0, 7), 0),
                DimensionType.Skybox.OVERWORLD,
                DimensionType.CardinalLightType.DEFAULT,
                attributes,
                timelines.getOrThrow(TimelineTags.IN_OVERWORLD)));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var dimensionType = context.lookup(Registries.DIMENSION_TYPE);
        var biomes = context.lookup(Registries.BIOME);
        var noiseSettings = context.lookup(Registries.NOISE_SETTINGS);
        var chunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomes.getOrThrow(Biomes.PLAINS)),
                noiseSettings.getOrThrow(NoiseGeneratorSettings.FLOATING_ISLANDS));
        context.register(CelestiumDimensions.CELESTIUM_LEVEL_KEY,
                new LevelStem(dimensionType.getOrThrow(CelestiumDimensions.CELESTIUM_TYPE_KEY),
                        chunkGenerator));
    }
}
