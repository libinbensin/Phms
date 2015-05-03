/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * SjglGzlbDownloadUtil.java
 * classes : com.cking.phss.util.SjglGzlbDownloadUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-9 下午12:11:29
 */
package com.cking.phss.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.Log;

import com.cking.application.MyApplication;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.BeanUtil.OnResultSaveToDb;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.HistoryDisease;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_gxy.FZJC;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.Ddjmjbxx7;
import com.cking.phss.dto.Ddjmjkxx7_2;
import com.cking.phss.dto.Ddjmxwxg7_1;
import com.cking.phss.dto.Ddjtxxxx5;
import com.cking.phss.dto.DdsfzzpJ001;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.Zjddjmjbxx7_9;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.dto.sfgl.gxy.Ddgxyglkxxxx18;
import com.cking.phss.dto.sfgl.gxy.Dgxysfjlxxxx15;
import com.cking.phss.dto.sfgl.tnb.Dtnbsfjlxxxx22;
import com.cking.phss.sqlite.Resident;
import com.cking.phss.sqlite.ResidentBll;

/**
 * com.cking.phss.util.SjglGzlbDownloadUtil
 * @author Administrator <br/>
 * create at 2014-7-9 下午12:11:29
 */
public class SjglGzglDownloader {
    private static final String TAG = "SjglGzglDownloader";
    public Map<String, IBean> beanMap = null;
    public Context mContext = null;

    private static SjglGzglDownloader mSjglGzglDownloader;

    public static SjglGzglDownloader getInstance() {
        if (mSjglGzglDownloader == null) {
            mSjglGzglDownloader = new SjglGzglDownloader();
        }
        return mSjglGzglDownloader;
    }

    public interface OnSjglGzlbDownloadLisener {
        public void onDownloadFinished(boolean isSuccessful, String tipText);
    }

    private OnSjglGzlbDownloadLisener mOnSjglGzglDownloadLisener;

    public void setOnSjglGzlbDownloadLisener(OnSjglGzlbDownloadLisener listener) {
        mOnSjglGzglDownloadLisener = listener;
    }

    public interface OnLoadUuidLisener {

        /**
         * @param residentUUID
         * @param projectUUID
         */
        public void onLoadUuid(String residentUUID, String projectUUID);
    }

    private OnLoadUuidLisener mOnLoadUuidLisener;

    public void setOnLoadUuidLisener(OnLoadUuidLisener listener) {
        mOnLoadUuidLisener = listener;
    }

    // 从服务器获取数据 首先直接得到居民信息，在用得到的信息去拿 居民四种信息
    public void getJbxxFromWebAndSaveToDb(Context mContext, Map<String, IBean> beanMap,
            String residentID) {
        this.beanMap = beanMap;
        this.mContext = mContext;
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        /**
         * 直接得到居民基本信息
         */
        Zjddjmjbxx7_9 zjddjmjbxx7_9 = new Zjddjmjbxx7_9();
        zjddjmjbxx7_9.request = new Zjddjmjbxx7_9.Request();
        zjddjmjbxx7_9.request.returnRecord = 0;// 0 表示返回所有符合的记录

        // // ProgressDialogUtil.showProgressDialog(mContext, "正在查询", "请稍等...");

        // 设置责任医生和员工号，当前是否有用户登录并且在全局变量注册
        Login1 loginUser = MyApplication.getInstance().getSession().getLoginResult();
        if (loginUser != null && loginUser.response != null) {
            // Doctor doctor = new
            // Doctor(Integer.parseInt(loginUser.response.doctorID),
            // loginUser.response.doctorName);
            // mJmjbxx.setDutyDoctor(doctor);//因为责任医生并不一定是登陆的用户，所有不设置
            BeanID employeeNo = loginUser.response.employeeNo;
            zjddjmjbxx7_9.request.employeeNo = employeeNo;
        } else {
            // // ProgressDialogUtil.hideProgressDialog();
            // // mToast.setText("用户未登录成功，不能进行操作！");
            // // mToast.show();
            if (mOnSjglGzglDownloadLisener != null) {
                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "用户未登录成功，不能进行操作！");
            }
            return;
        }

        zjddjmjbxx7_9.request.residentID = residentID;

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变
        beanList.add(zjddjmjbxx7_9); // 添加 79 直接得到居民基本信息.xml idto

        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    Zjddjmjbxx7_9 responseZjddjmjbxx7_9 = (Zjddjmjbxx7_9) listBean.get(0);

