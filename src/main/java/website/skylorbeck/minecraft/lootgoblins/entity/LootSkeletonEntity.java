package website.skylorbeck.minecraft.lootgoblins.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;
import website.skylorbeck.minecraft.lootgoblins.tables.LootManager;
import website.skylorbeck.minecraft.lootgoblins.tables.LootTables;

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
    public void tickMovement() {
        LootManager.emitParticle(this);
        super.tickMovement();
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            LootManager.dropLoot(this,LootTables.skeleton);
        }
        super.dropLoot(source, causedByPlayer);
    }

}
