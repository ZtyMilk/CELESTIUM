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
 * 六牙白象 (Saddanta) - 普贤尊者法相大乘金刚圣象
 *
 * 【解剖级树状体系架构 (Anatomical Tree Architecture)】：
 * root
 *  └── body (雄伟主躯干，背拱腹实，挂载织金赤锦鞍毯、金丝璎珞流苏、护胸金攀与三层须弥莲华法座)
 *       ├── head (巍峨象首，额顶隆起，神圣金刚顶珠金箍)
 *       │    ├── left_ear (左扇耳根部)
 *       │    │    └── left_ear_tip (左外展耳缘雕花大蒲扇)
 *       │    ├── right_ear (右扇耳根部)
 *       │    │    └── right_ear_tip (右外展耳缘雕花大蒲扇)
 *       │    ├── tusks_left (左侧三根向上挑起的锐利六牙)
 *       │    ├── tusks_right (右侧三根向上挑起的锐利六牙)
 *       │    └── trunk_base (长鼻根部)
 *       │         └── trunk_mid (长鼻中段)
 *       │              └── trunk_tip (长鼻末梢，微卷灵动)
 *       ├── tail_base (象尾基部)
 *       │    └── tail_tip (象尾末梢长毛拂尘)
 *       ├── leg_front_left (左前肩胛大腿，附金刚镯)
 *       │    └── foot_front_left (左前小腿柱与厚重象蹄)
 *       ├── leg_front_right (右前肩胛大腿，附金刚镯)
 *       │    └── foot_front_right (右前小腿柱与厚重象蹄)
 *       ├── leg_hind_left (左后臀大腿，附金刚镯)
 *       │    └── foot_hind_left (左后小腿柱与厚重象蹄)
 *       └── leg_hind_right (右后臀大腿，附金刚镯)
 *            └── foot_hind_right (右后小腿柱与厚重象蹄)
 *
 * 128x128 贴图UV严密规划，确保 0 重叠、0 溢出。
 */
