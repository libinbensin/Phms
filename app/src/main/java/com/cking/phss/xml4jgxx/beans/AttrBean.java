/* Xinhuaxing Inc. (C) 2013. All rights reserved.
 *
 * AttrBean.java
 * classes : net.xinhuaxing.eshow.util.xml.beans.AttrBean
 * @author wation
 * V 1.0.0
 * Create at 2013-10-21 涓婂崍10:42:10
 */
package com.cking.phss.xml4jgxx.beans;


/**
 * 路径中的正反斜杠在这里统一修正为正斜杠
 * net.xinhuaxing.eshow.util.xml.beans.AttrBean
 * @author wation <br/>
 * create at 2013-10-21 上午10:42:10
 */
public class AttrBean implements Cloneable {
    private static final String TAG = "AttrBean";

    private String id; // 序号
    private String name = ""; // 仪器名称
    private String type = ""; // 仪器类型
    private String brand = ""; // 品牌
    private String model = ""; // 型号
    private String serialNo = ""; // 编号
    private String webserviceUrl = "";
    private String uploadKstjUrl = "";
    private String downloadKstjUrl = "";
    private String versionServiceUrl = "";
    private String dataVersionUrl = "";
    private String printHeader = "";
    private String printFooter = "";
    private String bridgeId = "";
    private String status = "";

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the tag
     */
    public static String getTag() {
        return TAG;
    }
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

    public String getWebserviceUrl() {
        return webserviceUrl;
    }

    public void setWebserviceUrl(String webserviceUrl) {
        this.webserviceUrl = webserviceUrl;
    }

    public String getUploadKstjUrl() {
        return uploadKstjUrl;
    }

    public void setUploadKstjUrl(String uploadKstjUrl) {
        this.uploadKstjUrl = uploadKstjUrl;
    }

    public String getDownloadKstjUrl() {
        return downloadKstjUrl;
    }

    public void setDownloadKstjUrl(String downloadKstjUrl) {
        this.downloadKstjUrl = downloadKstjUrl;
    }

    public String getVersionServiceUrl() {
        return versionServiceUrl;
    }

    public void setVersionServiceUrl(String versionServiceUrl) {
        this.versionServiceUrl = versionServiceUrl;
    }

    public String getPrintHeader() {
        return printHeader;
    }

    public void setPrintHeader(String printHeader) {
        this.printHeader = printHeader;
    }

    public String getPrintFooter() {
        return printFooter;
    }

    public void setPrintFooter(String printFooter) {
        this.printFooter = printFooter;
    }

    public String getBridgeId() {
        return bridgeId;
    }

    public void setBridgeId(String bridgeId) {
        this.bridgeId = bridgeId;
    }

    public String getDataVersionUrl() {
        return dataVersionUrl;
    }

    public void setDataVersionUrl(String dataVersionUrl) {
        this.dataVersionUrl = dataVersionUrl;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {

    	AttrBean cloneAttrBean = (AttrBean) super.clone();
        if (name != null) {cloneAttrBean.name = new String(name);}
        if (id != null) {cloneAttrBean.id = new String(id);}
        if (status != null) {cloneAttrBean.status = new String(status);}
        if (type != null) {
            cloneAttrBean.type = new String(type);
        }
        if (brand != null) {
            cloneAttrBean.brand = new String(brand);
        }
        if (model != null) {
            cloneAttrBean.model = new String(model);
        }
        if (serialNo != null) {
            cloneAttrBean.serialNo = new String(serialNo);
        }
        if (webserviceUrl != null) {
            cloneAttrBean.webserviceUrl = new String(webserviceUrl);
        }
        if (uploadKstjUrl != null) {
            cloneAttrBean.uploadKstjUrl = new String(uploadKstjUrl);
        }
        if (downloadKstjUrl != null) {
            cloneAttrBean.downloadKstjUrl = new String(downloadKstjUrl);
        }
        if (versionServiceUrl != null) {
            cloneAttrBean.versionServiceUrl = new String(versionServiceUrl);
        }
        if (dataVersionUrl != null) {
            cloneAttrBean.dataVersionUrl = new String(dataVersionUrl);
        }
        if (bridgeId != null) {
            cloneAttrBean.bridgeId = new String(bridgeId);
        }
        return cloneAttrBean;
    }
}
