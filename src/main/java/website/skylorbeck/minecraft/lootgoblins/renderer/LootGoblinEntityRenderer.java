package website.skylorbeck.minecraft.lootgoblins.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import website.skylorbeck.minecraft.lootgoblins.entity.LootGoblinEntity;
import website.skylorbeck.minecraft.lootgoblins.model.LootGoblinModel;

public class LootGoblinEntityRenderer extends GeoEntityRenderer<LootGoblinEntity> {
    public LootGoblinEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new LootGoblinModel());
    }
}
