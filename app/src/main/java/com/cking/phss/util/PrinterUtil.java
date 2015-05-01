/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * PrinterUtil.java
 * classes : com.cking.phss.util.PrinterUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-18 下午8:29:36
 */
package com.cking.phss.util;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.Log;

import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_cjsf;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_gxy.FZJC;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.bluetooth.BluetoothClient4Qsprinter;
import com.cking.phss.bluetooth.BluetoothClient4SpRmtiii;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.file.FileLog;
import com.cking.phss.global.Global;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * com.cking.phss.util.PrinterUtil
 * @author Administrator <br/>
 * create at 2014-6-18 下午8:29:36
 */
public class PrinterUtil {
    private static final String TAG = "PrinterUtil";
        
    public static void ToastPrint(Context context, String text) {    	
    	String printText = "";
    	
    	printText = "\r\n\r\n-----------FrankDebug[Begin]--------\r\n\r\n"+
					text+
					"\r\n\r\n-----------FrankDebug[End]--------\r\n\r\n"; 
    	
    	Log.i(TAG,printText);
    	
    	FileLog.i(TAG, printText);
    }
    
    public static void print(Context context, String text) {
        int deviceId = JgxxConfigFactory.findIdByName(context, TagConstants.XML_VALUE_NAME_RMDYJ);
        if (deviceId == TagConstants.XML_VALUE_ID_SPRT_SPRMTIIIBTA) {
            new BluetoothClient4SpRmtiii(context).print(text);
        } else if (deviceId == TagConstants.XML_VALUE_ID_QS_BZ) {
            new BluetoothClient4Qsprinter(context).print(text);
        }
    }
    /**
     * @param strCHECKINID 
     * 
     */
    public static void printJktj(Context context, Jmjbxx jmjbxx, Jktj_kstj jktjKstj, String strCHECKINID) {
        String text = getPrintJktjText(context, jmjbxx, jktjKstj, strCHECKINID);
        print(context, text);
        //ToastPrint(context,"\r\n-------------------[PRINT TO Toast]----------------------------\r\n\r\n" + text);        
        //FileLog.i("","\r\n-------------------[PRINT TO FILE]----------------------------\r\n\r\n" + text);
    }

    public static void printSfglGxy(Context context, Jmjbxx jmjbxx, Sfgljl_gxy sfgljl_gxy) {
        String text = getPrintSfglGxyText(context, jmjbxx, sfgljl_gxy);
        print(context, text);
        //ToastPrint(context,"\r\n-------------------[PRINT TO Toast]----------------------------\r\n\r\n" + text);        
        //FileLog.i("","\r\n-------------------[PRINT TO FILE]----------------------------\r\n\r\n" + text);
    }

    private static String getZzxxText(Context context, String ids, String qt, String resName) {
        String ZZText = "";
		String printSplit = "、";
        int mValue = 0;

        if (ids == null || ids.trim().equals(""))
            return ZZText;
        String[] checkValues = null;
        String[] spilts = new String[] { ",", "\\|" };
        for (String spilt : spilts) {
            checkValues = ids.split(spilt);
            if (checkValues.length > 1 && checkValues[0].length() != ids.length()) { // 说明找到了分隔符
                break;
            }
        }
        if (checkValues.length == 0)
            return ZZText;

        for (int i = 0; i < checkValues.length; i++) {
        	mValue = Integer.parseInt(checkValues[i]);
        	
            String tmpData = ResourcesFactory.findValue(context, resName, mValue);
            ZZText += tmpData;
        	
            if (tmpData.contains("其他"))
        	{
                ZZText = ZZText + ":" + qt;
        	}
        	
        	ZZText += printSplit;
        }

		if (!ZZText.equals("") && ZZText.length() > printSplit.length()) {
            ZZText = ZZText.substring(0, ZZText.length() - printSplit.length());
        }

        return ZZText;
    }
    
