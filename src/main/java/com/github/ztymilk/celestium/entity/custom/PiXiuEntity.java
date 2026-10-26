package com.github.ztymilk.celestium.entity.custom;

import org.jspecify.annotations.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * 天禄貔貅 (Pi Xiu)
 * 纳四海瑞气，镇天门气运之瑞兽。
 * 龙首狮躯，单角羽翼，通体金翠交辉。喜爱吞食金玉，常随行于天界星野与神庭。
 */
public class PiXiuEntity extends Animal {

    public PiXiuEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.15D, stack -> isFood(stack), false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel sl && this.tickCount % 24 == 0) {
            if (sl.hasNearbyAlivePlayer(this.getX(), this.getY(), this.getZ(), 32.0)) {
                sl.sendParticles(ParticleTypes.ENCHANT, this.getX(), this.getY() + 0.8, this.getZ(), 1, 0.2, 0.2, 0.2, 0.02);
            }

            // 貔貅招财纳福：每 100 tick 赐予周围有缘玩家幸运眷顾 (Luck)
            if (this.tickCount % 120 == 0) {
                List<Player> nearby = sl.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(8.0));
                for (Player p : nearby) {
                    p.addEffect(new MobEffectInstance(MobEffects.LUCK, 160, 0, false, false, true));
                }
            }
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.GOLD_INGOT) || stack.is(Items.EMERALD) || stack.is(Items.GOLDEN_APPLE);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return (AgeableMob) this.getType().create(serverLevel, net.minecraft.world.entity.EntitySpawnReason.BREEDING);
    }
}
