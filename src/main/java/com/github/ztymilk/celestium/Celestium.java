package com.github.ztymilk.celestium;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ztymilk.celestium.entity.CelestiumEntities;
import com.github.ztymilk.celestium.registry.CelestiumItemGroups;
import com.github.ztymilk.celestium.registry.CelestiumItems;
import com.github.ztymilk.celestium.registry.block.CelestiumBlocks;
import com.github.ztymilk.celestium.registry.particle.CelestiumParticles;
import com.github.ztymilk.celestium.sound.CelestiumSounds;
import com.github.ztymilk.celestium.world.CelestiumWorldSpawner;

public class Celestium implements ModInitializer {
	public static final String MOD_ID = "celestium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
        CelestiumBlocks.initialize();
        CelestiumItems.initialize();
        CelestiumSounds.initialize();
        CelestiumParticles.initialize();
        CelestiumEntities.initialize();
        CelestiumItemGroups.initialize();
        CelestiumWorldSpawner.initialize();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
