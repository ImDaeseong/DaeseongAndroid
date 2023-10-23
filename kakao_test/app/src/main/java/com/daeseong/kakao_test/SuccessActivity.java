package com.daeseong.kakao_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;

public class SuccessActivity extends AppCompatActivity {

    private static final String TAG = SuccessActivity.class.getSimpleName();

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {

                        Log.e(TAG,"onCompleteLogout:" + "정상적으로 로그아웃되었습니다.");

                        Intent intent = new Intent(SuccessActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                });
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaolink();
            }
        });
    }

    private void kakaolink() {

        String imgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
        String title = "[나의앱]\n나의앱 제목!";
        String desc = "나의앱에 대한 설명과 링크 정보:\nhttps://m.naver.com";

        try {

            FeedTemplate params = FeedTemplate
                    .newBuilder(ContentObject.newBuilder(title, imgUrl, LinkObject.newBuilder()
                                    .setWebUrl("").setMobileWebUrl("").build())
                            .setDescrption(desc)
                            .build())
                    .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                            .setAndroidExecutionParams("key1=value1")
                            .setIosExecutionParams("key1=value1").build()))
                    .build();

            KakaoLinkService.getInstance().sendDefault(SuccessActivity.this, params, new ResponseCallback<KakaoLinkResponse>() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    Log.e(TAG, errorResult.toString());
                }

                @Override
                public void onSuccess(KakaoLinkResponse result) {
                    Log.e(TAG, result.toString());
                }
            });

        } catch (KakaoException ex) {
        } catch ( Exception ex) {
        }
    }

}
