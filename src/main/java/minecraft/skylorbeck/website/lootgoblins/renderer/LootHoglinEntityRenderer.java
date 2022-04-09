/*
 * Decompiled with CFR 0.1.0 (FabricMC a830a72d).
 */
package minecraft.skylorbeck.website.lootgoblins.renderer;

import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.Identifier;

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
}

