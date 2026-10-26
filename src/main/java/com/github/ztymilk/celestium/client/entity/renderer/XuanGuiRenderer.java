package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.XuanGuiModel;
import com.github.ztymilk.celestium.entity.custom.XuanGuiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class XuanGuiRenderer extends MobRenderer<XuanGuiEntity, LivingEntityRenderState, XuanGuiModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/xuan_gui.png");

    public XuanGuiRenderer(EntityRendererProvider.Context context) {
        super(context, new XuanGuiModel(context.bakeLayer(XuanGuiModel.LAYER_LOCATION)), 1.2F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        poseStack.scale(1.25F, 1.25F, 1.25F);
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