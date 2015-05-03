package net.mingxing.utils;


import net.mingxing.xml.bean.ConfigTag;
import net.mingxing.xml.bean.Device;
import net.mingxing.xml.bean.HospitalTag;

import java.util.List;

/**
 * Created by MingXing on 2015/5/3.
 * xml解析工厂
 */
public class XmlFactory {

    public static ConfigTag mConfigTag;

    static {
        mConfigTag = ParseXMLUtils.parseAppconfigXML();
    }

// =======================appconfig.xml util==========================
    /**
     * 得到所有hospital
     * @return
     */
    public static List<HospitalTag> getAllHospitals(){
        if(mConfigTag != null && mConfigTag.getHospitalTags() != null && mConfigTag.getHospitalTags().size() > 0) {
            return mConfigTag.getHospitalTags();
        }
        return null;
    }

    /**
     * 得到所有hospital名称
     * @return
     */
    public static String[] getHostitalNames() {
        List<HospitalTag> hospitals = getAllHospitals();
        if(hospitals != null && hospitals.size() > 0) {
            String[] hospitalNames = new String[hospitals.size()];
            for(int i=0; i<hospitals.size(); i++) {
                hospitalNames[i] = hospitals.get(i).getName();
            }
            return hospitalNames;
        }
        return null;
    }

    /**
     * 得到hospital
     * @param i
     * @return
     */
    public static HospitalTag getHospital(int i) {
        if(getAllHospitals() != null) {
            return getAllHospitals().get(i);
        }
       return null;
    }

    /**
     * 得到所有device
     * @return
     */
    public static List<Device> getAllDevices() {
        if(mConfigTag != null && mConfigTag.getDevices() != null && mConfigTag.getDevices().size() > 0) {
            return mConfigTag.getDevices();
        }
        return null;
    }


//    =======================values.xml util============
}
