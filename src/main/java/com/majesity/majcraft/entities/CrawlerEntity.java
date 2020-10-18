package com.majesity.majcraft.entities;

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
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class CrawlerEntity extends MonsterEntity implements IRangedAttackMob {

    private final RangedBowAttackGoal<CrawlerEntity> aiSpitAttack = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
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
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this,1.0D,true));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public void setCombatTask() {
        if (this.world != null && !this.world.isRemote) {
            this.goalSelector.removeGoal(aiSpitAttack);
            this.goalSelector.removeGoal(aiCollideAttack);
            if (this.getAttackTarget() != null) {
                BlockPos targetPos = this.getAttackTarget().getPosition();
                if (this.getPosition().withinDistance(targetPos,8)) {
                    this.goalSelector.addGoal(1, aiCollideAttack);
                }
                else {
                    this.goalSelector.addGoal(1, aiSpitAttack);
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
            // do cool shit here
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
    // VERY IMPORTANT
    // DO NOT OVERLOOK THIS
    // IM WARNING YOU
    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }
}
