package com.water.nvgtor.watermanegement.bean;

import java.util.List;

/**
 * Created by dell on 2015/8/27.
 * 巡检计划-->巡检区域-->巡检点
 */

public class PatrolPlanPoint {
    private String lpid;//巡检点ID
    private String name;//巡检点名称
    private String deviceNum;//设备数量
    private String remark;//备注
    private String no;//巡检点标号
    private String areaID;//巡检区域ID
    private String planID;//巡检计划ID
    private String lng;//巡检点坐标-经度
    private String lat;//巡检点坐标-纬度
    private List<PatrolPointDevice> deviceList;

    public String getLpid() {
        return lpid;
    }

    public void setLpid(String lpid) {
        lpid = lpid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public List<PatrolPointDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<PatrolPointDevice> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public String toString() {
        return "PatrolPlanPoint{" +
                "Lpid='" + lpid + '\'' +
                ", name='" + name + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                ", remark='" + remark + '\'' +
                ", no='" + no + '\'' +
                ", areaID='" + areaID + '\'' +
                ", planID='" + planID + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", deviceList=" + deviceList +
                '}';
    }
}
