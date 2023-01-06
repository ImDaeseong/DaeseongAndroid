package com.im.daeseong.gridlayout_test;

import android.graphics.Point;
import com.google.android.material.floatingactionbutton.FloatingActionButton;//import android.support.design.widget.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class GridLayout2Activity extends AppCompatActivity {

    private final int numRows = 20;
    private final int numCols = 11;

    private ScrollView scrollView;
    private GridLayout gridLayout;
    private FloatingActionButton floatingActionButton;

    String[] AlpabatList = new String[]{
            "", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ" ,
            "ㄱ", "가", "갸", "거", "겨", "고", "교", "구", "규", "그", "기" ,
            "ㄴ", "나", "냐", "너", "녀", "노", "뇨", "누", "뉴", "느", "니" ,
            "ㄷ", "다", "댜", "더", "뎌", "도", "됴", "두", "듀", "드", "디" ,
            "ㄹ", "라", "랴", "러", "려", "로", "료", "루", "류", "르", "리" ,
            "ㅁ", "마", "먀", "머", "며", "모", "묘", "무", "뮤", "므", "미" ,
            "ㅂ", "바", "뱌", "버", "벼", "보", "뵤", "부", "뷰", "브", "비" ,
            "ㅅ", "사", "샤", "서", "셔", "소", "쇼", "수", "슈", "스", "시" ,
            "ㅇ", "아", "야", "어", "여", "오", "요", "우", "유", "으", "이" ,
            "ㅈ", "자", "쟈", "저", "져", "조", "죠", "주", "쥬", "즈", "지" ,
            "ㅊ", "차", "챠", "처", "쳐", "초", "쵸", "추", "츄", "츠", "치" ,
            "ㅋ", "카", "캬", "커", "켜", "코", "쿄", "쿠", "큐", "크", "키" ,
            "ㅌ", "타", "탸", "터", "텨", "토", "툐", "투", "튜", "트", "티" ,
            "ㅍ", "파", "퍄", "퍼", "펴", "포", "표", "푸", "퓨", "프", "피" ,
            "ㅎ", "하", "햐", "허", "혀", "호", "효", "후", "휴", "흐", "히" ,
            "ㄲ", "까", "꺄", "꺼", "껴", "꼬", "꾜", "꾸", "뀨", "끄", "끼" ,
            "ㄸ", "따", "땨", "떠", "뗘", "또", "뚀", "뚜", "뜌", "뜨", "띠" ,
            "ㅃ", "빠", "뺘", "뻐", "뼈", "뽀", "뾰", "뿌", "쀼", "쁘", "삐" ,
            "ㅆ", "싸", "쌰", "써", "쎠", "쏘", "쑈", "쑤", "쓔", "쓰", "씨" ,
            "ㅉ", "짜", "쨔", "쩌", "쪄", "쪼", "쬬", "쭈", "쮸", "쯔", "찌"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout2);

        scrollView = (ScrollView)findViewById(R.id.sv1);
        gridLayout = (GridLayout)findViewById(R.id.gl1);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.scrollTo(0,0);
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(scrollView.getScrollY() > 0){
                    floatingActionButton.setVisibility(View.VISIBLE);
                }else {
                    floatingActionButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        loadData();
    }

    private void loadData(){

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        gridLayout.setColumnCount(11);
        gridLayout.setRowCount(20);

        for(int i=0; i < AlpabatList.length; i++) {

            GridItem item = new GridItem(this);
            item.setText(AlpabatList[i]);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sText = ((GridItem)view).getText();
                    if(!TextUtils.isEmpty(sText)){
                        Toast.makeText(GridLayout2Activity.this, sText, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            gridLayout.addView(item, (screenWidth / numCols), 60);
        }
    }

}
