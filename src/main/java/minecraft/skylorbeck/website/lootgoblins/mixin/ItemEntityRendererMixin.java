package minecraft.skylorbeck.website.lootgoblins.mixin;

import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import website.skylorbeck.minecraft.skylorlib.Color;
import website.skylorbeck.minecraft.skylorlib.SkylorLib;

import java.lang.annotation.Target;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
    @Inject(method = "render", at = @At("HEAD"))
    public void renderBeam(ItemEntity itemEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo ci) {
        float[] beamColor = Lootgoblins.BeamColors.WHITE.getColor();//todo config for enabling colors
        switch (itemEntity.getStack().getRarity()){
            case UNCOMMON -> beamColor = Lootgoblins.BeamColors.YELLOW.getColor();
            case RARE -> beamColor = Lootgoblins.BeamColors.CYAN.getColor();
            case EPIC -> beamColor = Lootgoblins.BeamColors.PURPLE.getColor();
        }

        matrixStack.push();
        matrixStack.translate(-0.5, 0,-0.5);
        BeaconBlockEntityRenderer.renderBeam(
                matrixStack,
                vertexConsumerProvider,
                Lootgoblins.getIdentifier("textures/beam.png"),
                tickDelta,
                1,
                itemEntity.world.getTime(),
                0,
                4,//todo config for height
                beamColor,
                0.015f,//todo config for changing size
                0.25f
        );
        matrixStack.pop();
    }


}
