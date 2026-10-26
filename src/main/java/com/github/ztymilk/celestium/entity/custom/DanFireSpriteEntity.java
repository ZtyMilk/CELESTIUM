package com.github.ztymilk.celestium.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * 丹火灵精 (Dan Fire Sprite)
 * 太上老君八卦炼丹炉中溢出的仙火通灵之精，若红莲流火般在空中跳跃悬浮。
 */
public class DanFireSpriteEntity extends Mob implements FlyingAnimal {

    public DanFireSpriteEntity(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.moveControl = new SpriteMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.FLYING_SPEED, 0.40D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new HoverRandomGoal(this));
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
        this.travelFlying(vec3, 0.02F);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level() instanceof ServerLevel serverLevel && this.tickCount % 4 == 0) {
            Vec3 p = this.position();
            serverLevel.sendParticles(ParticleTypes.FLAME,
                    p.x, p.y + 0.3, p.z,
                    2, 0.15, 0.15, 0.15, 0.01);
        }
    }

    public static class SpriteMoveControl extends MoveControl {
        private final DanFireSpriteEntity sprite;

        public SpriteMoveControl(DanFireSpriteEntity sprite) {
            super(sprite);
            this.sprite = sprite;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - this.sprite.getX(), this.wantedY - this.sprite.getY(), this.wantedZ - this.sprite.getZ());
                double dist = vec3.length();
                if (dist > 0.5) {
                    this.sprite.setDeltaMovement(this.sprite.getDeltaMovement().add(vec3.normalize().scale(0.03 * this.speedModifier)));
                } else {
                    this.operation = MoveControl.Operation.WAIT;
                }
            }
        }
    }

    public static class HoverRandomGoal extends Goal {
        private final DanFireSpriteEntity sprite;

        public HoverRandomGoal(DanFireSpriteEntity sprite) {
            this.sprite = sprite;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            MoveControl moveControl = this.sprite.getMoveControl();
            if (!moveControl.hasWanted()) {
                return true;
            }
            double d = moveControl.getWantedX() - this.sprite.getX();
            double e = moveControl.getWantedY() - this.sprite.getY();
            double f = moveControl.getWantedZ() - this.sprite.getZ();
            return (d * d + e * e + f * f) < 1.0;
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            RandomSource random = this.sprite.getRandom();
            double tx = this.sprite.getX() + (random.nextDouble() * 2.0 - 1.0) * 12.0;
            double ty = this.sprite.getY() + (random.nextDouble() * 2.0 - 1.0) * 4.0;
            double tz = this.sprite.getZ() + (random.nextDouble() * 2.0 - 1.0) * 12.0;
            this.sprite.getMoveControl().setWantedPosition(tx, ty, tz, 1.0);
        }
    }
}
