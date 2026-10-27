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
 * 天庭神将·降魔元帅 (Heavenly General / Demon-Subduing Marshal)
 * 统御十万天兵天将、执掌天宪诛邪降魔的神威大将，身形巍峨高大，
 * 身披九阳紫金龙鳞锁子重铠，胸嵌护心琉璃金镜，腰系狰狞龙首金吞腹与厚重战裙重甲；
 * 顶戴紫金飞凤冲霄战盔、双侧冲天金凤翅角、左右双凤展翼护耳与高耸翻卷的三色长翎雉鸡翎；
 * 双肩负巨大饕餮吞肩重铠，背负四面金边降魔令旗“风火雷电”，外罩猩红暗纹宽大征袍披风；
 * 右手稳持降魔神锋三尖两刃神戟，左手握拳按剑威震八荒，步伐沉稳如山崩地裂。
 *
 * 树状解剖体系架构 (Anatomical Tree Architecture):
 * root
 *  └── body (主躯干，紫金龙鳞胸铠 + 护心琉璃金镜 + 龙首金吞腹 + 束甲金带 + 战裙重甲甲叶)
 *       ├── head (威严神将金面，三目神印，紫金飞凤冲霄战盔 + 双凤护耳)
 *       │    ├── helmet_horns (战盔双侧冲天金角/凤翅)
 *       │    ├── pheasant_tail_l (左条高耸翻卷雉鸡翎)
 *       │    └── pheasant_tail_r (右条高耸翻卷雉鸡翎)
 *       ├── shoulder_left (巨大重型饕餮吞肩重甲，多层甲片重叠)
 *       ├── shoulder_right (巨大重型饕餮吞肩重甲，多层甲片重叠)
 *       ├── back_banners (背后靠旗架：四面金边降魔令旗“风火雷电”)
 *       │    ├── banner_top_l (靠旗“风”)
 *       │    ├── banner_top_r (靠旗“火”)
 *       │    ├── banner_mid_l (靠旗“雷”)
 *       │    └── banner_mid_r (靠旗“电”)
 *       ├── arm_left (左手重型龙鳞臂铠，按剑握拳威严)
 *       ├── arm_right (右手稳持降魔神锋)
 *       │    └── weapon (降魔神兵：神锋盘龙长柄 + 龙吞口 + 寒芒双刃月牙 + 三尖神锋中刃)
 *       ├── cape (猩红暗纹宽大征袍披风)
 *       ├── leg_left (紫金吊腿 + 金猊护膝 + 龙鳞胫甲 + 龙纹战靴)
 *       └── leg_right (紫金吊腿 + 金猊护膝 + 龙鳞胫甲 + 龙纹战靴)
 */
