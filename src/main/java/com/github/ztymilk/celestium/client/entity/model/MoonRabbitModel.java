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
 * 广寒玉兔 (Moon Rabbit)
 * 依照概念图：圆润软萌白兔体态、飞羽舒展天耳、额前镶红宝石金冠、胸前金铃项圈、背负织金紫缎鞍毯与四枚垂落朱红流苏
 */
public class MoonRabbitModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("moon_rabbit"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart body;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart bell;

    public MoonRabbitModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftEar = this.head.getChild("left_ear");
        this.rightEar = this.head.getChild("right_ear");
        this.bell = this.head.getChild("bell");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 圆润毛茸兔躯与紫金锦鞍 (Chubby Rabbit Body & Silk Saddle)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 滚圆软萌兔身 (Fluffy chubby body)
                .texOffs(0, 0).addBox(-4.0F, -3.5F, -5.0F, 8.0F, 7.0F, 10.0F)
                // 紫金仙缎鞍垫 (Royal purple gold-trimmed saddle blanket)
                .texOffs(0, 18).addBox(-4.2F, -4.2F, -3.0F, 8.4F, 2.0F, 7.0F)
                // 垂落四枚朱红流苏 (4 Vermilion hanging tassels on flanks)
                .texOffs(31, 18).addBox(4.2F, -2.5F, -2.5F, 0.5F, 4.0F, 2.0F)
                .texOffs(31, 18).addBox(4.2F, -2.5F, 1.5F, 0.5F, 4.0F, 2.0F)
                .texOffs(31, 18).addBox(-4.7F, -2.5F, -2.5F, 0.5F, 4.0F, 2.0F)
                .texOffs(31, 18).addBox(-4.7F, -2.5F, 1.5F, 0.5F, 4.0F, 2.0F),
                PartPose.offset(0.0F, 17.5F, 0.0F));

        // 毛茸茸小圆尾 (Fluffy ball tail)
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(38, 25).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F),
                PartPose.offset(0.0F, -0.5F, 5.0F));

        // 2. 软萌玉兔首、两颊腮毛、额前红宝金冠与金铃项圈 (Head, Tiara, Cheeks & Golden Bell)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                // 兔头主形
                .texOffs(36, 0).addBox(-3.0F, -4.5F, -4.5F, 6.0F, 6.0F, 6.0F)
                // 两颊蓬松颊毛 (Chubby cheek fur tufts)
                .texOffs(36, 12).addBox(-3.5F, -2.0F, -4.0F, 7.0F, 4.0F, 5.0F)
                // 额前赤玉金冠 (Forehead ruby gold tiara)
                .texOffs(0, 27).addBox(-1.5F, -5.0F, -5.0F, 3.0F, 2.5F, 1.0F)
                // 秀气三瓣唇小嘴与下颌毛
                .texOffs(9, 27).addBox(-1.5F, 0.5F, -5.0F, 3.0F, 2.0F, 3.0F),
                PartPose.offset(0.0F, 16.0F, -4.0F));

        // 金丝项圈与垂胸金铃 (Golden collar & dangling bell)
        head.addOrReplaceChild("bell", CubeListBuilder.create()
                .texOffs(22, 27).addBox(-1.0F, 2.0F, -4.5F, 2.0F, 2.5F, 2.0F),
                PartPose.ZERO);

        // 3. 羽翼般向外舒展的飞天仙耳 (Wing-like flared celestial ears)
        head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                .texOffs(48, 22).addBox(-0.5F, -9.0F, -1.5F, 1.0F, 9.0F, 3.5F)
                .texOffs(57, 22).addBox(-0.5F, -11.0F, -0.5F, 1.0F, 3.0F, 2.5F),
                PartPose.offsetAndRotation(2.0F, -4.0F, -1.0F, -0.2F, -0.15F, 0.45F));

        head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(48, 22).addBox(-0.5F, -9.0F, -1.5F, 1.0F, 9.0F, 3.5F, true)
                .texOffs(57, 22).addBox(-0.5F, -11.0F, -0.5F, 1.0F, 3.0F, 2.5F, true),
                PartPose.offsetAndRotation(-2.0F, -4.0F, -1.0F, -0.2F, 0.15F, -0.45F));

        // 4. 四足灵兔小腿与肉爪 (Paws)
        root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-1.0F, 0.0F, -2.0F, 2.5F, 4.5F, 4.0F),
                PartPose.offset(3.0F, 19.5F, 3.0F));

        root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-1.5F, 0.0F, -2.0F, 2.5F, 4.5F, 4.0F, true),
                PartPose.offset(-3.0F, 19.5F, 3.0F));

        root.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                .texOffs(14, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.5F, 2.0F),
                PartPose.offset(2.5F, 19.5F, -3.5F));

        root.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(14, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.5F, 2.0F, true),
                PartPose.offset(-2.5F, 19.5F, -3.5F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // 头部微仰与转动
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);

        // 飞羽仙耳灵巧微颤与抖动
        float earIdle = Mth.sin(state.ageInTicks * 0.08F) * 0.08F;
        this.leftEar.zRot = 0.45F + earIdle;
        this.rightEar.zRot = -0.45F - earIdle;
        this.leftEar.xRot = -0.2F + Mth.cos(state.ageInTicks * 0.06F) * 0.06F;
        this.rightEar.xRot = -0.2F + Mth.cos(state.ageInTicks * 0.06F) * 0.06F;

        // 胸前金铃轻摆
        this.bell.zRot = Mth.sin(state.ageInTicks * 0.12F) * 0.15F;

        // 蹦跳运动 (Hopping walk cycle)
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        float hop = Mth.sin(walkPos * 0.8F);
        this.leftHindLeg.xRot = hop * 0.8F * walkSpeed;
        this.rightHindLeg.xRot = hop * 0.8F * walkSpeed;
        this.leftFrontLeg.xRot = -hop * 0.7F * walkSpeed;
        this.rightFrontLeg.xRot = -hop * 0.7F * walkSpeed;

        this.body.y = 17.5F + Math.abs(hop) * -2.0F * walkSpeed;
        this.head.y = 16.0F + Math.abs(hop) * -1.5F * walkSpeed;
    }
}
