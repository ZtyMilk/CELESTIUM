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
 * 旋龟·玄灵负图 (Xuan Gui Model)
 * 八卦洛书神甲、鸟首衔珠、灵蛇盘尾、踏浪碧蹼。
 */
public class XuanGuiModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("xuan_gui"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart legFL;
    private final ModelPart legFR;
    private final ModelPart legBL;
    private final ModelPart legBR;

    public XuanGuiModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.tail = this.body.getChild("tail");
        this.legFL = root.getChild("leg_fl");
        this.legFR = root.getChild("leg_fr");
        this.legBL = root.getChild("leg_bl");
        this.legBR = root.getChild("leg_br");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 龟甲主躯 (Main Carapace & Shell Rim)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 腹甲基座 (Plastron)
                .texOffs(0, 0).addBox(-8.0F, -2.0F, -10.0F, 16.0F, 4.0F, 20.0F)
                // 碧玉穹隆主背甲 (Upper Shell Dome)
                .texOffs(0, 26).addBox(-9.0F, -7.0F, -11.0F, 18.0F, 6.0F, 22.0F)
                // 背甲凸棱与洛书八卦印 (Dorsal Ridge)
                .texOffs(0, 56).addBox(-3.0F, -9.0F, -9.0F, 6.0F, 3.0F, 18.0F)
                // 甲缘金镶边雕饰 (Shell Rim Flange)
                .texOffs(60, 56).addBox(-10.0F, -2.5F, -12.0F, 20.0F, 2.0F, 24.0F),
                PartPose.offset(0.0F, 18.0F, 0.0F));

        // 2. 鸟首与仙颈 (Bird-headed Neck, Golden Beak, Crown Crest)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 仙禽长颈 (Neck)
                .texOffs(74, 0).addBox(-2.5F, -6.0F, -5.0F, 5.0F, 7.0F, 6.0F)
                // 灵鸟头颅 (Head)
                .texOffs(98, 0).addBox(-3.0F, -9.0F, -10.0F, 6.0F, 5.0F, 6.0F)
                // 锋锐金喙 (Sharp Golden Beak)
                .texOffs(98, 13).addBox(-1.5F, -7.0F, -15.0F, 3.0F, 3.0F, 5.0F)
                // 顶上仙羽玉冠 (Crown Feather Crest)
                .texOffs(74, 15).addBox(-1.0F, -12.0F, -9.0F, 2.0F, 4.0F, 5.0F),
                PartPose.offset(0.0F, -1.0F, -10.0F));

        // 3. 灵蛇盘尾 (Serpent Tail with Dorsal Spines)
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(50, 0).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 3.0F, 8.0F)
                .texOffs(50, 13).addBox(-1.5F, -1.0F, 7.0F, 3.0F, 2.0F, 8.0F)
                // 蛇尾金尖 (Spiked Serpent Tail Tip)
                .texOffs(50, 25).addBox(-0.5F, -2.0F, 11.0F, 1.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 0.0F, 10.0F));

        // 4. 四足踏浪灵蹼 (4 Sturdy Webbed Flippers)
        CubeListBuilder flipperFrontL = CubeListBuilder.create()
                .texOffs(0, 80).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F)
                .texOffs(24, 80).addBox(-3.5F, 5.0F, -5.0F, 7.0F, 2.0F, 8.0F);
        CubeListBuilder flipperFrontR = CubeListBuilder.create()
                .texOffs(0, 80).mirror().addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F)
                .texOffs(24, 80).mirror().addBox(-3.5F, 5.0F, -5.0F, 7.0F, 2.0F, 8.0F);

        CubeListBuilder flipperHindL = CubeListBuilder.create()
                .texOffs(60, 84).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F)
                .texOffs(84, 84).addBox(-3.0F, 5.0F, -4.0F, 6.0F, 2.0F, 7.0F);
        CubeListBuilder flipperHindR = CubeListBuilder.create()
                .texOffs(60, 84).mirror().addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F)
                .texOffs(84, 84).mirror().addBox(-3.0F, 5.0F, -4.0F, 6.0F, 2.0F, 7.0F);

        root.addOrReplaceChild("leg_fl", flipperFrontL, PartPose.offset(9.0F, 17.0F, -7.0F));
        root.addOrReplaceChild("leg_fr", flipperFrontR, PartPose.offset(-9.0F, 17.0F, -7.0F));
        root.addOrReplaceChild("leg_bl", flipperHindL, PartPose.offset(8.5F, 17.0F, 7.0F));
        root.addOrReplaceChild("leg_br", flipperHindR, PartPose.offset(-8.5F, 17.0F, 7.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float walkSpeed = state.walkAnimationSpeed;
        float walkPos = state.walkAnimationPos;
        float age = state.ageInTicks;

        // 四肢蹒跚或划水摇摆
        this.legFL.xRot = Mth.cos(walkPos * 0.7F) * 0.5F * walkSpeed;
        this.legFR.xRot = Mth.cos(walkPos * 0.7F + (float) Math.PI) * 0.5F * walkSpeed;
        this.legBL.xRot = Mth.cos(walkPos * 0.7F + (float) Math.PI) * 0.45F * walkSpeed;
        this.legBR.xRot = Mth.cos(walkPos * 0.7F) * 0.45F * walkSpeed;

        // 鸟首灵动伸缩俯仰
        this.head.xRot = Mth.sin(age * 0.05F) * 0.06F;
        this.head.yRot = Mth.cos(age * 0.04F) * 0.08F;

        // 蛇尾自然摆动
        this.tail.yRot = Mth.sin(age * 0.08F) * 0.18F + Mth.sin(walkPos * 0.6F) * 0.3F * walkSpeed;
    }
}