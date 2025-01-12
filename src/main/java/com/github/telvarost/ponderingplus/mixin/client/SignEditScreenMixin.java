package com.github.telvarost.ponderingplus.mixin.client;

import com.github.telvarost.ponderingplus.events.init.BlockListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SignEditScreen.class)
public class SignEditScreenMixin {

    @Shadow private SignBlockEntity sign;

    @Shadow protected String title;

    @Inject(
            method = "init",
            at = @At("HEAD"),
            cancellable = true
    )
    public void init(CallbackInfo ci) {
        if (sign.getBlock().id == BlockListener.NOTES_BOOKSHELF.id) {
            this.title = "Notebook message:";
        }
    }
}
