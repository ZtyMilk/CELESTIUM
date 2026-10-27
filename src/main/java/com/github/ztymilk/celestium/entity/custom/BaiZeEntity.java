package com.github.ztymilk.celestium.entity.custom;

import com.github.ztymilk.celestium.sound.CelestiumSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * 辟邪祥瑞·白泽 (Bai Ze)
 * 通万物之情，晓天下鬼神。出则天下安平，身披瑞霭，为周边修士赐福。
 */
public class BaiZeEntity extends PathfinderMob {

    public BaiZeEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ARMOR, 8.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f) {
        boolean hurt = super.hurtServer(serverLevel, damageSource, f);
        if (hurt && !this.level().isClientSide() && this.random.nextFloat() < 0.4F) {
            // 受击时瞬移避险
            double tx = this.getX() + (this.random.nextDouble() - 0.5) * 16.0;
            double tz = this.getZ() + (this.random.nextDouble() - 0.5) * 16.0;
            double ty = this.getY() + (this.random.nextDouble() - 0.5) * 4.0;
            this.teleportTo(tx, ty, tz);
            serverLevel.sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY() + 1.0, this.getZ(), 15, 0.5, 0.5, 0.5, 0.05);
            serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1.0F, 1.2F);
        }
        return hurt;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide() && this.tickCount % 60 == 0) {
            // 每3秒为周边玩家赐予天庭祥瑞 (幸运与生命恢复)
            AABB area = this.getBoundingBox().inflate(10.0);
            List<Player> nearby = this.level().getEntitiesOfClass(Player.class, area);
            for (Player player : nearby) {
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 100, 0, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0, true, true));
            }

            if (this.level() instanceof ServerLevel sl) {
                Vec3 p = this.position();
                sl.sendParticles(ParticleTypes.ENCHANT, p.x, p.y + 1.2, p.z, 5, 0.5, 0.4, 0.5, 0.1);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return CelestiumSounds.BAI_ZE_AMBIENT;
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
