package com.majesity.majcraft.capabilities;

public interface IPlayerData {

    float getHealth();
    void setHealth(float amount);

    int getStrength();
    int getDexterity();
    int getIntelligence();

    void setStrength(int amount);
    void setDexterity(int amount);
    void setIntelligence(int amount);

    void addStrength(int amount);
    void addDexterity(int amount);
    void addIntelligence(int amount);

}
