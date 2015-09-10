package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.RepairDetail;
import com.water.nvgtor.watermanegement.bean.RepairRows;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by dell on 2015/8/31.
 */
public class RepairDetailActivity extends Activity implements View.OnClickListener{

    RepairRows eventModel = new RepairRows();
    private ImageView img_back;
    private TextView tv_title;

    private String eid;

    private TextView tv_reflectClass;
    private TextView tv_eventLevel;
    private TextView tv_happenAddr;
    private TextView tv_reflectPeople;
    private TextView tv_surveryMan;
    private TextView tv_surveyRemark;
    private TextView tv_reflectContent;
    private TextView tv_happenTime;
    private TextView tv_closingTime;

    private Button btn_accept;
    private Button btn_delay;
    private Button btn_cancel;

    private RelativeLayout reportLayout;
    private LinearLayout repairDetailLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.repair_task_detial);

        Intent intent = getIntent();
        eid = intent.getStringExtra("eid");
        Log.e("RepairDetailActivity eid", eid);

        initView();
        initClick();
        downloadClick();
    }

    private void initView(){
        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);
        tv_title.setText("待办维修任务详情");

        reportLayout = (RelativeLayout) findViewById(R.id.id_repair_detail_frame);
        repairDetailLayout = (LinearLayout) findViewById(R.id.id_repair_detail_ly);

        btn_accept = (Button) findViewById(R.id.id_btn_accept);
        btn_delay = (Button) findViewById(R.id.id_btn_delay);
        btn_cancel = (Button) findViewById(R.id.id_btn_cancel);

        tv_reflectClass = (TextView) findViewById(R.id.row1_text2);
        tv_eventLevel = (TextView) findViewById(R.id.row1_text4);
        tv_happenAddr = (TextView) findViewById(R.id.row2_text2);
        tv_reflectPeople = (TextView) findViewById(R.id.row2_text4);
        tv_surveryMan = (TextView) findViewById(R.id.row3_text2);
        tv_surveyRemark = (TextView) findViewById(R.id.row3_text4);
        tv_reflectContent = (TextView) findViewById(R.id.row4_text2);
        tv_happenTime = (TextView) findViewById(R.id.row5_text2);
        tv_closingTime = (TextView) findViewById(R.id.row6_text2);
    }

    private void setHeadData(RepairRows event){
        tv_reflectClass.setText(event.getReflectClass());
        tv_eventLevel.setText(event.getEventLevel());
        tv_happenAddr.setText(event.getHappenAddr());
        tv_reflectPeople.setText(event.getReflectPeople());
        tv_surveryMan.setText(event.getSurveryMan());
        tv_surveyRemark.setText(event.getSurveyRemark());
        tv_reflectContent.setText(event.getReflectContent());
        tv_happenTime.setText(event.getHappenTime());
        tv_closingTime.setText(event.getClosingTime());
    }
    private void initClick(){
        img_back.setOnClickListener(this);
        btn_accept.setOnClickListener(this);
        btn_delay.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        repairDetailLayout.setOnClickListener(this);
    }

    public void downloadClick() {
        RequestParams params = new RequestParams();
        params.put("nodeName","已派工单");
        params.put("eid",eid);
        String url = "http://172.27.35.1:8080/water-repair/repair/editFlowJson";
        AsycHttpUtil.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                RepairDetail repairDetail = gson.fromJson(response.toString(), RepairDetail.class);
                Log.d("RepairDetailActivity json back", repairDetail.toString());
                eventModel = repairDetail.getEventModel();
                Log.d("eventModel", eventModel.toString());
                setHeadData(eventModel);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(RepairDetailActivity.this, "可能未联网，加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_detail_back_img:
                finish();
                break;
            case R.id.id_repair_detail_ly:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
            case R.id.id_btn_accept:
                //reportLayout.setVisibility(View.VISIBLE);
                Intent acceptIntent = new Intent(RepairDetailActivity.this, IncidentReportActivity.class);
                startActivity(acceptIntent);
                break;
            case R.id.id_btn_delay:
                Intent intentDelay = new Intent(RepairDetailActivity.this, RepairApplyForDelayActivity.class);
                intentDelay.putExtra("eid",eventModel.getEid());
                intentDelay.putExtra("personId",eventModel.getPersonId());
                startActivity(intentDelay);
                break;
            case R.id.id_btn_cancel:
                Intent intentCancel = new Intent(RepairDetailActivity.this, RepairApplyForCancelActivity.class);
                intentCancel.putExtra("eid",eventModel.getEid());
                intentCancel.putExtra("personId",eventModel.getPersonId());
                startActivity(intentCancel);
                break;

        }
    }
}
