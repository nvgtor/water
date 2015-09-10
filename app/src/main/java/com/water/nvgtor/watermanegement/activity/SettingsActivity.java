package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/9/6.
 */
public class SettingsActivity extends Activity{

    private Button post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test_img_post);

        post = (Button) findViewById(R.id.id_post_img);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
