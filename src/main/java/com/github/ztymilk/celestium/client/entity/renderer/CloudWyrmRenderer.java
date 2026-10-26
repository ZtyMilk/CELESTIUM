package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CloudWyrmModel;
import com.github.ztymilk.celestium.entity.custom.CloudWyrmEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class CloudWyrmRenderer extends MobRenderer<CloudWyrmEntity, LivingEntityRenderState, CloudWyrmModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/cloud_wyrm.png");

    public CloudWyrmRenderer(EntityRendererProvider.Context context) {
        super(context, new CloudWyrmModel(context.bakeLayer(CloudWyrmModel.LAYER_LOCATION)), 2.6F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        // 宏伟东方苍龙：4.8倍辉煌霸气尺度，盘旋九天游云掠雾
        poseStack.scale(4.8F, 4.8F, 4.8F);
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