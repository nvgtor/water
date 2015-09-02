package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.RecorderAdapter;
import com.water.nvgtor.watermanegement.bean.Recorder;
import com.water.nvgtor.watermanegement.tool.HttpUtil;
import com.water.nvgtor.watermanegement.view.AudioRecorderButton;
import com.water.nvgtor.watermanegement.view.MyMediaManager;

import org.apache.http.Header;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/9/1.
 */
public class PatrolDetailReportActivity extends Activity{
    private ImageView img_back;
    private TextView tv_title;
    private ImageView img_loc;
    private RelativeLayout myRelayout;

    private String missionID,patrolPointID,deviceID;

    //录音相关
    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas = new ArrayList<Recorder>();
    private AudioRecorderButton mAudioRecorderButton;
    private View animView;

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private boolean[] imgState = {false,false,false,false};
    //拍照
    private Button btnCamera;
    private Button btnGallery;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    private Button btn_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_device_point_detail);

        initView();
        tv_title.setText("巡检点巡检");
        //拍照事件监听
        takePhoto();
        //录音事件
        initRecorder();
        Intent intent = getIntent();
        missionID = intent.getStringExtra("patrolMissionID");
        Log.e("missionID", missionID);
        patrolPointID = intent.getStringExtra("pointID");
        Log.e("pointID", patrolPointID+"");
        deviceID = intent.getStringExtra("deviceID");
        Log.e("deviceID", deviceID);
        initClick();
    }
    private void initRecorder(){
        mListView = (ListView) findViewById(R.id.id_device_recorder_list);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_map_device_microphone);
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                Recorder recorder = new Recorder(seconds, filePath);
                mDatas.add(recorder);
                mAdapter.notifyDataSetChanged();//通知更新的内容
                mListView.setSelection(mDatas.size() - 1);//将lisview设置为最后一个
            }
        });

        mAdapter = new RecorderAdapter(this,mDatas);
        mListView.setAdapter(mAdapter);

        //listView的item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (animView != null) {
                    animView.setBackgroundResource(R.drawable.adj);
                    animView = null;
                }
                //播放动画
                animView = view.findViewById(R.id.recorder_anim);
                animView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable anim = (AnimationDrawable) animView.getBackground();
                anim.start();
                //播放音频
                MyMediaManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        animView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });
    }

    private void initView(){
        myRelayout = (RelativeLayout) findViewById(R.id.id_map_marker_detail);

        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);
        img_loc = (ImageView)findViewById(R.id.id_detail_patrol_loc);
        img_loc.setImageResource(R.drawable.right_loc);

        btnCamera = (Button)findViewById(R.id.id_map_device_camera);
        btnGallery = (Button) findViewById(R.id.id_map_device_gallery);
        img1 = (ImageView) findViewById(R.id.id_map_img_add_1);
        img2 = (ImageView) findViewById(R.id.id_map_img_add_2);
        img3 = (ImageView) findViewById(R.id.id_map_img_add_3);
        img4 = (ImageView) findViewById(R.id.id_map_img_add_4);

        btn_finish = (Button) findViewById(R.id.id_map_device_finish);
    }

    private void initClick(){
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatrolDetailReportActivity.this, PatrolMapDetailActivity.class);
                startActivity(intent);
            }
        });

        //点击取消EditView的焦点
        myRelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        //完成button
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
                Toast.makeText(PatrolDetailReportActivity.this, "正在上传",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postData(){
        RequestParams params = new RequestParams();
        params.add("missionID",missionID);
        params.add("patrolPointID",patrolPointID);
        params.add("deviceID",deviceID);
        params.add("status",1+"");
        String url = "http://172.17.192.1:8080/water-patrol/patrol/record/addJson";
        HttpUtil.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.e("POST SUCCESS", new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("post failure", responseBody.toString());
            }
        });
    }

    private void takePhoto(){
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnGallery.setBackgroundResource(R.drawable.btn_posts_photo_l_1);
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
            }
        });
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!imgState[0]){
            img1.setImageBitmap(thumbnail);
            imgState[0] = true;
        }else if (!imgState[1]){
            img2.setImageBitmap(thumbnail);
            imgState[1] = true;
        }else if (!imgState[2]){
            img3.setImageBitmap(thumbnail);
            imgState[2] = true;
        }else if (!imgState[3]){
            img4.setImageBitmap(thumbnail);
            imgState[3] = true;
        }else{
            Toast.makeText(PatrolDetailReportActivity.this, "最多上传4张照片",Toast.LENGTH_SHORT).show();
        }
    }

   @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        if (!imgState[0]){
            img1.setImageBitmap(bm);
            imgState[0] = true;
        }else if (!imgState[1]){
            img2.setImageBitmap(bm);
            imgState[1] = true;
        }else if (!imgState[2]){
            img3.setImageBitmap(bm);
            imgState[2] = true;
        }else if (!imgState[3]){
            img4.setImageBitmap(bm);
            imgState[3] = true;
        }else{
            Toast.makeText(PatrolDetailReportActivity.this, "最多上传4张照片",Toast.LENGTH_SHORT).show();
        }
    }
}
