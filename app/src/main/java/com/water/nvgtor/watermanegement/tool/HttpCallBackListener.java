package com.water.nvgtor.watermanegement.tool;

/**
 * Created by dell on 2015/9/6.
 */
public interface HttpCallBackListener {
    void onFinish(String response);

    void onError(Exception e);
}
