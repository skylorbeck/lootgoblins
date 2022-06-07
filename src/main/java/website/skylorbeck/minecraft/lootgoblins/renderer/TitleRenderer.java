package website.skylorbeck.minecraft.lootgoblins.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Matrix4f;
import website.skylorbeck.minecraft.lootgoblins.Declarer;

public class TitleRenderer {
    public static void renderLabelIfPresent(LivingEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, EntityRenderDispatcher dispatcher, TextRenderer textRenderer) {
        double d = dispatcher.getSquaredDistanceToCamera(entity);
        if (d > 4096.0) {
            return;
        }
        String[] name = text.getString().split(" ");
        float Height = entity.getHeight() + 0.5f;
        matrices.push();
        matrices.translate(0.0, Height, 0.0);
        matrices.multiply(dispatcher.getRotation());
        matrices.scale(-0.025f, -0.025f, 0.025f);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25f);
        int j = (int) (g * 255.0f) << 24;
        float width = -textRenderer.getWidth(name[0]) / 2;
        textRenderer.draw(Text.of(name[0]), width, 0, 0x20FFFFFF, false, matrix4f, vertexConsumers, true, j, light);
        textRenderer.draw(Text.of(name[0]), width, 0, -1, false, matrix4f, vertexConsumers, false, 0, light);
        matrices.translate(0, Declarer.config.titleAbove?Declarer.config.titleSmall?-6:-8:10,0);
        float size = Declarer.config.titleSmall?0.5f:0.75f;
        matrices.scale(size,size,size);
        matrix4f = matrices.peek().getPositionMatrix();
        StringBuilder nameAppend = new StringBuilder();
        for (int i = 1; i < name.length; i++) {
            nameAppend.append(name[i]);
            if (i<name.length-1)
                nameAppend.append(" ");
        }
        String title = nameAppend.toString();
        if (title.length()>0) {
            width = -textRenderer.getWidth(title) / 2;
            textRenderer.draw(Text.of(title), width, 0, 0x20FFFFFF, false, matrix4f, vertexConsumers, true, j, light);
            textRenderer.draw(Text.of(title), width, 0, -1, false, matrix4f, vertexConsumers, false, 0, light);
        }
        matrices.pop();
    }
}
