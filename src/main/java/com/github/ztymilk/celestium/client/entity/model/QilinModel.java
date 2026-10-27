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
 * 玄玉神圣麒麟 (Majestic Qilin Model) - "神兽构思师"树状解剖体系
 *
 * 【解剖树状体系结构】：
 * 1. 龙头 (Head):
 *    - 龙颅 (Skull / Cranium)、隆起鼻孔与祥云龙吻上颚 (Snout & Nostrils & Upper Jaw)、下颚 (Lower Jaw)
 *    - 金玉神鹿角 (Branching Antlers): 主干向后仰升曲干 + 前支叉角 (Front Prong Tine) + 后支叉角 (Back Prong Tine)
 *    - 额心辟邪宝角与祥云宝珠 (Crown Gem / Horn on Forehead)
 *    - 左右飘拂灵动龙须 (Flowing Dragon Whiskers)
 *    - 左右展卷披云仙耳 (Flared Celestial Ears)
 *    - 颔下飘逸仙髯美须 (Chin Beard)
 * 2. 颈项与狮鬃 (Neck & Layered Mane):
 *    - 雄健曲颈核心与背脊烈焰鬣
 *    - 三层叠嶂狮鬃云鬣 (3-Tiered Outspread Neck Ruff Mane): 基底大环幅鬃、中段怒张火鬃、托颌仙云鬃
 * 3. 玉鳞仙躯 (Torso & Armor):
 *    - 健硕前胸大肌 (Thorax) 与后腰胯部 (Haunches)
 *    - 胸前祥云护心镜 (Chest Crest Mirror Plate & Medallion)
 *    - 侧腹护体玉鳞甲 (Flank Jade Scale Plates)
 *    - 腹底护心龙甲与肋排护板 (Ventral Rib & Belly Plates)
 *    - 贯穿背脊的高耸烈焰鳍鬃 (Dorsal Spine Flame Mane)
 * 4. 四肢踏云蹄 (4 Segmented Limbs & Cloud Hooves):
 *    - 粗壮肩胛大腿 (Upper Thigh / Shoulder)
 *    - 矫捷小腿胫骨 (Lower Shank)
 *    - 踏云烈焰蹄踝祥云饰 (Cloud Fire Ankle Tufts & Crests)
 *    - 破邪分瓣金蹄 (Golden Cloven Hooves)
 * 5. 祥云火尾 (Tail & Voluminous Cloud Flame Tassel):
 *    - 雄浑牛尾根 (Tail Root)
 *    - 细长遒劲牛尾轴 (Tail Stalk)
 *    - 蓬松翻卷如意祥云火簇尾缨 (Voluminous Cloud Flame Tassel Tip)
 */
