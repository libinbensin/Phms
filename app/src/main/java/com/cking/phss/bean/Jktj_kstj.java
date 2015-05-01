package com.cking.phss.bean;

import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.bean.Jktj_kstj 代表健康体检的快速体检的bean
 * 
 * @author TaoWenCong <br/>
 *         create at 2012-9-29 上午09:34:57
 */
public class Jktj_kstj implements IBean {
    /**
     * 血压
     */
    @XmlTag(name = "SBP")
    private String sBP = ""; // 收缩压

    @XmlTag(name = "DBP")
    private String dBP = ""; // 舒张压

    @XmlTag(name = "XL")
    private String xL = ""; // 心率

    @XmlTag(name = "XYJL")
    private String xYJL = ""; // 血压结论

    /**
     * 血糖
     */
    @XmlTag(name = "XTCSTJ")
    private int xTCSTJ = 0; // 血糖测试条件 1 2 3

    @XmlTag(name = "XTValue")
    private String xTValue = ""; // 随即血糖的值

    @XmlTag(name = "XTJL")
    private String xTJL = ""; // 随即血糖的结论

    /**
     * 血脂
     */
    @XmlTag(name = "DGC")
    private String dGC = "";// 胆固醇

    @XmlTag(name = "DGCJL")
    private String dGCJL = "";

    @XmlTag(name = "GYSZ")
    private String gYSZ = "";// 甘油三酯

    @XmlTag(name = "GYSZJL")
    private String gYSZJL = "";

    @XmlTag(name = "HDL")
    private String HDL = "";// 高密度脂蛋白

    @XmlTag(name = "HDLR")
    private String HDLR = "";

    @XmlTag(name = "LDL")
    private String LDL = "";// 低密度脂蛋白

    @XmlTag(name = "LDLR")
    private String LDLR = "";

    @XmlTag(name = "NS")
    private String NS = "";// 尿酸

    @XmlTag(name = "NSR")
    private String NSR = "";
    /**
     * 身体成分
     */
    @XmlTag(name = "Height")
    // 身高。
    private String height = "";

    @XmlTag(name = "Weight")
    // 体重
    private String weight = "";

    @XmlTag(name = "BMI")
    // 体质 公式=体重（kg）/身高的平方（m2）。浮点 -->
    private String bMI = "";

    @XmlTag(name = "IMP")
    private String iMP = "";// 阻抗

    @XmlTag(name = "Fat")
    private String fat = "";// 体脂肪率

    @XmlTag(name = "VisceralFat")
    private String visceralFat = "";// 体脂肪率

    @XmlTag(name = "BMR")
    private String bMR = "";// 基础代谢

    @XmlTag(name = "RBMR")
    private String rBMR = "";// 相对基础代谢

    @XmlTag(name = "TBW")
    private String tBW = "";// 水分含量

    @XmlTag(name = "Mus")
    private String mus = "";// 肌肉含量

    @XmlTag(name = "Bone")
    private String bone = "";// 骨含量

    @XmlTag(name = "Ctype")
    private String ctype = "";// 身体类型

    @XmlTag(name = "Cname")
    private String cname = "";// 结论

    /**
     * 三围
     */
    @XmlTag(name = "Bust")
    // 胸围
    private String bust = "";

    @XmlTag(name = "HIP")
    // 臀围
    private String hIP = "";

    @XmlTag(name = "Waist")
    // 腰围
    private String waist = "";

    @XmlTag(name = "YWJL")
    // 腰围
    private String ywjl = "";// 腰围结论

    @XmlTag(name = "YTB")
    // 腰围
    private String ytb = "";// 腰臀比

    @XmlTag(name = "YTBJL")
    // 腰围
    private String ytbjl = "";// 腰臀比结论

    @XmlTag(name = "ZK")
    private String zk = "";// 阻抗
    
    /**
     * 体温检测
     * @return
     */

    @XmlTag(name = "TW")
    private String tw = "";// 体温

    @XmlTag(name = "TWJL")
    private String twjl = "";// 体温结论

