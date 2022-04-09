package minecraft.skylorbeck.website.lootgoblins;

import com.google.gson.JsonObject;
import minecraft.skylorbeck.website.lootgoblins.entity.LootEndermenEntity;
import minecraft.skylorbeck.website.lootgoblins.entity.LootSkeletonEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import static minecraft.skylorbeck.website.lootgoblins.Lootgoblins.getIdentifier;

public class Declarer {
    public static GoblinConfig config = new GoblinConfig();

    public static final EntityType<LootSkeletonEntity> LOOT_SKELETON_GOLD = Registry.register(Registry.ENTITY_TYPE,getIdentifier("gold_loot_skeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LootSkeletonEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).trackRangeBlocks(8).build());
    public static final EntityType<LootEndermenEntity> LOOT_ENDERMAN_PRISMARINE = Registry.register(Registry.ENTITY_TYPE,getIdentifier("prismarine_enderman"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LootEndermenEntity::new).dimensions(EntityDimensions.fixed(0.6f, 2.9f)).trackRangeBlocks(8).build());

    public static final Item GOLD_BONE = new Item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON));
    public static JsonObject SMELT_GOLD_BONE;
    public static final Item PRISMARINE_PEARL = new EnderPearlItem(new FabricItemSettings().rarity(Rarity.RARE)){
        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack itemStack = user.getStackInHand(hand);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            user.getItemCooldownManager().set(this, 20);
            if (!world.isClient) {
                EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, user){
                    @Override
                    protected void onCollision(HitResult hitResult) {
                        //super.onCollision(hitResult);
                        for (int i = 0; i < 32; ++i) {
                            this.world.addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0, this.getZ(), this.random.nextGaussian(), 0.0, this.random.nextGaussian());
                        }
                        if (!this.world.isClient && !this.isRemoved()) {
                            for (int i = 0; i < 4 ; i++) {
                                if (world.random.nextBoolean()) {
                                    Item item = this.world.random.nextBoolean() ? Items.PRISMARINE_SHARD : Items.PRISMARINE_CRYSTALS;
                                    this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), item.getDefaultStack()));
                                }
                            }
                            this.discard();
                        }
                    }
                };
                enderPearlEntity.setItem(itemStack);
                enderPearlEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
                world.spawnEntity(enderPearlEntity);
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            return TypedActionResult.success(itemStack, world.isClient());
        }
    };
}
