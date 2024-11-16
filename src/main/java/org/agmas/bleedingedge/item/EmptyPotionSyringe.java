package org.agmas.bleedingedge.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.agmas.bleedingedge.BleedingEdgeItems;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmptyPotionSyringe extends Item implements PolymerItem {


    public EmptyPotionSyringe(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getActiveStatusEffects().isEmpty()) {
            ItemStack filledPotionVial = BleedingEdgeItems.POTION_SYRINGE.getDefaultStack();
            var statusEffectInstances = new ArrayList<StatusEffectInstance>();
            user.getActiveStatusEffects().forEach((key, value) -> {
                statusEffectInstances.add(new StatusEffectInstance(value.getEffectType(), value.getDuration(), value.getAmplifier()));
            });
            user.clearStatusEffects();
            var potionComponent = new PotionContentsComponent(Optional.empty(), Optional.empty(), statusEffectInstances);
            filledPotionVial.set(DataComponentTypes.POTION_CONTENTS, potionComponent);
            user.getStackInHand(hand).decrementUnlessCreative(1, user);
            user.getInventory().insertStack(filledPotionVial);
        }
        return super.use(world, user, hand);
    }



    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        tooltip.add(Text.literal("Stores potion effects."));
        tooltip.add(Text.literal("Right Click to fill when you have potion effects."));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.GLASS_BOTTLE;
    }
}
