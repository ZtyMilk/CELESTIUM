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
 * 纯阳大日金乌 (Three-Legged Golden Crow / 纯阳曜灵神鸟)
 * 《山海经·大荒东经》：“汤谷上有扶木，一日方至，一日方出，皆载于乌。”
 * 《淮南子·精神训》：“日中有踆乌。”
 *
 * “神兽构思师”树状解剖体系架构：
 * 1. 金乌曜首 (Head, Raptor Beak & 9-Rayed Solar Corona Crest):
 *    - 凶悍神禽曜首主颅与鹰隼式金煞眉脊 (Fierce raptor cranium & predatory brow ridges)
 *    - 玄金利喙弯钩吻端与下颌颔兜 (Hooked black-gold beak & lower mandible)
 *    - 曜火金冠 (9-rayed solar corona crest: 极天主冠羽、双内冲天羽、双中烈火羽、双外张扬翎、双枕流金翎)
 *    - 左右曜火颊翎与咽喉金乌羽领 (Cheek flame plumes & throat ruff)
 * 2. 大日金身与大日神轮 (Radiant Torso & Floating Great Sun Mandala):
 *    - 纯阳流金胸腔主躯干、隆起龙骨突与丰盈曜羽 (Muscular avian core & pectoral keel)
 *    - 背脊曜阳金披风与腹底烈阳绒羽 (Dorsal mantle saddle & ventral fluff)
 *    - 大日悬浮金轮 (Floating Great Sun Mandala: 曜核金丹、八荒十字枢纽、四正曜芒、四象天轨外环与四隅流火金炎)
 * 3. 垂天金翼 (Massive Tiered Solar Wings):
 *    - 左/右内翼肩翮、主骨架、覆羽板与次级飞羽群 (Inner wings & secondary plumage)
 *    - 左/右外翼初级飞羽大展翎、曜火羽尖羽刃与翼尖垂天流火翎 (Articulated outer primary blades & flame tips)
 * 4. 严谨考究三足鼎立 (Three Divine Legs: Iconic Balanced Tripod):
 *    - 左神足与右神足：丰盈上肢股部、玄金神胫、后距金刺与四向抓地利爪
 *    - 中央后方中柱神足 (Central Rear Celestial Pillar Leg): 粗壮鼎立柱骨、纯阳双金距与沉稳四趾神爪
 * 5. 三缕流金尾羽 (Three Long Solar Flame Streamers):
 *    - 尾羽基座与尾上流金覆羽 (Tail base & coverts)
 *    - 中脉大日正翎 (Central 3-tiered magnificent solar streamer)
 *    - 左/右开散翼展流火尾翎 (Left & right spreading flame streamers)
 */
