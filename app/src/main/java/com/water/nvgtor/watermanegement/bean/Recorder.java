package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/10.
 */
public class Recorder {
    public float time;
    public String filePath;

    public Recorder(float time, String filePath) {
        this.time = time;
        this.filePath = filePath;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
