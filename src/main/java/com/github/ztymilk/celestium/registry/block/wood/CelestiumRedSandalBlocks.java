package com.github.ztymilk.celestium.registry.block.wood;

import java.util.Optional;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.registry.particle.CelestiumParticles;
import com.github.ztymilk.celestium.registry.worldgenerator.CelestiumTreeFeatures;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CelestiumRedSandalBlocks {
    // red_sandal_log
    public static final ResourceKey<Block> RED_SANDAL_LOG_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("red_sandal_log"));
    public static final Block RED_SANDAL_LOG = registerBlock(
            RED_SANDAL_LOG_KEY,
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .sound(SoundType.WOOD)
                    .strength(8.0F)
                    .setId(RED_SANDAL_LOG_KEY)));
    // red_sandal_leaves
    public static final ResourceKey<Block> RED_SANDAL_LEAVES_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("red_sandal_leaves"));
    public static final Block RED_SANDAL_LEAVES = registerBlock(
            RED_SANDAL_LEAVES_KEY,
            new UntintedParticleLeavesBlock(
                    0.1F,
                    CelestiumParticles.RED_SANDAL_LEAVES,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                            .sound(SoundType.GRASS)
                            .strength(0.2F)
                            .noOcclusion()
                            .setId(RED_SANDAL_LEAVES_KEY)));
    // red_sandal_tree_grower
    public static final TreeGrower RED_SANDAL_TREE_GROWER = new TreeGrower(
            "red_sandal",
            Optional.empty(),
            Optional.of(CelestiumTreeFeatures.RED_SANDAL_TREE),
            Optional.empty());
    // red_sandal_sapling
    public static final ResourceKey<Block> RED_SANDAL_SAPLING_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("red_sandal_sapling"));
    public static final Block RED_SANDAL_SAPLING = registerBlock(
            RED_SANDAL_SAPLING_KEY,
            new SaplingBlock(RED_SANDAL_TREE_GROWER,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
                            .sound(SoundType.GRASS)
                            .noCollision()
                            .instabreak()
                            .setId(RED_SANDAL_SAPLING_KEY)));
    // red_sandal_planks
    public static final ResourceKey<Block> RED_SANDAL_PLANKS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("red_sandal_planks"));
    public static final Block RED_SANDAL_PLANKS = registerBlock(
            RED_SANDAL_PLANKS_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .sound(SoundType.WOOD)
                    .strength(2.0F, 3.0F)
                    .setId(RED_SANDAL_PLANKS_KEY)));
    // red_sandal_slab
    public static final ResourceKey<Block> RED_SANDAL_SLAB_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("red_sandal_slab"));
    public static final Block RED_SANDAL_SLAB = registerBlock(
            RED_SANDAL_SLAB_KEY,
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(RED_SANDAL_PLANKS)
                    .setId(RED_SANDAL_SLAB_KEY)));
    // red_sandal_stairs
    public static final ResourceKey<Block> RED_SANDAL_STAIRS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("red_sandal_stairs"));
    public static final Block RED_SANDAL_STAIRS = registerBlock(
            RED_SANDAL_STAIRS_KEY,
            new StairBlock(
                    RED_SANDAL_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(RED_SANDAL_PLANKS).setId(RED_SANDAL_STAIRS_KEY)));

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
