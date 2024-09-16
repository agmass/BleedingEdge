package org.agmas.bleedingedge.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class PotionSyringe extends Item implements PolymerItem {


    public PotionSyringe(Settings settings) {
        super(settings);
    }

    public ItemStack getDefaultStack() {
        ItemStack itemStack = super.getDefaultStack();
        itemStack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Potions.WATER));
        return itemStack;
    }


    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        PotionContentsComponent potionContentsComponent = (PotionContentsComponent)stack.get(DataComponentTypes.POTION_CONTENTS);
        tooltip.add(Text.literal("Stores potion effects."));
        tooltip.add(Text.literal("Attack players to give them the potion effects,"));
        tooltip.add(Text.literal("Use to give yourself the potion effects."));
        if (potionContentsComponent != null) {
            Objects.requireNonNull(tooltip);
            potionContentsComponent.buildTooltip(tooltip::add, 1.0F, context.getUpdateTickRate());
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.getStackInHand(hand).get(DataComponentTypes.POTION_CONTENTS) != null)
            user.getStackInHand(hand).get(DataComponentTypes.POTION_CONTENTS).forEachEffect((user::addStatusEffect));
        user.getStackInHand(hand).decrementUnlessCreative(1, user);
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.get(DataComponentTypes.POTION_CONTENTS) != null)
            stack.get(DataComponentTypes.POTION_CONTENTS).forEachEffect((target::addStatusEffect));
        stack.decrementUnlessCreative(1, attacker);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.SPLASH_POTION;
    }
}
