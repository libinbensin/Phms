package com.cking.phss.xml4jgxx;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import net.xinhuaxing.interfaces.ITag;
import net.xinhuaxing.util.FileFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.cking.phss.xml4jgxx.tags.util.TagFactory;

public class JgxxConfigPullService {

    private static final String TAG = "JgxxConfigPullService";

    public static void write(ITag rootTag, String xmlFile) throws Throwable {
        XmlSerializer ser = Xml.newSerializer();
        File file = new File(xmlFile);
        FileOutputStream fos = new FileOutputStream(file);
        ser.setOutput(fos, "UTF-8");
        ser.startDocument("UTF-8", true);

        // 写Xml，包括所有子标签
        rootTag.write(ser);
        
        ser.endDocument();
        fos.close();
    }

    public static ITag read(String xmlFile) throws Throwable{
        ITag rootTag = null;
        byte[] inBytes = FileFactory.read(new File(xmlFile));
        InputStream is = new ByteArrayInputStream(inBytes);
//        InputStream is = new BufferedInputStream(new FileInputStream(xmlFile);
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "UTF-8");
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    String tag = parser.getName();
                    if (rootTag == null) {
                        rootTag = TagFactory.getNewInstanceOfTag(tag);
                    }
                    if (rootTag != null) {
                        rootTag.read(parser, eventType);
                    }
                    break;
                case XmlPullParser.END_TAG:
//                    if (rootTag != null) {
//                        rootTag.read(parser, eventType);
//                    }
                    break;

            }
            eventType = parser.next();
        }
        
        is.close();

        return rootTag;
	}
    
    public static void replace(String xmlFile, String target, String replacement) throws Throwable{
        byte[] data = FileFactory.read(new File(xmlFile));
        String content = new String(data);
        content.replace(target, replacement);
        
    }
}
