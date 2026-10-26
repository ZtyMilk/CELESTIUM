package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.NineTailedFoxModel;
import com.github.ztymilk.celestium.entity.custom.NineTailedFoxEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class NineTailedFoxRenderer extends MobRenderer<NineTailedFoxEntity, LivingEntityRenderState, NineTailedFoxModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/nine_tailed_fox.png");
    public NineTailedFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new NineTailedFoxModel(context.bakeLayer(NineTailedFoxModel.LAYER_LOCATION)), 0.4F);
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
