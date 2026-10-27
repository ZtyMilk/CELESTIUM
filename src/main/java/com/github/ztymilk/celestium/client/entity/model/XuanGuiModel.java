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
 * 旋龟·玄灵负图 (Xuan Gui - Celestial Spirit Turtle / Master Multi-Cube Anatomical Tree)
 * 《山海经·南山经》载：“其中多玄龟，其状如龟而鸟首虺尾，其名曰旋龟。”
 *
 * 神兽构思师树状解剖体系 (Hierarchical Anatomical Tree Architecture):
 * 1. 灵甲主躯 (body):
 *    - 腹甲基座 (Plastron Base) 与阶梯形腹部护板 (Decorative Underbelly Scutes & Jade Inset)
 *    - 三重碧玉穹隆主背甲 (Tiered Multi-Segment Emerald Carapace Dome)
 *    - 背甲凸棱、洛书八卦神印雕饰与前后聚灵宝玉 (Dorsal Ridge Keel, Luoshu Bagua Medallion, Gem Insets)
 *    - 护甲金镶边雕饰与裙甲缘 (Carapace Rim Flange & Ornamental Golden Trim with Notches)
 * 2. 仙禽鸟首与双关节长颈 (body -> neck_lower -> neck_upper -> head):
 *    - 下段长颈基座与丰盈咽喉羽领 (Lower Crane Neck & Feather Collar Ring)
 *    - 上段优雅 S 弯曲颈与背枕羽脊 (Upper Slender S-Curved Neck & Nape Plumage)
 *    - 灵鸟头颅 (Bird Head Skull)、锐利金喙附鼻孔 (Golden Raptor Beak with Nostrils & Mandible)
 *    - 脑后顶天碧玉仙羽玉冠 (Swept-back Crown Jade Crest & Plumage) 与颔下仙髯肉垂 (Chin Beard Wattle)
 * 3. 灵蛇盘尾四节关节 (body -> tail_base -> tail_mid1 -> tail_mid2 -> tail_tip):
 *    - 四段分节骨节 (tail_base, tail_mid1, tail_mid2, tail_tip) 呈蛇行游动物理波浪
 *    - 逐节灵蛇背棘 (Dorsal Serpentine Spines) 与尾尖破浪金刃蛇针 (Golden Barb Tip & Tail Fins)
 * 4. 踏浪四灵蹼与多节强健兽足 (body -> leg_fl, leg_fr, leg_bl, leg_br):
 *    - 雄健肩部/大腿股部 (Muscular Upper Shoulder/Thigh) 与护甲玉饰 (Shoulder/Hip Armor Plates)
 *    - 灵活前臂/小腿关节 (Forearm / Shank Joints)
 *    - 阔大踏浪蹼面与锋利金锐蹼爪 (Broad Paddle Blades & Sharp Webbed Claws)
 */
