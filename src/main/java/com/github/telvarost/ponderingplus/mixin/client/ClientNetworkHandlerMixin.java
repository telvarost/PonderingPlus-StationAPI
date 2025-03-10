package com.github.telvarost.ponderingplus.mixin.client;

import com.github.telvarost.ponderingplus.ModHelper;
import com.github.telvarost.ponderingplus.blockentity.StoryBookshelfBlockEntity;
import com.github.telvarost.ponderingplus.events.init.BlockListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.ClientNetworkHandler;
import net.minecraft.network.packet.play.UpdateSignPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientNetworkHandler.class)
public class ClientNetworkHandlerMixin {

    @Shadow private Minecraft minecraft;

    @Inject(
            method = "handleUpdateSign",
            at = @At("HEAD"),
            cancellable = true
    )
    public void handleUpdateSign(UpdateSignPacket packet, CallbackInfo ci) {
        if (null == this.minecraft.world) {
            // TODO: create a queue and process update package messages on first open of edit sign screen
            ci.cancel();
        } else {
            if (this.minecraft.world.isPosLoaded(packet.x, packet.y, packet.z)) {

                if (BlockListener.STORY_BOOKSHELF.id == this.minecraft.world.getBlockId(packet.x, packet.y, packet.z)) {
                    BlockEntity var2 = this.minecraft.world.getBlockEntity(packet.x, packet.y, packet.z);
                    if (var2 instanceof StoryBookshelfBlockEntity) {
                        StoryBookshelfBlockEntity storyBookshelfBlockEntity = (StoryBookshelfBlockEntity) var2;

                        for (int var4 = 0; var4 < ModHelper.STORY_BOOK_SIZE; ++var4) {
                            storyBookshelfBlockEntity.texts[var4] = packet.text[var4];
                        }

                        storyBookshelfBlockEntity.markDirty();
                    }

                    ci.cancel();
                }
            }
        }
    }
}
