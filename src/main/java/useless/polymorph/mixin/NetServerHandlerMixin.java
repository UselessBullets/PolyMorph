package useless.polymorph.mixin;

import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet250CustomPayload;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.ContainerWorkbench;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.polymorph.PolyMorph;

import java.math.BigInteger;

@Mixin(value = NetServerHandler.class, remap = false)
public abstract class NetServerHandlerMixin extends NetHandler {
	@Shadow
	private EntityPlayerMP playerEntity;

	@Inject(method = "handleCustomPayload(Lnet/minecraft/core/net/packet/Packet250CustomPayload;)V", at = @At("HEAD"))
	public void handleMorphOffset(Packet250CustomPayload packet, CallbackInfo ci){
		if (packet.channel.equals(PolyMorph.OFFSET_CHANNEL)) {
			PolyMorph.setRecipeOffset(playerEntity, new BigInteger(packet.data).intValue());
			if (playerEntity.craftingInventory instanceof ContainerPlayer){
				playerEntity.inventorySlots.onCraftMatrixChanged(((ContainerPlayer)playerEntity.inventorySlots).craftMatrix);
			}
			if (playerEntity.craftingInventory instanceof ContainerWorkbench){
				playerEntity.craftingInventory.onCraftMatrixChanged(((ContainerWorkbench)playerEntity.craftingInventory).craftMatrix);
			}
		}
	}
}
