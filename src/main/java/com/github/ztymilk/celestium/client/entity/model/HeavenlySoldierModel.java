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
 * 天庭神兵 (Heavenly Soldier Model) - "天庭神兵"威严威武的树状解剖体系架构
 *
 * 【解剖树状体系结构 (Anatomical Tree Architecture)】:
 * root
 *  └── body (主躯干，金丝锁子内甲 + 鎏金护心龙镜 + 腹部吞兽饕餮带扣 + 左右战裙皮弁)
 *       ├── head (威严神面，凤翅兜鍪战盔 + 左右护耳顿项)
 *       │    ├── plume (盔顶红缨，随风微荡)
 *       │    ├── helmet_wings_l (凤翅左翼，翻展凌云)
 *       │    └── helmet_wings_r (凤翅右翼，翻展凌云)
 *       ├── pauldron_left / pauldron_right (双肩龙首金甲兽吞肩甲，层叠披挂护臂)
 *       ├── cape (背负暗红织金滚边战袍披风，随风向后翻卷波浪)
 *       ├── skirt_left / skirt_right (左右战裙皮弁护胯，随步伐自然开合)
 *       ├── arm_left (左臂持盾/握拳戒备，手腕金刚护腕)
 *       ├── arm_right (右臂执锐，手持武器)
 *       │    └── weapon (玄金方天神戟：玄金长柄 + 银刃利刃 + 缨穗 + 月牙侧刃 + 吞口龙首)
 *       ├── leg_left (左腿：战裙开叉护膝 + 束甲胫骨 + 踏云战靴)
 *       └── leg_right (右腿：战裙开叉护膝 + 束甲胫骨 + 踏云战靴)
 */
