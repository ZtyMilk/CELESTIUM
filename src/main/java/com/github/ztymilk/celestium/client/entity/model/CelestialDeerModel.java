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
 * 九色神鹿 (Celestial Nine-Colored Deer)
 * 敦煌瑞兽九色鹿·神兽构思师树状解剖体系 (Anatomical Tree Architecture):
 * 1. 灵秀鹿首与九彩神角 (Slender Graceful Deer Head & Nine-Colored Crystal Antlers)
 *    - 秀美长吻、灵动鹿首、额前通灵宝印
 *    - 九彩晶角：主干角梁、前向眉枝、侧向外展枝、后生王冠枝与顶端九彩神光宝珠
 *    - 灵动长耳（斜向外扬，灵敏微动）
 * 2. S弯仙颈与灵斑仙躯 (S-Curved Sacred Neck & Dappled Spirit Torso)
 *    - 双段优美 S 弯仙颈、悬垂如意宝玉佩与璎珞宝珠项链
 *    - 流线型神鹿仙躯、背脊金线与两侧浮雕仙斑灵云
 * 3. 轻盈仙蹄 (Slender Segmented Legs)
 *    - 上段大腿肌肉、修长管骨胫骨、金色蹄箍与精巧白玉仙蹄
 * 4. 仙光与如意尾 (Celestial Aura & Ruyi Tail)
 *    - 短翘如意灵鹿仙尾
 *    - 背后悬浮如意光晕/背光环 (八方如意仙轮，缓缓旋转悬浮)
 */
