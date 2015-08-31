package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/27.
 * 巡检任务
 */
public class PatrolMission {
    private String id;//巡检任务ID
    private String planName;//巡检计划名称
    private String planID;//巡检计划ID
    private String personName;//巡检人名称
    private String personID;//人员ID
    private String startTime;//开始时间
    private String endTime;//结束时间
    private double timeLimit;//时间期限
    private String remark;//备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PatrolMission{" +
                "id='" + id + '\'' +
                ", planName='" + planName + '\'' +
                ", planID='" + planID + '\'' +
                ", personName='" + personName + '\'' +
                ", personID='" + personID + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", timeLimit=" + timeLimit +
                ", remark='" + remark + '\'' +
                '}';
    }
}
