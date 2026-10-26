package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.XieZhiModel;
import com.github.ztymilk.celestium.entity.custom.XieZhiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class XieZhiRenderer extends MobRenderer<XieZhiEntity, LivingEntityRenderState, XieZhiModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/xie_zhi.png");

    public XieZhiRenderer(EntityRendererProvider.Context context) {
        super(context, new XieZhiModel(context.bakeLayer(XieZhiModel.LAYER_LOCATION)), 1.4F);
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