package com.majesity.majcraft.entities.projectile;

import com.majesity.majcraft.init.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = IRendersAsItem.class
)
public class AbstractCrawlerVenomEntity extends ProjectileItemEntity implements IRendersAsItem {
    private static final DataParameter<ItemStack> STACK = EntityDataManager.createKey(com.majesity.majcraft.entities.projectile.AbstractCrawlerVenomEntity.class, DataSerializers.ITEMSTACK);

    public AbstractCrawlerVenomEntity(EntityType<? extends AbstractCrawlerVenomEntity> type, double x, double y, double z, double accelX, double accelY, double accelZ, World world) {
        super(type, x, y, z, world);
    }

    //

    @OnlyIn(Dist.CLIENT)
    public AbstractCrawlerVenomEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(ModEntityTypes.CRAWLER_VENOM.get(), x, y, z, worldIn);
    }

    public AbstractCrawlerVenomEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(ModEntityTypes.CRAWLER_VENOM.get(), shooter, worldIn);
    }

    public AbstractCrawlerVenomEntity(EntityType<ProjectileItemEntity> entityEntityType, World world) {
        super(entityEntityType,world);
    }

    public AbstractCrawlerVenomEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
    {
        super(ModEntityTypes.CRAWLER_VENOM.get(), worldIn);
    }

    public void setStack(ItemStack p_213898_1_) {
        if (p_213898_1_.getItem() != Items.SLIME_BALL || p_213898_1_.hasTag()) {
            this.getDataManager().set(STACK, Util.make(p_213898_1_.copy(), (p_213897_0_) -> {
                p_213897_0_.setCount(1);
            }));
        }

    }

    protected ItemStack getStack() {
        return this.getDataManager().get(STACK);
    }

    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        ItemStack itemstack = this.getStack();
        return itemstack.isEmpty() ? new ItemStack(Items.SLIME_BALL) : itemstack;
    }

    protected Item getDefaultItem() {
        return Items.SLIME_BALL;
    }

    protected void registerData() {
        this.getDataManager().register(STACK, ItemStack.EMPTY);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        ItemStack itemstack = this.getStack();
        if (!itemstack.isEmpty()) {
            compound.put("Item", itemstack.write(new CompoundNBT()));
        }

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        ItemStack itemstack = ItemStack.read(compound.getCompound("Item"));
        this.setStack(itemstack);
    }

    protected IParticleData getParticle() {
        return ParticleTypes.DRAGON_BREATH;
    }
}