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
 * 九尾灵狐 (Nine-Tailed Fox)
 * 依照概念图：如雪白狐、优雅修长、额点朱砂花钿、颈佩通灵宝玉佩、足踏紫云履、背后九条云霞锦缎般翻滚摇曳的华美仙尾
 */
public class NineTailedFoxModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("nine_tailed_fox"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart[] tails = new ModelPart[9];

    public NineTailedFoxModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        for (int i = 0; i < 9; i++) {
            this.tails[i] = root.getChild("tail_" + i);
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 灵狐身躯与胸颈蓬松白毛 (Slender fox torso & chest ruff)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 主躯干 (Slender body)
                .texOffs(0, 0).addBox(-3.5F, -3.0F, -6.0F, 7.0F, 7.0F, 13.0F)
                // 胸前丰盈仙毛与璎珞 (Fluffy chest ruff & amulet)
                .texOffs(27, 0).addBox(-3.0F, -2.5F, -7.5F, 6.0F, 6.0F, 3.0F),
                PartPose.offset(0.0F, 13.0F, 0.0F));

        // 2. 秀气狐首、尖长口吻、灵动双耳与额前灵纹 (Fox Head & Ears)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                // 狐颅
                .texOffs(0, 20).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 6.0F)
                // 秀美长口吻 (Long slender snout)
                .texOffs(24, 20).addBox(-1.5F, 0.0F, -8.5F, 3.0F, 3.0F, 4.0F)
                // 额前灵玉花钿
                .texOffs(38, 20).addBox(-1.0F, -3.5F, -5.2F, 2.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 12.0F, -5.5F));

        // 灵狐尖耳 (紫尖白耳)
        head.addOrReplaceChild("left_ear", CubeListBuilder.create()
                .texOffs(44, 0).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 5.0F, 1.5F),
                PartPose.offsetAndRotation(2.2F, -2.5F, -2.5F, 0.0F, -0.15F, 0.25F));

        head.addOrReplaceChild("right_ear", CubeListBuilder.create()
                .texOffs(44, 0).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 5.0F, 1.5F, true),
                PartPose.offsetAndRotation(-2.2F, -2.5F, -2.5F, 0.0F, 0.15F, -0.25F));

        // 3. 修长优雅四肢 (Slender legs with purple socks)
        root.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F),
                PartPose.offset(2.2F, 15.0F, -4.0F));

        root.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, true),
                PartPose.offset(-2.2F, 15.0F, -4.0F));

        root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
                .texOffs(8, 32).addBox(-1.0F, 0.0F, -1.5F, 2.5F, 9.0F, 3.0F),
                PartPose.offset(2.2F, 15.0F, 4.5F));

        root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
                .texOffs(8, 32).addBox(-1.5F, 0.0F, -1.5F, 2.5F, 9.0F, 3.0F, true),
                PartPose.offset(-2.2F, 15.0F, 4.5F));

        // 4. 双层华美绽放九大仙尾 (Dual-tiered blooming fan of 9 billowing tails)
        // 依照概念图：尾羽向天际如祥云般浩瀚绽开
        float[] baseYaw = {-1.0F, -0.75F, -0.48F, -0.22F, 0.0F, 0.22F, 0.48F, 0.75F, 1.0F};
        float[] basePitch = {-0.6F, -0.85F, -1.05F, -1.25F, -1.35F, -1.25F, -1.05F, -0.85F, -0.6F};

        for (int i = 0; i < 9; i++) {
            PartDefinition tail = root.addOrReplaceChild("tail_" + i, CubeListBuilder.create()
                    // 尾部主躯段 (雪白蓬松)
                    .texOffs(20, 32).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 8.0F)
                    // 尾尖紫焰翎华 (Violet flame tip)
                    .texOffs(42, 32).addBox(-1.5F, -1.5F, 8.0F, 3.0F, 3.0F, 6.0F),
                    PartPose.offsetAndRotation(0.0F, 12.0F, 6.0F, basePitch[i], baseYaw[i], 0.0F));
        }

        return LayerDefinition.create(mesh, 64, 48);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // 头部自由灵动偏转
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.8F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.8F;

        // 优雅轻灵迈步
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        this.leftFrontLeg.xRot = Mth.cos(walkPos * 0.6662F) * 0.8F * walkSpeed;
        this.rightFrontLeg.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.8F * walkSpeed;
        this.leftHindLeg.xRot = this.rightFrontLeg.xRot;
        this.rightHindLeg.xRot = this.leftFrontLeg.xRot;

        // 九尾如云海潮汐般翻滚起伏 (Harmonic wave sway across all 9 tails)
        float[] baseYaw = {-1.0F, -0.75F, -0.48F, -0.22F, 0.0F, 0.22F, 0.48F, 0.75F, 1.0F};
        float[] basePitch = {-0.6F, -0.85F, -1.05F, -1.25F, -1.35F, -1.25F, -1.05F, -0.85F, -0.6F};

        for (int i = 0; i < 9; i++) {
            float phase = state.ageInTicks * 0.08F + i * 0.35F;
            float waveY = Mth.sin(phase) * 0.12F;
            float waveX = Mth.cos(phase * 0.8F) * 0.1F;
            this.tails[i].yRot = baseYaw[i] + waveY;
            this.tails[i].xRot = basePitch[i] + waveX;
        }
    }
}
