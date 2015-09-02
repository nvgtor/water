package com.water.nvgtor.watermanegement.bean;

import java.util.List;

/**
 * Created by dell on 2015/8/27.
 * 巡检计划
 */
public class PatrolPlan {
    private String id;//巡检计划ID
    private String planType;//巡检计划类型
    private String pidName;//巡检计划名称
    private String inspectionFrequency;//巡检频率
    private double timeLimit;//时间期限
    private String startTime;//巡检计划开始时间
    private String endTime;//巡检计划结束时间
    private String dispatchingPerson;//指派人
    private String dispatchTime;//指派时间
    private String remark;//备注
    //巡检计划下的区域
    private List<PatrolPlanArea> areaPatrolPointDeviceList;
    private List<PatrolPlanPoint> patrolPointDeviceList;//巡检计划下的巡检点
    private List<PatrolPlanDevice> deviceInformationList;//巡检计划下面的设备

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName;
    }

    public String getInspectionFrequency() {
        return inspectionFrequency;
    }

    public void setInspectionFrequency(String inspectionFrequency) {
        this.inspectionFrequency = inspectionFrequency;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
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

    public List<PatrolPlanArea> getAreaPatrolPointDeviceList() {
        return areaPatrolPointDeviceList;
    }

    public void setAreaPatrolPointDeviceList(List<PatrolPlanArea> areaPatrolPointDeviceList) {
        this.areaPatrolPointDeviceList = areaPatrolPointDeviceList;
    }

    public List<PatrolPlanPoint> getPatrolPointDeviceList() {
        return patrolPointDeviceList;
    }

    public void setPatrolPointDeviceList(List<PatrolPlanPoint> patrolPointDeviceList) {
        this.patrolPointDeviceList = patrolPointDeviceList;
    }

    public List<PatrolPlanDevice> getDeviceInformationList() {
        return deviceInformationList;
    }

    public void setDeviceInformationList(List<PatrolPlanDevice> deviceInformationList) {
        this.deviceInformationList = deviceInformationList;
    }

    @Override
    public String toString() {
        return "PatrolPlan{" +
                "id='" + id + '\'' +
                ", planType='" + planType + '\'' +
                ", pidName='" + pidName + '\'' +
                ", inspectionFrequency='" + inspectionFrequency + '\'' +
                ", timeLimit=" + timeLimit +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", dispatchingPerson='" + dispatchingPerson + '\'' +
                ", dispatchTime='" + dispatchTime + '\'' +
                ", remark='" + remark + '\'' +
                ", areaPatrolPointDeviceList=" + areaPatrolPointDeviceList +
                ", patrolPointDeviceList=" + patrolPointDeviceList +
                ", deviceInformationList=" + deviceInformationList +
                '}';
    }
}
