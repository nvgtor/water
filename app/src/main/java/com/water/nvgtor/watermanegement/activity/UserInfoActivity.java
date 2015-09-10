package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/9/9.
 */
public class UserInfoActivity extends Activity {

    private String userName;
    private TextView mName;

    private ImageView backImg;
    private TextView title;
    private ImageView rImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.user_info);

        mName = (TextView) findViewById(R.id.id_my_name);
        backImg = (ImageView) findViewById(R.id.id_detail_back_img);
        title = (TextView) findViewById(R.id.id_detail_back_title);
        rImg = (ImageView)findViewById(R.id.id_detail_patrol_loc);
        title.setText("个人信息");
        rImg.setVisibility(View.GONE);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        mName.setText(userName);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
