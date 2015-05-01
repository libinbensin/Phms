/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Jgxx.java
 * classes : com.cking.phss.bean.Jgxx
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-25 下午4:05:16
 */
package com.cking.phss.bean;

/**
 * com.cking.phss.bean.Jgxx
 * @author Administrator <br/>
 * create at 2014-6-25 下午4:05:16
 */
public class Jgxx {
    private static final String TAG = "Jgxx";
    
    // 机构代码
    private String jgdm = "";
    // 机构名称
    private String jgmc = "";
    // 设备信息列表
    // 1 平板电脑
    // 2 身份证阅读器
    // 3 射频读卡器
    // 4 血压计
    // 5 血糖仪
    // 6 血脂仪
    // 7 电子腰围尺
    // 8 血氧仪
    // 9 人体成分仪
    // 10 心电图机
    // 11 热敏打印机
    private DeviceInfo[] deviceInfos = new DeviceInfo[11];
    
    public class DeviceInfo {
        private int id; // 序号
        private String name = ""; // 仪器名称
        private String type = ""; // 仪器类型
        private String brand = ""; // 品牌
        private String model = ""; // 型号
        private String serialNo = ""; // 编号
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getBrand() {
            return brand;
        }
        public void setBrand(String brand) {
            this.brand = brand;
        }
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
        public String getSerialNo() {
            return serialNo;
        }
        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }
    }

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }
    
    public DeviceInfo[] getDeviceInfos() {
        return deviceInfos;
    }
    
    public DeviceInfo getDeviceInfo(int index) {
        return deviceInfos[index];
    }

    public void setDeviceInfo(int index, DeviceInfo deviceInfo) {
        this.deviceInfos[index] = deviceInfo;
    }
}
