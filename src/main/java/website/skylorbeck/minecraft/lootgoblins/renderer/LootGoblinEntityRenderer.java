package website.skylorbeck.minecraft.lootgoblins.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
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