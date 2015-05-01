package com.cking.phss.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.xinhuaxing.util.StringUtil;

import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.xml.util.XmlTag;

/**
 * 一次随访信息的记录
 * 
 * @author Administrator
 * 
 */
public class Sfgljl_gxy implements IBean {

	@XmlTag(name = "Type")
	private int type = 0;

    // <!-- 治疗建议 -->
    @XmlTag(name = "Recommendation")
    public String recommendation = "";

	@XmlTag(name="BZ")
	private String bz = "";

	@XmlTag(name = "VisitID")
	private String visitID = ""; // 随访序号

	@XmlTag(name = "VisitDate")
	private String visitDate = ""; // 随访日期

	// 必填。随访医生ID或代码
	@XmlTag(name = "DoctorID")
	private String doctorID = "";

	// 必填。随访医生姓名
	@XmlTag(name = "DoctorName")
	private String doctorName = "";
	
	// 必填。责任医生
	@XmlTag(name = "DutyDoctor")
	private String dutyDoctor = "";

	@XmlTag(name = "VisitDoctor")
	// 做随访的是哪个医生
    private BeanID visitDoctor;

	// 必填。随访方式代码（单选），值为代码：1.门诊 2.家庭 3.电话 4.集体
	@XmlTag(name = "SFFSCD")
	private int sffsCD = 1;

	// 高血压类型 值为代码：1：原发性；2：继发性；3：不详
	@XmlTag(name = "HBPTypeCD")
	private int hBPTypeCD = 1;

	// 症状代码（多选），多个代码之间用英文|分隔，值为代码：0无症状；1头痛头晕；2恶心呕吐；3眼花耳鸣；5呼吸困难；6心悸胸闷；7鼻衄出血不止；8四肢发麻；9下肢水肿；10其它
	@XmlTag(name = "ZZCD")
    private String ZZCD = "1";

	// 此处填其它症状
	@XmlTag(name = "ZZQT")
	private String ZZQT = "";

	// 必填。本次身高。单位：cm。整型
	@XmlTag(name = "BCSG")
	private String BCSG = "0";

	
	// 本次心率。单位：次/分。整型
	@XmlTag(name = "BCXL")
	private int bCXL = 0;
	
	// 下次心率。单位：次/分。整型
    @XmlTag(name = "XCXL")
    private int xCXL = 0;

	@XmlTag(name = "GXYZZQT")// 高血压其它症状
	private String gXYZZQT = "";

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
	
    //<!-- 下次体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2） -->
	@XmlTag(name = "XCZS")
	private String XCZS = "0";

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

	@XmlTag(name = "FYYCXCD")
	private int fYYCXCD = 1; // 服药依从性代码（单选），值为代码：1.规律服药 2.间断服药 3.不服药

	@XmlTag(name = "BLFY")
	private int bLFY = 0; // 药物不良反应（单选），值为代码：0：无；1：有

	@XmlTag(name = "FYQK")
	private String fYQK = "";// 不良反应情况-

	@XmlTag(name = "SFFLCD")
	private int sFFLCD = 1; // 此次随访分类代码（单选），值为代码：1控制满意，2控制不满意，3不良反应，4并发症

    @XmlTag(name = "ReferralVisitDate")
    private String referralVisitDate = ""; // 转诊日期
    
    @XmlTag(name = "ZZYY")
	private String zZYY = "";// 转诊原因

	@XmlTag(name = "ZZKB")
	private String zZKB = "";// 转诊机构及科别

	@XmlTag(name = "XCSF")
	// 下次随访日期
	private String xCSF = "";

	@XmlTag(name = "MedicineUse", isListWithoutGroupTag=true)
	private List<MedicineUse> medicineUse;// 药物名称
	
	@XmlTag(name = "DMBDCD")
	private int dMBDCD = 1;// 足背动脉搏动（单选），值为代码：1 未触及2 触及

	@XmlTag(name = "FZJC", isListWithoutGroupTag=true)
	private List<FZJC> fZJC;// 格式为 辅助检查项目|辅助检查结果 |检查人|检查日期

	@XmlTag(name = "BCZSL")
	private int bCZSL = 0; // 日主食量，单位：克/天，整型

	@XmlTag(name = "XCZSL")
	private int xCZSL = 0; // 下次希望主食量，单位：克/天，整型

	@XmlTag(name = "KFXT")
	private String kFXT = "0"; // 必填。空腹血糖，单位：mmol/L，浮点

	@XmlTag(name = "JCSJ")
	private String jCSJ = "";// 检查时间，格式：yyyy-mm-dd

	@XmlTag(name = "XHDB")
	private String xHDB = "0";// 糖化血红蛋白，单位：%，浮点

	@XmlTag(name = "QTJC")
	private String qTJC = "";// 其他检查

