package useless.polymorph;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.entity.player.EntityPlayer;
import turniplabs.halplibe.helper.ModVersionHelper;
import turniplabs.halplibe.helper.NetworkHelper;


public class PolyMorph implements ModInitializer {
	public static String MOD_ID = "polymorph";
	public static int recipesAmount = 0;
	public static EntityPlayer lastCraftedPlayer;
	public static boolean canCycle(){
		return recipesAmount > 1;
	}
	public static boolean isEnabled(){
		return ModVersionHelper.isModPresent(MOD_ID);
	}
	public static int getRecipeOffset(EntityPlayer player){
		return ((IDataStorage)player).polymorph$getRecipeOffset();
	}
	public static void setRecipeOffset(EntityPlayer player, int recipeOffset){
		((IDataStorage)player).polymorph$setRecipeOffset(recipeOffset);
	}
	@Override
	public void onInitialize() {
		NetworkHelper.register(RecipeOffsetPacket.class, true, false);
	}
}
