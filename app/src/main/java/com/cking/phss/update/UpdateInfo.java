package com.cking.phss.update;

import android.content.Context;
import android.content.SharedPreferences;

import com.cking.application.MyApplication;

/**
 * the local version and update info in the SharedPreference
 * 
 * @author taowencong
 * 
 */
public class UpdateInfo {
    // the SharedPreferences file name
    private static final String VERSION_CHECK_FILE = "versionUpdateInfo";
    // key ,the time of the last checked 
    private static final String LAST_CHECK_TIME_KEY = "lastCheckTime";
    // key ,the status of the last checked .
    //value 1 present  download and install succeed.
    //value 2 present download succeed ,but install not.
    //value 3 present download failed.
    //value 0 present the is no update operate.
    private static final String LAST_CHECK_STATUS_KEY="lastCheckStatus";
    // key ,the file name of the last checked and download,if return is "",they is no update operate 
    private static final String LAST_CHECK_FILE_KEY="lastCheckFile";
    // key,the last time we get the newest version from the server
    private static final String LAST_CHECK_VERSION_KEY="lastCheckVersion"; 
    private SharedPreferences sharedPre;
    
    
    public static final int LAST_CHECK_STATUS_NO=0;// the is no update operate.
    public static final int LAST_CHECK_STATUS_SUCCEED=1;//  download  and install succeed.
    public static final int LAST_CHECK_STATUS_NO_INSTALL=2;// download succeed ,but install not.
    public static final int LAST_CHECK_STATUS_FAILED=3;// download failed.
    
    
    /*
     * single instanse
     */
    private static final UpdateInfo UPDATE_INFO=new UpdateInfo();
    private UpdateInfo() {
        sharedPre=MyApplication.getInstance().getSharedPreferences(VERSION_CHECK_FILE,
            Context.MODE_PRIVATE);
    }
    public static UpdateInfo getInstance(){
        return UPDATE_INFO;
    }
    
    //get the last check time
    public String getLastCheckTime(){
        return sharedPre.getString(LAST_CHECK_TIME_KEY, "");
    }
    
    //set the last check time
    public void setLastCheckTime(String date){
        sharedPre.edit().putString(LAST_CHECK_TIME_KEY, date).commit();
    }
    
    //
    public int getLastCheckStatus(){
        return sharedPre.getInt(LAST_CHECK_STATUS_KEY, 0);
    }
    
    public void setLastCheckStatus(int status){
        sharedPre.edit().putInt(LAST_CHECK_STATUS_KEY, status).commit();
    }
    
    public String getLastCheckFile(){
        return sharedPre.getString(LAST_CHECK_FILE_KEY, "");
    }
    
    public void setLastCheckFile(String fileName){
        sharedPre.edit().putString(LAST_CHECK_FILE_KEY, fileName).commit();
    }
    
    public int getLastCheckVersion(){
        return sharedPre.getInt(LAST_CHECK_VERSION_KEY, -1);
    }
    
    public void setLastCheckVersion(int version){
        sharedPre.edit().putInt(LAST_CHECK_VERSION_KEY, version).commit();
    }
}
