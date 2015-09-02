package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/9/1.
 */
public class PhoneAddr {
    private int id;
    private String name;
    private String number;
    private String job;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    @Override
    public String toString() {
        return "PhoneAddr{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
