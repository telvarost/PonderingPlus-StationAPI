package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.ModHelper;
import com.github.telvarost.ponderingplus.entity.BookEntity;
import com.github.telvarost.ponderingplus.entity.SeatEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.EntityHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.Registry;

public class EntityListener {

    @EventListener
    public void registerEntities(EntityRegister event){
        event.register(BookEntity.class,"book");
        event.register(SeatEntity.class,"seat");
    }

    @EventListener
    public void registerEntityHandlers(EntityHandlerRegistryEvent event) {
        Registry.register(event.registry, ModHelper.NAMESPACE.id("book"), BookEntity::new);
        Registry.register(event.registry, ModHelper.NAMESPACE.id("seat"), SeatEntity::new);
    }
}
