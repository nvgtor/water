package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;

import org.apache.http.Header;

/**
 * Created by dell on 2015/9/9.
 */
public class RepairApplyForDelayActivity extends Activity{

    //上传参数
    private String eid;//工单Id
    private String personId;//被指派人员Id
    private String delayedFinishTime;//延时申请的维修完成时间
    private String delayReason;//延时申请理由
    private String operation;//操作名称 延时申请


    //控件
    private EditText delayEdit;
    private EditText finishTime;
    private Button btn_submit;
    private ImageView backImg;
    private TextView title;
    private ImageView rImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.apply_for_delay);

        Intent intent = getIntent();
        eid = intent.getStringExtra("eid");
        personId = intent.getStringExtra("personId");

        initView();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delayedFinishTime = finishTime.getText().toString();
                delayReason = delayEdit.getText().toString();
                operation = "延时申请";
                postData();
            }
        });
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        backImg = (ImageView) findViewById(R.id.id_detail_back_img);
        title = (TextView) findViewById(R.id.id_detail_back_title);
        rImg = (ImageView)findViewById(R.id.id_detail_patrol_loc);
        title.setText("延时申请");
        rImg.setVisibility(View.GONE);

        delayEdit = (EditText) findViewById(R.id.id_apply_delay_reason);
        finishTime = (EditText) findViewById(R.id.id_apply_delay_time);
        btn_submit = (Button) findViewById(R.id.id_apply_delay_submit);
    }

    private void postData(){
        String url = "http://172.27.35.1:8080/water-repair/repair/accept";
        RequestParams params = new RequestParams();
        params.put("eid",eid);
        params.put("personId",personId);
        params.put("delayedFinishTime", delayedFinishTime);
        params.put("delayReason", delayReason);
        params.put("operation",operation);
        AsycHttpUtil.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("RepairApplyForDelayActivity success", new String(responseBody));
                Toast.makeText(RepairApplyForDelayActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("RepairApplyForDelayActivity failure", new String(responseBody));
                Toast.makeText(RepairApplyForDelayActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
