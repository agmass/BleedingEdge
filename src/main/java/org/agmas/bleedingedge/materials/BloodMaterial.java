package org.agmas.bleedingedge.materials;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import org.agmas.bleedingedge.BleedingEdgeItems;

public class BloodMaterial implements ToolMaterial {
    public static final BloodMaterial INSTANCE = new BloodMaterial();
    @Override
    public int getDurability() {
        return 200;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7.0f;
    }

    @Override
    public float getAttackDamage() {
        return 0.5F;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return null;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(BleedingEdgeItems.BLOOD.asItem());
    }
}
