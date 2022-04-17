package website.skylorbeck.minecraft.lootgoblins.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;
import website.skylorbeck.minecraft.lootgoblins.entity.LootGoblinEntity;

public class LootGoblinModel extends AnimatedGeoModel<LootGoblinEntity> {
    @Override
    public Identifier getModelLocation(LootGoblinEntity lootGoblin) {
        return Lootgoblins.getIdentifier("geo/lootgoblin.geo.json");
    }

    @Override
    public Identifier getTextureLocation(LootGoblinEntity lootGoblin) {
        return switch (lootGoblin.getVariant()){
            default -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/lootgoblin.png");
            case 1 -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/nethergoblin.png");
            case 2 -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/endgoblin.png");
            case 3 -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/armorer.png");
            case 4 -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/cake.png");
            case 5 -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/plantgoblin.png");
            case 6 -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/eastergoblin.png");
            case 7 -> Lootgoblins.getIdentifier("textures/entity/lootgoblin/witch.png");
        };
    }

    @Override
    public Identifier getAnimationFileLocation(LootGoblinEntity lootGoblin) {
        return Lootgoblins.getIdentifier("animations/lootgoblin.animation.json");
    }

    @Override
    public double getCurrentTick() {
        return super.getCurrentTick();
    }

    @Override
    public void setLivingAnimations(LootGoblinEntity lootGoblin, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(lootGoblin, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }

    @Override
    public IBone getBone(String boneName) {
        return super.getBone(boneName);
    }
}
