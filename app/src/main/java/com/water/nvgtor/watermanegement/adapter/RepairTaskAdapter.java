package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.RepairRows;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/21.
 */
public class RepairTaskAdapter extends BaseAdapter {

    ArrayList<RepairRows> repairList = new ArrayList<RepairRows>();
    LayoutInflater inflater;
    private Handler handler;

    public RepairTaskAdapter(Context context, ArrayList<RepairRows> repairList){
        this.repairList = repairList;
        this.inflater = LayoutInflater.from(context);
    }


    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<RepairRows> repairList){
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
        RepairRows repairRow = repairList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_rapair_tasking_item, null);
            holder.tv_des = (TextView)convertView.findViewById(R.id.id_frag_repair_item1);
            holder.tv_addr = (TextView)convertView.findViewById(R.id.id_frag_repair_item2);
            holder.tv_time = (TextView)convertView.findViewById(R.id.id_frag_repair_item3);
            holder.tv_man = (TextView)convertView.findViewById(R.id.id_frag_repair_item4);
            holder.tv_deadline = (TextView)convertView.findViewById(R.id.id_frag_repair_item5);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_des.setText(repairRow.getReflectContent());
        holder.tv_addr.setText(repairRow.getHappenAddr());
        holder.tv_time.setText(repairRow.getHappenTime());
        holder.tv_man.setText(repairRow.getSurveryMan());
        holder.tv_deadline.setText(repairRow.getClosingTime());

        return convertView;
    }
    class ViewHolder{
        TextView tv_des;
        TextView tv_addr;
        TextView tv_time;
        TextView tv_man;
        TextView tv_deadline;
    }
}
