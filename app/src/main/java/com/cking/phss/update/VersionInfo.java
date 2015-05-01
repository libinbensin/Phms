package com.cking.phss.update;
/**
 * the version info on the service
 * @author taowencong
 *
 */
public class VersionInfo {
        public String url;
        public int versionCode=-1;
        public String versionName;
        public String fileName;
        public String dateInfo;
        @Override
        public String toString() {
        return "url="+url+"\n"+"versionCode="+versionCode+"\n"+"versionName="+versionName+"\n"+
        "fileName="+fileName+"\n"+
                "dateInfo="+dateInfo;
        }
}
