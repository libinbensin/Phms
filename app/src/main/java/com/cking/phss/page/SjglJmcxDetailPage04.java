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
import com.cking.phss.dto.sjcx.CxxlzymxJ012;
import com.cking.phss.util.StringManager;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.page.SjcxPage03
 * 
 */
public class SjglJmcxDetailPage04 extends LinearLayout implements IDetailPage {
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

    /* 心理测试 */
    private TextView mSummuryText;// 结论
    private TextView mSuggestText;// 建议

    /* 中医体质 */
    private TextView mTcmType1Text;// 中医_体质类型1
    private TextView mTcmDescribe1Text;// 中医_体质描述1
    private TextView mTcmSuggest1Text;// 中医_体质指导建议1 
    private TextView mTcmKind1Text;// 中医_体质分型1
    
    private TextView mTcmType2Text;// 中医_体质类型2
    private TextView mTcmDescribe2Text;// 中医_体质描述2 
    private TextView mTcmSuggest2Text;// 中医_体质指导建议2
    private TextView mTcmKind2Text;// 中医_体质分型2

    public SjglJmcxDetailPage04(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglJmcxDetailPage04(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_jmcx_detail_04_layout, this);

        mNameText = (TextView) findViewById(R.id.jbxx_name_text);
        mSexText = (TextView) findViewById(R.id.jbxx_sex_text);
        mPaperText = (TextView) findViewById(R.id.jbxx_paper_text);

        
        mEvalSnText = (TextView) findViewById(R.id.evalSn_text);
        mCheckDateText = (TextView) findViewById(R.id.check_date_text);
        mCheckDoc = (TextView) findViewById(R.id.visit_doc_text);

        mSummuryText = (TextView) findViewById(R.id.summury_text);
        mSuggestText = (TextView) findViewById(R.id.suggest_text);
        
        
        mTcmType1Text = (TextView) findViewById(R.id.tcmType1_text);
        mTcmDescribe1Text = (TextView) findViewById(R.id.tcmDescribe1_text);
        mTcmSuggest1Text = (TextView) findViewById(R.id.tcmSuggest1_text);
        mTcmKind1Text = (TextView) findViewById(R.id.tcmKind1_text);
        
        mTcmType2Text = (TextView) findViewById(R.id.tcmType2_text);
        mTcmDescribe2Text = (TextView) findViewById(R.id.tcmDescribe2_text);
        mTcmSuggest2Text = (TextView) findViewById(R.id.tcmSuggest2_text);
        mTcmKind2Text = (TextView) findViewById(R.id.tcmKind2_text);
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

        CxxlzymxJ012 cxxlzymxJ012 = new CxxlzymxJ012();
        cxxlzymxJ012.request = new CxxlzymxJ012.Request();
        cxxlzymxJ012.request.residentID = jmjbxx.getResidentID();
        cxxlzymxJ012.request.evalSn = checkSv;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(cxxlzymxJ012);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc || listBean != null) {
                    CxxlzymxJ012 responseCxxlzymxJ012 = (CxxlzymxJ012) listBean.get(0);
                    if (responseCxxlzymxJ012 == null || responseCxxlzymxJ012.response == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }
                    updateView(responseCxxlzymxJ012.response);
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

    private void updateView(CxxlzymxJ012.Response response) {
        mEvalSnText.setText(response.evalSn);
        mCheckDateText.setText(response.checkDate);
        mCheckDoc .setText(response.evalEmpName);

        mSummuryText.setText(response.summury);
        mSuggestText .setText(response.suggest);
        
        
        mTcmType1Text .setText(response.tcmType1);
        mTcmDescribe1Text .setText(response.tcmDescribe1);
        mTcmSuggest1Text.setText(response.tcmSuggest1);
        mTcmKind1Text.setText(response.tcmKind1);
        
        mTcmType2Text.setText(response.tcmType2);
        mTcmDescribe2Text .setText(response.tcmDescribe2);
        mTcmSuggest2Text.setText(response.tcmSuggest2);
        mTcmKind2Text.setText(response.tcmKind2);
    }
}
