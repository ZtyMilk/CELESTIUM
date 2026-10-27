package com.github.ztymilk.celestium.world;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.entity.CelestiumEntities;
import com.github.ztymilk.celestium.entity.custom.*;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 天庭仙境生态生成器 (Celestium Ambient Spawner)
 * 完美贴合原版和平生物生态，既不卡顿也不空旷：
 * 1. 绝不随意抹杀玩家身边的仙兽，保留自然群落生机。
 * 2. 仅对跌入虚空(Y < -30)或离开玩家 140 格以外的远距野怪进行自然化气消散。
 * 3. 玩家周围 60 格内仙灵数量维持在 12~18 只（对标原版草原/森林舒适生态）。
 * 4. 全维度上限 80 只，确保流畅高帧率运行。
 * 5. 巡天巨鲲与神龙翱翔于云霄，提供震撼的天空视觉体验。
 */
public class CelestiumWorldSpawner {
    private static final Identifier CELESTIUM_DIM_ID = Celestium.id("celestium");
    private static long tickCounter = 0;

    public static void initialize() {
        ServerTickEvents.END_WORLD_TICK.register(CelestiumWorldSpawner::onWorldTick);
    }

    private static void onWorldTick(ServerLevel level) {
        if (!level.dimension().identifier().equals(CELESTIUM_DIM_ID)) {
            return;
        }

        tickCounter++;
        // 每 80 tick (4秒) 进行一次温和的生态维护与补全
        if (tickCounter % 80 != 0) {
            return;
        }

        List<ServerPlayer> players = level.players();
        if (players.isEmpty()) {
            return;
        }

        RandomSource random = level.getRandom();

        // 统计全维度已加载的所有天庭生物
        List<Mob> allLoadedMobs = new ArrayList<>();
        for (Entity e : level.getAllEntities()) {
            if (e instanceof Mob mob && mob.isAlive()) {
                String descId = mob.getType().getDescriptionId();
                if (descId != null && descId.contains("celestium")) {
                    allLoadedMobs.add(mob);
                }
            }
        }

        // ==========================================
        // 阶段一：清理掉入虚空或超远距游离生物
        // ==========================================
        List<Mob> wildMobs = new ArrayList<>();
        for (Mob m : allLoadedMobs) {
            if (m.hasCustomName() || m.isLeashed() || m.isVehicle() || m.isPassenger()) {
                continue; // 保护玩家命名、牵引、骑乘的神兽
            }

            // 掉入虚空的实体直接清除
            if (m.getY() < -30.0) {
                m.discard();
                continue;
            }

            double minDistanceSq = Double.MAX_VALUE;
            for (ServerPlayer player : players) {
                double distSq = m.distanceToSqr(player);
                if (distSq < minDistanceSq) {
                    minDistanceSq = distSq;
                }
            }

            // 稀有大型天界神兽（鲲、龙、凤、麒麟、白泽、白象、貔貅、玄龟等）极度重要，玩家离开也绝不删除，保持常驻世界
            if (isImportantRareBeast(m)) {
                wildMobs.add(m);
                continue;
            }

            // 仅对超过 140 格极远处的普通小生物进行回收（避免内存堆积）
            if (minDistanceSq > 140.0 * 140.0) {
                m.discard();
                continue;
            }

            wildMobs.add(m);
        }

        // 统计巡天巨鲲：若全维度野生已有 2 只以上，不再额外生成，但绝不删除已有神兽
        // 统计高空神龙/烛龙/玄凤：若已有 4 只以上，不再额外生成，但绝不删除已有神兽

        // 重新统计存活的有效天庭仙兽
        List<Mob> activeMobs = allLoadedMobs.stream().filter(Mob::isAlive).toList();

        // 全维度保护上限：达到 80 只则不再新增生成，保护性能
        if (activeMobs.size() >= 80) {
            return;
        }

        long activeWhaleCount = activeMobs.stream().filter(m -> m instanceof CloudWhaleEntity).count();
        long activeDragonCount = activeMobs.stream().filter(m -> m instanceof CloudWyrmEntity || m instanceof CandleDragonEntity || m instanceof RainbowPhoenixEntity).count();

        // ==========================================
        // 阶段二：对标原版生态密度的自然补足
        // ==========================================
        for (ServerPlayer player : players) {
            BlockPos pPos = player.blockPosition();

            // 检查玩家周围 56 格内生物数量，若已有 14 只以上则不在此处额外强刷
            long nearbyCount = activeMobs.stream()
                    .filter(m -> m.distanceToSqr(player) < 56.0 * 56.0)
                    .count();
            if (nearbyCount >= 14) {
                continue;
            }

            // 20% 概率在高空生成巡天巨灵或神龙（辉煌霸气游弋）
            boolean isAerial = random.nextFloat() < 0.20F;
            if (isAerial && (activeDragonCount < 4 || activeWhaleCount < 2)) {
                EntityType<?> aerialType;
                if (activeWhaleCount < 2 && random.nextFloat() < 0.35F) {
                    aerialType = CelestiumEntities.CLOUD_WHALE;
                    activeWhaleCount++;
                } else {
                    int r = random.nextInt(3);
                    aerialType = switch (r) {
                        case 0 -> CelestiumEntities.CLOUD_WYRM;
                        case 1 -> CelestiumEntities.CANDLE_DRAGON;
                        default -> CelestiumEntities.RAINBOW_PHOENIX;
                    };
                    activeDragonCount++;
                }

                double angle = random.nextDouble() * Math.PI * 2.0;
                double dist = 28.0 + random.nextDouble() * 24.0;
                int skyX = pPos.getX() + (int) (Math.cos(angle) * dist);
                int skyZ = pPos.getZ() + (int) (Math.sin(angle) * dist);
                double skyY = Math.max(pPos.getY() + 20.0, 142.0 + random.nextDouble() * 20.0);
                if (skyY > level.getMaxY() - 15) {
                    skyY = level.getMaxY() - 15;
                }

                Mob aerialMob = (Mob) aerialType.create(level, EntitySpawnReason.NATURAL);
                if (aerialMob != null) {
                    aerialMob.snapTo(skyX + 0.5, skyY, skyZ + 0.5, random.nextFloat() * 360.0F, 0.0F);
                    level.addFreshEntity(aerialMob);
                }
                continue;
            }

            // 地面与水中生物生成：环绕玩家 20~45 格选取自然落点
            double angle = random.nextDouble() * Math.PI * 2.0;
            double dist = 20.0 + random.nextDouble() * 25.0;
            int spawnX = pPos.getX() + (int) (Math.cos(angle) * dist);
            int spawnZ = pPos.getZ() + (int) (Math.sin(angle) * dist);

            // 垂直寻找实体支撑面
            int startScanY = Math.min(level.getMaxY() - 5, pPos.getY() + 30);
            int minScanY = Math.max(level.getMinY() + 5, pPos.getY() - 35);
            int foundGroundY = -999;
            boolean isWaterSurface = false;

            for (int y = startScanY; y >= minScanY; y--) {
                BlockPos checkPos = new BlockPos(spawnX, y, spawnZ);
                BlockState state = level.getBlockState(checkPos);
                BlockState stateAbove = level.getBlockState(checkPos.above());
                BlockState stateAbove2 = level.getBlockState(checkPos.above(2));

                if (state.is(Blocks.WATER) && stateAbove.isAir()) {
                    foundGroundY = y;
                    isWaterSurface = true;
                    break;
                }

                if (state.isSolidRender() && (stateAbove.isAir() || stateAbove.is(BlockTags.FLOWERS) || stateAbove.is(BlockTags.REPLACEABLE))
                        && stateAbove2.isAir()) {
                    foundGroundY = y + 1;
                    break;
                }
            }

            if (foundGroundY == -999) {
                continue;
            }

            BlockPos spawnPos = new BlockPos(spawnX, foundGroundY, spawnZ);

            if (isWaterSurface) {
                Mob carp = CelestiumEntities.SPIRIT_CARP.create(level, EntitySpawnReason.NATURAL);
                if (carp != null) {
                    carp.snapTo(spawnX + 0.5, foundGroundY - 1, spawnZ + 0.5, random.nextFloat() * 360.0F, 0.0F);
                    level.addFreshEntity(carp);
                }
                continue;
            }

            Holder<Biome> biomeHolder = level.getBiome(spawnPos);
            String biomePath = biomeHolder.unwrapKey().map(k -> k.identifier().getPath()).orElse("yaochi_pool");

            EntityType<?> chosenType = pickBiomeEntity(biomePath, random);
            if (chosenType == null) {
                continue;
            }

            // 自然族群生成（1~3 只），还原原版生物漫步群生感
            int packSize = (random.nextFloat() < 0.5F) ? 2 : 1;
            for (int i = 0; i < packSize; i++) {
                Mob entity = (Mob) chosenType.create(level, EntitySpawnReason.NATURAL);
                if (entity != null) {
                    double offsetX = (i == 0) ? 0.0 : (random.nextDouble() - 0.5) * 3.5;
                    double offsetZ = (i == 0) ? 0.0 : (random.nextDouble() - 0.5) * 3.5;
                    entity.snapTo(spawnX + 0.5 + offsetX, foundGroundY, spawnZ + 0.5 + offsetZ, random.nextFloat() * 360.0F, 0.0F);
                    entity.setOnGround(true);
                    level.addFreshEntity(entity);
                }
            }
            level.sendParticles(ParticleTypes.CLOUD, spawnX + 0.5, foundGroundY + 0.2, spawnZ + 0.5,
                    2, 0.2, 0.1, 0.2, 0.01);
        }
    }

