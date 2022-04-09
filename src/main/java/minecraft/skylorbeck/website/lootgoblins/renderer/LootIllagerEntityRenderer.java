/*
 * Decompiled with CFR 0.1.0 (FabricMC a830a72d).
 */
package minecraft.skylorbeck.website.lootgoblins.renderer;

import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.client.render.entity.PillagerEntityRenderer;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class LootIllagerEntityRenderer extends PillagerEntityRenderer {
    private static final Identifier TEXTURE = Lootgoblins.getIdentifier("textures/entity/illager/emerald.png");
    public LootIllagerEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PillagerEntity pillagerEntity) {
        return TEXTURE;
    }
}

