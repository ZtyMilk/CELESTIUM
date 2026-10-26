package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.HeavenlySoldierModel;
import com.github.ztymilk.celestium.entity.custom.HeavenlySoldierEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class HeavenlySoldierRenderer extends MobRenderer<HeavenlySoldierEntity, LivingEntityRenderState, HeavenlySoldierModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/heavenly_soldier.png");
    public HeavenlySoldierRenderer(EntityRendererProvider.Context context) {
        super(context, new HeavenlySoldierModel(context.bakeLayer(HeavenlySoldierModel.LAYER_LOCATION)), 0.5F);
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
