package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/9/1.
 * 巡检计划-->区域-->巡检点-->设备
 */
public class PatrolPointDevice {
    private String remark;//备注
    private String did;//设备编号
    private String deviceName;//设备名称
    private String devAbbreviation;//简称
    private String deviceStatus;//状态
    private String deviceType;//类型
    private String lng;//经度
    private String lat;//纬度
    private String address;//地址
    private String maintenanceProject;
    private String patrolPointID;
    private String areaID;
    private String planID;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
        return "PatrolPointDevice{" +
                "remark='" + remark + '\'' +
                ", did='" + did + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", devAbbreviation='" + devAbbreviation + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", address='" + address + '\'' +
                ", maintenanceProject='" + maintenanceProject + '\'' +
                ", patrolPointID='" + patrolPointID + '\'' +
                ", areaID='" + areaID + '\'' +
                ", planID='" + planID + '\'' +
                '}';
    }
}
