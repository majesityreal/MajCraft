package com.majesity.majcraft.items;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.entities.projectile.CrawlerVenomEntity;
import com.majesity.majcraft.entities.projectile.WandSnowballEntity;
import com.majesity.majcraft.init.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WandSnowball extends Item {

    public WandSnowball() {
        super(new Properties()
                .group(MajCraft.TAB)
                .maxStackSize(1)
                .maxDamage(64)
                .defaultMaxDamage(64)
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        CrawlerVenomEntity test = new CrawlerVenomEntity(ModEntityTypes.CRAWLER_VENOM.get(),worldIn,playerIn.getPosX()+playerIn.getForward().getX(),playerIn.getPosY()+1.25,playerIn.getPosZ()+playerIn.getForward().getZ(),playerIn.getForward().getX(),playerIn.getForward().getY(),playerIn.getForward().getZ());
        test.setNoGravity(true);
        worldIn.addEntity(test);
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        heldItem.setDamage(heldItem.getDamage()+1);
        playerIn.getCooldownTracker().setCooldown(heldItem.getItem(),40);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
