package website.skylorbeck.minecraft.lootgoblins.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import website.skylorbeck.minecraft.lootgoblins.GoblinConfig;
import website.skylorbeck.minecraft.lootgoblins.renderer.*;

import static website.skylorbeck.minecraft.lootgoblins.Declarer.*;

@Environment(EnvType.CLIENT)
public class LootgoblinsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.getGuiRegistry(GoblinConfig.class);

        EntityRendererRegistry.register(LOOT_SKELETON, LootSkeletonEntityRenderer::new);
        EntityRendererRegistry.register(LOOT_ENDERMAN, LootEndermanEntityRenderer::new);
        EntityRendererRegistry.register(LOOT_CREEPER, LootCreeperEntityRenderer::new);
        EntityRendererRegistry.register(LOOT_HOGLIN, LootHoglinEntityRenderer::new);
        EntityRendererRegistry.register(LOOT_ILLAGER, LootIllagerEntityRenderer::new);
        EntityRendererRegistry.register(LOOT_SPIDER, LootSpiderEntityRenderer::new);
        EntityRendererRegistry.register(LOOT_ZOMBIE, LootZombieEntityRenderer::new);
    }
}
