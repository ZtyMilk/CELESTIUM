package com.github.ztymilk.celestium.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;

public class CelestialGourdItem extends Item {
    public CelestialGourdItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
            entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 400, 1));
            entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 1));
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.HONEY_DRINK, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            player.getCooldowns().addCooldown(stack, 100);
        }
        return stack;
    }
}
