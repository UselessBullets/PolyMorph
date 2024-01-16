package useless.polymorph.mixin;

import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.ContainerWorkbench;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import useless.polymorph.INetHandler;
import useless.polymorph.PolyMorph;
import useless.polymorph.RecipeOffsetPacket;

@Mixin(value = NetServerHandler.class, remap = false)
public abstract class NetServerHandlerMixin extends NetHandler implements INetHandler {
	@Shadow
	private EntityPlayerMP playerEntity;

	@Override
	public void polymorph$handleOffset(RecipeOffsetPacket packet) {
		System.out.println("Packet received! " + packet.offset);
		PolyMorph.setRecipeOffset(playerEntity, packet.offset);
		if (playerEntity.craftingInventory instanceof ContainerPlayer){
			playerEntity.inventorySlots.onCraftMatrixChanged(((ContainerPlayer)playerEntity.inventorySlots).craftMatrix);
		}
		if (playerEntity.craftingInventory instanceof ContainerWorkbench){
			playerEntity.craftingInventory.onCraftMatrixChanged(((ContainerWorkbench)playerEntity.craftingInventory).craftMatrix);
		}
	}
}
