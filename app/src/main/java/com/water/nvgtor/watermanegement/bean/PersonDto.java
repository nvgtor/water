package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/9/8.
 */
public class PersonDto {
    private String remark;
    private String pid;
    private String name;
    private String position;
    private String type;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "remark='" + remark + '\'' +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
