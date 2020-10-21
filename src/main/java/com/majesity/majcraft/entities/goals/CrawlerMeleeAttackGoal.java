package com.majesity.majcraft.entities.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.math.BlockPos;

public class CrawlerMeleeAttackGoal extends MeleeAttackGoal {

    private double distance;

    public CrawlerMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory, double distance) {
        super(creature, speedIn, useLongMemory);
        this.distance = distance;
    }

    /**
     * Doesn't attack if the player gets farther than 8 blocks
     */
    @Override
    public boolean shouldExecute() {
        boolean superBool = super.shouldExecute();
        if (!superBool)
            return false;
        BlockPos targetPos = super.attacker.getAttackTarget().getPosition();
        return targetPos.withinDistance(super.attacker.getPosition(), this.distance);
    }

    /**
     * Stops attacking if the player gets farther than 8 blocks
     */
    @Override
    public boolean shouldContinueExecuting() {
        boolean superBool = super.shouldContinueExecuting();
        if (!superBool)
            return false;
        BlockPos targetPos = super.attacker.getAttackTarget().getPosition();
        return superBool && targetPos.withinDistance(super.attacker.getPosition(), this.distance);
    }
}
