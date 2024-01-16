package useless.polymorph;

import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RecipeOffsetPacket extends Packet {
	public int offset;
	public RecipeOffsetPacket(){

	}
	public RecipeOffsetPacket(int offset) {
		this.offset = offset;
	}
	@Override
	public void readPacketData(DataInputStream dataInputStream) throws IOException {
		this.offset = dataInputStream.readInt();
	}

	@Override
	public void writePacketData(DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(offset);
	}

	@Override
	public void processPacket(NetHandler netHandler) {
		((INetHandler)netHandler).polymorph$handleOffset(this);
	}

	@Override
	public int getPacketSize() {
		return 8;
	}
}
