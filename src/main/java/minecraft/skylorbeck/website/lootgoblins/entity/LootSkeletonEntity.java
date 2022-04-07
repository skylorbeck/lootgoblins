package minecraft.skylorbeck.website.lootgoblins.entity;

import minecraft.skylorbeck.website.lootgoblins.Declarer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Random;

public class LootSkeletonEntity extends SkeletonEntity {
    public LootSkeletonEntity(EntityType<? extends LootSkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
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
            Random random = this.world.random;
            int boneAmt = random.nextInt(Declarer.config.skeletonStuff.maxGoldBone - Declarer.config.skeletonStuff.minGoldBone)+Declarer.config.skeletonStuff.minGoldBone;
            for (int i = 0; i < boneAmt ; i++) {
                ItemStack lootStack = new ItemStack(Declarer.GOLD_BONE);
                ItemEntity itemEntity = new ItemEntity(this.world, this.getX(),this.getY()+1,this.getZ(),lootStack);
                itemEntity.addVelocity(0,random.nextFloat()/2f,0);
                this.world.spawnEntity(itemEntity);
            }

            if (random.nextFloat()<Declarer.config.enchantedBookChance){
                for (int i = 0; i < random.nextInt(Declarer.config.bonusEnchantedBooksMax+1)+1 ; i++) {
                    ItemStack lootStack =EnchantmentHelper.enchant(random, new ItemStack(Items.BOOK),random.nextInt(30),random.nextBoolean());
                    ItemEntity itemEntity = new ItemEntity(this.world, this.getX(),this.getY()+1,this.getZ(),lootStack);
                    itemEntity.addVelocity(0,random.nextFloat()/2f,0);
                    this.world.spawnEntity(itemEntity);
                }
            }

            int randomLoot = random.nextInt(Declarer.config.skeletonStuff.bonusLootMax)+3;
            for (int i = 0; i < randomLoot; i++) {
                Item lootItem = Registry.ITEM.get(Declarer.config.loot[random.nextInt(Declarer.config.loot.length)]);
                ItemStack lootStack = new ItemStack(lootItem);
                if (lootItem.getEnchantability() > 0 && random.nextFloat() < Declarer.config.bonusLootEnchantChance) {
                    lootStack = EnchantmentHelper.enchant(random, lootStack, random.nextInt(30), random.nextBoolean());
                }
                ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), this.getY() + 1, this.getZ(), lootStack);
                itemEntity.addVelocity(0, random.nextFloat() / 2f, 0);
                this.world.spawnEntity(itemEntity);
            }

        }
        super.dropLoot(source, causedByPlayer);
    }

    public static DefaultAttributeContainer.Builder createLootSkeletonAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }
}
