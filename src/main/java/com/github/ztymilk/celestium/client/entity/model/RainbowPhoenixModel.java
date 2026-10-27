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
 * 九天玄凤 (Rainbow Phoenix / 百鸟之王，神圣九天玄火)
 * “丹穴之山，有鸟焉，其状如鸡，五采而文，名曰凤皇。首文曰德，翼文曰义，背文曰礼，膺文曰仁，腹文曰信。”
 *
 * 解剖级树状体系架构 (Anatomical Tree Architecture)：
 * root
 *  └── body (凤鸟主躯干，饱满凤胸，前挺背弓，腹部微弧)
 *       ├── neck_lower (下段曲颈，带下颈丰盈赤羽翎毛)
 *       │    └── neck_upper (上段曲颈，S弯向上仰起)
 *       │         └── head (神鸟玉首，丹凤金瞳，锐利猛禽金喙，下颌凤髯)
 *       │              └── crown (冲天流光九羽凤冠，高耸外扬，如烈焰金芒)
 *       ├── left_wing_inner (左内翼大臂骨，附带丰满覆羽)
 *       │    └── left_wing_mid (左中翼小臂骨)
 *       │         └── left_wing_outer (左外翼初级飞羽与刀锋金翎，宽幅伸展)
 *       ├── right_wing_inner (右内翼大臂骨，对称结构)
 *       │    └── right_wing_mid (右中翼小臂骨)
 *       │         └── right_wing_outer (右外翼初级飞羽与刀锋金翎)
 *       ├── tail_fan (尾羽基座扇面，展开呈孔雀般半圆凤屏)
 *       │    ├── tail_streamer_center (正中主翎长尾第一节)
 *       │    │    └── tail_streamer_center_mid (中节)
 *       │    │         └── tail_streamer_center_tip (末梢飘逸凤尾金印)
 *       │    ├── tail_streamer_left (左飘带长尾第一节)
 *       │    │    └── tail_streamer_left_mid (中节)
 *       │    │         └── tail_streamer_left_tip (末梢)
 *       │    └── tail_streamer_right (右飘带长尾第一节)
 *       │         └── tail_streamer_right_mid (中节)
 *       │              └── tail_streamer_right_tip (末梢)
 *       ├── leg_left (左大腿股部，附腿羽)
 *       │    └── shank_left (左小腿胫骨与金爪四趾，抓地或空中自然收缩)
 *       └── leg_right (右大腿股部，附腿羽)
 *            └── shank_right (右小腿胫骨与金爪四趾)
 */
