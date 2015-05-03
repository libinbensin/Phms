package net.mingxing.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by MingXing on 2015/5/1.
 * SDCARD工具 (是否有SDCARD  SDCARD是否可读, SDCARD是否可写,  )
 */
public class SDCardUtil {

    /**
     * sdcard 状态
     */
    public static String STATE;

    /**
     * sdcard 字符串路径
     */
    public static String SDCARD_PATH_STRING;

    /**
     * sdcard File文件
     */
    public static File SDCARD_FILE;


    static {
        STATE = Environment.getExternalStorageState();
        SDCARD_FILE = Environment.getExternalStorageDirectory();
        SDCARD_PATH_STRING = SDCARD_FILE.getAbsolutePath();
    }

    /**
     * sdcrad可读可写
     * @return
     */
    public static boolean isCan() {
        if(!sdCardCanRead() || !sdCardCanWrite()) {
            return false;
        }
        return true;
    }

    /**
     *  SDCard是否可读
     * @return 是否可读
     */
    public static boolean sdCardCanRead() {
        // 设备状态
        if (isSDcard() || sdCardIsRead()) { // 已经插入了sd卡，并且可以读
            return true;
        } else {
            return false;
        }
    }

    /**
     *  SDCard是否可写
     * @return 是否可写
     */
    public static boolean sdCardCanWrite() {
        // 设备状态
        if (isSDcard() || sdCardIsWiter()) {// 已经插入了sd卡，并且可以读与写
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否有sdcard
     * @return
     */
    public static boolean isSDcard() {
        String state = Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(STATE)) {
            return false;
        }
        return true;
    }

    /**
     * sdcard是否可读
     * @return
     */
    public static boolean sdCardIsRead() {
        if(!Environment.MEDIA_MOUNTED_READ_ONLY.equals(STATE)) {
            return false;
        }
        return true;
    }

    /**
     * sdCard是否可写
     * @return
     */
    public static boolean sdCardIsWiter() {
        if(!Environment.MEDIA_MOUNTED.equals(STATE)) {
            return false;
        }
        return true;
    }

}
