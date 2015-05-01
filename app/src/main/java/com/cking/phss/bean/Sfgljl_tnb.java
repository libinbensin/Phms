package com.cking.phss.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.xinhuaxing.util.StringUtil;

import com.cking.phss.bean.Sfgljl_gxy.FZJC;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.xml.util.XmlTag;

/**
 * 高血压 随访 管理卡
 * 
 * @author AUS
 * 
 */
public class Sfgljl_tnb implements IBean {

    @XmlTag(name = "Type")
    private int type = 0;

    @XmlTag(name = "BZ")
    private String bz = "";

    @XmlTag(name = "VisitID")
    private String visitID = ""; // 随访序号

    @XmlTag(name = "VisitDate")
    private String visitDate = ""; // 随访日期

    @XmlTag(name = "VisitDoctor")
    // 做随访的是哪个医生
    private BeanID visitDoctor;

    // 必填。随访医生ID或代码
    @XmlTag(name = "DoctorID")
    private String doctorID = "";

    // 必填。随访医生姓名
    @XmlTag(name = "DoctorName")
    private String doctorName = "";

    // 必填。责任医生
    @XmlTag(name = "DutyDoctor")
    private String dutyDoctor = "";

    @XmlTag(name = "SFFSCD")
    private int sFFSCD = 1; // 随访方式代码（单选），值为代码：1.门诊 2.家庭 3.电话 4.集体

    @XmlTag(name = "ZZCD")
    // 糖尿病症状：多个代码之间用英文|分隔，值为代码：1无症状；2多饮；3多食；4多尿；5视力模糊；6感染 ；7手脚麻木；8下肢浮肿；9
    // 体重明显下降；10其他-->
    private String zZCD = "1";

    @XmlTag(name = "ZZQT")
    // 糖尿病其它症状
    private String zZQT = "";

	
	// 本次心率。单位：次/分。整型
	@XmlTag(name = "BCXL")
	private int bCXL = 0;
	
	// 下次心率。单位：次/分。整型
    @XmlTag(name = "XCXL")
    private int xCXL = 0;

    @XmlTag(name = "SBP")
    private int sBP = 0; // 收缩压。单位：mmHg，整型

    @XmlTag(name = "DBP")
    private int dBP = 0; // 舒张压。单位：mmHg，整型

    @XmlTag(name = "BCTZ")
    private String bCTZ = "0"; // 本次体重，单位：kg，浮点

    @XmlTag(name = "XCTZ")
    private String xCTZ = "0";// 下次希望体重，单位：kg，浮点

    @XmlTag(name = "TZZS")
    private String tZZS = "0";// 本次体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2）。浮点

    @XmlTag(name = "XCZS")
    private String xCZS = "0";// 下次体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2）。浮点

    // 必填。本次身高。单位：cm。整型
    @XmlTag(name = "BCSG")
    private String bCSG = "0";

    @XmlTag(name = "JCSJ")
    private String jCSJ = "";// 检查时间，格式：yyyy-mm-dd

    @XmlTag(name = "DMBDCD")
    private int dMBDCD = 0;// 足背动脉搏动（单选），值为代码：1 未触及2 触及

    // <!-- 当前腰围 -->
    @XmlTag(name = "WaistNow")
    public String waistNow = "";

    // <!-- 目标腰围 -->
    @XmlTag(name = "WaistTarget")
    public String waistTarget = "";

    @XmlTag(name = "QTTZ")
    private String qTTZ = "";// 其他特征

    @XmlTag(name = "BCXYL")
    private int bCXYL = 0; // 本次日吸烟量。单位：支。整型-

    @XmlTag(name = "XCXY")
    private int xCXY = 0; // 下次希望日吸烟量。整型

    @XmlTag(name = "BCYJ")
    private int bCYJ = 0; // 本次日饮酒量。单位：两。整型

    @XmlTag(name = "XCYJ")
    private int xCYJ = 0; // 写错希望日饮酒

    @XmlTag(name = "YDZC")
    private int yDZC = 0; // 本次运动每周几次。整型

    @XmlTag(name = "YDCF")
    private int yDCF = 0; // 本次运动每次几分钟。整型

    @XmlTag(name = "XCYDZC")
    private int xCYDZC = 0; // 下次随访目标每周几次。整型

    @XmlTag(name = "XCYDCD")
    private int xCYDCD = 0; // 下次随访目标每次几分钟。整型

    @XmlTag(name = "XLTZCD")
    private int xLTZCD = 1; // 心理调整代码（单选），值为代码：1良好 2一般 3差

