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

public class SpiritualPillItem extends Item {
    public enum PillType {
        NINE_TURN,
        QI_GATHERING,
        SOARING,
        THUNDER_CLEANSING,
        STARLIGHT
    }

    private final PillType type;

    public SpiritualPillItem(Properties properties, PillType type) {
        super(properties);
        this.type = type;
    }

    public SpiritualPillItem(Properties properties, boolean isNineTurn) {
        this(properties, isNineTurn ? PillType.NINE_TURN : PillType.QI_GATHERING);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 16;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.EAT;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            switch (this.type) {
                case NINE_TURN -> {
                    entity.setHealth(entity.getMaxHealth());
                    entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3));
                    entity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, 2));
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 0));
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.2F);
                }
                case QI_GATHERING -> {
                    entity.addEffect(new MobEffectInstance(MobEffects.HASTE, 1200, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0));
                    if (entity instanceof Player player) {
                        player.giveExperiencePoints(50);
                    }
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.8F, 1.4F);
                }
                case SOARING -> {
                    entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 120, 0));
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 0));
                    entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 1200, 2));
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.6F);
                }
                case THUNDER_CLEANSING -> {
                    entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 1200, 2));
                    entity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 600, 0));
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            SoundEvents.TRIDENT_THUNDER.value(), SoundSource.PLAYERS, 0.6F, 1.8F);
                }
                case STARLIGHT -> {
                    entity.removeAllEffects();
                    entity.setHealth(entity.getMaxHealth());
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 2));
                    entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 600, 0));
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.9F, 1.5F);
                }
            }
        }
        stack.shrink(1);
        return stack;
    }
}
