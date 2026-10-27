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
 * 广寒玉兔 (Moon Rabbit)
 * 依照概念图与敦煌仙灵神兽设计规范构建：
 * 软萌圆润白兔体态、飞羽舒展天耳、额前镶赤宝金冠、胸前金铃项圈、背负织金紫缎鞍毯与四枚垂落金丝朱红流苏。
 *
 * 严格树状解剖体系架构 (Anatomical Tree Architecture):
 * root
 *  └── body (主躯干，圆滚滚毛茸兔躯 + 紫缎织金鞍毯 + 四枚垂落金丝朱红流苏)
 *       ├── head (圆润软萌兔首 + 灵动咀嚼三瓣嘴 + 额前镶赤宝金冠 + 胸前垂吊金铃项圈)
 *       │    ├── mouth (灵动咀嚼三瓣嘴)
 *       │    ├── left_ear (羽翼般向后向上飞扬的飞天仙耳，内外耳廓分明)
 *       │    │    └── left_ear_tip (次级飘拂仙羽)
 *       │    ├── right_ear (对称飞天仙耳)
 *       │    │    └── right_ear_tip (次级飘拂仙羽)
 *       │    └── bell (金铃项圈垂胸金铃)
 *       ├── tail (毛茸茸圆球兔尾，小巧灵动)
 *       ├── leg_front_left (前肢小肉爪，踩地自然)
 *       ├── leg_front_right (前肢小肉爪，踩地自然)
 *       ├── leg_hind_left (丰满大腿肌群)
 *       │    └── left_hind_foot (弹跳蹬地后足)
 *       └── leg_hind_right (丰满大腿肌群)
 *            └── right_hind_foot (弹跳蹬地后足)
 */
