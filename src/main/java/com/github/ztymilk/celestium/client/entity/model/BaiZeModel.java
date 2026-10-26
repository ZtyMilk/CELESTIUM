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
 * 辟邪祥瑞·白泽 (Bai Ze)
 * 羊首狮身，额生双角，颌下仙髯，四蹄踏瑞霭，通体雪白带金纹。
 */
public class BaiZeModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("bai_ze"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart tail;

    public BaiZeModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 躯干 (Body)
        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-6.0F, -5.0F, -10.0F, 12.0F, 10.0F, 20.0F)
                // 颈部祥云鬃毛 (Cloud mane)
                .texOffs(0, 30).addBox(-6.5F, -7.0F, -11.0F, 13.0F, 8.0F, 8.0F),
                PartPose.offset(0.0F, 11.0F, 0.0F));

        // 头部与双角、髯须 (Head, Horns & Beard)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(42, 30).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 8.0F, 8.0F)
                // 吻部 (Snout)
                .texOffs(44, 0).addBox(-2.5F, -1.0F, -12.0F, 5.0F, 4.0F, 4.0F)
                // 颌下飘逸仙髯 (Celestial Beard)
                .texOffs(0, 46).addBox(-1.5F, 3.0F, -11.0F, 3.0F, 5.0F, 2.0F)
                // 左曲金角 (Left Horn)
                .texOffs(44, 8).addBox(2.0F, -11.0F, -6.0F, 2.0F, 6.0F, 2.0F)
                // 右曲金角 (Right Horn)
                .texOffs(44, 8).addBox(-4.0F, -11.0F, -6.0F, 2.0F, 6.0F, 2.0F),
                PartPose.offset(0.0F, 6.0F, -9.0F));

        // 四肢 (Legs)
        CubeListBuilder leg = CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F);
        root.addOrReplaceChild("left_front_leg", leg, PartPose.offset(4.0F, 13.0F, -7.0F));
        root.addOrReplaceChild("right_front_leg", leg, PartPose.offset(-4.0F, 13.0F, -7.0F));
        root.addOrReplaceChild("left_hind_leg", leg, PartPose.offset(4.0F, 13.0F, 7.0F));
        root.addOrReplaceChild("right_hind_leg", leg, PartPose.offset(-4.0F, 13.0F, 7.0F));

        // 尾部如意祥云尾 (Tail)
        root.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(16, 46).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 8.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 8.0F, 10.0F, 0.4F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 80, 80);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float limbSwing = state.walkAnimationPos;
        float limbSwingAmount = state.walkAnimationSpeed;

        this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.tail.yRot = Mth.cos(state.ageInTicks * 0.1F) * 0.2F;
        this.head.xRot = 0.1F + Mth.sin(state.ageInTicks * 0.05F) * 0.05F;
    }
}
