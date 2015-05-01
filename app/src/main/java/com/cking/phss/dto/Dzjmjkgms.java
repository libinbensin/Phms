package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

public class Dzjmjkgms implements IDto{
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "J009";
        
        @XmlTag(name = "UserID")
        public String userID = "";
        
        @XmlTag(name = "ResidentID")
        public String residentID = "";
        
        
        @XmlTag(name = "HyperTypeCD")
        public String hyperTypeCD = "";

		//过敏类型文字值 
		@XmlTag(name = "HyperTypeName")
		public String hyperTypeName = "";
      
        
        @XmlTag(name = "HyperSn")
        public String hyperSn = "";
        
        @XmlTag(name = "HyperOperType")
        public int hyperOperType = 1;
        
        @XmlTag(name = "HyperSource")
        public BeanID hyperSource ;
        
        @XmlTag(name = "HappenDate")
        public String happenDate ="";
        
        @XmlTag(name = "HyperReason")
        public String hyperReason ="";
        
        @XmlTag(name = "CureDes")
        public String cureDes ="";

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
        public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
    }

    /**
     * Response
     * 
     * @author Administrator
     */
    @XmlTag(name = "Response")
    public Response response = null;

    static public class Response implements IDto {
        @XmlAttribute(name = "ErrMsg")
        public String errMsg = "";

        @XmlTag(name = "ResidentID")
        public String residentID = "";
        
        @XmlTag(name = "HyperSn")
        public String hyperSn = "";
    }

}
/**
 * <?xml version="1.0" encoding="utf-8"?>
<!--每个协议有Request或Response两个节点，说明一个是请求，另一个假如请求成功，则返回相应结果 -->
<!-- 在请求时无Response节点，同样在返回时也没有Request节点-->
<Body >
  <Request OrgCode='' OperType='J009'>
    <!--前提已经建立此居民的基本信息 -->
        <!--必填。 操作用户ID或代码  -->
        <UserID></UserID>
        <!--必填。个人档案号-->
        <ResidentID></ResidentID>
    <!--必填。过敏类型代码（单选）。值为代码：1：非药品过敏；2：药品过敏 -->
    <HyperTypeCD></HyperTypeCD>
        <!--过敏史序号 -->    
        <HyperSn></HyperSn>
        <!--过敏史操作类型 1新增2修改3删除 -->    
        <HyperOperType></HyperOperType>
    <!--必填。值：过敏源名称，ID：相应代码或ID -->
    <HyperSource ID=''></HyperSource>
    <!--必填。发病日期，格式：yyyy-mm-dd -->
    <HappenDate></HappenDate>
    <!-- 过敏原因-->
    <HyperReason></HyperReason>
    <!--治疗描述 -->
    <CureDes></CureDes>  
  </Request>
  <!--ErrMsg：如果错误则此处是错误信息（如节点无效或条件不足等），不可预料错误则以Err开头，以及Response节点下不能有子节点 -->
  <Response ErrMsg=''>
    <!--如果保存成功则返回个人档案号、过敏史序号 -->    
    <!--个人档案号-->
    <ResidentID></ResidentID>
    <!--过敏史序号 -->    
    <HyperSn></HyperSn>
  </Response>
</Body>
 */
