package com.github.ztymilk.celestium.entity.custom;

import com.github.ztymilk.celestium.sound.CelestiumSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * 衔烛照天·上古烛龙 (Candle Dragon)
 * 视为昼，瞑为夜，吹为冬，呼为夏。赤身赤鳞，首戴炎精宝珠，悬天漫游。
 * 支持乘龙御火：玩家右键即可骑乘烛龙巡天翱翔。
 */
public class CandleDragonEntity extends Mob implements FlyingAnimal {

    public CandleDragonEntity(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.moveControl = new SmoothCandleMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 220.0D)
                .add(Attributes.FLYING_SPEED, 0.45D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new CandleSoarGoal(this));
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
                verticalSpeed = -Math.sin(pitch * (Math.PI / 180.0)) * speed * 0.85;
            } else if (pitch < -20.0F) {
                verticalSpeed = 0.35;
            } else if (pitch > 20.0F) {
                verticalSpeed = -0.35;
            }

            Vec3 look = player.getLookAngle();
            Vec3 move;
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

            if (this.level() instanceof ServerLevel serverLevel && this.tickCount % 12 == 0) {
                Vec3 p = this.position();
                serverLevel.sendParticles(ParticleTypes.FLAME, p.x, p.y + 0.5, p.z, 1, 0.2, 0.2, 0.2, 0.01);
            }
            return;
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.91));
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
        return new Vec3(0.0, 4.4, 1.8);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            if (!this.isVehicle()) {
                double wave = Math.sin(this.tickCount * 0.07) * 0.01;
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, wave, 0.0));

                int groundY = this.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.getBlockX(), this.getBlockZ());
                if (groundY > 0 && this.getY() < groundY + 16.0) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.02, 0.0));
                } else if (this.getY() < 120.0) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.02, 0.0));
                }
            }

            // 首戴炎精宝珠粒子
            if (this.tickCount % 20 == 0 && this.level() instanceof ServerLevel sl) {
                if (sl.hasNearbyAlivePlayer(this.getX(), this.getY(), this.getZ(), 36.0)) {
                    Vec3 p = this.position();
                    sl.sendParticles(ParticleTypes.FLAME, p.x, p.y + 1.2, p.z, 1, 0.05, 0.05, 0.05, 0.01);
                }
            }
        }
    }

    public static class SmoothCandleMoveControl extends MoveControl {
        private final CandleDragonEntity dragon;

        public SmoothCandleMoveControl(CandleDragonEntity dragon) {
            super(dragon);
            this.dragon = dragon;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 toTarget = new Vec3(this.wantedX - this.dragon.getX(), this.wantedY - this.dragon.getY(), this.wantedZ - this.dragon.getZ());
                double dist = toTarget.length();

                if (dist > 1.5) {
                    Vec3 dir = toTarget.normalize();
                    double speed = 0.045 * this.speedModifier;
                    this.dragon.setDeltaMovement(this.dragon.getDeltaMovement().scale(0.91).add(dir.scale(speed)));

                    double dx = this.wantedX - this.dragon.getX();
                    double dz = this.wantedZ - this.dragon.getZ();
                    float targetRot = -((float) Mth.atan2(dx, dz)) * (180.0F / (float) Math.PI);
                    this.dragon.setYRot(this.rotlerp(this.dragon.getYRot(), targetRot, 3.0F));
                    this.dragon.yBodyRot = this.dragon.getYRot();
                } else {
                    this.operation = MoveControl.Operation.WAIT;
                }
            }
        }
    }

    public static class CandleSoarGoal extends Goal {
        private final CandleDragonEntity dragon;
        private int cooldown = 0;

        public CandleSoarGoal(CandleDragonEntity dragon) {
            this.dragon = dragon;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.dragon.isVehicle()) {
                return false;
            }
            MoveControl mc = this.dragon.getMoveControl();
            if (!mc.hasWanted() || this.cooldown-- <= 0) {
                return true;
            }
            double dx = mc.getWantedX() - this.dragon.getX();
            double dy = mc.getWantedY() - this.dragon.getY();
            double dz = mc.getWantedZ() - this.dragon.getZ();
            return (dx * dx + dy * dy + dz * dz) < 9.0;
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            RandomSource random = this.dragon.getRandom();
            this.cooldown = 120 + random.nextInt(60);

            double targetX = this.dragon.getX() + (random.nextDouble() * 2.0 - 1.0) * 36.0;
            double targetZ = this.dragon.getZ() + (random.nextDouble() * 2.0 - 1.0) * 36.0;
            int groundY = this.dragon.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) targetX, (int) targetZ);
            double targetY = Math.max(groundY + 16.0 + random.nextDouble() * 15.0, 130.0 + random.nextDouble() * 20.0);

            this.dragon.getMoveControl().setWantedPosition(targetX, targetY, targetZ, 1.0);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return CelestiumSounds.CANDLE_DRAGON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return CelestiumSounds.CANDLE_DRAGON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return CelestiumSounds.CANDLE_DRAGON_DEATH;
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

