package minecraft.skylorbeck.website.lootgoblins;

import minecraft.skylorbeck.website.lootgoblins.entity.LootSkeletonEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Lootgoblins implements ModInitializer {
    public static final String MOD_ID = "lootgoblins";
    public static Identifier getIdentifier(String path) {
        return new Identifier(MOD_ID,path);
    }

    public static final EntityType<LootSkeletonEntity> LOOT_SKELETON_GOLD = Registry.register(Registry.ENTITY_TYPE,getIdentifier("gold_loot_skeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LootSkeletonEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).trackRangeBlocks(8).build());

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(LOOT_SKELETON_GOLD,LootSkeletonEntity.createLootSkeletonAttributes());

        ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
            if (entity instanceof SkeletonEntity skeletonEntity && world.random.nextInt(100)<50){
                replaceEntity(skeletonEntity,LOOT_SKELETON_GOLD,world);
            }
        }));
    }

    private void replaceEntity(HostileEntity source, EntityType<?> target, ServerWorld world) {
        HostileEntity lootGoblin = (HostileEntity) target.create(world);
        lootGoblin.copyPositionAndRotation(source);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            lootGoblin.equipStack(slot,source.getEquippedStack(slot));
        }
        world.spawnEntity(lootGoblin);
        source.remove(Entity.RemovalReason.DISCARDED);
    }
}
