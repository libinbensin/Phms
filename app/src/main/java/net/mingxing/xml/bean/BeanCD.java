package net.mingxing.xml.bean;


import com.thoughtworks.xstream.annotations.XStreamConverter;

import net.mingxing.xstream.converter.BeanCDConverter;

/**
 * Created by Administrator on 2015/4/21.
 */

@XStreamConverter(BeanCDConverter.class)
public class BeanCD {

    public String cd = "";
    public String value = "";

    public BeanCD() {
            super();
    }

    public BeanCD(String cd, String value) {
            super();
            this.cd = cd;
            this.value = value;
    }

}

