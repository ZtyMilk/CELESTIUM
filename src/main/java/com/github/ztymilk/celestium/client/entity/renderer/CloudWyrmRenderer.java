package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CloudWyrmModel;
import com.github.ztymilk.celestium.entity.custom.CloudWyrmEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

/**
 * 腾云苍龙·巡天灵蛟渲染器 - 缩放至 6.2x 盘旋九天的巍峨神龙
 */
public class CloudWyrmRenderer extends MobRenderer<CloudWyrmEntity, LivingEntityRenderState, CloudWyrmModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/cloud_wyrm.png");

    public CloudWyrmRenderer(EntityRendererProvider.Context context) {
        super(context, new CloudWyrmModel(context.bakeLayer(CloudWyrmModel.LAYER_LOCATION)), 3.6F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        // 宏伟东方苍龙：6.2倍辉煌霸气尺度，盘旋九天游云掠雾
        poseStack.scale(6.2F, 6.2F, 6.2F);
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
