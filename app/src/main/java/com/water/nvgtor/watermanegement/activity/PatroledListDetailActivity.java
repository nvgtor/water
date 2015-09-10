package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PatroledDetailListAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolDetail;
import com.water.nvgtor.watermanegement.bean.PatrolPlanPoint;
import com.water.nvgtor.watermanegement.bean.PatrolTaskDetailList;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/11.
 */
public class PatroledListDetailActivity extends Activity implements UnPatrolLoadListview.ILoadListener {
    ArrayList<PatrolTaskDetailList> patrolList = new ArrayList<PatrolTaskDetailList>();
    ArrayList<PatrolPlanPoint> points = new ArrayList<PatrolPlanPoint>();
    PatroledDetailListAdapter adapter;
    UnPatrolLoadListview loadListview;

    PatrolDetail patrolDetail;

    private Handler handler;
    private ImageView img_back;
    private TextView tv_title;
    private ImageView patrol_loc;

    private String id;

    //detail head view
    private TextView tv_planType;
    private TextView tv_patrolName;
    private TextView tv_dispatchMan;
    private TextView tv_manName;
    private TextView tv_dispatchTime;
    private TextView tv_frequency;
    private TextView tv_areaAddr;
    private TextView tv_deadline;
    private TextView tv_startTime;
    private TextView tv_endTime;
    private TextView tv_remarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patroled_list_detail);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("id in activity", id);
        initView();
        initChanged();
        downloadClick();
        //getData();
    }

    private void initView() {
        img_back = (ImageView)findViewById(R.id.id_detail_back_img);
        tv_title = (TextView)findViewById(R.id.id_detail_back_title);
        patrol_loc = (ImageView) findViewById(R.id.id_detail_patrol_loc);
        //detail head
        tv_planType = (TextView) findViewById(R.id.id_patroled_type_in);
        tv_patrolName = (TextView) findViewById(R.id.id_patroled_name_in);
        tv_dispatchMan = (TextView) findViewById(R.id.id_patroled_dispatcher_name_in);
        tv_dispatchTime = (TextView) findViewById(R.id.id_patroled_dispatch_time_in);
        tv_deadline = (TextView) findViewById(R.id.id_patroled_time_limit_in);
        tv_manName = (TextView) findViewById(R.id.id_patroleder_name_in);
        tv_startTime = (TextView) findViewById(R.id.id_patroled_start_time_in);
        tv_endTime = (TextView) findViewById(R.id.id_patroled_end_time_in);
        tv_frequency = (TextView) findViewById(R.id.id_patroled_frequency_in);
        tv_areaAddr = (TextView) findViewById(R.id.id_area_address_in);
        tv_remarker = (TextView)findViewById(R.id.id_patroled_remark_in);
    }

    private void initData(PatrolDetail patrolDetail){
        tv_planType.setText(patrolDetail.getPatrolPlan().getPlanType());
        tv_patrolName.setText(patrolDetail.getPatrolPlan().getPidName());
        tv_dispatchMan.setText(patrolDetail.getPatrolPlan().getDispatchingPerson());
        tv_dispatchTime.setText(patrolDetail.getPatrolPlan().getDispatchTime());
        tv_deadline.setText(patrolDetail.getPatrolPlan().getTimeLimit()+"");
        tv_manName.setText(patrolDetail.getPatrolMission().getPersonName());
        tv_startTime.setText(patrolDetail.getPatrolPlan().getStartTime());
        tv_endTime.setText(patrolDetail.getPatrolPlan().getEndTime());
        tv_frequency.setText(patrolDetail.getPatrolPlan().getInspectionFrequency());
        tv_areaAddr.setText(patrolDetail.getPatrolPlan().getAreaPatrolPointDeviceList().get(0).getAddress());
        tv_remarker.setText(patrolDetail.getPatrolMission().getRemark());
    }
    private void initChanged(){
        tv_title.setText("已办巡检详情");
        patrol_loc.setImageResource(R.drawable.icon_activity_lbs);
        patrol_loc.setMaxWidth(30);
        patrol_loc.setMaxHeight(30);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        patrol_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatroledListDetailActivity.this, "you clicked loc", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(){
        for(int i = 0; i < 5; i++){
            PatrolTaskDetailList entity = new PatrolTaskDetailList();
            entity.setPatrolPointID("AK0183");
            entity.setPatrolPointName("A巡检点");
            entity.setPatrolPointAddress("高新七路距海油大道20m");
            entity.setDeviceNum(4);
            patrolList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            PatrolTaskDetailList entity = new PatrolTaskDetailList();
            entity.setPatrolPointID("B1111111");
            entity.setPatrolPointName("B巡检点");
            entity.setPatrolPointAddress("塘沽外滩公园解放路站下水道");
            entity.setDeviceNum(3);
            patrolList.add(entity);
        }
    }

    private void showListView(ArrayList<PatrolPlanPoint> patrolList){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_patroled_item_list);
            loadListview.setInterface(this);
            adapter = new PatroledDetailListAdapter(this, patrolList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(patrolList);
        }
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取更多数据
                getLoadData();
                //更新listview显示
                showListView(points);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

    Handler handler_h = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what){//如果item项目里有多个按钮触发，可以在这里区分
                case R.id.id_patroled_item_btn_1:
                    Intent intent = new Intent(PatroledListDetailActivity.this, PatroledPointDetailActivity.class);
                    startActivity(intent);
                    Toast.makeText(PatroledListDetailActivity.this, "你点击了查看" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };

    public void downloadClick() {
        RequestParams params = new RequestParams();
        params.put("id",id);
        Log.e("params", params.toString());
        String url = "http://172.27.35.1:8080/water-patrol/patrol/examine/editJson";
        AsycHttpUtil.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Gson gson = new Gson();
                patrolDetail = gson.fromJson(response.toString(), PatrolDetail.class);
                Log.e("patroled detail", patrolDetail.toString());
                points = (ArrayList<PatrolPlanPoint>) patrolDetail.getPatrolPlan().getAreaPatrolPointDeviceList().get(0).getPatrolPointDeviceList();
                Log.e("patroled point", points.toString());
                initData(patrolDetail);
                showListView(points);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(PatroledListDetailActivity.this, "可能未联网，加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

