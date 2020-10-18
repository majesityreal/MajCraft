package com.majesity.majcraft.entities.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WandFireballEntity extends AbstractFireballEntity {
    public int explosionPower = 0;
    public double explosionRadius = 1.5;

    public WandFireballEntity(EntityType<? extends net.minecraft.entity.projectile.FireballEntity> p_i50163_1_, World p_i50163_2_) {
        super(p_i50163_1_, p_i50163_2_);
    }

    @OnlyIn(Dist.CLIENT)
    public WandFireballEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(EntityType.FIREBALL, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    public WandFireballEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntityType.FIREBALL, shooter, accelX, accelY, accelZ, worldIn);
    }

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
                    this.getPosX()+1.5,this.getPosY()+explosionRadius,this.getPosZ()+explosionRadius);
            for(LivingEntity e:this.world.getEntitiesWithinAABB(LivingEntity.class,aabb)) {
                e.attackEntityFrom(DamageSource.MAGIC,5);
                double xDiff = e.getPosX() - this.getPosX();
                double yDiff = e.getPosY() - this.getPosY();
                double zDiff = e.getPosZ() - this.getPosZ();
                if(Math.abs(xDiff)<0.5)
                    if(xDiff>0)
                        xDiff = 0.7;
                    else
                        xDiff = -0.7;
                else
                    if(xDiff>0)
                        xDiff = 0.5;
                    else
                        xDiff = -0.5;

                if(Math.abs(yDiff)<0.5)
                    yDiff = 0.50;
                else
                    yDiff = 0.45;

                if(Math.abs(zDiff)<0.5)
                    if(zDiff>0)
                        zDiff = 0.7;
                    else
                        zDiff = -0.7;
                else
                    if(zDiff>0)
                        zDiff = 0.5;
                    else
                        zDiff = -0.5;

               /* if(xDiff>0)
                    xDiff = explosionRadius-xDiff;
                else
                    xDiff = explosionRadius+xDiff;
                if(yDiff>0)
                    yDiff = explosionRadius-yDiff;
                else
                    yDiff = explosionRadius+yDiff;
                if(zDiff>0)
                    xDiff = explosionRadius-zDiff;
                else
                    zDiff = explosionRadius+zDiff; */
                e.addVelocity(xDiff, yDiff, zDiff);
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
            entity.attackEntityFrom(DamageSource.func_233547_a_(this, entity1), 6.0F);
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
}