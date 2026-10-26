package com.github.ztymilk.celestium.registry.block;

import com.github.ztymilk.celestium.Celestium;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * 天庭中式装饰方块与仙境特异方块
 */
public class CelestiumDecorativeBlocks {
    public static final List<Block> ALL_DECORATIONS = new ArrayList<>();

    // 1. 天庭琉璃宫灯
    public static final ResourceKey<Block> CELESTIAL_PALACE_LANTERN_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_palace_lantern"));
    public static final Block CELESTIAL_PALACE_LANTERN = registerBlock(
            CELESTIAL_PALACE_LANTERN_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .sound(SoundType.LANTERN)
                    .strength(0.8F)
                    .lightLevel(state -> 15)
                    .noOcclusion()
                    .setId(CELESTIAL_PALACE_LANTERN_KEY)));

    // 2. 青铜云纹香炉
    public static final ResourceKey<Block> BRONZE_INCENSE_BURNER_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("bronze_incense_burner"));
    public static final Block BRONZE_INCENSE_BURNER = registerBlock(
            BRONZE_INCENSE_BURNER_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)
                    .sound(SoundType.COPPER)
                    .strength(2.0F)
                    .lightLevel(state -> 8)
                    .noOcclusion()
                    .setId(BRONZE_INCENSE_BURNER_KEY)));

    // 3. 白玉雕花栏杆
    public static final ResourceKey<Block> WHITE_JADE_FENCE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("white_jade_fence"));
    public static final Block WHITE_JADE_FENCE = registerBlock(
            WHITE_JADE_FENCE_KEY,
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE)
                    .sound(SoundType.STONE)
                    .strength(1.5F, 6.0F)
                    .setId(WHITE_JADE_FENCE_KEY)));

    // 4. 蟠龙祥云华表
    public static final ResourceKey<Block> DRAGON_TOTEM_PILLAR_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("dragon_totem_pillar"));
    public static final Block DRAGON_TOTEM_PILLAR = registerBlock(
            DRAGON_TOTEM_PILLAR_KEY,
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)
                    .sound(SoundType.STONE)
                    .strength(2.5F, 8.0F)
                    .setId(DRAGON_TOTEM_PILLAR_KEY)));

    // 5. 紫檀仙案供桌
    public static final ResourceKey<Block> CELESTIAL_ALTAR_TABLE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_altar_table"));
    public static final Block CELESTIAL_ALTAR_TABLE = registerBlock(
            CELESTIAL_ALTAR_TABLE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .sound(SoundType.WOOD)
                    .strength(1.2F)
                    .noOcclusion()
                    .setId(CELESTIAL_ALTAR_TABLE_KEY)));

    // 6. 碧玉风铎编钟
    public static final ResourceKey<Block> SPIRIT_CHIME_BELL_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("spirit_chime_bell"));
    public static final Block SPIRIT_CHIME_BELL = registerBlock(
            SPIRIT_CHIME_BELL_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BELL)
                    .sound(SoundType.AMETHYST)
                    .strength(1.5F)
                    .lightLevel(state -> 9)
                    .noOcclusion()
                    .setId(SPIRIT_CHIME_BELL_KEY)));

    // 7. 北斗星芝草皮 (Beidou Star Turf)
    public static final ResourceKey<Block> BEIDOU_STAR_TURF_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("beidou_star_turf"));
    public static final Block BEIDOU_STAR_TURF = registerBlock(
            BEIDOU_STAR_TURF_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                    .sound(SoundType.GRASS)
                    .strength(0.6F)
                    .lightLevel(state -> 4)
                    .setId(BEIDOU_STAR_TURF_KEY)));

    // 8. 北斗星石 (Celestial Star Stone)
    public static final ResourceKey<Block> CELESTIAL_STAR_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_star_stone"));
    public static final Block CELESTIAL_STAR_STONE = registerBlock(
            CELESTIAL_STAR_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .sound(SoundType.STONE)
                    .strength(2.0F, 8.0F)
                    .setId(CELESTIAL_STAR_STONE_KEY)));

    // 9. 刻纹星石 (Carved Star Stone)
    public static final ResourceKey<Block> CARVED_STAR_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("carved_star_stone"));
    public static final Block CARVED_STAR_STONE = registerBlock(
            CARVED_STAR_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_DEEPSLATE)
                    .sound(SoundType.STONE)
                    .strength(2.0F, 8.0F)
                    .lightLevel(state -> 6)
                    .setId(CARVED_STAR_STONE_KEY)));

    // 10. 九天雷玉 (Thunder Jade Stone)
    public static final ResourceKey<Block> THUNDER_JADE_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("thunder_jade_stone"));
    public static final Block THUNDER_JADE_STONE = registerBlock(
            THUNDER_JADE_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
                    .sound(SoundType.AMETHYST)
                    .strength(2.5F, 9.0F)
                    .lightLevel(state -> 10)
                    .setId(THUNDER_JADE_STONE_KEY)));

    // 11. 磨制雷玉 (Polished Thunder Jade)
    public static final ResourceKey<Block> POLISHED_THUNDER_JADE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("polished_thunder_jade"));
    public static final Block POLISHED_THUNDER_JADE = registerBlock(
            POLISHED_THUNDER_JADE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)
                    .sound(SoundType.AMETHYST)
                    .strength(2.5F, 9.0F)
                    .setId(POLISHED_THUNDER_JADE_KEY)));

    // 12. 琉璃仙瓦 (Celestial Glazed Tile)
    public static final ResourceKey<Block> CELESTIAL_GLAZED_TILE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_glazed_tile"));
    public static final Block CELESTIAL_GLAZED_TILE = registerBlock(
            CELESTIAL_GLAZED_TILE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)
                    .sound(SoundType.STONE)
                    .strength(1.8F, 7.0F)
                    .setId(CELESTIAL_GLAZED_TILE_KEY)));

    // 13. 琉璃仙瓦阶梯 (Celestial Glazed Stairs)
    public static final ResourceKey<Block> CELESTIAL_GLAZED_STAIRS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_glazed_stairs"));
    public static final Block CELESTIAL_GLAZED_STAIRS = registerBlock(
            CELESTIAL_GLAZED_STAIRS_KEY,
            new StairBlock(CELESTIAL_GLAZED_TILE.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(CELESTIAL_GLAZED_TILE).setId(CELESTIAL_GLAZED_STAIRS_KEY)));

    // 14. 琉璃仙瓦台阶 (Celestial Glazed Slab)
    public static final ResourceKey<Block> CELESTIAL_GLAZED_SLAB_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_glazed_slab"));
    public static final Block CELESTIAL_GLAZED_SLAB = registerBlock(
            CELESTIAL_GLAZED_SLAB_KEY,
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CELESTIAL_GLAZED_TILE).setId(CELESTIAL_GLAZED_SLAB_KEY)));

    // 15. 浮雕白玉砖 (Carved White Jade Brick)
    public static final ResourceKey<Block> CARVED_WHITE_JADE_BRICK_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("carved_white_jade_brick"));
    public static final Block CARVED_WHITE_JADE_BRICK = registerBlock(
            CARVED_WHITE_JADE_BRICK_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS)
                    .sound(SoundType.STONE)
                    .strength(2.0F, 8.0F)
                    .setId(CARVED_WHITE_JADE_BRICK_KEY)));

    // 16. 祥云仙台 (Celestial Cloud Altar)
    public static final ResourceKey<Block> CELESTIAL_CLOUD_ALTAR_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_cloud_altar"));
    public static final Block CELESTIAL_CLOUD_ALTAR = registerBlock(
            CELESTIAL_CLOUD_ALTAR_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE)
                    .sound(SoundType.STONE)
                    .strength(3.0F, 10.0F)
                    .lightLevel(state -> 11)
                    .noOcclusion()
                    .setId(CELESTIAL_CLOUD_ALTAR_KEY)));

    // 17. 摘星仙花 (Star Flower)
    public static final ResourceKey<Block> STAR_FLOWER_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("star_flower"));
    public static final Block STAR_FLOWER = registerBlock(
            STAR_FLOWER_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)
                    .sound(SoundType.GRASS)
                    .noCollision()
                    .instabreak()
                    .lightLevel(state -> 8)
                    .setId(STAR_FLOWER_KEY)));

    // 18. 雷精仙兰 (Thunder Spirit Orchid)
    public static final ResourceKey<Block> THUNDER_SPIRIT_ORCHID_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("thunder_spirit_orchid"));
    public static final Block THUNDER_SPIRIT_ORCHID = registerBlock(
            THUNDER_SPIRIT_ORCHID_KEY,
            new BlockSubclasses.ModBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID)
                    .sound(SoundType.GRASS)
                    .noCollision()
                    .instabreak()
                    .lightLevel(state -> 9)
                    .setId(THUNDER_SPIRIT_ORCHID_KEY)));

    private static Block registerBlock(ResourceKey<Block> key, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, key.identifier());
        Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, new Item.Properties().setId(itemKey)));
        ALL_DECORATIONS.add(block);
        return block;
    }

    public static void initialize() {
    }
}
