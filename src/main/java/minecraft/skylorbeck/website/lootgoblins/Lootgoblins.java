package minecraft.skylorbeck.website.lootgoblins;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import minecraft.skylorbeck.website.lootgoblins.entity.*;
import minecraft.skylorbeck.website.lootgoblins.tables.LootTables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import website.skylorbeck.minecraft.skylorlib.ConfigFileHandler;
import website.skylorbeck.minecraft.skylorlib.DynamicRecipeLoader;
import website.skylorbeck.minecraft.skylorlib.Registrar;

import static minecraft.skylorbeck.website.lootgoblins.Declarer.*;

public class Lootgoblins implements ModInitializer {
    public static final String MOD_ID = "lootgoblins";

    public static Identifier getIdentifier(String path) {
        return new Identifier(MOD_ID, path);
    }


    @Override
    public void onInitialize() {
        ConfigHolder<GoblinConfig> configHolder = AutoConfig.register(GoblinConfig.class, GsonConfigSerializer::new);
        config = configHolder.getConfig();
        configHolder.registerSaveListener((manager, data) ->{
            config = data;
            return ActionResult.SUCCESS;
        });
        LootTables.generic = ConfigFileHandler.initConfigFile("lootgoblins/generic_table.json", LootTables.generic);
        LootTables.musicDisks = ConfigFileHandler.initConfigFile("lootgoblins/music_table.json", LootTables.musicDisks);
        LootTables.skeleton = ConfigFileHandler.initConfigFile("lootgoblins/skeleton_table.json", LootTables.skeleton);
        LootTables.enderman = ConfigFileHandler.initConfigFile("lootgoblins/enderman_table.json", LootTables.enderman);
        LootTables.creeper = ConfigFileHandler.initConfigFile("lootgoblins/creeper_table.json", LootTables.creeper);
        LootTables.hoglin = ConfigFileHandler.initConfigFile("lootgoblins/hoglin_table.json", LootTables.hoglin);
        LootTables.illager = ConfigFileHandler.initConfigFile("lootgoblins/illager_table.json", LootTables.illager);
        LootTables.spider = ConfigFileHandler.initConfigFile("lootgoblins/enderman_table.json", LootTables.spider);
        LootTables.zombie = ConfigFileHandler.initConfigFile("lootgoblins/zombie_table.json", LootTables.zombie);

        regItem("gold_bone_", Declarer.GOLD_BONE);
        regItem("prismarine_pearl_", PRISMARINE_PEARL);

        Declarer.SMELT_GOLD_BONE = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.GOLD_BONE, Items.GOLD_INGOT,0.7f,200, DynamicRecipeLoader.furnaceTypes.smelting);

        FabricDefaultAttributeRegistry.register(LOOT_SKELETON, LootSkeletonEntity.createAbstractSkeletonAttributes());
        FabricDefaultAttributeRegistry.register(LOOT_ENDERMAN, LootEndermanEntity.createEndermanAttributes());
        FabricDefaultAttributeRegistry.register(LOOT_CREEPER, LootCreeperEntity.createCreeperAttributes());
        FabricDefaultAttributeRegistry.register(LOOT_HOGLIN, LootHoglinEntity.createHoglinAttributes());
        FabricDefaultAttributeRegistry.register(LOOT_ILLAGER, LootIllagerEntity.createPillagerAttributes());
        FabricDefaultAttributeRegistry.register(LOOT_SPIDER, LootSpiderEntity.createSpiderAttributes());
        FabricDefaultAttributeRegistry.register(LOOT_ZOMBIE, LootZombieEntity.createZombieAttributes());

        ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
            if (world.random.nextFloat() < config.goblinChance){
                if (!(entity instanceof LootSkeletonEntity) && entity instanceof SkeletonEntity skeletonEntity ) {
                    replaceEntity(skeletonEntity, LOOT_SKELETON, world);
                } else
                if (!(entity instanceof LootZombieEntity) && entity instanceof ZombieEntity zombieEntity) {
                    replaceEntity(zombieEntity, LOOT_ZOMBIE, world);
                } else
                if (!(entity instanceof LootSpiderEntity) && entity instanceof SpiderEntity spiderEntity) {
                    replaceEntity(spiderEntity, LOOT_SPIDER, world);
                } else
                if (!(entity instanceof LootCreeperEntity) && entity instanceof CreeperEntity creeperEntity) {
                    replaceEntity(creeperEntity, LOOT_CREEPER, world);
                } else
                if (!(entity instanceof LootHoglinEntity) && entity instanceof HoglinEntity hoglinEntity) {
                    replaceEntity(hoglinEntity, LOOT_HOGLIN, world);
                } else
                if (!(entity instanceof LootIllagerEntity) && entity instanceof PillagerEntity illagerEntity) {
                    replaceEntity(illagerEntity, LOOT_ILLAGER, world);
                } else
                if (!(entity instanceof LootEndermanEntity) && entity instanceof EndermanEntity endermanEntity) {
                    replaceEntity(endermanEntity, LOOT_ENDERMAN, world);
                }
            }
        }));
    }

    private void regItem(String name, Item item) {
        Registrar.regItem(name, item, MOD_ID);
    }

    private void regblock(String name, Block block) {
        Registrar.regBlock(name, block, MOD_ID);
    }

    private void replaceEntity(LivingEntity source, EntityType<?> target, ServerWorld world) {
        LivingEntity lootGoblin = (LivingEntity) target.create(world);
        lootGoblin.copyPositionAndRotation(source);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            lootGoblin.equipStack(slot, source.getEquippedStack(slot));
        }
        world.spawnEntity(lootGoblin);
        source.remove(Entity.RemovalReason.DISCARDED);
    }

    public enum BeamColors {
        WHITE(1, 1, 1),
        RED(1, 0, 0),
        GREEN(0, 1, 0),
        BLUE(0, 0, 1),
        YELLOW(1, 1, 0),
        PURPLE(1, 0, 1),
        CYAN(0, 1, 1);

        private final float[] color;

        BeamColors(float r, float g, float b) {
            this.color = new float[]{r, g, b};
        }

        public float[] getColor() {
            return this.color;
        }
    }
}