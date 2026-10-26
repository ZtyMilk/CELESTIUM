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
 * 六牙白象 (Saddanta) - 普贤天尊法相神圣战象
 * 6支优美向上弯曲弧度的神圣象牙、自然外扬的双层雕花扇耳、三层须弥八宝莲花宝座、金丝璎珞鞍毯与四足金刚镯
 */
public class SaddantaModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("saddanta"), "main");

    private final ModelPart head;
    private final ModelPart trunkBase;
    private final ModelPart trunkMid;
    private final ModelPart trunkTip;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;

    public SaddantaModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.trunkBase = this.head.getChild("trunk_base");
        this.trunkMid = this.trunkBase.getChild("trunk_mid");
        this.trunkTip = this.trunkMid.getChild("trunk_tip");
        this.leftEar = this.head.getChild("left_ear");
        this.rightEar = this.head.getChild("right_ear");
        this.tail = this.body.getChild("tail");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 雄伟象躯与鞍鞯 (Majestic Torso & Saddle Blanket)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 雄浑主躯干 (Main Body)
                .texOffs(0, 0).addBox(-9.0F, -10.0F, -14.0F, 18.0F, 16.0F, 28.0F)
                // 织金赤锦鞍毯 (Embroidered saddle blanket)
                .texOffs(92, 0).addBox(-9.5F, -10.5F, -7.0F, 19.0F, 17.0F, 14.0F)
                // 垂落金丝璎珞流苏 (Hanging gold & jade tassel fringe)
                .texOffs(92, 32).addBox(-10.0F, 5.0F, -6.5F, 20.0F, 4.0F, 13.0F)
                // 护胸织金攀带 (Chest harness strap)
                .texOffs(160, 0).addBox(-9.4F, -6.0F, -15.0F, 18.8F, 4.0F, 2.0F)
                // 前胸护心铜镜 (Chest golden lion mirror)
                .texOffs(160, 8).addBox(-3.0F, -7.0F, -15.5F, 6.0F, 6.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, 0.0F));

        // 2. 三层须弥八宝莲花法座 (Tiered Sumeru Lotus Throne)
        PartDefinition throne = body.addOrReplaceChild("throne", CubeListBuilder.create()
                // 须弥座下枋基台 (Tier 1: Sumeru lower plinth)
                .texOffs(0, 46).addBox(-8.5F, -12.5F, -6.5F, 17.0F, 3.0F, 13.0F)
                // 须弥座束腰 (Tier 2: Sumeru waist with relief)
                .texOffs(0, 63).addBox(-7.0F, -14.5F, -5.0F, 14.0F, 2.0F, 10.0F)
                // 须弥座上枋台 (Tier 3: Upper plinth rim)
                .texOffs(62, 46).addBox(-8.0F, -16.5F, -6.0F, 16.0F, 2.0F, 12.0F)
                // 绽放金莲花瓣 - 前瓣 (Blooming Lotus Front Petal)
                .texOffs(120, 46).addBox(-5.0F, -20.0F, -7.0F, 10.0F, 4.0F, 2.0F)
                // 绽放金莲花瓣 - 后瓣 (Blooming Lotus Back Petal)
                .texOffs(120, 46).addBox(-5.0F, -20.0F, 5.0F, 10.0F, 4.0F, 2.0F)
                // 绽放金莲花瓣 - 左瓣 (Blooming Lotus Left Petal)
                .texOffs(146, 46).addBox(6.0F, -20.0F, -4.0F, 2.0F, 4.0F, 8.0F)
                // 绽放金莲花瓣 - 右瓣 (Blooming Lotus Right Petal)
                .texOffs(146, 46).addBox(-8.0F, -20.0F, -4.0F, 2.0F, 4.0F, 8.0F)
                // 中央莲蓬软垫 (Central lotus seedpad seat)
                .texOffs(168, 46).addBox(-6.0F, -17.5F, -4.0F, 12.0F, 1.5F, 8.0F)
                // 顶端摩尼法宝灵珠 (Top Mani jewel finial)
                .texOffs(210, 46).addBox(-2.5F, -24.0F, -2.5F, 5.0F, 7.0F, 5.0F),
                PartPose.ZERO);

        // 象尾 (Tail)
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(0, 76).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 14.0F, 2.0F)
                .texOffs(10, 76).addBox(-2.0F, 12.0F, -1.0F, 4.0F, 8.0F, 4.0F),
                PartPose.offset(0.0F, -2.0F, 14.0F));

        // 3. 象首与额前金冠 (Elephant Head & Jeweled Tiara)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                // 象首主骨 (Elephant skull)
                .texOffs(0, 94).addBox(-6.5F, -8.0F, -10.0F, 13.0F, 13.0F, 12.0F)
                // 额前红玛瑙神光金冠 (Ruby & gold tiara)
                .texOffs(52, 94).addBox(-4.5F, -9.0F, -10.5F, 9.0F, 5.0F, 2.0F)
                // 下颌咽喉 (Jaw & throat)
                .texOffs(52, 102).addBox(-4.0F, 4.0F, -6.0F, 8.0F, 4.0F, 6.0F),
                PartPose.offset(0.0F, 1.0F, -14.0F));

        // 4. 自然外扬的双层雕花扇耳 (Sculpted Fan Ears with Jade Rings)
        PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                // 耳根厚软骨 (Inner ear root)
                .texOffs(76, 74).addBox(0.0F, -5.0F, -2.0F, 1.5F, 15.0F, 6.0F)
                // 外扬大扇面 (Outer flared fan)
                .texOffs(92, 74).addBox(0.3F, -6.0F, 4.0F, 1.0F, 16.0F, 7.0F)
                // 耳垂碧玉金环坠 (Earring pendant)
                .texOffs(110, 74).addBox(0.5F, 9.0F, 2.0F, 1.0F, 4.0F, 3.0F),
                PartPose.offsetAndRotation(6.5F, -3.0F, -5.0F, 0.0F, 0.25F, -0.05F));

        PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(76, 74).addBox(-1.5F, -5.0F, -2.0F, 1.5F, 15.0F, 6.0F, true)
                .texOffs(92, 74).addBox(-1.3F, -6.0F, 4.0F, 1.0F, 16.0F, 7.0F, true)
                .texOffs(110, 74).addBox(-1.5F, 9.0F, 2.0F, 1.0F, 4.0F, 3.0F, true),
                PartPose.offsetAndRotation(-6.5F, -3.0F, -5.0F, 0.0F, -0.25F, 0.05F));

        // 5. 灵动修长象鼻 (3-Segmented Curling Elephant Trunk)
        PartDefinition trunkBase = head.addOrReplaceChild("trunk_base", CubeListBuilder.create()
                .texOffs(0, 120).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F),
                PartPose.offset(0.0F, 4.0F, -9.0F));

        PartDefinition trunkMid = trunkBase.addOrReplaceChild("trunk_mid", CubeListBuilder.create()
                .texOffs(26, 120).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F),
                PartPose.offset(0.0F, 7.0F, 0.0F));

        trunkMid.addOrReplaceChild("trunk_tip", CubeListBuilder.create()
                .texOffs(48, 120).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F),
                PartPose.offset(0.0F, 7.0F, 0.0F));

        // 6. 六根优美向上弯曲弧度的神圣象牙 (SIX CURVED SACRED TUSKS: 3 on left, 3 on right)
        // 左上大牙 (Left Upper Tusk: Base + Upward curving tip)
        PartDefinition leftTuskTop = head.addOrReplaceChild("left_tusk_top", CubeListBuilder.create()
                // 金色牙箍 (Gold root collar)
                .texOffs(66, 120).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 2.0F)
                // 象牙基段 (Root shaft)
                .texOffs(78, 120).addBox(-1.2F, -1.2F, -8.0F, 2.4F, 2.4F, 8.0F),
                PartPose.offsetAndRotation(5.0F, 4.0F, -7.0F, -0.15F, 0.28F, 0.0F));
        leftTuskTop.addOrReplaceChild("left_tusk_top_tip", CubeListBuilder.create()
                // 向上扬起的象牙尖端 (Upward curving tip)
                .texOffs(100, 120).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -7.5F, -0.38F, 0.05F, 0.0F));

        // 左中牙 (Left Mid Tusk)
        PartDefinition leftTuskMid = head.addOrReplaceChild("left_tusk_mid", CubeListBuilder.create()
                .texOffs(66, 120).addBox(-1.3F, -1.3F, -2.0F, 2.6F, 2.6F, 2.0F)
                .texOffs(78, 120).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 7.0F),
                PartPose.offsetAndRotation(6.0F, 2.0F, -6.0F, -0.22F, 0.38F, 0.05F));
        leftTuskMid.addOrReplaceChild("left_tusk_mid_tip", CubeListBuilder.create()
                .texOffs(100, 120).addBox(-0.8F, -0.8F, -7.0F, 1.6F, 1.6F, 7.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -6.5F, -0.32F, 0.08F, 0.0F));

        // 左下牙 (Left Bot Tusk)
        PartDefinition leftTuskBot = head.addOrReplaceChild("left_tusk_bot", CubeListBuilder.create()
                .texOffs(66, 120).addBox(-1.2F, -1.2F, -2.0F, 2.4F, 2.4F, 2.0F)
                .texOffs(78, 120).addBox(-0.9F, -0.9F, -6.0F, 1.8F, 1.8F, 6.0F),
                PartPose.offsetAndRotation(4.0F, 6.0F, -6.5F, -0.08F, 0.18F, -0.05F));
        leftTuskBot.addOrReplaceChild("left_tusk_bot_tip", CubeListBuilder.create()
                .texOffs(100, 120).addBox(-0.7F, -0.7F, -6.0F, 1.4F, 1.4F, 6.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -5.5F, -0.25F, 0.05F, 0.0F));

        // 右上大牙 (Right Upper Tusk)
        PartDefinition rightTuskTop = head.addOrReplaceChild("right_tusk_top", CubeListBuilder.create()
                .texOffs(66, 120).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 2.0F)
                .texOffs(78, 120).addBox(-1.2F, -1.2F, -8.0F, 2.4F, 2.4F, 8.0F),
                PartPose.offsetAndRotation(-5.0F, 4.0F, -7.0F, -0.15F, -0.28F, 0.0F));
        rightTuskTop.addOrReplaceChild("right_tusk_top_tip", CubeListBuilder.create()
                .texOffs(100, 120).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -7.5F, -0.38F, -0.05F, 0.0F));

        // 右中牙 (Right Mid Tusk)
        PartDefinition rightTuskMid = head.addOrReplaceChild("right_tusk_mid", CubeListBuilder.create()
                .texOffs(66, 120).addBox(-1.3F, -1.3F, -2.0F, 2.6F, 2.6F, 2.0F)
                .texOffs(78, 120).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 7.0F),
                PartPose.offsetAndRotation(-6.0F, 2.0F, -6.0F, -0.22F, -0.38F, -0.05F));
        rightTuskMid.addOrReplaceChild("right_tusk_mid_tip", CubeListBuilder.create()
                .texOffs(100, 120).addBox(-0.8F, -0.8F, -7.0F, 1.6F, 1.6F, 7.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -6.5F, -0.32F, -0.08F, 0.0F));

        // 右下牙 (Right Bot Tusk)
        PartDefinition rightTuskBot = head.addOrReplaceChild("right_tusk_bot", CubeListBuilder.create()
                .texOffs(66, 120).addBox(-1.2F, -1.2F, -2.0F, 2.4F, 2.4F, 2.0F)
                .texOffs(78, 120).addBox(-0.9F, -0.9F, -6.0F, 1.8F, 1.8F, 6.0F),
                PartPose.offsetAndRotation(-4.0F, 6.0F, -6.5F, -0.08F, -0.18F, 0.05F));
        rightTuskBot.addOrReplaceChild("right_tusk_bot_tip", CubeListBuilder.create()
                .texOffs(100, 120).addBox(-0.7F, -0.7F, -6.0F, 1.4F, 1.4F, 6.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, -5.5F, -0.25F, -0.05F, 0.0F));

        // 7. 四足天柱神腿与金刚宝镯 (4 Sturdy Elephant Legs with Vajra Anklets)
        root.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                .texOffs(0, 140).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F)
                .texOffs(120, 140).addBox(-4.5F, 8.5F, -4.5F, 9.0F, 4.0F, 9.0F),
                PartPose.offset(6.5F, 10.0F, -8.0F));

        root.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(34, 140).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F)
                .texOffs(120, 140).addBox(-4.5F, 8.5F, -4.5F, 9.0F, 4.0F, 9.0F),
                PartPose.offset(-6.5F, 10.0F, -8.0F));

        root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                .texOffs(68, 140).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F)
                .texOffs(120, 140).addBox(-4.5F, 8.5F, -4.5F, 9.0F, 4.0F, 9.0F),
                PartPose.offset(6.5F, 10.0F, 8.0F));

        root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(68, 140).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F)
                .texOffs(120, 140).addBox(-4.5F, 8.5F, -4.5F, 9.0F, 4.0F, 9.0F),
                PartPose.offset(-6.5F, 10.0F, 8.0F));

        return LayerDefinition.create(mesh, 256, 256);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // 头部自由转动
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);

        // 象鼻灵动自然摆动与微卷
        float trunkSwing = Mth.sin(state.ageInTicks * 0.06F);
        this.trunkBase.xRot = -0.15F + trunkSwing * 0.1F;
        this.trunkMid.xRot = -0.2F + Mth.sin(state.ageInTicks * 0.06F + 0.6F) * 0.15F;
        this.trunkTip.xRot = -0.35F + Mth.sin(state.ageInTicks * 0.06F + 1.2F) * 0.2F;
        this.trunkBase.yRot = Mth.cos(state.ageInTicks * 0.04F) * 0.12F;

        // 扇耳随风与行动自然扇动
        float earFlap = Mth.sin(state.ageInTicks * 0.05F) * 0.08F;
        this.leftEar.yRot = 0.25F + earFlap;
        this.rightEar.yRot = -0.25F - earFlap;

        // 沉稳神象四足漫步
        float limbWalk = state.walkAnimationPos * 0.45F;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        this.leftFrontLeg.xRot = Mth.cos(limbWalk) * 0.7F * walkSpeed;
        this.rightFrontLeg.xRot = Mth.cos(limbWalk + (float) Math.PI) * 0.7F * walkSpeed;
        this.leftHindLeg.xRot = this.rightFrontLeg.xRot;
        this.rightHindLeg.xRot = this.leftFrontLeg.xRot;

        // 象尾摇曳
        this.tail.zRot = Mth.sin(state.ageInTicks * 0.08F) * 0.15F;
    }
}
