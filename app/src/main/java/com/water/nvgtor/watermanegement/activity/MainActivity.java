package com.water.nvgtor.watermanegement.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.MyGridAdapter;
import com.water.nvgtor.watermanegement.view.SlidingMenu;

/**
 * Created by dell on 2015/7/22.
 */
public class MainActivity extends FragmentActivity{

    private ImageView userImg;
    private ImageView rightImg;
    private SlidingMenu mLeftMenu;

    private RelativeLayout menu_item1;
    private RelativeLayout menu_item2;
    private RelativeLayout menu_item3;
    private RelativeLayout menu_item4;

    private String userName;

    private TextView myName;
    private ImageView userFace;

    /**
     * GridView相关
     */
    LayoutInflater mInflater;
    private Context mContext;
    private GridView gridView;
    public String[] img_text = {"巡检", "维修", "临时任务", "事件上报",
            "巡检地图", "通讯录", "系统设置","轨迹回放"};
    public int[] imgs = {R.drawable.zhoumoqunaer, R.drawable.icon_game_tab_class_n,
            R.drawable.icon_game_tab_hot_n, R.drawable.btn_live_speak_n, R.drawable.detail_normal_addr,
            R.drawable.walk, R.drawable.atm, R.drawable.icon_find_square};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        final Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        myName.setText(userName);

        userFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent = new Intent(MainActivity.this, UserInfoActivity.class);
                userIntent.putExtra("userName",userName);
                startActivity(userIntent);

            }
        });

        MyGridAdapter adapter = new MyGridAdapter(this,img_text,imgs);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, PatrolModelActivity.class);
                        startActivity(intent);
                        break;
                        //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, RepairModelActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, TemporaryTaskActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, IncidentReportActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, PatrolMapDetailActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(MainActivity.this, PhoneAddrActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(MainActivity.this, TrackPlayBackActivity.class);
                        startActivity(intent7);
                        break;
                }
            }
        });
    }

    private void initView(){

        gridView = (GridView)findViewById(R.id.gridview);

        userImg = (ImageView)findViewById(R.id.top_head_userImg);
        rightImg = (ImageView) findViewById(R.id.top_more);
        rightImg.setVisibility(View.GONE);

        mLeftMenu = (SlidingMenu)findViewById(R.id.id_menu);
        myName = (TextView) findViewById(R.id.id_my_name);
        userFace = (ImageView) findViewById(R.id.id_userface);
        menu_item1 = (RelativeLayout)findViewById(R.id.menu_item1);
        menu_item2 = (RelativeLayout)findViewById(R.id.menu_item2);
        menu_item3 = (RelativeLayout)findViewById(R.id.menu_item3);
        menu_item4 = (RelativeLayout)findViewById(R.id.menu_item4);

        setChangeView();
    }
    private void setChangeView(){
        initTopHead();
        initLeftMenu();
    }

    /**
     * 初始化头部布局,这里是userImage可点击事件注册，打开与关闭菜单
     */
    private void initTopHead(){
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftMenu.toggle();
            }
        });
    }

    /**
     * 初始化左侧菜单
     */
    private void initLeftMenu(){
        menu_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePW = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(changePW);
                Toast.makeText(MainActivity.this,"修改密码", Toast.LENGTH_SHORT).show();
            }
        });
        menu_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"软件说明", Toast.LENGTH_SHORT).show();
            }
        });
        menu_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myLocIntent = new Intent(MainActivity.this, MyLocationActivity.class);
                startActivity(myLocIntent);
                Toast.makeText(MainActivity.this,"设置", Toast.LENGTH_SHORT).show();
            }
        });
        menu_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent exitIntent = new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(exitIntent);
                finish();
                Toast.makeText(MainActivity.this,"退出账号", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
