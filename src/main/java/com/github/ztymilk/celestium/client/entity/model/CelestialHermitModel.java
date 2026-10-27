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
 * 逍遥仙翁 / 天界隐士 (Celestial Hermit) - "神兽构思师"树状解剖体系架构 (Anatomical Tree Architecture)
 * <p>
 * 隐居蓬莱仙岛、深谙长生大道的古仙隐士：
 * 仙风道骨，鹤发童颜，高束发髻，碧玉簪横贯其顶，左右飘垂青丝发带；
 * 身着宽袍大袖素青道袍，交领右衽，腰系九霄如意丝绦与太极阴阳玉佩，下垂仙风前摆与后襟；
 * 颔下蓄有飘逸脱俗的三段式流云长髯（下颌长须 -> 飘拂中段 -> 拂胸须尖）；
 * 垂天宽袍双云袖如流云卷舒，左手轻捻长生法诀、掐算天机，右手执九节苍龙蟠桃木拐杖，杖首悬挂朱砂造化紫金葫芦；
 * 背披仙家轻绡羽氅披风随天风自然后扬，下着素白云袜与葛麻仙履，步履从容闲适、暗合天地律动。
 * <p>
 * 【解剖树状体系结构 (Anatomical Tree Architecture)】:
 * root
 *  └── body (主躯干素青道袍 + 腰系九霄如意丝绦与太极阴阳玉佩 + 宽厚仙风下摆基座)
 *       ├── head (仙风道骨仙首，鹤发童颜，寿眉仙耳)
 *       │    ├── hair_bun (高耸道冠发髻 + 碧玉簪贯顶)
 *       │    │    ├── ribbon_left (左飘垂青丝发带)
 *       │    │    └── ribbon_right (右飘垂青丝发带)
 *       │    └── long_beard (长髯飘拂三段式：下颌长须基段)
 *       │         └── beard_mid (飘拂中段)
 *       │              └── beard_tip (拂胸须尖)
 *       ├── sleeve_left (左垂天宽袍云袖，肩袖上隆 + 流云垂袖 + 祥云翻卷水波下摆)
 *       ├── sleeve_right (右垂天宽袍云袖，肩袖上隆 + 流云垂袖 + 祥云翻卷水波下摆)
 *       ├── arm_left (左臂，内臂 + 掐算天机长生法诀仙手)
 *       ├── arm_right (右臂，内臂 + 扶杖握拳仙手)
 *       │    └── staff (九节苍龙蟠桃木拐杖，杖身九节 + 苍龙杖首 + 蟠桃分枝)
 *       │         └── gourd (朱砂造化紫金葫芦，上肚 + 细腰 + 下肚 + 朱砂绳穗)
 *       ├── cloak_back (仙家轻绡披风/羽氅，肩领羽帔 + 轻绡中段 + 后扬羽裾)
 *       ├── robe_front (仙风前摆，垂至脚面，随步履轻荡)
 *       ├── robe_back (仙风后襟，随步履从容后扬)
 *       ├── leg_left (素白云袜 + 葛麻仙履)
 *       └── leg_right (素白云袜 + 葛麻仙履)
 */
