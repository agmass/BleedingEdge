package org.agmas.bleedingedge;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.agmas.bleedingedge.item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BleedingEdgeItems {
    public static final Item BLOOD = register(
            new BloodItem(new Item.Settings().food(new FoodComponent(1,1,true, 0.4f, Optional.empty(),new ArrayList<>()))),
            "blood"
    );
    public static final Item BLOOD_EYE = register(
            new BloodEyeItem(new Item.Settings()),
            "blood_eye"
    );
    public static final Item EMPTY_POTION_SYRINGE = register(
            new EmptyPotionSyringe(new Item.Settings()),
            "empty_potion_syringe"
    );
    public static final Item POTION_SYRINGE = register(
            new PotionSyringe(new Item.Settings()),
            "potion_syringe"
    );
    public static final Item BLOOD_APPLE = register(
            new BloodApple(new Item.Settings().food(new FoodComponent(0,0,true,1f, Optional.empty(), List.of(new FoodComponent.StatusEffectEntry(new StatusEffectInstance(StatusEffects.POISON, 100, 1), 1.0F),new FoodComponent.StatusEffectEntry(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), 1.0F),new FoodComponent.StatusEffectEntry(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1), 1.0F),new FoodComponent.StatusEffectEntry(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1), 1.0F))))),
            "blood_apple"
    );

    public static Item register(Item item, String id) {
        // Create the identifier for the item.
        Identifier itemID = Identifier.of("bleedingedge", id);

        // Register the item.
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);


        // Return the registered item!
        return registeredItem;
    }
    public static void initialize() {
        if (FabricLoader.getInstance().isModLoaded("scythes")) {
            BleedingScythesItems.init();

        } else {
            Log.warn(LogCategory.GENERAL, "\n* * * * *\nYou don't have Server-Side Scythes installed! The Blood Scythe will not appear ingame.\nYou can safely ignore all upcoming errors related to the blood scythe.\nInstall SSS here - https://modrinth.com/mod/server-side-scythes\n* * * * *");
        }

    }
}
