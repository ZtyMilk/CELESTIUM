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
 * 九色神鹿 (Celestial Deer)
 * 敦煌瑞兽九色鹿：修长灵鹿身姿、分叉金角、灵动鹿耳与如意光晕
 */
public class CelestialDeerModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("celestial_deer"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;

    public CelestialDeerModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 躯体 (Slender graceful deer body)
        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.5F, -3.5F, -6.0F, 7.0F, 7.0F, 13.0F),
                PartPose.offset(0.0F, 11.5F, 0.0F));

        // 颈部与鹿首 (Neck & Head)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 20).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 8.0F, 5.0F)
                .texOffs(20, 20).addBox(-2.0F, -7.0F, -6.5F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(0.0F, 9.0F, -5.5F));

        // 分叉金角 (Branching Golden Antlers)
        head.addOrReplaceChild("left_antler", CubeListBuilder.create()
                .texOffs(36, 0).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 9.0F, 1.0F)
                .texOffs(36, 10).addBox(0.0F, -7.0F, 0.0F, 4.0F, 1.0F, 1.0F)
                .texOffs(36, 12).addBox(0.0F, -5.0F, -2.0F, 1.0F, 1.0F, 3.0F),
                PartPose.offsetAndRotation(1.5F, -7.0F, 0.0F, -0.15F, 0.0F, 0.2F));

        head.addOrReplaceChild("right_antler", CubeListBuilder.create()
                .texOffs(36, 0).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 9.0F, 1.0F, true)
                .texOffs(36, 10).addBox(-4.0F, -7.0F, 0.0F, 4.0F, 1.0F, 1.0F, true)
                .texOffs(36, 12).addBox(-1.0F, -5.0F, -2.0F, 1.0F, 1.0F, 3.0F, true),
                PartPose.offsetAndRotation(-1.5F, -7.0F, 0.0F, -0.15F, 0.0F, -0.2F));

        // 修长四肢 (Slender legs)
        root.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F), PartPose.offset(2.0F, 13.0F, -4.0F));
        root.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, true), PartPose.offset(-2.0F, 13.0F, -4.0F));
        root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(8, 33).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F), PartPose.offset(2.0F, 13.0F, 5.0F));
        root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(8, 33).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, true), PartPose.offset(-2.0F, 13.0F, 5.0F));

        return LayerDefinition.create(mesh, 64, 48);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.7F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.7F;

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        this.leftFrontLeg.xRot = Mth.cos(walkPos * 0.6662F) * 0.8F * walkSpeed;
        this.rightFrontLeg.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.8F * walkSpeed;
        this.leftHindLeg.xRot = this.rightFrontLeg.xRot;
        this.rightHindLeg.xRot = this.leftFrontLeg.xRot;
    }
}
