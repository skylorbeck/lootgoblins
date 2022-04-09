package website.skylorbeck.minecraft.lootgoblins.tables;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import website.skylorbeck.minecraft.lootgoblins.Declarer;

import java.util.Random;

public class LootManager {
    public static void dropLoot(LivingEntity mob, Identifier[] mobSpecificDrop) {
        if (mob.world.isClient) {
            return;
        }
        ServerWorld world = (ServerWorld) mob.world;
        Random random = world.random;

        //mob specific loot table
        int specificLoot = random.nextInt(Declarer.config.maxSpecificLoot) + 1;
        for (int i = 0; i < specificLoot; i++) {
            ItemStack lootStack = Registry.ITEM.get(mobSpecificDrop[random.nextInt(mobSpecificDrop.length)]).getDefaultStack();
            ItemEntity itemEntity = new ItemEntity(world, mob.getX(), mob.getY() + 1, mob.getZ(), lootStack);
            itemEntity.addVelocity(0, random.nextFloat() / 2f, 0);
            world.spawnEntity(itemEntity);
        }
        //enchanted books
        if (random.nextFloat() < Declarer.config.enchantedBookChance) {
            for (int i = 0; i < random.nextInt(Declarer.config.bonusEnchantedBooksMax + 1) + 1; i++) {
                ItemStack lootStack = EnchantmentHelper.enchant(random, Items.BOOK.getDefaultStack(), random.nextInt(30), random.nextBoolean());
                ItemEntity itemEntity = new ItemEntity(world, mob.getX(), mob.getY() + 1, mob.getZ(), lootStack);
                itemEntity.addVelocity(0, random.nextFloat() / 2f, 0);
                world.spawnEntity(itemEntity);
            }
        }
        //records
        if (random.nextFloat() < Declarer.config.recordChance) {
            ItemStack lootStack = Registry.ITEM.get(LootTables.musicDisks[random.nextInt(LootTables.musicDisks.length)]).getDefaultStack();
            ItemEntity itemEntity = new ItemEntity(world, mob.getX(), mob.getY() + 1, mob.getZ(), lootStack);
            itemEntity.addVelocity(0, random.nextFloat() / 2f, 0);
            world.spawnEntity(itemEntity);
        }
        //combat
        for (int i = 0; i <Declarer.config.bonusCombatLootMax ; i++) {
            if (random.nextFloat() < Declarer.config.bonusCombatLootChance) {
                Item lootItem = Registry.ITEM.get(LootTables.combat[random.nextInt(LootTables.combat.length)]);
                enchantAndSpawn(mob, world, random, lootItem);
            }
        }
        //generic table
        int randomLoot = random.nextInt(Declarer.config.bonusLootMax - 4) + 5;
        for (int i = 0; i < randomLoot; i++) {
            Item lootItem = Registry.ITEM.get(LootTables.generic[random.nextInt(LootTables.generic.length)]);
            enchantAndSpawn(mob, world, random, lootItem);
        }
    }

    private static void enchantAndSpawn(LivingEntity mob, ServerWorld world, Random random, Item lootItem) {
        ItemStack lootStack = lootItem.getDefaultStack();
        if (lootItem.getEnchantability() > 0 && random.nextFloat() < Declarer.config.bonusLootEnchantChance) {
            lootStack = EnchantmentHelper.enchant(random, lootStack, random.nextInt(30), random.nextBoolean());
        }
        ItemEntity itemEntity = new ItemEntity(world, mob.getX(), mob.getY() + 1, mob.getZ(), lootStack);
        itemEntity.addVelocity(0, random.nextFloat() / 2f, 0);
        world.spawnEntity(itemEntity);
    }

    public static void emitParticle(LivingEntity entity) {
        if (entity.world.isClient) {
            entity.world.addParticle(ParticleTypes.ELECTRIC_SPARK, entity.getParticleX(0.5), entity.getRandomBodyY() - 0.25, entity.getParticleZ(0.5), (entity.world.random.nextDouble()*0.5 - 0.25), entity.world.random.nextDouble()*0.5-0.25, (entity.world.random.nextDouble()*0.5 - 0.25));
        }
    }
}