package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/3.
 */
public class PatrolTask {
    private String taskName;
    private String executeMan;
    private String startTime;
    private String deadline;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getExecuteMan() {
        return executeMan;
    }

    public void setExecuteMan(String executeMan) {
        this.executeMan = executeMan;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "PatrolTask{" +
                "taskName='" + taskName + '\'' +
                ", executeMan='" + executeMan + '\'' +
                ", startTime='" + startTime + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
