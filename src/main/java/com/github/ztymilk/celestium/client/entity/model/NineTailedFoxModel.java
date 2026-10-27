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
 * 九尾仙狐 (Nine-Tailed Celestial Spirit Fox)
 * <p>
 * 顶级树状多方块解剖体系 (Multi-Cube Anatomical Tree Architecture):
 * 1. 身躯体系 (body):
 *    - 主胸膛 (chest)、流线腰腹 (waist)、丰盈后跨 (hips)
 *    - 丰盈仙羽胸鬃 (layered fluffy chest ruff & side tufts)
 *    - 通灵宝玉佩与璎珞绦带 (jade amulet & silk tassel)
 * 2. 仙颈体系 (body -> neck):
 *    - 优雅修长昂立仙颈、蓬松如意祥云领鬃 (cloud collar & ruff)
 * 3. 仙首与五官 (neck -> head):
 *    - 灵狐仙颅 (refined cranium)、秀丽长吻与鼻镜 (snout & nose)、清秀下颌 (lower jaw)
 *    - 额点朱砂花钿与顶珠 (forehead cinnabar spirit mark)
 * 4. 灵动双耳 (head -> left_ear, right_ear):
 *    - 双段式结构：基段外壳与丰盈内耳绒毛 + 顶段紫尖灵焰角尖 (2-segment fluffy ears with inner fluff & tipped points)
 * 5. 四肢仙履 (body -> left_front_leg, right_front_leg, left_hind_leg, right_hind_leg):
 *    - 三段式解剖：上臂/大腿 + 前臂/飞节胫骨 + 紫云履足爪 (upper arm/thigh, forearm/hock, paws with claws & pads)
 * 6. 双层华美绽放九大仙尾 (body -> tail_base -> tail_0..tail_8):
 *    - 尾座 (tail_base) 呈祥云弧状承托九条仙尾
 *    - 9条仙尾每条均具备三段式柔韧解剖骨骼 (base -> mid -> tip)，呈莲花宝扇般向后上方浩瀚绽开
 *    - 动态模拟：调和正余弦谐波相移波浪，节段逐级滞后传递，宛如天河云霞翻滚摇曳！
 */
