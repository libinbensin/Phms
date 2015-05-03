package net.mingxing.xstream.converter;


import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import net.mingxing.xml.bean.ZoneID;

/**
 * Created by Administrator on 2015/4/21.
 * ZoneID
 */
public class ZoneIDConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(ZoneID.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        ZoneID bean = (ZoneID) value;
        writer.addAttribute("ID", bean.id);
        writer.setValue(bean.value);
    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        ZoneID bean = new ZoneID();
        bean.id = reader.getAttribute("ID");
        bean.value = reader.getValue();
        return bean;
    }

}
