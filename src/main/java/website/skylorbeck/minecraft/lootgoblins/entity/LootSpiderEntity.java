package website.skylorbeck.minecraft.lootgoblins.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;
import website.skylorbeck.minecraft.lootgoblins.tables.LootManager;
import website.skylorbeck.minecraft.lootgoblins.tables.LootTables;

public class LootSpiderEntity extends SpiderEntity {
    public LootSpiderEntity(EntityType<? extends SpiderEntity> entityType, World world) {
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
            LootManager.dropLoot(this, LootTables.spider);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
