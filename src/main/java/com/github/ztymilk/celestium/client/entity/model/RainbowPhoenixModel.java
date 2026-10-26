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
 * 九天玄凤 (Rainbow Phoenix)
 * 凤冠高耸，修长双羽展翅，拖曳三道华美流光凤尾飘带。
 */
public class RainbowPhoenixModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("rainbow_phoenix"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart tailFeathers;

    public RainbowPhoenixModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.leftWing = this.body.getChild("left_wing");
        this.rightWing = this.body.getChild("right_wing");
        this.tailFeathers = this.body.getChild("tail_feathers");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 躯干 (Body)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 12.0F),
                PartPose.offset(0.0F, 14.0F, 0.0F));

        // 凤首与凤冠 (Head & Crown)
        body.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(28, 0).addBox(-2.5F, -9.0F, -4.0F, 5.0F, 9.0F, 5.0F)
                // 金喙 (Beak)
                .texOffs(48, 0).addBox(-1.0F, -6.0F, -8.0F, 2.0F, 3.0F, 4.0F)
                // 冲天凤冠 (Crest)
                .texOffs(0, 20).addBox(-0.5F, -14.0F, -2.0F, 1.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, -2.0F, -5.0F));

        // 展翅双翼 (Wings)
        body.addOrReplaceChild("left_wing", CubeListBuilder.create()
                .texOffs(0, 32).addBox(0.0F, -1.0F, -4.0F, 16.0F, 1.0F, 10.0F),
                PartPose.offset(4.0F, -2.0F, -2.0F));

        body.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-16.0F, -1.0F, -4.0F, 16.0F, 1.0F, 10.0F, true),
                PartPose.offset(-4.0F, -2.0F, -2.0F));

        // 华美三道飘带凤尾 (Long Phoenix Tail Streamers)
        body.addOrReplaceChild("tail_feathers", CubeListBuilder.create()
                .texOffs(32, 16).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 18.0F)
                .texOffs(0, 44).addBox(-1.5F, 1.0F, 12.0F, 3.0F, 1.0F, 10.0F),
                PartPose.offsetAndRotation(0.0F, -1.0F, 6.0F, 0.25F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float flap = Mth.sin(state.ageInTicks * 0.2F);
        this.leftWing.zRot = 0.2F + flap * 0.4F;
        this.rightWing.zRot = -0.2F - flap * 0.4F;

        // 凤尾随风飘舞
        this.tailFeathers.xRot = 0.25F + Mth.sin(state.ageInTicks * 0.1F) * 0.1F;
        this.tailFeathers.yRot = Mth.cos(state.ageInTicks * 0.08F) * 0.12F;
    }
}
