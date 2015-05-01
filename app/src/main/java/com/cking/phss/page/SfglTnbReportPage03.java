package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.dto.sfgl.tnb.Bctnbglk26;
import com.cking.phss.dto.sfgl.tnb.Ddtnbglkxxxx25;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;

public class SfglTnbReportPage03 extends MyPage  {
    private EditText sgEditText;
    private EditText tzEditText;
    private EditText bmiEditText;
    private EditText kfxtzEditText;
    private EditText sjxtzEditText;
    private EditText ogttsyEditText;

    private EditText zdgcEditText;
    private EditText hdlcEditText;

    private CheckBoxGroupUtil zslcbxCheckBoxGroupUtil;
    private int[] clinicalIds = new int[] { R.id.zslcbx01CheckBox, R.id.zslcbx02CheckBox,
            R.id.zslcbx03CheckBox, R.id.zslcbx04CheckBox, R.id.zslcbx05CheckBox,
            R.id.zslcbx06CheckBox, R.id.zslcbx07CheckBox, R.id.zslcbx08CheckBox,
            R.id.zslcbx09CheckBox, R.id.zslcbx10CheckBox, R.id.zslcbx11CheckBox, };
    private EditText qtlczzEditText;
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    public SfglTnbReportPage03(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglTnbReportPage03(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_bk_03_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        String[] ids = ResourcesFactory.listId(mContext, "zslcbx");
        zslcbxCheckBoxGroupUtil = new CheckBoxGroupUtil(clinicalIds, this, ids);
        qtlczzEditText = (EditText) findViewById(R.id.qtlczzEditText);
        
        sgEditText = (EditText) findViewById(R.id.sgEditText);
        tzEditText = (EditText) findViewById(R.id.tzEditText);
        bmiEditText = (EditText) findViewById(R.id.bmiEditText);
        kfxtzEditText = (EditText) findViewById(R.id.kfxtzEditText);
        sjxtzEditText = (EditText) findViewById(R.id.sjxtzEditText);
        ogttsyEditText = (EditText) findViewById(R.id.ogttsyEditText);
        
        zdgcEditText = (EditText) findViewById(R.id.zdgcEditText);
        hdlcEditText = (EditText) findViewById(R.id.hdlcEditText);
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
        
        zslcbxCheckBoxGroupUtil.setCheckedByValues(response.clinicalCD);
        qtlczzEditText.setText(response.clinicalOther);
        
        sgEditText.setText(response.height);
        tzEditText.setText(response.weight);
        bmiEditText.setText(response.bmi);
        kfxtzEditText.setText(response.fpg);
        sjxtzEditText.setText(response.randGLU);
        ogttsyEditText.setText(response.ogtt);

        zdgcEditText.setText(response.cho);
        hdlcEditText.setText(response.hdlc);
    
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
     
        request.clinicalCD=zslcbxCheckBoxGroupUtil.getCheckValues("|");
        request.clinicalOther = qtlczzEditText.getText().toString();
        request.height = sgEditText.getText().toString();
        request.weight = tzEditText.getText().toString();
        request.bmi = bmiEditText.getText().toString();
        request.fpg = kfxtzEditText.getText().toString();
        request.randGLU = sjxtzEditText.getText().toString();
        request.ogtt = ogttsyEditText.getText().toString();
        request.cho = zdgcEditText.getText().toString();
        request.hdlc = hdlcEditText.getText().toString();
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
