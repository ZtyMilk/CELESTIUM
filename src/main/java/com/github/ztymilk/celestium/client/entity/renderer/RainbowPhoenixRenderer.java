package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.RainbowPhoenixModel;
import com.github.ztymilk.celestium.entity.custom.RainbowPhoenixEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class RainbowPhoenixRenderer extends MobRenderer<RainbowPhoenixEntity, LivingEntityRenderState, RainbowPhoenixModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/rainbow_phoenix.png");

    public RainbowPhoenixRenderer(EntityRendererProvider.Context context) {
        super(context, new RainbowPhoenixModel(context.bakeLayer(RainbowPhoenixModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        poseStack.scale(1.15F, 1.15F, 1.15F);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
