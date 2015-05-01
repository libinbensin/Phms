/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Icd10.java
 * classes : com.cking.phss.sqlite4address.Icd10
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-9 上午9:16:50
 */
package com.cking.phss.sqlite4address;

/**
 * com.cking.phss.sqlite4address.Icd10
 * @author Administrator <br/>
 * create at 2014-7-9 上午9:16:50
 */
public class Icd10 {
    private static final String TAG = "Icd10";
    int icdTenSn;
    String icdTenCode;
    String icdTenName;
    String icdTenMnemonics;

    public int getIcdTenSn() {
        return icdTenSn;
    }

    public void setIcdTenSn(int icdTenSn) {
        this.icdTenSn = icdTenSn;
    }

    public String getIcdTenCode() {
        return icdTenCode;
    }

    public void setIcdTenCode(String icdTenCode) {
        this.icdTenCode = icdTenCode;
    }

    public String getIcdTenName() {
        return icdTenName;
    }

    public void setIcdTenName(String icdTenName) {
        this.icdTenName = icdTenName;
    }

    public String getIcdTenMnemonics() {
        return icdTenMnemonics;
    }

    public void setIcdTenMnemonics(String icdTenMnemonics) {
        this.icdTenMnemonics = icdTenMnemonics;
    }
}
