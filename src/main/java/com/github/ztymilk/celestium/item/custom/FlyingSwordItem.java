package com.github.ztymilk.celestium.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlyingSwordItem extends Item {
    public FlyingSwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        Vec3 look = player.getLookAngle();
        player.setDeltaMovement(look.x * 1.8, Math.max(look.y * 1.5, 0.6), look.z * 1.8);
        player.hurtMarked = true;

        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0, false, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 120, 1, false, false, true));

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TRIDENT_RIPTIDE_2.value(), SoundSource.PLAYERS, 1.0F, 1.2F);

        if (level instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 16; i++) {
                double ox = (player.getRandom().nextDouble() - 0.5) * 0.8;
                double oy = (player.getRandom().nextDouble() - 0.5) * 0.8;
                double oz = (player.getRandom().nextDouble() - 0.5) * 0.8;
                serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX() + ox, player.getY() + oy, player.getZ() + oz, 1, 0, 0, 0, 0.05);
            }
        }

        player.getCooldowns().addCooldown(player.getItemInHand(hand), 30);
        return InteractionResult.SUCCESS;
    }
}
