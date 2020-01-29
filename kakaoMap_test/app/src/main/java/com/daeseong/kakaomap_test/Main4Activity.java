package com.daeseong.kakaomap_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Main4Activity extends AppCompatActivity implements MapView.POIItemEventListener{

    private static final String TAG = Main4Activity.class.getSimpleName();

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mapView = (MapView)findViewById(R.id.map_view);

        // 마크 이벤트 감지
        mapView.setPOIItemEventListener(this);

        addMark();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mapView.removeAllPOIItems();
    }

    private void addMark(){

        //마크 전체 삭제
        mapView.removeAllPOIItems();

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(37.47856140136719 ,126.88128662109375);

        //마크 추가
        MapPOIItem item = new MapPOIItem();
        item.setItemName("현재 위치");
        item.setTag(0);
        item.setMapPoint(mapPoint);
        item.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양
        item.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
        mapView.addPOIItem(item);

        //선택된 아이템 설정
        mapView.selectPOIItem(item, true);

        // 첫번째 마커의 위치가 지도의 가운데로 오도록 지도를 이동시킴
        mapView.setMapCenterPoint(mapPoint, true);
    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

        String name = mapPOIItem.getItemName();
        MapPoint mapPoint = mapPOIItem.getMapPoint();
        Log.d(TAG,  "name: " + name + " latitude: " + mapPoint.getMapPointGeoCoord().latitude + " longitude: " + mapPoint.getMapPointGeoCoord().longitude);
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

        String name = mapPOIItem.getItemName();
        MapPoint mapPoint = mapPOIItem.getMapPoint();
        Log.d(TAG,  "name: " + name + " latitude: " + mapPoint.getMapPointGeoCoord().latitude + " longitude: " + mapPoint.getMapPointGeoCoord().longitude);
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
    }

}
