package minecraft.skylorbeck.website.lootgoblins.tables;

import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LootTables {
    public static Identifier[] generic = new Identifier[]{
            Registry.ITEM.getId(Items.GRANITE),
            Registry.ITEM.getId(Items.ANDESITE),
            Registry.ITEM.getId(Items.DIORITE),
            Registry.ITEM.getId(Items.BLACKSTONE),
            Registry.ITEM.getId(Items.TUFF),
            Registry.ITEM.getId(Items.STONE),
            Registry.ITEM.getId(Items.DEEPSLATE),
            Registry.ITEM.getId(Items.LAPIS_LAZULI),
            Registry.ITEM.getId(Items.EMERALD),
            Registry.ITEM.getId(Items.IRON_SWORD),
            Registry.ITEM.getId(Items.IRON_PICKAXE),
            Registry.ITEM.getId(Items.IRON_AXE),
            Registry.ITEM.getId(Items.IRON_SHOVEL),
            Registry.ITEM.getId(Items.IRON_HOE),
            Registry.ITEM.getId(Items.IRON_HORSE_ARMOR),
            Registry.ITEM.getId(Items.GOLDEN_HORSE_ARMOR),
            Registry.ITEM.getId(Items.CHAINMAIL_BOOTS),
            Registry.ITEM.getId(Items.CHAINMAIL_CHESTPLATE),
            Registry.ITEM.getId(Items.CHAINMAIL_LEGGINGS),
            Registry.ITEM.getId(Items.CHAINMAIL_HELMET),
            Registry.ITEM.getId(Items.SADDLE),
            Registry.ITEM.getId(Items.LEAD),
            Registry.ITEM.getId(Items.ACACIA_WOOD),
            Registry.ITEM.getId(Items.BIRCH_WOOD),
            Registry.ITEM.getId(Items.DARK_OAK_WOOD),
            Registry.ITEM.getId(Items.JUNGLE_WOOD),
            Registry.ITEM.getId(Items.OAK_WOOD),
            Registry.ITEM.getId(Items.SPRUCE_WOOD),
            Registry.ITEM.getId(Items.GOLD_NUGGET),
            Registry.ITEM.getId(Items.IRON_NUGGET),
            Registry.ITEM.getId(Items.AMETHYST_SHARD),
            Registry.ITEM.getId(Items.APPLE),
            Registry.ITEM.getId(Items.POTATO),
            Registry.ITEM.getId(Items.BEETROOT),
            Registry.ITEM.getId(Items.BAMBOO),
            Registry.ITEM.getId(Items.HONEYCOMB),
            Registry.ITEM.getId(Items.GUNPOWDER),
            Registry.ITEM.getId(Items.ICE),
            Registry.ITEM.getId(Items.GLISTERING_MELON_SLICE),
            Registry.ITEM.getId(Items.GOLDEN_CARROT),
            Registry.ITEM.getId(Items.MELON),
            Registry.ITEM.getId(Items.PUMPKIN),
            Registry.ITEM.getId(Items.FISHING_ROD),
            Registry.ITEM.getId(Items.REDSTONE)
    };

    public static Identifier[] musicDisks = new Identifier[]{
            Registry.ITEM.getId(Items.MUSIC_DISC_13),
            Registry.ITEM.getId(Items.MUSIC_DISC_11),
            Registry.ITEM.getId(Items.MUSIC_DISC_BLOCKS),
            Registry.ITEM.getId(Items.MUSIC_DISC_CAT),
            Registry.ITEM.getId(Items.MUSIC_DISC_CHIRP),
            Registry.ITEM.getId(Items.MUSIC_DISC_FAR),
            Registry.ITEM.getId(Items.MUSIC_DISC_MALL),
            Registry.ITEM.getId(Items.MUSIC_DISC_MELLOHI),
            Registry.ITEM.getId(Items.MUSIC_DISC_OTHERSIDE),
            Registry.ITEM.getId(Items.MUSIC_DISC_PIGSTEP),
            Registry.ITEM.getId(Items.MUSIC_DISC_STAL),
            Registry.ITEM.getId(Items.MUSIC_DISC_STRAD),
            Registry.ITEM.getId(Items.MUSIC_DISC_WAIT),
            Registry.ITEM.getId(Items.MUSIC_DISC_WARD)
    };

    public static Identifier[] skeleton = new Identifier[]{
            Lootgoblins.getIdentifier("gold_bone_item")
    };

    public static Identifier[] enderman = new Identifier[]{
            Lootgoblins.getIdentifier("prismarine_pearl_item")
    };

    public static Identifier[] creeper = new Identifier[]{
            Registry.ITEM.getId(Items.REDSTONE)//todo replace
    };

    public static Identifier[] hoglin = new Identifier[]{
            Registry.ITEM.getId(Items.QUARTZ)//todo replace
    };

    public static Identifier[] illager = new Identifier[]{
            Registry.ITEM.getId(Items.EMERALD)//todo replace
    };

    public static Identifier[] spider = new Identifier[]{
            Registry.ITEM.getId(Items.IRON_INGOT)//todo replace
    };

    public static Identifier[] zombie = new Identifier[]{
            Registry.ITEM.getId(Items.LAPIS_LAZULI)//todo replace
    };
}