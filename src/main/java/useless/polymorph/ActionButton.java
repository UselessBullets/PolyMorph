package useless.polymorph;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTexturedButton;

public class ActionButton extends GuiTexturedButton {
	private final Runnable runnable;
	private Runnable tickCheck;
	public ActionButton(int id, String texturePath, int xPosition, int yPosition, int u, int v, int width, int height, Runnable action) {
		super(id, texturePath, xPosition, yPosition, u, v, width, height);
		runnable = action;
	}
	public ActionButton setTickCheck(Runnable tickCheck){
		this.tickCheck = tickCheck;
		return this;
	}
	public boolean mouseClicked(Minecraft mc, int mouseX, int mouseY) {
		if (super.mouseClicked(mc, mouseX, mouseY)){
			runnable.run();
		}
		return super.mouseClicked(mc, mouseX, mouseY);
	}
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (tickCheck != null){
			tickCheck.run();
		}
		super.drawButton(mc, mouseX, mouseY);
	}
}
