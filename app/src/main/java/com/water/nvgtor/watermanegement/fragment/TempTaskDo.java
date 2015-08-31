package com.water.nvgtor.watermanegement.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.TempTaskingAdapter;
import com.water.nvgtor.watermanegement.bean.TempTasking;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/25.
 */
public class TempTaskDo extends Fragment implements UnPatrolLoadListview.ILoadListener{
    ArrayList<TempTasking> tempTaskList = new ArrayList<TempTasking>();
    TempTaskingAdapter adapter;
    UnPatrolLoadListview loadListview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getData();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_temporary_tasking, null);
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) view.findViewById(R.id.id_tempTask_do_list);
            loadListview.setInterface(this);
            adapter = new TempTaskingAdapter(getActivity(), tempTaskList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(tempTaskList);
        }
        return view;
    }

    /*private void showListView(View view, ArrayList<TempTasking> tempTaskList){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) view.findViewById(R.id.id_tempTask_do_list);
            loadListview.setInterface(this);
            adapter = new TempTaskingAdapter(getActivity(), tempTaskList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(tempTaskList);
        }
    }*/

    private void getData(){
        for(int i = 0; i < 5; i++){
            TempTasking entity = new TempTasking();
            entity.setTaskType("巡检");
            entity.setTaskName("A水表0巡检");
            entity.setTaskAddr("A片区");
            entity.setTaskMan("柳雪风");
            entity.setTaskDeadLine("2015-09-03");
            tempTaskList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            TempTasking entity = new TempTasking();
            entity.setTaskType("维修");
            entity.setTaskName("水表损坏");
            entity.setTaskAddr("高新七路");
            entity.setTaskMan("李 青");
            entity.setTaskDeadLine("2015-11-11");
            tempTaskList.add(entity);
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
                adapter.onDataChange(tempTaskList);
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
                case R.id.temporary_task_item6:
                    //switch (msg.arg1){
                    //case 0:
                    //Intent intent = new Intent(tempTaskListActivity.this, tempTaskListDetailActivity.class);
                    //startActivity(intent);
                    break;
            }
        }

    };
}
