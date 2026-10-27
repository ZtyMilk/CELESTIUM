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
 * 青鸾神鸟 (Azure Bird / Queen Mother's Messenger)
 * 依照"神兽构思师"树状解剖体系架构设计：
 * - 灵禽仙首 (Head)：流线型仙禽头颅、修长尖勾金喙、三道冲天翠羽凤翎冠
 * - 胸腹翠羽 (Body & Plumage)：挺秀丰盈胸脯、层叠背羽披肩、轻灵流线腹羽与尾基覆羽
 * - 三段式舒展大翼 (Wings)：翼根骨骼大臂、中段次级飞羽、翼梢初级长翎伸展飞羽
 * - 三道青鸾尾羽 (Tail Streamers)：中央超长流云长翎与金翠雀眼翎斑、左右弧曲飘逸尾翎
 * - 灵动金爪 (Legs & Claws)：羽裤腿羽、纤细金胫、苍劲四趾抓地抓枝金爪
 */
public class AzureBirdModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("azure_bird"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart crestCenter;
    private final ModelPart crestLeft;
    private final ModelPart crestRight;

    // 三段式舒展大翼 (Three-part wings)
    private final ModelPart leftWing;
    private final ModelPart leftWingMid;
    private final ModelPart leftWingOuter;

    private final ModelPart rightWing;
    private final ModelPart rightWingMid;
    private final ModelPart rightWingOuter;

    // 三道青鸾尾羽 (Three flowing tail streamers)
    private final ModelPart tailCenter;
    private final ModelPart tailCenterTip;
    private final ModelPart tailLeft;
    private final ModelPart tailRight;

    // 灵动金爪 (Legs & Talons)
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public AzureBirdModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.crestCenter = this.head.getChild("crest_center");
        this.crestLeft = this.head.getChild("crest_left");
        this.crestRight = this.head.getChild("crest_right");

        this.leftWing = this.body.getChild("left_wing");
        this.leftWingMid = this.leftWing.getChild("left_wing_mid");
        this.leftWingOuter = this.leftWingMid.getChild("left_wing_outer");

        this.rightWing = this.body.getChild("right_wing");
        this.rightWingMid = this.rightWing.getChild("right_wing_mid");
        this.rightWingOuter = this.rightWingMid.getChild("right_wing_outer");

        this.tailCenter = this.body.getChild("tail_center");
        this.tailCenterTip = this.tailCenter.getChild("tail_center_tip");
        this.tailLeft = this.body.getChild("tail_left");
        this.tailRight = this.body.getChild("tail_right");

        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. 灵禽主躯干与层叠翠羽 (Body & Plumage)
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 青鸾主躯干核心
                .texOffs(0, 0).addBox(-3.5F, -3.5F, -5.5F, 7.0F, 7.0F, 11.0F)
                // 挺秀丰盈胸前翠羽
                .texOffs(38, 0).addBox(-3.0F, -2.5F, -7.5F, 6.0F, 6.0F, 3.0F)
                // 背羽锦帔披肩
                .texOffs(0, 20).addBox(-3.0F, -4.5F, -4.5F, 6.0F, 2.0F, 9.0F)
                // 腹下轻灵流线羽
                .texOffs(38, 10).addBox(-2.5F, 3.0F, -4.0F, 5.0F, 2.0F, 8.0F)
                // 尾羽基座覆羽
                .texOffs(38, 22).addBox(-2.5F, -2.5F, 5.0F, 5.0F, 3.0F, 3.0F),
                PartPose.offset(0.0F, 13.0F, 0.0F));

        // 2. 灵禽仙首与三道冲天翠羽凤翎冠 (Head, Beak, Neck & Triple Feather Crests)
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create()
                // 灵禽仙颈
                .texOffs(64, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F)
                // 颔下喉羽仙襟
                .texOffs(82, 0).addBox(-1.5F, -4.5F, -3.0F, 3.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, -2.0F, -5.0F, 0.20F, 0.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create()
                // 灵秀仙首颅骨
                .texOffs(64, 12).addBox(-2.5F, -3.5F, -3.5F, 5.0F, 5.0F, 5.0F)
                // 额冠翠顶
                .texOffs(86, 9).addBox(-2.0F, -4.5F, -3.0F, 4.0F, 2.0F, 4.0F)
                // 修长金喙基座
                .texOffs(94, 0).addBox(-1.0F, -1.5F, -7.5F, 2.0F, 2.0F, 4.0F)
                // 金喙微勾尖端
                .texOffs(108, 0).addBox(-0.5F, -0.8F, -9.5F, 1.0F, 2.0F, 2.0F)
                // 金喙下颚羽
                .texOffs(116, 0).addBox(-0.8F, 0.2F, -6.5F, 2.0F, 1.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -5.5F, -0.5F, -0.10F, 0.0F, 0.0F));

        // 中央冲天凤翎冠 (Central tall crest plume)
        head.addOrReplaceChild("crest_center", CubeListBuilder.create()
                .texOffs(104, 8).addBox(-0.5F, -6.0F, -1.0F, 1.0F, 6.0F, 3.0F)
                .texOffs(112, 4).addBox(-0.5F, -14.0F, 0.0F, 1.0F, 8.0F, 5.0F)
                .texOffs(64, 22).addBox(-0.5F, -18.0F, 1.5F, 1.0F, 4.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, -3.5F, -1.0F, -0.35F, 0.0F, 0.0F));

        // 左侧偏冠翎 (Left crest plume)
        head.addOrReplaceChild("crest_left", CubeListBuilder.create()
                .texOffs(88, 18).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 10.0F, 3.0F)
                .texOffs(98, 20).addBox(-0.5F, -14.0F, 0.5F, 1.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(1.2F, -3.2F, -0.5F, -0.25F, 0.20F, 0.30F));

        // 右侧偏冠翎 (Right crest plume)
        head.addOrReplaceChild("crest_right", CubeListBuilder.create()
                .texOffs(106, 18).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 10.0F, 3.0F)
                .texOffs(116, 20).addBox(-0.5F, -14.0F, 0.5F, 1.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(-1.2F, -3.2F, -0.5F, -0.25F, -0.20F, -0.30F));

        // 3. 三段式舒展大翼 (Three-Part Articulated Wings)
        // 左翼 (Left Wing)
        PartDefinition leftWingRoot = body.addOrReplaceChild("left_wing", CubeListBuilder.create()
                // 翼根大臂骨骼基座
                .texOffs(0, 34).addBox(0.0F, -1.0F, -2.5F, 6.0F, 3.0F, 7.0F)
                // 肩部覆羽披饰
                .texOffs(28, 34).addBox(0.0F, -1.5F, -2.0F, 5.0F, 1.0F, 6.0F),
                PartPose.offset(3.5F, -1.5F, -3.5F));

        PartDefinition leftWingMid = leftWingRoot.addOrReplaceChild("left_wing_mid", CubeListBuilder.create()
                // 次级飞羽主羽片
                .texOffs(0, 46).addBox(0.0F, -0.8F, -2.0F, 7.0F, 2.0F, 8.0F)
                // 次级覆羽层叠片
                .texOffs(32, 44).addBox(0.0F, -0.4F, 4.0F, 6.0F, 1.0F, 4.0F),
                PartPose.offset(6.0F, 0.0F, 0.0F));

        leftWingMid.addOrReplaceChild("left_wing_outer", CubeListBuilder.create()
                // 翼梢初级飞羽长翎
                .texOffs(0, 58).addBox(0.0F, -0.5F, -1.5F, 8.0F, 1.0F, 9.0F)
                // 翼尖修长外扬飞羽梢
                .texOffs(36, 52).addBox(6.0F, -0.3F, 1.5F, 6.0F, 1.0F, 8.0F),
                PartPose.offset(7.0F, 0.0F, 0.0F));

        // 右翼 (Right Wing - Mirrored)
        PartDefinition rightWingRoot = body.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(64, 34).addBox(-6.0F, -1.0F, -2.5F, 6.0F, 3.0F, 7.0F, true)
                .texOffs(92, 34).addBox(-5.0F, -1.5F, -2.0F, 5.0F, 1.0F, 6.0F, true),
                PartPose.offset(-3.5F, -1.5F, -3.5F));

        PartDefinition rightWingMid = rightWingRoot.addOrReplaceChild("right_wing_mid", CubeListBuilder.create()
                .texOffs(64, 46).addBox(-7.0F, -0.8F, -2.0F, 7.0F, 2.0F, 8.0F, true)
                .texOffs(96, 44).addBox(-6.0F, -0.4F, 4.0F, 6.0F, 1.0F, 4.0F, true),
                PartPose.offset(-6.0F, 0.0F, 0.0F));

        rightWingMid.addOrReplaceChild("right_wing_outer", CubeListBuilder.create()
                .texOffs(64, 58).addBox(-8.0F, -0.5F, -1.5F, 8.0F, 1.0F, 9.0F, true)
                .texOffs(100, 52).addBox(-12.0F, -0.3F, 1.5F, 6.0F, 1.0F, 8.0F, true),
                PartPose.offset(-7.0F, 0.0F, 0.0F));

        // 4. 三道青鸾尾羽 (Three Celestial Flowing Tail Streamers)
        // 中央超长主翎与雀眼羽斑 (Central streamer & ocellus eye)
        PartDefinition tailCenter = body.addOrReplaceChild("tail_center", CubeListBuilder.create()
                // 尾羽近基主翎轴
                .texOffs(0, 70).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 16.0F),
                PartPose.offsetAndRotation(0.0F, -1.0F, 5.5F, 0.18F, 0.0F, 0.0F));

        tailCenter.addOrReplaceChild("tail_center_tip", CubeListBuilder.create()
                // 远端羽翎延展
                .texOffs(40, 64).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 10.0F)
                // 华美金翠青鸾雀眼羽扇 (Ocellus feather eye fan)
                .texOffs(0, 89).addBox(-3.5F, -0.4F, 8.0F, 7.0F, 1.0F, 8.0F)
                // 雀眼金耀眼心 (Inner jewel eye)
                .texOffs(32, 89).addBox(-1.5F, -0.6F, 10.5F, 3.0F, 1.0F, 4.0F)
                // 雀眼后飘流苏翎梢 (Trailing tassels)
                .texOffs(40, 76).addBox(-2.5F, -0.3F, 16.0F, 5.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 16.0F, 0.12F, 0.0F, 0.0F));

        // 左侧弧曲流云尾翎 (Left curved streamer plume)
        body.addOrReplaceChild("tail_left", CubeListBuilder.create()
                .texOffs(64, 70).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 16.0F)
                .texOffs(102, 70).addBox(-0.5F, -0.4F, 8.0F, 3.0F, 1.0F, 8.0F)
                .texOffs(102, 81).addBox(0.5F, -0.3F, 15.0F, 2.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(1.8F, -0.8F, 5.0F, 0.15F, 0.18F, 0.10F));

        // 右侧弧曲流云尾翎 (Right curved streamer plume - Mirrored)
        body.addOrReplaceChild("tail_right", CubeListBuilder.create()
                .texOffs(64, 89).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 16.0F, true)
                .texOffs(102, 90).addBox(-2.5F, -0.4F, 8.0F, 3.0F, 1.0F, 8.0F, true)
                .texOffs(102, 101).addBox(-2.5F, -0.3F, 15.0F, 2.0F, 1.0F, 6.0F, true),
                PartPose.offsetAndRotation(-1.8F, -0.8F, 5.0F, 0.15F, -0.18F, -0.10F));

        // 5. 灵动金爪 (Legs & Golden Talons)
        // 左足 (Left Leg)
        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                // 大腿蓬松翠羽 (Thigh feathers)
                .texOffs(0, 100).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F)
                // 细长金胫 (Slender leg)
                .texOffs(16, 100).addBox(-0.5F, 3.5F, -0.5F, 1.0F, 5.0F, 1.0F)
                // 抓地金爪掌骨 (Foot knuckle)
                .texOffs(22, 100).addBox(-1.0F, 7.2F, -1.0F, 2.0F, 1.0F, 2.0F)
                // 前中趾金爪 (Front middle talon)
                .texOffs(0, 110).addBox(-0.5F, 7.2F, -3.5F, 1.0F, 1.0F, 3.0F)
                // 前内趾金爪 (Front inner talon)
                .texOffs(10, 110).addBox(0.4F, 7.2F, -3.0F, 1.0F, 1.0F, 3.0F)
                // 前外趾金爪 (Front outer talon)
                .texOffs(20, 110).addBox(-1.4F, 7.2F, -3.0F, 1.0F, 1.0F, 3.0F)
                // 后趾抓枝爪 (Rear hallux claw)
                .texOffs(30, 110).addBox(-0.5F, 7.2F, 0.8F, 1.0F, 1.0F, 3.0F),
                PartPose.offset(2.0F, 16.0F, 1.0F));

        // 右足 (Right Leg - Mirrored)
        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(64, 110).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F, true)
                .texOffs(80, 110).addBox(-0.5F, 3.5F, -0.5F, 1.0F, 5.0F, 1.0F, true)
                .texOffs(86, 110).addBox(-1.0F, 7.2F, -1.0F, 2.0F, 1.0F, 2.0F, true)
                .texOffs(64, 120).addBox(-0.5F, 7.2F, -3.5F, 1.0F, 1.0F, 3.0F, true)
                .texOffs(74, 120).addBox(-1.4F, 7.2F, -3.0F, 1.0F, 1.0F, 3.0F, true)
                .texOffs(84, 120).addBox(0.4F, 7.2F, -3.0F, 1.0F, 1.0F, 3.0F, true)
                .texOffs(94, 120).addBox(-0.5F, 7.2F, 0.8F, 1.0F, 1.0F, 3.0F, true),
                PartPose.offset(-2.0F, 16.0F, 1.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkPos = state.walkAnimationPos;
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);

        // 1. 灵禽仙首朝向追踪与灵动寻觅点啄律动 (Head tracking & Avian Bobbing)
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.75F;
        this.head.yRot = state.yRot * ((float) Math.PI / 180F) * 0.75F;

        if (walkSpeed > 0.05F) {
            // 行走涉水时的灵动探头点头律动
            float headBob = Mth.cos(walkPos * 1.2F) * 0.18F * walkSpeed;
            this.head.xRot += headBob;
            this.neck.xRot = 0.20F + headBob * 0.6F;
            this.body.y = 13.0F + Mth.sin(walkPos * 1.2F) * 0.5F * walkSpeed;
        } else {
            // 闲置静立/悬停时的玄微呼吸律动与轻灵微顾
            float idleCycle = age * 0.08F;
            float idleBob = Mth.sin(idleCycle) * 0.05F;
            this.neck.xRot = 0.20F + idleBob;
            this.head.xRot += idleBob * 0.5F;
            this.head.zRot = Mth.sin(idleCycle * 0.6F) * 0.04F; // 灵鸟歪头微顾
            this.body.y = 13.0F + Mth.sin(idleCycle) * 0.6F; // 祥瑞灵气轻悬起伏
        }

        // 三道冲天翠羽凤翎冠随微风与身姿轻盈拂动
        float crestWave = Mth.sin(age * 0.12F) * 0.08F;
        this.crestCenter.xRot = -0.35F + crestWave;
        this.crestLeft.zRot = 0.30F + crestWave * 0.6F;
        this.crestLeft.xRot = -0.25F + Mth.cos(age * 0.10F) * 0.06F;
        this.crestRight.zRot = -0.30F - crestWave * 0.6F;
        this.crestRight.xRot = -0.25F + Mth.cos(age * 0.10F) * 0.06F;

        // 2. 三段式舒展大翼：多关节振翅动力学传递 (Wing flapping cycle with multi-joint bending)
        float flapTimer = age * 0.35F;
        float flapRoot = Mth.sin(flapTimer) * 0.42F;
        // 中段翼部滞后相位传递
        float flapMid = Mth.sin(flapTimer - 0.45F) * 0.32F;
        // 翼梢初级长飞羽气动滞后与波浪翻折
        float flapOuter = Mth.sin(flapTimer - 0.90F) * 0.38F;
        float flapTwist = Mth.cos(flapTimer) * 0.14F;

        // 左翼三段递进弯曲
        this.leftWing.zRot = 0.15F + flapRoot;
        this.leftWing.xRot = flapTwist;
        this.leftWingMid.zRot = 0.22F + flapMid;
        this.leftWingMid.xRot = flapTwist * 0.6F;
        this.leftWingOuter.zRot = 0.28F + flapOuter;
        this.leftWingOuter.yRot = -flapTwist * 0.8F;

        // 右翼三段对称递进弯曲
        this.rightWing.zRot = -0.15F - flapRoot;
        this.rightWing.xRot = flapTwist;
        this.rightWingMid.zRot = -0.22F - flapMid;
        this.rightWingMid.xRot = flapTwist * 0.6F;
        this.rightWingOuter.zRot = -0.28F - flapOuter;
        this.rightWingOuter.yRot = flapTwist * 0.8F;

        // 3. 三道青鸾尾羽流光波浪翻卷律动 (Tail streamers undulating in flight/standing)
        float tailWave = age * 0.10F;

        // 中央长翎与华美雀眼：纵向波浪翻腾与横向随风摆曳
        this.tailCenter.xRot = 0.20F + Mth.sin(tailWave) * 0.12F;
        this.tailCenter.yRot = Mth.cos(tailWave * 0.75F) * 0.08F;
        this.tailCenterTip.xRot = 0.12F + Mth.sin(tailWave - 0.7F) * 0.16F;
        this.tailCenterTip.yRot = Mth.cos(tailWave * 0.75F - 0.4F) * 0.10F;

        // 左侧弧曲流云尾翎：向外舒展并带有相位差波动
        this.tailLeft.xRot = 0.16F + Mth.sin(tailWave + 0.6F) * 0.10F;
        this.tailLeft.yRot = 0.18F + Mth.cos(tailWave * 0.8F + 0.3F) * 0.08F;
        this.tailLeft.zRot = 0.10F + Mth.sin(tailWave * 0.6F) * 0.06F;

        // 右侧弧曲流云尾翎：镜像舒展波动
        this.tailRight.xRot = 0.16F + Mth.sin(tailWave + 1.2F) * 0.10F;
        this.tailRight.yRot = -0.18F - Mth.cos(tailWave * 0.8F + 0.6F) * 0.08F;
        this.tailRight.zRot = -0.10F - Mth.sin(tailWave * 0.6F) * 0.06F;

        // 4. 金爪双足：地面迈步步伐与飞行凌空自然收拢 (Legs & Claws animation)
        if (walkSpeed > 0.05F) {
            // 地面迈步交替
            this.leftLeg.xRot = Mth.cos(walkPos * 0.6662F) * 0.85F * walkSpeed;
            this.rightLeg.xRot = Mth.cos(walkPos * 0.6662F + (float) Math.PI) * 0.85F * walkSpeed;
        } else {
            // 凌空翱翔仙禽后掠金爪姿态
            float legFlightCurl = 0.55F + Mth.sin(age * 0.06F) * 0.04F;
            this.leftLeg.xRot = legFlightCurl;
            this.rightLeg.xRot = legFlightCurl;
        }
    }
}
