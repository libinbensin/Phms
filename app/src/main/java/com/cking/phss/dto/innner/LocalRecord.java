/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Record.java
 * classes : com.cking.phss.dto.innner.Record
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-4 下午3:13:08
 */
package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.Record
 * @author Administrator <br/>
 * create at 2014-7-4 下午3:13:08
 */
public class LocalRecord implements IBean {
    private static final String TAG = "Record";

    // <!-- 必填。序号 -->
    @XmlTag(name = "Order")
    public String Order = "";
    // <!--必填。个人档案号-->
    @XmlTag(name = "ResidentID")
    public String ResidentID = "";
    // <!-- 必填。居民姓名 -->
    @XmlTag(name = "ResidentName")
    public String ResidentName = "";
    // <!-- 证件号码(一般为身份证) -->
    @XmlTag(name = "CredentialsNo")
    public String CredentialsNo = "";
    // <!--必填。性别，CD:代码（单选）。0）未知；1）男；2）女；9）未说明性别 -->
    @XmlTag(name = "Sex")
    public BeanCD Sex = null;
    // <!--必填。出生日期。格式：yyyy-mm-dd -->
    @XmlTag(name = "BirthDay")
    public String BirthDay = "";
    // <!--必填。类型，1.档案信息、2.快速体检、3.高血压随访、4.糖尿病随访-->
    @XmlTag(name = "ProjectType")
    public BeanCD ProjectType = null;
    // 数据来源
    @XmlTag(name = "DataSource")
    public BeanCD DataSource = null;
    // 操作标志
    @XmlTag(name = "OperType")
    public BeanCD OperType = null;
    // <!--必填。检查时间或创建修改时间。格式：yyyy-mm-dd -->
    @XmlTag(name = "CheckDate")
    public String CheckDate = "";
    // <!--必填。责任医生-->
    @XmlTag(name = "Doctor")
    public String Doctor = "";
    // <Doctor></Doctor>
    // <!--必填。上传状态 0.未上传，1.已上传-->
    @XmlTag(name = "Upload")
    public BeanCD Upload = null;

    @XmlTag(name = "ResidentUUID")
    public String ResidentUUID = "";
    @XmlTag(name = "ProjectUUID")
    public String ProjectUUID = "";
    @XmlTag(name = "ClassName")
    public String ClassName = "";
}
