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
 * 衔烛照天·上古烛龙 (Candle Dragon / 烛九阴)
 * 视为昼，瞑为夜，吹为冬，呼为夏。
 * 庞大蜿蜒的赤金蛇神龙躯、额前悬浮旋转燃烧的九阳神珠、烈阳火鬃与极天流火尾羽。
 */
public class CandleDragonModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("candle_dragon"), "main");

    private final ModelPart head;
    private final ModelPart sunOrbOuter;
    private final ModelPart sunOrbInner;
    private final ModelPart whiskerL;
    private final ModelPart whiskerR;
    private final ModelPart neck;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart tail;
    private final ModelPart tailFlame;

    public CandleDragonModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.sunOrbOuter = this.head.getChild("sun_orb_outer");
        this.sunOrbInner = this.sunOrbOuter.getChild("sun_orb_inner");
        this.whiskerL = this.head.getChild("whisker_l");
        this.whiskerR = this.head.getChild("whisker_r");

        this.neck = root.getChild("neck");
        this.body1 = this.neck.getChild("body1");
        this.body2 = this.body1.getChild("body2");
        this.body3 = this.body2.getChild("body3");
        this.body4 = this.body3.getChild("body4");
        this.tail = this.body4.getChild("tail");
        this.tailFlame = this.tail.getChild("tail_flame");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 威仪龙首 (Dragon Head, Antlers, Crown, Snout)
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                // 龙颅
                .texOffs(0, 0).addBox(-4.5F, -4.5F, -9.0F, 9.0F, 9.0F, 9.0F)
                // 威严长吻
                .texOffs(36, 0).addBox(-3.5F, -2.0F, -18.0F, 7.0F, 6.0F, 9.0F)
                // 下颚
                .texOffs(68, 0).addBox(-3.0F, 3.0F, -17.0F, 6.0F, 3.0F, 8.0F)
                // 炎阳金乌龙冠
                .texOffs(96, 0).addBox(-5.0F, -7.0F, -8.0F, 10.0F, 3.0F, 4.0F)
                // 颔下赤须
                .texOffs(52, 20).addBox(-1.5F, 4.0F, -14.0F, 3.0F, 4.0F, 3.0F)
                // 左赤金神角
                .texOffs(0, 20).addBox(-4.5F, -12.0F, -5.0F, 2.0F, 8.0F, 3.0F)
                // 右赤金神角
                .texOffs(12, 20).addBox(2.5F, -12.0F, -5.0F, 2.0F, 8.0F, 3.0F),
                PartPose.offset(0.0F, 11.0F, -16.0F));

        // 悬浮衔烛九阳炎精神珠 (Floating Blazing Sun Orb)
        PartDefinition orbOuter = head.addOrReplaceChild("sun_orb_outer", CubeListBuilder.create()
                .texOffs(64, 20).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, -14.0F, -11.0F));

        orbOuter.addOrReplaceChild("sun_orb_inner", CubeListBuilder.create()
                .texOffs(88, 20).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // 烈阳龙须
        head.addOrReplaceChild("whisker_l", CubeListBuilder.create()
                .texOffs(24, 20).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 12.0F),
                PartPose.offset(-3.5F, 1.0F, -16.0F));

        head.addOrReplaceChild("whisker_r", CubeListBuilder.create()
                .texOffs(38, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 12.0F),
                PartPose.offset(3.5F, 1.0F, -16.0F));

        // 2. 龙颈 (Neck)
        PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create()
                .texOffs(0, 34).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 12.0F)
                // 烈阳火鬃
                .texOffs(40, 34).addBox(-0.5F, -7.5F, 0.0F, 1.0F, 4.0F, 12.0F),
                PartPose.offset(0.0F, 11.5F, -15.0F));

        // 3. 龙躯第一节 (Body 1)
        PartDefinition body1 = neck.addOrReplaceChild("body1", CubeListBuilder.create()
                .texOffs(0, 52).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 14.0F)
                .texOffs(44, 52).addBox(-0.5F, -7.5F, 0.0F, 1.0F, 4.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 11.5F));

        // 4. 龙躯第二节 (Body 2)
        PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create()
                .texOffs(0, 72).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 14.0F)
                .texOffs(42, 72).addBox(-0.5F, -7.0F, 0.0F, 1.0F, 4.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 13.5F));

        // 5. 龙躯第三节 (Body 3)
        PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create()
                .texOffs(0, 92).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 14.0F)
                .texOffs(42, 92).addBox(-0.5F, -7.0F, 0.0F, 1.0F, 4.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 13.5F));

        // 6. 龙躯第四节 (Body 4)
        PartDefinition body4 = body3.addOrReplaceChild("body4", CubeListBuilder.create()
                .texOffs(60, 52).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 14.0F)
                .texOffs(100, 52).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 3.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 13.5F));

        // 7. 游龙尾部 (Tail)
        PartDefinition tail = body4.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(60, 72).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 13.5F));

        // 8. 烈阳极光尾羽 (Tail Flame)
        tail.addOrReplaceChild("tail_flame", CubeListBuilder.create()
                .texOffs(76, 90).addBox(-0.5F, -8.0F, 6.0F, 1.0F, 16.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks * 0.10F;

        // 龙头威严转向
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.6F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.6F + Mth.sin(age + 0.6F) * 0.10F;

        // 悬浮九阳神珠高频自转与炎阳起伏
        this.sunOrbOuter.yRot = age * 1.5F;
        this.sunOrbInner.yRot = -age * 2.5F;
        this.sunOrbOuter.xRot = Mth.sin(age * 0.8F) * 0.2F;
        this.sunOrbOuter.y = -14.0F + Mth.sin(age * 1.6F) * 1.8F;

        // 龙须律动
        this.whiskerL.yRot = -0.2F + Mth.sin(age * 1.3F) * 0.25F;
        this.whiskerR.yRot = 0.2F - Mth.sin(age * 1.3F) * 0.25F;
        this.whiskerL.zRot = Mth.cos(age * 1.0F) * 0.15F;
        this.whiskerR.zRot = -Mth.cos(age * 1.0F) * 0.15F;

        // 逐节层级波动蜿蜒游弋（呈现上古烛龙如巨蟒腾霄的连绵长躯）
        this.neck.yRot = Mth.sin(age) * 0.17F;
        this.body1.yRot = Mth.sin(age - 0.5F) * 0.21F;
        this.body2.yRot = Mth.sin(age - 1.0F) * 0.25F;
        this.body3.yRot = Mth.sin(age - 1.5F) * 0.29F;
        this.body4.yRot = Mth.sin(age - 2.0F) * 0.33F;
        this.tail.yRot = Mth.sin(age - 2.5F) * 0.37F;
        this.tailFlame.yRot = Mth.sin(age - 3.0F) * 0.42F;

        // 纵向波浪律动
        this.neck.xRot = Mth.cos(age * 0.7F) * 0.05F;
        this.body1.xRot = Mth.cos(age * 0.7F - 0.4F) * 0.07F;
        this.body2.xRot = Mth.cos(age * 0.7F - 0.8F) * 0.09F;
        this.body3.xRot = Mth.cos(age * 0.7F - 1.2F) * 0.11F;
        this.body4.xRot = Mth.cos(age * 0.7F - 1.6F) * 0.13F;
        this.tail.xRot = Mth.cos(age * 0.7F - 2.0F) * 0.15F;
    }
}
