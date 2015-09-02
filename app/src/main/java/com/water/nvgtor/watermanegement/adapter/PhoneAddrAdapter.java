package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.PhoneAddr;

import java.util.ArrayList;

/**
 * Created by dell on 2015/9/1.
 */
public class PhoneAddrAdapter extends BaseAdapter {
    ArrayList<PhoneAddr> phoneList = new ArrayList<PhoneAddr>();
    LayoutInflater inflater;

    public PhoneAddrAdapter(Context context, ArrayList<PhoneAddr> phoneList){
        this.phoneList = phoneList;
        this.inflater = LayoutInflater.from(context);
    }

    public void onDataChange(ArrayList<PhoneAddr> phoneList){
        this.phoneList = phoneList;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return phoneList.size();
    }

    @Override
    public Object getItem(int position) {
        return phoneList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PhoneAddr phoneAddr = phoneList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.phone_addr_item, null);
            holder.tv_id = (TextView)convertView.findViewById(R.id.id_phone_item1);
            holder.tv_name = (TextView)convertView.findViewById(R.id.id_phone_item2);
            holder.tv_number = (TextView)convertView.findViewById(R.id.id_phone_item3);
            holder.tv_job = (TextView)convertView.findViewById(R.id.id_phone_item4);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_id.setText(phoneAddr.getId()+"");
        holder.tv_name.setText(phoneAddr.getName());
        holder.tv_number.setText(phoneAddr.getNumber());
        holder.tv_job.setText(phoneAddr.getJob());

        return convertView;
    }
    class ViewHolder{
        TextView tv_id;
        TextView tv_name;
        TextView tv_number;
        TextView tv_job;
    }
}

