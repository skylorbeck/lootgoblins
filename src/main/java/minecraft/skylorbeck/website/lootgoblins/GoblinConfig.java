package minecraft.skylorbeck.website.lootgoblins;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = Lootgoblins.MOD_ID)
public class GoblinConfig implements ConfigData {
    public float goldLootSkeletonChance = 0.01f;

    public boolean doLootBeams = true;
    public boolean doLootBeamCommon = false;
    public boolean doLootBeamUncommon = true;
    public boolean doLootBeamRare = true;
    public boolean doLootBeamEpic = true;
    public int lootBeamHeight = 4;
    public float lootBeamInner = 0.015f;
    public float lootBeamOuter = 0.1f;
}
