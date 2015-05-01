package com.cking.phss.dto.sjcx;

import java.util.List;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * -时间段内人员自助体检记录查询-
 * @author taowencong
 *
 */
public class Sjdnryzztjlucx implements IDto{
    @XmlTag(name = "Condition",hasSubTag=true)
    public Condition condition = null;
    
    @XmlTag(name = "Results",hasSubTag=true)
    public Results results = null;
        
        public static class Condition implements IDto{
          //身份证号
            @XmlTag(name = "IDCARD")
            public String IDCARD = "";
            
            //起始时间
            @XmlTag(name = "STARTTIME")
            public String STARTTIME="";
            
            //截止时间-
            @XmlTag(name = "ENDTIME")
            public String ENDTIME="";
        }
    
        
        public static class Results implements IDto{
            @XmlTag(name="Item",isListWithoutGroupTag=true)
            public List<Item> items; 
        }
        
        public static class  Item implements IDto{
            @XmlTag(name="NAME")
            public String NAME = "";
            
            @XmlTag(name="SEX")
            public String SEX = "";
            
            @XmlTag(name="IDCARD")
            public String IDCARD = "";
            
            @XmlTag(name="CHECKINID")
            public String CHECKINID = "";
            
            @XmlTag(name="CHECKTYPE")
            public String CHECKTYPE = "";
            
            @XmlTag(name="ORGNAME")
            public String ORGNAME = "";
            
            @XmlTag(name="CHECKTIME")
            public String CHECKTIME = "";
        }   
}
/**
 * <!--时间段内人员自助体检记录查询格式-->
<!--string SearchCheck(string data)-->
<!--返回错误信息,NULL为正常-->
<!--参数data的xml格式-->
<Condition>
    <!--身份证号-->
    <IDCARD>330596165302036201</IDCARD>
    <!--起始时间-->
    <STARTTIME>2012-04-12</STARTTIME>
    <!--截止时间-->
    <ENDTIME>2012-12-12</ENDTIME>
</Condition>

<Results>
  <Item>
    <!--姓名-->
    <NAME>张三</NAME>
    <!--性别-->
    <SEX>男</SEX>
    <!--身份证号-->
    <IDCARD>330596165302036201</IDCARD>
    <!--体检编号-->
    <CHECKINID>201205140001</CHECKINID>
    <!--体检类型-->
    <CHECKTYPE>自助体检</CHECKTYPE>
    <!--检查机构-->
    <ORGNAME>白鹤医院</ORGNAME>
    <!--检查时间-->
    <CHECKTIME>2012-04-12</CHECKTIME>
  </Item>
  <Item>
    <!--姓名-->
    <NAME>李四</NAME>
    <!--性别-->
    <SEX>男</SEX>
    <!--身份证号-->
    <IDCARD>330596189804235023</IDCARD>
    <!--体检编号-->
    <CHECKINID>201205120001</CHECKINID>
    <!--体检类型-->
    <CHECKTYPE>自助体检</CHECKTYPE>
    <!--检查机构-->
    <ORGNAME>白鹤医院</ORGNAME>
    <!--检查时间-->
    <CHECKTIME>2012-05-12</CHECKTIME>
  </Item>
</Results>
 */
