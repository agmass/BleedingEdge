package org.agmas.bleedingedge;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.ModStatus;

public class Bleedingedge implements ModInitializer {

    @Override
    public void onInitialize() {
        BleedingEdgeEnchants.initialize();
        BleedingEdgeItems.initialize();
        BleedingEdgeBlocks.initialize();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register((a)->{
            a.add(BleedingEdgeItems.BLOOD);
            a.add(BleedingEdgeItems.BLOOD_APPLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((a)->{
            a.add(BleedingEdgeBlocks.BLOOD_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((a)->{
            a.add(BleedingEdgeItems.BLOOD_EYE);
            a.add(BleedingEdgeItems.EMPTY_POTION_SYRINGE);
        });
        ServerLifecycleEvents.SERVER_STARTED.register(BleedingEdgeAdvancements::initialize);
        PolymerResourcePackUtils.addModAssets("bleedingedge");


    }
}
