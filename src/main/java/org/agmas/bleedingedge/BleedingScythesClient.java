package org.agmas.bleedingedge;

import eu.pb4.polymer.networking.api.client.PolymerClientNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.nbt.NbtInt;

public class BleedingScythesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        PolymerClientNetworking.setClientMetadata(Bleedingedge.REGISTER_PACKET, NbtInt.of(1));
    }
}
