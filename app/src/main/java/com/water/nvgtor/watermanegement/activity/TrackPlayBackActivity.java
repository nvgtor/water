package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.TrackPlayBackAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolJson;
import com.water.nvgtor.watermanegement.bean.PatrolRows;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 2015/9/7.
 */
public class TrackPlayBackActivity extends Activity implements UnPatrolLoadListview.ILoadListener{

    private ImageView img_back;
    private TextView tv_title;
    private ImageView patrol_loc;
    private ProgressBar progressBar;

    private ArrayList<PatrolRows> patrolRow = new ArrayList<PatrolRows>();

    private TrackPlayBackAdapter adapter;
    private UnPatrolLoadListview loadListview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.track_playback);

        initView();
        initChanged();
        downloadClick();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);
        patrol_loc = (ImageView) findViewById(R.id.id_detail_patrol_loc);
        patrol_loc.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.id_track_bar);
    }

    private void showListView(ArrayList<PatrolRows> rowses){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_track_list);
            loadListview.setInterface(this);
            adapter = new TrackPlayBackAdapter(this, rowses);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(rowses);
        }

        loadListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TrackPlayBackActivity.this, "你点击了详情" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TrackPlayBackActivity.this, TrackMapActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    private void initChanged(){
        tv_title.setText("轨迹回放");
        patrol_loc.setImageResource(R.drawable.icon_activity_lbs);
        patrol_loc.setVisibility(View.GONE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        patrol_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrackPlayBackActivity.this, "you clicked loc", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取更多数据
                //getLoadData();
                //更新listview显示
                showListView(patrolRow);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

    public void downloadClick() {
        String url = "http://172.27.35.1:8080/water-patrol/patrol/patrolMission/listJson";
        AsycHttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Gson gson = new Gson();
                PatrolJson patrolJson = gson.fromJson(response.toString(), PatrolJson.class);
                Log.e("patrolJson on TrackPlayBackActivity", patrolJson.toString());
                patrolRow = (ArrayList<PatrolRows>) patrolJson.getRows();
                Log.e("patrolRow", patrolRow.toString());
                //initData(patrolDetail);
                showListView(patrolRow);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(TrackPlayBackActivity.this, "可能未联网，加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
