package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/9/2.
 */
public class ResponseData {
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "success='" + success + '\'' +
                '}';
    }
}
