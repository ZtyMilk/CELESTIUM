package com.github.ztymilk.celestium.datagenerator;

import java.util.concurrent.CompletableFuture;

import com.github.ztymilk.celestium.datagenerator.block.CelestiumBlockLootGenerator;
import com.github.ztymilk.celestium.datagenerator.block.CelestiumBlockStateGenerator;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(WorldGeneratorProvider::new);
        // pack.addProvider(CelestiumBlockStateGenerator::new);
        pack.addProvider(CelestiumBlockLootGenerator::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registrySetBuilder) {
        ModWorldGenerator.bootstrap(registrySetBuilder);
    }

    private static class WorldGeneratorProvider extends FabricDynamicRegistryProvider {
        public WorldGeneratorProvider(FabricDataOutput output,
                CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(HolderLookup.Provider registries, Entries entries) {
            entries.addAll(registries.lookupOrThrow(Registries.DIMENSION_TYPE));
            entries.addAll(registries.lookupOrThrow(Registries.BIOME));
            entries.addAll(registries.lookupOrThrow(Registries.NOISE_SETTINGS));
            entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
            entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        }

        @Override
        public String getName() {
            return "Celestium World Generator";
        }
    }
}
