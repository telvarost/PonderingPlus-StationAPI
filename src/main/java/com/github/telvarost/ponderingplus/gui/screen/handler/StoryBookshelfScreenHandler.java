//package com.github.telvarost.ponderingplus.gui.screen.handler;
//
//import com.github.telvarost.ponderingplus.blockentity.StoryBookshelfBlockEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.inventory.Inventory;
//import net.minecraft.screen.ScreenHandler;
//
//public class StoryBookshelfScreenHandler extends ScreenHandler {
//
//    public StoryBookshelfScreenHandler(Inventory iinventory, StoryBookshelfBlockEntity tile) {
//        this.tile = tile;
//    }
//
//    @Override
//    public boolean canUse(PlayerEntity player) {
//        return tile.canPlayerUse(player);
//    }
//
//    private final StoryBookshelfBlockEntity tile;
//}