public class NineTailedFoxModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("nine_tailed_fox"), "main");

    // =========================================================================
    // 姿态基准常量 (Rest Pose Constants)
    // =========================================================================
    private static final float BASE_NECK_PITCH = -0.38F;
    private static final float BASE_HEAD_PITCH = 0.38F;

    private static final float BASE_EAR_L_PITCH = -0.15F;
    private static final float BASE_EAR_L_YAW = -0.12F;
    private static final float BASE_EAR_L_ROLL = 0.28F;

    private static final float BASE_EAR_R_PITCH = -0.15F;
    private static final float BASE_EAR_R_YAW = 0.12F;
    private static final float BASE_EAR_R_ROLL = -0.28F;

    // 9大仙尾基础扇面展布角 (Lotus Blossom Fan: 左右对称展开与高低起伏弧线)
    private static final float[] BASE_YAW = {
        -1.10F, -0.82F, -0.54F, -0.26F, 0.00F, 0.26F, 0.54F, 0.82F, 1.10F
    };
    private static final float[] BASE_PITCH = {
        -0.60F, -0.85F, -1.08F, -1.26F, -1.36F, -1.26F, -1.08F, -0.85F, -0.60F
    };
    private static final float[] BASE_ROLL = {
        -0.36F, -0.27F, -0.18F, -0.08F, 0.00F, 0.08F, 0.18F, 0.27F, 0.36F
    };

    // 仙尾根基在尾座上的弧状分布位点 (Curved mounting crest on tail_base)
    private static final float[] ROOT_X = {
        -2.2F, -1.8F, -1.4F, -0.8F, 0.0F, 0.8F, 1.4F, 1.8F, 2.2F
    };
    private static final float[] ROOT_Y = {
        0.8F, 0.2F, -0.4F, -1.0F, -1.4F, -1.0F, -0.4F, 0.2F, 0.8F
    };
    private static final float[] ROOT_Z = {
        0.5F, 0.9F, 1.3F, 1.7F, 2.0F, 1.7F, 1.3F, 0.9F, 0.5F
    };

    // =========================================================================
    // 骨骼部件层级 (Model Parts Hierarchy)
    // =========================================================================
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart leftEarTip;
    private final ModelPart rightEar;
    private final ModelPart rightEarTip;

    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontForearm;
    private final ModelPart leftFrontPaw;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontForearm;
    private final ModelPart rightFrontPaw;

    private final ModelPart leftHindLeg;
    private final ModelPart leftHindHock;
    private final ModelPart leftHindPaw;
    private final ModelPart rightHindLeg;
    private final ModelPart rightHindHock;
    private final ModelPart rightHindPaw;

    private final ModelPart tailBase;
    private final ModelPart[] tails = new ModelPart[9];
    private final ModelPart[] tailMids = new ModelPart[9];
    private final ModelPart[] tailTips = new ModelPart[9];

    public NineTailedFoxModel(ModelPart root) {
        super(root);

        // 1. 身躯与仙颈、仙首
        this.body = root.getChild("body");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");

        // 2. 双耳与耳尖
        this.leftEar = this.head.getChild("left_ear");
        this.leftEarTip = this.leftEar.getChild("left_ear_tip");
        this.rightEar = this.head.getChild("right_ear");
        this.rightEarTip = this.rightEar.getChild("right_ear_tip");

        // 3. 前肢三段关节
        this.leftFrontLeg = this.body.getChild("left_front_leg");
        this.leftFrontForearm = this.leftFrontLeg.getChild("left_front_forearm");
        this.leftFrontPaw = this.leftFrontForearm.getChild("left_front_paw");

        this.rightFrontLeg = this.body.getChild("right_front_leg");
        this.rightFrontForearm = this.rightFrontLeg.getChild("right_front_forearm");
        this.rightFrontPaw = this.rightFrontForearm.getChild("right_front_paw");

        // 4. 后肢三段关节
        this.leftHindLeg = this.body.getChild("left_hind_leg");
        this.leftHindHock = this.leftHindLeg.getChild("left_hind_hock");
        this.leftHindPaw = this.leftHindHock.getChild("left_hind_paw");

        this.rightHindLeg = this.body.getChild("right_hind_leg");
        this.rightHindHock = this.rightHindLeg.getChild("right_hind_hock");
        this.rightHindPaw = this.rightHindHock.getChild("right_hind_paw");

        // 5. 九大仙尾层级
        this.tailBase = this.body.getChild("tail_base");
        for (int i = 0; i < 9; i++) {
            this.tails[i] = this.tailBase.getChild("tail_" + i);
            this.tailMids[i] = this.tails[i].getChild("tail_" + i + "_mid");
            this.tailTips[i] = this.tailMids[i].getChild("tail_" + i + "_tip");
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 灵狐身躯与胸颈蓬松白毛 (Slender Torso, Ruff & Jade Amulet)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 主胸膛 (Muscular anterior ribcage)
                .texOffs(0, 0).addBox(-3.5F, -3.5F, -6.5F, 7.0F, 7.0F, 5.0F)
                // 流线腰腹 (Agile slender mid-body)
                .texOffs(25, 0).addBox(-3.0F, -3.0F, -1.5F, 6.0F, 6.0F, 5.0F)
                // 丰盈后臀 (Posterior hips & pelvis)
                .texOffs(48, 0).addBox(-3.0F, -3.2F, 3.5F, 6.0F, 7.0F, 5.0F)
                // 胸前丰盈仙毛基座 (Fluffy chest ruff layer 1)
                .texOffs(71, 0).addBox(-3.0F, -2.5F, -8.5F, 6.0F, 6.0F, 2.0F)
                // 胸前凸起毛簇 (Fluffy chest ruff layer 2)
                .texOffs(88, 0).addBox(-2.5F, -1.5F, -9.5F, 5.0F, 5.0F, 2.0F)
                // 左侧胸侧毛簇 (Left chest flank tuft)
                .texOffs(103, 0).addBox(3.2F, -2.0F, -7.0F, 1.0F, 4.0F, 4.0F)
                // 右侧胸侧毛簇 (Right chest flank tuft)
                .texOffs(114, 0).addBox(-4.2F, -2.0F, -7.0F, 1.0F, 4.0F, 4.0F)
                // 通灵宝玉佩 (Celestial Jade Pendant)
                .texOffs(0, 13).addBox(-1.5F, 0.5F, -9.9F, 3.0F, 3.0F, 1.0F)
                // 玉佩红锦流苏 (Crimson silk tassel)
                .texOffs(9, 13).addBox(-1.0F, 3.5F, -9.8F, 2.0F, 3.0F, 1.0F)
                // 金珀璎珞宝扣 (Golden knot fastening)
                .texOffs(16, 13).addBox(-1.0F, -0.5F, -9.8F, 2.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 13.0F, 0.0F));

        // =========================================================================
        // 2. 优雅仙颈 (Elegant S-curved Neck & Cloud Ruff)
        // =========================================================================
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                // 仙颈主干 (Neck column)
                .texOffs(0, 21).addBox(-2.0F, -5.0F, -2.5F, 4.0F, 6.0F, 5.0F)
                // 下部如意祥云领鬃 (Lower cloud collar ruff)
                .texOffs(19, 21).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F)
                // 上部迎风鬃毛 (Upper neck feathering)
                .texOffs(42, 21).addBox(-2.0F, -4.5F, -3.2F, 4.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -1.8F, -5.5F, BASE_NECK_PITCH, 0.0F, 0.0F));

        // =========================================================================
        // 3. 秀气狐首、尖长口吻与额前朱砂 (Head, Muzzle & Cinnabar Mark)
        // =========================================================================
        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                // 灵狐仙颅 (Fox Cranium)
                .texOffs(55, 13).addBox(-3.0F, -3.0F, -4.5F, 6.0F, 6.0F, 6.0F)
                // 左面颊仙髯 (Left cheek fluff)
                .texOffs(80, 13).addBox(2.8F, -1.0F, -3.5F, 1.0F, 3.0F, 4.0F)
                // 右面颊仙髯 (Right cheek fluff)
                .texOffs(91, 13).addBox(-3.8F, -1.0F, -3.5F, 1.0F, 3.0F, 4.0F)
                // 秀美尖长口吻基段 (Muzzle base)
                .texOffs(102, 13).addBox(-1.5F, 0.0F, -8.0F, 3.0F, 3.0F, 4.0F)
                // 吻尖与灵狐鼻镜 (Muzzle tip & nose pad)
                .texOffs(117, 13).addBox(-1.0F, 0.0F, -9.5F, 2.0F, 2.0F, 2.0F)
                // 清秀下颌 (Lower jaw)
                .texOffs(55, 26).addBox(-1.0F, 2.0F, -7.5F, 2.0F, 1.0F, 4.0F)
                // 额点朱砂花钿 (Forehead Cinnabar Petal Mark)
                .texOffs(68, 26).addBox(-1.0F, -3.5F, -4.8F, 2.0F, 2.0F, 1.0F)
                // 朱砂顶焰灵珠 (Cinnabar flame crown bead)
                .texOffs(75, 26).addBox(-0.5F, -4.2F, -4.7F, 1.0F, 1.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, BASE_HEAD_PITCH, 0.0F, 0.0F));

        // =========================================================================
        // 4. 双段式灵狐尖耳 (2-Segment Fluffy Fox Ears with Inner Fluff & Tipped Points)
        // =========================================================================
        // 左耳基段
        PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                // 耳壳基底 (Ear outer shell)
                .texOffs(0, 33).addBox(-1.2F, -4.5F, -1.0F, 3.0F, 5.0F, 2.0F)
                // 内耳绒毛 (Inner ear fluffy tuft)
                .texOffs(11, 33).addBox(-0.8F, -3.5F, -1.3F, 2.0F, 4.0F, 1.0F)
                // 侧耳蓬松外展毛 (Outer lateral fluff)
                .texOffs(18, 33).addBox(1.2F, -2.0F, -0.8F, 1.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(2.2F, -2.8F, -1.5F, BASE_EAR_L_PITCH, BASE_EAR_L_YAW, BASE_EAR_L_ROLL));

        // 左耳顶段灵尖 (Left ear tip)
        leftEar.addOrReplaceChild("left_ear_tip", CubeListBuilder.create()
                // 紫尖过渡段 (Tapered purple tip)
                .texOffs(23, 33).addBox(-1.0F, -2.5F, -0.8F, 2.0F, 3.0F, 1.0F)
                // 灵锐尖芒 (Needle point)
                .texOffs(30, 33).addBox(-0.5F, -3.5F, -0.5F, 1.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, -4.5F, 0.0F));

        // 右耳基段
        PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(35, 33).addBox(-1.8F, -4.5F, -1.0F, 3.0F, 5.0F, 2.0F, true)
                .texOffs(46, 33).addBox(-1.2F, -3.5F, -1.3F, 2.0F, 4.0F, 1.0F, true)
                .texOffs(53, 33).addBox(-2.2F, -2.0F, -0.8F, 1.0F, 3.0F, 1.0F, true),
                PartPose.offsetAndRotation(-2.2F, -2.8F, -1.5F, BASE_EAR_R_PITCH, BASE_EAR_R_YAW, BASE_EAR_R_ROLL));

        // 右耳顶段灵尖 (Right ear tip)
        rightEar.addOrReplaceChild("right_ear_tip", CubeListBuilder.create()
                .texOffs(58, 33).addBox(-1.0F, -2.5F, -0.8F, 2.0F, 3.0F, 1.0F, true)
                .texOffs(65, 33).addBox(-0.5F, -3.5F, -0.5F, 1.0F, 1.0F, 1.0F, true),
                PartPose.offset(0.0F, -4.5F, 0.0F));

        // =========================================================================
        // 5. 修长优雅四肢 (Three-Segmented Graceful Legs with Purple Cloud Socks)
        // =========================================================================
        // 前左肢 (Upper arm -> Forearm -> Paw)
        PartDefinition leftFrontLeg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                // 前上臂/肩胛 (Upper arm)
                .texOffs(0, 41).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offset(2.2F, 1.5F, -4.5F));

        PartDefinition leftFrontForearm = leftFrontLeg.addOrReplaceChild("left_front_forearm", CubeListBuilder.create()
                // 前臂 (Forearm)
                .texOffs(9, 41).addBox(-0.8F, 0.0F, -0.8F, 2.0F, 4.0F, 2.0F)
                // 腕部紫云履流云饰毛 (Wrist cloud crest)
                .texOffs(29, 41).addBox(-0.9F, 1.5F, 0.7F, 2.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 5.0F, 0.0F));

        leftFrontForearm.addOrReplaceChild("left_front_paw", CubeListBuilder.create()
                // 仙狐足掌与肉垫爪尖 (Paw pad & claws)
                .texOffs(18, 41).addBox(-1.0F, 0.0F, -1.6F, 2.0F, 1.0F, 3.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        // 前右肢
        PartDefinition rightFrontLeg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(36, 41).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, true),
                PartPose.offset(-2.2F, 1.5F, -4.5F));

        PartDefinition rightFrontForearm = rightFrontLeg.addOrReplaceChild("right_front_forearm", CubeListBuilder.create()
                .texOffs(45, 41).addBox(-1.2F, 0.0F, -0.8F, 2.0F, 4.0F, 2.0F, true)
                .texOffs(65, 41).addBox(-1.1F, 1.5F, 0.7F, 2.0F, 2.0F, 1.0F, true),
                PartPose.offset(0.0F, 5.0F, 0.0F));

        rightFrontForearm.addOrReplaceChild("right_front_paw", CubeListBuilder.create()
                .texOffs(54, 41).addBox(-1.0F, 0.0F, -1.6F, 2.0F, 1.0F, 3.0F, true),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        // 后左肢 (Muscular thigh -> Hock/shin -> Paw)
        PartDefinition leftHindLeg = body.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                // 后大腿丰满肌肉 (Muscular thigh)
                .texOffs(0, 49).addBox(-1.3F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                // 腿侧飘逸鬃毛 (Haunch flank tuft)
                .texOffs(33, 49).addBox(1.3F, 0.5F, -1.0F, 1.0F, 4.0F, 2.0F),
                PartPose.offset(2.2F, 1.5F, 5.5F));

        PartDefinition leftHindHock = leftHindLeg.addOrReplaceChild("left_hind_hock", CubeListBuilder.create()
                // 飞节与下胫骨 (Hock & lower shin)
                .texOffs(13, 49).addBox(-0.8F, 0.0F, -1.2F, 2.0F, 4.0F, 2.0F),
                PartPose.offset(0.0F, 5.0F, 0.8F));

        leftHindHock.addOrReplaceChild("left_hind_paw", CubeListBuilder.create()
                // 后爪 (Hind paw)
                .texOffs(22, 49).addBox(-1.0F, 0.0F, -1.6F, 2.0F, 1.0F, 3.0F),
                PartPose.offset(0.0F, 4.0F, -0.5F));

        // 后右肢
        PartDefinition rightHindLeg = body.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(40, 49).addBox(-1.7F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, true)
                .texOffs(73, 49).addBox(-2.3F, 0.5F, -1.0F, 1.0F, 4.0F, 2.0F, true),
                PartPose.offset(-2.2F, 1.5F, 5.5F));

        PartDefinition rightHindHock = rightHindLeg.addOrReplaceChild("right_hind_hock", CubeListBuilder.create()
                .texOffs(53, 49).addBox(-1.2F, 0.0F, -1.2F, 2.0F, 4.0F, 2.0F, true),
                PartPose.offset(0.0F, 5.0F, 0.8F));

        rightHindHock.addOrReplaceChild("right_hind_paw", CubeListBuilder.create()
                .texOffs(62, 49).addBox(-1.0F, 0.0F, -1.6F, 2.0F, 1.0F, 3.0F, true),
                PartPose.offset(0.0F, 4.0F, -0.5F));

        // =========================================================================
        // 6. 尾座与浩瀚九大仙尾 (Sacral Tail Base & 9 Articulated Billowing Tails)
        // =========================================================================
        PartDefinition tailBase = body.addOrReplaceChild("tail_base", CubeListBuilder.create()
                // 尾座通灵基台 (Sacral spirit foundation)
                .texOffs(23, 13).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 3.0F)
                // 尾根祥云簇毛 (Tail base cloud fluff)
                .texOffs(38, 13).addBox(-2.5F, -1.5F, 0.5F, 5.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, -1.2F, 8.0F));

        // 9条仙尾：每条独立3段关节 (Base -> Mid -> Flame Tip)
        for (int i = 0; i < 9; i++) {
            int col = i % 2;
            int row = i / 2;
            int uBase = (col == 0) ? 0 : 64;
            int vBase = 59 + row * 11;

            // 仙尾根段 (Snow-white Tail Base)
            PartDefinition tail = tailBase.addOrReplaceChild("tail_" + i, CubeListBuilder.create()
                    .texOffs(uBase, vBase).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F),
                    PartPose.offsetAndRotation(ROOT_X[i], ROOT_Y[i], ROOT_Z[i], BASE_PITCH[i], BASE_YAW[i], BASE_ROLL[i]));

            // 仙尾中段 (Billowing Cloud Silk Mid-segment)
            PartDefinition tailMid = tail.addOrReplaceChild("tail_" + i + "_mid", CubeListBuilder.create()
                    .texOffs(uBase + 19, vBase).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 6.0F),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, -0.15F, 0.0F, 0.0F));

            // 仙尾顶段 (Violet Flame Crest Tip)
            tailMid.addOrReplaceChild("tail_" + i + "_tip", CubeListBuilder.create()
                    .texOffs(uBase + 40, vBase).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 7.0F),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, -0.20F, 0.0F, 0.0F));
        }

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        float headYaw = state.yRot * ((float) Math.PI / 180F);
        float headPitch = state.xRot * ((float) Math.PI / 180F);

        // =========================================================================
        // 1. 灵狐呼吸律动与身躯浮沉 (Breathing Motion & Body Oscillation)
        // =========================================================================
        float breath = Mth.sin(age * 0.06F);
        float breathY = breath * 0.20F;
        float breathPitch = breath * 0.015F;

        // 躯干行走微弹与轻盈起伏
        this.body.y = 13.0F + Mth.sin(walkPos * 1.3324F) * 0.5F * walkSpeed + breathY;
        this.body.xRot = Mth.sin(walkPos * 1.3324F) * 0.025F * walkSpeed + breathPitch;
        this.body.zRot = Mth.cos(walkPos * 0.6662F) * 0.03F * walkSpeed;

        // =========================================================================
        // 2. 灵动四肢仙履漫步 (Legs & Paws Stepping with Realistic Joint Flexing)
        // =========================================================================
        float flSwing = Mth.cos(walkPos * 0.6662F);
        float frSwing = Mth.cos(walkPos * 0.6662F + (float) Math.PI);

        // 左前肢 (上臂摆动 + 前臂后曲 + 爪部回弹缓冲)
        this.leftFrontLeg.xRot = flSwing * 0.65F * walkSpeed;
        this.leftFrontForearm.xRot = Math.max(0.0F, flSwing) * 0.55F * walkSpeed;
        this.leftFrontPaw.xRot = -this.leftFrontForearm.xRot * 0.5F;

        // 右前肢
        this.rightFrontLeg.xRot = frSwing * 0.65F * walkSpeed;
        this.rightFrontForearm.xRot = Math.max(0.0F, frSwing) * 0.55F * walkSpeed;
        this.rightFrontPaw.xRot = -this.rightFrontForearm.xRot * 0.5F;

        // 左后肢 (对角配对: 与右前肢同步，后腿屈伸自然向前弯曲飞节)
        this.leftHindLeg.xRot = frSwing * 0.65F * walkSpeed;
        this.leftHindHock.xRot = Math.max(0.0F, -frSwing) * 0.55F * walkSpeed;
        this.leftHindPaw.xRot = -this.leftHindHock.xRot * 0.5F;

        // 右后肢 (对角配对: 与左前肢同步)
        this.rightHindLeg.xRot = flSwing * 0.65F * walkSpeed;
        this.rightHindHock.xRot = Math.max(0.0F, -flSwing) * 0.55F * walkSpeed;
        this.rightHindPaw.xRot = -this.rightHindHock.xRot * 0.5F;

        // =========================================================================
        // 3. 仙颈与仙首点头环视 (Neck & Head Look Tracking)
        // =========================================================================
        float idleNod = Mth.sin(age * 0.06F) * 0.04F;
        float walkNod = Mth.cos(walkPos * 0.6662F) * 0.08F * walkSpeed;

        // 颈项柔和跟随
        this.neck.xRot = BASE_NECK_PITCH + headPitch * 0.30F + idleNod * 0.4F + walkNod * 0.4F;
        this.neck.yRot = headYaw * 0.35F;

        // 狐首精准环视与灵动微倾
        this.head.xRot = BASE_HEAD_PITCH + headPitch * 0.70F + idleNod * 0.6F + walkNod * 0.5F;
        this.head.yRot = headYaw * 0.65F;
        this.head.zRot = headYaw * 0.08F; // 灵狐特有的歪头灵动感

        // =========================================================================
        // 4. 狐耳警觉轻抖 (Ear Subtle Twitching / Flicking)
        // =========================================================================
        // 周期性快速抖耳脉冲（模拟灵兽倾听八荒仙音）
        float earFlick1 = (float) Math.pow(Math.max(0.0, Math.sin(age * 0.075F)), 8.0) * Mth.sin(age * 0.9F) * 0.22F;
        float earFlick2 = (float) Math.pow(Math.max(0.0, Math.sin(age * 0.06F + 2.0F)), 8.0) * Mth.sin(age * 0.85F) * 0.20F;

        this.leftEar.xRot = BASE_EAR_L_PITCH + Mth.cos(age * 0.06F) * 0.02F;
        this.leftEar.yRot = BASE_EAR_L_YAW;
        this.leftEar.zRot = BASE_EAR_L_ROLL + Mth.sin(age * 0.07F) * 0.025F + earFlick1;
        this.leftEarTip.zRot = earFlick1 * 0.5F;

        this.rightEar.xRot = BASE_EAR_R_PITCH + Mth.cos(age * 0.06F + 1.0F) * 0.02F;
        this.rightEar.yRot = BASE_EAR_R_YAW;
        this.rightEar.zRot = BASE_EAR_R_ROLL - Mth.sin(age * 0.07F + 1.0F) * 0.025F - earFlick2;
        this.rightEarTip.zRot = -earFlick2 * 0.5F;

        // =========================================================================
        // 5. 九尾如锦缎翻滚与祥云绽放 (Billowing Wave Across All 9 Tails)
        // =========================================================================
        // 灵狐祥云扇面呼吸开合
        float blossom = Mth.sin(age * 0.04F) * 0.08F;
        float fanYawSpread = 1.0F + blossom;
        // 行走时身尾共振摇摆
        float walkSway = Mth.sin(walkPos * 0.6662F) * 0.12F * walkSpeed;

        for (int i = 0; i < 9; i++) {
            float tailPhase = age * 0.08F + i * 0.42F;

            // 根段 (Base segment): 形成主扇面与基准起伏
            float waveY1 = Mth.sin(tailPhase) * 0.10F + walkSway * 0.4F;
            float waveX1 = Mth.cos(tailPhase * 0.85F) * 0.08F;
            float waveZ1 = Mth.sin(tailPhase * 0.70F) * 0.06F;

            this.tails[i].xRot = BASE_PITCH[i] + waveX1;
            this.tails[i].yRot = BASE_YAW[i] * fanYawSpread + waveY1;
            this.tails[i].zRot = BASE_ROLL[i] + waveZ1;

            // 中段 (Mid segment): 柔韧滞后波动，如水波逐浪
            float midPhase = tailPhase - 0.55F;
            float waveY2 = Mth.sin(midPhase) * 0.16F + walkSway * 0.7F;
            float waveX2 = Mth.cos(midPhase * 0.85F) * 0.14F;
            float waveZ2 = Mth.sin(midPhase * 0.70F) * 0.10F;

            this.tailMids[i].xRot = -0.15F + waveX2;
            this.tailMids[i].yRot = waveY2;
            this.tailMids[i].zRot = waveZ2;

            // 尾尖紫焰翎华 (Tip segment): 灵动鞭击飘拂与如火跳跃
            float tipPhase = tailPhase - 1.10F;
            float waveY3 = Mth.sin(tipPhase) * 0.22F + walkSway * 1.0F;
            float waveX3 = Mth.cos(tipPhase * 0.85F) * 0.18F;
            float waveZ3 = Mth.sin(tipPhase * 0.70F) * 0.14F;

            this.tailTips[i].xRot = -0.20F + waveX3;
            this.tailTips[i].yRot = waveY3;
            this.tailTips[i].zRot = waveZ3;
        }
    }
}
