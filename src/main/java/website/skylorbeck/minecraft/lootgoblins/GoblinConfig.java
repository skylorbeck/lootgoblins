package website.skylorbeck.minecraft.lootgoblins;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = "lootgoblins/"+Lootgoblins.MOD_ID)
public class GoblinConfig implements ConfigData {

    public boolean doLootBeams = true;
    public int lootBeamHeight = 4;
    public float lootBeamInner = 0.015f;
    public float lootBeamOuter = 0.1f;
    public boolean doLootBeamCommon = false;
    public boolean doLootBeamUncommon = true;
    public boolean doLootBeamRare = true;
    public boolean doLootBeamEpic = true;
    public float goblinChance = 0.0001f;
    public int particleCount = 2;
    public boolean titleAbove = true;
    public boolean titleSmall = true;
    public int bonusLootMax = 10;
    public int maxSpecificLoot = 5;
    public int bonusCombatLootMax = 2;
    public float bonusCombatLootChance = 0.25f;
    public float bonusLootEnchantChance = 0.25f;
    public int bonusEnchantedBooksMax = 1;
    public float enchantedBookChance = 0.25f;
    public float recordChance = 0.05f;

    public int cooldown = 20;
}
