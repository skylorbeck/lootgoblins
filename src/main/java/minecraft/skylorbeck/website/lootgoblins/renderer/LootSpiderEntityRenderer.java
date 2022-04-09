/*
 * Decompiled with CFR 0.1.0 (FabricMC a830a72d).
 */
package minecraft.skylorbeck.website.lootgoblins.renderer;

import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.client.render.entity.SpiderEntityRenderer;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;

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
}

