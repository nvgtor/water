package com.water.nvgtor.watermanegement.bean;

import java.util.List;

/**
 * Created by dell on 2015/8/21.
 */
public class PatrolRows {

    private String id;
    private String planID;
    private String planName;
    private String personName;
    private String startTime;
    private String endTime;
    private double timeLimit;
    private String dispatchingPerson;
    private String dispatchTime;
    private String remark;
    private List<Track> trackList;

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

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public String toString() {
        return "PatrolRows{" +
                "id='" + id + '\'' +
                ", planID='" + planID + '\'' +
                ", planName='" + planName + '\'' +
                ", personName='" + personName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", timeLimit=" + timeLimit +
                ", dispatchingPerson='" + dispatchingPerson + '\'' +
                ", dispatchTime='" + dispatchTime + '\'' +
                ", remark='" + remark + '\'' +
                ", trackList=" + trackList +
                '}';
    }
}
