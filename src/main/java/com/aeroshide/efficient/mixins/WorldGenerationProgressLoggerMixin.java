package com.aeroshide.efficient.mixins;

import net.minecraft.server.WorldGenerationProgressLogger;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldGenerationProgressLogger.class)
public class WorldGenerationProgressLoggerMixin {

    @Shadow
    private int generatedCount;


    @Inject(method = "getProgressPercentage", at = @At("RETURN"), cancellable = true)
    private void modifyProgress(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(MathHelper.floor((float)this.generatedCount * 100.0F / (float)144));
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
