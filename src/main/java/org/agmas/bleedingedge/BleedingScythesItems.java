package org.agmas.bleedingedge;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import org.agmas.bleedingedge.materials.BloodMaterial;
import org.agmas.scythes.items.Scythe;
import org.agmas.scythes.materials.CloudMaterial;

public class BleedingScythesItems
{

    public static Item bloodScythe;

    public static void init() {
        BleedingScythesEffects.init();
        bloodScythe = org.agmas.scythes.ScythesItems.register(
                new org.agmas.scythes.items.Scythe(new Item.Settings().attributeModifiers(Scythe.createAttributeModifiers(BloodMaterial.INSTANCE, 0f, -3.2f)),
                        BloodMaterial.INSTANCE,
                        "blood_scythe",
                        Items.GOLDEN_HOE,
                        "bleedingedge"),
                "blood_scythe");


    }
}
