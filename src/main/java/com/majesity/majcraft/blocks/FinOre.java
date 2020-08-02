package com.majesity.majcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class FinOre extends Block {


    public FinOre() {
        super(Properties.create(Material.PORTAL)
                // same hardness as a diamond block
                // same resistance as end stone and dragon egg
                .hardnessAndResistance(5.0f,9.0f)
                .sound(SoundType.METAL)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool()
                //.setLightLevel(value -> 15)
        );
    }
}
