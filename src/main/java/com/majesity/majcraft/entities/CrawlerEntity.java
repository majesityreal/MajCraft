package com.majesity.majcraft.entities;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.entities.projectile.CrawlerVenomEntity;
import com.majesity.majcraft.init.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class CrawlerEntity extends MonsterEntity implements IRangedAttackMob {

    private final RangedBowAttackGoal<CrawlerEntity> aiSpitAttack = new RangedBowAttackGoal<>(this, 1.0D, 20, 30.0F);
    private final MeleeAttackGoal aiCollideAttack = new MeleeAttackGoal(this, 1.0D, true);

        public CrawlerEntity(EntityType<? extends CrawlerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 12.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.30D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D);
    }

    // this is part of the AI of the entity
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this,1.0D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public void setCombatTask() {
            // the this world is remote method may not be working because of the server
        if (this.world != null && this.world.isRemote) {
            MajCraft.LOGGER.info("Combat task call");
            this.goalSelector.removeGoal(this.aiSpitAttack);
            this.goalSelector.removeGoal(this.aiCollideAttack);
            findTarget(30.0);
            if (this.getAttackTarget() != null) {
                BlockPos targetPos = this.getAttackTarget().getPosition();
                if (this.getPosition().withinDistance(targetPos,8)) {
                    this.goalSelector.addGoal(1, aiCollideAttack);
                    MajCraft.LOGGER.info("Physical Attacking");
                }
                else {
                    this.goalSelector.addGoal(1, aiSpitAttack);
                    MajCraft.LOGGER.info("Ranged attacking");
                }
            }
        }
    }

    private void findTarget(double distance) {
        double minDistance;
        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox().expand(distance, distance, distance));
        if (entities.size() > 0 && entities != null) {
            minDistance = entities.get(0).getPosition().distanceSq(this.getPosition());
            for (Entity e : entities) {
                if(e instanceof LivingEntity && e.getPosition().distanceSq(this.getPosition()) <= minDistance) {
                    minDistance = e.getPosition().distanceSq(this.getPosition());
                    this.setAttackTarget((LivingEntity)e);

                }
            }
        }

    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 1+this.world.rand.nextInt(4);
    }

    @Nullable @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_PIG_AMBIENT; }

    @Nullable @Override
    protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_PIG_DEATH; }

    @Nullable @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundEvents.ENTITY_PIG_HURT; }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) { this.playSound(SoundEvents.ENTITY_PIG_STEP,0.15F,1.0F); }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public void livingTick() {
        if(this.world.isRemote) {
            setCombatTask();
        }
        super.livingTick();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
            super.handleStatusUpdate(id);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    // THIS IS THE RANGED ATTACK INTERFACE THAT I NEED TO IMPLEMENT AND USE
    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        CrawlerVenomEntity venom = new CrawlerVenomEntity(ModEntityTypes.CRAWLER_VENOM.get(),this.world);
        double d0 = target.getPosX() - this.getPosX();
        double d1 = target.getPosYHeight(0.3333333333333333D) - venom.getPosY();
        double d2 = target.getPosZ() - this.getPosZ();
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        venom.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.world.addEntity(venom);
    }
}
