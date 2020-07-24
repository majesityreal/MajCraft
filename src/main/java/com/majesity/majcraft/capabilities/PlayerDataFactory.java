package com.majesity.majcraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class PlayerDataFactory implements IPlayerData{

    private float health;
    private int strength;
    private int dexterity;
    private int intelligence;

    public PlayerDataFactory() {
    }

    /*
    public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerData.class, new Capability.IStorage<IPlayerData>() {
            @Override
            public INBT writeNBT(final Capability<IPlayerData> capability, final IPlayerData instance, final Direction side) {
                return instance instanceof PlayerData ? ((PlayerData)instance).serializeNBT() : new CompoundNBT();
            }

            @Override
            public void readNBT(final Capability<IPlayerData> capability, final IPlayerData instance, final Direction side, final INBT nbt) {
                instance.setHealth(((IntNBT) nbt).getInt());
            }
        }, () -> new PlayerData());
    } */

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float amount) {
        this.health = amount;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getDexterity() {
        return dexterity;
    }

    @Override
    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public void setStrength(int amount) {
        this.strength = amount;
    }

    @Override
    public void setDexterity(int amount) {
        this.dexterity = amount;
    }

    @Override
    public void setIntelligence(int amount) {
        this.intelligence = amount;
    }

    @Override
    public void addStrength(int amount) {
        this.strength += amount;
    }

    @Override
    public void addDexterity(int amount) {
        this.dexterity += amount;
    }

    @Override
    public void addIntelligence(int amount) {
        this.intelligence += amount;
    }

}
