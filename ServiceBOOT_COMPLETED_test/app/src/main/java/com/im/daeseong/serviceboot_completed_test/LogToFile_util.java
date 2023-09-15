package com.im.daeseong.serviceboot_completed_test;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class LogToFile_util {

    private static LogToFile_util instance;

    private static Context mContext;

    private static File logFile;

    private static SimpleDateFormat logSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int  LOG_MAX_SIZE = 2 * 1024 * 1024;

    private static String tag;

    public static void init(Context context){
        mContext = context;
        instance = new LogToFile_util();
        logFile = getLogFile();
        long logFileSize = getFileSize(logFile);
        if (LOG_MAX_SIZE < logFileSize) {
            resetLogFile();
        }
    }

    public static void write(String strLog){

        String logStr = getFunctionInfo() + " - " + strLog;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
            bw.write(logStr);
            bw.write("\r\n");
            bw.flush();
        } catch (Exception e) {
        }
    }

    private static File getLogFile() {
        File file;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(mContext.getExternalFilesDir("BootserviceLog").getPath() + "/");
        } else {
            file = new File(mContext.getFilesDir().getPath() + "/BootserviceLog/");
        }

        if (!file.exists()) {
            file.mkdir();
        }

        File logFile = new File(file.getPath() + "/logs.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (Exception e) {
            }
        }
        return logFile;
    }

    private static String getFunctionInfo() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(instance.getClass().getName())) {
                continue;
            }
            tag = st.getFileName();
            return "[" + logSDF.format(new java.util.Date()) + " " + st.getClassName() + " " + st.getMethodName() + " Line:" + st.getLineNumber() + "]";
        }
        return null;
    }


    private static void resetLogFile() {

        File lastLogFile = new File(logFile.getParent() + "/lastLog.txt");
        if (lastLogFile.exists()) {
            lastLogFile.delete();
        }
        logFile.renameTo(lastLogFile);

        try {
            logFile.createNewFile();
        } catch (Exception e) {
        }
    }

    private static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
            } catch (Exception e) {
            }
        }
        return size;
    }

}

