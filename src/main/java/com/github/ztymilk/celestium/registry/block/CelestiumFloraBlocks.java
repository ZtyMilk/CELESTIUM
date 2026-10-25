package com.github.ztymilk.celestium.registry.block;

import com.github.ztymilk.celestium.Celestium;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

public class CelestiumFloraBlocks {
    // celestial_grass_block
    public static final ResourceKey<Block> CELESTIAL_GRASS_BLOCK_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_grass_block"));
    public static final Block CELESTIAL_GRASS_BLOCK = registerBlock(
            CELESTIAL_GRASS_BLOCK_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                    .sound(SoundType.GRASS)
                    .strength(0.6F)
                    .setId(CELESTIAL_GRASS_BLOCK_KEY)));
    // celestial_dirt
    public static final ResourceKey<Block> CELESTIAL_DIRT_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_dirt"));
    public static final Block CELESTIAL_DIRT = registerBlock(
            CELESTIAL_DIRT_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                    .setId(CELESTIAL_DIRT_KEY)));
    // celestial_grass
    public static final ResourceKey<Block> CELESTIAL_GRASS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_grass"));
    public static final Block CELESTIAL_GRASS = registerBlock(
            CELESTIAL_GRASS_KEY,
            new BushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .sound(SoundType.GRASS)
                    .noCollision()
                    .instabreak()
                    .setId(CELESTIAL_GRASS_KEY)));
    // celestial_stone
    public static final ResourceKey<Block> CELESTIAL_STONE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("celestial_stone"));
    public static final Block CELESTIAL_STONE = registerBlock(
            CELESTIAL_STONE_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .setId(CELESTIAL_STONE_KEY)));

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
