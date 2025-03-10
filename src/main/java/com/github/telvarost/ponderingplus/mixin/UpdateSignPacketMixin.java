//package com.github.telvarost.ponderingplus.mixin;
//
//import com.github.telvarost.ponderingplus.ModHelper;
//import net.minecraft.network.packet.Packet;
//import net.minecraft.network.packet.play.UpdateSignPacket;
//import org.objectweb.asm.Opcodes;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.io.DataInputStream;
//
//@Mixin(UpdateSignPacket.class)
//public abstract class UpdateSignPacketMixin extends Packet {
//
//    @Shadow public String[] text;
//
//    @Inject(
//            method = "read",
//            at = @At(
//                    value = "FIELD",
//                    opcode = Opcodes.PUTFIELD,
//                    target = "Lnet/minecraft/network/packet/play/UpdateSignPacket;text:[Ljava/lang/String;",
//                    shift = At.Shift.AFTER
//            )
//    )
//    public void ponderingPlus_read(DataInputStream par1, CallbackInfo ci) {
//        this.text = new String[ModHelper.STORY_BOOK_SIZE];
//    }
//}
