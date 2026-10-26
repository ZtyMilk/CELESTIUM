package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.MoonRabbitModel;
import com.github.ztymilk.celestium.entity.custom.MoonRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class MoonRabbitRenderer extends MobRenderer<MoonRabbitEntity, LivingEntityRenderState, MoonRabbitModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/moon_rabbit.png");

    public MoonRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new MoonRabbitModel(context.bakeLayer(MoonRabbitModel.LAYER_LOCATION)), 0.35F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        // 软萌体态
        poseStack.scale(0.8F, 0.8F, 0.8F);
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