    /**
     * 体质检测
     * @return
     */
    @XmlTag(name = "TZJL")
    private String tzjl = "";// 体质结论
    
    /**
     * 血氧
     * @return
     */

    @XmlTag(name = "XYBHD")
    private String xybhd = "";// 血氧饱和度

    @XmlTag(name = "XYML")
    private String xyml = "";// 脉率

    @XmlTag(name = "XYJL")
    private String xyjl = "";// 血氧结论

    /**
     * 心电分析
     * @return
     */
    @XmlTag(name = "XDJCSJ")
    private String xdjcsj = "";// 心电检查时间
    
    @XmlTag(name = "XDXL")
    private String xdxl = "";// 心电心率
    
    @XmlTag(name = "XDPSX")
    private String xdpsx = "";// 心电P时限
    
    @XmlTag(name = "XDPRJQ")
    private String xdprjq = "";// 心电PR间期
    
    @XmlTag(name = "XDQRSSX")
    private String xdqrssx = "";// 心电QRS时限
    
    @XmlTag(name = "XDQRJQ")
    private String xdqrjq = "";// 心电QR间期
    
    @XmlTag(name = "XDQTCJQ")
    private String xdqtcjq = "";// 心电QTc间期
    
    @XmlTag(name = "XDRV5ZF")
    private String xdrv5zf = "";// 心电RV5振幅
    
    @XmlTag(name = "XDRV6ZF")
    private String xdrv6zf = "";// 心电RV6振幅
    
    @XmlTag(name = "XDSV1ZF")
    private String xdsv1zf = "";// 心电SV1振幅
    
    @XmlTag(name = "XDSV2ZF")
    private String xdsv2zf = "";// 心电SV2振幅
    
    @XmlTag(name = "XDPDZ")
    private String xdpdz = "";// 心电P电轴
    
    @XmlTag(name = "XDQRSDZ")
    private String xdqrsdz = "";// 心电QRS电轴
    
    @XmlTag(name = "XDTDZ")
    private String xdtdz = "";// 心电T电轴

    @XmlTag(name = "XDJL")
    private String xdjl = "";// 心电结论

    @XmlTag(name = "XDBXT")
    private String xdbxt = "";// 心电波形图

    public String yxsybdljg = "";// 亚硝酸盐半定量结果
    public String yxsyjl = "";// 亚硝酸盐结论
    public String bxbz = "";// 白细胞值
    public String bxbbdljg = "";// 白细胞半定量结果
    public String bxbjl = "";// 白细胞结论
    public String ndyz = "";// 尿胆原值
    public String ndyjl = "";// 尿胆原结论
    public String ndbz = "";// 尿蛋白值
    public String ndbbdljg = "";// 尿蛋白半定量结果
    public String ndbjl = "";// 尿蛋白结论
    public String qxz = "";// 潜血值
    public String qxbdljg = "";// 潜血半定量结果
    public String qxjl = "";// 潜血结论
    public String nttz = "";// 尿酮体值
    public String nttbdljg = "";// 尿酮体半定量结果
    public String nttjl = "";// 尿酮体结论
    public String dhsz = "";// 胆红素值
    public String dhsbdljg = "";// 胆红素半定量结果
    public String dhsjl = "";// 胆红素结论
    public String ntz = "";// 尿糖值
    public String ntbdljg = "";// 尿糖半定量结果
    public String ntjl = "";// 尿糖结论
    public String wsscz = "";// 维生素C值
    public String wssbdljg = "";// 维生素半定量结果
    public String wsscjl = "";// 维生素C结论
    public String phz = "";// PH值
    public String phjl = "";// PH结论
    public String nbz = "";// 尿比重
    public String nbzjl = "";// 尿比重结论

    /**
     * 中医体质辨识
     * @return
     */

    @XmlTag(name = "ZYTZBSLX")
    private String zytzbslx = "";// 中医体质辨识类型

    /**
     * 心理测试
     * @return
     */

    @XmlTag(name = "XLCSJL")
    private String xlcsjl = "";// 心理测试结论

