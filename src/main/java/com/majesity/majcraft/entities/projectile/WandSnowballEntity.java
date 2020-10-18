package com.majesity.majcraft.entities.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WandSnowballEntity extends ProjectileItemEntity {
    public int explosionPower = 0;
    public double explosionRadius = 1.5;

    public WandSnowballEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public WandSnowballEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(type, x, y, z, worldIn);
        this.setVelocity(accelX,accelY,accelZ);
    }

    /*
    public WandSnowballEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntityType.SNOWBALL, shooter, accelX, accelY, accelZ, worldIn);
    } */

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.func_234616_v_());
            this.world.createExplosion((Entity)null, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionPower, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            Vector3d victor = result.getHitVec();
            AxisAlignedBB aabb = new AxisAlignedBB(this.getPosX()-explosionRadius,this.getPosY()-explosionRadius,this.getPosZ()-explosionRadius,
                    this.getPosX()+explosionRadius,this.getPosY()+explosionRadius,this.getPosZ()+explosionRadius);
            for(LivingEntity e:this.world.getEntitiesWithinAABB(LivingEntity.class,aabb)) {
                e.attackEntityFrom(DamageSource.MAGIC,5);
                e.addPotionEffect(new EffectInstance(Effects.SLOWNESS,100,0));
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

    @Override
    public void setItem(ItemStack stack) {
        super.setItem(new ItemStack(Items.SLIME_BALL));
    }

    @Override
    protected Item getDefaultItem() {
        return Items.SLIME_BALL;
    }


}