package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.SpiritCarpModel;
import com.github.ztymilk.celestium.entity.custom.SpiritCarpEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class SpiritCarpRenderer extends MobRenderer<SpiritCarpEntity, LivingEntityRenderState, SpiritCarpModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/spirit_carp.png");
    public SpiritCarpRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiritCarpModel(context.bakeLayer(SpiritCarpModel.LAYER_LOCATION)), 0.3F);
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