    /**
     * 老年评估
     * @return
     */

    @XmlTag(name = "LNPGJG")
    private String lnpgjg = "";// 老年评估结果
    
    public String getsBP() {
        return sBP;
    }

    public void setsBP(String sBP) {
        this.sBP = sBP;
    }

    public String getdBP() {
        return dBP;
    }

    public void setdBP(String dBP) {
        this.dBP = dBP;
    }

    public String getxL() {
        return xL;
    }

    public void setxL(String xL) {
        this.xL = xL;
    }

    public String getxYJL() {
        return xYJL;
    }

    public void setxYJL(String xYJL) {
        this.xYJL = xYJL;
    }

    public int getxTCSTJ() {
        return xTCSTJ;
    }

    public void setxTCSTJ(int xTCSTJ) {
        this.xTCSTJ = xTCSTJ;
    }

    public String getxTValue() {
        return xTValue;
    }

    public void setxTValue(String xTValue) {
        this.xTValue = xTValue;
    }

    public String getxTJL() {
        return xTJL;
    }

    public void setxTJL(String xTJL) {
        this.xTJL = xTJL;
    }

    public String getdGC() {
        return dGC;
    }

    public void setdGC(String dGC) {
        this.dGC = dGC;
    }

    public String getdGCJL() {
        return dGCJL;
    }

    public void setdGCJL(String dGCJL) {
        this.dGCJL = dGCJL;
    }

    public String getgYSZ() {
        return gYSZ;
    }

    public void setgYSZ(String gYSZ) {
        this.gYSZ = gYSZ;
    }

    public String getgYSZJL() {
        return gYSZJL;
    }

