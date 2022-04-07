package minecraft.skylorbeck.website.lootgoblins;

import minecraft.skylorbeck.website.lootgoblins.entity.LootSkeletonEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import static minecraft.skylorbeck.website.lootgoblins.Declarer.LOOT_SKELETON_GOLD;

public class Lootgoblins implements ModInitializer {
    public static final String MOD_ID = "lootgoblins";
    public static Identifier getIdentifier(String path) {
        return new Identifier(MOD_ID,path);
    }

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(LOOT_SKELETON_GOLD,LootSkeletonEntity.createLootSkeletonAttributes());

        ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
            if (entity instanceof SkeletonEntity skeletonEntity && world.random.nextInt(100)<50){//todo config setting here
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

    public enum BeamColors{
        WHITE(1,1,1),
        RED(1,0,0),
        GREEN(0,1,0),
        BLUE(0,0,1),
        YELLOW(1,1,0),
        PURPLE(1,0,1),
        CYAN(0,1,1);

        private final float[] color;
        BeamColors(float r, float g, float b){
            this.color = new float[]{r,g,b};
        }

        public float[] getColor(){
            return this.color;
        }
    }
}
