package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.blockentity.StoryBookshelfBlockEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;

public class BlockEntityListener {
    @EventListener
    public void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(StoryBookshelfBlockEntity.class, "ponderingplus:story_bookshelf");
    }
}