    private static String getPrintSfglGxyText(Context context, Jmjbxx jmjbxx, Sfgljl_gxy sfgljl_gxy) {
        String printText = "";
        String headFormatter = Global.printHeader + "——高血压随访\r\n" +
                "\r\n";
        printText += headFormatter;
        
        String grjbxxFormatter = "个人基本信息\r\n" + 
                "姓名:%s\r\n" + 
                "性别:%s  年龄:%d\r\n" + 
                "身份证号:%s\r\n" + 
                "高血压类型:%s\r\n" +
                "\r\n" + 
                "\r\n";
        String grjbxxText = String.format(grjbxxFormatter, jmjbxx.getResidentName(),
                ResourcesFactory.findValue(context, "xb", jmjbxx.getSexCD()),
                CalendarUtil.getAge(jmjbxx.getBirthDay()), jmjbxx.getPaperNum(),
                ResourcesFactory.findValue(context, "gxylx", sfgljl_gxy.gethBPTypeCD()));
        printText += grjbxxText;

        String sfxxFormatter = "随访信息\r\n" + 
                "\r\n" + 
                "随访编号：%s\r\n" + 
                "随访日期：%s\r\n" + 
                "随访方式：%s\r\n" + 
                "责任医生：%s\r\n" + 
                "随访医生：%s\r\n" + 
                "下次随访日期：%s\r\n" + 
                "\r\n" + 
                "\r\n";

        // sfgljl_gxy.setVisitID("2014063000001");//debug code,add by frank.

        if (!sfgljl_gxy.getVisitID().equals("")) {
            String sfxxText = String.format(sfxxFormatter, 
            		sfgljl_gxy.getVisitID(), 
            		sfgljl_gxy.getVisitDate(),
            		ResourcesFactory.findValue(context, "sffs", sfgljl_gxy.getsFFSCD()),
            		sfgljl_gxy.getDutyDoctor(),
                    BeanIDUtil.getTagValue(sfgljl_gxy.getVisitDoctor()),
            		sfgljl_gxy.getxCSF()
                    );
            printText += sfxxText;
        }

		String zzxxFormatter = "症状信息\r\n" + 
                "\r\n" + 
                "症状：%s\r\n" + 
                "\r\n" + 
                "\r\n";

        if (!sfgljl_gxy.getZZCD().equals("0")) {
            String zzxxText = String.format(zzxxFormatter, 
                    getZzxxText(context, sfgljl_gxy.getZZCD(), sfgljl_gxy.getZZQT(), "gxyzz")
				);
            printText += zzxxText;
        }

		String tzxxFormatter = "体征信息\r\n" + 
                "\r\n" + 
                "血压（mmHg）：%d/%d  身高（cm）：%s  体重（Kg）：%s\r\n" + 
                "目标体重（Kg）：%s  体质指数：%s  目标指数：%s\r\n" + 
                "心率（次/min）：%s  其他：%s" + 
                "\r\n" + 
                "\r\n";

        {
            String zzxxText = String.format(tzxxFormatter,
				sfgljl_gxy.getsBP(),sfgljl_gxy.getdBP(),sfgljl_gxy.getBCSG(),sfgljl_gxy.getbCTZ(),
				sfgljl_gxy.getxCTZ(),sfgljl_gxy.gettZZS(),sfgljl_gxy.getXCZS(),
				sfgljl_gxy.getbCXL(),sfgljl_gxy.getqTTZ()
				);
            printText += zzxxText;
        }

		String shfsFormatter = "生活方式\r\n" + 
                "\r\n" + 
                "日吸烟量（支）：%d  目标吸烟量（支）：%d  日摄盐量（g/天）：%d\r\n" + 
                "日饮酒量（两）：%d  目标饮酒量（两）：%d  目标摄盐量（g/天）：%d\r\n" + 
                "运动量：%d次/周 %d分钟/次  目标运动量：%d次/周 %d分钟/次\r\n" + 
                "心理调整：%s  遵医行为：%s" + 
                "\r\n" + 
                "\r\n";

        {
            String shfsText = String.format(shfsFormatter,
				sfgljl_gxy.getbCXYL(),sfgljl_gxy.getxCXY(),sfgljl_gxy.getbCSYL(),
				sfgljl_gxy.getbCYJ(),sfgljl_gxy.getxCYJ(),sfgljl_gxy.getxCSYL(),
				sfgljl_gxy.getyDZC(),sfgljl_gxy.getyDCF(),sfgljl_gxy.getxCYDZC(),sfgljl_gxy.getxCYDCD(),
				ResourcesFactory.findValue(context, "xltz", sfgljl_gxy.getxLTZCD()),
				ResourcesFactory.findValue(context, "zyxw", sfgljl_gxy.getzYXWCD())				
				);
            printText += shfsText;
        }

		String yyqkFormatter = "用药情况\r\n" + 
                "\r\n" + 
                "服药依从性：%s  药物不良反应：%s  \r\n" + 
                "此次随访分类：%s\r\n" + 
                "\r\n";

        {
            String yyqkText = String.format(yyqkFormatter,
				ResourcesFactory.findValue(context, "fyycx", sfgljl_gxy.getfYYCXCD()),
				(sfgljl_gxy.getbLFY() == 0)?"无":sfgljl_gxy.getfYQK(),
				ResourcesFactory.findValue(context, "ccfwfl", sfgljl_gxy.getsFFLCD())				
				);
            printText += yyqkText;
        }

		String yyjlFormatter = "用药记录\r\n" + 
                "\r\n" + 
                "序号：%d  药物类型：%s  药物名称：%s \r\n" + 
                "用量：%s 单位：%s 用法：%s\r\n" + 
                "使用总剂量：%s 给药方式：%s\r\n" + 
                "\r\n";

        {
            int i = 0;
            for (MedicineUse medicineUse : sfgljl_gxy.getMedicineUse()) {
                String yyqkText = String.format(yyjlFormatter, i++,
                        medicineUse.medicineType.getTagValue(), medicineUse.medicine.getTagValue(),
                        medicineUse.dosage, medicineUse.medicineUnit.getTagValue(),
                        medicineUse.usage.getTagValue(), medicineUse.integralDose,
                        medicineUse.way.getTagValue());
                printText += yyqkText;
            }
        }

		String zzqkFormatter1 = "转诊情况\r\n" + 
                "\r\n" + 
                "是否转诊：是  \r\n" + 
                "机构及科别：%s  原因：%s\r\n" + 
                "\r\n";
		
		String zzqkFormatter2 = "转诊情况\r\n" + 
                "\r\n" + 
                "是否转诊：否  \r\n" + 
                "\r\n";
        {
        	String zzqkText = "";
        	
        	if(!StringUtil.isEmptyString(sfgljl_gxy.getzZYY()) && 
        	   !StringUtil.isEmptyString(sfgljl_gxy.getxCSF()))
        	{
        		zzqkText = String.format(zzqkFormatter1,
        				sfgljl_gxy.getzZKB(),
        				sfgljl_gxy.getzZYY()				
        				);	
        	}
        	else
        	{
        		zzqkText = String.format(zzqkFormatter2);
        	}
            
            printText += zzqkText;
        }

		String zzhfFormatter = "转诊回访\r\n" + 
                "\r\n" + 
                "回访日期：%s\r\n" + 
                "合并症：%s  \r\n" + 
                "靶器官损害：%s\r\n" + 
                "其他疾病：%s\r\n" + 
                "\r\n";

        {
            String zzhfText = String.format(zzhfFormatter,
				sfgljl_gxy.getReferralVisitDate(),
                    BeanCDUtil.getTagValue(sfgljl_gxy.getComorbidity()),
                    BeanCDUtil.getTagValue(sfgljl_gxy.getCriticalOrgan()),
				sfgljl_gxy.getOtherDiseases()				
				);
            printText += zzhfText;
        }

       
        
        String tailFormatter = Global.printFooter +
                "\r\n\r\n\r\n";
        printText += tailFormatter;
        
        return printText;
    }
    /**
     * @param strCHECKINID 
     * @return
     */
    private static String getPrintJktjText(Context context, Jmjbxx jmjbxx, Jktj_kstj jktjKstj, String strCHECKINID) {
        String printText = "";
        String headFormatter = Global.printHeader + "——快速体检\r\n" +
                "\r\n";
        printText += headFormatter;
        
        String grjbxxFormatter = "个人基本信息\r\n" + 
                "姓名:%s\r\n" + 
                "性别:%s  年龄:%d\r\n" + 
                "体检编号:%s\r\n" + 
                "\r\n" + 
                "\r\n";
        String grjbxxText = String.format(grjbxxFormatter, jmjbxx.getResidentName(),
                ResourcesFactory.findValue(context, "xb", jmjbxx.getSexCD()),
                CalendarUtil.getAge(jmjbxx.getBirthDay()), strCHECKINID);
        printText += grjbxxText;
        
        String twFormatter = "体温\r\n" + 
                "\r\n" + 
                "体温（℃）：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if (!jktjKstj.getTw().equals("")) {
            String twText = String.format(twFormatter, 
                    jktjKstj.getTw(), jktjKstj.getTwjl());
            printText += twText;
        }
        
