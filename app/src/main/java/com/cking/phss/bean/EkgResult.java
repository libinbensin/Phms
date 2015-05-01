/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * EkgResult.java
 * classes : com.cking.phss.bean.EkgResult
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-9 下午9:25:05
 */
package com.cking.phss.bean;

/**
 * com.cking.phss.bean.EkgResult
 * @author Administrator <br/>
 * create at 2014-6-9 下午9:25:05
 */
public class EkgResult {
    private static final String TAG = "EkgResult";
    /**
     * 心率 HR：80
     * P电轴 ：54
     * P时限：93
     * PR间期：168
     * QRS电轴：45
     * QRS时限：78
     * QTc间期：392
     * QR间期：340
     * T电轴：53
     * RV5振幅：1047uV，在显示时需要转化为mV，所以需要除以1000，并以float类型显示。
     * RV6振幅：750uV，操作同RV5
     * SV1振幅：552uV，操作同RV5
     * SV2振幅：910uV，操作同RV5
     * DiagnoseInfo=窦性心律 ^******正常心电图******              （诊断结果）
     * PicPath=/mnt/sdcard/fVirtue/patient/123456.PNG                                   （图片地址）
     * ExamDate=2013-03-22 14:18:29 
     */ 
    String id;
    int hr;
    int p;
    int p1;
    int pr;
    int qrs;
    int qrs1;
    int qtc;
    int qr;
    int t;
    int rv5;
    int rv6;
    int sv1;
    int sv2;
    String diagnoseInfo;
    String picPath;
    String examDate;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getHr() {
        return hr;
    }
    public void setHr(int hr) {
        this.hr = hr;
    }
    public int getP() {
        return p;
    }
    public void setP(int p) {
        this.p = p;
    }
    public int getP1() {
        return p1;
    }
    public void setP1(int p1) {
        this.p1 = p1;
    }
    public int getPr() {
        return pr;
    }
    public void setPr(int pr) {
        this.pr = pr;
    }
    public int getQrs() {
        return qrs;
    }
    public void setQrs(int qrs) {
        this.qrs = qrs;
    }
    public int getQrs1() {
        return qrs1;
    }
    public void setQrs1(int qrs1) {
        this.qrs1 = qrs1;
    }
    public int getQtc() {
        return qtc;
    }
    public void setQtc(int qtc) {
        this.qtc = qtc;
    }
    public int getQr() {
        return qr;
    }
    public void setQr(int qr) {
        this.qr = qr;
    }
    public int getT() {
        return t;
    }
    public void setT(int t) {
        this.t = t;
    }
    public int getRv5() {
        return rv5;
    }
    public void setRv5(int rv5) {
        this.rv5 = rv5;
    }
    public int getRv6() {
        return rv6;
    }
    public void setRv6(int rv6) {
        this.rv6 = rv6;
    }
    public int getSv1() {
        return sv1;
    }
    public void setSv1(int sv1) {
        this.sv1 = sv1;
    }
    public int getSv2() {
        return sv2;
    }
    public void setSv2(int sv2) {
        this.sv2 = sv2;
    }
    public String getDiagnoseInfo() {
        return diagnoseInfo;
    }
    public void setDiagnoseInfo(String diagnoseInfo) {
        this.diagnoseInfo = diagnoseInfo;
    }
    public String getPicPath() {
        return picPath;
    }
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    public String getExamDate() {
        return examDate;
    }
    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
    
}
