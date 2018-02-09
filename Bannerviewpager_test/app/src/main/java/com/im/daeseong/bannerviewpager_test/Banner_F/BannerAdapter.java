package com.im.daeseong.bannerviewpager_test.Banner_F;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.im.daeseong.bannerviewpager_test.R;

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private int[] pics;

    public BannerAdapter(Context context, int[] pics) {

        this.context = context;
        this.pics = pics;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = (position) % getRealCount();
        View itemView = LayoutInflater.from(context).inflate(R.layout.widget_banner_f_viewitem, container, false);
        ImageView ivBanner = (ImageView) itemView.findViewById(R.id.ivBanner);
        ivBanner.setImageResource(pics[realPosition]);

        /*
        Bitmap bitmapdownload = null;
        try {
            bitmapdownload = new BannerdisplayImage().execute(imgUrl).get();
            ivBanner.setImageBitmap(bitmapdownload);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(bitmapdownload == null)
            ivBanner.setImageResource(R.drawable.number1);
        */

        container.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public int getCount() {
        return pics.length;//displayList.size();
    }

    public int getRealCount() {
        return pics.length;//list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    private class BannerdisplayImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = DownLoadImage( params[0]);
            return bitmap;
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
