package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.block.NotesBookshelf;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import static net.minecraft.block.Block.*;

public class BlockListener {

    public static Block[] blocks;
    public static Block NOTES_BOOKSHELF;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        NOTES_BOOKSHELF = new NotesBookshelf(Identifier.of(NAMESPACE, "notes_bookshelf"), Material.WOOD).setSoundGroup(WOOD_SOUND_GROUP).setHardness(2.0F).setTranslationKey(NAMESPACE, "notes_bookshelf");

        blocks = new Block[]
        {   NOTES_BOOKSHELF
        };
    }
}

