package net.mingxing.xml.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import net.mingxing.xstream.converter.ZoneIDConverter;

/**
 * Created by Administrator on 2015/4/21.
 */
@XStreamAlias("Zone")
@XStreamConverter(ZoneIDConverter.class)
public class ZoneID {

    public String id;
    public String value;

    public ZoneID() {
        super();
    }

    public ZoneID(String id, String value) {
        super();
        this.id = id;
        this.value = value;
    }
}
