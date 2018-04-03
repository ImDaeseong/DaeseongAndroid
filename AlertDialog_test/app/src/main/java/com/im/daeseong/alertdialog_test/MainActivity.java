package com.im.daeseong.alertdialog_test;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new Alert1Dialog(MainActivity.this);
                builder.setTitle("제목");

                builder
                        .setMessage("공지사항 내용표현\n공지사항 내용표현")
                        .setCancelable(false)
                        .setPositiveButton("종료",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 프로그램을 종료
                                        //MainActivity.this.finish();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소
                                        //dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                //btnNegative.setBackgroundColor(Color.MAGENTA);
                btnNegative.setTextColor(Color.BLACK);

                Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                //btnPositive.setBackgroundColor(Color.YELLOW);
                btnPositive.setTextColor(Color.BLACK);

            }
        });



        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme1);
                builder.setTitle("title");
                builder
                        .setMessage("공지사항 내용표현\n공지사항 내용표현")
                        .setCancelable(false)
                        .setPositiveButton("종료",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 프로그램을 종료
                                        //MainActivity.this.finish();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소
                                        //dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                //btnNegative.setBackgroundColor(Color.MAGENTA);
                btnNegative.setTextColor(Color.BLACK);

                Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                //btnPositive.setBackgroundColor(Color.YELLOW);
                btnPositive.setTextColor(Color.BLACK);
            }
        });



        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme1);
                builder.setTitle("title");
                builder
                        .setMessage("공지사항 내용표현\n공지사항 내용표현")
                        .setCancelable(false)
                        .setPositiveButton("종료",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 프로그램을 종료
                                        //MainActivity.this.finish();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                //btnPositive.setBackgroundColor(Color.YELLOW);
                btnPositive.setTextColor(Color.BLACK);

            }
        });


        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme1);
                builder.setTitle(Html.fromHtml("<font color='#FF7F27'>제목</font>"));
                builder
                        .setMessage(Html.fromHtml("<font color='#FF7F27'>공지사항 내용표현<br>공지사항 내용표현</font>"))
                        .setCancelable(false)
                        .setPositiveButton("종료",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 프로그램을 종료
                                        //MainActivity.this.finish();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소
                                        //dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                //btnNegative.setBackgroundColor(Color.MAGENTA);
                btnNegative.setTextColor(Color.BLACK);

                Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                //btnPositive.setBackgroundColor(Color.YELLOW);
                btnPositive.setTextColor(Color.BLACK);

            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
