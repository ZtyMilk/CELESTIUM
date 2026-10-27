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
 * 獬豸·天宪神兽 (Xie Zhi Model) - 神兽大师级多立方体树状解剖体系
 *
 * 1. 雄健兽躯 (body):
 *    - 雄狮胸膛、后腰胯部、护心正气符牌 (Justice Chest Medallion)、背脊脊骨与金焰棘刺、肩甲祥云飞焰。
 * 2. 颈项与双层神鬃 (body -> neck -> mane_lower, mane_upper):
 *    - 粗壮颈骨、下层大环幅祥云金鬃 (mane_lower)、上层托颅烈焰鬃冠 (mane_upper)。
 * 3. 仙首与神圣独角 (neck -> head -> horn, jaw, beard, ears):
 *    - 威严神首、长吻利齿、开阖下颚、飘逸长髯美须、机警仙耳。
 *    - 核心之作：天宪神圣独角 (The Divine Single Horn of Justice) —— 五段式前扬曲度螺旋神角，明辨是非！
 * 4. 前后四肢多段关节 (body -> left_front_leg, right_front_leg, left_hind_leg, right_hind_leg):
 *    - 粗壮大臂/股骨、小腿胫骨、五爪金刚肉掌与锋锐神爪。
 * 5. 祥云长尾与灵拂火簇 (body -> tail -> tail_tuft):
 *    - 遒劲尾根、长尾轴、翻卷如意神焰尾缨。
 */
