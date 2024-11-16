package org.agmas.bleedingedge.item;

import eu.pb4.polymer.common.api.PolymerCommonUtils;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.networking.api.server.PolymerServerNetworking;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtInt;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.agmas.bleedingedge.Bleedingedge;
import org.agmas.scythes.Scythes;
import org.jetbrains.annotations.Nullable;

public class BloodApple extends Item implements PolymerItem {

        PolymerModelData modelData = PolymerResourcePackUtils.requestModel(Items.APPLE, Identifier.of("bleedingedge","item/blood_apple"));

    public BloodApple(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, TooltipType tooltipType, RegistryWrapper.WrapperLookup lookup, @Nullable ServerPlayerEntity player) {
        ItemStack stack = PolymerItem.super.getPolymerItemStack(itemStack, tooltipType, lookup, player);
        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, modelData.asComponent());
        return stack;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        if (player == null) return this;
        if (PolymerServerNetworking.getMetadata(player.networkHandler, Bleedingedge.REGISTER_PACKET, NbtInt.TYPE) == NbtInt.of(1)) {
            return this;
        } else {
            return Items.APPLE;
        }
    }
}
