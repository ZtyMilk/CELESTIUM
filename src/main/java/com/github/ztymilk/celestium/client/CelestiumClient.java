package com.github.ztymilk.celestium.client;

import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumRedSandalBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumSandalBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumXimuBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class CelestiumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.CELESTIAL_GRASS, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumRedSandalBlocks.RED_SANDAL_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumRedSandalBlocks.RED_SANDAL_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumSandalBlocks.SANDAL_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumSandalBlocks.SANDAL_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumXimuBlocks.XIMU_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumXimuBlocks.XIMU_SAPLING, ChunkSectionLayer.CUTOUT);
        CelestiumParticleFactories.initialize();
    }
}
