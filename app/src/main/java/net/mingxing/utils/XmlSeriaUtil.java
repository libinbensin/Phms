package net.mingxing.utils;

import com.thoughtworks.xstream.XStream;

/**
 * 实现 bean To XML 相互转换
 */
public class XmlSeriaUtil {

    /**
     * beanToXml;
     * @return
     */
    public static String beanToXml(Object obj) {
        String xml = null;
        try {
            XStream xstream = new XStream();
            xstream.processAnnotations(obj.getClass());
            xml = xstream.toXML(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    /**
     * xmlToBean
     * @param xml  xml 字符串
     * @param clazz  bean对象
     * @param <T>
     * @return
     */
    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(xml);
    }

}