    private static double getMinPlayerDistSq(Entity e, List<ServerPlayer> players) {
        double min = Double.MAX_VALUE;
        for (ServerPlayer p : players) {
            double d = e.distanceToSqr(p);
            if (d < min) {
                min = d;
            }
        }
        return min;
    }

    public static EntityType<?> pickBiomeEntity(String biome, RandomSource random) {
        return switch (biome) {
            case "beidou_star_plain" -> {
                int r = random.nextInt(4);
                if (r == 0) yield CelestiumEntities.MOON_RABBIT;
                if (r == 1) yield CelestiumEntities.BAI_ZE;
                if (r == 2) yield CelestiumEntities.PI_XIU;
                yield CelestiumEntities.CELESTIAL_DEER;
            }
            case "celestial_thunder_peak" -> {
                int r = random.nextInt(5);
                if (r == 0) yield CelestiumEntities.QILIN;
                if (r == 1) yield CelestiumEntities.HEAVENLY_SOLDIER;
                if (r == 2) yield CelestiumEntities.GOLDEN_CROW;
                if (r == 3) yield CelestiumEntities.XIE_ZHI;
                yield CelestiumEntities.HEAVENLY_GENERAL;
            }
            case "lunar_frostpeak" -> {
                int r = random.nextInt(3);
                if (r == 0) yield CelestiumEntities.MOON_RABBIT;
                if (r == 1) yield CelestiumEntities.BAI_ZE;
                yield CelestiumEntities.SADDANTA;
            }
            case "sunset_sandal_forest" -> {
                int r = random.nextInt(3);
                if (r == 0) yield CelestiumEntities.CELESTIAL_DEER;
                if (r == 1) yield CelestiumEntities.NINE_TAILED_FOX;
                yield CelestiumEntities.AZURE_BIRD;
            }
            case "vermilion_peak" -> {
                int r = random.nextInt(3);
                if (r == 0) yield CelestiumEntities.DAN_FIRE_SPRITE;
                if (r == 1) yield CelestiumEntities.GOLDEN_CROW;
                yield CelestiumEntities.BIFANG;
            }
            case "ziwei_cloud_domain" -> {
                int r = random.nextInt(5);
                if (r == 0) yield CelestiumEntities.HEAVENLY_SOLDIER;
                if (r == 1) yield CelestiumEntities.NINE_TAILED_FOX;
                if (r == 2) yield CelestiumEntities.PI_XIU;
                if (r == 3) yield CelestiumEntities.XIE_ZHI;
                yield CelestiumEntities.HEAVENLY_GENERAL;
            }
            case "penglai_bamboo_sea" -> {
                int r = random.nextInt(4);
                if (r == 0) yield CelestiumEntities.CELESTIAL_CRANE;
                if (r == 1) yield CelestiumEntities.AZURE_BIRD;
                if (r == 2) yield CelestiumEntities.XUAN_GUI;
                yield CelestiumEntities.CELESTIAL_HERMIT;
            }
            case "peach_orchard" -> {
                int r = random.nextInt(3);
                if (r == 0) yield CelestiumEntities.MOON_RABBIT;
                if (r == 1) yield CelestiumEntities.CELESTIAL_DEER;
                yield CelestiumEntities.CELESTIAL_CRANE;
            }
            case "yaochi_pool" -> {
                int r = random.nextInt(5);
                if (r == 0) yield CelestiumEntities.CELESTIAL_CRANE;
                if (r == 1) yield CelestiumEntities.CELESTIAL_DEER;
                if (r == 2) yield CelestiumEntities.SPIRIT_CARP;
                if (r == 3) yield CelestiumEntities.XUAN_GUI;
                yield CelestiumEntities.SADDANTA;
            }
            default -> CelestiumEntities.CELESTIAL_CRANE;
        };
    }

    private static boolean isImportantRareBeast(Mob m) {
        return m instanceof CloudWhaleEntity
                || m instanceof CandleDragonEntity
                || m instanceof CloudWyrmEntity
                || m instanceof RainbowPhoenixEntity
                || m instanceof BaiZeEntity
                || m instanceof QilinEntity
                || m instanceof SaddantaEntity
                || m instanceof PiXiuEntity
                || m instanceof XuanGuiEntity
                || m instanceof XieZhiEntity
                || m instanceof CelestialDeerEntity
                || m instanceof GoldenCrowEntity
                || m instanceof CelestialHermitEntity;
    }
}