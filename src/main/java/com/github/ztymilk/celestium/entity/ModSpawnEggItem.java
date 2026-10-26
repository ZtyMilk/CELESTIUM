package com.github.ztymilk.celestium.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ModSpawnEggItem extends Item {
    private final EntityType<?> entityType;

    public ModSpawnEggItem(EntityType<?> entityType, Item.Properties properties) {
        super(properties);
        this.entityType = entityType;
    }

    public EntityType<?> getEntityType() {
        return this.entityType;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            BlockPos spawnPos = context.getClickedPos().relative(context.getClickedFace());
            this.entityType.spawn((ServerLevel) level, spawnPos, EntitySpawnReason.SPAWN_ITEM_USE);
            if (!context.getPlayer().hasInfiniteMaterials()) {
                context.getItemInHand().shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
