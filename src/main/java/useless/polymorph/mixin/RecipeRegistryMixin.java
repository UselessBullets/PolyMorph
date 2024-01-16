package useless.polymorph.mixin;

import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import useless.polymorph.PolyMorph;

import java.util.ArrayList;
import java.util.List;
@Mixin(value = RecipeRegistry.class, remap = false)
public abstract class RecipeRegistryMixin {
	@Shadow
	public abstract List<RecipeEntryCrafting<?, ?>> getAllCraftingRecipes();

	/**
	 * @author Useless
	 * @reason I need to significantly change this method not just return the first matching recipe
	 */
	@Overwrite()
	public ItemStack findMatchingRecipe(InventoryCrafting inventorycrafting){
		List<ItemStack> possibilities = new ArrayList<>();
		for (int i = 0; i < this.getAllCraftingRecipes().size(); ++i) {
			RecipeEntryCrafting<?, ?> recipe = this.getAllCraftingRecipes().get(i);
			if (!recipe.matches(inventorycrafting)) continue;
			possibilities.add(recipe.getCraftingResult(inventorycrafting));
		}
		PolyMorph.recipesAmount = possibilities.size();
		if (possibilities.isEmpty()) return null;
		int i = PolyMorph.getRecipeOffset(PolyMorph.lastCraftedPlayer);
		while (i < 0){
			i += possibilities.size();
		}
		return possibilities.get(i % possibilities.size());
	}
}
