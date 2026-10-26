package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.BaiZeModel;
import com.github.ztymilk.celestium.entity.custom.BaiZeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class BaiZeRenderer extends MobRenderer<BaiZeEntity, LivingEntityRenderState, BaiZeModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/bai_ze.png");

    public BaiZeRenderer(EntityRendererProvider.Context context) {
        super(context, new BaiZeModel(context.bakeLayer(BaiZeModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        poseStack.scale(1.2F, 1.2F, 1.2F);
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
