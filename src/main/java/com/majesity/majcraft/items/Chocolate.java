package com.majesity.majcraft.items;

import com.majesity.majcraft.MajCraft;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Chocolate extends Item {

    public Chocolate() {
        super(new Item.Properties()
                .group(MajCraft.TAB)
                .food(new Food.Builder()
                    .hunger(1)
                    .saturation(0.8F)
                    .effect(new EffectInstance(Effects.SPEED,300, 0),1)
                    .setAlwaysEdible()
                    .build())
        );
    }



}
