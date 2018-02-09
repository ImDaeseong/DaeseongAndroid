package com.im.daeseong.http_test.HttpUtil;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class DownloadJson extends AsyncTask<String, Void, String> {

    private TextView textView;

    public DownloadJson(TextView textView){
        this.textView = textView;
    }


    @Override
    protected String doInBackground(String... params) {

        String urlText = params[0];
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            URL url = new URL(urlText);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            //httpURLConnection.setConnectTimeout(60);//타임아웃 시간 설정(default:무한대기)
            //httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
            }
            httpURLConnection.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
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
        //String jsonResponse = stringBuilder.toString();
        //jsonResponse = jsonResponse.replace("\\", "");
        return stringBuilder.toString();
    }

    public void GetParseDataGson(String sDate){

        Gson gson = new Gson();
        Goods goods[] = gson.fromJson(sDate, Goods[].class);
        List<Goods> result = Arrays.asList(goods);

        for(int i=0; i < result.size(); i++){
            Log.e(String.valueOf(i) + result.get(i).getGoodsName(), result.get(i).getFileName1() );
        }
    }

    public void GetParseData(String sData){
        try{

            //String jsonResponse = sData;
            //jsonResponse = jsonResponse.replace("\\", "")
                    //.replace("#", "\\");

            JSONArray jsonArray = new JSONArray(sData);
            for(int i=0; i<jsonArray.length(); i++) {

                // JSONObject jsonObject = jsonArray.getJSONObject(i);
                //Log.e( String.valueOf(i) + " 번쨰 데이타 " , obj.toString());

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String ShopNo = jsonObject.getString("ShopNo");
                String GoodsCode = jsonObject.getString("GoodsCode");
                String GoodsName = jsonObject.getString("GoodsName");
                String Content = jsonObject.getString("Content");
                String ExcerciseCode = jsonObject.getString("ExcerciseCode");
                String GoodsType = jsonObject.getString("GoodsType");
                String FreeUseFlag = jsonObject.getString("FreeUseFlag");
                String GoodsForm1 = jsonObject.getString("GoodsForm1");
                String GoodsForm2 = jsonObject.getString("GoodsForm2");
                String GoodsForm3 = jsonObject.getString("GoodsForm3");
                String Price = jsonObject.getString("Price");
                String Amount = jsonObject.getString("Amount");
                String SaleCnt = jsonObject.getString("SaleCnt");
                String SaleStartDate = jsonObject.getString("SaleStartDate");
                String SaleEndDate = jsonObject.getString("SaleEndDate");
                String UseGender = jsonObject.getString("UseGender");
                String ReserveFlag = jsonObject.getString("ReserveFlag");
                String Notice1 = jsonObject.getString("Notice1");
                String Notice2 = jsonObject.getString("Notice2");
                String FileType1 = jsonObject.getString("FileType1");
                String FileName1 = jsonObject.getString("FileName1");
                String FileType2 = jsonObject.getString("FileType2");
                String FileName2 = jsonObject.getString("FileName2");
                String FileType3 = jsonObject.getString("FileType3");
                String FileName3 = jsonObject.getString("FileName3");
                String FileType4 = jsonObject.getString("FileType4");
                String FileName4 = jsonObject.getString("FileName4");
                String FileType5 = jsonObject.getString("FileType5");
                String FileName5 = jsonObject.getString("FileName5");
                String AdminID = jsonObject.getString("AdminID");
                String Reason = jsonObject.getString("Reason");
                Log.e(String.valueOf(i) +  GoodsName, FileName1);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
            if(s != null){

                //기본 제공 타입으로 구현
                GetParseData(s);

                //라이브러리 사용 구현
                GetParseDataGson(s);

                this.textView.setText(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public class Goods{
        String ShopNo;
        String GoodsCode;
        String GoodsName;
        String Content;
        String ExcerciseCode;
        String GoodsType;
        String FreeUseFlag;
        String GoodsForm1;
        String GoodsForm2;
        String GoodsForm3;
        String Price;
        String Amount;
        String SaleCnt;
        String SaleStartDate;
        String SaleEndDate;
        String UseGender;
        String ReserveFlag;
        String Notice1;
        String Notice2;
        String FileType1;
        String FileName1;
        String FileType2;
        String FileName2;
        String FileType3;
        String FileName3;
        String FileType4;
        String FileName4;
        String FileType5;
        String FileName5;
        String AdminID;
        String Reason;

        public String getShopNo(){
            return ShopNo;
        }

        public void SetShopNo(String ShopNo){
            this.ShopNo = ShopNo;
        }

        public String getGoodsCode(){
            return GoodsCode;
        }

        public void SetGoodsCode(String GoodsCode){
            this.GoodsCode = GoodsCode;
        }

        public String getGoodsName(){
            return GoodsName;
        }

        public void SetGoodsName(String GoodsName){
            this.GoodsName = GoodsName;
        }

        public String getContent(){
            return Content;
        }

        public void SetContent(String Content){
            this.Content = Content;
        }

        public String getExcerciseCode(){
            return ExcerciseCode;
        }

        public void SetExcerciseCode(String ExcerciseCode){
            this.ExcerciseCode = ExcerciseCode;
        }

        public String getGoodsType(){
            return GoodsType;
        }

        public void SetGoodsType(String GoodsType){
            this.GoodsType = GoodsType;
        }

        public String getFreeUseFlag(){
            return FreeUseFlag;
        }

        public void SetFreeUseFlag(String FreeUseFlag){
            this.FreeUseFlag = FreeUseFlag;
        }

        public String getGoodsForm1(){
            return GoodsForm1;
        }

        public void SetGoodsForm1(String GoodsForm1){
            this.GoodsForm1 = GoodsForm1;
        }

        public String getGoodsForm2(){
            return GoodsForm2;
        }

        public void SetGoodsForm2(String GoodsForm2){
            this.GoodsForm2 = GoodsForm2;
        }

        public String getGoodsForm3(){
            return GoodsForm3;
        }

        public void SetGoodsForm3(String GoodsForm3){
            this.GoodsForm3 = GoodsForm3;
        }

        public String getPrice(){
            return Price;
        }

        public void SetPrice(String Price){
            this.Price = Price;
        }

        public String getAmount(){
            return Amount;
        }

        public void SetAmount(String Amount){
            this.Amount = Amount;
        }

        public String getSaleCnt(){
            return SaleCnt;
        }

        public void SetSaleCnt(String SaleCnt){
            this.SaleCnt = SaleCnt;
        }

        public String getSaleStartDate(){
            return SaleStartDate;
        }

        public void SetSaleStartDate(String SaleStartDate){
            this.SaleStartDate = SaleStartDate;
        }

        public String getSaleEndDate(){
            return SaleEndDate;
        }

        public void SetSaleEndDate(String SaleEndDate){
            this.SaleEndDate = SaleEndDate;
        }

        public String getUseGender(){
            return UseGender;
        }

        public void SetUseGender(String UseGender){
            this.UseGender = UseGender;
        }

        public String getReserveFlag(){
            return ReserveFlag;
        }

        public void SetReserveFlag(String ReserveFlag){
            this.ReserveFlag = ReserveFlag;
        }

        public String getNotice1(){
            return Notice1;
        }

        public void SetNotice1(String Notice1){
            this.Notice1 = Notice1;
        }

        public String getNotice2(){
            return Notice2;
        }

        public void SetNotice2(String Notice2){
            this.Notice2 = Notice2;
        }

        public String getFileType1(){
            return FileType1;
        }

        public void SetFileType1(String FileType1){
            this.FileType1 = FileType1;
        }

        public String getFileName1(){
            return FileName1;
        }

        public void SetFileName1(String FileName1){
            this.FileName1 = FileName1;
        }

        public String getFileType2(){
            return FileType2;
        }

        public void SetFileType2(String FileType2){
            this.FileType2 = FileType2;
        }

        public String getFileName2(){
            return FileName2;
        }

        public void SetFileName2(String FileName2){
            this.FileName2 = FileName2;
        }

        public String getFileType3(){
            return FileType3;
        }

        public void SetFileType3(String FileType3){
            this.FileType3 = FileType3;
        }

        public String getFileName3(){
            return FileName3;
        }

        public void SetFileName3(String FileName3){
            this.FileName3 = FileName3;
        }

        public String getFileType4(){
            return FileType4;
        }

        public void SetFileType4(String FileType4){
            this.FileType4 = FileType4;
        }

        public String getFileName4(){
            return FileName4;
        }

        public void SetFileName4(String FileName4){
            this.FileName4 = FileName4;
        }

        public String getFileType5(){
            return FileType5;
        }

        public void SetFileType5(String FileType5){
            this.FileType5 = FileType5;
        }

        public String getFileName5(){
            return FileName5;
        }

        public void SetFileName5(String FileName5){
            this.FileName5 = FileName5;
        }

        public String getAdminID(){
            return AdminID;
        }

        public void SetAdminID(String AdminID){
            this.AdminID = AdminID;
        }

        public String getReason(){
            return Reason;
        }

        public void SetReason(String Reason){
            this.Reason = Reason;
        }
    }


}
