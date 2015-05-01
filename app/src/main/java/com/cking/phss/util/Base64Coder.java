package com.cking.phss.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * 封装Base64的工具类 
 * @author taowencong
 *
 */
public class Base64Coder {  
    public final static String ENCODING = "UTF-8";  
    // 加密  
    public static String encoded(String data) throws UnsupportedEncodingException {  
        byte[] b = Base64.encodeBase64(data.getBytes(ENCODING));  
        return new String(b, ENCODING);  
    }  
    
    // 加密  
    public static String encoded(byte[] data) throws UnsupportedEncodingException {  
        byte[] b = Base64.encodeBase64(data);  
        return new String(b, ENCODING);  
    }  
  
    // 加密,遵循RFC标准  
    public static String encodedSafe(String data) throws UnsupportedEncodingException {  
        byte[] b = Base64.encodeBase64(data.getBytes(ENCODING),true);  
        return new String(b, ENCODING);  
    }  
    
    // 加密,遵循RFC标准  
    public static String encodedSafe(byte[] data) throws UnsupportedEncodingException {  
        byte[] b = Base64.encodeBase64(data,true);  
        return new String(b, ENCODING);  
    }  
  
    // 解密  
    public static byte[]  decode(String data) throws UnsupportedEncodingException {  
        byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));  
        return b;  
    }  
} 
