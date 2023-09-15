package com.daeseong.rxjava3_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daeseong.rxjava3_test.Common.SendUtil;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main6Activity.class.getSimpleName();

    private Button button1;

    private TextView textView1;

    private String sLocalIP;

    private SendUtil sendUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        sendUtil = new SendUtil();

        setLocalIP();

        textView1 = (TextView)findViewById(R.id.textView1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sMsg = textView1.getText().toString();
                if(TextUtils.isEmpty(sMsg)) return;

                //sendUtil = new SendUtil();
                sendUtil.SendMessage(sLocalIP, 10000, sMsg)
                        .subscribeOn(Schedulers.io())
                        .onErrorComplete()
                        .subscribe(bResult -> {
                            try{
                                Log.e(TAG, "result:" + bResult);
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        });
            }
        });

    }

    private void setLocalIP(){

        try {
            String sIP = getLocalIpAddress();
            String[] sArray = sIP.split("\\.");
            sLocalIP = sArray[0] + "." + sArray[1] + "." + sArray[2] + ".2";
            //Log.e(TAG, "sLocalIP: " + sLocalIP);
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}