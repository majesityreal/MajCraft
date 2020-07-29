package com.majesity.majcraft.items;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.entities.WandFireballEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WandFireball extends Item {

    public WandFireball() {
        super(new Item.Properties()
                .group(MajCraft.TAB)
                .maxStackSize(1)
                .maxDamage(64)
                .defaultMaxDamage(64)
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        WandFireballEntity test = new WandFireballEntity(worldIn,playerIn.getPosX()+playerIn.getForward().getX(),playerIn.getPosY()+1.25,playerIn.getPosZ()+playerIn.getForward().getZ(),playerIn.getForward().getX(),playerIn.getForward().getY(),playerIn.getForward().getZ());
        worldIn.addEntity(test);
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        heldItem.setDamage(heldItem.getDamage()+1);
        playerIn.getCooldownTracker().setCooldown(heldItem.getItem(),40);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
