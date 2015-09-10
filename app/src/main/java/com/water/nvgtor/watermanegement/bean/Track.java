package com.water.nvgtor.watermanegement.bean;

import java.io.Serializable;

/**
 * Created by dell on 2015/9/7.
 */
public class Track implements Serializable {

    private static final long serialVersionUID = -6919461967497580385L;

    private String remark;
    private String createName;
    private String createTime;
    private String updateName;
    private String updateTime;
    private int isDeleted;
    private String id;
    private double lng;
    private double lat;
    private String patrolMissionId;
    private String uploadTime;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getPatrolMissionId() {
        return patrolMissionId;
    }

    public void setPatrolMissionId(String patrolMissionId) {
        this.patrolMissionId = patrolMissionId;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "Track{" +
                "remark='" + remark + '\'' +
                ", createName='" + createName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateName='" + updateName + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDeleted=" + isDeleted +
                ", id='" + id + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", patrolMissionId='" + patrolMissionId + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
