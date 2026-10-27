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
 * 瑶池玄天灵鲤 (Spirit Carp - 瑶池通灵瑞鳞 / 具化龙之姿)
 * <p>
 * 解剖学树状铰接层级 (Anatomical Tree Architecture):
 * root
 *  └── body (主躯干与丰润鱼胸，背部拱起优美弧线)
 *       ├── head (灵动鱼头，圆滑吻部，含珠微张鱼唇)
 *       │    ├── whisker_left (飘逸仙家金龙须，随水流向后轻柔摆动)
 *       │    └── whisker_right (对称金龙须)
 *       ├── dorsal_fin (背脊飘逸透明仙鳍，如同丝绢)
 *       ├── fin_pectoral_left / fin_pectoral_right (左右宽阔胸鳍，如蝉翼般划水)
 *       ├── fin_pelvic_left / fin_pelvic_right (左右腹鳍)
 *       └── tail_segment_1 (中后段鱼身，向尾部逐渐收窄)
 *            └── tail_segment_2 (尾柄)
 *                 └── tail_fin (如纱似幕的四散广展大尾鳍，分为上下多片与大扇形)
 */
public class SpiritCarpModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("spirit_carp"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart whiskerLeft;
    private final ModelPart whiskerRight;
    private final ModelPart dorsalFin;
    private final ModelPart finPectoralLeft;
    private final ModelPart finPectoralRight;
    private final ModelPart finPelvicLeft;
    private final ModelPart finPelvicRight;
    private final ModelPart tailSegment1;
    private final ModelPart tailSegment2;
    private final ModelPart tailFin;

    public SpiritCarpModel(ModelPart root) {
        super(root);
        this.root = root;
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.whiskerLeft = this.head.getChild("whisker_left");
        this.whiskerRight = this.head.getChild("whisker_right");
        this.dorsalFin = this.body.getChild("dorsal_fin");
        this.finPectoralLeft = this.body.getChild("fin_pectoral_left");
        this.finPectoralRight = this.body.getChild("fin_pectoral_right");
        this.finPelvicLeft = this.body.getChild("fin_pelvic_left");
        this.finPelvicRight = this.body.getChild("fin_pelvic_right");
        this.tailSegment1 = this.body.getChild("tail_segment_1");
        this.tailSegment2 = this.tailSegment1.getChild("tail_segment_2");
        this.tailFin = this.tailSegment2.getChild("tail_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // 1. body: 主躯干与丰润鱼胸，背部拱起优美弧线 (UV bounds: [0..42, 0..14])
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                // 主躯干主腔 (Torso Core: dx=4, dy=5, dz=8)
                .texOffs(0, 0).addBox(-2.0F, -2.5F, -4.0F, 4.0F, 5.0F, 8.0F)
                // 背脊拱弧隆起 (Dorsal Arch Ridge: dx=3, dy=1, dz=6 - 锦鲤丰满隆背)
                .texOffs(24, 0).addBox(-1.5F, -3.5F, -3.0F, 3.0F, 1.0F, 6.0F)
                // 丰满鱼腹弧度 (Full Ventral Curve: dx=3, dy=1, dz=6)
                .texOffs(24, 7).addBox(-1.5F, 2.5F, -3.0F, 3.0F, 1.0F, 6.0F),
                PartPose.offset(0.0F, 19.5F, 0.0F));

        // 2. head: 灵动鱼头，圆滑吻部，含珠微张鱼唇 (UV bounds: [0..22, 14..22])
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                // 鱼头主颅 (Cranium: dx=3, dy=4, dz=4)
                .texOffs(0, 14).addBox(-1.5F, -2.0F, -4.0F, 3.0F, 4.0F, 4.0F)
                // 圆滑吻部与微张鱼唇 (Snout & Lips: dx=2, dy=3, dz=2)
                .texOffs(14, 14).addBox(-1.0F, -1.5F, -6.0F, 2.0F, 3.0F, 2.0F)
                // 混元含珠 (Spiritual Dragon Pearl: dx=1, dy=1, dz=1)
                .texOffs(14, 19).addBox(-0.5F, -0.5F, -6.8F, 1.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, -4.0F));

        // 2.1 whisker_left: 飘逸仙家金龙须 (UV: [44..58, 0..7])
        head.addOrReplaceChild("whisker_left", CubeListBuilder.create()
                .texOffs(44, 0).addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(0.9F, 0.5F, -4.8F, 0.15F, -0.22F, -0.10F));

        // 2.2 whisker_right: 对称金龙须 (UV: [44..58, 7..14])
        head.addOrReplaceChild("whisker_right", CubeListBuilder.create()
                .texOffs(44, 7).addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 6.0F),
                PartPose.offsetAndRotation(-0.9F, 0.5F, -4.8F, 0.15F, 0.22F, 0.10F));

        // 3. dorsal_fin: 背脊飘逸透明仙鳍，如同丝绢 (UV: [24..48, 15..28])
        body.addOrReplaceChild("dorsal_fin", CubeListBuilder.create()
                // 高耸高挑丝绢背鳍主面 (Tall Silk Dorsal Sail: dx=0, dy=5, dz=8)
                .texOffs(24, 15).addBox(0.0F, -5.0F, -3.0F, 0.0F, 5.0F, 8.0F)
                // 随波后曳轻纱飘带 (Trailing Silk Streamer: dx=0, dy=3, dz=4)
                .texOffs(40, 15).addBox(0.0F, -4.0F, 5.0F, 0.0F, 3.0F, 4.0F),
                PartPose.offset(0.0F, -3.5F, 0.0F));

        // 4. fin_pectoral_left: 左侧宽阔胸鳍，如蝉翼般划水 (UV: [0..22, 23..29])
        body.addOrReplaceChild("fin_pectoral_left", CubeListBuilder.create()
                .texOffs(0, 23).addBox(0.0F, 0.0F, 0.0F, 5.0F, 0.0F, 6.0F),
                PartPose.offsetAndRotation(2.0F, 1.5F, -2.5F, 0.15F, 0.25F, 0.25F));

        // 5. fin_pectoral_right: 右侧宽阔胸鳍 (UV: [0..22, 29..35])
        body.addOrReplaceChild("fin_pectoral_right", CubeListBuilder.create()
                .texOffs(0, 29).addBox(-5.0F, 0.0F, 0.0F, 5.0F, 0.0F, 6.0F),
                PartPose.offsetAndRotation(-2.0F, 1.5F, -2.5F, 0.15F, -0.25F, -0.25F));

        // 6. fin_pelvic_left: 左腹鳍 (UV: [0..14, 35..39])
        body.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create()
                .texOffs(0, 35).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 4.0F),
                PartPose.offsetAndRotation(1.5F, 2.8F, 1.5F, 0.35F, 0.15F, 0.20F));

        // 7. fin_pelvic_right: 右腹鳍 (UV: [14..28, 35..39])
        body.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create()
                .texOffs(14, 35).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 4.0F),
                PartPose.offsetAndRotation(-1.5F, 2.8F, 1.5F, 0.35F, -0.15F, -0.20F));

        // 8. tail_segment_1: 中后段鱼身，向尾部逐渐收窄 (UV: [48..64, 15..24] & [40..48, 22..29])
        PartDefinition tailSegment1 = body.addOrReplaceChild("tail_segment_1", CubeListBuilder.create()
                // 中段收拢肌节 (Mid-tail tapered segment: dx=3, dy=4, dz=5)
                .texOffs(48, 15).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 5.0F)
                // 腹下飘拂臀鳍 (Ventral / Anal Silk Fin: dx=0, dy=3, dz=4)
                .texOffs(40, 22).addBox(0.0F, 2.0F, 0.5F, 0.0F, 3.0F, 4.0F),
                PartPose.offset(0.0F, 0.0F, 4.0F));

        // 9. tail_segment_2: 尾柄 (Caudal Peduncle: dx=2, dy=3, dz=5, UV: [48..62, 24..32])
        PartDefinition tailSegment2 = tailSegment1.addOrReplaceChild("tail_segment_2", CubeListBuilder.create()
                .texOffs(48, 24).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 0.0F, 5.0F));

        // 10. tail_fin: 如纱似幕的四散广展大尾鳍，分为上下多片与大扇形 (UV: [0..48, 40..58])
        tailSegment2.addOrReplaceChild("tail_fin", CubeListBuilder.create()
                // 中央宏伟如纱大扇形尾鳍 (Main Great Caudal Fan: dx=0, dy=10, dz=8)
                .texOffs(0, 40).addBox(0.0F, -5.0F, 0.0F, 0.0F, 10.0F, 8.0F)
                // 上层飞扬飘纱羽叶 (Upper Trailing Veil Ribbon: dx=0, dy=4, dz=7)
                .texOffs(16, 40).addBox(0.0F, -6.5F, 1.0F, 0.0F, 4.0F, 7.0F)
                // 下层拂水轻纱羽叶 (Lower Trailing Veil Ribbon: dx=0, dy=4, dz=7)
                .texOffs(30, 40).addBox(0.0F, 2.5F, 1.0F, 0.0F, 4.0F, 7.0F)
                // 左侧斜展如意纱瓣 (Left Lateral Veil Flap: dx=3, dy=0, dz=5)
                .texOffs(16, 52).addBox(0.0F, -1.0F, 1.0F, 3.0F, 0.0F, 5.0F)
                // 右侧斜展如意纱瓣 (Right Lateral Veil Flap: dx=3, dy=0, dz=5)
                .texOffs(32, 52).addBox(-3.0F, -1.0F, 1.0F, 3.0F, 0.0F, 5.0F),
                PartPose.offset(0.0F, 0.0F, 5.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);

        // 1. 水流游弋律动基频 (Base Aquatic Motion Frequency)
        // 游速加快时摆动加速，静止时保持悠然自得的慢速仙气游动
        float walkSpeed = Math.min(state.walkAnimationSpeed, 1.0F);
        float speedMultiplier = 1.0F + walkSpeed * 1.6F;
        float swimAngle = state.ageInTicks * 0.22F * speedMultiplier;

        // 摆动振幅随游动速度自适应增强
        float amp = 0.20F + walkSpeed * 0.22F;

        // 2. 绝美S形水生波动链条 (Continuous S-Curve Kinematic Wave Chain)
        // 相位依次滞后，形成流体动力学级顺滑的灵鲤游尾
        this.body.yRot = Mth.sin(swimAngle) * (amp * 0.45F);
        this.tailSegment1.yRot = Mth.sin(swimAngle - 0.42F) * (amp * 0.85F);
        this.tailSegment2.yRot = Mth.sin(swimAngle - 0.84F) * (amp * 1.15F);
        this.tailFin.yRot = Mth.sin(swimAngle - 1.28F) * (amp * 1.45F);

        // 3. 头部姿态反馈 (Head Steering & Pitch)
        // 头部与主躯干轻微反相微摆，保持视觉重心稳定，并响应视角朝向
        this.head.yRot = -Mth.sin(swimAngle + 0.3F) * 0.08F + (state.yRot * ((float) Math.PI / 180F) * 0.35F);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F) * 0.5F;

        // 4. 飘逸仙家金龙须 (Drifting Celestial Dragon Whiskers)
        // 金须随游速向后拖曳微颤，伴有细微高频水流谐波颤动
        float whiskerFlutter = Mth.sin(swimAngle * 1.4F) * 0.08F;
        float whiskerHarmonic = Mth.cos(swimAngle * 2.1F) * 0.05F;
        float dragBack = walkSpeed * 0.16F;

        this.whiskerLeft.yRot = -0.22F - dragBack + whiskerFlutter;
        this.whiskerLeft.zRot = -0.10F + whiskerHarmonic;
        this.whiskerLeft.xRot = 0.15F + Mth.sin(swimAngle * 1.8F) * 0.06F;

        this.whiskerRight.yRot = 0.22F + dragBack - whiskerFlutter;
        this.whiskerRight.zRot = 0.10F - whiskerHarmonic;
        this.whiskerRight.xRot = 0.15F + Mth.sin(swimAngle * 1.8F) * 0.06F;

        // 5. 宽阔胸鳍划水与侧倾微张 (Pectoral Fin Rowing & Undulation)
        // zRot (侧向拍展) 与 yRot (前后划水) 协同波浪，展现蝉翼般轻盈划水
        float strokeCos = Mth.cos(swimAngle);
        float strokeSin = Mth.sin(swimAngle);

        this.finPectoralLeft.zRot = 0.25F + strokeSin * (0.18F + walkSpeed * 0.22F);
        this.finPectoralLeft.yRot = 0.25F + strokeCos * (0.15F + walkSpeed * 0.18F);
        this.finPectoralLeft.xRot = 0.15F - strokeSin * 0.08F;

        this.finPectoralRight.zRot = -0.25F - strokeSin * (0.18F + walkSpeed * 0.22F);
        this.finPectoralRight.yRot = -0.25F - strokeCos * (0.15F + walkSpeed * 0.18F);
        this.finPectoralRight.xRot = 0.15F - strokeSin * 0.08F;

        // 6. 腹鳍微幅摆动平衡 (Pelvic Fin Stabilization)
        this.finPelvicLeft.zRot = 0.20F + Mth.sin(swimAngle - 0.5F) * 0.10F;
        this.finPelvicRight.zRot = -0.20F - Mth.sin(swimAngle - 0.5F) * 0.10F;
        this.finPelvicLeft.yRot = 0.15F + Mth.cos(swimAngle - 0.5F) * 0.08F;
        this.finPelvicRight.yRot = -0.15F - Mth.cos(swimAngle - 0.5F) * 0.08F;

        // 7. 背脊丝绢仙鳍迎水波动 (Dorsal Fin Wave Motion)
        this.dorsalFin.zRot = Mth.sin(swimAngle - 0.35F) * 0.12F;

        // 8. 浮沉与波浪微倾 (Pitch, Roll & Heave)
        // 躯干俯仰微起伏与随水摆动侧倾
        this.body.xRot = Mth.cos(swimAngle * 0.8F) * 0.04F;
        this.body.zRot = -Mth.cos(swimAngle) * 0.06F;
        this.body.y = 19.5F + Mth.sin(swimAngle * 0.8F) * (0.6F + walkSpeed * 0.8F);
    }
}