    public void setgYSZJL(String gYSZJL) {
        this.gYSZJL = gYSZJL;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getbMI() {
        return bMI;
    }

    public void setbMI(String bMI) {
        this.bMI = bMI;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String gethIP() {
        return hIP;
    }

    public void sethIP(String hIP) {
        this.hIP = hIP;
    }

    public String getiMP() {
        return iMP;
    }

    public void setiMP(String iMP) {
        this.iMP = iMP;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getVisceralFat() {
        return visceralFat;
    }

    public void setVisceralFat(String visceralFat) {
        this.visceralFat = visceralFat;
    }

    public String getbMR() {
        return bMR;
    }

    public void setbMR(String bMR) {
        this.bMR = bMR;
    }

    public String getrBMR() {
        return rBMR;
    }

    public void setrBMR(String rBMR) {
        this.rBMR = rBMR;
    }

    public String gettBW() {
        return tBW;
    }

    public void settBW(String tBW) {
        this.tBW = tBW;
    }

    public String getMus() {
        return mus;
    }

    public void setMus(String mus) {
        this.mus = mus;
    }

    public String getBone() {
        return bone;
    }

    public void setBone(String bone) {
        this.bone = bone;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getYwjl() {
        return ywjl;
    }

    public void setYwjl(String ywjl) {
        this.ywjl = ywjl;
    }

    public String getYtb() {
        return ytb;
    }

    public void setYtb(String ytb) {
        this.ytb = ytb;
    }

    public String getYtbjl() {
        return ytbjl;
    }

    public void setYtbjl(String ytbjl) {
        this.ytbjl = ytbjl;
    }

    public String getZk() {
        return zk;
    }

    public void setZk(String zk) {
        this.zk = zk;
    }

    public String getHDL() {
        return this.HDL;
    }

    public void setHDL(String hDL) {
        this.HDL = hDL;
    }

    public String getHDLR() {
        return this.HDLR;
    }

    public void setHDLR(String hDLR) {
        this.HDLR = hDLR;
    }

    public String getLDL() {
        return this.LDL;
    }

    public void setLDL(String lDL) {
        this.LDL = lDL;
    }

    public String getLDLR() {
        return this.LDLR;
    }

    public void setLDLR(String lDLR) {
        this.LDLR = lDLR;
    }

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw;
    }

    public String getTwjl() {
        return twjl;
    }

    public void setTwjl(String twjl) {
        this.twjl = twjl;
    }

    public String getTzjl() {
        return tzjl;
    }

    public void setTzjl(String tzjl) {
        this.tzjl = tzjl;
    }

    public String getXybhd() {
        return xybhd;
    }

    public void setXybhd(String xybhd) {
        this.xybhd = xybhd;
    }

    public String getXyml() {
        return xyml;
    }

    public void setXyml(String xyml) {
        this.xyml = xyml;
    }

    public String getXyjl() {
        return xyjl;
    }

    public void setXyjl(String xyjl) {
        this.xyjl = xyjl;
    }

    public String getXdjl() {
        return xdjl;
    }

    public void setXdjl(String xdjl) {
        this.xdjl = xdjl;
    }

    public String getZytzbslx() {
        return zytzbslx;
    }

    public void setZytzbslx(String zytzbslx) {
        this.zytzbslx = zytzbslx;
    }

    public String getXlcsjl() {
        return xlcsjl;
    }

    public void setXlcsjl(String xlcsjl) {
        this.xlcsjl = xlcsjl;
    }

    public String getLnpgjg() {
        return lnpgjg;
    }

    public void setLnpgjg(String lnpgjg) {
        this.lnpgjg = lnpgjg;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String nS) {
        NS = nS;
    }

    public String getNSR() {
        return NSR;
    }

    public void setNSR(String nSR) {
        NSR = nSR;
    }

    public String getXdjcsj() {
        return xdjcsj;
    }

    public void setXdjcsj(String xdjcsj) {
        this.xdjcsj = xdjcsj;
    }

    public String getXdxl() {
        return xdxl;
    }

    public void setXdxl(String xdxl) {
        this.xdxl = xdxl;
    }

    public String getXdpsx() {
        return xdpsx;
    }

    public void setXdpsx(String xdpsx) {
        this.xdpsx = xdpsx;
    }

    public String getXdprjq() {
        return xdprjq;
    }

    public void setXdprjq(String xdprjq) {
        this.xdprjq = xdprjq;
    }

    public String getXdqrssx() {
        return xdqrssx;
    }

    public void setXdqrssx(String xdqrssx) {
        this.xdqrssx = xdqrssx;
    }

    public String getXdqrjq() {
        return xdqrjq;
    }

    public void setXdqrjq(String xdqrjq) {
        this.xdqrjq = xdqrjq;
    }

    public String getXdqtcjq() {
        return xdqtcjq;
    }

    public void setXdqtcjq(String xdqtcjq) {
        this.xdqtcjq = xdqtcjq;
    }

    public String getXdrv5zf() {
        return xdrv5zf;
    }

    public void setXdrv5zf(String xdrv5zf) {
        this.xdrv5zf = xdrv5zf;
    }

    public String getXdrv6zf() {
        return xdrv6zf;
    }

    public void setXdrv6zf(String xdrv6zf) {
        this.xdrv6zf = xdrv6zf;
    }

    public String getXdsv1zf() {
        return xdsv1zf;
    }

    public void setXdsv1zf(String xdsv1zf) {
        this.xdsv1zf = xdsv1zf;
    }

    public String getXdsv2zf() {
        return xdsv2zf;
    }

    public void setXdsv2zf(String xdsv2zf) {
        this.xdsv2zf = xdsv2zf;
    }

    public String getXdpdz() {
        return xdpdz;
    }

    public void setXdpdz(String xdpdz) {
        this.xdpdz = xdpdz;
    }

    public String getXdqrsdz() {
        return xdqrsdz;
    }

    public void setXdqrsdz(String xdqrsdz) {
        this.xdqrsdz = xdqrsdz;
    }

    public String getXdtdz() {
        return xdtdz;
    }

    public void setXdtdz(String xdtdz) {
        this.xdtdz = xdtdz;
    }

    public String getXdbxt() {
        return xdbxt;
    }

    public void setXdbxt(String xdbxt) {
        this.xdbxt = xdbxt;
    }
}
