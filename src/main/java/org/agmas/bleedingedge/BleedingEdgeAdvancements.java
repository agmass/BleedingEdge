package org.agmas.bleedingedge;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

public class BleedingEdgeAdvancements {
    public static AdvancementEntry EYESEEYOU;

    public static void initialize(MinecraftServer server) {
        EYESEEYOU = server.getAdvancementLoader().get(Identifier.of("bleedingedge", "mockery"));
    }
}
