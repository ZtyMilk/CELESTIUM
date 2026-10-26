package com.github.ztymilk.celestium.registry;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.entity.CelestiumEntities;
import com.github.ztymilk.celestium.registry.block.CelestiumDecorativeBlocks;
import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;
import com.github.ztymilk.celestium.registry.block.CelestiumHerbBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumRedSandalBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumSandalBlocks;
import com.github.ztymilk.celestium.registry.block.wood.CelestiumXimuBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CelestiumItemGroups {
    public static final ResourceKey<CreativeModeTab> CELESTIUM_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB, Celestium.id("celestium_tab"));

    public static final CreativeModeTab CELESTIUM_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            CELESTIUM_TAB_KEY,
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(CelestiumFloraBlocks.CELESTIAL_GRASS_BLOCK))
                    .title(Component.translatable("itemGroup.celestium.celestium_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // 1. 经典基准生态方块 (原有顺序严格保留)
                        output.accept(CelestiumFloraBlocks.CELESTIAL_GRASS_BLOCK);
                        output.accept(CelestiumFloraBlocks.CELESTIAL_DIRT);
                        output.accept(CelestiumFloraBlocks.CELESTIAL_GRASS);

                        // 2. 原有三大神木体系 (红檀 -> 白檀 -> 析木，严格保留)
                        output.accept(CelestiumRedSandalBlocks.RED_SANDAL_LOG);
                        output.accept(CelestiumRedSandalBlocks.RED_SANDAL_PLANKS);
                        output.accept(CelestiumRedSandalBlocks.RED_SANDAL_STAIRS);
                        output.accept(CelestiumRedSandalBlocks.RED_SANDAL_SLAB);
                        output.accept(CelestiumRedSandalBlocks.RED_SANDAL_LEAVES);
                        output.accept(CelestiumRedSandalBlocks.RED_SANDAL_SAPLING);

                        output.accept(CelestiumSandalBlocks.SANDAL_LOG);
                        output.accept(CelestiumSandalBlocks.SANDAL_PLANKS);
                        output.accept(CelestiumSandalBlocks.SANDAL_STAIRS);
                        output.accept(CelestiumSandalBlocks.SANDAL_SLAB);
                        output.accept(CelestiumSandalBlocks.SANDAL_LEAVES);
                        output.accept(CelestiumSandalBlocks.SANDAL_SAPLING);

                        output.accept(CelestiumXimuBlocks.XIMU_LOG);
                        output.accept(CelestiumXimuBlocks.XIMU_PLANKS);
                        output.accept(CelestiumXimuBlocks.XIMU_STAIRS);
                        output.accept(CelestiumXimuBlocks.XIMU_SLAB);
                        output.accept(CelestiumXimuBlocks.XIMU_LEAVES);
                        output.accept(CelestiumXimuBlocks.XIMU_SAPLING);

                        output.accept(CelestiumFloraBlocks.CELESTIAL_STONE);

                        // 3. 新增群系地表与特异方块 (9大仙境地皮与石材)
                        output.accept(CelestiumFloraBlocks.CELESTIAL_CLOUD_BLOCK);
                        output.accept(CelestiumFloraBlocks.LUNAR_JADE_STONE);
                        output.accept(CelestiumFloraBlocks.CINNABAR_STONE);
                        output.accept(CelestiumFloraBlocks.WHITE_JADE_BLOCK);
                        output.accept(CelestiumFloraBlocks.WHITE_JADE_PILLAR);
                        output.accept(CelestiumFloraBlocks.JADE_LOTUS);
                        output.accept(CelestiumFloraBlocks.FROST_CRYSTAL_GRASS);
                        output.accept(CelestiumFloraBlocks.BLAZING_SPIRIT_GRASS);

                        output.accept(CelestiumFloraBlocks.ZIWEI_SPIRIT_TURF);
                        output.accept(CelestiumFloraBlocks.ZIWEI_CLOUD_STONE);
                        output.accept(CelestiumFloraBlocks.AMETHYST_SPIRIT_GRASS);

                        output.accept(CelestiumFloraBlocks.EMERALD_MOSS_TURF);
                        output.accept(CelestiumFloraBlocks.EMERALD_JADE_STONE);
                        output.accept(CelestiumFloraBlocks.CELESTIAL_ORCHID);

                        output.accept(CelestiumFloraBlocks.GOLDEN_CELESTIAL_LOAM);
                        output.accept(CelestiumFloraBlocks.GLAZED_SUNSTONE);
                        output.accept(CelestiumFloraBlocks.GOLDEN_LOTUS);

                        // 4. 20种天庭仙草草药
                        for (var herb : CelestiumHerbBlocks.ALL_HERBS) {
                            output.accept(herb);
                        }

                        // 5. 天庭中式装饰与建筑方块
                        for (var decor : CelestiumDecorativeBlocks.ALL_DECORATIONS) {
                            output.accept(decor);
                        }

                        // 6. 天庭生灵刷怪蛋 (全22种天庭神兽神灵仙禽)
                        output.accept(CelestiumEntities.SADDANTA_SPAWN_EGG);
                        output.accept(CelestiumEntities.CELESTIAL_CRANE_SPAWN_EGG);
                        output.accept(CelestiumEntities.MOON_RABBIT_SPAWN_EGG);
                        output.accept(CelestiumEntities.NINE_TAILED_FOX_SPAWN_EGG);
                        output.accept(CelestiumEntities.CLOUD_WHALE_SPAWN_EGG);
                        output.accept(CelestiumEntities.CELESTIAL_DEER_SPAWN_EGG);
                        output.accept(CelestiumEntities.QILIN_SPAWN_EGG);
                        output.accept(CelestiumEntities.AZURE_BIRD_SPAWN_EGG);
                        output.accept(CelestiumEntities.HEAVENLY_SOLDIER_SPAWN_EGG);
                        output.accept(CelestiumEntities.HEAVENLY_GENERAL_SPAWN_EGG);
                        output.accept(CelestiumEntities.SPIRIT_CARP_SPAWN_EGG);
                        output.accept(CelestiumEntities.DAN_FIRE_SPRITE_SPAWN_EGG);
                        output.accept(CelestiumEntities.BIFANG_SPAWN_EGG);
                        output.accept(CelestiumEntities.CLOUD_WYRM_SPAWN_EGG);
                        output.accept(CelestiumEntities.CELESTIAL_HERMIT_SPAWN_EGG);
                        output.accept(CelestiumEntities.BAI_ZE_SPAWN_EGG);
                        output.accept(CelestiumEntities.CANDLE_DRAGON_SPAWN_EGG);
                        output.accept(CelestiumEntities.RAINBOW_PHOENIX_SPAWN_EGG);
                        output.accept(CelestiumEntities.PI_XIU_SPAWN_EGG);
                        output.accept(CelestiumEntities.GOLDEN_CROW_SPAWN_EGG);
                        output.accept(CelestiumEntities.XUAN_GUI_SPAWN_EGG);
                        output.accept(CelestiumEntities.XIE_ZHI_SPAWN_EGG);

                        // 7. 仙家法宝与灵丹圣物
                        output.accept(CelestiumItems.FLYING_SWORD);
                        output.accept(CelestiumItems.CELESTIAL_GOURD);
                        output.accept(CelestiumItems.CELESTIAL_WHISK);
                        output.accept(CelestiumItems.NINE_TURN_ELIXIR);
                        output.accept(CelestiumItems.QI_GATHERING_PILL);
                        output.accept(CelestiumItems.IMMORTAL_SOARING_PILL);
                        output.accept(CelestiumItems.THUNDER_CLEANSING_PILL);
                        output.accept(CelestiumItems.STARLIGHT_ESSENCE_PILL);
                        output.accept(CelestiumItems.ALCHEMY_FURNACE_ITEM);
                    })
                    .build());

    public static void initialize() {
    }
}
