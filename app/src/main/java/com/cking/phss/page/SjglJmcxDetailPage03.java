package com.cking.phss.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.dto.sfgl.tnb.Dtnbsfjlxxxx22;
import com.cking.application.MyApplication;
import com.cking.phss.util.StringManager;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.page.SjcxPage03
 * 
 */
public class SjglJmcxDetailPage03 extends LinearLayout implements IDetailPage {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    // 控件
    private Button mBackBtn = null;
    /* 基本信息 */
    private TextView mNameText;// 姓名
    private TextView mSexText;// 性别
    private TextView mPaperText;// 身份证

    /* 随访信息 */
    private TextView mVisitSn;// 随访序号
    private TextView mVisitDate;// 随访日期
    private TextView mVisitDoc;// 随访医生
    private TextView mVisitType;// 随访方式
    private TextView mNextVistDate;// 下次随访日期

    /* 基本数据 */
    private TextView mSbpText;// 收缩压
    private TextView mDbpText;// 舒张压
    private TextView mBmiText;// 体质指数
    private TextView mDmbdText;// 足动脉搏
    private TextView mSgText;// 身高
    private TextView mBctzText;// 本次体重
    private TextView mXctzText;// 下次体重
    private TextView mQttzText;// 其他体征
    private TextView mZzcdText;// 症状

    /* 生活习惯 */
    private TextView mBcxyText;// 本次吸烟
    private TextView mXcxyText;// 下次吸烟
    private TextView mBcyjText;// 本次饮酒
    private TextView mXcyjText;// 下次饮酒
    private TextView mYdzcText;// 本周运动几次
    private TextView mYdcfText;// 本周每次运动时间
    private TextView mXcYdzcText;// 下周运动几次
    private TextView mXcYdcfText;// 下周每次运动时间
    private TextView mBczslText;// 本周主食量
    private TextView mXczslText;// 下周主食量

    /* 治疗情况 */
    private TextView mXltzText;// 心理调整
    private TextView mZyxwText;// 遵医行为
    private TextView mFyycxText;// 服药依从性
    private TextView mBlfyText;// 不良反应
    private TextView mSfflText;// 此次随访分类
    private TextView mZzyyText;// 转诊原因
    private TextView mZzkbText;// 转诊可别
    private TextView mBzText;// 备注
    private TextView mFzjcText;// 辅助检查

    private TextView mKfxtText;// 空腹血糖
    private TextView mJcsjText;// 检查时间
    private TextView mXhdbText;// 糖化血红蛋白
    private TextView mDxtfyText;// 低血糖反应

    /* 用药情况 */
    private ListView mListView;

    public SjglJmcxDetailPage03(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglJmcxDetailPage03(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_jmcx_detail_03_layout, this);

        mNameText = (TextView) findViewById(R.id.jbxx_name_text);
        mSexText = (TextView) findViewById(R.id.jbxx_sex_text);
        mPaperText = (TextView) findViewById(R.id.jbxx_paper_text);

        mVisitSn = (TextView) findViewById(R.id.visit_sn_text);
        mVisitDate = (TextView) findViewById(R.id.visit_date_text);
        mVisitDoc = (TextView) findViewById(R.id.visit_doc_text);
        mVisitType = (TextView) findViewById(R.id.visit_type_text);
        mNextVistDate = (TextView) findViewById(R.id.nexe_visit_date_text);

        mSbpText = (TextView) findViewById(R.id.sbp_text);
        mDbpText = (TextView) findViewById(R.id.dbp_text);
        mBmiText = (TextView) findViewById(R.id.bmi_text);
        mDmbdText = (TextView) findViewById(R.id.dmbdcd_text);
        mSgText = (TextView) findViewById(R.id.height__text);
        mBctzText = (TextView) findViewById(R.id.bctz_text);
        mXctzText = (TextView) findViewById(R.id.xctz_text);
        mQttzText = (TextView) findViewById(R.id.qttz__text);
        mZzcdText = (TextView) findViewById(R.id.zzcd__text);

        mBcxyText = (TextView) findViewById(R.id.bcxyl_text);
        mXcxyText = (TextView) findViewById(R.id.xcxy_text);
        mBcyjText = (TextView) findViewById(R.id.bcyj_text);
        mXcyjText = (TextView) findViewById(R.id.xcyj__text);
        mYdzcText = (TextView) findViewById(R.id.ydzc__text);
        mYdcfText = (TextView) findViewById(R.id.ydcf_text);
        mXcYdzcText = (TextView) findViewById(R.id.xcydzc_text);
        mXcYdcfText = (TextView) findViewById(R.id.xcydcf__text);
        mBczslText = (TextView) findViewById(R.id.bczsl__text);
        mXczslText = (TextView) findViewById(R.id.xczsl__text);

        mXltzText = (TextView) findViewById(R.id.xltzcd_text);
        mZyxwText = (TextView) findViewById(R.id.zyxwcd_text);
        mFyycxText = (TextView) findViewById(R.id.fyyxxcd_text);
        mBlfyText = (TextView) findViewById(R.id.blfy__text);
        mSfflText = (TextView) findViewById(R.id.sffl__text);
        mZzyyText = (TextView) findViewById(R.id.zzyy_text);
        mZzkbText = (TextView) findViewById(R.id.zzkb_text);
        mBzText = (TextView) findViewById(R.id.bz_text);
        mFzjcText = (TextView) findViewById(R.id.fzjc__text);
        mKfxtText = (TextView) findViewById(R.id.kfxt_text);
        mJcsjText = (TextView) findViewById(R.id.jcsj_text);
        mXhdbText = (TextView) findViewById(R.id.xhdb_text);
        mDxtfyText = (TextView) findViewById(R.id.dxtfy_text);

        mListView = (ListView) findViewById(R.id.list_data);
    }

