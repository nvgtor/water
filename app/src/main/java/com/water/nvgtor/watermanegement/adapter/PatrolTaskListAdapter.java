package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.PatrolRows;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/3.
 */
public class PatrolTaskListAdapter extends BaseAdapter {
    ArrayList<PatrolRows> patrolList = new ArrayList<PatrolRows>();
    LayoutInflater inflater;
    private Handler handler;

    public PatrolTaskListAdapter(Context context, ArrayList<PatrolRows> patrolList){
        this.patrolList = patrolList;
        this.inflater = LayoutInflater.from(context);
    }


    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<PatrolRows> patrolList){
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
        PatrolRows patrolTask = patrolList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_patrol_tasking_item, null);
            holder.tv_name = (TextView)convertView.findViewById(R.id.id_patrol_tasking_item1);
            holder.tv_execute = (TextView)convertView.findViewById(R.id.id_patrol_tasking_item2);
            holder.tv_startTime = (TextView)convertView.findViewById(R.id.id_patrol_tasking_item3);
            holder.tv_deadline = (TextView)convertView.findViewById(R.id.id_patrol_tasking_item4);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(patrolTask.getPlanName());
        holder.tv_execute.setText(patrolTask.getPersonName());
        holder.tv_startTime.setText(patrolTask.getStartTime());
        holder.tv_deadline.setText(patrolTask.getTimeLimit()+"");

        return convertView;
    }
    class ViewHolder{
        TextView tv_name;
        TextView tv_execute;
        TextView tv_startTime;
        TextView tv_deadline;
    }
}
