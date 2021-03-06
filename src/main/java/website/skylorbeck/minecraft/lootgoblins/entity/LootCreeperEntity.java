package website.skylorbeck.minecraft.lootgoblins.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import website.skylorbeck.minecraft.lootgoblins.tables.LootManager;
import website.skylorbeck.minecraft.lootgoblins.tables.LootTables;

public class LootCreeperEntity extends CreeperEntity implements iLootGoblin{
    public LootCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }

    @Override
    public void tickMovement() {
        LootManager.emitParticle(this);
        super.tickMovement();
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.creeper);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