public class MoonRabbitModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("moon_rabbit"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart leftEar;
    private final ModelPart leftEarTip;
    private final ModelPart rightEar;
    private final ModelPart rightEarTip;
    private final ModelPart bell;
    private final ModelPart tail;
    private final ModelPart legFrontLeft;
    private final ModelPart legFrontRight;
    private final ModelPart legHindLeft;
    private final ModelPart legHindRight;
    private final ModelPart leftHindFoot;
    private final ModelPart rightHindFoot;

    public MoonRabbitModel(ModelPart root) {
        super(root);
        this.root = root;

        // 严格树状解剖层级获取 (Strict Anatomical Tree Hierarchy)
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.mouth = this.head.getChild("mouth");
        this.leftEar = this.head.getChild("left_ear");
        this.leftEarTip = this.leftEar.getChild("left_ear_tip");
        this.rightEar = this.head.getChild("right_ear");
        this.rightEarTip = this.rightEar.getChild("right_ear_tip");
        this.bell = this.head.getChild("bell");
        this.tail = this.body.getChild("tail");
        this.legFrontLeft = this.body.getChild("leg_front_left");
        this.legFrontRight = this.body.getChild("leg_front_right");
        this.legHindLeft = this.body.getChild("leg_hind_left");
        this.legHindRight = this.body.getChild("leg_hind_right");
        this.leftHindFoot = this.legHindLeft.getChild("left_hind_foot");
        this.rightHindFoot = this.legHindRight.getChild("right_hind_foot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 主躯干体系 (Root -> Body)
        // 圆滚滚毛茸兔躯 + 紫缎织金鞍毯 + 四枚垂落金丝朱红流苏
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 圆滚滚毛茸兔身 (Fluffy Chubby Body)
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -5.5F, 8.0F, 8.0F, 11.0F)
                // 胸前丰盈仙毛 (Chest Breast Tuft)
                .texOffs(40, 0).addBox(-3.0F, -2.5F, -6.5F, 6.0F, 6.0F, 2.0F)
                // 紫缎织金鞍毯 (Royal Purple Gold-Trimmed Saddle Blanket)
                .texOffs(58, 0).addBox(-4.5F, -4.3F, -3.5F, 9.0F, 3.0F, 8.0F)
                // 鞍毯织金背饰云垫 (Embroidered Saddle Crest Cushion)
                .texOffs(94, 0).addBox(-3.5F, -4.6F, -2.5F, 7.0F, 1.0F, 6.0F)
                // 两侧垂落金丝朱红流苏 (4 Hanging Vermilion Tassels with Gold Rings)
                .texOffs(58, 13).addBox(4.5F, -2.0F, -2.5F, 1.0F, 5.0F, 2.0F)  // 左前流苏
                .texOffs(66, 13).addBox(4.5F, -2.0F, 1.5F, 1.0F, 5.0F, 2.0F)   // 左后流苏
                .texOffs(74, 13).addBox(-5.5F, -2.0F, -2.5F, 1.0F, 5.0F, 2.0F) // 右前流苏
                .texOffs(82, 13).addBox(-5.5F, -2.0F, 1.5F, 1.0F, 5.0F, 2.0F), // 右后流苏
                PartPose.offset(0.0F, 17.0F, 0.0F));

        // =========================================================================
        // 2. 毛茸茸圆球兔尾 (Body -> Tail)
        // =========================================================================
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(40, 10).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F)
                .texOffs(40, 18).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, -1.0F, 5.5F));

        // =========================================================================
        // 3. 仙兔玉首体系 (Body -> Head)
        // 圆润软萌兔首 + 两颊腮毛 + 额前镶赤宝金冠 + 金丝项圈
        // =========================================================================
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 兔头主颅 (Chubby Head Skull)
                .texOffs(0, 24).addBox(-3.0F, -4.5F, -5.0F, 6.0F, 6.0F, 6.0F)
                // 两颊蓬松颊毛 (Chubby Fluffy Cheek Tufts)
                .texOffs(26, 24).addBox(-4.0F, -2.0F, -4.5F, 8.0F, 4.0F, 5.0F)
                // 颈部与胸口毛发平滑过渡 (Neck Fur Bridge)
                .texOffs(54, 24).addBox(-2.5F, -0.5F, -1.0F, 5.0F, 4.0F, 3.0F)
                // 额前赤玉金冠箍带 (Forehead Ruby Gold Crown Circlet)
                .texOffs(72, 24).addBox(-2.0F, -5.2F, -5.2F, 4.0F, 2.0F, 1.0F)
                // 额心通灵赤宝 (Forehead Radiant Ruby Jewel)
                .texOffs(84, 24).addBox(-1.0F, -6.8F, -5.3F, 2.0F, 2.0F, 1.0F)
                // 胸颈金丝项圈 (Braided Golden Neck Collar)
                .texOffs(92, 24).addBox(-3.0F, 0.5F, -3.0F, 6.0F, 2.0F, 4.0F),
                PartPose.offset(0.0F, -2.0F, -5.5F));

        // =========================================================================
        // 4. 灵动咀嚼三瓣嘴 (Head -> Mouth)
        // =========================================================================
        head.addOrReplaceChild("mouth", CubeListBuilder.create()
                // 上吻部粉鼻与三瓣唇 (Pink Nose & Three-Cleft Upper Lip)
                .texOffs(0, 38).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F)
                // 软萌下颌绒毛 (Cute Chin Tuft)
                .texOffs(12, 38).addBox(-1.0F, 1.0F, -1.8F, 2.0F, 1.0F, 2.0F),
                PartPose.offset(0.0F, -0.5F, -4.5F));

        // =========================================================================
        // 5. 胸前垂吊金铃 (Head -> Bell)
        // =========================================================================
        head.addOrReplaceChild("bell", CubeListBuilder.create()
                // 金铃主体 (Golden Bell Body)
                .texOffs(72, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F)
                // 铃舌底扣 (Bell Clapper Ring)
                .texOffs(82, 30).addBox(-0.5F, 2.5F, -0.5F, 1.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 2.5F, -2.5F));

        // =========================================================================
        // 6. 飞天仙耳体系 (Head -> Ears -> Tips)
        // 羽翼般向后向上飞扬的飞天仙耳，内外耳廓分明，次级飘拂
        // =========================================================================
        PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                // 左耳主耳廓与内耳凹槽 (Main Left Ear Blade)
                .texOffs(0, 46).addBox(-1.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F)
                // 飞羽仙翼后缘 (Flared Wing Feather Crest)
                .texOffs(12, 46).addBox(-0.5F, -7.0F, 1.5F, 1.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(2.0F, -4.2F, -1.5F, -0.35F, -0.2F, 0.45F));

        // 左耳次级飘拂仙羽 (Left Ear Tip Streamer)
        leftEar.addOrReplaceChild("left_ear_tip", CubeListBuilder.create()
                .texOffs(20, 46).addBox(-0.5F, -4.0F, -1.5F, 1.0F, 4.0F, 3.0F)
                .texOffs(30, 46).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                // 右耳主耳廓与内耳凹槽 (Main Right Ear Blade)
                .texOffs(40, 46).addBox(-1.0F, -8.0F, -1.5F, 2.0F, 8.0F, 3.0F)
                // 飞羽仙翼后缘 (Flared Wing Feather Crest)
                .texOffs(52, 46).addBox(-0.5F, -7.0F, 1.5F, 1.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(-2.0F, -4.2F, -1.5F, -0.35F, 0.2F, -0.45F));

        // 右耳次级飘拂仙羽 (Right Ear Tip Streamer)
        rightEar.addOrReplaceChild("right_ear_tip", CubeListBuilder.create()
                .texOffs(60, 46).addBox(-0.5F, -4.0F, -1.5F, 1.0F, 4.0F, 3.0F)
                .texOffs(70, 46).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, -8.0F, 0.0F));

        // =========================================================================
        // 7. 前肢小肉爪体系 (Body -> Front Legs)
        // =========================================================================
        body.addOrReplaceChild("leg_front_left", CubeListBuilder.create()
                // 前肢管骨小腿
                .texOffs(0, 62).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F)
                // 踩地粉嫩小肉爪 (Cute Paw Pads)
                .texOffs(10, 62).addBox(-1.0F, 3.0F, -2.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(2.2F, 2.0F, -3.5F));

        body.addOrReplaceChild("leg_front_right", CubeListBuilder.create()
                // 前肢管骨小腿
                .texOffs(20, 62).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F)
                // 踩地粉嫩小肉爪 (Cute Paw Pads)
                .texOffs(30, 62).addBox(-1.0F, 3.0F, -2.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(-2.2F, 2.0F, -3.5F));

        // =========================================================================
        // 8. 丰满后肢与弹跳蹬地足体系 (Body -> Hind Legs -> Feet)
        // =========================================================================
        PartDefinition legHindLeft = body.addOrReplaceChild("leg_hind_left", CubeListBuilder.create()
                // 丰满大腿肌群 (Chubby Muscular Thigh)
                .texOffs(40, 62).addBox(-1.0F, -1.0F, -2.5F, 3.0F, 5.0F, 5.0F),
                PartPose.offset(3.0F, 0.5F, 2.5F));

        legHindLeft.addOrReplaceChild("left_hind_foot", CubeListBuilder.create()
                // 弹跳蹬地后足 (Elongated Hopping Foot)
                .texOffs(58, 62).addBox(-1.0F, 0.5F, -5.0F, 2.0F, 2.0F, 6.0F),
                PartPose.offset(0.5F, 4.0F, 0.5F));

        PartDefinition legHindRight = body.addOrReplaceChild("leg_hind_right", CubeListBuilder.create()
                // 丰满大腿肌群 (Chubby Muscular Thigh)
                .texOffs(76, 62).addBox(-2.0F, -1.0F, -2.5F, 3.0F, 5.0F, 5.0F),
                PartPose.offset(-3.0F, 0.5F, 2.5F));

        legHindRight.addOrReplaceChild("right_hind_foot", CubeListBuilder.create()
                // 弹跳蹬地后足 (Elongated Hopping Foot)
                .texOffs(94, 62).addBox(-1.0F, 0.5F, -5.0F, 2.0F, 2.0F, 6.0F),
                PartPose.offset(-0.5F, 4.0F, 0.5F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // =========================================================================
        // 1. 待机呼吸与身体起伏 (Idle Breathing & Torso Float)
        // =========================================================================
        float breath = Mth.sin(age * 0.08F);
        float idleBob = breath * 0.25F;

        // 弹跳周期运动 (Hopping Walk Cycle)
        float hopFreq = 0.8F;
        float hopPhase = walkPos * hopFreq;
        float hopSin = Mth.sin(hopPhase);
        // 滞空腾跃：当 sin 为负半周时兔躯向空中腾起
        float hopHeight = Math.max(0.0F, -hopSin) * 3.5F * walkSpeed;

        // 兔躯高度与倾角：跳跃腾空时前倾，落地缓冲时微仰
        this.body.y = 17.0F + idleBob - hopHeight;
        this.body.xRot = (hopSin * 0.22F) * walkSpeed + (breath * 0.015F);
        this.body.zRot = Mth.sin(hopPhase * 0.5F) * 0.04F * walkSpeed;

        // =========================================================================
        // 2. 玉首注视追踪与灵动咀嚼三瓣嘴 (Head Tracking, Chewing & Breathing)
        // =========================================================================
        float headPitch = state.xRot * ((float) Math.PI / 180F);
        float headYaw = state.yRot * ((float) Math.PI / 180F);

        this.head.xRot = headPitch * 0.7F + (breath * 0.02F);
        this.head.yRot = headYaw * 0.7F;
        this.head.zRot = headYaw * 0.08F; // 偏头探望仙韵

        // 灵动三瓣嘴微颤与周期性咀嚼呼吸 (Rabbit nose and cleft-mouth twitching)
        float chewCycle = Mth.sin(age * 0.35F);
        float chewActive = (Mth.sin(age * 0.06F) > -0.3F) ? 1.0F : 0.2F;
        this.mouth.y = -0.5F + chewCycle * 0.18F * chewActive;
        this.mouth.xRot = chewCycle * 0.05F * chewActive;

        // =========================================================================
        // 3. 飞天仙耳微风轻晃与次级飘拂 (Celestial Ears Flutter & Secondary Streamers)
        // =========================================================================
        float earSway = Mth.sin(age * 0.07F) * 0.06F;
        float earBreeze = Mth.cos(age * 0.05F) * 0.05F;
        float earWind = walkSpeed * 0.22F; // 奔跑时仙耳向后破风飘逸

        this.leftEar.zRot = 0.45F + earSway;
        this.rightEar.zRot = -0.45F - earSway;
        this.leftEar.xRot = -0.35F + earBreeze + earWind;
        this.rightEar.xRot = -0.35F + earBreeze + earWind;

        // 仙耳耳梢次级飘拂 (Secondary Flutter with phase delay)
        float tipFlutter = Mth.sin(age * 0.07F - 0.45F) * 0.10F;
        float tipWind = walkSpeed * 0.32F;
        this.leftEarTip.zRot = tipFlutter;
        this.rightEarTip.zRot = -tipFlutter;
        this.leftEarTip.xRot = Mth.cos(age * 0.05F - 0.45F) * 0.08F + tipWind;
        this.rightEarTip.xRot = Mth.cos(age * 0.05F - 0.45F) * 0.08F + tipWind;

        // =========================================================================
        // 4. 金铃项圈垂胸金铃惯性微摆 (Dangling Bell Inertia & Swing)
        // =========================================================================
        float bellHopSwing = Mth.sin(hopPhase - 0.4F) * 0.35F * walkSpeed;
        float bellIdle = Mth.sin(age * 0.12F) * 0.10F;
        this.bell.zRot = bellIdle + (Mth.sin(age * 0.22F) * 0.05F);
        this.bell.xRot = bellHopSwing + (idleBob * 0.12F);

        // =========================================================================
        // 5. 四肢弹跳与蹬地缓冲运动学 (Quadruped Hopping Biomechanics)
        // =========================================================================
        // 后腿蓄力蹬地：起跃阶段大幅后蹬，着陆时前收蓄力
        float hindKick = hopSin * 0.85F * walkSpeed;
        this.legHindLeft.xRot = hindKick;
        this.legHindRight.xRot = hindKick;

        // 蹬地后足屈伸：起跃蹬伸，滞空自然舒展，落地时平踏受力
        this.leftHindFoot.xRot = -hindKick * 0.65F;
        this.rightHindFoot.xRot = -hindKick * 0.65F;

        // 前肢缓冲前伸：起跳时收拢，下落时向前伸展触地缓冲
        float frontReach = -hopSin * 0.7F * walkSpeed;
        this.legFrontLeft.xRot = frontReach;
        this.legFrontRight.xRot = frontReach;

        // =========================================================================
        // 6. 毛茸球尾上下弹摆 (Fluffy Ball Tail Bouncing)
        // =========================================================================
        float tailBounce = Mth.cos(hopPhase) * 0.45F * walkSpeed;
        this.tail.xRot = -0.2F + tailBounce + (breath * 0.03F);
        this.tail.yRot = Mth.sin(age * 0.09F) * 0.12F;
    }
}
