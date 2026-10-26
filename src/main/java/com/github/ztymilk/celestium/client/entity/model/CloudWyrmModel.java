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
 * 腾云苍龙·巡天灵蛟 (Cloud Wyrm / Azure Dragon)
 * 辉煌霸气的东方神龙：金角玉额、威严长吻、飞扬龙须、飘逸龙髯、五节游云龙躯、踏云神爪与祥云尾羽。
 */
public class CloudWyrmModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("cloud_wyrm"), "main");

    private final ModelPart head;
    private final ModelPart neck;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart tail;
    private final ModelPart tailFin;
    private final ModelPart clawFL;
    private final ModelPart clawFR;
    private final ModelPart clawBL;
    private final ModelPart clawBR;
    private final ModelPart whiskerL;
    private final ModelPart whiskerR;

    public CloudWyrmModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.whiskerL = this.head.getChild("whisker_l");
        this.whiskerR = this.head.getChild("whisker_r");

        this.neck = root.getChild("neck");
        this.body1 = this.neck.getChild("body1");
        this.clawFL = this.body1.getChild("claw_fl");
        this.clawFR = this.body1.getChild("claw_fr");

        this.body2 = this.body1.getChild("body2");
        this.body3 = this.body2.getChild("body3");
        this.clawBL = this.body3.getChild("claw_bl");
        this.clawBR = this.body3.getChild("claw_br");

        this.body4 = this.body3.getChild("body4");
        this.tail = this.body4.getChild("tail");
        this.tailFin = this.tail.getChild("tail_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 威武龙首 (Dragon Head, Antlers, Crown, Snout, Whiskers)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                // 龙颅
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F)
                // 威严长吻
                .texOffs(32, 0).addBox(-3.0F, -2.0F, -16.0F, 6.0F, 5.0F, 8.0F)
                // 龙吻下颚
                .texOffs(60, 0).addBox(-2.5F, 2.0F, -15.0F, 5.0F, 2.0F, 7.0F)
                // 威严龙额冠与金眉
                .texOffs(84, 0).addBox(-4.5F, -6.0F, -7.0F, 9.0F, 3.0F, 4.0F)
                // 颔下龙髯
                .texOffs(76, 20).addBox(-1.5F, 3.5F, -13.0F, 3.0F, 4.0F, 3.0F)
                // 左金色鹿角主干与分叉
                .texOffs(0, 20).addBox(-3.5F, -11.0F, -4.0F, 2.0F, 7.0F, 2.0F)
                .texOffs(12, 20).addBox(-5.5F, -15.0F, -2.0F, 2.0F, 5.0F, 2.0F)
                // 右金色鹿角主干与分叉
                .texOffs(24, 20).addBox(1.5F, -11.0F, -4.0F, 2.0F, 7.0F, 2.0F)
                .texOffs(36, 20).addBox(3.5F, -15.0F, -2.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offset(0.0F, 12.0F, -16.0F));

        // 飘逸灵动龙须 (Whiskers)
        head.addOrReplaceChild("whisker_l", CubeListBuilder.create()
                .texOffs(48, 20).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 10.0F),
                PartPose.offset(-3.0F, 1.0F, -14.0F));

        head.addOrReplaceChild("whisker_r", CubeListBuilder.create()
                .texOffs(62, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 10.0F),
                PartPose.offset(3.0F, 1.0F, -14.0F));

        // 2. 龙颈 (Neck)
        PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 10.0F)
                // 颈部如意背鬃
                .texOffs(36, 32).addBox(-0.5F, -6.5F, 0.0F, 1.0F, 3.0F, 10.0F),
                PartPose.offset(0.0F, 12.5F, -15.0F));

        // 3. 龙躯第一节 (Body 1) + 前双神爪
        PartDefinition body1 = neck.addOrReplaceChild("body1", CubeListBuilder.create()
                .texOffs(0, 48).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 12.0F)
                .texOffs(40, 48).addBox(-0.5F, -6.5F, 0.0F, 1.0F, 3.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 9.5F));

        // 左前爪
        body1.addOrReplaceChild("claw_fl", CubeListBuilder.create()
                .texOffs(68, 48).addBox(-2.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F),
                PartPose.offset(-3.5F, 2.0F, 4.0F));

        // 右前爪
        body1.addOrReplaceChild("claw_fr", CubeListBuilder.create()
                .texOffs(84, 48).addBox(-0.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F),
                PartPose.offset(3.5F, 2.0F, 4.0F));

        // 4. 龙躯第二节 (Body 2)
        PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create()
                .texOffs(0, 66).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 12.0F)
                .texOffs(38, 66).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 3.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 11.5F));

        // 5. 龙躯第三节 (Body 3) + 后双神爪
        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create()
                .texOffs(0, 84).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 12.0F)
                .texOffs(38, 84).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 3.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 11.5F));

        // 左后爪
        body3.addOrReplaceChild("claw_bl", CubeListBuilder.create()
                .texOffs(68, 84).addBox(-2.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F),
                PartPose.offset(-3.0F, 2.0F, 4.0F));

        // 右后爪
        body3.addOrReplaceChild("claw_br", CubeListBuilder.create()
                .texOffs(84, 84).addBox(-0.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F),
                PartPose.offset(3.0F, 2.0F, 4.0F));

        // 6. 龙躯第四节 (Body 4)
        PartDefinition body4 = body3.addOrReplaceChild("body4", CubeListBuilder.create()
                .texOffs(0, 102).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 12.0F)
                .texOffs(36, 102).addBox(-0.5F, -5.5F, 0.0F, 1.0F, 3.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 11.5F));

        // 7. 游龙尾部 (Tail)
        PartDefinition tail = body4.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(56, 102).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 14.0F)
                .texOffs(94, 102).addBox(-0.5F, -4.5F, 0.0F, 1.0F, 2.5F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 11.5F));

        // 8. 祥云流光尾扇 (Tail Fin)
        tail.addOrReplaceChild("tail_fin", CubeListBuilder.create()
                .texOffs(70, 66).addBox(-1.0F, -7.0F, 8.0F, 2.0F, 14.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks * 0.11F;

        // 龙头灵动转向
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.6F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.6F + Mth.sin(age + 0.6F) * 0.12F;

        // 龙须飘荡
        this.whiskerL.yRot = -0.2F + Mth.sin(age * 1.5F) * 0.25F;
        this.whiskerR.yRot = 0.2F - Mth.sin(age * 1.5F) * 0.25F;
        this.whiskerL.zRot = Mth.cos(age * 1.2F) * 0.15F;
        this.whiskerR.zRot = -Mth.cos(age * 1.2F) * 0.15F;

        // 逐节层级波动蜿蜒游弋（形成东方巨龙行云布雨的经典 S 形身段）
        this.neck.yRot = Mth.sin(age) * 0.18F;
        this.body1.yRot = Mth.sin(age - 0.5F) * 0.22F;
        this.body2.yRot = Mth.sin(age - 1.0F) * 0.26F;
        this.body3.yRot = Mth.sin(age - 1.5F) * 0.30F;
        this.body4.yRot = Mth.sin(age - 2.0F) * 0.34F;
        this.tail.yRot = Mth.sin(age - 2.5F) * 0.38F;
        this.tailFin.yRot = Mth.sin(age - 3.0F) * 0.42F;

        // 纵向波浪律动
        this.neck.xRot = Mth.cos(age * 0.8F) * 0.06F;
        this.body1.xRot = Mth.cos(age * 0.8F - 0.4F) * 0.08F;
        this.body2.xRot = Mth.cos(age * 0.8F - 0.8F) * 0.10F;
        this.body3.xRot = Mth.cos(age * 0.8F - 1.2F) * 0.12F;
        this.body4.xRot = Mth.cos(age * 0.8F - 1.6F) * 0.14F;
        this.tail.xRot = Mth.cos(age * 0.8F - 2.0F) * 0.16F;

        // 四神爪划云律动
        this.clawFL.xRot = 0.3F + Mth.sin(age + 0.5F) * 0.2F;
        this.clawFR.xRot = 0.3F - Mth.sin(age + 0.5F) * 0.2F;
        this.clawBL.xRot = 0.3F + Mth.sin(age - 1.0F) * 0.2F;
        this.clawBR.xRot = 0.3F - Mth.sin(age - 1.0F) * 0.2F;
    }
}