public class HeavenlyGeneralModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("heavenly_general"), "main");

    // 树状解剖体系架构各部位
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart helmetHorns;
    private final ModelPart pheasantTailL;
    private final ModelPart pheasantTailR;
    private final ModelPart shoulderLeft;
    private final ModelPart shoulderRight;
    private final ModelPart backBanners;
    private final ModelPart bannerTopL;
    private final ModelPart bannerTopR;
    private final ModelPart bannerMidL;
    private final ModelPart bannerMidR;
    private final ModelPart armLeft;
    private final ModelPart armRight;
    private final ModelPart weapon;
    private final ModelPart cape;
    private final ModelPart legLeft;
    private final ModelPart legRight;

    public HeavenlyGeneralModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.helmetHorns = this.head.getChild("helmet_horns");
        this.pheasantTailL = this.head.getChild("pheasant_tail_l");
        this.pheasantTailR = this.head.getChild("pheasant_tail_r");
        this.shoulderLeft = this.body.getChild("shoulder_left");
        this.shoulderRight = this.body.getChild("shoulder_right");
        this.backBanners = this.body.getChild("back_banners");
        this.bannerTopL = this.backBanners.getChild("banner_top_l");
        this.bannerTopR = this.backBanners.getChild("banner_top_r");
        this.bannerMidL = this.backBanners.getChild("banner_mid_l");
        this.bannerMidR = this.backBanners.getChild("banner_mid_r");
        this.armLeft = this.body.getChild("arm_left");
        this.armRight = this.body.getChild("arm_right");
        this.weapon = this.armRight.getChild("weapon");
        this.cape = this.body.getChild("cape");
        this.legLeft = this.body.getChild("leg_left");
        this.legRight = this.body.getChild("leg_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 主躯干 (Torso: 紫金龙鳞胸铠、护心琉璃金镜、腹部狰狞龙首金吞腹、束甲金带、战裙重甲甲叶)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 紫金龙鳞胸铠
                .texOffs(0, 48).addBox(-5.5F, -1.0F, -3.5F, 11.0F, 7.0F, 7.0F)
                // 护心琉璃金镜
                .texOffs(36, 48).addBox(-3.0F, 0.5F, -4.2F, 6.0F, 5.0F, 1.0F)
                // 腹部狰狞龙首金吞腹
                .texOffs(36, 54).addBox(-3.5F, 6.0F, -4.2F, 7.0F, 4.0F, 2.0F)
                // 束腰神腹
                .texOffs(0, 62).addBox(-4.5F, 6.0F, -3.0F, 9.0F, 5.0F, 6.0F)
                // 束甲金带
                .texOffs(30, 62).addBox(-5.0F, 10.0F, -3.5F, 10.0F, 2.0F, 7.0F)
                // 前铛重甲叶
                .texOffs(0, 73).addBox(-4.0F, 11.5F, -3.6F, 8.0F, 7.0F, 1.0F)
                // 后护臀重甲叶
                .texOffs(18, 73).addBox(-4.0F, 11.5F, 2.6F, 8.0F, 7.0F, 1.0F)
                // 左披膊裙甲
                .texOffs(0, 81).addBox(4.6F, 10.5F, -3.2F, 1.0F, 7.0F, 6.4F)
                // 右披膊裙甲
                .texOffs(14, 81).addBox(-5.6F, 10.5F, -3.2F, 1.0F, 7.0F, 6.4F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 2. 威严神首与战盔 (Head, Neck Collar, Visage & Phoenix Helmet)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 威严神将金面
                .texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                // 紫金锁喉重铠领
                .texOffs(32, 0).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F)
                // 眉心金凤神珠额饰
                .texOffs(32, 8).addBox(-2.0F, -9.0F, -4.8F, 4.0F, 3.0F, 1.0F)
                // 紫金飞凤冲霄战盔
                .texOffs(0, 16).addBox(-4.5F, -11.0F, -4.5F, 9.0F, 5.0F, 9.0F)
                // 冲霄盔梁金脊
                .texOffs(36, 19).addBox(-1.0F, -13.5F, -5.0F, 2.0F, 3.0F, 10.0F)
                // 左双凤展翼护耳
                .texOffs(0, 30).addBox(4.2F, -9.0F, -2.5F, 1.0F, 6.0F, 5.0F)
                // 右双凤展翼护耳
                .texOffs(12, 30).addBox(-5.2F, -9.0F, -2.5F, 1.0F, 6.0F, 5.0F),
                PartPose.offset(0.0F, -1.0F, 0.0F));

        // 2.1 战盔双侧冲天金角/凤翅 (Helmet Horns)
        head.addOrReplaceChild("helmet_horns", CubeListBuilder.create()
                // 左冲天凤翅金角
                .texOffs(24, 32).addBox(4.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F)
                .texOffs(24, 40).addBox(5.0F, -9.0F, 0.0F, 1.0F, 5.0F, 3.0F)
                // 右冲天凤翅金角
                .texOffs(32, 32).addBox(-6.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F)
                .texOffs(32, 40).addBox(-6.0F, -9.0F, 0.0F, 1.0F, 5.0F, 3.0F),
                PartPose.offset(0.0F, -11.0F, 0.0F));

        // 2.2 盔顶双条长翎三色雉鸡翎 (Left & Right Pheasant Plumes)
        head.addOrReplaceChild("pheasant_tail_l", CubeListBuilder.create()
                .texOffs(42, 8).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F)
                .texOffs(50, 8).addBox(-0.5F, -16.0F, 0.5F, 1.0F, 9.0F, 2.0F)
                .texOffs(40, 32).addBox(-0.5F, -14.0F, 2.5F, 1.0F, 7.0F, 3.0F),
                PartPose.offset(1.5F, -13.0F, 1.0F));

        head.addOrReplaceChild("pheasant_tail_r", CubeListBuilder.create()
                .texOffs(46, 8).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F)
                .texOffs(56, 8).addBox(-0.5F, -16.0F, 0.5F, 1.0F, 9.0F, 2.0F)
                .texOffs(48, 32).addBox(-0.5F, -14.0F, 2.5F, 1.0F, 7.0F, 3.0F),
                PartPose.offset(-1.5F, -13.0F, 1.0F));

        // 3. 巨大重型双龙戏珠/饕餮吞肩重甲 (Left & Right Pauldrons)
        body.addOrReplaceChild("shoulder_left", CubeListBuilder.create()
                .texOffs(0, 94).addBox(0.0F, -3.5F, -3.5F, 6.0F, 5.0F, 7.0F)
                .texOffs(26, 94).addBox(1.0F, -5.0F, -2.5F, 4.0F, 2.0F, 5.0F)
                .texOffs(0, 106).addBox(0.5F, 1.5F, -3.0F, 5.0F, 4.0F, 6.0F),
                PartPose.offset(6.0F, 0.5F, 0.0F));

        body.addOrReplaceChild("shoulder_right", CubeListBuilder.create()
                .texOffs(26, 101).addBox(-6.0F, -3.5F, -3.5F, 6.0F, 5.0F, 7.0F)
                .texOffs(44, 94).addBox(-5.0F, -5.0F, -2.5F, 4.0F, 2.0F, 5.0F)
                .texOffs(0, 116).addBox(-5.5F, 1.5F, -3.0F, 5.0F, 4.0F, 6.0F),
                PartPose.offset(-6.0F, 0.5F, 0.0F));

        // 4. 背后靠旗架：四面金边降魔令旗“风火雷电” (Back Banners)
        PartDefinition backBanners = body.addOrReplaceChild("back_banners", CubeListBuilder.create()
                .texOffs(94, 48).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 8.0F, 2.0F)
                .texOffs(94, 58).addBox(-5.0F, 0.0F, 0.5F, 10.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, 1.0F, 3.5F));

        // 4.1 靠旗：左上“风” (Wind Banner - Top Left)
        backBanners.addOrReplaceChild("banner_top_l", CubeListBuilder.create()
                .texOffs(108, 62).addBox(-0.5F, -20.0F, -0.5F, 1.0F, 20.0F, 1.0F)
                .texOffs(116, 79).addBox(-1.0F, -23.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(64, 72).addBox(0.5F, -19.0F, -0.5F, 10.0F, 14.0F, 1.0F),
                PartPose.offsetAndRotation(2.5F, -1.0F, 1.5F, -0.35F, 0.45F, 0.35F));

        // 4.2 靠旗：右上“火” (Fire Banner - Top Right)
        backBanners.addOrReplaceChild("banner_top_r", CubeListBuilder.create()
                .texOffs(112, 62).addBox(-0.5F, -20.0F, -0.5F, 1.0F, 20.0F, 1.0F)
                .texOffs(122, 79).addBox(-1.0F, -23.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(86, 72).addBox(-10.5F, -19.0F, -0.5F, 10.0F, 14.0F, 1.0F),
                PartPose.offsetAndRotation(-2.5F, -1.0F, 1.5F, -0.35F, -0.45F, -0.35F));

        // 4.3 靠旗：左中“雷” (Thunder Banner - Mid Left)
        backBanners.addOrReplaceChild("banner_mid_l", CubeListBuilder.create()
                .texOffs(116, 62).addBox(-0.5F, -16.0F, -0.5F, 1.0F, 16.0F, 1.0F)
                .texOffs(108, 83).addBox(-1.0F, -19.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(64, 87).addBox(0.5F, -15.0F, -0.5F, 9.0F, 12.0F, 1.0F),
                PartPose.offsetAndRotation(2.0F, 3.0F, 1.5F, -0.20F, 0.70F, 0.55F));

        // 4.4 靠旗：右中“电” (Lightning Banner - Mid Right)
        backBanners.addOrReplaceChild("banner_mid_r", CubeListBuilder.create()
                .texOffs(120, 62).addBox(-0.5F, -16.0F, -0.5F, 1.0F, 16.0F, 1.0F)
                .texOffs(114, 83).addBox(-1.0F, -19.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(84, 87).addBox(-9.5F, -15.0F, -0.5F, 9.0F, 12.0F, 1.0F),
                PartPose.offsetAndRotation(-2.0F, 3.0F, 1.5F, -0.20F, -0.70F, -0.55F));

        // 5. 左臂重型龙鳞臂铠 (Left Arm: 龙鳞甲袖、紫金龙鳞重臂铠、护腕金刺、威严按剑)
        body.addOrReplaceChild("arm_left", CubeListBuilder.create()
                .texOffs(64, 0).addBox(-0.5F, -1.0F, -2.0F, 4.0F, 6.0F, 4.0F)
                .texOffs(64, 11).addBox(-1.0F, 5.0F, -2.5F, 5.0F, 7.0F, 5.0F)
                .texOffs(96, 0).addBox(3.5F, 7.0F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(96, 4).addBox(-0.5F, 11.5F, -2.0F, 4.0F, 3.0F, 4.0F),
                PartPose.offsetAndRotation(6.5F, 1.0F, 0.0F, -0.25F, 0.25F, 0.22F));

        // 6. 右臂重铠与神兵 (Right Arm & Weapon: 稳持降魔神兵)
        PartDefinition armRight = body.addOrReplaceChild("arm_right", CubeListBuilder.create()
                .texOffs(80, 0).addBox(-3.5F, -1.0F, -2.0F, 4.0F, 6.0F, 4.0F)
                .texOffs(84, 11).addBox(-4.0F, 5.0F, -2.5F, 5.0F, 7.0F, 5.0F)
                .texOffs(104, 0).addBox(-5.5F, 7.0F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(112, 0).addBox(-3.5F, 11.5F, -2.0F, 4.0F, 3.0F, 4.0F),
                PartPose.offsetAndRotation(-6.5F, 1.0F, 0.0F, -0.40F, -0.15F, -0.05F));

        // 6.1 降魔神兵：三尖两刃神锋 / 降魔神戟 (Weapon: 神锋长柄 + 龙吞口 + 寒芒双刃月牙 + 三尖神锋中刃)
        armRight.addOrReplaceChild("weapon", CubeListBuilder.create()
                // 神锋赤金盘龙长柄
                .texOffs(120, 83).addBox(-1.0F, -22.0F, -1.0F, 2.0F, 40.0F, 2.0F)
                // 龙吞口
                .texOffs(64, 100).addBox(-2.0F, -25.0F, -2.0F, 4.0F, 4.0F, 4.0F)
                // 降魔金鐏配重底
                .texOffs(80, 100).addBox(-1.5F, 18.0F, -1.5F, 3.0F, 3.0F, 3.0F)
                // 神锋中刃三尖主剑
                .texOffs(92, 100).addBox(-1.0F, -37.0F, -0.5F, 2.0F, 12.0F, 1.0F)
                // 寒芒月牙侧刃左
                .texOffs(64, 108).addBox(0.0F, -33.0F, 1.5F, 1.0F, 8.0F, 3.0F)
                // 寒芒月牙侧刃右
                .texOffs(72, 108).addBox(0.0F, -33.0F, -4.5F, 1.0F, 8.0F, 3.0F),
                PartPose.offsetAndRotation(-1.5F, 12.0F, 0.0F, 0.15F, 0.0F, 0.0F));

        // 7. 猩红暗纹宽大征袍披风 (Cape)
        body.addOrReplaceChild("cape", CubeListBuilder.create()
                .texOffs(64, 45).addBox(-5.0F, 0.0F, -0.5F, 10.0F, 2.0F, 1.0F)
                .texOffs(64, 48).addBox(-6.0F, 2.0F, 0.0F, 12.0F, 10.0F, 1.0F)
                .texOffs(64, 59).addBox(-7.0F, 12.0F, 0.5F, 14.0F, 12.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -0.5F, 3.6F, 0.12F, 0.0F, 0.0F));

        // 8. 左腿 (Left Leg: 紫金吊腿 + 金猊护膝 + 龙鳞胫甲 + 龙纹战靴)
        body.addOrReplaceChild("leg_left", CubeListBuilder.create()
                .texOffs(64, 23).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                .texOffs(104, 23).addBox(-2.0F, 4.5F, -3.5F, 4.0F, 3.0F, 2.0F)
                .texOffs(64, 34).addBox(-2.5F, 6.0F, -2.5F, 5.0F, 5.0F, 5.0F)
                .texOffs(104, 28).addBox(-2.5F, 11.0F, -4.5F, 5.0F, 2.0F, 7.0F),
                PartPose.offset(3.0F, 11.0F, 0.0F));

        // 9. 右腿 (Right Leg: 紫金吊腿 + 金猊护膝 + 龙鳞胫甲 + 龙纹战靴)
        body.addOrReplaceChild("leg_right", CubeListBuilder.create()
                .texOffs(84, 23).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                .texOffs(116, 23).addBox(-2.0F, 4.5F, -3.5F, 4.0F, 3.0F, 2.0F)
                .texOffs(84, 34).addBox(-2.5F, 6.0F, -2.5F, 5.0F, 5.0F, 5.0F)
                .texOffs(104, 37).addBox(-2.5F, 11.0F, -4.5F, 5.0F, 2.0F, 7.0F),
                PartPose.offset(-3.0F, 11.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        float age = state.ageInTicks;

        // 1. 仙首顾盼与天庭威严神韵 (Head yaw & pitch tracking + subtle breathing)
        float headPitch = state.xRot * ((float) Math.PI / 180F) * 0.75F;
        float headYaw = state.yRot * ((float) Math.PI / 180F) * 0.75F;
        float breathCycle = age * 0.045F;
        float breathSin = Mth.sin(breathCycle);

        this.head.xRot = headPitch + breathSin * 0.02F;
        this.head.yRot = headYaw;
        this.head.zRot = 0.0F;

        // 2. 岳峙渊渟威严行进 (Dignified Mountain-Firm Walking Gait)
        // 步伐沉稳刚劲如山崩地裂，双肩重甲巍然，战裙甲叶交错
        float walkPhase = walkPos * 0.55F;
        this.legLeft.xRot = Mth.cos(walkPhase) * 0.65F * walkSpeed;
        this.legLeft.yRot = 0.0F;
        this.legLeft.zRot = 0.02F;

        this.legRight.xRot = Mth.cos(walkPhase + (float) Math.PI) * 0.65F * walkSpeed;
        this.legRight.yRot = 0.0F;
        this.legRight.zRot = -0.02F;

        // 躯干重甲威仪微震与呼吸律动
        this.body.y = breathSin * 0.3F + Mth.abs(Mth.sin(walkPhase)) * 0.5F * walkSpeed;
        this.body.zRot = Mth.cos(walkPhase) * 0.02F * walkSpeed;

        // 双肩重甲巍然不动，随步伐威严沉稳微调
        this.shoulderLeft.zRot = 0.04F - Mth.cos(walkPhase) * 0.03F * walkSpeed;
        this.shoulderRight.zRot = -0.04F - Mth.cos(walkPhase) * 0.03F * walkSpeed;

        // 3. 戒备威仪 (Left Arm Dignified Command Stance, Right Arm Weapon Stance)
        // 左手重型龙鳞臂铠，按剑握拳威严沉着
        this.armLeft.xRot = -0.25F + Mth.cos(walkPhase) * 0.20F * walkSpeed;
        this.armLeft.yRot = 0.25F;
        this.armLeft.zRot = 0.22F + breathSin * 0.015F;

        // 右手神兵稳如泰山，持大戟如定海神针
        this.armRight.xRot = -0.40F + Mth.sin(age * 0.04F) * 0.02F - (walkSpeed * 0.05F);
        this.armRight.yRot = -0.15F;
        this.armRight.zRot = -0.05F;
        this.weapon.xRot = 0.15F + breathSin * 0.01F;

        // 4. 靠旗招展 (Back Banners Fluttering Wave with Phase Offsets)
        // 背负的四面降魔令旗“风火雷电”在待机与行走时产生风动波浪相位差翻卷
        float flagWave = age * 0.12F + walkPos * 0.35F;

        // 靠旗左上“风”
        this.bannerTopL.xRot = -0.35F + Mth.sin(flagWave * 0.9F + 0.4F) * 0.08F;
        this.bannerTopL.yRot = 0.45F + Mth.cos(flagWave * 0.8F) * 0.10F;
        this.bannerTopL.zRot = 0.35F + Mth.sin(flagWave) * 0.12F + walkSpeed * 0.10F;

        // 靠旗右上“火” (相位差 +1.2F)
        this.bannerTopR.xRot = -0.35F + Mth.sin(flagWave * 0.9F + 1.6F) * 0.08F;
        this.bannerTopR.yRot = -0.45F - Mth.cos(flagWave * 0.8F + 1.2F) * 0.10F;
        this.bannerTopR.zRot = -0.35F - Mth.sin(flagWave + 1.2F) * 0.12F - walkSpeed * 0.10F;

        // 靠旗左中“雷” (相位差 +2.4F)
        this.bannerMidL.xRot = -0.20F + Mth.sin(flagWave * 0.9F + 2.8F) * 0.10F;
        this.bannerMidL.yRot = 0.70F + Mth.cos(flagWave * 0.8F + 2.4F) * 0.12F;
        this.bannerMidL.zRot = 0.55F + Mth.sin(flagWave + 2.4F) * 0.15F + walkSpeed * 0.15F;

        // 靠旗右中“电” (相位差 +3.6F)
        this.bannerMidR.xRot = -0.20F + Mth.sin(flagWave * 0.9F + 4.0F) * 0.10F;
        this.bannerMidR.yRot = -0.70F - Mth.cos(flagWave * 0.8F + 3.6F) * 0.12F;
        this.bannerMidR.zRot = -0.55F - Mth.sin(flagWave + 3.6F) * 0.15F - walkSpeed * 0.15F;

        // 5. 披风与雉鸡翎动态 (Cape Billowing & Pheasant Plumes Fluttering)
        // 雉鸡翎随头部动作轻颤弹跳
        float plumeWave = age * 0.16F + walkPos * 0.4F;
        this.pheasantTailL.xRot = 0.20F + Mth.sin(plumeWave) * 0.12F - headPitch * 0.25F + walkSpeed * 0.18F;
        this.pheasantTailL.zRot = 0.12F + Mth.cos(plumeWave * 0.8F) * 0.06F;

        this.pheasantTailR.xRot = 0.20F + Mth.sin(plumeWave + 0.8F) * 0.12F - headPitch * 0.25F + walkSpeed * 0.18F;
        this.pheasantTailR.zRot = -0.12F - Mth.cos(plumeWave * 0.8F + 0.8F) * 0.06F;

        // 猩红暗纹征袍大披风向后翻卷波浪
        float capeWave = age * 0.10F + walkPos * 0.45F;
        this.cape.xRot = 0.12F + (walkSpeed * 0.55F) + Mth.sin(capeWave) * (0.06F + walkSpeed * 0.12F);
        this.cape.yRot = Mth.cos(capeWave * 0.5F) * 0.03F;
        this.cape.zRot = Mth.sin(capeWave * 0.7F) * 0.04F;
    }
}
