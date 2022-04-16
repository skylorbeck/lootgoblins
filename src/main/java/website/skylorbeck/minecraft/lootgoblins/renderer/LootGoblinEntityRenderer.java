package website.skylorbeck.minecraft.lootgoblins.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.text.Text;
import net.minecraft.util.math.Matrix4f;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import website.skylorbeck.minecraft.lootgoblins.entity.LootGoblinEntity;
import website.skylorbeck.minecraft.lootgoblins.model.LootGoblinModel;

public class LootGoblinEntityRenderer extends GeoEntityRenderer<LootGoblinEntity> {
    public LootGoblinEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new LootGoblinModel());
        this.addLayer(new GoblinArmorLayerRenderer(this));
    }

    @Override
    protected void renderLabelIfPresent(LootGoblinEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        TitleRenderer.renderLabelIfPresent(entity,text,matrices,vertexConsumers,light,this.dispatcher,this.getTextRenderer());
    }
}