public class CelestialDeerModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("celestial_deer"), "main");

    // 躯体与附属部件
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart halo;

    // S弯仙颈与鹿首体系
    private final ModelPart neck;
    private final ModelPart neckUpper;
    private final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;

    // 前后四肢（三段关节解剖体系：大腿 + 胫骨管骨 + 仙蹄）
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontShin;
    private final ModelPart leftFrontHoof;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontShin;
    private final ModelPart rightFrontHoof;

    private final ModelPart leftHindLeg;
    private final ModelPart leftHindShin;
    private final ModelPart leftHindHoof;
    private final ModelPart rightHindLeg;
    private final ModelPart rightHindShin;
    private final ModelPart rightHindHoof;

    public CelestialDeerModel(ModelPart root) {
        super(root);

        // 躯干层级
        this.body = root.getChild("body");
        this.tail = this.body.getChild("tail");
        this.halo = this.body.getChild("halo");

        // 仙颈与鹿首层级
        this.neck = this.body.getChild("neck");
        this.neckUpper = this.neck.getChild("neck_upper");
        this.head = this.neckUpper.getChild("head");
        this.leftEar = this.head.getChild("left_ear");
        this.rightEar = this.head.getChild("right_ear");
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");

        // 轻盈四肢层级
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.leftFrontShin = this.leftFrontLeg.getChild("left_front_shin");
        this.leftFrontHoof = this.leftFrontShin.getChild("left_front_hoof");

        this.rightFrontLeg = root.getChild("right_front_leg");
        this.rightFrontShin = this.rightFrontLeg.getChild("right_front_shin");
        this.rightFrontHoof = this.rightFrontShin.getChild("right_front_hoof");

        this.leftHindLeg = root.getChild("left_hind_leg");
        this.leftHindShin = this.leftHindLeg.getChild("left_hind_shin");
        this.leftHindHoof = this.leftHindShin.getChild("left_hind_hoof");

        this.rightHindLeg = root.getChild("right_hind_leg");
        this.rightHindShin = this.rightHindLeg.getChild("right_hind_shin");
        this.rightHindHoof = this.rightHindShin.getChild("right_hind_hoof");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // ==========================================
        // 1. 灵斑仙躯 (Slender Graceful Torso)
        // ==========================================
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 前胸与前躯干 (Chest & Anterior Torso)
                .texOffs(0, 0).addBox(-3.5F, -4.0F, -7.5F, 7.0F, 8.0F, 8.0F)
                // 后腹与腰臀 (Abdomen & Posterior Torso)
                .texOffs(32, 0).addBox(-3.0F, -3.5F, 0.5F, 6.0F, 7.0F, 8.0F)
                // 背脊仙金鬃脊 (Dorsal Spine Ridge)
                .texOffs(0, 17).addBox(-0.5F, -4.5F, -6.5F, 1.0F, 1.0F, 13.0F)
                // 左侧浮雕仙斑灵云 (Left Flank Raised Spirit Spots)
                .texOffs(30, 16).addBox(3.1F, -2.5F, -4.0F, 0.5F, 5.0F, 11.0F)
                // 右侧浮雕仙斑灵云 (Right Flank Raised Spirit Spots)
                .texOffs(0, 32).addBox(-3.6F, -2.5F, -4.0F, 0.5F, 5.0F, 11.0F),
                PartPose.offset(0.0F, 9.5F, 0.0F));

        // 如意尾 (Short Perky Ruyi Tail)
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                // 鹿尾根基 (Tail Base)
                .texOffs(26, 33).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F)
                // 如意尾羽灵簇 (Ruyi Tail Plume)
                .texOffs(40, 33).addBox(-1.5F, -1.0F, 3.5F, 3.0F, 3.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, -2.5F, 8.5F, 0.45F, 0.0F, 0.0F));

        // 悬浮如意光晕/背光环 (Floating Celestial Halo / Wheel of Light)
        body.addOrReplaceChild("halo", CubeListBuilder.create()
                // 祥云光心摩尼宝珠 (Halo Central Mani Jewel)
                .texOffs(62, 60).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F)
                // 上光弧 (Top Halo Arch)
                .texOffs(72, 60).addBox(-4.0F, -9.0F, -0.5F, 8.0F, 1.5F, 1.0F)
                // 下光弧 (Bottom Halo Arch)
                .texOffs(72, 64).addBox(-4.0F, 7.5F, -0.5F, 8.0F, 1.5F, 1.0F)
                // 左光弧 (Left Halo Arch)
                .texOffs(92, 60).addBox(7.5F, -4.0F, -0.5F, 1.5F, 8.0F, 1.0F)
                // 右光弧 (Right Halo Arch)
                .texOffs(100, 60).addBox(-9.0F, -4.0F, -0.5F, 1.5F, 8.0F, 1.0F)
                // 左上如意云瓣 (Top-Left Diagonal Petal)
                .texOffs(62, 70).addBox(3.5F, -7.0F, -0.5F, 4.0F, 3.5F, 1.0F)
                // 右上如意云瓣 (Top-Right Diagonal Petal)
                .texOffs(74, 70).addBox(-7.5F, -7.0F, -0.5F, 4.0F, 3.5F, 1.0F)
                // 左下如意云瓣 (Bottom-Left Diagonal Petal)
                .texOffs(86, 70).addBox(3.5F, 3.5F, -0.5F, 4.0F, 3.5F, 1.0F)
                // 右下如意云瓣 (Bottom-Right Diagonal Petal)
                .texOffs(98, 70).addBox(-7.5F, 3.5F, -0.5F, 4.0F, 3.5F, 1.0F)
                // 四方飞天芒刃 (Four Radiant Aura Flames)
                .texOffs(62, 77).addBox(-1.0F, -12.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(70, 77).addBox(-1.0F, 9.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(78, 77).addBox(9.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F)
                .texOffs(88, 77).addBox(-12.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, -10.0F, 1.0F));

        // ==========================================
        // 2. S弯仙颈 (Curved S-Neck with Necklace & Pendant)
        // ==========================================
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                // 下段仙颈 (Lower Graceful Neck)
                .texOffs(0, 50).addBox(-2.0F, -6.5F, -2.5F, 4.0F, 7.0F, 5.0F)
                // 颈部璎珞宝珠项链 (Celestial Bead Necklace Collar)
                .texOffs(20, 50).addBox(-2.5F, -1.0F, -3.0F, 5.0F, 2.0F, 6.0F)
                // 悬垂通灵宝玉佩 (Hanging Jade Pendant)
                .texOffs(44, 50).addBox(-1.5F, 1.0F, -3.5F, 3.0F, 4.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -2.0F, -6.0F, -0.45F, 0.0F, 0.0F));

        PartDefinition neckUpper = neck.addOrReplaceChild("neck_upper", CubeListBuilder.create()
                // 上段仙颈 (Upper Slender Neck)
                .texOffs(0, 64).addBox(-1.5F, -5.5F, -2.0F, 3.0F, 6.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.25F, 0.0F, 0.0F));

        // ==========================================
        // 3. 灵秀鹿首与九彩神角 (Head, Antlers, Ears)
        // ==========================================
        PartDefinition head = neckUpper.addOrReplaceChild("head", CubeListBuilder.create()
                // 灵秀鹿颅 (Deer Cranium)
                .texOffs(16, 64).addBox(-2.5F, -3.5F, -4.0F, 5.0F, 5.0F, 5.0F)
                // 秀美长吻 (Delicate Muzzle)
                .texOffs(38, 64).addBox(-1.5F, -1.0F, -8.0F, 3.0F, 3.0F, 4.5F)
                // 灵鹿鼻镜 (Nose Pad)
                .texOffs(56, 64).addBox(-1.0F, -0.8F, -8.6F, 2.0F, 1.5F, 1.0F)
                // 下颌咽喉 (Lower Jaw)
                .texOffs(0, 76).addBox(-1.2F, 1.2F, -7.0F, 2.4F, 1.0F, 4.0F)
                // 额前通灵宝印 (Forehead Celestial Spirit Jewel)
                .texOffs(14, 76).addBox(-1.0F, -3.8F, -4.4F, 2.0F, 2.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.20F, 0.0F, 0.0F));

        // 左仙耳 (Left Alert Ear - Angled outward)
        head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                .texOffs(22, 76).addBox(-0.5F, -5.5F, -1.0F, 1.5F, 6.0F, 2.5F)
                .texOffs(34, 76).addBox(0.0F, -7.0F, -0.5F, 1.0F, 2.0F, 1.5F),
                PartPose.offsetAndRotation(2.0F, -3.0F, -1.0F, -0.20F, 0.35F, 0.65F));

        // 右仙耳 (Right Alert Ear - Angled outward)
        head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(42, 76).addBox(-1.0F, -5.5F, -1.0F, 1.5F, 6.0F, 2.5F, true)
                .texOffs(54, 76).addBox(-1.0F, -7.0F, -0.5F, 1.0F, 2.0F, 1.5F, true),
                PartPose.offsetAndRotation(-2.0F, -3.0F, -1.0F, -0.20F, -0.35F, -0.65F));

        // 左九彩神角 (Left Nine-Colored Sacred Antler: Main beam + multiple prongs + tip orbs)
        head.addOrReplaceChild("left_antler", CubeListBuilder.create()
                // 角基金环 (Root Collar)
                .texOffs(62, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F)
                // 主干角梁下段 (Main Beam Lower)
                .texOffs(62, 5).addBox(-0.8F, -7.0F, -0.8F, 1.6F, 6.5F, 1.6F)
                // 主干角梁上段 (Main Beam Upper)
                .texOffs(62, 15).addBox(-0.6F, -14.0F, 0.5F, 1.4F, 7.5F, 1.4F)
                // 前向眉枝主杆 (Brow Tine Beam)
                .texOffs(72, 0).addBox(-0.5F, -5.5F, -4.5F, 1.2F, 1.2F, 4.0F)
                // 前向眉枝竖钩 (Brow Tine Hook)
                .texOffs(72, 6).addBox(-0.5F, -7.5F, -4.5F, 1.2F, 2.2F, 1.2F)
                // 眉枝宝珠 (Brow Tine Orb)
                .texOffs(72, 10).addBox(-1.0F, -9.0F, -5.0F, 2.0F, 2.0F, 2.0F)
                // 中段侧向外展枝 (Mid Outward Prong)
                .texOffs(82, 0).addBox(0.5F, -9.0F, 0.0F, 4.0F, 1.2F, 1.2F)
                // 侧向分叉挑钩 (Mid Prong Hook)
                .texOffs(82, 4).addBox(3.5F, -12.0F, 0.0F, 1.2F, 3.5F, 1.2F)
                // 侧枝灵珠 (Mid Prong Orb)
                .texOffs(82, 10).addBox(3.0F, -13.5F, -0.5F, 2.0F, 2.0F, 2.0F)
                // 后分叉王冠枝 (Rear Crown Branch)
                .texOffs(72, 16).addBox(-0.5F, -11.0F, 1.5F, 1.2F, 1.2F, 3.5F)
                // 后分叉竖挺 (Rear Prong Tip)
                .texOffs(84, 16).addBox(-0.5F, -13.5F, 3.5F, 1.2F, 2.8F, 1.2F)
                // 后枝灵珠 (Rear Prong Orb)
                .texOffs(72, 22).addBox(-1.0F, -15.0F, 3.0F, 2.0F, 2.0F, 2.0F)
                // 主干角冠顶端神光宝珠 (Main Crown Orb)
                .texOffs(82, 22).addBox(-1.0F, -16.0F, 0.2F, 2.2F, 2.2F, 2.2F),
                PartPose.offsetAndRotation(1.5F, -3.5F, -1.5F, -0.15F, 0.0F, 0.18F));

        // 右九彩神角 (Right Nine-Colored Sacred Antler: Main beam + multiple prongs + tip orbs)
        head.addOrReplaceChild("right_antler", CubeListBuilder.create()
                .texOffs(94, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, true)
                .texOffs(94, 5).addBox(-0.8F, -7.0F, -0.8F, 1.6F, 6.5F, 1.6F, true)
                .texOffs(94, 15).addBox(-0.8F, -14.0F, 0.5F, 1.4F, 7.5F, 1.4F, true)
                .texOffs(104, 0).addBox(-0.7F, -5.5F, -4.5F, 1.2F, 1.2F, 4.0F, true)
                .texOffs(104, 6).addBox(-0.7F, -7.5F, -4.5F, 1.2F, 2.2F, 1.2F, true)
                .texOffs(104, 10).addBox(-1.0F, -9.0F, -5.0F, 2.0F, 2.0F, 2.0F, true)
                .texOffs(114, 0).addBox(-4.5F, -9.0F, 0.0F, 4.0F, 1.2F, 1.2F, true)
                .texOffs(114, 4).addBox(-4.7F, -12.0F, 0.0F, 1.2F, 3.5F, 1.2F, true)
                .texOffs(114, 10).addBox(-5.0F, -13.5F, -0.5F, 2.0F, 2.0F, 2.0F, true)
                .texOffs(104, 16).addBox(-0.7F, -11.0F, 1.5F, 1.2F, 1.2F, 3.5F, true)
                .texOffs(116, 16).addBox(-0.7F, -13.5F, 3.5F, 1.2F, 2.8F, 1.2F, true)
                .texOffs(104, 22).addBox(-1.0F, -15.0F, 3.0F, 2.0F, 2.0F, 2.0F, true)
                .texOffs(114, 22).addBox(-1.2F, -16.0F, 0.2F, 2.2F, 2.2F, 2.2F, true),
                PartPose.offsetAndRotation(-1.5F, -3.5F, -1.5F, -0.15F, 0.0F, -0.18F));

        // ==========================================
        // 4. 轻盈四肢 (Slender Segmented Legs)
        // ==========================================
        // 前左腿 (Front Left Leg)
        PartDefinition leftFrontLeg = root.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                // 前大腿 (Upper Arm / Shoulder)
                .texOffs(62, 30).addBox(-1.2F, 0.0F, -1.2F, 2.4F, 6.0F, 2.4F),
                PartPose.offset(2.8F, 11.0F, -4.5F));
        PartDefinition leftFrontShin = leftFrontLeg.addOrReplaceChild("left_front_shin", CubeListBuilder.create()
                // 修长胫骨管骨 (Slender Canon Bone)
                .texOffs(62, 39).addBox(-0.8F, 0.0F, -0.8F, 1.6F, 5.5F, 1.6F),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        leftFrontShin.addOrReplaceChild("left_front_hoof", CubeListBuilder.create()
                // 金丝蹄箍 (Golden Fetlock Ring)
                .texOffs(62, 48).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.8F, 2.0F)
                // 仙鹿金蹄 (Delicate Cloven Hoof)
                .texOffs(62, 52).addBox(-0.9F, 0.7F, -1.4F, 1.8F, 0.8F, 2.2F),
                PartPose.offset(0.0F, 5.5F, 0.0F));

        // 前右腿 (Front Right Leg)
        PartDefinition rightFrontLeg = root.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(72, 30).addBox(-1.2F, 0.0F, -1.2F, 2.4F, 6.0F, 2.4F, true),
                PartPose.offset(-2.8F, 11.0F, -4.5F));
        PartDefinition rightFrontShin = rightFrontLeg.addOrReplaceChild("right_front_shin", CubeListBuilder.create()
                .texOffs(72, 39).addBox(-0.8F, 0.0F, -0.8F, 1.6F, 5.5F, 1.6F, true),
                PartPose.offset(0.0F, 6.0F, 0.0F));
        rightFrontShin.addOrReplaceChild("right_front_hoof", CubeListBuilder.create()
                .texOffs(72, 48).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.8F, 2.0F, true)
                .texOffs(72, 52).addBox(-0.9F, 0.7F, -1.4F, 1.8F, 0.8F, 2.2F, true),
                PartPose.offset(0.0F, 5.5F, 0.0F));

        // 后左腿 (Hind Left Leg)
        PartDefinition leftHindLeg = root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                // 后大腿/臀肌 (Muscular Haunch)
                .texOffs(82, 30).addBox(-1.3F, 0.0F, -1.5F, 2.6F, 6.0F, 3.2F),
                PartPose.offset(2.8F, 11.0F, 5.0F));
        PartDefinition leftHindShin = leftHindLeg.addOrReplaceChild("left_hind_shin", CubeListBuilder.create()
                // 飞节与下胫骨 (Hock & Canon Bone)
                .texOffs(82, 41).addBox(-0.8F, 0.0F, -1.2F, 1.6F, 5.5F, 1.6F),
                PartPose.offset(0.0F, 6.0F, 0.5F));
        leftHindShin.addOrReplaceChild("left_hind_hoof", CubeListBuilder.create()
                // 后足金箍 (Golden Fetlock Ring)
                .texOffs(82, 50).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.8F, 2.0F)
                // 后足金蹄 (Hind Cloven Hoof)
                .texOffs(82, 54).addBox(-0.9F, 0.7F, -1.3F, 1.8F, 0.8F, 2.2F),
                PartPose.offset(0.0F, 5.5F, -0.4F));

        // 后右腿 (Hind Right Leg)
        PartDefinition rightHindLeg = root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(96, 30).addBox(-1.3F, 0.0F, -1.5F, 2.6F, 6.0F, 3.2F, true),
                PartPose.offset(-2.8F, 11.0F, 5.0F));
        PartDefinition rightHindShin = rightHindLeg.addOrReplaceChild("right_hind_shin", CubeListBuilder.create()
                .texOffs(96, 41).addBox(-0.8F, 0.0F, -1.2F, 1.6F, 5.5F, 1.6F, true),
                PartPose.offset(0.0F, 6.0F, 0.5F));
        rightHindShin.addOrReplaceChild("right_hind_hoof", CubeListBuilder.create()
                .texOffs(96, 50).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.8F, 2.0F, true)
                .texOffs(96, 54).addBox(-0.9F, 0.7F, -1.3F, 1.8F, 0.8F, 2.2F, true),
                PartPose.offset(0.0F, 5.5F, -0.4F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        float headYaw = state.yRot * ((float) Math.PI / 180F);
        float headPitch = state.xRot * ((float) Math.PI / 180F);

        // ==========================================
        // 1. 轻盈灵动步态 (Graceful Springy Deer Gait)
        // ==========================================
        // 躯体纵向弹性微弹 (Springy vertical oscillation during trot)
        this.body.y = 9.5F + Mth.sin(walkPos * 1.3324F) * 0.8F * walkSpeed;
        this.body.xRot = Mth.sin(walkPos * 1.3324F) * 0.04F * walkSpeed;
        this.body.zRot = Mth.cos(walkPos * 0.6662F) * 0.03F * walkSpeed;

        // 四肢对角踏云漫步（大腿摆动 + 胫骨自然屈伸 + 仙蹄回弹）
        float flSwing = Mth.cos(walkPos * 0.6662F);
        float frSwing = Mth.cos(walkPos * 0.6662F + (float) Math.PI);

        // 左前肢
        this.leftFrontLeg.xRot = flSwing * 0.65F * walkSpeed;
        this.leftFrontShin.xRot = Math.max(0.0F, flSwing) * 0.5F * walkSpeed;
        this.leftFrontHoof.xRot = -this.leftFrontShin.xRot * 0.6F;

        // 右前肢
        this.rightFrontLeg.xRot = frSwing * 0.65F * walkSpeed;
        this.rightFrontShin.xRot = Math.max(0.0F, frSwing) * 0.5F * walkSpeed;
        this.rightFrontHoof.xRot = -this.rightFrontShin.xRot * 0.6F;

        // 左后肢 (对角配对: 与右前肢同步)
        this.leftHindLeg.xRot = frSwing * 0.65F * walkSpeed;
        this.leftHindShin.xRot = Math.max(0.0F, -frSwing) * 0.5F * walkSpeed;
        this.leftHindHoof.xRot = -this.leftHindShin.xRot * 0.6F;

        // 右后肢 (对角配对: 与左前肢同步)
        this.rightHindLeg.xRot = flSwing * 0.65F * walkSpeed;
        this.rightHindShin.xRot = Math.max(0.0F, -flSwing) * 0.5F * walkSpeed;
        this.rightHindHoof.xRot = -this.rightHindShin.xRot * 0.6F;

        // ==========================================
        // 2. 仙颈与仙首点头律动 (Neck & Head Gentle Nodding)
        // ==========================================
        // 呼吸微点头 + 行走节律性点头
        float idleNod = Mth.sin(age * 0.06F) * 0.05F;
        float walkNod = Mth.cos(walkPos * 0.6662F) * 0.12F * walkSpeed;

        // 下颈项跟随转动与俯仰
        this.neck.xRot = -0.45F + headPitch * 0.30F + idleNod * 0.5F + walkNod * 0.4F;
        this.neck.yRot = headYaw * 0.35F;

        // 上颈项柔和延伸
        this.neckUpper.xRot = 0.25F + idleNod * 0.3F + walkNod * 0.3F;
        this.neckUpper.yRot = headYaw * 0.25F;

        // 鹿首精准环视与灵动微点
        this.head.xRot = 0.20F + headPitch * 0.55F + idleNod * 0.6F + walkNod * 0.5F;
        this.head.yRot = headYaw * 0.40F;

        // ==========================================
        // 3. 灵耳机警微颤 (Ears Subtle Twitching)
        // ==========================================
        // 周期性快速抖耳脉冲（模拟灵兽倾听天籁仙音）
        float earFlick = (float) Math.pow(Math.max(0.0, Math.sin(age * 0.08F)), 6.0) * Mth.sin(age * 0.8F) * 0.18F;
        this.leftEar.zRot = 0.65F + Mth.sin(age * 0.07F) * 0.03F + earFlick;
        this.leftEar.xRot = -0.20F + Mth.cos(age * 0.06F) * 0.03F;

        this.rightEar.zRot = -0.65F - Mth.sin(age * 0.07F + 1.0F) * 0.03F - earFlick;
        this.rightEar.xRot = -0.20F + Mth.cos(age * 0.06F + 1.0F) * 0.03F;

        // 九彩神角随头颅微振
        this.leftAntler.zRot = 0.18F + Mth.sin(age * 0.05F) * 0.015F;
        this.rightAntler.zRot = -0.18F - Mth.sin(age * 0.05F) * 0.015F;

        // ==========================================
        // 4. 如意尾灵动轻拂 (Tail Gentle Flick)
        // ==========================================
        this.tail.zRot = Mth.sin(age * 0.1F) * 0.12F + Mth.cos(walkPos * 0.6662F) * 0.15F * walkSpeed;
        this.tail.xRot = 0.45F + Mth.cos(age * 0.08F) * 0.05F;

        // ==========================================
        // 5. 悬浮如意光晕旋转与漂浮 (Halo Rotating & Hovering)
        // ==========================================
        // 缓曼周天回转
        this.halo.zRot = age * 0.02F;
        // 仙气上下悬浮浮动
        this.halo.y = -10.0F + Mth.sin(age * 0.05F) * 0.75F;
        // 虚空微倾空灵律动
        this.halo.xRot = Mth.sin(age * 0.03F) * 0.05F;
        this.halo.yRot = Mth.cos(age * 0.025F) * 0.05F;
    }
}
