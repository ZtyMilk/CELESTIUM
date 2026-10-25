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

public class CelestiumXimuBlocks {
    // ximu_log
    public static final ResourceKey<Block> XIMU_LOG_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ximu_log"));
    public static final Block XIMU_LOG = registerBlock(
            XIMU_LOG_KEY,
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .sound(SoundType.WOOD)
                    .strength(4.0F)
                    .setId(XIMU_LOG_KEY)));
    // ximu_leaves
    public static final ResourceKey<Block> XIMU_LEAVES_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ximu_leaves"));
    public static final Block XIMU_LEAVES = registerBlock(
            XIMU_LEAVES_KEY,
            new UntintedParticleLeavesBlock(
                    0.1F,
                    CelestiumParticles.XIMU_LEAVES,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                            .sound(SoundType.GRASS)
                            .strength(0.2F)
                            .noOcclusion()
                            .setId(XIMU_LEAVES_KEY)));
    // ximu_tree_grower
    public static final TreeGrower XIMU_TREE_GROWER = new TreeGrower(
            "ximu",
            Optional.empty(),
            Optional.of(CelestiumTreeFeatures.XIMU_TREE),
            Optional.empty());
    // ximu_sapling
    public static final ResourceKey<Block> XIMU_SAPLING_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ximu_sapling"));
    public static final Block XIMU_SAPLING = registerBlock(
            XIMU_SAPLING_KEY,
            new SaplingBlock(XIMU_TREE_GROWER,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
                            .sound(SoundType.GRASS)
                            .noCollision()
                            .instabreak()
                            .setId(XIMU_SAPLING_KEY)));
    // ximu_planks
    public static final ResourceKey<Block> XIMU_PLANKS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ximu_planks"));
    public static final Block XIMU_PLANKS = registerBlock(
            XIMU_PLANKS_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .sound(SoundType.WOOD)
                    .strength(2.0F, 3.0F)
                    .setId(XIMU_PLANKS_KEY)));
    // ximu_slab
    public static final ResourceKey<Block> XIMU_SLAB_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ximu_slab"));
    public static final Block XIMU_SLAB = registerBlock(
            XIMU_SLAB_KEY,
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(XIMU_PLANKS)
                    .setId(XIMU_SLAB_KEY)));
    // ximu_stairs
    public static final ResourceKey<Block> XIMU_STAIRS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("ximu_stairs"));
    public static final Block XIMU_STAIRS = registerBlock(
            XIMU_STAIRS_KEY,
            new StairBlock(
                    XIMU_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(XIMU_PLANKS).setId(XIMU_STAIRS_KEY)));

    private static Block registerBlock(ResourceKey<Block> key, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM, key.identifier());
        Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(
                block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()));
        return block;
    }

    public static void initialize() {
    }
}

