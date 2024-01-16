package useless.polymorph.mixin;

import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import useless.polymorph.IDataStorage;
@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin implements IDataStorage {
	@Unique
	public int recipeOffset = 0;
	@Override
	public int polymorph$getRecipeOffset() {
		return recipeOffset;
	}
	@Override
	public void polymorph$setRecipeOffset(int offset) {
		this.recipeOffset = offset;
	}
}
