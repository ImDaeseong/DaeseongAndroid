package com.daeseong.volley_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MainActivity4 extends AppCompatActivity {

    private  static final String TAG = MainActivity4.class.getSimpleName();

    private Button button1, button2;
    private TextView tv1, tv2;

    private GetRequestData getRequestData;

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
        setContentView(R.layout.activity_main4);

        getRequestData = GetRequestData.getInstance(this);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setMovementMethod(new ScrollingMovementMethod());

        tv2 = (TextView)findViewById(R.id.tv2);
        tv2.setMovementMethod(new ScrollingMovementMethod());

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText("");
                String sUrl = String.format("%sdobType=%s&dsptcKsco=%s&continent=%s&showItemListCount=%s&sepmt61=%s", sPage, sdobType, sdsptcKsco, scontinent, sshowItemListCount, sepmt61);
                getRequestData.getData(sUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        tv1.setText(response);

                        int nTop = tv1.getLayout().getLineTop(tv1.getLineCount());
                        int nScrollY = nTop - tv1.getHeight();
                        if(nScrollY > 0){
                            tv1.scrollTo(0, nScrollY);
                        }else {
                            tv1.scrollTo(0, 0);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(TAG, "getData onErrorResponse: " + error.getMessage().toString());
                    }
                });
            }
        });


        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv2.setText("");
                String sUrl = sPage;
                getRequestData.getPostData(sUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        tv2.setText(response);

                        int nTop = tv2.getLayout().getLineTop(tv2.getLineCount());
                        int nScrollY = nTop - tv2.getHeight();
                        if(nScrollY > 0){
                            tv2.scrollTo(0, nScrollY);
                        }else {
                            tv2.scrollTo(0, 0);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(TAG, "getPostData onErrorResponse: " + error.getMessage().toString());
                    }
                }, sdobType, sdsptcKsco, scontinent, sshowItemListCount, sepmt61);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getRequestData.clear();
    }
}