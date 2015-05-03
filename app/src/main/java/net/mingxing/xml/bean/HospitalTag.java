package net.mingxing.xml.bean;

/**
 * Created by admin on 2015/2/7.
 * 封装 /sdcard/pmhs/xml/appconfig.xml Hospital 数据
 */
public class HospitalTag {

    private String serialno = "2354489";
    private String status = "0";
    private String name = "上海先德医疗设备有限公司";
    private String webserviceurl = "http://shmed.eicp.net:8038/HandeService.asmx/HealthPADBus";
    private String uploadkstjurl = "http://shmed.eicp.net:8038/HandeService.asmx";
    private String downloadkstjurl = "http://shmed.eicp.net:8038/HandeService.asmx?op=GetWaistData";
    private String versionserviceurl = "http://shmed.eicp.net:8038/HandeService.asmx";
    private String dataversionurl = "http://xinhuaxing.net/download/phms.zip";
    private String printheader = "宁波市居民移动体检平台";
    private String printfooter = "社区名称：白鹤街道社区卫生服务中心&#x000D;&#x000A;社区地址：宁波市江东区甬港南路225号&#x000D;&#x000A;电    话：0574-88222999&#x000D;&#x000A;服务热线：4006-772-882&#x000D;&#x000A;";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrintfooter() {
        return printfooter;
    }

    public void setPrintfooter(String printfooter) {
        this.printfooter = printfooter;
    }

    public String getPrintheader() {

        return printheader;
    }

    public void setPrintheader(String printheader) {
        this.printheader = printheader;
    }

    public String getDataversionurl() {

        return dataversionurl;
    }

    public void setDataversionurl(String dataversionurl) {
        this.dataversionurl = dataversionurl;
    }

    public String getVersionserviceurl() {

        return versionserviceurl;
    }

    public void setVersionserviceurl(String versionserviceurl) {
        this.versionserviceurl = versionserviceurl;
    }

    public String getDownloadkstjurl() {

        return downloadkstjurl;
    }

    public void setDownloadkstjurl(String downloadkstjurl) {
        this.downloadkstjurl = downloadkstjurl;
    }

    public String getUploadkstjurl() {

        return uploadkstjurl;
    }

    public void setUploadkstjurl(String uploadkstjurl) {
        this.uploadkstjurl = uploadkstjurl;
    }

    public String getWebserviceurl() {

        return webserviceurl;
    }

    public void setWebserviceurl(String webserviceurl) {
        this.webserviceurl = webserviceurl;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}
