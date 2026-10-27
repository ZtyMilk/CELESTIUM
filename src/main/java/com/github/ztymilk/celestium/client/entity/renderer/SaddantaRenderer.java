package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.SaddantaModel;
import com.github.ztymilk.celestium.entity.custom.SaddantaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

/**
 * 六牙白象渲染器 - 缩放至 2.65x 巍峨须弥神象体魄
 */
public class SaddantaRenderer extends MobRenderer<SaddantaEntity, LivingEntityRenderState, SaddantaModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/saddanta.png");

    public SaddantaRenderer(EntityRendererProvider.Context context) {
        super(context, new SaddantaModel(context.bakeLayer(SaddantaModel.LAYER_LOCATION)), 3.2F);
    }

    @Override
    protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        poseStack.scale(2.65F, 2.65F, 2.65F);
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
