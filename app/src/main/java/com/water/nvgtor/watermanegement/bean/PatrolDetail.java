package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/27.
 */
public class PatrolDetail {
    private PatrolPlan patrolPlan;
    private PatrolMission patrolMission;

    public PatrolPlan getPatrolPlan() {
        return patrolPlan;
    }

    public void setPatrolPlan(PatrolPlan patrolPlan) {
        this.patrolPlan = patrolPlan;
    }

    public PatrolMission getPatrolMission() {
        return patrolMission;
    }

    public void setPatrolMission(PatrolMission patrolMission) {
        this.patrolMission = patrolMission;
    }

    @Override
    public String toString() {
        return "PatrolDetail{" +
                "patrolPlan=" + patrolPlan +
                ", patrolMission=" + patrolMission +
                '}';
    }
}
