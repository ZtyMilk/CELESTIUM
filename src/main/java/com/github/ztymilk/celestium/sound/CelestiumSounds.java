package com.github.ztymilk.celestium.sound;

import com.github.ztymilk.celestium.Celestium;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;

public class CelestiumSounds {
    // 1. 北冥巨鲲 (Cloud Whale)
    public static final SoundEvent CLOUD_WHALE_AMBIENT = register("entity.cloud_whale.ambient");
    public static final SoundEvent CLOUD_WHALE_HURT = register("entity.cloud_whale.hurt");
    public static final SoundEvent CLOUD_WHALE_DEATH = register("entity.cloud_whale.death");

    // 2. 腾云苍龙·巡天灵蛟 (Cloud Wyrm)
    public static final SoundEvent CLOUD_WYRM_AMBIENT = register("entity.cloud_wyrm.ambient");
    public static final SoundEvent CLOUD_WYRM_ROAR = register("entity.cloud_wyrm.roar");
    public static final SoundEvent CLOUD_WYRM_HURT = register("entity.cloud_wyrm.hurt");
    public static final SoundEvent CLOUD_WYRM_DEATH = register("entity.cloud_wyrm.death");

    // 3. 衔烛照天·上古烛龙 (Candle Dragon)
    public static final SoundEvent CANDLE_DRAGON_AMBIENT = register("entity.candle_dragon.ambient");
    public static final SoundEvent CANDLE_DRAGON_ROAR = register("entity.candle_dragon.roar");
    public static final SoundEvent CANDLE_DRAGON_HURT = register("entity.candle_dragon.hurt");
    public static final SoundEvent CANDLE_DRAGON_DEATH = register("entity.candle_dragon.death");

    // 4. 九天玄凤 (Rainbow Phoenix)
    public static final SoundEvent RAINBOW_PHOENIX_AMBIENT = register("entity.rainbow_phoenix.ambient");
    public static final SoundEvent RAINBOW_PHOENIX_CRY = register("entity.rainbow_phoenix.cry");
    public static final SoundEvent RAINBOW_PHOENIX_HURT = register("entity.rainbow_phoenix.hurt");
    public static final SoundEvent RAINBOW_PHOENIX_DEATH = register("entity.rainbow_phoenix.death");

    // 5. 玄玉麒麟 (Qilin)
    public static final SoundEvent QILIN_AMBIENT = register("entity.qilin.ambient");
    public static final SoundEvent QILIN_HURT = register("entity.qilin.hurt");
    public static final SoundEvent QILIN_DEATH = register("entity.qilin.death");

    // 6. 六牙白象 (Saddanta)
    public static final SoundEvent SADDANTA_AMBIENT = register("entity.saddanta.ambient");
    public static final SoundEvent SADDANTA_ROAR = register("entity.saddanta.roar");
    public static final SoundEvent SADDANTA_HURT = register("entity.saddanta.hurt");
    public static final SoundEvent SADDANTA_DEATH = register("entity.saddanta.death");

    // 7. 丹顶仙鹤 (Celestial Crane)
    public static final SoundEvent CELESTIAL_CRANE_AMBIENT = register("entity.celestial_crane.ambient");
    public static final SoundEvent CELESTIAL_CRANE_HURT = register("entity.celestial_crane.hurt");
    public static final SoundEvent CELESTIAL_CRANE_DEATH = register("entity.celestial_crane.death");

    // 8. 九尾灵狐 (Nine-Tailed Fox)
    public static final SoundEvent NINE_TAILED_FOX_AMBIENT = register("entity.nine_tailed_fox.ambient");
    public static final SoundEvent NINE_TAILED_FOX_HURT = register("entity.nine_tailed_fox.hurt");
    public static final SoundEvent NINE_TAILED_FOX_DEATH = register("entity.nine_tailed_fox.death");

    // 9. 毕方神鸟 (Bi Fang)
    public static final SoundEvent BIFANG_AMBIENT = register("entity.bifang.ambient");
    public static final SoundEvent BIFANG_CRY = register("entity.bifang.cry");

    // 10. 三足金乌 (Golden Crow)
    public static final SoundEvent GOLDEN_CROW_AMBIENT = register("entity.golden_crow.ambient");
    public static final SoundEvent GOLDEN_CROW_CRY = register("entity.golden_crow.cry");

    // 11. 辟邪白泽 (Bai Ze)
    public static final SoundEvent BAI_ZE_AMBIENT = register("entity.bai_ze.ambient");

    // 12. 天禄貔貅 (Pi Xiu)
    public static final SoundEvent PI_XIU_AMBIENT = register("entity.pi_xiu.ambient");

    // 13. 九色神鹿 (Celestial Deer)
    public static final SoundEvent CELESTIAL_DEER_AMBIENT = register("entity.celestial_deer.ambient");

    // 14. 旋龟·玄灵负图 (Xuan Gui)
    public static final SoundEvent XUAN_GUI_AMBIENT = register("entity.xuan_gui.ambient");

    // 15. 獬豸·天宪神兽 (Xie Zhi)
    public static final SoundEvent XIE_ZHI_AMBIENT = register("entity.xie_zhi.ambient");

    // 16. 青鸾神鸟 (Azure Bird)
    public static final SoundEvent AZURE_BIRD_AMBIENT = register("entity.azure_bird.ambient");
    public static final SoundEvent AZURE_BIRD_HURT = register("entity.azure_bird.hurt");

    private static SoundEvent register(String name) {
        var id = Celestium.id(name);
        ResourceKey<SoundEvent> key = ResourceKey.create(Registries.SOUND_EVENT, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, key, SoundEvent.createVariableRangeEvent(id));
    }

    public static void initialize() {
        Celestium.LOGGER.info("Registering Celestium Mythical Beast Sound Events");
    }
}
