package com.github.ztymilk.celestium.entity;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.entity.custom.*;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;

public class CelestiumEntities {
    // 1. 鍏墮鐧借薄 (Saddanta)
    // 1. 六牙白象 (Saddanta) - 须弥仙山巨象
    public static final ResourceKey<EntityType<?>> SADDANTA_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("saddanta"));
    public static final EntityType<SaddantaEntity> SADDANTA = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, SADDANTA_KEY,
            EntityType.Builder.of(SaddantaEntity::new, MobCategory.CREATURE).sized(3.8F, 4.6F).build(SADDANTA_KEY));

    // 2. 涓归《浠欓工 (Celestial Crane)
    public static final ResourceKey<EntityType<?>> CELESTIAL_CRANE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("celestial_crane"));
    public static final EntityType<CelestialCraneEntity> CELESTIAL_CRANE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, CELESTIAL_CRANE_KEY,
            EntityType.Builder.of(CelestialCraneEntity::new, MobCategory.CREATURE).sized(0.7F, 1.8F).build(CELESTIAL_CRANE_KEY));

    // 3. 骞垮瘨鐜夊厰 (Moon Rabbit)
    public static final ResourceKey<EntityType<?>> MOON_RABBIT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("moon_rabbit"));
    public static final EntityType<MoonRabbitEntity> MOON_RABBIT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, MOON_RABBIT_KEY,
            EntityType.Builder.of(MoonRabbitEntity::new, MobCategory.CREATURE).sized(0.6F, 0.6F).build(MOON_RABBIT_KEY));

    // 4. 涔濆熬鐏电嫄 (Nine-Tailed Fox)
    public static final ResourceKey<EntityType<?>> NINE_TAILED_FOX_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("nine_tailed_fox"));
    public static final EntityType<NineTailedFoxEntity> NINE_TAILED_FOX = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, NINE_TAILED_FOX_KEY,
            EntityType.Builder.of(NineTailedFoxEntity::new, MobCategory.CREATURE).sized(0.9F, 1.0F).build(NINE_TAILED_FOX_KEY));

    // 5. 流云巨鲲 (Cloud Whale) - 遮天巨兽
    public static final ResourceKey<EntityType<?>> CLOUD_WHALE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("cloud_whale"));
    public static final EntityType<CloudWhaleEntity> CLOUD_WHALE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, CLOUD_WHALE_KEY,
            EntityType.Builder.of(CloudWhaleEntity::new, MobCategory.AMBIENT).sized(18.0F, 8.5F).build(CLOUD_WHALE_KEY));

    // 6. 九色神鹿 (Celestial Deer)
    public static final ResourceKey<EntityType<?>> CELESTIAL_DEER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("celestial_deer"));
    public static final EntityType<CelestialDeerEntity> CELESTIAL_DEER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, CELESTIAL_DEER_KEY,
            EntityType.Builder.of(CelestialDeerEntity::new, MobCategory.CREATURE).sized(1.2F, 1.8F).build(CELESTIAL_DEER_KEY));

    // 7. 玄玉麒麟 (Qilin) - 威严神骏
    public static final ResourceKey<EntityType<?>> QILIN_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("qilin"));
    public static final EntityType<QilinEntity> QILIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, QILIN_KEY,
            EntityType.Builder.of(QilinEntity::new, MobCategory.CREATURE).sized(2.2F, 2.6F).build(QILIN_KEY));

    // 8. 闈掗妇绁為笩 (Azure Bird)
    public static final ResourceKey<EntityType<?>> AZURE_BIRD_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("azure_bird"));
    public static final EntityType<AzureBirdEntity> AZURE_BIRD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, AZURE_BIRD_KEY,
            EntityType.Builder.of(AzureBirdEntity::new, MobCategory.CREATURE).sized(0.8F, 1.4F).build(AZURE_BIRD_KEY));

    // 9. 澶╁涵绁炲叺 (Heavenly Soldier)
    public static final ResourceKey<EntityType<?>> HEAVENLY_SOLDIER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("heavenly_soldier"));
    public static final EntityType<HeavenlySoldierEntity> HEAVENLY_SOLDIER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, HEAVENLY_SOLDIER_KEY,
            EntityType.Builder.of(HeavenlySoldierEntity::new, MobCategory.CREATURE).sized(0.6F, 1.9F).build(HEAVENLY_SOLDIER_KEY));

    // 10. 闀囨绁炲皢 (Heavenly General)
    public static final ResourceKey<EntityType<?>> HEAVENLY_GENERAL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("heavenly_general"));
    public static final EntityType<HeavenlyGeneralEntity> HEAVENLY_GENERAL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, HEAVENLY_GENERAL_KEY,
            EntityType.Builder.of(HeavenlyGeneralEntity::new, MobCategory.CREATURE).sized(1.0F, 2.5F).build(HEAVENLY_GENERAL_KEY));

    // 11. 鐜勫ぉ閿﹂菠 (Spirit Carp)
    public static final ResourceKey<EntityType<?>> SPIRIT_CARP_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("spirit_carp"));
    public static final EntityType<SpiritCarpEntity> SPIRIT_CARP = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, SPIRIT_CARP_KEY,
            EntityType.Builder.of(SpiritCarpEntity::new, MobCategory.WATER_CREATURE).sized(0.6F, 0.4F).build(SPIRIT_CARP_KEY));

    // 12. 涓圭伀鐏电簿 (Dan Fire Sprite)
    public static final ResourceKey<EntityType<?>> DAN_FIRE_SPRITE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("dan_fire_sprite"));
    public static final EntityType<DanFireSpriteEntity> DAN_FIRE_SPRITE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, DAN_FIRE_SPRITE_KEY,
            EntityType.Builder.of(DanFireSpriteEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build(DAN_FIRE_SPRITE_KEY));

    // 13. 姣曟柟绁為笩 (Bi Fang)
    public static final ResourceKey<EntityType<?>> BIFANG_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("bifang"));
    public static final EntityType<BiFangEntity> BIFANG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, BIFANG_KEY,
            EntityType.Builder.of(BiFangEntity::new, MobCategory.CREATURE).sized(1.0F, 1.8F).build(BIFANG_KEY));

    // 14. 幻云蛟龙 (Cloud Wyrm) - 东方苍龙
    public static final ResourceKey<EntityType<?>> CLOUD_WYRM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("cloud_wyrm"));
    public static final EntityType<CloudWyrmEntity> CLOUD_WYRM = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, CLOUD_WYRM_KEY,
            EntityType.Builder.of(CloudWyrmEntity::new, MobCategory.AMBIENT).sized(6.0F, 4.0F).build(CLOUD_WYRM_KEY));

    // 15. 云游仙灵 (Celestial Hermit)
    public static final ResourceKey<EntityType<?>> CELESTIAL_HERMIT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("celestial_hermit"));
    public static final EntityType<CelestialHermitEntity> CELESTIAL_HERMIT = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, CELESTIAL_HERMIT_KEY,
            EntityType.Builder.of(CelestialHermitEntity::new, MobCategory.CREATURE).sized(0.6F, 1.9F).build(CELESTIAL_HERMIT_KEY));

    // 16. 辟邪白泽 (Bai Ze)
    public static final ResourceKey<EntityType<?>> BAI_ZE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("bai_ze"));
    public static final EntityType<BaiZeEntity> BAI_ZE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, BAI_ZE_KEY,
            EntityType.Builder.of(BaiZeEntity::new, MobCategory.CREATURE).sized(1.4F, 1.8F).build(BAI_ZE_KEY));

    // 17. 衔烛幼龙 (Candle Dragon) - 照天古龙
    public static final ResourceKey<EntityType<?>> CANDLE_DRAGON_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("candle_dragon"));
    public static final EntityType<CandleDragonEntity> CANDLE_DRAGON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, CANDLE_DRAGON_KEY,
            EntityType.Builder.of(CandleDragonEntity::new, MobCategory.AMBIENT).sized(6.2F, 4.2F).build(CANDLE_DRAGON_KEY));

    // 18. 九天玄凤 (Rainbow Phoenix) - 百鸟之王
    public static final ResourceKey<EntityType<?>> RAINBOW_PHOENIX_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("rainbow_phoenix"));
    public static final EntityType<RainbowPhoenixEntity> RAINBOW_PHOENIX = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, RAINBOW_PHOENIX_KEY,
            EntityType.Builder.of(RainbowPhoenixEntity::new, MobCategory.CREATURE).sized(2.6F, 3.2F).build(RAINBOW_PHOENIX_KEY));

    // 19. 澶╃璨旇矃 (Pi Xiu)
    public static final ResourceKey<EntityType<?>> PI_XIU_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("pi_xiu"));
    public static final EntityType<PiXiuEntity> PI_XIU = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, PI_XIU_KEY,
            EntityType.Builder.of(PiXiuEntity::new, MobCategory.CREATURE).sized(1.4F, 1.3F).build(PI_XIU_KEY));

    // 20. 涓夎冻閲戜箤 (Three-Legged Golden Crow)
    public static final ResourceKey<EntityType<?>> GOLDEN_CROW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("golden_crow"));
    public static final EntityType<GoldenCrowEntity> GOLDEN_CROW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, GOLDEN_CROW_KEY,
            EntityType.Builder.of(GoldenCrowEntity::new, MobCategory.CREATURE).sized(1.0F, 1.4F).build(GOLDEN_CROW_KEY));

    // 21. 旋龟 (Xuan Gui)
    public static final ResourceKey<EntityType<?>> XUAN_GUI_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("xuan_gui"));
    public static final EntityType<XuanGuiEntity> XUAN_GUI = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, XUAN_GUI_KEY,
            EntityType.Builder.of(XuanGuiEntity::new, MobCategory.CREATURE).sized(1.4F, 0.8F).build(XUAN_GUI_KEY));

    // 22. 獬豸 (Xie Zhi)
    public static final ResourceKey<EntityType<?>> XIE_ZHI_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Celestium.id("xie_zhi"));
    public static final EntityType<XieZhiEntity> XIE_ZHI = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, XIE_ZHI_KEY,
            EntityType.Builder.of(XieZhiEntity::new, MobCategory.CREATURE).sized(1.5F, 1.6F).build(XIE_ZHI_KEY));

    // --- 瀵瑰簲鍒锋€泲 (Spawn Eggs) ---
    public static final Item SADDANTA_SPAWN_EGG = registerEgg("saddanta_spawn_egg", SADDANTA);
    public static final Item CELESTIAL_CRANE_SPAWN_EGG = registerEgg("celestial_crane_spawn_egg", CELESTIAL_CRANE);
    public static final Item MOON_RABBIT_SPAWN_EGG = registerEgg("moon_rabbit_spawn_egg", MOON_RABBIT);
    public static final Item NINE_TAILED_FOX_SPAWN_EGG = registerEgg("nine_tailed_fox_spawn_egg", NINE_TAILED_FOX);
    public static final Item CLOUD_WHALE_SPAWN_EGG = registerEgg("cloud_whale_spawn_egg", CLOUD_WHALE);
    public static final Item CELESTIAL_DEER_SPAWN_EGG = registerEgg("celestial_deer_spawn_egg", CELESTIAL_DEER);
    public static final Item QILIN_SPAWN_EGG = registerEgg("qilin_spawn_egg", QILIN);
    public static final Item AZURE_BIRD_SPAWN_EGG = registerEgg("azure_bird_spawn_egg", AZURE_BIRD);
    public static final Item HEAVENLY_SOLDIER_SPAWN_EGG = registerEgg("heavenly_soldier_spawn_egg", HEAVENLY_SOLDIER);
    public static final Item HEAVENLY_GENERAL_SPAWN_EGG = registerEgg("heavenly_general_spawn_egg", HEAVENLY_GENERAL);
    public static final Item SPIRIT_CARP_SPAWN_EGG = registerEgg("spirit_carp_spawn_egg", SPIRIT_CARP);
    public static final Item DAN_FIRE_SPRITE_SPAWN_EGG = registerEgg("dan_fire_sprite_spawn_egg", DAN_FIRE_SPRITE);
    public static final Item BIFANG_SPAWN_EGG = registerEgg("bifang_spawn_egg", BIFANG);
    public static final Item CLOUD_WYRM_SPAWN_EGG = registerEgg("cloud_wyrm_spawn_egg", CLOUD_WYRM);
    public static final Item CELESTIAL_HERMIT_SPAWN_EGG = registerEgg("celestial_hermit_spawn_egg", CELESTIAL_HERMIT);
    public static final Item BAI_ZE_SPAWN_EGG = registerEgg("bai_ze_spawn_egg", BAI_ZE);
    public static final Item CANDLE_DRAGON_SPAWN_EGG = registerEgg("candle_dragon_spawn_egg", CANDLE_DRAGON);
    public static final Item RAINBOW_PHOENIX_SPAWN_EGG = registerEgg("rainbow_phoenix_spawn_egg", RAINBOW_PHOENIX);
    public static final Item PI_XIU_SPAWN_EGG = registerEgg("pi_xiu_spawn_egg", PI_XIU);
    public static final Item GOLDEN_CROW_SPAWN_EGG = registerEgg("golden_crow_spawn_egg", GOLDEN_CROW);
    public static final Item XUAN_GUI_SPAWN_EGG = registerEgg("xuan_gui_spawn_egg", XUAN_GUI);
    public static final Item XIE_ZHI_SPAWN_EGG = registerEgg("xie_zhi_spawn_egg", XIE_ZHI);

    private static Item registerEgg(String name, EntityType<?> type) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Celestium.id(name));
        return Registry.register(BuiltInRegistries.ITEM, key, new ModSpawnEggItem(type, new Item.Properties().setId(key)));
    }

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(SADDANTA, SaddantaEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CELESTIAL_CRANE, CelestialCraneEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MOON_RABBIT, MoonRabbitEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(NINE_TAILED_FOX, NineTailedFoxEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CLOUD_WHALE, CloudWhaleEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CELESTIAL_DEER, CelestialDeerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(QILIN, QilinEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(AZURE_BIRD, AzureBirdEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(HEAVENLY_SOLDIER, HeavenlySoldierEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(HEAVENLY_GENERAL, HeavenlyGeneralEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(SPIRIT_CARP, SpiritCarpEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(DAN_FIRE_SPRITE, DanFireSpriteEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BIFANG, BiFangEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CLOUD_WYRM, CloudWyrmEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CELESTIAL_HERMIT, CelestialHermitEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BAI_ZE, BaiZeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CANDLE_DRAGON, CandleDragonEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(RAINBOW_PHOENIX, RainbowPhoenixEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(PI_XIU, PiXiuEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(GOLDEN_CROW, GoldenCrowEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(XUAN_GUI, XuanGuiEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(XIE_ZHI, XieZhiEntity.createAttributes());

        // 娉ㄥ唽鑷劧鐢熸垚瑙勫垯
        SpawnPlacements.register(SADDANTA, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(CELESTIAL_CRANE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(MOON_RABBIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(NINE_TAILED_FOX, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(CLOUD_WHALE, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(CELESTIAL_DEER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(QILIN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(AZURE_BIRD, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(HEAVENLY_SOLDIER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(HEAVENLY_GENERAL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(SPIRIT_CARP, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(DAN_FIRE_SPRITE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(BIFANG, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(CLOUD_WYRM, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(CELESTIAL_HERMIT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(BAI_ZE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(CANDLE_DRAGON, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(RAINBOW_PHOENIX, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(PI_XIU, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(GOLDEN_CROW, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(XUAN_GUI, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
        SpawnPlacements.register(XIE_ZHI, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (type, level, reason, pos, random) -> true);
    }
}
