package com.github.telvarost.ponderingplus.mixin;

import com.github.telvarost.ponderingplus.events.init.BlockListener;
import com.github.telvarost.ponderingplus.packet.UpdateStoryPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignBlockEntity.class)
public class SignBlockEntityMixin extends BlockEntity {
    @Shadow public String[] texts;
    @Shadow private boolean editable;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    public void ponderingPlus_init(CallbackInfo ci) {
        this.texts = new String[]{"", "", "", "", "", "", "", ""};
    }

    @Inject(
            method = "writeNbt",
            at = @At("RETURN"),
            cancellable = true
    )
    public void ponderingPlus_writeNbt(NbtCompound nbt, CallbackInfo ci) {
        if (null != this.getBlock() && BlockListener.STORY_BOOKSHELF.id == this.getBlock().id) {
            nbt.putString("Text5", this.texts[4]);
            nbt.putString("Text6", this.texts[5]);
            nbt.putString("Text7", this.texts[6]);
            nbt.putString("Text8", this.texts[7]);
        }
    }

    @Inject(
            method = "readNbt",
            at = @At("RETURN"),
            cancellable = true
    )
    public void ponderingPlus_readNbt(NbtCompound nbt, CallbackInfo ci) {
        //if (null != this.getBlock() && BlockListener.STORY_BOOKSHELF.id == this.getBlock().id) {
            this.editable = true;

            for (int var2 = 4; var2 < 8; ++var2) {
                this.texts[var2] = nbt.getString("Text" + (var2 + 1));
                if (this.texts[var2].length() > 15) {
                    this.texts[var2] = this.texts[var2].substring(0, 15);
                }
            }
        //}
    }

    @Environment(EnvType.SERVER)
    @Inject(
            method = "createUpdatePacket",
            at = @At("RETURN"),
            cancellable = true
    )
    public void ponderingPlus_createUpdatePacket(CallbackInfoReturnable<Packet> cir) {
        if (null != this.getBlock() && BlockListener.STORY_BOOKSHELF.id == this.getBlock().id) {
            String[] var1 = new String[8];

            for (int var2 = 0; var2 < 8; ++var2) {
                var1[var2] = this.texts[var2];
            }

            cir.setReturnValue(new UpdateStoryPacket(this.x, this.y, this.z, var1));
        }
    }
}
