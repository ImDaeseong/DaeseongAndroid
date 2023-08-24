package com.daeseong.kakao_v2_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.share.model.SharingResult;
import com.kakao.sdk.talk.TalkApiClient;
import com.kakao.sdk.template.model.Content;
import com.kakao.sdk.template.model.DefaultTemplate;
import com.kakao.sdk.template.model.FeedTemplate;
import com.kakao.sdk.template.model.ItemContent;
import com.kakao.sdk.template.model.ItemInfo;
import com.kakao.sdk.template.model.Link;
import com.kakao.sdk.template.model.Social;
import com.kakao.sdk.template.model.TextTemplate;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.AccessTokenInfo;
import com.kakao.sdk.user.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import com.kakao.sdk.share.ShareClient;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHashKey();

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaologin();
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaologout();
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaoTokenInfo();
            }
        });

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaounReg();
            }
        });

        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaolink();
            }
        });

        button1.performClick();
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

        Function2<OAuthToken, Throwable, Unit> callback= new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                requestLogin();

                if (oAuthToken.getIdToken()!= null) {

                    Log.e(TAG, "getIdToken:" + oAuthToken.getIdToken());

                } else if(oAuthToken.getAccessToken()!=null) {

                    Log.e(TAG, "getAccessToken:" + oAuthToken.getAccessToken());
                }

                return null;
            }
        };

        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(this)) {

            Log.e(TAG, "kakao 설치");
            UserApiClient.getInstance().loginWithKakaoTalk(this, callback);

        } else {

            Log.e(TAG, "kakao 미설치");
            UserApiClient.getInstance().loginWithKakaoAccount(this, callback);
        }
    }

    private void requestLogin() {

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

        //https://developers.kakao.com/docs/latest/ko/getting-started/sdk-android
        //https://ddasi-live.tistory.com/100
        //https://github.com/han0gu/domaadoNew/blob/a452fcc14e5fa9b36d17e0a29a14c0fa35f2f9d0/app/src/main/java/com/domaado/mobileapp/share/KakaoTalklink.java#L135

        //https://kakao-tam.tistory.com/116
        //https://jangstory.tistory.com/105
        //https://pu1et-panggg.tistory.com/74

        //https://github.com/kookmin-sw/capstone-2022-21/blob/19f72361fe089a75819d48371849bd49d6073e0e/app/src/main/java/com/example/myapplication/KakaoService.java#L52
        //https://github.com/han0gu/domaadoNew/blob/a452fcc14e5fa9b36d17e0a29a14c0fa35f2f9d0/app/src/main/java/com/domaado/mobileapp/share/KakaoTalklink.java#L58


        if (!ShareClient.getInstance().isKakaoTalkSharingAvailable(this))
            return;

        TalkApiClient.getInstance().friends(((friendFriends, error) -> {
            if(error != null) {
                Log.e(TAG, "카카오톡 친구 목록 가져오기 실패:" + error);
            } else {
                if(friendFriends.getElements().isEmpty()) {
                    Log.e(TAG, "메시지를 보낼 수 있는 친구가 없습니다.");
                } else {

                    Log.e(TAG, friendFriends.toString());
                }
            }

            return null;
        }));

        DefaultTemplate defaulttxt = new TextTemplate("abc",new Link());
        TalkApiClient.getInstance().sendDefaultMemo(defaulttxt,(error) -> {

            Log.e(TAG, error.getMessage().toString());
            return Unit.INSTANCE;
        });

        /*
        //v1 에서  v2 변경된 내용
        KakaoLinkService  -> ShareClient
        KakaoTalkService  -> TalkApiClient
        */

        /*
        FeedTemplate feedTemplate = new FeedTemplate(
                new Content("제목1",
                        "https://files.picaplay.com/upload/kakao/kakao_share_600_main.jpg",
                        new Link("https://developers.kakao.com",
                                "https://developers.kakao.com"),
                        "테스트1"
                ),
                new ItemContent("제목2",
                        "https://files.picaplay.com/upload/kakao/kakao_share_600_main.jpg",
                        "이미지1",
                        "https://files.picaplay.com/upload/kakao/kakao_share_600_main.jpg",
                        "이미지2",
                        Arrays.asList(new ItemInfo("아이템", "1000원")),
                        "전체",
                        "15000원"
                ),
                new Social(286, 45, 845),
                Arrays.asList(new com.kakao.sdk.template.model.Button("웹으로 보기", new Link("https://developers.kakao.com", "https://developers.kakao.com")))
        );

        ShareClient.getInstance().shareDefault(this, feedTemplate, (SharingResult sharingResult, Throwable throwable) -> {
            if (throwable != null) {
                Log.e(TAG, "실패  : " + throwable + " ,  메세지   :  "  + throwable.getCause());
            } else if (sharingResult != null) {
                Log.e("TAG", String.valueOf(sharingResult.getIntent()));
                Log.e("TAG", "Warning Msg: " + sharingResult.getWarningMsg());
                Log.e("TAG", "Argument Msg: " + sharingResult.getArgumentMsg());
            }
            return null;
        });
        */

        /*
        String sUrl = "https://files.picaplay.com/upload/kakao/kakao_share_600_main.jpg";
        String sMsg = "[카카오]\n카카오 링크 메시지 테스트!";
        String sButton = "자세히 보기";
        String link = "";

        FeedTemplate feedTemplate2  = new FeedTemplate(
                new Content(
                        "sendPrefix",         // 타이틀
                        sUrl,             // 이미지
                        new Link("",      // web url
                                ""        // mobile web url
                        ),
                        ""         // description
                ),
                null,
                null,
                Collections.singletonList(new Button(sButton, new Link(link, link)))
        );

        ShareClient.getInstance().shareDefault(this, feedTemplate2, (SharingResult sharingResult, Throwable throwable) -> {
            if (throwable != null) {
                LogUtil.d(TAG, "실패  : " + throwable + " ,  메세지   :  "  + throwable.getCause());
            } else if (sharingResult != null) {
                activity.startActivity(sharingResult.getIntent());
                LogUtil.d("TAG", "Warning Msg: " + sharingResult.getWarningMsg());
                LogUtil.d("TAG", "Argument Msg: " + sharingResult.getArgumentMsg());
            }
            return null;
        });
        */

        /*
        ShareClient.getInstance().shareDefault(context, feedTemplate, ((sharingResult, error) -> {
            if(error!=null) {
                myLog.e(TAG, "*** Error: "+error.getMessage());
            } else if(sharingResult!=null) {
                myLog.d(TAG, "*** SUCCESS: "+sharingResult.getIntent());
                context.startActivity(sharingResult.getIntent());

                if(sharingResult.getWarningMsg().size()>0) {
                    for(Map.Entry<String, String> entry : sharingResult.getWarningMsg().entrySet()) {
                        myLog.w(TAG, "*** WarningMessage: " + entry.getKey()+" - "+entry.getValue());
                    }
                }

                if(sharingResult.getArgumentMsg().size()>0) {
                    for(Map.Entry<String, String> entry : sharingResult.getArgumentMsg().entrySet()) {
                        myLog.w(TAG, "*** ArgumentMessage: " + entry.getKey()+" - "+entry.getValue());
                    }
                }
            }
            return null;
        }));

        String sUrl = "http://files.picaplay.com/upload/kakao/kakao_share_600_main.jpg";
        String sMsg = "[카카오]\n카카오 링크 메시지 테스트!";


        Content content = new Content(title, sUrl, new Link(sUrl, sUrl), bodyText);

        ItemContent itemContent = new ItemContent();
        Social social = new Social();
        Button[] buttons = new Button[]{button};

        FeedTemplate feedTemplate = new FeedTemplate(content,
                new ItemContent(),
                new Social(),
                Arrays.asList(buttons));

         */


    }

}