package com.cking.phss.sqlite;

import java.util.UUID;

import android.util.Log;

import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.util.MyApplication;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class JktjBll {
	static private final String TAG = "JktjBll";
	
    public static SaveToDbResult saveBean(IBean bean, long dateTime) {
		if(MyApplication.getInstance()
				.getSession().getCurrentResident() == null){
            return null;
		}
		String residentUUID = MyApplication.getInstance()
				.getSession().getCurrentResident().getResidentUUID();
		
        Jktj jktj = new Jktj();
        jktj.setClassName(bean.getClass().getName());
        jktj.setJktjUUID(UUID.randomUUID().toString());
        jktj.setResidentUUID(residentUUID);
        jktj.setDateTime(dateTime);
        jktj.setBean(XmlSerializerUtil.getInstance().beanToXml(
				bean));
        Log.i(TAG, jktj.getBean());
        boolean isAdd = false;
        if (!JktjDal.exist(residentUUID, jktj.getClassName(), jktj.getDateTime())) {
            JktjDal.add(jktj);
            isAdd = true;
		}else{
            JktjDal.update(jktj);
            isAdd = false;
		}
        jktj = JktjDal.query(residentUUID, jktj.getClassName(), jktj.getDateTime());
        if (jktj != null) {
            SaveToDbResult result = new SaveToDbResult(isAdd, residentUUID, jktj.getJktjUUID(),
                    bean);
            return result;
        } else {
            return null;
        }
	}
	
	public static Jktj query(String className,String residentUUID,long dateTime){
		Jktj sfgl =  JktjDal.query(residentUUID,className,dateTime);
		if(sfgl!=null){
			Log.i(TAG, sfgl.getBean());
		}else{
			Log.i(TAG, "sfgl=null");
		}
		return sfgl;
	}
	
	public static Jktj queryLast(String className,String residentUUID){
		Jktj sfgl =  JktjDal.queryLast(residentUUID,className);
		if(sfgl!=null){
			Log.i(TAG, sfgl.getBean());
		}else{
			Log.i(TAG, "sfgl=null");
		}
		return sfgl;
	}
	
	public static boolean exist(String sfglUUID) {
		return JktjDal.exist(sfglUUID);
	}
	
	public static boolean exist(String residentUUID, String className,long dateTime) {
		return JktjDal.exist(residentUUID, className, dateTime);
	}

    /**
     * @param name
     * @param residentUUID
     * @param projectUUID
     * @return
     */
    public static Jktj query(String className, String residentUUID, String projectUUID) {
        return JktjDal.query(className, residentUUID, projectUUID);
    }

    /**
     * @param name
     * @param residentUUID
     * @param projectUUID
     */
    public static void delete(String className, String residentUUID, String projectUUID) {
        JktjDal.delete(className, residentUUID, projectUUID);
    }
	
	

}
