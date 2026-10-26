package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CelestialHermitModel;
import com.github.ztymilk.celestium.entity.custom.CelestialHermitEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class CelestialHermitRenderer extends MobRenderer<CelestialHermitEntity, LivingEntityRenderState, CelestialHermitModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/celestial_hermit.png");
    public CelestialHermitRenderer(EntityRendererProvider.Context context) {
        super(context, new CelestialHermitModel(context.bakeLayer(CelestialHermitModel.LAYER_LOCATION)), 0.5F);
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
