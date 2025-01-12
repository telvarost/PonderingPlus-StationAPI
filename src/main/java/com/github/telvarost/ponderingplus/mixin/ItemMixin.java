package com.github.telvarost.ponderingplus.mixin;

import com.github.telvarost.ponderingplus.Config;
import com.github.telvarost.ponderingplus.entity.BookEntity;
import com.github.telvarost.ponderingplus.entity.SeatEntity;
import com.github.telvarost.ponderingplus.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Shadow public static Item BOOK;

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_useOnTile(ItemStack itemStack, PlayerEntity player, World world, int x, int y, int z, int meta, CallbackInfoReturnable<Boolean> cir) {

        if (BOOK.id == itemStack.itemId) {
            if (Config.config.useBookAsSeat) {
                if (null != player.vehicle && player.vehicle instanceof BookEntity) {
                    player.setVehicle(null);
                } else {
                    itemStack.count--;

                    BookEntity entity = new BookEntity(world);
                    float bookYaw = (player.yaw - 90.0F);
                    bookYaw = (0.0F > bookYaw) ? (bookYaw + 360.0F) : bookYaw;
                    entity.setPositionAndAngles(player.x, (player.y - 1.5), player.z, bookYaw, 0.0F);
                    world.spawnEntity(entity);
                    player.setVehicle(entity);

                    cir.setReturnValue(true);
                }
            } else {
                if (null != player.vehicle && player.vehicle instanceof SeatEntity) {
                    player.setVehicle(null);
                } else {
                    SeatEntity entity = new SeatEntity(world);
                    float bookYaw = (player.yaw - 90.0F);
                    bookYaw = (0.0F > bookYaw) ? (bookYaw + 360.0F) : bookYaw;
                    entity.setPositionAndAngles(player.x, (player.y - 1.5), player.z, bookYaw, 0.0F);
                    world.spawnEntity(entity);
                    player.setVehicle(entity);

                    cir.setReturnValue(true);
                }
            }
        } else if (Item.FEATHER.id == itemStack.itemId) {
            if (Block.BOOKSHELF.id == world.getBlockId(x, y, z)) {
                if (player.inventory.remove(Item.DYE.id)) {
                    world.playSound(player, "step.wood", 1.0F, 1.0F / (world.random.nextFloat() * 0.4F + 0.8F));
                    world.setBlock(x, y, z, BlockListener.NOTES_BOOKSHELF.id);

                    /** - Open sign screen */
                    SignBlockEntity signBlockEntity = (SignBlockEntity)world.getBlockEntity(x, y, z);
                    if (signBlockEntity != null) {
                        player.openEditSignScreen(signBlockEntity);
                    } else {
                        signBlockEntity = new SignBlockEntity();
                        world.setBlockEntity(x, y, z, signBlockEntity);
                        player.openEditSignScreen(signBlockEntity);
                    }

                    cir.setReturnValue(true);
                }
            }
        }
    }
}