                    if (responseZjddjmjbxx7_9 == null || responseZjddjmjbxx7_9.response == null) {
                        // // mToast.setText("【直接得到居民基本信息】服务器接口异常");
                        // // mToast.show();
                        // // ProgressDialogUtil.hideProgressDialog();
                        if (mOnSjglGzglDownloadLisener != null) {
                            mOnSjglGzglDownloadLisener.onDownloadFinished(false,
                                    "【直接得到居民基本信息】服务器接口异常");
                        }
                    } else {
                        if (responseZjddjmjbxx7_9.response.errMsg.length() > 0) {
                            // // mToast.setText("【直接得到居民基本信息】" +
                            // responseZjddjmjbxx7_9.response.errMsg);
                            // // mToast.show();
                            // // ProgressDialogUtil.hideProgressDialog();
                            if (mOnSjglGzglDownloadLisener != null) {
                                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "【直接得到居民基本信息】"
                                        + responseZjddjmjbxx7_9.response.errMsg);
                            }
                        } else {
                            // responseZjddjmjbxx7_9.response.resident=null;//测试建档时候读取身份证
                            if (responseZjddjmjbxx7_9.response.resident == null
                                    || responseZjddjmjbxx7_9.response.resident.size() <= 0) {// 返回的符合的记录不存在
                            // // 没有数据
                                // // mToast.setText("【直接得到居民基本信息】沒有数据");
                                // // mToast.show();
                                if (mOnSjglGzglDownloadLisener != null) {
                                    mOnSjglGzglDownloadLisener.onDownloadFinished(false,
                                            "【直接得到居民基本信息】沒有数据");
                                }
                            //
                                // // ProgressDialogUtil.hideProgressDialog();
                            } else {
                                // 成功拿到数据，不做提示，继续更新本地数据，
                                updateParams(responseZjddjmjbxx7_9.response.resident.get(0));
                            }
                        }
                    }

                } else {
                    if (mOnSjglGzglDownloadLisener != null) {
                        mOnSjglGzglDownloadLisener.onDownloadFinished(false, "网络请求异常");
                    }
                    // // mToast.setDuration(Toast.LENGTH_SHORT);
                    // // mToast.setText("网络请求异常");
                    // // mToast.show();
                    // // ProgressDialogUtil.hideProgressDialog();
                }

            }
        });
    }


    /**
     * 从网上得到居民基本信息，并存入beanmap
     */
    protected void updateParams(final com.cking.phss.dto.innner.Resident resident) {
        if (resident == null) {
            if (mOnSjglGzglDownloadLisener != null) {
                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "【直接得到居民基本信息】沒有数据");
            }
            // ProgressDialogUtil.hideProgressDialog();
            return;
        }
        // 首先设置一部分的基本信息
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());

        // 从服务器拿身份证照片
        getProfileFromWeb(resident.residentID);

        mJmjtxx.setFamilyID(resident.familyID);
        mJmjbxx.setResidentID(resident.residentID);
        mJmjbxx.setResidentName(resident.residentName);
        mJmjbxx.setSexCD(resident.sexCD);
        mJmjbxx.setBirthDay(resident.birthDay);
        mJmjbxx.setPaperNum(resident.paperNum);
        mJmjbxx.setCardID(resident.cardID);
        mJmjbxx.setSelfPhone(resident.selfPhone);
        mJmjbxx.setWorkUnit(resident.workUnit);
        if (resident.folkCD != null) {
            mJmjbxx.setFlokCD(resident.folkCD);
        }
        mJmjbxx.setEducationCD(resident.EducationCD);
        if (resident.vocationCD != null) {
            mJmjbxx.setVocationCD(resident.vocationCD);
        }
        mJmjbxx.setMarriageCD(resident.marriageCD);
        mJmjbxx.setAddressTypeCD(resident.AddressTypeCD);

        if (resident.nowProvince != null) {
            mJmjbxx.setNowProvince(resident.nowProvince);
        }
        if (resident.nowCity != null) {
            mJmjbxx.setNowCity(resident.nowCity);
        }
        if (resident.nowDistrict != null) {
            mJmjbxx.setNowDistrict(resident.nowDistrict);
        }
        if (resident.nowStreet != null) {
            mJmjbxx.setNowStreet(resident.nowStreet);
        }
        if (resident.nowZone != null) {
            mJmjbxx.setNowZone(resident.nowZone);
        }
        if (resident.nowRoad != null) {
            mJmjbxx.setNowRoad(resident.nowRoad);
        }

        mJmjbxx.setNowH(resident.nowH);
        mJmjbxx.setNowN(resident.nowN);
        mJmjbxx.setNowS(resident.nowS);

        mJmjbxx.setRegDetail(resident.regDetail);
        mJmjbxx.setRegAddress(resident.regDetail);

        mJmjbxx.setHeight(resident.height);
        mJmjbxx.setWeight(resident.weight);
        // 责任医生
        if (resident.dutyDoctor != null) {
            BeanID doctor = new BeanID();
            doctor.setiD(resident.dutyDoctor.getiD());
            doctor.setTagValue(resident.dutyDoctor.getTagValue());
            mJmjbxx.setDutyDoctor(doctor);
        }
        mJmjbxx.setFileStatusCD(resident.fileStatusCD);

        /**
         * 在调用 得到居民基本信息
         */
        Ddjmjbxx7 ddjmjbxx7 = new Ddjmjbxx7();
        ddjmjbxx7.request = new Ddjmjbxx7.Request();
        ddjmjbxx7.request.familyID = mJmjtxx.getFamilyID();
        ddjmjbxx7.request.residentID = mJmjbxx.getResidentID();
        Login1 loginUser = MyApplication.getInstance().getSession().getLoginResult();
        if (loginUser != null && loginUser.response != null) {
            ddjmjbxx7.request.employeeNo = loginUser.response.employeeNo;
        } else {
            // mToast.setText("用户未登录注册成功，不能进行操作！");
            // mToast.show();
            // ProgressDialogUtil.hideProgressDialog();
            if (mOnSjglGzglDownloadLisener != null) {
                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "用户未登录注册成功，不能进行操作！");
            }
            return;
        }

        /**
         * 得到居民家庭信息
         */
        Ddjtxxxx5 ddjtxxxx5 = new Ddjtxxxx5();
        ddjtxxxx5.request = new Ddjtxxxx5.Request();
        ddjtxxxx5.request.familyID = resident.familyID;
        ddjtxxxx5.request.employeeNo = ddjmjbxx7.request.employeeNo;

        /**
         * 得到居民行为习惯
         */
        Ddjmxwxg7_1 ddjmxwxg7_1 = new Ddjmxwxg7_1();
        ddjmxwxg7_1.request = new Ddjmxwxg7_1.Request();
        ddjmxwxg7_1.request.familyID = mJmjtxx.getFamilyID();
        ddjmxwxg7_1.request.residentID = mJmjbxx.getResidentID();
        ddjmxwxg7_1.request.employeeNo = ddjmjbxx7.request.employeeNo;
        /**
         * 得到居民健康信息
         */
        Ddjmjkxx7_2 ddjmjkxx7_2 = new Ddjmjkxx7_2();
        ddjmjkxx7_2.request = new Ddjmjkxx7_2.Request();
        ddjmjkxx7_2.request.familyID = mJmjtxx.getFamilyID();
        ddjmjkxx7_2.request.residentID = mJmjbxx.getResidentID();
        ddjmjkxx7_2.request.employeeNo = ddjmjbxx7.request.employeeNo;

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变
        beanList.add(ddjmjbxx7); // 添加 7 得到居民基本信息.xml idto
        beanList.add(ddjmxwxg7_1);
        beanList.add(ddjmjkxx7_2);

        if (resident.familyID != null && !resident.familyID.trim().equals("")) {
            beanList.add(ddjtxxxx5); // 家庭信息防最后，因为家庭信息不一定有，防最后容易判断操作
        }

        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                // ProgressDialogUtil.hideProgressDialog();
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    // 得到居民基本信息的反馈信息
                    Ddjmjbxx7 ddjmjbxx7 = (Ddjmjbxx7) listBean.get(0);
                    if (ddjmjbxx7 == null || ddjmjbxx7.response == null) {
                        sb.append("【得到居民基本信息】服务器接口异常");
                    } else {
                        if (ddjmjbxx7.response.errMsg.length() > 0) {
                            sb.append("【得到居民基本信息】" + ddjmjbxx7.response.errMsg);
                        } else {
                            updateJmjbxx(ddjmjbxx7.response);
                            sb.append("【得到居民基本信息】获取成功");
                        }
                    }

                    // 得到居民行为习惯
                    Ddjmxwxg7_1 responseDdjmxwxg7_1 = (Ddjmxwxg7_1) listBean.get(1);
                    if (responseDdjmxwxg7_1 == null || responseDdjmxwxg7_1.response == null) {
                        sb.append("\n");
                        sb.append("【居民行为习惯】服务器接口异常");
                    } else {
                        if (responseDdjmxwxg7_1.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【居民行为习惯】" + responseDdjmxwxg7_1.response.errMsg);
                        } else {
                            updateJmxwxg(responseDdjmxwxg7_1.response);
                            sb.append("\n");
                            sb.append("【居民行为习惯】获取成功");
                        }
                    }

                    // 居民健康信息
                    Ddjmjkxx7_2 responseDdjmjkxx7_2 = (Ddjmjkxx7_2) listBean.get(2);
                    if (responseDdjmjkxx7_2 == null || responseDdjmjkxx7_2.response == null) {
                        sb.append("\n");
                        sb.append("【居民健康信息】服务器接口异常");
                    } else {
                        if (responseDdjmjkxx7_2.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【居民健康信息】" + responseDdjmjkxx7_2.response.errMsg);
                        } else {
                            updateJmjkxx(responseDdjmjkxx7_2.response);
                            sb.append("\n");
                            sb.append("【居民健康信息】获取成功");
                        }
                    }

                    // 得到居民家庭信息的反馈信息，从本地数据库拿,先判断到底差没差居民基本信息
                    if (resident.familyID != null && !resident.familyID.trim().equals("")) {
                        Ddjtxxxx5 ddjtxxxx5 = (Ddjtxxxx5) listBean.get(3);
                        if (ddjtxxxx5 == null || ddjtxxxx5.response == null) {
                            sb.append("\n");
                            sb.append("【得到居民家庭信息】服务器接口异常");
                        } else {
                            if (ddjtxxxx5.response.errMsg.length() > 0) {
                                sb.append("\n");
                                sb.append("【得到居民家庭信息】" + ddjtxxxx5.response.errMsg);
                            } else {
                                updateJmjtxx(ddjtxxxx5.response);
                                sb.append("\n");
                                sb.append("【得到居民家庭信息】获取成功");
                            }
                        }
                    } else {
                        sb.append("\n");
                        sb.append("【居民家庭信息】没有数据");
                    }
                    // mToast.setDuration(Toast.LENGTH_LONG);
                    // mToast.setText(sb.toString());
                    // mToast.show();
                    // setValue();
                    saveValueToDb();
                    // 清空居民健康体检里面的数据
                    //((MainActivity) mContext).clearJktj();

                    if (mOnSjglGzglDownloadLisener != null) {
                        mOnSjglGzglDownloadLisener.onDownloadFinished(true, sb.toString());
                    }
                } else {
                    if (mOnSjglGzglDownloadLisener != null) {
                        mOnSjglGzglDownloadLisener.onDownloadFinished(false, "网络请求异常");
                    }
                    // mToast.setDuration(Toast.LENGTH_SHORT);
                    // mToast.setText("网络请求异常");
                    // mToast.show();
                }
            }
        });
    }

    /**
     * 根据网上得到的居民id，更新界面的居民基本信息
     */
    public void updateJmjbxx(com.cking.phss.dto.Ddjmjbxx7.Response response) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        mJmjbxx.setResideStatusCD(response.resideStatusCD);
        mJmjbxx.setRegTypeCD(response.regTypeCD);
        mJmjbxx.setRegAddress(response.regAddress);
        mJmjbxx.setRelaName(response.relaName);
        mJmjbxx.setRelaPhone(response.RelaPhone);
        mJmjbxx.setResideCD(response.resideCD);
        mJmjbxx.setBloodCD(response.bloodCD);
        mJmjbxx.setInsuranceCD(response.insuranceCD + "");
        mJmjbxx.setInsuranceNum(response.insuranceNum);
        mJmjbxx.setAidCD(response.aidCD);
        mJmjbxx.setRegDetail(response.regAddress);
        mJmjbxx.setRegAddress(response.regAddress);

        if (response.nationalityCD != null) {
            mJmjbxx.setNationalityCD(response.nationalityCD);
        }

        if (response.relation != null) {
            BeanID relation = new BeanID();
            relation.setiD(response.relation.getiD());
            relation.setTagValue(response.relation.getTagValue());
            mJmjbxx.setRelation(relation);
        }
        mJmjbxx.setZip(response.ZIP);
        mJmjbxx.setEmail(response.email);
        mJmjbxx.setManuaINm(response.manualNm + "");// 应该为字符串

        if (response.dutyDoctor != null) {
            mJmjbxx.setDutyDoctor(response.dutyDoctor);
        }

        if (response.manageOrg != null) {
            mJmjbxx.setManageOrg(response.manageOrg);
        }

        if (response.station != null) {
            mJmjbxx.setStation(response.station);
        }

        if (response.buildDate != null) {
            mJmjbxx.setBuildDate(response.buildDate);
        }

        if (response.builder != null) {
            mJmjbxx.setBuilder(response.builder);
        }

        if (response.buildOrg != null) {
            mJmjbxx.setBuildOrg(response.buildOrg);
        }

        mJmjbxx.setHeight(response.height);
        mJmjbxx.setWeight(response.weight);
        mJmjbxx.setBmi(response.BMI);
        mJmjbxx.setBust(response.Bust + "");
        mJmjbxx.sethIP(response.HIP);
        mJmjbxx.setWaist(response.waist);
        mJmjbxx.setFileStatusCD(response.fileStatusCD);

        // 先存入到数据库
        // 1.写居民登记信息表
        Resident bean = new Resident();
        bean.setPaperNum(mJmjbxx.getPaperNum());
        bean.setResidentName(mJmjbxx.getResidentName());
        bean.setCardId(mJmjbxx.getCardID());
        ResidentBll.saveBean(bean);

        // 2.写session
        loadResidentInfoIntoSession(mJmjbxx.getPaperNum());
        // Global.updateJmjbxx = true;
    }

    /**
     * 得到家庭信息的设置
     */
    public void updateJmjtxx(Ddjtxxxx5.Response response) {
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        mJmjtxx.setFamilyID(response.familyID);
        mJmjtxx.setFamilyTypeCD(response.familyTypeCD);
        mJmjtxx.setIncomeCD(response.incomeCD);
        mJmjtxx.setHouseHoldCD(response.houseHoldCD);
        try {
            int population = Integer.parseInt(response.population);
            mJmjtxx.setPopulation(population);
        } catch (NumberFormatException e) {
        }
        mJmjtxx.setEconomics(response.economics);
        mJmjtxx.setHousingLighting(response.housingLighting);
        
        if (!StringUtil.isEmptyString(response.housingRooms)) {
            mJmjtxx.setHousingRooms(Integer.parseInt(response.housingRooms));
        }
        
        mJmjtxx.setHousingVentilation(response.housingVentilation);
        mJmjtxx.setHousingWarm(response.housingWarm);
        mJmjtxx.setAirHumidity(response.airHumidity);
        mJmjtxx.setHealthStatus(response.healthStatus);
        mJmjtxx.setWaterStatus(response.waterStatus);
        mJmjtxx.setSewageTreatment(response.sewageTreatment);
        
        
        if( null != response.stylisticDevices )
        {       
            mJmjtxx.setStylisticDevices(response.stylisticDevices);
        }
        
        mJmjtxx.setSmokeRemoval(response.smokeRemoval);
        mJmjtxx.setFamilyMember(response.familyMember);
        mJmjtxx.setFamilyMainProblems(response.familyMainProblems);
        mJmjtxx.setArea(response.area);
        mJmjtxx.setAvgArea(response.avgArea);
        mJmjtxx.setFloorTypeCD(response.floorTypeCD);
        mJmjtxx.setKitchenUseCD(response.kitchenUseCD);
        mJmjtxx.setKitchenFanCD(response.kitchenFanCD);
        mJmjtxx.setWaterCD(response.waterCD);
        mJmjtxx.setFuelCD(response.fuelCD);
        mJmjtxx.setSanToiletCD(response.sanToiletCD);
        mJmjtxx.setNotSanToiletCD(response.NotSanToiletCD);
        mJmjtxx.setAnimalPlaceCD(response.animalPlaceCD);
        mJmjtxx.setGarbageDealCD(response.garbageDealCD);
        mJmjtxx.setApplianceCD(response.applianceCD);
        mJmjtxx.setTransport(response.transport);
        // Global.updateJmjtxx = true;
    }

    /**
     * 根据网上得到的居民id，更新界面的居民健康信息
     */
    public void updateJmjkxx(Ddjmjkxx7_2.Response response) {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        mJmjkxx.setFatherCD(response.fatherCD);
        mJmjkxx.setFatherName(response.fatherName);
        mJmjkxx.setMotherCD(response.motherCD);
        mJmjkxx.setMotherName(response.motherName);
        mJmjkxx.setBrotherCD(response.brotherCD);
        mJmjkxx.setBrotherName(response.brotherName);
        mJmjkxx.setChildCD(response.childCD);
        mJmjkxx.setChildName(response.childName);
        mJmjkxx.setOtherMemberCD(response.otherMemberCD);
        mJmjkxx.setDeformityCD(response.deformityCD);
        mJmjkxx.setDeformityCardNo(response.deformityCardNo);
        mJmjkxx.setDeformityLevel(response.deformityLevel);
        mJmjkxx.setDeformityName(response.deformityName);
        mJmjkxx.setHeredityCD(response.heredityCD);
        mJmjkxx.setHeredityName(response.heredityName);
        mJmjkxx.setExposureCD(response.exposureCD);
        mJmjkxx.setExposureName(response.exposureName);

        List<HistoryDisease> historyDis = mJmjkxx.getHistoryDisease();
        if (historyDis != null) { // 如果有数据则先清空
            historyDis.clear();
        } else { // 如果没有则先创建一个实体
            historyDis = new ArrayList<HistoryDisease>();
            mJmjkxx.setHistoryDisease(historyDis);
        }
        if (response.historyDisease != null) {
            for (int i = 0; i < response.historyDisease.size(); i++) {
                HistoryDisease historyDisease = new HistoryDisease();
                historyDisease.sethDType(response.historyDisease.get(i).hDType);
                historyDisease.setDisSn(response.historyDisease.get(i).disSn);
                historyDisease.setDisease(response.historyDisease.get(i).disease);
                historyDisease.setDiagnoseDate(response.historyDisease.get(i).diagnoseDate);
                historyDisease.sethDReason(response.historyDisease.get(i).cureDes);
                historyDisease.setCureHos(response.historyDisease.get(i).cureHos);
                historyDisease.setHappenDate(response.historyDisease.get(i).happenDate);
                historyDisease.setResultCD(response.historyDisease.get(i).resultCD);
                historyDisease.setiCD10(response.historyDisease.get(i).iCD10);
                historyDis.add(historyDisease);
            }
        }

        List<HistoryHyper> historyHypers = mJmjkxx.getHistoryHyper();
        if (historyHypers != null) { // 如果有数据则先清空
            historyHypers.clear();
        } else { // 如果没有则先创建一个实体
            historyHypers = new ArrayList<HistoryHyper>();
            mJmjkxx.setHistoryHyper(historyHypers);
        }
        if (response.historyHyper != null) {
            for (int i = 0; i < response.historyHyper.size(); i++) {
                // ido信息不全
                HistoryHyper historyHyper = new HistoryHyper();
                // historyHyper.setCureDes(response.historyHyper.get(i).)
                historyHyper.setHyperTypeCD(response.historyHyper.get(i).hyperTypeCD);
                historyHyper.setDisSn(response.historyHyper.get(i).hyperSn);
                historyHyper.setHyperSource(response.historyHyper.get(i).hyperSource);
                historyHyper.setCureDes(response.historyHyper.get(i).cureDes);
                historyHyper.setHappenDate(response.historyHyper.get(i).happenDate);
                historyHyper.setHyperReason(response.historyHyper.get(i).hyperReason);
                historyHypers.add(historyHyper);
            }
        }
        // Global.updateJmjkxx = true;
    }

    /**
     * 根据网上得到的居民id，更新界面的居民行为习惯
     */
    public void updateJmxwxg(Ddjmxwxg7_1.Response response) {
        Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
        mJmxwxg.setSmokeCD(response.smokeCD);
        mJmxwxg.setSmokeAge(response.smokeAge);
        mJmxwxg.setNoSmokeAge(response.noSmokeAge);
        mJmxwxg.setSmokeDay(response.smokeDay);
        mJmxwxg.setSmokeDayPast(response.smokeDayPast);
        mJmxwxg.setDrinkCD(response.drinkCD);
        mJmxwxg.setDrinkTypeCD(response.drinkTypeCD);
        mJmxwxg.setDrinkAmount(response.drinkAmount);
        mJmxwxg.setNoDrinkCD(response.noDrinkCD);
        mJmxwxg.setNoDrinkAge(response.noDrinkAge);
        mJmxwxg.setPastDrinkNum(response.pastDrinkNum);
        mJmxwxg.setPastDrinkAmount(response.pastDrinkAmount);
        mJmxwxg.setPastDrinkTypeCD(response.pastDrinkTypeCD);
        mJmxwxg.setFoodCD(String.valueOf(response.foodCD));
        mJmxwxg.setBrushTeethCD(response.brushTeethCD);
        mJmxwxg.setSportRateCD(response.sportRateCD);
        mJmxwxg.setSportTypeCD(response.sportTypeCD);
        mJmxwxg.setSportTypeElse(response.sportTypeElse);
        mJmxwxg.setSportTime(response.sportTime);
        mJmxwxg.setPrimaryEvent(response.primaryEventCD);
        mJmxwxg.setPrimaryEventName(response.primaryEventName);
        // Global.updateJmxwxg = true;
    }
    
    // 获取最后一条高血压随访信息
    public void getLastGxySfxxAndSaveGxyjlxxxxToDb(Context mContext, Map<String, IBean> beanMap,
            String residentID) {
        this.beanMap = beanMap;
        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            if (mOnSjglGzglDownloadLisener != null) {
                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "当前没有医生登录，请先登录！");
            }
            // mToast.setText("当前没有医生登录，请先登录！");
            // mToast.show();
            return;
        }

        // 高血压数据
        Dgxysfjlxxxx15 dgxysfjlxxxx15 = new Dgxysfjlxxxx15();
        dgxysfjlxxxx15.request = new Dgxysfjlxxxx15.Request();
        dgxysfjlxxxx15.request.employeeNo = login1Result.response.employeeNo;
        dgxysfjlxxxx15.request.residentID = residentID;
        dgxysfjlxxxx15.request.visitID = "0";// 0表示请求最后一条数据

        // // 高血压报卡数据
        // Ddgxyglkxxxx18 ddgxyglkxxxx18 = new Ddgxyglkxxxx18();
        // ddgxyglkxxxx18.request = new Ddgxyglkxxxx18.Request();
        // ddgxyglkxxxx18.request.employeeNo = login1Result.response.employeeNo;
        // ddgxyglkxxxx18.request.residentID = residentID;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dgxysfjlxxxx15);
        // beanList.add(ddgxyglkxxxx18);
        // ProgressDialogUtil.showProgressDialog(mContext, "正在查询", "请稍等...");
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                // ProgressDialogUtil.hideProgressDialog();
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    Dgxysfjlxxxx15 dgxysfjlxxxx15Result = (Dgxysfjlxxxx15) listBean.get(0);
                    if (dgxysfjlxxxx15Result == null || dgxysfjlxxxx15Result.response == null) {
                        if (mOnSjglGzglDownloadLisener != null) {
                            mOnSjglGzglDownloadLisener.onDownloadFinished(false, "【得到随访记录】服务器接口异常");
                        }
                        // mToast.setText("【得到随访记录】服务器接口异常");
                        // mToast.show();
                    } else {
                        if (dgxysfjlxxxx15Result.response.errMsg.length() > 0) {
                            if (mOnSjglGzglDownloadLisener != null) {
                                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "【得到随访记录】"
                                        + dgxysfjlxxxx15Result.response.errMsg);
                            }
                            // mToast.setText("【得到随访记录】" +
                            // dgxysfjlxxxx15Result.response.errMsg);
                            // mToast.show();
                        } else {
                            try {
                                saveGxyjlxxxxToDb(dgxysfjlxxxx15Result.response);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    // Ddgxyglkxxxx18 responseDdgxyglkxxxx18 = (Ddgxyglkxxxx18)
                    // listBean.get(1);
                    // if (responseDdgxyglkxxxx18 == null ||
                    // responseDdgxyglkxxxx18.response == null
                    // || responseDdgxyglkxxxx18.response.errMsg.length() > 0) {
                    // if (mOnSjglGzglDownloadLisener != null) {
                    // mOnSjglGzglDownloadLisener.onDownloadFinished(false,
                    // "【获取高血压报卡】服务器接口异常");
                    // }
                    // } else {
                    // if
                    // (StringUtil.isEmptyString(responseDdgxyglkxxxx18.response.paperNum))
                    // {// 返回的符合的记录不存在
                    // // 没有数据
                    // if (mOnSjglGzglDownloadLisener != null) {
                    // mOnSjglGzglDownloadLisener.onDownloadFinished(false,
                    // "【获取高血压报卡】没有数据"
                    // + dgxysfjlxxxx15Result.response.errMsg);
                    // }
                    // } else {
                    // saveGxyjlbkxxxxToDb(responseDdgxyglkxxxx18);
                    // }
                    // }
                }
            }
        });
    }

    /**
     * @param responseDdgxyglkxxxx18
     */
    protected void saveGxyjlbkxxxxToDb(Ddgxyglkxxxx18 responseDdgxyglkxxxx18) {
    }

    public void saveGxyjlxxxxToDb(Dgxysfjlxxxx15.Response response) throws ParseException {
        final Sfgljl_gxy mSfgljl_gxy = new Sfgljl_gxy();

        String visitDate = response.visitDate;
        mSfgljl_gxy.setVisitID(response.visitID);// 使用上一次的id，即为编辑模式
        mSfgljl_gxy.setVisitDate(response.visitDate);
        // date不设置，使用新的date
        mSfgljl_gxy.setsFFSCD(response.SFFSCD);
        try {
            mSfgljl_gxy.sethBPTypeCD(Integer.parseInt(response.hBPTypeCD));
        } catch (NumberFormatException e) {

        }
        mSfgljl_gxy.setxCSF(response.XCSF);
        mSfgljl_gxy.setZZCD(response.ZZCD);
        mSfgljl_gxy.setZZQT(response.ZZQT);
        mSfgljl_gxy.setsBP(response.SBP);
        mSfgljl_gxy.setdBP(response.DBP);
        mSfgljl_gxy.setbCTZ(response.BCTZ);
        mSfgljl_gxy.setBCSG(response.BCSG);
        mSfgljl_gxy.settZZS(response.TZZS);
        mSfgljl_gxy.setXCZS(response.xczs);
        mSfgljl_gxy.setbCXL(response.BCXL);
        mSfgljl_gxy.setqTTZ(response.QTTZ);
        mSfgljl_gxy.setxCTZ(response.XCTZ);
        mSfgljl_gxy.setxCXL(response.XCXL);
        mSfgljl_gxy.setbCXYL(response.BCXYL);
        mSfgljl_gxy.setxCXY(response.XCXY);
        mSfgljl_gxy.setbCYJ(response.BCYJ);
        mSfgljl_gxy.setxCYJ(response.XCYJ);
        mSfgljl_gxy.setyDZC(response.YDZC);
        mSfgljl_gxy.setyDCF(response.YDCF);
        mSfgljl_gxy.setxCYDZC(response.XCYDZC);
        mSfgljl_gxy.setxCYDCD(response.XCYDCD);
        mSfgljl_gxy.setbCSYL(response.BCSYL);
        mSfgljl_gxy.setxCSYL(response.XCSYL);
        mSfgljl_gxy.setxLTZCD(response.XLTZCD);
        mSfgljl_gxy.setzYXWCD(response.ZYXWCD);
        mSfgljl_gxy.setCriticalOrgan(response.criticalOrgan);
        mSfgljl_gxy.setComorbidity(response.comorbidity);
        mSfgljl_gxy.setOtherDiseases(response.otherDiseases);
        mSfgljl_gxy.recommendation = response.recommendation;

        List<FZJC> fzjcList = new ArrayList<Sfgljl_gxy.FZJC>();
        String fzjcString = response.FZJC;
        if (fzjcString != null && !fzjcString.equals("")) {
            String[] fzjcs = fzjcString.split("\\|");
            if (fzjcs != null && fzjcs.length > 0) {
                for (String f : fzjcs) {
                    String[] fItem = f.split(",");
                    if (fItem.length >= 4) {
                        FZJC fzjc = new FZJC();
                        fzjc.setjCXM(fItem[0]);
                        fzjc.setjCJG(fItem[1]);
                        fzjc.setjCR(fItem[2]);
                        fzjc.setjCRQ(fItem[3]);
                        fzjcList.add(fzjc);
                    }
                }
            }
        }
        mSfgljl_gxy.setfZJC(fzjcList);

        mSfgljl_gxy.setfYYCXCD(response.FYYCXCD);
        mSfgljl_gxy.setbLFY(response.BLFY);
        mSfgljl_gxy.setfYQK(response.FYQK);
        mSfgljl_gxy.setsFFLCD(response.SFFLCD);
        mSfgljl_gxy.setzZYY(response.ZZYY);
        mSfgljl_gxy.setzZKB(response.ZZKB);
        mSfgljl_gxy.setBz(response.BZ);

        List<MedicineUse> medicineUses = new ArrayList<MedicineUse>();
        if (response.medicineUse != null) {
            for (MedicineUse medicineUse : response.medicineUse) {
                if (medicineUse == null) {
                    continue;
                }
                medicineUses.add(medicineUse);
            }
        }
        mSfgljl_gxy.setMedicineUse(medicineUses);

        // 写数据库
        List<IBean> listBeans = new ArrayList<IBean>();
        listBeans.add(mSfgljl_gxy);
        BeanUtil.getInstance().saveSfglToDb(listBeans, new Date().getTime(), new Date().getTime(),
                new BeanUtil.OnResultSaveToDb() {
                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                        if (isSucc) {
                            // mToast.setText("随访管理高血压存储成功！");
                            // mToast.show();
                            SaveToDbResult result = listBean.get(0);
                            // 操作标志 1-原始 2-新建 3-修改
                            // 保存本地记录
                            Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                            BeanUtil.getInstance().saveLocalRecord(mContext, jmjbxx,
                                    result.projectUUID, "41", 1, 1, new Date().getTime(),
                                    mSfgljl_gxy.getClass().getName());

                            if (mOnLoadUuidLisener != null) {
                                mOnLoadUuidLisener.onLoadUuid(result.residentUUID,
                                        result.projectUUID);
                            }
                            if (mOnSjglGzglDownloadLisener != null) {
                                mOnSjglGzglDownloadLisener.onDownloadFinished(true, "随访管理高血压存储成功！");
                            }
                        } else {
                            if (mOnSjglGzglDownloadLisener != null) {
                                mOnSjglGzglDownloadLisener
                                        .onDownloadFinished(false, "随访管理高血压存储失败！");
                            }
                            // mToast.setText("随访管理高血压存储失败！");
                            // mToast.show();
                        }
                    }
                });
    }

    // 获取最后一条糖尿病随访信息
    public void getLastTnbSfxxAndSaveTnbjlxxxxToDb(Context mContext, Map<String, IBean> beanMap,
            String residentID) {
        this.beanMap = beanMap;
        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            if (mOnSjglGzglDownloadLisener != null) {
                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "当前没有医生登录，请先登录！");
            }
            // mToast.setText("当前没有医生登录，请先登录！");
            // mToast.show();
            return;
        }
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());

        Dtnbsfjlxxxx22 dtnbsfjlxxxx22 = new Dtnbsfjlxxxx22();
        dtnbsfjlxxxx22.request = new Dtnbsfjlxxxx22.Request();
        dtnbsfjlxxxx22.request.employeeNo = login1Result.response.employeeNo;
        dtnbsfjlxxxx22.request.residentID = jmjbxx.getResidentID();
        dtnbsfjlxxxx22.request.visitID = "0";// 0表示请求最后一条数据

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dtnbsfjlxxxx22);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    Dtnbsfjlxxxx22 dtnbsfjlxxxx22Result = (Dtnbsfjlxxxx22) listBean.get(0);

                    if (dtnbsfjlxxxx22Result == null || dtnbsfjlxxxx22Result.response == null) {
                        if (mOnSjglGzglDownloadLisener != null) {
                            mOnSjglGzglDownloadLisener.onDownloadFinished(false, "【得到随访记录】服务器接口异常");
                        }
                    } else {
                        if (dtnbsfjlxxxx22Result.response.errMsg.length() > 0) {
                            if (mOnSjglGzglDownloadLisener != null) {
                                mOnSjglGzglDownloadLisener.onDownloadFinished(false, "【得到随访记录】"
                                        + dtnbsfjlxxxx22Result.response.errMsg);
                            }
                        } else {
                            try {
                                saveTnbjlxxxxToDb(dtnbsfjlxxxx22Result.response);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public void saveTnbjlxxxxToDb(Dtnbsfjlxxxx22.Response response) throws ParseException {
        final Sfgljl_tnb mSfgljl_tnb = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());

        String visitDate = response.visitDate;

        mSfgljl_tnb.setVisitID(response.visitID);// 使用上一次的id，即为编辑模式
        mSfgljl_tnb.setVisitDate(response.visitDate);
        // date不设置，使用新的date
        mSfgljl_tnb.setsFFSCD(response.SFFSCD);
        mSfgljl_tnb.setxCSF(response.XCSF);
        mSfgljl_tnb.setzZCD(response.ZZCD);
        mSfgljl_tnb.setzZQT(response.ZZQT);
        mSfgljl_tnb.setsBP(response.SBP);
        mSfgljl_tnb.setdBP(response.DBP);
        mSfgljl_tnb.setbCXL(response.HeartRate);
        mSfgljl_tnb.setbCTZ(response.BCTZ);
        mSfgljl_tnb.setbCSG(response.BCSG);
        mSfgljl_tnb.setxCTZ(response.XCTZ);
        mSfgljl_tnb.settZZS(response.TZZS);
        mSfgljl_tnb.setdMBDCD(response.DMBDCD);
        mSfgljl_tnb.setWaistNow(response.WaistNow);
        mSfgljl_tnb.setWaistTarget(response.WaistTarget);
        mSfgljl_tnb.setqTTZ(response.QTTZ);
        mSfgljl_tnb.setbCXYL(response.BCXYL);
        mSfgljl_tnb.setxCXY(response.XCXY);
        mSfgljl_tnb.setbCYJ(response.BCYJ);
        mSfgljl_tnb.setxCYJ(response.XCYJ);
        mSfgljl_tnb.setyDZC(response.YDZC);
        mSfgljl_tnb.setyDCF(response.YDCF);
        mSfgljl_tnb.setxCYDZC(response.XCYDZC);
        mSfgljl_tnb.setxCYDCD(response.XCYDCD);
        mSfgljl_tnb.setbCZSL(response.BCZSL);
        mSfgljl_tnb.setxCZSL(response.XCZSL);
        mSfgljl_tnb.setxLTZCD(response.XLTZCD);
        mSfgljl_tnb.setzYXWCD(response.ZYXWCD);
        mSfgljl_tnb.setkFXT(response.KFXT);
        mSfgljl_tnb.setjCSJ(response.JCSJ);
        mSfgljl_tnb.setxHDB(response.XHDB);
        mSfgljl_tnb.setqTJC(response.QTJC);

        List<FZJC> fzjcList = new ArrayList<Sfgljl_gxy.FZJC>();
        String fzjcString = response.QTJC;// 其他检查
        if (fzjcString != null) {
            String[] fzjcs = fzjcString.split("\\|");
            if (fzjcs != null && fzjcs.length > 0) {
                for (String f : fzjcs) {
                    String[] fItem = f.split(",");
                    if (fItem.length >= 4) {
                        FZJC fzjc = new FZJC();
                        fzjc.setjCXM(fItem[0]);
                        fzjc.setjCJG(fItem[1]);
                        fzjc.setjCR(fItem[2]);
                        fzjc.setjCRQ(fItem[3]);
                        fzjcList.add(fzjc);
                    }
                }
            }
        }

        mSfgljl_tnb.setfZJC(fzjcList);

        mSfgljl_tnb.setfYYCXCD(response.FYYCXCD);
        mSfgljl_tnb.setbLFY(response.BLFY);
        mSfgljl_tnb.setfYQK(response.FYQK);
        mSfgljl_tnb.setdXTFYCD(response.DXTFYCD);
        mSfgljl_tnb.setsFFLCD(response.SFFLCD);
        mSfgljl_tnb.setzZYY(response.ZZYY);
        mSfgljl_tnb.setzZKB(response.ZZKB);
        mSfgljl_tnb.setBz(response.BZ);

        List<MedicineUse> medicineUses = new ArrayList<MedicineUse>();
        if (response.medicineUse != null) {
            for (MedicineUse medicineUse : response.medicineUse) {
                if (medicineUse == null)
                    continue;
                medicineUses.add(medicineUse);
            }
        }
        mSfgljl_tnb.setMedicineUse(medicineUses);

        // 写数据库
        List<IBean> listBeans = new ArrayList<IBean>();
        listBeans.add(mSfgljl_tnb);
        BeanUtil.getInstance().saveSfglToDb(listBeans, new Date().getTime(), new Date().getTime(),
                new BeanUtil.OnResultSaveToDb() {
                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                        if (isSucc) {
                            // mToast.setText("随访管理高血压存储成功！");
                            // mToast.show();
                            SaveToDbResult result = listBean.get(0);
                            // 操作标志 1-原始 2-新建 3-修改
                            // 保存本地记录
                            Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                            BeanUtil.getInstance().saveLocalRecord(mContext, jmjbxx,
                                    result.projectUUID, "42", 1, 1, new Date().getTime(),
                                    Sfgljl_tnb.class.getName());

                            if (mOnLoadUuidLisener != null) {
                                mOnLoadUuidLisener.onLoadUuid(result.residentUUID,
                                        result.projectUUID);
                            }
                            if (mOnSjglGzglDownloadLisener != null) {
                                mOnSjglGzglDownloadLisener.onDownloadFinished(true, "随访管理糖尿病存储成功！");
                            }
                        } else {
                            if (mOnSjglGzglDownloadLisener != null) {
                                mOnSjglGzglDownloadLisener
                                        .onDownloadFinished(false, "随访管理糖尿病存储失败！");
                            }
                            // mToast.setText("随访管理高血压存储失败！");
                            // mToast.show();
                        }
                    }
                });
    }

    public void getProfileFromWeb(String residentID) {
        DdsfzzpJ001 ddsfzzpJ001 = new DdsfzzpJ001();
        ddsfzzpJ001.request = new DdsfzzpJ001.Request();
        ddsfzzpJ001.request.residentID = residentID;
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(ddsfzzpJ001);

        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    DdsfzzpJ001 responseDdsfzzpJ001 = (DdsfzzpJ001) listBean.get(0);
                    if (responseDdsfzzpJ001 == null || responseDdsfzzpJ001.response == null) {
                        sb.append("【得到身份证照片】服务器接口异常");
                    } else {
                        if (responseDdsfzzpJ001.response.errMsg.length() > 0) {
                            sb.append("【得到身份证照片】" + responseDdsfzzpJ001.response.errMsg);
                        } else {
                            if (responseDdsfzzpJ001.response.photo.trim().equals("")) {
                                sb.append("【得到身份证照片】失败，服务器没有数据");
                            } else {
                                // showProfile(responseDdsfzzpJ001.response.residentID,
                                // responseDdsfzzpJ001.response.photo);
                                sb.append("【得到身份证照片】成功");
                            }
                        }
                    }
                    // mToast.setDuration(Toast.LENGTH_LONG);
                    // mToast.setText(sb.toString());
                    // mToast.show();
                } else {
                    // mToast.setDuration(Toast.LENGTH_SHORT);
                    // mToast.setText("网络请求异常");
                    // mToast.show();
                }

            }
        });

    }

    private void saveValueToDb() {
        // 保存数据到数据库
        final Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        // 将省市等保存到NowDetail节点
        if (mJmjbxx != null) {
            mJmjbxx.setNowDetail(getNowDetail(mJmjbxx));
        }
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
        if (mJmjbxx == null) {
            // mToast.setText("用户基本信息填写不完整");
            // mToast.show();
            return;
        } else {
            // 1.写居民登记信息表
            Resident bean = new Resident();
            bean.setPaperNum(mJmjbxx.getPaperNum());
            bean.setResidentID(mJmjbxx.getResidentID());
            bean.setResidentName(mJmjbxx.getResidentName());
            bean.setCardId(mJmjbxx.getCardID());
            ResidentBll.saveBean(bean);

            // 2.写session全局信息
            loadResidentInfoIntoSession(mJmjbxx.getPaperNum());
        }

        // 3.写居民基本信息表
        List<IBean> listBean = new ArrayList<IBean>();
        listBean.add(mJmjbxx);
        listBean.add(mJmjtxx);
        listBean.add(mJmjkxx);
        listBean.add(mJmxwxg);

        BeanUtil.getInstance().saveJbxxToDb(listBean, new OnResultSaveToDb() {
            @Override
            public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                if (isSucc) {
                    SaveToDbResult result = listBean.get(0);
                    // 操作标志 1-原始 2-新建 3-修改
                    // 保存本地记录
                    BeanUtil.getInstance().saveLocalRecord(mContext, mJmjbxx, result.projectUUID,
                            "21", 1, 1, new Date().getTime(), mJmjbxx.getClass().getName());

                    if (mOnLoadUuidLisener != null) {
                        mOnLoadUuidLisener.onLoadUuid(result.residentUUID, result.projectUUID);
                    }
                }
            }
        });
    }

    // 在Myapplication中维持一个该居民信息的全局对象
    private void loadResidentInfoIntoSession(String paperNum) {
        Resident bean = ResidentBll.query(paperNum);// 从数据库拿出这个居民的信息
        // 存入到Session中
        Session session = MyApplication.getInstance().getSession();
        session.setCurrentResident(bean);
        Log.i(TAG, "loadResidentInfoIntoSession - paperNum: " + paperNum);
    }

    private String getNowDetail(Jmjbxx jmjbxx) {
        StringBuilder sb = new StringBuilder();
        // 将省市等保存到NowDetail节点
        if (jmjbxx != null) {
            BeanID province = jmjbxx.getNowProvince();
            if (province != null && province.getTagValue() != null) {
                sb.append(province.getTagValue());
            }

            BeanID city = jmjbxx.getNowCity();
            if (city != null && city.getTagValue() != null) {
                sb.append(city.getTagValue());
            }

            BeanID district = jmjbxx.getNowDistrict();
            if (district != null && district.getTagValue() != null) {
                sb.append(district.getTagValue());
            }

            BeanID street = jmjbxx.getNowStreet();
            if (street != null && street.getTagValue() != null) {
                sb.append(street.getTagValue());
            }

            BeanID zone = jmjbxx.getNowZone();
            if (zone != null && zone.getTagValue() != null) {
                sb.append(zone.getTagValue());
            }

            BeanID road = jmjbxx.getNowRoad();
            if (road != null && road.getTagValue() != null) {
                sb.append(road.getTagValue());
            }

            if (jmjbxx.getNowN() != null && !jmjbxx.getNowN().equals("")) {
                sb.append(jmjbxx.getNowN() + "弄（幢）");
            }

            if (jmjbxx.getNowH() != null && !jmjbxx.getNowH().equals("")) {
                sb.append(jmjbxx.getNowH() + "号");
            }

            if (jmjbxx.getNowS() != null && !jmjbxx.getNowS().equals("")) {
                sb.append(jmjbxx.getNowS() + "室");
            }
        }
        return sb.toString();
    }

}
