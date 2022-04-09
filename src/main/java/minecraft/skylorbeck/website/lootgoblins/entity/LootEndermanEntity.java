package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.tables.LootManager;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.world.World;

public class LootEndermanEntity extends EndermanEntity {
    public LootEndermanEntity(EntityType<? extends EndermanEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }

    @Override
    public boolean hurtByWater() {
        return false;
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.enderman);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
