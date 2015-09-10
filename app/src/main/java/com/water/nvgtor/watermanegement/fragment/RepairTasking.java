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
import com.loopj.android.http.RequestParams;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.activity.RepairDetailActivity;
import com.water.nvgtor.watermanegement.adapter.RepairTaskAdapter;
import com.water.nvgtor.watermanegement.bean.RepairJson;
import com.water.nvgtor.watermanegement.bean.RepairRows;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/31.
 */
public class RepairTasking extends Fragment implements UnPatrolLoadListview.ILoadListener{
    ArrayList<RepairRows> repairRowses = new ArrayList<>();
    RepairTaskAdapter adapter;
    UnPatrolLoadListview loadListview;
    ProgressBar progressBar;
    //private Handler handler;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //getData();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_repair_tasking, null);
        progressBar = (ProgressBar) view.findViewById(R.id.id_repair_bar);
        progressBar.setVisibility(View.VISIBLE);
        loadListview = (UnPatrolLoadListview) view.findViewById(R.id.id_fragment_repair_task_list);

        downloadClick();

        loadListview.setInterface(this);
        loadListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RepairDetailActivity.class);
                intent.putExtra("eid", repairRowses.get(position).getEid());
                Log.e("RepairTasking position+eid", position + ":" + repairRowses.get(position).getEid());
                startActivity(intent);
                Toast.makeText(getActivity(), "你点击了维修详情" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
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
                adapter.onDataChange(repairRowses);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

    public void downloadClick() {
        RequestParams params = new RequestParams();
        params.put("nodeName","已派工单");
        String url = "http://172.27.35.1:8080/water-repair/repair/listJsonbyNodeName";
        AsycHttpUtil.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Gson gson = new Gson();
                RepairJson RepairJson = gson.fromJson(response.toString(), RepairJson.class);
                Log.d("RepairTasking json back", RepairJson.toString());
                for (int i = 0; i < RepairJson.getRows().size(); i++) {
                    repairRowses.add(RepairJson.getRows().get(i));
                }
                Log.d("RepairTasking repairRows", repairRowses.toString());
                if (adapter == null) {
                    adapter = new RepairTaskAdapter(getActivity(), repairRowses);
                    //adapter.setHandler(handler_h);
                    loadListview.setAdapter(adapter);
                } else {
                    adapter.onDataChange(repairRowses);
                }
                loadListview.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                }, 100);
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
                }, 500);
            }
        });
    }
}
