package useless.polymorph;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.options.GuiOptions;
import net.minecraft.client.gui.options.components.BooleanOptionComponent;
import net.minecraft.client.gui.options.components.IntegerOptionComponent;
import net.minecraft.client.gui.options.components.ToggleableOptionComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import turniplabs.halplibe.helper.ModVersionHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.version.EnumModList;

import static useless.polymorph.PolyMorph.MOD_ID;
import static useless.polymorph.PolyMorph.recipesAmount;

public class PolyMorphClient implements ClientStartEntrypoint {
	public static ISettings modSettings;
	public static boolean useSmallButtons(){
		return true;
	}
	public static boolean canCycle(){
		return (recipesAmount > 1) && isEnabled();
	}
	public static boolean isEnabled(){
		return ModVersionHelper.isModPresent(MOD_ID);
	}
	public static int getU(){
		return useSmallButtons() ? 20 : 0;
	}
	public static int getXInv(){
		return modSettings.polymorph$getInvX().value - 50 - getSize()/2;
	}
	public static int getYInv(){
		return modSettings.polymorph$getInvY().value - 50 - getSize()/2;
	}
	public static int getXBench(){
		return modSettings.polymorph$getBenchX().value - 50 - getSize()/2;
	}
	public static int getYBench(){
		return modSettings.polymorph$getBenchY().value - 50 - getSize()/2;
	}
	public static int getSize(){
		return 12 + (useSmallButtons() ? 0 : 8);
	}
	public static OptionsPage polyMorphOptions;
	@Override
	public void beforeClientStart() {

	}

	@Override
	public void afterClientStart() {
		modSettings = (ISettings) Minecraft.getMinecraft(Minecraft.class).gameSettings;
		polyMorphOptions = new OptionsPage("polymorph.gui.options.page.title")
			.withComponent(new BooleanOptionComponent(modSettings.polymorph$getSmallButtons()))
			.withComponent(new ToggleableOptionComponent<>(modSettings.polymorph$getInvX()))
			.withComponent(new ToggleableOptionComponent<>(modSettings.polymorph$getInvY()))
			.withComponent(new ToggleableOptionComponent<>(modSettings.polymorph$getBenchX()))
			.withComponent(new ToggleableOptionComponent<>(modSettings.polymorph$getBenchY()));
		if (!ModVersionHelper.isModPresent("modmenu", EnumModList.LOCAL)){
			OptionsPages.register(polyMorphOptions);
		}
	}
	public static GuiScreen getOptionsScreen(GuiScreen guiScreen){
		return new GuiOptions(guiScreen, Minecraft.getMinecraft(Minecraft.class).gameSettings, polyMorphOptions);
	}
}
