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
 * 丹顶仙鹤 (Celestial Crane)
 * 依照概念图：流线型飞天白鹤躯体、优美 S 弯鹤颈、丹砂鹤顶红、修长金喙、三色渐变舒展仙翼与涉水长足
 */
public class CelestialCraneModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("celestial_crane"), "main");

    private final ModelPart body;
    private final ModelPart neckBase;
    private final ModelPart neckTop;
    private final ModelPart head;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart tail;

    public CelestialCraneModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.neckBase = root.getChild("neck_base");
        this.neckTop = this.neckBase.getChild("neck_top");
        this.head = this.neckTop.getChild("head");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 仙鹤流线型躯干与玄青尾羽 (Streamlined Body & Tail)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 10.0F),
                PartPose.offset(0.0F, 10.0F, 0.0F));

        // 蓬松折叠尾羽 (Tiered tail plumes)
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(24, 0).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 7.0F),
                PartPose.offsetAndRotation(0.0F, -1.0F, 5.0F, -0.2F, 0.0F, 0.0F));

        // 2. 优美 S 弯双段鹤颈与灵秀仙首 (Curved S-Neck & Crowned Head)
        PartDefinition neckBase = root.addOrReplaceChild("neck_base", CubeListBuilder.create()
                .texOffs(0, 16).addBox(-1.0F, -6.0F, -1.5F, 2.0F, 7.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 9.0F, -4.0F, 0.25F, 0.0F, 0.0F));

        PartDefinition neckTop = neckBase.addOrReplaceChild("neck_top", CubeListBuilder.create()
                .texOffs(10, 16).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, -0.4F, 0.0F, 0.0F));

        PartDefinition head = neckTop.addOrReplaceChild("head", CubeListBuilder.create()
                // 鹤首
                .texOffs(18, 16).addBox(-1.5F, -2.5F, -3.0F, 3.0F, 3.0F, 4.0F)
                // 丹砂冠顶 (Red Crown)
                .texOffs(32, 16).addBox(-1.0F, -3.5F, -2.5F, 2.0F, 1.5F, 3.0F)
                // 修长尖喙 (Long Beak)
                .texOffs(42, 16).addBox(-0.5F, -1.5F, -8.0F, 1.0F, 1.5F, 5.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.15F, 0.0F, 0.0F));

        // 3. 舒展仙羽飞翼 (Broad flight wings matching Concept Image #1)
        root.addOrReplaceChild("left_wing", CubeListBuilder.create()
                // 主翼面 (羽白与孔雀青)
                .texOffs(0, 26).addBox(0.0F, -0.5F, -4.0F, 1.0F, 5.0F, 12.0F)
                // 翼梢桃粉色翎羽 (Peach-pink primary feather tips)
                .texOffs(26, 26).addBox(0.5F, 0.5F, 4.0F, 0.5F, 4.0F, 8.0F),
                PartPose.offsetAndRotation(3.2F, 8.5F, -1.0F, 0.0F, 0.0F, 0.1F));

        root.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(0, 26).addBox(-1.0F, -0.5F, -4.0F, 1.0F, 5.0F, 12.0F, true)
                .texOffs(26, 26).addBox(-1.0F, 0.5F, 4.0F, 0.5F, 4.0F, 8.0F, true),
                PartPose.offsetAndRotation(-3.2F, 8.5F, -1.0F, 0.0F, 0.0F, -0.1F));

        // 4. 修长优雅涉水足 (Long slender crane legs, 14 pixels high)
        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(42, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 14.0F, 1.0F)
                .texOffs(46, 0).addBox(-1.0F, 13.5F, -2.5F, 2.0F, 0.5F, 3.0F),
                PartPose.offset(1.8F, 10.0F, 1.0F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(42, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 14.0F, 1.0F, true)
                .texOffs(46, 0).addBox(-1.0F, 13.5F, -2.5F, 2.0F, 0.5F, 3.0F, true),
                PartPose.offset(-1.8F, 10.0F, 1.0F));

        return LayerDefinition.create(mesh, 64, 48);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // 鹤首自如转动
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.5F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.7F;

        // 颈部轻缓曲折随息律动
        float neckBob = Mth.sin(state.ageInTicks * 0.05F) * 0.05F;
        this.neckBase.xRot = 0.25F + neckBob;
        this.neckTop.xRot = -0.4F - neckBob * 0.8F;

        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // 仙步涉禽迈步
        this.leftLeg.xRot = Mth.cos(walkPos * 0.6F) * 0.9F * walkSpeed;
        this.rightLeg.xRot = Mth.cos(walkPos * 0.6F + (float) Math.PI) * 0.9F * walkSpeed;

        // 双翼舒展与扇动 (羽翼随行动翩跹)
        float wingFlap = Mth.cos(state.ageInTicks * 0.2F) * 0.15F + (walkSpeed * 0.25F);
        this.leftWing.zRot = 0.1F + wingFlap;
        this.rightWing.zRot = -0.1F - wingFlap;

        // 尾羽微翘
        this.tail.xRot = -0.2F + Mth.sin(state.ageInTicks * 0.06F) * 0.05F;
    }
}
