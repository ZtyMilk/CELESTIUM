package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.BiFangModel;
import com.github.ztymilk.celestium.entity.custom.BiFangEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class BiFangRenderer extends MobRenderer<BiFangEntity, LivingEntityRenderState, BiFangModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/bifang.png");
    public BiFangRenderer(EntityRendererProvider.Context context) {
        super(context, new BiFangModel(context.bakeLayer(BiFangModel.LAYER_LOCATION)), 0.6F);
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
