package com.github.ztymilk.celestium.registry;

import com.github.ztymilk.celestium.Celestium;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class CelestiumBiomes {
    // 0. 苍穹平原 - 原有天界仙山基准群系 (Original baseline plains biome)
    public static final ResourceKey<Biome> CELESTIAL_PLAINS_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("celestial_plains"));

    // 1. 碧霄瑶池 - 翠绿灵泉与玉色水雾 (Jade Emerald / Pale Cyan Mist)
    public static final ResourceKey<Biome> YAOCHI_POOL_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("yaochi_pool"));

    // 2. 栖霞檀林 - 紫檀古木与晚霞落樱 (Sunset Rose / Amaranth Purple / Incense Amber)
    public static final ResourceKey<Biome> SUNSET_SANDAL_FOREST_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("sunset_sandal_forest"));

    // 3. 玄天广寒 - 极夜星穹与霜晶月魄 (Glacial Pale Azure / Silver Frost / Starlit Midnight)
    public static final ResourceKey<Biome> LUNAR_FROSTPEAK_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("lunar_frostpeak"));

    // 4. 赤霄炎峰 - 朱雀赤羽与烈阳火霞 (Crimson Vermilion / Blazing Gold / Molten Spiritual Flame)
    public static final ResourceKey<Biome> VERMILION_PEAK_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("vermilion_peak"));

    // 5. 紫霄神庭 - 紫微星垣与雷泽祥云 (Royal Amethyst / Imperial Violet / Stardust Lavender)
    public static final ResourceKey<Biome> ZIWEI_CLOUD_DOMAIN_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("ziwei_cloud_domain"));

    // 6. 蓬莱仙岛 - 仙竹流云与碧波灵石 (Emerald Bamboo / Spring Mist / Sacred Flora)
    public static final ResourceKey<Biome> PENGLAI_BAMBOO_SEA_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("penglai_bamboo_sea"));

    // 7. 蟠桃宝苑 - 九天金阙与长生仙桃 (Golden Sunburst / Sacred Nectar / Apricot Glow)
    public static final ResourceKey<Biome> PEACH_ORCHARD_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("peach_orchard"));

    // 8. 北斗星野 - 幽蓝星汉与摘星灵台 (Midnight Indigo / Star Constellations / Cosmic Mist)
    public static final ResourceKey<Biome> BEIDOU_STAR_PLAIN_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("beidou_star_plain"));

    // 9. 九天雷泽 - 碧霄雷动与奔霄灵峰 (Cyan Electric Azure / Gold Lightning / Storm Cloud)
    public static final ResourceKey<Biome> CELESTIAL_THUNDER_PEAK_KEY = ResourceKey.create(
            Registries.BIOME, Celestium.id("celestial_thunder_peak"));
}
