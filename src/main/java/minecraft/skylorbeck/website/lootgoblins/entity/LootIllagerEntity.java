package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.tables.LootManager;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class LootIllagerEntity extends PillagerEntity {
    public LootIllagerEntity(EntityType<? extends PillagerEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }
    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this, LootTables.illager);
        }
        super.dropLoot(source, causedByPlayer);
    }
}
