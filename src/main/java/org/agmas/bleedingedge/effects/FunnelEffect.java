package org.agmas.bleedingedge.effects;

import eu.pb4.polymer.core.api.other.PolymerStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FunnelEffect extends StatusEffect implements PolymerStatusEffect {
    public FunnelEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}
