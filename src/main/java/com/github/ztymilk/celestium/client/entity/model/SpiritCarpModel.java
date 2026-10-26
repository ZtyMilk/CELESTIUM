package com.github.ztymilk.celestium.client.entity.model;

import com.github.ztymilk.celestium.Celestium;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

/**
 * 瑶池灵鲤 (Spirit Carp) - 游弋于仙池中的通灵锦鲤
 */
public class SpiritCarpModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("spirit_carp"), "main");

    private final ModelPart body;
    private final ModelPart leftFin;
    private final ModelPart rightFin;

    public SpiritCarpModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.leftFin = root.getChild("left_fin");
        this.rightFin = root.getChild("right_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-1.5F, -2.5F, -5.0F, 3.0F, 5.0F, 10.0F)
                .texOffs(0, 15).addBox(-1.0F, -2.0F, -8.0F, 2.0F, 4.0F, 3.0F)
                .texOffs(16, 0).addBox(0.0F, -5.5F, -2.0F, 0.0F, 3.0F, 6.0F)
                .texOffs(16, 9).addBox(0.0F, -4.0F, 5.0F, 0.0F, 8.0F, 8.0F), PartPose.offset(0.0F, 20.0F, 0.0F));
        root.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 4.0F), PartPose.offset(1.5F, 21.0F, -2.0F));
        root.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 4.0F), PartPose.offset(-1.5F, 21.0F, -2.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float swim = Mth.sin(state.ageInTicks * 0.25F);
        this.body.yRot = swim * 0.2F;
        this.leftFin.zRot = 0.2F + swim * 0.25F;
        this.rightFin.zRot = -0.2F - swim * 0.25F;
    }
}
