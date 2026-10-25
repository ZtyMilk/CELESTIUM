package com.github.ztymilk.celestium.client;

import com.github.ztymilk.celestium.registry.particle.CelestiumParticles;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FallingLeavesParticle;

public class CelestiumParticleFactories {
    public static void initialize() {
        ParticleFactoryRegistry.getInstance().register(
                CelestiumParticles.RED_SANDAL_LEAVES,
                FallingLeavesParticle.CherryProvider::new);
        ParticleFactoryRegistry.getInstance().register(
                CelestiumParticles.SANDAL_LEAVES,
                FallingLeavesParticle.CherryProvider::new);
        ParticleFactoryRegistry.getInstance().register(
                CelestiumParticles.XIMU_LEAVES,
                FallingLeavesParticle.CherryProvider::new);
    }
}
