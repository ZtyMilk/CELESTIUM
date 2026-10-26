package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.DanFireSpriteModel;
import com.github.ztymilk.celestium.entity.custom.DanFireSpriteEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class DanFireSpriteRenderer extends MobRenderer<DanFireSpriteEntity, LivingEntityRenderState, DanFireSpriteModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/dan_fire_sprite.png");
    public DanFireSpriteRenderer(EntityRendererProvider.Context context) {
        super(context, new DanFireSpriteModel(context.bakeLayer(DanFireSpriteModel.LAYER_LOCATION)), 0.4F);
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
