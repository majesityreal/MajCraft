package com.majesity.majcraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerDataStorage implements Capability.IStorage<IPlayerData> {

    // THIS MAY NEED TO BE IPlayerData, we'll see
    @Nullable
    @Override
    public INBT writeNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side) {
        CompoundNBT tag = new CompoundNBT();
        tag.putFloat("Health", instance.getHealth());
        tag.putInt("Strength", instance.getStrength());
        tag.putInt("Dexterity", instance.getDexterity());
        tag.putInt("Intelligence", instance.getIntelligence());
        return tag;
    }

    @Override
    public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side, INBT nbt) {
        CompoundNBT tag = (CompoundNBT) nbt;
        instance.setHealth(tag.getFloat("Health"));
        instance.setStrength(tag.getInt("Strength"));
        instance.setDexterity(tag.getInt("Dexterity"));
        instance.setIntelligence(tag.getInt("Intelligence"));
    }
}
