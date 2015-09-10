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
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.RecorderAdapter;
import com.water.nvgtor.watermanegement.bean.PostFile;
import com.water.nvgtor.watermanegement.bean.PostFileReturn;
import com.water.nvgtor.watermanegement.bean.Recorder;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;
import com.water.nvgtor.watermanegement.tool.HttpCallBackListener;
import com.water.nvgtor.watermanegement.tool.HttpUtil;
import com.water.nvgtor.watermanegement.view.AudioRecorderButton;
import com.water.nvgtor.watermanegement.view.MyMediaManager;

import org.apache.http.Header;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by dell on 2015/8/24.
 */
public class IncidentReportActivity extends Activity {
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
    private LinearLayout imgLayout;
    private Button btn_finish;

    //editText
    private EditText addr;
    private EditText man;
    private EditText reflect;

    private ImageView backImg;
    private TextView title;
    private ImageView rImg;

    private LinearLayout incidentLayout;
    private ProgressBar progressBar;

    //上传参数
    private ArrayList<String> photoUrl = new ArrayList<>();
    private String happenAddr;
    private String reflectPeople;
    private String reflectContent;
    private String acceptTime;
    //上传附件
    private ArrayList<PostFile> imageList = new ArrayList<>();
    private ArrayList<PostFile> VoiceList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.incident_reporting);

        initView();
        addr.getText();
        man.getText();
        reflect.getText();
        initClick();
        takePhoto();
        initRecorder();

    }
    private void initView(){
        backImg = (ImageView) findViewById(R.id.id_detail_back_img);
        title = (TextView) findViewById(R.id.id_detail_back_title);
        rImg = (ImageView)findViewById(R.id.id_detail_patrol_loc);
        title.setText("事件上报");
        rImg.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.id_incident_bar);
        progressBar.setVisibility(View.GONE);

        addr = (EditText) findViewById(R.id.id_incident_report_address);
        man = (EditText) findViewById(R.id.id_incident_report_man);
        reflect = (EditText) findViewById(R.id.id_incident_report_reflectContent);


        imgLayout = (LinearLayout) findViewById(R.id.id_incident_report_photo);
        incidentLayout = (LinearLayout) findViewById(R.id.id_incident_layout);

        btnCamera = (Button)findViewById(R.id.id_incident_camera);
        btnGallery = (Button) findViewById(R.id.id_incident_gallery);
        img1 = (ImageView) findViewById(R.id.id_report_img_add_1);
        img2 = (ImageView) findViewById(R.id.id_report_img_add_2);
        img3 = (ImageView) findViewById(R.id.id_report_img_add_3);
        img4 = (ImageView) findViewById(R.id.id_report_img_add_4);

        btn_finish = (Button) findViewById(R.id.id_incident_report_finish);
    }

    private void initClick(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        incidentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IncidentReportActivity.this, "提交", Toast.LENGTH_SHORT).show();
                Log.e("photoUrl", photoUrl.toString());
                progressBar.setVisibility(View.VISIBLE);
                getReturnUrl();
                getRecorder();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("imageList", imageList.toString());
                        postData();
                        progressBar.setVisibility(View.GONE);
                    }
                }, 8000);
            }
        });
    }

    private void postData(){
        String url = "http://172.27.35.1:8080/water-patrol/event/event/addJson";

        SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss", Locale.CHINA);
        String    date    =    sDateFormat.format(new    java.util.Date());
        acceptTime = date;
        RequestParams params = new RequestParams();
        params.put("happenAddr",addr.getText());
        params.put("reflectPeople", man.getText());
        params.put("reflectContent",reflect.getText());
        params.put("acceptTime", acceptTime);

        if (imageList != null){
            for (int j = 0; j < imageList.size(); j++){
                params.put("imageList[" + j + "].url" ,imageList.get(j).getUrl());
            }
        }

        if (VoiceList.size() > 0)
            params.put("VoiceList[0].url", VoiceList.get(0).getUrl());

        Log.e("params", params.toString());
        AsycHttpUtil.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("success", new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("failure", "failure post");
            }
        });
    }

    private void getReturnUrl(){
        if (photoUrl != null){
            Log.e("photoUrl.size", photoUrl.size() + "");
            for (int i = 0; i < photoUrl.size(); i++){
                String url = "http://172.27.35.1:8080/water-patrol/patrol/accessory/upload";
                HttpUtil.sendRequestWithHttpClient(url, new File(photoUrl.get(i)), new HttpCallBackListener() {
                    @Override
                    public void onFinish(String response) {
                        Gson gson = new Gson();
                        PostFileReturn fileReturn = gson.fromJson(response, PostFileReturn.class);
                        Log.e("PostFileReturn", fileReturn.getFileName());
                        PostFile postFile = new PostFile(fileReturn.getFileName());
                        imageList.add(postFile);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }else {
            Toast.makeText(IncidentReportActivity.this, "没有照片", Toast.LENGTH_SHORT).show();
        }
    }

    private void getRecorder(){
        if (mDatas.size() > 0){
            int len = mDatas.size() - 1;
            File myRecoederFile = new File(mDatas.get(len).getFilePath());
            String url = "http://172.27.35.1:8080/water-patrol/patrol/accessory/upload";
            HttpUtil.sendRequestWithHttpClient(url, myRecoederFile, new HttpCallBackListener() {
                @Override
                public void onFinish(String response) {
                    Gson gson = new Gson();
                    PostFileReturn fileReturn = gson.fromJson(response, PostFileReturn.class);
                    Log.e("PostRecorderReturn", fileReturn.getFileName());
                    PostFile postFile = new PostFile(fileReturn.getFileName());
                    VoiceList.add(postFile);
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
        else{
            Toast.makeText(IncidentReportActivity.this, "没有录音", Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecorder(){
        mListView = (ListView) findViewById(R.id.id_incident_recorder_list);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_incident_microphone);
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
        mListView.setVisibility(View.VISIBLE);
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
        Log.e("photo url", destination.toString());
        photoUrl.add(destination.toString());
        //Log.e("photoUrl", photoUrl.toString());
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

        imgLayout.setVisibility(View.VISIBLE);
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
            Toast.makeText(IncidentReportActivity.this, "最多上传4张照片", Toast.LENGTH_SHORT).show();
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
        Log.e("gallery path", selectedImagePath);
        photoUrl.add(selectedImagePath);
        //Log.e("photoUrl", photoUrl.toString());
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
        Bitmap bm = BitmapFactory.decodeFile(selectedImagePath, options);

        imgLayout.setVisibility(View.VISIBLE);
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
            Toast.makeText(IncidentReportActivity.this, "最多上传4张照片",Toast.LENGTH_SHORT).show();
        }
    }
}

