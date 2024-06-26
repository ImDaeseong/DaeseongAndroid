package com.daeseong.networkcheck_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tv1;

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);

        // 네트워크 변경 감지 리시버 등록
        networkChangeReceiver = registerNetworkReceiver(this, new OnNetworkChangeListener() {
            @Override
            public void onNetworkChange(boolean isConnected) {

                if (isConnected) {

                    int rssi = getWifiRssi(MainActivity.this);
                    int signalLevel = getWifiSignalLevel(MainActivity.this);
                    boolean isCommunicationPoor = rssi < -70;

                    /*
                    -30dBm ~ -50dBm: 매우 강한 신호
                    -51dBm ~ -60dBm: 강한 신호
                    -61dBm ~ -70dBm: 중간 신호
                    -71dBm ~ -80dBm: 약한 신호
                    -81dBm ~ -90dBm: 매우 약한 신호
                    */

                    if (isCommunicationPoor) {
                        tv1.setText("통신 불량 가능성 있음, RSSI 값: " + rssi);
                    } else {
                        tv1.setText("통신 양호, RSSI 값: " + rssi);
                    }
                } else {
                    tv1.setText("네트워크 연결 끊김");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 네트워크 변경 감지 리시버 해제
        unregisterNetworkReceiver(this, networkChangeReceiver);
    }

    // 네트워크 변경 감지 리시버 등록 함수
    private NetworkChangeReceiver registerNetworkReceiver(Context context, OnNetworkChangeListener listener) {
        NetworkChangeReceiver receiver = new NetworkChangeReceiver(listener);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, filter);
        return receiver;
    }

    // 네트워크 변경 감지 리시버 해제 함수
    private void unregisterNetworkReceiver(Context context, NetworkChangeReceiver receiver) {
        context.unregisterReceiver(receiver);
    }

    // 네트워크 상태 체크 함수
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            android.net.Network network = connectivityManager.getActiveNetwork();
            if (network == null) return false;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

    // Wi-Fi RSSI 값 확인 함수
    private int getWifiRssi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getRssi();
    }

    // Wi-Fi 신호 강도 수준 확인 함수 (0에서 4 사이)
    private int getWifiSignalLevel(Context context) {
        int rssi = getWifiRssi(context);
        return WifiManager.calculateSignalLevel(rssi, 5);
    }

    // 네트워크 변경 감지 리시버 클래스
    public class NetworkChangeReceiver extends BroadcastReceiver {
        private OnNetworkChangeListener onNetworkChangeListener;

        public NetworkChangeReceiver(OnNetworkChangeListener listener) {
            this.onNetworkChangeListener = listener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
            onNetworkChangeListener.onNetworkChange(isConnected);
        }
    }

    // 네트워크 변경 감지 리스너 인터페이스
    public interface OnNetworkChangeListener {
        void onNetworkChange(boolean isConnected);
    }
}