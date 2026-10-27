package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.QilinModel;
import com.github.ztymilk.celestium.entity.custom.QilinEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

/**
 * 玄玉麒麟渲染器 - 缩放至 1.85x 威严神骏体型
 */
public class QilinRenderer extends MobRenderer<QilinEntity, LivingEntityRenderState, QilinModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/qilin.png");

    public QilinRenderer(EntityRendererProvider.Context context) {
        super(context, new QilinModel(context.bakeLayer(QilinModel.LAYER_LOCATION)), 1.6F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        poseStack.scale(1.85F, 1.85F, 1.85F);
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
