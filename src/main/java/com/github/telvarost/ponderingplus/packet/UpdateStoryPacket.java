package com.github.telvarost.ponderingplus.packet;

import com.github.telvarost.ponderingplus.ModHelper;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.play.UpdateSignPacket;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UpdateStoryPacket extends UpdateSignPacket implements ManagedPacket<UpdateStoryPacket> {
	public static final PacketType<UpdateStoryPacket> TYPE = PacketType.builder(true, true, UpdateStoryPacket::new).build();
	private static final String STATION_ID = StationAPI.NAMESPACE.id("id").toString();
	private static final Identifier ID = ModHelper.NAMESPACE.id("update_story");

	public UpdateStoryPacket() {
		this.worldPacket = true;
	}

	public UpdateStoryPacket(int x, int y, int z, String[] text) {
		this.worldPacket = true;
		this.x = x;
		this.y = y;
		this.z = z;
		this.text = text;
	}

	@Override
	public void read(DataInputStream stream) {
        try {
            this.x = stream.readInt();
			this.y = stream.readShort();
			this.z = stream.readInt();
			this.text = new String[ModHelper.STORY_BOOK_SIZE];

			for(int var2 = 0; var2 < ModHelper.STORY_BOOK_SIZE; ++var2) {
				this.text[var2] = readString(stream, 15);
			}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.x);
			stream.writeShort(this.y);
			stream.writeInt(this.z);

			for(int var2 = 0; var2 < ModHelper.STORY_BOOK_SIZE; ++var2) {
				writeString(this.text[var2], stream);
			}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void apply(NetworkHandler networkHandler) {
		networkHandler.handleUpdateSign(this);
	}

	@Override
	public int size() {
		int var1 = 0;

		for(int var2 = 0; var2 < ModHelper.STORY_BOOK_SIZE; ++var2) {
			var1 += this.text[var2].length();
		}

		return var1;
	}

	@Override
	public @NotNull PacketType<UpdateStoryPacket> getType() {
		return TYPE;
	}
}
