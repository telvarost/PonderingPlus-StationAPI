package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.entity.BookEntity;
import com.github.telvarost.ponderingplus.entity.SeatEntity;
import com.github.telvarost.ponderingplus.entity.renderer.BookRenderer;
import com.github.telvarost.ponderingplus.entity.renderer.SeatRenderer;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;

public class EntityRendererListener {

    @EventListener
    public void registerEntityRenderer(EntityRendererRegisterEvent event){
        event.renderers.put(BookEntity.class, new BookRenderer());
        event.renderers.put(SeatEntity.class, new SeatRenderer());
    }
}
