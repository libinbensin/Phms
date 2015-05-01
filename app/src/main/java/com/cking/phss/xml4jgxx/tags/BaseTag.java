/* Xinhuaxing Inc. (C) 2012. All rights reserved.
 *
 * Body.java
 * classes : com.cking.phss.xml.bean.Body
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 涓嬪崍11:12:42
 */
package com.cking.phss.xml4jgxx.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.xinhuaxing.interfaces.ITag;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml4jgxx.beans.AttrBean;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;
import com.cking.phss.xml4jgxx.tags.util.TagFactory;

/**
 * com.cking.phss.xml.bean.Body
 * @author Administrator <br/>
 * create at 2012-9-22 下午11:12:42
 */
public abstract class BaseTag implements ITag, Cloneable {
    public static final String TAG = "BaseTag";

    ITag parentTag = null; // 父标签，用于反向寻址（根据子标签找到父标签）
    
    private List<ITag> mTags = new ArrayList<ITag>();
    private String mValue = null; // 值
    public AttrBean attrBean = new AttrBean(); // 属性
    public String tmpData = null; // 临时数据，以‘|’分隔
    
    private boolean mHasReadAttr = false; // 已经读了属性了
    
    /**
     * 获取XML_TAG
     * @return
     */
    public abstract String getXmlTag();
    
    /**
     * 获取有效的属性
     * @return
     */
    protected abstract String[] getValidAttr();
    
    public String getValue() {
    	return mValue;
    }
    
