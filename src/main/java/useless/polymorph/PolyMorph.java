package useless.polymorph;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShapeless;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.NetworkHelper;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.ArrayList;
import java.util.List;


public class PolyMorph implements ModInitializer, RecipeEntrypoint {
	public static String MOD_ID = "polymorph";
	public static String OFFSET_CHANNEL = MOD_ID + "|" + "OFFSET";
	public static int recipesAmount = 0;
	public static EntityPlayer lastCraftedPlayer;
	public static int getRecipeOffset(EntityPlayer player){
		return ((IDataStorage)player).polymorph$getRecipeOffset();
	}
	public static void setRecipeOffset(EntityPlayer player, int recipeOffset){
		((IDataStorage)player).polymorph$setRecipeOffset(recipeOffset);
	}
	@Override
	public void onRecipesReady() {
//		Recipes for Testing
//		List<RecipeSymbol> symbols = new ArrayList<>();
//		symbols.add(new RecipeSymbol(Block.dirt.getDefaultStack()));
//		Registries.RECIPES.WORKBENCH.register("test1", new RecipeEntryCraftingShapeless(symbols, Item.diamond.getDefaultStack()));
//		Registries.RECIPES.WORKBENCH.register("test1", new RecipeEntryCraftingShapeless(symbols, Item.ingotSteel.getDefaultStack()));
//		Registries.RECIPES.WORKBENCH.register("test1", new RecipeEntryCraftingShapeless(symbols, Item.ingotIron.getDefaultStack()));
//		Registries.RECIPES.WORKBENCH.register("test1", new RecipeEntryCraftingShapeless(symbols, Item.ingotGold.getDefaultStack()));
	}
	@Override
	public void onInitialize() {
	}

    @Override
    public void initNamespaces() {
    }
}
