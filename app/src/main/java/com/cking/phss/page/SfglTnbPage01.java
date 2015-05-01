package com.cking.phss.page;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.global.Global;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

public class SfglTnbPage01 extends MyPage  {

    private static final String TAG = "SfglTnbPage01";
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    private Context mContext;
    

//    private TextView mUserNameTextView = null;
//    public TextView mUserIdTextView = null;
    
    EditText 		zrysEditText		=null;
    CalendarText 	sfrqCalendarText	=null;
    SpinnerUtil 	sffsSpinnerUtil		=null;
    CheckBox zz01CheckBox=null;
    CheckBox zz02CheckBox=null;
    CheckBox zz03CheckBox=null;
    CheckBox zz04CheckBox=null;
    CheckBox zz05CheckBox=null;
    CheckBox zz06CheckBox=null;
    CheckBox zz07CheckBox=null;
    CheckBox zz08CheckBox=null;
    CheckBox zz09CheckBox=null;
	CheckBox zz10CheckBox = null;
    CheckBox zz11CheckBox=null;
    CheckBox zz12CheckBox=null;
    CheckBox zz13CheckBox=null;
    CheckBox zz14CheckBox=null;
    CheckBox zz15CheckBox=null;    
    EditText zzEditText;
    
    private int[] viewIds = new int[] { R.id.zz02CheckBox, R.id.zz03CheckBox, R.id.zz04CheckBox,
            R.id.zz05CheckBox, R.id.zz06CheckBox, R.id.zz07CheckBox, R.id.zz08CheckBox, R.id.zz09CheckBox,
            R.id.zz10CheckBox, R.id.zz11CheckBox, R.id.zz12CheckBox, R.id.zz13CheckBox, R.id.zz14CheckBox, R.id.zz15CheckBox };
    private CheckBoxGroupUtil zzCheckBoxGroup = null;
//    private Button mMedicalButton = null;
//
//    private EditText mXueyaEditText1 = null;// 本次血压
//    private EditText mXueyaEditText2 = null;// 期望血压
//    private EditText mTizhongEditText1 = null;// 本次体重
//    private EditText mTizhongEditText2 = null;// 期望体重
//    private EditText mHeightEdit = null;// 本次身高
//    private EditText mTyzsEditText1 = null;// 本次体压指数
//    private EditText mTyzsEditText2 = null;// 期望体压指数
//    private Spinner mZdmbtdSpinner = null;// 组东脉搏跳动
//    private EditText mTzOtherEditText = null;// 体征 其他

