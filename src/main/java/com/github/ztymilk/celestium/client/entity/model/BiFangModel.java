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
 * 毕方神鸟 (Bi Fang) - 上古火精神鸟，青羽赤文，白喙独足
 */
public class BiFangModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("bifang"), "main");

    private final ModelPart head;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart singleLeg;
    private final ModelPart tail;

    public BiFangModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
        this.singleLeg = root.getChild("single_leg");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 8.0F, 8.0F), PartPose.offset(0.0F, 12.0F, 0.0F));
        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 16).addBox(-1.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F)
                .texOffs(12, 16).addBox(-2.0F, -11.0F, -3.5F, 4.0F, 4.0F, 5.0F)
                .texOffs(30, 16).addBox(-0.5F, -9.0F, -7.0F, 1.0F, 2.0F, 4.0F)
                .texOffs(40, 16).addBox(-0.5F, -14.0F, -1.5F, 1.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 10.0F, -3.0F));
        root.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(28, 0).addBox(0.0F, -1.0F, -3.0F, 1.0F, 6.0F, 9.0F), PartPose.offset(3.0F, 10.0F, 0.0F));
        root.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 6.0F, 9.0F, true), PartPose.offset(-3.0F, 10.0F, 0.0F));
        // 毕方独足 (Single Leg)
        root.addOrReplaceChild("single_leg", CubeListBuilder.create()
                .texOffs(0, 27).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F)
                .texOffs(8, 27).addBox(-2.0F, 8.0F, -3.0F, 4.0F, 1.0F, 4.0F), PartPose.offset(0.0F, 15.0F, 0.0F));
        root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 8.0F), PartPose.offset(0.0F, 12.0F, 4.0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);

        float wingFlap = Mth.cos(state.ageInTicks * 0.25F) * 0.3F;
        this.leftWing.zRot = wingFlap;
        this.rightWing.zRot = -wingFlap;

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        this.singleLeg.xRot = Mth.sin(walkPos * 0.8F) * 0.8F * walkSpeed;

        this.tail.xRot = Mth.sin(state.ageInTicks * 0.08F) * 0.15F;
    }
}
