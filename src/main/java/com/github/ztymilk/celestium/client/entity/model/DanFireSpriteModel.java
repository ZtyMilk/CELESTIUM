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
 * 丹火精 (Dan Fire Sprite) - 灵炉丹气所化天生火灵
 */
public class DanFireSpriteModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Celestium.id("dan_fire_sprite"), "main");

    private final ModelPart core;
    private final ModelPart flameNorth;
    private final ModelPart flameSouth;
    private final ModelPart flameEast;
    private final ModelPart flameWest;

    public DanFireSpriteModel(ModelPart root) {
        super(root);
        this.core = root.getChild("core");
        this.flameNorth = root.getChild("flame_north");
        this.flameSouth = root.getChild("flame_south");
        this.flameEast = root.getChild("flame_east");
        this.flameWest = root.getChild("flame_west");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, 16.0F, 0.0F));
        root.addOrReplaceChild("flame_north", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, -1.5F, -7.0F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 16.0F, 0.0F));
        root.addOrReplaceChild("flame_south", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, -1.5F, 4.0F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 16.0F, 0.0F));
        root.addOrReplaceChild("flame_east", CubeListBuilder.create().texOffs(24, 6).addBox(4.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 16.0F, 0.0F));
        root.addOrReplaceChild("flame_west", CubeListBuilder.create().texOffs(24, 6).addBox(-7.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 16.0F, 0.0F));
        return LayerDefinition.create(mesh, 48, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float rot = state.ageInTicks * 0.12F;
        float bob = Mth.sin(state.ageInTicks * 0.1F) * 1.5F;

        this.core.y = 16.0F + bob;
        this.core.yRot = rot * 0.5F;

        this.flameNorth.y = 16.0F + bob;
        this.flameSouth.y = 16.0F + bob;
        this.flameEast.y = 16.0F + bob;
        this.flameWest.y = 16.0F + bob;

        this.flameNorth.yRot = rot;
        this.flameSouth.yRot = rot;
        this.flameEast.yRot = rot;
        this.flameWest.yRot = rot;
    }
}
