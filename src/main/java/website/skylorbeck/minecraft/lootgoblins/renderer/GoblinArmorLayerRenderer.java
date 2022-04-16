package website.skylorbeck.minecraft.lootgoblins.renderer;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;
import website.skylorbeck.minecraft.lootgoblins.entity.LootGoblinEntity;

public class GoblinArmorLayerRenderer extends GeoLayerRenderer {
    @SuppressWarnings("unchecked")
    public GoblinArmorLayerRenderer(IGeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }
    private static final Identifier MODEL = Lootgoblins.getIdentifier("geo/lootgoblin.geo.json");

    @SuppressWarnings("unchecked")
    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LootGoblinEntity lootGoblin = (LootGoblinEntity) entitylivingbaseIn;
        matrixStackIn.push();
        //Move or scale the model as you see fit
        matrixStackIn.scale(1.01f, 1.01f, 1.01f);
        matrixStackIn.translate(0.0d, 0.0d, 0.0d);
        if (lootGoblin.hasStackEquipped(EquipmentSlot.HEAD)) {
            ItemStack armorStack = lootGoblin.getEquippedStack(EquipmentSlot.HEAD);
            String[] armorString = Registry.ITEM.getId(armorStack.getItem()).getPath().split("_");
            RenderLayer cameo = RenderLayer.getArmorCutoutNoCull(Lootgoblins.getIdentifier("textures/entity/lootgoblin/"+ armorString[0] +"armor.png"));
            this.getRenderer().render(this.getEntityModel().getModel(MODEL), lootGoblin, partialTicks, cameo, matrixStackIn, bufferIn,
                    bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
        }
        matrixStackIn.pop();
    }
}
