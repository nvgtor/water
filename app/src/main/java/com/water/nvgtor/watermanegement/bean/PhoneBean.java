package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/9/9.
 */
public class PhoneBean {
    private String tid;//通讯录ID
    private String department;//部门名字
    private String telephone;//部门电话
    private int isDeleted;//是否删除

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "PhoneBean{" +
                "tid='" + tid + '\'' +
                ", department='" + department + '\'' +
                ", telephone='" + telephone + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
