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
 * 丹顶仙鹤 (Celestial Crane / Red-Crowned Divine Crane)
 * "神兽构思师" 树状解剖体系 (Anatomical Tree Architecture):
 * 1. 丹顶长颈: 修长玄黑尖喙、朱砂丹顶赤红肉冠、双关节优美 S 弯鹤颈 (下颈节与上颈节)
 * 2. 仙躯玄尾: 纯白流线仙骨躯干、前胸龙骨流线、背腹羽层、玄青蓬松舒展长翎尾羽与披垂覆羽
 * 3. 舒展仙翼: 双关节大展翅 (内翼骨骼覆羽 + 外翼展开飞羽面 + 多层玄青初级飞羽尖)
 * 4. 涉水长足: 鹤胫羽衣大腿 + 鹤膝节瘤 + 超长涉水黑骨细腿 + 涉水四爪 (三前一后交泰爪)
 */
public class CelestialCraneModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("celestial_crane"), "main");

    // 树状解剖体系核心模型部件 (Anatomical Tree ModelParts)
    private final ModelPart body;
    private final ModelPart neckLower;
    private final ModelPart neckUpper;
    private final ModelPart head;
    private final ModelPart leftWing;
    private final ModelPart leftWingOuter;
    private final ModelPart rightWing;
    private final ModelPart rightWingOuter;
    private final ModelPart tail;
    private final ModelPart leftLeg;
    private final ModelPart leftShin;
    private final ModelPart rightLeg;
    private final ModelPart rightShin;

    public CelestialCraneModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.neckLower = this.body.getChild("neck_lower");
        this.neckUpper = this.neckLower.getChild("neck_upper");
        this.head = this.neckUpper.getChild("head");
        this.leftWing = this.body.getChild("left_wing");
        this.leftWingOuter = this.leftWing.getChild("left_wing_outer");
        this.rightWing = this.body.getChild("right_wing");
        this.rightWingOuter = this.rightWing.getChild("right_wing_outer");
        this.tail = this.body.getChild("tail");
        this.leftLeg = root.getChild("left_leg");
        this.leftShin = this.leftLeg.getChild("left_shin");
        this.rightLeg = root.getChild("right_leg");
        this.rightShin = this.rightLeg.getChild("right_shin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 仙鹤流线型躯干 (Streamlined Avian Torso)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 仙鹤纯白流线主躯干 (Slender aerodynamic white torso core: 7x7x13)
                .texOffs(0, 0).addBox(-3.5F, -3.5F, -6.5F, 7.0F, 7.0F, 13.0F)
                // 丰盈前胸羽与龙骨突 (Plump chest plumage & breast keel: 6x5x3)
                .texOffs(42, 0).addBox(-3.0F, -2.5F, -8.5F, 6.0F, 5.0F, 3.0F)
                // 背部流线羽层与鞍部微隆 (Dorsal slope plumage coverts: 6x2x9)
                .texOffs(0, 21).addBox(-3.0F, -4.2F, -4.0F, 6.0F, 2.0F, 9.0F)
                // 腹部收束流线羽 (Underbelly contour coverts: 5x1x7)
                .texOffs(32, 21).addBox(-2.5F, 3.2F, -2.0F, 5.0F, 1.0F, 7.0F),
                PartPose.offset(0.0F, 9.5F, 0.0F));

        // =========================================================================
        // 2. 蓬松玄青舒展尾羽 (Elegant Bustling Tail Coverts & Drooping Secondary Plumes)
        // 丹顶鹤标志性的玄青尾羽实为三级飞羽折叠后垂覆于尾部形成的蓬松披风状长翎
        // =========================================================================
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                // 纯白基底覆羽 (Base white tail coverts)
                .texOffs(64, 0).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 3.0F, 6.0F)
                // 中央玄青垂落主翎 (Central cascading black drooping plumes)
                .texOffs(88, 0).addBox(-3.0F, 0.2F, 1.5F, 6.0F, 4.0F, 9.0F)
                // 左侧舒展外扬垂羽 (Left billowing drooping plume)
                .texOffs(64, 11).addBox(1.5F, 0.8F, 2.5F, 3.0F, 4.0F, 8.0F)
                // 右侧舒展外扬垂羽 (Right billowing drooping plume)
                .texOffs(88, 15).addBox(-4.5F, 0.8F, 2.5F, 3.0F, 4.0F, 8.0F)
                // 尾端自然微卷下垂羽梢 (Graceful cascading plume tips)
                .texOffs(64, 25).addBox(-2.0F, 3.5F, 6.5F, 4.0F, 3.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -1.8F, 6.0F, -0.22F, 0.0F, 0.0F));

        // =========================================================================
        // 3. 丹顶长颈体系 (Head & Flexible S-Curved Neck Architecture)
        // 双关节 S 弯灵活长颈 + 灵秀鹤颅 + 丹顶赤砂 + 修长玄喙
        // =========================================================================
        // 下段长颈 (Lower Neck: 前倾仰伸 第一道优美弧线)
        PartDefinition neckLower = body.addOrReplaceChild("neck_lower", CubeListBuilder.create()
                // 下段长颈主干 (Lower neck column)
                .texOffs(0, 34).addBox(-1.5F, -8.0F, -2.0F, 3.0F, 9.0F, 4.0F)
                // 颈根丰盈基羽领 (Lower throat & chest plumage collar)
                .texOffs(16, 34).addBox(-2.0F, -3.0F, -2.5F, 4.0F, 4.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, -1.5F, -6.0F, 0.35F, 0.0F, 0.0F));

        // 上段长颈 (Upper Neck: 回折后倾 第二道优美反向 S 弯弧线)
        PartDefinition neckUpper = neckLower.addOrReplaceChild("neck_upper", CubeListBuilder.create()
                // 上段修长曲颈 (Upper slender neck column)
                .texOffs(36, 34).addBox(-1.0F, -8.0F, -1.5F, 2.0F, 9.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -7.5F, 0.0F, -0.65F, 0.0F, 0.0F));

        // 鹤首与冠喙 (Crane Head, Crimson Crown & Dagger Beak)
        PartDefinition head = neckUpper.addOrReplaceChild("head", CubeListBuilder.create()
                // 灵秀仙鹤头颅 (Cranium & cheeks)
                .texOffs(48, 34).addBox(-1.5F, -2.2F, -3.0F, 3.0F, 4.0F, 5.0F)
                // 丹顶赤砂冠 (Crimson ruby crown spot on forehead and skull)
                .texOffs(0, 49).addBox(-1.0F, -3.0F, -2.8F, 2.0F, 1.0F, 4.0F)
                // 丹砂冠顶耸起棱角 (Crimson crown crest finial)
                .texOffs(14, 49).addBox(-0.5F, -3.5F, -2.0F, 1.0F, 1.0F, 3.0F)
                // 枕羽与玄青咽颊羽 (Throat plumage & nape markings)
                .texOffs(0, 56).addBox(-1.2F, 0.5F, -2.2F, 2.4F, 2.0F, 3.0F)
                // 修长玄黑鹤喙基部 (Slender black beak shaft)
                .texOffs(24, 49).addBox(-0.8F, -1.0F, -8.5F, 1.6F, 2.0F, 6.0F)
                // 尖锐锋利玄黑喙尖 (Slender tapering dagger beak tip)
                .texOffs(42, 49).addBox(-0.5F, -0.8F, -13.0F, 1.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, -7.5F, 0.0F, 0.30F, 0.0F, 0.0F));

        // =========================================================================
        // 4. 舒展仙鹤大翼 (Wide Wingspan: Inner Wing + Outer Wing + Black Primary Tips)
        // =========================================================================
        // 左仙翼内节 (Left Inner Wing - 肱骨与次级覆羽面)
        PartDefinition leftWing = body.addOrReplaceChild("left_wing", CubeListBuilder.create()
                // 内翼主翼骨与白羽覆羽 (Inner humeral wing bone & white coverts)
                .texOffs(0, 64).addBox(0.0F, -0.8F, -2.0F, 1.0F, 5.0F, 8.0F)
                // 内侧次级飞羽白羽面 (Secondary flight feather blade)
                .texOffs(20, 64).addBox(0.2F, 1.5F, 0.0F, 1.0F, 4.0F, 7.0F),
                PartPose.offsetAndRotation(3.6F, -2.0F, -3.5F, 0.0F, 0.0F, 0.08F));

        // 左仙翼外节 (Left Outer Wing & Black Primary Feather Tips - 桡骨与玄青初级飞羽尖)
        leftWing.addOrReplaceChild("left_wing_outer", CubeListBuilder.create()
                // 外翼主飞羽面 (Outer flight feather blade)
                .texOffs(38, 64).addBox(0.0F, -0.5F, 0.0F, 1.0F, 5.0F, 8.0F)
                // 玄青初级飞羽尖 第一层 (Black primary feather tips: Tier 1)
                .texOffs(0, 80).addBox(0.1F, 1.0F, 6.5F, 1.0F, 5.0F, 6.0F)
                // 玄青初级飞羽尖 第二层长翎 (Black primary feather tips: Tier 2)
                .texOffs(16, 80).addBox(0.15F, 2.5F, 10.5F, 1.0F, 4.0F, 4.0F)
                // 飘逸玄黑末梢翎羽 (Extended primary tip flutter remiges)
                .texOffs(28, 80).addBox(0.2F, 3.8F, 13.5F, 1.0F, 2.0F, 3.0F),
                PartPose.offsetAndRotation(0.2F, 0.5F, 5.5F, 0.0F, 0.0F, 0.0F));

        // 右仙翼内节 (Right Inner Wing)
        PartDefinition rightWing = body.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(64, 64).addBox(-1.0F, -0.8F, -2.0F, 1.0F, 5.0F, 8.0F)
                .texOffs(84, 64).addBox(-1.2F, 1.5F, 0.0F, 1.0F, 4.0F, 7.0F),
                PartPose.offsetAndRotation(-3.6F, -2.0F, -3.5F, 0.0F, 0.0F, -0.08F));

        // 右仙翼外节 (Right Outer Wing & Black Primary Feather Tips)
        rightWing.addOrReplaceChild("right_wing_outer", CubeListBuilder.create()
                .texOffs(102, 64).addBox(-1.0F, -0.5F, 0.0F, 1.0F, 5.0F, 8.0F)
                .texOffs(64, 80).addBox(-1.1F, 1.0F, 6.5F, 1.0F, 5.0F, 6.0F)
                .texOffs(80, 80).addBox(-1.15F, 2.5F, 10.5F, 1.0F, 4.0F, 4.0F)
                .texOffs(92, 80).addBox(-1.2F, 3.8F, 13.5F, 1.0F, 2.0F, 3.0F),
                PartPose.offsetAndRotation(-0.2F, 0.5F, 5.5F, 0.0F, 0.0F, 0.0F));

        // =========================================================================
        // 5. 涉水长足 (Stilted Wading Legs: Thigh Coverts + Stilts + 4-Toed Claws)
        // 涉禽结构：髋部大腿羽衣 + 鹤膝折节 + 超长黑骨涉水胫蹠 + 涉水四爪 (3前1后)
        // =========================================================================
        // 左足股部 (Left Thigh - 鹤胫羽衣)
        PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                // 鹤胫大腿白色丰盈羽衣 (Thigh feather coverts: 2.4x5x3)
                .texOffs(0, 96).addBox(-1.2F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                // 股骨下端细骨过渡段 (Upper stilt joint stem)
                .texOffs(14, 96).addBox(-0.5F, 4.5F, -0.5F, 1.0F, 2.0F, 1.0F),
                PartPose.offset(2.2F, 9.5F, 0.5F));

        // 左小腿与涉水四爪 (Left Shin, Knee, Stilt & 4-Toed Claws - 鹤膝与长爪)
        leftLeg.addOrReplaceChild("left_shin", CubeListBuilder.create()
                // 鹤膝节瘤 (Avian knee joint knot)
                .texOffs(0, 106).addBox(-0.7F, -0.5F, -0.8F, 2.0F, 2.0F, 2.0F)
                // 超长修长涉水黑骨细腿 (Extra-long slender stilt tarsus: 1x8x1)
                .texOffs(14, 101).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 8.0F, 1.0F)
                // 涉水足踝部 (Ankle tarsal joint)
                .texOffs(20, 96).addBox(-0.8F, 8.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                // 涉水前伸三爪 (3 Forward wading toes: middle, inner, outer)
                .texOffs(20, 101).addBox(-1.5F, 8.5F, -4.5F, 3.0F, 1.0F, 5.0F)
                // 涉水后趾爪 (1 Hind toe / hallux for wading stability)
                .texOffs(0, 114).addBox(-0.5F, 8.5F, 0.8F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(0.0F, 5.5F, 0.0F));

        // 右足股部 (Right Thigh)
        PartDefinition rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(40, 96).addBox(-1.8F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                .texOffs(54, 96).addBox(-0.5F, 4.5F, -0.5F, 1.0F, 2.0F, 1.0F),
                PartPose.offset(-2.2F, 9.5F, 0.5F));

        // 右小腿与涉水四爪 (Right Shin, Knee, Stilt & 4-Toed Claws)
        rightLeg.addOrReplaceChild("right_shin", CubeListBuilder.create()
                .texOffs(40, 106).addBox(-1.3F, -0.5F, -0.8F, 2.0F, 2.0F, 2.0F)
                .texOffs(54, 101).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 8.0F, 1.0F)
                .texOffs(60, 96).addBox(-1.2F, 8.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                .texOffs(60, 101).addBox(-1.5F, 8.5F, -4.5F, 3.0F, 1.0F, 5.0F)
                .texOffs(40, 114).addBox(-0.5F, 8.5F, 0.8F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(0.0F, 5.5F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // =========================================================================
        // 1. 丹顶长颈律动与涉水点头 (Neck Curving, Breathing S-Curve & Optokinetic Head Bob)
        // =========================================================================
        // 头部自由观察俯仰与偏航
        float lookPitch = state.xRot * ((float) Math.PI / 180F);
        float lookYaw = state.yRot * ((float) Math.PI / 180F);
        this.head.xRot = 0.30F + lookPitch * 0.65F;
        this.head.yRot = lookYaw * 0.70F;
        this.head.zRot = lookYaw * 0.12F;

        // 颈部静息随息 S 弯起伏
        float breath = Mth.sin(state.ageInTicks * 0.05F);
        float lowerBreath = breath * 0.04F;
        float upperBreath = Mth.sin(state.ageInTicks * 0.05F + 0.6F) * 0.05F;
        this.neckLower.xRot = 0.35F + lowerBreath;
        this.neckUpper.xRot = -0.65F - upperBreath;

        // 涉水行进步态下的长颈伸缩与仙鹤点头 (Head bobbing during wading)
        float walkHeadBob = Mth.cos(walkPos * 1.1F) * 0.14F * walkSpeed;
        this.neckLower.xRot += walkHeadBob;
        this.neckUpper.xRot -= walkHeadBob * 0.75F;
        this.head.xRot += walkHeadBob * 0.50F;

        // 行进时长颈优雅侧向微摆
        float neckSway = Mth.cos(walkPos * 0.55F) * 0.06F * walkSpeed;
        this.neckLower.yRot = neckSway;
        this.neckUpper.yRot = -neckSway * 0.5F;

        // =========================================================================
        // 2. 仙鹤高抬腿涉水步 (Elegant Crane Wading Step with High Knees)
        // 涉禽步态特征：大腿高抬向前，膝关节向后大幅折叠折脚提足，落步时膝盖向前伸展踏水
        // =========================================================================
        float strideFreq = 0.55F;
        float legAngle = walkPos * strideFreq;

        // 左腿涉水大腿高抬
        float leftThighSwing = Mth.sin(legAngle) * 0.90F * walkSpeed;
        this.leftLeg.xRot = leftThighSwing;
        // 左膝折叠 (抬起时膝盖向后弯曲以防长爪拖泥带水，下踩时顺畅伸直)
        float leftKneeFold = Math.max(0.0F, Mth.sin(legAngle)) * 1.15F * walkSpeed;
        float leftShinSwing = leftKneeFold + (Mth.sin(legAngle - 0.3F) * 0.25F * walkSpeed);
        this.leftShin.xRot = Math.max(0.0F, leftShinSwing);

        // 右腿涉水反相步态 (Phase shifted by PI)
        float rightThighSwing = Mth.sin(legAngle + (float) Math.PI) * 0.90F * walkSpeed;
        this.rightLeg.xRot = rightThighSwing;
        float rightKneeFold = Math.max(0.0F, Mth.sin(legAngle + (float) Math.PI)) * 1.15F * walkSpeed;
        float rightShinSwing = rightKneeFold + (Mth.sin(legAngle + (float) Math.PI - 0.3F) * 0.25F * walkSpeed);
        this.rightShin.xRot = Math.max(0.0F, rightShinSwing);

        // 躯干随涉水高抬步伐起伏与重心侧倾
        float torsoBob = Mth.sin(legAngle * 2.0F) * 0.45F * walkSpeed;
        this.body.y = 9.5F + torsoBob;
        this.body.zRot = Mth.cos(legAngle) * 0.04F * walkSpeed;

        // =========================================================================
        // 3. 宏阔大展翅多节扇动 (Majestic Wide-Span Wing Flapping)
        // 双节翅膀：内翼展开骨架，外翼飞羽面呈波浪延迟扇拂，行进与腾空时大展仙羽
        // =========================================================================
        float flapTime = state.ageInTicks * 0.25F;
        // 静息时微动呼吸，行进或奔跑时大展翅飞扬
        float flapAmp = 0.12F + walkSpeed * 0.65F;
        float innerFlap = Mth.cos(flapTime) * flapAmp;
        // 展翼水平开合角 (Spread angle)
        float wingSpread = 0.05F + walkSpeed * 0.70F + Math.max(0.0F, -innerFlap * 0.35F);

        // 左仙翼内节
        this.leftWing.yRot = wingSpread;
        this.leftWing.zRot = 0.08F + innerFlap;
        // 右仙翼内节
        this.rightWing.yRot = -wingSpread;
        this.rightWing.zRot = -0.08F - innerFlap;

        // 外翼次级多节波浪扇动 (Secondary harmonic wave flap with phase lag & wider span)
        float outerFlap = Mth.cos(flapTime - 0.45F) * (flapAmp * 1.40F);
        float outerSpread = 0.08F + walkSpeed * 0.50F + Math.max(0.0F, -innerFlap * 0.40F);
        this.leftWingOuter.yRot = outerSpread;
        this.leftWingOuter.zRot = outerFlap * 0.85F;
        this.rightWingOuter.yRot = -outerSpread;
        this.rightWingOuter.zRot = -outerFlap * 0.85F;

        // 展翅扇风时的前后俯仰 (Wing attack angle / pitch)
        float wingPitch = Mth.sin(flapTime) * 0.15F * (0.20F + walkSpeed * 0.80F);
        this.leftWing.xRot = wingPitch;
        this.rightWing.xRot = wingPitch;
        this.leftWingOuter.xRot = wingPitch * 1.25F;
        this.rightWingOuter.xRot = wingPitch * 1.25F;

        // =========================================================================
        // 4. 玄青尾羽随风起伏与重心摇曳 (Tail Plumes Swaying)
        // =========================================================================
        this.tail.xRot = -0.22F + Mth.sin(state.ageInTicks * 0.06F) * 0.05F + walkSpeed * 0.10F;
        this.tail.yRot = Mth.cos(legAngle) * 0.12F * walkSpeed;
    }
}
