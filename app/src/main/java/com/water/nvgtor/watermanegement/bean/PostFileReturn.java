package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/9/6.
 */
public class PostFileReturn {
    private String message;
    private String fileName;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "PostFileReturn{" +
                "message='" + message + '\'' +
                ", fileName='" + fileName + '\'' +
                ", success=" + success +
                '}';
    }
}
