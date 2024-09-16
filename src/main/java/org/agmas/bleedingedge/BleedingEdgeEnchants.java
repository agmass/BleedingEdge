package org.agmas.bleedingedge;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class BleedingEdgeEnchants {
    public static final RegistryKey<Enchantment> BLEEDING_EDGE = of("bleeding_edge");
    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of("bleedingedge", name));
    }

    public static void initialize() {

    }
}
