package website.skylorbeck.minecraft.lootgoblins;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.command.argument.EntitySummonArgumentType;
import net.minecraft.command.suggestion.SuggestionProviders;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;
import website.skylorbeck.minecraft.lootgoblins.entity.*;
import website.skylorbeck.minecraft.lootgoblins.tables.LootTables;
import website.skylorbeck.minecraft.skylorlib.ConfigFileHandler;
import website.skylorbeck.minecraft.skylorlib.DynamicRecipeLoader;
import website.skylorbeck.minecraft.skylorlib.Registrar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class Lootgoblins implements ModInitializer {
    public static final String MOD_ID = "lootgoblins";

    public static Identifier getIdentifier(String path) {
        return new Identifier(MOD_ID, path);
    }


    @Override
    public void onInitialize() {
        GeckoLib.initialize();

        ConfigHolder<GoblinConfig> configHolder = AutoConfig.register(GoblinConfig.class, GsonConfigSerializer::new);
        Declarer.config = configHolder.getConfig();
        configHolder.registerSaveListener((manager, data) -> {
            Declarer.config = data;
            return ActionResult.SUCCESS;
        });
        if (FabricLoader.getInstance().isModLoaded("sentimentality3")) {
            ArrayList<Identifier> sids = new ArrayList<>(Arrays.stream(LootTables.generic).toList());
            sids.add(new Identifier("sentimentality3", "monuple_compressed_cobbled_deepslate"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_gravel"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_netherrack"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_granite"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_andesite"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_dirt"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_diorite"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_sand"));
            sids.add(new Identifier("sentimentality3", "monuple_compressed_cobblestone"));
            sids.add(new Identifier("sentimentality3", "double_compressed_cobbled_deepslate"));
            sids.add(new Identifier("sentimentality3", "double_compressed_gravel"));
            sids.add(new Identifier("sentimentality3", "double_compressed_netherrack"));
            sids.add(new Identifier("sentimentality3", "double_compressed_granite"));
            sids.add(new Identifier("sentimentality3", "double_compressed_andesite"));
            sids.add(new Identifier("sentimentality3", "double_compressed_dirt"));
            sids.add(new Identifier("sentimentality3", "double_compressed_diorite"));
            sids.add(new Identifier("sentimentality3", "double_compressed_sand"));
            sids.add(new Identifier("sentimentality3", "double_compressed_cobblestone"));
            LootTables.generic = sids.toArray(new Identifier[0]);
            sids = new ArrayList<>(Arrays.stream(LootTables.combat).toList());
            sids.add(new Identifier("sentimentality3", "diorite_axe"));
            sids.add(new Identifier("sentimentality3", "diorite_shovel"));
            sids.add(new Identifier("sentimentality3", "diorite_sword"));
            sids.add(new Identifier("sentimentality3", "diorite_pick"));
            sids.add(new Identifier("sentimentality3", "andesite_axe"));
            sids.add(new Identifier("sentimentality3", "andesite_shovel"));
            sids.add(new Identifier("sentimentality3", "andesite_sword"));
            sids.add(new Identifier("sentimentality3", "andesite_pick"));
            sids.add(new Identifier("sentimentality3", "amethyst_axe"));
            sids.add(new Identifier("sentimentality3", "amethyst_shovel"));
            sids.add(new Identifier("sentimentality3", "amethyst_sword"));
            sids.add(new Identifier("sentimentality3", "amethyst_pick"));
            sids.add(new Identifier("sentimentality3", "granite_axe"));
            sids.add(new Identifier("sentimentality3", "granite_shovel"));
            sids.add(new Identifier("sentimentality3", "granite_sword"));
            sids.add(new Identifier("sentimentality3", "granite_pick"));
            sids.add(new Identifier("sentimentality3", "copper_axe"));
            sids.add(new Identifier("sentimentality3", "copper_shovel"));
            sids.add(new Identifier("sentimentality3", "copper_sword"));
            sids.add(new Identifier("sentimentality3", "copper_pick"));
            sids.add(new Identifier("sentimentality3", "quartz_axe"));
            sids.add(new Identifier("sentimentality3", "quartz_shovel"));
            sids.add(new Identifier("sentimentality3", "quartz_sword"));
            sids.add(new Identifier("sentimentality3", "quartz_pick"));
            sids.add(new Identifier("sentimentality3", "emerald_axe"));
            sids.add(new Identifier("sentimentality3", "emerald_shovel"));
            sids.add(new Identifier("sentimentality3", "emerald_sword"));
            sids.add(new Identifier("sentimentality3", "emerald_pick"));
            sids.add(new Identifier("sentimentality3", "lapis_axe"));
            sids.add(new Identifier("sentimentality3", "lapis_shovel"));
            sids.add(new Identifier("sentimentality3", "lapis_sword"));
            sids.add(new Identifier("sentimentality3", "lapis_pick"));
            sids.add(new Identifier("sentimentality3", "redstone_axe"));
            sids.add(new Identifier("sentimentality3", "redstone_shovel"));
            sids.add(new Identifier("sentimentality3", "redstone_sword"));
            sids.add(new Identifier("sentimentality3", "redstone_pick"));
            sids.add(new Identifier("sentimentality3", "netherrack_axe"));
            sids.add(new Identifier("sentimentality3", "netherrack_shovel"));
            sids.add(new Identifier("sentimentality3", "netherrack_sword"));
            sids.add(new Identifier("sentimentality3", "netherrack_pick"));
            sids.add(new Identifier("sentimentality3", "netherbrick_axe"));
            sids.add(new Identifier("sentimentality3", "netherbrick_shovel"));
            sids.add(new Identifier("sentimentality3", "netherbrick_sword"));
            sids.add(new Identifier("sentimentality3", "netherbrick_pick"));
            sids.add(new Identifier("sentimentality3", "rednetherbrick_axe"));
            sids.add(new Identifier("sentimentality3", "rednetherbrick_shovel"));
            sids.add(new Identifier("sentimentality3", "rednetherbrick_sword"));
            sids.add(new Identifier("sentimentality3", "rednetherbrick_pick"));
            sids.add(new Identifier("sentimentality3", "sandstone_axe"));
            sids.add(new Identifier("sentimentality3", "sandstone_shovel"));
            sids.add(new Identifier("sentimentality3", "sandstone_sword"));
            sids.add(new Identifier("sentimentality3", "sandstone_pick"));
            sids.add(new Identifier("sentimentality3", "redsandstone_axe"));
            sids.add(new Identifier("sentimentality3", "redsandstone_shovel"));
            sids.add(new Identifier("sentimentality3", "redsandstone_sword"));
            sids.add(new Identifier("sentimentality3", "redsandstone_pick"));
            sids.add(new Identifier("sentimentality3", "flint_axe"));
            sids.add(new Identifier("sentimentality3", "flint_shovel"));
            sids.add(new Identifier("sentimentality3", "flint_sword"));
            sids.add(new Identifier("sentimentality3", "flint_pick"));
            sids.add(new Identifier("sentimentality3", "blackstone_axe"));
            sids.add(new Identifier("sentimentality3", "blackstone_shovel"));
            sids.add(new Identifier("sentimentality3", "blackstone_sword"));
            sids.add(new Identifier("sentimentality3", "blackstone_pick"));
            sids.add(new Identifier("sentimentality3", "basalt_axe"));
            sids.add(new Identifier("sentimentality3", "basalt_shovel"));
            sids.add(new Identifier("sentimentality3", "basalt_sword"));
            sids.add(new Identifier("sentimentality3", "basalt_pick"));
            sids.add(new Identifier("sentimentality3", "endstone_axe"));
            sids.add(new Identifier("sentimentality3", "endstone_shovel"));
            sids.add(new Identifier("sentimentality3", "endstone_sword"));
            sids.add(new Identifier("sentimentality3", "endstone_pick"));
            sids.add(new Identifier("sentimentality3", "warped_axe"));
            sids.add(new Identifier("sentimentality3", "warped_shovel"));
            sids.add(new Identifier("sentimentality3", "warped_sword"));
            sids.add(new Identifier("sentimentality3", "warped_pick"));
            sids.add(new Identifier("sentimentality3", "crimson_axe"));
            sids.add(new Identifier("sentimentality3", "crimson_shovel"));
            sids.add(new Identifier("sentimentality3", "crimson_sword"));
            sids.add(new Identifier("sentimentality3", "crimson_pick"));
            sids.add(new Identifier("sentimentality3", "deepslate_axe"));
            sids.add(new Identifier("sentimentality3", "deepslate_shovel"));
            sids.add(new Identifier("sentimentality3", "deepslate_sword"));
            sids.add(new Identifier("sentimentality3", "deepslate_pick"));
            sids.add(new Identifier("sentimentality3", "copper_helmet"));
            sids.add(new Identifier("sentimentality3", "copper_chestplate"));
            sids.add(new Identifier("sentimentality3", "copper_leggings"));
            sids.add(new Identifier("sentimentality3", "copper_boots"));
            sids.add(new Identifier("sentimentality3", "wool_helmet"));
            sids.add(new Identifier("sentimentality3", "wool_chestplate"));
            sids.add(new Identifier("sentimentality3", "wool_leggings"));
            sids.add(new Identifier("sentimentality3", "wool_boots"));
            LootTables.combat = sids.toArray(new Identifier[0]);
        }
        if (FabricLoader.getInstance().isModLoaded("magehand")) {
            ArrayList<Identifier> hands = new ArrayList<>(Arrays.stream(LootTables.blacklist).toList());
            hands.add(new Identifier("magehand", "copper"));
            hands.add(new Identifier("magehand", "iron"));
            hands.add(new Identifier("magehand", "gold"));
            hands.add(new Identifier("magehand", "diamond"));
            hands.add(new Identifier("magehand", "amethyst"));
            LootTables.blacklist = hands.toArray(new Identifier[0]);
        }
        LootTables.generic = ConfigFileHandler.initConfigFile("lootgoblins/generic_table.json", LootTables.generic);
        LootTables.combat = ConfigFileHandler.initConfigFile("lootgoblins/combat_table.json", LootTables.combat);
        LootTables.musicDisks = ConfigFileHandler.initConfigFile("lootgoblins/music_table.json", LootTables.musicDisks);
        LootTables.skeleton = ConfigFileHandler.initConfigFile("lootgoblins/skeleton_table.json", LootTables.skeleton);
        LootTables.enderman = ConfigFileHandler.initConfigFile("lootgoblins/enderman_table.json", LootTables.enderman);
        LootTables.creeper = ConfigFileHandler.initConfigFile("lootgoblins/creeper_table.json", LootTables.creeper);
        LootTables.hoglin = ConfigFileHandler.initConfigFile("lootgoblins/hoglin_table.json", LootTables.hoglin);
        LootTables.illager = ConfigFileHandler.initConfigFile("lootgoblins/illager_table.json", LootTables.illager);
        LootTables.spider = ConfigFileHandler.initConfigFile("lootgoblins/spider_table.json", LootTables.spider);
        LootTables.zombie = ConfigFileHandler.initConfigFile("lootgoblins/zombie_table.json", LootTables.zombie);
        LootTables.loot_goblin = ConfigFileHandler.initConfigFile("lootgoblins/overworld_goblin_table.json", LootTables.loot_goblin);
        LootTables.nether_goblin = ConfigFileHandler.initConfigFile("lootgoblins/nether_goblin_table.json", LootTables.nether_goblin);
        LootTables.ender_goblin = ConfigFileHandler.initConfigFile("lootgoblins/end_goblin_table.json", LootTables.ender_goblin);
        LootTables.cake = ConfigFileHandler.initConfigFile("lootgoblins/cake_table.json", LootTables.cake);
        LootTables.plant_goblin = ConfigFileHandler.initConfigFile("lootgoblins/plant_goblin.json", LootTables.plant_goblin);
        LootTables.witch_goblin = ConfigFileHandler.initConfigFile("lootgoblins/witch_goblin.json", LootTables.witch_goblin);
        LootTables.blacklist = ConfigFileHandler.initConfigFile("lootgoblins/blacklist.json", LootTables.blacklist);

        regItem("gold_bone_", Declarer.GOLD_BONE);
        regItem("prismarine_pearl_", Declarer.PRISMARINE_PEARL);
        regItem("iron_eye_", Declarer.IRON_EYE);
        regItem("quartzchop_", Declarer.QUARTZCHOP);
        regItem("emerald_crossbow_", Declarer.EMERALD_CROSSBOW);
        regItem("red_bomb_", Declarer.RED_BOMB);
        regItem("stoneflesh_", Declarer.STONEFLESH);

        Declarer.SMELT_GOLD_BONE = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.GOLD_BONE, Items.GOLD_INGOT, 1, 200, DynamicRecipeLoader.furnaceTypes.smelting);
        Declarer.SMELT_IRON_EYE = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.IRON_EYE, Items.IRON_INGOT, 1, 200, DynamicRecipeLoader.furnaceTypes.smelting);
        Declarer.SMELT_QUARTZCHOP = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.QUARTZCHOP, Items.QUARTZ, 1, 200, DynamicRecipeLoader.furnaceTypes.smelting);
        Declarer.SMELT_EMERALD_CROSSBOW = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.EMERALD_CROSSBOW, Items.EMERALD, 1, 200, DynamicRecipeLoader.furnaceTypes.smelting);
        Declarer.SMELT_STONEFLESH = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.STONEFLESH, Items.LAPIS_LAZULI, 1, 200, DynamicRecipeLoader.furnaceTypes.smelting);
        Declarer.BLAST_GOLD_BONE = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.GOLD_BONE, Items.GOLD_INGOT, 1, 200, DynamicRecipeLoader.furnaceTypes.blasting);
        Declarer.BLAST_IRON_EYE = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.IRON_EYE, Items.IRON_INGOT, 1, 200, DynamicRecipeLoader.furnaceTypes.blasting);
        Declarer.BLAST_QUARTZCHOP = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.QUARTZCHOP, Items.QUARTZ, 1, 200, DynamicRecipeLoader.furnaceTypes.blasting);
        Declarer.BLAST_EMERALD_CROSSBOW = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.EMERALD_CROSSBOW, Items.EMERALD, 1, 200, DynamicRecipeLoader.furnaceTypes.blasting);
        Declarer.BLAST_STONEFLESH = DynamicRecipeLoader.createSmeltingRecipeJson(Declarer.STONEFLESH, Items.LAPIS_LAZULI, 1, 200, DynamicRecipeLoader.furnaceTypes.blasting);

        FabricDefaultAttributeRegistry.register(Declarer.LOOT_SKELETON, LootSkeletonEntity.createAbstractSkeletonAttributes());
        FabricDefaultAttributeRegistry.register(Declarer.LOOT_ENDERMAN, LootEndermanEntity.createEndermanAttributes());
        FabricDefaultAttributeRegistry.register(Declarer.LOOT_CREEPER, LootCreeperEntity.createCreeperAttributes());
        FabricDefaultAttributeRegistry.register(Declarer.LOOT_HOGLIN, LootHoglinEntity.createHoglinAttributes());
        FabricDefaultAttributeRegistry.register(Declarer.LOOT_ILLAGER, LootIllagerEntity.createPillagerAttributes());
        FabricDefaultAttributeRegistry.register(Declarer.LOOT_SPIDER, LootSpiderEntity.createSpiderAttributes());
        FabricDefaultAttributeRegistry.register(Declarer.LOOT_ZOMBIE, LootZombieEntity.createZombieAttributes());
        FabricDefaultAttributeRegistry.register(Declarer.LOOT_GOBLIN, LootGoblinEntity.createMobAttributes());

        ServerPlayNetworking.registerGlobalReceiver(getIdentifier("itemlink"), ((server, player, handler, buf, responseSender) -> {
            Text text = buf.readText();
            Text playerDisplayName = player.getDisplayName();
            MutableText translatableText = Text.translatable("lootgoblins.itemlink.chat", playerDisplayName, text);
            server.getPlayerManager().getPlayerList().forEach(serverPlayerEntity ->
                    serverPlayerEntity.sendMessage(translatableText));
//            server.getPlayerManager().sendToAll(new GameMessageS2CPacket(translatableText, player.getUuid()));
        }
        ));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("lootgoblins")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(literal("blacklist")
                            .then(literal("add")
                                    .then(argument("entity", EntitySummonArgumentType.entitySummon())
                                            .suggests(SuggestionProviders.SUMMONABLE_ENTITIES)
                                            .executes((context -> {
                                                ArrayList<Identifier> mobs = new ArrayList<>(Arrays.stream(LootTables.blacklist).toList());
                                                mobs.add(EntitySummonArgumentType.getEntitySummon(context, "entity"));
                                                LootTables.blacklist = mobs.toArray(new Identifier[0]);
                                                GsonBuilder gsonBuilder = new GsonBuilder();
                                                gsonBuilder.setPrettyPrinting();
                                                Gson gson = gsonBuilder.create();
                                                try {
                                                    Files.write(Paths.get("config/lootgoblins/blacklist.json"), gson.toJson(LootTables.blacklist).getBytes());
                                                    context.getSource().sendFeedback(Text.of("Added " + EntitySummonArgumentType.getEntitySummon(context, "entity").getPath() + " to blacklist.json"), true);
                                                    return 1;
                                                } catch (IOException e) {
                                                    context.getSource().sendFeedback(Text.of("Failed to add to config file. " + e.getMessage()), false);
                                                    return 0;
                                                }
                                            }))
                                    ))));
            dispatcher.register(literal("lootgoblins")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(literal("blacklist")
                            .then(literal("remove")
                                    .then(argument("entity", EntitySummonArgumentType.entitySummon())
                                            .suggests(SuggestionProviders.SUMMONABLE_ENTITIES)
                                            .executes((context -> {
                                                ArrayList<Identifier> mobs = new ArrayList<>(Arrays.stream(LootTables.blacklist).toList());
                                                mobs.remove(EntitySummonArgumentType.getEntitySummon(context, "entity"));
                                                LootTables.blacklist = mobs.toArray(new Identifier[0]);
                                                GsonBuilder gsonBuilder = new GsonBuilder();
                                                gsonBuilder.setPrettyPrinting();
                                                Gson gson = gsonBuilder.create();
                                                try {
                                                    Files.write(Paths.get("config/lootgoblins/blacklist.json"), gson.toJson(LootTables.blacklist).getBytes());
                                                    context.getSource().sendFeedback(Text.of("Removed " + EntitySummonArgumentType.getEntitySummon(context, "entity").getPath() + " from blacklist.json"), true);
                                                    return 1;
                                                } catch (IOException e) {
                                                    context.getSource().sendFeedback(Text.of("Failed to remove from config file. " + e.getMessage()), false);
                                                    return 0;
                                                }
                                            }))
                                    ))));
            dispatcher.register(literal("lootgoblins")
                    .then(literal("blacklist")
                            .then(literal("list")
                                            .executes((context -> {
                                                ArrayList<Identifier> mobs = new ArrayList<>(Arrays.stream(LootTables.blacklist).toList());
                                                StringBuilder names = new StringBuilder("LootGoblin Blacklisted mobs: ");
                                                names.append(mobs.get(0));
                                                for (int i = 1; i < mobs.size(); i++) {
                                                    names.append(", ").append(mobs.get(i));
                                                }
                                                context.getSource().sendFeedback(Text.of(names.toString()),false);
                                                return 1;
                                            }))
                                    )));
        });
    }
    private void regItem(String name, Item item) {
        Registrar.regItem(name, item, MOD_ID);
    }

    private void regblock(String name, Block block) {
        Registrar.regBlock(name, block, MOD_ID);
    }

    public static LivingEntity replaceEntity(LivingEntity source, EntityType<?> target, ServerWorld world) {
        LivingEntity lootGoblin = (LivingEntity) target.create(world);
        lootGoblin.copyPositionAndRotation(source);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            lootGoblin.equipStack(slot, source.getEquippedStack(slot));
        }
        int random = world.random.nextInt(goblinNames.length);
        lootGoblin.setCustomName(Text.of(goblinNames[random] + (random<=4?" Noble Dog Knight": " The " + titleA[world.random.nextInt(titleA.length)] + titleB[world.random.nextInt(titleB.length)])));
        world.spawnEntity(lootGoblin);
        source.remove(Entity.RemovalReason.DISCARDED);
        return lootGoblin;
    }

    public static String[] goblinNames = {
            "Kynan",
            "Scar13t",
            "Striker",
            "BradleyChauvin",
            "NoobGamer",
            "Apkap",
            "Brieh",
            "Bruvzenq",
            "Chert",
            "Chuz",
            "Cyail",
            "Cyaz",
            "Cyon",
            "Cyublic",
            "Delia",
            "Dror",
            "Drubzebs",
            "Druphial",
            "Eerboc",
            "Epnaaq",
            "Ets",
            "Foxai",
            "Glaaknealb",
            "Gliavags",
            "Gnubs",
            "Graknup",
            "Griurkain",
            "Grom",
            "Halbigz",
            "Iaptoixe",
            "Ippip",
            "Jaarmup",
            "Joltu",
            "Jukt",
            "Kiovzoikt",
            "Kyic",
            "Loklux",
            "Luz",
            "Muftiafi",
            "Nikka",
            "Osenx",
            "Phiegs",
            "Phusreng",
            "Publem",
            "Qahx",
            "Qraq",
            "Raabsofz",
            "Ram",
            "Reaeart",
            "Rebhe",
            "Sliort",
            "Sriq",
            "Stoszaats",
            "Stribbaart",
            "Taagnegs",
            "Thihkeaxe",
            "Treatleexai",
            "Treerk",
            "Trigs",
            "Trippox",
            "Tron",
            "Trukx",
            "Trurm",
            "Tyokle",
            "Uprat",
            "Vrert",
            "Waarxea",
            "Wanarxee",
            "Wroibunk",
            "Xen",
            "Xird",
            "Yobkiar",
            "Zidnazz",
            "Zix"
    };

    public static String[] titleA = new String[]{
            "Aether",
            "Aspect",
            "Azure",
            "Blood",
            "Chain",
            "Cloud",
            "Crimson",
            "Dark",
            "Distant",
            "Dusk",
            "Elder",
            "Elf",
            "Free",
            "Furor",
            "God",
            "Haven",
            "Marsh",
            "Mist",
            "Morn",
            "Onyx",
            "Other",
            "Rage",
            "Spark",
            "Star",
            "Still",
            "Sun",
            "Swift",
            "Totem",
            "Wild",
            "Wonder",
            "High",
            "Low",
            "Slow",
            "Quick",
            "Obsidian",
            "Iron",
            "Gold",
            "Silver",
            "Gray",
    };
    public static String[] titleB = new String[]{
            " Blood",
            " Child",
            " Gift",
            " Heart",
            " One",
            " Soul",
            "born",
            "bough",
            "dancer",
            "mark",
            "ridge",
            "snarl",
            "snout",
            "sorrow",
            "sun",
            "swift",
            "wind",
            "earth",
            " Voice",
            "cry",
            "moon",
            "tooth",
            "chosen",
    };

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
//known issues
//goblins replace entity on save-load, unfixable
//todo emerald crossbow reloads weird