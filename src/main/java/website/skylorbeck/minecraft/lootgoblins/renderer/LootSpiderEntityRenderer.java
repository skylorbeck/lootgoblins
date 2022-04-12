/*
 * Decompiled with CFR 0.1.0 (FabricMC a830a72d).
 */
package website.skylorbeck.minecraft.lootgoblins.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SpiderEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;

@Environment(value=EnvType.CLIENT)
public class LootSpiderEntityRenderer<T extends SpiderEntity> extends SpiderEntityRenderer<T> {
    private static final Identifier TEXTURE = Lootgoblins.getIdentifier("textures/entity/spider/iron.png");
    public LootSpiderEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(SpiderEntity spiderEntity) {
        return TEXTURE;
    }

    @Override
    protected void renderLabelIfPresent(T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        TitleRenderer.renderLabelIfPresent(entity,text,matrices,vertexConsumers,light,this.dispatcher,this.getTextRenderer());
    }
}

