package minecraft.skylorbeck.website.lootgoblins;

import com.google.gson.JsonObject;
import minecraft.skylorbeck.website.lootgoblins.entity.LootSkeletonEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import website.skylorbeck.minecraft.skylorlib.DynamicRecipeLoader;

import static minecraft.skylorbeck.website.lootgoblins.Lootgoblins.getIdentifier;

public class Declarer {
    public static final EntityType<LootSkeletonEntity> LOOT_SKELETON_GOLD = Registry.register(Registry.ENTITY_TYPE,getIdentifier("gold_loot_skeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LootSkeletonEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).trackRangeBlocks(8).build());


    public static final Item GOLD_BONE = new Item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON));
    public static JsonObject SMELT_GOLD_BONE;
}
