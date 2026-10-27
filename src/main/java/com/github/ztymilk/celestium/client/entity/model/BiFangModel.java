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
 * 毕方神鸟 (Bi Fang) - 上古火精神鸟，青羽赤文，白喙独足
 * 《山海经·西山经》：“有鸟焉，其状如鹤，一足，赤文青质而白喙，名曰毕方，其鸣自叫也，见则其邑有讹火。”
 *
 * “神兽构思师”树状解剖体系架构：
 * 1. 白喙神鸟首 (Head & Beak & Crimson Crest):
 *    - 纯白仙喙 (Sharp hooked predatory white beak)
 *    - 冲天赤火神冠 (3-tiered fiery crimson crest plumage)
 *    - 锐目仙眉与咽喉灵羽领圈 (Keen piercing eyes & throat ruff)
 * 2. 青羽赤文身躯 (Body & Torso):
 *    - 雄健流线型神禽躯体 (Muscular avian body)
 *    - 侧翼立体赤文烈焰甲板 (Crimson flame pattern sideplates)
 *    - 背脊烈焰羽棱与丰盈赤火胸脯 (Back spine crest & breast plumage)
 * 3. 炽火飞羽双翼 (Tiered Flight Wings):
 *    - 肩翼主骨架 (Wing base)
 *    - 广展主飞羽 (Main flight plumage)
 *    - 炽火翼梢剑羽与翼下覆羽 (Flame-tipped feather ends & secondary feathers)
 * 4. 上古标志性独足 (Iconic Sturdy Single Leg):
 *    - 强健股部大腿肌肉群与赤羽腿甲 (Muscular upper thigh & feather fluff)
 *    - 遒劲小腿胫骨 (Sturdy shank)
 *    - 稳重四趾神爪 (Formidable 4-taloned gripping claw: 3 front toes, 1 back hallux)
 * 5. 炽焰火凤尾 (Spreading Fan Tail Streamers):
 *    - 尾羽基座与丰盈火羽簇
 *    - 扇形开散的三道炽焰神羽长翎与外展羽剑
 */
