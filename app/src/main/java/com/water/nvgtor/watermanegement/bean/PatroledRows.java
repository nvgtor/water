package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/28.
 */
public class PatroledRows{
    private String id;
    private String planID;
    private String planName;
    private String personName;
    private String startTime;
    private double timeLimit;
    private String dispatchingPerson;
    private int finalStatus;//0待审核1审核通过

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

    public int getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(int finalStatus) {
        this.finalStatus = finalStatus;
    }

    @Override
    public String toString() {
        return "PatroledRows{" +
                "id='" + id + '\'' +
                ", planID='" + planID + '\'' +
                ", planName='" + planName + '\'' +
                ", personName='" + personName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", timeLimit=" + timeLimit +
                ", dispatchingPerson='" + dispatchingPerson + '\'' +
                ", finalStatus=" + finalStatus +
                '}';
    }
}
