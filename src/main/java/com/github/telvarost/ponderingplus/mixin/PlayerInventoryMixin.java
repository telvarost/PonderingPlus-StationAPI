package com.github.telvarost.ponderingplus.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Shadow public ItemStack[] main;

    @Unique
    private int ponderingPlus_indexOfBlackDye(int itemId) {
        for(int var2 = 0; var2 < this.main.length; ++var2) {
            if (this.main[var2] != null && this.main[var2].itemId == itemId && 0 == this.main[var2].getDamage()) {
                return var2;
            }
        }

        return -1;
    }

    @WrapOperation(
            method = "remove",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerInventory;indexOf(I)I"
            )
    )
    public int remove(PlayerInventory instance, int itemId, Operation<Integer> original) {
        if (Item.DYE.id == itemId) {
            return ponderingPlus_indexOfBlackDye(itemId);
        } else {
            return original.call(instance, itemId);
        }
    }
}
