package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.tables.LootManager;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class LootCreeperEntity extends CreeperEntity {
    public LootCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }
    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.creeper);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
