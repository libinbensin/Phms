package net.mingxing.global;

import android.view.View;

import net.mingxing.bean.DoctorInfo;
import net.mingxing.xml.bean.HospitalTag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingXing on 2015/5/3.
 * 全局类
 */
public class Global {

    /**
     * 是否已关闭程序
     * true:close false:running
     */
    public static boolean isApplicationStoped = true;

    /**
     * 当前网络状态
     */
    public static boolean isNetStateValid = false;

    /**
     * 登录状态( 默认本地登录)
     */
    public static boolean isLocalLogin = true;

    /**
     * 当前的hospital
     */
    public static  HospitalTag mHospitalTag;

    /**
     * 当前医生信息
     */
    public static DoctorInfo mDoctorInfo;






    /**
     * 网络状态相关的视图列表，当网络不好时，这些视图无效
     */
    public static List<View> globalViewList = new ArrayList<View>();

}
