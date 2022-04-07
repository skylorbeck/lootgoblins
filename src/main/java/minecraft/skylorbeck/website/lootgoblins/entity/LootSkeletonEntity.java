package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.Declarer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class LootSkeletonEntity extends SkeletonEntity {
    public LootSkeletonEntity(EntityType<? extends LootSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean isDisallowedInPeaceful() {
        return false;
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
        if (causedByPlayer){
            ItemStack lootStack = new ItemStack(Declarer.GOLD_BONE);//todo roll for loot table here
            ItemEntity itemEntity = new ItemEntity(this.world, this.getX(),this.getY()+1,this.getZ(),lootStack);
            this.world.spawnEntity(itemEntity);
        }
        super.dropLoot(source, causedByPlayer);
    }

    public static DefaultAttributeContainer.Builder createLootSkeletonAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }
}
