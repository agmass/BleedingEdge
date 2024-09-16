package org.agmas.bleedingedge;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.agmas.bleedingedge.effects.DrainEffect;
import org.agmas.bleedingedge.effects.FunnelEffect;
import org.agmas.bleedingedge.materials.BloodMaterial;
import org.agmas.scythes.items.Scythe;

public class BleedingScythesEffects
{

    public static final StatusEffect DRAIN;
    public static final StatusEffect FUNNEL;

    static {
        DRAIN = Registry.register(Registries.STATUS_EFFECT, Identifier.of("bleedingedge", "drain"), new DrainEffect(StatusEffectCategory.HARMFUL, Colors.RED));
        FUNNEL = Registry.register(Registries.STATUS_EFFECT, Identifier.of("bleedingedge", "funnel"), new FunnelEffect(StatusEffectCategory.BENEFICIAL, Colors.GRAY));
    }

    public static void init() {}
}