public class HeavenlySoldierModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("heavenly_soldier"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart plume;
    private final ModelPart helmetWingsL;
    private final ModelPart helmetWingsR;
    private final ModelPart pauldronLeft;
    private final ModelPart pauldronRight;
    private final ModelPart cape;
    private final ModelPart skirtLeft;
    private final ModelPart skirtRight;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart weapon;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public HeavenlySoldierModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.plume = this.head.getChild("plume");
        this.helmetWingsL = this.head.getChild("helmet_wings_l");
        this.helmetWingsR = this.head.getChild("helmet_wings_r");
        this.pauldronLeft = this.body.getChild("pauldron_left");
        this.pauldronRight = this.body.getChild("pauldron_right");
        this.cape = this.body.getChild("cape");
        this.skirtLeft = this.body.getChild("skirt_left");
        this.skirtRight = this.body.getChild("skirt_right");
        this.leftArm = this.body.getChild("arm_left");
        this.rightArm = this.body.getChild("arm_right");
        this.weapon = this.rightArm.getChild("weapon");
        this.leftLeg = this.body.getChild("leg_left");
        this.rightLeg = this.body.getChild("leg_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 主躯干体系 (Body & Mingguang Armor & Taotie Buckle & Skirt Flaps)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 金丝锁子内甲核心 (Chainmail Core)
                .texOffs(64, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F)
                // 鎏金明光重铠胸甲 (Mingguang Breastplate)
                .texOffs(88, 0).addBox(-4.5F, 0.5F, -2.6F, 9.0F, 8.0F, 1.0F)
                // 鎏金护心龙镜中央宝心 (Dragon Heart-Mirror Central Gem)
                .texOffs(108, 0).addBox(-2.5F, 1.5F, -3.2F, 5.0F, 5.0F, 1.0F)
                // 护心龙镜外圈浮雕宝边 (Dragon Heart-Mirror Rim)
                .texOffs(108, 6).addBox(-3.0F, 1.0F, -2.9F, 6.0F, 6.0F, 1.0F)
                // 背部明光靠背甲 (Back Armor Plate)
                .texOffs(88, 9).addBox(-4.5F, 1.0F, 1.6F, 9.0F, 8.0F, 1.0F)
                // 屯颈护喉甲 (Gorget Throat Collar)
                .texOffs(64, 16).addBox(-3.5F, -1.0F, -2.5F, 7.0F, 2.0F, 5.0F)
                // 腹部金丝织锦战带 (Waist Belt Band)
                .texOffs(88, 18).addBox(-4.5F, 8.5F, -2.5F, 9.0F, 4.0F, 5.0F)
                // 腹部吞兽饕餮带扣兽首 (Taotie Beast Buckle Face)
                .texOffs(64, 23).addBox(-2.5F, 8.0F, -3.2F, 5.0F, 4.0F, 2.0F)
                // 饕餮带扣金角龙须 (Taotie Buckle Horns)
                .texOffs(108, 13).addBox(-3.5F, 7.5F, -3.0F, 7.0F, 2.0F, 1.0F)
                // 前垂蔽膝战裙皮弁 (Front Skirt Flap)
                .texOffs(116, 16).addBox(-2.5F, 11.5F, -2.7F, 5.0F, 6.0F, 1.0F)
                // 后臀护甲 (Back Skirt Flap)
                .texOffs(78, 27).addBox(-3.5F, 11.5F, 1.7F, 7.0F, 5.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 左右侧身战裙皮弁 (Dynamic Side Skirt Tassets)
        body.addOrReplaceChild("skirt_left", CubeListBuilder.create()
                .texOffs(94, 27).addBox(-0.5F, 0.0F, -2.5F, 2.0F, 6.0F, 5.0F)
                .texOffs(116, 23).addBox(0.2F, 0.5F, -2.0F, 1.0F, 5.0F, 4.0F),
                PartPose.offset(4.2F, 11.0F, 0.0F));

        body.addOrReplaceChild("skirt_right", CubeListBuilder.create()
                .texOffs(64, 29).addBox(-1.5F, 0.0F, -2.5F, 2.0F, 6.0F, 5.0F)
                .texOffs(108, 32).addBox(-1.2F, 0.5F, -2.0F, 1.0F, 5.0F, 4.0F),
                PartPose.offset(-4.2F, 11.0F, 0.0F));

        // =========================================================================
        // 2. 威严神面与凤翅金盔 (Head & Phoenix-Wing Helmet & Plume)
        // =========================================================================
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 神层面容基底 (Head Inner Face)
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                // 凤翅兜鍪战盔主钵 (Helmet Bowl Outer Casing)
                .texOffs(0, 16).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 5.0F, 9.0F)
                // 兜鍪前额龙首冠檐 (Forehead Crest Brow)
                .texOffs(32, 0).addBox(-3.0F, -9.5F, -5.2F, 6.0F, 4.0F, 2.0F)
                // 冠顶辟邪朱红宝珠 (Forehead Ruby Gem)
                .texOffs(48, 0).addBox(-1.0F, -9.0F, -5.5F, 2.0F, 2.0F, 1.0F)
                // 后颈顿项垂甲 (Helmet Nape Neck Curtain)
                .texOffs(32, 6).addBox(-4.5F, -4.0F, 2.5F, 9.0F, 6.0F, 2.0F)
                // 左侧护耳顿项 (Left Cheek Guard)
                .texOffs(36, 14).addBox(4.0F, -4.5F, -3.5F, 1.0F, 5.0F, 7.0F)
                // 右侧护耳顿项 (Right Cheek Guard)
                .texOffs(36, 26).addBox(-5.0F, -4.5F, -3.5F, 1.0F, 5.0F, 7.0F)
                // 盔顶金座 (Helmet Plume Socket)
                .texOffs(52, 14).addBox(-1.5F, -11.0F, -1.5F, 3.0F, 2.0F, 3.0F)
                // 盔顶玄金锋芒尖顶 (Finial Spike)
                .texOffs(54, 0).addBox(-0.5F, -13.0F, -0.5F, 1.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 盔顶红缨随风微荡 (Flowing Crimson Plume)
        head.addOrReplaceChild("plume", CubeListBuilder.create()
                .texOffs(52, 19).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F)
                .texOffs(54, 3).addBox(-1.0F, 0.0F, 0.5F, 2.0F, 5.0F, 3.0F)
                .texOffs(52, 25).addBox(-0.5F, 4.0F, 1.8F, 1.0F, 4.0F, 2.0F),
                PartPose.offset(0.0F, -11.0F, 0.5F));

        // 左右护耳凤翅翻展 (Phoenix Wings on Helmet)
        head.addOrReplaceChild("helmet_wings_l", CubeListBuilder.create()
                .texOffs(0, 30).addBox(0.0F, -2.0F, -1.5F, 1.0F, 4.0F, 3.0F)
                .texOffs(8, 30).addBox(0.4F, -7.0F, -1.0F, 1.0F, 6.0F, 4.0F)
                .texOffs(18, 30).addBox(0.4F, -4.0F, 2.5F, 1.0F, 4.0F, 4.0F),
                PartPose.offsetAndRotation(4.5F, -6.5F, -1.0F, -0.1F, 0.15F, 0.20F));

        head.addOrReplaceChild("helmet_wings_r", CubeListBuilder.create()
                .texOffs(28, 30).addBox(-1.0F, -2.0F, -1.5F, 1.0F, 4.0F, 3.0F)
                .texOffs(52, 31).addBox(-1.4F, -7.0F, -1.0F, 1.0F, 6.0F, 4.0F)
                .texOffs(18, 38).addBox(-1.4F, -4.0F, 2.5F, 1.0F, 4.0F, 4.0F),
                PartPose.offsetAndRotation(-4.5F, -6.5F, -1.0F, -0.1F, -0.15F, -0.20F));

        // =========================================================================
        // 3. 双肩龙首金甲兽吞肩甲 (Beast Head Pauldrons & Layered Laminar Plates)
        // =========================================================================
        body.addOrReplaceChild("pauldron_left", CubeListBuilder.create()
                // 龙首金甲兽吞穹顶 (Dragon Beast Head Dome)
                .texOffs(0, 48).addBox(-1.0F, -2.5F, -2.5F, 5.0F, 4.0F, 5.0F)
                // 龙吻兽口吐刃 (Dragon Snout)
                .texOffs(20, 48).addBox(1.0F, -1.5F, -3.2F, 3.0F, 3.0F, 2.0F)
                // 龙角金翎 (Dragon Horns Crest)
                .texOffs(30, 48).addBox(0.5F, -4.0F, -1.5F, 3.0F, 2.0F, 3.0F)
                // 一重层叠披挂护臂 (Laminar Plate Tier 1)
                .texOffs(42, 48).addBox(-0.5F, 1.5F, -2.3F, 4.0F, 3.0F, 5.0F)
                // 二重层叠披挂护臂 (Laminar Plate Tier 2)
                .texOffs(20, 53).addBox(0.0F, 3.8F, -2.0F, 3.0F, 3.0F, 4.0F),
                PartPose.offset(5.5F, 1.0F, 0.0F));

        body.addOrReplaceChild("pauldron_right", CubeListBuilder.create()
                // 龙首金甲兽吞穹顶
                .texOffs(34, 56).addBox(-4.0F, -2.5F, -2.5F, 5.0F, 4.0F, 5.0F)
                // 龙吻兽口
                .texOffs(54, 56).addBox(-4.0F, -1.5F, -3.2F, 3.0F, 3.0F, 2.0F)
                // 龙角金翎
                .texOffs(0, 57).addBox(-3.5F, -4.0F, -1.5F, 3.0F, 2.0F, 3.0F)
                // 一重层叠披挂护臂
                .texOffs(12, 60).addBox(-3.5F, 1.5F, -2.3F, 4.0F, 3.0F, 5.0F)
                // 二重层叠披挂护臂
                .texOffs(30, 65).addBox(-3.0F, 3.8F, -2.0F, 3.0F, 3.0F, 4.0F),
                PartPose.offset(-5.5F, 1.0F, 0.0F));

        // =========================================================================
        // 4. 背负暗红织金滚边战袍披风 (Crimson & Gold Bordered Cloak Cape)
        // =========================================================================
        body.addOrReplaceChild("cape", CubeListBuilder.create()
                // 披风金扣颈锁 (Cape Golden Clasp Collar)
                .texOffs(62, 80).addBox(-5.0F, 0.0F, -0.5F, 10.0F, 2.0F, 1.0F)
                // 上段背负战袍 (Upper Cape Fabric)
                .texOffs(84, 80).addBox(-5.5F, 1.5F, 0.2F, 11.0F, 10.0F, 1.0F)
                // 下段展卷流风后摆 (Lower Flowing Cape Fabric)
                .texOffs(52, 86).addBox(-6.0F, 11.0F, 0.6F, 12.0F, 10.0F, 1.0F),
                PartPose.offset(0.0F, 0.5F, 2.5F));

        // =========================================================================
        // 5. 左臂戒备与金刚护腕 (Left Arm & Vajra Vambrace)
        // =========================================================================
        body.addOrReplaceChild("arm_left", CubeListBuilder.create()
                // 左臂基础身躯 (Left Arm Sleeve Base)
                .texOffs(0, 76).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                // 臂膀金色护圈 (Upper Arm Band)
                .texOffs(16, 76).addBox(-1.2F, -1.0F, -2.2F, 5.0F, 3.0F, 5.0F)
                // 手腕金刚护腕 (Vajra Vambrace)
                .texOffs(36, 76).addBox(-1.3F, 5.0F, -2.3F, 5.0F, 5.0F, 5.0F)
                // 护腕辟邪宝珠 (Vambrace Gem Stud)
                .texOffs(56, 76).addBox(3.4F, 6.0F, -1.0F, 1.0F, 3.0F, 2.0F),
                PartPose.offset(5.0F, 2.0F, 0.0F));

        // =========================================================================
        // 6. 右臂执锐与玄金方天神戟 (Right Arm & Heavenly Halberd Weapon)
        // =========================================================================
        PartDefinition armRight = body.addOrReplaceChild("arm_right", CubeListBuilder.create()
                // 右臂基础身躯 (Right Arm Base)
                .texOffs(16, 84).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                // 臂膀金色护圈 (Upper Arm Band)
                .texOffs(32, 86).addBox(-3.8F, -1.0F, -2.2F, 5.0F, 3.0F, 5.0F)
                // 手腕金刚护腕 (Vajra Vambrace)
                .texOffs(32, 94).addBox(-3.7F, 5.0F, -2.3F, 5.0F, 5.0F, 5.0F)
                // 护腕辟邪宝珠
                .texOffs(56, 81).addBox(-4.4F, 6.0F, -1.0F, 1.0F, 3.0F, 2.0F),
                PartPose.offset(-5.0F, 2.0F, 0.0F));

        // 右手握持：玄金方天神戟 (Black-Gold Heavenly Halberd: Shaft + Spear Blade + Crescent + Tassel)
        armRight.addOrReplaceChild("weapon", CubeListBuilder.create()
                // 玄金长柄 (Halberd Shaft - 32 units long)
                .texOffs(108, 80).addBox(-0.5F, -16.0F, -0.5F, 1.0F, 32.0F, 1.0F)
                // 矛鐏底端配重金尊 (Halberd Pommel Butt-Cap)
                .texOffs(112, 80).addBox(-1.0F, 15.0F, -1.0F, 2.0F, 3.0F, 2.0F)
                // 戟喉龙首套筒 (Spear Socket Collar)
                .texOffs(112, 85).addBox(-1.5F, -18.0F, -1.5F, 3.0F, 3.0F, 3.0F)
                // 戟头朱红缨穗 (Spear Red Plume Tassel)
                .texOffs(78, 91).addBox(-2.0F, -17.5F, -2.0F, 4.0F, 4.0F, 4.0F)
                // 中央破阵利刃银芒 (Central Silver Spearhead Blade)
                .texOffs(94, 91).addBox(-1.0F, -27.0F, -0.5F, 2.0F, 9.0F, 1.0F)
                // 枪尖锋锐绝顶 (Spearhead Sharp Tip)
                .texOffs(120, 80).addBox(-0.5F, -29.0F, -0.5F, 1.0F, 2.0F, 1.0F)
                // 方天月牙侧刃横梁 (Crescent Blade Crossbar)
                .texOffs(62, 83).addBox(-5.5F, -24.0F, -0.5F, 5.0F, 1.0F, 1.0F)
                // 方天月牙弧面利刃 (Crescent Curved Outer Blade)
                .texOffs(124, 80).addBox(-6.0F, -26.5F, -0.5F, 1.0F, 6.0F, 1.0F)
                // 月牙上挑银钩 (Crescent Upper Hook)
                .texOffs(74, 83).addBox(-5.5F, -27.5F, -0.5F, 2.0F, 1.0F, 1.0F)
                // 月牙下回倒刺钩 (Crescent Lower Hook)
                .texOffs(78, 85).addBox(-5.5F, -20.5F, -0.5F, 2.0F, 1.0F, 1.0F)
                // 吞刃龙首扣座 (Dragon Bracket Swallowing Crescent)
                .texOffs(100, 91).addBox(-2.5F, -24.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(-1.0F, 8.5F, 0.0F));

        // =========================================================================
        // 7. 双腿与踏云战靴 (Legs & Greaves & Cloud-Treading Boots)
        // =========================================================================
        body.addOrReplaceChild("leg_left", CubeListBuilder.create()
                // 左腿身躯基础 (Left Leg Base)
                .texOffs(64, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                // 护膝鎏金兽面 (Knee Guard Beast Mask)
                .texOffs(80, 52).addBox(-2.5F, 3.5F, -2.8F, 5.0F, 4.0F, 1.0F)
                // 束甲胫骨甲 (Greaves Shin Armor)
                .texOffs(92, 52).addBox(-2.5F, 6.5F, -2.5F, 5.0F, 5.0F, 5.0F)
                // 踏云战靴 (Cloud Boot Foot)
                .texOffs(80, 62).addBox(-2.5F, 10.0F, -3.2F, 5.0F, 2.0F, 6.0F)
                // 踏云如意祥云翘头 (Cloud Toe Upturn)
                .texOffs(112, 52).addBox(-1.5F, 9.0F, -4.0F, 3.0F, 3.0F, 1.0F),
                PartPose.offset(2.0F, 12.0F, 0.0F));

        body.addOrReplaceChild("leg_right", CubeListBuilder.create()
                // 右腿身躯基础
                .texOffs(112, 56).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                // 护膝鎏金兽面
                .texOffs(80, 57).addBox(-2.5F, 3.5F, -2.8F, 5.0F, 4.0F, 1.0F)
                // 束甲胫骨甲
                .texOffs(64, 70).addBox(-2.5F, 6.5F, -2.5F, 5.0F, 5.0F, 5.0F)
                // 踏云战靴
                .texOffs(84, 70).addBox(-2.5F, 10.0F, -3.2F, 5.0F, 2.0F, 6.0F)
                // 踏云如意祥云翘头
                .texOffs(120, 52).addBox(-1.5F, 9.0F, -4.0F, 3.0F, 3.0F, 1.0F),
                PartPose.offset(-2.0F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // =========================================================================
        // 1. 头部环视与凤翅兜鍪战盔微动 (Regal Head Vigilance & Phoenix Wings)
        // =========================================================================
        float headPitch = state.xRot * ((float) Math.PI / 180F);
        float headYaw = state.yRot * ((float) Math.PI / 180F);

        // 呼吸微荡与警惕仰俯环视
        float breath = Mth.sin(age * 0.06F);
        this.head.xRot = headPitch + breath * 0.02F;
        this.head.yRot = headYaw;
        this.head.zRot = headYaw * 0.05F; // 转向巡视时金盔随仙姿自然微倾

        // 盔顶红缨随灵风与动态荡漾 (Crimson Plume Wind Oscillation)
        float plumeWave = Mth.sin(age * 0.12F);
        this.plume.xRot = 0.15F + plumeWave * 0.08F + walkSpeed * 0.35F;
        this.plume.zRot = Mth.cos(age * 0.09F) * 0.06F;

        // 凤翅金翎在天风中微振 (Phoenix Wings Celestial Vibration)
        float wingFlutter = Mth.sin(age * 0.08F) * 0.03F;
        this.helmetWingsL.zRot = 0.20F + wingFlutter;
        this.helmetWingsR.zRot = -0.20F - wingFlutter;

        // =========================================================================
        // 2. 躯干与双肩兽吞威严体态 (Majestic Torso & Pauldrons)
        // =========================================================================
        // 行进时躯干稳重微摆与起伏
        this.body.yRot = Mth.sin(walkPos * 0.6662F) * 0.04F * walkSpeed;
        this.body.zRot = Mth.cos(walkPos * 0.6662F) * 0.02F * walkSpeed;

        // 双肩兽吞肩甲保持威严姿态，随躯干微动但坚实稳重
        this.pauldronLeft.zRot = 0.04F + breath * 0.015F;
        this.pauldronRight.zRot = -0.04F - breath * 0.015F;
        this.pauldronLeft.xRot = Mth.sin(walkPos * 0.6662F) * 0.05F * walkSpeed;
        this.pauldronRight.xRot = -Mth.sin(walkPos * 0.6662F) * 0.05F * walkSpeed;

        // =========================================================================
        // 3. 威严行进步态与战裙自然摆动 (Marching Gait & War Skirt Dynamic Sway)
        // =========================================================================
        float legSwing = Mth.cos(walkPos * 0.6662F) * 0.7F * walkSpeed;
        float legSwingOpposite = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.7F * walkSpeed;

        this.leftLeg.xRot = legSwing;
        this.rightLeg.xRot = legSwingOpposite;
        this.leftLeg.yRot = 0.0F;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.zRot = 0.02F;
        this.rightLeg.zRot = -0.02F;

        // 左右战裙皮弁随双腿迈步向外与前后自然微摆，既贴合腿甲又随步伐灵动
        this.skirtLeft.xRot = Math.max(0.0F, legSwing * 0.6F);
        this.skirtLeft.zRot = 0.05F + Math.abs(Mth.sin(walkPos * 0.6662F)) * 0.12F * walkSpeed;
        this.skirtRight.xRot = Math.max(0.0F, legSwingOpposite * 0.6F);
        this.skirtRight.zRot = -0.05F - Math.abs(Mth.sin(walkPos * 0.6662F)) * 0.12F * walkSpeed;

        // =========================================================================
        // 4. 左臂戒备与右臂执戟巡逻 (Left Arm Guard & Right Arm Halberd Vigil)
        // =========================================================================
        // 左臂持盾/握拳戒备，稳重前屈随步伐微摆
        this.leftArm.xRot = Mth.cos(walkPos * 0.6662F) * 0.5F * walkSpeed - 0.15F;
        this.leftArm.yRot = 0.10F;
        this.leftArm.zRot = 0.08F + breath * 0.02F;

        // 右臂执锐：右手握持玄金方天神戟，平稳前指/斜向警惕巡逻
        // 基础姿态斜持戒备（xRot 约 -37度前指），巡逻行进时随步伐稳重微调
        float halberdMarch = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.25F * walkSpeed;
        this.rightArm.xRot = -0.65F + halberdMarch;
        this.rightArm.yRot = -0.20F;
        this.rightArm.zRot = -0.08F;

        // 神戟长柄在手中微仰刺指，威震四方
        this.weapon.xRot = 0.25F + Mth.sin(age * 0.05F) * 0.03F;
        this.weapon.zRot = 0.08F;

        // =========================================================================
        // 5. 战袍披风动态：待机轻缓飘荡，移动时随风向后扬起飘卷 (Flowing Crimson Cape)
        // =========================================================================
        float capeIdle = Mth.sin(age * 0.08F) * 0.06F;
        float capeWave = Mth.sin(age * 0.15F + walkPos * 0.5F) * 0.08F * walkSpeed;
        float capeLift = walkSpeed * 0.55F; // 疾走巡天时披风扬起
        this.cape.xRot = 0.12F + capeIdle + capeLift + capeWave;
        this.cape.zRot = Mth.cos(age * 0.06F) * 0.04F;
        this.cape.yRot = Mth.sin(walkPos * 0.6662F) * 0.06F * walkSpeed;
    }
}
