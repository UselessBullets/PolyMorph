package useless.polymorph.mixin;

import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.ContainerWorkbench;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.polymorph.PolyMorph;

@Mixin(value = ContainerWorkbench.class, remap = false)
public abstract class ContainerCraftingMixin extends Container {
	@Inject(method = "onCraftMatrixChanged(Lnet/minecraft/core/player/inventory/IInventory;)V", at = @At("HEAD"))
	private void setLastCrafted(IInventory iinventory, CallbackInfo ci){
		PolyMorph.lastCraftedPlayer = ((InventoryPlayer)inventorySlots.get(12).getInventory()).player;
	}
}