public class BiFangModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("bifang"), "main");

    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart leftWing;
    private final ModelPart leftWingOuter;
    private final ModelPart rightWing;
    private final ModelPart rightWingOuter;
    private final ModelPart singleLeg;
    private final ModelPart shank;
    private final ModelPart foot;
    private final ModelPart tail;
    private final ModelPart tailCenter;
    private final ModelPart tailLeft;
    private final ModelPart tailRight;

    public BiFangModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.leftWing = this.body.getChild("left_wing");
        this.leftWingOuter = this.leftWing.getChild("left_wing_outer");
        this.rightWing = this.body.getChild("right_wing");
        this.rightWingOuter = this.rightWing.getChild("right_wing_outer");
        this.singleLeg = root.getChild("single_leg");
        this.shank = this.singleLeg.getChild("shank");
        this.foot = this.shank.getChild("foot");
        this.tail = this.body.getChild("tail");
        this.tailCenter = this.tail.getChild("tail_center");
        this.tailLeft = this.tail.getChild("tail_left");
        this.tailRight = this.tail.getChild("tail_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 青羽赤文身躯 (Muscular Avian Body & Flame Flanks)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 主躯干 (Main Avian Torso: 9x8x12)
                .texOffs(0, 0).addBox(-4.5F, -4.0F, -6.0F, 9.0F, 8.0F, 12.0F)
                // 胸前丰盈赤羽 (Breast Plumage: 7x7x3)
                .texOffs(44, 0).addBox(-3.5F, -3.0F, -8.0F, 7.0F, 7.0F, 3.0F)
                // 背脊赤羽棱脊 (Back Spine Ridge: 2x2x10)
                .texOffs(44, 11).addBox(-1.0F, -5.5F, -4.0F, 2.0F, 2.0F, 10.0F)
                // 左侧赤文焰羽甲板 (Left Crimson Flame Sideplate: 1x6x11)
                .texOffs(0, 22).addBox(4.0F, -3.0F, -5.0F, 1.0F, 6.0F, 11.0F)
                // 右侧赤文焰羽甲板 (Right Crimson Flame Sideplate: 1x6x11)
                .texOffs(25, 22).addBox(-5.0F, -3.0F, -5.0F, 1.0F, 6.0F, 11.0F),
                PartPose.offset(0.0F, 10.0F, 0.0F));

        // 2. 灵禽修颈与白喙神首 (Neck, Head, Beak & Crimson Crest)
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                // 灵禽长颈 (Neck Column: 5x8x5)
                .texOffs(70, 0).addBox(-2.5F, -8.0F, -2.5F, 5.0F, 8.0F, 5.0F)
                // 咽喉赤羽领圈 (Throat Ruff Plumes: 6x5x3)
                .texOffs(92, 0).addBox(-3.0F, -5.0F, -4.5F, 6.0F, 5.0F, 3.0F)
                // 喉下垂绦赤羽 (Throat Plume Ruff: 3x3x2)
                .texOffs(112, 0).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -2.5F, -5.0F, -0.35F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                // 神鸟颅首 (Head Cranium: 6x6x7)
                .texOffs(70, 15).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 7.0F)
                // 锐目仙眉 (Keen Piercing Eyes & Brow Ridges)
                .texOffs(98, 10).addBox(2.8F, -2.5F, -3.5F, 1.0F, 1.0F, 4.0F)
                .texOffs(110, 10).addBox(-3.8F, -2.5F, -3.5F, 1.0F, 1.0F, 4.0F)
                // 纯白仙喙基段 (Pale White Sharp Predatory Upper Beak: 3x3x6)
                .texOffs(70, 30).addBox(-1.5F, -1.0F, -9.0F, 3.0F, 3.0F, 6.0F)
                // 纯白仙喙利钩爪吻 (Predatory Curved Beak Hook: 2x2x2)
                .texOffs(90, 30).addBox(-1.0F, 1.2F, -9.8F, 2.0F, 2.0F, 2.0F)
                // 仙喙下颚 (Lower Mandible: 2x1x5)
                .texOffs(90, 35).addBox(-1.0F, 1.0F, -8.0F, 2.0F, 1.0F, 5.0F)
                // 冲天赤火神冠第一重·回首长翎 (Crimson Crest Tier 1: 1x6x9)
                .texOffs(106, 17).addBox(-0.5F, -8.0F, -1.0F, 1.0F, 6.0F, 9.0F)
                // 冲天赤火神冠第二重·冲天烈焰 (Crimson Crest Tier 2: 1x6x5)
                .texOffs(106, 33).addBox(-0.5F, -13.0F, 2.0F, 1.0F, 6.0F, 5.0F)
                // 冲天赤火神冠第三重·枕后火羽 (Crimson Crest Tier 3: 2x4x3)
                .texOffs(70, 41).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 3.0F)
                // 颔下神火流苏 (Chin Flame Tassel: 2x4x3)
                .texOffs(82, 41).addBox(-1.0F, 2.0F, -4.0F, 2.0F, 4.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -7.5F, 0.0F, 0.35F, 0.0F, 0.0F));

        // 3. 炽火飞羽双翼 (Broad Tiered Flight Wings)
        // 左翼 (Left Wing)
        PartDefinition leftWing = body.addOrReplaceChild("left_wing", CubeListBuilder.create()
                // 肩翼主骨架 (Wing Base: 2x4x8)
                .texOffs(0, 42).addBox(0.0F, -1.5F, -2.0F, 2.0F, 4.0F, 8.0F),
                PartPose.offset(4.5F, -2.0F, -3.0F));

        leftWing.addOrReplaceChild("left_wing_outer", CubeListBuilder.create()
                // 主翼飞羽面 (Main Plumage: 12x1x12)
                .texOffs(0, 55).addBox(0.0F, -1.0F, -2.0F, 12.0F, 1.0F, 12.0F)
                // 炽火飞羽翼梢剑羽 (Flame-Tipped Feather Ends: 8x1x8)
                .texOffs(0, 69).addBox(8.0F, -0.8F, 2.0F, 8.0F, 1.0F, 8.0F)
                // 副羽与翼底覆羽 (Secondary Underwing Feathers: 8x1x6)
                .texOffs(0, 79).addBox(1.0F, -0.6F, 6.0F, 8.0F, 1.0F, 6.0F),
                PartPose.offset(2.0F, 0.0F, 0.0F));

        // 右翼 (Right Wing)
        PartDefinition rightWing = body.addOrReplaceChild("right_wing", CubeListBuilder.create()
                // 肩翼主骨架 (Wing Base: 2x4x8)
                .texOffs(22, 42).addBox(-2.0F, -1.5F, -2.0F, 2.0F, 4.0F, 8.0F),
                PartPose.offset(-4.5F, -2.0F, -3.0F));

        rightWing.addOrReplaceChild("right_wing_outer", CubeListBuilder.create()
                // 主翼飞羽面 (Main Plumage: 12x1x12)
                .texOffs(50, 55).addBox(-12.0F, -1.0F, -2.0F, 12.0F, 1.0F, 12.0F)
                // 炽火飞羽翼梢剑羽 (Flame-Tipped Feather Ends: 8x1x8)
                .texOffs(33, 69).addBox(-16.0F, -0.8F, 2.0F, 8.0F, 1.0F, 8.0F)
                // 副羽与翼底覆羽 (Secondary Underwing Feathers: 8x1x6)
                .texOffs(29, 79).addBox(-9.0F, -0.6F, 6.0F, 8.0F, 1.0F, 6.0F),
                PartPose.offset(-2.0F, 0.0F, 0.0F));

        // 4. 上古标志性独足 (Iconic Sturdy Single Leg)
        PartDefinition singleLeg = root.addOrReplaceChild("single_leg", CubeListBuilder.create()
                // 粗壮上肢股部 (Muscular Upper Thigh: 4x6x5)
                .texOffs(100, 45).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 6.0F, 5.0F)
                // 股部赤羽覆羽 (Thigh Feather Fluff: 5x4x6)
                .texOffs(100, 57).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 4.0F, 6.0F),
                PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition shank = singleLeg.addOrReplaceChild("shank", CubeListBuilder.create()
                // 遒劲胫骨 (Sturdy Shank: 3x6x3)
                .texOffs(68, 69).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F),
                PartPose.offset(0.0F, 5.5F, 0.0F));

        shank.addOrReplaceChild("foot", CubeListBuilder.create()
                // 掌垫爪踵 (Foot Pad: 4x1x4)
                .texOffs(82, 69).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F)
                // 前中主趾 (Front Middle Toe: 2x1x4)
                .texOffs(82, 75).addBox(-1.0F, 0.0F, -6.0F, 2.0F, 1.0F, 4.0F)
                // 前左角趾 (Front Left Angled Toe: 1x1x4)
                .texOffs(96, 75).addBox(1.0F, 0.0F, -5.5F, 1.0F, 1.0F, 4.0F)
                // 前右角趾 (Front Right Angled Toe: 1x1x4)
                .texOffs(108, 75).addBox(-2.0F, 0.0F, -5.5F, 1.0F, 1.0F, 4.0F)
                // 后距爪趾 (Rear Hallux Spur: 2x1x3)
                .texOffs(68, 79).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 3.0F)
                // 利爪金钩尖刃 (Claw Tips: 2x1x1)
                .texOffs(80, 81).addBox(-1.0F, -0.3F, -7.0F, 2.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 5.5F, 0.0F));

        // 5. 炽焰火凤尾 (Spreading Fan Tail Streamers)
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                // 尾羽基座 (Tail Base: 5x3x5)
                .texOffs(0, 88).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 5.0F)
                // 尾上焰羽簇 (Tail Base Flame Tufts: 6x2x4)
                .texOffs(22, 88).addBox(-3.0F, -2.0F, 1.0F, 6.0F, 2.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, -1.5F, 5.5F, 0.22F, 0.0F, 0.0F));

        // 中轴炽焰长尾翎
        tail.addOrReplaceChild("tail_center", CubeListBuilder.create()
                .texOffs(0, 97).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 18.0F)
                // 尾翎末端烈焰升腾
                .texOffs(0, 117).addBox(-3.5F, -0.7F, 13.0F, 7.0F, 1.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 4.5F));

        // 左开散炽焰翎羽与外展羽剑
        tail.addOrReplaceChild("tail_left", CubeListBuilder.create()
                .texOffs(46, 97).addBox(-1.0F, -0.4F, 0.0F, 3.0F, 1.0F, 15.0F)
                .texOffs(44, 85).addBox(1.5F, -0.2F, 2.0F, 2.0F, 1.0F, 10.0F),
                PartPose.offsetAndRotation(1.5F, 0.0F, 4.0F, 0.05F, 0.2F, 0.1F));

        // 右开散炽焰翎羽与外展羽剑
        tail.addOrReplaceChild("tail_right", CubeListBuilder.create()
                .texOffs(84, 97).addBox(-2.0F, -0.4F, 0.0F, 3.0F, 1.0F, 15.0F)
                .texOffs(70, 85).addBox(-3.5F, -0.2F, 2.0F, 2.0F, 1.0F, 10.0F),
                PartPose.offsetAndRotation(-1.5F, 0.0F, 4.0F, 0.05F, -0.2F, -0.1F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        float walkPos = state.walkAnimationPos;

        // 1. 白喙仙首偏侧与机警巡视 (Head Cocking & Vigilant Watching)
        float headPitch = state.xRot * ((float) Math.PI / 180F) * 0.75F;
        float headYaw = state.yRot * ((float) Math.PI / 180F) * 0.75F;
        float vigilTime = state.ageInTicks * 0.04F;
        // 独足神鸟生性机警，伴随灵动侧首斜视 (Avian curious head cocking)
        float cockAngle = Mth.sin(vigilTime) * 0.16F;
        float breath = Mth.sin(state.ageInTicks * 0.08F);

        // 2. 上古标志性独足踏跃与金鸡独立平衡 (Iconic Single-Leg Hopping & Balancing)
        if (walkSpeed > 0.02F) {
            // 行走跃进阶段：金鸡踏火，独足单腿连环弹跃 (Single-leg hop cycle)
            float hopPhase = walkPos * 1.2F;
            float hopSin = Mth.sin(hopPhase);
            // 躯体腾空起落弧度
            float hopBounce = -Math.abs(hopSin) * 3.5F * walkSpeed;
            this.body.y = 10.0F + hopBounce;

            // 跃步时的前倾与着地受力缓冲
            this.body.xRot = 0.05F + hopSin * 0.22F * walkSpeed;
            // 独足起跃时为了保持重心平衡的微微侧倾摇曳
            this.body.zRot = Mth.cos(hopPhase * 0.5F) * 0.12F * walkSpeed;

            // 独足关节联动：大腿提拉、小腿胫骨收缩折叠、神爪爪踵着地平衡反曲
            this.singleLeg.xRot = hopSin * 0.55F * walkSpeed;
            this.singleLeg.zRot = -this.body.zRot * 0.6F;
            this.shank.xRot = Math.max(0.0F, Mth.sin(hopPhase - 0.5F)) * 0.75F * walkSpeed;
            this.foot.xRot = -this.singleLeg.xRot - this.shank.xRot;

            // 跃步平衡振翼：展开双翼维持空中姿态
            float flapCycle = Mth.sin(state.ageInTicks * 0.38F) * (0.3F + walkSpeed * 0.35F);
            this.leftWing.xRot = 0.08F - walkSpeed * 0.12F;
            this.leftWing.zRot = 0.28F + walkSpeed * 0.32F + flapCycle;
            this.leftWingOuter.zRot = flapCycle * 0.45F;

            this.rightWing.xRot = 0.08F - walkSpeed * 0.12F;
            this.rightWing.zRot = -0.28F - walkSpeed * 0.32F - flapCycle;
            this.rightWingOuter.zRot = -flapCycle * 0.45F;

            // 跃动时颈部与头部具有鸟类经典的防抖定焦视线追踪 (Avian head stabilization)
            this.neck.xRot = -0.35F - this.body.xRot * 0.5F + headPitch * 0.3F;
            this.neck.yRot = headYaw * 0.35F;
            this.head.xRot = 0.35F - this.body.xRot * 0.4F + headPitch * 0.65F;
            this.head.yRot = headYaw * 0.65F;
            this.head.zRot = cockAngle * 0.5F;
        } else {
            // 静止驻足阶段：金鸡独立·巍然不动·灵气吐纳 (Majestic Single-Leg Stance & Breathing)
            this.body.y = 10.0F + breath * 0.35F;
            this.body.xRot = 0.05F;
            // 极细微的单足站立动态微调重心平衡 (Subtle balance micro-sway)
            float balanceSway = Mth.sin(state.ageInTicks * 0.05F) * 0.035F;
            this.body.zRot = balanceSway;

            this.singleLeg.xRot = 0.0F;
            this.singleLeg.zRot = -balanceSway * 0.5F;
            this.shank.xRot = 0.0F;
            this.foot.xRot = 0.0F;

            // 悠然收敛双翼与微风羽动
            this.leftWing.xRot = 0.08F;
            this.leftWing.zRot = 0.16F + breath * 0.03F;
            this.leftWingOuter.zRot = 0.06F;

            this.rightWing.xRot = 0.08F;
            this.rightWing.zRot = -0.16F - breath * 0.03F;
            this.rightWingOuter.zRot = -0.06F;

            // 驻足机警巡视神态
            this.neck.xRot = -0.35F + headPitch * 0.3F + breath * 0.02F;
            this.neck.yRot = headYaw * 0.35F;
            this.head.xRot = 0.35F + headPitch * 0.65F;
            this.head.yRot = headYaw * 0.65F;
            this.head.zRot = cockAngle;
        }

        // 3. 炽焰火凤尾随神火升腾流转 (Spreading Tail Streamers Flame Dynamics)
        float tailWave = state.ageInTicks * 0.12F;
        this.tail.xRot = 0.22F + Mth.sin(tailWave) * 0.06F;

        // 中轴尾翎起伏
        this.tailCenter.xRot = Mth.sin(tailWave) * 0.12F - walkSpeed * 0.15F;
        this.tailCenter.yRot = Mth.cos(tailWave * 0.7F) * 0.08F;

        // 左开散炽焰翎羽翻卷
        this.tailLeft.xRot = 0.05F + Mth.sin(tailWave + 0.6F) * 0.14F;
        this.tailLeft.yRot = 0.2F + Mth.cos(tailWave * 0.6F) * 0.06F;
        this.tailLeft.zRot = 0.1F + Mth.sin(tailWave * 0.8F) * 0.05F;

        // 右开散炽焰翎羽翻卷
        this.tailRight.xRot = 0.05F + Mth.sin(tailWave + 1.2F) * 0.14F;
        this.tailRight.yRot = -0.2F - Mth.cos(tailWave * 0.6F) * 0.06F;
        this.tailRight.zRot = -0.1F - Mth.sin(tailWave * 0.8F) * 0.05F;
    }
}
