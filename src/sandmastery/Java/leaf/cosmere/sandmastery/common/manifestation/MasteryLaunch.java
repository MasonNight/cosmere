/*
 * File updated ~ 26 - 5 - 2023 ~ Leaf
 */

package leaf.cosmere.sandmastery.common.manifestation;

import leaf.cosmere.api.Manifestations;
import leaf.cosmere.api.Taldain;
import leaf.cosmere.api.math.VectorHelper;
import leaf.cosmere.api.spiritweb.ISpiritweb;
import leaf.cosmere.common.cap.entity.SpiritwebCapability;
import leaf.cosmere.sandmastery.common.capabilities.SandmasterySpiritwebSubmodule;
import leaf.cosmere.sandmastery.common.utils.MiscHelper;
import leaf.cosmere.sandmastery.common.utils.SandmasteryConstants;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class MasteryLaunch extends SandmasteryManifestation
{
	public MasteryLaunch(Taldain.Mastery mastery)
	{
		super(mastery);
	}

	@Override
	public boolean tick(ISpiritweb data)
	{
		boolean enabledViaHotkey = MiscHelper.enabledViaHotkey(data, SandmasteryConstants.LAUNCH_HOTKEY_FLAG);
		if (getMode(data) > 0 && enabledViaHotkey)
		{
			return performEffectServer(data);
		}
		return false;
	}

	protected boolean performEffectServer(ISpiritweb data)
	{
		SpiritwebCapability playerSpiritweb = (SpiritwebCapability) data;
		SandmasterySpiritwebSubmodule submodule = (SandmasterySpiritwebSubmodule) playerSpiritweb.getSubmodule(Manifestations.ManifestationTypes.SANDMASTERY);

		if (!submodule.adjustHydration(-10, false))
		{
			return false;
		}
		int scaleFactor = getMode(data);
		if (notEnoughChargedSand(data))
		{
			return false;
		}

		LivingEntity living = data.getLiving();
		Vec3 direction = living.getForward();
		Vec3 add = living.getDeltaMovement().add(direction.multiply(scaleFactor, scaleFactor, scaleFactor));
		living.setDeltaMovement(VectorHelper.ClampMagnitude(add, 10));
		living.hurtMarked = true; // Allow the game to move the player
		living.resetFallDistance();

		data.setMode(this, getMode(data) - 1);
		data.syncToClients(null);

		submodule.adjustHydration(-10, true);
		useChargedSand(data);
		return true;
	}
}
