package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.PatrolPlanPoint;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/6.
 */
public class PatrolTaskDetailListAdapter extends BaseAdapter {

    ArrayList<PatrolPlanPoint> patrolList = new ArrayList<PatrolPlanPoint>();
    LayoutInflater inflater;
    private Handler handler;

    public PatrolTaskDetailListAdapter(Context context, ArrayList<PatrolPlanPoint> patrolList){
        this.patrolList = patrolList;
        this.inflater = LayoutInflater.from(context);
    }

    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<PatrolPlanPoint> patrolList){
        this.patrolList = patrolList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return patrolList.size();
    }

    @Override
    public Object getItem(int position) {
        return patrolList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PatrolPlanPoint detailList = patrolList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.patrol_task_detail_list_item, null);
            holder.tv_lpid = (TextView)convertView.findViewById(R.id.id_detail_ID);
            holder.tv_name = (TextView)convertView.findViewById(R.id.id_detail_name);
            holder.tv_deviceNum = (TextView)convertView.findViewById(R.id.id_detail_num);
            holder.tv_remark = (TextView)convertView.findViewById(R.id.id_detail_remark);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv_lpid.setText(detailList.getLpid());
        holder.tv_name.setText(detailList.getName());
        holder.tv_deviceNum.setText(detailList.getDeviceNum());
        holder.tv_remark.setText(detailList.getRemark()+"个");

        holder.btn_start = (Button)convertView.findViewById(R.id.id_detail_btn);
        holder.btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handler != null){
                    Message msg = handler.obtainMessage();
                    msg.what = v.getId();
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView tv_lpid;
        TextView tv_name;
        TextView tv_deviceNum;
        TextView tv_remark;
        Button btn_start;
    }
}
