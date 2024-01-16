package useless.polymorph.mixin;

import net.minecraft.client.entity.player.EntityClientPlayerMP;
import net.minecraft.client.net.handler.NetClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import useless.polymorph.IDataStorage;
import useless.polymorph.RecipeOffsetPacket;

@Mixin(value = EntityClientPlayerMP.class, remap = false)
public class EntityClientPlayerMPMixin extends EntityPlayerMixin implements IDataStorage {
	@Shadow
	public NetClientHandler sendQueue;

	@Override
	public void polymorph$setRecipeOffset(int offset) {
		super.polymorph$setRecipeOffset(offset);
		sendQueue.addToSendQueue(new RecipeOffsetPacket(offset));
	}
}
