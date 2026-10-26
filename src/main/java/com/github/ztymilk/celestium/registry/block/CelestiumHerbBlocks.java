package com.github.ztymilk.celestium.registry.block;

import com.github.ztymilk.celestium.Celestium;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * 20种天庭仙草草药注册类 (20 Celestial Herbs & Spiritual Plants)
 */
public class CelestiumHerbBlocks {
    public static final List<Block> ALL_HERBS = new ArrayList<>();

    // 1. 九叶灵芝 (Nine-Leaf Lingzhi) - 赤金神芝
    public static final Block NINE_LEAF_LINGZHI = registerHerb("nine_leaf_lingzhi", 7);

    // 2. 千年何首乌 (Thousand-Year Polygonum) - 聚天地精气
    public static final Block THOUSAND_YEAR_POLYGONUM = registerHerb("thousand_year_polygonum", 0);

    // 3. 九品还魂草 (Soul-Reviving Grass) - 碧光生肌
    public static final Block SOUL_REVIVING_GRASS = registerHerb("soul_reviving_grass", 8);

    // 4. 雪莲仙草 (Snow Lotus Herb) - 冰魄冷香
    public static final Block SNOW_LOTUS_HERB = registerHerb("snow_lotus_herb", 6);

    // 5. 龙涎朱果 (Dragon Saliva Fruit) - 赤霞炽烈
    public static final Block DRAGON_SALIVA_FRUIT = registerHerb("dragon_saliva_fruit", 9);

    // 6. 紫霄灵芝 (Purple Cloud Reishi) - 紫气东来
    public static final Block PURPLE_CLOUD_REISHI = registerHerb("purple_cloud_reishi", 7);

    // 7. 纯阳金参 (Sun Spirit Ginseng) - 太阳元阳
    public static final Block SUN_SPIRIT_GINSENG = registerHerb("sun_spirit_ginseng", 10);

    // 8. 凝碧仙笋 (Clear Jade Bamboo Shoot) - 生机勃发
    public static final Block CLEAR_JADE_BAMBOO_SHOOT = registerHerb("clear_jade_bamboo_shoot", 0);

    // 9. 望月幽兰 (Moon-Gazing Orchid) - 太阴清辉
    public static final Block MOON_GAZING_ORCHID = registerHerb("moon_gazing_orchid", 5);

    // 10. 悟道仙茗 (Dao Enlightenment Tea) - 清心明道
    public static final Block DAO_ENLIGHTENMENT_TEA = registerHerb("dao_enlightenment_tea", 0);

    // 11. 凤羽灵蕨 (Phoenix Feather Fern) - 涅槃祥羽
    public static final Block PHOENIX_FEATHER_FERN = registerHerb("phoenix_feather_fern", 8);

    // 12. 太乙拂尘草 (Taiyi Whisk Grass) - 白玉拂尘
    public static final Block TAIYI_WHISK_GRASS = registerHerb("taiyi_whisk_grass", 0);

    // 13. 碧海玄珠花 (Azure Sea Pearl Flower) - 蕴含夜明宝珠
    public static final Block AZURE_SEA_PEARL_FLOWER = registerHerb("azure_sea_pearl_flower", 8);

    // 14. 青玄灵藤 (Celestial Morning Vine) - 仙云青萝
    public static final Block CELESTIAL_MORNING_VINE = registerHerb("celestial_morning_vine", 0);

    // 15. 赤血玄参 (Crimson Blood Ginseng) - 延年益寿
    public static final Block CRIMSON_BLOOD_GINSENG = registerHerb("crimson_blood_ginseng", 6);

    // 16. 冰魄水仙 (Ice Soul Water Lily) - 凝霜若雪
    public static final Block ICE_SOUL_WATER_LILY = registerHerb("ice_soul_water_lily", 7);

    // 17. 极光仙草 (Aurora Spirit Grass) - 五彩流光
    public static final Block AURORA_SPIRIT_GRASS = registerHerb("aurora_spirit_grass", 9);

    // 18. 太清仙芝 (Taiqing Immortal Herb) - 三清玄妙
    public static final Block TAIQING_IMMORTAL_HERB = registerHerb("taiqing_immortal_herb", 8);

    // 19. 百炼仙藤 (Hundred-Refinement Vine) - 金刚不朽
    public static final Block HUNDRED_REFINEMENT_VINE = registerHerb("hundred_refinement_vine", 0);

    // 20. 云霖仙花 (Cloud Dew Blossom) - 承甘霖灵露
    public static final Block CLOUD_DEW_BLOSSOM = registerHerb("cloud_dew_blossom", 6);

    private static Block registerHerb(String name, int lightLevel) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Celestium.id(name));
        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)
                .sound(SoundType.GRASS)
                .noCollision()
                .instabreak()
                .setId(blockKey);
        if (lightLevel > 0) {
            props.lightLevel(state -> lightLevel);
        }

        Block block = new BlockSubclasses.ModBushBlock(props);
        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Celestium.id(name));
        Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, new Item.Properties().setId(itemKey)));

        ALL_HERBS.add(block);
        return block;
    }

    public static void initialize() {
    }
}
