package com.cking.phss.sqlite;

import java.util.List;

import android.util.Log;

import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.dto.innner.Record;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class SjglBll {
    static private final String TAG = "SjglBll";
	
    public static SaveToDbResult saveBean(IBean bean, String residentUUID, String projectUUID,
            String projectType,
            long operDateTime) {
        Sjgl sjgl = new Sjgl();
        sjgl.setClassName(bean.getClass().getName());
        sjgl.setProjectUUID(projectUUID);
        sjgl.setResidentUUID(residentUUID);
        sjgl.setProjectType(projectType);
        sjgl.setOperDateTime(operDateTime);
        sjgl.setBean(XmlSerializerUtil.getInstance().beanToXml(
				bean));
        Log.i(TAG, sjgl.getBean());
        boolean isAdd = false;
        if (!SjglDal.exist(residentUUID, sjgl.getProjectUUID(), sjgl.getClassName())) {
            SjglDal.add(sjgl);
            isAdd = true;
		}else{
            SjglDal.update(sjgl);
            isAdd = false;
		}
        sjgl = SjglDal.query(residentUUID, sjgl.getProjectUUID(), sjgl.getClassName());
        if (sjgl != null) {
            SaveToDbResult result = new SaveToDbResult(isAdd, residentUUID, sjgl.getProjectUUID(),
                    bean);
            return result;
        } else {
            return null;
        }
	}

    public static void delete(String residentUUID, String projectUUID, String projectType,
            String className) {
        SjglDal.delete(residentUUID, projectUUID, projectType, className);
    }

    public static boolean exist(String sjglUUID, String projectType) {
        return SjglDal.exist(sjglUUID, projectType);
	}
	
    public static boolean exist(String residentUUID, String projectType, String className) {
        return SjglDal.exist(residentUUID, projectType, className);
	}

    /**
     * @param residentID
     * @param serialNumber
     * @param getcD
     * @return
     */
    public static long queryOperTime(String residentID, String serialNumber, String projectType) {
        Sjgl sjgl = SjglDal.query(residentID, serialNumber, projectType, Record.class.getName());
        return sjgl.getOperDateTime();
    }

    public static String queryBean(String residentID, String serialNumber, String projectType) {
        Sjgl sjgl = SjglDal.query(residentID, serialNumber, projectType, Record.class.getName());
        if (sjgl == null) {
            return null;
        } else {
            return sjgl.getBean();
        }
    }

    /**
     * @param name
     * @return
     */
    public static List<Sjgl> query(String className) {
        return SjglDal.query(className);
    }

    /**
     * @param name
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<Sjgl> query(String className, long beginTime, long endTime) {
        return SjglDal.query(className, beginTime, endTime);
    }
}
