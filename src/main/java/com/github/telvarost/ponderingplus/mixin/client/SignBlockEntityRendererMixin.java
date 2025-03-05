package com.github.telvarost.ponderingplus.mixin.client;

import com.github.telvarost.ponderingplus.events.init.BlockListener;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SignBlockEntityRenderer.class)
public abstract class SignBlockEntityRendererMixin extends BlockEntityRenderer {
    @Unique
    private boolean swapTexture = false;

    @Unique
    private boolean swapStoryTexture = false;

    @Inject(
            method = "render(Lnet/minecraft/block/entity/SignBlockEntity;DDDF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void render(SignBlockEntity signBlockEntity, double d, double e, double f, float g, CallbackInfo ci) {
        if (signBlockEntity.getBlock().id == BlockListener.NOTES_BOOKSHELF.id && d != -0.5D && e != -0.75D && f != -0.5D && g != 0.0F) {
            swapTexture = false;
            ci.cancel();
        } else if (signBlockEntity.getBlock().id == BlockListener.STORY_BOOKSHELF.id && d != -0.5D && e != -0.75D && f != -0.5D && g != 0.0F) {
            swapStoryTexture = false;
            ci.cancel();
        } else if (signBlockEntity.getBlock().id == BlockListener.NOTES_BOOKSHELF.id && d == -0.5D && e == -0.75D && f == -0.5D && g == 0.0F) {
            swapTexture = true;
        } else if (signBlockEntity.getBlock().id == BlockListener.STORY_BOOKSHELF.id && d == -0.5D && e == -0.75D && f == -0.5D && g == 0.0F) {
            swapStoryTexture = true;
        } else {
            swapTexture = false;
        }
    }

    @WrapOperation(
            method = "render(Lnet/minecraft/block/entity/SignBlockEntity;DDDF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/block/entity/SignBlockEntityRenderer;bindTexture(Ljava/lang/String;)V"
            )
    )
    public void renderBackground(SignBlockEntityRenderer instance, String path, Operation<Void> original) {
        if (swapTexture) {
            original.call(instance, "/assets/ponderingplus/notebook.png");
        } else if (swapStoryTexture) {
            original.call(instance, "/assets/ponderingplus/icon.png");
        } else {
            original.call(instance, path);
        }
    }

    @WrapOperation(
            method = "render(Lnet/minecraft/block/entity/SignBlockEntity;DDDF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/font/TextRenderer;draw(Ljava/lang/String;III)V"
            )
    )
    public void renderBackground(TextRenderer instance, String text, int x, int y, int color, Operation<Void> original) {
        if (swapTexture) {
            original.call(instance, text, x, y + 20, color);
        } else if (swapStoryTexture) {
            original.call(instance, text, x, y, color);
        } else {
            original.call(instance, text, x, y + 20, color);
        }
    }
}
