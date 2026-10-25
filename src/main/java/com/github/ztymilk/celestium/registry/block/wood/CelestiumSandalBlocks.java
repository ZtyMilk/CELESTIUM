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

public class CelestiumSandalBlocks {
    // sandal_log
    public static final ResourceKey<Block> SANDAL_LOG_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("sandal_log"));
    public static final Block SANDAL_LOG = registerBlock(
            SANDAL_LOG_KEY,
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .sound(SoundType.WOOD)
                    .strength(4.0F)
                    .setId(SANDAL_LOG_KEY)));
    // sandal_leaves
    public static final ResourceKey<Block> SANDAL_LEAVES_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("sandal_leaves"));
    public static final Block SANDAL_LEAVES = registerBlock(
            SANDAL_LEAVES_KEY,
            new UntintedParticleLeavesBlock(
                    0.1F,
                    CelestiumParticles.SANDAL_LEAVES,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                            .sound(SoundType.GRASS)
                            .strength(0.2F)
                            .noOcclusion()
                            .setId(SANDAL_LEAVES_KEY)));
    // sandal_tree_grower
    public static final TreeGrower SANDAL_TREE_GROWER = new TreeGrower(
            "sandal",
            Optional.empty(),
            Optional.of(CelestiumTreeFeatures.SANDAL_TREE),
            Optional.empty());
    // sandal_sapling
    public static final ResourceKey<Block> SANDAL_SAPLING_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("sandal_sapling"));
    public static final Block SANDAL_SAPLING = registerBlock(
            SANDAL_SAPLING_KEY,
            new SaplingBlock(SANDAL_TREE_GROWER,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
                            .sound(SoundType.GRASS)
                            .noCollision()
                            .instabreak()
                            .setId(SANDAL_SAPLING_KEY)));
    // sandal_planks
    public static final ResourceKey<Block> SANDAL_PLANKS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("sandal_planks"));
    public static final Block SANDAL_PLANKS = registerBlock(
            SANDAL_PLANKS_KEY,
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .sound(SoundType.WOOD)
                    .strength(2.0F, 3.0F)
                    .setId(SANDAL_PLANKS_KEY)));
    // sandal_slab
    public static final ResourceKey<Block> SANDAL_SLAB_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("sandal_slab"));
    public static final Block SANDAL_SLAB = registerBlock(
            SANDAL_SLAB_KEY,
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SANDAL_PLANKS)
                    .setId(SANDAL_SLAB_KEY)));
    // sandal_stairs
    public static final ResourceKey<Block> SANDAL_STAIRS_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("sandal_stairs"));
    public static final Block SANDAL_STAIRS = registerBlock(
            SANDAL_STAIRS_KEY,
            new StairBlock(
                    SANDAL_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(SANDAL_PLANKS).setId(SANDAL_STAIRS_KEY)));

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