	@XmlTag(name = "DXTFYCD")
	private int dXTFYCD = 1; // 低血糖反应代码（单选），值为代码：1无 2 偶尔 3频繁
	
    @XmlTag(name = "CriticalOrgan")
    private BeanCD criticalOrgan = null;// <!-- 转诊回访--靶器官损害 CD:包括（心脏、脑、肾脏、周围血管、眼底、其他）的代码；值：文字 -->

    @XmlTag(name = "Comorbidity")
    private BeanCD comorbidity = null;// <!-- 转诊回访--合并症 CD包括（左室肥厚、心力衰竭、冠心病、心律失常、脑卒中、主动脉夹层动脉瘤、动脉粥样硬化、视网膜病变、其他）的代码；值：文字-->

    @XmlTag(name = "OtherDiseases")
    private String otherDiseases = "";// <!-- 转诊回访--其他疾病 -->

	public static class FZJC implements IBean {
		@XmlTag(name = "JCXM")
		private String jCXM = "";// 检查项目

		@XmlTag(name = "JCJG")
		private String jCJG = "";// 检查结果

		@XmlTag(name = "JCR")
		private String jCR = "";// 检查人

		@XmlTag(name = "JCRQ")
		// 检查日期
		private String jCRQ = "";

		public String getjCXM() {
			return jCXM;
		}

		public void setjCXM(String jCXM) {
			this.jCXM = jCXM;
		}

		public String getjCJG() {
			return jCJG;
		}

		public void setjCJG(String jCJG) {
			this.jCJG = jCJG;
		}

		public String getjCR() {
			return jCR;
		}

		public void setjCR(String jCR) {
			this.jCR = jCR;
		}

		public String getjCRQ() {
			return jCRQ;
		}

		public void setjCRQ(String jCRQ) {
			this.jCRQ = jCRQ;
		}
	}

	public List<FZJC> getfZJC() {
		return fZJC;
	}

	public String getgXYZZQT() {
		return gXYZZQT;
	}

	public void setgXYZZQT(String gXYZZQT) {
		this.gXYZZQT = gXYZZQT;
	}

	public int gethBPTypeCD() {
		return hBPTypeCD;
	}

	public void sethBPTypeCD(int hBPTypeCD) {
		this.hBPTypeCD = hBPTypeCD;
	}

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
	
/*	public String getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(String nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}
*/

    public BeanID getVisitDoctor() {
		return visitDoctor;
	}

    public void setVisitDoctor(BeanID visitDoctor) {
		this.visitDoctor = visitDoctor;
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
	
	public String getXCZS() {
		return XCZS;
	}

	public void setXCZS(String XCZS) {
		this.XCZS = XCZS;
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

	public List<MedicineUse> getMedicineUse() {
		return medicineUse;
	}

	public void setMedicineUse(List<MedicineUse> medicineUse) {
		this.medicineUse = medicineUse;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	
	public String getDutyDoctor() {
		return dutyDoctor;
	}

	public void setDutyDoctor(String dutyDoctor) {
		this.dutyDoctor = dutyDoctor;
	}

	public int getsFFSCD() {
		return sffsCD;
	}

	public void setsFFSCD(int sffsCD) {
		this.sffsCD = sffsCD;
	}

	public String getZZCD() {
		return ZZCD;
	}

	public void setZZCD(String zZCD) {
		ZZCD = zZCD;
	}

	public String getZZQT() {
		return ZZQT;
	}

	public void setZZQT(String zZQT) {
		ZZQT = zZQT;
	}

	public String getBCSG() {
		return BCSG;
	}

	public void setBCSG(String bCSG) {
		BCSG = bCSG;
	}


	public int getbCXL() {
        return bCXL;
    }

    public void setbCXL(int bCXL) {
        this.bCXL = bCXL;
    }

    public int getdMBDCD() {
		return dMBDCD;
	}

	public void setdMBDCD(int dMBDCD) {
		this.dMBDCD = dMBDCD;
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

	public int getdXTFYCD() {
		return dXTFYCD;
	}

	public void setdXTFYCD(int dXTFYCD) {
		this.dXTFYCD = dXTFYCD;
	} 
	public void setfZJC(List<FZJC> fZJC) {
		this.fZJC = fZJC;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

    public int getxCXL() {
        return xCXL;
    }

    public void setxCXL(int xCXL) {
        this.xCXL = xCXL;
    } 

    public String getReferralVisitDate() {
        return referralVisitDate;
    }

    public void setReferralVisitDate(String referralVisitDate) {
        this.referralVisitDate = referralVisitDate;
    }

    public BeanCD getCriticalOrgan() {
        return criticalOrgan;
    }

    public void setCriticalOrgan(BeanCD criticalOrgan) {
        this.criticalOrgan = criticalOrgan;
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
}