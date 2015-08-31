package com.water.nvgtor.watermanegement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.activity.PatrolTaskDetailActivity;
import com.water.nvgtor.watermanegement.adapter.PatrolTaskListAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolJson;
import com.water.nvgtor.watermanegement.bean.PatrolRows;
import com.water.nvgtor.watermanegement.tool.HttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/26.
 */
public class PatrolTasking extends Fragment implements UnPatrolLoadListview.ILoadListener{
    ArrayList<PatrolRows> patrolList = new ArrayList<PatrolRows>();
    ArrayList<PatrolRows> patrolRowses = new ArrayList<PatrolRows>();
    PatrolTaskListAdapter adapter;
    UnPatrolLoadListview loadListview;
    ProgressBar progressBar;
    //private Handler handler;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getData();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_patrol_tasking, null);
        progressBar = (ProgressBar) view.findViewById(R.id.id_tasking_bar);
        progressBar.setVisibility(View.VISIBLE);
        downloadClick();
        loadListview = (UnPatrolLoadListview) view.findViewById(R.id.id_patrol_tasking_list);
        loadListview.setInterface(this);
        loadListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PatrolTaskDetailActivity.class);
                intent.putExtra("id", patrolRowses.get(position).getId());
                Log.d("position id", position + ":" + patrolRowses.get(position).getId());
                startActivity(intent);
                Toast.makeText(getActivity(), "你点击了详情" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    private void getData() {
        for (int i = 0; i < 15; i++) {
            PatrolRows entity = new PatrolRows();
            entity.setPlanName("A片区巡检");
            entity.setPersonName("张默默");
            entity.setStartTime("2015-08-15");
            entity.setTimeLimit(3.2);
            patrolList.add(entity);
        }
    }

    private void getLoadData() {
        for (int i = 0; i < 2; i++) {
            PatrolRows entity = new PatrolRows();
            entity.setPlanName("C片区巡检");
            entity.setPersonName("李怡然");
            entity.setStartTime("2015-08-15");
            entity.setTimeLimit(2.0);
            patrolList.add(entity);
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
                adapter.onDataChange(patrolRowses);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

    public void downloadClick() {
        String url = "http://172.19.53.1:8080/water-patrol/patrol/patrolMission/listJson";
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Gson gson = new Gson();
                PatrolJson patrolJson = gson.fromJson(response.toString(), PatrolJson.class);
                Log.d("Json bean", patrolJson.toString());
                for (int i = 0; i < patrolJson.getRows().size(); i++){
                    patrolRowses.add(patrolJson.getRows().get(i));
                }
                Log.d("rows", patrolRowses.toString());
                if (adapter == null){
                    adapter = new PatrolTaskListAdapter(getActivity(), patrolRowses);
                    //adapter.setHandler(handler_h);
                    loadListview.setAdapter(adapter);
                }else {
                    adapter.onDataChange(patrolRowses);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                }, 500);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                // Do something with the response
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getActivity(),"可能未联网，加载失败",Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1000);
            }
        });
    }
}
