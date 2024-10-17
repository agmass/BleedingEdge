package org.agmas.bleedingedge.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.agmas.bleedingedge.BleedingEdgeEnchants;
import org.agmas.bleedingedge.BleedingEdgeItems;
import org.agmas.bleedingedge.BleedingScythesEffects;
import org.agmas.bleedingedge.BleedingScythesItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "damage", at = @At(value = "HEAD"))
    public void spreadBlood(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getWeaponStack() != null) {
            if (source.getAttacker() != null) {
                Registry<Enchantment> enchantRegistry = source.getAttacker().getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);
                Registry<StatusEffect> statuseffectRegistry = source.getAttacker().getWorld().getRegistryManager().get(RegistryKeys.STATUS_EFFECT);

                if (FabricLoader.getInstance().isModLoaded("scythes")) {
                    if (source.getWeaponStack().getItem().equals(BleedingScythesItems.bloodScythe)) {
                        int charge = (int) Math.min(amount, 1);
                        addStatusEffect(new StatusEffectInstance(statuseffectRegistry.getEntry(BleedingScythesEffects.DRAIN), 25*charge, 0) {
                            @Override
                            public boolean shouldShowIcon() {
                                return false;
                            }

                            @Override
                            public boolean isAmbient() {
                                return true;
                            }
                        });
                        if (source.getAttacker() instanceof LivingEntity spe) {
                            StatusEffectInstance inst = new StatusEffectInstance(statuseffectRegistry.getEntry(BleedingScythesEffects.FUNNEL), 25 * charge, 0) {
                                @Override
                                public boolean shouldShowIcon() {
                                    return false;
                                }

                                @Override
                                public boolean isAmbient() {
                                    return true;
                                }


                            };
                            spe.addStatusEffect(inst);
                        }
                    }
                }
                if (source.getWeaponStack().getEnchantments().getEnchantments().contains(enchantRegistry.getEntry(enchantRegistry.get(BleedingEdgeEnchants.BLEEDING_EDGE)))) {
                    me().getWorld().playSound((PlayerEntity) null,me().getBlockPos(), SoundEvents.BLOCK_SCULK_BREAK, SoundCategory.MASTER, 1f, 1.25f);

                    for (int i = 0; i < Math.max(amount/3, 1); i++) {

                        double d = (double)EntityType.ITEM.getHeight() / 2.0;
                        double e = me().getX() + 0.5 + MathHelper.nextDouble(me().getWorld().random, -0.25, 0.25);
                        double f = me().getY() + 0.5 + MathHelper.nextDouble(me().getWorld().random, -0.25, 0.25) - d;
                        double g = me().getZ() + 0.5 + MathHelper.nextDouble(me().getWorld().random, -0.25, 0.25);

                        var blood = new ItemEntity(me().getWorld(),e,f,g, BleedingEdgeItems.BLOOD.getDefaultStack());
                        blood.setPos(me().getX(), me().getY(), me().getZ());
                        me().getWorld().spawnEntity(blood);
                    }
                }
            }
        }
    }

    @Unique
    public LivingEntity me() {
        return (LivingEntity) (Object) this;
    }
}
