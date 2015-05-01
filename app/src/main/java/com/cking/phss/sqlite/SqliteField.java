/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SqliteField.java
 * classes : com.iaxure.remotecontrol.sqlite.SqliteField
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-7 上午11:40:31
 */
package com.cking.phss.sqlite;


/**
 * com.iaxure.remotecontrol.sqlite.SqliteField
 * @author Wation Haliyoo <br/>
 * create at 2012-9-7 上午11:40:31
 */
public class SqliteField {
    private static final String TAG = "SqliteField";

    /**
     * 账户信息类
     * com.cking.phss.sqlite.AccountField
     * @author Wation Haliyoo <br/>
     * create at 2012-9-7 上午11:11:24
     */
    public class AccountField {
        public static final String TB_NAME = "tb_account";
        public static final String ACCOUNT_UUID = "account_uuid";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String USERGROUP = "usergroup";
        public static final String REMEMBER = "remember";
        public static final String STATUS = "status";
        public static final String BEAN = "bean";
        public static final String LAST_LOGIN_DATETIME = "last_login_datetime";
    }

    /**
     * 睡眠曲线字段类
     * com.iaxure.remotecontrol.sqlite.SleepCurveField
     * @author Wation Haliyoo <br/>
     * create at 2012-9-7 上午11:12:51
     */
    public class SleepCurveField {
        public static final String TB_NAME = "tb_sleep_curve";
        public static final String ID = "id";
        public static final String DATA_FILE = "data_file";
        public static final String LOCATION = "location";
        public static final String START_DATE = "start_date";
        public static final String STOP_DATE = "stop_date";
    }

    /**
     * 运行配置字段类
     * com.iaxure.remotecontrol.sqlite.RunConfigField
     * @author Wation Haliyoo <br/>
     * create at 2012-9-7 上午11:15:26
     */
    public class RunConfigField {
        public static final String TB_NAME = "tb_run_config";
        public static final String LAST_USED_DEVICE_ID = "location";
        public static final String LAST_USED_SLEEP_CURVE_ID = "start_date";
    }
    
    //居民信息
    public class ResidentField{
        public static final String TB_NAME = "tb_resident";
        public static final String RESIDENT_UUID = "resident_uuid";
        public static final String RESIDENT_ID = "resident_id";
        public static final String CARD_ID = "card_id";
        public static final String RESIDENT_NAME = "resident_name";
        public static final String PAPER_NUM = "paper_num";
        public static final String DOWNLOAD_DATETIME = "download_datetime";
    }
    
    //基本信息
    public class JbxxField{
        public static final String TB_NAME = "tb_jbxx";
        public static final String RESIDENT_UUID = "resident_uuid";
        public static final String JBXX_UUID = "jbxx_uuid";
        public static final String CLASS_NAME = "class_name";
        public static final String BEAN = "bean";
        public static final String DOWNLOAD_DATETIME = "download_datetime";
    }
    
    //随访管理
    public class SfglField{
        public static final String TB_NAME = "tb_sfgl";
        public static final String RESIDENT_UUID = "resident_uuid";
        public static final String SFGL_UUID = "sfgl_uuid";
        public static final String DATETIME = "date_time";
        public static final String CLASS_NAME = "class_name";
        public static final String BEAN = "bean";
        public static final String DOWNLOAD_DATETIME = "download_datetime";
    }    

    // 数据管理
    public class SjglField {
        public static final String TB_NAME = "tb_sjgl";
        public static final String RESIDENT_UUID = "resident_uuid";
        public static final String PROJECT_UUID = "project_uuid";
        public static final String PROJECT_TYPE = "project_type";
        public static final String CLASS_NAME = "class_name";
        public static final String BEAN = "bean";
        public static final String OPER_DATETIME = "oper_datetime";
    }
    
    //健康体检
    public class JktjField{
        public static final String TB_NAME = "tb_jbtj";
        public static final String RESIDENT_UUID = "resident_uuid";
        public static final String JKTJ_UUID = "jktj_uuid";
        public static final String DATETIME = "date_time";
        public static final String CLASS_NAME = "class_name";
        public static final String BEAN = "bean";
        public static final String DOWNLOAD_DATETIME = "download_datetime";
    }
     
    //心理测试
    public class XlcsjgAndTzpsjg{
    	public static final String TB_NAME = "tb_xlcsjg_tzpsjg"; 
    	public static final String RESIDENT_UUID = "resident_uuid";
    	public static final String XLCSJG_SDS = "xlcs_sds"; 
    	public static final String XLCSJG_SAS ="xlcs_sas";
    	public static final String XLCSJG_PSQI ="xlcs_psqi";
    	public static final String XLCSJG_SAQ ="xlcs_saq";
    	public static final String XLCSJG_UCLA ="xlcs_ucla";
    	public static final String XLCSJG_GCQ ="xlcs_gcq";
    	public static final String XLCSJG_SCL90 ="xlcs_scl90";
    	public static final String XLCSJG_QLSCA ="xlcs_qlsca";
    	
    	public static final String DATE1 ="date1";
    	public static final String DATE2 ="date2";
    	public static final String DATE3 ="date3";
    	public static final String DATE4 ="date4";
    	public static final String DATE5 ="date5";
    	public static final String DATE6 ="date6";
    	public static final String DATE7 ="date7";
    	public static final String DATE8 ="date8";
    	public static final String UPDATE_DATE="update_date";
        public static final String TZJG1 = "tzjg1";
        public static final String TZJG2 = "tzjg2";
        public static final String EVALSN="evalsn";
    } 
    
    public static final  String[][] XlcsjgAndTzpsjgFields={
        {"date1","xlcs_sds"},
        {"date2","xlcs_sas"},
        {"date3","xlcs_psqi"},
        {"date4","xlcs_saq"},
        {"date5","xlcs_ucla"},
        {"date6","xlcs_gcq"},
        {"date7","xlcs_scl90"},
        {"date8","xlcs_qlsca"},
    };
    
    
    //体质判识的保存分数
    public class TzpsField{
        public static final String TB_NAME = "tb_tzps";
        public static final String RESIDENT_UUID = "resident_uuid";
        public static final String VALUE = "value";
    } 
    
    // 老年评估自理评估的保存分数
    public class LnpgField {
        public static final String TB_NAME = "tb_lnpg";
        public static final String RESIDENT_UUID = "resident_uuid";
        public static final String ZILIPG = "zilipg";
        public static final String YIYUPG = "yiyupg";
        public static final String ZHILIPG = "zhilipg";
    }

}
