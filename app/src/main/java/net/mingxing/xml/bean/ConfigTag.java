package net.mingxing.xml.bean;

import java.util.List;

/**
 * Created by admin on 2015/2/7.
 * appconfig.xml 实体类
 */
public class ConfigTag {

    private String serialno; // 设备编号
    private String id;  // 平台编号
    private String bridgeid;  // 基站编号

    private List<HospitalTag> hospitalTags; // hospital集合
    private List<Device> devices;   // device集合

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<HospitalTag> getHospitalTags() {
        return hospitalTags;
    }

    public void setHospitalTags(List<HospitalTag> hospitalTags) {
        this.hospitalTags = hospitalTags;
    }

    public void setBridgeid(String bridgeid) {
        this.bridgeid = bridgeid;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setSerialno(String serialno) {

        this.serialno = serialno;
    }

    public String getBridgeid() {
        return bridgeid;
    }

    public String getId() {

        return id;
    }

    public String getSerialno() {

        return serialno;
    }
}