public class QilinModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("qilin"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart dorsalMane;
    private final ModelPart neck;
    private final ModelPart neckManeLower;
    private final ModelPart neckManeMid;
    private final ModelPart neckManeUpper;
    private final ModelPart head;
    private final ModelPart chinBeard;
    private final ModelPart leftWhisker;
    private final ModelPart rightWhisker;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;
    private final ModelPart tail;
    private final ModelPart tailFlame;
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontShank;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontShank;
    private final ModelPart leftHindLeg;
    private final ModelPart leftHindShank;
    private final ModelPart rightHindLeg;
    private final ModelPart rightHindShank;

    public QilinModel(ModelPart root) {
        super(root);
        this.root = root;

        // 躯干与背脊鬃
        this.body = root.getChild("body");
        this.dorsalMane = this.body.getChild("dorsal_mane");
        this.tail = this.body.getChild("tail");
        this.tailFlame = this.tail.getChild("tail_flame");

        // 颈项与多层狮鬃
        this.neck = this.body.getChild("neck");
        this.neckManeLower = this.neck.getChild("neck_mane_lower");
        this.neckManeMid = this.neck.getChild("neck_mane_mid");
        this.neckManeUpper = this.neck.getChild("neck_mane_upper");

        // 龙头五官与分叉神鹿角
        this.head = this.neck.getChild("head");
        this.chinBeard = this.head.getChild("chin_beard");
        this.leftWhisker = this.head.getChild("left_whisker");
        this.rightWhisker = this.head.getChild("right_whisker");
        this.leftEar = this.head.getChild("left_ear");
        this.rightEar = this.head.getChild("right_ear");
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");

        // 四肢多段踏云金蹄
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.leftFrontShank = this.leftFrontLeg.getChild("left_front_shank");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.rightFrontShank = this.rightFrontLeg.getChild("right_front_shank");

        this.leftHindLeg = root.getChild("left_hind_leg");
        this.leftHindShank = this.leftHindLeg.getChild("left_hind_shank");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.rightHindShank = this.rightHindLeg.getChild("right_hind_shank");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 玉鳞仙躯 (Torso & Armor Plates)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 前胸主躯干 (Muscular Thorax)
                .texOffs(0, 0).addBox(-5.0F, -5.5F, -9.0F, 10.0F, 11.0F, 10.0F)
                // 后腰胯部 (Haunches)
                .texOffs(40, 0).addBox(-4.5F, -5.0F, 0.0F, 9.0F, 10.0F, 9.0F)
                // 祥云护心镜外圈 (Chest Crest Plate Rim)
                .texOffs(76, 0).addBox(-3.5F, -4.0F, -10.2F, 7.0F, 7.0F, 2.0F)
                // 祥云护心镜宝心 (Chest Mirror Medallion)
                .texOffs(96, 0).addBox(-2.0F, -2.5F, -11.0F, 4.0F, 4.0F, 1.0F)
                // 腹部龙甲与肋排护板 (Belly Rib Plates)
                .texOffs(0, 22).addBox(-3.5F, 5.0F, -7.0F, 7.0F, 2.0F, 14.0F)
                // 侧腹玉鳞护甲 (Flank Jade Scales - Left & Right)
                .texOffs(42, 22).addBox(4.8F, -3.5F, -6.0F, 1.0F, 7.0F, 8.0F)
                .texOffs(42, 22).mirror().addBox(-5.8F, -3.5F, -6.0F, 1.0F, 7.0F, 8.0F).mirror(false),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        // 背脊高耸烈焰鳍鬃 (Dorsal Spine Flame Mane along Back)
        body.addOrReplaceChild("dorsal_mane", CubeListBuilder.create()
                // 背脊烈焰鬃基底 (Dorsal Spine Flame Mane Base)
                .texOffs(62, 18).addBox(-1.0F, -8.5F, -6.0F, 2.0F, 4.0F, 14.0F)
                // 背脊中央耸立焰棘 (Dorsal Spire Spines)
                .texOffs(96, 10).addBox(-0.5F, -10.5F, -2.0F, 1.0F, 3.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // =========================================================================
        // 2. 颈项与三层叠嶂狮鬃云鬣 (Neck & Layered Ruff Mane)
        // =========================================================================
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                // 雄健颈项核心 (Neck Core)
                .texOffs(0, 40).addBox(-3.5F, -7.0F, -5.0F, 7.0F, 9.0F, 7.0F)
                // 颈背烈焰鬣 (Neck Dorsal Flame)
                .texOffs(28, 40).addBox(-1.0F, -9.5F, -2.5F, 2.0F, 4.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -2.0F, -8.0F, -0.35F, 0.0F, 0.0F));

        // 第一层：基底大环幅祥云狮鬃 (Tier 1 Lower Ruff Tufts)
        neck.addOrReplaceChild("neck_mane_lower", CubeListBuilder.create()
                .texOffs(46, 40).addBox(-5.5F, -2.0F, -5.5F, 11.0F, 5.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.15F, 0.0F, 0.0F));

        // 第二层：中段怒张烈焰云鬃 (Tier 2 Mid Ruff Tufts)
        neck.addOrReplaceChild("neck_mane_mid", CubeListBuilder.create()
                .texOffs(86, 40).addBox(-5.0F, -5.5F, -4.5F, 10.0F, 5.0F, 7.0F),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.10F, 0.0F, 0.0F));

        // 第三层：颈顶托颌仙火云鬃 (Tier 3 Upper Ruff Tufts)
        neck.addOrReplaceChild("neck_mane_upper", CubeListBuilder.create()
                .texOffs(0, 58).addBox(-4.5F, -8.0F, -4.0F, 9.0F, 4.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.05F, 0.0F, 0.0F));

        // =========================================================================
        // 3. 龙头与金玉神鹿角、仙耳、龙须、美髯 (Dragon Head Anatomy)
        // =========================================================================
        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                // 威严龙颅 (Skull / Cranium)
                .texOffs(32, 56).addBox(-4.0F, -4.5F, -6.0F, 8.0F, 8.0F, 7.0F)
                // 隆起鼻孔与祥云龙吻上颚 (Snout & Nostrils & Upper Jaw)
                .texOffs(64, 56).addBox(-3.0F, -1.5F, -12.0F, 6.0F, 5.0F, 6.0F)
                .texOffs(90, 56).addBox(-2.5F, -2.5F, -11.5F, 5.0F, 2.0F, 3.0F)
                // 威仪下颚 (Lower Jaw)
                .texOffs(0, 70).addBox(-2.5F, 3.2F, -11.0F, 5.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, -6.5F, -2.0F, 0.35F, 0.0F, 0.0F));

        // 额心辟邪宝角与祥云宝珠 (Crown Gem / Horn on Forehead)
        head.addOrReplaceChild("crown_horn", CubeListBuilder.create()
                // 额心辟邪宝角金座 (Crown Gem Base)
                .texOffs(46, 70).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F)
                // 额心通天碧玉尖角 (Crown Horn Spire)
                .texOffs(60, 70).addBox(-1.0F, -5.5F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -4.5F, -4.5F, -0.25F, 0.0F, 0.0F));

        // 左右展卷披云仙耳 (Flared Celestial Ears)
        head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                .texOffs(70, 70).addBox(0.0F, -1.5F, -1.0F, 4.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(3.6F, -3.5F, -1.0F, -0.1F, 0.25F, 0.35F));

        head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(70, 70).mirror().addBox(-4.0F, -1.5F, -1.0F, 4.0F, 3.0F, 2.0F).mirror(false),
                PartPose.offsetAndRotation(-3.6F, -3.5F, -1.0F, -0.1F, -0.25F, -0.35F));

        // 颔下飘逸仙髯美须 (Chin Beard)
        head.addOrReplaceChild("chin_beard", CubeListBuilder.create()
                // 颌下飘逸美髯上段
                .texOffs(22, 70).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                // 仙髯下垂飘带尾尖
                .texOffs(36, 70).addBox(-1.0F, 5.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 4.5F, -9.5F, 0.12F, 0.0F, 0.0F));

        // 金玉神鹿角 - 左角 (Left Branching Antler)
        PartDefinition leftAntler = head.addOrReplaceChild("left_antler", CubeListBuilder.create()
                // 主干角基 (Main Antler Trunk Base)
                .texOffs(84, 70).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F)
                // 主干向上后仰弧段 (Upper Curved Beam)
                .texOffs(94, 70).addBox(-0.8F, -11.0F, 0.0F, 2.0F, 6.0F, 2.0F)
                // 尖端神芒角梢 (Antler Tip Beam)
                .texOffs(104, 70).addBox(-0.5F, -15.0F, 1.5F, 1.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(2.4F, -4.5F, -2.5F, -0.2F, 0.0F, 0.22F));

        leftAntler.addOrReplaceChild("front_tine", CubeListBuilder.create()
                // 前分叉支角 (Front Prong Tine - Branching forward & up)
                .texOffs(84, 80).addBox(-0.5F, -2.5F, -3.0F, 1.0F, 2.0F, 4.0F)
                .texOffs(84, 80).addBox(-0.5F, -5.5F, -3.0F, 1.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, -0.35F, 0.0F, 0.1F));

        leftAntler.addOrReplaceChild("back_tine", CubeListBuilder.create()
                // 后分叉支角 (Back Prong Tine - Branching backward & out)
                .texOffs(96, 80).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 4.0F)
                .texOffs(96, 80).addBox(-0.5F, -4.5F, 2.5F, 1.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -8.0F, 0.5F, 0.35F, 0.0F, 0.15F));

        // 金玉神鹿角 - 右角 (Right Branching Antler)
        PartDefinition rightAntler = head.addOrReplaceChild("right_antler", CubeListBuilder.create()
                .texOffs(84, 70).mirror().addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F)
                .texOffs(94, 70).mirror().addBox(-1.2F, -11.0F, 0.0F, 2.0F, 6.0F, 2.0F)
                .texOffs(104, 70).mirror().addBox(-0.5F, -15.0F, 1.5F, 1.0F, 5.0F, 2.0F).mirror(false),
                PartPose.offsetAndRotation(-2.4F, -4.5F, -2.5F, -0.2F, 0.0F, -0.22F));

        rightAntler.addOrReplaceChild("front_tine", CubeListBuilder.create()
                .texOffs(84, 80).mirror().addBox(-0.5F, -2.5F, -3.0F, 1.0F, 2.0F, 4.0F)
                .texOffs(84, 80).mirror().addBox(-0.5F, -5.5F, -3.0F, 1.0F, 3.0F, 1.0F).mirror(false),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, -0.35F, 0.0F, -0.1F));

        rightAntler.addOrReplaceChild("back_tine", CubeListBuilder.create()
                .texOffs(96, 80).mirror().addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 4.0F)
                .texOffs(96, 80).mirror().addBox(-0.5F, -4.5F, 2.5F, 1.0F, 3.0F, 1.0F).mirror(false),
                PartPose.offsetAndRotation(0.0F, -8.0F, 0.5F, 0.35F, 0.0F, -0.15F));

        // 左右飘拂灵动龙须 (Flowing Dragon Whiskers)
        head.addOrReplaceChild("left_whisker", CubeListBuilder.create()
                .texOffs(0, 80).addBox(0.0F, -0.5F, -0.5F, 1.0F, 1.0F, 4.0F)
                .texOffs(12, 80).addBox(0.2F, -0.2F, 3.5F, 1.0F, 1.0F, 7.0F),
                PartPose.offsetAndRotation(3.2F, 0.5F, -10.0F, 0.1F, 0.25F, -0.1F));

        head.addOrReplaceChild("right_whisker", CubeListBuilder.create()
                .texOffs(0, 80).mirror().addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 4.0F)
                .texOffs(12, 80).mirror().addBox(-1.2F, -0.2F, 3.5F, 1.0F, 1.0F, 7.0F).mirror(false),
                PartPose.offsetAndRotation(-3.2F, 0.5F, -10.0F, 0.1F, -0.25F, 0.1F));

        // =========================================================================
        // 4. 祥云长牛尾与流火尾簇 (Tail & Voluminous Cloud Flame Tassel)
        // =========================================================================
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                // 牛尾根部 (Tail Root)
                .texOffs(68, 100).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 3.0F, 3.0F)
                // 细长牛尾杆 (Tail Stalk)
                .texOffs(82, 100).addBox(-1.0F, 0.5F, 2.5F, 2.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, -3.0F, 8.5F, 0.45F, 0.0F, 0.0F));

        tail.addOrReplaceChild("tail_flame", CubeListBuilder.create()
                // 尾尖丰盈祥云火簇主核 (Voluminous Cloud Flame Tassel Central Core)
                .texOffs(68, 112).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 7.0F)
                // 展卷流火云纹侧翎 (Flared Cloud Wisps)
                .texOffs(94, 112).addBox(-3.5F, -1.5F, 2.0F, 7.0F, 3.0F, 5.0F)
                // 向上翻卷飞焰尖刃 (Flame Spire Tip)
                .texOffs(104, 100).addBox(-1.5F, -3.5F, 4.0F, 3.0F, 2.0F, 4.0F),
                PartPose.offset(0.0F, 1.5F, 10.0F));

        // =========================================================================
        // 5. 四肢多段踏云金蹄 (4 Segmented Limbs & Cloud Fire Hooves)
        // =========================================================================
        // 左前肢 (Left Front Leg)
        PartDefinition leftFrontLeg = root.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                // 肩胛大腿 (Upper Thigh / Shoulder)
                .texOffs(0, 90).addBox(-1.75F, 0.0F, -2.0F, 3.5F, 6.5F, 4.0F),
                PartPose.offset(4.5F, 11.0F, -6.0F));

        leftFrontLeg.addOrReplaceChild("left_front_shank", CubeListBuilder.create()
                // 矫健小腿 (Lower Shank)
                .texOffs(18, 90).addBox(-1.4F, 0.0F, -1.5F, 2.8F, 5.5F, 3.0F)
                // 蹄踝踏云烈焰 tufts (Cloud Fire Ankle Tufts)
                .texOffs(32, 90).addBox(-2.0F, 2.5F, 0.5F, 4.0F, 3.5F, 3.0F)
                // 侧向祥云纹 (Side Cloud Crest)
                .texOffs(48, 90).addBox(-2.2F, 3.0F, -1.5F, 4.4F, 2.5F, 2.5F)
                // 金光分瓣蹄 (Golden Hoof)
                .texOffs(66, 90).addBox(-1.8F, 5.5F, -2.2F, 3.6F, 1.5F, 4.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 右前肢 (Right Front Leg)
        PartDefinition rightFrontLeg = root.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(0, 90).mirror().addBox(-1.75F, 0.0F, -2.0F, 3.5F, 6.5F, 4.0F).mirror(false),
                PartPose.offset(-4.5F, 11.0F, -6.0F));

        rightFrontLeg.addOrReplaceChild("right_front_shank", CubeListBuilder.create()
                .texOffs(18, 90).mirror().addBox(-1.4F, 0.0F, -1.5F, 2.8F, 5.5F, 3.0F)
                .texOffs(32, 90).mirror().addBox(-2.0F, 2.5F, 0.5F, 4.0F, 3.5F, 3.0F)
                .texOffs(48, 90).mirror().addBox(-2.2F, 3.0F, -1.5F, 4.4F, 2.5F, 2.5F)
                .texOffs(66, 90).mirror().addBox(-1.8F, 5.5F, -2.2F, 3.6F, 1.5F, 4.0F).mirror(false),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        // 左后肢 (Left Hind Leg)
        PartDefinition leftHindLeg = root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                // 健硕后臀腿 (Upper Haunch / Thigh)
                .texOffs(0, 104).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 7.0F, 5.0F),
                PartPose.offset(4.2F, 11.0F, 6.0F));

        leftHindLeg.addOrReplaceChild("left_hind_shank", CubeListBuilder.create()
                // 后胫小腿 (Lower Shank)
                .texOffs(20, 104).addBox(-1.4F, 0.0F, -1.5F, 2.8F, 5.0F, 3.0F)
                // 后足踏云烈焰 tufts (Cloud Fire Ankle Tufts)
                .texOffs(34, 104).addBox(-2.0F, 2.0F, 0.5F, 4.0F, 3.5F, 3.0F)
                // 金光神蹄 (Golden Hoof)
                .texOffs(50, 104).addBox(-1.8F, 5.0F, -2.0F, 3.6F, 1.5F, 4.0F),
                PartPose.offset(0.0F, 6.5F, 0.5F));

        // 右后肢 (Right Hind Leg)
        PartDefinition rightHindLeg = root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(0, 104).mirror().addBox(-2.0F, 0.0F, -2.5F, 4.0F, 7.0F, 5.0F).mirror(false),
                PartPose.offset(-4.2F, 11.0F, 6.0F));

        rightHindLeg.addOrReplaceChild("right_hind_shank", CubeListBuilder.create()
                .texOffs(20, 104).mirror().addBox(-1.4F, 0.0F, -1.5F, 2.8F, 5.0F, 3.0F)
                .texOffs(34, 104).mirror().addBox(-2.0F, 2.0F, 0.5F, 4.0F, 3.5F, 3.0F)
                .texOffs(50, 104).mirror().addBox(-1.8F, 5.0F, -2.0F, 3.6F, 1.5F, 4.0F).mirror(false),
                PartPose.offset(0.0F, 6.5F, 0.5F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // =========================================================================
        // 1. 龙头与曲颈：注视偏转与轻柔呼吸点首 (Head Subtle Nodding & Looking Around)
        // =========================================================================
        float headPitch = state.xRot * ((float) Math.PI / 180F);
        float headYaw = state.yRot * ((float) Math.PI / 180F);

        float breathCycle = Mth.sin(age * 0.06F);
        float nodIdle = breathCycle * 0.04F;
        float yawIdle = Mth.cos(age * 0.03F) * 0.02F;

        // 颈部柔和承接偏转与微倾
        this.neck.xRot = -0.35F + headPitch * 0.35F + nodIdle * 0.6F;
        this.neck.yRot = headYaw * 0.35F + yawIdle;

        // 头部主导视野注视与点首
        this.head.xRot = 0.35F + headPitch * 0.65F + nodIdle;
        this.head.yRot = headYaw * 0.65F;
        this.head.zRot = headYaw * 0.1F; // 转向时龙头随仙姿自然微侧倾

        // 仙耳灵动微颤 (Flared Celestial Ears)
        float earTwitch = Mth.sin(age * 0.08F) * 0.04F;
        this.leftEar.zRot = 0.35F + earTwitch;
        this.rightEar.zRot = -0.35F - earTwitch;

        // =========================================================================
        // 2. 龙须与多层狮鬃：仙气飘拂波浪律动 (Whiskers and Mane Gentle Floating Wave)
        // =========================================================================
        float waveTime = age * 0.12F;
        float whiskerSin = Mth.sin(waveTime);
        float whiskerCos = Mth.cos(waveTime);

        this.leftWhisker.yRot = 0.25F + whiskerSin * 0.18F;
        this.leftWhisker.zRot = -0.10F + whiskerCos * 0.12F;
        this.rightWhisker.yRot = -0.25F - whiskerSin * 0.18F;
        this.rightWhisker.zRot = 0.10F - whiskerCos * 0.12F;

        // 颔下美髯随气流与呼吸轻扬
        this.chinBeard.xRot = 0.12F + Mth.sin(waveTime * 0.8F) * 0.08F;
        this.chinBeard.zRot = Mth.cos(waveTime * 0.6F) * 0.05F;

        // 三层狮鬃与烈焰颈鬣阶梯式波浪律动 (Layered Ruff Mane Wave)
        float maneSpeed = age * 0.08F;
        this.neckManeLower.xRot = 0.15F + Mth.sin(maneSpeed) * 0.04F;
        this.neckManeLower.zRot = Mth.cos(maneSpeed) * 0.03F;

        this.neckManeMid.xRot = 0.10F + Mth.sin(maneSpeed + 0.8F) * 0.05F;
        this.neckManeMid.zRot = Mth.cos(maneSpeed + 0.8F) * 0.04F;

        this.neckManeUpper.xRot = 0.05F + Mth.sin(maneSpeed + 1.6F) * 0.06F;
        this.neckManeUpper.zRot = Mth.cos(maneSpeed + 1.6F) * 0.05F;

        // 背脊高耸烈焰鬣随躯干微摆
        this.dorsalMane.zRot = Mth.sin(maneSpeed * 1.2F + walkPos * 0.4F) * 0.04F;

        // =========================================================================
        // 3. 四足踏云蹄：自然四足奔腾/踱步步态 (Natural Quadruped Galloping Walk Cycle)
        // =========================================================================
        float walkFreq = 0.6662F;
        float frontSwing = Mth.cos(walkPos * walkFreq) * 1.0F * walkSpeed;
        float frontSwingOpposite = Mth.cos(walkPos * walkFreq + (float) Math.PI) * 1.0F * walkSpeed;

        this.leftFrontLeg.xRot = frontSwing;
        this.rightFrontLeg.xRot = frontSwingOpposite;

        // 前足关节屈伸 (前腿后摆蹬地时挺直，前迈腾空时小腿膝盖微屈)
        this.leftFrontShank.xRot = Math.max(0.0F, Mth.sin(walkPos * walkFreq)) * 0.6F * walkSpeed;
        this.rightFrontShank.xRot = Math.max(0.0F, Mth.sin(walkPos * walkFreq + (float) Math.PI)) * 0.6F * walkSpeed;

        // 后足相位差 (对角步态 / 骏马奔驰连贯推进)
        float hindPhase = (float) Math.PI * 0.75F;
        float hindSwing = Mth.cos(walkPos * walkFreq + hindPhase) * 1.0F * walkSpeed;
        float hindSwingOpposite = Mth.cos(walkPos * walkFreq + hindPhase + (float) Math.PI) * 1.0F * walkSpeed;

        this.leftHindLeg.xRot = hindSwing;
        this.rightHindLeg.xRot = hindSwingOpposite;

        // 后足小腿关节屈伸
        this.leftHindShank.xRot = Math.max(0.0F, -Mth.sin(walkPos * walkFreq + hindPhase)) * 0.55F * walkSpeed;
        this.rightHindShank.xRot = Math.max(0.0F, -Mth.sin(walkPos * walkFreq + hindPhase + (float) Math.PI)) * 0.55F * walkSpeed;

        // 躯干在踱步奔跑时的微弱起伏与侧倾 (Subtle Torso Gallop Dynamics)
        this.body.yRot = Mth.cos(walkPos * walkFreq * 0.5F) * 0.04F * walkSpeed;
        this.body.zRot = Mth.sin(walkPos * walkFreq) * 0.03F * walkSpeed;
        this.body.xRot = Mth.sin(walkPos * walkFreq * 2.0F) * 0.03F * walkSpeed;

        // =========================================================================
        // 4. 祥云火尾：韵律摇曳与火簇二次飘拂 (Tail Flame Rhythmic Sway)
        // =========================================================================
        float tailTime = age * 0.10F + walkPos * 0.35F;
        float tailBaseSway = Mth.cos(tailTime);
        this.tail.yRot = tailBaseSway * (0.22F + 0.28F * walkSpeed);
        this.tail.xRot = 0.45F + Mth.sin(age * 0.07F) * 0.06F - walkSpeed * 0.15F;

        // 祥云火簇尾缨滞后摆动 (二次摆动增强如梦似幻的流火感)
        this.tailFlame.yRot = Mth.cos(tailTime - 0.7F) * (0.32F + 0.35F * walkSpeed);
        this.tailFlame.zRot = Mth.sin(tailTime) * 0.12F;
        this.tailFlame.xRot = Mth.sin(age * 0.09F) * 0.08F;
    }
}
