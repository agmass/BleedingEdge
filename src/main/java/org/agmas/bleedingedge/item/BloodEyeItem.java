package org.agmas.bleedingedge.item;

import eu.pb4.polymer.common.api.PolymerCommonUtils;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.networking.api.server.PolymerServerNetworking;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtInt;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.agmas.bleedingedge.BleedingEdgeAdvancements;
import org.agmas.bleedingedge.Bleedingedge;
import org.jetbrains.annotations.Nullable;

public class BloodEyeItem extends Item implements PolymerItem {

    PolymerModelData modelData = PolymerResourcePackUtils.requestModel(Items.ENDER_EYE, Identifier.of("bleedingedge","item/blood_eye"));

    public BloodEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            var entities = world.getOtherEntities(user, user.getBoundingBox().expand(80,100,80));
            world.playSound((PlayerEntity) null,user.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_WOLOLO, SoundCategory.MASTER,1,1);
            user.getStackInHand(hand).decrementUnlessCreative(1,user);
            entities.forEach((e)->{
                if (e instanceof WardenEntity) {
                    if (user instanceof ServerPlayerEntity spe) {
                        spe.getAdvancementTracker().grantCriterion(BleedingEdgeAdvancements.EYESEEYOU, "aaa");
                    }
                }
                if (e instanceof ServerPlayerEntity spe) {
                    spe.sendMessage(Text.literal("You were revealed by a blood eye..").formatted(Formatting.RED), true);
                }
                if (e instanceof LivingEntity le) {
                    le.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20*4,0));
                }
            });
        }
        return super.use(world, user, hand);
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
            return Items.ENDER_EYE;
        }
    }
}
