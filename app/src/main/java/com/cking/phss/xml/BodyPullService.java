package com.cking.phss.xml;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.cking.phss.xml.bean.Body;

public class BodyPullService {

    public static void write(Body body, String xmlFile) throws Throwable {
        XmlSerializer ser = Xml.newSerializer();
        File file = new File(xmlFile);
        FileOutputStream fos = new FileOutputStream(file);
        ser.setOutput(fos, "UTF-8");
        ser.startDocument("UTF-8", true);

        // 写BODY
        body.writeTag(ser);
        
        ser.endDocument();
    }

    public static Body read(String xmlFile) throws Throwable{
        Body body = null;
        InputStream is = new BufferedInputStream(new FileInputStream(xmlFile));
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "UTF-8");
        int eventType = parser.getEventType();
        while (eventType != parser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    body = new Body();
                    break;
                case XmlPullParser.START_TAG:
                    if (body != null) {
                        body.read(parser, eventType);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (body != null) {
                        body.read(parser, eventType);
                    }
                    break;

            }
            eventType = parser.next();
        }

        return body;
	}
    
    public static String print(Body body) throws IllegalArgumentException, IllegalStateException, IOException {
        XmlSerializer ser = Xml.newSerializer();
        ByteArrayOutputStream packet = new ByteArrayOutputStream(512);
        ser.setOutput(packet, "UTF-8");
        ser.startDocument("UTF-8", true);

        // 写BODY
        body.writeTag(ser);
        
        ser.endDocument();
        
        return packet.toString();
    }
}
