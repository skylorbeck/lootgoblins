package website.skylorbeck.minecraft.lootgoblins.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.world.World;
import website.skylorbeck.minecraft.lootgoblins.tables.LootManager;
import website.skylorbeck.minecraft.lootgoblins.tables.LootTables;

public class LootHoglinEntity extends HoglinEntity implements iLootGoblin {
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
    public void tickMovement() {
        LootManager.emitParticle(this);
        super.tickMovement();
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.hoglin);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
