package com.im.daeseong.completablefuture_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class HttpUtil {

    private static final String TAG = HttpUtil.class.getSimpleName();
    private static final int CONNECT_TIMEOUT = 10000; // 10초
    private static final int READ_TIMEOUT = 15000; // 15초

    // 안전한 HostnameVerifier 구현
    private static final HostnameVerifier HOSTNAME_VERIFIER = (hostname, session) -> hostname != null && hostname.equals(session.getPeerHost());


    public static String getDataString(String urlString) {

        if (urlString == null || urlString.isEmpty()) {
            return "";
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();

        try {
            connection = setupConnection(urlString);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            } else {
                Log.e(TAG, "HTTP 에러 코드: " + responseCode);
            }
        } catch (IOException e) {
            Log.e(TAG, "네트워크 요청 중 오류 발생", e);
        } finally {
            closeQuietly(reader);
            closeQuietly(inputStream);
            if (connection != null) {
                connection.disconnect();
            }
        }

        return result.toString();
    }

    public static Bitmap getDataBitmap(String urlString) {

        if (urlString == null || urlString.isEmpty()) {
            return null;
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        Bitmap bitmap = null;

        try {
            connection = setupConnection(urlString);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                if (bitmap == null) {
                    Log.e(TAG, "비트맵 디코딩 실패");
                }
            } else {
                Log.e(TAG, "HTTP 에러 코드: " + responseCode);
            }
        } catch (IOException e) {
            Log.e(TAG, "이미지 다운로드 중 오류 발생", e);
        } finally {
            closeQuietly(inputStream);
            if (connection != null) {
                connection.disconnect();
            }
        }

        return bitmap;
    }


    private static HttpURLConnection setupConnection(String urlString) throws IOException {

        // HTTPS 연결에 대한 기본 HostnameVerifier 설정
        HttpsURLConnection.setDefaultHostnameVerifier(HOSTNAME_VERIFIER);

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setInstanceFollowRedirects(true);
        connection.setAllowUserInteraction(false);
        connection.connect();

        return connection;
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.w(TAG, "리소스를 닫는 중 오류 발생", e);
            }
        }
    }
}