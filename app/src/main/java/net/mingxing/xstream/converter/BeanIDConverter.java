package net.mingxing.xstream.converter;


import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import net.mingxing.xml.bean.BeanID;

/**
 * Created by Administrator on 2015/4/21.
 */
public class BeanIDConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(BeanID.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        BeanID bean = (BeanID) value;
        writer.addAttribute("ID", bean.id);
        writer.setValue(bean.value);
    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        BeanID bean = new BeanID();
        bean.id = reader.getAttribute("ID");
        bean.value = reader.getValue();
        return bean;
    }

}
