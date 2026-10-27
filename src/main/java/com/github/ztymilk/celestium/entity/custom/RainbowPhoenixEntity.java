package com.github.ztymilk.celestium.entity.custom;

import com.github.ztymilk.celestium.sound.CelestiumSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * 九天玄凤 (Rainbow Phoenix)
 * 鸣动八荒，五色备举。出入太虚，羽翼流光溢彩，为天庭至尊瑞鸟。
 */
public class RainbowPhoenixEntity extends Mob implements FlyingAnimal {

    public RainbowPhoenixEntity(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.moveControl = new SmoothPhoenixMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 85.0D)
                .add(Attributes.FLYING_SPEED, 0.45D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PhoenixSoarGoal(this));
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
    public void travel(Vec3 vec3) {
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.91));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            double wave = Math.sin(this.tickCount * 0.1) * 0.015;
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, wave, 0.0));

            int groundY = this.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.getBlockX(), this.getBlockZ());
            if (groundY > 0 && this.getY() < groundY + 15.0) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.02, 0.0));
            } else if (this.getY() < 125.0) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.02, 0.0));
            }

            // 尾羽拖曳五彩星芒与天火粒子
            if (this.tickCount % 20 == 0 && this.level() instanceof ServerLevel sl) {
                if (sl.hasNearbyAlivePlayer(this.getX(), this.getY(), this.getZ(), 36.0)) {
                    Vec3 p = this.position();
                    sl.sendParticles(ParticleTypes.END_ROD, p.x, p.y + 0.5, p.z, 1, 0.1, 0.1, 0.1, 0.01);
                }
            }
        }
    }

    public static class SmoothPhoenixMoveControl extends MoveControl {
        private final RainbowPhoenixEntity phoenix;

        public SmoothPhoenixMoveControl(RainbowPhoenixEntity phoenix) {
            super(phoenix);
            this.phoenix = phoenix;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 toTarget = new Vec3(this.wantedX - this.phoenix.getX(), this.wantedY - this.phoenix.getY(), this.wantedZ - this.phoenix.getZ());
                double dist = toTarget.length();

                if (dist > 1.5) {
                    Vec3 dir = toTarget.normalize();
                    double speed = 0.055 * this.speedModifier;
                    this.phoenix.setDeltaMovement(this.phoenix.getDeltaMovement().scale(0.91).add(dir.scale(speed)));

                    double dx = this.wantedX - this.phoenix.getX();
                    double dz = this.wantedZ - this.phoenix.getZ();
                    float targetRot = -((float) Mth.atan2(dx, dz)) * (180.0F / (float) Math.PI);
                    this.phoenix.setYRot(this.rotlerp(this.phoenix.getYRot(), targetRot, 3.5F));
                    this.phoenix.yBodyRot = this.phoenix.getYRot();
                } else {
                    this.operation = MoveControl.Operation.WAIT;
                }
            }
        }
    }

    public static class PhoenixSoarGoal extends Goal {
        private final RainbowPhoenixEntity phoenix;
        private int cooldown = 0;

        public PhoenixSoarGoal(RainbowPhoenixEntity phoenix) {
            this.phoenix = phoenix;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            MoveControl mc = this.phoenix.getMoveControl();
            if (!mc.hasWanted() || this.cooldown-- <= 0) {
                return true;
            }
            double dx = mc.getWantedX() - this.phoenix.getX();
            double dy = mc.getWantedY() - this.phoenix.getY();
            double dz = mc.getWantedZ() - this.phoenix.getZ();
            return (dx * dx + dy * dy + dz * dz) < 9.0;
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            RandomSource random = this.phoenix.getRandom();
            this.cooldown = 100 + random.nextInt(60);

            double targetX = this.phoenix.getX() + (random.nextDouble() * 2.0 - 1.0) * 40.0;
            double targetZ = this.phoenix.getZ() + (random.nextDouble() * 2.0 - 1.0) * 40.0;
            int groundY = this.phoenix.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) targetX, (int) targetZ);
            double targetY = Math.max(groundY + 16.0 + random.nextDouble() * 20.0, 135.0 + random.nextDouble() * 25.0);

            this.phoenix.getMoveControl().setWantedPosition(targetX, targetY, targetZ, 1.0);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return CelestiumSounds.RAINBOW_PHOENIX_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return CelestiumSounds.RAINBOW_PHOENIX_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return CelestiumSounds.RAINBOW_PHOENIX_DEATH;
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
