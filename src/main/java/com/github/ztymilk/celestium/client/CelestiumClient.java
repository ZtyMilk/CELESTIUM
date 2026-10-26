package com.github.ztymilk.celestium.client;

import com.github.ztymilk.celestium.client.entity.model.*;
import com.github.ztymilk.celestium.client.entity.renderer.*;
import com.github.ztymilk.celestium.entity.CelestiumEntities;
import com.github.ztymilk.celestium.registry.block.CelestiumDecorativeBlocks;
import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;
import com.github.ztymilk.celestium.registry.block.CelestiumHerbBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumRedSandalBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumSandalBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumXimuBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class CelestiumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 方块渲染图层
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.CELESTIAL_GRASS, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.CELESTIAL_CLOUD_BLOCK, ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.JADE_LOTUS, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.FROST_CRYSTAL_GRASS, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.BLAZING_SPIRIT_GRASS, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.AMETHYST_SPIRIT_GRASS, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.CELESTIAL_ORCHID, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumFloraBlocks.GOLDEN_LOTUS, ChunkSectionLayer.CUTOUT);

        // 20种仙草全部采用 CUTOUT 剔除渲染
        for (var herb : CelestiumHerbBlocks.ALL_HERBS) {
            BlockRenderLayerMap.putBlock(herb, ChunkSectionLayer.CUTOUT);
        }

        // 装饰与新增特异方块渲染图层
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.CELESTIAL_PALACE_LANTERN, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.BRONZE_INCENSE_BURNER, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.WHITE_JADE_FENCE, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.CELESTIAL_ALTAR_TABLE, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.SPIRIT_CHIME_BELL, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.STAR_FLOWER, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.THUNDER_SPIRIT_ORCHID, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumDecorativeBlocks.CELESTIAL_CLOUD_ALTAR, ChunkSectionLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(CelestiumRedSandalBlocks.RED_SANDAL_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumRedSandalBlocks.RED_SANDAL_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumSandalBlocks.SANDAL_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumSandalBlocks.SANDAL_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumXimuBlocks.XIMU_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(CelestiumXimuBlocks.XIMU_SAPLING, ChunkSectionLayer.CUTOUT);

        // 实体模型层注册 (22种天庭仙兽神灵)
        EntityModelLayerRegistry.registerModelLayer(SaddantaModel.LAYER_LOCATION, SaddantaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CelestialCraneModel.LAYER_LOCATION, CelestialCraneModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(MoonRabbitModel.LAYER_LOCATION, MoonRabbitModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NineTailedFoxModel.LAYER_LOCATION, NineTailedFoxModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CloudWhaleModel.LAYER_LOCATION, CloudWhaleModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CelestialDeerModel.LAYER_LOCATION, CelestialDeerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(QilinModel.LAYER_LOCATION, QilinModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AzureBirdModel.LAYER_LOCATION, AzureBirdModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BiFangModel.LAYER_LOCATION, BiFangModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(HeavenlySoldierModel.LAYER_LOCATION, HeavenlySoldierModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(HeavenlyGeneralModel.LAYER_LOCATION, HeavenlyGeneralModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SpiritCarpModel.LAYER_LOCATION, SpiritCarpModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DanFireSpriteModel.LAYER_LOCATION, DanFireSpriteModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CloudWyrmModel.LAYER_LOCATION, CloudWyrmModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CelestialHermitModel.LAYER_LOCATION, CelestialHermitModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BaiZeModel.LAYER_LOCATION, BaiZeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(CandleDragonModel.LAYER_LOCATION, CandleDragonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(RainbowPhoenixModel.LAYER_LOCATION, RainbowPhoenixModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PiXiuModel.LAYER_LOCATION, PiXiuModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GoldenCrowModel.LAYER_LOCATION, GoldenCrowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(XuanGuiModel.LAYER_LOCATION, XuanGuiModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(XieZhiModel.LAYER_LOCATION, XieZhiModel::createBodyLayer);

        // 实体渲染器注册
        EntityRendererRegistry.register(CelestiumEntities.SADDANTA, SaddantaRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.CELESTIAL_CRANE, CelestialCraneRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.MOON_RABBIT, MoonRabbitRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.NINE_TAILED_FOX, NineTailedFoxRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.CLOUD_WHALE, CloudWhaleRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.CELESTIAL_DEER, CelestialDeerRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.QILIN, QilinRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.AZURE_BIRD, AzureBirdRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.BIFANG, BiFangRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.HEAVENLY_SOLDIER, HeavenlySoldierRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.HEAVENLY_GENERAL, HeavenlyGeneralRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.SPIRIT_CARP, SpiritCarpRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.DAN_FIRE_SPRITE, DanFireSpriteRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.CLOUD_WYRM, CloudWyrmRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.CELESTIAL_HERMIT, CelestialHermitRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.BAI_ZE, BaiZeRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.CANDLE_DRAGON, CandleDragonRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.RAINBOW_PHOENIX, RainbowPhoenixRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.PI_XIU, PiXiuRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.GOLDEN_CROW, GoldenCrowRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.XUAN_GUI, XuanGuiRenderer::new);
        EntityRendererRegistry.register(CelestiumEntities.XIE_ZHI, XieZhiRenderer::new);

        CelestiumParticleFactories.initialize();
    }
}
