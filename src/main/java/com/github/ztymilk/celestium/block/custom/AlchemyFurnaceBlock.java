package com.github.ztymilk.celestium.block.custom;

import com.github.ztymilk.celestium.registry.CelestiumItems;
import com.github.ztymilk.celestium.registry.block.CelestiumFloraBlocks;
import com.github.ztymilk.celestium.registry.block.CelestiumHerbBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AlchemyFurnaceBlock extends Block {
    public AlchemyFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack resultPill = getPillFromIngredient(stack);
        if (resultPill != null && !resultPill.isEmpty()) {
            if (!level.isClientSide()) {
                stack.shrink(1);
                ItemEntity drop = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 1.25, pos.getZ() + 0.5, resultPill);
                drop.setDeltaMovement(0.0, 0.2, 0.0);
                level.addFreshEntity(drop);

                level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, 1.2F);
                level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.5F);

                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 12, 0.25, 0.2, 0.25, 0.05);
                    serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + 0.5, pos.getY() + 1.3, pos.getZ() + 0.5, 8, 0.2, 0.15, 0.2, 0.02);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private static ItemStack getPillFromIngredient(ItemStack stack) {
        if (stack.is(CelestiumFloraBlocks.BLAZING_SPIRIT_GRASS.asItem()) || stack.is(CelestiumHerbBlocks.SUN_SPIRIT_GINSENG.asItem())) {
            return new ItemStack(CelestiumItems.NINE_TURN_ELIXIR);
        }
        if (stack.is(CelestiumHerbBlocks.NINE_LEAF_LINGZHI.asItem())) {
            return new ItemStack(CelestiumItems.NINE_TURN_ELIXIR, 2);
        }
        if (stack.is(CelestiumFloraBlocks.JADE_LOTUS.asItem()) || stack.is(CelestiumHerbBlocks.CLEAR_JADE_BAMBOO_SHOOT.asItem())) {
            return new ItemStack(CelestiumItems.QI_GATHERING_PILL, 2);
        }
        if (stack.is(CelestiumHerbBlocks.SNOW_LOTUS_HERB.asItem()) || stack.is(CelestiumFloraBlocks.FROST_CRYSTAL_GRASS.asItem())) {
            return new ItemStack(CelestiumItems.IMMORTAL_SOARING_PILL);
        }
        if (stack.is(CelestiumHerbBlocks.DRAGON_SALIVA_FRUIT.asItem())) {
            return new ItemStack(CelestiumItems.THUNDER_CLEANSING_PILL);
        }
        if (stack.is(CelestiumHerbBlocks.PURPLE_CLOUD_REISHI.asItem()) || stack.is(CelestiumHerbBlocks.THOUSAND_YEAR_POLYGONUM.asItem()) || stack.is(CelestiumHerbBlocks.SOUL_REVIVING_GRASS.asItem())) {
            return new ItemStack(CelestiumItems.STARLIGHT_ESSENCE_PILL);
        }
        return null;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.4;
        double y = pos.getY() + 0.95;
        double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.4;
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.04, 0.0);
        level.addParticle(ParticleTypes.SMOKE, x, y + 0.2, z, 0.0, 0.02, 0.0);
        if (random.nextFloat() < 0.25F) {
            level.addParticle(ParticleTypes.END_ROD, x, y + 0.3, z, 0.0, 0.015, 0.0);
        }
    }
}
