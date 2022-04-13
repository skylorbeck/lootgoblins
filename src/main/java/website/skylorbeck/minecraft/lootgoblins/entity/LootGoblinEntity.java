package website.skylorbeck.minecraft.lootgoblins.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;
import website.skylorbeck.minecraft.lootgoblins.tables.LootManager;
import website.skylorbeck.minecraft.lootgoblins.tables.LootTables;

public class LootGoblinEntity extends HostileEntity implements IAnimatable,iLootGoblin {
    private final AnimationFactory factory = new AnimationFactory(this);
    public static final TrackedData<Integer> VARIANT = DataTracker.registerData(LootGoblinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    //todo variants
    // music
    // sapling
    // cow

    public LootGoblinEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 100;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT, 0);
    }
    public void setVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    public int getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0f)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35f);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(nbt.getInt("Variant"));
    }
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant());
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.ENTITY_WITCH_HURT,1,2f);
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ENTITY_WITCH_AMBIENT,1,2f);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP,1,2);
        super.playStepSound(pos, state);
    }

    @Override
    public void tickMovement() {
        LootManager.emitParticle(this);
        super.tickMovement();
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        if (causedByPlayer) {
            Identifier[] lootTable = switch (this.getVariant()) {
                default -> LootTables.loot_goblin;
                case 1 -> LootTables.nether_goblin;
                case 2 -> LootTables.ender_goblin;
                case 3 -> LootTables.combat;
                case 4 -> LootTables.cake;
            };
            LootManager.dropLoot(this, lootTable);
        }
        super.dropLoot(source, causedByPlayer);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25f));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PlayerEntity.class, 8, 1, 1));
        this.goalSelector.add(8, new WanderAroundGoal(this,1,60));
        this.goalSelector.add(8, new LookAroundGoal(this));
        super.initGoals();
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "locomotion_controller", 5, this::locomotion_predicate));
    }

    public <E extends IAnimatable> PlayState locomotion_predicate(AnimationEvent<E> event) {
        LootGoblinEntity lootGoblin = (LootGoblinEntity) event.getAnimatable();
        if (lootGoblin == null) {
            return PlayState.CONTINUE;
        }
        if (event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lootgoblin.run"));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lootgoblin.idle"));
            /*event.getController().clearAnimationCache();
            return PlayState.STOP;*/
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
