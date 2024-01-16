package useless.polymorph.mixin;

import net.minecraft.client.option.BooleanOption;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.Option;
import net.minecraft.client.option.RangeOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.polymorph.ISettings;
@Mixin(value = GameSettings.class, remap = false)
public class GameSettingsMixin implements ISettings {
	@Unique
	private final GameSettings thisAs = (GameSettings)(Object)this;
	@Unique
	public BooleanOption smallButtons = new BooleanOption(thisAs, "polymorph.small.buttons", true);
	@Unique
	public RangeOption invX = new RangeOption(thisAs, "polymorph.inv.x", 152 + 50, 176 + 100);
	@Unique
	public RangeOption invY = new RangeOption(thisAs, "polymorph.inv.y", 60 + 50, 166 + 100);
	@Unique
	public RangeOption benchX = new RangeOption(thisAs, "polymorph.bench.x", 101 + 50, 176 + 100);
	@Unique
	public RangeOption benchY = new RangeOption(thisAs, "polymorph.bench.y", 62 + 50, 166 + 100);

	@Override
	public BooleanOption polymorph$getSmallButtons() {
		return smallButtons;
	}

	@Override
	public RangeOption polymorph$getInvX() {
		return invX;
	}

	@Override
	public RangeOption polymorph$getInvY() {
		return invY;
	}

	@Override
	public RangeOption polymorph$getBenchX() {
		return benchX;
	}

	@Override
	public RangeOption polymorph$getBenchY() {
		return benchY;
	}
	@Inject(method = "getDisplayString(Lnet/minecraft/client/option/Option;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
	private void changeDisplayString(Option<?> option, CallbackInfoReturnable<String> cir){
		if (option == invX || option == invY || option == benchX || option == benchY){
			RangeOption rangeOption = (RangeOption) option;
			int v = rangeOption.value;
			cir.setReturnValue(String.valueOf(v-50));
		}
	}
}
