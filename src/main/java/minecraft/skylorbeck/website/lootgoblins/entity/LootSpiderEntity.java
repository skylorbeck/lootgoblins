package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.tables.LootManager;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

public class LootSpiderEntity extends SpiderEntity {
    public LootSpiderEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }
    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.spider);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
