package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PhoneAddrAdapter;
import com.water.nvgtor.watermanegement.bean.PhoneAddr;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/9/1.
 */
public class PhoneAddrActivity extends Activity implements UnPatrolLoadListview.ILoadListener{

    private ArrayList<PhoneAddr> phoneAddrsList = new ArrayList<>();
    private UnPatrolLoadListview loadListview;
    private PhoneAddrAdapter addrAdapter;

    private ImageView img_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.phone_address);

        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);

        tv_title.setText("通讯录");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();
        showListView(phoneAddrsList);

    }
    private int i;
    private void getData(){
        for( i= 0; i < 10; i++){
            PhoneAddr entity = new PhoneAddr();
            entity.setId(i);
            entity.setName("唐琳琳");
            entity.setNumber("13820793791");
            entity.setJob("测量站");
            phoneAddrsList.add(entity);
        }
    }


    private void getLoadData(){
        for (int j = 0; j < 2; j++){
            PhoneAddr entity = new PhoneAddr();
            entity.setId(i+j);
            entity.setName("李秋然");
            entity.setNumber("15220767362");
            entity.setJob("测量站");
            phoneAddrsList.add(entity);
        }
        i+=2;
    }

    private void showListView(ArrayList<PhoneAddr> phoneAddrs){
        if (addrAdapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_phone_addr_list);
            loadListview.setInterface(this);
            addrAdapter = new PhoneAddrAdapter(this, phoneAddrs);
            loadListview.setAdapter(addrAdapter);
        }else {
            addrAdapter.onDataChange(phoneAddrs);
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
                showListView(phoneAddrsList);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

}
