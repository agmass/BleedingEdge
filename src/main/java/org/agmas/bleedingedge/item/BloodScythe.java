package org.agmas.bleedingedge.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.agmas.scythes.items.Scythe;

import java.util.List;

public class BloodScythe extends Scythe {
    public BloodScythe(Settings settings, ToolMaterial material, String modelName, Item item, String modelNamespace) {
        super(settings, material, modelName, item, modelNamespace);
    }

    public BloodScythe(Settings settings, ToolMaterial material, String modelName, Item item) {
        super(settings, material, modelName, item);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        tooltip.add(Text.literal("Quickly steals a few hearts from the target once hit."));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
