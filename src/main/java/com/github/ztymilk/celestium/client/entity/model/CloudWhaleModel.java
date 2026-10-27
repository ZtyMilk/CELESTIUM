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
 * 流云巨鲲 (Colossal Cloud Whale / Kun - 百块级重构)
 * 北冥有鱼，其名为鲲。其翼若垂天之云，背负青天朝沧溟。
 * 包含：
 * 1. 宏伟流线鲸首、呼吸气孔、侧鳃与下颌咽喉滤板
 * 2. 宽厚星宿鲸躯与背脊仙阁须弥法座（玩家御气飞天鞍座）
 * 3. 巨幅多节羽化垂天之翼（内翼骨、翼前缘、初级飞羽与流云飘带）
 * 4. 四段无缝相接柔性大尾与无隙如意大尾鳍（彻底消除尾巴断开Bug）
 */
public class CloudWhaleModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("cloud_whale"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftFin;
    private final ModelPart leftFinTip;
    private final ModelPart rightFin;
    private final ModelPart rightFinTip;
    private final ModelPart tailBase;
    private final ModelPart tailMid1;
    private final ModelPart tailMid2;
    private final ModelPart tailFluke;

    public CloudWhaleModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.leftFin = this.body.getChild("left_fin");
        this.leftFinTip = this.leftFin.getChild("left_fin_tip");
        this.rightFin = this.body.getChild("right_fin");
        this.rightFinTip = this.rightFin.getChild("right_fin_tip");
        this.tailBase = this.body.getChild("tail_base");
        this.tailMid1 = this.tailBase.getChild("tail_mid1");
        this.tailMid2 = this.tailMid1.getChild("tail_mid2");
        this.tailFluke = this.tailMid2.getChild("tail_fluke");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 巍峨主躯干与须弥仙阁法座 (Central Torso & Sumeru Pavilion Throne)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 宽厚星海鲸躯 (Torso core)
                .texOffs(0, 0).addBox(-13.0F, -10.0F, -16.0F, 26.0F, 20.0F, 32.0F)
                // 左右侧肋弧面 (Lateral flank curves)
                .texOffs(116, 0).addBox(12.5F, -7.0F, -14.0F, 2.0F, 14.0F, 28.0F)
                .texOffs(116, 0).mirror().addBox(-14.5F, -7.0F, -14.0F, 2.0F, 14.0F, 28.0F)
                // 腹部月魄喉褶护板 (Ventral throat pleats)
                .texOffs(0, 54).addBox(-11.5F, 10.0F, -14.0F, 23.0F, 3.0F, 28.0F)
                // 腹底龙骨中脊 (Ventral keel)
                .texOffs(102, 54).addBox(-4.0F, 12.5F, -12.0F, 8.0F, 2.0F, 24.0F)
                // 前中后三段背脊祥云鳍饰 (Triple dorsal cloud crests)
                .texOffs(148, 0).addBox(-1.5F, -14.0F, -12.0F, 3.0F, 4.0F, 10.0F)
                .texOffs(148, 14).addBox(-2.0F, -17.0F, 0.0F, 4.0F, 7.0F, 14.0F)
                .texOffs(148, 35).addBox(-1.5F, -13.0F, 14.0F, 3.0F, 3.0F, 8.0F),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        // 须弥仙阁法座 (Sumeru Lotus Platform / Daoist Throne for Player Riding)
        body.addOrReplaceChild("throne", CubeListBuilder.create()
                // 须弥基台 (Sumeru Jade Plinth)
                .texOffs(184, 0).addBox(-8.0F, -12.0F, -6.0F, 16.0F, 2.0F, 12.0F)
                // 莲瓣围框 (Lotus Petal Borders)
                .texOffs(184, 14).addBox(-7.0F, -13.5F, -6.5F, 14.0F, 2.0F, 1.0F)
                .texOffs(184, 14).addBox(-7.0F, -13.5F, 5.5F, 14.0F, 2.0F, 1.0F)
                .texOffs(184, 17).addBox(7.5F, -13.5F, -5.5F, 1.0F, 2.0F, 11.0F)
                .texOffs(184, 17).addBox(-8.5F, -13.5F, -5.5F, 1.0F, 2.0F, 11.0F)
                // 四角云纹金柱 (Four Pavilion Pillars)
                .texOffs(214, 14).addBox(-6.5F, -19.0F, -4.5F, 1.5F, 7.0F, 1.5F)
                .texOffs(214, 14).addBox(5.0F, -19.0F, -4.5F, 1.5F, 7.0F, 1.5F)
                .texOffs(214, 14).addBox(-6.5F, -19.0F, 3.0F, 1.5F, 7.0F, 1.5F)
                .texOffs(214, 14).addBox(5.0F, -19.0F, 3.0F, 1.5F, 7.0F, 1.5F)
                // 飞檐华盖 (Flying Eaves Canopy)
                .texOffs(184, 30).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 1.0F, 14.0F)
                // 云锦蒲团坐席 (Silk Cushion)
                .texOffs(220, 0).addBox(-4.0F, -13.0F, -3.0F, 8.0F, 1.5F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 2. 硕大流线流云鲸首与长吻 (Streamlined Rostrum, Head, Baleen & Throat)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 上颌主颅骨 (Upper rostrum cranium)
                .texOffs(0, 88).addBox(-12.0F, -9.0F, -24.0F, 24.0F, 15.0F, 24.0F)
                // 吻部前收拢 (Rostrum taper)
                .texOffs(72, 88).addBox(-9.0F, -7.0F, -32.0F, 18.0F, 11.0F, 8.0F)
                // 吻端前锋 (Snout tip)
                .texOffs(124, 88).addBox(-6.0F, -5.0F, -36.0F, 12.0F, 8.0F, 4.0F)
                // 顶上呼吸气孔与祥云浮雕 (Blowhole & Crest)
                .texOffs(156, 88).addBox(-3.0F, -10.5F, -16.0F, 6.0F, 2.0F, 6.0F)
                // 左右鲸须梳板 (Baleen plates)
                .texOffs(180, 88).addBox(8.5F, 3.0F, -28.0F, 1.0F, 4.0F, 18.0F)
                .texOffs(180, 88).mirror().addBox(-9.5F, 3.0F, -28.0F, 1.0F, 4.0F, 18.0F)
                // 下颌喉褶条纹板块 (Lower jaw)
                .texOffs(0, 127).addBox(-10.5F, 5.0F, -24.0F, 21.0F, 5.0F, 24.0F)
                // 兜底长下巴 (Chin taper)
                .texOffs(90, 127).addBox(-8.0F, 4.0F, -31.0F, 16.0F, 4.0F, 7.0F)
                // 左右灵蛟长须 (Celestial Whiskers)
                .texOffs(136, 127).addBox(10.5F, 1.0F, -26.0F, 1.0F, 1.0F, 16.0F)
                .texOffs(136, 127).mirror().addBox(-11.5F, 1.0F, -26.0F, 1.0F, 1.0F, 16.0F),
                PartPose.offset(0.0F, 0.0F, -16.0F));

        // 3. 巨幅垂天之云飞天双翼 (Multi-layered 60-wide Cloud Wings)
        // 左翼 (Left Wing)
        PartDefinition leftFin = body.addOrReplaceChild("left_fin", CubeListBuilder.create()
                // 内翼主骨 (Inner wing bone)
                .texOffs(0, 156).addBox(0.0F, -2.0F, -10.0F, 20.0F, 4.0F, 20.0F)
                // 中翼主展面 (Mid wing body)
                .texOffs(80, 156).addBox(20.0F, -1.5F, -9.0F, 24.0F, 3.0F, 18.0F)
                // 翼后缘祥云羽化飘带 (Trailing cloud streamer)
                .texOffs(0, 180).addBox(8.0F, -0.5F, 10.0F, 36.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(13.0F, 2.0F, -6.0F, 0.08F, -0.15F, 0.12F));
        // 外翼飞羽尖梢 (Outer primary feather blade)
        leftFin.addOrReplaceChild("left_fin_tip", CubeListBuilder.create()
                .texOffs(164, 156).addBox(0.0F, -1.0F, -8.0F, 26.0F, 2.0F, 16.0F),
                PartPose.offsetAndRotation(44.0F, 0.0F, 0.0F, 0.0F, -0.10F, 0.08F));

        // 右翼 (Right Wing - 对称镜像)
        PartDefinition rightFin = body.addOrReplaceChild("right_fin", CubeListBuilder.create()
                .texOffs(0, 156).mirror().addBox(-20.0F, -2.0F, -10.0F, 20.0F, 4.0F, 20.0F)
                .texOffs(80, 156).mirror().addBox(-44.0F, -1.5F, -9.0F, 24.0F, 3.0F, 18.0F)
                .texOffs(0, 180).mirror().addBox(-44.0F, -0.5F, 10.0F, 36.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(-13.0F, 2.0F, -6.0F, 0.08F, 0.15F, -0.12F));
        rightFin.addOrReplaceChild("right_fin_tip", CubeListBuilder.create()
                .texOffs(164, 156).mirror().addBox(-26.0F, -1.0F, -8.0F, 26.0F, 2.0F, 16.0F),
                PartPose.offsetAndRotation(-44.0F, 0.0F, 0.0F, 0.0F, 0.10F, -0.08F));

        // 4. 四段无缝巨尾与无隙如意大尾鳍 (4-Segment Seamless Tail with ZERO Gap)
        // 尾段 1：连接躯干基部 (Tail Base: length 28)
        PartDefinition tailBase = body.addOrReplaceChild("tail_base", CubeListBuilder.create()
                .texOffs(0, 187).addBox(-9.5F, -8.5F, 0.0F, 19.0F, 17.0F, 28.0F)
                .texOffs(94, 187).addBox(-1.0F, -12.5F, 8.0F, 2.0F, 4.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 16.0F));

        // 尾段 2：中段收缩段 (Tail Mid 1: length 26, starts at -1.0F to SEAMLESSLY OVERLAP, ZERO GAP)
        PartDefinition tailMid1 = tailBase.addOrReplaceChild("tail_mid1", CubeListBuilder.create()
                .texOffs(126, 187).addBox(-7.0F, -6.5F, -1.0F, 14.0F, 13.0F, 26.0F)
                .texOffs(94, 205).addBox(-1.0F, -9.5F, 6.0F, 2.0F, 3.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 27.0F));

        // 尾段 3：尾柄关节段 (Tail Mid 2: length 22, starts at -1.0F for seamless overlap)
        PartDefinition tailMid2 = tailMid1.addOrReplaceChild("tail_mid2", CubeListBuilder.create()
                .texOffs(166, 54).addBox(-4.5F, -4.5F, -1.0F, 9.0F, 9.0F, 22.0F),
                PartPose.offset(0.0F, 0.0F, 24.0F));

        // 尾段 4：如意祥云双瓣大横尾鳍 (Tail Flukes: ZERO GAP! Overlaps tail_mid2 completely)
        tailMid2.addOrReplaceChild("tail_fluke", CubeListBuilder.create()
                // 尾鳍根部紧贴基座 (Fluke Root Hub: seamlessly encloses caudal peduncle)
                .texOffs(62, 232).addBox(-6.0F, -2.0F, -2.0F, 12.0F, 4.0F, 8.0F)
                // 左如意尾叶 (Left Fluke Lobe)
                .texOffs(102, 226).addBox(0.0F, -1.5F, 2.0F, 30.0F, 3.0F, 22.0F)
                // 右如意尾叶 (Right Fluke Lobe)
                .texOffs(102, 226).mirror().addBox(-30.0F, -1.5F, 2.0F, 30.0F, 3.0F, 22.0F)
                // 尾背立式祥云鳍 (Dorsal tail stabilizer)
                .texOffs(206, 226).addBox(-1.0F, -8.0F, 4.0F, 2.0F, 7.0F, 14.0F)
                // 尾腹立式祥云鳍 (Ventral tail stabilizer)
                .texOffs(228, 54).addBox(-1.0F, 1.5F, 6.0F, 2.0F, 5.0F, 12.0F),
                PartPose.offset(0.0F, 0.0F, 20.0F));

        return LayerDefinition.create(mesh, 256, 256);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // 巨鲲巡天慢速宏伟呼吸与波浪律动
        float age = state.ageInTicks * 0.035F;
        float wave = Mth.sin(age);
        float waveCos = Mth.cos(age);

        // 四级连续正弦波波动（解决尾巴断开，各段紧密跟随形成顺滑骨骼链）
        this.tailBase.xRot = wave * 0.08F;
        this.tailMid1.xRot = Mth.sin(age - 0.4F) * 0.12F;
        this.tailMid2.xRot = Mth.sin(age - 0.8F) * 0.16F;
        this.tailFluke.xRot = Mth.sin(age - 1.2F) * 0.20F;

        // 左右微摆
        this.tailBase.yRot = waveCos * 0.04F;
        this.tailMid1.yRot = Mth.cos(age - 0.4F) * 0.06F;
        this.tailMid2.yRot = Mth.cos(age - 0.8F) * 0.08F;
        this.tailFluke.yRot = Mth.cos(age - 1.2F) * 0.10F;

        // 垂天双翼舒展微振（内翼大展面 + 外翼飞羽尖梢柔韧起伏波浪）
        this.leftFin.zRot = 0.12F + wave * 0.10F;
        this.rightFin.zRot = -0.12F - wave * 0.10F;
        this.leftFin.yRot = -0.15F + waveCos * 0.06F;
        this.rightFin.yRot = 0.15F - waveCos * 0.06F;

        // 外翼飞羽尖梢滞后 0.35 rad，随风浪起伏弯折
        this.leftFinTip.zRot = 0.08F + Mth.sin(age - 0.35F) * 0.12F;
        this.rightFinTip.zRot = -0.08F - Mth.sin(age - 0.35F) * 0.12F;
        this.leftFinTip.yRot = -0.10F + Mth.cos(age - 0.35F) * 0.08F;
        this.rightFinTip.yRot = 0.10F - Mth.cos(age - 0.35F) * 0.08F;

        // 头部轻微浮沉响应
        this.head.xRot = -wave * 0.04F;

        // 巨躯微浪浮沉
        this.body.y = 8.0F + wave * 2.0F;
    }
}
