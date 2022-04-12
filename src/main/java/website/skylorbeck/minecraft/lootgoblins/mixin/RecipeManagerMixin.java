package website.skylorbeck.minecraft.lootgoblins.mixin;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import website.skylorbeck.minecraft.lootgoblins.Declarer;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;

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
        createMap.apply(Declarer.SMELT_STONEFLESH, "smelt_stoneflesh");
        createMap.apply(Declarer.SMELT_IRON_EYE, "smelt_iron_eye");
        createMap.apply(Declarer.SMELT_EMERALD_CROSSBOW, "smelt_emerald_crossbow");
        createMap.apply(Declarer.SMELT_QUARTZCHOP, "smelt_quartzchop");
        createMap.apply(Declarer.BLAST_GOLD_BONE, "blast_gold_bone");
        createMap.apply(Declarer.BLAST_STONEFLESH, "blast_stoneflesh");
        createMap.apply(Declarer.BLAST_IRON_EYE, "blast_iron_eye");
        createMap.apply(Declarer.BLAST_EMERALD_CROSSBOW, "blast_emerald_crossbow");
        createMap.apply(Declarer.BLAST_QUARTZCHOP, "blast_quartzchop");
    }
}