package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

public class Glucose implements IDto {

    // <!-- id编号-->
    @XmlTag(name = "id")
    public String id = "";

    // <!-- 数据基站id-->
    @XmlTag(name = "bridgeId")
    public String bridgeId = "";

    // <!-- 设备编号-->
    @XmlTag(name = "deviceSn")
    public String deviceSn = "";

    // <!-- 血糖类型-->
    @XmlTag(name = "glucoseType")
    public String glucoseType = "";

    // <!-- 血糖值-->
    @XmlTag(name = "glucoseValue")
    public String glucoseValue = "";

    // <!-- 测试日期-->
    @XmlTag(name = "measurementDate")
    public String measurementDate = "";

    // <!-- 平台id -->
    @XmlTag(name = "pfId")
    public String pfId = "";

    // <!-- 系统时间-->
    @XmlTag(name = "timeType")
    public String timeType = "";
}  







			
