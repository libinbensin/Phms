/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Request.java
 * classes : com.cking.phss.xml.bean.Request
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 下午10:42:01
 */
package com.cking.phss.xml.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/**
 * com.cking.phss.xml.bean.Request
 * @author Administrator <br/>
 * create at 2012-9-22 下午10:42:01
 */
public class Request implements ITag {
    public static final String TAG = "Request";
    /**
     * 公共
     */
    //OrgCode='' OperType='10'
    public String mOrgCode = null;
    public String mOperType = null;
    
    /**
     * 登录
     */
    //<!--必填。 用户名-->
    public UserN mUserN = null;
    //<!--必填。 密码-->
    public PassWord mPassWord = null;
    
    /**
     * 国籍
     */
    //<!--必填。国家查询条件，在查询时用国家名称进行like查询 -->
    public Nationality mNationality = null;
    
    /**
     * TODO 添加其他XML中的Request字段
     */
    
    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        ser.startTag("", TAG);
        if (mOrgCode != null) {
            ser.attribute("", "OrgCode", mOrgCode);
        }
        if (mOperType != null) {
            ser.attribute("", "OperType", mOperType);
        }

        // 登录
        if (mUserN != null)
            mUserN.writeTag(ser);
        if (mPassWord != null)
            mPassWord.writeTag(ser);

        // 国籍
        if (mNationality != null)
            mNationality.writeTag(ser);

        ser.endTag("", TAG);
    }

    private List<ITag> tags = new ArrayList<ITag>();
    /* (non-Javadoc)
     * @see com.cking.phss.xml.bean.ITag#read(java.lang.String, java.lang.String)
     */
    @Override
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException, IOException {
//        String[] tagList = {
//                UserN.TAG, 
//                PassWord.TAG, 
//                Nationality.TAG};
//
//        ITag[] objList = {
//                mUserN, 
//                mPassWord, 
//                mNationality};
//        
//        ITag[] relList = {
//                new UserN(), 
//                new PassWord(), 
//                new Nationality()};

        String tag = parser.getName();
        switch (eventType) {
            case XmlPullParser.START_TAG:
                if (tag.equals(TAG)) {
                    mOrgCode = parser.getAttributeValue(0);
                    mOperType = parser.getAttributeValue(1);
                    return true;
                }
                
                //TagUtil.autoNewTag(tags, tag, tagList, objList, relList);

                // 登陆
                if (tag.equals(UserN.TAG)) {mUserN = new UserN();tags.add(mUserN);}
                if (tag.equals(PassWord.TAG)) {mPassWord = new PassWord();tags.add(mPassWord);}
                
                // 国籍
                if (tag.equals(Nationality.TAG)) {mNationality = new Nationality();tags.add(mNationality);}

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
    
//    @Override
//    public boolean read(XmlPullParser parser, int eventType) {
//        List<ITag> tags = new ArrayList<ITag>();
//        
//        switch (eventType) {
//            case XmlPullParser.START_TAG:
//                String tag = parser.getName();
//                if (tag.equals(TAG)) {
//                    mOrgCode = parser.getAttributeValue(0);
//                    mOperType = parser.getAttributeValue(1);
//                    return true;
//                }
//                
//                if (tag.equals(UserN.TAG)) {
//                    mUserN = new UserN();
//                    tags.add(mUserN);
//                }
//                if (tag.equals(PassWord.TAG)) {
//                    mPassWord = new PassWord();
//                    tags.add(mPassWord);
//                }
//                if (tag.equals(Nationality.TAG)) {
//                    mNationality = new Nationality();
//                    tags.add(mNationality);
//                }
//                
//                for (ITag itag : tags) {
//                    boolean ret = itag.read(parser, eventType);
//                    if (ret) {
//                        return ret;
//                    }
//                }
//                break;
//            case XmlPullParser.END_TAG:
//                break;
//
//        }
//        return false;
//    }
}
