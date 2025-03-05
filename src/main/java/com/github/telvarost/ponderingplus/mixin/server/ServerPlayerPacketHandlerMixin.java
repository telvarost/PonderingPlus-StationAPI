package com.github.telvarost.ponderingplus.mixin.server;

import com.github.telvarost.ponderingplus.ModHelper;
import com.github.telvarost.ponderingplus.events.init.BlockListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.play.UpdateSignPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.CharacterUtils;
import net.minecraft.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.SERVER)
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayerPacketHandlerMixin extends NetworkHandler implements CommandOutput {

    @Shadow private MinecraftServer server;

    @Shadow private ServerPlayerEntity player;

    @Inject(
            method = "handleUpdateSign",
            at = @At("HEAD"),
            cancellable = true
    )
    public void ponderingPlus_handleUpdateSign(UpdateSignPacket packet, CallbackInfo ci) {
        ServerWorld serverWorld = this.server.getWorld(this.player.dimensionId);
        if (serverWorld.isPosLoaded(packet.x, packet.y, packet.z)) {
            if (BlockListener.STORY_BOOKSHELF.id == serverWorld.getBlockId(packet.x, packet.y, packet.z)) {

                BlockEntity var3 = serverWorld.getBlockEntity(packet.x, packet.y, packet.z);
                if (var3 instanceof SignBlockEntity) {
                    SignBlockEntity var4 = (SignBlockEntity)var3;
                    // TODO: fix is editable property for bookshelf messages
                    if (!var4.isEditable()) {
                        this.server.warn("Player " + this.player.name + " just tried to change non-editable sign");
                        return;
                    }
                }

                int var6;
                int var9;
                for(var9 = 0; var9 < ModHelper.STORY_BOOK_SIZE; ++var9) {
                    boolean var5 = true;
                    if (packet.text[var9].length() > 15) {
                        var5 = false;
                    } else {
                        for(var6 = 0; var6 < packet.text[var9].length(); ++var6) {
                            if (CharacterUtils.VALID_CHARACTERS.indexOf(packet.text[var9].charAt(var6)) < 0) {
                                var5 = false;
                            }
                        }
                    }

                    if (!var5) {
                        packet.text[var9] = "!?";
                    }
                }

                if (var3 instanceof SignBlockEntity) {
                    var9 = packet.x;
                    int var10 = packet.y;
                    var6 = packet.z;
                    SignBlockEntity var7 = (SignBlockEntity)var3;

                    for(int textIndex = 0; textIndex < ModHelper.STORY_BOOK_SIZE; ++textIndex) {
                        var7.texts[textIndex] = packet.text[textIndex];
                    }

                    var7.setEditable(false);
                    var7.markDirty();
                    serverWorld.blockUpdateEvent(var9, var10, var6);
                }

                ci.cancel();
            }
        }
    }
}
