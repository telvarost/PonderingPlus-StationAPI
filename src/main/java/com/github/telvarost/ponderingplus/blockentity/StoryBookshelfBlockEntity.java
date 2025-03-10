package com.github.telvarost.ponderingplus.blockentity;

import com.github.telvarost.ponderingplus.ModHelper;
import com.github.telvarost.ponderingplus.events.init.BlockListener;
import com.github.telvarost.ponderingplus.packet.UpdateStoryPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;

public class StoryBookshelfBlockEntity extends BlockEntity {
    public String[] texts = new String[]{"", "", "", "", "", "", "", "", "", ""};
    public int currentRow = -1;
    private boolean editable = true;

    public StoryBookshelfBlockEntity() {
    }

    public SignBlockEntity convertToSignBlockEntity() {
        SignBlockEntity signBlockEntity = new SignBlockEntity();
        signBlockEntity.world = world;
        signBlockEntity.x = x;
        signBlockEntity.y = y;
        signBlockEntity.z = z;
        signBlockEntity.texts = texts;
        signBlockEntity.currentRow = currentRow;
        return signBlockEntity;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Block getBlock() {
        return BlockListener.STORY_BOOKSHELF;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("Text1", this.texts[0]);
        nbt.putString("Text2", this.texts[1]);
        nbt.putString("Text3", this.texts[2]);
        nbt.putString("Text4", this.texts[3]);
        nbt.putString("Text5", this.texts[4]);
        nbt.putString("Text6", this.texts[5]);
        nbt.putString("Text7", this.texts[6]);
        nbt.putString("Text8", this.texts[7]);
        nbt.putString("Text9", this.texts[8]);
        nbt.putString("Text10", this.texts[9]);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.editable = true;

        for(int var2 = 0; var2 < ModHelper.STORY_BOOK_SIZE; ++var2) {
            this.texts[var2] = nbt.getString("Text" + (var2 + 1));
            if (this.texts[var2].length() > 15) {
                this.texts[var2] = this.texts[var2].substring(0, 15);
            }
        }
    }

    @Environment(EnvType.SERVER)
    @Override
    public Packet createUpdatePacket() {
        String[] var1 = new String[ModHelper.STORY_BOOK_SIZE];

        for (int var2 = 0; var2 < ModHelper.STORY_BOOK_SIZE; ++var2) {
            var1[var2] = this.texts[var2];
        }

        return new UpdateStoryPacket(this.x, this.y, this.z, var1);
    }

    @Environment(EnvType.SERVER)
    public boolean isEditable() {
        return this.editable;
    }

    @Environment(EnvType.SERVER)
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
