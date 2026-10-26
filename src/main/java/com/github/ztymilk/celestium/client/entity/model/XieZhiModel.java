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
 * 獬豸·天宪神兽 (Xie Zhi Model)
 * 神圣独角、狮形威躯、祥云肩焰、灵拂长尾。
 */
public class XieZhiModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("xie_zhi"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart horn;
    private final ModelPart tail;
    private final ModelPart legFL;
    private final ModelPart legFR;
    private final ModelPart legBL;
    private final ModelPart legBR;

    public XieZhiModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.horn = this.head.getChild("horn");
        this.tail = this.body.getChild("tail");
        this.legFL = root.getChild("leg_fl");
        this.legFR = root.getChild("leg_fr");
        this.legBL = root.getChild("leg_bl");
        this.legBR = root.getChild("leg_br");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 雄健兽躯与祥云肩甲 (Torso, Ribs, Cloud Shoulder Tufts)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 主胸膛 (Chest)
                .texOffs(0, 0).addBox(-6.5F, -7.0F, -11.0F, 13.0F, 13.0F, 12.0F)
                // 后腰胯部 (Haunches)
                .texOffs(52, 0).addBox(-5.5F, -6.0F, 1.0F, 11.0F, 11.0F, 11.0F)
                // 肩部祥云羽焰 (Left Cloud Flame)
                .texOffs(0, 27).addBox(6.0F, -9.0F, -10.0F, 3.0F, 6.0F, 8.0F)
                // 肩部祥云羽焰 (Right Cloud Flame)
                .texOffs(0, 27).mirror().addBox(-9.0F, -9.0F, -10.0F, 3.0F, 6.0F, 8.0F),
                PartPose.offset(0.0F, 10.0F, 0.0F));

        // 2. 威严神首与独角 (Noble Head, Brow, Horn, Jaws, Ears)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 颈项 (Neck)
                .texOffs(24, 27).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 9.0F, 7.0F)
                // 头颅 (Head)
                .texOffs(56, 24).addBox(-4.5F, -11.0F, -12.0F, 9.0F, 8.0F, 8.0F)
                // 威严长吻 (Snout & Muzzle)
                .texOffs(92, 24).addBox(-3.0F, -8.0F, -17.0F, 6.0F, 5.0F, 6.0F)
                // 下颚长髯 (Chin Beard)
                .texOffs(92, 37).addBox(-2.0F, -3.0F, -16.0F, 4.0F, 4.0F, 5.0F)
                // 双耳 (Left Ear)
                .texOffs(56, 42).addBox(3.5F, -14.0F, -8.0F, 2.0F, 4.0F, 3.0F)
                // 双耳 (Right Ear)
                .texOffs(56, 42).mirror().addBox(-5.5F, -14.0F, -8.0F, 2.0F, 4.0F, 3.0F),
                PartPose.offset(0.0F, -2.0F, -9.0F));

        // 神圣螺旋独角 (Sacred Spiral Horn of Justice)
        head.addOrReplaceChild("horn", CubeListBuilder.create()
                // 角基 (Horn Base)
                .texOffs(68, 42).addBox(-1.5F, -15.0F, -12.0F, 3.0F, 5.0F, 3.0F)
                // 角中段 (Horn Mid - Curving forward)
                .texOffs(82, 42).addBox(-1.0F, -19.0F, -14.0F, 2.0F, 5.0F, 2.0F)
                // 锐利角尖 (Horn Tip)
                .texOffs(92, 48).addBox(-0.5F, -23.0F, -16.5F, 1.0F, 5.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 3. 灵拂长尾 (Swaying Tassel Tail)
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(0, 43).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 10.0F)
                .texOffs(0, 58).addBox(-2.5F, -1.0F, 9.0F, 5.0F, 5.0F, 7.0F),
                PartPose.offset(0.0F, -3.0F, 11.0F));

        // 4. 四足神蹄 (4 Muscular Legs)
        CubeListBuilder legFront = CubeListBuilder.create()
                .texOffs(98, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F);
        CubeListBuilder legHind = CubeListBuilder.create()
                .texOffs(26, 45).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F);

        root.addOrReplaceChild("leg_fl", legFront, PartPose.offset(4.8F, 12.0F, -7.0F));
        root.addOrReplaceChild("leg_fr", legFront, PartPose.offset(-4.8F, 12.0F, -7.0F));
        root.addOrReplaceChild("leg_bl", legHind, PartPose.offset(4.2F, 12.0F, 8.0F));
        root.addOrReplaceChild("leg_br", legHind, PartPose.offset(-4.2F, 12.0F, 8.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float walkSpeed = state.walkAnimationSpeed;
        float walkPos = state.walkAnimationPos;
        float age = state.ageInTicks;

        // 四肢行走步态
        this.legFL.xRot = Mth.cos(walkPos * 0.6662F) * 0.7F * walkSpeed;
        this.legFR.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.7F * walkSpeed;
        this.legBL.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.7F * walkSpeed;
        this.legBR.xRot = Mth.cos(walkPos * 0.6662F) * 0.7F * walkSpeed;

        // 独角随头颅威严环视
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.5F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.5F;

        // 尾羽拂动
        this.tail.yRot = Mth.cos(walkPos * 0.5F) * 0.35F * walkSpeed + Mth.sin(age * 0.08F) * 0.12F;
    }
}