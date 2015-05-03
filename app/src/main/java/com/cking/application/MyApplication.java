package com.cking.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.file.FileHelper;
import com.cking.phss.global.Global;
import com.cking.phss.service.ListenNetStateService;
import com.cking.phss.sqlite.Account;
import com.cking.phss.sqlite.AccountBll;
import com.cking.phss.sqlite.AccountDal;
import com.cking.phss.util.AppConfigFactory;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.ImageCache;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.Session;
import com.cking.phss.util.SharedPreferencesUtil;
import com.cking.phss.util.TispToastFactory;

import net.xinhuaxing.eshow.constants.Constants;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class MyApplication extends BaseApplication {
    private static final String TAG = "MyApplication";
	private static MyApplication instance = null;
	private boolean isActive = true;// 是否在前台
	private ImageCache imageCache;
	private boolean isSdCardWriteble = true;
	private Session mSession = null;
    private Toast mToast = null;
	
    private SoundPool sp = null;
    private int hit = 0;
    private Vibrator vibrator = null;
    private Context mContext = null;
    private static Account adminAccount = new Account();
    private static String defaultLoginBean = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Body><Request OperType=\"1\" OrgCode=\"210203001010001\"><PassWord>hande</PassWord><UserN>admin</UserN></Request><Response ErrMsg=\"\"><Zones><Zone ID=\"1\">虹梅 </Zone><Zone ID=\"2\">闵行</Zone></Zones><City CD=\"\">宁波</City><Community CD=\"1\">白鹤</Community><Country CD=\"1\">中国</Country><District CD=\"\">白鹤</District><DoctorID>12</DoctorID><DoctorName>管理员</DoctorName><EmployeeNo ID=\"1\">管理</EmployeeNo><Group>1002</Group><HealthService CD=\"54265751\">先德服务中心</HealthService><Lane>1001</Lane><Other></Other><PassWord>123</PassWord><Position CD=\"\"></Position><Province CD=\"\">浙江</Province><Road CD=\"1\">涌巷</Road><Room>103</Room><Station CD=\"54265751\">先德服务中心</Station><Street CD=\"\">白鹤</Street><UserID>2</UserID></Response></Body>";
    public SharedPreferences globalSp;
    
	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public void onCreate() {
		super.onCreate();
Log.e(TAG, "init application");
		mContext = this;
        mToast = TispToastFactory.getToast(getApplicationContext(), "");
		init();
	}

	private void init() {
		instance = this;
		imageCache = new ImageCache();
		mSession = new Session();

        /**
         * 播放系统声音设置
         */
        if (sp == null) {
            sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
            // 第一个参数是最大连接数，这儿可以建立一个播放池，第二个参数是播放类型，第三个是声音质量
            hit = sp.load(MyApplication.this, R.raw.chime, 1);
        }

        /**
         * 震动设置
         */
        if (vibrator == null) {
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        }

        // 读列表配置文件
        JgxxConfigFactory.readFile(mContext);

        // 启动网络状态侦听服务
        Intent intent = new Intent();
        intent.setClass(mContext, ListenNetStateService.class);
        startService(intent);

        // 添加默认admin账户
        insertAdminAccountToDb();

        // // 测试代码
        // SqliteDataController.getIcd10List("cdicdtenjbmc");
        // SqliteDataController.getIcd10List("cdicdtenssmc");
        // SqliteDataController.getIcd10List("cdicdtenywmc");

        // 读取配置信息
        AppConfig appConfig = Global.appConfig;
        if (appConfig != null) {
            try {
                AppConfigFactory.readAppConfig(mContext, appConfig);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

    @Override
    public void initPrepare() {
        mContext = this;
        SharedPreferencesUtil.changeStorageDir(MyApplication.this, Constants.APP_XML_DIR);
        globalSp = mContext.getSharedPreferences("DefaultSharedPreferences", 0);
        // 拷贝资源文件到存储器
        copyResToStorage();

        super.initPrepare();
    }

    /**
     * 
     */
    private void copyResToStorage() {
        boolean hasCopyRes = globalSp.getBoolean("HasCopyRes", false);
        if (!hasCopyRes) {
            // 拷贝address.db -> record/
            try {
                FileHelper.copyResToStorage(mContext, R.raw.address, Constants.APP_RECORD_DIR
                        + "address.db");
                // 拷贝appconfig.xml -> xml/
                FileHelper.copyResToStorage(mContext, R.raw.appconfig, Constants.APP_XML_DIR
                        + "appconfig.xml");
                // 拷贝values.xml -> xml/
                FileHelper.copyResToStorage(mContext, R.raw.values, Constants.APP_XML_DIR
                        + "values.xml");
                // 拷贝widgets.xml -> xml/
                FileHelper.copyResToStorage(mContext, R.raw.widgets, Constants.APP_XML_DIR
                        + "widgets.xml");
                globalSp.edit().putBoolean("HasCopyRes", true).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ImageCache getImageCache() {
		return imageCache;
	}

	public static MyApplication getInstance() {
		return instance;
	}

	public boolean isSdCardWriteble() {
		return this.isSdCardWriteble;
	}

	public void setSdCardWriteble(boolean isSdCardWriteble) {
		this.isSdCardWriteble = isSdCardWriteble;
	}

	public Session getSession() {
		return mSession;
	}

    /**
     * 解码成功时播放铃音
     */
    public void tone() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sp.play(hit, (float) (0.56), (float) (0.56), 0, 0, 1);
            }
        }).start();
    }

    /**
     * 震动
     */
    public void shock() {
        // TODO Auto-generated method stub
        long[] pattern = { 0, 200 }; // OFF/ON/OFF/ON...

        vibrator.vibrate(pattern, -1);// -1不重复，非-1为从pattern的指定下标开始重复
    }

    /**
     * 插入管理员账号
     */
    private void insertAdminAccountToDb() {
        adminAccount.setAccountUUID(UUID.randomUUID().toString());
        adminAccount.setUsername("admin");
        adminAccount.setPassword("hande");
        adminAccount.setRemember(0);
        adminAccount.setStatus(1);
        adminAccount.setUserGroup(Account.ADMIN);
        adminAccount.setLastLoginDateTime(new Date().getTime());
        adminAccount.setBean(defaultLoginBean);

        Account account = AccountBll.query("admin");
        if (account == null) {
            AccountDal.add(adminAccount);
        }
    }

    @Override
    public String getWebServiceUrl() {
        return Global.webserviceUrl;
    }

    @Override
    public String getAppRootName() {
        return Constants.APP_ROOT_NAME;
    }

    @Override
    public String getTemplateXmlDir() {
        return Constants.APP_TEMPLATE_DIR;
    }

    @Override
    public String getResourcesFile() {
        return Constants.APP_XML_DIR + "values.xml";
    }

    @Override
    public String getResourcesFile2() {
        return Constants.APP_XML_DIR + "widgets.xml";
    }

    @Override
    public String getResidentUuid() {
        return getSession().getCurrentResident().getResidentUUID();
    }

    @Override
    public String getLogPath() {
        return Constants.LOG_PATH;
    }

    @Override
    public String getDatabasePath() {
        return Constants.DATABASE_FILE_PATH;
    }

    @Override
    public int getDatabaseVersion() {
        return Constants.DB_VERSION;
    }

    /**
     * @return
     */
    public int getJktjFirstVisibleIndex() {
        AppConfig appConfig = Global.appConfig;
        if (appConfig != null) {
            String mksz = appConfig.getMksz();
            if (mksz != null) {
                String[] items = { "快速体检", "普通体检", "体质辨识", "心理评估", "老年评估" };
                for (int i = 0; i < items.length; i++) {
                    if (!mksz.contains(items[i])) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * @return
     */
    public int getSfglFirstVisibleIndex() {
        AppConfig appConfig = Global.appConfig;
        if (appConfig != null) {
            String mksz = appConfig.getMksz();
            if (mksz != null) {
                String[] items = { "高血压", "糖尿病", "脑卒中", "精神病", "孕产访视", "儿童访视", "老年随访", "残疾随访" };
                for (int i = 0; i < items.length; i++) {
                    if (!mksz.contains(items[i])) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
