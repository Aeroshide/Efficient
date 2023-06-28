package com.aeroshide.efficient.mixins;

import com.aeroshide.efficient.Efficient;
import net.minecraft.client.gui.WorldGenerationProgressTracker;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.gui.screen.LevelLoadingScreen.drawChunkMap;

@Mixin(LevelLoadingScreen.class)
public class LoadingScreenRendererMixin {

    @Final
    @Shadow
    private WorldGenerationProgressTracker progressProvider;

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/LevelLoadingScreen;drawCenteredString(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V"))
    private void modifyProgressString(LevelLoadingScreen levelLoadingScreen, MatrixStack matrixStack, TextRenderer textRenderer, String string, int x, int y, int color) {
        String originalString = Efficient.df.format(MathHelper.clamp(this.progressProvider.getProgressPercentage() * 3.9, 0, 100)) + "%";
        String modifiedString = "ACCELERATED: " + originalString;
        levelLoadingScreen.drawCenteredString(matrixStack ,textRenderer, modifiedString, x, y, color);
    }

    /*
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/LevelLoadingScreen;drawChunkMap(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/gui/WorldGenerationProgressTracker;IIII)V"))
    private void modifyChunkmap(MatrixStack matrixStack, WorldGenerationProgressTracker worldGenerationProgressTracker, int i, int j, int k, int l) {
        LevelLoadingScreen levelLoadingScreen = (LevelLoadingScreen) (Object) this;
        int chunkMapX = levelLoadingScreen.width / 2 - 128; // Adjust the X coordinate as per your requirements
        drawChunkMap(matrixStack, this.progressProvider, chunkMapX, j + 30, 2, 0);
    }
    /**
     */

}
