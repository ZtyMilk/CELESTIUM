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
 * 流云巨鲲 (Colossal Cloud Whale / Kun)
 * 北冥有鱼，其名为鲲。其翼若垂天之云，舒展八十格巍峨苍穹规制。
 * 包含宽厚星宿鲸躯、流云喉褶下颌、超巨幅双段飞天垂云翼、波浪起伏多节龙鲸巨尾与祥云横尾鳍。
 */
public class CloudWhaleModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("cloud_whale"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftFin;
    private final ModelPart rightFin;
    private final ModelPart tailBase;
    private final ModelPart tailMid;
    private final ModelPart tailFluke;

    public CloudWhaleModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.leftFin = this.body.getChild("left_fin");
        this.rightFin = this.body.getChild("right_fin");
        this.tailBase = this.body.getChild("tail_base");
        this.tailMid = this.tailBase.getChild("tail_mid");
        this.tailFluke = this.tailMid.getChild("tail_fluke");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 巍峨主躯干 (Central Torso: 26 x 20 x 32)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 宽厚星海鲸躯 (Torso)
                .texOffs(0, 0).addBox(-13.0F, -10.0F, -16.0F, 26.0F, 20.0F, 32.0F)
                // 背脊祥云鳍冠 (Dorsal cloud crest)
                .texOffs(120, 0).addBox(-1.5F, -16.0F, 0.0F, 3.0F, 6.0F, 12.0F)
                // 腹部月魄喉褶护板 (Ventral throat pleat plate)
                .texOffs(0, 54).addBox(-11.5F, 10.0F, -14.0F, 23.0F, 3.0F, 28.0F),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        // 2. 硕大流线流云鲸首与下颌长吻 (Rostrum & Throat)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 宏伟上颌与颅顶 (Upper rostrum)
                .texOffs(0, 88).addBox(-12.0F, -9.0F, -24.0F, 24.0F, 15.0F, 24.0F)
                // 下颌喉褶条纹板块 (Lower jaw grooves)
                .texOffs(100, 88).addBox(-10.5F, 5.0F, -22.0F, 21.0F, 7.0F, 22.0F),
                PartPose.offset(0.0F, 0.0F, -16.0F));

        // 3. 巨幅垂天之云飞天双翼 (Colossal Cloud Wings / Pectoral Flippers: 60 units per wing!)
        // 左翼：内翼基骨 + 外翼流云翼羽
        PartDefinition leftFin = body.addOrReplaceChild("left_fin", CubeListBuilder.create()
                .texOffs(0, 130).addBox(0.0F, -1.5F, -10.0F, 36.0F, 3.0F, 20.0F),
                PartPose.offsetAndRotation(13.0F, 2.0F, -6.0F, 0.08F, -0.15F, 0.12F));
        leftFin.addOrReplaceChild("left_fin_tip", CubeListBuilder.create()
                .texOffs(116, 130).addBox(0.0F, -1.0F, -8.0F, 24.0F, 2.0F, 16.0F),
                PartPose.offsetAndRotation(36.0F, 0.0F, 0.0F, 0.0F, -0.10F, 0.08F));

        // 右翼：内翼基骨 + 外翼流云翼羽 (对称镜像)
        PartDefinition rightFin = body.addOrReplaceChild("right_fin", CubeListBuilder.create()
                .texOffs(0, 130).addBox(-36.0F, -1.5F, -10.0F, 36.0F, 3.0F, 20.0F, true),
                PartPose.offsetAndRotation(-13.0F, 2.0F, -6.0F, 0.08F, 0.15F, -0.12F));
        rightFin.addOrReplaceChild("right_fin_tip", CubeListBuilder.create()
                .texOffs(116, 130).addBox(-24.0F, -1.0F, -8.0F, 24.0F, 2.0F, 16.0F, true),
                PartPose.offsetAndRotation(-36.0F, 0.0F, 0.0F, 0.0F, 0.10F, -0.08F));

        // 4. 柔顺两段巨龙鲸尾与如意横尾鳍 (Segmented Tail & Majestic 48-Wide Fluke)
        PartDefinition tailBase = body.addOrReplaceChild("tail_base", CubeListBuilder.create()
                .texOffs(0, 156).addBox(-9.0F, -8.0F, 0.0F, 18.0F, 16.0F, 28.0F),
                PartPose.offset(0.0F, 0.0F, 16.0F));

        PartDefinition tailMid = tailBase.addOrReplaceChild("tail_mid", CubeListBuilder.create()
                .texOffs(96, 156).addBox(-6.0F, -5.5F, 0.0F, 12.0F, 11.0F, 24.0F),
                PartPose.offset(0.0F, 0.0F, 28.0F));

        tailMid.addOrReplaceChild("tail_fluke", CubeListBuilder.create()
                // 舒展如意祥云双瓣横尾鳍 (48 units wide!)
                .texOffs(0, 204).addBox(-24.0F, -1.5F, 8.0F, 48.0F, 3.0F, 20.0F)
                // 尾背立式祥云尾脊 (Dorsal tail stabilizer)
                .texOffs(140, 204).addBox(-1.0F, -7.5F, 10.0F, 2.0F, 6.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 24.0F));

        return LayerDefinition.create(mesh, 256, 256);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // 巨鲲游弋于云海的慢速宏伟律动 (Majestic undulating motion)
        float wave = Mth.sin(state.ageInTicks * 0.035F);
        float waveCos = Mth.cos(state.ageInTicks * 0.035F);

        // 尾段逐节律动
        this.tailBase.xRot = wave * 0.12F;
        this.tailMid.xRot = waveCos * 0.18F;
        this.tailFluke.xRot = Mth.sin(state.ageInTicks * 0.035F + 0.5F) * 0.22F;

        // 垂天双翼舒展微振
        this.leftFin.zRot = 0.12F + wave * 0.10F;
        this.rightFin.zRot = -0.12F - wave * 0.10F;
        this.leftFin.yRot = -0.15F + waveCos * 0.06F;
        this.rightFin.yRot = 0.15F - waveCos * 0.06F;

        // 躯干微浪浮沉
        this.body.y = 8.0F + wave * 2.0F;
    }
}
