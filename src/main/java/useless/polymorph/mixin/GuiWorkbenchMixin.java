package useless.polymorph.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiCrafting;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.ContainerWorkbench;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import useless.polymorph.ActionButton;
import useless.polymorph.PolyMorph;
import useless.polymorph.PolyMorphClient;

@Environment(EnvType.CLIENT)
@Mixin(value = GuiCrafting.class, remap = false)
public abstract class GuiWorkbenchMixin extends GuiContainer {
	public GuiWorkbenchMixin(Container container) {
		super(container);
	}
	@Override
	public void init(){
		super.init();
		ActionButton button = new ActionButton(-3000, "/assets/polymorph/gui/morphButton.png", (width - xSize)/2 + PolyMorphClient.getXBench(), (height - ySize)/2 + PolyMorphClient.getYBench(), PolyMorphClient.getU(), 0, PolyMorphClient.getSize(), PolyMorphClient.getSize());
		button.setActionCheck(() -> {
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
				PolyMorph.setRecipeOffset(mc.thePlayer, PolyMorph.getRecipeOffset(mc.thePlayer) - 1);
			} else {
				PolyMorph.setRecipeOffset(mc.thePlayer, PolyMorph.getRecipeOffset(mc.thePlayer) + 1);
			}
			inventorySlots.onCraftMatrixChanged(((ContainerWorkbench)inventorySlots).craftMatrix);
		});
		button.setTickCheck(() ->  {
			button.enabled = PolyMorphClient.isEnabled();
			button.visible = PolyMorphClient.canCycle();
		});
		controlList.add(button);
	}
}
