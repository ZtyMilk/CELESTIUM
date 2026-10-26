package com.github.ztymilk.celestium.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/**
 * 獬豸·天宪神兽 (Xie Zhi / Divine Beast of Justice)
 * 《异物志》载：“性知人有罪。见人斗，触不直者；闻人论，咋不正者。”
 * 额顶生有神圣独角，鬃毛如云焰披拂，秉持天庭正气。
 * 栖息于紫微仙域与天雷极巅，能辨奸邪，主动铲除宵小魔物。
 * 抚弄独角可获天宪正气加护（抗性提升与力量增幅）。
 */
public class XieZhiEntity extends PathfinderMob {

    public XieZhiEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 90.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 36.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.85D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        // 正气凛然：惩戒敌性怪物与入侵者
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, net.minecraft.world.entity.monster.Monster.class, true));
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        boolean success = super.doHurtTarget(level, target);
        if (success) {
            Vec3 p = target.position();
            level.sendParticles(ParticleTypes.ENCHANTED_HIT, p.x, p.y + 0.8, p.z, 12, 0.3, 0.4, 0.3, 0.1);
            this.playSound(SoundEvents.TRIDENT_THUNDER.value(), 1.0F, 1.2F);
        }
        return success;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        if (held.is(Items.GOLD_INGOT) || held.is(Items.EMERALD)) {
            if (!player.getAbilities().instabuild) {
                held.shrink(1);
            }
            this.heal(30.0F);
            this.playSound(SoundEvents.BELL_BLOCK, 1.0F, 1.4F);

            if (!this.level().isClientSide()) {
                // 赐予玩家天宪正气：抗性提升与力量增幅
                player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 1800, 1));
                player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, 0));

                if (this.level() instanceof ServerLevel serverLevel) {
                    Vec3 p = this.position();
                    serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, p.x, p.y + 1.2, p.z, 8, 0.4, 0.4, 0.4, 0.05);
                    serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, p.x, p.y + 1.5, p.z, 6, 0.2, 0.3, 0.2, 0.02);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float multiplier, DamageSource source) {
        return super.causeFallDamage(Math.max(0.0D, fallDistance - 4.0D), multiplier * 0.5F, source);
    }
}