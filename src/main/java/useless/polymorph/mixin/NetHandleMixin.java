package useless.polymorph.mixin;

import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import useless.polymorph.INetHandler;
import useless.polymorph.RecipeOffsetPacket;
@Mixin(value = NetHandler.class, remap = false)
public abstract class NetHandleMixin implements INetHandler {
	@Shadow
	public abstract void handleInvalidPacket(Packet packet);

	@Override
	public void polymorph$handleOffset(RecipeOffsetPacket packet) {
		handleInvalidPacket(packet);
	}
}
