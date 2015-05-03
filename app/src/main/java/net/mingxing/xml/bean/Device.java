package net.mingxing.xml.bean;

/**
 * Created by admin on 2015/2/7.
 * 封装设备信息
 */
public class Device {
    private String id = "1";
    private String name = "平板电脑";
    private String type = "硬件平台";
    private String brand = "三星";
    private String model = "T520";
    private String serialno = " ";

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getModel() {

        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {

        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
