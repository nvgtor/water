package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/21.
 */
public class PatrolRows {

    private String id;
    private String planID;
    private String planName;
    private String personName;
    private String startTime;
    private double timeLimit;
    private String dispatchingPerson;
    private String dispatchTime;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getDispatchingPerson() {
        return dispatchingPerson;
    }

    public void setDispatchingPerson(String dispatchingPerson) {
        this.dispatchingPerson = dispatchingPerson;
    }
    public String getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(String dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PatrolRows{" +
                "id='" + id + '\'' +
                ", planID='" + planID + '\'' +
                ", planName='" + planName + '\'' +
                ", personName='" + personName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", timeLimit=" + timeLimit +
                ", dispatchingPerson='" + dispatchingPerson + '\'' +
                ", dispatchTime='" + dispatchTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
