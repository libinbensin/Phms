/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Global.java
 * classes : com.cking.phss.global.Global
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-19 上午9:55:27
 */
package com.cking.phss.global;

import android.view.View;

import com.cking.phss.bean.Dzqy;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.bean.Sfgljl_cjsf;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.innner.Card;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.xml4jgxx.tags.ConfigTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.cking.phss.global.Global
 * @author Administrator <br/>
 * create at 2014-6-19 上午9:55:27
 */
public class Global {
    private static final String TAG = "Global";

    public static Jmjbxx jmjbxx = new Jmjbxx();
    public static boolean updateJmjbxx = false; // 是否已更新
    public static Jmjtxx jmjtxx = new Jmjtxx();
    public static boolean updateJmjtxx = false; // 是否已更新
    public static Jmjkxx jmjkxx = new Jmjkxx();
    public static boolean updateJmjkxx = false; // 是否已更新
    public static Jmxwxg jmxwxg = new Jmxwxg();
    public static boolean updateJmxwxg = false; // 是否已更新
    public static Dzqy dzqy = new Dzqy();
    public static boolean updateDzqy = false; // 是否已更新
    public static Jktj_kstj jktjKstj = new Jktj_kstj();
    public static Sfgljl_gxy sfgljlGxy = new Sfgljl_gxy();
    public static Sfgljl_tnb sfgljlTnb = new Sfgljl_tnb();
    public static Sfgljl_jsb sfgljlJsb = new Sfgljl_jsb();
    public static Sfgljl_lnsf sfgljlLnsf = new Sfgljl_lnsf();
    public static Sfgljl_cjsf sfgljlCjsf = new Sfgljl_cjsf();
    public static AppConfig appConfig = new AppConfig();
    
    public static boolean isLocalLogin = false; // 是否为本地登录
    
    public static ConfigTag configTag = new ConfigTag(); // 机构信息配置文件

    public static boolean isApplicationStoped = true; // 是否已关闭程序

    public static String doctorID = null;
    public static String doctorName = null;
    public static String status = null;
    public static String orgCode = null; // 机构代码
    public static String orgName = null; // 机构名称
    public static String buildCode = null; // 建档单位
    public static String buildName = null; // 建档名称
    public static String manageCode = null; // 管理代码
    public static String webserviceUrl = "http://shmed.eicp.net:8038/HandeService.asmx/HealthPADBus";
    public static String uploadKstjUrl = "http://shmed.eicp.net:8038/HandeService.asmx";
    public static String downloadKstjUrl = "http://shmed.eicp.net:8038/HandeService.asmx?op=Waistsize";
    public static String versionServiceUrl = "http://shmed.eicp.net:8038/HandeService.asmx";
    public static String dataVersionUrl = "http://shmed.eicp.net:8038/HandeService.asmx";
    public static String printHeader = "宁波市居民移动体检平台";
    public static String printFooter = "社区名称：白鹤街道社区卫生服务中心\r\n社区地址：宁波市江东区甬港南路225号\r\n电    话：0574-88222999\r\n服务热线：4006-772-882\r\n";

    public static int activePageId = 0; // 当前活动页面，主要针对各个模块

    public static HashMap<String, Object> lxReturnHashMap = new HashMap<String, Object>(); // 乐心设备返回数据
    public static boolean isNetStateValid = false; // 网络状态是否可用
    public static List<View> globalViewList = new ArrayList<View>(); // 网络状态相关的视图列表，当网络不好时，这些视图无效
    public static List<Card> cards = null; // 报卡列表
    public static String[] xlpgResults = new String[8]; // 心理评估结果列表
    public static String tjbh = null; // 体检编号


}
