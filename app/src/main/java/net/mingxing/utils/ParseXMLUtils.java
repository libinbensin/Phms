package net.mingxing.utils;


import android.util.Xml;

import net.mingxing.constant.Constant;
import net.mingxing.xml.bean.ConfigTag;
import net.mingxing.xml.bean.Device;
import net.mingxing.xml.bean.HospitalTag;
import net.mingxing.xml.bean.StringItem;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/2/13.
 * 工具类
 */
public class ParseXMLUtils {

    /**
     * 解析appconfig.XML文件，封装chengConfigTag对象返回
     *
     * @return
     */
    public static ConfigTag parseAppconfigXML() {
        ConfigTag configTag = null;
        File xmlFile = new File(Constant.APPCONFIG_PATH);
        XmlPullParser parser = Xml.newPullParser();
        InputStream input = null;
        try {
            input = new FileInputStream(xmlFile);
            parser.setInput(input, "UTF-8");
            int eventType = parser.getEventType();
            List<HospitalTag> hospitalTags = null;
            List<Device> devices = null;
            HospitalTag hospitalTag = null;
            Device device = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        configTag = new ConfigTag();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("ConfigTag".equals(parser.getName())) {
                            configTag.setId(parser.getAttributeValue(null, "id"));
                            configTag.setSerialno(parser.getAttributeValue(null, "serialno"));
                            configTag.setBridgeid(parser.getAttributeValue(null, "bridgeid"));
                        } else if ("SelectHospitalTag".equals(parser.getName())) {
                            hospitalTags = new ArrayList<HospitalTag>();
                        } else if ("HospitalTag".equals(parser.getName())) {
                            hospitalTag = new HospitalTag();
                            hospitalTag.setSerialno(parser.getAttributeValue(null, "serialno"));
                            hospitalTag.setStatus(parser.getAttributeValue(null, "status"));
                            hospitalTag.setDataversionurl(parser.getAttributeValue(null, "dataversionurl"));
                            hospitalTag.setDownloadkstjurl(parser.getAttributeValue(null, "downloadkstjurl"));
                            hospitalTag.setName(parser.getAttributeValue(null, "name"));
                            hospitalTag.setPrintfooter(parser.getAttributeValue(null, "printfooter"));
                            hospitalTag.setPrintheader(parser.getAttributeValue(null, "printheader"));
                            hospitalTag.setUploadkstjurl(parser.getAttributeValue(null, "uploadkstjurl"));
                            hospitalTag.setVersionserviceurl(parser.getAttributeValue(null, "versionserviceurl"));
                            hospitalTag.setWebserviceurl(parser.getAttributeValue(null, "webserviceurl"));
                        } else if ("ListDeviceInfoTag".equals(parser.getName())) {
                            devices = new ArrayList<Device>();
                        } else if ("DeviceInfoTag".equals(parser.getName())) {
                            device = new Device();
                            device.setId(parser.getAttributeValue(null, "id"));
                            device.setName(parser.getAttributeValue(null, "name"));
                            device.setBrand(parser.getAttributeValue(null, "brand"));
                            device.setSerialno(parser.getAttributeValue(null, "serialno"));
                            device.setModel(parser.getAttributeValue(null, "model"));
                            device.setType(parser.getAttributeValue(null, "type"));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("HospitalTag".equals(parser.getName())) {
                            hospitalTags.add(hospitalTag);
                            hospitalTag = null;
                        } else if ("SelectHospitalTag".equals(parser.getName())) {
                            configTag.setHospitalTags(hospitalTags); // 将hospital集合添加到configTag对象中。
                            hospitalTags = null;
                        } else if ("DeviceInfoTag".equals(parser.getName())) {
                            devices.add(device);
                            device = null;
                        } else if ("ListDeviceInfoTag".equals(parser.getName())) {
                            configTag.setDevices(devices);  // 将devices集合添加到configTag对象中
                            devices = null;
                        }
                        break;
                }
                eventType = parser.next(); // 下一事件类型
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return configTag;
    }


    /**
     * 解析  /sdCard/phms/xml/values.xml 文件
     * @return
     */
    public static Map<String, List<StringItem>> parseValues() {
        Map<String, List<StringItem>> map = null;
        File xmlFile = new File(Constant.VALUES_PATH);
        XmlPullParser parser = Xml.newPullParser();
        InputStream input = null;
        try {
            input = new FileInputStream(xmlFile);
            parser.setInput(input, "UTF-8");
            int eventType = parser.getEventType();
            List<StringItem> list = null;
            String name = null;
            // 循环到文档标签结束
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        map = new HashMap<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if("string-array".equals(parser.getName())) {
                            name = parser.getAttributeValue(null,"name");
                            list = new ArrayList<>();
                        }
                        if("item".equals(parser.getName())) {
                            if(list != null) {
                                StringItem item = new StringItem();
                                item.id = parser.getAttributeValue(null,"ID");
                                item.value = parser.nextText();
                                item.keyword = parser.getAttributeValue(null,"keyword");
                                list.add(item);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(map != null) {
                            map.put(name,list);
                        }
                        list = null;
                        break;

                }
                eventType = parser.next();  // 执行下个事件
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

}
