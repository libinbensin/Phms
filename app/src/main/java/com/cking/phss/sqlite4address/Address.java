/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Privence.java
 * classes : com.cking.phss.sqlite4address.Privence
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-13 下午4:57:59
 */
package com.cking.phss.sqlite4address;

/**
 * com.cking.phss.sqlite4address.Address
 * @author Administrator <br/>
 * create at 2014-6-13 下午4:57:59
 */
public class Address {
    private static final String TAG = "Address";
    int sn;
    String foreignKey;
    String code;
    String value;
    public int getSn() {
        return sn;
    }
    public void setSn(int sn) {
        this.sn = sn;
    }
    public String getForeignKey() {
        return foreignKey;
    }
    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
