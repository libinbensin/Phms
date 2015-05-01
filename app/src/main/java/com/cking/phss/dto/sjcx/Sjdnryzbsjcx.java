package com.cking.phss.dto.sjcx;

import java.util.List;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * 时间段内人员指标数据查询格式
 * 
 * @author taowencong
 * 
 */
public class Sjdnryzbsjcx implements IDto {

    @XmlTag(name = "Condition", hasSubTag = true)
    public Condition condition = null;

    @XmlTag(name = "Results", hasSubTag = true)
    public Results results = null;

    public static class Condition implements IDto {
        // 姓名
        @XmlTag(name = "NAME")
        public String NAME = "";

        // 身份证号
        @XmlTag(name = "IDCARD")
        public String IDCARD = "";

        // 指标编号
        @XmlTag(name = "TARGETID")
        public String TARGETID = "";

        // 项目名称
        @XmlTag(name = "TARGETNAME")
        public String TARGETNAME = "";

        // 起始时间
        @XmlTag(name = "STARTTIME")
        public String STARTTIME = "";

        // 截止时间-
        @XmlTag(name = "ENDTIME")
        public String ENDTIME = "";
    }

    public static class Results implements IDto {
        @XmlTag(name = "Item", isListWithoutGroupTag = true)
        public List<Item> items;
    }

    public static class Item implements IDto {
        // 指标编号-->
        @XmlTag(name = "TARGETID")
        public String TARGETID = "";

        // 项目名称
        @XmlTag(name = "TARGETNAME")
        public String TARGETNAME = "";

        // 检查时间
        @XmlTag(name = "CHECKTIME")
        public String CHECKTIME = "";

        // 结果
        @XmlTag(name = "RESULT")
        public String RESULT = "";
    }

}
/**
 * 
 <!--时间段内人员指标数据查询格式--> <!--string SearchResults(string data)-->
 * <!--返回错误信息,NULL为正常--> <!--参数data的xml格式--> <Condition>
 * 
 * </Condition>
 * 
 * <!--时间段内人员指标数据返回数据格式--> <!--返回错误信息,NULL为正常--> <Results> <Item> <!--指标编号-->
 * <TARGETID>01</TARGETID> <!--项目名称--> <TARGETNAME>身高</TARGETNAME> <!--检查时间-->
 * <CHECKTIME>2012-04-12</CHECKTIME> <!--结果--> <RESULT>80/110</RESULT> </Item>
 * <Item> <!--指标编号--> <TARGETID>01</TARGETID> <!--项目名称-->
 * <TARGETNAME>身高</TARGETNAME> <!--检查时间--> <CHECKTIME>2012-04-12</CHECKTIME>
 * <!--结果--> <RESULT>90/120</RESULT> </Item> </Results>
 * 
 * 先德查询约定项目对应码 TARGETID 值 01 体重 02 血压 03 血糖 16甘油三酯 17胆固醇
 */
