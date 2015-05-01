package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.global.Global;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

public class SfglGxyPage01 extends MyPage  {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglGxyPage01";
    // 个人信息部分
//    private TextView mUserNameText;
//    public  TextView mUserIDText;
    EditText zrysEditText;
    CalendarText sfrqCalendarText;
    SpinnerUtil sffsSpinnerUtil;
    SpinnerUtil gxylxSpinnerUtil;
    private CheckBoxGroupUtil zzCheckBoxGroup;
    EditText zzEditText;
    CheckBox zz01CheckBox;

    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

//    private Button mAddButton = null;
//    private Button mEditButton = null;
//    private Button mReportButton=null;
    
    int[] viewIds = new int[] { R.id.dummyCheckBox, R.id.zz02CheckBox, R.id.zz03CheckBox,
            R.id.zz04CheckBox, R.id.zz05CheckBox, R.id.zz06CheckBox, R.id.zz07CheckBox,
            R.id.zz08CheckBox, R.id.zz09CheckBox, R.id.zz10CheckBox, R.id.zz11CheckBox,
            R.id.zz12CheckBox, R.id.zz13CheckBox, R.id.zz14CheckBox };

    public SfglGxyPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglGxyPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_gxy_01_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        zrysEditText = (EditText) findViewById(R.id.zrysEditText);
        sfrqCalendarText = (CalendarText) findViewById(R.id.sfrqCalendarText);
        sffsSpinnerUtil = (SpinnerUtil) findViewById(R.id.sffsSpinnerUtil);
        gxylxSpinnerUtil = (SpinnerUtil) findViewById(R.id.gxylxSpinnerUtil);
        zzEditText = (EditText) findViewById(R.id.zzEditText);
        zz01CheckBox = (CheckBox) findViewById(R.id.zz01CheckBox);

        String[] ids = ResourcesFactory.listId(mContext, "gxyzz");
        zzCheckBoxGroup = new CheckBoxGroupUtil(viewIds, this, ids);

        /**
         * 该页面加载进来的初始状态,症状默认选为无，其余的checkbox不可编辑等等
         */
        zz01CheckBox.setChecked(true);
        zzCheckBoxGroup.setEnabledAll(false);
        zzEditText.setEnabled(false);
        // 对于无症状checkBox的监听
        zz01CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            zzCheckBoxGroup.setCheckedAll(false);
                            zzCheckBoxGroup.setEnabledAll(false);
                            zzEditText.setText("");
                            zzEditText.setEnabled(false);
                        } else {
                            zzCheckBoxGroup.setEnabledAll(true);
                        }
                    }
                });
        zzCheckBoxGroup.getCheckBox(zzCheckBoxGroup.size() - 1)// 对于无症状checkBox的监听
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            zzEditText.setEnabled(true);
                        } else {
                            zzEditText.setText("");
                            zzEditText.setEnabled(false);
                        }
                    }
                });
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
        if (mJmjbxx == null || mSfgljl == null || mJmjbxx.getResidentName().equals("")) {
            return;
        }
        // String zrys = mSfgljl.getDutyDoctor();
        // Log.e(TAG, "zrys="+zrys);
        zrysEditText.setText(Global.doctorName);

        String sfrq = mSfgljl.getVisitDate();
        Log.e(TAG, "sfrq="+sfrq);
        sfrqCalendarText.setText(sfrq);
        
        int sffs = mSfgljl.getsFFSCD();
        Log.e(TAG, "sffs="+sffs);
        sffsSpinnerUtil.setSelectedPositionByValue(sffs);
        
        int HBPType = mSfgljl.gethBPTypeCD();
        Log.e(TAG, "HBPType="+HBPType);
        gxylxSpinnerUtil.setSelectedPositionByValue(HBPType);

         //症状
         String ZZCD = mSfgljl.getZZCD();
         Log.e(TAG, "ZZCD="+ZZCD);
         if ( ZZCD != null && !ZZCD.equals("1")) {             
            zzCheckBoxGroup.setCheckedByValues(ZZCD);
             zz01CheckBox.setChecked(false);
             
             String zzQT = mSfgljl.getZZQT();
             Log.e(TAG, "zzQT="+zzQT);
             if(zzQT !=null && !zzQT.equals("") )
             {
            	 zzEditText.setText(zzQT);
             }
         } else {
             zz01CheckBox.setChecked(true);
         }
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
        if (mSfgljl == null) {
            Log.e(TAG, "mSfgljl is null");
            return false;
        }

        String zrys = zrysEditText.getText().toString();
        Log.e(TAG, "zrys="+zrys);
        mSfgljl.setDutyDoctor(zrys);
        
        String sfrq = sfrqCalendarText.getText().toString();
        Log.e(TAG, "sfrq="+sfrq);
        mSfgljl.setVisitDate(sfrq);

        // 根据选择的位置，设置对应的类型
        int sffs = sffsSpinnerUtil.getSelectedValueInt();
        Log.e(TAG, "sffs="+sffs);
        mSfgljl.setsFFSCD(sffs);
        
        int HBPType = gxylxSpinnerUtil.getSelectedValueInt();
        Log.e(TAG, "HBPType="+HBPType);
        mSfgljl.sethBPTypeCD(HBPType);

        // 得到症状
        String ZZCD = "1";
        if( zz01CheckBox.isChecked() )
        {
        	ZZCD = "1";
        }
        else
        {
            ZZCD = zzCheckBoxGroup.getCheckValues(",");
        }
    	Log.e(TAG, "ZZCD="+ZZCD);    	
    	mSfgljl.setZZCD(ZZCD);
        
        //其他症状
    	//if( zz14CheckBox.isChecked() )
        {
    		String zzQT = zzEditText.getText().toString();
        	Log.e(TAG, "zzQT="+zzQT);
        	mSfgljl.setZZQT( zzQT );
        }
        
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
