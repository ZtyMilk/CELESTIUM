package com.github.ztymilk.celestium.registry.particle;

import com.github.ztymilk.celestium.Celestium;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class CelestiumParticles {
    public static final SimpleParticleType RED_SANDAL_LEAVES = FabricParticleTypes.simple();
    public static final SimpleParticleType SANDAL_LEAVES = FabricParticleTypes.simple();
    public static final SimpleParticleType XIMU_LEAVES = FabricParticleTypes.simple();
    public static void initialize() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Celestium.id("red_sandal_leaves"), RED_SANDAL_LEAVES);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Celestium.id("sandal_leaves"), SANDAL_LEAVES);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Celestium.id("ximu_leaves"), XIMU_LEAVES);
    }
}
