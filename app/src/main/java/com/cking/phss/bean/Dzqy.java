package com.cking.phss.bean;

import java.util.List;

import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.xml.util.XmlTag;

/**
 * 电子签约
 * 
 * @author Administrator
 */
public class Dzqy implements IBean {
    // <!-- 是否签约 0:否；1:是 -->
    @XmlTag(name = "SignContract")
    public BeanCD SignContract = null;
    // <!-- 签约日期 -->
    @XmlTag(name = "SignDate")
    public String SignDate = "";
    // <!-- 签约地点 -->
    @XmlTag(name = "SignPlace")
    public String SignPlace = "";
    // <!-- 签约医生 -->
    @XmlTag(name = "SignDoctor")
    public String SignDoctor = "";
    // <!-- 签约单位 -->
    @XmlTag(name = "SignUnit")
    public BeanCD SignUnit = null;
    // <!-- 签约类型 -->
    @XmlTag(name = "SignType")
    public BeanCD SignType = null;
    // <!-- 签约知情；0：不同意，1：同意 -->
    @XmlTag(name = "SignKnow")
    public BeanCD SignKnow = null;
    // <!-- 授权电话 -->
    @XmlTag(name = "SignPhone")
    public String SignPhone = "";
    // <!-- 授权状态 -->
    @XmlTag(name = "SignStatus")
    public BeanCD SignStatus = null;
    // <!-- 操作医生（登陆医生） -->
    @XmlTag(name = "OperDoctor")
    public String OperDoctor = "";
    // <!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
    @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
    public List<DeviceUse> deviceUses = null;
}
