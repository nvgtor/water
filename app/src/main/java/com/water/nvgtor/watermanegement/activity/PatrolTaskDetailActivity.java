package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PatrolTaskDetailListAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolDetail;
import com.water.nvgtor.watermanegement.bean.PatrolPlanPoint;
import com.water.nvgtor.watermanegement.bean.PatrolTaskDetailList;
import com.water.nvgtor.watermanegement.tool.HttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/6.
 */
public class PatrolTaskDetailActivity extends Activity implements UnPatrolLoadListview.ILoadListener{
    ArrayList<PatrolTaskDetailList> patrolList = new ArrayList<PatrolTaskDetailList>();
    ArrayList<PatrolPlanPoint> points = new ArrayList<PatrolPlanPoint>();
    PatrolTaskDetailListAdapter adapter;
    UnPatrolLoadListview loadListview;
    PatrolDetail patrolDetail;
    private String id;
    private String patrolMissionID;

    private Handler handler;

    private ImageView img_back;
    private TextView tv_title;
    private ImageView patrol_loc;

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

    private Button btn_finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patrol_task_detail);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        initView();
        downloadClick();
        //planID.setText(patrolDetails1.getPatrolPlan().getId() + "10");
        initChanged();

        //getData();

    }

    private void initView() {
        img_back = (ImageView)findViewById(R.id.id_detail_back_img);
        tv_title = (TextView)findViewById(R.id.id_detail_back_title);
        patrol_loc = (ImageView) findViewById(R.id.id_detail_patrol_loc);
        //detail head
        tv_planType = (TextView) findViewById(R.id.id_patrol_type_in);
        tv_patrolName = (TextView) findViewById(R.id.id_patrol_name_in);
        tv_dispatchMan = (TextView) findViewById(R.id.id_dispatcher_name_in);
        tv_dispatchTime = (TextView) findViewById(R.id.id_dispatch_time_in);
        tv_deadline = (TextView) findViewById(R.id.id_time_limit_in);
        tv_manName = (TextView) findViewById(R.id.id_patroler_name_in);
        tv_startTime = (TextView) findViewById(R.id.id_start_time_in);
        tv_endTime = (TextView) findViewById(R.id.id_end_time_in);
        tv_frequency = (TextView) findViewById(R.id.id_frequency_in);
        tv_areaAddr = (TextView) findViewById(R.id.id_area_address_in);
        tv_remarker = (TextView)findViewById(R.id.id_remark_in);

        btn_finish = (Button) findViewById(R.id.id_detail_task_finish);
    }

    private void initData(PatrolDetail patrol){
        tv_planType.setText(patrol.getPatrolPlan().getPlanType());
        tv_patrolName.setText(patrol.getPatrolPlan().getPidName());
        tv_dispatchMan.setText(patrol.getPatrolPlan().getDispatchingPerson());
        tv_dispatchTime.setText(patrol.getPatrolPlan().getDispatchTime());
        tv_deadline.setText(patrol.getPatrolPlan().getTimeLimit()+"");
        tv_manName.setText(patrol.getPatrolMission().getPersonName());
        tv_startTime.setText(patrol.getPatrolPlan().getStartTime());
        tv_endTime.setText(patrol.getPatrolPlan().getEndTime());
        tv_frequency.setText(patrol.getPatrolPlan().getInspectionFrequency());
        tv_areaAddr.setText(patrol.getPatrolPlan().getAreaPatrolPointDeviceList().get(0).getAddress());
        tv_remarker.setText(patrol.getPatrolMission().getRemark());
    }

     private void initChanged(){
         tv_title.setText("待办巡检详情");
         patrol_loc.setImageResource(R.drawable.icon_activity_lbs);
         img_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
         patrol_loc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(PatrolTaskDetailActivity.this, "you clicked loc", Toast.LENGTH_SHORT).show();
             }
         });
     }

    private void getData(){
        for(int i = 0; i < 10; i++){
            PatrolTaskDetailList entity = new PatrolTaskDetailList();
            entity.setPatrolPointID("AK0183");
            entity.setPatrolPointName("A巡检点");
            entity.setPatrolPointAddress("备注");
            entity.setDeviceNum(4);
            patrolList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            PatrolTaskDetailList entity = new PatrolTaskDetailList();
            entity.setPatrolPointID("B1111111");
            entity.setPatrolPointName("B巡检点");
            entity.setPatrolPointAddress("备注");
            entity.setDeviceNum(3);
            patrolList.add(entity);
        }
    }

    private void showListView(ArrayList<PatrolPlanPoint> patrolList){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_detail_list);
            loadListview.setInterface(this);
            adapter = new PatrolTaskDetailListAdapter(this, patrolList);
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
                case R.id.id_detail_btn:
                    Intent intent = new Intent(PatrolTaskDetailActivity.this, PatrolDetailReportActivity.class);
                    intent.putExtra("position",msg.arg1);
                    Log.e("msg.arg1",msg.arg1+"");
                    intent.putExtra("patrolMissionID",patrolDetail.getPatrolMission().getId());
                    intent.putExtra("pointID",patrolDetail.getPatrolPlan().getAreaPatrolPointDeviceList()
                                    .get(0).getPatrolPointDeviceList().get(msg.arg1).getLpid());
                    intent.putExtra("deviceID",patrolDetail.getPatrolPlan().getAreaPatrolPointDeviceList()
                            .get(0).getPatrolPointDeviceList().get(msg.arg1).getDeviceList().get(0).getDid());
                    startActivity(intent);
                    Toast.makeText(PatrolTaskDetailActivity.this, "你点击了开始" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };

    public void downloadClick() {
        RequestParams params = new RequestParams();
        params.put("id",id);
        String url = "http://172.17.192.1:8080/water-patrol/patrol/patrolMission/editJson";
        HttpUtil.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Gson gson = new Gson();
                patrolDetail = gson.fromJson(response.toString(), PatrolDetail.class);
                Log.d("patrol detail", patrolDetail.toString());
                patrolMissionID = patrolDetail.getPatrolMission().getId();
                Log.e("patrolMissionID", patrolMissionID);
                points = (ArrayList<PatrolPlanPoint>) patrolDetail.getPatrolPlan().getAreaPatrolPointDeviceList().get(0).getPatrolPointDeviceList();
                Log.e("patrol point", points.toString());
                initData(patrolDetail);
                showListView(points);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(PatrolTaskDetailActivity.this, "可能未联网，加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