    /**
     * 
     * @param checkSv检查序号
     */
    public void require(String checkSv) {
        setJbxx();
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }

        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        Dtnbsfjlxxxx22 dtnbsfjlxxxx22 = new Dtnbsfjlxxxx22();
        dtnbsfjlxxxx22.request = new Dtnbsfjlxxxx22.Request();
        dtnbsfjlxxxx22.request.employeeNo = login1.response.employeeNo;
        dtnbsfjlxxxx22.request.residentID = jmjbxx.getResidentID();
        dtnbsfjlxxxx22.request.visitID = checkSv;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dtnbsfjlxxxx22);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc || listBean != null) {
                    Dtnbsfjlxxxx22 responseDtnbsfjlxxxx22 = (Dtnbsfjlxxxx22) listBean.get(0);
                    if (responseDtnbsfjlxxxx22 == null || responseDtnbsfjlxxxx22.response == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }
                    updateView(responseDtnbsfjlxxxx22.response);
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

    private void updateView(Dtnbsfjlxxxx22.Response response) {
        mVisitSn.setText(response.visitID);
        mVisitDate.setText(response.visitDate);
        mVisitDoc.setText(response.doctorName);
        mVisitType.setText(StringManager.getSffs(response.SFFSCD));// 随访方式
        mNextVistDate.setText(response.XCSF);

        mSbpText.setText(response.SBP+"");
        mDbpText.setText(response.DBP+"");
        mBmiText.setText(response.TZZS+"");
        mSgText.setText(response.BCSG+"");
        mBctzText.setText(response.BCTZ+"");
        mXctzText.setText(response.XCTZ+"");
        mQttzText.setText(response.QTTZ+"");
        mZzcdText.setText(StringManager.getTnbZz(response.ZZCD));// 症状

        mBcxyText.setText(response.BCXYL+"");
        mXcxyText.setText(response.XCXY+"");
        mBcyjText.setText(response.BCYJ+"");
        mXcyjText.setText(response.XCYJ+"");
        mYdzcText.setText(response.YDZC+"");
        mYdcfText.setText(response.YDCF+"");
        mXcYdzcText.setText(response.XCYDZC+"");
        mXcYdcfText.setText(response.XCYDCD+"");
        mBczslText.setText(response.BCZSL + "");
        mXczslText.setText(response.XCZSL + "");
        mDmbdText.setText(StringManager.getZdmb(response.DMBDCD));//足动脉搏

        mXltzText.setText(StringManager.getXltz(response.XLTZCD));// 心理调整
        mZyxwText.setText(StringManager.getXltz(response.ZYXWCD));// 遵义行为
        mFyycxText.setText(StringManager.getFyycx(response.FYYCXCD));// 服药依从性
        mBlfyText.setText(response.FYQK);
        mSfflText.setText(StringManager.getSffl(response.SFFLCD));// 随访分类
        mZzyyText.setText(response.ZZYY);
        mZzkbText.setText(response.ZZKB);
        mBzText.setText(response.BZ);
        mFzjcText.setText(response.QTJC);
        mKfxtText.setText(response.KFXT+"");
        mJcsjText.setText(response.JCSJ);
        mXhdbText .setText(response.XHDB+"");
        mDxtfyText .setText(StringManager.getDxtfy(response.DXTFYCD));//低血糖反应
        

        if (response.medicineUse == null || response.medicineUse.size() <= 0) {
            mListView.setAdapter(null);
            return;
        }

        mListView = (ListView) findViewById(R.id.list_data);

        List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
        for (int i = 0; i < response.medicineUse.size(); i++) {
            if (response.medicineUse.get(i) == null)
                continue;
            MedicineUse m = response.medicineUse.get(i);
            Map<String, String> map = new HashMap<String, String>();
            if (m.medicine == null) {
                continue;
            }

            map.put("name", m.medicine.getTagValue());
            map.put("dosage", m.dosage);
            if (m.medicineUnit == null) {
                map.put("medicineUnit", "");
            } else {
                map.put("medicineUnit", m.medicineUnit.getTagValue());
            }

            if (m.usage == null) {
                map.put("usage", "");
            } else {
                map.put("usage", m.usage.getTagValue());
            }

            if (m.way == null) {
                map.put("way", "");
            } else {
                map.put("way", m.way.getTagValue());
            }
            listMaps.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(mContext, listMaps,
                R.layout.list_sjcx_page03_item, new String[] { "name", "dosage", "medicineUnit",
                        "usage", "way" }, new int[] { R.id.name_text, R.id.dosage_text,
                        R.id.unit_text, R.id.usage_text, R.id.way_text });
        mListView.setAdapter(adapter);
        mListView.invalidate();
    }
}
