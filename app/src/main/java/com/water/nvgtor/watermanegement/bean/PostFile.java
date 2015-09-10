package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/9/6.
 */
public class PostFile {
    String url;

    public PostFile(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PostFile{" +
                "url='" + url + '\'' +
                '}';
    }
}
