package com.daeseong.kakaomap_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Main3Activity extends AppCompatActivity implements MapView.CurrentLocationEventListener {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private MapView mapView;
    private MapPoint.GeoCoordinate center = new MapPoint.GeoCoordinate(0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        try {

            mapView = (MapView) findViewById(R.id.map_view);

            // 현재 위치 이벤트 감지
            mapView.setCurrentLocationEventListener(this);
            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            setContentView(mapView);

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint currentPoint, float accuracyInMeters) {

        double latitude = currentPoint.getMapPointGeoCoord().latitude;
        double longitude = currentPoint.getMapPointGeoCoord().longitude;
        center.latitude = latitude;
        center.longitude = longitude;

        Log.d(TAG,  "latitude: " + Double.toString(center.latitude) + " longitude: " + Double.toString(center.longitude));
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
        Log.d(TAG,  "onCurrentLocationDeviceHeadingUpdate");
    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        Log.d(TAG,  "onCurrentLocationUpdateFailed");
    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        Log.d(TAG,  "onCurrentLocationUpdateCancelled");
    }

}
