/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * BloodOxygen.java
 * classes : com.cking.phss.bean.BloodOxygen
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-10 下午4:01:24
 */
package com.cking.phss.bean;

/**
 * com.cking.phss.bean.BloodOxygen
 * @author Administrator <br/>
 * create at 2014-6-10 下午4:01:24
 */
public class BloodOxygen {
    private static final String TAG = "BloodOxygen";
    int signal;
    int searchTimeLong;
    int spo2Low;
    int pulseWave;
    int pi;
    int proberError;
    int searchPulse;
    int spo2;
    int realPulseRate;
    
    public int getSignal() {
        return signal;
    }
    public void setSignal(int signal) {
        this.signal = signal;
    }
    public int getSearchTimeLong() {
        return searchTimeLong;
    }
    public void setSearchTimeLong(int searchTimeLong) {
        this.searchTimeLong = searchTimeLong;
    }
    public int getSpo2Low() {
        return spo2Low;
    }
    public void setSpo2Low(int spo2Low) {
        this.spo2Low = spo2Low;
    }
    public int getPulseWave() {
        return pulseWave;
    }
    public void setPulseWave(int pulseWave) {
        this.pulseWave = pulseWave;
    }
    public int getPi() {
        return pi;
    }
    public void setPi(int pi) {
        this.pi = pi;
    }
    public int getProberError() {
        return proberError;
    }
    public void setProberError(int proberError) {
        this.proberError = proberError;
    }
    public int getSearchPulse() {
        return searchPulse;
    }
    public void setSearchPulse(int searchPulse) {
        this.searchPulse = searchPulse;
    }
    public int getSpo2() {
        return spo2;
    }
    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }
    public int getRealPulseRate() {
        return realPulseRate;
    }
    public void setRealPulseRate(int realPulseRate) {
        this.realPulseRate = realPulseRate;
    }
}
