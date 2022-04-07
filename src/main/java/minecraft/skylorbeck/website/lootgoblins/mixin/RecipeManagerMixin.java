package minecraft.skylorbeck.website.lootgoblins.mixin;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import minecraft.skylorbeck.website.lootgoblins.Declarer;
import minecraft.skylorbeck.website.lootgoblins.Lootgoblins;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.BiFunction;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        BiFunction<JsonObject, String, ?> createMap = (json, id) -> {
            if (json != null) {
                map.put(Lootgoblins.getIdentifier(id), json);
                return true;
            }
            return false;
        };
        createMap.apply(Declarer.SMELT_GOLD_BONE, "smelt_gold_bone");
    }
}