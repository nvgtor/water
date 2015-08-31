package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/27.
 * 【区域下面的设备】
 */
public class PatrolPlanDevice {
    private String did;//设备ID
    private String deviceName;//设备名称
    private String devAbbreviation;//设备简称
    private String deviceStatus;//设备状态
    private String deviceType;//设备类型
    private String lng;//经度
    private String lat;//纬度
    private String address;//设备详细地址
    private String maintenanceProject;//维护项目
    private String remark;//备注
    private String patrolPointID;//巡检点ID
    private String areaID;//区域ID
    private String planID;//计划ID

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDevAbbreviation() {
        return devAbbreviation;
    }

    public void setDevAbbreviation(String devAbbreviation) {
        this.devAbbreviation = devAbbreviation;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaintenanceProject() {
        return maintenanceProject;
    }

    public void setMaintenanceProject(String maintenanceProject) {
        this.maintenanceProject = maintenanceProject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPatrolPointID() {
        return patrolPointID;
    }

    public void setPatrolPointID(String patrolPointID) {
        this.patrolPointID = patrolPointID;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    @Override
    public String toString() {
        return "PatrolPlanDevice{" +
                "did='" + did + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", devAbbreviation='" + devAbbreviation + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", address='" + address + '\'' +
                ", maintenanceProject='" + maintenanceProject + '\'' +
                ", remark='" + remark + '\'' +
                ", patrolPointID='" + patrolPointID + '\'' +
                ", areaID='" + areaID + '\'' +
                ", planID='" + planID + '\'' +
                '}';
    }
}