public class XieZhiModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("xie_zhi"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart maneLower;
    private final ModelPart maneUpper;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart beard;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart horn;
    private final ModelPart tail;
    private final ModelPart tailTuft;

    // 前后四肢多段关节 (Forearms, Shanks, Paws)
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontForearm;
    private final ModelPart leftFrontPaw;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontForearm;
    private final ModelPart rightFrontPaw;

    private final ModelPart leftHindLeg;
    private final ModelPart leftHindShank;
    private final ModelPart leftHindPaw;
    private final ModelPart rightHindLeg;
    private final ModelPart rightHindShank;
    private final ModelPart rightHindPaw;

    public XieZhiModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");

        // 颈项与双层神鬃
        this.neck = this.body.getChild("neck");
        this.maneLower = this.neck.getChild("mane_lower");
        this.maneUpper = this.neck.getChild("mane_upper");

        // 仙首与独角、五官
        this.head = this.neck.getChild("head");
        this.jaw = this.head.getChild("jaw");
        this.beard = this.head.getChild("beard");
        this.leftEar = this.head.getChild("left_ear");
        this.rightEar = this.head.getChild("right_ear");
        this.horn = this.head.getChild("horn");

        // 祥云神尾
        this.tail = this.body.getChild("tail");
        this.tailTuft = this.tail.getChild("tail_tuft");

        // 前肢
        this.leftFrontLeg = this.body.getChild("left_front_leg");
        this.leftFrontForearm = this.leftFrontLeg.getChild("left_front_forearm");
        this.leftFrontPaw = this.leftFrontForearm.getChild("left_front_paw");

        this.rightFrontLeg = this.body.getChild("right_front_leg");
        this.rightFrontForearm = this.rightFrontLeg.getChild("right_front_forearm");
        this.rightFrontPaw = this.rightFrontForearm.getChild("right_front_paw");

        // 后肢
        this.leftHindLeg = this.body.getChild("left_hind_leg");
        this.leftHindShank = this.leftHindLeg.getChild("left_hind_shank");
        this.leftHindPaw = this.leftHindShank.getChild("left_hind_paw");

        this.rightHindLeg = this.body.getChild("right_hind_leg");
        this.rightHindShank = this.rightHindLeg.getChild("right_hind_shank");
        this.rightHindPaw = this.rightHindShank.getChild("right_hind_paw");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 雄健兽躯与正气宝甲 (Body: Thorax, Haunches, Medallion, Dorsal Spines)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 雄健狮身主胸膛 (Thorax & Ribcage)
                .texOffs(0, 0).addBox(-6.0F, -6.0F, -10.0F, 12.0F, 12.0F, 11.0F)
                // 健美腹腰与后胯 (Haunches & Abdomen)
                .texOffs(46, 0).addBox(-5.5F, -5.5F, 1.0F, 11.0F, 11.0F, 10.0F)
                // 天宪护心正气符牌外框 (Justice Chest Medallion Rim)
                .texOffs(88, 12).addBox(-4.0F, -3.0F, -11.0F, 8.0F, 8.0F, 2.0F)
                // 天宪护心正气符牌宝心 (Justice Medallion Core Emblem)
                .texOffs(90, 22).addBox(-2.5F, -1.5F, -11.8F, 5.0F, 5.0F, 1.0F)
                // 左侧肩部祥云羽焰 (Left Shoulder Cloud Flame)
                .texOffs(88, 0).addBox(6.0F, -8.0F, -9.0F, 3.0F, 5.0F, 7.0F)
                // 右侧肩部祥云羽焰 (Right Shoulder Cloud Flame)
                .texOffs(108, 0).addBox(-9.0F, -8.0F, -9.0F, 3.0F, 5.0F, 7.0F)
                // 前段背脊骨骨脊 (Anterior Dorsal Spine)
                .texOffs(46, 21).addBox(-1.5F, -7.5F, -8.0F, 3.0F, 2.0F, 8.0F)
                // 后段背脊骨骨脊 (Posterior Dorsal Spine)
                .texOffs(68, 22).addBox(-1.5F, -7.0F, 1.0F, 3.0F, 2.0F, 8.0F)
                // 前段背脊向天金焰刺 (Anterior Golden Spine Flames)
                .texOffs(108, 12).addBox(-0.5F, -10.0F, -6.0F, 1.0F, 3.0F, 5.0F)
                // 后段背脊向天金焰刺 (Posterior Golden Spine Flames)
                .texOffs(108, 20).addBox(-0.5F, -9.5F, 2.5F, 1.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 9.5F, 0.0F));

        // =========================================================================
        // 2. 颈项与双层神圣雄鬃 (Neck & Multi-Layered Divine Mane)
        // =========================================================================
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                // 雄健颈项核心 (Neck Core)
                .texOffs(0, 32).addBox(-4.0F, -6.0F, -5.5F, 8.0F, 9.0F, 7.0F)
                // 颈背金焰鬃脊 (Neck Dorsal Crest)
                .texOffs(0, 48).addBox(-1.0F, -8.5F, -3.5F, 2.0F, 3.0F, 5.0F)
                // 颈下威仪喉鬃肉垂 (Throat Dewlap Tufts)
                .texOffs(14, 48).addBox(-2.5F, 2.5F, -5.0F, 5.0F, 2.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -2.5F, -8.5F, -0.2F, 0.0F, 0.0F));

        // 下层神鬃：环幅雄壮祥云鬃环 (Lower Divine Cloud Mane)
        neck.addOrReplaceChild("mane_lower", CubeListBuilder.create()
                // 下层主领鬃 (Mane Lower Collar)
                .texOffs(30, 32).addBox(-6.5F, -3.0F, -4.5F, 13.0F, 8.0F, 8.0F)
                // 左侧飞扬鬃毛云簇 (Left Flared Mane Tuft)
                .texOffs(72, 32).addBox(6.2F, -1.5F, -3.5F, 2.0F, 6.0F, 6.0F)
                // 右侧飞扬鬃毛云簇 (Right Flared Mane Tuft)
                .texOffs(88, 32).addBox(-8.2F, -1.5F, -3.5F, 2.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, -0.5F));

        // 上层神鬃：托颌烈焰金鬃冠 (Upper Divine Flame Mane)
        neck.addOrReplaceChild("mane_upper", CubeListBuilder.create()
                // 上层主鬃冠 (Mane Upper Hood)
                .texOffs(72, 44).addBox(-5.5F, -4.5F, -3.5F, 11.0F, 6.0F, 6.0F)
                // 颈顶怒张火鬃棘 (Mane Upper Flame Crest)
                .texOffs(36, 48).addBox(-3.5F, -6.5F, -2.5F, 7.0F, 3.0F, 5.0F)
                // 左面颊怒张鬃簇 (Left Cheek Mane Tuft)
                .texOffs(104, 32).addBox(4.5F, -2.5F, -3.5F, 2.0F, 4.0F, 5.0F)
                // 右面颊怒张鬃簇 (Right Cheek Mane Tuft)
                .texOffs(106, 41).addBox(-6.5F, -2.5F, -3.5F, 2.0F, 4.0F, 5.0F),
                PartPose.offset(0.0F, -3.5F, -2.0F));

        // =========================================================================
        // 3. 仙首、威严长吻与辟邪仙耳 (Noble Head, Snout, Jaws, Beard, Ears)
        // =========================================================================
        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                // 狮形神首主颅 (Noble Feline/Caprine Cranium)
                .texOffs(0, 60).addBox(-4.5F, -5.0F, -6.5F, 9.0F, 8.0F, 8.0F)
                // 威严长吻上颚 (Snout & Upper Muzzle)
                .texOffs(34, 60).addBox(-3.0F, -1.5F, -12.0F, 6.0F, 4.0F, 6.0F)
                // 隆起鼻梁与眼眶眉脊 (Nose Bridge & Brow Ridge)
                .texOffs(84, 67).addBox(-2.5F, -3.0F, -11.0F, 5.0F, 2.0F, 4.0F)
                // 上颚正气利齿獠牙 (Upper Fangs & Teeth)
                .texOffs(34, 70).addBox(-2.5F, 2.0F, -11.5F, 5.0F, 1.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -5.0F, -4.0F, 0.2F, 0.0F, 0.0F));

        // 威仪下颚 (Lower Jaw)
        head.addOrReplaceChild("jaw", CubeListBuilder.create()
                // 下颚骨主体 (Lower Jaw Body)
                .texOffs(58, 60).addBox(-2.5F, 0.0F, -5.5F, 5.0F, 2.0F, 6.0F)
                // 下颚向天利齿 (Lower Teeth)
                .texOffs(48, 70).addBox(-2.0F, -0.8F, -5.0F, 4.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 1.5F, -6.0F));

        // 颔下飘逸长髯仙须 (Flowing Chin Beard / Goatee)
        head.addOrReplaceChild("beard", CubeListBuilder.create()
                // 须髯上段 (Upper Beard Base)
                .texOffs(80, 60).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F)
                // 须髯下垂拂尘尖梢 (Lower Flowing Beard Tip)
                .texOffs(58, 68).addBox(-1.0F, 3.8F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, 3.5F, -10.0F));

        // 左右机警仙耳 (Expressive Celestial Ears)
        head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                .texOffs(102, 67).addBox(0.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(3.8F, -4.5F, -2.0F, -0.15F, 0.25F, 0.35F));

        head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(110, 67).addBox(-2.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(-3.8F, -4.5F, -2.0F, -0.15F, -0.25F, -0.35F));

        // =========================================================================
        // 4. 天宪神圣独角 (The Divine Single Horn of Justice - Centerpiece!)
        // =========================================================================
        head.addOrReplaceChild("horn", CubeListBuilder.create()
                // 第1段：独角神圣金座基台 (Horn Base Pedestal / Lotus Socket)
                .texOffs(92, 60).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F)
                // 第2段：角根螺旋坚固段 (Horn Segment 2 - Root)
                .texOffs(108, 60).addBox(-1.5F, -6.0F, -2.0F, 3.0F, 4.0F, 3.0F)
                // 第2段螺旋金棱 (Spiral Gold Ring Rib 1)
                .texOffs(70, 68).addBox(-1.0F, -5.5F, -2.5F, 2.0F, 3.0F, 1.0F)
                // 第3段：弧形前扬中段 (Horn Segment 3 - Curving Forward & Up)
                .texOffs(120, 60).addBox(-1.0F, -10.5F, -2.8F, 2.0F, 5.0F, 2.0F)
                // 第3段螺旋金棱 (Spiral Gold Ring Rib 2)
                .texOffs(66, 68).addBox(-0.5F, -9.5F, -3.5F, 1.0F, 4.0F, 1.0F)
                // 第4段：修长尖锥段 (Horn Segment 4 - Slender Upper)
                .texOffs(118, 67).addBox(-0.5F, -15.0F, -4.0F, 1.0F, 5.0F, 1.0F)
                // 第5段：辨别是非之破邪锐芒角尖 (Horn Segment 5 - Piercing Justice Tip)
                .texOffs(122, 67).addBox(-0.5F, -19.5F, -5.5F, 1.0F, 5.0F, 1.0F)
                // 独角背侧支撑金棱 (Horn Dorsal Reinforcing Ridge)
                .texOffs(80, 67).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 6.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -5.0F, -4.5F, -0.35F, 0.0F, 0.0F));

        // =========================================================================
        // 5. 前后四肢多段关节 (4 Segmented Feline Limbs with Claws)
        // =========================================================================
        // 左前肢 (Left Front Leg)
        PartDefinition leftFrontLeg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                // 健硕肩胛大臂 (Muscular Upper Shoulder)
                .texOffs(0, 78).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F),
                PartPose.offset(4.8F, 2.5F, -7.0F));

        PartDefinition leftFrontForearm = leftFrontLeg.addOrReplaceChild("left_front_forearm", CubeListBuilder.create()
                // 前臂小腿 (Forearm)
                .texOffs(40, 78).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                // 肘关节神焰毛簇 (Elbow Cloud Tuft)
                .texOffs(112, 78).addBox(-1.5F, 0.0F, 1.5F, 3.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        leftFrontForearm.addOrReplaceChild("left_front_paw", CubeListBuilder.create()
                // 宽厚狮爪掌垫 (Broad Lion Paw)
                .texOffs(72, 78).addBox(-2.5F, 0.0F, -3.5F, 5.0F, 2.0F, 5.0F)
                // 破邪锋锐金爪 (Sharp Claws)
                .texOffs(72, 85).addBox(-2.0F, 1.0F, -4.5F, 4.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 4.5F, -0.5F));

        // 右前肢 (Right Front Leg)
        PartDefinition rightFrontLeg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(20, 78).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F),
                PartPose.offset(-4.8F, 2.5F, -7.0F));

        PartDefinition rightFrontForearm = rightFrontLeg.addOrReplaceChild("right_front_forearm", CubeListBuilder.create()
                .texOffs(56, 78).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                .texOffs(112, 83).addBox(-1.5F, 0.0F, 1.5F, 3.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        rightFrontForearm.addOrReplaceChild("right_front_paw", CubeListBuilder.create()
                .texOffs(92, 78).addBox(-2.5F, 0.0F, -3.5F, 5.0F, 2.0F, 5.0F)
                .texOffs(82, 85).addBox(-2.0F, 1.0F, -4.5F, 4.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 4.5F, -0.5F));

        // 左后肢 (Left Hind Leg)
        PartDefinition leftHindLeg = body.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                // 雄厚后臀股骨 (Muscular Haunch / Thigh)
                .texOffs(0, 94).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F),
                PartPose.offset(4.5F, 2.5F, 7.5F));

        PartDefinition leftHindShank = leftHindLeg.addOrReplaceChild("left_hind_shank", CubeListBuilder.create()
                // 后小腿胫骨 (Hind Shank / Hock)
                .texOffs(44, 94).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                // 足跟祥云火鬃 (Heel Flame Tuft)
                .texOffs(116, 94).addBox(-1.5F, 2.0F, 1.5F, 3.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, 6.0F, 1.0F));

        leftHindShank.addOrReplaceChild("left_hind_paw", CubeListBuilder.create()
                .texOffs(76, 94).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 2.0F, 5.0F)
                .texOffs(76, 101).addBox(-2.0F, 1.0F, -4.0F, 4.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 4.5F, -1.5F));

        // 右后肢 (Right Hind Leg)
        PartDefinition rightHindLeg = body.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(22, 94).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F),
                PartPose.offset(-4.5F, 2.5F, 7.5F));

        PartDefinition rightHindShank = rightHindLeg.addOrReplaceChild("right_hind_shank", CubeListBuilder.create()
                .texOffs(60, 94).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F)
                .texOffs(116, 99).addBox(-1.5F, 2.0F, 1.5F, 3.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, 6.0F, 1.0F));

        rightHindShank.addOrReplaceChild("right_hind_paw", CubeListBuilder.create()
                .texOffs(96, 94).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 2.0F, 5.0F)
                .texOffs(86, 101).addBox(-2.0F, 1.0F, -4.0F, 4.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 4.5F, -1.5F));

        // =========================================================================
        // 6. 祥云神尾与拂动火簇 (Tail: Root, Stalk, Flame Tuft Tassel)
        // =========================================================================
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                // 雄健尾根节 (Tail Root)
                .texOffs(46, 110).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 4.0F, 4.0F)
                // 遒劲长尾轴 (Tail Shaft)
                .texOffs(26, 110).addBox(-1.5F, -1.0F, 3.5F, 3.0F, 3.0F, 7.0F),
                PartPose.offsetAndRotation(0.0F, -3.5F, 10.0F, 0.55F, 0.0F, 0.0F));

        tail.addOrReplaceChild("tail_tuft", CubeListBuilder.create()
                // 翻卷祥云火簇核心 (Flame Tuft Core)
                .texOffs(0, 110).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 7.0F)
                // 侧翼展卷飞扬火羽 (Side Flame Flare)
                .texOffs(62, 110).addBox(-4.0F, -2.0F, 2.0F, 8.0F, 4.0F, 4.0F)
                // 尾尖飘拂金焰梢 (Trailing Flame Tip)
                .texOffs(86, 110).addBox(-2.0F, -1.0F, 6.5F, 4.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, 0.5F, 10.0F, -0.25F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        float headPitch = state.xRot * ((float) Math.PI / 180F);
        float headYaw = state.yRot * ((float) Math.PI / 180F);

        // =========================================================================
        // 1. 雄健威严猫科/神兽步态与躯干沉稳摇摆 (Confident Feline Prowl & Weight Shift)
        // =========================================================================
        // 躯体沉稳起伏 (Vertical Prowl Oscillation)
        this.body.y = 9.5F + Mth.abs(Mth.sin(walkPos * 0.6662F)) * 0.6F * walkSpeed;
        // 行走时雄狮肩胯自然微卷与滚转 (Lateral Roll & Sway)
        this.body.zRot = Mth.cos(walkPos * 0.6662F) * 0.04F * walkSpeed;
        this.body.yRot = Mth.sin(walkPos * 0.3331F) * 0.03F * walkSpeed;
        this.body.xRot = Mth.sin(walkPos * 1.3324F) * 0.02F * walkSpeed;

        // 四足对角步态相位 (Diagonal Quadrupedal Gait)
        float flPhase = walkPos * 0.6662F;
        float frPhase = flPhase + (float) Math.PI;

        float flSwing = Mth.cos(flPhase);
        float frSwing = Mth.cos(frPhase);

        // 左前肢与关节屈伸 (Left Front Leg & Paw Flex)
        this.leftFrontLeg.xRot = flSwing * 0.75F * walkSpeed;
        this.leftFrontForearm.xRot = Math.max(0.0F, flSwing) * 0.45F * walkSpeed;
        this.leftFrontPaw.xRot = -Math.max(0.0F, flSwing) * 0.35F * walkSpeed + (flSwing < 0 ? -flSwing * 0.15F * walkSpeed : 0.0F);

        // 右前肢与关节屈伸 (Right Front Leg & Paw Flex)
        this.rightFrontLeg.xRot = frSwing * 0.75F * walkSpeed;
        this.rightFrontForearm.xRot = Math.max(0.0F, frSwing) * 0.45F * walkSpeed;
        this.rightFrontPaw.xRot = -Math.max(0.0F, frSwing) * 0.35F * walkSpeed + (frSwing < 0 ? -frSwing * 0.15F * walkSpeed : 0.0F);

        // 左后肢（对角配对右前肢）与跗关节/爪屈伸 (Left Hind Leg & Paw Flex)
        this.leftHindLeg.xRot = frSwing * 0.75F * walkSpeed;
        this.leftHindShank.xRot = Math.max(0.0F, -frSwing) * 0.45F * walkSpeed;
        this.leftHindPaw.xRot = -Math.max(0.0F, -frSwing) * 0.3F * walkSpeed;

        // 右后肢（对角配对左前肢）与跗关节/爪屈伸 (Right Hind Leg & Paw Flex)
        this.rightHindLeg.xRot = flSwing * 0.75F * walkSpeed;
        this.rightHindShank.xRot = Math.max(0.0F, -flSwing) * 0.45F * walkSpeed;
        this.rightHindPaw.xRot = -Math.max(0.0F, -flSwing) * 0.3F * walkSpeed;

        // =========================================================================
        // 2. 颈项与仙首庄严微点头、视线追踪 (Neck & Head Tracking & Solemn Nodding)
        // =========================================================================
        // 肃穆呼吸微点律动 + 行走威仪节律点头
        float idleNod = Mth.sin(age * 0.05F) * 0.04F;
        float walkNod = Mth.cos(walkPos * 0.6662F) * 0.08F * walkSpeed;

        // 粗壮颈项承担部分转向与俯仰
        this.neck.xRot = -0.2F + headPitch * 0.35F + idleNod * 0.4F + walkNod * 0.3F;
        this.neck.yRot = headYaw * 0.35F;

        // 仙首精准追踪玩家视线与庄严威仪微点头
        this.head.xRot = 0.2F + headPitch * 0.55F + idleNod * 0.6F + walkNod * 0.5F;
        this.head.yRot = headYaw * 0.55F;
        this.head.zRot = Mth.sin(age * 0.04F) * 0.02F;

        // 机警双耳微动（倾听天道仙音与正邪之辨）
        float earTwitch = (float) Math.pow(Math.max(0.0, Math.sin(age * 0.06F)), 8.0) * Mth.sin(age * 0.9F) * 0.15F;
        this.leftEar.zRot = 0.35F + Mth.sin(age * 0.05F) * 0.03F + earTwitch;
        this.rightEar.zRot = -0.35F - Mth.sin(age * 0.05F + 1.2F) * 0.03F - earTwitch;

        // 威仪下颚微张微合，吐纳天地正气
        this.jaw.xRot = 0.05F + Mth.sin(age * 0.07F) * 0.03F;

        // 颔下美髯随风与重力飘拂
        this.beard.xRot = 0.1F + Mth.cos(age * 0.08F) * 0.05F + walkSpeed * 0.1F;
        this.beard.zRot = Mth.sin(age * 0.06F) * 0.04F;

        // =========================================================================
        // 3. 双层神鬃随天风与步态轻灵飘曳 (Multi-layered Mane Celestial Sway)
        // =========================================================================
        float breezeLower = Mth.sin(age * 0.08F) * 0.04F + Mth.cos(walkPos * 0.6662F) * 0.05F * walkSpeed;
        this.maneLower.xRot = breezeLower;
        this.maneLower.zRot = Mth.cos(age * 0.07F) * 0.03F;

        float breezeUpper = Mth.sin(age * 0.09F + 0.4F) * 0.05F + Mth.cos(walkPos * 0.6662F + 0.3F) * 0.06F * walkSpeed;
        this.maneUpper.xRot = breezeUpper;
        this.maneUpper.yRot = Mth.cos(age * 0.06F) * 0.03F;

        // =========================================================================
        // 4. 祥云神尾高扬拂动与尾簇次级滞后弹动 (Tail Swishing with Lag Bounce)
        // =========================================================================
        float tailSway = Mth.sin(age * 0.1F) * 0.25F + Mth.sin(walkPos * 0.6662F) * 0.25F * walkSpeed;
        float tailBob = Mth.cos(age * 0.07F) * 0.06F + Mth.sin(walkPos * 1.3324F) * 0.08F * walkSpeed;
        this.tail.yRot = tailSway;
        this.tail.xRot = 0.55F + tailBob;

        // 尾尖如意神焰火簇次级滞后回弹 (Secondary Lag & Rebound)
        float tuftLagY = Mth.sin(age * 0.1F - 0.6F) * 0.2F + Mth.sin(walkPos * 0.6662F - 0.5F) * 0.22F * walkSpeed;
        float tuftLagX = Mth.cos(age * 0.07F - 0.4F) * 0.08F + Mth.sin(walkPos * 1.3324F - 0.4F) * 0.10F * walkSpeed;
        this.tailTuft.yRot = tuftLagY;
        this.tailTuft.xRot = -0.25F + tuftLagX;
        this.tailTuft.zRot = Mth.sin(age * 0.08F - 0.5F) * 0.05F;
    }
}
