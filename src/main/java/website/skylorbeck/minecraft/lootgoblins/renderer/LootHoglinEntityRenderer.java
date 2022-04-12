/*
 * Decompiled with CFR 0.1.0 (FabricMC a830a72d).
 */
package website.skylorbeck.minecraft.lootgoblins.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;

@Environment(value=EnvType.CLIENT)
public class LootHoglinEntityRenderer extends HoglinEntityRenderer {
    private static final Identifier TEXTURE = Lootgoblins.getIdentifier("textures/entity/hoglin/quartz.png");
    public LootHoglinEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(HoglinEntity HoglinEntity) {
        return TEXTURE;
    }

    @Override
    protected void renderLabelIfPresent(HoglinEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        TitleRenderer.renderLabelIfPresent(entity,text,matrices,vertexConsumers,light,this.dispatcher,this.getTextRenderer());
    }
}

