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
 * 辟邪祥瑞·白泽 (Bai Ze, 通晓万物的祥瑞天尊神兽 - "神兽构思师"树状解剖体系)
 * 羊首狮身，通体雪白带金纹，额生通天天眼神印，脑后生九曲玄金大盘羊角，颌下悬飘逸三段长髯仙须，
 * 颈围祥云鬃圈，双肩斜披如意祥云帔帛仙氅，四足踏祥云瑞霭，尾若如意拂尘灵动飘摇。
 */
public class BaiZeModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("bai_ze"), "main");

    // 躯干与肩帔
    private final ModelPart body;
    private final ModelPart cloudStoleLeft;
    private final ModelPart cloudStoleRight;

    // 仙首与五官角耳胡须
    private final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart leftHorn;
    private final ModelPart rightHorn;
    private final ModelPart beardUpper;
    private final ModelPart beardMid;
    private final ModelPart beardTip;

    // 灵拂狮尾
    private final ModelPart tailBase;
    private final ModelPart tailMid;
    private final ModelPart tailTip;

    // 四蹄踏瑞霭
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontLower;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontLower;
    private final ModelPart leftHindLeg;
    private final ModelPart leftHindLower;
    private final ModelPart rightHindLeg;
    private final ModelPart rightHindLower;

    public BaiZeModel(ModelPart root) {
        super(root);

        // 躯干主干与肩部帔帛
        this.body = root.getChild("body");
        this.cloudStoleLeft = this.body.getChild("cloud_stole_left");
        this.cloudStoleRight = this.body.getChild("cloud_stole_right");

        // 仙首、仙耳、玄金双角与三段仙髯
        this.head = this.body.getChild("head");
        this.leftEar = this.head.getChild("left_ear");
        this.rightEar = this.head.getChild("right_ear");
        this.leftHorn = this.head.getChild("left_horn");
        this.rightHorn = this.head.getChild("right_horn");
        this.beardUpper = this.head.getChild("beard_upper");
        this.beardMid = this.beardUpper.getChild("beard_mid");
        this.beardTip = this.beardMid.getChild("beard_tip");

        // 三段式灵拂狮尾
        this.tailBase = this.body.getChild("tail_base");
        this.tailMid = this.tailBase.getChild("tail_mid");
        this.tailTip = this.tailMid.getChild("tail_tip");

        // 四肢与踏云足部
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.leftFrontLower = this.leftFrontLeg.getChild("left_front_lower");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.rightFrontLower = this.rightFrontLeg.getChild("right_front_lower");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.leftHindLower = this.leftHindLeg.getChild("left_hind_lower");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.rightHindLower = this.rightHindLeg.getChild("right_hind_lower");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 狮身仙氅 (Snow-white Leonine Torso, Muscular Ribcage & Cloud Mantle)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 雄健狮身胸膛 (Muscular Chest & Ribcage)
                .texOffs(0, 0).addBox(-5.5F, -5.5F, -10.0F, 11.0F, 11.0F, 10.0F)
                // 健美腰腹后臀 (Haunches & Abdomen)
                .texOffs(0, 22).addBox(-4.5F, -5.0F, 0.0F, 9.0F, 10.0F, 10.0F)
                // 肩甲与背脊披风基座 (Shoulder stole transverse base)
                .texOffs(0, 43).addBox(-6.5F, -6.0F, -7.0F, 13.0F, 2.0F, 4.0F)
                // 脊背祥云神脊 (Dorsal Spine Fur Crest)
                .texOffs(0, 50).addBox(-1.0F, -7.0F, -8.0F, 2.0F, 2.0F, 16.0F)
                // 咽喉祥云垂肉 (Throat Cloud Dewlap)
                .texOffs(0, 69).addBox(-3.0F, 4.5F, -10.5F, 6.0F, 3.0F, 3.0F)
                // 颈部祥云鬃领环 (Neck Cloud Collar Base Ring)
                .texOffs(71, 0).addBox(-6.0F, -6.5F, -12.0F, 12.0F, 10.0F, 3.0F)
                // 左侧祥云鬃领毛簇 (Left Neck Cloud Tuft)
                .texOffs(103, 0).addBox(5.5F, -6.0F, -12.5F, 2.0F, 8.0F, 4.0F)
                // 右侧祥云鬃领毛簇 (Right Neck Cloud Tuft)
                .texOffs(103, 13).addBox(-7.5F, -6.0F, -12.5F, 2.0F, 8.0F, 4.0F),
                PartPose.offset(0.0F, 11.0F, 0.0F));

        // 左右如意祥云帔帛仙氅 (Cloud Stole / Floating Shoulder Cape Ribbons)
        body.addOrReplaceChild("cloud_stole_left", CubeListBuilder.create()
                .texOffs(93, 35).addBox(0.0F, 0.0F, -1.5F, 1.0F, 7.0F, 3.0F)
                .texOffs(102, 35).addBox(0.5F, 5.0F, -1.0F, 1.0F, 6.0F, 5.0F)
                .texOffs(93, 47).addBox(0.0F, 8.5F, 2.5F, 2.0F, 4.0F, 4.0F),
                PartPose.offset(6.0F, -4.5F, -5.0F));

        body.addOrReplaceChild("cloud_stole_right", CubeListBuilder.create()
                .texOffs(66, 54).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 7.0F, 3.0F)
                .texOffs(75, 54).addBox(-1.5F, 5.0F, -1.0F, 1.0F, 6.0F, 5.0F)
                .texOffs(88, 56).addBox(-2.0F, 8.5F, 2.5F, 2.0F, 4.0F, 4.0F),
                PartPose.offset(-6.0F, -4.5F, -5.0F));

        // =========================================================================
        // 2. 仙首与五官 (Gentle/Majestic Caprine-Leonine Skull, Snout, 3rd Eye & Mane)
        // =========================================================================
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 优雅仙颈 (Graceful Arched Neck)
                .texOffs(44, 0).addBox(-3.5F, -5.0F, -5.0F, 7.0F, 8.0F, 6.0F)
                // 仙兽颅骨 (Noble Cranium)
                .texOffs(44, 15).addBox(-3.5F, -9.5F, -11.0F, 7.0F, 7.0F, 7.0F)
                // 额心神纹隆起 (Forehead Brow Ridge)
                .texOffs(44, 30).addBox(-2.0F, -10.5F, -10.5F, 4.0F, 2.0F, 3.0F)
                // 额心第三天眼/宝珠 (Forehead 3rd Celestial Eye / Gem)
                .texOffs(59, 30).addBox(-1.0F, -9.0F, -11.5F, 2.0F, 3.0F, 1.0F)
                // 修长灵秀吻部上颌 (Slender Upper Snout)
                .texOffs(73, 15).addBox(-2.5F, -6.5F, -16.0F, 5.0F, 4.0F, 6.0F)
                // 吻部下颚 (Lower Jaw)
                .texOffs(73, 26).addBox(-2.0F, -3.0F, -15.5F, 4.0F, 2.0F, 5.0F)
                // 左侧丰润颊毛 (Left Cheek Tuft)
                .texOffs(92, 26).addBox(3.2F, -7.0F, -9.0F, 2.0F, 4.0F, 4.0F)
                // 右侧丰润颊毛 (Right Cheek Tuft)
                .texOffs(105, 26).addBox(-5.2F, -7.0F, -9.0F, 2.0F, 4.0F, 4.0F),
                PartPose.offset(0.0F, -3.0F, -9.0F));

        // 垂云仙耳 (Drooping Celestial Ears)
        head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                .texOffs(66, 35).addBox(0.0F, 0.0F, -1.5F, 4.0F, 2.0F, 3.0F)
                .texOffs(81, 35).addBox(3.0F, 1.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(3.5F, -7.5F, -6.5F, 0.2F, -0.25F, 0.45F));

        head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(66, 41).addBox(-4.0F, 0.0F, -1.5F, 4.0F, 2.0F, 3.0F)
                .texOffs(81, 41).addBox(-5.0F, 1.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(-3.5F, -7.5F, -6.5F, 0.2F, 0.25F, -0.45F));

        // 九曲玄金大盘羊角 (Grand Segmented Curved Golden Ram Horns)
        // 左角 (Left Horn)
        PartDefinition leftHorn = head.addOrReplaceChild("left_horn", CubeListBuilder.create()
                .texOffs(40, 36).addBox(-1.0F, -5.0F, -1.5F, 3.0F, 5.0F, 3.0F),
                PartPose.offsetAndRotation(2.2F, -9.5F, -7.5F, -0.4F, 0.25F, 0.35F));
        PartDefinition leftHornMid = leftHorn.addOrReplaceChild("left_horn_mid", CubeListBuilder.create()
                .texOffs(40, 45).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.5F, -5.0F, 0.0F, -0.5F, 0.15F, 0.35F));
        leftHornMid.addOrReplaceChild("left_horn_tip", CubeListBuilder.create()
                .texOffs(40, 53).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -4.5F, 0.0F, -0.55F, -0.2F, -0.25F));

        // 右角 (Right Horn)
        PartDefinition rightHorn = head.addOrReplaceChild("right_horn", CubeListBuilder.create()
                .texOffs(53, 36).addBox(-2.0F, -5.0F, -1.5F, 3.0F, 5.0F, 3.0F),
                PartPose.offsetAndRotation(-2.2F, -9.5F, -7.5F, -0.4F, -0.25F, -0.35F));
        PartDefinition rightHornMid = rightHorn.addOrReplaceChild("right_horn_mid", CubeListBuilder.create()
                .texOffs(49, 45).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, -5.0F, 0.0F, -0.5F, -0.15F, -0.35F));
        rightHornMid.addOrReplaceChild("right_horn_tip", CubeListBuilder.create()
                .texOffs(49, 53).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -4.5F, 0.0F, -0.55F, 0.2F, 0.25F));

        // 飘逸三段长髯仙须 (Flowing Triple-Section Celestial Chin Beard)
        PartDefinition beardUpper = head.addOrReplaceChild("beard_upper", CubeListBuilder.create()
                .texOffs(66, 47).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -1.5F, -13.5F, 0.15F, 0.0F, 0.0F));
        PartDefinition beardMid = beardUpper.addOrReplaceChild("beard_mid", CubeListBuilder.create()
                .texOffs(77, 47).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.1F, 0.0F, 0.0F));
        beardMid.addOrReplaceChild("beard_tip", CubeListBuilder.create()
                .texOffs(86, 47).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.15F, 0.0F, 0.0F));

        // =========================================================================
        // 3. 灵拂狮尾 (Voluminous Ruyi Cloud-Puff Articulated Tail)
        // =========================================================================
        PartDefinition tailBase = body.addOrReplaceChild("tail_base", CubeListBuilder.create()
                .texOffs(0, 76).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -2.5F, 9.5F, -0.35F, 0.0F, 0.0F));
        PartDefinition tailMid = tailBase.addOrReplaceChild("tail_mid", CubeListBuilder.create()
                .texOffs(0, 86).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 5.5F, 0.2F, 0.0F, 0.0F));
        tailMid.addOrReplaceChild("tail_tip", CubeListBuilder.create()
                // 如意祥云尾帚主核 (Voluminous Cloud Brush Core)
                .texOffs(19, 76).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 7.0F)
                // 顶端如意云冠 (Top Ruyi Crest)
                .texOffs(19, 89).addBox(-2.0F, -4.5F, 1.5F, 4.0F, 2.0F, 5.0F)
                // 左右如意侧卷 (Left & Right Flanking Cloud Swirls)
                .texOffs(38, 89).addBox(2.2F, -1.5F, 1.5F, 1.0F, 3.0F, 4.0F)
                .texOffs(0, 95).addBox(-3.2F, -1.5F, 1.5F, 1.0F, 3.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 5.5F, 0.2F, 0.0F, 0.0F));

        // =========================================================================
        // 4. 四肢四蹄踏瑞霭 (Four Muscular Legs with Golden Hooves & Cloud Puffs)
        // =========================================================================
        // 左前肢 (Left Front Leg)
        PartDefinition legFL = root.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                .texOffs(49, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F),
                PartPose.offset(4.2F, 12.0F, -6.0F));
        legFL.addOrReplaceChild("left_front_lower", CubeListBuilder.create()
                .texOffs(66, 66).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                .texOffs(79, 66).addBox(-2.0F, 4.5F, -2.0F, 4.0F, 2.0F, 4.0F)
                .texOffs(96, 66).addBox(-2.5F, 2.5F, -2.5F, 5.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 右前肢 (Right Front Leg)
        PartDefinition legFR = root.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(49, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F),
                PartPose.offset(-4.2F, 12.0F, -6.0F));
        legFR.addOrReplaceChild("right_front_lower", CubeListBuilder.create()
                .texOffs(66, 77).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                .texOffs(79, 77).addBox(-2.0F, 4.5F, -2.0F, 4.0F, 2.0F, 4.0F)
                .texOffs(96, 77).addBox(-2.5F, 2.5F, -2.5F, 5.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 左后肢 (Left Hind Leg)
        PartDefinition legBL = root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                .texOffs(49, 88).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 6.0F, 5.0F),
                PartPose.offset(4.0F, 12.0F, 6.0F));
        legBL.addOrReplaceChild("left_hind_lower", CubeListBuilder.create()
                .texOffs(68, 88).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                .texOffs(81, 88).addBox(-2.0F, 4.5F, -2.0F, 4.0F, 2.0F, 4.0F)
                .texOffs(98, 88).addBox(-2.5F, 2.5F, -2.5F, 5.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 右后肢 (Right Hind Leg)
        PartDefinition legBR = root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(49, 100).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 6.0F, 5.0F),
                PartPose.offset(-4.0F, 12.0F, 6.0F));
        legBR.addOrReplaceChild("right_hind_lower", CubeListBuilder.create()
                .texOffs(68, 100).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                .texOffs(81, 100).addBox(-2.0F, 4.5F, -2.0F, 4.0F, 2.0F, 4.0F)
                .texOffs(98, 100).addBox(-2.5F, 2.5F, -2.5F, 5.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        float age = state.ageInTicks;

        // 1. 仙首顾盼与祥瑞吞吐呼吸 (Gentle breathing, looking & serene tilting)
        float headPitch = state.xRot * ((float) Math.PI / 180F) * 0.6F;
        float headYaw = state.yRot * ((float) Math.PI / 180F) * 0.6F;
        float breathCycle = age * 0.05F;
        float breathSin = Mth.sin(breathCycle);
        float breathCos = Mth.cos(breathCycle);

        this.head.xRot = headPitch + 0.05F + breathSin * 0.035F;
        this.head.yRot = headYaw + breathCos * 0.02F;
        this.head.zRot = breathCos * 0.018F;

        // 垂云仙耳轻灵微颤 (Gentle ear droop and subtle flutter)
        this.leftEar.zRot = 0.45F + Mth.sin(age * 0.07F) * 0.04F;
        this.rightEar.zRot = -0.45F - Mth.sin(age * 0.07F + 0.5F) * 0.04F;

        // 2. 仙躯微浪律动与端庄步态 (Serene, dignified walking gait & body motion)
        float walkPhase = walkPos * 0.6662F;
        this.body.y = 11.0F + Mth.sin(walkPos * 1.3324F) * 0.4F * walkSpeed + breathSin * 0.3F;
        this.body.zRot = Mth.cos(walkPhase) * 0.025F * walkSpeed;

        // 四蹄对角漫步 (Diagonal quadruped gait)
        this.leftFrontLeg.xRot = Mth.cos(walkPhase) * 0.65F * walkSpeed;
        this.rightFrontLeg.xRot = Mth.cos(walkPhase + (float) Math.PI) * 0.65F * walkSpeed;
        this.leftHindLeg.xRot = Mth.cos(walkPhase + (float) Math.PI) * 0.65F * walkSpeed;
        this.rightHindLeg.xRot = Mth.cos(walkPhase) * 0.65F * walkSpeed;

        // 膝关节顺应屈伸 (Natural knee/joint flexion for dignified elegance)
        this.leftFrontLower.xRot = Math.max(0.0F, Mth.sin(walkPhase) * 0.38F) * walkSpeed;
        this.rightFrontLower.xRot = Math.max(0.0F, Mth.sin(walkPhase + (float) Math.PI) * 0.38F) * walkSpeed;
        this.leftHindLower.xRot = Math.max(0.0F, -Mth.sin(walkPhase + (float) Math.PI) * 0.32F) * walkSpeed;
        this.rightHindLower.xRot = Math.max(0.0F, -Mth.sin(walkPhase) * 0.32F) * walkSpeed;

        // 3. 灵拂狮尾优雅三段摆动 (Graceful swaying tail kinematic chain)
        float tailWave = age * 0.08F + walkPos * 0.28F;
        this.tailBase.xRot = -0.35F + Mth.cos(tailWave * 0.8F) * 0.05F;
        this.tailBase.yRot = Mth.sin(tailWave) * 0.18F;

        this.tailMid.xRot = 0.15F + Mth.cos(tailWave * 0.8F - 0.3F) * 0.06F;
        this.tailMid.yRot = Mth.sin(tailWave - 0.4F) * 0.24F;

        this.tailTip.xRot = 0.18F + Mth.cos(tailWave * 0.8F - 0.6F) * 0.08F;
        this.tailTip.yRot = Mth.sin(tailWave - 0.8F) * 0.32F;
        this.tailTip.zRot = Mth.cos(tailWave - 0.8F) * 0.10F;

        // 4. 三段长髯仙须随风飘拂 (Triple-section celestial beard fluttering in wind)
        float beardWave = age * 0.09F + walkPos * 0.22F;
        this.beardUpper.xRot = 0.12F + Mth.sin(beardWave) * 0.08F + walkSpeed * 0.14F;
        this.beardUpper.zRot = Mth.cos(beardWave * 0.7F) * 0.03F;

        this.beardMid.xRot = 0.12F + Mth.sin(beardWave - 0.35F) * 0.12F + walkSpeed * 0.18F;
        this.beardMid.zRot = Mth.cos(beardWave * 0.7F - 0.3F) * 0.05F;

        this.beardTip.xRot = 0.15F + Mth.sin(beardWave - 0.7F) * 0.16F + walkSpeed * 0.22F;
        this.beardTip.zRot = Mth.cos(beardWave * 0.7F - 0.6F) * 0.08F;

        // 5. 祥云帔帛仙氅拂动飘逸 (Cloud stole ribbons floating in celestial wind)
        float stoleWave = age * 0.075F + walkPos * 0.25F;
        this.cloudStoleLeft.xRot = 0.12F + Mth.sin(stoleWave) * 0.10F + walkSpeed * 0.22F;
        this.cloudStoleLeft.yRot = -0.05F + Mth.sin(stoleWave * 0.6F) * 0.05F;
        this.cloudStoleLeft.zRot = 0.08F + Mth.cos(stoleWave * 0.8F) * 0.06F;

        this.cloudStoleRight.xRot = 0.12F + Mth.sin(stoleWave + 0.35F) * 0.10F + walkSpeed * 0.22F;
        this.cloudStoleRight.yRot = 0.05F - Mth.sin(stoleWave * 0.6F) * 0.05F;
        this.cloudStoleRight.zRot = -0.08F - Mth.cos(stoleWave * 0.8F) * 0.06F;
    }
}
