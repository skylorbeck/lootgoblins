package minecraft.skylorbeck.website.lootgoblins.client;

import me.shedaniel.autoconfig.AutoConfig;
import minecraft.skylorbeck.website.lootgoblins.GoblinConfig;
import minecraft.skylorbeck.website.lootgoblins.renderer.LootSkeletonEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import static minecraft.skylorbeck.website.lootgoblins.Declarer.LOOT_SKELETON_GOLD;

@Environment(EnvType.CLIENT)
public class LootgoblinsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.getGuiRegistry(GoblinConfig.class);

        EntityRendererRegistry.register(LOOT_SKELETON_GOLD, LootSkeletonEntityRenderer::new);
    }
}
