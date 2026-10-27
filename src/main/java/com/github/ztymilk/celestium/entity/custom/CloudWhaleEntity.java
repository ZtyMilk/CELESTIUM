package com.github.ztymilk.celestium.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import com.github.ztymilk.celestium.sound.CelestiumSounds;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * 流云巨鲸 (Cloud Whale / 鲲)
 * 仙境巡天巨灵，长伴祥云，永驻高天。
 * 支持乘鲲御气，玩家右键即可骑乘并在苍穹自由翱翔。
 */
public class CloudWhaleEntity extends Mob implements FlyingAnimal {

    public CloudWhaleEntity(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.moveControl = new SmoothSoaringMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.FLYING_SPEED, 0.45D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new CelestialWhaleWanderGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 32.0F));
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity first = this.getFirstPassenger();
        return first instanceof Player player ? player : null;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (!this.isAlive()) {
            return;
        }

        // 乘鲲御气机制：玩家骑乘时响应 WASD 与视角俯仰三维翱翔控制
        LivingEntity controller = this.getControllingPassenger();
        if (this.isVehicle() && controller instanceof Player player) {
            this.setYRot(player.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(player.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;

            float forward = player.zza;
            float strafe = player.xxa;

            double speed = 0.55;
            double verticalSpeed = 0.0;

            float pitch = player.getXRot();
            if (forward > 0) {
                // 前进时，根据视线俯仰角进行平滑爬升与俯冲
                verticalSpeed = -Math.sin(pitch * (Math.PI / 180.0)) * speed * 0.85;
            } else if (pitch < -20.0F) {
                verticalSpeed = 0.35;
            } else if (pitch > 20.0F) {
                verticalSpeed = -0.35;
            }

            Vec3 look = player.getLookAngle();
            Vec3 move = new Vec3(0, 0, 0);
            if (forward > 0) {
                move = new Vec3(look.x * speed, verticalSpeed, look.z * speed);
            } else if (forward < 0) {
                move = new Vec3(-look.x * speed * 0.4, verticalSpeed, -look.z * speed * 0.4);
            } else if (Math.abs(strafe) > 0.1) {
                Vec3 right = new Vec3(-look.z, 0, look.x).normalize();
                move = new Vec3(right.x * strafe * speed * 0.5, verticalSpeed, right.z * strafe * speed * 0.5);
            } else {
                move = new Vec3(0, verticalSpeed, 0);
            }

            this.setDeltaMovement(this.getDeltaMovement().scale(0.88).add(move.scale(0.22)));
            this.move(MoverType.SELF, this.getDeltaMovement());

            // 骑乘巡天伴生祥云气旋
            if (this.level() instanceof ServerLevel serverLevel && this.tickCount % 10 == 0) {
                Vec3 p = this.position();
                serverLevel.sendParticles(ParticleTypes.CLOUD,
                        p.x + (this.random.nextDouble() - 0.5) * 5.0,
                        p.y - 0.5,
                        p.z + (this.random.nextDouble() - 0.5) * 5.0,
                        3, 0.5, 0.2, 0.5, 0.02);
            }
            return;
        }

        // 自然巡天漂浮：无重力滑翔与缓动
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.92));
    }

    @Override
    public void tick() {
        super.tick();

        // 巨鲲在仙境云海维持悠然起伏
        if (!this.level().isClientSide()) {
            if (!this.isVehicle()) {
                // 悠缓的如意祥云上下起伏律动
                double wave = Math.sin(this.tickCount * 0.04) * 0.008;
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, wave, 0.0));

                // 保持在地面高度上方 15~35 格巡弋，避免撞山或落入虚空
                int groundY = this.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.getBlockX(), this.getBlockZ());
                if (groundY > 0) {
                    double targetAltitude = groundY + 22.0;
                    if (this.getY() < targetAltitude) {
                        this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.015, 0.0));
                    }
                } else if (this.getY() < 120.0) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.02, 0.0));
                }
            }

            // 伴生仙界祥云 (仅在附近有玩家时轻量发射)
            if (this.tickCount % 24 == 0 && this.level() instanceof ServerLevel serverLevel) {
                if (serverLevel.hasNearbyAlivePlayer(this.getX(), this.getY(), this.getZ(), 48.0)) {
                    Vec3 pos = this.position();
                    serverLevel.sendParticles(ParticleTypes.CLOUD,
                            pos.x + (this.random.nextDouble() - 0.5) * 6.0,
                            pos.y + (this.random.nextDouble() - 0.5) * 2.0,
                            pos.z + (this.random.nextDouble() - 0.5) * 6.0,
                            1, 0.3, 0.1, 0.3, 0.01);
                }
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide() && !this.isVehicle()) {
            player.startRiding(this);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions entityDimensions, float f) {
        return new Vec3(0.0, 8.8, 0.0); // 骑乘于背部须弥仙阁法座正中
    }

    /**
     * 巨鲲平滑连续巡弋控制器 (每 tick 连续旋转与平滑推进，杜绝停滞卡顿)
     */
    public static class SmoothSoaringMoveControl extends MoveControl {
        private final CloudWhaleEntity whale;

        public SmoothSoaringMoveControl(CloudWhaleEntity whale) {
            super(whale);
            this.whale = whale;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 toTarget = new Vec3(this.wantedX - this.whale.getX(), this.wantedY - this.whale.getY(), this.wantedZ - this.whale.getZ());
                double dist = toTarget.length();

                if (dist > 3.0) {
                    Vec3 dir = toTarget.normalize();
                    double speed = 0.045 * this.speedModifier;
                    this.whale.setDeltaMovement(this.whale.getDeltaMovement().scale(0.92).add(dir.scale(speed)));

                    // 平滑转向，呈现宏伟巨鲸的转向弧度
                    double dx = this.wantedX - this.whale.getX();
                    double dz = this.wantedZ - this.whale.getZ();
                    float targetRot = -((float) Mth.atan2(dx, dz)) * (180.0F / (float) Math.PI);
                    this.whale.setYRot(this.rotlerp(this.whale.getYRot(), targetRot, 1.8F));
                    this.whale.yBodyRot = this.whale.getYRot();
                } else {
                    this.operation = MoveControl.Operation.WAIT;
                }
            }
        }
    }

    /**
     * 巡天翱翔巡弋目标 (持续保持平滑飞行航向)
     */
    public static class CelestialWhaleWanderGoal extends Goal {
        private final CloudWhaleEntity whale;
        private int courseChangeCooldown = 0;

        public CelestialWhaleWanderGoal(CloudWhaleEntity whale) {
            this.whale = whale;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.whale.isVehicle()) {
                return false;
            }
            MoveControl mc = this.whale.getMoveControl();
            if (!mc.hasWanted() || this.courseChangeCooldown-- <= 0) {
                return true;
            }
            double dx = mc.getWantedX() - this.whale.getX();
            double dy = mc.getWantedY() - this.whale.getY();
            double dz = mc.getWantedZ() - this.whale.getZ();
            return (dx * dx + dy * dy + dz * dz) < 16.0;
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            RandomSource random = this.whale.getRandom();
            this.courseChangeCooldown = 120 + random.nextInt(80);

            float yawRad = -this.whale.getYRot() * ((float) Math.PI / 180.0F);
            double forwardX = Math.sin(yawRad);
            double forwardZ = Math.cos(yawRad);

            double dist = 40.0 + random.nextDouble() * 40.0;
            double targetX = this.whale.getX() + forwardX * dist + (random.nextDouble() - 0.5) * 30.0;
            double targetZ = this.whale.getZ() + forwardZ * dist + (random.nextDouble() - 0.5) * 30.0;

            int groundY = this.whale.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) targetX, (int) targetZ);
            double targetY = Math.max(groundY + 20.0 + random.nextDouble() * 15.0, 140.0 + random.nextDouble() * 20.0);

            this.whale.getMoveControl().setWantedPosition(targetX, targetY, targetZ, 1.0);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return CelestiumSounds.CLOUD_WHALE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return CelestiumSounds.CLOUD_WHALE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return CelestiumSounds.CLOUD_WHALE_DEATH;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }
}
