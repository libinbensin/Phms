package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.sfgl.jsb.BcjsbglkHfm01;
import com.cking.phss.dto.sfgl.jsb.DdjsbglkxxxxHfm02;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

public class SfglJsbReportPage03 extends MyPage  {
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    
    SpinnerUtil mzSpinnerUtil = null;
    CalendarText sckjsbyzlsjCalendarText = null;
    EditText jwjszkzycsEditText = null;

    private CheckBoxGroupUtil zdCheckBoxGroupUtil;
    private int[] zdViewsId = new int[] { R.id.zd01CheckBox, R.id.zd02CheckBox, R.id.zd03CheckBox,
            R.id.zd04CheckBox, R.id.zd05CheckBox, R.id.zd06CheckBox, R.id.zd07CheckBox };
    CheckBox zd01CheckBox = null;
    CheckBox zd02CheckBox = null;
    CheckBox zd03CheckBox = null;
    CheckBox zd04CheckBox = null;
    CheckBox zd05CheckBox = null;
    CheckBox zd06CheckBox = null;
    CheckBox zd07CheckBox = null;
    
    EditText qtEditText = null;
    EditText qzzyEditText = null;
    CalendarText qzsjCalendarText = null;
    SpinnerUtil zjyczlxgSpinnerUtil = null;
    
    public SfglJsbReportPage03(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglJsbReportPage03(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_bk_03_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
    	
    	mzSpinnerUtil = (SpinnerUtil) findViewById(R.id.mzSpinnerUtil);
        sckjsbyzlsjCalendarText = (CalendarText) findViewById(R.id.sckjsbyzlsjCalendarText);
        jwjszkzycsEditText = (EditText) findViewById(R.id.jwjszkzycsEditText);
        
        zd01CheckBox = (CheckBox) findViewById(R.id.zd01CheckBox);
        zd02CheckBox = (CheckBox) findViewById(R.id.zd02CheckBox);
        zd03CheckBox = (CheckBox) findViewById(R.id.zd03CheckBox);
        zd04CheckBox = (CheckBox) findViewById(R.id.zd04CheckBox);
        zd05CheckBox = (CheckBox) findViewById(R.id.zd05CheckBox);
        zd06CheckBox = (CheckBox) findViewById(R.id.zd06CheckBox);
        zd07CheckBox = (CheckBox) findViewById(R.id.zd07CheckBox);

        qtEditText = (EditText) findViewById(R.id.qtEditText);

        zdCheckBoxGroupUtil = new CheckBoxGroupUtil(context, zdViewsId, this, "jsbzd", qtEditText);
        
        qzzyEditText = (EditText) findViewById(R.id.qzzyEditText);
        qzsjCalendarText = (CalendarText) findViewById(R.id.qzsjCalendarText);
        zjyczlxgSpinnerUtil = (SpinnerUtil) findViewById(R.id.zjyczlxgSpinnerUtil);
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            return;
        }
        DdjsbglkxxxxHfm02 ddjsbglkxxxxHfm02 = (DdjsbglkxxxxHfm02) beanMap
                .get(DdjsbglkxxxxHfm02.class.getName());
        if (ddjsbglkxxxxHfm02 == null || ddjsbglkxxxxHfm02.response == null) {
            mToast.setText("直接得到精神病管理卡信息为空！");
            mToast.show();
            return;
        }
        DdjsbglkxxxxHfm02.Response response = ddjsbglkxxxxHfm02.response;

        // 既往治疗情况
        if (response.outpatientTreatment != null) {
            mzSpinnerUtil.setSelectedPositionByValue(response.outpatientTreatment.getcD());
        }
        sckjsbyzlsjCalendarText.setText(response.firstTreatDate);
        jwjszkzycsEditText.setText(response.hospitalTimes);
        
        // 目前诊断情况
        zdCheckBoxGroupUtil.setCheckedByBeanCD(response.diagnosis);
        
        qzzyEditText.setText(response.diagnosisHospital);
        qzsjCalendarText.setText(response.diagnosisDate);
        if (response.lastEffect != null) {
            zjyczlxgSpinnerUtil.setSelectedPositionByValue(response.lastEffect.getcD());
        }
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        DdjsbglkxxxxHfm02 ddjsbglkxxxxHfm02 = (DdjsbglkxxxxHfm02) beanMap
                .get(DdjsbglkxxxxHfm02.class.getName());
        if (ddjsbglkxxxxHfm02 == null) {
            ddjsbglkxxxxHfm02 = new DdjsbglkxxxxHfm02();
        }
        if (ddjsbglkxxxxHfm02.response == null) {
            ddjsbglkxxxxHfm02.response = new DdjsbglkxxxxHfm02.Response();
        }
        DdjsbglkxxxxHfm02.Response response = ddjsbglkxxxxHfm02.response;

        BcjsbglkHfm01 bcjsbglkHfm01 = (BcjsbglkHfm01) beanMap.get(BcjsbglkHfm01.class.getName());
        if (bcjsbglkHfm01 == null || bcjsbglkHfm01.request == null) {
            mToast.setText("上传出错，请重试！");
            mToast.show();
            return false;
        }
        BcjsbglkHfm01.Request request = bcjsbglkHfm01.request;

        // 既往治疗情况
        request.outpatientTreatment = mzSpinnerUtil.getCheckedBeanCD();
        request.firstTreatDate = sckjsbyzlsjCalendarText.getText().toString();
        request.hospitalTimes = jwjszkzycsEditText.getText().toString();

        // 目前诊断情况
        request.diagnosis = zdCheckBoxGroupUtil.getCheckedBeanCD("|", qtEditText.getText()
                .toString());

        request.diagnosisHospital = qzzyEditText.getText().toString();
        request.diagnosisDate = qzsjCalendarText.getText().toString();
        request.lastEffect = zjyczlxgSpinnerUtil.getCheckedBeanCD();

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
