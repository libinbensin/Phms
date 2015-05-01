/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * PatientInfo.java
 * classes : com.cking.phss.bean.PatientInfo
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-9 下午9:23:32
 */
package com.cking.phss.bean;

/**
 * com.cking.phss.bean.PatientInfo
 * @author Administrator <br/>
 * create at 2014-6-9 下午9:23:32
 */
public class PatientInfo {
    private static final String TAG = "PatientInfo";
    
    String id;
    String name;
    int sexCode;
    int age;
    String idcard;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSexCode() {
        return sexCode;
    }
    public void setSexCode(int sexCode) {
        this.sexCode = sexCode;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
