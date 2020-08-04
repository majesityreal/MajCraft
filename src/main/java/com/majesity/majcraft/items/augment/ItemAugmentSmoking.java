package com.majesity.majcraft.items.augment;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ItemAugmentSmoking extends ItemAugment {


    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent("+Halves the cooktime for smoking recipes.").setStyle(Style.EMPTY.setFormatting((TextFormatting.GREEN))));
        tooltip.add(new StringTextComponent("-Only allows for smoking recipes.").setStyle(Style.EMPTY.setFormatting(TextFormatting.DARK_RED)));
    }
}