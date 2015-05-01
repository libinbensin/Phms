/* Xinhuaxing Inc. (C) 2013. All rights reserved.
 *
 * JgxxConfigFactory.java
 * classes : net.xinhuaxing.eshow.util.JgxxConfigFactory
 * @author wation
 * V 1.0.0
 * Create at 2013-10-22 涓嬪崍8:40:42
 */
package com.cking.phss.util;

import java.util.ArrayList;
import java.util.List;

import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.interfaces.ITag;
import android.content.Context;
import android.util.Log;

import com.cking.phss.xml4jgxx.JgxxConfigPullService;
import com.cking.phss.xml4jgxx.tags.BaseTag;
import com.cking.phss.xml4jgxx.tags.ConfigTag;
import com.cking.phss.xml4jgxx.tags.DeviceInfoTag;
import com.cking.phss.xml4jgxx.tags.HospitalTag;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * net.xinhuaxing.eshow.util.JgxxConfigFactory
 * @author wation <br/>
 * create at 2013-10-22 下午8:40:42
 */
public class JgxxConfigFactory {
    private static final String TAG = "JgxxConfigFactory";
    private static ConfigTag mConfigTag = null;
    private static ITag mPrecentTag = mConfigTag;
    
//    public interface OnLoadSysConfigListener {
//    	public void onReloadSysConfig();
//    }
//    private static OnLoadSysConfigListener mOnLoadSysConfigListener = null;
//    public static void setOnLoadSysConfigListener(OnLoadSysConfigListener listener) {
//    	mOnLoadSysConfigListener = listener;
//    }

    public static final int READ_ERROR = -1;
    public static final int READ_SUCCESS = 0;
    public static final int RELOAD_SUCCESS = 1;
    public static final int HAS_READ = 2;

	public static class StringItem {
    	public String id;
    	public String value;
	}
    
    public static int readFile(Context context) {
        try {
            String configFile = Constants.PHSS_XML_DIR + "appconfig.xml";
            Log.i(TAG, "readFile, configFile: " + configFile);
            mConfigTag = (ConfigTag) JgxxConfigPullService.read(configFile);

            return READ_SUCCESS;
        } catch (Throwable e) {
            e.printStackTrace();
            return READ_ERROR;
        }
    }

    public static boolean writeFile() {
        if (mConfigTag == null) {
            return false;
        }
        
        try {
            String resourcesFile = Constants.PHSS_XML_DIR + "appconfig.xml";
            JgxxConfigPullService.write(mConfigTag, resourcesFile);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void setPrecentTag(ITag tag) {
        mPrecentTag = tag;
    }
    
    public static ITag getPrecentTag() {
        return mPrecentTag;
    }

    public static ConfigTag getConfigTag(Context context) {
        //readSysConfigFile(context);
        
        return mConfigTag;
    }
    
    public static List<ITag> list(Context context, ITag folder, String tagName) {
        //readSysConfigFile(context);

        if (mConfigTag != null) {
            return ((BaseTag) folder).findChildTag(tagName);
        }

        return null;
    }

    public static List<BaseTag> listAll(Context context, ITag folder, String tagName) {
        //readSysConfigFile(context);

        if (mConfigTag != null) {
            return ((BaseTag) folder).findAllChildTags(tagName);
        }

        return null;
    }
    
    public static List<ITag> list(Context context, ITag folder) {
        //readSysConfigFile(context);

        if (mConfigTag != null) {
            return ((BaseTag) folder).list();
        }

        return null;
    }

    public static List<HospitalTag> listHosptalTag(Context context) {
        // readSysConfigFile(context);

        if (mConfigTag != null) {
            List<BaseTag> tagList = mConfigTag.findTagsByAttrValue(
                    new String[] { HospitalTag.XML_TAG }, null, null);
            if (tagList != null && tagList.size() > 0) {
                List<HospitalTag> retList = new ArrayList<HospitalTag>();
                for (BaseTag tag : tagList) {
                    retList.add((HospitalTag) tag);
                }
                return retList;
            }
        }

        return null;
    }

    public static DeviceInfoTag findDeviceInfoTagByType(Context context, String type) {
        if (mConfigTag != null) {
        	List<BaseTag> tagList = mConfigTag.findTagsByAttrValue(
							new String[] { DeviceInfoTag.XML_TAG },
							new String[] { TagConstants.XML_ATTR_TYPE }, type);
        	if (tagList != null && tagList.size() > 0) {
        		return (DeviceInfoTag) tagList.get(0);
        	}
        }

        return null;
    }

    public static DeviceInfoTag findDeviceInfoTagByName(Context context, String name) {
        if (mConfigTag != null) {
            List<BaseTag> tagList = mConfigTag.findTagsByAttrValue(
                            new String[] { DeviceInfoTag.XML_TAG },
                            new String[] { TagConstants.XML_ATTR_NAME }, name);
            if (tagList != null && tagList.size() > 0) {
                return (DeviceInfoTag) tagList.get(0);
            }
        }

        return null;
    }

    public static int findIdByName(Context context, String name) {
        if (mConfigTag != null) {
            List<BaseTag> tagList = mConfigTag.findTagsByAttrValue(
                            new String[] { DeviceInfoTag.XML_TAG },
                            new String[] { TagConstants.XML_ATTR_NAME }, name);
            if (tagList != null && tagList.size() > 0) {
                String id = tagList.get(0).attrBean.getId();
                return Integer.parseInt(id);
            }
        }

        return 0;
    }
}
