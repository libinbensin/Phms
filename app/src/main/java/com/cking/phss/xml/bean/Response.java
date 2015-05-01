/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Response.java
 * classes : com.cking.phss.xml.bean.Response
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 下午10:49:55
 */
package com.cking.phss.xml.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/**
 * com.cking.phss.xml.bean.Response
 * @author Administrator <br/>
 * create at 2012-9-22 下午10:49:55
 */
public class Response implements ITag {
    public static final String TAG = "Response";
    
    /**
     * 公共
     */
    //<!--对于查询确实无符合条件的，则ErrMsg应该为空，只不过Response节点无子节点 -->
    public String mErrMsg = null;
    
    /**
     * 登录
     */
    //<!--如果成功则返回相应用户ID或代码 -->
    public UserID mUserID = null;
    //<!--用户登录名 -->
    public UserName mUserName = null;
    //<!-- 相应医生ID或代码-->
    public DoctorID mDoctorID = null;
    //<!-- 相应医生姓名-->
    public DoctorName mDoctorName = null;
    //<!--必填。此社区卫生服务中心所在省，值：省名，ID：省ID或代码 -->
    public Province mProvince = null;
    //<!--必填。此社区卫生服务中心所在市，值：市名，ID：市ID或代码 -->
    public City mCity = null;
    //<!--必填。此社区卫生服务中心所在区县，值：区县名，ID：区县ID或代码 -->
    public District mDistrict = null;
    //<!--必填。此社区卫生服务中心所在街道或镇，值：街道名，ID：街道ID或代码 -->
    public Street mStreet = null;
    //<!--必填。值：此用户工号，ID：相应代码或ID -->
    public EmployeeNo mEmployeeNo = null;
    //<!-- 此用户所属角色代码。值为代码：1.管理员；2.院长；3.团队长；4.一般成员-->
    public RoleCD mRoleCD = null;
    //<!--此用户可以管辖哪些社区（村）或者居委会 -->
    public Zones mZones = null;

    /**
     * 国籍
     */
    //<!--成功：有多条记录，按NationalityCD升序。值：国家名称，ID：ID或代码 -->
    public NationalityCD mNationalityCD = null;

    /**
     * TODO 添加其他XML中的Response字段
     */
    
    public void writeTag(XmlSerializer ser) throws IllegalArgumentException,
            IllegalStateException, IOException {
        ser.startTag("", TAG);
        if (mErrMsg != null) {
            ser.attribute("", "ErrMsg", mErrMsg);
        }

        // 登录
        if (mUserID != null)
            mUserID.writeTag(ser);
        if (mUserName != null)
            mUserName.writeTag(ser);
        if (mDoctorID != null)
            mDoctorID.writeTag(ser);
        if (mDoctorName != null)
            mDoctorName.writeTag(ser);
        if (mProvince != null)
            mProvince.writeTag(ser);
        if (mCity != null)
            mCity.writeTag(ser);
        if (mDistrict != null)
            mDistrict.writeTag(ser);
        if (mStreet != null)
            mStreet.writeTag(ser);
        if (mEmployeeNo != null)
            mEmployeeNo.writeTag(ser);
        if (mRoleCD != null)
            mRoleCD.writeTag(ser);
        if (mZones != null)
            mZones.writeTag(ser);

        // 国籍
        if (mNationalityCD != null)
            mNationalityCD.writeTag(ser);

        ser.endTag("", TAG);
    }

    private List<ITag> tags = new ArrayList<ITag>();
    /* (non-Javadoc)
     * @see com.cking.phss.xml.bean.ITag#read(java.lang.String, java.lang.String)
     */
    @Override
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException, IOException {
//        String[] tagList = {
//                UserID.TAG, 
//                UserName.TAG, 
//                DoctorID.TAG, 
//                DoctorName.TAG, 
//                Province.TAG, 
//                City.TAG, 
//                District.TAG, 
//                Street.TAG, 
//                EmployeeNo.TAG, 
//                RoleCD.TAG,
//                Zones.TAG};
//
//        ITag[] objList = {
//                mUserID, 
//                mUserName, 
//                mDoctorID, 
//                mDoctorName, 
//                mProvince, 
//                mCity, 
//                mDistrict, 
//                mStreet, 
//                mEmployeeNo, 
//                mRoleCD,
//                mZones};
//        ITag[] relList = {
//                new UserID(), 
//                new UserName(), 
//                new DoctorID(), 
//                new DoctorName(), 
//                new Province(), 
//                new City(), 
//                new District(), 
//                new Street(), 
//                new EmployeeNo(), 
//                new RoleCD(),
//                new Zones()};

        String tag = parser.getName();
        switch (eventType) {
            case XmlPullParser.START_TAG:
                if (tag.equals(TAG)) {
                    mErrMsg = parser.getAttributeValue(0);
                    return true;
                }

                //TagUtil.autoNewTag(tags, tag, tagList, objList, relList);

                // 登陆
                if (tag.equals(UserID.TAG)) {mUserID = new UserID();tags.add(mUserID);}
                if (tag.equals(UserName.TAG)) {mUserName = new UserName();tags.add(mUserName);}
                if (tag.equals(DoctorID.TAG)) {mDoctorID = new DoctorID();tags.add(mDoctorID);}
                if (tag.equals(DoctorName.TAG)) {mDoctorName = new DoctorName();tags.add(mDoctorName);}
                if (tag.equals(Province.TAG)) {mProvince = new Province();tags.add(mProvince);}
                if (tag.equals(City.TAG)) {mCity = new City();tags.add(mCity);}
                if (tag.equals(District.TAG)) {mDistrict = new District();tags.add(mDistrict);}
                if (tag.equals(Street.TAG)) {mStreet = new Street();tags.add(mStreet);}
                if (tag.equals(EmployeeNo.TAG)) {mEmployeeNo = new EmployeeNo();tags.add(mEmployeeNo);}
                if (tag.equals(RoleCD.TAG)) {mRoleCD = new RoleCD();tags.add(mRoleCD);}
                if (tag.equals(Zones.TAG)) {mZones = new Zones();tags.add(mZones);}
                
                // 国籍
                if (tag.equals(NationalityCD.TAG)) {mNationalityCD = new NationalityCD();tags.add(mNationalityCD);}
                
                for (ITag itag : tags) {
                    boolean ret = itag.read(parser, eventType);
                    if (ret) {
                        return ret;
                    }
                }
                break;
            case XmlPullParser.END_TAG:
                if (tag.equals(TAG)) {
                    tags.clear();
                }
                break;

        }
        return false;
    }
}
