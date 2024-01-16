package useless.polymorph.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiInventory;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.ContainerPlayer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.polymorph.ActionButton;
import useless.polymorph.PolyMorph;
import useless.polymorph.PolyMorphClient;

@Environment(EnvType.CLIENT)
@Mixin(value = GuiInventory.class, remap = false)
public abstract class GuiInventoryMixin extends GuiContainer {
	public GuiInventoryMixin(Container container) {
		super(container);
	}

	@Inject(method = "init()V", at = @At("TAIL"))
	private void addButton(CallbackInfo ci){
		ActionButton button = new ActionButton(-3000, "/assets/polymorph/gui/morphButton.png", (width - xSize)/2 + PolyMorphClient.getXInv(), (height - ySize)/2 + PolyMorphClient.getYInv(), PolyMorphClient.getU(), 0, PolyMorphClient.getSize(), PolyMorphClient.getSize());
		button.setActionCheck(() -> {
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
				PolyMorph.setRecipeOffset(mc.thePlayer, PolyMorph.getRecipeOffset(mc.thePlayer) - 1);
			} else {
				PolyMorph.setRecipeOffset(mc.thePlayer, PolyMorph.getRecipeOffset(mc.thePlayer) + 1);
			}
			inventorySlots.onCraftMatrixChanged(((ContainerPlayer)inventorySlots).craftMatrix);
		});
		button.setTickCheck(() ->  {
			button.enabled = PolyMorphClient.isEnabled();
			button.visible = PolyMorphClient.canCycle();
		});
		controlList.add(button);
	}
}
