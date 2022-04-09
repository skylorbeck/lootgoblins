package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.tables.LootManager;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class LootZombieEntity extends ZombieEntity {
    public LootZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return false;
    }

    @Override
    public boolean isConvertingInWater() {
        return false;
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.zombie);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
