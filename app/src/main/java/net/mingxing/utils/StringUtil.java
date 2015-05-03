package net.mingxing.utils;

/**
 * Created by MingXing on 2015/5/3.
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否为null
     * @return  true: null
     */
    public  static boolean isEmpty(String string) {
        if(string == null || string.equals("") || string.trim().length() <= 0) {
            return true;
        }
        return false;
    }



}
