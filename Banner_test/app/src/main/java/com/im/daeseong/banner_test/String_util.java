package com.im.daeseong.banner_test;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_util {

    private String_util() {
        throw new UnsupportedOperationException("String_util");
    }

    //파일 확장자
    public static String getExt(String url){
        String sResult = "";
        try{
            sResult = url.substring(url.lastIndexOf(".") + 1);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return sResult;
    }

    //파일 이름
    public static String getImageName(String url){
        String sResult = "";
        try{
            sResult = url.substring(url.lastIndexOf("/") + 1);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return sResult;
    }

    //대문자 변환
    public static String getUpper(String sInput){
        //String sResult = sInput.toUpperCase();
        //return sResult;
        StringBuffer stringBuffer = new StringBuffer();
        if(sInput != null){

            for(int i=0;i<sInput.length();i++){

                char ch = sInput.charAt(i);

                if(Character.isLowerCase(ch)){
                    stringBuffer.append(Character.toUpperCase(ch));
                }else {
                    stringBuffer.append(ch);
                }
            }
        }
        return stringBuffer.toString();
    }

    //소문자 변화
    public static String getLower(String sInput){
        //String sResult = sInput.toLowerCase();
        //return sResult;
        StringBuffer stringBuffer = new StringBuffer();
        if(sInput != null){

            for(int i=0;i<sInput.length();i++){

                char ch = sInput.charAt(i);

                if(Character.isUpperCase(ch)){
                    stringBuffer.append(Character.toLowerCase(ch));
                }else {
                    stringBuffer.append(ch);
                }
            }
        }
        return stringBuffer.toString();
    }

    //문자형 숫자
    public static String getMoneyDecimalFormat(String sInput) {
        double value;
        try {
            value = Double.parseDouble(sInput);
        } catch (Exception e) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(value);
    }

    //시간 문자형
    public static String getTime()
    {
        String format1 = "yyyy-MM-dd hh:mm:ss";
        String format2 = "yyyyMMddhhmmssSSS";
        String format3 = "yyyy-MM-dd";
        String format4  = "yyyy년MM월dd일HH시mm분ss초";

        SimpleDateFormat sDateFormat = new SimpleDateFormat(format4);
        String sResult = sDateFormat.format(new java.util.Date());
        return sResult;
    }

    //시간 데이터형
    public static Date getDate(String sInput, String Format) {

        if(sInput==null) return null;

        SimpleDateFormat sDateFormat = new SimpleDateFormat(Format);
        Date convertedDate = new Date();

        try {
            convertedDate = sDateFormat.parse(sInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  convertedDate;
    }

    public static boolean isExpired(String sInput){
        boolean expiring = false;
        int nExpiredDay = 3;

        try {
            //Date startDate = String_util.getDate("2018-02-05 12:00:00", "yyyy-MM-dd hh:mm:ss");
            Date startDate = String_util.getDate(sInput, "yyyy-MM-dd");

            long lTime = String_util.getDifferentDays(startDate, new Date());
            if (lTime >= nExpiredDay) {
                expiring = true;
            }
            return expiring;
        }catch (Exception e){
            e.printStackTrace();
        }
        return expiring;
    }

    //날짜 차이
    public static long getDifferentDays(Date startDate, Date endData){
        try {
            long duration = endData.getTime() - startDate.getTime();
            return TimeUnit.MILLISECONDS.toDays(duration);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //시간 차이
    public static long getDifferentHours(Date startDate, Date endData){
        try {
            long duration = endData.getTime() - startDate.getTime();
            return TimeUnit.MILLISECONDS.toHours(duration);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //분 차이
    public static long getDifferentMinutes(Date startDate, Date endData){
        try {
            long duration = endData.getTime() - startDate.getTime();
            return TimeUnit.MILLISECONDS.toMinutes(duration);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //쌈따옴표를 제거
    public static String removeQuoted(String sInput){
       String result = sInput.trim().replaceAll("^\"|\"$", "");
       return result.trim();
    }

    //문자열 분리
    public static String[] split(String sInput, String delim) {
        StringTokenizer token = new StringTokenizer(sInput, delim);
        String[] result = new String[token.countTokens()];

        for (int i = 0; token.hasMoreTokens(); i++) {
            result[i] = token.nextToken();
        }
        return result;
    }

    //전화번호 정규식
    public static boolean isPhoneNo(String sInput) {
        String regex = "\\d{2,4}-\\d{3,4}-\\d{4}$";
        return sInput.matches(regex);
    }

    //이메일 정규식
    public static boolean isEmail(String sInput) {
        String regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        return sInput.matches(regex);
    }

    //숫자만 정규식
    public static boolean isNumeric(String sInput) {
        String regex = "[\\d]*$";
        return sInput.matches(regex);
    }

    //숫자 포함 여부
    public static boolean isNumericInc(String sInput) {
        boolean isNumber = false;
        String regex = "[\\d]*$";

        String sub = "";
        for(int i=0; i<sInput.length(); i++){
            sub = sInput.substring(i, i+1);
            if(!isNumber)
                isNumber = sub.matches(regex);
        }

        if( isNumber ){
            return true;
        }else{
            return false;
        }
    }

    public static int getBannerIndex(){

        int nMax = 8;
        int bannclip[] = new int[] { 5, 0, 30, 0, 0, 45, 0, 20  };

        int nVal = (int)(Math.random() * nMax ) + 1;
        if (nVal == 0) nVal = 1;

        int nResult = 0;
        for (int i = 0; i < nMax; i++){

            if (bannclip[i] != 0){

                // 몫
                int nMok = bannclip[i] / nVal;

                // 나머지
                int nNa = bannclip[i] % nVal;

                //Log.e("d", "Hotclip: " + Hotclip[i] + "몫: " +  nMok + "나머지: " +  nNa);

                if (nMok == 0 && (nNa == nVal))
                {
                    if(nResult < bannclip[i])
                        nResult = bannclip[i];
                }

                if (nMok > 0 && nNa == 0)
                {
                    if(nResult < bannclip[i])
                        nResult = bannclip[i];
                }

                if (nMok > 0 && (nNa < nVal))
                {
                    if(nResult < bannclip[i])
                        nResult = bannclip[i];
                }

                if (nMok > 0 && (nNa > nVal))
                {
                    if(nResult < bannclip[i])
                        nResult = bannclip[i];
                }
            }
        }
        return nResult;
    }


    public static boolean isEmpty(String sInput) {
        if(sInput == null || sInput.length() == 0)
            return true;
        return false;
    }

    public static String getTrim(String sInput) {
        if(sInput == null)
            return null;
        return sInput.trim();
    }

    public static String getrandomUUID() {
        String sResult = "";
        try {
            sResult = UUID.randomUUID().toString().replace("-", "");
        } catch (Exception e) {
            return "";
        }
        return sResult;
    }

    public static String getReplace(String sInput) {
        return sInput.replace(" ", "+");
    }

    public static String getFileSize(long lLength) {
        char[] unit = new char[]{'B', 'K', 'M', 'G'};
        int index = 0;
        int div = 1;
        for (; index < unit.length; index++) {
            div *= 1024;
            if (lLength < div) {
                break;
            }
        }

        div /= 1024;
        if (lLength % div == 0) {
            return String.valueOf(lLength / div) + unit[index];
        } else {
            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(lLength * 1.0 / div) + unit[index];
        }
    }

}
