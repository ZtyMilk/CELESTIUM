package com.github.ztymilk.celestium.registry;

import com.github.ztymilk.celestium.Celestium;
import com.github.ztymilk.celestium.block.custom.AlchemyFurnaceBlock;
import com.github.ztymilk.celestium.item.custom.*;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CelestiumItems {
    // 1. 太乙飞剑 (Flying Sword)
    public static final ResourceKey<Item> FLYING_SWORD_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("flying_sword"));
    public static final Item FLYING_SWORD = Registry.register(
            BuiltInRegistries.ITEM, FLYING_SWORD_KEY,
            new FlyingSwordItem(new Item.Properties().rarity(Rarity.EPIC).durability(1000).setId(FLYING_SWORD_KEY)));

    // 2. 紫金聚灵葫芦 (Celestial Gourd)
    public static final ResourceKey<Item> CELESTIAL_GOURD_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("celestial_gourd"));
    public static final Item CELESTIAL_GOURD = Registry.register(
            BuiltInRegistries.ITEM, CELESTIAL_GOURD_KEY,
            new CelestialGourdItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).setId(CELESTIAL_GOURD_KEY)));

    // 3. 太虚拂尘 (Celestial Whisk)
    public static final ResourceKey<Item> CELESTIAL_WHISK_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("celestial_whisk"));
    public static final Item CELESTIAL_WHISK = Registry.register(
            BuiltInRegistries.ITEM, CELESTIAL_WHISK_KEY,
            new CelestialWhiskItem(new Item.Properties().rarity(Rarity.RARE).durability(500).setId(CELESTIAL_WHISK_KEY)));

    // 4. 九转金丹 (Nine-Turn Golden Elixir)
    public static final ResourceKey<Item> NINE_TURN_ELIXIR_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("nine_turn_elixir"));
    public static final Item NINE_TURN_ELIXIR = Registry.register(
            BuiltInRegistries.ITEM, NINE_TURN_ELIXIR_KEY,
            new SpiritualPillItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).setId(NINE_TURN_ELIXIR_KEY), SpiritualPillItem.PillType.NINE_TURN));

    // 5. 聚灵丹 (Qi-Gathering Pill)
    public static final ResourceKey<Item> QI_GATHERING_PILL_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("qi_gathering_pill"));
    public static final Item QI_GATHERING_PILL = Registry.register(
            BuiltInRegistries.ITEM, QI_GATHERING_PILL_KEY,
            new SpiritualPillItem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(64).setId(QI_GATHERING_PILL_KEY), SpiritualPillItem.PillType.QI_GATHERING));

    // 6. 飞升灵丹 (Immortal Soaring Pill)
    public static final ResourceKey<Item> IMMORTAL_SOARING_PILL_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("immortal_soaring_pill"));
    public static final Item IMMORTAL_SOARING_PILL = Registry.register(
            BuiltInRegistries.ITEM, IMMORTAL_SOARING_PILL_KEY,
            new SpiritualPillItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(16).setId(IMMORTAL_SOARING_PILL_KEY), SpiritualPillItem.PillType.SOARING));

    // 7. 辟雷神丹 (Thunder Cleansing Pill)
    public static final ResourceKey<Item> THUNDER_CLEANSING_PILL_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("thunder_cleansing_pill"));
    public static final Item THUNDER_CLEANSING_PILL = Registry.register(
            BuiltInRegistries.ITEM, THUNDER_CLEANSING_PILL_KEY,
            new SpiritualPillItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(16).setId(THUNDER_CLEANSING_PILL_KEY), SpiritualPillItem.PillType.THUNDER_CLEANSING));

    // 8. 星华淬体丹 (Starlight Essence Pill)
    public static final ResourceKey<Item> STARLIGHT_ESSENCE_PILL_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("starlight_essence_pill"));
    public static final Item STARLIGHT_ESSENCE_PILL = Registry.register(
            BuiltInRegistries.ITEM, STARLIGHT_ESSENCE_PILL_KEY,
            new SpiritualPillItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).setId(STARLIGHT_ESSENCE_PILL_KEY), SpiritualPillItem.PillType.STARLIGHT));

    // 9. 八卦炼丹炉 (Alchemy Furnace Block)
    public static final ResourceKey<Block> ALCHEMY_FURNACE_KEY = ResourceKey.create(
            Registries.BLOCK, Celestium.id("alchemy_furnace"));
    public static final Block ALCHEMY_FURNACE = Registry.register(
            BuiltInRegistries.BLOCK, ALCHEMY_FURNACE_KEY,
            new AlchemyFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)
                    .sound(SoundType.ANVIL)
                    .strength(3.5F, 12.0F)
                    .lightLevel(state -> 12)
                    .setId(ALCHEMY_FURNACE_KEY)));

    public static final ResourceKey<Item> ALCHEMY_FURNACE_ITEM_KEY = ResourceKey.create(
            Registries.ITEM, Celestium.id("alchemy_furnace"));
    public static final Item ALCHEMY_FURNACE_ITEM = Registry.register(
            BuiltInRegistries.ITEM, ALCHEMY_FURNACE_ITEM_KEY,
            new BlockItem(ALCHEMY_FURNACE, new Item.Properties().rarity(Rarity.RARE).setId(ALCHEMY_FURNACE_ITEM_KEY)));

    public static void initialize() {
    }
}