public class RainbowPhoenixModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("rainbow_phoenix"), "main");

    private final ModelPart root;
    private final ModelPart body;

    // 颈部与凤首
    private final ModelPart neckLower;
    private final ModelPart neckUpper;
    private final ModelPart head;
    private final ModelPart crown;

    // 左翼三段铰接
    private final ModelPart leftWingInner;
    private final ModelPart leftWingMid;
    private final ModelPart leftWingOuter;

    // 右翼三段铰接
    private final ModelPart rightWingInner;
    private final ModelPart rightWingMid;
    private final ModelPart rightWingOuter;

    // 凤屏与三道九天流光长尾 (3节级联)
    private final ModelPart tailFan;
    private final ModelPart tailStreamerCenter;
    private final ModelPart tailStreamerCenterMid;
    private final ModelPart tailStreamerCenterTip;
    private final ModelPart tailStreamerLeft;
    private final ModelPart tailStreamerLeftMid;
    private final ModelPart tailStreamerLeftTip;
    private final ModelPart tailStreamerRight;
    private final ModelPart tailStreamerRightMid;
    private final ModelPart tailStreamerRightTip;

    // 双足
    private final ModelPart legLeft;
    private final ModelPart shankLeft;
    private final ModelPart legRight;
    private final ModelPart shankRight;

    public RainbowPhoenixModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");

        // 颈部与凤首
        this.neckLower = this.body.getChild("neck_lower");
        this.neckUpper = this.neckLower.getChild("neck_upper");
        this.head = this.neckUpper.getChild("head");
        this.crown = this.head.getChild("crown");

        // 左翼三段
        this.leftWingInner = this.body.getChild("left_wing_inner");
        this.leftWingMid = this.leftWingInner.getChild("left_wing_mid");
        this.leftWingOuter = this.leftWingMid.getChild("left_wing_outer");

        // 右翼三段
        this.rightWingInner = this.body.getChild("right_wing_inner");
        this.rightWingMid = this.rightWingInner.getChild("right_wing_mid");
        this.rightWingOuter = this.rightWingMid.getChild("right_wing_outer");

        // 尾羽屏与三道长尾
        this.tailFan = this.body.getChild("tail_fan");
        this.tailStreamerCenter = this.tailFan.getChild("tail_streamer_center");
        this.tailStreamerCenterMid = this.tailStreamerCenter.getChild("tail_streamer_center_mid");
        this.tailStreamerCenterTip = this.tailStreamerCenterMid.getChild("tail_streamer_center_tip");

        this.tailStreamerLeft = this.tailFan.getChild("tail_streamer_left");
        this.tailStreamerLeftMid = this.tailStreamerLeft.getChild("tail_streamer_left_mid");
        this.tailStreamerLeftTip = this.tailStreamerLeftMid.getChild("tail_streamer_left_tip");

        this.tailStreamerRight = this.tailFan.getChild("tail_streamer_right");
        this.tailStreamerRightMid = this.tailStreamerRight.getChild("tail_streamer_right_mid");
        this.tailStreamerRightTip = this.tailStreamerRightMid.getChild("tail_streamer_right_tip");

        // 双足
        this.legLeft = this.body.getChild("leg_left");
        this.shankLeft = this.legLeft.getChild("shank_left");
        this.legRight = this.body.getChild("leg_right");
        this.shankRight = this.legRight.getChild("shank_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 凤鸟主躯干 (Torso, Chest, Belly, Back Mantle)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.5F, -4.5F, -7.0F, 9.0F, 9.0F, 14.0F)
                .texOffs(106, 74).addBox(-3.5F, -3.5F, -9.0F, 7.0F, 7.0F, 3.0F)
                .texOffs(68, 41).addBox(-3.5F, 4.0F, -4.0F, 7.0F, 2.0F, 10.0F)
                .texOffs(0, 49).addBox(-3.0F, -5.5F, -3.0F, 6.0F, 2.0F, 9.0F),
                PartPose.offset(0.0F, 12.0F, 0.0F));

        // 2. 颈项、凤首与九羽冲天凤冠 (S-Neck, Head, Beak, Beard, Eyebrows, Flame Crown)
        PartDefinition neckLower = body.addOrReplaceChild("neck_lower", CubeListBuilder.create()
                .texOffs(102, 41).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 7.0F, 5.0F)
                .texOffs(60, 93).addBox(-3.0F, -6.0F, -3.5F, 6.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -3.0F, -6.0F, -0.45F, 0.0F, 0.0F));

        PartDefinition neckUpper = neckLower.addOrReplaceChild("neck_upper", CubeListBuilder.create()
                .texOffs(0, 81).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F)
                .texOffs(88, 93).addBox(-1.0F, -5.5F, 1.5F, 2.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, 0.35F, 0.0F, 0.0F));

        PartDefinition head = neckUpper.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(104, 53).addBox(-2.5F, -3.5F, -4.0F, 5.0F, 5.0F, 6.0F)
                .texOffs(76, 93).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 3.0F, 4.0F)
                .texOffs(96, 93).addBox(-0.5F, 1.5F, -5.0F, 1.0F, 4.0F, 3.0F)
                .texOffs(0, 106).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 2.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.10F, 0.0F, 0.0F));

        head.addOrReplaceChild("crown", CubeListBuilder.create()
                .texOffs(46, 0).addBox(-0.5F, -12.0F, -1.5F, 1.0F, 12.0F, 5.0F)
                .texOffs(100, 0).addBox(0.8F, -10.0F, 0.0F, 1.0F, 10.0F, 4.0F)
                .texOffs(110, 0).addBox(-1.8F, -10.0F, 0.0F, 1.0F, 10.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, -3.5F, 0.5F, -0.22F, 0.0F, 0.0F));

        // 3. 左翼三段铰接 (Left Wing: Inner Arm -> Mid Arm -> Outer Blade & Tips)
        PartDefinition leftWingInner = body.addOrReplaceChild("left_wing_inner", CubeListBuilder.create()
                .texOffs(32, 43).addBox(0.0F, -1.5F, -3.5F, 10.0F, 3.0F, 8.0F)
                .texOffs(64, 100).addBox(0.0F, -0.5F, 4.0F, 9.0F, 1.0F, 5.0F),
                PartPose.offset(4.5F, -2.5F, -3.0F));

        PartDefinition leftWingMid = leftWingInner.addOrReplaceChild("left_wing_mid", CubeListBuilder.create()
                .texOffs(0, 71).addBox(0.0F, -1.2F, -3.0F, 11.0F, 2.0F, 8.0F)
                .texOffs(0, 100).addBox(0.0F, -0.4F, 4.5F, 11.0F, 1.0F, 5.0F),
                PartPose.offset(10.0F, 0.0F, 0.0F));

        leftWingMid.addOrReplaceChild("left_wing_outer", CubeListBuilder.create()
                .texOffs(16, 84).addBox(0.0F, -0.8F, -2.5F, 13.0F, 2.0F, 7.0F)
                .texOffs(96, 64).addBox(11.0F, -0.5F, -1.5F, 6.0F, 1.0F, 9.0F),
                PartPose.offset(11.0F, 0.0F, 0.0F));

        // 4. 右翼三段铰接 (Right Wing: Inner Arm -> Mid Arm -> Outer Blade & Tips)
        PartDefinition rightWingInner = body.addOrReplaceChild("right_wing_inner", CubeListBuilder.create()
                .texOffs(68, 53).addBox(-10.0F, -1.5F, -3.5F, 10.0F, 3.0F, 8.0F)
                .texOffs(92, 100).addBox(-9.0F, -0.5F, 4.0F, 9.0F, 1.0F, 5.0F),
                PartPose.offset(-4.5F, -2.5F, -3.0F));

        PartDefinition rightWingMid = rightWingInner.addOrReplaceChild("right_wing_mid", CubeListBuilder.create()
                .texOffs(38, 74).addBox(-11.0F, -1.2F, -3.0F, 11.0F, 2.0F, 8.0F)
                .texOffs(32, 100).addBox(-11.0F, -0.4F, 4.5F, 11.0F, 1.0F, 5.0F),
                PartPose.offset(-10.0F, 0.0F, 0.0F));

        rightWingMid.addOrReplaceChild("right_wing_outer", CubeListBuilder.create()
                .texOffs(56, 84).addBox(-13.0F, -0.8F, -2.5F, 13.0F, 2.0F, 7.0F)
                .texOffs(76, 74).addBox(-17.0F, -0.5F, -1.5F, 6.0F, 1.0F, 9.0F),
                PartPose.offset(-11.0F, 0.0F, 0.0F));

        // 5. 尾羽扇面与三道九天流光长尾 (Tail Fan & 3 Cascaded Streamers)
        PartDefinition tailFan = body.addOrReplaceChild("tail_fan", CubeListBuilder.create()
                .texOffs(56, 64).addBox(-6.0F, -1.0F, 0.0F, 12.0F, 2.0F, 8.0F)
                .texOffs(16, 93).addBox(-8.0F, -0.5F, 3.0F, 16.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -2.0F, 6.5F, 0.32F, 0.0F, 0.0F));

        // 正中主翎长尾 (3节级联)
        PartDefinition tailStreamerCenter = tailFan.addOrReplaceChild("tail_streamer_center", CubeListBuilder.create()
                .texOffs(96, 15).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 7.5F));

        PartDefinition tailStreamerCenterMid = tailStreamerCenter.addOrReplaceChild("tail_streamer_center_mid", CubeListBuilder.create()
                .texOffs(58, 15).addBox(-2.5F, -0.4F, 0.0F, 5.0F, 1.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 12.0F));

        tailStreamerCenterMid.addOrReplaceChild("tail_streamer_center_tip", CubeListBuilder.create()
                .texOffs(58, 0).addBox(-3.5F, -0.3F, 0.0F, 7.0F, 1.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 14.0F));

        // 左飘带长尾 (3节级联)
        PartDefinition tailStreamerLeft = tailFan.addOrReplaceChild("tail_streamer_left", CubeListBuilder.create()
                .texOffs(30, 54).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 10.0F),
                PartPose.offsetAndRotation(3.5F, 0.2F, 6.0F, 0.08F, 0.18F, 0.10F));

        PartDefinition tailStreamerLeftMid = tailStreamerLeft.addOrReplaceChild("tail_streamer_left_mid", CubeListBuilder.create()
                .texOffs(96, 28).addBox(-2.0F, -0.4F, 0.0F, 4.0F, 1.0F, 12.0F),
                PartPose.offset(0.5F, 0.0F, 10.0F));

        tailStreamerLeftMid.addOrReplaceChild("tail_streamer_left_tip", CubeListBuilder.create()
                .texOffs(0, 23).addBox(-2.5F, -0.3F, 0.0F, 5.0F, 1.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 12.0F));

        // 右飘带长尾 (3节级联)
        PartDefinition tailStreamerRight = tailFan.addOrReplaceChild("tail_streamer_right", CubeListBuilder.create()
                .texOffs(0, 60).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 10.0F),
                PartPose.offsetAndRotation(-3.5F, 0.2F, 6.0F, 0.08F, -0.18F, -0.10F));

        PartDefinition tailStreamerRightMid = tailStreamerRight.addOrReplaceChild("tail_streamer_right_mid", CubeListBuilder.create()
                .texOffs(0, 36).addBox(-2.0F, -0.4F, 0.0F, 4.0F, 1.0F, 12.0F),
                PartPose.offset(-0.5F, 0.0F, 10.0F));

        tailStreamerRightMid.addOrReplaceChild("tail_streamer_right_tip", CubeListBuilder.create()
                .texOffs(34, 30).addBox(-2.5F, -0.3F, 0.0F, 5.0F, 1.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 12.0F));

        // 6. 左大腿与小腿四趾金爪 (Leg Left & Shank Left)
        PartDefinition legLeft = body.addOrReplaceChild("leg_left", CubeListBuilder.create()
                .texOffs(96, 84).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 5.0F, 4.0F),
                PartPose.offset(3.0F, 4.0F, 2.0F));

        legLeft.addOrReplaceChild("shank_left", CubeListBuilder.create()
                .texOffs(0, 91).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F)
                .texOffs(20, 106).addBox(-2.0F, 5.0F, -4.0F, 4.0F, 2.0F, 4.0F)
                .texOffs(52, 106).addBox(-1.0F, 5.0F, 0.0F, 2.0F, 2.0F, 3.0F),
                PartPose.offset(0.0F, 5.0F, 0.0F));

        // 7. 右大腿与小腿四趾金爪 (Leg Right & Shank Right)
        PartDefinition legRight = body.addOrReplaceChild("leg_right", CubeListBuilder.create()
                .texOffs(110, 84).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 5.0F, 4.0F),
                PartPose.offset(-3.0F, 4.0F, 2.0F));

        legRight.addOrReplaceChild("shank_right", CubeListBuilder.create()
                .texOffs(8, 91).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F)
                .texOffs(36, 106).addBox(-2.0F, 5.0F, -4.0F, 4.0F, 2.0F, 4.0F)
                .texOffs(62, 106).addBox(-1.0F, 5.0F, 0.0F, 2.0F, 2.0F, 3.0F),
                PartPose.offset(0.0F, 5.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // 1. 待机浮游呼吸律动 (Ethereal Hovering Respiration)
        float breath = Mth.sin(age * 0.08F);
        float breathCos = Mth.cos(age * 0.08F);
        this.body.y = 12.0F + breath * 0.8F;
        this.body.xRot = breathCos * 0.02F;

        // 2. S曲颈与灵眸注视跟踪 (Optokinetic Head Tracking & Cervical S-Curve)
        float netHeadYaw = state.yRot * ((float) Math.PI / 180F);
        float headPitch = state.xRot * ((float) Math.PI / 180F);

        // 平滑分配给下曲颈(25%)、上曲颈(35%)与凤首(40%)
        this.neckLower.yRot = netHeadYaw * 0.25F;
        this.neckLower.xRot = -0.45F + headPitch * 0.25F + breath * 0.04F;

        this.neckUpper.yRot = netHeadYaw * 0.35F;
        this.neckUpper.xRot = 0.35F + headPitch * 0.35F - breath * 0.03F;

        this.head.yRot = netHeadYaw * 0.40F;
        this.head.xRot = 0.10F + headPitch * 0.40F + breathCos * 0.04F;

        // 冲天凤冠随气流轻颤
        this.crown.xRot = -0.22F + breath * 0.03F;
        this.crown.zRot = breathCos * 0.02F;

        // 3. 多节羽翼层级挥舞波浪 (Articulated Wing Flap Wave with Phase Delays)
        // 飞行与滑翔自适应节奏
        float flapSpeed = 0.22F + walkSpeed * 0.08F;
        float flapTime = age * flapSpeed;
        float flapCycle = Mth.sin(flapTime);
        float flapCos = Mth.cos(flapTime);

        // (1) 内翼 (Inner Arm): 大角度上下扑翼与轻微前后摆动
        float innerFlapAmp = 0.38F + walkSpeed * 0.12F;
        this.leftWingInner.zRot = 0.15F + flapCycle * innerFlapAmp;
        this.leftWingInner.xRot = flapCos * 0.10F;
        this.leftWingInner.yRot = -0.05F - flapCycle * 0.06F;

        this.rightWingInner.zRot = -0.15F - flapCycle * innerFlapAmp;
        this.rightWingInner.xRot = flapCos * 0.10F;
        this.rightWingInner.yRot = 0.05F + flapCycle * 0.06F;

        // (2) 中翼 (Mid Arm): 相位滞后 0.35 rad (Phase Delay)
        float flapMid = Mth.sin(flapTime - 0.35F);
        float flapMidCos = Mth.cos(flapTime - 0.35F);
        this.leftWingMid.zRot = 0.10F + flapMid * 0.32F;
        this.leftWingMid.xRot = flapMidCos * 0.08F;
        this.leftWingMid.yRot = -flapMid * 0.05F;

        this.rightWingMid.zRot = -0.10F - flapMid * 0.32F;
        this.rightWingMid.xRot = flapMidCos * 0.08F;
        this.rightWingMid.yRot = flapMid * 0.05F;

        // (3) 外翼初级飞羽与刀锋金翎 (Outer Blade & Tips): 相位滞后 0.70 rad，大幅弯折柔韧波浪
        float flapOuter = Mth.sin(flapTime - 0.70F);
        float flapOuterCos = Mth.cos(flapTime - 0.70F);
        this.leftWingOuter.zRot = 0.12F + flapOuter * 0.48F;
        this.leftWingOuter.xRot = flapOuterCos * 0.12F;
        this.leftWingOuter.yRot = -flapOuter * 0.08F;

        this.rightWingOuter.zRot = -0.12F - flapOuter * 0.48F;
        this.rightWingOuter.xRot = flapOuterCos * 0.12F;
        this.rightWingOuter.yRot = flapOuter * 0.08F;

        // 4. 三条长尾飘带行波方程 (Continuous Streamer Kinematic Wave)
        // 振幅逐节递增，相位逐节滞后，左右两侧带有外展与反相位飘动
        float waveTime = age * 0.14F;

        // 尾羽基座孔雀扇面轻摇
        this.tailFan.xRot = 0.32F + Mth.sin(waveTime * 0.6F) * 0.05F;
        this.tailFan.yRot = Mth.cos(waveTime * 0.5F) * 0.04F;
        this.tailFan.zRot = Mth.sin(waveTime * 0.4F) * 0.02F;

        // --- 正中主翎长尾 (Center Streamer: Base -> Mid -> Tip) ---
        // 节 1 (Base)
        this.tailStreamerCenter.xRot = 0.06F + Mth.sin(waveTime) * 0.09F;
        this.tailStreamerCenter.yRot = Mth.cos(waveTime * 0.85F) * 0.07F;
        this.tailStreamerCenter.zRot = Mth.sin(waveTime * 0.70F) * 0.04F;

        // 节 2 (Mid) - 滞后 0.55 rad，振幅递增
        this.tailStreamerCenterMid.xRot = 0.05F + Mth.sin(waveTime - 0.55F) * 0.16F;
        this.tailStreamerCenterMid.yRot = Mth.cos(waveTime * 0.85F - 0.55F) * 0.13F;
        this.tailStreamerCenterMid.zRot = Mth.sin(waveTime * 0.70F - 0.55F) * 0.08F;

        // 节 3 (Tip) - 滞后 1.10 rad，末梢大幅翻飞
        this.tailStreamerCenterTip.xRot = 0.06F + Mth.sin(waveTime - 1.10F) * 0.24F;
        this.tailStreamerCenterTip.yRot = Mth.cos(waveTime * 0.85F - 1.10F) * 0.20F;
        this.tailStreamerCenterTip.zRot = Mth.sin(waveTime * 0.70F - 1.10F) * 0.13F;

        // --- 左飘带长尾 (Left Streamer: Base -> Mid -> Tip, 初始左外展, 相位+0.75) ---
        float leftPhase = waveTime + 0.75F;
        this.tailStreamerLeft.xRot = 0.08F + Mth.sin(leftPhase) * 0.08F;
        this.tailStreamerLeft.yRot = 0.18F + Mth.cos(leftPhase * 0.85F) * 0.08F;
        this.tailStreamerLeft.zRot = 0.10F + Mth.sin(leftPhase * 0.70F) * 0.05F;

        this.tailStreamerLeftMid.xRot = 0.05F + Mth.sin(leftPhase - 0.55F) * 0.15F;
        this.tailStreamerLeftMid.yRot = 0.08F + Mth.cos(leftPhase * 0.85F - 0.55F) * 0.14F;
        this.tailStreamerLeftMid.zRot = 0.06F + Mth.sin(leftPhase * 0.70F - 0.55F) * 0.09F;

        this.tailStreamerLeftTip.xRot = 0.06F + Mth.sin(leftPhase - 1.10F) * 0.25F;
        this.tailStreamerLeftTip.yRot = 0.10F + Mth.cos(leftPhase * 0.85F - 1.10F) * 0.22F;
        this.tailStreamerLeftTip.zRot = 0.08F + Mth.sin(leftPhase * 0.70F - 1.10F) * 0.15F;

        // --- 右飘带长尾 (Right Streamer: Base -> Mid -> Tip, 初始右外展, 相位+1.55 反相位波动) ---
        float rightPhase = waveTime + 1.55F;
        this.tailStreamerRight.xRot = 0.08F + Mth.sin(rightPhase) * 0.08F;
        this.tailStreamerRight.yRot = -0.18F - Mth.cos(rightPhase * 0.85F) * 0.08F;
        this.tailStreamerRight.zRot = -0.10F - Mth.sin(rightPhase * 0.70F) * 0.05F;

        this.tailStreamerRightMid.xRot = 0.05F + Mth.sin(rightPhase - 0.55F) * 0.15F;
        this.tailStreamerRightMid.yRot = -0.08F - Mth.cos(rightPhase * 0.85F - 0.55F) * 0.14F;
        this.tailStreamerRightMid.zRot = -0.06F - Mth.sin(rightPhase * 0.70F - 0.55F) * 0.09F;

        this.tailStreamerRightTip.xRot = 0.06F + Mth.sin(rightPhase - 1.10F) * 0.25F;
        this.tailStreamerRightTip.yRot = -0.10F - Mth.cos(rightPhase * 0.85F - 1.10F) * 0.22F;
        this.tailStreamerRightTip.zRot = -0.08F - Mth.sin(rightPhase * 0.70F - 1.10F) * 0.15F;

        // 5. 腿部飞行与站立自适应 (Legs: Flying Tuck vs Standing / Walking Stride)
        if (walkSpeed > 0.05F) {
            // 地面行走 / 助跑迈步：双腿前后交替跨步，小腿自然屈伸
            float walkPos = state.walkAnimationPos;
            this.legLeft.xRot = Mth.cos(walkPos * 0.6662F) * 0.65F * walkSpeed;
            this.legLeft.zRot = 0.0F;
            this.shankLeft.xRot = Math.max(0.0F, -Mth.sin(walkPos * 0.6662F) * 0.4F * walkSpeed);

            this.legRight.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.65F * walkSpeed;
            this.legRight.zRot = 0.0F;
            this.shankRight.xRot = Math.max(0.0F, -Mth.sin(walkPos * 0.6662F + (float) Math.PI) * 0.4F * walkSpeed);
        } else {
            // 空中翱翔 / 悬停时：双大腿向后上方微蜷收敛，小腿向后屈折，金爪虚握随风微摆
            this.legLeft.xRot = 0.75F + Mth.sin(age * 0.10F) * 0.04F;
            this.legLeft.zRot = 0.12F;
            this.shankLeft.xRot = 0.45F + Mth.sin(age * 0.10F + 0.2F) * 0.03F;

            this.legRight.xRot = 0.75F + Mth.sin(age * 0.10F + 0.3F) * 0.04F;
            this.legRight.zRot = -0.12F;
            this.shankRight.xRot = 0.45F + Mth.sin(age * 0.10F + 0.5F) * 0.03F;
        }
    }
}
