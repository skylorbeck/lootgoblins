package website.skylorbeck.minecraft.lootgoblins.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import website.skylorbeck.minecraft.lootgoblins.Declarer;
import website.skylorbeck.minecraft.lootgoblins.entity.LootGoblinEntity;
import website.skylorbeck.minecraft.lootgoblins.entity.iLootGoblin;
import website.skylorbeck.minecraft.lootgoblins.tables.LootTables;

import static website.skylorbeck.minecraft.lootgoblins.Lootgoblins.replaceEntity;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "spawnEntity", at = @At("HEAD"))
    public void modspawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        ServerWorld world = (ServerWorld) (Object) this;
        if (world.random.nextFloat() < Declarer.config.goblinChance) {

            if (!(entity instanceof iLootGoblin) && !(entity.isPlayer())) {
                for (Identifier typeID : LootTables.blacklist) {
                    EntityType<?> entityType = Registry.ENTITY_TYPE.get(typeID);
                    if (entity.getType() == entityType){
                        return;
                    }
                }
                if (entity instanceof SkeletonEntity skeletonEntity) {
                    replaceEntity(skeletonEntity, Declarer.LOOT_SKELETON, world);
                } else if (entity instanceof ZombieEntity zombieEntity) {
                    replaceEntity(zombieEntity, Declarer.LOOT_ZOMBIE, world);
                } else if (entity instanceof SpiderEntity spiderEntity) {
                    replaceEntity(spiderEntity, Declarer.LOOT_SPIDER, world);
                } else if (entity instanceof CreeperEntity creeperEntity) {
                    replaceEntity(creeperEntity, Declarer.LOOT_CREEPER, world);
                } else if (entity instanceof HoglinEntity hoglinEntity) {
                    replaceEntity(hoglinEntity, Declarer.LOOT_HOGLIN, world);
                } else if (entity instanceof PillagerEntity illagerEntity) {
                    replaceEntity(illagerEntity, Declarer.LOOT_ILLAGER, world);
                } else if (entity instanceof EndermanEntity endermanEntity) {
                    replaceEntity(endermanEntity, Declarer.LOOT_ENDERMAN, world);
                } else if (entity instanceof LivingEntity) {
                    LootGoblinEntity lootGoblin = (LootGoblinEntity) replaceEntity((LivingEntity) entity, Declarer.LOOT_GOBLIN, world);
                    lootGoblin.setVariant(world.random.nextInt(8));
                }
            }
        }
    }
}