package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.HeavenlyGeneralModel;
import com.github.ztymilk.celestium.entity.custom.HeavenlyGeneralEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class HeavenlyGeneralRenderer extends MobRenderer<HeavenlyGeneralEntity, LivingEntityRenderState, HeavenlyGeneralModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/heavenly_general.png");
    public HeavenlyGeneralRenderer(EntityRendererProvider.Context context) {
        super(context, new HeavenlyGeneralModel(context.bakeLayer(HeavenlyGeneralModel.LAYER_LOCATION)), 0.7F);
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
