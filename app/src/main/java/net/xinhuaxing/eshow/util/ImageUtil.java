/* Xinhuaxing Inc. (C) 2013. All rights reserved.
 *
 * ImageUtil.java
 * classes : net.xinhuaxing.eshow.util.ImageUtil
 * @author wation
 * V 1.0.0
 * Create at 2013-10-23 下午1:35:27
 */
package net.xinhuaxing.eshow.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * net.xinhuaxing.eshow.util.ImageUtil
 * @author wation <br/>
 * create at 2013-10-23 下午1:35:27
 */
public class ImageUtil {
    private static final String TAG = "ImageUtil";

    public static Drawable getDrawableFromFile(String url) {
         Bitmap bitmap = getLocalBitmap(url);
         if (bitmap != null) {
             return bitmap2Drawable(bitmap);
         }
         
         return null;
    }
    
    /**
    * 加载本地图片
    * http://bbs.3gstdy.com
    * @param url
    * @return
    */
    public static Bitmap getLocalBitmap(String url) {
         try {
              FileInputStream fis = new FileInputStream(url);
              return BitmapFactory.decodeStream(fis);
         } catch (FileNotFoundException e) {
              e.printStackTrace();
              return null;
         }
    }

    /**
    * 从服务器取图片
    * http://adhere.haliyoo.com
    * @param url
    * @return
    */
    public static Bitmap getHttpBitmap(URL url) {
         URL myFileUrl = null;
         Bitmap bitmap = null;
         Log.d(TAG, url.toString());
         myFileUrl = url;
         try {
              HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
              conn.setConnectTimeout(0);
              conn.setDoInput(true);
              conn.connect();
              InputStream is = conn.getInputStream();
              bitmap = BitmapFactory.decodeStream(is);
              is.close();
         } catch (IOException e) {
              e.printStackTrace();
         }
         return bitmap;
    }

    /**
     * drawable转bitmap
     * @param drawable 
     * @return Bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                .getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();
    }

    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
}
