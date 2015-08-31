package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.PatroledRows;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/11.
 */
public class PatroledTaskAdapter extends BaseAdapter{
    ArrayList<PatroledRows> patroledRowses = new ArrayList<PatroledRows>();
    LayoutInflater inflater;
    private Handler handler;

    public PatroledTaskAdapter(Context context, ArrayList<PatroledRows> patroledList){
        this.patroledRowses = patroledList;
        this.inflater = LayoutInflater.from(context);
    }


    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<PatroledRows> patroledList){
        this.patroledRowses = patroledList;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return patroledRowses.size();
    }

    @Override
    public Object getItem(int position) {
        return patroledRowses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PatroledRows patroledTask = patroledRowses.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_patroled_tasking_item, null);
            holder.tv_name = (TextView)convertView.findViewById(R.id.id_patroled_tasking_item1);
            holder.tv_execute = (TextView)convertView.findViewById(R.id.id_patroled_tasking_item2);
            holder.tv_startTime = (TextView)convertView.findViewById(R.id.id_patroled_tasking_item3);
            holder.tv_deadline = (TextView)convertView.findViewById(R.id.id_patroled_tasking_item4);
            holder.tv_state = (TextView)convertView.findViewById(R.id.id_patroled_tasking_item5);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(patroledTask.getPlanName());
        holder.tv_execute.setText(patroledTask.getPersonName());
        holder.tv_startTime.setText(patroledTask.getStartTime());
        holder.tv_deadline.setText(patroledTask.getTimeLimit()+"");
        if (patroledTask.getFinalStatus() == 0){
            holder.tv_state.setText("待审核");
        }else {
            holder.tv_state.setText("审核通过");
        }
        return convertView;
    }
    class ViewHolder{
        TextView tv_name;
        TextView tv_execute;
        TextView tv_startTime;
        TextView tv_deadline;
        TextView tv_state;
    }
}
