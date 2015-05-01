package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.sjcx.CxjkpgmxJ013;
import com.cking.phss.util.StringManager;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.page.SjcxPage03
 * 
 */
public class SjglJmcxDetailPage05 extends LinearLayout implements IDetailPage {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    // 控件
    private Button mBackBtn = null;
    /* 基本信息 */
    private TextView mNameText;// 姓名
    private TextView mSexText;// 性别
    private TextView mPaperText;// 身份证

    /* 评估信息 */
    private TextView mEvalSnText;// 评估序号
    private TextView mCheckDateText;// 检查日期
    private TextView mCheckDoc;// 检查医生

    /*健康评估*/
    private TextView mYypgText;//营养评估
    private TextView mFxysText;// 风险因素
    private TextView mSszdText;// 膳食指导
    private TextView mYdcfText;// 运动处方
    private TextView mJktsText;// 健康提示
    
    
    public SjglJmcxDetailPage05(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglJmcxDetailPage05(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_jmcx_detail_05_layout, this);

        mNameText = (TextView) findViewById(R.id.jbxx_name_text);
        mSexText = (TextView) findViewById(R.id.jbxx_sex_text);
        mPaperText = (TextView) findViewById(R.id.jbxx_paper_text);

        
        mEvalSnText = (TextView) findViewById(R.id.evalSn_text);
        mCheckDateText = (TextView) findViewById(R.id.check_date_text);
        mCheckDoc = (TextView) findViewById(R.id.visit_doc_text);

        mYypgText = (TextView) findViewById(R.id.yypg_text);
        mFxysText = (TextView) findViewById(R.id.fxys_text);
        mSszdText = (TextView) findViewById(R.id.sszd_text);
        mYdcfText = (TextView) findViewById(R.id.ydcf_text);
        mJktsText = (TextView) findViewById(R.id.jkts_text);
    }

    /**
     * 
     * @param checkSv检查序号
     */
    public void require(String checkSv) {
        setJbxx();

        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        CxjkpgmxJ013 cxjkpgmxJ013 = new CxjkpgmxJ013();
        cxjkpgmxJ013.request = new CxjkpgmxJ013.Request();
        cxjkpgmxJ013.request.residentID = jmjbxx.getResidentID();
        cxjkpgmxJ013.request.evalSn = checkSv;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(cxjkpgmxJ013);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc || listBean != null) {
                    CxjkpgmxJ013 responseCxjkpgmxJ013 = (CxjkpgmxJ013) listBean.get(0);
                    if (responseCxjkpgmxJ013 == null || responseCxjkpgmxJ013.response == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }
                    updateView(responseCxjkpgmxJ013.response);
                }
            }
        });
    }

    private void setJbxx() {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        mNameText.setText("姓名："+jmjbxx.getResidentName());
        mSexText.setText("性别："+StringManager.getSexbyCD(jmjbxx.getSexCD()));
        mPaperText.setText("身份证号："+jmjbxx.getPaperNum());
    }

    private void updateView(CxjkpgmxJ013.Response response) {
        mEvalSnText.setText(response.evalSn);
        mCheckDateText.setText(response.checkDate);
        mCheckDoc .setText(response.evalEmpName);

        mYypgText.setText(response.eval);
        mFxysText.setText(response.risk);
        mSszdText.setText(response.suggest);
        mYdcfText.setText(response.prescribe);
        mJktsText.setText(response.tips);
    }
}