    @XmlTag(name = "ZYXWCD")
    private int zYXWCD = 1; // 遵医行为代码（单选），值为代码：1.良好 2.一般 3.差

	/**
	 * 这里是整形，ui上是 spinner 轻中重
	 */
	@XmlTag(name = "BCSYL")
	private int bCSYL = 0;// 本次日摄盐量，单位：克，整型 -

	@XmlTag(name = "XCSYL")
	private int xCSYL = 0;// 下次目标摄盐量，单位：克，整型
    @XmlTag(name = "BCZSL")
    private int bCZSL = 0; // 日主食量，单位：克/天，整型

    @XmlTag(name = "XCZSL")
    private int xCZSL = 0; // 下次希望主食量，单位：克/天，整型

    @XmlTag(name = "KFXT")
    private String kFXT = "0"; // 必填。空腹血糖，单位：mmol/L，浮点

    @XmlTag(name = "DXTFYCD")
    private int dXTFYCD = 1; // 低血糖反应代码（单选），值为代码：1无 2 偶尔 3频繁

    /**
     * 改为list
     */
    @XmlTag(name = "FZJC", isListWithoutGroupTag = true)
    private List<FZJC> fZJC = null;// 格式为 辅助检查项目|辅助检查结果 |检查人|检查日期

    @XmlTag(name = "FYYCXCD")
    private int fYYCXCD = 1; // 服药依从性代码（单选），值为代码：1.规律服药 2.间断服药 3.不服药

    @XmlTag(name = "BLFY")
    private int bLFY = 0; // 药物不良反应（单选），值为代码：0：无；1：有

    @XmlTag(name = "FYQK")
    private String fYQK = "";// 不良反应情况-

    @XmlTag(name = "SFFLCD")
    private int sFFLCD = 1; // 此次随访分类代码（单选），值为代码：1控制满意，2控制不满意，3不良反应，4并发症

    @XmlTag(name = "MedicineUse", isListWithoutGroupTag = true)
    private List<MedicineUse> medicineUse;// 药物名称

	@XmlTag(name = "InsulinUse")
	// 胰島素
	private String insulinUse = "";

    @XmlTag(name = "ZZYY")
    private String zZYY = "";// 转诊原因

    @XmlTag(name = "ZZKB")
    private String zZKB = "";// 转诊机构及科别

    @XmlTag(name = "XCSF") // 下次随访日期
    private String xCSF = "";
    
    @XmlTag(name = "XHDB")
    private String xHDB = "0";// 糖化血红蛋白，单位：%，浮点
    
    public String getWaistNow() {
        return waistNow;
    }

    public void setWaistNow(String waistNow) {
        this.waistNow = waistNow;
    }

    public String getWaistTarget() {
        return waistTarget;
    }

    public void setWaistTarget(String waistTarget) {
        this.waistTarget = waistTarget;
    }

    @XmlTag(name = "CHXT")
    private String cHXT = "0";// 餐后两小时血糖值
    
	//<!-- 必填。其他血糖类型的血糖值，单位：mmol/L，浮点-->
	@XmlTag(name = "QTXT")
	public String qTXT = "";

    
    @XmlTag(name = "XTLX")
    private BeanCD xTLX = null;// 血糖类型， CD：ID或代码，1.空腹血糖，2.餐后两小时血糖，3.随机血糖, 4.其他

    @XmlTag(name = "QTJC")
    private String qTJC = "";// 其他检查

    @XmlTag(name = "Complication")
    private BeanCD complication = null;// <!-- 转诊回访--并发症 CD包括（足病、肾病、眼病、脑病、心脏病、皮肤病、性病、其他酮症酸中毒、非酮症性高渗性昏迷、乳酸性酸中毒、低血糖昏迷、泌尿道感染、呼吸道感染、皮肤感染、酮症酸中毒、肾病、心脏病变、神经病变、眼部病变、眼部病变、糖尿病足、心脏病、脑血管病变、肢端坏疽、其他）的代码；值：文字-->

    @XmlTag(name = "Comorbidity")
    private BeanCD comorbidity = null;// <!-- 转诊回访--合并症 CD包括（左室肥厚、心力衰竭、冠心病、心律失常、脑卒中、主动脉夹层动脉瘤、动脉粥样硬化、视网膜病变、其他）的代码；值：文字-->

    @XmlTag(name = "OtherDiseases")
    private String otherDiseases = "";// <!-- 转诊回访--其他疾病 -->

