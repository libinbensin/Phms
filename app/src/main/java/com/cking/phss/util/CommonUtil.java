/* Cking Inc. (C) 2012. All rights reserved.
 *
 * CommonUtil.java
 * classes : com.iaxure.remotecontrol.util.CommonUtil
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-8-30 下午04:37:19
 * Reference:
 * http://www.cnblogs.com/zdz8207/archive/2012/08/24/bytesToHexString.html
 */
package com.cking.phss.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Spinner;

/**
 * com.iaxure.remotecontrol.util.CommonUtil
 * @author Wation Haliyoo <br/>
 * create at 2012-8-30 下午04:37:19
 */
public class CommonUtil {
    /**
     * byte数组转换成16进制字符串
     * 
     * @param src
     * @return
     */
    public static String bytes2HexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * byte数组转换成16进制字符串
     * 
     * @param src
     * @return
     */
    public static String bytes2HexString(byte[] src, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < len; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    /**
     * byte数组转换成16进制字符数组
     * 
     * @param src
     * @return
     */
    public static String[] bytes2HexStrings(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }
        String[] str = new String[src.length];

        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                str[i] = "0";
            }
            str[i] = hv;
        }
        return str;
    }

    /**
     * 将byte数组转换为int数据
     * 
     * @param b
     *            字节数组
     * @return 生成的int数据
     */
    public static int bytes2Int(byte[] b) {
        int ret = 0;
        ret = (int) (b[0] & 0xFFL);
        ret |= ((b[1] * (long)Math.pow(2, 8)) & 0xFF00L);
        ret |= ((b[2] * (long)Math.pow(2, 16)) & 0xFF0000L);
        ret |= ((b[3] * (long)Math.pow(2, 24)) & 0xFF000000L);
        
        return ret;
    }

    /**
     * 将byte数组转换为int数据
     * 
     * @param b
     *            字节数组
     * @return 生成的int数据
     */
    public static byte[] int2Bytes(int integer) {
        int byteNum = (40 -Integer.numberOfLeadingZeros (integer < 0 ? ~integer : integer))/ 8;
        byte[] byteArray = new byte[4];

        for (int n = 0; n < byteNum; n++)
        byteArray[n] = (byte) (integer>>> (n * 8));

        return (byteArray);
    }
    
    /**
     * byte数组转换成16进制字符串
     * 
     * @param src
     * @return
     */
    public static byte[] hexString2Bytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     * 
     * @param c
     *            char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
     
    /**
     * 设置下拉框
     * @param spinner 下拉框对象
     * @param strs 下拉列表数据
     * @param itemStr 设置的数据
     */
    public static void setSpinner(Spinner spinner, String[] strs, String itemStr) {

        for (int i=0; i<strs.length; i++) {
            if (itemStr.equals(strs[i])) {
                spinner.setSelection(i);
                return;
            }
        }
        spinner.setSelection(strs.length - 1);
    }

    /**
     * 获取版本号
     * 
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }
}