public class XuanGuiModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("xuan_gui"), "main");

    // 树状解剖体系核心模型部件 (Anatomical Tree ModelParts)
    private final ModelPart body;
    private final ModelPart neckLower;
    private final ModelPart neckUpper;
    private final ModelPart head;

    // 灵蛇盘尾四节关节 (Serpent Tail Joints)
    private final ModelPart tailBase;
    private final ModelPart tailMid1;
    private final ModelPart tailMid2;
    private final ModelPart tailTip;

    // 踏浪四灵蹼与关节 (4 Swimming Flippers & Webbed Beast Legs)
    private final ModelPart legFL;
    private final ModelPart forearmFL;
    private final ModelPart legFR;
    private final ModelPart forearmFR;
    private final ModelPart legBL;
    private final ModelPart shankBL;
    private final ModelPart legBR;
    private final ModelPart shankBR;

    public XuanGuiModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");

        // 鸟首长颈 (Avian Neck & Raptor Head)
        this.neckLower = this.body.getChild("neck_lower");
        this.neckUpper = this.neckLower.getChild("neck_upper");
        this.head = this.neckUpper.getChild("head");

        // 灵蛇盘尾四节关节 (Serpent Tail 4 Joints)
        this.tailBase = this.body.getChild("tail_base");
        this.tailMid1 = this.tailBase.getChild("tail_mid1");
        this.tailMid2 = this.tailMid1.getChild("tail_mid2");
        this.tailTip = this.tailMid2.getChild("tail_tip");

        // 踏浪四灵蹼 (4 Powerful Flippers)
        this.legFL = this.body.getChild("leg_fl");
        this.forearmFL = this.legFL.getChild("forearm_fl");
        this.legFR = this.body.getChild("leg_fr");
        this.forearmFR = this.legFR.getChild("forearm_fr");
        this.legBL = this.body.getChild("leg_bl");
        this.shankBL = this.legBL.getChild("shank_bl");
        this.legBR = this.body.getChild("leg_br");
        this.shankBR = this.legBR.getChild("shank_br");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 龟甲主躯 (Carapace Dome, Plastron & Luoshu Bagua Engravings)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 腹甲基座 (Flat Bottom Plastron Core: 15x2x19)
                .texOffs(0, 27).addBox(-7.5F, 1.0F, -9.5F, 15.0F, 2.0F, 19.0F)
                // 阶梯状腹部装饰护板 (Decorative Underbelly Scutes Plate: 12x1x15)
                .texOffs(0, 49).addBox(-6.0F, 2.5F, -7.5F, 12.0F, 1.0F, 15.0F)
                // 腹底中央聚灵玉印 (Central Umbilical Jade Gem Inset: 6x1x6)
                .texOffs(99, 65).addBox(-3.0F, 3.2F, -3.0F, 6.0F, 1.0F, 6.0F)
                // 第一重 碧玉主背甲基底 (Carapace Tier 1 Lower Emerald Dome: 17x5x21)
                .texOffs(0, 0).addBox(-8.5F, -4.0F, -10.5F, 17.0F, 5.0F, 21.0F)
                // 第二重 碧玉穹隆中层阶梯甲 (Carapace Tier 2 Middle Stepped Dome: 13x3x17)
                .texOffs(68, 26).addBox(-6.5F, -7.0F, -8.5F, 13.0F, 3.0F, 17.0F)
                // 第三重 碧玉穹顶顶层皇冠甲 (Carapace Tier 3 Upper Crown Dome: 9x2x13)
                .texOffs(0, 66).addBox(-4.5F, -9.0F, -6.5F, 9.0F, 2.0F, 13.0F)
                // 背甲正中隆起脊棱 (Dorsal Ridge Spine Keel: 3x2x13)
                .texOffs(45, 66).addBox(-1.5F, -10.5F, -6.5F, 3.0F, 2.0F, 13.0F)
                // 洛书八卦神印中枢宝盘 (Central Luoshu Bagua Medallion Plate: 5x1x5)
                .texOffs(78, 65).addBox(-2.5F, -10.0F, -2.5F, 5.0F, 1.0F, 5.0F)
                // 前脊聚灵宝玉 (Anterior Ridge Gem Plate: 3x1x2)
                .texOffs(56, 49).addBox(-1.5F, -11.0F, -5.0F, 3.0F, 1.0F, 2.0F)
                // 后脊聚灵宝玉 (Posterior Ridge Gem Plate: 3x1x2)
                .texOffs(56, 54).addBox(-1.5F, -11.0F, 3.0F, 3.0F, 1.0F, 2.0F)
                // 甲缘左侧裙翼 (Left Lateral Rim Flange: 2x2x21)
                .texOffs(78, 0).addBox(8.0F, -1.0F, -10.5F, 2.0F, 2.0F, 21.0F)
                // 甲缘右侧裙翼 (Right Lateral Rim Flange - Mirrored)
                .texOffs(78, 0).mirror().addBox(-10.0F, -1.0F, -10.5F, 2.0F, 2.0F, 21.0F).mirror(false)
                // 甲缘金色左镶边雕饰 (Left Golden Marginal Edge Trim: 1x1x17)
                .texOffs(70, 47).addBox(9.5F, -1.5F, -8.5F, 1.0F, 1.0F, 17.0F)
                // 甲缘金色右镶边雕饰 (Right Golden Marginal Edge Trim - Mirrored)
                .texOffs(70, 47).mirror().addBox(-10.5F, -1.5F, -8.5F, 1.0F, 1.0F, 17.0F).mirror(false)
                // 前缘出颈凹口镶边 (Anterior Rim Flange with Neck Notch: 12x2x2)
                .texOffs(0, 82).addBox(-6.0F, -1.0F, -11.5F, 12.0F, 2.0F, 2.0F)
                // 后缘出尾凹口镶边 (Posterior Rim Flange with Tail Notch: 10x2x2)
                .texOffs(29, 82).addBox(-5.0F, -1.0F, 9.5F, 10.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, 17.0F, 0.0F));

        // =========================================================================
        // 2. 鸟首长颈体系 (Crane-like Avian Neck & Golden Raptor Head)
        // =========================================================================
        // 下段长颈 (Lower Neck: 前倾伸展)
        PartDefinition neckLower = body.addOrReplaceChild("neck_lower", CubeListBuilder.create()
                // 下段长颈主柱 (Lower Neck Column: 4x6x5)
                .texOffs(108, 47).addBox(-2.0F, -6.0F, -2.5F, 4.0F, 6.0F, 5.0F)
                // 颈根丰盈羽领环 (Throat Plumage Collar Ring: 5x3x6)
                .texOffs(78, 72).addBox(-2.5F, -3.0F, -3.0F, 5.0F, 3.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -1.0F, -10.0F, -0.30F, 0.0F, 0.0F));

        // 上段长颈 (Upper Neck: 回折 S 弯仰起)
        PartDefinition neckUpper = neckLower.addOrReplaceChild("neck_upper", CubeListBuilder.create()
                // 上段修长曲颈 (Upper Neck Column: 3x6x4)
                .texOffs(101, 72).addBox(-1.5F, -6.0F, -2.0F, 3.0F, 6.0F, 4.0F)
                // 颈枕背羽棱 (Dorsal Nape Plumage Ridge: 2x5x2)
                .texOffs(116, 72).addBox(-1.0F, -5.5F, 1.2F, 2.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.40F, 0.0F, 0.0F));

        // 灵鸟头颅与五官 (Bird Head Skull, Sharp Raptor Beak, Jade Crest, Chin Wattle)
        PartDefinition head = neckUpper.addOrReplaceChild("head", CubeListBuilder.create()
                // 灵禽头颅骨架 (Bird Cranium Skull: 5x4x6)
                .texOffs(0, 87).addBox(-2.5F, -2.5F, -4.5F, 5.0F, 4.0F, 6.0F)
                // 眉骨与面颊羽 (Brow Ridges & Cheeks: 6x2x4)
                .texOffs(23, 87).addBox(-3.0F, -1.5F, -3.5F, 6.0F, 2.0F, 4.0F)
                // 猛禽金喙基底与鼻孔蜡膜 (Raptor Beak Base with Nostrils / Cere: 3x3x3)
                .texOffs(44, 87).addBox(-1.5F, -0.5F, -7.5F, 3.0F, 3.0F, 3.0F)
                // 锋锐钩状金色猛禽喙尖 (Sharp Hooked Raptor Beak Tip: 2x3x3)
                .texOffs(57, 87).addBox(-1.0F, 0.5F, -10.5F, 2.0F, 3.0F, 3.0F)
                // 喙下颌骨 (Beak Lower Mandible / Jaw: 2x1x4)
                .texOffs(68, 87).addBox(-1.0F, 1.5F, -8.5F, 2.0F, 1.0F, 4.0F)
                // 顶上碧玉仙羽主冠 (Crown Jade Crest Main Plume: 2x3x5)
                .texOffs(81, 83).addBox(-1.0F, -5.5F, -3.5F, 2.0F, 3.0F, 5.0F)
                // 仙冠延展羽翎 (Crown Extended Crest Feathers: 1x2x5)
                .texOffs(96, 83).addBox(-0.5F, -6.5F, 0.0F, 1.0F, 2.0F, 5.0F)
                // 左侧仙羽耳翎 (Left Lateral Crest Plume: 1x2x3)
                .texOffs(109, 83).addBox(1.8F, -4.5F, -2.0F, 1.0F, 2.0F, 3.0F)
                // 右侧仙羽耳翎 (Right Lateral Crest Plume - Mirrored)
                .texOffs(109, 83).mirror().addBox(-2.8F, -4.5F, -2.0F, 1.0F, 2.0F, 3.0F).mirror(false)
                // 颔下仙髯肉垂 (Chin Beard Wattle / Dewlap: 2x3x2)
                .texOffs(118, 83).addBox(-1.0F, 1.5F, -2.5F, 2.0F, 3.0F, 2.0F)
                // 仙髯须尖 (Spiritual Chin Beard Tip: 1x3x1)
                .texOffs(124, 0).addBox(-0.5F, 4.0F, -2.0F, 1.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, -0.10F, 0.0F, 0.0F));

        // =========================================================================
        // 3. 灵蛇盘尾四节关节体系 (4-Segment Articulate Serpent Tail)
        // =========================================================================
        // 关节1 (Tail Base)
        PartDefinition tailBase = body.addOrReplaceChild("tail_base", CubeListBuilder.create()
                // 粗壮尾基躯干 (Tail Base Trunk: 4x4x5)
                .texOffs(0, 98).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 5.0F)
                // 尾基灵蛇背棘 (Tail Base Dorsal Spine: 1x2x3)
                .texOffs(19, 98).addBox(-0.5F, -3.5F, 1.0F, 1.0F, 2.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 10.0F, -0.12F, 0.0F, 0.0F));

        // 关节2 (Tail Mid 1)
        PartDefinition tailMid1 = tailBase.addOrReplaceChild("tail_mid1", CubeListBuilder.create()
                // 尾中部第一节 (Tail Mid1 Segment: 3x3x5)
                .texOffs(28, 98).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 5.0F)
                // 尾中部第一节背棘 (Tail Mid1 Dorsal Spine: 1x2x3)
                .texOffs(45, 98).addBox(-0.5F, -3.0F, 1.0F, 1.0F, 2.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 4.8F, 0.05F, 0.0F, 0.0F));

        // 关节3 (Tail Mid 2)
        PartDefinition tailMid2 = tailMid1.addOrReplaceChild("tail_mid2", CubeListBuilder.create()
                // 尾中部第二节 (Tail Mid2 Segment: 3x2x5)
                .texOffs(54, 98).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 5.0F)
                // 尾中部第二节背棘 (Tail Mid2 Dorsal Spine: 1x1x3)
                .texOffs(71, 98).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 4.8F, 0.08F, 0.0F, 0.0F));

        // 关节4 (Tail Tip)
        PartDefinition tailTip = tailMid2.addOrReplaceChild("tail_tip", CubeListBuilder.create()
                // 细长灵蛇尾尖 (Serpent Tail Tip Segment: 2x2x5)
                .texOffs(80, 92).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F)
                // 尾梢金毒刺与响尾针 (Golden Stinger Barb Tip: 1x2x3)
                .texOffs(95, 92).addBox(-0.5F, -2.5F, 2.0F, 1.0F, 2.0F, 3.0F)
                // 尾尖左游水侧鳍 (Left Barb Fin: 1x1x2)
                .texOffs(104, 92).addBox(0.8F, -0.5F, 2.5F, 1.0F, 1.0F, 2.0F)
                // 尾尖右游水侧鳍 (Right Barb Fin - Mirrored)
                .texOffs(104, 92).mirror().addBox(-1.8F, -0.5F, 2.5F, 1.0F, 1.0F, 2.0F).mirror(false),
                PartPose.offsetAndRotation(0.0F, 0.0F, 4.8F, 0.12F, 0.0F, 0.0F));

        // =========================================================================
        // 4. 踏浪四灵蹼 (4 Powerful Swimming Flippers & Webbed Beast Legs)
        // =========================================================================
        // --- 左前肢 (Front Left Leg: Upper Shoulder + Forearm + Broad Paddle & Claws) ---
        PartDefinition legFL = body.addOrReplaceChild("leg_fl", CubeListBuilder.create()
                // 强健前肩胛上臂 (Muscular Upper Shoulder: 4x4x5)
                .texOffs(0, 108).addBox(-2.0F, -1.5F, -2.5F, 4.0F, 4.0F, 5.0F)
                // 肩甲碧玉护片 (Shoulder Jade Armor Plate: 2x3x4)
                .texOffs(19, 108).addBox(1.5F, -2.0F, -2.0F, 2.0F, 3.0F, 4.0F),
                PartPose.offset(8.5F, 0.5F, -6.5F));

        legFL.addOrReplaceChild("forearm_fl", CubeListBuilder.create()
                // 前臂小腿骨骼 (Forearm Shank: 3x3x4)
                .texOffs(32, 108).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F)
                // 宽阔踏浪蹼面 (Broad Swimming Paddle Blade: 5x2x7)
                .texOffs(47, 108).addBox(-2.0F, 2.5F, -4.5F, 5.0F, 2.0F, 7.0F)
                // 锋利金色踏浪爪 (Sharp Golden Webbed Claws: 3x1x2)
                .texOffs(72, 108).addBox(-1.5F, 3.0F, -6.5F, 3.0F, 1.0F, 2.0F),
                PartPose.offset(1.0F, 2.5F, 0.0F));

        // --- 右前肢 (Front Right Leg - Mirrored) ---
        PartDefinition legFR = body.addOrReplaceChild("leg_fr", CubeListBuilder.create()
                .texOffs(0, 108).mirror().addBox(-2.0F, -1.5F, -2.5F, 4.0F, 4.0F, 5.0F).mirror(false)
                .texOffs(19, 108).mirror().addBox(-3.5F, -2.0F, -2.0F, 2.0F, 3.0F, 4.0F).mirror(false),
                PartPose.offset(-8.5F, 0.5F, -6.5F));

        legFR.addOrReplaceChild("forearm_fr", CubeListBuilder.create()
                .texOffs(32, 108).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F).mirror(false)
                .texOffs(47, 108).mirror().addBox(-3.0F, 2.5F, -4.5F, 5.0F, 2.0F, 7.0F).mirror(false)
                .texOffs(72, 108).mirror().addBox(-1.5F, 3.0F, -6.5F, 3.0F, 1.0F, 2.0F).mirror(false),
                PartPose.offset(-1.0F, 2.5F, 0.0F));

        // --- 左后肢 (Hind Left Leg: Upper Thigh + Shank + Broad Paddle & Claws) ---
        PartDefinition legBL = body.addOrReplaceChild("leg_bl", CubeListBuilder.create()
                // 强健后臀大腿 (Muscular Hind Thigh: 4x4x5)
                .texOffs(0, 118).addBox(-2.0F, -1.5F, -2.5F, 4.0F, 4.0F, 5.0F)
                // 臀甲碧玉护片 (Hip Jade Armor Plate: 2x3x4)
                .texOffs(19, 118).addBox(1.5F, -2.0F, -2.0F, 2.0F, 3.0F, 4.0F),
                PartPose.offset(8.0F, 0.5F, 6.5F));

        legBL.addOrReplaceChild("shank_bl", CubeListBuilder.create()
                // 后肢小腿骨骼 (Hind Shank Joint: 3x3x4)
                .texOffs(32, 118).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F)
                // 宽阔后肢操舵蹼面 (Rear Steering Paddle Blade: 5x2x6)
                .texOffs(47, 118).addBox(-2.0F, 2.5F, -3.5F, 5.0F, 2.0F, 6.0F)
                // 后肢锋利金爪 (Rear Golden Webbed Claws: 3x1x2)
                .texOffs(70, 118).addBox(-1.5F, 3.0F, -5.5F, 3.0F, 1.0F, 2.0F),
                PartPose.offset(1.0F, 2.5F, 0.0F));

        // --- 右后肢 (Hind Right Leg - Mirrored) ---
        PartDefinition legBR = body.addOrReplaceChild("leg_br", CubeListBuilder.create()
                .texOffs(0, 118).mirror().addBox(-2.0F, -1.5F, -2.5F, 4.0F, 4.0F, 5.0F).mirror(false)
                .texOffs(19, 118).mirror().addBox(-3.5F, -2.0F, -2.0F, 2.0F, 3.0F, 4.0F).mirror(false),
                PartPose.offset(-8.0F, 0.5F, 6.5F));

        legBR.addOrReplaceChild("shank_br", CubeListBuilder.create()
                .texOffs(32, 118).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F).mirror(false)
                .texOffs(47, 118).mirror().addBox(-3.0F, 2.5F, -3.5F, 5.0F, 2.0F, 6.0F).mirror(false)
                .texOffs(70, 118).mirror().addBox(-1.5F, 3.0F, -5.5F, 3.0F, 1.0F, 2.0F).mirror(false),
                PartPose.offset(-1.0F, 2.5F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // =========================================================================
        // 1. 沉稳威严的重型水陆两栖步态与躯干律动 (Heavy Amphibious Waddling & Swimming Gait)
        // =========================================================================
        float strideRate = 0.60F;
        float stridePos = walkPos * strideRate;

        // 躯干垂直沉浮 (Heavy Locomotion Bobbing)
        float bodyBob = Mth.cos(stridePos * 2.0F) * 0.45F * walkSpeed;
        this.body.y = 17.0F + bodyBob;

        // 步态侧倾摇摆 (Lateral Waddling Roll & Yaw Sway)
        float bodyRoll = Mth.sin(stridePos) * 0.06F * walkSpeed;
        float bodyYawSway = Mth.cos(stridePos) * 0.04F * walkSpeed;
        this.body.zRot = bodyRoll;
        this.body.yRot = bodyYawSway;
        this.body.xRot = Mth.sin(stridePos * 2.0F) * 0.03F * walkSpeed;

        // =========================================================================
        // 2. 灵禽鹤首长颈: 呼吸起伏、视线注视追踪、好奇探寻啄水 (Avian Neck & Raptor Head)
        // =========================================================================
        // 头部注视追踪 (Look Tracking xRot / yRot)
        float lookPitch = state.xRot * ((float) Math.PI / 180F);
        float lookYaw = state.yRot * ((float) Math.PI / 180F);

        // S 弯长颈逐节分配视线偏航 (Yaw distributed down the flexible neck column)
        this.neckLower.yRot = lookYaw * 0.25F;
        this.neckUpper.yRot = lookYaw * 0.35F;
        this.head.yRot = lookYaw * 0.40F;

        // 基础姿态角 + 俯仰追踪 (Pitch distribution)
        this.neckLower.xRot = -0.30F + lookPitch * 0.30F;
        this.neckUpper.xRot = 0.40F + lookPitch * 0.35F;
        this.head.xRot = -0.10F + lookPitch * 0.35F;

        // 灵鸟呼吸起伏点头 (Breathing Nod: gentle harmonic S-curve expansion)
        float breathCycle = age * 0.05F;
        float breathLower = Mth.sin(breathCycle) * 0.035F;
        float breathUpper = Mth.sin(breathCycle + 0.5F) * 0.045F;
        float breathHead = Mth.cos(breathCycle) * 0.03F;
        this.neckLower.xRot += breathLower;
        this.neckUpper.xRot -= breathUpper;
        this.head.xRot += breathHead;

        // 灵秀探寻与涉水啄水觅食微动作 (Subtle Pecking / Searching Gesture)
        float searchTimer = age * 0.035F;
        float searchInquiry = (float) Math.pow(Math.max(0.0F, Mth.sin(searchTimer)), 6.0) * 0.22F;
        float searchTwitch = Mth.sin(searchTimer * 1.5F) * 0.04F;
        this.head.xRot += searchInquiry;
        this.head.zRot = searchTwitch + lookYaw * 0.15F;
        this.neckUpper.zRot = -searchTwitch * 0.6F;

        // 行走时长颈前后伸缩点头补偿 (Optokinetic Head Nod during locomotion)
        float walkHeadBob = Mth.cos(stridePos) * 0.10F * walkSpeed;
        this.neckLower.xRot += walkHeadBob;
        this.neckUpper.xRot -= walkHeadBob * 0.70F;
        this.head.xRot += walkHeadBob * 0.45F;

        // =========================================================================
        // 3. 灵蛇盘尾四节关节蛇形波浪横向摆动 (Sinuous Multi-Segment Serpent Tail Wave)
        // =========================================================================
        float tailWaveFreq = age * 0.085F + stridePos * 0.75F;
        float tailBaseAmp = 0.16F + walkSpeed * 0.28F;

        // 关节1 (tail_base): 粗壮尾基，小幅摆动
        this.tailBase.yRot = Mth.sin(tailWaveFreq) * tailBaseAmp;
        this.tailBase.xRot = -0.12F + Mth.cos(tailWaveFreq * 0.5F) * 0.04F;

        // 关节2 (tail_mid1): 相位滞后 0.65 rad，振幅逐节放大
        this.tailMid1.yRot = Mth.sin(tailWaveFreq - 0.65F) * (tailBaseAmp * 1.25F);
        this.tailMid1.xRot = 0.05F + Mth.sin(tailWaveFreq * 0.5F - 0.4F) * 0.05F;

        // 关节3 (tail_mid2): 相位滞后 1.30 rad
        this.tailMid2.yRot = Mth.sin(tailWaveFreq - 1.30F) * (tailBaseAmp * 1.50F);
        this.tailMid2.xRot = 0.08F + Mth.cos(tailWaveFreq * 0.5F - 0.8F) * 0.06F;

        // 关节4 (tail_tip): 尾尖蛇信金刺，大幅度蜿蜒与微翘 (蛇尾卷翘灵动)
        this.tailTip.yRot = Mth.sin(tailWaveFreq - 1.95F) * (tailBaseAmp * 1.85F);
        this.tailTip.xRot = 0.12F + Mth.sin(tailWaveFreq - 1.95F) * 0.12F;
        this.tailTip.zRot = Mth.cos(tailWaveFreq - 1.95F) * 0.18F;

        // =========================================================================
        // 4. 踏浪四灵蹼与关节划动 (Powerful Swimming Flippers & Webbed Beast Legs)
        // =========================================================================
        float swingAmp = 0.52F * walkSpeed;
        float paddleFlexAmp = 0.38F * walkSpeed;

        // 静息浮水微动踩水 (Water Treading while floating/idle)
        float idleTread = Mth.sin(age * 0.06F) * 0.05F;

        // --- 左前肢 (Front Left Flipper) ---
        float flPhase = stridePos;
        this.legFL.xRot = Mth.cos(flPhase) * swingAmp;
        this.legFL.yRot = -Mth.sin(flPhase) * 0.15F * walkSpeed;
        this.legFL.zRot = 0.12F + idleTread + Mth.sin(flPhase) * 0.18F * walkSpeed;
        this.forearmFL.xRot = Math.max(0.0F, -Mth.cos(flPhase)) * paddleFlexAmp;
        this.forearmFL.zRot = Mth.sin(flPhase) * 0.14F * walkSpeed;

        // --- 右前肢 (Front Right Flipper: Phase shifted by PI) ---
        float frPhase = stridePos + (float) Math.PI;
        this.legFR.xRot = Mth.cos(frPhase) * swingAmp;
        this.legFR.yRot = -Mth.sin(frPhase) * 0.15F * walkSpeed;
        this.legFR.zRot = -0.12F - idleTread - Mth.sin(frPhase) * 0.18F * walkSpeed;
        this.forearmFR.xRot = Math.max(0.0F, -Mth.cos(frPhase)) * paddleFlexAmp;
        this.forearmFR.zRot = -Mth.sin(frPhase) * 0.14F * walkSpeed;

        // --- 左后肢 (Hind Left Flipper: Phase shifted by PI) ---
        float blPhase = stridePos + (float) Math.PI;
        this.legBL.xRot = Mth.cos(blPhase) * (swingAmp * 0.85F);
        this.legBL.yRot = Mth.sin(blPhase) * 0.12F * walkSpeed;
        this.legBL.zRot = 0.15F + idleTread + Mth.sin(blPhase) * 0.15F * walkSpeed;
        this.shankBL.xRot = Math.max(0.0F, -Mth.cos(blPhase)) * (paddleFlexAmp * 0.85F);

        // --- 右后肢 (Hind Right Flipper: In phase with Front Left) ---
        float brPhase = stridePos;
        this.legBR.xRot = Mth.cos(brPhase) * (swingAmp * 0.85F);
        this.legBR.yRot = Mth.sin(brPhase) * 0.12F * walkSpeed;
        this.legBR.zRot = -0.15F - idleTread - Mth.sin(brPhase) * 0.15F * walkSpeed;
        this.shankBR.xRot = Math.max(0.0F, -Mth.cos(brPhase)) * (paddleFlexAmp * 0.85F);
    }
}
