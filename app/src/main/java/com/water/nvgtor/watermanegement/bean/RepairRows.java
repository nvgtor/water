package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/31.
 */
public class RepairRows {
    private String eid;//工单ID
    private String processId;//实例Id
    private String reflectContent;//反映内容
    private String happenAddr;//发生地点
    private String happenTime;//发生日期
    private String surveryMan;//查勘人
    private String closingTime;//截止时间
    private String remark;//备注

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getReflectContent() {
        return reflectContent;
    }

    public void setReflectContent(String reflectContent) {
        this.reflectContent = reflectContent;
    }

    public String getHappenAddr() {
        return happenAddr;
    }

    public void setHappenAddr(String happenAddr) {
        this.happenAddr = happenAddr;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getSurveryMan() {
        return surveryMan;
    }

    public void setSurveryMan(String surveryMan) {
        this.surveryMan = surveryMan;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RepairRows{" +
                "eid='" + eid + '\'' +
                ", processId='" + processId + '\'' +
                ", reflectContent='" + reflectContent + '\'' +
                ", happenAddr='" + happenAddr + '\'' +
                ", happenTime='" + happenTime + '\'' +
                ", surveryMan='" + surveryMan + '\'' +
                ", closingTime='" + closingTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