public class GoldenCrowModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("golden_crow"), "main");

    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart sunWheel;
    private final ModelPart wingL;
    private final ModelPart wingLOuter;
    private final ModelPart wingR;
    private final ModelPart wingROuter;
    private final ModelPart legL;
    private final ModelPart shankL;
    private final ModelPart talonL;
    private final ModelPart legR;
    private final ModelPart shankR;
    private final ModelPart talonR;
    private final ModelPart legM;
    private final ModelPart shankM;
    private final ModelPart talonM;
    private final ModelPart tail;
    private final ModelPart tailCenter;
    private final ModelPart tailL;
    private final ModelPart tailR;

    public GoldenCrowModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.sunWheel = this.body.getChild("sun_wheel");
        this.wingL = this.body.getChild("wing_l");
        this.wingLOuter = this.wingL.getChild("wing_l_outer");
        this.wingR = this.body.getChild("wing_r");
        this.wingROuter = this.wingR.getChild("wing_r_outer");
        this.tail = this.body.getChild("tail");
        this.tailCenter = this.tail.getChild("tail_center");
        this.tailL = this.tail.getChild("tail_l");
        this.tailR = this.tail.getChild("tail_r");
        this.legL = root.getChild("leg_l");
        this.shankL = this.legL.getChild("shank_l");
        this.talonL = this.shankL.getChild("talon_l");
        this.legR = root.getChild("leg_r");
        this.shankR = this.legR.getChild("shank_r");
        this.talonR = this.shankR.getChild("talon_r");
        this.legM = root.getChild("leg_m");
        this.shankM = this.legM.getChild("shank_m");
        this.talonM = this.shankM.getChild("talon_m");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // =========================================================================
        // 1. 大日金身主躯干 (Radiant Solar Golden Torso)
        // =========================================================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 金身胸腔腹背主躯干 (Torso core: 8 x 8 x 13)
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -6.5F, 8.0F, 8.0F, 13.0F)
                // 金乌曜胸与龙骨突羽 (Breast plumage & pectoral keel: 7 x 7 x 3)
                .texOffs(42, 0).addBox(-3.5F, -3.0F, -9.0F, 7.0F, 7.0F, 3.0F)
                // 背脊曜阳金披风与甲羽 (Dorsal mantle / saddle: 6 x 2 x 9)
                .texOffs(0, 22).addBox(-3.0F, -5.0F, -4.5F, 6.0F, 2.0F, 9.0F)
                // 腹底烈阳绒羽 (Ventral flame fluff: 6 x 2 x 8)
                .texOffs(31, 22).addBox(-3.0F, 3.5F, -4.0F, 6.0F, 2.0F, 8.0F)
                // 左侧金乌胁羽 (Left flank sideplate: 1 x 5 x 8)
                .texOffs(0, 34).addBox(3.5F, -2.5F, -4.5F, 1.0F, 5.0F, 8.0F)
                // 右侧金乌胁羽 (Right flank sideplate: 1 x 5 x 8)
                .texOffs(19, 34).addBox(-4.5F, -2.5F, -4.5F, 1.0F, 5.0F, 8.0F),
                PartPose.offset(0.0F, 13.0F, 0.0F));

        // =========================================================================
        // 2. 金乌曜首与九曜金冠 (Head, Hooked Raptor Beak & 9-Rayed Solar Corona Crest)
        // =========================================================================
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                // 神颈主柱 (Neck column: 5 x 6 x 5)
                .texOffs(64, 0).addBox(-2.5F, -6.0F, -2.5F, 5.0F, 6.0F, 5.0F)
                // 曜火羽领 (Throat feather ruff: 6 x 4 x 3)
                .texOffs(85, 0).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 4.0F, 3.0F)
                // 颈后流金披羽 (Nape feather crest: 4 x 5 x 2)
                .texOffs(104, 0).addBox(-2.0F, -5.0F, 1.5F, 4.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -2.0F, -5.5F, -0.25F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                // 金乌曜首主脑颅 (Main Cranium: 6 x 5 x 6)
                .texOffs(64, 12).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 5.0F, 6.0F)
                // 鹰隼式金煞眉脊 (Raptor brow ridges: 1 x 1 x 4)
                .texOffs(89, 12).addBox(2.6F, -2.5F, -3.5F, 1.0F, 1.0F, 4.0F)
                .texOffs(100, 12).addBox(-3.6F, -2.5F, -3.5F, 1.0F, 1.0F, 4.0F)
                // 左右曜火颊翎 (Cheek flame feather tufts: 1 x 3 x 4)
                .texOffs(89, 18).addBox(2.8F, -0.5F, -1.0F, 1.0F, 3.0F, 4.0F)
                .texOffs(100, 18).addBox(-3.8F, -0.5F, -1.0F, 1.0F, 3.0F, 4.0F)
                // 玄金上喙基部 (Hooked beak base: 3 x 3 x 4)
                .texOffs(64, 25).addBox(-1.5F, -1.0F, -8.0F, 3.0F, 3.0F, 4.0F)
                // 利喙弯钩前锋 (Raptor hooked beak apex: 2 x 2 x 2)
                .texOffs(79, 25).addBox(-1.0F, 1.0F, -8.8F, 2.0F, 2.0F, 2.0F)
                // 玄金下喙与颔兜 (Lower mandible: 2 x 1 x 4)
                .texOffs(88, 25).addBox(-1.0F, 1.0F, -7.5F, 2.0F, 1.0F, 4.0F)
                // 颔下金髯火羽 (Chin flame tuft: 2 x 2 x 2)
                .texOffs(101, 25).addBox(-1.0F, 1.8F, -3.5F, 2.0F, 2.0F, 2.0F)
                // ---------------------------------------------------------------------
                // 曜火金冠 - 九曜金冠 (9-Rayed Solar Corona Crest: 9 distinct coronal solar rays)
                // ---------------------------------------------------------------------
                // 冠羽第1曜：极天冲霄主冠羽 (Ray 1 - Central supreme spire: 1 x 7 x 3)
                .texOffs(64, 33).addBox(-0.5F, -10.0F, -1.0F, 1.0F, 7.0F, 3.0F)
                // 冠羽第2/3曜：左右内层冲天曜羽 (Rays 2 & 3 - Inner coronal pair: 1 x 6 x 2)
                .texOffs(73, 33).addBox(0.8F, -9.0F, 0.0F, 1.0F, 6.0F, 2.0F)
                .texOffs(80, 33).addBox(-1.8F, -9.0F, 0.0F, 1.0F, 6.0F, 2.0F)
                // 冠羽第4/5曜：左右中层烈火副冠 (Rays 4 & 5 - Mid coronal pair: 1 x 5 x 2)
                .texOffs(87, 33).addBox(1.8F, -8.0F, 0.8F, 1.0F, 5.0F, 2.0F)
                .texOffs(94, 33).addBox(-2.8F, -8.0F, 0.8F, 1.0F, 5.0F, 2.0F)
                // 冠羽第6/7曜：左右外层张扬翼翎 (Rays 6 & 7 - Outer coronal pair: 1 x 4 x 2)
                .texOffs(101, 33).addBox(2.6F, -6.5F, 1.6F, 1.0F, 4.0F, 2.0F)
                .texOffs(108, 33).addBox(-3.6F, -6.5F, 1.6F, 1.0F, 4.0F, 2.0F)
                // 冠羽第8/9曜：枕后贴颈流金翎 (Rays 8 & 9 - Nape streamer pair: 1 x 3 x 3)
                .texOffs(115, 33).addBox(0.8F, -4.5F, 2.5F, 1.0F, 3.0F, 3.0F)
                .texOffs(115, 40).addBox(-1.8F, -4.5F, 2.5F, 1.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.25F, 0.0F, 0.0F));

        // =========================================================================
        // 3. 大日悬浮神轮 (Great Sun Mandala / Halo of Solar Fire)
        // =========================================================================
        body.addOrReplaceChild("sun_wheel", CubeListBuilder.create()
                // 大日金丹·纯阳曜核 (Sun core: 4 x 4 x 1)
                .texOffs(64, 84).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 1.0F)
                // 八荒金芒符文十字枢纽 (Sun hub cross: 6 x 2 x 1 & 2 x 6 x 1)
                .texOffs(75, 84).addBox(-3.0F, -1.0F, -0.55F, 6.0F, 2.0F, 1.0F)
                .texOffs(90, 84).addBox(-1.0F, -3.0F, -0.55F, 2.0F, 6.0F, 1.0F)
                // 四正曜芒 (Four cardinal solar rays)
                .texOffs(97, 84).addBox(-1.0F, -9.0F, -0.5F, 2.0F, 4.0F, 1.0F)   // Top ray
                .texOffs(104, 84).addBox(-1.0F, 5.0F, -0.5F, 2.0F, 4.0F, 1.0F)   // Bottom ray
                .texOffs(111, 84).addBox(5.0F, -1.0F, -0.5F, 4.0F, 2.0F, 1.0F)   // Left ray
                .texOffs(111, 88).addBox(-9.0F, -1.0F, -0.5F, 4.0F, 2.0F, 1.0F)  // Right ray
                // 神轮外环四象天轨 (Four quadrant rim segments)
                .texOffs(64, 90).addBox(-5.0F, -6.0F, -0.5F, 10.0F, 1.0F, 1.0F)  // Top rim
                .texOffs(64, 93).addBox(-5.0F, 5.0F, -0.5F, 10.0F, 1.0F, 1.0F)   // Bottom rim
                .texOffs(64, 96).addBox(5.0F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F)   // Left rim
                .texOffs(69, 96).addBox(-6.0F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F)  // Right rim
                // 四隅流火金炎 (Four diagonal corner flares)
                .texOffs(75, 96).addBox(4.0F, -7.0F, -0.45F, 2.0F, 2.0F, 1.0F)   // Top-Left flare
                .texOffs(82, 96).addBox(-6.0F, -7.0F, -0.45F, 2.0F, 2.0F, 1.0F)  // Top-Right flare
                .texOffs(75, 100).addBox(4.0F, 5.0F, -0.45F, 2.0F, 2.0F, 1.0F)   // Bottom-Left flare
                .texOffs(82, 100).addBox(-6.0F, 5.0F, -0.45F, 2.0F, 2.0F, 1.0F), // Bottom-Right flare
                PartPose.offset(0.0F, -8.0F, 5.0F));

        // =========================================================================
        // 4. 垂天金翼多节大展羽翼 (Tiered Solar Flight Wings)
        // =========================================================================
        // 左内翼 (Left Inner Wing)
        PartDefinition wingL = body.addOrReplaceChild("wing_l", CubeListBuilder.create()
                // 肱骨内翼大主骨 (Inner wing bone: 8 x 3 x 4)
                .texOffs(0, 48).addBox(0.0F, -1.5F, -2.0F, 8.0F, 3.0F, 4.0F)
                // 内翼上覆羽板 (Secondary coverts: 8 x 1 x 6)
                .texOffs(25, 48).addBox(0.0F, -1.0F, 2.0F, 8.0F, 1.0F, 6.0F)
                // 次级飞羽群 (Secondary flight feathers: 7 x 1 x 8)
                .texOffs(0, 56).addBox(0.5F, -0.5F, 5.0F, 7.0F, 1.0F, 8.0F)
                // 内翼下缘流火羽褶 (Flame feather serrated edge: 6 x 1 x 4)
                .texOffs(31, 56).addBox(1.0F, -0.3F, 11.0F, 6.0F, 1.0F, 4.0F),
                PartPose.offsetAndRotation(4.0F, -2.5F, -3.5F, 0.05F, -0.10F, 0.15F));

        // 左外翼飞羽尖端 (Left Outer Wing Primaries)
        wingL.addOrReplaceChild("wing_l_outer", CubeListBuilder.create()
                // 外翼主指骨 (Outer wing pinion: 9 x 2 x 3)
                .texOffs(0, 66).addBox(0.0F, -1.0F, -1.5F, 9.0F, 2.0F, 3.0F)
                // 初级飞羽大展翎 (Primary flight feather blade: 9 x 1 x 8)
                .texOffs(25, 66).addBox(0.0F, -0.6F, 1.5F, 9.0F, 1.0F, 8.0F)
                // 曜火外缘羽尖 (Flame feather tips: 6 x 1 x 5)
                .texOffs(0, 72).addBox(4.0F, -0.4F, 7.0F, 6.0F, 1.0F, 5.0F)
                // 翼尖垂天流火翎 (Wingtip solar streamer: 5 x 1 x 6)
                .texOffs(23, 76).addBox(8.0F, -0.2F, 3.0F, 5.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 0.0F, 0.05F, 0.10F));

        // 右内翼 (Right Inner Wing)
        PartDefinition wingR = body.addOrReplaceChild("wing_r", CubeListBuilder.create()
                // 肱骨内翼大主骨 (Inner wing bone: 8 x 3 x 4)
                .texOffs(64, 48).addBox(-8.0F, -1.5F, -2.0F, 8.0F, 3.0F, 4.0F)
                // 内翼上覆羽板 (Secondary coverts: 8 x 1 x 6)
                .texOffs(89, 48).addBox(-8.0F, -1.0F, 2.0F, 8.0F, 1.0F, 6.0F)
                // 次级飞羽群 (Secondary flight feathers: 7 x 1 x 8)
                .texOffs(64, 56).addBox(-7.5F, -0.5F, 5.0F, 7.0F, 1.0F, 8.0F)
                // 内翼下缘流火羽褶 (Flame feather serrated edge: 6 x 1 x 4)
                .texOffs(95, 56).addBox(-7.0F, -0.3F, 11.0F, 6.0F, 1.0F, 4.0F),
                PartPose.offsetAndRotation(-4.0F, -2.5F, -3.5F, 0.05F, 0.10F, -0.15F));

        // 右外翼飞羽尖端 (Right Outer Wing Primaries)
        wingR.addOrReplaceChild("wing_r_outer", CubeListBuilder.create()
                // 外翼主指骨 (Outer wing pinion: 9 x 2 x 3)
                .texOffs(64, 66).addBox(-9.0F, -1.0F, -1.5F, 9.0F, 2.0F, 3.0F)
                // 初级飞羽大展翎 (Primary flight feather blade: 9 x 1 x 8)
                .texOffs(89, 66).addBox(-9.0F, -0.6F, 1.5F, 9.0F, 1.0F, 8.0F)
                // 曜火外缘羽尖 (Flame feather tips: 6 x 1 x 5)
                .texOffs(64, 72).addBox(-10.0F, -0.4F, 7.0F, 6.0F, 1.0F, 5.0F)
                // 翼尖垂天流火翎 (Wingtip solar streamer: 5 x 1 x 6)
                .texOffs(87, 76).addBox(-13.0F, -0.2F, 3.0F, 5.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, -0.05F, -0.10F));

        // =========================================================================
        // 5. 严谨考究三足鼎立 (Three Divine Legs: Balanced Tripod Architecture)
        // =========================================================================
        // 左神足 (Left Leg)
        PartDefinition legL = root.addOrReplaceChild("leg_l", CubeListBuilder.create()
                // 左股丰盈大腿 (Thigh: 3 x 4 x 3)
                .texOffs(0, 84).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F),
                PartPose.offset(-3.0F, 14.5F, 0.5F));

        PartDefinition shankL = legL.addOrReplaceChild("shank_l", CubeListBuilder.create()
                // 玄金神胫 (Shank: 2 x 4 x 2)
                .texOffs(13, 84).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F)
                // 纯阳后距金刺 (Spur: 1 x 1 x 1)
                .texOffs(22, 84).addBox(-0.5F, 2.5F, 1.0F, 1.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        shankL.addOrReplaceChild("talon_l", CubeListBuilder.create()
                // 掌垫爪踵 (Foot pad: 3 x 1 x 3)
                .texOffs(0, 92).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 3.0F)
                // 前中神趾 (Front middle toe: 1 x 1 x 3)
                .texOffs(13, 91).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 3.0F)
                // 前左斜趾 (Front left toe: 1 x 1 x 2)
                .texOffs(22, 88).addBox(1.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F)
                // 前右斜趾 (Front right toe: 1 x 1 x 2)
                .texOffs(22, 92).addBox(-2.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F)
                // 后距抓扣 (Rear hallux toe: 1 x 1 x 2)
                .texOffs(29, 88).addBox(-0.5F, 0.0F, 1.0F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        // 右神足 (Right Leg)
        PartDefinition legR = root.addOrReplaceChild("leg_r", CubeListBuilder.create()
                // 右股丰盈大腿 (Thigh: 3 x 4 x 3)
                .texOffs(36, 84).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F),
                PartPose.offset(3.0F, 14.5F, 0.5F));

        PartDefinition shankR = legR.addOrReplaceChild("shank_r", CubeListBuilder.create()
                // 玄金神胫 (Shank: 2 x 4 x 2)
                .texOffs(49, 84).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F)
                // 纯阳后距金刺 (Spur: 1 x 1 x 1)
                .texOffs(58, 84).addBox(-0.5F, 2.5F, 1.0F, 1.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        shankR.addOrReplaceChild("talon_r", CubeListBuilder.create()
                // 掌垫爪踵 (Foot pad: 3 x 1 x 3)
                .texOffs(36, 92).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 3.0F)
                // 前中神趾 (Front middle toe: 1 x 1 x 3)
                .texOffs(49, 91).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 3.0F)
                // 前左斜趾 (Front left toe: 1 x 1 x 2)
                .texOffs(58, 88).addBox(1.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F)
                // 前右斜趾 (Front right toe: 1 x 1 x 2)
                .texOffs(58, 92).addBox(-2.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F)
                // 后距抓扣 (Rear hallux toe: 1 x 1 x 2)
                .texOffs(29, 92).addBox(-0.5F, 0.0F, 1.0F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        // 中央后方中柱神足 (Central Rear Celestial Pillar Leg - 中柱神足，平衡雄健)
        PartDefinition legM = root.addOrReplaceChild("leg_m", CubeListBuilder.create()
                // 雄健中柱股肌 (Pillar thigh: 4 x 4 x 4)
                .texOffs(0, 97).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(0.0F, 14.5F, 3.5F));

        PartDefinition shankM = legM.addOrReplaceChild("shank_m", CubeListBuilder.create()
                // 玄金粗壮中柱神胫 (Pillar shank: 3 x 4 x 3)
                .texOffs(17, 97).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F)
                // 纯阳双金距 (Twin spurs: 2 x 2 x 1)
                .texOffs(30, 97).addBox(-1.0F, 2.0F, 1.5F, 2.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        shankM.addOrReplaceChild("talon_m", CubeListBuilder.create()
                // 广展稳重鼎足底座 (Broad pillar foot pad: 4 x 1 x 4)
                .texOffs(37, 97).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F)
                // 鼎立前锋重趾 (Front heavy claw: 2 x 1 x 3)
                .texOffs(54, 97).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 3.0F)
                // 左支撑趾 (Left bracing toe: 1 x 1 x 2)
                .texOffs(30, 101).addBox(1.5F, 0.0F, -3.5F, 1.0F, 1.0F, 2.0F)
                // 右支撑趾 (Right bracing toe: 1 x 1 x 2)
                .texOffs(37, 103).addBox(-2.5F, 0.0F, -3.5F, 1.0F, 1.0F, 2.0F)
                // 镇地后距岩趾 (Rear anchor toe: 2 x 1 x 2)
                .texOffs(44, 103).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 1.0F, 2.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        // =========================================================================
        // 6. 三缕流金尾羽 (Three Long Solar Flame Streamers)
        // =========================================================================
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                // 尾羽基座 (Tail base: 5 x 3 x 4)
                .texOffs(90, 96).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 4.0F)
                // 尾上流金覆羽 (Dorsal tail coverts: 4 x 1 x 3)
                .texOffs(109, 96).addBox(-2.0F, -2.2F, 0.5F, 4.0F, 1.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -1.8F, 6.0F, 0.20F, 0.0F, 0.0F));

        // 中脉大日正翎 (Central Long Solar Streamer: 3-tiered magnificent length)
        tail.addOrReplaceChild("tail_center", CubeListBuilder.create()
                // 中翎基段 (Center streamer base: 4 x 1 x 9)
                .texOffs(0, 107).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 9.0F)
                // 中翎中段延展 (Center streamer mid: 5 x 1 x 9)
                .texOffs(29, 107).addBox(-2.5F, -0.3F, 9.0F, 5.0F, 1.0F, 9.0F)
                // 末端极天流金焰羽刃 (Center streamer flame blade: 6 x 1 x 8)
                .texOffs(60, 107).addBox(-3.0F, -0.1F, 18.0F, 6.0F, 1.0F, 8.0F),
                PartPose.offset(0.0F, 0.0F, 3.5F));

        // 左翼流金火翎 (Left Solar Flame Streamer)
        tail.addOrReplaceChild("tail_l", CubeListBuilder.create()
                // 左翎主段 (Left streamer base: 3 x 1 x 9)
                .texOffs(89, 107).addBox(-1.0F, -0.4F, 0.0F, 3.0F, 1.0F, 9.0F)
                // 左翎末端流火羽尖 (Left streamer flame tip: 4 x 1 x 8)
                .texOffs(0, 118).addBox(-1.5F, -0.2F, 9.0F, 4.0F, 1.0F, 8.0F),
                PartPose.offsetAndRotation(2.0F, -0.2F, 3.0F, 0.05F, 0.18F, 0.08F));

        // 右翼流金火翎 (Right Solar Flame Streamer)
        tail.addOrReplaceChild("tail_r", CubeListBuilder.create()
                // 右翎主段 (Right streamer base: 3 x 1 x 9)
                .texOffs(25, 117).addBox(-2.0F, -0.4F, 0.0F, 3.0F, 1.0F, 9.0F)
                // 右翎末端流火羽尖 (Right streamer flame tip: 4 x 1 x 8)
                .texOffs(52, 118).addBox(-2.5F, -0.2F, 9.0F, 4.0F, 1.0F, 8.0F),
                PartPose.offsetAndRotation(-2.0F, -0.2F, 3.0F, 0.05F, -0.18F, -0.08F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        float walkPos = state.walkAnimationPos;

        // =========================================================================
        // 1. 金乌曜首神禽注视与呼吸 (Predatory Gaze & Avian Head Tracking)
        // =========================================================================
        float headPitch = state.xRot * ((float) Math.PI / 180F) * 0.70F;
        float headYaw = state.yRot * ((float) Math.PI / 180F) * 0.70F;
        float idleBreath = Mth.sin(age * 0.06F);

        this.neck.xRot = -0.25F + headPitch * 0.35F + idleBreath * 0.02F;
        this.neck.yRot = headYaw * 0.35F;
        this.head.xRot = 0.25F + headPitch * 0.65F;
        this.head.yRot = headYaw * 0.65F;
        this.head.zRot = Mth.sin(age * 0.05F) * 0.04F;

        // =========================================================================
        // 2. 大日悬浮神轮悠然自转 (Sun Wheel Gently Revolving & Floating Halo)
        // =========================================================================
        // 神轮绕 Z 轴徐徐恒转，演化太阳周天流转之律
        this.sunWheel.zRot = age * 0.030F;
        // 烈阳神轮灵虚微浮
        this.sunWheel.y = -8.0F + Mth.sin(age * 0.07F) * 0.70F;
        // 随法力潮汐产生极细微的进动与俯仰
        this.sunWheel.xRot = 0.06F + Mth.cos(age * 0.04F) * 0.04F;
        this.sunWheel.yRot = Mth.sin(age * 0.035F) * 0.04F;

        // =========================================================================
        // 3. 垂天金翼多节强劲振翅律动 (Powerful Wing Beat Flight Cycle)
        // =========================================================================
        float flapTime = age * 0.35F;
        float flap = Mth.sin(flapTime);
        float flapCos = Mth.cos(flapTime);

        if (walkSpeed > 0.05F) {
            // 地面步态或低空踏风时：双翼展开协助维持平衡
            float groundFlap = flap * (0.20F + walkSpeed * 0.30F);
            this.wingL.xRot = 0.08F - walkSpeed * 0.10F;
            this.wingL.yRot = -0.10F + walkSpeed * 0.20F;
            this.wingL.zRot = 0.15F + walkSpeed * 0.25F + groundFlap;
            this.wingLOuter.zRot = groundFlap * 0.50F;

            this.wingR.xRot = 0.08F - walkSpeed * 0.10F;
            this.wingR.yRot = 0.10F - walkSpeed * 0.20F;
            this.wingR.zRot = -0.15F - walkSpeed * 0.25F - groundFlap;
            this.wingROuter.zRot = -groundFlap * 0.50F;
        } else {
            // 凌虚高飞翱翔模式 (Majestic Full Flight Cycle: Deep downstroke & phase-delayed outer wings)
            float flightAmp = 0.55F;
            // 内翼主骨架振幅
            this.wingL.xRot = 0.08F + flapCos * 0.12F;
            this.wingL.yRot = -0.10F - flapCos * 0.15F;
            this.wingL.zRot = 0.20F + flap * flightAmp;

            this.wingR.xRot = 0.08F + flapCos * 0.12F;
            this.wingR.yRot = 0.10F + flapCos * 0.15F;
            this.wingR.zRot = -0.20F - flap * flightAmp;

            // 外翼初级飞羽相位滞后（0.5 rad 滞后），形成波浪式抽打气流的雄浑动力
            float outerFlap = Mth.sin(flapTime - 0.50F) * (flightAmp * 1.25F);
            this.wingLOuter.xRot = flapCos * 0.15F;
            this.wingLOuter.zRot = outerFlap;

            this.wingROuter.xRot = flapCos * 0.15F;
            this.wingROuter.zRot = -outerFlap;
        }

        // 躯干飞翔浮沉与气动俯仰
        this.body.y = 13.0F - flap * 0.80F;
        this.body.xRot = 0.06F + flapCos * 0.04F;

        // =========================================================================
        // 4. 上古三足鼎立踏步与凌空收爪 (Three Legs: Tripod Gait vs Graceful Flight Retraction)
        // =========================================================================
        if (walkSpeed > 0.05F) {
            // 地面三足鼎立步态：左足、右足、后中柱神足形成 120 度(2π/3)等距三相轮替步序
            float strideFreq = walkPos * 0.85F;
            float legSwingAmp = 0.80F * walkSpeed;

            // 左足相位 (Phase 0)
            float leftPhase = strideFreq;
            this.legL.xRot = Mth.cos(leftPhase) * legSwingAmp;
            this.shankL.xRot = Math.max(0.0F, Mth.sin(leftPhase)) * 0.65F * walkSpeed;
            this.talonL.xRot = -this.legL.xRot * 0.5F;

            // 右足相位 (Phase 2π/3 ≈ 2.0944F)
            float rightPhase = strideFreq + 2.0944F;
            this.legR.xRot = Mth.cos(rightPhase) * legSwingAmp;
            this.shankR.xRot = Math.max(0.0F, Mth.sin(rightPhase)) * 0.65F * walkSpeed;
            this.talonR.xRot = -this.legR.xRot * 0.5F;

            // 中柱神足相位 (Phase 4π/3 ≈ 4.1888F) - 后方鼎力支撑足
            float midPhase = strideFreq + 4.1888F;
            this.legM.xRot = Mth.cos(midPhase) * legSwingAmp;
            this.shankM.xRot = Math.max(0.0F, Mth.sin(midPhase)) * 0.65F * walkSpeed;
            this.talonM.xRot = -this.legM.xRot * 0.5F;
        } else {
            // 飞行/腾空优雅收爪 (Graceful Flight Leg Retraction: folded neatly under torso)
            this.legL.xRot = 0.65F;
            this.shankL.xRot = 0.50F;
            this.talonL.xRot = 0.20F;

            this.legR.xRot = 0.65F;
            this.shankR.xRot = 0.50F;
            this.talonR.xRot = 0.20F;

            // 中柱神足紧贴腹底中央脊线收拢
            this.legM.xRot = 0.78F;
            this.shankM.xRot = 0.55F;
            this.talonM.xRot = 0.25F;
        }

        // =========================================================================
        // 5. 三缕流金尾羽随风如火浪翻卷 (Flaming Tail Waving Like Solar Flares)
        // =========================================================================
        float tailTime = age * 0.12F;
        this.tail.xRot = 0.18F + Mth.sin(tailTime) * 0.05F;

        // 中脉大日正翎：宏伟起伏
        this.tailCenter.xRot = Mth.sin(tailTime) * 0.14F;
        this.tailCenter.yRot = Mth.cos(tailTime * 0.70F) * 0.08F;

        // 左翼流金火翎：向左外展伴随流火翻卷
        this.tailL.xRot = 0.08F + Mth.sin(tailTime + 0.60F) * 0.16F;
        this.tailL.yRot = 0.18F + Mth.cos(tailTime * 0.80F) * 0.07F;
        this.tailL.zRot = 0.08F + Mth.sin(tailTime * 0.90F) * 0.06F;

        // 右翼流金火翎：向右外展对称翻卷
        this.tailR.xRot = 0.08F + Mth.sin(tailTime + 1.20F) * 0.16F;
        this.tailR.yRot = -0.18F - Mth.cos(tailTime * 0.80F) * 0.07F;
        this.tailR.zRot = -0.08F - Mth.sin(tailTime * 0.90F) * 0.06F;
    }
}
