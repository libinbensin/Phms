package com.cking.phss.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import android.util.Log;

import com.cking.phss.util.Constant;

public class FileLog {
	private static String TAG = "FileLog";

    public static void i(String tag, String logStr) {
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
        String data = formatTime.format(System.currentTimeMillis()) + " - " + tag + " - " + "INFO " + " - " + logStr;
        try {
            writeFileData(Constant.LOG_PATH, data, true);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void d(String tag, String logStr) {
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
        String data = formatTime.format(System.currentTimeMillis()) + " - " + tag + " - " + "DEBUG" + " - " + logStr;
        try {
            writeFileData(Constant.LOG_PATH, data, true);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void w(String tag, String logStr) {
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
        String data = formatTime.format(System.currentTimeMillis()) + " - " + tag + " - " + "WARN " + " - " + logStr;
        try {
            writeFileData(Constant.LOG_PATH, data, true);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
    
    public static void e(String tag, String logStr) {
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
        String data = formatTime.format(System.currentTimeMillis()) + " - " + tag + " - " + "ERROR" + " - " + logStr;
        try {
            writeFileData(Constant.LOG_PATH, data, true);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
    
	private static boolean writeFileData(String filepath,String data,boolean iscontinue) throws Exception {
        FileOutputStream fout = null;
        OutputStreamWriter osw = null;

        // 判断文件和路径是否存在
        File path = new File(filepath);
        if (!path.exists())
            path.mkdir();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        String fileName = filepath + formatDate.format(System.currentTimeMillis()) + ".log";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            Log.i(TAG, "------create new logfile---------");
        }
        if (!file.canWrite()) {
            Log.i(TAG, "------file is not can write---------");
            return false;
        }
        fout = new FileOutputStream(file, iscontinue);
        osw = new OutputStreamWriter(fout);
        osw.write(data + "\r\n");
        osw.flush();
        osw.close();
        fout.close();
        return true;
	}

}
