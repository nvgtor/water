package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.PatrolJson;
import com.water.nvgtor.watermanegement.bean.PatrolRows;
import com.water.nvgtor.watermanegement.bean.Track;
import com.water.nvgtor.watermanegement.tool.AsycHttpUtil;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/9/7.
 */
public class TrackMapActivity extends Activity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private BitmapDescriptor mMarker;
    private BitmapDescriptor nav_Marker;
    private boolean isFirstIn = true;
    private OverlayOptions options;
    private List<LatLng> points = new ArrayList<LatLng>();
    private int position;
    private ArrayList<PatrolRows> patrolRow = new ArrayList<PatrolRows>();
    private ArrayList<Track> taskTrack = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.map_track_play_back);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);

        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.bubble_start);
        nav_Marker = BitmapDescriptorFactory.fromResource(R.drawable.bubble_end);
        mMapView = (MapView) findViewById(R.id.id_track_map);
        mBaiduMap = mMapView.getMap ();
        //设置地图放大比例
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);

        downloadClick();
}

    private void addLines(){
        if (taskTrack.size() != 0){
            for (int i = 0; i < taskTrack.size(); i++)
                points.add(new LatLng(taskTrack.get(i).getLat(), taskTrack.get(i).getLng()));
        }
        Log.e("points", points.toString());

        addOverlays(points.get(0).latitude, points.get(0).longitude,mMarker);
        addOverlays(points.get(points.size()-1).latitude, points.get(points.size()-1).longitude,nav_Marker);
        //定位到起始点
        if (isFirstIn)
        {
            LatLng latLng = new LatLng(points.get(0).latitude, points.get(0).longitude);
            MapStatusUpdate msu1 = MapStatusUpdateFactory.newLatLng(latLng);
            mBaiduMap.animateMapStatus(msu1);
            isFirstIn = false;

            Toast.makeText(TrackMapActivity.this, "定位到起点位置",
                    Toast.LENGTH_SHORT).show();
        }
        //画线
        if (points.size() != 0){
            options = new PolylineOptions().color(0xAAFF0000).width(6)
                    .points(points);
            mBaiduMap.addOverlay(options);
        }
    }


    /**
     * 添加覆盖物
     */
    private void addOverlays(double latitude, double longitude,BitmapDescriptor myMarker)
    {
        //mBaiduMap.clear();
        LatLng latLng = null;
        Marker marker = null;
        OverlayOptions options;
        // 经纬度
        latLng = new LatLng(latitude, longitude);
        // 图标
        options = new MarkerOptions().position(latLng).icon(myMarker).zIndex(5);
        marker = (Marker) mBaiduMap.addOverlay(options);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(msu);
    }

    public void downloadClick() {
        String url = "http://172.27.35.1:8080/water-patrol/patrol/patrolMission/listJson";
        AsycHttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Gson gson = new Gson();
                PatrolJson patrolJson = gson.fromJson(response.toString(), PatrolJson.class);
                Log.e("patrolJson on TrackPlayBackActivity", patrolJson.toString());
                patrolRow = (ArrayList<PatrolRows>) patrolJson.getRows();
                if (position != -1) {
                    taskTrack = (ArrayList<Track>) patrolRow.get(position).getTrackList();
                    Log.e("taskTrack", taskTrack.toString());
                }
                addLines();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(TrackMapActivity.this, "可能未联网，加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
