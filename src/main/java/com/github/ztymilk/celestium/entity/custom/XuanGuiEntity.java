package com.github.ztymilk.celestium.entity.custom;

import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;
import com.github.ztymilk.celestium.sound.CelestiumSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/**
 * 旋龟·玄灵负图 (Xuan Gui / Celestial Spirit Turtle)
 * 《山海经》载：“其中多玄龟，其状如龟而鸟首虺尾。”
 * 背负洛书八卦碧玉仙甲，鸟首衔珠，灵蛇盘尾。
 * 祥瑞水灵仙兽，栖息于瑶池仙池与蓬莱竹海泽畔。
 * 供奉玉莲或仙草可获“水下呼吸”与“生命恢复”仙佑。
 */
public class XuanGuiEntity extends PathfinderMob {

    public XuanGuiEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 65.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.22D)
                .add(Attributes.ARMOR, 12.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 6.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.85D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        if (held.is(CelestiumFloraBlocks.JADE_LOTUS.asItem()) || held.is(CelestiumFloraBlocks.GOLDEN_LOTUS.asItem())) {
            if (!player.getAbilities().instabuild) {
                held.shrink(1);
            }
            this.heal(25.0F);
            this.playSound(SoundEvents.TURTLE_LAY_EGG, 1.2F, 0.8F);

            if (!this.level().isClientSide()) {
                // 赐予玩家水下呼吸与长生恢复仙泽
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2400, 0));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));

                if (this.level() instanceof ServerLevel serverLevel) {
                    Vec3 p = this.position();
                    serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, p.x, p.y + 0.8, p.z, 7, 0.5, 0.3, 0.5, 0.05);
                    serverLevel.sendParticles(ParticleTypes.SPLASH, p.x, p.y + 0.5, p.z, 10, 0.4, 0.2, 0.4, 0.1);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.TURTLE_SHAMBLE, 0.15F, 1.0F);
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float multiplier, DamageSource source) {
        return super.causeFallDamage(Math.max(0.0D, fallDistance - 3.0D), multiplier * 0.5F, source);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return CelestiumSounds.XUAN_GUI_AMBIENT;
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