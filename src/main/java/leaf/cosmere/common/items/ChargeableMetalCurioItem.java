/*
 * File updated ~ 24 - 4 - 2021 ~ Leaf
 */

package leaf.cosmere.common.items;

import leaf.cosmere.api.IHasMetalType;
import leaf.cosmere.api.Metals;
import leaf.cosmere.common.properties.PropTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ChargeableMetalCurioItem extends ChargeableItemBase implements IHasMetalType, ICurioItem
{
	private final Metals.MetalType metalType;

	public ChargeableMetalCurioItem(Metals.MetalType metalType, CreativeModeTab group)
	{
		super(PropTypes.Items.ONE.get().rarity(metalType.getRarity()).tab(group));
		this.metalType = metalType;
	}

	@Override
	public Metals.MetalType getMetalType()
	{
		return this.metalType;
	}


	@Override
	public boolean showAttributesTooltip(String identifier, ItemStack stack)
	{
		return false;
	}


	@Override
	public boolean makesPiglinsNeutral(SlotContext slotContext, ItemStack stack)
	{
		return makesPiglinsNeutral(stack, slotContext.entity());
	}

	@Override
	public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
	{
		return this.metalType == Metals.MetalType.GOLD;
	}
}
