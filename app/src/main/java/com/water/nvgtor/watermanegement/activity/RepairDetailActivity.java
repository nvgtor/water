package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/8/31.
 */
public class RepairDetailActivity extends Activity{

    private ImageView img_back;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.repair_task_detial);

        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);
        tv_title.setText("维修任务详情");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
