package useless.polymorph;

import net.minecraft.client.option.BooleanOption;
import net.minecraft.client.option.RangeOption;

public interface ISettings {
	BooleanOption polymorph$getSmallButtons();
	RangeOption polymorph$getInvX();
	RangeOption polymorph$getInvY();
	RangeOption polymorph$getBenchX();
	RangeOption polymorph$getBenchY();
}
