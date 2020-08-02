package com.majesity.majcraft.entities.BirdEntity;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.BirdNest;
import com.majesity.majcraft.init.ModBlocks;
import com.majesity.majcraft.init.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
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
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BirdEntity extends AnimalEntity implements IFlyingAnimal {

    //BIRD SPECIFIC VARS
    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS);
    private double startingY;
    private double flightChanger;
    public int timeUntilLand;
    public int timeUntilFly;
    public int xDir =0;
    public int zDir =0;
    // the flying vars are here
    private static final DataParameter<Byte> FLYING = EntityDataManager.createKey(BirdEntity.class, DataSerializers.BYTE);
    private BlockPos targetPosition;
    // this is used for closest player detection
    private static final EntityPredicate closestPlayer = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();
    // the flapping animation vars
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;
    // variant is here
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(BirdEntity.class, DataSerializers.VARINT);
    // nest info is here
    private static final DataParameter<BlockPos> HOME_POS = EntityDataManager.createKey(BirdEntity.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Boolean> HAS_NEST = EntityDataManager.createKey(BirdEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> GOING_HOME = EntityDataManager.createKey(BirdEntity.class, DataSerializers.BOOLEAN);

    public BirdEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type,worldIn);
        // allows it to fly
        this.moveController = new FlyingMovementController(this, 10, false);
        if(xDir==0) if(this.rand.nextBoolean()) {
            xDir = -1;
        } else {
            xDir = 1;
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

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(FLYING, (byte)0);
        this.dataManager.register(VARIANT, 0);
        this.dataManager.register(HOME_POS, BlockPos.ZERO);
        this.dataManager.register(HAS_NEST, false);
        this.dataManager.register(GOING_HOME, false);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 5.0D)
                .createMutableAttribute(Attributes.FLYING_SPEED, 0.6F)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2F);
    }


    @Override
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

    // overrides the MobEntity navigator, makes it try to fly. but also ruins the walking mechanic.
    // ADD METHOD FOR SHORT FLY HOPS, LITTLE FLITS
    // flap speed will change how much it flies
   /* @Override
    protected PathNavigator createNavigator(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanSwim(true);
        flyingpathnavigator.setCanEnterDoors(true);
        //flyingpathnavigator.setSpeed(2.0F);
        return flyingpathnavigator;
    } */

    // turns off fall damage?
    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        BlockPos blockpos = this.getPosition();
        BlockPos blockpos1 = blockpos.down();
        // checks if the block under is leaves for nesting mechanics
        if(!getGoingHome()) {
            if (this.getEntityWorld().getBlockState(blockpos1).getBlock().equals(Blocks.OAK_LEAVES) || this.getEntityWorld().getBlockState(blockpos1).getBlock().equals(Blocks.BIRCH_LEAVES)) {
                if (this.rand.nextInt(35) == 0) {
                    this.setIsBirdFlying(false);
                    if (!this.getHasNest()) {
                        this.getEntityWorld().setBlockState(blockpos, ModBlocks.BIRD_NEST.get().getDefaultState());
                        this.setHome(blockpos);
                        this.setHasNest(true);
                    }
                }
                // check if there is a nest
                // if no nest, build nest
                // set home to nest location
            }
            // checks if the bird is far from the nest, if so goes home
            if(!this.getHome().withinDistance(this.getPositionVec(),25.0D)) {
                MajCraft.LOGGER.info("I am going home!");
                setGoingHome(true);
            }
        }
            // this handles the flying mechanics
        if (!this.getIsBirdFlying()) {
                // if the block under it is burning, lava, or water, set it to flying
                if (this.world.getBlockState(blockpos1).isBurning(this.world,blockpos) || this.world.getBlockState(blockpos1).getBlock().equals(Blocks.LAVA) || this.world.getBlockState(blockpos1).getBlock().equals(Blocks.WATER)) {
                    this.setIsBirdFlying(true);
                }
                // if the player is within 4 blocks, set it to flying
                if (this.world.getClosestPlayer(closestPlayer, this) != null) {
                    PlayerEntity player = this.world.getClosestPlayer(closestPlayer, this);
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
            // this checks if the target is valid (an air block, not under y1)
            if (this.targetPosition != null && (!this.world.isAirBlock(this.targetPosition) || this.targetPosition.getY() < 1)) {
                this.targetPosition = null;
            }

            if(this.rand.nextInt(50) == 0) {
                randomDirection();
            }
            // if target checked by up there, or on a random time, or if it gets within 2 blocks of its target, it switches targets
            if (this.targetPosition == null || this.targetPosition.withinDistance(this.getPositionVec(), 2.0D)) {
                setTargetPosition();
               // this.targetPosition = new BlockPos(this.getPosX() + (double)this.rand.nextInt(10) - (double)this.rand.nextInt(10), this.getPosY() + (double)this.rand.nextInt(7) - flightChanger, this.getPosZ() + (double)this.rand.nextInt(10) - (double)this.rand.nextInt(10));
            }

            // if the path is blocked, pick a random location and try to change direction ish. Prevents birds getting stuck a little more
            if(isPathBlocked(targetPosition)) {
                for (int i = 0; i < 5; i++) {
                    if (i > 2) {
                        randomDirection();
                    }
                    if (isPathBlocked(targetPosition)) {
                        setTargetPosition();
                    } else {
                        break;
                    }
                }
            }

            double dx = (double)this.targetPosition.getX() - this.getPosX();
            // this makes it so slowly they wind up going back down if they're not going home
            double dy = this.getGoingHome() ? (double)this.targetPosition.getY() - this.getPosY() : (double)this.targetPosition.getY() - 0.2D - this.getPosY();
            double dz = (double)this.targetPosition.getZ() - this.getPosZ();

            Vector3d vector3d = this.getMotion();
            Vector3d vector3d1 = vector3d.add((Math.signum(dx) * 0.5D - vector3d.x) * (double)0.1F, (Math.signum(dy) * (double)0.7F - vector3d.y) * (double)0.1F, (Math.signum(dz) * 0.5D - vector3d.z) * (double)0.1F);

            this.setMotion(new Vector3d(dx*0.05,dy*0.05,dz*0.05));
            // this.setMotion(vector3d1);

            float f = (float)(MathHelper.atan2(vector3d1.z, vector3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;
            if(!getGoingHome()) {
            if (this.rand.nextInt(50) == 0) {
                if (this.getPosY() - this.startingY > 15) {
                    this.flightChanger = 3.5D;
                } else if (this.getPosY() - this.startingY > 10) {
                    this.flightChanger = 3.0D;
                } else if (this.getPosY() - this.startingY > 5) {
                    this.flightChanger = 2.0D;
                }
                if (this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos1)) {
                    this.flightChanger = 0.0D;
                    this.setIsBirdFlying(false);
                    // !! this needs to be fixed, the leaves tag doesn't work
                    if (this.world.getBlockState(blockpos1).getBlock().equals(Blocks.OAK_LEAVES)||this.world.getBlockState(blockpos1).getBlock().equals(Blocks.BIRCH_LEAVES)) {
                        this.timeUntilFly = this.rand.nextInt(300) + 380;
                        MajCraft.LOGGER.info("I am staying on the leaves!");
                    } else {
                        this.timeUntilFly = this.rand.nextInt(600) + 80;
                    }
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

    @Override
    public void livingTick() {
        super.livingTick();
        /*
        Add something here that checks if the block under is a leaf block. then add nesting thing. that can be updated in the AI to rpevent lag
         */
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

    public int getVariant() {
        return MathHelper.clamp(this.dataManager.get(VARIANT), 0, 3);
    }

    public void setVariant(int variantIn) {
        this.dataManager.set(VARIANT, variantIn);
    }

    public void setHome(BlockPos position) {
        this.dataManager.set(HOME_POS, position);
    }

    private BlockPos getHome() {
        return this.dataManager.get(HOME_POS);
    }

    public void setHasNest(boolean bool) {
        this.dataManager.set(HAS_NEST,bool);
    }

    private boolean getHasNest() {
        return this.dataManager.get(HAS_NEST);
    }

    public void setGoingHome(boolean bool) {
        this.dataManager.set(GOING_HOME,bool);
    }

    private boolean getGoingHome() {
        return this.dataManager.get(GOING_HOME);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putByte("isFlying", this.dataManager.get(FLYING));
        compound.putInt("HomePosX", this.getHome().getX());
        compound.putInt("HomePosY", this.getHome().getY());
        compound.putInt("HomePosZ", this.getHome().getZ());
        compound.putBoolean("hasNest", this.dataManager.get(HAS_NEST));
        compound.putBoolean("goingHome", this.dataManager.get(GOING_HOME));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setVariant(compound.getInt("Variant"));
        this.dataManager.set(FLYING, compound.getByte("isFlying"));
        int i = compound.getInt("HomePosX");
        int j = compound.getInt("HomePosY");
        int k = compound.getInt("HomePosZ");
        this.setHome(new BlockPos(i, j, k));
        this.dataManager.set(HAS_NEST, compound.getBoolean("hasNest"));
        this.dataManager.set(GOING_HOME, compound.getBoolean("goingHome"));
    }

    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setVariant(this.rand.nextInt(4));
        this.setHome(this.getPosition());
        this.setHasNest(false);
        this.setGoingHome(false);
        // sets where they spawn to a bird nest block seems to break the game
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return super.canSpawn(worldIn, spawnReasonIn);
    }

    public static boolean canAnimalSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return (worldIn.getBlockState(pos.down()).isIn(Blocks.OAK_LEAVES) || worldIn.getBlockState(pos.down()).isIn(Blocks.BIRCH_LEAVES));
    }

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

    // check if the flight path is obstructed by blocks
    private boolean isPathBlocked(BlockPos targetPosition) {
        Vector3d vector3d = new Vector3d(this.getPosX(), this.getPosYEye(), this.getPosZ());
        Vector3d vector3d1 = new Vector3d(targetPosition.getX(), targetPosition.getY(), targetPosition.getZ());
        Boolean bool = !(this.world.rayTraceBlocks(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS);
        return bool;
    }

   private void setTargetPosition() {
       if(getGoingHome()) {
           Vector3d difference = findVectorDifference(this.getHome(),this.getPosition());
           this.xDir = (int)Math.signum(difference.getX());
           this.zDir = (int)Math.signum(difference.getZ());
           int yDir = (int)Math.signum(difference.getY());
           if(difference.length()<2.5D) {
               // NEED A GET INSIDE NEST METHOD, RESTING NESTING, where the bird just stands there no moving!
               // could increase the radius and make it so it sets the target to the nest within the radius, then cancel flying right inside the nest
               this.setGoingHome(false);
               MajCraft.LOGGER.info("Set going home to false");
               this.setIsBirdFlying(false);
               targetPosition = this.getHome();
               // if the bird's home is not a nest, remove the home location.
               // !! Change for future when the nest has multiple states for the eggs
               if(!this.world.getBlockState(targetPosition).equals(ModBlocks.BIRD_NEST.get().getDefaultState())) {
                   // IMPORTANT, FIX IT SO ONLY HAS NEST WILL WORK WITH THE RADIUS & HAS HOME
                   this.setHasNest(false);
                   MajCraft.LOGGER.info("I don't have a nest");
               }
           }
           else if(difference.length()<10.0D) {
               if(this.getPosition().getY() < getHome().getY()) {
                   this.targetPosition = new BlockPos(this.getPosX() + (xDir * Math.max(this.rand.nextInt(10),5)), this.getHome().getY()+4, this.getPosZ() + (zDir * Math.max(this.rand.nextInt(10),5)));
               }
               else {
                   this.targetPosition = new BlockPos(this.getPosX() + (xDir * Math.max(this.rand.nextInt(10),5)), this.getHome().getY()+1, this.getPosZ() + (zDir * Math.max(this.rand.nextInt(10),5)));
               }
               // check if the home pos is lower or higher than current position
               // if the blocks above are air, then go above, otherwise go to?
           }
           else {
               // get the position and add a random amount times the direction
               this.targetPosition = new BlockPos(this.getPosX() + (xDir * Math.max(this.rand.nextInt(10),5)), this.getPosY()+ (yDir*Math.max(this.rand.nextInt(3),1)), this.getPosZ() + (zDir * Math.max(this.rand.nextInt(10),5)));
              // old no work, always go in the + direction // targetPosition = new BlockPos(this.getPosX()+(Math.signum(difference.getX())*Math.min(difference.getX()*Math.max(this.rand.nextDouble()*0.38,0.1),10)),this.getPosY()+(Math.signum(difference.getY())*Math.min(difference.getY()*Math.max(this.rand.nextDouble()*0.38,0.1),10)),this.getPosZ()+(Math.signum(difference.getZ())*Math.min(difference.getZ()*Math.max(this.rand.nextDouble()*0.38,0.1),10)));
           }
       }
       else {
           // randomly changes the direction of the flight
           this.targetPosition = new BlockPos(this.getPosX() + (xDir * Math.max(this.rand.nextInt(10),5)), this.getPosY() + (double)this.rand.nextInt(7) - flightChanger, this.getPosZ() + (zDir * Math.max(this.rand.nextInt(10),5)));
       }
   }

   private void randomDirection() {
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

    private Vector3d findVectorDifference(BlockPos pos1, BlockPos pos2) {
        double x = pos1.getX()-pos2.getX();
        double y = pos1.getY()-pos2.getY();
        double z = pos1.getZ()-pos2.getZ();
        return new Vector3d(x,y,z);
    }

}
