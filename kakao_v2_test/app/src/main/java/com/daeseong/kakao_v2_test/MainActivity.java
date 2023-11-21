package com.daeseong.kakao_v2_test;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.link.LinkClient;
import com.kakao.sdk.talk.TalkApiClient;
import com.kakao.sdk.template.model.Content;
import com.kakao.sdk.template.model.FeedTemplate;
import com.kakao.sdk.template.model.ItemContent;
import com.kakao.sdk.template.model.Link;
import com.kakao.sdk.template.model.Social;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.AccessTokenInfo;
import com.kakao.sdk.user.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import com.kakao.sdk.share.ShareClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3, button4, button5;

    private final Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
        @Override
        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

            try {

                if (oAuthToken.getIdToken() != null) {

                    Log.e(TAG, "getIdToken:" + oAuthToken.getIdToken());

                } else if (oAuthToken.getAccessToken() != null) {

                    Log.e(TAG, "getAccessToken:" + oAuthToken.getAccessToken());
                }

                LoginInfo();

            } catch (Exception e) {
                Log.e(TAG, e.getMessage().toString());
            }

            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHashKey();

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //카카오 미설치시 웹으로 로그인
                kakaologin();
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(MainActivity.this)) {
                    Kakaoinstall(MainActivity.this);
                    return;
                }

                kakaologout();
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(MainActivity.this)) {
                    Kakaoinstall(MainActivity.this);
                    return;
                }

                kakaoTokenInfo();
            }
        });

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(MainActivity.this)) {
                    Kakaoinstall(MainActivity.this);
                    return;
                }

                kakaounReg();
            }
        });

        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(MainActivity.this)) {
                    Kakaoinstall(MainActivity.this);
                    return;
                }

                kakaolink();
                //kakaolink1();
                //kakaolink2();
            }
        });

        if (getIntent() != null && getIntent().getData() != null) {

            Uri uri = getIntent().getData();
            if( uri != null ) {

                String sURi = uri.toString().toLowerCase();
                if (sURi.contains("kakaolink")) {
                    String sType1 = uri.getQueryParameter("key1");
                    if(sType1 != null){
                        Log.e(TAG, "key1:" + sType1);
                    }

                    String sType2 = uri.getQueryParameter("key2");
                    if(sType2 != null){
                        Log.e(TAG, "key2:" +sType2);
                    }
                }
            }
        }

    }

    private void getHashKey() {

        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException ex) {
        }

        for (Signature signature : packageInfo.signatures) {

            try {

                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.e(TAG, "haskey:"+ Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));

            } catch (NoSuchAlgorithmException ex) {
            }
        }
    }

    //카카오 로그인
    private void kakaologin() {

        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(this)) {

            Log.e(TAG, "kakao 설치");
            UserApiClient.getInstance().loginWithKakaoTalk(this, callback);

        } else {

            Log.e(TAG, "kakao 미설치");
            UserApiClient.getInstance().loginWithKakaoAccount(this, callback);
        }
    }

    private void LoginInfo() {

        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {

                if (user != null) {

                    try {

                        Log.e(TAG, "getId : " + user.getId());
                        //Log.e(TAG, "getNickname : " + user.getKakaoAccount().getProfile().getNickname());
                        //Log.e(TAG, "getEmail : " + user.getKakaoAccount().getEmail());
                        //Log.e(TAG, "getGender : " + user.getKakaoAccount().getGender());
                        //Log.e(TAG, "getAgeRange : " + user.getKakaoAccount().getAgeRange());
                        //Log.e(TAG, "getProfileImageUrl : " + user.getKakaoAccount().getProfile().getProfileImageUrl());

                        Log.e(TAG, "카카오 로그인 성공");

                    } catch (Exception ex) {
                      Log.e(TAG, ex.getMessage().toString());
                    }

                } else {

                    Log.e(TAG, "카카오 로그인 실패: ", throwable);
                }

                return null;
            }
        });
    }

    //카카오 로그아웃
    private void kakaologout() {

        UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
            @Override
            public Unit invoke(Throwable throwable) {

                if (throwable != null) {

                    Log.e(TAG, "kakao 로그아웃 실패");

                } else {

                    Log.e(TAG, "kakao 로그아웃 성공");
                }
                return null;
            }
        });
    }

    //카카오 토큰 정보
    private void kakaoTokenInfo() {

        UserApiClient.getInstance().accessTokenInfo(new Function2<AccessTokenInfo, Throwable, Unit>() {
            @Override
            public Unit invoke(AccessTokenInfo accessTokenInfo, Throwable throwable) {

                if (throwable != null) {

                    Log.e(TAG, "kakao 토큰 정보 호출 실패");

                } else if(accessTokenInfo != null) {

                    Log.e(TAG, "getId:" + accessTokenInfo.getId());
                    Log.e(TAG, "만료시간:" + accessTokenInfo.getExpiresIn());

                }
                return null;
            }
        });
    }

    //카카오 탈퇴
    private void kakaounReg() {

        UserApiClient.getInstance().unlink(new Function1<Throwable, Unit>() {
            @Override
            public Unit invoke(Throwable throwable) {

                if (throwable != null) {

                    Log.e(TAG, "kakao 토큰 회원탈퇴 실패");

                } else {

                    Log.e(TAG, "kakao 토큰 회원탈퇴 성공");
                }

                return null;
            }
        });
    }

    private void kakaolink() {

        //v1 에서  v2 변경된 내용
        //KakaoLinkService  -> ShareClient
        //KakaoTalkService  -> TalkApiClient

        if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(this))
            return;

        String imgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
        String title = "[나의앱]\n나의앱 제목!";
        String desc = "나의앱에 대한 설명과 링크 정보:\nhttps://m.naver.com";

        //링크에 파라미터 전달 여부
        boolean blinkparam = true;

        Map<String, String> param1 = new HashMap<>();
        param1.put("key1", "value1");
        param1.put("key2", "value2");
        Link link = new Link(null, null, param1, null);

        ItemContent itemContent = new ItemContent();
        Social social = new Social();

        Content content;
        com.kakao.sdk.template.model.Button button;

        if (blinkparam) {
            content = new Content(title, imgUrl, link, desc);
            button = new com.kakao.sdk.template.model.Button("앱에서 보기", link);
        } else {
            content = new Content(title, imgUrl, new Link(), desc);
            button = new com.kakao.sdk.template.model.Button("앱에서 보기", new Link());
        }

        com.kakao.sdk.template.model.Button[] buttons = new com.kakao.sdk.template.model.Button[] { button };
        FeedTemplate feedTemplate = new FeedTemplate(content, itemContent, social, Arrays.asList(buttons));

        ShareClient.getInstance().shareDefault(this, feedTemplate, ((sharingResult, error) -> {

            if (error != null) {
                Log.e(TAG, "카카오톡 공유 실패:" + error);
            } else if (sharingResult != null) {
                startActivity(sharingResult.getIntent());
                Log.e(TAG, String.valueOf(sharingResult.getWarningMsg()));
                Log.e(TAG, String.valueOf(sharingResult.getArgumentMsg()));
                Log.e(TAG, String.valueOf(sharingResult.getWarningMsg().size()));
            }
            return null;
        }));

    }

    private void kakaolink1() {

        //v1 에서  v2 변경된 내용
        //KakaoLinkService  -> ShareClient
        //KakaoTalkService  -> TalkApiClient

        if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(this))
            return;

        String imgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
        String title = "[나의앱]\n나의앱 제목!";
        String desc = "나의앱에 대한 설명과 링크 정보:\nhttps://m.naver.com";

        //링크에 파라미터 전달 여부
        boolean blinkparam = true;

        Map<String, String> param1 = new HashMap<>();
        param1.put("key1", "value1");
        param1.put("key2", "value2");
        Link link = new Link(null, null, param1, null);

        ItemContent itemContent = new ItemContent();
        Social social = new Social(0,0);

        Content content;
        com.kakao.sdk.template.model.Button Button1;
        com.kakao.sdk.template.model.Button Button2;

        if (blinkparam) {
            content = new Content(title, imgUrl, link, desc);
            Button1 = new com.kakao.sdk.template.model.Button("앱에서 보기", link);
            Button2 = new com.kakao.sdk.template.model.Button("앱에서 보기1", link);
        } else {
            content = new Content(title, imgUrl, new Link(), desc);
            Button1 = new com.kakao.sdk.template.model.Button("앱에서 보기", new Link(null, null, null, null));
            Button2 = new com.kakao.sdk.template.model.Button("앱에서 보기1", new Link(null, null, null, null));
        }

        com.kakao.sdk.template.model.Button[] buttons = new com.kakao.sdk.template.model.Button[] { Button1, Button2 };
        FeedTemplate feedTemplate = new FeedTemplate(content, itemContent, social, Arrays.asList(buttons));

        ShareClient.getInstance().shareDefault(this, feedTemplate, ((sharingResult, error) -> {

            if (error != null) {
                Log.e(TAG, "카카오톡 공유 실패:" + error);
            } else if (sharingResult != null) {
                startActivity(sharingResult.getIntent());
                Log.e(TAG, String.valueOf(sharingResult.getWarningMsg()));
                Log.e(TAG, String.valueOf(sharingResult.getArgumentMsg()));
                Log.e(TAG, String.valueOf(sharingResult.getWarningMsg().size()));
            }
            return null;
        }));
    }

    private void kakaolink2() {

        //v1 에서  v2 변경된 내용
        //KakaoLinkService  -> ShareClient
        //KakaoTalkService  -> TalkApiClient

        if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(this))
            return;

        String imgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
        String title = "[나의앱]\n나의앱 제목!";
        String desc = "나의앱에 대한 설명과 링크 정보:\nhttps://m.naver.com";

        //링크에 파라미터 전달 여부
        boolean blinkparam = true;

        Map<String, String> param1 = new HashMap<>();
        param1.put("key1", "https://m.naver.com");
        param1.put("key2", "http://m.naver.com");
        Link link = new Link(null, null, param1, null);

        ItemContent itemContent = new ItemContent();
        Social social = new Social(0,0);

        Content content;

        if (blinkparam) {
            content = new Content(title, imgUrl, link, desc);
        } else {
            content = new Content(title, imgUrl, new Link(), desc);
        }

        FeedTemplate feedTemplate = new FeedTemplate(content, itemContent, social);

        ShareClient.getInstance().shareDefault(this, feedTemplate, ((sharingResult, error) -> {

            if (error != null) {
                Log.e(TAG, "카카오톡 공유 실패:" + error);
            } else if (sharingResult != null) {
                startActivity(sharingResult.getIntent());
                Log.e(TAG, String.valueOf(sharingResult.getWarningMsg()));
                Log.e(TAG, String.valueOf(sharingResult.getArgumentMsg()));
                Log.e(TAG, String.valueOf(sharingResult.getWarningMsg().size()));
            }
            return null;
        }));
    }

    private void Kakaoinstall(Activity activity) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + "com.kakao.talk"));
        activity.startActivity(intent);
    }
}