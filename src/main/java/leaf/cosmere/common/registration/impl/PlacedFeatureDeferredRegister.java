package leaf.cosmere.common.registration.impl;

import leaf.cosmere.common.registration.WrappedDeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Supplier;

public class PlacedFeatureDeferredRegister extends WrappedDeferredRegister<PlacedFeature>
{

	public PlacedFeatureDeferredRegister(String modid)
	{
		super(modid, Registry.PLACED_FEATURE_REGISTRY);
	}

	public <PLACED_FEATURE extends PlacedFeature> PlacedFeatureRegistryObject<PLACED_FEATURE> register(String name, Supplier<PLACED_FEATURE> sup)
	{
		return register(name, sup, PlacedFeatureRegistryObject::new);
	}
}