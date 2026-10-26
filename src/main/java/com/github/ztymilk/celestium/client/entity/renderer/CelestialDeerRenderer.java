package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CelestialDeerModel;
import com.github.ztymilk.celestium.entity.custom.CelestialDeerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class CelestialDeerRenderer extends MobRenderer<CelestialDeerEntity, LivingEntityRenderState, CelestialDeerModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/celestial_deer.png");
    public CelestialDeerRenderer(EntityRendererProvider.Context context) {
        super(context, new CelestialDeerModel(context.bakeLayer(CelestialDeerModel.LAYER_LOCATION)), 0.6F);
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
