package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PhoneAddrAdapter;
import com.water.nvgtor.watermanegement.bean.PhoneAddr;
import com.water.nvgtor.watermanegement.bean.PhoneBean;
import com.water.nvgtor.watermanegement.bean.PhoneJson;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;

import java.util.ArrayList;

/**
 * Created by dell on 2015/9/1.
 */
public class PhoneAddrActivity extends Activity implements UnPatrolLoadListview.ILoadListener{

    private ArrayList<PhoneAddr> phoneAddrsList = new ArrayList<>();
    private UnPatrolLoadListview loadListview;
    private PhoneAddrAdapter addrAdapter;

    private PhoneJson phoneJson;
    private ArrayList<PhoneBean> phoneList = new ArrayList<>();

    private ImageView img_back;
    private ImageView right_img;
    private TextView tv_title;

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.phone_address);

        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        right_img = (ImageView) findViewById(R.id.id_detail_patrol_loc);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);
        right_img.setVisibility(View.GONE);
        tv_title.setText("通讯录");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        downClick();
        //getData();
        //showListView(phoneAddrsList);

    }
    /*private int i;
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
    }*/

    private void showListView(final ArrayList<PhoneBean> phoneAddrs){
        if (addrAdapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_phone_addr_list);
            loadListview.setInterface(this);
            addrAdapter = new PhoneAddrAdapter(this, phoneAddrs);
            loadListview.setAdapter(addrAdapter);
        }else {
            addrAdapter.onDataChange(phoneAddrs);
        }

        loadListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CallOrSendMessage(phoneAddrs.get(position).getTelephone());
            }
        });
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取更多数据
                //getLoadData();
                //更新listview显示
                showListView(phoneList);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

    private void CallOrSendMessage(final String number) {
        final CharSequence[] items = { "拨打电话", "发短信",
                "取消" };

        AlertDialog.Builder builder = new AlertDialog.Builder(PhoneAddrActivity.this);
        builder.setTitle(number);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("拨打电话")) {
                    Intent intent = new Intent();
                    /*要执行的动作*/
                    intent.setAction("android.intent.action.CALL");
                    //绑定数据
                    intent.setData(Uri.parse("tel:" + number));
                    //激活打电话组件 通过隐式意图 另外不要忘记在清单文件中注册一下打电话的权限
                    startActivity(intent);
                } else if (items[item].equals("发短信")) {
                    Intent intent1 = new Intent();
                    intent1.setAction(Intent.ACTION_SENDTO);
                    intent1.setData(Uri.parse("smsto:"+ number));
                    startActivity(intent1);
                } else if (items[item].equals("取消")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void downClick(){
        String url = "http://172.27.35.1:8080/water-callCenter/call/usefulTelephone/listJson";
        AsycHttpUtil.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                PhoneJson phoneJson = gson.fromJson(new String(responseBody), PhoneJson.class);
                phoneList = (ArrayList<PhoneBean>) phoneJson.getRows();
                showListView(phoneList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(PhoneAddrActivity.this, "可能未联网，加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
