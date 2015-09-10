package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/31.
 */
public class RepairRows {
    private String eid;//工单ID
    private String processId;//实例Id
    private String remark;//备注
    private String reflectForm;//反应形式
    private String informationFrom;//信息来源
    private String reflectPeople;//反映人
    private String reflectUnit;//反应单位
    private String reflectArea;//反应区域
    private String telephone;//联系电话
    private String email;//电子邮件
    private String happenTime;//发生日期
    private String happenAddr;//发生地点
    private String reflectClass;//事件类别
    private String reflectContent;//反映内容
    private String eventLevel;//事件等级
    private String processingDepartment;//处理部门ID
    private String closingTime;//截止时间
    private String personId;//客服人员ID/--------------------注意不是维修人员ID---------需改
    private String processingMan;//处理人
    private String acceptMan;//受理人
    private String surveryMan;//查勘人
    private String surveyRemark;//查勘备注

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReflectForm() {
        return reflectForm;
    }

    public void setReflectForm(String reflectForm) {
        this.reflectForm = reflectForm;
    }

    public String getInformationFrom() {
        return informationFrom;
    }

    public void setInformationFrom(String informationFrom) {
        this.informationFrom = informationFrom;
    }

    public String getReflectPeople() {
        return reflectPeople;
    }

    public void setReflectPeople(String reflectPeople) {
        this.reflectPeople = reflectPeople;
    }

    public String getReflectUnit() {
        return reflectUnit;
    }

    public void setReflectUnit(String reflectUnit) {
        this.reflectUnit = reflectUnit;
    }

    public String getReflectArea() {
        return reflectArea;
    }

    public void setReflectArea(String reflectArea) {
        this.reflectArea = reflectArea;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getHappenAddr() {
        return happenAddr;
    }

    public void setHappenAddr(String happenAddr) {
        this.happenAddr = happenAddr;
    }

    public String getReflectClass() {
        return reflectClass;
    }

    public void setReflectClass(String reflectClass) {
        this.reflectClass = reflectClass;
    }

    public String getReflectContent() {
        return reflectContent;
    }

    public void setReflectContent(String reflectContent) {
        this.reflectContent = reflectContent;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getProcessingDepartment() {
        return processingDepartment;
    }

    public void setProcessingDepartment(String processingDepartment) {
        this.processingDepartment = processingDepartment;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getProcessingMan() {
        return processingMan;
    }

    public void setProcessingMan(String processingMan) {
        this.processingMan = processingMan;
    }

    public String getAcceptMan() {
        return acceptMan;
    }

    public void setAcceptMan(String acceptMan) {
        this.acceptMan = acceptMan;
    }

    public String getSurveryMan() {
        return surveryMan;
    }

    public void setSurveryMan(String surveryMan) {
        this.surveryMan = surveryMan;
    }

    public String getSurveyRemark() {
        return surveyRemark;
    }

    public void setSurveyRemark(String surveyRemark) {
        this.surveyRemark = surveyRemark;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "RepairRows{" +
                "eid='" + eid + '\'' +
                ", processId='" + processId + '\'' +
                ", remark='" + remark + '\'' +
                ", reflectForm='" + reflectForm + '\'' +
                ", informationFrom='" + informationFrom + '\'' +
                ", reflectPeople='" + reflectPeople + '\'' +
                ", reflectUnit='" + reflectUnit + '\'' +
                ", reflectArea='" + reflectArea + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", happenTime='" + happenTime + '\'' +
                ", happenAddr='" + happenAddr + '\'' +
                ", reflectClass='" + reflectClass + '\'' +
                ", reflectContent='" + reflectContent + '\'' +
                ", eventLevel='" + eventLevel + '\'' +
                ", processingDepartment='" + processingDepartment + '\'' +
                ", closingTime='" + closingTime + '\'' +
                ", personId='" + personId + '\'' +
                ", processingMan='" + processingMan + '\'' +
                ", acceptMan='" + acceptMan + '\'' +
                ", surveryMan='" + surveryMan + '\'' +
                ", surveyRemark='" + surveyRemark + '\'' +
                '}';
    }
}
