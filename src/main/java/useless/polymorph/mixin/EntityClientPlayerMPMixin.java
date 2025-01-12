package useless.polymorph.mixin;

import net.minecraft.client.entity.player.EntityClientPlayerMP;
import net.minecraft.client.net.handler.NetClientHandler;
import net.minecraft.core.net.packet.Packet250CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import useless.polymorph.IDataStorage;
import useless.polymorph.PolyMorph;
import useless.polymorph.PolyMorphClient;

import java.math.BigInteger;

@Mixin(value = EntityClientPlayerMP.class, remap = false)
public class EntityClientPlayerMPMixin extends EntityPlayerMixin implements IDataStorage {
	@Shadow
	public NetClientHandler sendQueue;

	@Override
	public void polymorph$setRecipeOffset(int offset) {
		super.polymorph$setRecipeOffset(offset);
		if (PolyMorphClient.isEnabled()){
			sendQueue.addToSendQueue(new Packet250CustomPayload(PolyMorph.OFFSET_CHANNEL, BigInteger.valueOf(offset).toByteArray()));
		}
	}
}
