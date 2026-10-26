package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.PiXiuModel;
import com.github.ztymilk.celestium.entity.custom.PiXiuEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class PiXiuRenderer extends MobRenderer<PiXiuEntity, LivingEntityRenderState, PiXiuModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/pi_xiu.png");

    public PiXiuRenderer(EntityRendererProvider.Context context) {
        super(context, new PiXiuModel(context.bakeLayer(PiXiuModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        poseStack.scale(1.3F, 1.3F, 1.3F);
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
