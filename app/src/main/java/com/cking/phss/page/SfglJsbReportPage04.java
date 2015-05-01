package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dialog.SfglJsbBaokaDialog;
import com.cking.phss.dto.sfgl.jsb.BcjsbglkHfm01;
import com.cking.phss.dto.sfgl.jsb.DdjsbglkxxxxHfm02;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

public class SfglJsbReportPage04 extends MyPage  {
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    
    EditText qdzsEditText = null;
	EditText zhaoshiEditText = null;
	EditText zhEditText = null;
	EditText zsEditText = null;
	EditText zswsEditText = null;
	EditText qtEditText = null;

	SpinnerUtil gsqkSpinnerUtil = null;
	SpinnerUtil jjqkSpinnerUtil = null;

	EditText zkysyjEditText = null;

	CalendarText tbrqCalendarText = null;

	EditText ysqzEditText = null;

	Button qrButton = null;
	Button fhButton = null;
	
	private SfglJsbBaokaDialog mParent;

    public SfglJsbReportPage04(Context context, Map<String, IBean> beanMap,
            SfglJsbBaokaDialog parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        // init(context);
    }

    public SfglJsbReportPage04(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglJsbReportPage04(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_bk_04_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

		qdzsEditText		= (EditText) findViewById(R.id.qdzsEditText	);
		zhaoshiEditText	= (EditText) findViewById(R.id.zhaoshiEditText);
		zhEditText			= (EditText) findViewById(R.id.zhEditText		);
		zsEditText			= (EditText) findViewById(R.id.zsEditText		);
		zswsEditText		= (EditText) findViewById(R.id.zswsEditText	);
		qtEditText			= (EditText) findViewById(R.id.qtEditText		);

		gsqkSpinnerUtil = (SpinnerUtil ) findViewById(R.id.gsqkSpinnerUtil);
		jjqkSpinnerUtil = (SpinnerUtil ) findViewById(R.id.jjqkSpinnerUtil);

		zkysyjEditText = (EditText) findViewById(R.id.zkysyjEditText);

		tbrqCalendarText = (CalendarText) findViewById(R.id.tbrqCalendarText);

		ysqzEditText = (EditText) findViewById(R.id.ysqzEditText);

		qrButton = (Button) findViewById(R.id.qrButton);
        fhButton = (Button) findViewById(R.id.fhButton);

        fhButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParent.onJsbBaokaResultListener != null) {
                	mParent.dismiss();
                	mParent.onJsbBaokaResultListener.onCancel();
                }
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if (mParent.onJsbBaokaResultListener != null) {
                	mParent.dismiss();
                	mParent.onJsbBaokaResultListener.onConfirm();
                }
            }
        });

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

        // 患者对家庭社会的影响
        qdzsEditText.setText(response.mildTrouble);
        zhaoshiEditText.setText(response.accident);
        zhEditText.setText(response.trouble);
        zsEditText.setText(response.selfWounding);
        zswsEditText.setText(response.attemptedSuicide);
        qtEditText.setText(response.influenceOther);
        if (response.lockUp != null) {
            gsqkSpinnerUtil.setSelectedPositionByValue(response.lockUp.getcD());
        }
        if (response.economic != null) {
            jjqkSpinnerUtil.setSelectedPositionByValue(response.economic.getcD());
        }
        zkysyjEditText.setText(response.doctorsAdvice);
        tbrqCalendarText.setText(response.reportDate);
        // if (response.reportDoctor != null) {
        // ysqzEditText.setText(response.reportDoctor.getTagValue());
        // }
        ysqzEditText.setText(Global.doctorName);
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

        // 患者对家庭社会的影响
        request.mildTrouble = qdzsEditText.getText().toString();
        request.accident = zhaoshiEditText.getText().toString();
        request.trouble = zhEditText.getText().toString();
        request.selfWounding = zsEditText.getText().toString();
        request.attemptedSuicide = zswsEditText.getText().toString();
        request.influenceOther = qtEditText.getText().toString();
        request.lockUp = gsqkSpinnerUtil.getCheckedBeanCD();
        request.economic = jjqkSpinnerUtil.getCheckedBeanCD();
        request.doctorsAdvice = zkysyjEditText.getText().toString();
        request.reportDate = tbrqCalendarText.getText().toString();
        request.reportDoctor = new BeanCD(Global.doctorID, Global.doctorName);

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
