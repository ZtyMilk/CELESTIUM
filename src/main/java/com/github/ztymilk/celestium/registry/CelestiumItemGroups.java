package com.github.ztymilk.celestium.registry;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;
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
                        output.accept(CelestiumFloraBlocks.CELESTIAL_GRASS_BLOCK);
                        output.accept(CelestiumFloraBlocks.CELESTIAL_DIRT);
                        output.accept(CelestiumFloraBlocks.CELESTIAL_GRASS);
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
                    })
                    .build());

    public static void initialize() {
    }
}
