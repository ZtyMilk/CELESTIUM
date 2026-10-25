package com.github.ztymilk.celestium.datagenerator.block;

import com.github.ztymilk.celestium.datagenerator.block.loot.CelestiumFloraBlockLootGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class CelestiumBlockLootGenerator extends FabricBlockLootTableProvider {

    public CelestiumBlockLootGenerator(FabricDataOutput dataOutput,
            CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        CelestiumFloraBlockLootGenerator.generate(this);
    }

    @Override
    public void dropSelf(Block block) {
        super.dropSelf(block);
    }
}
