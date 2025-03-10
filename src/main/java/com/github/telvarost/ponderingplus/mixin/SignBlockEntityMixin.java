package com.github.telvarost.ponderingplus.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignBlockEntity.class)
public class SignBlockEntityMixin extends BlockEntity {
    @Shadow
    public String[] texts;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    public void ponderingPlus_init(CallbackInfo ci) {
        this.texts = new String[]{"", "", "", "", "", "", "", "", "", ""};
    }
}
