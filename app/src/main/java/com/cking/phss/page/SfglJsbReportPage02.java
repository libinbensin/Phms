package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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

public class SfglJsbReportPage02 extends MyPage  {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglGxyPage01";
    // 辖区村（居）委会信息部分
    EditText cjwhmcEditText;
    EditText cjwhlxrEditText;
    EditText cjwhlxrdhEditText;
    
    // 知情同意
    SpinnerUtil zqtySpinnerUtil;
    EditText qzEditText;
    CalendarText qzsjCalendarText;
    EditText scfbnlEditText;
    
    private CheckBoxGroupUtil zzCheckBoxGroupUtil;
    private int[] zzViewsId = new int[] { R.id.zz01CheckBox, R.id.zz02CheckBox, R.id.zz03CheckBox,
            R.id.zz04CheckBox, R.id.zz05CheckBox, R.id.zz06CheckBox, R.id.zz07CheckBox,
            R.id.zz08CheckBox, R.id.zz09CheckBox, R.id.zz10CheckBox, R.id.zz11CheckBox,
            R.id.zz12CheckBox
             };
    EditText qtEditText;
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    
    public SfglJsbReportPage02(Context context,Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglJsbReportPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_bk_02_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        cjwhmcEditText = (EditText) findViewById(R.id.cjwhmcEditText);
        cjwhlxrEditText = (EditText) findViewById(R.id.cjwhlxrEditText);
        cjwhlxrdhEditText = (EditText) findViewById(R.id.cjwhlxrdhEditText);
        zqtySpinnerUtil = (SpinnerUtil) findViewById(R.id.zqtySpinnerUtil);
        qzEditText = (EditText) findViewById(R.id.qzEditText);
        qzsjCalendarText = (CalendarText) findViewById(R.id.qzsjCalendarText);
        scfbnlEditText = (EditText) findViewById(R.id.scfbnlEditText);
        qtEditText = (EditText) findViewById(R.id.qtEditText);

        zzCheckBoxGroupUtil = new CheckBoxGroupUtil(context, zzViewsId, this, "mqzz", qtEditText);
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

        // 辖区村（居）委会信息
        if (response.committeeName != null) {
            cjwhmcEditText.setText(response.committeeName);
        }
        if (response.committeeContacts != null) {
            cjwhlxrEditText.setText(response.committeeContacts);
        }
        if (response.committeePhone != null) {
            cjwhlxrdhEditText.setText(response.committeePhone);
        }

        // 知情同意
        if (response.informed != null) {
            zqtySpinnerUtil.setSelectedPositionByValue(response.informed.getcD());
        }
        if (response.signature != null) {
            qzEditText.setText(response.signature);
        }
        if (response.signatureDate != null) {
            qzsjCalendarText.setText(response.signatureDate);
        }
        if (response.onsetAge != null) {
            scfbnlEditText.setText(response.onsetAge);
        }
        // 症状
        if (response.symptoms != null) {
            zzCheckBoxGroupUtil.setCheckedByBeanCD(response.symptoms);
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

        // 辖区村（居）委会信息
        request.committeeName = cjwhmcEditText.getText().toString();
        request.committeeContacts = cjwhlxrEditText.getText().toString();
        request.committeePhone = cjwhlxrdhEditText.getText().toString();

        // 知情同意
        request.informed = zqtySpinnerUtil.getCheckedBeanCD();
        request.signature = qzEditText.getText().toString();
        request.signatureDate = qzsjCalendarText.getText().toString();
        request.onsetAge = scfbnlEditText.getText().toString();

        // 症状
        request.symptoms = zzCheckBoxGroupUtil.getCheckedBeanCD("|", qtEditText.getText()
                .toString());
        
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
