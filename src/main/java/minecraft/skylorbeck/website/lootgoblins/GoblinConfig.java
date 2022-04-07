package minecraft.skylorbeck.website.lootgoblins;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@me.shedaniel.autoconfig.annotation.Config(name = Lootgoblins.MOD_ID)
public class GoblinConfig implements ConfigData {

    public boolean doLootBeams = true;
    public boolean doLootBeamCommon = false;
    public boolean doLootBeamUncommon = true;
    public boolean doLootBeamRare = true;
    public boolean doLootBeamEpic = true;
    public int lootBeamHeight = 4;
    public float lootBeamInner = 0.015f;
    public float lootBeamOuter = 0.1f;
    public float enchantedBookChance = 0.25f;
    public int bonusEnchantedBooksMax = 1;
    public float bonusLootEnchantChance = 0.25f;

    @ConfigEntry.Gui.CollapsibleObject
    public SkeletonStuff skeletonStuff = new SkeletonStuff();

    public static class SkeletonStuff{
        public float goldLootSkeletonChance = 0.001f;
        public int bonusLootMax = 10;
        public int minGoldBone = 1;
        public int maxGoldBone = 5;

    }

    public Identifier[] loot = new Identifier[]{
            Registry.ITEM.getId(Items.STONE),
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
            Registry.ITEM.getId(Items.REDSTONE),
    };
}
