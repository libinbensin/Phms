/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * SjglScglUploader.java
 * classes : com.cking.phss.util.SjglScglUploader
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-10 上午3:57:26
 */
package com.cking.phss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.Log;

import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromDb;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.HistoryDisease;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_gxy.FZJC;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.BcjmjbxxhjtxxJ003;
import com.cking.phss.dto.Bcjmxwxg8_1;
import com.cking.phss.dto.Bcjtxxxx6;
import com.cking.phss.dto.BcsfzzpJ002;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.JmjkxxbcJ007;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.dto.sfgl.gxy.Bcgxysfjl16;
import com.cking.phss.dto.sfgl.tnb.Bctnbsfjl23;
import com.cking.phss.file.FileHelper;
import com.cking.phss.global.Global;
import com.cking.phss.net.ISoapRecv;
import com.cking.phss.net.WebService;
import com.cking.phss.sqlite.Resident;
import com.cking.phss.sqlite.ResidentBll;
import com.cking.phss.sqlite.Sfgl;
import com.cking.phss.sqlite.SfglBll;
import com.cking.phss.xml.util.XmlSerializerUtil;

/**
 * com.cking.phss.util.SjglScglUploader
 * @author Administrator <br/>
 * create at 2014-7-10 上午3:57:26
 */
public class SjglScglUploader {
    private static final String TAG = "SjglScglUploader";
    public Map<String, IBean> beanMap = null;

    private static SjglScglUploader mSjglScglUploader;

    public static SjglScglUploader getInstance() {
        if (mSjglScglUploader == null) {
            mSjglScglUploader = new SjglScglUploader();
        }
        return mSjglScglUploader;
    }

    public interface OnSjglScglUploadLisener {
        public void onUploadFinished(boolean isSuccessful, String tipText);
    }

    private OnSjglScglUploadLisener mOnSjglScglUploadLisener;

    public void setOnSjglScglUploadLisener(OnSjglScglUploadLisener listener) {
        mOnSjglScglUploadLisener = listener;
    }

    public void getJktjFromDbAndSaveToWeb(Context mContext, final Map<String, IBean> beanMap,
            String residentUUID, String projectUUID, String residentId) {
        this.beanMap = beanMap;
        getKstjSfxxFromDb(residentUUID, projectUUID, residentId);
    }

