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
 * 兜率丹火灵精模型 (Tushita Dan Fire Sprite Model)
 * 太上老君八卦炼丹炉中溢出的通灵三昧真火与纯阳丹气凝聚之灵精。
 * 构筑多层灵性解剖体系架构（Anatomical Sacred Elemental Architecture）：
 * - 内层通灵金丹核心 (圆融纯阳灵核 + 纯阳丹气护壁，自转与心跳般呼吸律动)
 * - 内焰升腾火尖与垂华 (天地贯通纯阳真火火尖与丹露垂华)
 * - 外层八卦玄火轨环 (四方轨段 + 卦象枢纽，搭载四枚公转并轻微翻卷的三昧真火/青冥文火灵瓣)
 * - 最外围倾斜漂浮旋转的阴阳丹道符环/四极星芒浮片 (反向慢转立体丹道天象)
 */
public class DanFireSpriteModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("dan_fire_sprite"), "main");

    private final ModelPart root;
    private final ModelPart core;
    private final ModelPart flameInnerTop;
    private final ModelPart flameInnerBottom;
    private final ModelPart outerOrbitRing;
    private final ModelPart flameWisp1;
    private final ModelPart flameWisp2;
    private final ModelPart flameWisp3;
    private final ModelPart flameWisp4;
    private final ModelPart haloRunes;

    public DanFireSpriteModel(ModelPart root) {
        super(root);
        this.root = root;
        this.core = root.getChild("core");
        this.flameInnerTop = this.core.getChild("flame_inner_top");
        this.flameInnerBottom = this.core.getChild("flame_inner_bottom");
        this.outerOrbitRing = this.core.getChild("outer_orbit_ring");
        this.flameWisp1 = this.outerOrbitRing.getChild("flame_wisp_1");
        this.flameWisp2 = this.outerOrbitRing.getChild("flame_wisp_2");
        this.flameWisp3 = this.outerOrbitRing.getChild("flame_wisp_3");
        this.flameWisp4 = this.outerOrbitRing.getChild("flame_wisp_4");
        this.haloRunes = this.core.getChild("halo_runes");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 金丹灵核与纯阳护壁 (Inner Sacred Golden Dan Core & Yang Aura Shell)
        PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create()
                // 圆融纯阳灵核 (Golden Dan Nucleus) - 4x4x4
                .texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F)
                // 纯阳丹气护壁 (Pure Yang Aura Shell) - 5x5x5
                .texOffs(0, 9).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F),
                PartPose.offset(0.0F, 16.0F, 0.0F));

        // 2. 内焰升腾火尖 (Ascending Inner Flame Spire)
        core.addOrReplaceChild("flame_inner_top", CubeListBuilder.create()
                // 升腾主焰 (Spire Flame) - 3x5x3
                .texOffs(0, 20).addBox(-1.5F, -7.0F, -1.5F, 3.0F, 5.0F, 3.0F)
                // 极火尖晶 (Spire Tip) - 1x3x1
                .texOffs(13, 20).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 3.0F, 1.0F),
                PartPose.ZERO);

        // 3. 内焰垂华 (Cascading Inner Flame Droop)
        core.addOrReplaceChild("flame_inner_bottom", CubeListBuilder.create()
                // 垂华焰基 (Droop Base) - 3x4x3
                .texOffs(0, 29).addBox(-1.5F, 2.0F, -1.5F, 3.0F, 4.0F, 3.0F)
                // 流霞火滴 (Droop Tip) - 1x3x1
                .texOffs(13, 29).addBox(-0.5F, 6.0F, -0.5F, 1.0F, 3.0F, 1.0F),
                PartPose.ZERO);

        // 4. 外层八卦玄火轨环 (Bagua Celestial Fire Orbit Ring)
        PartDefinition outerOrbitRing = core.addOrReplaceChild("outer_orbit_ring", CubeListBuilder.create()
                // 北方玄火轨段 (North Rail) - 8x1x1
                .texOffs(22, 0).addBox(-4.0F, -0.5F, -7.5F, 8.0F, 1.0F, 1.0F)
                // 南方玄火轨段 (South Rail) - 8x1x1
                .texOffs(22, 3).addBox(-4.0F, -0.5F, 6.5F, 8.0F, 1.0F, 1.0F)
                // 东方玄火轨段 (East Rail) - 1x1x6
                .texOffs(42, 0).addBox(6.5F, -0.5F, -3.0F, 1.0F, 1.0F, 6.0F)
                // 西方玄火轨段 (West Rail) - 1x1x6
                .texOffs(42, 8).addBox(-7.5F, -0.5F, -3.0F, 1.0F, 1.0F, 6.0F)
                // 东北卦位枢纽 (NE Node) - 2x2x2
                .texOffs(22, 6).addBox(4.5F, -1.0F, -6.5F, 2.0F, 2.0F, 2.0F)
                // 西北卦位枢纽 (NW Node) - 2x2x2
                .texOffs(31, 6).addBox(-6.5F, -1.0F, -6.5F, 2.0F, 2.0F, 2.0F)
                // 东南卦位枢纽 (SE Node) - 2x2x2
                .texOffs(22, 11).addBox(4.5F, -1.0F, 4.5F, 2.0F, 2.0F, 2.0F)
                // 西南卦位枢纽 (SW Node) - 2x2x2
                .texOffs(31, 11).addBox(-6.5F, -1.0F, 4.5F, 2.0F, 2.0F, 2.0F),
                PartPose.ZERO);

        // 5. 四枚三昧真火符焰/莲瓣 (Four Samadhi True Fire & Wen Fire Petals)
        // flame_wisp_1 (赤金真火灵瓣 1 - North)
        outerOrbitRing.addOrReplaceChild("flame_wisp_1", CubeListBuilder.create()
                .texOffs(0, 38).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F)
                .texOffs(11, 38).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 3.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, -7.5F));

        // flame_wisp_2 (赤金真火灵瓣 2 - South)
        outerOrbitRing.addOrReplaceChild("flame_wisp_2", CubeListBuilder.create()
                .texOffs(0, 45).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F)
                .texOffs(11, 45).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 7.5F, 0.0F, Mth.PI, 0.0F));

        // flame_wisp_3 (青冥文火灵瓣 3 - East)
        outerOrbitRing.addOrReplaceChild("flame_wisp_3", CubeListBuilder.create()
                .texOffs(18, 38).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F)
                .texOffs(29, 38).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -Mth.HALF_PI, 0.0F));

        // flame_wisp_4 (青冥文火灵瓣 4 - West)
        outerOrbitRing.addOrReplaceChild("flame_wisp_4", CubeListBuilder.create()
                .texOffs(18, 45).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F)
                .texOffs(29, 45).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, Mth.HALF_PI, 0.0F));

        // 6. 最外围倾斜漂浮旋转的阴阳丹道符环/星芒浮片 (Outer Tilted Yin-Yang Rune Halo)
        core.addOrReplaceChild("halo_runes", CubeListBuilder.create()
                // 乾象丹道符片 (Qian / Heaven Rune Tablet) - 3x4x1
                .texOffs(0, 53).addBox(7.0F, -2.0F, -7.0F, 3.0F, 4.0F, 1.0F)
                // 坤象丹道符片 (Kun / Earth Rune Tablet) - 3x4x1
                .texOffs(9, 53).addBox(7.0F, -2.0F, 6.0F, 3.0F, 4.0F, 1.0F)
                // 坎象丹道符片 (Kan / Water Rune Tablet) - 3x4x1
                .texOffs(18, 53).addBox(-10.0F, -2.0F, 6.0F, 3.0F, 4.0F, 1.0F)
                // 离象丹道符片 (Li / Fire Rune Tablet) - 3x4x1
                .texOffs(27, 53).addBox(-10.0F, -2.0F, -7.0F, 3.0F, 4.0F, 1.0F)
                // 四极玄火星芒浮片 (Floating Astral Shards) - 1x3x1
                .texOffs(36, 53).addBox(-0.5F, -1.5F, -11.5F, 1.0F, 3.0F, 1.0F)
                .texOffs(41, 53).addBox(-0.5F, -1.5F, 10.5F, 1.0F, 3.0F, 1.0F)
                .texOffs(46, 53).addBox(10.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F)
                .texOffs(51, 53).addBox(-11.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.1745F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float age = state.ageInTicks;

        // 1. 无足悬浮灵体在空中呈正弦波浮沉 (Sacred Elemental Bobbing & Atmospheric Drift)
        float bob = Mth.sin(age * 0.08F) * 1.8F;
        this.core.y = 16.0F + bob;
        this.core.xRot = Mth.sin(age * 0.05F) * 0.05F;
        this.core.zRot = Mth.cos(age * 0.06F) * 0.05F;

        // 2. 内层通灵金丹核心自转与心跳般微弱膨胀律动 (Pulsating Yang Core Breath & Rotation)
        float coreRot = age * 0.05F;
        this.core.yRot = coreRot;
        float pulse = 1.0F + Mth.sin(age * 0.12F) * 0.06F;
        this.core.xScale = pulse;
        this.core.yScale = pulse;
        this.core.zScale = pulse;

        // 内焰火尖与垂华随气流生生不息微幅摇曳 (Inner Flame Spire & Droop Dynamics)
        this.flameInnerTop.yRot = -coreRot * 0.4F + Mth.sin(age * 0.15F) * 0.12F;
        this.flameInnerTop.zRot = Mth.cos(age * 0.12F) * 0.08F;
        this.flameInnerBottom.yRot = coreRot * 0.3F;
        this.flameInnerBottom.zRot = -Mth.sin(age * 0.1F) * 0.08F;

        // 3. 外层八卦玄火轨环公转与四枚三昧真火灵瓣随风翻卷 (Orbital Revolution & Petal Flutter)
        float orbitAngle = age * 0.08F;
        this.outerOrbitRing.yRot = orbitAngle - coreRot;
        this.outerOrbitRing.xRot = Mth.sin(age * 0.06F) * 0.07F;
        this.outerOrbitRing.zRot = Mth.cos(age * 0.07F) * 0.07F;

        // 四瓣真火随风翻卷倾角 (Wind Flap & Flame Flutter for each wisp)
        float flutter1 = Mth.sin(age * 0.20F);
        float flutter2 = Mth.sin(age * 0.20F + Mth.PI);
        float flutter3 = Mth.sin(age * 0.20F + Mth.HALF_PI);
        float flutter4 = Mth.sin(age * 0.20F - Mth.HALF_PI);

        this.flameWisp1.xRot = 0.12F + flutter1 * 0.14F;
        this.flameWisp1.zRot = Mth.cos(age * 0.18F) * 0.10F;

        this.flameWisp2.xRot = 0.12F + flutter2 * 0.14F;
        this.flameWisp2.zRot = Mth.cos(age * 0.18F + Mth.PI) * 0.10F;

        this.flameWisp3.xRot = 0.12F + flutter3 * 0.14F;
        this.flameWisp3.zRot = Mth.cos(age * 0.18F + Mth.HALF_PI) * 0.10F;

        this.flameWisp4.xRot = 0.12F + flutter4 * 0.14F;
        this.flameWisp4.zRot = Mth.cos(age * 0.18F - Mth.HALF_PI) * 0.10F;

        // 4. 最外围阴阳丹道符环按反方向慢速旋转 (Tilted Counter-Rotating Rune Halo)
        float runeRev = -age * 0.03F;
        this.haloRunes.yRot = runeRev - coreRot;
        this.haloRunes.xRot = 0.2618F + Mth.sin(age * 0.04F) * 0.05F;
        this.haloRunes.zRot = 0.1745F + Mth.cos(age * 0.03F) * 0.05F;
    }
}
