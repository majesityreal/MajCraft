package com.majesity.majcraft.entities.projectile;

import com.majesity.majcraft.init.ModEntityTypes;
import com.sun.javafx.geom.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
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
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.commons.lang3.RandomUtils;

public class CrawlerVenomEntity extends AbstractCrawlerVenomEntity implements IRendersAsItem {
    public int explosionPower = 0;
    public double explosionRadius = 2.0;

   /* public CrawlerVenomEntity(EntityType<CrawlerVenomEntity> type, World worldIn) {
        super(type, worldIn);
    }*/

    public CrawlerVenomEntity(EntityType<? extends ProjectileItemEntity> entity, World world) {
        super((EntityType<ProjectileItemEntity>) entity, world);
        this.setStack(Items.SLIME_BALL.getDefaultInstance());
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

    public CrawlerVenomEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
    {
        super(packet, worldIn);
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }



    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        // this is for spawning the particles on the impact
        if (this.world.isRemote) {
            Vector3d vec3d = this.getMotion();
            double d0 = this.getPosX() + vec3d.x;
            double d1 = this.getPosY() + vec3d.y;
            double d2 = this.getPosZ() + vec3d.z;
            world.addParticle(ParticleTypes.ITEM_SLIME,d0,this.getPosY()+0.5,d2,vec3d.x,1,vec3d.z);
            world.addParticle(ParticleTypes.ITEM_SLIME,d0,this.getPosY()+0.5,d2,0,1,0);
            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX(),this.getPosY()+0.5,this.getPosZ(),0,1,0);

            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX()+0.5,this.getPosY()+0.5,this.getPosZ()+0.5,1,0.5,1);
            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX()+0.5,this.getPosY()+0.5,this.getPosZ()-0.5,1,0.5,-1);
            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX()-0.5,this.getPosY()+0.5,this.getPosZ()+0.5,-1,0.5,1);
            world.addParticle(ParticleTypes.ITEM_SLIME,this.getPosX()-0.5,this.getPosY()+0.5,this.getPosZ()-0.5,-1,0.5,-1);

            this.world.playSound(this.getPosX(),this.getPosY(),this.getPosZ(),SoundEvents.ENTITY_SLIME_SQUISH,SoundCategory.HOSTILE,1.0F,1.0F,true);
        }
        if (!this.world.isRemote) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.func_234616_v_());
            this.world.playSound(this.getPosX(),this.getPosY(),this.getPosZ(), SoundEvents.ENTITY_SPIDER_AMBIENT, SoundCategory.HOSTILE,1.0F,1.0F,true);
            ServerWorld world = (ServerWorld) this.world;
            //World world = this.getEntityWorld();
            // this.getServer().getWorld();

            // this.world.createExplosion((Entity)this, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionPower, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
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

    public void tick() {
            super.tick();
            if(this.world.isRemote) {
                Vector3d vec3d = this.getMotion();
                double d0 = this.getPosX() + vec3d.x;
                double d1 = this.getPosY() + vec3d.y;
                double d2 = this.getPosZ() + vec3d.z;
                if (RandomUtils.nextBoolean()) {
                    this.world.addParticle(ParticleTypes.ITEM_SLIME, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
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

    @Override
    protected Item getDefaultItem() {
        return super.getDefaultItem();
    }
}
