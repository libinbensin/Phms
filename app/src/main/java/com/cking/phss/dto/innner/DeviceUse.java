package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

public class DeviceUse implements IDto {
	//@XmlAttribute(name = "!-- 数据来源类型， CD：代码；1.使用软件的设备，2.高血压检测设备，3.糖尿病检测设备, 4.血脂检测设备，99.其他-->
    @XmlTag(name = "DataType")
	public BeanCD dataType = null;
	//@XmlAttribute(name = "!-- 所使用的设备名称-->
    @XmlTag(name = "Device")
	public BeanCD device = null;
	//@XmlAttribute(name = "!--所使用的设备的品牌或厂商-->
    @XmlTag(name = "DeviceBrand")
	public BeanCD deviceBrand = null;
	//@XmlAttribute(name = "!--所使用的设备的型号-->
    @XmlTag(name = "DeviceType")
	public BeanCD deviceType = null;
	//@XmlAttribute(name = "!-- 所使用的设备序列号（如：padA0006788） -->
    @XmlTag(name = "DeviceSn")
	public String deviceSn= ""; 
	//@XmlAttribute(name = "!-- 所属先德系列，CD：ID或代码；1.随访包,2.随访箱,3.健康小屋 -->
    @XmlTag(name = "HandeType")
	public BeanCD handeType = null;
}