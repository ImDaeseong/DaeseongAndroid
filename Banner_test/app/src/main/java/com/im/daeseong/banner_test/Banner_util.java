package com.im.daeseong.banner_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Banner_util {

    private static final String TAG = Banner_util.class.getName();

    private static Banner_util instance;
    public static Banner_util getInstance(){
        if( instance == null){
            instance = new Banner_util();
        }
        return instance;
    }

    private List<RowItem> BannerItem1;
    private boolean CompleteBannerItem1;

    public void setBanner1(){

        String url1 = "https://.png";
        String url2 = "https://.png";
        String url3 = "https://.png";
        String url4 = "https://.png";
        String url5 = "https://.png";
        String url6 = "https://.png";
        String url7 = "https://.png";
        String url8 = "https://.png";
        String url9 = "https://.png";
        String url10 = "https://.png";
        String url11 = "https://.png";
        String url12 = "https://.png";
        String url13 = "https://.png";
        String url14 = "https://.png";
        String url15 = "https://.png";
        String url16 = "https://.png";
        String url17 = "https://.png";
        String url18 = "https://.png";
        String url19 = "https://.png";
        String url20 = "https://.png";
        new Banner1Task().execute(new String[]{url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12, url13, url14, url15, url16, url17, url18, url19, url20});
    }

    public List<RowItem> getBanner1(){
        return BannerItem1;
    }

    public boolean getCompleteBannerItem1(){
        return CompleteBannerItem1;
    }

    private class Banner1Task extends AsyncTask<String, Integer, List<RowItem> > {

        @Override
        protected void onPostExecute(List<RowItem> rowItems) {
            CompleteBannerItem1 = true;
            //Log.e(TAG, "onPostExecute");
        }

        @Override
        protected List<RowItem> doInBackground(String... params) {
            CompleteBannerItem1 = false;
            Bitmap bitmap = null;
            BannerItem1 = new ArrayList<RowItem>();

            for(String url : params){

                //Log.e(TAG, "getExt:" + String_util.getExt(url) );
                //Log.e(TAG, "getImageName:" + String_util.getImageName(url) );
                //Log.e(TAG, "getUpper:" + String_util.getUpper(String_util.getExt(url)) );
                //Log.e(TAG, "getLower:" + String_util.getLower(String_util.getExt(url)) );
                //Log.e(TAG, "getMoneyDecimalFormat:" +  String_util.getMoneyDecimalFormat("100000"));
                //Log.e(TAG, "getrandomUUID:" +  String_util.getrandomUUID());
                //Log.e(TAG, "sTime:" +  String_util.getTime());
                //Log.e(TAG, "sTime1:" +  String_util.getDate(String_util.getTime(), "yyyy-MM-dd hh:mm:ss"));
                //Log.e(TAG, "isExpired:" +  String_util.isExpired("2018-02-03"));
                //Log.e(TAG, "removeQuoted:" + String_util.removeQuoted("\"test\""));
                //Log.e(TAG, "isPhoneNo:" + String_util.isPhoneNo("010-1234-5678"));
                //Log.e(TAG, "isEmail:" + String_util.isEmail("test@test.co"));
                //Log.e(TAG, "isNumeric:" + String_util.isNumeric("101030305"));
                //Log.e(TAG, "isNumericInc:" + String_util.isNumericInc("ABD-3030-A"));
                //Log.e(TAG, "getBannerIndex:" + String_util.getBannerIndex());
                //String[] result =  String_util.split("test1;test2;test3", ";");
                //for(int i=0; i <result.length; i++){
                //    Log.e(TAG, "split:" + result[i]);
                //}

                bitmap = DownLoadImage(url);

                //오류로 인해 이미지를 받아오지 못할 경우 가지고 있는 리소스를 이용한다.
                if(bitmap == null){
                    String sImg = String_util.getImageName(url);
                    if(sImg.equals("logo.png"))
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number2);
                    else
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.number1);
                }
                BannerItem1.add(new RowItem(bitmap));
            }
            return BannerItem1;
        }

        private Bitmap DownLoadImage(String sUrl) {

            String urlImage = sUrl;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            Bitmap bitmap = null;
            try{
                URL url = new URL(urlImage);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setAllowUserInteraction(false);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                int resCode = httpURLConnection.getResponseCode();
                if(resCode != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                inputStream = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                httpURLConnection.disconnect();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(inputStream != null){
                    try{
                        inputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if(bufferedReader != null){
                    try{
                        bufferedReader.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if(httpURLConnection != null){
                    try{
                        httpURLConnection.disconnect();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }
    }

}