    public void getKstjSfxxFromDb(String residentUUID, String projectUUID, String residentId) {
        // 在取出快速体检的数据放进去
        List<Class<? extends IBean>> kstjListBean = new ArrayList<Class<? extends IBean>>();
        kstjListBean.add(Jktj_kstj.class);
        BeanUtil.getInstance().getLastJktjFromDb(kstjListBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {
                    if (listBean.get(0) != null) {
                        beanMap.put(Jktj_kstj.class.getName(), listBean.get(0));

                        // 发送
                        // saveKjtjToWeb();
                        jktjRegister(); // 体检登记
                    } else {
                        if (mOnSjglScglUploadLisener != null) {
                            mOnSjglScglUploadLisener.onUploadFinished(false, "读取数据库失败");
                        }
                    }
                }
            }
        });
    }

    private void jktjRegister() {
        // 体检登记
        String strCheckin = assemblePacket_Checkin();
        if (strCheckin == null)
            return;
        WebService.getInstance().excute("Checkin", "data", strCheckin, new ISoapRecv() {
            @Override
            public void onRecvData(String xmlStr, boolean success) {
                Log.v(TAG, "Result >>>>>" + xmlStr + "code: " + success);
                if (success) {
                    // mToast.setText("体检登记成功！");
                    // mToast.show();
                    if (xmlStr.contains("<CHECKINID>")) {// 包含这个字符传，表示登记成功返回登记体检号
                        int start = xmlStr.indexOf("<CHECKINID>") + "<CHECKINID>".length();
                        int end = xmlStr.indexOf("</CHECKINID>");

                        // 体检编号
                        String tjbh = xmlStr.substring(start, end);
                        // 发送数据
                        saveKjtjToWeb(tjbh);
                        return;
                    } else {// 没有这个节点，表示返回了错误信息
                        if (mOnSjglScglUploadLisener != null) {
                            mOnSjglScglUploadLisener.onUploadFinished(false, "体检登记失败");
                        }
                    // mToast.setText(xmlStr);
                    // mToast.show();
                    }
                } else {
                    if (mOnSjglScglUploadLisener != null) {
                        mOnSjglScglUploadLisener.onUploadFinished(false, "体检登记失败");
                    }
                    // mToast.setText("体检登记失败！");
                    // mToast.show();
                }
            }

        });

    }

    /**
     * @return
     */
    private String assemblePacket_Checkin() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        // 获取身份证号码
        String paperNum = mJmjbxx.getPaperNum();
        String sex = null;
        switch (mJmjbxx.getSexCD()) {
            case 1:
                sex = "M";
                break;
            case 2:
                sex = "F";
                break;
            default:
                sex = "N";
                break;
        }
        String birthDay = mJmjbxx.getBirthDay();
        // 10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
        String marriage = null;
        switch (mJmjbxx.getMarriageCD()) {
        // (1/2/3/4/5/6 已婚/未婚/离异/再婚/丧偶/不详)
            case 10:
                marriage = "2";
                break;
            case 20:
                marriage = "1";
                break;
            case 22:
                marriage = "4";
                break;
            case 23:
                marriage = "6";
                break;
            case 30:
                marriage = "5";
                break;
            case 40:
                marriage = "3";
                break;
            case 90:
                marriage = "6";
                break;
            default:
                marriage = "6";
                break;
        }
        String docId = null;
        if (mJmjbxx.getDutyDoctor() != null) {
            docId = mJmjbxx.getDutyDoctor().getiD();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int age = 0;
        // 计算年龄
        try {
            age = new Date(System.currentTimeMillis()).getYear()
                    - dateFormat.parse(birthDay).getYear() + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strRequest = "<Staff>" + "<NAME>" + mJmjbxx.getResidentName() + "</NAME>" + "<SEX>"
                + sex + "</SEX>" + "<BIRTHDAY>" + birthDay + "</BIRTHDAY>" + "<IDCARD>" + paperNum
                + "</IDCARD>" + "<MARRIAGE>" + marriage + "</MARRIAGE>" + "<AGE>" + age + "</AGE>"
                + "<TEL></TEL>" + "<ADDRESS></ADDRESS>" + "<DOC_ID>" + docId + "</DOC_ID>"
                + "</Staff>";
        return strRequest;
    }

    /**
     * 
     */
    public void saveKjtjToWeb(String strCHECKINID) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        // 保存结果
        if (strCHECKINID.equals("")) {
            if (mOnSjglScglUploadLisener != null) {
                mOnSjglScglUploadLisener.onUploadFinished(false, "无法获取体检编号");
            }
            // mToast.setText("无法获取体检编号！");
            // mToast.show();
            return;
        } else {
            String strSaveResults = assemblePacket_SaveResults(strCHECKINID);
            if (strSaveResults == null)
                return;
            WebService.getInstance().excute("SaveResults", "data", strSaveResults, new ISoapRecv() {

                @Override
                public void onRecvData(String xmlStr, boolean success) {
                    Log.v(TAG, "Result >>>>>" + xmlStr + "code: " + success);
                    if (success) {
                        if (mOnSjglScglUploadLisener != null) {
                            mOnSjglScglUploadLisener.onUploadFinished(true, "上传快速体检数据成功");
                        }
                        // mToast.setText("上传快速体检数据成功！");
                        // mToast.show();
                    } else {
                        if (mOnSjglScglUploadLisener != null) {
                            mOnSjglScglUploadLisener.onUploadFinished(false, "上传快速体检数据失败");
                        }
                        // mToast.setText("上传快速体检数据失败！");
                        // mToast.show();
                    }
                }

            });
        }
    }

    /**
     * @param strCHECKINID
     * @return
     */
    public String assemblePacket_SaveResults(String strCHECKINID) {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        String result = "";
        String CHECKINID = strCHECKINID;
        String DRIVERID = "";
        String PROJECTCODE = "";
        String PROJECTNAME = "";
        String RESULT = "";
        String UNIT = "";
        String REFERENCE = "";
        String EXCEPTIONTIPS = "";
        String TRUN = "";
        String CHECKDATE = "";
        String DOC_ID = "";
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            if (mOnSjglScglUploadLisener != null) {
                mOnSjglScglUploadLisener.onUploadFinished(false, "当前没有医生登录，请先登录！");
            }
            // mToast.setText("当前没有医生登录，请先登录！");
            // mToast.show();
            return null;
        }
        DOC_ID = login1.response.doctorID;

        String head = "<Results>";
        result += head;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CHECKINID = strCHECKINID;
        // 血压
        {
            DRIVERID = "02";
            // 收缩压
            if (!mJktj_kstj.getsBP().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getsBP().trim()) > 0) {
                PROJECTCODE = "020001";
                PROJECTNAME = "收缩压";
                RESULT = String.valueOf(mJktj_kstj.getsBP());
                UNIT = "mmHg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 舒张压
            if (!mJktj_kstj.getdBP().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getdBP().trim()) > 0) {
                PROJECTCODE = "020002";
                PROJECTNAME = "舒张压";
                RESULT = String.valueOf(mJktj_kstj.getdBP());
                UNIT = "mmHg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 脉搏
            if (!mJktj_kstj.getxL().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getxL().trim()) > 0) {
                PROJECTCODE = "020003";
                PROJECTNAME = "脉搏";
                RESULT = String.valueOf(mJktj_kstj.getxL());
                UNIT = "次/min";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 血压结论
            if (mJktj_kstj.getxYJL() != null && !mJktj_kstj.getxYJL().trim().equals("")) {
                PROJECTCODE = "020005";
                PROJECTNAME = "血压结论";
                RESULT = mJktj_kstj.getxYJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 胆固醇
        {
            DRIVERID = "17";
            // 胆固醇值
            if (!mJktj_kstj.getdGC().equals("") && Float.parseFloat(mJktj_kstj.getdGC()) > 0) {
                PROJECTCODE = "170001";
                PROJECTNAME = "胆固醇值";
                RESULT = String.valueOf(mJktj_kstj.getdGC());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 胆固醇结论
            if (mJktj_kstj.getdGCJL() != null && !mJktj_kstj.getdGCJL().equals("")) {
                PROJECTCODE = "170002";
                PROJECTNAME = "胆固醇结论";
                RESULT = mJktj_kstj.getdGCJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 甘油三酯
        {
            DRIVERID = "16";
            // 甘油三酯值
            if (!mJktj_kstj.getgYSZ().equals("") && Float.parseFloat(mJktj_kstj.getgYSZ()) > 0) {
                PROJECTCODE = "160001";
                PROJECTNAME = "甘油三酯值";
                RESULT = String.valueOf(mJktj_kstj.getgYSZ());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 甘油三酯结论
            if (mJktj_kstj.getgYSZJL() != null && !mJktj_kstj.getgYSZJL().equals("")) {
                PROJECTCODE = "160002";
                PROJECTNAME = "甘油三酯结论";
                RESULT = mJktj_kstj.getgYSZJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        // 高密度脂蛋白
        {
            DRIVERID = "20";
            // 高密度脂蛋白值
            if (!mJktj_kstj.getHDL().equals("") && Float.parseFloat(mJktj_kstj.getHDL()) > 0) {
                PROJECTCODE = "200001";
                PROJECTNAME = "高密度脂蛋白值";
                RESULT = String.valueOf(mJktj_kstj.getHDL());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 高密度脂蛋白结论
            if (mJktj_kstj.getHDLR() != null && !mJktj_kstj.getHDLR().equals("")) {
                PROJECTCODE = "200002";
                PROJECTNAME = "高密度脂蛋白结论";
                RESULT = mJktj_kstj.getHDLR();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        // 低密度脂蛋白
        {
            DRIVERID = "21";
            // 低密度脂蛋白值
            if (!mJktj_kstj.getLDL().equals("") && Float.parseFloat(mJktj_kstj.getLDL()) > 0) {
                PROJECTCODE = "210001";
                PROJECTNAME = "低密度脂蛋白值";
                RESULT = String.valueOf(mJktj_kstj.getLDL());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 低密度脂蛋白结论
            if (mJktj_kstj.getLDLR() != null && !mJktj_kstj.getLDLR().equals("")) {
                PROJECTCODE = "210002";
                PROJECTNAME = "低密度脂蛋白结论";
                RESULT = mJktj_kstj.getLDLR();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        // 血糖
        {
            DRIVERID = "03";
            // 血糖值
            if (!mJktj_kstj.getxTValue().equals("")
                    && Float.parseFloat(mJktj_kstj.getxTValue()) > 0) {
                PROJECTCODE = "030001";
                PROJECTNAME = "血糖值";
                RESULT = String.valueOf(mJktj_kstj.getxTValue());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 血糖结论
            if (mJktj_kstj.getxTJL() != null && !mJktj_kstj.getxTJL().equals("")) {
                PROJECTCODE = "030002";
                PROJECTNAME = "血糖结论";
                RESULT = mJktj_kstj.getxTJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 腰围
        {
            DRIVERID = "18";
            // 胸围
            if (!mJktj_kstj.getBust().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getBust().trim()) > 0) {
                PROJECTCODE = "180001";
                PROJECTNAME = "胸围";
                RESULT = String.valueOf(mJktj_kstj.getBust());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 腰围
            if (!mJktj_kstj.getWaist().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getWaist().trim()) > 0) {
                PROJECTCODE = "180002";
                PROJECTNAME = "腰围";
                RESULT = String.valueOf(mJktj_kstj.getWaist());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 臀围
            if (!mJktj_kstj.gethIP().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.gethIP().trim()) > 0) {
                PROJECTCODE = "180003";
                PROJECTNAME = "臀围";
                RESULT = String.valueOf(mJktj_kstj.gethIP());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 腰臀比结论
            if (mJktj_kstj.getYtbjl() != null && !mJktj_kstj.getYtbjl().trim().equals("")) {
                PROJECTCODE = "180004";
                PROJECTNAME = "结论";
                RESULT = mJktj_kstj.getYtbjl();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 人体成分
        {
            DRIVERID = "08";
            // 体脂肪率
            if (!mJktj_kstj.getFat().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getFat().trim()) > 0) {
                PROJECTCODE = "080004";
                PROJECTNAME = "体脂肪率";
                RESULT = String.valueOf(mJktj_kstj.getFat());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 基础代谢
            if (!mJktj_kstj.getbMR().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getbMR().trim()) > 0) {
                PROJECTCODE = "080006";
                PROJECTNAME = "基础代谢";
                RESULT = String.valueOf(mJktj_kstj.getbMR());
                UNIT = "Kcal";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 相对基础代谢
            if (!mJktj_kstj.getrBMR().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getrBMR().trim()) > 0) {
                PROJECTCODE = "080007";
                PROJECTNAME = "相对基础代谢";
                RESULT = String.valueOf(mJktj_kstj.getrBMR());
                UNIT = "Kcal";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 肌肉含量
            if (!mJktj_kstj.getMus().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getMus().trim()) > 0) {
                PROJECTCODE = "080011";
                PROJECTNAME = "肌肉含量";
                RESULT = String.valueOf(mJktj_kstj.getMus());
                UNIT = "Kg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 水分含量
            if (!mJktj_kstj.gettBW().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.gettBW().trim()) > 0) {
                PROJECTCODE = "080012";
                PROJECTNAME = "水分含量";
                RESULT = String.valueOf(mJktj_kstj.gettBW());
                UNIT = "Kg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 阻抗
            if (!mJktj_kstj.getiMP().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getiMP().trim()) > 0) {
                PROJECTCODE = "080013";
                PROJECTNAME = "阻抗";
                RESULT = String.valueOf(mJktj_kstj.getiMP());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // bmi
            if (!mJktj_kstj.getbMI().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getbMI().trim()) > 0) {
                PROJECTCODE = "080016";
                PROJECTNAME = "BMI";
                RESULT = String.valueOf(mJktj_kstj.getbMI());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 结论
            if (!mJktj_kstj.getCname().trim().equals("")) {
                PROJECTCODE = "080017";
                PROJECTNAME = "结论建议";
                RESULT = String.valueOf(mJktj_kstj.getCname());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 内脏脂肪
            if (!mJktj_kstj.getVisceralFat().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getVisceralFat().trim()) > 0) {
                PROJECTCODE = "080018";
                PROJECTNAME = "BMI";
                RESULT = String.valueOf(mJktj_kstj.getVisceralFat());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 骨含量
            if (!mJktj_kstj.getBone().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getBone().trim()) > 0) {
                PROJECTCODE = "080019";
                PROJECTNAME = "Kg";
                RESULT = String.valueOf(mJktj_kstj.getBone());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 身体类型
            if (!mJktj_kstj.getCtype().trim().equals("")) {
                PROJECTCODE = "080020";
                PROJECTNAME = "";
                RESULT = String.valueOf(mJktj_kstj.getCtype());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        String tail = "</Results>";
        result += tail;
        Log.i(TAG, result);
        return result;
    }

    public void getTnbFromDbAndSaveToWeb(Context mContext, final Map<String, IBean> beanMap,
            String residentUUID, String projectUUID, String residentId) {
        this.beanMap = beanMap;
        getTnbSfxxFromDb(residentUUID, projectUUID, residentId);
    }

    // 获取最后一条糖尿病随访信息
    public void getTnbSfxxFromDb(final String residentUUID, String projectUUID,
            final String residentId) {
        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            if (mOnSjglScglUploadLisener != null) {
                mOnSjglScglUploadLisener.onUploadFinished(false, "当前没有医生登录，请先登录！");
            }
            // // mToast.setText("当前没有医生登录，请先登录！");
            // // mToast.show();
            return;
        }
        // 在取最近一次的糖尿病和糖尿病随访记录
        List<Class<? extends IBean>> sfListBean = new ArrayList<Class<? extends IBean>>();
        sfListBean.add(Sfgljl_tnb.class);
        BeanUtil.getInstance().getLastSfglFromDb(sfListBean, new BeanUtil.OnResultFromDb() {

            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {
                    Sfgljl_tnb sfgljl = (Sfgljl_tnb) listBean.get(0);
                    try {
                        Date visitDate = new SimpleDateFormat("yyyy-MM-dd").parse(sfgljl
                                .getVisitDate());
                        // 因为每天只能新增一条数据，所以要判断是否是同一天
                        if (visitDate.getDay() == new Date().getDay()) {
                            // 如果是同一天
                            // mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                            // mToast.show();
                            sfgljl.setVisitID(sfgljl.getVisitID());// 使用上一次的id，即为编辑模式
                            sfgljl.setVisitDate(sfgljl.getVisitDate());
                        } else {
                            sfgljl.setVisitID("");// ID为空，则为新增模式
                            sfgljl.setVisitDate(new SimpleDateFormat("yyyy-MM-dd")
                                    .format(new Date()));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    beanMap.put(Sfgljl_tnb.class.getName(), sfgljl);

                    // 发送
                    saveTnbValueToWeb(residentUUID, residentId);
                }
            }
        });
    }

    /**
     * 随访管理糖尿病
     */
    private void saveTnbValueToWeb(String residentUUID, String residentId) {
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        Sfgljl_tnb mSfgljl_tnb = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());

        Sfgl sfgl = SfglBll.queryLast(Sfgljl_tnb.class.getName(), residentUUID);
        if (sfgl != null) {
            Sfgljl_tnb sfgljl_tnbQuery = (Sfgljl_tnb) XmlSerializerUtil.getInstance().beanFromXML(
                    Sfgljl_tnb.class, sfgl.getBean());
            if (sfgljl_tnbQuery != null) {
                if (sfgljl_tnbQuery.getVisitDate() != null) {
                    if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(
                            sfgljl_tnbQuery.getVisitDate())) {
                        // 不是同一天，那么默认使用编辑模式
                        mSfgljl_tnb.setVisitID(sfgljl_tnbQuery.getVisitID());
                        // mTnbPage01.mUserIdTextView.setText(sfgljl_tnbQuery.getVisitID());
                    }
                }
            }

            Bctnbsfjl23 bctnbsfjl23 = new Bctnbsfjl23();

            bctnbsfjl23.request = new Bctnbsfjl23.Request();
            if (mSfgljl_tnb != null) {
                bctnbsfjl23.request.type = 1;
            }
            bctnbsfjl23.request.userID = stringuserID;
            bctnbsfjl23.request.residentID = residentId;
            bctnbsfjl23.request.visitID = mSfgljl_tnb.getVisitID();
            bctnbsfjl23.request.visitDate = mSfgljl_tnb.getVisitDate();

            // 设置责任医生
            if (Global.doctorID != null && Global.doctorName != null) {
                mSfgljl_tnb.setDoctorID(Global.doctorID);
                mSfgljl_tnb.setDoctorName(Global.doctorName);
            }

            bctnbsfjl23.request.doctorID = mSfgljl_tnb.getDoctorID();
            bctnbsfjl23.request.doctorName = mSfgljl_tnb.getDoctorName();
            bctnbsfjl23.request.SFFSCD = mSfgljl_tnb.getsFFSCD();
            bctnbsfjl23.request.XCSF = mSfgljl_tnb.getxCSF();
            bctnbsfjl23.request.ZZCD = mSfgljl_tnb.getzZCD();
            bctnbsfjl23.request.SBP = mSfgljl_tnb.getsBP();
            bctnbsfjl23.request.DBP = mSfgljl_tnb.getdBP();
            bctnbsfjl23.request.HeartRate = mSfgljl_tnb.getbCXL();
            bctnbsfjl23.request.BCTZ = mSfgljl_tnb.getbCTZ();
            bctnbsfjl23.request.BCSG = mSfgljl_tnb.getbCSG();
            bctnbsfjl23.request.XCTZ = mSfgljl_tnb.getxCTZ();
            bctnbsfjl23.request.TZZS = mSfgljl_tnb.gettZZS();
            bctnbsfjl23.request.DMBDCD = mSfgljl_tnb.getdMBDCD();
            bctnbsfjl23.request.WaistNow = mSfgljl_tnb.getWaistNow();
            bctnbsfjl23.request.WaistTarget = mSfgljl_tnb.getWaistTarget();
            bctnbsfjl23.request.QTTZ = mSfgljl_tnb.getqTTZ();
            bctnbsfjl23.request.BCXYL = mSfgljl_tnb.getbCXYL();
            bctnbsfjl23.request.XCXY = mSfgljl_tnb.getxCXY();
            bctnbsfjl23.request.BCYJ = mSfgljl_tnb.getbCYJ();
            bctnbsfjl23.request.XCYJ = mSfgljl_tnb.getxCYJ();
            bctnbsfjl23.request.YDZC = mSfgljl_tnb.getyDZC();
            bctnbsfjl23.request.XLTZCD = mSfgljl_tnb.getxLTZCD();
            bctnbsfjl23.request.YDCF = mSfgljl_tnb.getyDCF();
            bctnbsfjl23.request.XCYDZC = mSfgljl_tnb.getxCYDZC();
            bctnbsfjl23.request.XCYDCD = mSfgljl_tnb.getxCYDCD();
            bctnbsfjl23.request.BCZSL = mSfgljl_tnb.getbCZSL();
            bctnbsfjl23.request.XCZSL = mSfgljl_tnb.getxCZSL();
            bctnbsfjl23.request.ZYXWCD = mSfgljl_tnb.getzYXWCD();
            bctnbsfjl23.request.KFXT = mSfgljl_tnb.getkFXT();
            bctnbsfjl23.request.XHDB = mSfgljl_tnb.getxHDB();
            bctnbsfjl23.request.QTJC = mSfgljl_tnb.getqTJC();
            bctnbsfjl23.request.FYYCXCD = mSfgljl_tnb.getfYYCXCD();
            bctnbsfjl23.request.BLFY = mSfgljl_tnb.getbLFY();
            bctnbsfjl23.request.DXTFYCD = mSfgljl_tnb.getdXTFYCD();
            bctnbsfjl23.request.SFFLCD = mSfgljl_tnb.getsFFLCD();
            bctnbsfjl23.request.ZZYY = mSfgljl_tnb.getzZYY();
            bctnbsfjl23.request.ZZKB = mSfgljl_tnb.getzZKB();
            bctnbsfjl23.request.BZ = mSfgljl_tnb.getBz();

            bctnbsfjl23.request.referralVisitDate = mSfgljl_tnb.getReferralVisitDate();
            if (mSfgljl_tnb.getComplication() != null) {
                bctnbsfjl23.request.complication = mSfgljl_tnb.getComplication();
            }
            if (mSfgljl_tnb.getComorbidity() != null) {
                bctnbsfjl23.request.comorbidity = mSfgljl_tnb.getComorbidity();
            }

            String fzjcString = "";
            if (mSfgljl_tnb.getfZJC() != null) {
                List<FZJC> fromBean = mSfgljl_tnb.getfZJC();
                for (FZJC fzjc : fromBean) {
                    fzjcString += (fzjc.getjCXM() + ",");
                    fzjcString += (fzjc.getjCJG() + ",");
                    fzjcString += (fzjc.getjCR() + ",");
                    fzjcString += (fzjc.getjCRQ() + "|");
                }
            }
            bctnbsfjl23.request.QTJC = fzjcString;

            if (mSfgljl_tnb.getMedicineUse() != null) {
                ArrayList<MedicineUse> medicineUses = new ArrayList<MedicineUse>();
                List<MedicineUse> from = mSfgljl_tnb.getMedicineUse();
                for (MedicineUse medicineUse : from) {
                    MedicineUse mu = new MedicineUse();
                    mu.dosage = medicineUse.dosage;
                    mu.medicine = medicineUse.medicine;
                    mu.medicineUnit = medicineUse.medicineUnit;
                    mu.usage = medicineUse.usage;
                    mu.way = medicineUse.way;
                    medicineUses.add(mu);
                }
                bctnbsfjl23.request.medicineUse = medicineUses;
            }
            /**
             * 发送网络请求
             */
            List<IDto> beanList = new ArrayList<IDto>();
            beanList.add(bctnbsfjl23);
            BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
                @Override
                public void onResult(List<IDto> listBean, boolean isSucc) {
                    if (listBean != null && listBean.size() > 0) {
                        Bctnbsfjl23 responseBean = (Bctnbsfjl23) listBean.get(0);
                        if (isSucc) {
                            if (responseBean.response == null) {
                                if (mOnSjglScglUploadLisener != null) {
                                    mOnSjglScglUploadLisener.onUploadFinished(false,
                                            "[糖尿病上传] - 网络异常");
                                }
                                // mToast.setText("网络异常");
                                // mToast.show();
                            } else {
                                if (responseBean.response.errMsg != null
                                        && responseBean.response.errMsg.length() > 0) {
                                    if (mOnSjglScglUploadLisener != null) {
                                        mOnSjglScglUploadLisener.onUploadFinished(false,
                                                "[糖尿病上传] - " + responseBean.response.errMsg);
                                    }
                                    // mToast.setText(responseBean.response.errMsg);
                                    // mToast.show();
                                    return;
                                } else {
                                    if (mOnSjglScglUploadLisener != null) {
                                        mOnSjglScglUploadLisener.onUploadFinished(true,
                                                "[糖尿病上传] - 数据上传成功");
                                    }
                                    // mTnbPage01.mUserIdTextView
                                    // .setText(responseBean.response.VisitID);
                                    // mToast.setText("数据上传成功");
                                    // mToast.show();
                                    // saveTnbToDb();
                                }
                            }
                        } else {
                            if (mOnSjglScglUploadLisener != null) {
                                mOnSjglScglUploadLisener.onUploadFinished(false, "[糖尿病上传] - 网络异常");
                            }
                            // mToast.setText("网络请求失败");
                            // mToast.show();
                        }
                    }
                }
            });
        }

    }

    public void getGxyFromDbAndSaveToWeb(Context mContext, final Map<String, IBean> beanMap,
            String residentUUID, String projectUUID, String residentId) {
        this.beanMap = beanMap;
        getGxySfxxFromDb(residentUUID, projectUUID, residentId);
    }

    // 获取最后一条高血压随访信息
    public void getGxySfxxFromDb(final String residentUUID, String projectUUID,
            final String residentId) {
        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            if (mOnSjglScglUploadLisener != null) {
                mOnSjglScglUploadLisener.onUploadFinished(false, "当前没有医生登录，请先登录！");
            }
            // // mToast.setText("当前没有医生登录，请先登录！");
            // // mToast.show();
            return;
        }
        // 在取高血压随访记录
        List<Class<? extends IBean>> sfListBean = new ArrayList<Class<? extends IBean>>();
        sfListBean.add(Sfgljl_gxy.class);
        BeanUtil.getInstance().getSfglFromDb(sfListBean, residentUUID, projectUUID,
                new BeanUtil.OnResultFromDb() {

                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        if (isSucc) {
                            Sfgljl_gxy sfgljl = (Sfgljl_gxy) listBean.get(0);
                            try {
                                Date visitDate = new SimpleDateFormat("yyyy-MM-dd").parse(sfgljl
                                        .getVisitDate());
                                // 因为每天只能新增一条数据，所以要判断是否是同一天
                                if (visitDate.getDay() == new Date().getDay()) {
                                    // 如果是同一天
                                    // //
                                    // mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                                    // // mToast.show();
                                    sfgljl.setVisitID(sfgljl.getVisitID());// 使用上一次的id，即为编辑模式
                                    sfgljl.setVisitDate(sfgljl.getVisitDate());
                                } else {
                                    sfgljl.setVisitID("");// ID为空，则为新增模式
                                    sfgljl.setVisitDate(new SimpleDateFormat("yyyy-MM-dd")
                                            .format(new Date()));
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            beanMap.put(Sfgljl_gxy.class.getName(), sfgljl);

                            // 发送
                            saveGxyValueToWeb(residentUUID, residentId);
                        }
                    }
                });
    }

    /**
     * 随访管理高血压
     */
    private void saveGxyValueToWeb(String residentUUID, String residentId) {
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        Sfgljl_gxy sfgljl_gxy = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
        Sfgl sfgl = SfglBll.queryLast(Sfgljl_gxy.class.getName(), residentUUID);
        if (sfgl != null) {
            Sfgljl_gxy sfgljl_gxyQuery = (Sfgljl_gxy) XmlSerializerUtil.getInstance().beanFromXML(
                    Sfgljl_gxy.class, sfgl.getBean());
            if (sfgljl_gxyQuery != null) {
                if (sfgljl_gxyQuery.getVisitDate() != null) {
                    if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(
                            sfgljl_gxyQuery.getVisitDate())) {
                        // 不是同一天，那么默认使用编辑模式
                        sfgljl_gxy.setVisitID(sfgljl_gxyQuery.getVisitID());
                        // mGxyPage01.mUserIDText.setText(sfgljl_gxyQuery.getVisitID());
                    }
                }
            }

            Bcgxysfjl16 bcgxysfjl16 = new Bcgxysfjl16();
            bcgxysfjl16.request = new Bcgxysfjl16.Request();
            bcgxysfjl16.request.userID = stringuserID;
            bcgxysfjl16.request.residentID = residentId;

            // 设置责任医生
            if (Global.doctorID != null && Global.doctorName != null) {
                sfgljl_gxy.setDoctorID(Global.doctorID);
                sfgljl_gxy.setDoctorName(Global.doctorName);
            }

            // 从数据库中读数据，拿到随访序列号，如果是在同一天，则使用同一个序列号，如果不是，那么使用""
            bcgxysfjl16.request.visitID = sfgljl_gxy.getVisitID();
            if (bcgxysfjl16.request.visitID.trim().equals("")) {
                bcgxysfjl16.request.type = 1;// 新增存盘
            } else {
                bcgxysfjl16.request.type = 2;// 编辑存盘
            }
            bcgxysfjl16.request.visitDate = sfgljl_gxy.getVisitDate();
            bcgxysfjl16.request.doctorID = sfgljl_gxy.getDoctorID();
            bcgxysfjl16.request.doctorName = sfgljl_gxy.getDoctorName();
            bcgxysfjl16.request.sffscd = sfgljl_gxy.getsFFSCD();

            // bcgxysfjl16.request.sffscd = sfgljl_gxy.getSddsCD();
            bcgxysfjl16.request.xcsf = sfgljl_gxy.getxCSF();
            bcgxysfjl16.request.ZZCD = sfgljl_gxy.getZZCD();
            bcgxysfjl16.request.ZZQT = sfgljl_gxy.getZZQT();
            bcgxysfjl16.request.SBP = sfgljl_gxy.getsBP();
            bcgxysfjl16.request.DBP = sfgljl_gxy.getdBP();
            bcgxysfjl16.request.BCTZ = sfgljl_gxy.getbCTZ();

            bcgxysfjl16.request.BCSG = sfgljl_gxy.getBCSG();
            bcgxysfjl16.request.TZZS = sfgljl_gxy.gettZZS();
            bcgxysfjl16.request.BCXL = sfgljl_gxy.getbCXL();
            bcgxysfjl16.request.QTTZ = sfgljl_gxy.getqTTZ();
            bcgxysfjl16.request.XCTZ = sfgljl_gxy.getxCTZ();
            bcgxysfjl16.request.XCXL = sfgljl_gxy.getxCXL();
            bcgxysfjl16.request.BCXYL = sfgljl_gxy.getbCXYL();
            bcgxysfjl16.request.XCXY = sfgljl_gxy.getxCXY();
            bcgxysfjl16.request.BCYJ = sfgljl_gxy.getbCYJ();
            bcgxysfjl16.request.XCYJ = sfgljl_gxy.getxCYJ();
            bcgxysfjl16.request.YDZC = sfgljl_gxy.getyDZC();
            bcgxysfjl16.request.YDCF = sfgljl_gxy.getyDCF();

            bcgxysfjl16.request.XCYDZC = sfgljl_gxy.getxCYDZC();
            bcgxysfjl16.request.XCYDCD = sfgljl_gxy.getxCYDCD();
            bcgxysfjl16.request.BCSYL = sfgljl_gxy.getbCSYL();
            bcgxysfjl16.request.XCSYL = sfgljl_gxy.getxCSYL();
            bcgxysfjl16.request.XLTZCD = sfgljl_gxy.getxLTZCD();
            bcgxysfjl16.request.ZYXWCD = sfgljl_gxy.getzYXWCD();

            bcgxysfjl16.request.referralVisitDate = sfgljl_gxy.getReferralVisitDate();
            if (sfgljl_gxy.getCriticalOrgan() != null) {
                bcgxysfjl16.request.criticalOrgan = sfgljl_gxy.getCriticalOrgan();
            }
            if (sfgljl_gxy.getComorbidity() != null) {
                bcgxysfjl16.request.comorbidity = sfgljl_gxy.getComorbidity();
            }
            /**
             * 辅助检查部分
             */
            // if (sfgljl_gxy.getfZJC() != null) {
            // ArrayList<Bcgxysfjl16.Request.FZJC> fzjclLists = new
            // ArrayList<Bcgxysfjl16.Request.FZJC>();
            // List<FZJC> fromBean = sfgljl_gxy.getfZJC();
            // for (FZJC fzjc : fromBean) {
            // Bcgxysfjl16.Request.FZJC f = new Bcgxysfjl16.Request.FZJC();
            // f.jcxm = fzjc.getjCXM();
            // f.jcjg = fzjc.getjCJG();
            // f.jcr = fzjc.getjCR();
            // f.jcrq = fzjc.getjCRQ();
            // fzjclLists.add(f);
            // }
            // }
            String fzjcString = "";
            if (sfgljl_gxy.getfZJC() != null) {
                List<FZJC> fromBean = sfgljl_gxy.getfZJC();
                for (FZJC fzjc : fromBean) {
                    fzjcString += (fzjc.getjCXM() + ",");
                    fzjcString += (fzjc.getjCJG() + ",");
                    fzjcString += (fzjc.getjCR() + ",");
                    fzjcString += (fzjc.getjCRQ() + "|");
                }
            }
            bcgxysfjl16.request.FZJC = fzjcString;

            bcgxysfjl16.request.FYYCXCD = sfgljl_gxy.getfYYCXCD();
            bcgxysfjl16.request.BLFY = sfgljl_gxy.getbLFY();
            bcgxysfjl16.request.FYQK = sfgljl_gxy.getfYQK();
            bcgxysfjl16.request.SFFLCD = sfgljl_gxy.getsFFLCD();
            bcgxysfjl16.request.ZZYY = sfgljl_gxy.getzZYY();
            bcgxysfjl16.request.ZZKB = sfgljl_gxy.getzZKB();
            bcgxysfjl16.request.BZ = sfgljl_gxy.getBz();

            if (sfgljl_gxy.getMedicineUse() != null) {
                bcgxysfjl16.request.medicineUse = (ArrayList<MedicineUse>) sfgljl_gxy
                        .getMedicineUse();
            }
            /**
             * 发送网络请求
             */
            List<IDto> beanList = new ArrayList<IDto>();
            beanList.add(bcgxysfjl16);
            BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
                @Override
                public void onResult(List<IDto> listBean, boolean isSucc) {
                    if (listBean != null && listBean.size() > 0) {
                        Bcgxysfjl16 responseBean = (Bcgxysfjl16) listBean.get(0);
                        if (isSucc) {
                            if (responseBean != null && responseBean.response == null) {
                                if (mOnSjglScglUploadLisener != null) {
                                    mOnSjglScglUploadLisener.onUploadFinished(false,
                                            "[高血压上传] - 网络异常");
                                }
                                // mToast.setText("网络异常");
                                // mToast.show();
                            } else {
                                if (responseBean.response.errMsg != null
                                        && responseBean.response.errMsg.length() > 0) {
                                    if (mOnSjglScglUploadLisener != null) {
                                        mOnSjglScglUploadLisener.onUploadFinished(false,
                                                "[高血压上传] - " + responseBean.response.errMsg);
                                    }
                                    // mToast.setText(responseBean.response.errMsg);
                                    // mToast.show();
                                    return;
                                } else {
                                    // mGxyPage01.mUserIDText.setText(responseBean.response.visitID
                                    // + "");
                                    // mToast.setText("数据上传成功");
                                    // mToast.show();
                                    // 数据上传成功，得到随访序列号，保存到数据库中
                                    if (mOnSjglScglUploadLisener != null) {
                                        mOnSjglScglUploadLisener.onUploadFinished(true,
                                                "[高血压上传] - " + "数据上传成功");
                                    }
                                }
                            }
                        } else {
                            if (mOnSjglScglUploadLisener != null) {
                                mOnSjglScglUploadLisener.onUploadFinished(false, "网络请求失败");
                            }
                            // mToast.setText("网络请求失败");
                            // mToast.show();
                        }
                    }
                }
            });
        }

    }

    public void getJbxxFromDbAndSaveToWeb(Context mContext, Map<String, IBean> beanMap,
            String paperNum) {
        this.beanMap = beanMap;
        getJbxxFromDB(paperNum);
    }

    private boolean getJbxxFromDB(String paperNum) {
        // 2.写session
        loadResidentInfoIntoSession(paperNum);

        List<Class<? extends IBean>> beanList = new ArrayList<Class<? extends IBean>>();

        beanList.add(Jmjbxx.class);
        beanList.add(Jmjtxx.class);
        beanList.add(Jmjkxx.class);
        beanList.add(Jmxwxg.class);

        BeanUtil.getInstance().getJbxxFromDb(beanList, new OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {// 如果成功

                    Jmjbxx jmjbxx = (Jmjbxx) listBean.get(0);
                    if (listBean.get(0) == null) {
                        Global.jmjbxx = new Jmjbxx();
                        jmjbxx = Global.jmjbxx;
                    }
                    Jmjtxx jmjtxx = (Jmjtxx) listBean.get(1);
                    if (listBean.get(1) == null) {
                        Global.jmjtxx = new Jmjtxx();
                        jmjtxx = Global.jmjtxx;
                    }
                    Jmjkxx jmjkxx = (Jmjkxx) listBean.get(2);
                    if (listBean.get(2) == null) {
                        Global.jmjkxx = new Jmjkxx();
                        jmjkxx = Global.jmjkxx;
                    }
                    Jmxwxg jmxwxg = (Jmxwxg) listBean.get(3);
                    if (listBean.get(3) == null) {
                        Global.jmxwxg = new Jmxwxg();
                        jmxwxg = Global.jmxwxg;
                    }
                    beanMap.put(Jmjbxx.class.getName(), jmjbxx);
                    beanMap.put(Jmjtxx.class.getName(), jmjtxx);
                    beanMap.put(Jmjkxx.class.getName(), jmjkxx);
                    beanMap.put(Jmxwxg.class.getName(), jmxwxg);

                    // 上传
                    saveValueToWeb();
                } else {
                    if (mOnSjglScglUploadLisener != null) {
                        mOnSjglScglUploadLisener.onUploadFinished(false, "读取数据库失败");
                    }
                }
            }
        });
        return true;
    }

    private void saveValueToWeb() {
        // 先判断这个人是不是第一建档
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx.getResidentID().trim() == null || mJmjbxx.getResidentID().trim().equals("")) {// 说明是第一次建档
            firstAddToWeb();// 新增
        } else {
            editToWeb();// 编辑
        }
    }

    public void firstAddToWeb() {
        uploadToWeb(1);
    }

    public void uploadToWeb(int operType) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());

        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;

        BcjmjbxxhjtxxJ003 bcjmjbxxhjtxxJ003 = new BcjmjbxxhjtxxJ003();
        bcjmjbxxhjtxxJ003.request = new BcjmjbxxhjtxxJ003.Request();

        bcjmjbxxhjtxxJ003.request.type = operType;// 新增存盘
        bcjmjbxxhjtxxJ003.request.userID = stringuserID;
        bcjmjbxxhjtxxJ003.request.familyID = mJmjtxx.getFamilyID();// 新增的时候是空的
        bcjmjbxxhjtxxJ003.request.residentID = mJmjbxx.getResidentID();// 新增的时候是空的
        bcjmjbxxhjtxxJ003.request.residentName = mJmjbxx.getResidentName();
        bcjmjbxxhjtxxJ003.request.sexCD = mJmjbxx.getSexCD();
        bcjmjbxxhjtxxJ003.request.birthDay = mJmjbxx.getBirthDay();
        bcjmjbxxhjtxxJ003.request.paperNum = mJmjbxx.getPaperNum();
        bcjmjbxxhjtxxJ003.request.cardID = mJmjbxx.getCardID();
        bcjmjbxxhjtxxJ003.request.addressType = mJmjbxx.getAddressTypeCD();

        if (mJmjbxx.getNowProvince() != null) {
            bcjmjbxxhjtxxJ003.request.nowProvince = mJmjbxx.getNowProvince();
        }
        if (mJmjbxx.getNowCity() != null) {
            bcjmjbxxhjtxxJ003.request.nowCity = mJmjbxx.getNowCity();
        }
        if (mJmjbxx.getNowDistrict() != null) {
            bcjmjbxxhjtxxJ003.request.nowDistrict = mJmjbxx.getNowDistrict();
        }
        if (mJmjbxx.getNowStreet() != null) {
            bcjmjbxxhjtxxJ003.request.nowStreet = mJmjbxx.getNowStreet();
        }
        if (mJmjbxx.getNowZone() != null) {
            bcjmjbxxhjtxxJ003.request.nowZone = mJmjbxx.getNowZone();
        }

        if (mJmjbxx.getNowRoad() != null) {
            bcjmjbxxhjtxxJ003.request.nowRoadCD = mJmjbxx.getNowRoad().getiD();
            bcjmjbxxhjtxxJ003.request.nowRoad = mJmjbxx.getNowRoad().getTagValue();
        }

        bcjmjbxxhjtxxJ003.request.nowN = mJmjbxx.getNowN();
        bcjmjbxxhjtxxJ003.request.nowH = mJmjbxx.getNowH();
        bcjmjbxxhjtxxJ003.request.nowS = mJmjbxx.getNowS();

        if (mJmjbxx.getNowProvince() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowCity() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowDistrict() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowStreet() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowZone() != null) {
            // TODO:户籍地址
        }
        bcjmjbxxhjtxxJ003.request.regDetail = mJmjbxx.getRegDetail();
        bcjmjbxxhjtxxJ003.request.resideStatusCD = mJmjbxx.getResideStatusCD();
        bcjmjbxxhjtxxJ003.request.regTypeCD = mJmjbxx.getRegTypeCD();
        bcjmjbxxhjtxxJ003.request.regAddress = mJmjbxx.getRegAddress();// 户籍地址是不是身份证上面的地址
        bcjmjbxxhjtxxJ003.request.workUnit = mJmjbxx.getWorkUnit();
        bcjmjbxxhjtxxJ003.request.selfPhone = mJmjbxx.getSelfPhone();
        bcjmjbxxhjtxxJ003.request.relaName = mJmjbxx.getRelaName();
        bcjmjbxxhjtxxJ003.request.relaPhone = mJmjbxx.getRelaPhone();
        bcjmjbxxhjtxxJ003.request.resideCD = mJmjbxx.getResideCD();
        if (mJmjbxx.getFlokCD() != null) {
            bcjmjbxxhjtxxJ003.request.folkCD = mJmjbxx.getFlokCD();
        }
        bcjmjbxxhjtxxJ003.request.bloodCD = mJmjbxx.getBloodCD();
        bcjmjbxxhjtxxJ003.request.educationCD = mJmjbxx.getEducationCD();
        if (mJmjbxx.getVocationCD() != null) {
            bcjmjbxxhjtxxJ003.request.vocationCD = mJmjbxx.getVocationCD();
        }
        bcjmjbxxhjtxxJ003.request.marriageCD = mJmjbxx.getMarriageCD();
        bcjmjbxxhjtxxJ003.request.insuranceCD = mJmjbxx.getInsuranceCD();
        bcjmjbxxhjtxxJ003.request.insuranceNum = mJmjbxx.getInsuranceNum();
        bcjmjbxxhjtxxJ003.request.aidCD = mJmjbxx.getAidCD();
        if (mJmjbxx.getNationalityCD() != null) {
            bcjmjbxxhjtxxJ003.request.nationalityCD = mJmjbxx.getNationalityCD();
        }
        if (mJmjbxx.getRelation() != null) {
            bcjmjbxxhjtxxJ003.request.relation = mJmjbxx.getRelation();
        }
        bcjmjbxxhjtxxJ003.request.zip = mJmjbxx.getZip();
        bcjmjbxxhjtxxJ003.request.email = mJmjbxx.getEmail();
        bcjmjbxxhjtxxJ003.request.manuaINm = mJmjbxx.getManuaINm();

        // 责任医生的设置
        BeanID doctor = new BeanID();
        if (mJmjbxx.getDutyDoctor() != null) {
            doctor.setiD(mJmjbxx.getDutyDoctor().getiD());
            doctor.setTagValue(mJmjbxx.getDutyDoctor().getTagValue());
        } else {
            Login1.Response userLogin1Response = MyApplication.getInstance().getSession()
                    .getLoginResult().response;
            if (userLogin1Response != null) {
                doctor.setiD(userLogin1Response.doctorID);
                doctor.setTagValue(userLogin1Response.doctorName);
            }
        }
        bcjmjbxxhjtxxJ003.request.dutyDoctor = doctor;

        // 管理机构的设置
        BeanID manageOrg = null;
        if (mJmjbxx.getManageOrg() != null && !StringUtil.isEmptyString(mJmjbxx.getManageOrg().getiD())) {
            manageOrg = mJmjbxx.getManageOrg();
        } else {
            BeanID session_manageOrg = MyApplication.getInstance().getSession().getManageOrg();
            if (session_manageOrg != null) {
                manageOrg = session_manageOrg;
            } else {// 默认的设置
                manageOrg = new BeanID(Global.orgCode, Global.orgName);
            }
        }
        bcjmjbxxhjtxxJ003.request.manageOrg = manageOrg;

        // 设置服务站点
        BeanID station = null;
        if (mJmjbxx.getStation() != null && !StringUtil.isEmptyString(mJmjbxx.getStation().getiD())) {
            station = mJmjbxx.getStation();
        } else {
            BeanID session_station = MyApplication.getInstance().getSession().getStation();
            if (session_station != null && !StringUtil.isEmptyString(session_station.getiD())) {
                station = session_station;
            } else {// 默认设置
                station = mJmjbxx.getManageOrg();
            }
        }
        bcjmjbxxhjtxxJ003.request.station = station;

        // 建档日期
        if (mJmjbxx.getBuildDate() != null && !mJmjbxx.getBuildDate().equals("")) {
            bcjmjbxxhjtxxJ003.request.buildDate = mJmjbxx.getBuildDate();
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            bcjmjbxxhjtxxJ003.request.buildDate = format.format(new Date());
        }

        // 建档人员
        BeanID builder = new BeanID();
        if (mJmjbxx.getBuilder() != null) {
            builder = mJmjbxx.getBuilder();
        } else {
            Login1.Response userLogin1Response = MyApplication.getInstance().getSession()
                    .getLoginResult().response;
            if (userLogin1Response != null) {
                // 用当前医生ID和名字 作为建档人员
                builder = new BeanID(userLogin1Response.doctorID, userLogin1Response.doctorName);
            }
        }
        bcjmjbxxhjtxxJ003.request.builder = builder;

        // 建档机构
        BeanID buildOrg = new BeanID();
        if (mJmjbxx.getBuildOrg() != null) {
            buildOrg = mJmjbxx.getBuildOrg();
        } else {
            BeanID session_buildOrg = MyApplication.getInstance().getSession().getBuildOrg();
            if (session_buildOrg != null) {
                buildOrg = session_buildOrg;
            } else {// 默认的设置
                buildOrg = new BeanID(Global.orgCode, "江厦街道社区卫生服务中心");
            }
        }
        bcjmjbxxhjtxxJ003.request.buildOrg = buildOrg;

        if (bcjmjbxxhjtxxJ003.request.type == 1) {// 如果是新建档案的情况,传体检的数据
            Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
            if (mJktj_kstj != null) {
                bcjmjbxxhjtxxJ003.request.height = mJktj_kstj.getHeight();
                bcjmjbxxhjtxxJ003.request.weight = mJktj_kstj.getWeight();
                bcjmjbxxhjtxxJ003.request.bMI = mJktj_kstj.getbMI();
                bcjmjbxxhjtxxJ003.request.bust = mJktj_kstj.getBust();
                bcjmjbxxhjtxxJ003.request.hIP = mJktj_kstj.gethIP();
                bcjmjbxxhjtxxJ003.request.waist = mJktj_kstj.getWaist();
            }
        }

        bcjmjbxxhjtxxJ003.request.fileStatusCD = mJmjbxx.getFileStatusCD();

        /**
         * 家庭信息部分
         */
        bcjmjbxxhjtxxJ003.request.familyTypeCD = mJmjtxx.getFamilyTypeCD();
        bcjmjbxxhjtxxJ003.request.incomeCD = mJmjtxx.getIncomeCD();
        bcjmjbxxhjtxxJ003.request.houseHoldCD = mJmjtxx.getHouseHoldCD();
        bcjmjbxxhjtxxJ003.request.population = mJmjtxx.getPopulation() + "";
        bcjmjbxxhjtxxJ003.request.economics = mJmjtxx.getEconomics();
        bcjmjbxxhjtxxJ003.request.housingLighting = mJmjtxx.getHousingLighting();
        bcjmjbxxhjtxxJ003.request.housingRooms = mJmjtxx.getHousingRooms() + "";
        bcjmjbxxhjtxxJ003.request.housingVentilation = mJmjtxx.getHousingVentilation();
        bcjmjbxxhjtxxJ003.request.housingWarm = mJmjtxx.getHousingWarm();
        bcjmjbxxhjtxxJ003.request.airHumidity = mJmjtxx.getAirHumidity();
        bcjmjbxxhjtxxJ003.request.healthStatus = mJmjtxx.getHealthStatus();
        bcjmjbxxhjtxxJ003.request.waterStatus = mJmjtxx.getWaterStatus();
        bcjmjbxxhjtxxJ003.request.sewageTreatment = mJmjtxx.getSewageTreatment();
        bcjmjbxxhjtxxJ003.request.stylisticDevices = mJmjtxx.getStylisticDevices();
        bcjmjbxxhjtxxJ003.request.smokeRemoval = mJmjtxx.getSmokeRemoval();
        bcjmjbxxhjtxxJ003.request.familyMember = mJmjtxx.getFamilyMember();
        bcjmjbxxhjtxxJ003.request.familyMainProblems = mJmjtxx.getFamilyMainProblems();
        bcjmjbxxhjtxxJ003.request.area = mJmjtxx.getArea();
        bcjmjbxxhjtxxJ003.request.avgArea = mJmjtxx.getAvgArea();
        bcjmjbxxhjtxxJ003.request.floorTypeCD = mJmjtxx.getFloorTypeCD();
        bcjmjbxxhjtxxJ003.request.kitchenUseCD = mJmjtxx.getKitchenUseCD();
        bcjmjbxxhjtxxJ003.request.kitchenFanCD = mJmjtxx.getKitchenFanCD();
        bcjmjbxxhjtxxJ003.request.waterCD = mJmjtxx.getWaterCD();
        bcjmjbxxhjtxxJ003.request.fuelCD = mJmjtxx.getFuelCD();
        bcjmjbxxhjtxxJ003.request.sanToiletCD = mJmjtxx.getSanToiletCD();
        bcjmjbxxhjtxxJ003.request.notSanToiletCD = mJmjtxx.getNotSanToiletCD();
        bcjmjbxxhjtxxJ003.request.animalPlaceCD = mJmjtxx.getAnimalPlaceCD();
        bcjmjbxxhjtxxJ003.request.garbageDealCD = mJmjtxx.getGarbageDealCD();
        bcjmjbxxhjtxxJ003.request.applianceCD = mJmjtxx.getApplianceCD();
        bcjmjbxxhjtxxJ003.request.transport = mJmjtxx.getTransport();

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为
        beanList.add(bcjmjbxxhjtxxJ003); // 添加保存居民基本信息idto
        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    BcjmjbxxhjtxxJ003 responseBcjmjbxxhjtxxJ003 = (BcjmjbxxhjtxxJ003) listBean
                            .get(0);
                    if (responseBcjmjbxxhjtxxJ003 == null
                            || responseBcjmjbxxhjtxxJ003.response == null) {
                        sb.append("【居民基本信息和家庭信息】服务器接口异常");
                        if (mOnSjglScglUploadLisener != null) {
                            mOnSjglScglUploadLisener.onUploadFinished(false, sb.toString());
                        }
                    } else {
                        if (responseBcjmjbxxhjtxxJ003.response.errMsg.length() > 0) {
                            sb.append("【居民基本信息和家庭信息】" + responseBcjmjbxxhjtxxJ003.response.errMsg);
                            if (mOnSjglScglUploadLisener != null) {
                                mOnSjglScglUploadLisener.onUploadFinished(false, sb.toString());
                            }
                        } else {
                            // 保存成功的话，更新居民基本信息的id和famliyID
                            saveResidentID_FamilyID(responseBcjmjbxxhjtxxJ003.response.residentID,
                                    responseBcjmjbxxhjtxxJ003.response.familyID);
                            sb.append("【居民基本信息】修改成功");
                            if (responseBcjmjbxxhjtxxJ003.response.returnType == 1) {
                                sb.append("\n【居民家庭信息】修改成功");
                            } else if (responseBcjmjbxxhjtxxJ003.response.returnType == 2) {
                                sb.append("\n【居民家庭信息】有重复家庭");
                            }
                            saveProfileToWeb(responseBcjmjbxxhjtxxJ003.response.residentID);// 保存身份证头像到Web
                            saveJmjkxx_JmxwxgToWeb(false, sb);// 在取保存健康信息和行为习惯
                        }
                    }
                    // // // mToast.setDuration(Toast.LENGTH_LONG);
                    // // // mToast.setText(sb.toString());
                    // // // mToast.show();

                } else {
                    if (mOnSjglScglUploadLisener != null) {
                        mOnSjglScglUploadLisener.onUploadFinished(false, "网络请求异常");
                    }
                    // // // mToast.setDuration(Toast.LENGTH_SHORT);
                    // // // mToast.setText("网络请求异常,数据暂存本地");
                    // // // mToast.show();
                    // saveValueToDb();
                }

            }
        });

    }

    public void editToWeb() {
        uploadToWeb(2);
    }

    public void saveProfileToWeb(String residentID) {
        Jmjbxx mjJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        // userId
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        // 获取图片的字符串数据
        String photoString = FileHelper.getInstance().getBitmapString(mjJmjbxx.getPaperNum());
        if (photoString == null || photoString.equals(""))
            return;
        BcsfzzpJ002 bcsfzzpJ002 = new BcsfzzpJ002();
        bcsfzzpJ002.request = new BcsfzzpJ002.Request();
        bcsfzzpJ002.request.residentID = residentID;
        bcsfzzpJ002.request.userId = stringuserID;
        bcsfzzpJ002.request.photo = photoString;

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为
        beanList.add(bcsfzzpJ002); // 添加保存居民基本信息idto
        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    BcsfzzpJ002 responseBcsfzzpJ002 = (BcsfzzpJ002) listBean.get(0);
                    if (responseBcsfzzpJ002 == null || responseBcsfzzpJ002.response == null) {
                        sb.append("【保存身份证照片】服务器接口异常");
                    } else {
                        if (responseBcsfzzpJ002.response.errMsg.length() > 0) {
                            sb.append("【保存身份证照片】" + responseBcsfzzpJ002.response.errMsg);
                        } else {
                            sb.append("【保存身份证照片】新增成功");
                        }
                    }
                }
            }
        });
    }

    /**
     * 
     * @param saveJmjtxx
     *            true表示是从编辑状态到这一步 false表示从新增状态到这一步
     */
    public void saveJmjkxx_JmxwxgToWeb(final boolean saveJmjtxx, final StringBuilder sb) {
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());

        // 保存居民健康信息8.2
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        JmjkxxbcJ007 jmjkxxbcJ007 = new JmjkxxbcJ007();
        jmjkxxbcJ007.request = new JmjkxxbcJ007.Request();
        jmjkxxbcJ007.request.userID = stringuserID;
        jmjkxxbcJ007.request.familyID = mJmjtxx.getFamilyID();
        jmjkxxbcJ007.request.residentID = mJmjbxx.getResidentID();
        jmjkxxbcJ007.request.fatherCD = mJmjkxx.getFatherCD();
        jmjkxxbcJ007.request.fatherName = mJmjkxx.getFatherName();
        jmjkxxbcJ007.request.motherCD = mJmjkxx.getMotherCD();
        jmjkxxbcJ007.request.motherName = mJmjkxx.getMotherName();
        jmjkxxbcJ007.request.brotherCD = mJmjkxx.getBrotherCD();
        jmjkxxbcJ007.request.brotherName = mJmjkxx.getBrotherName();
        jmjkxxbcJ007.request.childCD = mJmjkxx.getChildCD();
        jmjkxxbcJ007.request.childName = mJmjkxx.getChildName();
        jmjkxxbcJ007.request.otherMemberCD = mJmjkxx.getOtherMemberCD();
        jmjkxxbcJ007.request.otherMemberCD = "1";// 默认没有
        jmjkxxbcJ007.request.deformityCD = mJmjkxx.getDeformityCD();
        jmjkxxbcJ007.request.deformityCardNo = mJmjkxx.getDeformityCardNo();
        jmjkxxbcJ007.request.deformityLevel = mJmjkxx.getDeformityLevel();
        jmjkxxbcJ007.request.deformityName = mJmjkxx.getDeformityName();
        jmjkxxbcJ007.request.heredityCD = mJmjkxx.getHeredityCD();
        jmjkxxbcJ007.request.heredityName = mJmjkxx.getHeredityName();
        jmjkxxbcJ007.request.exposureCD = mJmjkxx.getExposureCD();
        jmjkxxbcJ007.request.exposureName = mJmjkxx.getExposureName();

        if (mJmjkxx.getHistoryDisease() != null) {
            ArrayList<HistoryDisease> historyDiseases = new ArrayList<HistoryDisease>();

            List<HistoryDisease> from = mJmjkxx.getHistoryDisease();
            for (int i = 0; i < from.size(); i++) {
                HistoryDisease hd = from.get(i);
                HistoryDisease h = new HistoryDisease();
                h.hDType = hd.gethDType();
                h.disSn = hd.getDisSn();
                h.iCD10 = hd.getiCD10();
                h.disease = hd.getDisease();
                h.diagnoseDate = hd.getDiagnoseDate();
                h.happenDate = hd.getHappenDate();
                h.resultCD = hd.getResultCD();
                h.cureDes = hd.gethDReason();
                h.cureHos = hd.getCureHos();

                historyDiseases.add(h);
            }
            jmjkxxbcJ007.request.historyDisease = historyDiseases;
        }

        if (mJmjkxx.getHistoryHyper() != null) {
            ArrayList<HistoryHyper> historyHypers = new ArrayList<HistoryHyper>();
            List<HistoryHyper> from = mJmjkxx.getHistoryHyper();
            for (HistoryHyper historyHyper : from) {
                HistoryHyper hh = new HistoryHyper();
                hh.hyperTypeCD = historyHyper.getHyperTypeCD();
                hh.hyperSn = historyHyper.getDisSn();
                hh.hyperSource = historyHyper.getHyperSource();
                hh.happenDate = historyHyper.getHappenDate();
                hh.hyperReason = historyHyper.getHyperReason();
                hh.cureDes = historyHyper.getCureDes();
                historyHypers.add(hh);
            }
            jmjkxxbcJ007.request.historyHyper = historyHypers;
        }

        /**
         * 居民行为习惯
         */
        Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
        Bcjmxwxg8_1 bcjmxwxg8_1 = new Bcjmxwxg8_1();
        bcjmxwxg8_1.request = new Bcjmxwxg8_1.Request();
        bcjmxwxg8_1.request.userID = stringuserID;
        bcjmxwxg8_1.request.familyID = mJmjtxx.getFamilyID();
        bcjmxwxg8_1.request.residentID = mJmjbxx.getResidentID();
        bcjmxwxg8_1.request.smokeCD = mJmxwxg.getSmokeCD();
        bcjmxwxg8_1.request.smokeAge = mJmxwxg.getSmokeAge();
        bcjmxwxg8_1.request.noSmokeAge = mJmxwxg.getNoSmokeAge();
        bcjmxwxg8_1.request.smokeDay = mJmxwxg.getSmokeDay();
        bcjmxwxg8_1.request.smokeDayPast = mJmxwxg.getSmokeDayPast();
        bcjmxwxg8_1.request.drinkTypeCD = mJmxwxg.getDrinkTypeCD();
        bcjmxwxg8_1.request.drinkAmount = mJmxwxg.getDrinkAmount();
        bcjmxwxg8_1.request.noDrinkCD = mJmxwxg.getNoDrinkCD();
        bcjmxwxg8_1.request.noDrinkAge = mJmxwxg.getNoDrinkAge();
        bcjmxwxg8_1.request.pastDrinkNum = mJmxwxg.getPastDrinkNum();
        bcjmxwxg8_1.request.pastDrinkAmount = mJmxwxg.getPastDrinkAmount();
        bcjmxwxg8_1.request.pastDrinkTypeCD = mJmxwxg.getPastDrinkTypeCD();
        bcjmxwxg8_1.request.foodCD = mJmxwxg.getFoodCD();
        bcjmxwxg8_1.request.brushTeethCD = mJmxwxg.getBrushTeethCD();
        bcjmxwxg8_1.request.sportRateCD = mJmxwxg.getSportRateCD();
        bcjmxwxg8_1.request.sportTypeCD = mJmxwxg.getSportTypeCD();
        bcjmxwxg8_1.request.sportTypeElse = mJmxwxg.getSportTypeElse();
        bcjmxwxg8_1.request.sportTime = mJmxwxg.getSportTime();
        bcjmxwxg8_1.request.primaryEventCD = mJmxwxg.getPrimaryEvent();
        bcjmxwxg8_1.request.primaryEventName = mJmxwxg.getPrimaryEventName();

        /**
         * 保存居民家庭信息 居民家庭信息可能没有，要判断到底保不保存
         */
        Bcjtxxxx6 bcjtxxxx6 = new Bcjtxxxx6();
        bcjtxxxx6.request = new Bcjtxxxx6.Request();
        bcjtxxxx6.request.userID = stringuserID;
        bcjtxxxx6.request.familyID = mJmjtxx.getFamilyID();
        if (mJmjbxx.getNowStreet() != null) {
            bcjtxxxx6.request.street = mJmjbxx.getNowStreet();
        }
        if (mJmjbxx.getNowZone() != null) {
            bcjtxxxx6.request.zone = mJmjbxx.getNowZone();
        }
        if (mJmjbxx.getNowRoad() != null) {
            bcjtxxxx6.request.road = mJmjbxx.getNowRoad();
        }
        bcjtxxxx6.request.n = mJmjbxx.getNowN();
        bcjtxxxx6.request.h = mJmjbxx.getNowH();
        bcjtxxxx6.request.s = mJmjbxx.getNowS();
        bcjtxxxx6.request.familyTypeCD = mJmjtxx.getFamilyTypeCD();
        bcjtxxxx6.request.incomeCD = mJmjtxx.getIncomeCD();
        bcjtxxxx6.request.houseHoldCD = mJmjtxx.getHouseHoldCD();
        bcjtxxxx6.request.area = mJmjtxx.getArea();
        bcjtxxxx6.request.avgArea = mJmjtxx.getAvgArea();
        bcjtxxxx6.request.floorTypeCD = mJmjtxx.getFloorTypeCD();
        bcjtxxxx6.request.kitchenUseCD = mJmjtxx.getKitchenUseCD();
        bcjtxxxx6.request.kitchenFanCD = mJmjtxx.getKitchenFanCD();
        bcjtxxxx6.request.waterCD = mJmjtxx.getWaterCD();
        bcjtxxxx6.request.fuelCD = mJmjtxx.getFuelCD();
        bcjtxxxx6.request.sanToiletCD = mJmjtxx.getSanToiletCD();
        bcjtxxxx6.request.notSanToiletCD = mJmjtxx.getNotSanToiletCD();
        bcjtxxxx6.request.animalPlaceCD = mJmjtxx.getAnimalPlaceCD();
        bcjtxxxx6.request.garbageDealCD = mJmjtxx.getGarbageDealCD();
        bcjtxxxx6.request.applianceCD = mJmjtxx.getApplianceCD();
        bcjtxxxx6.request.transport = mJmjtxx.getTransport();

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为

        beanList.add(bcjmxwxg8_1); // 保存居民行为习惯8.1idto
        beanList.add(jmjkxxbcJ007); // 保存居民健康信息8.2idto
        if (mJmjtxx.getFamilyID() != null && !mJmjtxx.getFamilyID().equals("") && saveJmjtxx) {
            beanList.add(bcjtxxxx6); // 添加保存居民家庭信息信息idto
            bcjtxxxx6.request.type = 2;// 目前只能是2 编辑
        }

        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    Bcjmxwxg8_1 responseBcjmjkxx8_1 = (Bcjmxwxg8_1) listBean.get(0);
                    if (responseBcjmjkxx8_1 == null || responseBcjmjkxx8_1.response == null) {
                        sb.append("【居民行为习惯】服务器接口异常");
                    } else {
                        if (responseBcjmjkxx8_1.response.errMsg.length() > 0) {
                            sb.append("【居民行为习惯】" + responseBcjmjkxx8_1.response.errMsg);
                        } else {
                            sb.append("【居民行为习惯】上传成功");
                        }
                    }

                    JmjkxxbcJ007 responseJmjkxxbcJ007 = (JmjkxxbcJ007) listBean.get(1);
                    if (responseJmjkxxbcJ007 == null || responseJmjkxxbcJ007.response == null) {
                        sb.append("\n");
                        sb.append("【居民健康信息】服务器接口异常");
                    } else {
                        if (responseJmjkxxbcJ007.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【居民健康信息】" + responseJmjkxxbcJ007.response.errMsg);
                        } else {
                            sb.append("\n");
                            sb.append("【居民健康信息】上传成功");
                        }
                    }

                    Jmjtxx jmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
                    if (jmjtxx.getFamilyID() != null && !jmjtxx.getFamilyID().equals("")) {
                        if (saveJmjtxx) {
                            Bcjtxxxx6 responseBcjtxxxx6 = (Bcjtxxxx6) listBean.get(2);
                            if (responseBcjtxxxx6 == null || responseBcjtxxxx6.response == null) {
                                sb.append("\n");
                                sb.append("【家庭详细信息】服务器接口异常");
                            } else {
                                if (responseBcjtxxxx6 != null
                                        && responseBcjtxxxx6.response.errMsg.length() > 0) {
                                    sb.append("\n");
                                    sb.append("【家庭详细信息】" + responseBcjtxxxx6.response.errMsg);
                                    if (mOnSjglScglUploadLisener != null) {
                                        mOnSjglScglUploadLisener.onUploadFinished(true,
                                                sb.toString());
                                    }
                                } else {
                                    sb.append("\n");
                                    sb.append("【家庭详细信息】上传成功");
                                    if (mOnSjglScglUploadLisener != null) {
                                        mOnSjglScglUploadLisener.onUploadFinished(true,
                                                sb.toString());
                                    }
                                }
                            }
                        } else {
                            if (mOnSjglScglUploadLisener != null) {
                                mOnSjglScglUploadLisener.onUploadFinished(true, sb.toString());
                            }
                        }
                    } else {
                        sb.append("\n");
                        sb.append("【家庭详细信息】因服务器问题，暂时不能上传");
                        if (mOnSjglScglUploadLisener != null) {
                            mOnSjglScglUploadLisener.onUploadFinished(false, sb.toString());
                        }
                    }

                } else {
                    if (mOnSjglScglUploadLisener != null) {
                        mOnSjglScglUploadLisener.onUploadFinished(false, "网络请求异常");
                    }
                    // // // mToast.setDuration(Toast.LENGTH_SHORT);
                    // // // mToast.setText("网络请求异常,数据暂存本地");
                    // // // mToast.show();
                    // saveValueToDb();
                }

            }
        });
    }

    protected void saveResidentID_FamilyID(String residentID, String familyID) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        mJmjbxx.setResidentID(residentID);
        mJmjtxx.setFamilyID(familyID);
    }

    // 在Myapplication中维持一个该居民信息的全局对象
    private void loadResidentInfoIntoSession(String paperNum) {
        Resident bean = ResidentBll.query(paperNum);// 从数据库拿出这个居民的信息
        // 存入到Session中
        Session session = MyApplication.getInstance().getSession();
        session.setCurrentResident(bean);
    }
}
