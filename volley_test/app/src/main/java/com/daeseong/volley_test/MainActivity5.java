package com.daeseong.volley_test;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {

    private  static final String TAG = MainActivity5.class.getSimpleName();

    private Button button1, button2;
    private TextView tv1, tv2;
    private ProgressDialog progressDialog;

    //호출 정보
    private String sPage = "http://www.worldjob.or.kr/openapi/openapi.do?"; ////https://www.data.go.kr/dataset/3038249/openapi.do
    private String sdobType = "1";                                          //1:해외취업,2:해외연수,3:해외인턴,4:해외봉사,5:해외창업
    private String sdsptcKsco = "01";                                       //직종별코드(해외취업,연수만 사용)01:전산,컴퓨터,02:전기/전자,06:기계/금속,07:건설/토목,08:사무/서비스,09:의료,10:기타
    private String scontinent = "1";                                        //대륙별코드 1:아시아,2:북아메리카, 3:남아메리카,4:유럽,5:오세아니아,6:아프리카
    private String sepmt61 = "Y";                                           //일자리Best20(해외취업만 사용)Y,N
    private String sshowItemListCount = "1000"; //한번에보여질리스트갯수출력결과

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setMovementMethod(new ScrollingMovementMethod());

        tv2 = (TextView)findViewById(R.id.tv2);
        tv2.setMovementMethod(new ScrollingMovementMethod());

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText("");
                progressDialog = ProgressDialog.show(MainActivity5.this, "volleygetdata", "volleygetdata.getData", true);
                String sUrl = String.format("%sdobType=%s&dsptcKsco=%s&continent=%s&showItemListCount=%s&sepmt61=%s", sPage, sdobType, sdsptcKsco, scontinent, sshowItemListCount, sepmt61);
                volleygetdata.getData(sUrl, MainActivity5.this, progressDialog, tv1);
            }
        });


        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv2.setText("");
                progressDialog = ProgressDialog.show(MainActivity5.this, "volleypostdata", "volleypostdata.getPostData", true);
                String sUrl = sPage;
                volleypostdata.getPostData(sUrl, MainActivity5.this, progressDialog, tv2, sdobType, sdsptcKsco, scontinent, sshowItemListCount, sepmt61);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MyApplication.getRequestQueue().cancelAll("getData");
        MyApplication.getRequestQueue().cancelAll("getPostData");
    }
}