package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.entity.BookEntity;
import com.github.telvarost.ponderingplus.entity.SeatEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.EntityHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class EntityListener {
    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerEntities(EntityRegister event){
        event.register(BookEntity.class,"book");
        event.register(SeatEntity.class,"seat");
    }

    @EventListener
    public void registerEntityHandlers(EntityHandlerRegistryEvent event) {
        Registry.register(event.registry, NAMESPACE.id("book"), BookEntity::new);
        Registry.register(event.registry, NAMESPACE.id("seat"), SeatEntity::new);
    }
}