    /**
     * 写属性
     * @param ser
     */
    protected void writeAttr(String namespace, XmlSerializer ser) {
        String[] attrs = getValidAttr();
        Arrays.sort(attrs); // 二分法搜索前必须排序

        try {
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_ID) != -1
                    && attrBean.getId() != null) {
                ser.attribute(namespace,  TagConstants.XML_ATTR_ID, attrBean.getId());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_NAME) != -1
                    && attrBean.getName() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_NAME, attrBean.getName());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_TYPE) != -1
                    && attrBean.getType() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_TYPE, attrBean.getType());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_BRAND) != -1
                    && attrBean.getBrand() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_BRAND, attrBean.getBrand());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_MODEL) != -1
                    && attrBean.getModel() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_MODEL, attrBean.getModel());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_SERIALNO) != -1
                    && attrBean.getSerialNo() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_SERIALNO, attrBean.getSerialNo());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_WEBSERVICEURL) != -1
                    && attrBean.getWebserviceUrl() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_WEBSERVICEURL,
                        attrBean.getWebserviceUrl());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_UPLOADKSTJURL) != -1
                    && attrBean.getUploadKstjUrl() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_UPLOADKSTJURL,
                        attrBean.getUploadKstjUrl());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_DOWNLOADKSTJURL) != -1
                    && attrBean.getDownloadKstjUrl() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_DOWNLOADKSTJURL,
                        attrBean.getDownloadKstjUrl());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_VERSIONSERVICEURL) != -1
                    && attrBean.getVersionServiceUrl() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_VERSIONSERVICEURL,
                        attrBean.getVersionServiceUrl());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_PRINTHEADER) != -1
                    && attrBean.getPrintHeader() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_PRINTHEADER,
                        attrBean.getPrintHeader());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_PRINTFOOTER) != -1
                    && attrBean.getPrintFooter() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_PRINTFOOTER,
                        attrBean.getPrintFooter());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_BRIDGEID) != -1
                    && attrBean.getBridgeId() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_BRIDGEID, attrBean.getBridgeId());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_DATAVERSIONURL) != -1
                    && attrBean.getDataVersionUrl() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_DATAVERSIONURL,
                        attrBean.getDataVersionUrl());
            }
            if (Arrays.binarySearch(attrs, TagConstants.XML_ATTR_STATUS) != -1
                    && attrBean.getStatus() != null) {
                ser.attribute(namespace, TagConstants.XML_ATTR_STATUS,
                        attrBean.getStatus());
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读属性
     * @param parser
     */
    protected void readAttr(String namespace, XmlPullParser parser) {
        attrBean.setId(parser.getAttributeValue(namespace, TagConstants.XML_ATTR_ID));
        attrBean.setName(parser.getAttributeValue(namespace, TagConstants.XML_ATTR_NAME));
        attrBean.setType(parser.getAttributeValue(namespace, TagConstants.XML_ATTR_TYPE));
        attrBean.setBrand(parser.getAttributeValue(namespace, TagConstants.XML_ATTR_BRAND));
        attrBean.setModel(parser.getAttributeValue(namespace, TagConstants.XML_ATTR_MODEL));
        attrBean.setSerialNo(parser.getAttributeValue(namespace, TagConstants.XML_ATTR_SERIALNO));
        attrBean.setWebserviceUrl(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_WEBSERVICEURL));
        attrBean.setUploadKstjUrl(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_UPLOADKSTJURL));
        attrBean.setDownloadKstjUrl(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_DOWNLOADKSTJURL));
        attrBean.setVersionServiceUrl(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_VERSIONSERVICEURL));
        attrBean.setPrintHeader(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_PRINTHEADER));
        attrBean.setPrintFooter(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_PRINTFOOTER));
        attrBean.setBridgeId(parser.getAttributeValue(namespace, TagConstants.XML_ATTR_BRIDGEID));
        attrBean.setDataVersionUrl(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_DATAVERSIONURL));
        attrBean.setStatus(parser.getAttributeValue(namespace,
                TagConstants.XML_ATTR_STATUS));
    }
    
    @Override
    public void write(XmlSerializer ser) throws IllegalArgumentException,
            IllegalStateException, IOException {
        ser.startTag(TagConstants.XML_NAMESPACE, getXmlTag());
        
        // 写属性
        writeAttr(TagConstants.XML_NAMESPACE, ser);
        
        // 写标签
        for (ITag tag : mTags) {
            tag.write(ser);
        }
        
        ser.endTag(TagConstants.XML_NAMESPACE, getXmlTag());
    }

    /* (non-Javadoc)
     * @see com.cking.phss.xml.bean.ITag#read(org.xmlpull.v1.XmlPullParser, int)
     */
    @Override
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException,
            IOException {
        boolean ret = true;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tag = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    // 对于标签名相同的，根据是否已经读取属性了来判断
                    if (tag.equals(getXmlTag()) && (!mHasReadAttr)) {// 开始读当前标签
                        readAttr(TagConstants.XML_NAMESPACE, parser);
                        mHasReadAttr = true;
                    } else {// 读子标签
                        ITag itag = TagFactory.getNewInstanceOfTag(tag);
                        itag.read(parser, eventType);
                        ((BaseTag) itag).setParentTag(this); // 设置父标签
                        mTags.add(itag);
                        //ret &= subRet;
                    }
                    break;
                    
                case XmlPullParser.TEXT:
                    // 读子标签
                    mValue = parser.getText().trim();
                    break;
                    
                case XmlPullParser.END_TAG:
                    if (tag.equals(getXmlTag())) {// 当前标签读完了
                        return ret;
                    }
            }
            eventType = parser.next();
        }
        return ret;
    }
    
    /**
     * 查找当前Tag的子Tag列表
     * @param tagName
     * @return
     */
    public List<ITag> findChildTag(String tagName) {
        List<ITag> tagList = new ArrayList<ITag>();
        for (ITag tag : mTags) {
            String myTagName = TagFactory.convertClassNameToTag(tag.getClass().getName());
            
            //Log.i(TAG, "this tag:" + myTagName + ", param tag:" + tagName);
            
            if (myTagName.equals(tagName)) {
                tagList.add(tag);
            }
        }
        
        return tagList;
    }

    /**
     * 查找当前Tag的子Tag列表(递归)
     * @param tagName
     * @return
     */
    public List<BaseTag> findAllChildTags(String tagName) {
        List<BaseTag> retTags = new ArrayList<BaseTag>();

    	String xmlTag = getXmlTag();
    	//Log.i(TAG, "findTagsByAttrValue - xmlTag: " + xmlTag);
        if (tagName.equals(xmlTag)) { // Tag相同
        	retTags.add(this);
        }

        for (ITag tag : mTags) { // 子标签
            retTags.addAll(((BaseTag) tag).findAllChildTags(tagName));
        }
        return retTags;
    }
    
    public List<ITag> list() {
        return mTags;
    }


    /**
     * 根据属性值检索标签
     * @param tags
     * @param keys
     * @param fileName
     * @return
     */
    public List<BaseTag> findTagsByAttrValue(String[] tags, String[] keys, String value) {
        List<BaseTag> retTags = new ArrayList<BaseTag>();
        for (String tag : tags) {
        	String xmlTag = getXmlTag();
        	//Log.i(TAG, "findTagsByAttrValue - xmlTag: " + xmlTag);
            if (tag.equals(xmlTag)) { // Tag相同
                String[] attrs = getValidAttr();
                Arrays.sort(attrs); // 二分法搜索前必须排序
                if (keys == null) {
                	retTags.add(this);
                } else {
                	class ParamSet {
                		public String attr;
                		public String value;
                		
                		public ParamSet(String attr, String value) {
                			this.attr = attr;
                			this.value = value;
                		}
                	}
                	ParamSet[] paramSets = new ParamSet[] {
                            new ParamSet(TagConstants.XML_ATTR_ID, attrBean.getId()),
                			new ParamSet(TagConstants.XML_ATTR_NAME, attrBean.getName()),
                            new ParamSet(TagConstants.XML_ATTR_TYPE, attrBean.getType()),
                            new ParamSet(TagConstants.XML_ATTR_BRAND, attrBean.getBrand()),
                            new ParamSet(TagConstants.XML_ATTR_MODEL, attrBean.getModel()),
                            new ParamSet(TagConstants.XML_ATTR_SERIALNO, attrBean.getSerialNo()),
                            new ParamSet(TagConstants.XML_ATTR_WEBSERVICEURL,
                                    attrBean.getWebserviceUrl()),
                            new ParamSet(TagConstants.XML_ATTR_UPLOADKSTJURL,
                                    attrBean.getUploadKstjUrl()),
                            new ParamSet(TagConstants.XML_ATTR_DOWNLOADKSTJURL,
                                    attrBean.getDownloadKstjUrl()),
                            new ParamSet(TagConstants.XML_ATTR_VERSIONSERVICEURL,
                                    attrBean.getVersionServiceUrl()),
                            new ParamSet(TagConstants.XML_ATTR_PRINTHEADER,
                                    attrBean.getPrintHeader()),
                            new ParamSet(TagConstants.XML_ATTR_PRINTFOOTER,
                                    attrBean.getPrintFooter()),
                            new ParamSet(TagConstants.XML_ATTR_BRIDGEID, attrBean.getBridgeId()),
                            new ParamSet(TagConstants.XML_ATTR_DATAVERSIONURL,
                                    attrBean.getDataVersionUrl()),
                                    new ParamSet(TagConstants.XML_ATTR_STATUS,
                                            attrBean.getDataVersionUrl()),
                	};
	                for (String key : keys) {
		                for (ParamSet paramSet : paramSets) {
		                     // Tag相同
		                    if (key.equals(paramSet.attr) 
		                            && Arrays.binarySearch(attrs, paramSet.attr) != -1
		                            && paramSet.value != null) {
		                    	if (value == null) {
		                    		retTags.add(this);
		                    	} else {
			                        if (value.equals(paramSet.value)) {
			                            retTags.add(this);
			                        }
		                    	}
		                    }
		                }
	                }
                }
            }
        }
        
        for (ITag tag : mTags) { // 子标签
            retTags.addAll(((BaseTag) tag).findTagsByAttrValue(tags, keys, value));
        }
        
        return retTags;
    }
    
    /**
     * @return the parentTag
     */
    public ITag getParentTag() {
        return parentTag;
    }

    /**
     * @param parentTag the parentTag to set
     */
    public void setParentTag(ITag parentTag) {
        this.parentTag = parentTag;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
    	BaseTag cloneTag = (BaseTag) super.clone();

    	cloneTag.mTags = new ArrayList<ITag>();
    	for (ITag tag : mTags) {
    		cloneTag.mTags.add((ITag) ((BaseTag)tag).clone());
    	}
    	if (mValue != null) {
    		cloneTag.mValue = new String(mValue); // 值
    	}
    	if (tmpData != null) {
    		cloneTag.tmpData = new String(tmpData); // 临时数据，以‘|’分隔
    	}
    	cloneTag.attrBean = (AttrBean) attrBean.clone(); // 属性
    	
        return cloneTag;
    }
}
