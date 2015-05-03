package com.cking.phss.util;

import android.content.Context;

import com.cking.application.MyApplication;
import com.cking.phss.R;
/**
 * 获取一些字符串值
 * @author taowencong
 *
 */
public class StringManager {
    
    private static Context mContext= MyApplication.getInstance().getApplicationContext();
    
    //获取随访方式
    public static String getSffs(int index){
        if(index<1||index>4)
            return "";
        String[] sffs=mContext.getResources().getStringArray(R.array.suifang_conditions);
        if(sffs==null||sffs.length<4)
            return "";
        return sffs[index-1];
    }
    
    //获取高血压症状字符串
    //多个代码之间用英文|分隔，值为代码：1无症状；2头痛头晕；3恶心呕吐；4眼花耳鸣；5呼吸困难；6心悸胸闷；7鼻衄出血不止；8四肢发麻；9下肢水肿；10其它 -->
    public static String getGxyZz(String zzcd){
        if(zzcd==null||zzcd.equals("")){
            return "";
        }
        String[] zzString=new String[]{
            "无症状","头痛头晕","恶心呕吐","眼花耳鸣","呼吸困难","心悸胸闷","鼻衄出血不止","四肢发麻","下肢水肿","其它"
        };
        String resultStr="";
        try {
            String values = zzcd;
            String[] checkValues = null;
            String[] spilts = new String[] { ",", "\\|" };
            for (String spilt : spilts) {
                checkValues = values.split(spilt);
                if (checkValues.length > 1 && checkValues[0].length() != values.length()) { // 说明找到了分隔符
                    break;
                }
            }
            for (String s : checkValues) {
                int index=Integer.parseInt(s);
                if(index<0||index>zzString.length-1){
                    continue;
                }
                resultStr+=(zzString[index]+"   ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }
    
    
  //获取高血压症状字符串
    //多个代码之间用英文|分隔，值为代码1无症状；2多饮；3多食；4多尿；5视力模糊；6感染 ；7手脚麻木；8下肢浮肿；9 体重明显下降；10其他-->
    public static String getTnbZz(String zzcd){
        if(zzcd==null||zzcd.equals("")){
            return "";
        }
        String[] zzString=new String[]{
            "无症状","多饮","多食","多尿","视力模糊","感染","手脚麻木","下肢浮肿","体重明显下降","其它"
        };
        String resultStr="";
        try {
            String values = zzcd;
            String[] checkValues = null;
            String[] spilts = new String[] { ",", "\\|" };
            for (String spilt : spilts) {
                checkValues = values.split(spilt);
                if (checkValues.length > 1 && checkValues[0].length() != values.length()) { // 说明找到了分隔符
                    break;
                }
            }
            for (String s : checkValues) {
                int index=Integer.parseInt(s);
                if(index<0||index>zzString.length-1){
                    continue;
                }
                resultStr+=(zzString[index]+"   ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }
    
    
    //获取心理调整 或者遵医行为  这两个的字符串是一模一样的
    public static String getXltz(int index){
        if(index<1||index>3)
            return "";
        String[] xltz=mContext.getResources().getStringArray(R.array.xinlitz_conditions);
        if(xltz==null||xltz.length<3)
            return "";
        return xltz[index-1];
    }
    
    
    //获取服药依从性
    public static String getFyycx(int index){
        if(index<1||index>3)
            return "";
        String[] fyycx=mContext.getResources().getStringArray(R.array.fyycx_conditions);
        if(fyycx==null||fyycx.length<3)
            return "";
        return fyycx[index-1];
    }
    
    
    //获取随访分类
    public static String getSffl(int index){
        if(index<1||index>4)
            return "";
        String[] sffl=mContext.getResources().getStringArray(R.array.ccfwfl_conditions);
        if(sffl==null||sffl.length<4)
            return "";
        return sffl[index-1];
    }
    
    //获取足动脉搏
    public static String getZdmb(int index){
        if(index<1||index>2)
            return "";
        String[] sffl=mContext.getResources().getStringArray(R.array.zdmbtd_conditions);
        if(sffl==null||sffl.length<2)
            return "";
        return sffl[index-1];
    }
    
    //获取低血糖反应
    public static String getDxtfy(int index){
        if(index<1||index>3)
            return "";
        String[] sffl=mContext.getResources().getStringArray(R.array.dxtfy_conditions);
        if(sffl==null||sffl.length<3)
            return "";
        return sffl[index-1];
    }
    
    //获取性别
    public static String getSexbyCD(int sexCD) {
        if (sexCD==1) {
            return "男";
        } else if (sexCD==2) {
            return "女";
        } else {
            return "不详";
        }
    }
}
