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
 * 镇殿神将 (Heavenly General)
 */
public class HeavenlyGeneralModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("heavenly_general"), "main");

    private final ModelPart head;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public HeavenlyGeneralModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                .texOffs(32, 0).addBox(-6.0F, -11.0F, -1.0F, 12.0F, 4.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(16, 16).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 12.0F, 6.0F)
                .texOffs(0, 48).addBox(-8.0F, -4.0F, 3.0F, 16.0F, 14.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 16).addBox(-1.0F, -2.0F, -2.5F, 4.0F, 12.0F, 5.0F), PartPose.offset(6.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 16).addBox(-3.0F, -2.0F, -2.5F, 4.0F, 12.0F, 5.0F), PartPose.offset(-6.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F), PartPose.offset(2.5F, 12.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F), PartPose.offset(-2.5F, 12.0F, 0.0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        this.leftArm.xRot = Mth.cos(walkPos * 0.6662F) * 0.8F * walkSpeed;
        this.rightArm.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.8F * walkSpeed;
        this.leftLeg.xRot = this.rightArm.xRot;
        this.rightLeg.xRot = this.leftArm.xRot;
    }
}
