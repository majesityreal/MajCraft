package com.majesity.majcraft.entities.BirdEntity;

import com.majesity.majcraft.init.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BirdEntity extends AnimalEntity implements IFlyingAnimal {

    // ADD THE BIRD SPECIFIC VARIABLES HERE
    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS);
    private double startingY;
    private double flightChanger;
    public int timeUntilLand;
    public int timeUntilFly;
    public int xDir =0;
    public int zDir =0;

    // the bat vars are here
    private static final DataParameter<Byte> FLYING = EntityDataManager.createKey(BirdEntity.class, DataSerializers.BYTE);
    private BlockPos targetPosition;
    // this is used for the closest player thing
    private static final EntityPredicate field_213813_c = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();

    // the flapping animation vars
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;


    public BirdEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type,worldIn);
        // allows it to fly
        this.moveController = new FlyingMovementController(this, 10, false);
        if(xDir==0) {
            if(this.rand.nextBoolean()) {
                xDir = -1;
            } else {
                xDir = 1;
            }
        }
        if(zDir==0) {
            if(this.rand.nextBoolean()) {
                zDir = -1;
            } else {
                zDir = 1;
            }
        }
        if(this.timeUntilFly<=0) {
            this.setIsBirdFlying(true);
        }
        if(this.timeUntilFly<=0) {
            this.setIsBirdFlying(false);
            this.timeUntilFly = this.rand.nextInt(600) + 80;
        }
        // INSTANTIATE THE BIRD SPECIFIC VARIABLES HERE
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(FLYING, (byte)0);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .func_233815_a_(Attributes.MAX_HEALTH, 5.0D)
                .func_233815_a_(Attributes.FLYING_SPEED, 0.6F)
                .func_233815_a_(Attributes.MOVEMENT_SPEED, 0.2F);
    }


    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this,1.25D));
        this.goalSelector.addGoal(1, new BreedGoal(this,1.0D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this,1.1D));
        this.goalSelector.addGoal(2, new TemptGoal(this,1.1D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.2D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
    }

    // overrides the MobEntity navigator, makes it try to fly.
    // flap speed will change how much it flies
    protected PathNavigator createNavigator(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanSwim(true);
        flyingpathnavigator.setCanEnterDoors(true);
        //flyingpathnavigator.setSpeed(2.0F);
        return flyingpathnavigator;
    }

    // turns off fall damage?
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        BlockPos blockpos = this.func_233580_cy_();
        BlockPos blockpos1 = blockpos.down();
        if (!this.getIsBirdFlying()) {
                // if the block under it is burning, lava, or water, set it to flying
                if (this.world.getBlockState(blockpos1).isBurning(this.world,blockpos) || this.world.getBlockState(blockpos1).getBlock().equals(Blocks.LAVA) || this.world.getBlockState(blockpos1).getBlock().equals(Blocks.WATER)) {
                    this.setIsBirdFlying(true);
                }
                // if the player is within 4 blocks, set it to flying
                if (this.world.getClosestPlayer(field_213813_c, this) != null) {
                    PlayerEntity player = this.world.getClosestPlayer(field_213813_c, this);
                    if(this.getPosX() - player.getPosX()>0) {
                        this.xDir = 1;
                    } else {
                        this.xDir = -1;
                    }
                    if(this.getPosZ() - player.getPosZ()>0) {
                        this.zDir = 1;
                    } else {
                        this.zDir = -1;
                    }
                    this.world.playSound(player,new BlockPos(this.getPosX(),this.getPosY(),this.getPosZ()),SoundEvents.ENTITY_PARROT_FLY, SoundCategory.AMBIENT,1.0F,1.0F);
                    this.setIsBirdFlying(true);
                   // TURN THIS INTO A BIRD SCARE EVENT? boolean flag = this.isSilent(); if (!flag) { this.world.playEvent((PlayerEntity)null, 1025, blockpos, 0); }
                }
        } else {
            // add something here in the future to check if it is a leaf block!!!! yeeeaa
            if (this.targetPosition != null && (!this.world.isAirBlock(this.targetPosition) || this.targetPosition.getY() < 1)) {
                this.targetPosition = null;
            }
                // if target checked by up there, or on a random time, or if it gets within 2 blocks of its target, it switches targets
            // change this to change the flight pattern
             if(this.rand.nextInt(50) == 0) {
                boolean rando = this.rand.nextBoolean();
                if(rando) {
                    if(this.rand.nextBoolean()) {
                        xDir = -1;
                    } else {
                        xDir = 1;
                    }
                } else {
                    if(this.rand.nextBoolean()) {
                        zDir = -1;
                    } else {
                        zDir = 1;
                    }
                }
            }
            if (this.targetPosition == null || this.targetPosition.withinDistance(this.getPositionVec(), 2.0D)) {
                this.targetPosition = new BlockPos(this.getPosX() + (xDir * Math.max(this.rand.nextInt(10),5)), this.getPosY() + (double)this.rand.nextInt(7) - flightChanger, this.getPosZ() + (zDir * Math.max(this.rand.nextInt(10),5)));
               // this.targetPosition = new BlockPos(this.getPosX() + (double)this.rand.nextInt(10) - (double)this.rand.nextInt(10), this.getPosY() + (double)this.rand.nextInt(7) - flightChanger, this.getPosZ() + (double)this.rand.nextInt(10) - (double)this.rand.nextInt(10));
            }

            double dx = (double)this.targetPosition.getX() - this.getPosX();
            // this makes it so slowly they wind up going back down
            double dy = (double)this.targetPosition.getY() - 0.2D - this.getPosY();
            double dz = (double)this.targetPosition.getZ() - this.getPosZ();

            Vector3d vector3d = this.getMotion();
            Vector3d vector3d1 = vector3d.add((Math.signum(dx) * 0.5D - vector3d.x) * (double)0.1F, (Math.signum(dy) * (double)0.7F - vector3d.y) * (double)0.1F, (Math.signum(dz) * 0.5D - vector3d.z) * (double)0.1F);

            this.setMotion(new Vector3d(dx*0.05,dy*0.05,dz*0.05));
            // this.setMotion(vector3d1);

            float f = (float)(MathHelper.atan2(vector3d1.z, vector3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;
            if (this.rand.nextInt(50) == 0) {
                if(this.getPosY()-this.startingY>15) {
                    this.flightChanger = 3.5D;
                } else if(this.getPosY()-this.startingY>10) {
                    this.flightChanger = 3.0D;
                } else if(this.getPosY()-this.startingY>5) {
                    this.flightChanger = 2.0D;
                }
                if(this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos1)) {
                    this.flightChanger = 0.0D;
                    this.setIsBirdFlying(false);
                    if(this.world.getBlockState(blockpos1).getBlock().getTags().contains("leaves")) {
                        this.timeUntilFly = this.rand.nextInt(300) + 380;
                        this.spawnExplosionParticle();
                    } else {
                        this.timeUntilFly = this.rand.nextInt(600) + 80;
                    }
                }

            }
        }
    }

    public boolean getIsBirdFlying() {
        return (this.dataManager.get(FLYING) & 1) != 0;
    }

    public void setIsBirdFlying(boolean isFlying) {
        byte b0 = this.dataManager.get(FLYING);
        if (isFlying) {
            this.startingY = this.getPosY();
            this.flightChanger = 0.0D;
            this.timeUntilLand = this.rand.nextInt(440) + 160;
            this.timeUntilFly = 0;
            this.dataManager.set(FLYING, (byte)(b0 | 1));
        } else {
            this.timeUntilLand = 0;
            this.dataManager.set(FLYING, (byte)(b0 & -2));
        }

    }

    public void livingTick() {
        super.livingTick();
        if(this.timeUntilFly <= 0) {
            if (--this.timeUntilLand <= 0) {
                this.flightChanger = 3.5D;
            }
        } else {
            if (--this.timeUntilFly <= 1) {
                this.setIsBirdFlying(true);
            }
        }
        // this does the flap animation
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
        if (!this.onGround && this.wingRotDelta < 1.0F) {
            this.wingRotDelta = 1.0F;
        }
        this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);
        this.wingRotation += this.wingRotDelta * 2.0F;

    }

    /*
    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return super.canSpawn(worldIn, spawnReasonIn);
    }

    public static boolean canAnimalSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return (worldIn.getBlockState(pos.down()).isIn(Blocks.OAK_LEAVES) || worldIn.getBlockState(pos.down()).isIn(Blocks.OAK_LEAVES)) && worldIn.getLightSubtracted(pos, 0) > 8;
    } */

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return ModEntityTypes.BIRD.get().create(this.world);
    }

    @Nullable @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_PARROT_AMBIENT; }

    @Nullable @Override
    protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_PARROT_DEATH; }

    @Nullable @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundEvents.ENTITY_PARROT_HURT; }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) { this.playSound(SoundEvents.ENTITY_PARROT_STEP,0.15F,1.0F); }

}
