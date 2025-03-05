//package com.github.telvarost.ponderingplus.mixin.server;
//
//import net.minecraft.network.packet.Packet;
//import net.minecraft.network.packet.play.UpdateSignPacket;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//
//@Mixin(UpdateSignPacket.class)
//public abstract class UpdateSign0x82C2SPacketMixin extends Packet {
//
//    @Shadow public int x;
//
//    @Shadow public int y;
//
//    @Shadow public int z;
//
//    @Shadow public String[] text;
//
//    @Inject(method = "read", at = @At("HEAD"), cancellable = true)
//    public void miscTweaks_read(DataInputStream dataInputStream, CallbackInfo ci) throws IOException {
//        this.x = dataInputStream.readInt();
//        this.y = dataInputStream.readShort();
//        this.z = dataInputStream.readInt();
//        this.text = new String[8];
//
//        for(int var2 = 0; var2 < 8; ++var2) {
//            this.text[var2] = readString(dataInputStream, 15);
//        }
//        ci.cancel();
//    }
//}
