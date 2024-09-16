package org.agmas.bleedingedge;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.agmas.bleedingedge.block.BloodBlock;
import org.agmas.bleedingedge.item.BloodItem;

import java.util.ArrayList;
import java.util.Optional;

public class BleedingEdgeBlocks {
    public static final Block BLOOD_BLOCK = register(
            new BloodBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).strength(0.3F).sounds(BlockSoundGroup.GLASS).dropsNothing()),
            "blood_block",
            Items.NETHERRACK
    );

    public static Block register(Block item, String id, Item displayItem) {
        // Create the identifier for the item.
        Identifier itemID = Identifier.of("bleedingedge", id);

        // Register the item.
        Block registeredItem = Registry.register(Registries.BLOCK, itemID, item);
        Item registeredBlockItem = Registry.register(Registries.ITEM, itemID ,new PolymerBlockItem(registeredItem, new Item.Settings(), displayItem));

        // Return the registered item!
        return registeredItem;
    }
    public static void initialize() {
    }
}
