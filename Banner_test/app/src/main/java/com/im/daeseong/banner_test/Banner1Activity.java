package com.im.daeseong.banner_test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Banner1Activity extends AppCompatActivity {

    private static final String TAG = Banner1Activity.class.getSimpleName();

    private long lastTimeBackPressed;

    private ListView listView1;
    private Button button1;
    private ListViewAdapter listViewAdapter;
    private Banner1Task banner1Task;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner1);

        listView1 = (ListView)findViewById(R.id.listView1);
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banner1Task = new Banner1Task(Banner1Activity.this);
                banner1Task.execute(new String[]{url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12, url13, url14, url15, url16, url17, url18, url19, url20});
            }
        });
    }

    private class Banner1Task extends AsyncTask<String, Integer, List<RowItem> > {
        private ProgressDialog progressDialog;
        private  Activity activity;
        private List<RowItem> rowItems;
        private int nTotalCount;

        public Banner1Task(Activity activity){
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setTitle("setTitle");
            progressDialog.setMessage("setMessage");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setIcon(R.drawable.number1);
            progressDialog.setCancelable(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                    Log.e(TAG, "AsyncTask onCancelled");

                    banner1Task.cancel(true);

                    Banner1Activity.this.finish();
                }
            });
            progressDialog.show();
        }

        @Override
        protected List<RowItem> doInBackground(String... params) {

            nTotalCount = params.length;
            Bitmap bitmap = null;
            rowItems = new ArrayList<RowItem>();

            for(String url : params){
                bitmap = DownLoadImage(url);

                if(bitmap == null)continue;

                rowItems.add(new RowItem(bitmap));

                if(isCancelled()){
                    Log.e(TAG, "doInBackground isCancelled");
                    break;
                }
            }
            return rowItems;
        }

        @Override
        protected void onPostExecute(List<RowItem> rowItems) {

            listViewAdapter = new ListViewAdapter(activity, rowItems);
            listView1.setAdapter(listViewAdapter);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            progressDialog.setProgress(values[0]);
            if (rowItems != null) {
                progressDialog.setMessage("Loading " + (rowItems.size() + 1) + "/" + nTotalCount);
                Log.e(TAG, "onProgressUpdate:" + (rowItems.size() + 1) + "/" + nTotalCount);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e(TAG, "AsyncTask onCancelled");
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

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finish();
            return;
        }
        Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

    public class ListViewAdapter extends BaseAdapter{

        Context context;
        List<RowItem> rowItems;

        public ListViewAdapter(Context context, List<RowItem> rowItems){
            this.context = context;
            this.rowItems = rowItems;
        }

        @Override
        public int getCount() {
            return rowItems.size();
        }

        @Override
        public Object getItem(int position) {
            return rowItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if(convertView == null){
                convertView = inflater.inflate(R.layout.list_item1, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView)convertView.findViewById(R.id.thumbnail);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            RowItem rowItem = (RowItem)getItem(position);
            holder.imageView.setImageBitmap(rowItem.getBitmap());
            return convertView;
        }
    }

    private class ViewHolder {
        ImageView imageView;
    }

}
