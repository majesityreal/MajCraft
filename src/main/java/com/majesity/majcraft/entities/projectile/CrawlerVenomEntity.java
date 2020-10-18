package com.majesity.majcraft.entities.projectile;

import com.majesity.majcraft.init.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrawlerVenomEntity extends AbstractCrawlerVenomEntity implements IRendersAsItem {
    public int explosionPower = 0;
    public double explosionRadius = 2.0;

   /* public CrawlerVenomEntity(EntityType<CrawlerVenomEntity> type, World worldIn) {
        super(type, worldIn);
    }*/

    public CrawlerVenomEntity(EntityType<? extends DamagingProjectileEntity> entity, World world) {
        super((EntityType<DamagingProjectileEntity>) entity, world);
    }

    @OnlyIn(Dist.CLIENT)
    public CrawlerVenomEntity(EntityType<? extends CrawlerVenomEntity> type, World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(type, x, y, z, accelX, accelY, accelZ, worldIn);
        this.setVelocity(accelX, accelY, accelZ);
    }

    //

    @OnlyIn(Dist.CLIENT)
    public CrawlerVenomEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(ModEntityTypes.CRAWLER_VENOM.get(), x, y, z, accelX, accelY, accelZ, worldIn);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.func_234616_v_());
            this.world.playSound(this.getPosX(),this.getPosY(),this.getPosZ(), SoundEvents.ENTITY_SPIDER_AMBIENT, SoundCategory.HOSTILE,1.0F,1.0F,true);
            World world = this.getEntityWorld();
            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX(),this.getPosY()+0.5,this.getPosZ(),0,1,0);
            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX(),this.getPosY()+1.5,this.getPosZ(),0,1,0);
            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX(),this.getPosY()+1.0,this.getPosZ(),0,1,0);

            // this.world.createExplosion((Entity)null, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionPower, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            Vector3d victor = result.getHitVec();
            AxisAlignedBB aabb = new AxisAlignedBB(this.getPosX()-explosionRadius,this.getPosY()-explosionRadius,this.getPosZ()-explosionRadius,
                    this.getPosX()+explosionRadius,this.getPosY()+explosionRadius,this.getPosZ()+explosionRadius);
            for(LivingEntity e:this.world.getEntitiesWithinAABB(LivingEntity.class,aabb)) {
                e.attackEntityFrom(DamageSource.MAGIC,5);
                e.addPotionEffect(new EffectInstance(Effects.POISON,80,1));
            }
            this.remove();
        }

    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        if (!this.world.isRemote) {
            Entity entity = p_213868_1_.getEntity();
            Entity entity1 = this.func_234616_v_();
            entity.attackEntityFrom(DamageSource.MAGIC, 6.0F);
            if (entity1 instanceof LivingEntity) {
                this.applyEnchantments((LivingEntity)entity1, entity);
            }

        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("ExplosionPower", this.explosionPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("ExplosionPower", 99)) {
            this.explosionPower = compound.getInt("ExplosionPower");
        }

    }

    protected ItemStack getStack() {
        return new ItemStack(Items.SLIME_BALL);
    }

    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        ItemStack itemstack = this.getStack();
        return itemstack.isEmpty() ? new ItemStack(Items.SLIME_BALL) : itemstack;
    }

}
