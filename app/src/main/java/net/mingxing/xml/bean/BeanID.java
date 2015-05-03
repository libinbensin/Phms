package net.mingxing.xml.bean;


import com.thoughtworks.xstream.annotations.XStreamConverter;

import net.mingxing.xstream.converter.BeanIDConverter;

/**
 * Created by Administrator on 2015/4/21.
 */
@XStreamConverter(BeanIDConverter.class)
public class BeanID {

    public String id = "";
    public String value = "";

    public BeanID() {
        super();
    }

    public BeanID(String id, String value) {
        super();
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanID{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
