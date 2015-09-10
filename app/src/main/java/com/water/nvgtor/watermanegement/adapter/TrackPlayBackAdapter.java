package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.PatrolRows;

import java.util.ArrayList;

/**
 * Created by dell on 2015/9/7.
 */
public class TrackPlayBackAdapter extends BaseAdapter {
    ArrayList<PatrolRows> patrolRows = new ArrayList<PatrolRows>();
    LayoutInflater inflater;

    public TrackPlayBackAdapter(Context context, ArrayList<PatrolRows> patrolRows){
        this.patrolRows = patrolRows;
        this.inflater = LayoutInflater.from(context);
    }

    public void onDataChange(ArrayList<PatrolRows> patrolRows){
        this.patrolRows = patrolRows;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return patrolRows.size();
    }

    @Override
    public Object getItem(int position) {
        return patrolRows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PatrolRows rows = patrolRows.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.track_playback_item, null);
            holder.tv_planName = (TextView)convertView.findViewById(R.id.id_track_item1);
            holder.tv_exeMan = (TextView)convertView.findViewById(R.id.id_track_item2);
            holder.tv_endTime = (TextView)convertView.findViewById(R.id.id_track_item3);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv_planName.setText(rows.getPlanName());
        holder.tv_exeMan.setText(rows.getPersonName());
        holder.tv_endTime.setText(rows.getEndTime());

        return convertView;
    }
    class ViewHolder{
        TextView tv_planName;
        TextView tv_exeMan;
        TextView tv_endTime;
    }
}
