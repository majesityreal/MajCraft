package com.majesity.majcraft.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {

    PlayerEntity getClientPlayer();

    World getClientWorld();

}