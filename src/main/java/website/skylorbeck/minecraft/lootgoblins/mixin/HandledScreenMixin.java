package website.skylorbeck.minecraft.lootgoblins.mixin;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import website.skylorbeck.minecraft.lootgoblins.Declarer;
import website.skylorbeck.minecraft.lootgoblins.Lootgoblins;
import website.skylorbeck.minecraft.lootgoblins.client.LootgoblinsClient;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {
    int cooldown = 0;
    @Inject(method = "keyPressed",at = @At("HEAD"), cancellable = true)
    public void MMOCHAT(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (cooldown <= 0) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (InputUtil.isKeyPressed(client.getWindow().getHandle(), KeyBindingHelper.getBoundKeyOf(LootgoblinsClient.keyBinding).getCode())) {
                if (((FocusedSlotAccessor) this).getFocusedSlot() != null && ((FocusedSlotAccessor) this).getFocusedSlot().hasStack()) {
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeText(((FocusedSlotAccessor) this).getFocusedSlot().getStack().toHoverableText());
                    ClientPlayNetworking.send(Lootgoblins.getIdentifier("itemlink"), buf);
                    cooldown = Declarer.config.cooldown;
                    cir.setReturnValue(true);
                }
            }
        }
    }
    @Inject(method = "tick",at = @At("HEAD"))
    public void cooldown(CallbackInfo ci){
        if (cooldown>0){
            cooldown--;
        }
    }
}
