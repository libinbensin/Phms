package net.mingxing.xstream.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import net.mingxing.xml.bean.BeanCD;

/**
 * Created by Administrator on 2015/4/21.
 */
public class BeanCDConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(BeanCD.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        BeanCD bean = (BeanCD) value;
        writer.addAttribute("CD", bean.cd);
        writer.setValue(bean.value);
    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        BeanCD bean = new BeanCD();
        bean.cd = reader.getAttribute("CD");
        bean.value = reader.getValue();
        return bean;
    }

}
