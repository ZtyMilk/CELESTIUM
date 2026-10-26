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
 * 玄玉麒麟 (Qilin)
 * 龙头、鹿角、狮鬃、云鳞甲与牛尾，伴随祥云踏步生辉
 */
public class QilinModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("qilin"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart tail;

    public QilinModel(ModelPart root) {
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

        // 麒麟身躯与背脊烈焰鳞鬣
        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.5F, -5.0F, -8.0F, 9.0F, 10.0F, 16.0F)
                .texOffs(34, 0).addBox(-1.0F, -8.0F, -7.0F, 2.0F, 3.0F, 14.0F),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        // 龙头、鹿角与飘拂龙须
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 26).addBox(-4.0F, -4.5F, -6.0F, 8.0F, 8.0F, 8.0F)
                .texOffs(32, 26).addBox(-3.0F, -1.0F, -11.0F, 6.0F, 4.5F, 5.0F)
                // 独角/双鹿角 (Jade Horns)
                .texOffs(0, 42).addBox(-1.5F, -10.5F, -5.0F, 3.0F, 6.0F, 2.0F)
                .texOffs(54, 26).addBox(3.0F, 1.0F, -10.0F, 4.0F, 1.0F, 1.0F)
                .texOffs(54, 26).addBox(-7.0F, 1.0F, -10.0F, 4.0F, 1.0F, 1.0F, true),
                PartPose.offset(0.0F, 5.0F, -8.0F));

        // 四肢踏云蹄
        root.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F), PartPose.offset(3.5F, 13.0F, -5.5F));
        root.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, true), PartPose.offset(-3.5F, 13.0F, -5.5F));
        root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(16, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F), PartPose.offset(3.5F, 13.0F, 5.5F));
        root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(16, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, true), PartPose.offset(-3.5F, 13.0F, 5.5F));

        // 狮尾鬃毛
        root.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(32, 50).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 4.0F, 9.0F),
                PartPose.offset(0.0F, 7.0F, 8.0F));

        return LayerDefinition.create(mesh, 64, 64);
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

        this.tail.yRot = Mth.sin(state.ageInTicks * 0.1F) * 0.25F;
    }
}
