package com.github.ztymilk.celestium.registry.block;

import com.github.ztymilk.celestium.Celestium;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

public class CelestiumFloraBlocks {
    // 1. 经典仙境生态方块
    public static final ResourceKey<Block> CELESTIAL_GRASS_BLOCK_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_grass_block"));
    public static final Block CELESTIAL_GRASS_BLOCK = registerBlock(
            CELESTIAL_GRASS_BLOCK_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                    .sound(SoundType.GRASS)
                    .strength(0.6F)
                    .setId(CELESTIAL_GRASS_BLOCK_KEY)));

    public static final ResourceKey<Block> CELESTIAL_DIRT_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_dirt"));
    public static final Block CELESTIAL_DIRT = registerBlock(
            CELESTIAL_DIRT_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                    .setId(CELESTIAL_DIRT_KEY)));

    public static final ResourceKey<Block> CELESTIAL_GRASS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_grass"));
    public static final Block CELESTIAL_GRASS = registerBlock(
            CELESTIAL_GRASS_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .sound(SoundType.GRASS)
                    .noCollision()
                    .instabreak()
                    .setId(CELESTIAL_GRASS_KEY)));

    public static final ResourceKey<Block> CELESTIAL_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_stone"));
    public static final Block CELESTIAL_STONE = registerBlock(
            CELESTIAL_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .setId(CELESTIAL_STONE_KEY)));

    public static final ResourceKey<Block> CELESTIAL_CLOUD_BLOCK_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_cloud_block"));
    public static final Block CELESTIAL_CLOUD_BLOCK = registerBlock(
            CELESTIAL_CLOUD_BLOCK_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)
                    .sound(SoundType.WOOL)
                    .strength(0.2F)
                    .noOcclusion()
                    .setId(CELESTIAL_CLOUD_BLOCK_KEY)));

    public static final ResourceKey<Block> LUNAR_JADE_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("lunar_jade_stone"));
    public static final Block LUNAR_JADE_STONE = registerBlock(
            LUNAR_JADE_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .sound(SoundType.STONE)
                    .strength(1.5F, 6.0F)
                    .setId(LUNAR_JADE_STONE_KEY)));

    public static final ResourceKey<Block> CINNABAR_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("cinnabar_stone"));
    public static final Block CINNABAR_STONE = registerBlock(
            CINNABAR_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)
                    .sound(SoundType.STONE)
                    .strength(1.5F, 6.0F)
                    .setId(CINNABAR_STONE_KEY)));

    public static final ResourceKey<Block> WHITE_JADE_BLOCK_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("white_jade_block"));
    public static final Block WHITE_JADE_BLOCK = registerBlock(
            WHITE_JADE_BLOCK_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
                    .sound(SoundType.STONE)
                    .strength(1.2F, 4.0F)
                    .setId(WHITE_JADE_BLOCK_KEY)));

    public static final ResourceKey<Block> WHITE_JADE_PILLAR_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("white_jade_pillar"));
    public static final Block WHITE_JADE_PILLAR = registerBlock(
            WHITE_JADE_PILLAR_KEY,
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)
                    .sound(SoundType.STONE)
                    .strength(1.2F, 4.0F)
                    .setId(WHITE_JADE_PILLAR_KEY)));

    public static final ResourceKey<Block> JADE_LOTUS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("jade_lotus"));
    public static final Block JADE_LOTUS = registerBlock(
            JADE_LOTUS_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
                    .sound(SoundType.GRASS)
                    .noCollision()
                    .instabreak()
                    .setId(JADE_LOTUS_KEY)));

    public static final ResourceKey<Block> FROST_CRYSTAL_GRASS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("frost_crystal_grass"));
    public static final Block FROST_CRYSTAL_GRASS = registerBlock(
            FROST_CRYSTAL_GRASS_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .sound(SoundType.GRASS)
                    .noCollision()
                    .instabreak()
                    .setId(FROST_CRYSTAL_GRASS_KEY)));

    public static final ResourceKey<Block> BLAZING_SPIRIT_GRASS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("blazing_spirit_grass"));
    public static final Block BLAZING_SPIRIT_GRASS = registerBlock(
            BLAZING_SPIRIT_GRASS_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)
                    .sound(SoundType.GRASS)
                    .lightLevel(state -> 8)
                    .noCollision()
                    .instabreak()
                    .setId(BLAZING_SPIRIT_GRASS_KEY)));

    // 2. 新增3个群系专属地皮与仙草 (New Biome Surface & Flora Blocks)
    // 紫霄神庭 (Ziwei Cloud Domain) - 紫霄灵壤、紫霄云石、紫芝仙草
    public static final ResourceKey<Block> ZIWEI_SPIRIT_TURF_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ziwei_spirit_turf"));
    public static final Block ZIWEI_SPIRIT_TURF = registerBlock(
            ZIWEI_SPIRIT_TURF_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                    .sound(SoundType.GRASS)
                    .strength(0.6F)
                    .setId(ZIWEI_SPIRIT_TURF_KEY)));

    public static final ResourceKey<Block> ZIWEI_CLOUD_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ziwei_cloud_stone"));
    public static final Block ZIWEI_CLOUD_STONE = registerBlock(
            ZIWEI_CLOUD_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .sound(SoundType.STONE)
                    .strength(1.5F, 6.0F)
                    .setId(ZIWEI_CLOUD_STONE_KEY)));

    public static final ResourceKey<Block> AMETHYST_SPIRIT_GRASS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("amethyst_spirit_grass"));
    public static final Block AMETHYST_SPIRIT_GRASS = registerBlock(
            AMETHYST_SPIRIT_GRASS_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)
                    .sound(SoundType.GRASS)
                    .lightLevel(state -> 6)
                    .noCollision()
                    .instabreak()
                    .setId(AMETHYST_SPIRIT_GRASS_KEY)));

    // 蓬莱竹海 (Penglai Bamboo Sea) - 青玉仙草、碧波灵石、仙灵兰草
    public static final ResourceKey<Block> EMERALD_MOSS_TURF_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("emerald_moss_turf"));
    public static final Block EMERALD_MOSS_TURF = registerBlock(
            EMERALD_MOSS_TURF_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)
                    .sound(SoundType.MOSS)
                    .strength(0.5F)
                    .setId(EMERALD_MOSS_TURF_KEY)));

    public static final ResourceKey<Block> EMERALD_JADE_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("emerald_jade_stone"));
    public static final Block EMERALD_JADE_STONE = registerBlock(
            EMERALD_JADE_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .sound(SoundType.STONE)
                    .strength(1.5F, 6.0F)
                    .setId(EMERALD_JADE_STONE_KEY)));

    public static final ResourceKey<Block> CELESTIAL_ORCHID_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_orchid"));
    public static final Block CELESTIAL_ORCHID = registerBlock(
            CELESTIAL_ORCHID_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID)
                    .sound(SoundType.GRASS)
                    .noCollision()
                    .instabreak()
                    .setId(CELESTIAL_ORCHID_KEY)));

    // 蟠桃宝苑 (Peach Orchard) - 九天金壤、琉璃神石、金莲仙华
    public static final ResourceKey<Block> GOLDEN_CELESTIAL_LOAM_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("golden_celestial_loam"));
    public static final Block GOLDEN_CELESTIAL_LOAM = registerBlock(
            GOLDEN_CELESTIAL_LOAM_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                    .sound(SoundType.GRASS)
                    .strength(0.6F)
                    .setId(GOLDEN_CELESTIAL_LOAM_KEY)));

    public static final ResourceKey<Block> GLAZED_SUNSTONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("glazed_sunstone"));
    public static final Block GLAZED_SUNSTONE = registerBlock(
            GLAZED_SUNSTONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OCHRE_FROGLIGHT)
                    .sound(SoundType.GLASS)
                    .lightLevel(state -> 12)
                    .strength(1.0F)
                    .setId(GLAZED_SUNSTONE_KEY)));

    public static final ResourceKey<Block> GOLDEN_LOTUS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("golden_lotus"));
    public static final Block GOLDEN_LOTUS = registerBlock(
            GOLDEN_LOTUS_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
                    .sound(SoundType.GRASS)
                    .lightLevel(state -> 9)
                    .noCollision()
                    .instabreak()
                    .setId(GOLDEN_LOTUS_KEY)));

    private static Block registerBlock(ResourceKey<Block> key, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM, key.identifier());
        Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(
                block, new Item.Properties().setId(itemKey)));
        return block;
    }

    public static void initialize() {
    }
}
