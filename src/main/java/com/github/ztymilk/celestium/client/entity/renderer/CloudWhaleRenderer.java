package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CloudWhaleModel;
import com.github.ztymilk.celestium.entity.custom.CloudWhaleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

/**
 * 流云巨鲲渲染器 - 缩放至 11.0x 巍峨苍穹遮天巨灵
 */
public class CloudWhaleRenderer extends MobRenderer<CloudWhaleEntity, LivingEntityRenderState, CloudWhaleModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/cloud_whale.png");

    public CloudWhaleRenderer(EntityRendererProvider.Context context) {
        super(context, new CloudWhaleModel(context.bakeLayer(CloudWhaleModel.LAYER_LOCATION)), 8.5F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        // 放大为巡天云海巨兽，背负青天朝沧溟
        poseStack.scale(11.0F, 11.0F, 11.0F);
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
