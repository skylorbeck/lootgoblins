package website.skylorbeck.minecraft.lootgoblins.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;
import website.skylorbeck.minecraft.lootgoblins.Declarer;
import website.skylorbeck.minecraft.lootgoblins.GoblinConfig;
import website.skylorbeck.minecraft.lootgoblins.renderer.*;

import static website.skylorbeck.minecraft.lootgoblins.Declarer.*;

@Environment(EnvType.CLIENT)
public class LootgoblinsClient implements ClientModInitializer {
    public static KeyBinding keyBinding;
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
        EntityRendererRegistry.register(LOOT_GOBLIN, LootGoblinEntityRenderer::new);

        ModelPredicateProviderRegistry.register(Declarer.EMERALD_CROSSBOW, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (CrossbowItem.isCharged(stack)) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack);
        });
        ModelPredicateProviderRegistry.register(Declarer.EMERALD_CROSSBOW, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(Declarer.EMERALD_CROSSBOW, new Identifier("charged"), (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(Declarer.EMERALD_CROSSBOW, new Identifier("firework"), (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0f : 0.0f);

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                        "key.lootgoblins.itemlink",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_LEFT_ALT,
                "category.lootgoblins"));

    }
}
