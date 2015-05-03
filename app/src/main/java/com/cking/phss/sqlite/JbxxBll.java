package com.cking.phss.sqlite;

import java.util.UUID;

import android.util.Log;

import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.application.MyApplication;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class JbxxBll {
	static private final String TAG = "JbxxBll";
	
    public static SaveToDbResult saveBean(IBean bean) {
		if(MyApplication.getInstance()
				.getSession().getCurrentResident() == null){
            return null;
		}
		String residentUUID = MyApplication.getInstance()
				.getSession().getCurrentResident().getResidentUUID();
		
		Jbxx jbxx = new Jbxx();
		jbxx.setClassName(bean.getClass().getName());
		jbxx.setJbxxUUID(UUID.randomUUID().toString());
		jbxx.setResidentUUID(residentUUID);
		jbxx.setBean(XmlSerializerUtil.getInstance().beanToXml(
				bean));
		Log.i(TAG, jbxx.getBean());
        boolean isAdd = false;
		if(!JbxxDal.exist(residentUUID, jbxx.getClassName())){
			JbxxDal.add(jbxx);
            isAdd = true;
		}else{
			JbxxDal.update(jbxx);
            isAdd = false;
		}
        jbxx = JbxxDal.query(jbxx.getResidentUUID(), jbxx.getClassName());
        if (jbxx != null) {
            SaveToDbResult result = new SaveToDbResult(isAdd, residentUUID, jbxx.getJbxxUUID(),
                    bean);
            return result;
        } else {
            return null;
        }
	}
	
	public static Jbxx query(String className,String residentUUID){
		Jbxx jbxx =  JbxxDal.query(residentUUID,className);
		if(jbxx!=null){
			Log.i(TAG, jbxx.getBean());
		}else{
			Log.i(TAG, "jbxx=null");
		}
		return jbxx;
	}
	
	public static boolean exist(String residentUUID, String className) {
		return JbxxDal.exist(residentUUID, className);
	}

    /**
     * @param name
     * @param residentUUID
     * @param projectUUID
     * @return
     */
    public static Jbxx query(String className, String residentUUID, String projectUUID) {
        return JbxxDal.query(className, residentUUID, projectUUID);
    }

    /**
     * @param name
     * @param residentUUID
     */
    public static void delete(String className, String residentUUID) {
        JbxxDal.delete(className, residentUUID);
    }
	
	

}
