package com.github.ztymilk.celestium.registry;

import com.github.ztymilk.celestium.Celestium;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class CelestiumBiomes {
    public static final ResourceKey<Biome> CELESTIAL_PLAINS_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("celestial_plains"));
}
