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
import com.water.nvgtor.watermanegement.activity.PatroledListDetailActivity;
import com.water.nvgtor.watermanegement.adapter.PatroledTaskAdapter;
import com.water.nvgtor.watermanegement.bean.PatroledJson;
import com.water.nvgtor.watermanegement.bean.PatroledRows;
import com.water.nvgtor.watermanegement.bean.PatroledTask;
import com.water.nvgtor.watermanegement.tool.HttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/26.
 */
public class PatroledTasking extends Fragment implements UnPatrolLoadListview.ILoadListener{
    ArrayList<PatroledTask> patroledList = new ArrayList<PatroledTask>();
    ArrayList<PatroledRows> patroledRowses = new ArrayList<>();

    PatroledTaskAdapter adapter;
    UnPatrolLoadListview loadListview;
    ProgressBar progressBar;
    //private Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getData();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_patroled_tasking, null);
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) view.findViewById(R.id.id_patroled_tasking_list);
            progressBar = (ProgressBar) view.findViewById(R.id.id_patroled_tasking_bar);
            progressBar.setVisibility(View.VISIBLE);
            downloadClick();
            loadListview.setInterface(this);
            adapter = new PatroledTaskAdapter(getActivity(), patroledRowses);
            //adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(patroledRowses);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                loadListview.setVisibility(View.VISIBLE);
            }
        }, 2000);

        loadListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PatroledListDetailActivity.class);
                intent.putExtra("id",patroledRowses.get(position).getId());
                Log.e("patroled id position", position + ":" + patroledRowses.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData(){
        for(int i = 0; i < 13; i++){
            PatroledTask entity = new PatroledTask();
            entity.setTaskName("B片区巡检");
            entity.setTaskArea("B片区");
            entity.setExecuteMan("祝天顾");
            entity.setDeadline("2015-08-15");
            entity.setFinalState(0);
            patroledList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            PatroledTask entity = new PatroledTask();
            entity.setTaskName("B片区巡检");
            entity.setTaskArea("B片区");
            entity.setExecuteMan("高 雨");
            entity.setDeadline("2015-09-01");
            entity.setFinalState(1);
            patroledList.add(entity);
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
                adapter.onDataChange(patroledRowses);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

    /*Handler handler_h = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what){//如果item项目里有多个按钮触发，可以在这里区分
                case R.id.temporary_task_item6:
                    //switch (msg.arg1){
                    //case 0:
                    //Intent intent = new Intent(tempTaskListActivity.this, tempTaskListDetailActivity.class);
                    //startActivity(intent);
                    break;
            }
        }

    };*/

    public void downloadClick() {
        String url = "http://172.17.192.1:8080/water-patrol/patrol/examine/listJson";
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Gson gson = new Gson();
                PatroledJson patroledJsons = gson.fromJson(response.toString(), PatroledJson.class);
                Log.d("Json bean", patroledJsons.toString());
                for (int i = 0; i < patroledJsons.getRows().size(); i++) {
                    patroledRowses.add(patroledJsons.getRows().get(i));
                }
                Log.d("rows", patroledRowses.toString());
                if (adapter == null) {
                    adapter = new PatroledTaskAdapter(getActivity(), patroledRowses);
                    //adapter.setHandler(handler_h);
                    loadListview.setAdapter(adapter);
                } else {
                    adapter.onDataChange(patroledRowses);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1000);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getActivity(), "可能未联网，加载失败", Toast.LENGTH_SHORT).show();
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

