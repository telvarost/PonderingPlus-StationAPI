package com.github.telvarost.ponderingplus.mixin.client;

import com.github.telvarost.ponderingplus.events.init.BlockListener;
import com.github.telvarost.ponderingplus.packet.UpdateStoryPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SignEditScreen.class)
public class SignEditScreenMixin extends Screen {

    @Shadow private SignBlockEntity sign;

    @Shadow protected String title;

    @Shadow private int currentRow;

    @Shadow @Final private static String VALID_CHARACTERS;

    @Inject(
            method = "init",
            at = @At("HEAD"),
            cancellable = true
    )
    public void ponderingPlus_init(CallbackInfo ci) {
        if (sign.getBlock().id == BlockListener.NOTES_BOOKSHELF.id) {
            this.title = "Notebook message:";
        } else if (sign.getBlock().id == BlockListener.STORY_BOOKSHELF.id) {
            this.title = "Storybook message:";
        }
    }

    @Inject(
            method = "removed",
            at = @At("HEAD"),
            cancellable = true
    )
    public void ponderingPlus_removed(CallbackInfo ci) {
        if (sign.getBlock().id == BlockListener.STORY_BOOKSHELF.id) {
            Keyboard.enableRepeatEvents(false);
            if (this.minecraft.world.isRemote) {
                this.minecraft.getNetworkHandler().sendPacket(new UpdateStoryPacket(this.sign.x, this.sign.y, this.sign.z, this.sign.texts));
            }
            ci.cancel();
        }
    }

    @Inject(
            method = "keyPressed",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void ponderingPlus_keyPressed(char character, int keyCode, CallbackInfo ci) {
        if (sign.getBlock().id == BlockListener.STORY_BOOKSHELF.id) {
            if (keyCode == 200) {
                this.currentRow = (this.currentRow - 1) % 8;
            }

            if (keyCode == 208 || keyCode == 28) {
                this.currentRow = (this.currentRow + 1) % 8;
            }

            if (keyCode == 14 && this.sign.texts[this.currentRow].length() > 0) {
                this.sign.texts[this.currentRow] = this.sign.texts[this.currentRow].substring(0, this.sign.texts[this.currentRow].length() - 1);
            }

            if (VALID_CHARACTERS.indexOf(character) >= 0 && this.sign.texts[this.currentRow].length() < 15) {
                StringBuilder var10000 = new StringBuilder();
                String[] var10002 = this.sign.texts;
                int var10004 = this.currentRow;
                var10002[var10004] = var10000.append(var10002[var10004]).append(character).toString();
            }

            ci.cancel();
        }
    }
}
