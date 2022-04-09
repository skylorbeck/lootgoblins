package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.tables.LootManager;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

public class LootSkeletonEntity extends SkeletonEntity {
    public LootSkeletonEntity(EntityType<? extends LootSkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return false;
    }

    @Override
    public boolean isConverting() {
        return false;
    }

    @Override
    public boolean isShaking() {
        return false;
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this,LootTables.skeleton);
        }
        super.dropLoot(source, causedByPlayer);
    }

}
