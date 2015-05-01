package com.cking.phss.dto.sjcx;

import java.util.List;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * 人员指标数据查询
 * 
 * @author taowencong
 * 
 */
public class Ryzbsjcx implements IDto {

    @XmlTag(name = "Results", hasSubTag = true)
    public Results results = null;

    public static class Results implements IDto {
        @XmlTag(name = "Item", isListWithoutGroupTag = true)
        public List<Item> items;
    }

    public static class Item implements IDto {
        
        // 项目编号
        @XmlTag(name = "PROJECTCODE")
        public String PROJECTCODE = "";

        // 项目名称
        @XmlTag(name = "PROJECTNAME")
        public String PROJECTNAME = "";

        // 接口指标编号（设置的对应编号）
        @XmlTag(name = "TARGETID")
        public String TARGETID = "";

        // -项目名称
        @XmlTag(name = "TARGETNAME")
        public String TARGETNAME = "";
        
     // 检查时间
        @XmlTag(name = "CHECKTIME")
        public String CHECKTIME = "";
        
     // -项目名称
        @XmlTag(name = "RESULT")
        public String RESULT = "";
        
     // 单位
        @XmlTag(name = "UNIT")
        public String UNIT = "";
        
     // 参考范围-
        @XmlTag(name = "REFERENCE")
        public String REFERENCE = "";
        
     //-结果标记-
        @XmlTag(name = "EXCEPTIONTIPS")
        public String EXCEPTIONTIPS = "";
        
    }

}
/**
<!--人员指标数据查询格式-->
<!--checkinid为体检编号-->
<!--string SearchResultsByCheckinId(string checkinid)-->
<!--返回错误信息,NULL为正常-->
<!--返回数据XML格式-->
<Results>
  <Item>
    <!--项目编号-->
    <PROJECTCODE>XDSC01</PROJECTCODE>
    <!--项目名称-->
    <PROJECTNAME>身高体重</PROJECTNAME>
    <!--接口指标编号（设置的对应编号）-->
    <TARGETID>XDSC1000001</TARGETID>
    <!--项目名称-->
    <TARGETNAME>身高</TARGETNAME>
    <!--检查时间-->
    <CHECKTIME>2012-04-12</CHECKTIME>
    <!--结果-->
    <RESULT>170</RESULT>
    <!--单位-->
    <UNIT>CM</UNIT>
    <!--参考范围-->
    <REFERENCE>0-200</REFERENCE>
    <!--结果标记-->
    <EXCEPTIONTIPS>√</EXCEPTIONTIPS>
  </Item>
  <Item>
    <PROJECTCODE>XDSC02</PROJECTCODE>
    <PROJECTNAME>血压</PROJECTNAME>
    <TARGETID>XDSC2000001</TARGETID>
    <TARGETNAME>血压</TARGETNAME>
    <CHECKTIME>2012-04-12</CHECKTIME>
    <RESULT>90/120</RESULT>
    <UNIT>mmg</UNIT>
    <REFERENCE>70-90/100-150</REFERENCE>
    <EXCEPTIONTIPS>√</EXCEPTIONTIPS>
  </Item>
</Results>*/