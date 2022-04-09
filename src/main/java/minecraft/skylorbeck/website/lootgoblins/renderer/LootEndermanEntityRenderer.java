/*
 * Decompiled with CFR 0.1.0 (FabricMC a830a72d).
 */
package minecraft.skylorbeck.website.lootgoblins.renderer;

import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class LootEndermanEntityRenderer extends EndermanEntityRenderer {
    private static final Identifier TEXTURE = Lootgoblins.getIdentifier("textures/entity/enderman/prismarine.png");
    public LootEndermanEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(EndermanEntity endermanEntity) {
        return TEXTURE;
    }
}

