package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.GoldenCrowModel;
import com.github.ztymilk.celestium.entity.custom.GoldenCrowEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class GoldenCrowRenderer extends MobRenderer<GoldenCrowEntity, LivingEntityRenderState, GoldenCrowModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/golden_crow.png");

    public GoldenCrowRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldenCrowModel(context.bakeLayer(GoldenCrowModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        poseStack.scale(1.35F, 1.35F, 1.35F);
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
