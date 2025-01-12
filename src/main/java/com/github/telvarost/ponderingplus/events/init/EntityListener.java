package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.entity.BookEntity;
import com.github.telvarost.ponderingplus.entity.SeatEntity;
import com.github.telvarost.ponderingplus.entity.renderer.BookRenderer;
import com.github.telvarost.ponderingplus.entity.renderer.SeatRenderer;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;

public class EntityListener {

    @EventListener
    public void registerEntities(EntityRegister event){
        event.register(BookEntity.class,"book");
        event.register(SeatEntity.class,"seat");
    }

    @EventListener
    public void registerEntityRenderer(EntityRendererRegisterEvent event){
        event.renderers.put(BookEntity.class, new BookRenderer());
        event.renderers.put(SeatEntity.class, new SeatRenderer());
    }
}
