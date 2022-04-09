package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.tables.LootManager;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.world.World;

public class LootHoglinEntity extends HoglinEntity {
    public LootHoglinEntity(EntityType<? extends HoglinEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
        this.setImmuneToZombification(true);
    }

    @Override
    public boolean canConvert() {
        return false;
    }


    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.hoglin);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
