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
 * 三足金乌模型 (Three-Legged Golden Crow Model)
 * 炽阳金羽、玄金利喙、曜火金冠、三足鼎立踏火、三缕流金尾羽。
 */
public class GoldenCrowModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("golden_crow"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart wingL;
    private final ModelPart wingR;
    private final ModelPart legL;
    private final ModelPart legR;
    private final ModelPart legM;
    private final ModelPart tail;

    public GoldenCrowModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.wingL = this.body.getChild("wing_l");
        this.wingR = this.body.getChild("wing_r");
        this.tail = this.body.getChild("tail");
        this.legL = root.getChild("leg_l");
        this.legR = root.getChild("leg_r");
        this.legM = root.getChild("leg_m");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 金乌神首 (Head, Beak, Sun Crown)
        root.addOrReplaceChild("head", CubeListBuilder.create()
                // 乌首
                .texOffs(0, 0).addBox(-3.0F, -3.5F, -4.0F, 6.0F, 6.0F, 6.0F)
                // 玄金曜喙
                .texOffs(24, 0).addBox(-1.5F, -0.5F, -8.0F, 3.0F, 3.0F, 5.0F)
                // 烈阳神鸟凤翎火冠
                .texOffs(40, 0).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 5.0F, 6.0F),
                PartPose.offset(0.0F, 12.0F, -5.0F));

        // 2. 炎阳躯干 (Body)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 14).addBox(-4.0F, -3.5F, -5.0F, 8.0F, 7.0F, 11.0F),
                PartPose.offset(0.0F, 14.5F, 0.0F));

        // 左右垂天金翼 (Wings)
        body.addOrReplaceChild("wing_l", CubeListBuilder.create()
                .texOffs(28, 14).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 6.0F, 12.0F),
                PartPose.offset(-4.0F, -2.0F, -3.0F));

        body.addOrReplaceChild("wing_r", CubeListBuilder.create()
                .texOffs(28, 14).addBox(0.0F, -1.0F, -1.0F, 1.0F, 6.0F, 12.0F),
                PartPose.offset(4.0F, -2.0F, -3.0F));

        // 三缕流火长尾羽 (Tail)
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 2.0F, 14.0F),
                PartPose.offset(0.0F, -1.5F, 6.0F));

        // 3. 上古三足鼎立 (Three Divine Legs)
        // 左足
        root.addOrReplaceChild("leg_l", CubeListBuilder.create()
                .texOffs(40, 32).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F),
                PartPose.offset(-2.5F, 17.0F, 0.5F));

        // 右足
        root.addOrReplaceChild("leg_r", CubeListBuilder.create()
                .texOffs(40, 32).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F),
                PartPose.offset(2.5F, 17.0F, 0.5F));

        // 中央玄金神足 (The 3rd Middle Leg)
        root.addOrReplaceChild("leg_m", CubeListBuilder.create()
                .texOffs(52, 32).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F),
                PartPose.offset(0.0F, 17.0F, 2.5F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.7F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.7F;

        // 飞行时与地面的不同动画模式
        boolean inAir = state.ageInTicks > 0 && Math.abs(this.body.y - 14.5F) > 0.1F;

        if (state.walkAnimationSpeed > 0.05F) {
            // 三足交错步态
            this.legL.xRot = Mth.cos(state.walkAnimationPos * 0.8F) * 1.1F * state.walkAnimationSpeed;
            this.legR.xRot = Mth.cos(state.walkAnimationPos * 0.8F + (float) Math.PI) * 1.1F * state.walkAnimationSpeed;
            this.legM.xRot = Mth.cos(state.walkAnimationPos * 0.8F + (float) Math.PI * 0.5F) * 1.1F * state.walkAnimationSpeed;
        } else {
            this.legL.xRot = 0.0F;
            this.legR.xRot = 0.0F;
            this.legM.xRot = 0.0F;
        }

        // 振翅翱翔
        float flap = Mth.cos(state.ageInTicks * 0.55F) * 0.65F;
        this.wingL.zRot = -0.2F - flap;
        this.wingR.zRot = 0.2F + flap;

        this.tail.yRot = Mth.sin(state.ageInTicks * 0.12F) * 0.18F;
        this.tail.xRot = -0.15F + Mth.cos(state.ageInTicks * 0.1F) * 0.08F;
    }
}
