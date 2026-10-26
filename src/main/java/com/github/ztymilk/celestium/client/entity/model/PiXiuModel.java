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
 * 天禄貔貅模型 (Pi Xiu Model)
 * 威武神狮之躯、独角破苍穹、双翼生祥云、金玉鳞甲护体。
 */
public class PiXiuModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("pi_xiu"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart legFL;
    private final ModelPart legFR;
    private final ModelPart legBL;
    private final ModelPart legBR;
    private final ModelPart tail;
    private final ModelPart wingL;
    private final ModelPart wingR;

    public PiXiuModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.wingL = this.body.getChild("wing_l");
        this.wingR = this.body.getChild("wing_r");
        this.tail = this.body.getChild("tail");
        this.legFL = root.getChild("leg_fl");
        this.legFR = root.getChild("leg_fr");
        this.legBL = root.getChild("leg_bl");
        this.legBR = root.getChild("leg_br");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 狮首龙吻与通天单角 (Head, Horn, Mane, Snout)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                // 狮龙颅骨
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 8.0F)
                // 威严宽吻
                .texOffs(32, 0).addBox(-3.0F, -1.0F, -11.0F, 6.0F, 5.0F, 6.0F)
                // 额心通天碧玉神角
                .texOffs(0, 32).addBox(-1.0F, -9.0F, -4.0F, 2.0F, 6.0F, 3.0F)
                // 左右金耳
                .texOffs(12, 32).addBox(-4.5F, -6.0F, -2.0F, 2.0F, 3.0F, 2.0F)
                .texOffs(12, 32).addBox(2.5F, -6.0F, -2.0F, 2.0F, 3.0F, 2.0F)
                // 颈部祥云鬃毛圈
                .texOffs(24, 32).addBox(-5.0F, -3.0F, -3.0F, 10.0F, 9.0F, 5.0F),
                PartPose.offset(0.0F, 11.0F, -8.0F));

        // 2. 雄壮金甲躯干 (Body)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 16).addBox(-5.0F, -4.5F, -7.0F, 10.0F, 9.0F, 15.0F)
                // 背脊祥云鳍饰
                .texOffs(36, 16).addBox(-1.0F, -6.5F, -5.0F, 2.0F, 3.0F, 12.0F),
                PartPose.offset(0.0F, 12.0F, 0.0F));

        // 左右胁下天禄羽翼 (Flank Wings)
        body.addOrReplaceChild("wing_l", CubeListBuilder.create()
                .texOffs(0, 48).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F),
                PartPose.offset(-5.0F, -2.0F, -4.0F));

        body.addOrReplaceChild("wing_r", CubeListBuilder.create()
                .texOffs(20, 48).addBox(0.0F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F),
                PartPose.offset(5.0F, -2.0F, -4.0F));

        // 蜿蜒如意龙尾 (Tail)
        body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(40, 48).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 8.0F)
                // 尾尖卷云锦簇
                .texOffs(56, 32).addBox(-2.5F, -3.5F, 6.0F, 5.0F, 6.0F, 5.0F),
                PartPose.offset(0.0F, -1.0F, 8.0F));

        // 3. 四柱神兽金爪 (Four Legs)
        root.addOrReplaceChild("leg_fl", CubeListBuilder.create()
                .texOffs(0, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F),
                PartPose.offset(-3.5F, 15.0F, -5.0F));

        root.addOrReplaceChild("leg_fr", CubeListBuilder.create()
                .texOffs(16, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F),
                PartPose.offset(3.5F, 15.0F, -5.0F));

        root.addOrReplaceChild("leg_bl", CubeListBuilder.create()
                .texOffs(32, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F),
                PartPose.offset(-3.5F, 15.0F, 5.5F));

        root.addOrReplaceChild("leg_br", CubeListBuilder.create()
                .texOffs(48, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F),
                PartPose.offset(3.5F, 15.0F, 5.5F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.7F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.7F;

        // 四肢行走步态
        this.legFL.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.2F * state.walkAnimationSpeed;
        this.legFR.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.2F * state.walkAnimationSpeed;
        this.legBL.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.2F * state.walkAnimationSpeed;
        this.legBR.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.2F * state.walkAnimationSpeed;

        // 如意尾摆与胁翼呼吸律动
        this.tail.yRot = Mth.sin(state.ageInTicks * 0.12F) * 0.28F;
        this.tail.xRot = -0.2F + Mth.cos(state.ageInTicks * 0.08F) * 0.08F;

        float wingWave = Mth.sin(state.ageInTicks * 0.15F) * 0.1F;
        this.wingL.zRot = -0.15F - wingWave;
        this.wingR.zRot = 0.15F + wingWave;
    }
}