    @XmlTag(name = "ReferralVisitDate")
    private String referralVisitDate = ""; // 转诊日期
    
    public String getVisitID() {
        return visitID;
    }

    public void setVisitID(String visitID) {
        this.visitID = visitID;
    }

    public String getVisitDate() {
        if (StringUtil.isEmptyString(visitDate)) {
            // 自动定位今天
            visitDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public BeanID getVisitDoctor() {
        return visitDoctor;
    }

    public void setVisitDoctor(BeanID visitDoctor) {
        this.visitDoctor = visitDoctor;
    }

    public int getsFFSCD() {
        return sFFSCD;
    }

    public void setsFFSCD(int sFFSCD) {
        this.sFFSCD = sFFSCD;
    }

    public String getZZQT() {
        return zZQT;
    }

    public void setZZQT(String zZQT) {
        this.zZQT = zZQT;
    }

    public int getsBP() {
        return sBP;
    }

    public void setsBP(int sBP) {
        this.sBP = sBP;
    }

    public int getdBP() {
        return dBP;
    }

    public void setdBP(int dBP) {
        this.dBP = dBP;
    }

    public String getbCTZ() {
        return bCTZ;
    }

    public void setbCTZ(String bCTZ) {
        this.bCTZ = bCTZ;
    }

    public String getxCTZ() {
        return xCTZ;
    }

    public void setxCTZ(String xCTZ) {
        this.xCTZ = xCTZ;
    }

    public String gettZZS() {
        return tZZS;
    }

    public void settZZS(String tZZS) {
        this.tZZS = tZZS;
    }

    public int getdMBDCD() {
        return dMBDCD;
    }

    public void setdMBDCD(int dMBDCD) {
        this.dMBDCD = dMBDCD;
    }

    public String getqTTZ() {
        return qTTZ;
    }

    public void setqTTZ(String qTTZ) {
        this.qTTZ = qTTZ;
    }

    public int getbCXYL() {
        return bCXYL;
    }

    public void setbCXYL(int bCXYL) {
        this.bCXYL = bCXYL;
    }

    public int getxCXY() {
        return xCXY;
    }

    public void setxCXY(int xCXY) {
        this.xCXY = xCXY;
    }

    public int getbCYJ() {
        return bCYJ;
    }

    public void setbCYJ(int bCYJ) {
        this.bCYJ = bCYJ;
    }

    public int getxCYJ() {
        return xCYJ;
    }

    public void setxCYJ(int xCYJ) {
        this.xCYJ = xCYJ;
    }

    public int getyDZC() {
        return yDZC;
    }

    public void setyDZC(int yDZC) {
        this.yDZC = yDZC;
    }

    public int getyDCF() {
        return yDCF;
    }

    public void setyDCF(int yDCF) {
        this.yDCF = yDCF;
    }

    public int getxCYDZC() {
        return xCYDZC;
    }

    public void setxCYDZC(int xCYDZC) {
        this.xCYDZC = xCYDZC;
    }

    public int getxCYDCD() {
        return xCYDCD;
    }

    public void setxCYDCD(int xCYDCD) {
        this.xCYDCD = xCYDCD;
    }

    public int getxLTZCD() {
        return xLTZCD;
    }

    public void setxLTZCD(int xLTZCD) {
        this.xLTZCD = xLTZCD;
    }

    public int getzYXWCD() {
        return zYXWCD;
    }

    public void setzYXWCD(int zYXWCD) {
        this.zYXWCD = zYXWCD;
    }


	public int getbCSYL() {
		return bCSYL;
	}

	public void setbCSYL(int bCSYL) {
		this.bCSYL = bCSYL;
	}

	public int getxCSYL() {
		return xCSYL;
	}

	public void setxCSYL(int xCSYL) {
		this.xCSYL = xCSYL;
	}

	public int getbCXL() {
        return bCXL;
    }

    public void setbCXL(int bCXL) {
        this.bCXL = bCXL;
    }
    public int getbCZSL() {
        return bCZSL;
    }

    public void setbCZSL(int bCZSL) {
        this.bCZSL = bCZSL;
    }

    public int getxCZSL() {
        return xCZSL;
    }

    public void setxCZSL(int xCZSL) {
        this.xCZSL = xCZSL;
    }

    public String getkFXT() {
        return kFXT;
    }

    public void setkFXT(String kFXT) {
        this.kFXT = kFXT;
    }

    public int getdXTFYCD() {
        return dXTFYCD;
    }

    public void setdXTFYCD(int dXTFYCD) {
        this.dXTFYCD = dXTFYCD;
    }

    public List<FZJC> getfZJC() {
        return fZJC;
    }

    public void setfZJC(List<FZJC> fZJC) {
        this.fZJC = fZJC;
    }

    public int getfYYCXCD() {
        return fYYCXCD;
    }

    public void setfYYCXCD(int fYYCXCD) {
        this.fYYCXCD = fYYCXCD;
    }

    public int getbLFY() {
        return bLFY;
    }

    public void setbLFY(int bLFY) {
        this.bLFY = bLFY;
    }

    public String getfYQK() {
        return fYQK;
    }

    public void setfYQK(String fYQK) {
        this.fYQK = fYQK;
    }

    public int getsFFLCD() {
        return sFFLCD;
    }

    public void setsFFLCD(int sFFLCD) {
        this.sFFLCD = sFFLCD;
    }

    public List<MedicineUse> getMedicineUse() {
        return medicineUse;
    }

    public void setMedicineUse(List<MedicineUse> medicineUse) {
        this.medicineUse = medicineUse;
    }

    public String getzZYY() {
        return zZYY;
    }

    public void setzZYY(String zZYY) {
        this.zZYY = zZYY;
    }

    public String getzZKB() {
        return zZKB;
    }

    public void setzZKB(String zZKB) {
        this.zZKB = zZKB;
    }

    public String getxCSF() {
        if (StringUtil.isEmptyString(xCSF)) {
            // 自动定位下月今天
            Date nextMonth = CalendarUtil.getNextMonth(new Date());
            xCSF = new SimpleDateFormat("yyyy-MM-dd").format(nextMonth);
        }
        return xCSF;
    }

    public void setxCSF(String xCSF) {
        this.xCSF = xCSF;
    }

    public String getzZCD() {
        return zZCD;
    }

    public void setzZCD(String zZCD) {
        this.zZCD = zZCD;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getzZQT() {
        return zZQT;
    }

    public void setzZQT(String zZQT) {
        this.zZQT = zZQT;
    }

    public String getbCSG() {
        return bCSG;
    }

    public void setbCSG(String bCSG) {
        this.bCSG = bCSG;
    }

    public String getjCSJ() {
        return jCSJ;
    }

    public void setjCSJ(String jCSJ) {
        this.jCSJ = jCSJ;
    }

    public String getxHDB() {
        return xHDB;
    }

    public void setxHDB(String xHDB) {
        this.xHDB = xHDB;
    }

    public String getqTJC() {
        return qTJC;
    }

    public void setqTJC(String qTJC) {
        this.qTJC = qTJC;
    }


    public int getxCXL() {
        return xCXL;
    }

    public void setxCXL(int xCXL) {
        this.xCXL = xCXL;
    }

    public String getqTXT() {
        return qTXT;
    }

    public void setqTXT(String qTXT) {
        this.qTXT = qTXT;
    }
    
    public String getcHXT() {
        return cHXT;
    }

    public void setcHXT(String cHXT) {
        this.cHXT = cHXT;
    }

    public BeanCD getxTLX() {
        return xTLX;
    }

    public void setxTLX(BeanCD xTLX) {
        this.xTLX = xTLX;
    } 

    public String getReferralVisitDate() {
        return referralVisitDate;
    }

    public void setReferralVisitDate(String referralVisitDate) {
        this.referralVisitDate = referralVisitDate;
    }

    public String getDutyDoctor() {
        return dutyDoctor;
    }

    public void setDutyDoctor(String dutyDoctor) {
        this.dutyDoctor = dutyDoctor;
    }

    public BeanCD getComplication() {
        return complication;
    }

    public void setComplication(BeanCD complication) {
        this.complication = complication;
    }

    public BeanCD getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(BeanCD comorbidity) {
        this.comorbidity = comorbidity;
    }

    public String getOtherDiseases() {
        return otherDiseases;
    }

    public void setOtherDiseases(String otherDiseases) {
        this.otherDiseases = otherDiseases;
    }

	/**
	 * @return the insulinUse
	 */
	public String getInsulinUse() {
		return insulinUse;
	}

	/**
	 * @param insulinUse
	 *            the insulinUse to set
	 */
	public void setInsulinUse(String insulinUse) {
		this.insulinUse = insulinUse;
	}

    public String getxCZS() {
        return xCZS;
    }

    public void setxCZS(String xCZS) {
        this.xCZS = xCZS;
    }
}
