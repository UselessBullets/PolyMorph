package useless.polymorph.mixin;

import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.polymorph.PolyMorph;

@Mixin(value = ContainerPlayer.class, remap = false)
public class ContainerPlayerMixin {
	@Shadow
	public InventoryPlayer playerInv;

	@Inject(method = "onCraftMatrixChanged(Lnet/minecraft/core/player/inventory/IInventory;)V", at = @At("HEAD"))
	private void setLastCrafted(IInventory iinventory, CallbackInfo ci){
		PolyMorph.lastCraftedPlayer = playerInv.player;
	}
}
