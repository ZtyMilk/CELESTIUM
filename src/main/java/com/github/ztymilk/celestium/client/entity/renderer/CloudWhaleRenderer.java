package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CloudWhaleModel;
import com.github.ztymilk.celestium.entity.custom.CloudWhaleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class CloudWhaleRenderer extends MobRenderer<CloudWhaleEntity, LivingEntityRenderState, CloudWhaleModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/cloud_whale.png");

    public CloudWhaleRenderer(EntityRendererProvider.Context context) {
        super(context, new CloudWhaleModel(context.bakeLayer(CloudWhaleModel.LAYER_LOCATION)), 6.0F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        // 放大为巡天云海巨兽，并对齐重力与骑乘中心
        poseStack.scale(8.0F, 8.0F, 8.0F);
        poseStack.translate(0.0D, -0.45D, 0.0D);
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
