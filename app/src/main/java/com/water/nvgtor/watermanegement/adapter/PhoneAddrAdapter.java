package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.PhoneBean;

import java.util.ArrayList;

/**
 * Created by dell on 2015/9/1.
 */
public class PhoneAddrAdapter extends BaseAdapter {
    ArrayList<PhoneBean> phoneList = new ArrayList<PhoneBean>();
    LayoutInflater inflater;

    public PhoneAddrAdapter(Context context, ArrayList<PhoneBean> phoneList){
        this.phoneList = phoneList;
        this.inflater = LayoutInflater.from(context);
    }

    public void onDataChange(ArrayList<PhoneBean> phoneList){
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
        PhoneBean PhoneBean = phoneList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.phone_addr_item, null);
            holder.tv_department = (TextView)convertView.findViewById(R.id.id_phone_item2);
            holder.tv_telephone = (TextView)convertView.findViewById(R.id.id_phone_item3);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_department.setText(PhoneBean.getDepartment());
        holder.tv_telephone.setText(PhoneBean.getTelephone());
        return convertView;
    }
    class ViewHolder{
        TextView tv_department;
        TextView tv_telephone;
    }
}