        String xueyaFormatter = "血压\r\n" + 
                "\r\n" + 
                "收缩压/舒张压（mmHg）：%s/%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if (!jktjKstj.getsBP().equals("") && !jktjKstj.getdBP().equals("")) {
            String xueyaText = String.format(xueyaFormatter, 
                    jktjKstj.getsBP(), jktjKstj.getdBP(), jktjKstj.getxYJL());
            printText += xueyaText;
        }
        
        String xtsjxtFormatter = "血糖（随机血糖）\r\n" + 
                "\r\n" + 
                "血糖值（mmol/L）：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if (!jktjKstj.getxTValue().equals("")) {
            String xtsjxtText = String.format(xtsjxtFormatter, 
                    jktjKstj.getxTValue(), jktjKstj.getxTJL());
            printText += xtsjxtText;
        }
        
        String xzPrintText = "";
        String xzHeadFormatter = "血脂\r\n" + 
                "\r\n";
        String dgcFormatter = "胆固醇（mmol/L）：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n";
        if (!jktjKstj.getdGC().equals("")) {
            String text = String.format(dgcFormatter, 
                    jktjKstj.getdGC(), jktjKstj.getdGCJL());
            xzPrintText += text;
        }
        
        String sygzFormatter = "甘油三酯（mmol/L）：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n";
        if (!jktjKstj.getgYSZ().equals("")) {
            String text = String.format(sygzFormatter, 
                    jktjKstj.getgYSZ(), jktjKstj.getgYSZJL());
            xzPrintText += text;
        }
        
        String gmdzdbFormatter = "高密度脂蛋白（mmol/L）：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n";
        if (!jktjKstj.getHDL().equals("")) {
            String text = String.format(gmdzdbFormatter, 
                    jktjKstj.getHDL(), jktjKstj.getHDLR());
            xzPrintText += text;
        }
        
        String dmdzdbFormatter = "低密度脂蛋白（mmol/L）：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n";
        if (!jktjKstj.getLDL().equals("")) {
            String text = String.format(dmdzdbFormatter, 
                    jktjKstj.getLDL(), jktjKstj.getLDLR());
            xzPrintText += text;
        }
        
        String nsbFormatter = "尿酸（mmol/L）：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n";
        if (!jktjKstj.getNS().equals("")) {
            String text = String.format(nsbFormatter, 
                    jktjKstj.getNS(), jktjKstj.getNSR());
            xzPrintText += text;
        }
        
        String xzTailFormatter = "\r\n";
        if (!xzPrintText.equals("")) {
            printText += xzHeadFormatter + xzPrintText + xzTailFormatter;
        }
        
        String xueyangFormatter = "血氧\r\n" + 
                "\r\n" + 
                "血氧饱和度：%s\r\n" + 
                "结论：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if (!jktjKstj.getXybhd().equals("")) {
            String xueyangText = String.format(xueyangFormatter, 
                    jktjKstj.getXybhd() + "%", jktjKstj.getXyjl());
            printText += xueyangText;
        }
        
        String rtcfFormatter = "人体成分\r\n" + 
                "\r\n" + 
                "体重（kg）：%s\r\n" + 
                "体脂肪率：%s\r\n" + 
                "基础代谢（cal）：%s\r\n" + 
                "身体结论：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if ( !jktjKstj.getWeight().equals("") &&
        	 !jktjKstj.getFat().equals("") &&
        	 !jktjKstj.getbMR().equals("") &&
        	 !jktjKstj.getCtype().equals("") ) {
            String rtcfText = String.format(rtcfFormatter, 
                    jktjKstj.getWeight(), jktjKstj.getFat() + "%", jktjKstj.getbMR(), jktjKstj.getCtype());
            printText += rtcfText;
        }
        
        String swFormatter = "三围\r\n" + 
                "\r\n" + 
                "腰围（cm）：%s\r\n" + 
                "臀围（cm）：%s\r\n" + 
                "胸围（cm）：%s\r\n" + 
                "腰臀比结论：%s\r\n" + 
                "\r\n" + 
                "\r\n"; 
        if (!jktjKstj.getBust().equals("") || 
        	!jktjKstj.gethIP().equals("")  || 
        	!jktjKstj.getWaist().equals("")||
        	!jktjKstj.getYtbjl().equals("")	) {
            String swText = String.format(swFormatter, jktjKstj.getWaist(), jktjKstj.gethIP(), 
                    jktjKstj.getBust(), jktjKstj.getYtbjl());
            printText += swText;
        }
        
        String xdtFormatter = "心电图\r\n" + 
                "心电图检测结论：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if (!jktjKstj.getXdjl().equals("")) {
            String xdtText = String.format(xdtFormatter, jktjKstj.getXdjl());
            printText += xdtText;
        }
        
        String zytzbsFormatter = "中医体质辨识\r\n" + 
                "中医体质辨识类型：%s\r\n" + 
                "\r\n" + 
                "\r\n"; 
        if (!jktjKstj.getZytzbslx().equals("")) {
            String zytzbsText = String.format(zytzbsFormatter, jktjKstj.getZytzbslx());
            printText += zytzbsText;
        }
        
        String xlcsFormatter = "心理测试\r\n" + 
                "心理测试结论：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if (!jktjKstj.getXlcsjl().equals("")) {
            String xlcsText = String.format(xlcsFormatter, jktjKstj.getXlcsjl());
            printText += xlcsText;
        }
        
        String lncsFormatter = "老年评估\r\n" + 
                "老年评估结果：%s\r\n" + 
                "\r\n" + 
                "\r\n";
        if (!jktjKstj.getLnpgjg().equals("")) {
            String lncsText = String.format(lncsFormatter, jktjKstj.getLnpgjg());
            printText += lncsText;
        }
        
        String tailFormatter = Global.printFooter +
                "\r\n\r\n\r\n";
        printText += tailFormatter;
        
        return printText;
    }
    /**
     * @param mContext
     * @param mJmjbxx
     * @param mSfgljl
     */
    public static void printSfglTnb(Context context, Jmjbxx jmjbxx, Sfgljl_tnb sfgljl) {
        String text = getPrintSfglTnbText(context, jmjbxx, sfgljl);
        print(context, text);
    }

    /**
     * @param context
     * @param jmjbxx
     * @param sfgljl
     * @return
     */
    private static String getPrintSfglTnbText(Context context, Jmjbxx jmjbxx, Sfgljl_tnb sfgljl) {
        String printText = "";
        String headFormatter = Global.printHeader + "——糖尿病随访\r\n" +
                "\r\n";
        printText += headFormatter;
        
        String grjbxxFormatter = "个人基本信息\r\n" + 
                "姓名:%s\r\n" + 
                "性别:%s  年龄:%d\r\n" + 
                "身份证号:%s\r\n" + 
                "\r\n" + 
                "\r\n";
        String grjbxxText = String.format(grjbxxFormatter, jmjbxx.getResidentName(),
                ResourcesFactory.findValue(context, "xb", jmjbxx.getSexCD()),
                CalendarUtil.getAge(jmjbxx.getBirthDay()), jmjbxx.getPaperNum());
        printText += grjbxxText;

        String zzxxFormatter = "症状信息\r\n" + 
                "\r\n" + 
                "症状：%s\r\n" + 
                "\r\n" + 
                "\r\n";

        if (!sfgljl.getzZCD().equals("0")) {
            String zzxxText = String.format(zzxxFormatter, 
                    getZzxxText(context, sfgljl.getzZCD(), sfgljl.getzZQT(), "tnbzz")
                );
            printText += zzxxText;
        }

        String tzxxFormatter = "体征信息\r\n" + "\r\n" + "血压（mmHg）：%d/%d  身高（cm）：%s  体重（Kg）：%s\r\n"
                + "目标体重（Kg）：%s  体质指数：%s  目标指数：%s\r\n" + "足背脉搏跳动：%s  腰围(cm)：%s  目标腰围(cm)：%s\r\n"
                + "其他：%s" + "\r\n" + "\r\n";
        {
            String zzxxText = String.format(tzxxFormatter, sfgljl.getsBP(), sfgljl.getdBP(),
                    sfgljl.getbCSG(), sfgljl.getbCTZ(), sfgljl.getxCTZ(), sfgljl.gettZZS(),
                    sfgljl.getxCZS(), sfgljl.getdMBDCD(), sfgljl.getWaistNow(),
                    sfgljl.getWaistTarget(), sfgljl.getqTTZ());
            printText += zzxxText;
        }

        String shfsFormatter = "生活方式\r\n" + "\r\n" + "日吸烟量（支）：%d  目标吸烟量（支）：%d  主食量（g/天）：%d\r\n"
                + "日饮酒量（两）：%d  目标饮酒量（两）：%d  目标食量（g/天）：%d\r\n"
                + "运动量：%d次/周 %d分钟/次  目标运动量：%d次/周 %d分钟/次\r\n" + "心理调整：%s  遵医行为：%s" + "\r\n" + "\r\n";

        {
            String shfsText = String.format(shfsFormatter, sfgljl.getbCXYL(), sfgljl.getxCXY(),
                    sfgljl.getbCZSL(), sfgljl.getbCYJ(), sfgljl.getxCYJ(), sfgljl.getxCZSL(),
                    sfgljl.getyDZC(), sfgljl.getyDCF(), sfgljl.getxCYDZC(), sfgljl.getxCYDCD(),
                    ResourcesFactory.findValue(context, "xltz", sfgljl.getxLTZCD()),
                    ResourcesFactory.findValue(context, "zyxw", sfgljl.getzYXWCD()));
            printText += shfsText;
        }

        String fzjcFormatter = "辅助检查\r\n" + "\r\n" + "序号：%d  空腹血糖(mmol/L)：%s \r\n"
                + "餐后两小时血糖(mmol/L)：%s \r\n" + "随机血糖(mmol/L)：%s \r\n" + "糖化血红蛋白(%)：%s 检查项目：%s\r\n"
                + "检查结果：%s 检查人：%s\r\n" + "检查日期：%s\r\n" + "\r\n";

        if (sfgljl.getfZJC() != null)
        {
            int i = 0;
            for (FZJC fzjc : sfgljl.getfZJC()) {
                String yyqkText = String.format(fzjcFormatter, i++, sfgljl.getkFXT(),
                        sfgljl.getxHDB(), sfgljl.getcHXT(), sfgljl.getqTXT(), fzjc.getjCXM(),
                        fzjc.getjCJG(), fzjc.getjCR(), fzjc.getjCRQ());
                printText += yyqkText;
            }
        }

        String yyqkFormatter = "用药情况\r\n" + 
                "\r\n" + 
                "服药依从性：%s  药物不良反应：%s  \r\n" + 
                "此次随访分类：%s\r\n" + 
                "\r\n";

        {
            String yyqkText = String.format(yyqkFormatter,
                ResourcesFactory.findValue(context, "fyycx", sfgljl.getfYYCXCD()),
                (sfgljl.getbLFY() == 0)?"无":sfgljl.getfYQK(),
                ResourcesFactory.findValue(context, "ccfwfl", sfgljl.getsFFLCD())               
                );
            printText += yyqkText;
        }

        String yyjlFormatter = "用药记录\r\n" + 
                "\r\n" + 
                "序号：%d  药物类型：%s  药物名称：%s \r\n" + 
                "用量：%s 单位：%s 用法：%s\r\n" + 
                "使用总剂量：%s 给药方式：%s\r\n" + 
                "\r\n";

        if (sfgljl.getMedicineUse() != null)
        {
            int i = 0;
            for (MedicineUse medicineUse : sfgljl.getMedicineUse()) {
                String yyqkText = String.format(yyjlFormatter, i++,
                        medicineUse.medicineType.getTagValue(), medicineUse.medicine.getTagValue(),
                        medicineUse.dosage, medicineUse.medicineUnit.getTagValue(),
                        medicineUse.usage.getTagValue(), medicineUse.integralDose,
                        medicineUse.way.getTagValue());
                printText += yyqkText;
            }
        }

        String ydsFormatter = "胰岛素\r\n" + "\r\n" + "序号：%d  用药种类：%s \r\n" + "使用频率：%s 使用剂量：%s \r\n"
                + "\r\n";

        if (!StringUtil.isEmptyString(sfgljl.getInsulinUse()))
        {
            int i = 0;
            String[] insulinUse = sfgljl.getInsulinUse().split("|");
            for (String is : insulinUse) {
                if (!StringUtil.isEmptyString(is)) {
                    String[] iu = is.split(",");
                    String[] text = new String[] { "", "", "" };
                    if (iu != null) {
                        int j = 0;
                        for (String c : iu) {
                            text[j] = c;
                            j++;
                        }
                        String yyqkText = String.format(ydsFormatter, i++, text[0], text[1],
                                text[2]);
                        printText += yyqkText;
                    }
                }
            }
        }

        String zzqkFormatter1 = "转诊情况\r\n" + 
                "\r\n" + 
                "是否转诊：是  \r\n" + 
                "机构及科别：%s  原因：%s\r\n" + 
                "\r\n";
        
        String zzqkFormatter2 = "转诊情况\r\n" + 
                "\r\n" + 
                "是否转诊：否  \r\n" + 
                "\r\n";
        {
            String zzqkText = "";
            
            if(!StringUtil.isEmptyString(sfgljl.getzZYY()) && 
               !StringUtil.isEmptyString(sfgljl.getxCSF()))
            {
                zzqkText = String.format(zzqkFormatter1,
                        sfgljl.getzZKB(),
                        sfgljl.getzZYY()                
                        );  
            }
            else
            {
                zzqkText = String.format(zzqkFormatter2);
            }
            
            printText += zzqkText;
        }

        String zzhfFormatter = "转诊回访\r\n" + 
                "\r\n" + 
                "回访日期：%s\r\n" + 
                "合并症：%s  \r\n" + 
                "靶器官损害：%s\r\n" + 
                "其他疾病：%s\r\n" + 
                "\r\n";

        {
            String zzhfText = String.format(zzhfFormatter,
                sfgljl.getReferralVisitDate(),
                    BeanCDUtil.getTagValue(sfgljl.getComorbidity()),
                    BeanCDUtil.getTagValue(sfgljl.getComplication()),
                sfgljl.getOtherDiseases()               
                );
            printText += zzhfText;
        }

       
        
        String tailFormatter = Global.printFooter +
                "\r\n\r\n\r\n";
        printText += tailFormatter;
        
        return printText;
    }

    /**
     * @param mContext
     * @param mJmjbxx
     * @param mSfgljl
     */
    public static void printSfglJsb(Context mContext, Jmjbxx mJmjbxx, Sfgljl_jsb mSfgljl) {
    }
    /**
     * @param mContext
     * @param mJmjbxx
     * @param mSfgljl
     */
    public static void printSfglLnsf(Context mContext, Jmjbxx mJmjbxx, Sfgljl_lnsf mSfgljl) {
    }
    /**
     * @param mContext
     * @param mJmjbxx
     * @param mSfgljl
     */
    public static void printSfglCjsf(Context mContext, Jmjbxx mJmjbxx, Sfgljl_cjsf mSfgljl) {
    }

}
