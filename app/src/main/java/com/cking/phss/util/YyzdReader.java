/* Cking Inc. (C) 2012. All rights reserved.
 *
 * YyzdReader.java
 * classes : com.cking.phss.util.YyzdReader
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-22 下午06:00:30
 */
package com.cking.phss.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.os.Environment;

/**
 * com.cking.phss.util.YyzdReader
 * @author Wation Haliyoo <br/>
 * create at 2012-9-22 下午06:00:30
 */
public class YyzdReader {
    private static final String TAG = "YyzdReader";
    
    private static final String YYZD_DIR = Environment.getExternalStorageDirectory().getPath() + "/wltlib/用药指导/";
    
    public static String[] getFolderList(Context context) {
        File yyzdDir = new File(YYZD_DIR);
        String[] list = null;
        
        if(!yyzdDir.exists()){
            return null;
        }
        
        list = yyzdDir.list();

        return list;
    }

    public static String getFileContent(Context context, String folder, String file) {
        File yyzdDir = new File(YYZD_DIR + folder + "/" + file + ".txt");
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(yyzdDir));
            int res = in.available();
            byte [] by = new byte[res];
            in.read(by);

            return EncodingUtils.getString(by,"gbk");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
