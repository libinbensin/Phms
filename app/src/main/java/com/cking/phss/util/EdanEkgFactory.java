/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * EdanEkgFactory.java
 * classes : com.cking.phss.util.EdanEkgFactory
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-9 下午9:08:33
 */
package com.cking.phss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.util.FileFactory;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;

import com.cking.phss.bean.EkgResult;
import com.cking.phss.bean.PatientInfo;

/**
 * com.cking.phss.util.EdanEkgFactory
 * @author Administrator <br/>
 * create at 2014-6-9 下午9:08:33
 */
public class EdanEkgFactory {
    private static final String TAG = "EdanEkgFactory";
    
    /**
     * ID=12345678
     * Name=test
     * Sex=1
     * Age=25
     * Idcard=123456789011234578
     */
    public static void setPatientInfo(PatientInfo patientInfo) {
        String data = "[Patient]\r\n" + 
                "ID=" + patientInfo.getId() + "\r\n" + 
                "Name=" + patientInfo.getName() + "\r\n" + 
                "Sex=" + patientInfo.getSexCode() + "\r\n" + 
                "Age=" + patientInfo.getAge() + "\r\n" + 
                "Idcard=" + patientInfo.getIdcard() + "\r\n";
        String file = Constants.EDAN_PATIENT_DIR + "patient.ini";
        
        FileFactory.write(data.getBytes(), new File(file));
    }
    
    public static EkgResult getEkgResult() {
        String file = Constants.EDAN_PATIENT_DIR + "result.ini";
        
        /**
         * [result]
         * ID=123456                                                                                                              （编号）
         * MeasuerInfo=80^54^93^168^45^78^392^340^53^1047^750^552^910（测量信息）
         * DiagnoseInfo=窦性心律 ^******正常心电图******              （诊断结果）
         * PicPath=/mnt/sdcard/fVirtue/patient/123456.PNG                                   （图片地址）
         * ExamDate=2013-03-22 14:18:29 
         */
        Properties properties = new Properties();

        File fil = new File(file);
        if (!fil.exists()) {
            return null;
        }

        try {
            FileInputStream s = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(s, "UTF-8"); 
            properties.load(isr);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (properties == null || properties.isEmpty()) {
            return null;
        } else {
            EkgResult ekgResult = new EkgResult();
            
            if (properties.containsKey("ID")) {
                String ret = properties.getProperty("ID");
                ekgResult.setId(ret);
            }
            if (properties.containsKey("MeasuerInfo")) {
                String ret = properties.getProperty("MeasuerInfo");
                String[] item = ret.split("\\^");
                try {
                    ekgResult.setHr(Integer.parseInt(item[0]));
                    ekgResult.setP(Integer.parseInt(item[1]));
                    ekgResult.setP1(Integer.parseInt(item[2]));
                    ekgResult.setPr(Integer.parseInt(item[3]));
                    ekgResult.setQrs(Integer.parseInt(item[4]));
                    ekgResult.setQrs1(Integer.parseInt(item[5]));
                    ekgResult.setQtc(Integer.parseInt(item[6]));
                    ekgResult.setQr(Integer.parseInt(item[7]));
                    ekgResult.setT(Integer.parseInt(item[8]));
                    ekgResult.setRv5(Integer.parseInt(item[9]));
                    ekgResult.setRv6(Integer.parseInt(item[10]));
                    ekgResult.setSv1(Integer.parseInt(item[11]));
                    ekgResult.setSv2(Integer.parseInt(item[12]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
            if (properties.containsKey("DiagnoseInfo")) {
                String ret = properties.getProperty("DiagnoseInfo");
                ret = ret.replace("^", "\n");
                ekgResult.setDiagnoseInfo(ret);
            }
            if (properties.containsKey("PicPath")) {
                String ret = properties.getProperty("PicPath");
                ekgResult.setPicPath(ret);
            }
            if (properties.containsKey("ExamDate")) {
                String ret = properties.getProperty("ExamDate");
                ekgResult.setExamDate(ret);
            }
            
            return ekgResult;
        }
    }

    private static void deleteResult() {
        String file = Constants.EDAN_PATIENT_DIR;
        File f = new File(file);
        FileFactory.deleteDir(f);
    }
    
    private static boolean hasResult() {
        String file = Constants.EDAN_PATIENT_DIR + "result.ini";
        File f = new File(file);
        if (f.exists() && f.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public interface OnGetEkgResultListener {
        public void OnGetEkgResult(EkgResult ekgResult);
    }
    
    private static OnGetEkgResultListener mOnGetEkgResultListener = null;
    
    public static void autoRunEkg(Context context, PatientInfo patientInfo, OnGetEkgResultListener listener) {
        mOnGetEkgResultListener = listener;
        deleteResult(); // 删除之前的结果文件
        setPatientInfo(patientInfo); // 写病人信息
        callEkgProcess(context); // 调用心电仪
        new Thread(new Runnable() { // 周期性检测结果文件

            @Override
            public void run() {
                Looper.prepare();
                while (true) {
                    if (hasResult()) {
                        if (mOnGetEkgResultListener != null) {
                            mOnGetEkgResultListener.OnGetEkgResult(getEkgResult());
                        }
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }).start();
    }

    /**
     * 
     */
    private static void callEkgProcess(Context context) {
        try {
        Intent intent = new Intent("com.edan.sample");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
