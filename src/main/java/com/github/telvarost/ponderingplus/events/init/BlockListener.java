package com.github.telvarost.ponderingplus.events.init;

import com.github.telvarost.ponderingplus.ModHelper;
import com.github.telvarost.ponderingplus.block.NotesBookshelf;
import com.github.telvarost.ponderingplus.block.StoryBookshelf;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.util.Identifier;

import static net.minecraft.block.Block.*;

public class BlockListener {

    public static Block[] blocks;
    public static Block NOTES_BOOKSHELF;
    public static Block STORY_BOOKSHELF;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        NOTES_BOOKSHELF = new NotesBookshelf(Identifier.of(ModHelper.NAMESPACE, "notes_bookshelf"), Material.WOOD).setSoundGroup(WOOD_SOUND_GROUP).setHardness(2.0F).setTranslationKey(ModHelper.NAMESPACE, "notes_bookshelf");
        STORY_BOOKSHELF = new StoryBookshelf(Identifier.of(ModHelper.NAMESPACE, "story_bookshelf"), Material.WOOD).setSoundGroup(WOOD_SOUND_GROUP).setHardness(3.0F).setTranslationKey(ModHelper.NAMESPACE, "story_bookshelf");

        blocks = new Block[]
        {   NOTES_BOOKSHELF
        ,   STORY_BOOKSHELF
        };
    }
}

