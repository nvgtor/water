package com.water.nvgtor.watermanegement.bean;

import java.util.List;

/**
 * Created by dell on 2015/8/27.
 * 【区域下面的设备】
 */
public class PatrolPlanArea {
    private String rid;//区域ID
    private String no;//区域轮廓编号
    private String areaName;//区域名称
    private String address;//区域地址
    private String contour;//区域轮廓坐标
    private String remark;//备注
    private String planID;//巡检计划ID
    private List<PatrolPlanPoint> patrolPointDeviceList;//区域下的巡检点
    private List<PatrolPlanDevice> deviceInformationList;//区域下面的设备

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContour() {
        return contour;
    }

    public void setContour(String contour) {
        this.contour = contour;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
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
        return "PatrolPlanArea{" +
                "rid='" + rid + '\'' +
                ", no='" + no + '\'' +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                ", contour='" + contour + '\'' +
                ", remark='" + remark + '\'' +
                ", planID='" + planID + '\'' +
                ", patrolPointDeviceList=" + patrolPointDeviceList +
                ", deviceInformationList=" + deviceInformationList +
                '}';
    }
}
