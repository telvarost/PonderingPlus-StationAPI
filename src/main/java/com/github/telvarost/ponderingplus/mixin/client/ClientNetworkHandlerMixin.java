package com.github.telvarost.ponderingplus.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
            ci.cancel();
        }
    }
}
