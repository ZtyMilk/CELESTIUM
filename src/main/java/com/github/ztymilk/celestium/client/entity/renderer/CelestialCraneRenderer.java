package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.CelestialCraneModel;
import com.github.ztymilk.celestium.entity.custom.CelestialCraneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class CelestialCraneRenderer extends MobRenderer<CelestialCraneEntity, LivingEntityRenderState, CelestialCraneModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/crane.png");
    public CelestialCraneRenderer(EntityRendererProvider.Context context) {
        super(context, new CelestialCraneModel(context.bakeLayer(CelestialCraneModel.LAYER_LOCATION)), 0.5F);
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
