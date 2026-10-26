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
 * 青鸾神鸟 (Azure Bird) - 西王母青鸟使者
 */
public class AzureBirdModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("azure_bird"), "main");

    private final ModelPart head;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart tailLeft;
    private final ModelPart tailRight;

    public AzureBirdModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.tailLeft = root.getChild("tail_left");
        this.tailRight = root.getChild("tail_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -3.0F, -3.0F, 5.0F, 6.0F, 7.0F), PartPose.offset(0.0F, 15.0F, 0.0F));
        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 13).addBox(-1.5F, -6.0F, -2.5F, 3.0F, 6.0F, 4.0F)
                .texOffs(14, 13).addBox(-0.5F, -4.0F, -5.5F, 1.0F, 1.0F, 3.0F)
                .texOffs(22, 13).addBox(-0.5F, -9.0F, -1.5F, 1.0F, 4.0F, 3.0F), PartPose.offset(0.0F, 14.0F, -3.0F));
        root.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -1.0F, -2.0F, 1.0F, 4.0F, 8.0F), PartPose.offset(2.5F, 14.0F, 0.0F));
        root.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 4.0F, 8.0F, true), PartPose.offset(-2.5F, 14.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F), PartPose.offset(1.5F, 18.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, true), PartPose.offset(-1.5F, 18.0F, 0.0F));
        root.addOrReplaceChild("tail_left", CubeListBuilder.create().texOffs(42, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 12.0F), PartPose.offsetAndRotation(1.0F, 16.0F, 4.0F, -0.2F, 0.1F, 0.0F));
        root.addOrReplaceChild("tail_right", CubeListBuilder.create().texOffs(42, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 12.0F, true), PartPose.offsetAndRotation(-1.0F, 16.0F, 4.0F, -0.2F, -0.1F, 0.0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);

        float wingFlap = Mth.cos(state.ageInTicks * 0.3F) * 0.35F;
        this.leftWing.zRot = wingFlap;
        this.rightWing.zRot = -wingFlap;

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        this.leftLeg.xRot = Mth.cos(walkPos * 0.6662F) * 0.8F * walkSpeed;
        this.rightLeg.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.8F * walkSpeed;

        this.tailLeft.yRot = 0.1F + Mth.sin(state.ageInTicks * 0.08F) * 0.1F;
        this.tailRight.yRot = -0.1F - Mth.sin(state.ageInTicks * 0.08F) * 0.1F;
    }
}
