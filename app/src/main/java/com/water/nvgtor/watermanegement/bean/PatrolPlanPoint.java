package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/27.
 * 【区域下的巡检点】
 */

public class PatrolPlanPoint {
    private String Lpid;//巡检点ID
    private String name;//巡检点名称
    private String deviceNum;//设备数量
    private String remark;//备注
    private String no;//巡检点标号
    private String areaID;//巡检区域ID
    private String planID;//巡检计划ID

    public String getLpid() {
        return Lpid;
    }

    public void setLpid(String lpid) {
        Lpid = lpid;
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

    @Override
    public String toString() {
        return "PatrolPlanPoint{" +
                "Lpid='" + Lpid + '\'' +
                ", name='" + name + '\'' +
                ", deviceNum=" + deviceNum +
                ", remark='" + remark + '\'' +
                ", no='" + no + '\'' +
                ", areaID='" + areaID + '\'' +
                ", planID='" + planID + '\'' +
                '}';
    }
}
