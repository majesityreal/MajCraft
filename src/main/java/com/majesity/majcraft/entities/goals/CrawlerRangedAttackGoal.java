package com.majesity.majcraft.entities.goals;

import com.majesity.majcraft.MajCraft;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;

public class CrawlerRangedAttackGoal<T extends MonsterEntity & IRangedAttackMob> extends RangedBowAttackGoal {

    private final T entity;
    private final double minDistance;
    private int attackCooldown;
    private int attackTime = -1;

    public CrawlerRangedAttackGoal(T mob, double moveSpeedAmpIn, int attackCooldownIn, float maxAttackDistanceIn, double minDistance) {
        super(mob, moveSpeedAmpIn, attackCooldownIn, maxAttackDistanceIn);
        this.entity = mob;
        this.minDistance = minDistance;
        this.attackCooldown = attackCooldownIn;
    }

    @Override
    public boolean shouldExecute() {
        if (this.entity.getAttackTarget() == null) {
            return false;
        }
        LivingEntity livingEntity = this.entity.getAttackTarget();
        if (!livingEntity.isAlive()) {
            return false;
        }
        if (!this.entity.getPosition().withinDistance(livingEntity.getPosition(),this.minDistance)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute();
    }

    @Override
    public void tick() {
        if (this.entity.getAttackTarget() != null) {
            this.entity.lookAt(EntityAnchorArgument.Type.EYES, this.entity.getAttackTarget().getPositionVec());
            if (--attackTime < 0) {
                this.entity.attackEntityWithRangedAttack(this.entity.getAttackTarget(),0.0f);
                this.attackTime = attackCooldown;
            }
        }
    }
}