    public SfglTnbPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglTnbPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        // init(context);
        // TODO Auto-generated constructor stub
    }

    protected void init(Context context) {
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_01_layout, this);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {   	
        zrysEditText			=(EditText) findViewById(R.id.zrysEditText);
        sfrqCalendarText	=(CalendarText) findViewById(R.id.sfrqCalendarText);
        sffsSpinnerUtil		=(SpinnerUtil) findViewById(R.id.sffsSpinnerUtil);
        zz01CheckBox			=(CheckBox) findViewById(R.id.zz01CheckBox);
        zz02CheckBox			=(CheckBox) findViewById(R.id.zz02CheckBox);
        zz03CheckBox			=(CheckBox) findViewById(R.id.zz03CheckBox);
        zz04CheckBox			=(CheckBox) findViewById(R.id.zz04CheckBox);
        zz05CheckBox			=(CheckBox) findViewById(R.id.zz05CheckBox);
        zz06CheckBox			=(CheckBox) findViewById(R.id.zz06CheckBox);
        zz07CheckBox			=(CheckBox) findViewById(R.id.zz07CheckBox);
        zz08CheckBox			=(CheckBox) findViewById(R.id.zz08CheckBox);
        zz09CheckBox			=(CheckBox) findViewById(R.id.zz09CheckBox);
        zz10CheckBox			=(CheckBox) findViewById(R.id.zz10CheckBox);
	    zz11CheckBox			=(CheckBox) findViewById(R.id.zz11CheckBox);
        zz12CheckBox			=(CheckBox) findViewById(R.id.zz12CheckBox);
        zz13CheckBox			=(CheckBox) findViewById(R.id.zz13CheckBox);
        zz14CheckBox			=(CheckBox) findViewById(R.id.zz14CheckBox);
        zz15CheckBox			=(CheckBox) findViewById(R.id.zz15CheckBox);    
        zzEditText = (EditText) findViewById(R.id.zzEditText);

        String[] ids = ResourcesFactory.listId(mContext, "tnbzz");
        zzCheckBoxGroup = new CheckBoxGroupUtil(viewIds, this, ids);

        /**
         * 下拉列表的赋值
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
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
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
               

         //症状
         String ZZCD = mSfgljl.getzZCD();
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
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
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

        // 得到症状
        String ZZCD = "1";
        if( zz01CheckBox.isChecked() )
        {
        	ZZCD = "1";
        }
        else
        {
        	ZZCD = zzCheckBoxGroup.getCheckValues("|");
        }
    	Log.e(TAG, "ZZCD="+ZZCD);    	
    	mSfgljl.setzZCD(ZZCD);
        
        //其他症状
    	//if( zz14CheckBox.isChecked() )
        {
    		String zzQT = zzEditText.getText().toString();
        	Log.e(TAG, "zzQT="+zzQT);
        	mSfgljl.setZZQT( zzQT );
        }

        return true;
    }

    private boolean isDecimal(String values) {
        Pattern pattern = Pattern.compile("[0-9]+[.]?[0-9]*");
        Matcher matcher = pattern.matcher(values);
        return matcher.matches();
    }

    private void wuCheckBoxChecked(boolean checked) {
        for (int i = 1; i < zzCheckBoxGroup.size(); i++) {
            if (checked)
                zzCheckBoxGroup.getCheckBox(i).setChecked(!checked);
            zzCheckBoxGroup.getCheckBox(i).setEnabled(!checked);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

//    class BmiAndMbtzWatcher extends DecimalsTextWatcher{
//        private boolean heigth_edit;//当前的输入框，是身高的还是体重的
//        public BmiAndMbtzWatcher(boolean heigth_edit) {
//            this.heigth_edit=heigth_edit;
//        }
//        @Override
//        public void afterTextChanged(Editable s) {
//            super.afterTextChanged(s);
//            String heightStr=mHeightEdit.getText().toString();
//            String weigthStr=mTizhongEditText1.getText().toString();
//            try {
//                if(heightStr==null||heightStr.trim().equals("")||Float.parseFloat(heightStr.trim())<=0){
//                    mTyzsEditText1.setText("");
//                    return;
//                }
//                float height=Float.parseFloat(heightStr.trim());
//                if(heigth_edit){
//                      mTizhongEditText2.setText(height-105+"");
//                }
//                if(weigthStr==null||weigthStr.trim().equals("")||Float.parseFloat(weigthStr.trim())<=0){
//                    mTyzsEditText1.setText("");
//                    return;
//                }
//                height=height/100;
//                float weigth=Float.parseFloat(weigthStr.trim());
//                //取小数点几位
//                double bmi=DecimalsTextWatcher.parsePercision(weigth/height/height);
//                mTyzsEditText1.setText(bmi+"");
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    
//    class MbBmiWatcher extends DecimalsTextWatcher{
//        @Override
//        public void afterTextChanged(Editable s) {
//            super.afterTextChanged(s);
//            String heightStr=mHeightEdit.getText().toString();
//            String weigthStr=s.toString();
//            try {
//                if(heightStr==null||heightStr.trim().equals("")||Float.parseFloat(heightStr.trim())<=0){
//                    mTyzsEditText2.setText("");
//                    return;
//                }
//                float height=Float.parseFloat(heightStr.trim());
//                if(weigthStr==null||weigthStr.trim().equals("")||Float.parseFloat(weigthStr.trim())<=0){
//                    mTyzsEditText2.setText("");
//                    return;
//                }
//                height=height/100;
//                float weigth=Float.parseFloat(weigthStr.trim());
//                //取小数点几位
//                double bmi=DecimalsTextWatcher.parsePercision(weigth/height/height);
//                mTyzsEditText2.setText(bmi+"");
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
