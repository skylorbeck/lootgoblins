package minecraft.skylorbeck.website.lootgoblins;

import minecraft.skylorbeck.website.lootgoblins.entity.LootSkeletonEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

import static minecraft.skylorbeck.website.lootgoblins.Lootgoblins.getIdentifier;

public class Declarer {
    public static final EntityType<LootSkeletonEntity> LOOT_SKELETON_GOLD = Registry.register(Registry.ENTITY_TYPE,getIdentifier("gold_loot_skeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LootSkeletonEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).trackRangeBlocks(8).build());

}
