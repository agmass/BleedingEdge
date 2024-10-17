package org.agmas.bleedingedge.effects;

import eu.pb4.polymer.core.api.other.PolymerStatusEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import org.agmas.bleedingedge.BleedingScythesEffects;

public class DrainEffect extends StatusEffect implements PolymerStatusEffect {
    public DrainEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.timeUntilRegen = 0;
        entity.damage(entity.getDamageSources().magic(), 0.4f);
        if (entity.getAttacker() instanceof ServerPlayerEntity spe) {
            if (spe.hasStatusEffect(spe.getRegistryManager().get(RegistryKeys.STATUS_EFFECT).getEntry(BleedingScythesEffects.FUNNEL))) {
                spe.heal(0.15f);
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 2 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }
}
