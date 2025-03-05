package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.ModHelper;
import com.github.telvarost.ponderingplus.packet.UpdateStoryPacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;

public class PacketListener {

    @EventListener
    public static void registerPackets(PacketRegisterEvent event){
        Registry.register(PacketTypeRegistry.INSTANCE, ModHelper.NAMESPACE.id("update_story"), UpdateStoryPacket.TYPE);
    }
}