public class SaddantaModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("saddanta"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart leftEarTip;
    private final ModelPart rightEar;
    private final ModelPart rightEarTip;
    private final ModelPart tusksLeft;
    private final ModelPart tusksRight;
    private final ModelPart trunkBase;
    private final ModelPart trunkMid;
    private final ModelPart trunkTip;
    private final ModelPart tailBase;
    private final ModelPart tailTip;
    private final ModelPart legFrontLeft;
    private final ModelPart footFrontLeft;
    private final ModelPart legFrontRight;
    private final ModelPart footFrontRight;
    private final ModelPart legHindLeft;
    private final ModelPart footHindLeft;
    private final ModelPart legHindRight;
    private final ModelPart footHindRight;

    public SaddantaModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");

        // 象首与五官/六牙/长鼻/大蒲扇耳
        this.head = this.body.getChild("head");
        this.leftEar = this.head.getChild("left_ear");
        this.leftEarTip = this.leftEar.getChild("left_ear_tip");
        this.rightEar = this.head.getChild("right_ear");
        this.rightEarTip = this.rightEar.getChild("right_ear_tip");
        this.tusksLeft = this.head.getChild("tusks_left");
        this.tusksRight = this.head.getChild("tusks_right");
        this.trunkBase = this.head.getChild("trunk_base");
        this.trunkMid = this.trunkBase.getChild("trunk_mid");
        this.trunkTip = this.trunkMid.getChild("trunk_tip");

        // 象尾基部与拂尘末梢
        this.tailBase = this.body.getChild("tail_base");
        this.tailTip = this.tailBase.getChild("tail_tip");

        // 雄伟四足天柱关节
        this.legFrontLeft = this.body.getChild("leg_front_left");
        this.footFrontLeft = this.legFrontLeft.getChild("foot_front_left");
        this.legFrontRight = this.body.getChild("leg_front_right");
        this.footFrontRight = this.legFrontRight.getChild("foot_front_right");
        this.legHindLeft = this.body.getChild("leg_hind_left");
        this.footHindLeft = this.legHindLeft.getChild("foot_hind_left");
        this.legHindRight = this.body.getChild("leg_hind_right");
        this.footHindRight = this.legHindRight.getChild("foot_hind_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 雄伟主躯干 (Body: 挂载织金赤锦鞍毯、金丝璎珞流苏、护胸金攀与三层须弥莲华法座)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 雄浑主躯干 (Main Body Core: 15x13x21)
                .texOffs(0, 0).addBox(-7.5F, -7.0F, -10.5F, 15.0F, 13.0F, 21.0F)
                // 织金赤锦鞍毯 (Embroidered saddle blanket: 16x9x13)
                .texOffs(0, 34).addBox(-8.0F, -7.5F, -6.5F, 16.0F, 9.0F, 13.0F)
                // 垂落金丝璎珞流苏 (Hanging gold & jade tassel fringe: 17x3x14)
                .texOffs(58, 34).addBox(-8.5F, 1.5F, -7.0F, 17.0F, 3.0F, 14.0F)
                // 护胸织金攀带 (Chest harness strap: 16x4x2)
                .texOffs(14, 101).addBox(-8.0F, -2.0F, -11.0F, 16.0F, 4.0F, 2.0F)
                // 前胸护心铜镜 (Chest golden lion mirror: 5x5x1)
                .texOffs(94, 75).addBox(-2.5F, -3.0F, -11.5F, 5.0F, 5.0F, 1.0F)
                // 须弥座下枋基台 (Tier 1: Sumeru lower plinth: 14x2x11)
                .texOffs(58, 51).addBox(-7.0F, -9.5F, -5.5F, 14.0F, 2.0F, 11.0F)
                // 须弥座束腰 (Tier 2: Sumeru waist with relief: 11x2x8)
                .texOffs(40, 75).addBox(-5.5F, -11.5F, -4.0F, 11.0F, 2.0F, 8.0F)
                // 须弥座上枋台 (Tier 3: Upper plinth rim: 13x2x10)
                .texOffs(0, 56).addBox(-6.5F, -13.5F, -5.0F, 13.0F, 2.0F, 10.0F)
                // 中央莲蓬软垫 (Central lotus seedpad seat: 10x1x7)
                .texOffs(30, 85).addBox(-5.0F, -14.5F, -3.5F, 10.0F, 1.0F, 7.0F)
                // 绽放金莲花瓣 - 前瓣 (Blooming Lotus Front Petal: 7x3x1)
                .texOffs(92, 30).addBox(-3.5F, -16.5F, -5.0F, 7.0F, 3.0F, 1.0F)
                // 绽放金莲花瓣 - 后瓣 (Blooming Lotus Back Petal: 7x3x1)
                .texOffs(70, 101).addBox(-3.5F, -16.5F, 4.0F, 7.0F, 3.0F, 1.0F)
                // 绽放金莲花瓣 - 左瓣 (Blooming Lotus Left Petal: 1x3x6)
                .texOffs(16, 79).addBox(4.0F, -16.5F, -3.0F, 1.0F, 3.0F, 6.0F)
                // 绽放金莲花瓣 - 右瓣 (Blooming Lotus Right Petal: 1x3x6)
                .texOffs(94, 83).addBox(-5.0F, -16.5F, -3.0F, 1.0F, 3.0F, 6.0F)
                // 顶端摩尼法宝灵珠 (Top Mani jewel finial: 4x5x4)
                .texOffs(78, 75).addBox(-2.0F, -19.5F, -2.0F, 4.0F, 5.0F, 4.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // =========================================================================
        // 2. 巍峨象首 (Head: 额顶隆起，神圣金刚顶珠金箍与下颌)
        // =========================================================================
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 象首主骨 (Elephant skull: 11x10x9)
                .texOffs(72, 0).addBox(-5.5F, -5.5F, -9.0F, 11.0F, 10.0F, 9.0F)
                // 额顶隆起与神圣金刚顶珠金箍 (Tiara & Forehead dome: 8x3x2)
                .texOffs(50, 101).addBox(-4.0F, -7.5F, -9.5F, 8.0F, 3.0F, 2.0F)
                // 下颌咽喉 (Jaw & throat: 6x3x5)
                .texOffs(48, 93).addBox(-3.0F, 3.5F, -6.0F, 6.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 0.0F, -10.0F));

        // 2.1 双层外展雕花大蒲扇耳 (Left & Right Flared Fan Ears)
        PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                // 左扇耳根部 (Left ear root: 1x10x4)
                .texOffs(72, 19).addBox(0.0F, -4.0F, -2.0F, 1.0F, 10.0F, 4.0F),
                PartPose.offsetAndRotation(5.5F, -1.5F, -3.5F, 0.0F, 0.22F, -0.05F));
        leftEar.addOrReplaceChild("left_ear_tip", CubeListBuilder.create()
                // 左外展耳缘雕花大蒲扇 (Left outer fan flap: 1x12x5)
                .texOffs(112, 0).addBox(0.0F, -4.0F, 0.0F, 1.0F, 12.0F, 5.0F),
                PartPose.offset(0.5F, -1.0F, 2.0F));

        PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                // 右扇耳根部 (Right ear root: 1x10x4)
                .texOffs(82, 19).addBox(-1.0F, -4.0F, -2.0F, 1.0F, 10.0F, 4.0F),
                PartPose.offsetAndRotation(-5.5F, -1.5F, -3.5F, 0.0F, -0.22F, 0.05F));
        rightEar.addOrReplaceChild("right_ear_tip", CubeListBuilder.create()
                // 右外展耳缘雕花大蒲扇 (Right outer fan flap: 1x12x5)
                .texOffs(112, 17).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 12.0F, 5.0F),
                PartPose.offset(-0.5F, -1.0F, 2.0F));

        // 2.2 六根向上挑起的锐利纯白灵牙 (Left & Right Sacred Tusks: 3 on left, 3 on right)
        head.addOrReplaceChild("tusks_left", CubeListBuilder.create()
                // 上灵牙 (Upper Tusk: 金色牙箍 2x2x2 + 锐利长牙 2x2x6)
                .texOffs(120, 43).addBox(-0.5F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F)
                .texOffs(112, 92).addBox(-0.5F, -1.0F, -7.5F, 2.0F, 2.0F, 6.0F)
                // 中灵牙 (Mid Tusk: 牙箍 2x2x2 + 锐利长牙 2x2x5)
                .texOffs(120, 47).addBox(1.0F, 0.5F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(0, 96).addBox(1.0F, 0.5F, -6.0F, 2.0F, 2.0F, 5.0F)
                // 下灵牙 (Lower Tusk: 牙箍 2x2x2 + 锐利长牙 2x2x4)
                .texOffs(30, 79).addBox(-1.5F, 1.5F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(100, 100).addBox(-1.5F, 1.5F, -5.0F, 2.0F, 2.0F, 4.0F),
                PartPose.offsetAndRotation(4.0F, 3.0F, -6.0F, -0.28F, 0.25F, 0.0F));

        head.addOrReplaceChild("tusks_right", CubeListBuilder.create()
                // 上灵牙 (Upper Tusk: 金色牙箍 2x2x2 + 锐利长牙 2x2x6)
                .texOffs(120, 83).addBox(-1.5F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F)
                .texOffs(70, 93).addBox(-1.5F, -1.0F, -7.5F, 2.0F, 2.0F, 6.0F)
                // 中灵牙 (Mid Tusk: 牙箍 2x2x2 + 锐利长牙 2x2x5)
                .texOffs(120, 87).addBox(-3.0F, 0.5F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(86, 100).addBox(-3.0F, 0.5F, -6.0F, 2.0F, 2.0F, 5.0F)
                // 下灵牙 (Lower Tusk: 牙箍 2x2x2 + 锐利长牙 2x2x4)
                .texOffs(14, 96).addBox(-0.5F, 1.5F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(112, 100).addBox(-0.5F, 1.5F, -5.0F, 2.0F, 2.0F, 4.0F),
                PartPose.offsetAndRotation(-4.0F, 3.0F, -6.0F, -0.28F, -0.25F, 0.0F));

        // 2.3 多节灵动卷曲象鼻 (Segmented Trunk: trunk_base -> trunk_mid -> trunk_tip)
        PartDefinition trunkBase = head.addOrReplaceChild("trunk_base", CubeListBuilder.create()
                // 长鼻根部 (Trunk Base: 5x5x5)
                .texOffs(106, 73).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F),
                PartPose.offset(0.0F, 3.0F, -7.5F));

        PartDefinition trunkMid = trunkBase.addOrReplaceChild("trunk_mid", CubeListBuilder.create()
                // 长鼻中段 (Trunk Mid: 4x5x4)
                .texOffs(0, 79).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F),
                PartPose.offset(0.0F, 5.0F, 0.0F));

        trunkMid.addOrReplaceChild("trunk_tip", CubeListBuilder.create()
                // 长鼻末梢 (Trunk Tip: 3x4x3)
                .texOffs(46, 56).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F),
                PartPose.offset(0.0F, 5.0F, 0.0F));

        // =========================================================================
        // 3. 象尾基部与拂尘末梢 (Tail: tail_base -> tail_tip)
        // =========================================================================
        PartDefinition tailBase = body.addOrReplaceChild("tail_base", CubeListBuilder.create()
                // 象尾基部 (Tail Base: 2x7x2)
                .texOffs(120, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offset(0.0F, -2.0F, 10.5F));

        tailBase.addOrReplaceChild("tail_tip", CubeListBuilder.create()
                // 象尾末梢长毛拂尘 (Tail Whisk Tip: 3x6x3)
                .texOffs(108, 83).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 6.0F, 3.0F),
                PartPose.offset(0.0F, 7.0F, 0.0F));

        // =========================================================================
        // 4. 四足天柱神腿与金刚宝镯 (4 Heavy Leg Columns & Vajra Bangles)
        // =========================================================================
        // 4.1 左前腿 (Left Front Leg & Foot)
        PartDefinition legFL = body.addOrReplaceChild("leg_front_left", CubeListBuilder.create()
                // 左前肩胛大腿 (Thigh: 5x6x5)
                .texOffs(92, 19).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                // 金刚宝镯 (Vajra Bangle: 6x2x6)
                .texOffs(64, 85).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 2.0F, 6.0F),
                PartPose.offset(5.5F, 6.0F, -6.5F));
        legFL.addOrReplaceChild("foot_front_left", CubeListBuilder.create()
                // 左前小腿与厚重象蹄 (Foot: 5x6x5)
                .texOffs(108, 51).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 4.2 右前腿 (Right Front Leg & Foot)
        PartDefinition legFR = body.addOrReplaceChild("leg_front_right", CubeListBuilder.create()
                // 右前肩胛大腿 (Thigh: 5x6x5)
                .texOffs(108, 62).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                // 金刚宝镯 (Vajra Bangle: 6x2x6)
                .texOffs(0, 88).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 2.0F, 6.0F),
                PartPose.offset(-5.5F, 6.0F, -6.5F));
        legFR.addOrReplaceChild("foot_front_right", CubeListBuilder.create()
                // 右前小腿与厚重象蹄 (Foot: 5x6x5)
                .texOffs(46, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 4.3 左后腿 (Left Hind Leg & Foot)
        PartDefinition legHL = body.addOrReplaceChild("leg_hind_left", CubeListBuilder.create()
                // 左后臀大腿 (Thigh: 5x6x5)
                .texOffs(66, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                // 金刚宝镯 (Vajra Bangle: 6x2x6)
                .texOffs(88, 92).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 2.0F, 6.0F),
                PartPose.offset(5.5F, 6.0F, 6.5F));
        legHL.addOrReplaceChild("foot_hind_left", CubeListBuilder.create()
                // 左后小腿与厚重象蹄 (Foot: 5x6x5)
                .texOffs(86, 64).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 4.4 右后腿 (Right Hind Leg & Foot)
        PartDefinition legHR = body.addOrReplaceChild("leg_hind_right", CubeListBuilder.create()
                // 右后臀大腿 (Thigh: 5x6x5)
                .texOffs(0, 68).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                // 金刚宝镯 (Vajra Bangle: 6x2x6)
                .texOffs(24, 93).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 2.0F, 6.0F),
                PartPose.offset(-5.5F, 6.0F, 6.5F));
        legHR.addOrReplaceChild("foot_hind_right", CubeListBuilder.create()
                // 右后小腿与厚重象蹄 (Foot: 5x6x5)
                .texOffs(20, 68).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // =========================================================================
        // 1. 沉稳威严的巨兽四肢踏步动力学 (Elephant Gait Kinematics)
        // =========================================================================
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        // 步态频率降低（配合巨兽体型），步履沉重震地
        float limbWalk = state.walkAnimationPos * 0.45F;

        // 对角步态周期相角
        float phaseFL = limbWalk;
        float phaseFR = limbWalk + (float) Math.PI;
        float phaseHL = limbWalk + (float) Math.PI;
        float phaseHR = limbWalk;

        // 大腿前后摆动
        this.legFrontLeft.xRot = Mth.cos(phaseFL) * 0.55F * walkSpeed;
        this.legFrontRight.xRot = Mth.cos(phaseFR) * 0.55F * walkSpeed;
        this.legHindLeft.xRot = Mth.cos(phaseHL) * 0.55F * walkSpeed;
        this.legHindRight.xRot = Mth.cos(phaseHR) * 0.55F * walkSpeed;

        // 大腿与小腿膝关节自然屈伸（大腿前摆时小腿微屈，踏地时撑直）
        this.footFrontLeft.xRot = Math.max(0.0F, -Mth.cos(phaseFL - 0.4F)) * 0.50F * walkSpeed;
        this.footFrontRight.xRot = Math.max(0.0F, -Mth.cos(phaseFR - 0.4F)) * 0.50F * walkSpeed;
        this.footHindLeft.xRot = Math.max(0.0F, -Mth.cos(phaseHL - 0.4F)) * 0.50F * walkSpeed;
        this.footHindRight.xRot = Math.max(0.0F, -Mth.cos(phaseHR - 0.4F)) * 0.50F * walkSpeed;

        // 行走时主躯干伴随左右微摇摆 (Roll zRot) 与沉重下压起伏 (Heave body.y)
        float breathe = Mth.sin(state.ageInTicks * 0.04F);
        float walkHeave = Mth.cos(limbWalk * 2.0F) * 0.8F * walkSpeed;
        this.body.y = 6.0F + walkHeave + breathe * 0.25F;
        this.body.zRot = Mth.sin(limbWalk) * 0.05F * walkSpeed;
        this.body.xRot = Mth.sin(limbWalk * 2.0F - 0.5F) * 0.02F * walkSpeed + breathe * 0.01F;

        // =========================================================================
        // 2. 象首注视巡视 (Head Yaw & Pitch Dynamics)
        // =========================================================================
        // 沉稳神象受首部自重，承受平缓阻尼的注视转动，带动六牙与长鼻巡视
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.65F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.65F;

        // =========================================================================
        // 3. 多节长鼻卷曲与甩拂 (Segmented Trunk Kinematics)
        // =========================================================================
        // 呼吸时向内微卷上扬，待机时长鼻左右轻拂探嗅，展现活灵活现的象鼻物理级联
        float breatheTrunk = Mth.sin(state.ageInTicks * 0.05F);
        this.trunkBase.xRot = -0.08F + breatheTrunk * 0.05F;
        this.trunkMid.xRot = -0.16F + Mth.sin(state.ageInTicks * 0.05F + 0.5F) * 0.08F;
        this.trunkTip.xRot = -0.28F + Mth.sin(state.ageInTicks * 0.05F + 1.0F) * 0.12F;

        // 待机左右探嗅轻拂 (Cascading Trunk Yaw Wave)
        float idleTrunkYaw = Mth.cos(state.ageInTicks * 0.035F);
        this.trunkBase.yRot = idleTrunkYaw * 0.10F;
        this.trunkMid.yRot = Mth.cos(state.ageInTicks * 0.035F - 0.4F) * 0.15F;
        this.trunkTip.yRot = Mth.cos(state.ageInTicks * 0.035F - 0.8F) * 0.22F;

        // 踏步走动时的象鼻惯性甩拂
        float trunkWalkSway = Mth.sin(limbWalk) * 0.08F * walkSpeed;
        this.trunkBase.zRot = trunkWalkSway * 0.5F;
        this.trunkMid.zRot = trunkWalkSway * 0.8F;
        this.trunkTip.zRot = trunkWalkSway * 1.2F;
        this.trunkBase.xRot += Mth.cos(limbWalk) * 0.08F * walkSpeed;
        this.trunkMid.xRot += Mth.cos(limbWalk - 0.3F) * 0.10F * walkSpeed;
        this.trunkTip.xRot += Mth.cos(limbWalk - 0.6F) * 0.14F * walkSpeed;

        // =========================================================================
        // 4. 双层大蒲扇耳煽动 (Ear Flapping Dynamics)
        // =========================================================================
        // 耳根与耳缘带有反相位延迟的柔和煽动波浪，如巨大蒲扇般扇起微风
        float earBaseWave = Mth.sin(state.ageInTicks * 0.055F);
        float earTipWave = Mth.sin(state.ageInTicks * 0.055F - 0.65F);

        this.leftEar.yRot = 0.22F + earBaseWave * 0.14F;
        this.leftEarTip.yRot = 0.08F + earTipWave * 0.16F;
        this.leftEar.zRot = -0.05F + earBaseWave * 0.03F;

        this.rightEar.yRot = -0.22F - earBaseWave * 0.14F;
        this.rightEarTip.yRot = -0.08F - earTipWave * 0.16F;
        this.rightEar.zRot = 0.05F - earBaseWave * 0.03F;

        // =========================================================================
        // 5. 尾部拂尘摇摆 (Tail Fly-Whisk Sway)
        // =========================================================================
        // 尾基与尾梢柔和摇摆，驱赶微尘
        float tailWave = Mth.sin(state.ageInTicks * 0.08F);
        float tailTipWave = Mth.sin(state.ageInTicks * 0.08F - 0.7F);

        this.tailBase.zRot = tailWave * 0.16F;
        this.tailTip.zRot = tailTipWave * 0.24F;
        this.tailBase.xRot = 0.15F + Mth.abs(tailWave) * 0.06F;
        this.tailTip.xRot = 0.10F + Mth.abs(tailTipWave) * 0.08F;
    }
}
