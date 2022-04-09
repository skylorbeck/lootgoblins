/*
 * Decompiled with CFR 0.1.0 (FabricMC a830a72d).
 */
package minecraft.skylorbeck.website.lootgoblins.renderer;

import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class LootZombieEntityRenderer extends ZombieEntityRenderer {
    private static final Identifier TEXTURE = Lootgoblins.getIdentifier("textures/entity/zombie/lapis.png");
    public LootZombieEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}

