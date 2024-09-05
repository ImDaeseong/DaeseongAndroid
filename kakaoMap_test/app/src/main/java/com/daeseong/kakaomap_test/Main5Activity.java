package com.daeseong.kakaomap_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

public class Main5Activity extends AppCompatActivity implements MapReverseGeoCoder.ReverseGeoCodingResultListener , MapView.MapViewEventListener{

    private static final String TAG = Main5Activity.class.getSimpleName();

    private MapView mapView;

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mapView = (MapView)findViewById(R.id.map_view);

        // 지도 이벤트 감지
        mapView.setMapViewEventListener(this);


        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MapPoint thisMapPoint = MapPoint.mapPointWithGeoCoord(mapView.getMapCenterPoint().getMapPointGeoCoord().latitude, mapView.getMapCenterPoint().getMapPointGeoCoord().longitude);
                MapReverseGeoCoder mapReverseGeoCoder = new MapReverseGeoCoder("251718aa4bdbfda74ec81a745aeb39a0", thisMapPoint, Main5Activity.this, Main5Activity.this);
                mapReverseGeoCoder.startFindingAddress();
            }
        });
    }

    //MapReverseGeoCoder.ReverseGeoCodingResultListener
    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {

        Log.d(TAG,  "onReverseGeoCoderFoundAddress address:" + s);
        Log.d(TAG,  "onReverseGeoCoderFoundAddress latitude:" + String.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().latitude));
        Log.d(TAG,  "onReverseGeoCoderFoundAddress longitude:" + String.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().longitude));
    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {

    }
    //MapReverseGeoCoder.ReverseGeoCodingResultListener


    //MapView.MapViewEventListener
    @Override
    public void onMapViewInitialized(MapView mapView) {

        Log.d(TAG,  "onMapViewInitialized");

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.47856140136719 ,126.88128662109375), true);
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        Log.d(TAG,  "onMapViewCenterPointMoved");
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        Log.d(TAG,  "onMapViewZoomLevelChanged");
    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        Log.d(TAG,  "onMapViewSingleTapped");
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        Log.d(TAG,  "onMapViewDoubleTapped");
    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        Log.d(TAG,  "onMapViewLongPressed");
    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        Log.d(TAG,  "onMapViewDragStarted");
    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        Log.d(TAG,  "onMapViewDragEnded");
    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        Log.d(TAG,  "onMapViewMoveFinished");
    }
    //MapView.MapViewEventListener
}
