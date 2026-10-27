package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CandleDragonModel;
import com.github.ztymilk.celestium.entity.custom.CandleDragonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

/**
 * 衔烛照天·上古烛龙渲染器 - 缩放至 6.5x 宏伟赤金蛇神龙躯
 */
public class CandleDragonRenderer extends MobRenderer<CandleDragonEntity, LivingEntityRenderState, CandleDragonModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/candle_dragon.png");

    public CandleDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new CandleDragonModel(context.bakeLayer(CandleDragonModel.LAYER_LOCATION)), 3.8F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        // 衔烛之神·上古烛龙：6.5倍辉煌尺度，赤金日轮照耀万壑
        poseStack.scale(6.5F, 6.5F, 6.5F);
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