public class CelestialHermitModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("celestial_hermit"), "main");

    // 仙躯主干与下摆羽氅
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart robeFront;
    private final ModelPart robeBack;
    private final ModelPart cloakBack;

    // 仙首五官发髻与三段流云长髯
    private final ModelPart head;
    private final ModelPart hairBun;
    private final ModelPart ribbonLeft;
    private final ModelPart ribbonRight;
    private final ModelPart beardUpper;
    private final ModelPart beardMid;
    private final ModelPart beardTip;

    // 垂天宽袍双云袖
    private final ModelPart sleeveLeft;
    private final ModelPart sleeveRight;

    // 仙臂、法诀与万年蟠桃木拐杖葫芦
    private final ModelPart armLeft;
    private final ModelPart armRight;
    private final ModelPart staff;
    private final ModelPart gourd;

    // 素袜仙履下肢
    private final ModelPart legLeft;
    private final ModelPart legRight;

    public CelestialHermitModel(ModelPart root) {
        super(root);
        this.root = root;

        // 躯干主干与前后袍摆、羽氅披风
        this.body = root.getChild("body");
        this.robeFront = this.body.getChild("robe_front");
        this.robeBack = this.body.getChild("robe_back");
        this.cloakBack = this.body.getChild("cloak_back");

        // 仙首、发髻、飘垂发带与三段式流云长髯
        this.head = this.body.getChild("head");
        this.hairBun = this.head.getChild("hair_bun");
        this.ribbonLeft = this.hairBun.getChild("ribbon_left");
        this.ribbonRight = this.hairBun.getChild("ribbon_right");
        this.beardUpper = this.head.getChild("long_beard");
        this.beardMid = this.beardUpper.getChild("beard_mid");
        this.beardTip = this.beardMid.getChild("beard_tip");

        // 垂天流云双袖
        this.sleeveLeft = this.body.getChild("sleeve_left");
        this.sleeveRight = this.body.getChild("sleeve_right");

        // 左手掐算天机法诀，右手执九节苍龙蟠桃木拐杖
        this.armLeft = this.body.getChild("arm_left");
        this.armRight = this.body.getChild("arm_right");
        this.staff = this.armRight.getChild("staff");
        this.gourd = this.staff.getChild("gourd");

        // 素白云袜与葛麻仙履
        this.legLeft = this.body.getChild("leg_left");
        this.legRight = this.body.getChild("leg_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 仙躯主干与素青道袍 (Celestial Torso, Robe, Sash & Taiji Pendant)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 主躯干素青道袍胸膛 (Upper Robe Torso)
                .texOffs(0, 23).addBox(-4.5F, 0.0F, -2.5F, 9.0F, 12.0F, 5.0F)
                // 道袍交领右衽领口 (Daoist Cross Collar Lapel)
                .texOffs(28, 23).addBox(-3.5F, 0.0F, -2.8F, 7.0F, 4.0F, 1.0F)
                // 九霄如意丝绦锦带 (Nine-Heavens Auspicious Silk Sash Belt)
                .texOffs(44, 23).addBox(-5.0F, 9.5F, -2.8F, 10.0F, 2.0F, 6.0F)
                // 太极阴阳玉佩 (Taiji Yin-Yang Jade Medallion)
                .texOffs(76, 23).addBox(-1.5F, 11.5F, -3.2F, 3.0F, 4.0F, 1.0F)
                // 丝绦飘垂朱砂玉绦尾 (Sash Streamer Ribbons)
                .texOffs(84, 23).addBox(-1.0F, 13.5F, -3.0F, 2.0F, 7.0F, 1.0F)
                // 宽袍下摆两侧基底 (Lower Robe Skirt Flanks)
                .texOffs(0, 41).addBox(-5.0F, 11.5F, -2.5F, 10.0F, 11.0F, 5.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 仙风前摆与后襟 (Flowing Robe Front & Back Articulated Panels)
        body.addOrReplaceChild("robe_front", CubeListBuilder.create()
                .texOffs(30, 41).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 11.0F, 1.0F),
                PartPose.offset(0.0F, 11.5F, -2.6F));

        body.addOrReplaceChild("robe_back", CubeListBuilder.create()
                .texOffs(50, 41).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 11.0F, 1.0F),
                PartPose.offset(0.0F, 11.5F, 2.6F));

        // =========================================================================
        // 2. 仙首鹤发、道冠发髻、玉簪发带与三段流云长髯 (Immortal Head & Facial Features)
        // =========================================================================
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 仙首颅骨与面容 (Cranium & Gentle Sage Face)
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                // 脑后披拂白发 (Long Silver Hair Draping Down Back)
                .texOffs(56, 3).addBox(-3.0F, -4.0F, 3.8F, 6.0F, 8.0F, 1.0F)
                // 仙风寿眉 (Floating Long Wispy Eyebrows)
                .texOffs(70, 0).addBox(-3.5F, -5.5F, -4.2F, 7.0F, 1.0F, 1.0F)
                // 左右长眉仙耳 (Left & Right Ears)
                .texOffs(70, 3).addBox(4.0F, -5.0F, -1.0F, 1.0F, 2.0F, 2.0F)
                .texOffs(76, 3).addBox(-5.0F, -5.0F, -1.0F, 1.0F, 2.0F, 2.0F)
                // 左右八字银白仙须 (Left & Right Mustache Wisps)
                .texOffs(28, 16).addBox(0.5F, -1.0F, -4.2F, 2.0F, 1.0F, 1.0F)
                .texOffs(34, 16).addBox(-2.5F, -1.0F, -4.2F, 2.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 高耸道冠/发髻 + 碧玉簪贯顶 + 左右飘垂青丝发带 (Hair Bun, Jade Hairpin & Ribbons)
        PartDefinition hairBun = head.addOrReplaceChild("hair_bun", CubeListBuilder.create()
                // 高束发髻道冠 (Coiled Daoist Topknot Bun)
                .texOffs(32, 0).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 5.0F, 4.0F)
                // 横贯碧玉簪 (Imperial Emerald Jade Hairpin)
                .texOffs(48, 0).addBox(-4.5F, -3.5F, 0.5F, 9.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, -8.0F, 0.0F));

        hairBun.addOrReplaceChild("ribbon_left", CubeListBuilder.create()
                .texOffs(48, 3).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 8.0F, 1.0F),
                PartPose.offset(2.2F, -3.0F, 1.0F));

        hairBun.addOrReplaceChild("ribbon_right", CubeListBuilder.create()
                .texOffs(52, 3).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 8.0F, 1.0F),
                PartPose.offset(-2.2F, -3.0F, 1.0F));

        // 飘逸三段长髯仙须 (Flowing Triple-Section Celestial Chin Beard)
        PartDefinition beardUpper = head.addOrReplaceChild("long_beard", CubeListBuilder.create()
                // 下颌长须基段 (Beard Upper)
                .texOffs(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 4.0F, 2.0F),
                PartPose.offset(0.0F, 0.0F, -3.2F));

        PartDefinition beardMid = beardUpper.addOrReplaceChild("beard_mid", CubeListBuilder.create()
                // 飘拂中段 (Beard Mid)
                .texOffs(12, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F),
                PartPose.offset(0.0F, 3.8F, 0.0F));

        beardMid.addOrReplaceChild("beard_tip", CubeListBuilder.create()
                // 拂胸须尖 (Beard Tip)
                .texOffs(22, 16).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F),
                PartPose.offset(0.0F, 3.8F, 0.0F));

        // =========================================================================
        // 3. 垂天宽袍云袖 (Immortal Cascading Cloud Sleeves)
        // =========================================================================
        body.addOrReplaceChild("sleeve_left", CubeListBuilder.create()
                // 左肩宽袖上隆 (Left Shoulder Flare)
                .texOffs(0, 58).addBox(-0.5F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F)
                // 垂天宽袍云袖垂摆 (Left Flowing Hanging Sleeve Drape)
                .texOffs(22, 58).addBox(0.5F, 2.0F, -3.0F, 4.0F, 10.0F, 6.0F)
                // 袖口祥云翻卷水波下摆 (Left Sleeve Ruffle Hem)
                .texOffs(42, 58).addBox(1.0F, 12.0F, -2.5F, 3.0F, 3.0F, 5.0F),
                PartPose.offset(5.0F, 2.0F, 0.0F));

        body.addOrReplaceChild("sleeve_right", CubeListBuilder.create()
                // 右肩宽袖上隆 (Right Shoulder Flare)
                .texOffs(0, 75).addBox(-4.5F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F)
                // 垂天宽袍云袖垂摆 (Right Flowing Hanging Sleeve Drape)
                .texOffs(22, 75).addBox(-4.5F, 2.0F, -3.0F, 4.0F, 10.0F, 6.0F)
                // 袖口祥云翻卷水波下摆 (Right Sleeve Ruffle Hem)
                .texOffs(42, 75).addBox(-4.0F, 12.0F, -2.5F, 3.0F, 3.0F, 5.0F),
                PartPose.offset(-5.0F, 2.0F, 0.0F));

        // =========================================================================
        // 4. 双臂、掐算法诀与执万年蟠桃拐杖 (Arms, Mudra & Longevity Peach Staff)
        // =========================================================================
        // 左臂：轻捻法诀、掐算天机
        body.addOrReplaceChild("arm_left", CubeListBuilder.create()
                // 左内臂 (Left Inner Arm)
                .texOffs(58, 58).addBox(0.0F, -1.5F, -1.5F, 3.0F, 9.0F, 3.0F)
                // 左手轻捻法诀仙手 (Left Mudra Hand Palm)
                .texOffs(70, 58).addBox(0.5F, 7.5F, -2.0F, 2.0F, 3.0F, 3.0F)
                // 掐算天机法诀指端 (Left Mudra Pointing Fingers)
                .texOffs(80, 58).addBox(0.5F, 7.0F, -3.5F, 1.0F, 2.0F, 2.0F),
                PartPose.offset(5.0F, 2.0F, 0.0F));

        // 右臂：执万年蟠桃木拐杖
        PartDefinition armRight = body.addOrReplaceChild("arm_right", CubeListBuilder.create()
                // 右内臂 (Right Inner Arm)
                .texOffs(58, 71).addBox(-3.0F, -1.5F, -1.5F, 3.0F, 9.0F, 3.0F)
                // 右手扶杖握拳 (Right Grasping Hand)
                .texOffs(70, 71).addBox(-2.5F, 7.5F, -2.0F, 2.0F, 3.0F, 3.0F),
                PartPose.offset(-5.0F, 2.0F, 0.0F));

        // 九节苍龙蟠桃木拐杖 (Nine-Node Canglong Longevity Peach Staff)
        PartDefinition staff = armRight.addOrReplaceChild("staff", CubeListBuilder.create()
                // 九节盘陀木主杖身 (Staff Main Shaft)
                .texOffs(88, 32).addBox(-1.0F, -14.0F, -1.0F, 2.0F, 28.0F, 2.0F)
                // 苍龙杖首曲木 (Dragon Head Knob)
                .texOffs(96, 32).addBox(-1.5F, -18.0F, -3.0F, 3.0F, 4.0F, 5.0F)
                // 苍龙角须/蟠桃古木分枝 (Dragon Brow / Branch Tine)
                .texOffs(112, 32).addBox(-1.0F, -19.0F, -5.0F, 2.0F, 2.0F, 3.0F),
                PartPose.offset(-1.5F, 8.5F, -0.5F));

        // 朱砂造化紫金葫芦 (Cinnabar Purple-Gold Gourd of Creation)
        staff.addOrReplaceChild("gourd", CubeListBuilder.create()
                // 葫芦上肚 (Gourd Upper Lobe)
                .texOffs(96, 42).addBox(-1.5F, 2.0F, -1.5F, 3.0F, 3.0F, 3.0F)
                // 葫芦细腰 (Gourd Waist)
                .texOffs(108, 42).addBox(-1.0F, 5.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                // 葫芦下肚 (Gourd Lower Lobe)
                .texOffs(96, 49).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F)
                // 朱砂绳结与底穗 (Cinnabar Cord & Tassel)
                .texOffs(112, 49).addBox(-0.5F, 10.0F, -0.5F, 1.0F, 4.0F, 1.0F),
                PartPose.offset(0.0F, -14.0F, -2.5F));

        // =========================================================================
        // 5. 仙家轻绡披风/羽氅 (Celestial Feather Mantle / Gossamer Cloak)
        // =========================================================================
        body.addOrReplaceChild("cloak_back", CubeListBuilder.create()
                // 披风肩领羽帔上段 (Cloak Upper Mantle)
                .texOffs(0, 92).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 4.0F, 1.0F)
                // 披风轻绡中段 (Cloak Mid Section)
                .texOffs(26, 92).addBox(-5.5F, 4.0F, 0.2F, 11.0F, 8.0F, 1.0F)
                // 披风随风后扬羽裾下段 (Cloak Lower Fluttering Hem)
                .texOffs(50, 92).addBox(-6.0F, 12.0F, 0.5F, 12.0F, 9.0F, 1.0F),
                PartPose.offset(0.0F, 1.0F, 2.6F));

        // =========================================================================
        // 6. 素白云袜与葛麻仙履 (Cloud Socks & Hemp Immortal Shoes)
        // =========================================================================
        body.addOrReplaceChild("leg_left", CubeListBuilder.create()
                // 素白云袜左腿 (Left Leg Cloud Sock)
                .texOffs(0, 103).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F)
                // 葛麻仙履左履 (Left Hemp Immortal Shoe)
                .texOffs(12, 103).addBox(-1.5F, 10.0F, -2.5F, 3.0F, 2.0F, 4.0F),
                PartPose.offset(2.0F, 12.0F, 0.0F));

        body.addOrReplaceChild("leg_right", CubeListBuilder.create()
                // 素白云袜右腿 (Right Leg Cloud Sock)
                .texOffs(26, 103).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F)
                // 葛麻仙履右履 (Right Hemp Immortal Shoe)
                .texOffs(38, 103).addBox(-1.5F, 10.0F, -2.5F, 3.0F, 2.0F, 4.0F),
                PartPose.offset(-2.0F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        float age = state.ageInTicks;

        // 1. 祥瑞呼吸与身形微浪律动 (Chest Breathing & Serene Floating)
        float breathCycle = age * 0.05F;
        float breathSin = Mth.sin(breathCycle);
        float breathCos = Mth.cos(breathCycle);

        this.body.y = breathSin * 0.25F;
        this.body.xRot = breathSin * 0.015F;

        // 仙隐踱步步态相位 (Slow, dignified, leisurely walking phase)
        float walkPhase = walkPos * 0.45F;
        this.body.zRot = Mth.cos(walkPhase) * 0.025F * walkSpeed;

        // 2. 仙首观天与顾盼巡视四方 (Celestial Head Gaze & Look Motion)
        float headPitch = state.xRot * ((float) Math.PI / 180F);
        float headYaw = state.yRot * ((float) Math.PI / 180F);

        // 仙首微仰观天 (-0.04F)，随呼吸从容微仰
        this.head.xRot = headPitch - 0.04F + breathSin * 0.02F;
        this.head.yRot = headYaw;
        this.head.zRot = breathCos * 0.015F;

        // 发带微随清风轻扬 (Hair ribbons fluttering in breeze)
        this.ribbonLeft.xRot = 0.08F + Mth.sin(age * 0.07F) * 0.06F + walkSpeed * 0.15F;
        this.ribbonLeft.zRot = 0.05F + Mth.cos(age * 0.06F) * 0.04F;
        this.ribbonRight.xRot = 0.08F + Mth.sin(age * 0.07F + 0.5F) * 0.06F + walkSpeed * 0.15F;
        this.ribbonRight.zRot = -0.05F - Mth.cos(age * 0.06F + 0.5F) * 0.04F;

        // 3. 飘逸三段长髯仙须随步履与天风自然滞后飘拂 (Flowing Triple-Section Beard with Inertia & Flutter)
        float beardWave = age * 0.08F + walkPos * 0.20F;
        float beardLag = this.head.yRot * 0.25F;

        // 下颌长须基段
        this.beardUpper.xRot = 0.08F + Mth.sin(beardWave) * 0.05F + walkSpeed * 0.12F - Math.max(0.0F, headPitch * 0.3F);
        this.beardUpper.yRot = -beardLag * 0.4F + Mth.cos(beardWave * 0.7F) * 0.03F;
        this.beardUpper.zRot = Mth.cos(beardWave * 0.7F) * 0.02F;

        // 飘拂中段
        this.beardMid.xRot = 0.10F + Mth.sin(beardWave - 0.4F) * 0.08F + walkSpeed * 0.18F;
        this.beardMid.yRot = -beardLag * 0.7F + Mth.cos(beardWave * 0.7F - 0.4F) * 0.05F;
        this.beardMid.zRot = Mth.cos(beardWave * 0.7F - 0.4F) * 0.04F;

        // 拂胸须尖
        this.beardTip.xRot = 0.14F + Mth.sin(beardWave - 0.8F) * 0.12F + walkSpeed * 0.24F;
        this.beardTip.yRot = -beardLag + Mth.cos(beardWave * 0.7F - 0.8F) * 0.08F;
        this.beardTip.zRot = Mth.cos(beardWave * 0.7F - 0.8F) * 0.06F;

        // 4. 左手轻捻法诀、掐算天机 (Left Hand Mudra & Divination Gesture)
        this.armLeft.xRot = -0.32F + breathSin * 0.04F + Mth.sin(walkPhase + (float) Math.PI) * 0.12F * walkSpeed;
        this.armLeft.yRot = 0.22F + breathCos * 0.02F;
        this.armLeft.zRot = 0.12F;

        // 5. 右手执万年蟠桃木拐杖与紫金葫芦微摆 (Right Hand Staff Ground Touch & Gourd Pendulum Motion)
        this.armRight.xRot = -0.15F + Mth.cos(walkPhase) * 0.18F * walkSpeed;
        this.armRight.yRot = -0.08F;
        this.armRight.zRot = -0.05F;

        // 拐杖自适应点地支撑角度
        this.staff.xRot = 0.05F - Mth.cos(walkPhase) * 0.08F * walkSpeed;
        this.staff.zRot = 0.02F;

        // 杖头朱砂造化紫金葫芦动力学惯性摆动
        float gourdWave = age * 0.07F + walkPos * 0.3F;
        this.gourd.xRot = 0.12F + Mth.sin(gourdWave) * 0.14F * (0.3F + walkSpeed * 0.7F);
        this.gourd.zRot = Mth.cos(gourdWave * 0.8F) * 0.10F * (0.3F + walkSpeed * 0.7F);

        // 6. 垂天宽袍云袖如流云卷舒 (Billowing Cloud Sleeves)
        float sleeveWave = age * 0.065F + walkPos * 0.22F;
        this.sleeveLeft.xRot = this.armLeft.xRot * 0.8F + Mth.sin(sleeveWave) * 0.08F + walkSpeed * 0.15F;
        this.sleeveLeft.yRot = this.armLeft.yRot * 0.6F;
        this.sleeveLeft.zRot = 0.08F + Mth.cos(sleeveWave * 0.8F) * 0.05F;

        this.sleeveRight.xRot = this.armRight.xRot * 0.8F + Mth.sin(sleeveWave + 0.5F) * 0.08F + walkSpeed * 0.15F;
        this.sleeveRight.yRot = this.armRight.yRot * 0.6F;
        this.sleeveRight.zRot = -0.08F - Mth.cos(sleeveWave * 0.8F + 0.5F) * 0.05F;

        // 7. 仙家轻绡披风/羽氅随天风后扬卷动 (Cloak Fluttering in Celestial Wind)
        this.cloakBack.xRot = 0.08F + walkSpeed * 0.32F + Mth.sin(age * 0.055F) * 0.05F + Mth.cos(walkPhase) * 0.06F * walkSpeed;
        this.cloakBack.yRot = Mth.sin(age * 0.04F) * 0.02F;
        this.cloakBack.zRot = Mth.cos(age * 0.045F) * 0.025F;

        // 8. 仙风前摆与后襟如水波荡漾 (Flowing Robe Front & Back Panels)
        this.robeFront.xRot = -0.05F - Math.max(0.0F, Mth.sin(walkPhase) * 0.22F) * walkSpeed
                - Math.max(0.0F, Mth.sin(walkPhase + (float) Math.PI) * 0.22F) * walkSpeed
                - walkSpeed * 0.08F + breathSin * 0.03F;
        this.robeBack.xRot = 0.06F + walkSpeed * 0.28F + Mth.sin(age * 0.06F) * 0.04F + Mth.cos(walkPhase) * 0.08F * walkSpeed;

        // 9. 素白云袜与葛麻仙履从容漫步 (Legs Walking Motion)
        this.legLeft.xRot = Mth.cos(walkPhase) * 0.42F * walkSpeed;
        this.legRight.xRot = Mth.cos(walkPhase + (float) Math.PI) * 0.42F * walkSpeed;
        this.legLeft.yRot = 0.0F;
        this.legRight.yRot = 0.0F;
        this.legLeft.zRot = 0.02F;
        this.legRight.zRot = -0.02F;
    }
}
