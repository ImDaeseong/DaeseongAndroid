package com.daeseong.kakaomap_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mapView != null){
            ((ViewGroup)mapView.getParent()).removeView(mapView);
        }
    }

    private void init(){

        mapView = new MapView(this);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.cL1);

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.47856140136719 ,126.88128662109375), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(2, true);

        // 줌 인
        mapView.zoomIn(true);

        // 줌 아웃
        mapView.zoomOut(true);

        viewGroup.addView(mapView);
    }

}
