package com.majesity.majcraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerData implements IPlayerData, ICapabilitySerializable<CompoundNBT> {
    @CapabilityInject(IPlayerData.class)
    public static Capability<IPlayerData> INSTANCE = null;
    private final LazyOptional<IPlayerData> holder = LazyOptional.of(() -> {
        return this;
    });

    private float health;
    private int strength;
    private int dexterity;
    private int intelligence;

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

    @Nonnull @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return INSTANCE.orEmpty(cap, this.holder);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("Health", this.health);
        nbt.putInt("Strength", this.strength);
        nbt.putInt("Dexterity", this.dexterity);
        nbt.putInt("Intelligence", this.intelligence);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.health = nbt.getFloat("Health");
        this.strength = nbt.getInt("Strength");
        this.dexterity = nbt.getInt("Dexterity");
        this.intelligence = nbt.getInt("Intelligence");
    }

    private static class Storage implements Capability.IStorage<IPlayerData> {
        private Storage() {
        }

        @Nullable @Override
        public INBT writeNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side) {
            return instance instanceof PlayerData ? ((PlayerData)instance).serializeNBT() : new CompoundNBT();
        }
        @Override
        public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side, INBT nbt) {
            if (instance instanceof PlayerData) {
                ((PlayerData)instance).deserializeNBT((CompoundNBT)nbt);
            }

        }
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerData.class, new PlayerData.Storage(), PlayerData::new);
    }
}
