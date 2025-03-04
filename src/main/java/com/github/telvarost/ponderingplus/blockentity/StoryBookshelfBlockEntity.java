package com.github.telvarost.ponderingplus.blockentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.play.UpdateSignPacket;

public class StoryBookshelfBlockEntity extends BlockEntity {
    public static final int NUM_LINES = 8;
    public String[] texts = new String[NUM_LINES];
    public int currentRow = -1;
    private boolean editable = true;

    public StoryBookshelfBlockEntity() {
        for(int var2 = 0; var2 < NUM_LINES; ++var2) {
            this.texts[var2] = "";
        }
    }

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
    }

    public void readNbt(NbtCompound nbt) {
        this.editable = true;
        super.readNbt(nbt);

        for(int var2 = 0; var2 < NUM_LINES; ++var2) {
            this.texts[var2] = nbt.getString("Text" + (var2 + 1));
            if (this.texts[var2].length() > 15) {
                this.texts[var2] = this.texts[var2].substring(0, 15);
            }
        }

    }

//    @Override
//    public boolean canPlayerUse(PlayerEntity player) {
//        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
//            return false;
//        } else {
//            return !(player.getSquaredDistance((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) > 64.0);
//        }
//    }

    @Environment(EnvType.SERVER)
    public Packet createUpdatePacket() {
        String[] var1 = new String[NUM_LINES];

        for(int var2 = 0; var2 < NUM_LINES; ++var2) {
            var1[var2] = this.texts[var2];
        }

        return new UpdateSignPacket(this.x, this.y, this.z, var1);
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
