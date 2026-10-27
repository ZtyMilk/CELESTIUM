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
 * 天禄貔貅模型 (Pi Xiu Model) - "神兽构思师"树状解剖体系
 * 
 * 招财辟邪天禄神兽：
 * - 狮首龙吻：怒目金眉、宽吻利齿獠牙、额心通天神角、如意卷耳、飘逸龙须、厚重祥云鬃毛领圈。
 * - 聚财雄躯：雄健厚重前胸与腹腰、金玉护心宝甲、肩胛云垫、背脊元宝如意脊甲与连钱金鳞。
 * - 胁生双翼：胁下生出凌云羽翼，层叠祥云飞羽，逐风而动。
 * - 踏金神爪：四柱雄浑粗壮神腿、五爪金刚重爪、踏金祥云足环。
 * - 如意金毛厚尾：浓密金鬃尾基，尾端如意云头盛开并生飞金流苏，凌空高扬摇曳。
 */
public class PiXiuModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("pi_xiu"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart horn;
    private final ModelPart whiskerL;
    private final ModelPart whiskerR;
    private final ModelPart wingL;
    private final ModelPart wingR;
    private final ModelPart tail;
    private final ModelPart tailTip;
    private final ModelPart legFL;
    private final ModelPart legFR;
    private final ModelPart legBL;
    private final ModelPart legBR;

    public PiXiuModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.jaw = this.head.getChild("jaw");
        this.horn = this.head.getChild("horn");
        this.whiskerL = this.head.getChild("whisker_l");
        this.whiskerR = this.head.getChild("whisker_r");
        this.wingL = this.body.getChild("wing_l");
        this.wingR = this.body.getChild("wing_r");
        this.tail = this.body.getChild("tail");
        this.tailTip = this.tail.getChild("tail_tip");
        this.legFL = root.getChild("leg_fl");
        this.legFR = root.getChild("leg_fr");
        this.legBL = root.getChild("leg_bl");
        this.legBR = root.getChild("leg_br");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 聚财雄躯 (Body & Ingot Armor & Mane & Cloud Shoulders)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 雄壮前胸 (Muscular Front Chest)
                .texOffs(0, 0).addBox(-6.0F, -6.0F, -10.0F, 12.0F, 11.0F, 10.0F)
                // 宽厚腹腰与后胯 (Stocky Torso & Haunches)
                .texOffs(44, 0).addBox(-5.5F, -5.5F, 0.0F, 11.0F, 10.0F, 10.0F)
                // 金玉护心宝甲 (Chest Pectoral Armor)
                .texOffs(86, 0).addBox(-4.5F, -3.0F, -10.5F, 9.0F, 7.0F, 2.0F)
                // 左右肩胛祥云羽垫 (Shoulder Cloud Tufts)
                .texOffs(86, 9).addBox(5.5F, -7.0F, -9.0F, 2.0F, 5.0F, 6.0F)
                .texOffs(102, 9).addBox(-7.5F, -7.0F, -9.0F, 2.0F, 5.0F, 6.0F)
                // 背脊金元宝与如意宝珠脊饰 (Dorsal Ingot & Ruyi Armor Ridges)
                // 前段元宝底座与飞檐
                .texOffs(0, 21).addBox(-2.5F, -8.0F, -8.0F, 5.0F, 2.0F, 4.0F)
                .texOffs(18, 21).addBox(-3.5F, -9.0F, -7.5F, 7.0F, 1.0F, 3.0F)
                // 中段如意宝座与云珠
                .texOffs(38, 21).addBox(-2.0F, -7.5F, -3.0F, 4.0F, 2.0F, 5.0F)
                .texOffs(56, 21).addBox(-1.0F, -9.5F, -2.5F, 2.0F, 2.0F, 4.0F)
                // 后段连钱纳福金鳞
                .texOffs(68, 21).addBox(-2.0F, -7.0F, 3.0F, 4.0F, 2.0F, 5.0F)
                .texOffs(86, 21).addBox(-1.0F, -8.5F, 3.5F, 2.0F, 2.0F, 4.0F),
                PartPose.offset(0.0F, 11.0F, 0.0F));

        // 2. 狮首龙吻 (Head, Lion Skull, Snout, Mane, Ears)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 雄狮神首主颅 (Lion Skull)
                .texOffs(0, 28).addBox(-4.5F, -4.5F, -7.0F, 9.0F, 8.0F, 8.0F)
                // 宽阔龙吻上颚 (Upper Dragon Snout)
                .texOffs(34, 28).addBox(-3.5F, -1.0F, -12.5F, 7.0F, 4.0F, 6.0F)
                // 威严怒目金眉 (Brow Ridge)
                .texOffs(60, 28).addBox(-5.0F, -5.5F, -6.5F, 10.0F, 2.0F, 5.0F)
                // 上颚獠牙与利齿 (Upper Fangs & Teeth)
                .texOffs(90, 28).addBox(-3.0F, 3.0F, -12.0F, 6.0F, 2.0F, 2.0F)
                // 左右如意宝卷耳 (Curled Ears)
                .texOffs(106, 28).addBox(3.5F, -7.0F, -3.0F, 3.0F, 4.0F, 2.0F)
                .texOffs(116, 28).addBox(-6.5F, -7.0F, -3.0F, 3.0F, 4.0F, 2.0F)
                // 厚重祥云鬃毛领圈 (Cloud Collar Mane around neck)
                .texOffs(0, 44).addBox(-6.0F, -3.5F, -3.0F, 12.0F, 10.0F, 6.0F)
                // 双颊祥云卷须 (Cheek Mane Tufts)
                .texOffs(36, 44).addBox(4.5F, -1.0F, -5.0F, 2.0F, 5.0F, 5.0F)
                .texOffs(50, 44).addBox(-6.5F, -1.0F, -5.0F, 2.0F, 5.0F, 5.0F),
                PartPose.offset(0.0F, -3.0F, -9.0F));

        // 龙吻下颚利齿与仙髯 (Lower Jaw, Fangs & Chin Beard)
        head.addOrReplaceChild("jaw", CubeListBuilder.create()
                // 下颚骨 (Lower Jaw)
                .texOffs(64, 38).addBox(-3.0F, 0.0F, -5.5F, 6.0F, 3.0F, 6.0F)
                // 颌下飘逸祥云仙髯 (Chin Cloud Beard)
                .texOffs(88, 35).addBox(-2.0F, 3.0F, -4.5F, 4.0F, 5.0F, 4.0F)
                // 下颚向天利齿獠牙 (Lower Fangs)
                .texOffs(104, 35).addBox(-2.5F, -1.8F, -5.0F, 5.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, 2.5F, -6.5F));

        // 额心通天神角 (Sky-Piercing Celestial Horn)
        head.addOrReplaceChild("horn", CubeListBuilder.create()
                // 神角基座 (Horn Base)
                .texOffs(64, 47).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                // 螺旋中段 (Horn Mid)
                .texOffs(76, 47).addBox(-1.0F, -9.0F, -0.5F, 2.0F, 5.0F, 2.0F)
                // 通天锐尖 (Horn Tip)
                .texOffs(84, 47).addBox(-0.5F, -13.0F, 0.5F, 1.0F, 5.0F, 1.0F)
                // 角脊金棱 (Horn Ridge)
                .texOffs(88, 47).addBox(-0.5F, -8.0F, -2.5F, 1.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -4.5F, -4.0F, -0.2F, 0.0F, 0.0F));

        // 左右飘逸龙须 (Flowing Jaw Whiskers)
        head.addOrReplaceChild("whisker_l", CubeListBuilder.create()
                .texOffs(94, 47).addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 8.0F),
                PartPose.offsetAndRotation(3.5F, 0.5F, -11.0F, 0.1F, 0.35F, 0.0F));

        head.addOrReplaceChild("whisker_r", CubeListBuilder.create()
                .texOffs(94, 56).addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 8.0F),
                PartPose.offsetAndRotation(-3.5F, 0.5F, -11.0F, 0.1F, -0.35F, 0.0F));

        // 3. 胁生双翼 (Flank Cloud Wings for Riding Heavenly Winds)
        body.addOrReplaceChild("wing_l", CubeListBuilder.create()
                // 翼根骨节 (Wing Bone)
                .texOffs(0, 60).addBox(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 4.0F)
                // 祥云主翼羽面 (Main Cloud Feather Blade)
                .texOffs(10, 60).addBox(0.5F, -3.0F, 0.0F, 1.0F, 6.0F, 8.0F)
                // 翼尖逐风飞羽 (Outer Cloud Quill Tips)
                .texOffs(28, 60).addBox(1.0F, -2.0F, 6.0F, 1.0F, 4.0F, 5.0F),
                PartPose.offsetAndRotation(5.8F, -3.0F, -4.0F, 0.2F, 0.15F, -0.2F));

        body.addOrReplaceChild("wing_r", CubeListBuilder.create()
                // 翼根骨节
                .texOffs(0, 74).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 4.0F)
                // 祥云主翼羽面
                .texOffs(10, 74).addBox(-1.5F, -3.0F, 0.0F, 1.0F, 6.0F, 8.0F)
                // 翼尖逐风飞羽
                .texOffs(28, 74).addBox(-2.0F, -2.0F, 6.0F, 1.0F, 4.0F, 5.0F),
                PartPose.offsetAndRotation(-5.8F, -3.0F, -4.0F, 0.2F, -0.15F, 0.2F));

        // 4. 如意金毛厚尾 (Curled Bushy Ruyi Tail)
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                // 雄浑金尾基节 (Tail Base)
                .texOffs(40, 65).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 4.0F)
                // 尾部中段浓密金鬃 (Mid Fur Plume)
                .texOffs(56, 65).addBox(-2.5F, -9.0F, 0.0F, 5.0F, 5.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, -3.5F, 9.5F, 0.65F, 0.0F, 0.0F));

        tail.addOrReplaceChild("tail_tip", CubeListBuilder.create()
                // 如意云头卷尾簇 (Curled Ruyi Cloud Rosette)
                .texOffs(40, 75).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 6.0F, 6.0F)
                // 尾梢飞扬流苏 (Trailing Wisps)
                .texOffs(64, 75).addBox(-2.0F, -7.0F, 1.0F, 4.0F, 4.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, -8.5F, 2.5F, -0.4F, 0.0F, 0.0F));

        // 5. 踏金神爪 (4 Stout Muscular Legs with 5-Clawed Paws & Cloud Anklets)
        // 左前肢
        root.addOrReplaceChild("leg_fl", CubeListBuilder.create()
                .texOffs(0, 88).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                .texOffs(20, 88).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                .texOffs(36, 88).addBox(-2.5F, 9.0F, -4.5F, 5.0F, 2.0F, 6.0F)
                .texOffs(58, 88).addBox(-3.0F, 6.5F, -3.0F, 6.0F, 3.0F, 6.0F),
                PartPose.offset(4.8F, 13.0F, -6.0F));

        // 右前肢
        root.addOrReplaceChild("leg_fr", CubeListBuilder.create()
                .texOffs(0, 99).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                .texOffs(20, 99).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                .texOffs(36, 99).addBox(-2.5F, 9.0F, -4.5F, 5.0F, 2.0F, 6.0F)
                .texOffs(58, 99).addBox(-3.0F, 6.5F, -3.0F, 6.0F, 3.0F, 6.0F),
                PartPose.offset(-4.8F, 13.0F, -6.0F));

        // 左后肢
        root.addOrReplaceChild("leg_bl", CubeListBuilder.create()
                .texOffs(82, 88).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F)
                .texOffs(106, 88).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                .texOffs(82, 100).addBox(-2.5F, 9.0F, -4.5F, 5.0F, 2.0F, 6.0F)
                .texOffs(104, 100).addBox(-3.0F, 6.5F, -3.0F, 6.0F, 3.0F, 6.0F),
                PartPose.offset(4.8F, 13.0F, 6.5F));

        // 右后肢
        root.addOrReplaceChild("leg_br", CubeListBuilder.create()
                .texOffs(0, 110).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F)
                .texOffs(24, 110).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                .texOffs(40, 110).addBox(-2.5F, 9.0F, -4.5F, 5.0F, 2.0F, 6.0F)
                .texOffs(62, 110).addBox(-3.0F, 6.5F, -3.0F, 6.0F, 3.0F, 6.0F),
                PartPose.offset(-4.8F, 13.0F, 6.5F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // 1. 威武神兽行进步态 (Powerful Prowling Beast Walk)
        this.legFL.xRot = Mth.cos(walkPos * 0.6662F) * 0.85F * walkSpeed;
        this.legFR.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.85F * walkSpeed;
        this.legBL.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.85F * walkSpeed;
        this.legBR.xRot = Mth.cos(walkPos * 0.6662F) * 0.85F * walkSpeed;

        // 行走时雄躯微沉起伏与前后摇摆 (Prowling Sway & Weight Shift)
        this.body.zRot = Mth.cos(walkPos * 0.3331F) * 0.04F * walkSpeed;
        this.body.yRot = Mth.sin(walkPos * 0.3331F) * 0.05F * walkSpeed;
        this.body.y = 11.0F + Mth.abs(Mth.sin(walkPos * 0.6662F)) * 0.6F * walkSpeed;

        // 2. 胁生双翼扑动与呼吸律动 (Flank Wings Fluttering with Heavenly Breeze)
        float flutterSpeed = 0.2F + walkSpeed * 0.3F;
        float flutterAmp = 0.12F + walkSpeed * 0.25F;
        float wingWave = Mth.sin(age * flutterSpeed) * flutterAmp;
        this.wingL.zRot = -0.2F - wingWave;
        this.wingR.zRot = 0.2F + wingWave;
        this.wingL.yRot = 0.15F + wingWave * 0.3F;
        this.wingR.yRot = -0.15F - wingWave * 0.3F;

        // 3. 狮首威严环视、大张龙口与须髯飘拂 (Head Vigilant Looking, Snarling Jaw, Flowing Whiskers)
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.7F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.7F;
        this.head.zRot = Mth.sin(age * 0.06F) * 0.03F;

        // 宽口利齿微阖微张，吞吐天地灵气
        this.jaw.xRot = 0.1F + Mth.sin(age * 0.09F) * 0.06F;

        // 飘拂龙须迎风自展
        this.whiskerL.yRot = 0.35F + Mth.sin(age * 0.15F) * 0.12F;
        this.whiskerR.yRot = -0.35F - Mth.sin(age * 0.15F) * 0.12F;
        this.whiskerL.zRot = Mth.cos(age * 0.12F) * 0.06F;
        this.whiskerR.zRot = -Mth.cos(age * 0.12F) * 0.06F;

        // 4. 如意金毛厚尾高扬摆动 (Bushy Curled Ruyi Tail Swishing)
        float tailSway = Mth.sin(age * 0.12F) * 0.32F + Mth.sin(walkPos * 0.6662F) * 0.22F * walkSpeed;
        this.tail.yRot = tailSway;
        this.tail.xRot = 0.65F + Mth.cos(age * 0.08F) * 0.08F;

        // 尾梢如意云头次级灵动鞭梢翻卷
        this.tailTip.yRot = Mth.sin(age * 0.12F - 0.7F) * 0.22F;
        this.tailTip.xRot = -0.4F + Mth.cos(age * 0.08F - 0.4F) * 0.1F;
    }
}
