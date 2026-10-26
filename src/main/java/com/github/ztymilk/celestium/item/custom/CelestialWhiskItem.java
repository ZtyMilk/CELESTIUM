package com.github.ztymilk.celestium.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CelestialWhiskItem extends Item {
    public CelestialWhiskItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        AABB area = player.getBoundingBox().inflate(8.0);
        List<Mob> mobs = level.getEntitiesOfClass(Mob.class, area);
        for (Mob mob : mobs) {
            Vec3 dir = mob.position().subtract(player.position()).normalize();
            mob.setDeltaMovement(dir.x * 1.5, 0.6, dir.z * 1.5);
            mob.hurtMarked = true;
            mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
        }

        player.clearFire();
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 1));

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.WIND_CHARGE_THROW, SoundSource.PLAYERS, 1.0F, 0.9F);

        if (level instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 24; i++) {
                double angle = i * (Math.PI * 2 / 24);
                double px = player.getX() + Math.cos(angle) * 3.0;
                double pz = player.getZ() + Math.sin(angle) * 3.0;
                serverLevel.sendParticles(ParticleTypes.ENCHANTED_HIT, px, player.getY() + 1.0, pz, 1, 0, 0, 0, 0.1);
            }
        }

        player.getCooldowns().addCooldown(player.getItemInHand(hand), 60);
        return InteractionResult.SUCCESS;
    }
}
