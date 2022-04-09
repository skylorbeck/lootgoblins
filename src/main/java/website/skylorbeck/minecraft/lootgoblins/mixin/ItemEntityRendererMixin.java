package website.skylorbeck.minecraft.lootgoblins.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import website.skylorbeck.minecraft.lootgoblins.Declarer;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
    @Inject(method = "render", at = @At("HEAD"))
    public void injectedBeam(ItemEntity itemEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo ci) {
        float[] beamColor = Lootgoblins.BeamColors.WHITE.getColor();
        boolean doBeam = Declarer.config.doLootBeamCommon;
        switch (itemEntity.getStack().getRarity()) {
            case UNCOMMON -> {
                beamColor = Lootgoblins.BeamColors.YELLOW.getColor();
                doBeam = Declarer.config.doLootBeamUncommon;
            }
            case RARE -> {
                beamColor = Lootgoblins.BeamColors.CYAN.getColor();
                doBeam = Declarer.config.doLootBeamRare;
            }
            case EPIC -> {
                beamColor = Lootgoblins.BeamColors.PURPLE.getColor();
                doBeam = Declarer.config.doLootBeamEpic;
            }
        }
        if (doBeam && Declarer.config.doLootBeams) {
            matrixStack.push();
            matrixStack.translate(-0.5, 0, -0.5);
            BeaconBlockEntityRenderer.renderBeam(
                    matrixStack,
                    vertexConsumerProvider,
                    Lootgoblins.getIdentifier("textures/beam.png"),
                    tickDelta,
                    1,
                    itemEntity.world.getTime(),
                    0,
                    Declarer.config.lootBeamHeight,
                    beamColor,
                    Declarer.config.lootBeamInner,
                    Declarer.config.lootBeamOuter
            );
            matrixStack.pop();
        }
    }
}
