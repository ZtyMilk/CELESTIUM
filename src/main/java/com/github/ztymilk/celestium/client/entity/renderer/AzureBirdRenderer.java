package com.github.ztymilk.celestium.client.entity.renderer;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.client.entity.model.AzureBirdModel;
import com.github.ztymilk.celestium.entity.custom.AzureBirdEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class AzureBirdRenderer extends MobRenderer<AzureBirdEntity, LivingEntityRenderState, AzureBirdModel> {
    private static final Identifier TEXTURE = Celestium.id("textures/entity/azure_bird.png");
    public AzureBirdRenderer(EntityRendererProvider.Context context) {
        super(context, new AzureBirdModel(context.bakeLayer(AzureBirdModel.LAYER_LOCATION)), 0.5F);
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
