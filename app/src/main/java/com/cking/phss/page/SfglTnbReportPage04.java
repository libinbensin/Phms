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
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.IBean;
import com.cking.phss.dialog.SfglTnbBaokaDialog;
import com.cking.phss.dto.sfgl.tnb.Bctnbglk26;
import com.cking.phss.dto.sfgl.tnb.Ddtnbglkxxxx25;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;

public class SfglTnbReportPage04 extends MyPage  {
    EditText ldlcEditText;
    EditText gyszEditText;
    EditText nwldbEditText;
    EditText yhxhdbEditText;
    EditText bzEditText;
    
    Button qrButton;
    Button fhButton;
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    
    private SfglTnbBaokaDialog mParent;

    public SfglTnbReportPage04(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglTnbReportPage04(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }
    
    public SfglTnbReportPage04(Context context, Map<String, IBean> beanMap, SfglTnbBaokaDialog parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        // init(context);
    }    

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_bk_04_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        ldlcEditText = (EditText) findViewById(R.id.ldlcEditText);
        gyszEditText = (EditText) findViewById(R.id.gyszEditText);
        nwldbEditText = (EditText) findViewById(R.id.nwldbEditText);
        yhxhdbEditText = (EditText) findViewById(R.id.yhxhdbEditText);
        bzEditText = (EditText) findViewById(R.id.bzEditText);
        
        qrButton = (Button) findViewById(R.id.qrButton);
        fhButton = (Button) findViewById(R.id.fhButton);

        fhButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParent.onTnbBaokaResultListener != null) {
                	mParent.dismiss();
                	mParent.onTnbBaokaResultListener.onCancel();
                }
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParent.onTnbBaokaResultListener != null) {
                	mParent.dismiss();
                	mParent.onTnbBaokaResultListener.onConfirm();
                }
            }
        });
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Ddtnbglkxxxx25 ddtnbglkxxxx25 = (Ddtnbglkxxxx25) beanMap
                .get(Ddtnbglkxxxx25.class.getName());
        if (ddtnbglkxxxx25 == null || ddtnbglkxxxx25.response == null) {
            mToast.setText("直接得到糖尿病管理卡信息为空！");
            mToast.show();
            return;
        }
        Ddtnbglkxxxx25.Response response = ddtnbglkxxxx25.response;
        
        ldlcEditText.setText(response.ldlc);
        gyszEditText.setText(response.tg);
        nwldbEditText.setText(response.mAlb);
        yhxhdbEditText.setText(response.hbAlc);
        bzEditText.setText(response.memo);
    
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Ddtnbglkxxxx25 ddtnbglkxxxx25 = (Ddtnbglkxxxx25) beanMap.get(Ddtnbglkxxxx25.class
                .getName());
        if (ddtnbglkxxxx25 == null){
            ddtnbglkxxxx25=new Ddtnbglkxxxx25();
        }
        if(ddtnbglkxxxx25.response==null){
            ddtnbglkxxxx25.response=new Ddtnbglkxxxx25.Response();
        }
        Ddtnbglkxxxx25.Response response = ddtnbglkxxxx25.response;
        
        Bctnbglk26 bctnbglk26 = (Bctnbglk26) beanMap.get(Bctnbglk26.class
                .getName());
        if (bctnbglk26 == null||bctnbglk26.request==null){
            mToast.setText("上传出错，请重试！");
            mToast.show();
            return false;
        }
        Bctnbglk26.Request request = bctnbglk26.request;
     
        request.ldlc = ldlcEditText.getText().toString();
        request.tg = gyszEditText.getText().toString();
        request.mAlb = nwldbEditText.getText().toString();
        request.hbAlc = yhxhdbEditText.getText().toString();
        request.memo = bzEditText.getText().toString();
        response.reportDoctor = new BeanID(Global.doctorID, Global.doctorName);
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
