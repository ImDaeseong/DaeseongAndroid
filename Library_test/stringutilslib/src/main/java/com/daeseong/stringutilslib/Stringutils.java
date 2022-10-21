package com.daeseong.stringutilslib;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Stringutils {

    // /,? 사이의 문자열 반환
    public static String getSubStringUrl(String sUrl){

        String sResult = "";

        try{

            int start = sUrl.lastIndexOf("/") + 1;
            int end = sUrl.lastIndexOf("?");

            if(end < 0){
                end = sUrl.length();
            }

            sResult = sUrl.substring(start, end);

        }catch (NullPointerException e){
        }
        return sResult;
    }

    // /뒤에 문자열 반환
    public static String getlastStringUrl(String sUrl){

        String sResult = "";

        try{

            sResult = sUrl.substring(sUrl.lastIndexOf("/") + 1);

        }catch (NullPointerException e){
        }
        return sResult;
    }

    //확장자 반환
    public static String getStringExt(String sUrl){

        String sResult = "";

        try{

            sResult = sUrl.substring(sUrl.lastIndexOf(".") + 1);

        }catch (NullPointerException e){
        }
        return sResult;
    }

    //대문자
    public static String getStringUpper(String sInput){

        StringBuffer stringBuffer = new StringBuffer();

        if(sInput != null){

            for(int i=0; i<sInput.length(); i++){

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

    //소문자
    public static String getStringLower(String sInput){

        StringBuffer stringBuffer = new StringBuffer();

        if(sInput != null){

            for(int i=0; i<sInput.length(); i++){

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

    //문자형 숮자
    public static String getStringDecimalFormat(String sInput) {

        double value;

        try {
            value = Double.parseDouble(sInput);
        } catch (Exception e) {
            return "";
        }

        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(value);
    }

    //숫자형 문자
    public static String getDoubleCovnertToString(Double dInput){

        String value;

        try{
            DecimalFormat df = new DecimalFormat("#,###.##");
            value = df.format(dInput);
        }catch (Exception e){
            return "0";
        }
        return value;
    }

    //시간 표시
    public static String getStringTime(int nformat)
    {
        String format = "";
        if(nformat == 1){
            format = "yyyy-MM-dd hh:mm:ss";
        }else if(nformat == 2){
            format = "yyyyMMddhhmmssSSS";
        }else if(nformat == 3){
            format = "yyyy-MM-dd";
        }else if(nformat == 4){
            format  = "yyyy년MM월dd일HH시mm분ss초";
        }else if(nformat == 5){
            format  = "yyyy.MM.dd HH시mm분";
        }

        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        String sResult = sDateFormat.format(new java.util.Date());
        return sResult;
    }

    //string convert Date
    public static Date getStringToDate(String sInput, int nformat) {

        String format = "";
        if(nformat == 1){
            format = "yyyy-MM-dd hh:mm:ss";
        }else if(nformat == 2){
            format = "yyyyMMddhhmmssSSS";
        }else if(nformat == 3){
            format = "yyyy-MM-dd";
        }else if(nformat == 4){
            format  = "yyyy년MM월dd일HH시mm분ss초";
        }else if(nformat == 5){
            format  = "yyyy.MM.dd HH시mm분";
        }

        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        Date convertedDate = new Date();

        try {
            convertedDate = sDateFormat.parse(sInput);
        } catch (ParseException e) {

        }
        return  convertedDate;
    }

    //Date convert string
    public static String getDateToString(Date dDate, int nformat) {

        String value;

        String format = "";
        if(nformat == 1){
            format = "yyyy-MM-dd hh:mm:ss";
        }else if(nformat == 2){
            format = "yyyyMMddhhmmssSSS";
        }else if(nformat == 3){
            format = "yyyy-MM-dd";
        }else if(nformat == 4){
            format  = "yyyy년MM월dd일HH시mm분ss초";
        }else if(nformat == 5){
            format  = "yyyy.MM.dd HH시mm분";
        }

        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        value = sDateFormat.format(dDate);
        return value;
    }

    //쌈따옴표를 제거
    public static String removeStringQuoted(String sInput){
        String result = sInput.trim().replaceAll("^\"|\"$", "");
        return result.trim();
    }

    //문자열 분리
    public static String[] splitString(String sInput, String delim) {
        StringTokenizer token = new StringTokenizer(sInput, delim);
        String[] result = new String[token.countTokens()];

        for (int i = 0; token.hasMoreTokens(); i++) {
            result[i] = token.nextToken();
        }
        return result;
    }

    //전화번호 정규식
    public static boolean isPhone(String sInput) {
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
}
