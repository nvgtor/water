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
import com.water.nvgtor.watermanegement.bean.TempTasking;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/25.
 */
public class TempTaskingAdapter  extends BaseAdapter {

    ArrayList<TempTasking> repairList = new ArrayList<TempTasking>();
    LayoutInflater inflater;
    private Handler handler;

    public TempTaskingAdapter(Context context, ArrayList<TempTasking> repairList){
        this.repairList = repairList;
        this.inflater = LayoutInflater.from(context);
    }


    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<TempTasking> repairList){
        this.repairList = repairList;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return repairList.size();
    }

    @Override
    public Object getItem(int position) {
        return repairList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TempTasking tempTasking = repairList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_temporary_tasking_item, null);
            holder.tv_taskType = (TextView)convertView.findViewById(R.id.temporary_task_item1);
            holder.tv_taskName = (TextView)convertView.findViewById(R.id.temporary_task_item2);
            holder.tv_taskAddr = (TextView)convertView.findViewById(R.id.temporary_task_item3);
            holder.tv_taskMan = (TextView)convertView.findViewById(R.id.temporary_task_item4);
            holder.tv_deakLine = (TextView)convertView.findViewById(R.id.temporary_task_item5);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_taskType.setText(tempTasking.getTaskType());
        holder.tv_taskName.setText(tempTasking.getTaskName());
        holder.tv_taskAddr.setText(tempTasking.getTaskAddr());
        holder.tv_taskMan.setText(tempTasking.getTaskMan());
        holder.tv_deakLine.setText(tempTasking.getTaskDeadLine());

        holder.btn_detail = (Button)convertView.findViewById(R.id.temporary_task_item6);
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_taskType;
        TextView tv_taskName;
        TextView tv_taskAddr;
        TextView tv_taskMan;
        TextView tv_deakLine;
        Button btn_detail;
    }
}
