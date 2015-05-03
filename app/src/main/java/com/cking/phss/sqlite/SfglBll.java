package com.cking.phss.sqlite;

import java.util.UUID;

import android.util.Log;

import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.application.MyApplication;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class SfglBll {
	static private final String TAG = "SfglBll";
	
    public static SaveToDbResult saveBean(IBean bean, long dateTime, long downloadTime) {
        Log.i(TAG, "SaveToDbResult - bean: " + bean.getClass().getName());
		if(MyApplication.getInstance()
				.getSession().getCurrentResident() == null){
            return null;
		}
		String residentUUID = MyApplication.getInstance()
				.getSession().getCurrentResident().getResidentUUID();
		
		Sfgl sfgl = new Sfgl();
		sfgl.setClassName(bean.getClass().getName());
		sfgl.setSfglUUID(UUID.randomUUID().toString());
		sfgl.setResidentUUID(residentUUID);
		sfgl.setDateTime(dateTime);
        sfgl.setDownloadDateTime(downloadTime);
		sfgl.setBean(XmlSerializerUtil.getInstance().beanToXml(
				bean));
		Log.i(TAG, sfgl.getBean());
        boolean isAdd = false;
		if(!SfglDal.exist(residentUUID, sfgl.getClassName(),sfgl.getDateTime())){
			SfglDal.add(sfgl);
            isAdd = true;
		}else{
			SfglDal.update(sfgl);
            isAdd = false;
		}
        sfgl = SfglDal.query(residentUUID, sfgl.getClassName(), sfgl.getDateTime());
        if (sfgl != null) {
            SaveToDbResult result = new SaveToDbResult(isAdd, residentUUID, sfgl.getSfglUUID(),
                    bean);
            return result;
        } else {
            return null;
        }
	}

    public static SaveToDbResult saveBean(String beanXml, String className, long dateTime,
            long downloadTime) {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            return null;
        }
        String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();

        Sfgl sfgl = new Sfgl();
        sfgl.setClassName(className);
        sfgl.setSfglUUID(UUID.randomUUID().toString());
        sfgl.setResidentUUID(residentUUID);
        sfgl.setDateTime(dateTime);
        sfgl.setDownloadDateTime(downloadTime);
        sfgl.setBean(beanXml);
        Log.i(TAG, sfgl.getBean());
        boolean isAdd = false;
        if (!SfglDal.exist(residentUUID, sfgl.getClassName(), sfgl.getDateTime())) {
            SfglDal.add(sfgl);
            isAdd = true;
        } else {
            SfglDal.update(sfgl);
            isAdd = false;
        }
        sfgl = SfglDal.query(residentUUID, sfgl.getClassName(), sfgl.getDateTime());
        if (sfgl != null) {
            SaveToDbResult result = new SaveToDbResult(isAdd, residentUUID, sfgl.getSfglUUID(),
                    null);
            return result;
        } else {
            return null;
        }
    }

    public static Sfgl queryLastDownload(String className, String residentUUID) {
        Sfgl sfgl = SfglDal.queryLastDownload(residentUUID, className);
        if (sfgl != null) {
            Log.i(TAG, sfgl.getBean());
        } else {
            Log.i(TAG, "sfgl=null");
        }
        return sfgl;
    }
	
	public static Sfgl queryLast(String className,String residentUUID){
		Sfgl sfgl =  SfglDal.queryLast(residentUUID,className);
		if(sfgl!=null){
			Log.i(TAG, sfgl.getBean());
		}else{
			Log.i(TAG, "sfgl=null");
		}
		return sfgl;
	}
	
	public static boolean exist(String sfglUUID) {
		return SfglDal.exist(sfglUUID);
	}
	
	public static boolean exist(String residentUUID, String className,long dateTime) {
		return SfglDal.exist(residentUUID, className, dateTime);
	}

    /**
     * @param name
     * @param residentUUID
     * @param projectUUID
     * @return
     */
    public static Sfgl query(String className, String residentUUID, String projectUUID) {
        return SfglDal.query(className, residentUUID, projectUUID);
    }

    /**
     * @param name
     * @param residentUUID
     * @param projectUUID
     */
    public static void delete(String className, String residentUUID, String projectUUID) {
        SfglDal.delete(className, residentUUID, projectUUID);
    }
}
