/*
 * File updated ~ 13 - 6 - 2023 ~ Leaf
 */

package leaf.cosmere.hemalurgy.client.render.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import leaf.cosmere.hemalurgy.client.render.HemalurgyLayerDefinitions;
import leaf.cosmere.hemalurgy.client.render.model.KolossModel;
import leaf.cosmere.hemalurgy.common.Hemalurgy;
import leaf.cosmere.hemalurgy.common.entity.Koloss;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KolossRenderer extends MobRenderer<Koloss, KolossModel<Koloss>>
{
	private static final ResourceLocation TEXTURE = Hemalurgy.rl("textures/entity/koloss/koloss_large.png");

	public KolossRenderer(EntityRendererProvider.Context context)
	{
		super(context, new KolossModel<>(context.bakeLayer(HemalurgyLayerDefinitions.KOLOSS_LARGE)), 1.0F);

		this.addLayer(new KolossItemInHandLayer<>(this, context.getItemInHandRenderer())
		{
			public void render(PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, Koloss pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch)
			{
				//only show weapon if aggressive?
				//if (pLivingEntity.isAggressive())
				{
					super.render(poseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks, pAgeInTicks, pNetHeadYaw, pHeadPitch);
				}
			}
		});

	}

	@Override
	public ResourceLocation getTextureLocation(Koloss pEntity)
	{
		return TEXTURE;
	}
}
