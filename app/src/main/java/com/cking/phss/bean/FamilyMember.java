/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * FamilyMember.java
 * classes : com.cking.phss.bean.FamilyMember
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-27 下午3:28:20
 */
package com.cking.phss.bean;

import java.util.List;

import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.bean.FamilyMember
 * 
 * @author Administrator <br/>
 *         create at 2014-6-27 下午3:28:20
 */
public class FamilyMember implements IBean {
    // <!-- 成员，有多条数据，因此会有多个Member节点 -->
    @XmlTag(name = "Member", isListWithoutGroupTag = true)
    public List<Member> members = null;

    static public class Member implements IBean {

        // <!--成员编号， 新建时系统自动生成 -->
        @XmlTag(name = "MemberID")
        public String memberID = "";
        // <!--个人档案号-->
        @XmlTag(name = "ResidentID")
        public String residentID = "";
        // <!--成员姓名-->
        @XmlTag(name = "MemberName")
        public String memberName = "";
        // <!-- 性别,值为：0）未知；1）男；2）女；9）未说明性别-->
        @XmlTag(name = "Sex")
        public BeanCD sex = null;
        // <!--出生日期。格式：yyyy-mm-dd-->
        @XmlTag(name = "BirthDay")
        public String birthDay = null;
        // <!-- 证件类型 -->
        @XmlTag(name = "Credentials")
        public BeanCD credentialsCD = null;
        // <!-- 证件号 -->
        @XmlTag(name = "CredentialsNo")
        public String credentialsNo = "";
        // <!-- 文化程度，值为：10.研究生教育 11.博士研究生毕业 12.博士研究生结业 13.博士研究生肄业
        // 14.硕士研究生毕业 15.硕士研究生结业 16.硕士研究生肄业 17.研究生班毕业 18.研究生班结业 19.研究生肄业
        // 20.大学本科 30.专科教育 21.大学本科毕业 22.大学本科结业 23.大学本科肄业 28.大学普通班毕业
        // 31.大学专科毕业 32.大学专科结业 33.大学专科肄业 40.中等职业教育 41.中等专科毕业 42.中等专科结业
        // 43.中等专科肄业 44.职业高中毕业 45.职业高中结业 46.职业高中肄业 47.技工学校毕业 48.技工学校结业
        // 49.技工学校肄业 60.普通高级中学教育 61.普通高中毕业 62.普通高中结业 63.普通高中肄业 70.初级中学教育
        // 71.初中毕业 73.初中肄业 80.小学教育 81.小学毕业 83.小学肄业 90.其他 -->
        @XmlTag(name = "Education")
        public BeanCD educationCD = null;
        // <!-- 婚姻状况,值为：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
        // -->
        @XmlTag(name = "MaritalStatus")
        public BeanCD maritalStatusCD = null;
        // <!-- 本人电话（宅电） -->
        @XmlTag(name = "Telephone")
        public String telephone = "";
        // <!-- 手机号 -->
        @XmlTag(name = "MobilePhone")
        public String mobilePhone = "";
        // <!--户主 -->
        @XmlTag(name = "Householder")
        public String householder = "";
        // <!-- 与户主关系 -->
        @XmlTag(name = "RelationToHouseholder")
        public BeanCD relationToHouseholderCD = null;
        // <!-- 现住地址 -->
        @XmlTag(name = "Address")
        public String address = "";
        // <!-- 行业 -->
        @XmlTag(name = "Industry")
        public BeanCD industryCD = null;
        // <!-- 职业 -->
        @XmlTag(name = "Occupation")
        public BeanCD occupationCD = null;
        // <!-- 个人档案状态。值为状态：0.正常；1.注销；2.死亡；3.迁出(区域外地)；4.挂起；5.临时 -->
        @XmlTag(name = "ArchivesStatus")
        public BeanCD archivesStatusCD = null;
        // <!-- 备注 -->
        @XmlTag(name = "Remark")
        public String remark = "";
    }
}
