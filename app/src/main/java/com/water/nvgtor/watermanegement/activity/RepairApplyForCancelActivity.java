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

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by dell on 2015/9/9.
 */
public class RepairApplyForCancelActivity extends Activity {

    //上传参数
    private String eid;//工单Id
    private String personId;//被指派人员Id
    private int isAccepted;//是否接受
    private String rejectReason;//拒绝原因
    private String acceptRejectTime;//接受/拒绝时间
    private String operation;//操作名称 受理/未受理

    //控件
    private EditText rejectEdit;
    private Button btn_submit;
    private ImageView backImg;
    private TextView title;
    private ImageView rImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.apply_for_cancel);

        Intent intent = getIntent();
        eid = intent.getStringExtra("eid");
        personId = intent.getStringExtra("personId");

        initView();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectReason = rejectEdit.getText().toString();
                isAccepted = 0;
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss", Locale.CHINA);
                String date = sDateFormat.format(new java.util.Date());
                acceptRejectTime = date;
                operation = "未受理";
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
        title.setText("申请消单");
        rImg.setVisibility(View.GONE);
        rejectEdit = (EditText) findViewById(R.id.id_apply_cancel_edit);
        btn_submit = (Button) findViewById(R.id.id_apply_cancel_submit);
    }

    private void postData(){
        String url = "http://172.27.35.1:8080/water-repair/repair/accept";
        RequestParams params = new RequestParams();
        params.put("eid",eid);
        params.put("personId",personId);
        params.put("isAccepted",isAccepted);
        params.put("rejectReason",rejectReason);
        params.put("acceptRejectTime",acceptRejectTime);
        params.put("operation",operation);
        Log.e("RepairApplyForCancelActivity params", params.toString());
        AsycHttpUtil.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("RepairApplyForCancelActivity success", new String(responseBody));
                Toast.makeText(RepairApplyForCancelActivity.this, "消单成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("RepairApplyForCancelActivity failure", new String(responseBody));
                Toast.makeText(RepairApplyForCancelActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
