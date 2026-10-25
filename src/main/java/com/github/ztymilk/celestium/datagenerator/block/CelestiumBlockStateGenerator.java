package com.github.ztymilk.celestium.datagenerator.block;

import com.github.ztymilk.celestium.datagenerator.block.state.CelestiumFloraBlockStateGenerator;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class CelestiumBlockStateGenerator extends FabricModelProvider{
    public CelestiumBlockStateGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        CelestiumFloraBlockStateGenerator.generate(generator);
    }
    @Override 
    public void generateItemModels(ItemModelGenerators itemModelGenerator){}
}
