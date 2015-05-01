package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.IBean;
import com.cking.phss.dto.sfgl.tnb.Bctnbglk26;
import com.cking.phss.dto.sfgl.tnb.Ddtnbglkxxxx25;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

public class SfglTnbReportPage02 extends MyPage  {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglGxyPage01";
    // 个人信息部分
    SpinnerUtil zgzddwSpinnerUtil;
    CalendarText sczdrqCalendarText;
    SpinnerUtil bkysSpinnerUtil;
    CalendarText bkrqCalendarText;
    SpinnerUtil bkdwSpinnerUtil;
    SpinnerUtil icdbmSpinnerUtil;
    SpinnerUtil tnblxSpinnerUtil;
    
    Button qkButton;

    private CheckBoxGroupUtil bfzCheckBoxGroupUtil;
    private int[] bfzViewsId = new int[] { R.id.bfz01CheckBox, R.id.bfz02CheckBox,
            R.id.bfz03CheckBox, R.id.bfz04CheckBox, R.id.bfz05CheckBox, R.id.bfz06CheckBox, };

    private CheckBoxGroupUtil wxysCheckBoxGroupUtil;
    private int[] dangerViewsId = new int[] { R.id.wxys01CheckBox, R.id.wxys02CheckBox,
            R.id.wxys03CheckBox, R.id.wxys04CheckBox, };

    EditText fmxdgEditText;

    private CheckBoxGroupUtil ytnbszCheckBoxGroupUtil;
    private int[] familyViewsId = new int[] { R.id.ytnbsz01CheckBox, R.id.ytnbsz02CheckBox,
            R.id.ytnbsz03CheckBox, R.id.ytnbsz04CheckBox, R.id.ytnbsz05CheckBox, };

    CheckBox sfsyjdxCheckBox;
    EditText bcqEditText;
    CalendarText swrqCalendarText;
    SpinnerUtil swyySpinnerUtil;
    SpinnerUtil swicd10SpinnerUtil;
    EditText swicdEditText;

    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    
    public SfglTnbReportPage02(Context context,Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglTnbReportPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        // loadArrays();
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_bk_02_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        zgzddwSpinnerUtil = (SpinnerUtil) findViewById(R.id.zgzddwSpinnerUtil);
        sczdrqCalendarText = (CalendarText) findViewById(R.id.sczdrqCalendarText);
        bkysSpinnerUtil = (SpinnerUtil) findViewById(R.id.bkysSpinnerUtil);
        bkrqCalendarText = (CalendarText) findViewById(R.id.bkrqCalendarText);
        bkdwSpinnerUtil = (SpinnerUtil) findViewById(R.id.bkdwSpinnerUtil);
        
        icdbmSpinnerUtil = (SpinnerUtil) findViewById(R.id.icdbmSpinnerUtil);
        tnblxSpinnerUtil = (SpinnerUtil) findViewById(R.id.tnblxSpinnerUtil);
        String[] ids = ResourcesFactory.listId(mContext, "tnbbkbfz");
        bfzCheckBoxGroupUtil = new CheckBoxGroupUtil(bfzViewsId, this, ids);
        ids = ResourcesFactory.listId(mContext, "tnbwxys");
        wxysCheckBoxGroupUtil = new CheckBoxGroupUtil(dangerViewsId, this, ids);
        fmxdgEditText = (EditText) findViewById(R.id.fmxdgEditText);
        ids = ResourcesFactory.listId(mContext, "ytnbsz");
        ytnbszCheckBoxGroupUtil = new CheckBoxGroupUtil(familyViewsId, this, ids);
        sfsyjdxCheckBox = (CheckBox) findViewById(R.id.sfsyjdxCheckBox);
        bcqEditText = (EditText) findViewById(R.id.bcqEditText);
        swrqCalendarText = (CalendarText) findViewById(R.id.swrqCalendarText);
        swyySpinnerUtil = (SpinnerUtil) findViewById(R.id.swyySpinnerUtil);
        swicd10SpinnerUtil = (SpinnerUtil) findViewById(R.id.swicd10SpinnerUtil);
        swicdEditText = (EditText) findViewById(R.id.swicdEditText);

        // mReportDoctorAdapter = new ArrayAdapter<String>(mContext,
        // R.layout.simple_spinner_item,
        // reportDoctorStrings);
        // mReportDoctorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bkysSpinnerUtil.setAdapter(mReportDoctorAdapter);
        //
        // mReportUnitAdapter = new ArrayAdapter<String>(mContext,
        // R.layout.simple_spinner_item,
        // reportUnitStrings);
        // mReportUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bkdwSpinnerUtil.setAdapter(mReportUnitAdapter);

        qkButton = (Button) findViewById(R.id.qkButton);
        qkButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                swrqCalendarText.setText("");
            }
        });

        // getReportDoctorFromWeb();
        // getReportUnitFromWeb();
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
        
        zgzddwSpinnerUtil.setSelectedPositionByValue(response.diagnoseUnitCD);
        sczdrqCalendarText.setText(response.firstDate);
        
        //设置报卡医生
        if (response.reportDoctor != null) {
            bkysSpinnerUtil.setSelectedPositionByValue(response.reportDoctor.getiD());
            bkysSpinnerUtil.setEnabled(false);
        }
        // for (int i = 0; i < reportDoctorValues.size(); i++) {
        // if (response.reportDoctor != null
        // && (reportDoctorValues.get(i).equals(response.reportDoctor.getiD())
        // || reportDoctorStrings
        // .get(i).equals(response.reportDoctor.getTagValue()))) {
        // bkysSpinnerUtil.setSelection(i);
        // break;
        // }
        // }
        //设置报卡时间
        bkrqCalendarText.setText(response.reportDate);
        
        //设置报卡单位
        if (response.reportUnit != null) {
            bkdwSpinnerUtil.setSelectedPositionByValue(response.reportUnit.getiD());
            bkdwSpinnerUtil.setEnabled(false);
        }
        // for (int i = 0; i < reportUnitValues.size(); i++) {
        // if (response.reportUnit != null
        // && (reportUnitValues.get(i).equals(response.reportUnit.getiD()) ||
        // reportUnitStrings
        // .get(i).equals(response.reportUnit.getTagValue()))) {
        // bkdwSpinnerUtil.setSelection(i);
        // break;
        // }
        // }
        
        //设置icd码
        if (response.icd != null) {
            icdbmSpinnerUtil.setSelectedPositionByValue(response.icd.getiD());
        }
        //糖尿病类型
        tnblxSpinnerUtil.setSelectedPositionByValue(response.diagnoseCD);
        //并发症
        bfzCheckBoxGroupUtil.setCheckedByValues(response.syndromeCD);
        //危险因素
        wxysCheckBoxGroupUtil.setCheckedByValues(response.dangerCD);
        //父母兄弟几个
        fmxdgEditText.setText(response.familysNum);
        //父母兄弟患糖尿病的
        ytnbszCheckBoxGroupUtil.setCheckedByValues(response.historyDMCD);
        // 是否是研究对象
        sfsyjdxCheckBox.setChecked(response.studyObjectFlag == 1);
        // 病存期
        bcqEditText.setText(response.diseasePeriod);
        //死亡日期
        swrqCalendarText.setText(response.deathDate);
        //死亡原因
        swyySpinnerUtil.setSelectedPositionByValue(response.deathReasonCD);
        //死亡ICd10
        if (response.deathICD10 != null) {
            swicd10SpinnerUtil.setSelectedPositionByValue(response.deathICD10.getiD());
        }
        //死亡ICD10名称
        swicdEditText.setText(response.deathICDName);
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

      //最高诊断单位
        request.diagnoseUnitCD = zgzddwSpinnerUtil.getSelectedValue();
        //首次诊断日期
        request.firstDate = sczdrqCalendarText.getText().toString();
        
        //报卡医生
        request.reportDoctor = bkysSpinnerUtil.getCheckedBeanID();
        // int index = bkysSpinnerUtil.getSelectedItemPosition();
        // if (index >= 0 && index < reportDoctorValues.size()) {
        // String id = reportDoctorValues.get(index);
        // String str = reportDoctorStrings.get(index);
        // request.reportDoctor=new BeanID(id,str);
        // }
        
        //报卡日期
        request.reportDate = bkrqCalendarText.getText().toString();
        
        //报卡单位
        request.reportUnit = bkdwSpinnerUtil.getCheckedBeanID();
        // index = bkdwSpinnerUtil.getSelectedItemPosition();
        // if (index >= 0 && index < reportUnitValues.size()) {
        // String id = reportUnitValues.get(index);
        // String str = reportUnitStrings.get(index);
        // request.reportUnit=new BeanID(id,str);
        // }
        
        //icd码
        request.icd = new BeanID(icdbmSpinnerUtil.getSelectedValue(),
                icdbmSpinnerUtil.getSelectedData());
        //糖尿病类型
        request.diagnoseCD=tnblxSpinnerUtil.getSelectedValue();
        //并发症
        request.syndromeCD=bfzCheckBoxGroupUtil.getCheckValues("|");
        //危险因素
        request.dangerCD=wxysCheckBoxGroupUtil.getCheckValues("|");
        //父母兄弟人数
        request.familysNum = fmxdgEditText.getText().toString().trim();
        // 是否是研究对象
        request.studyObjectFlag = sfsyjdxCheckBox.isChecked() ? 1 : 0;
        // 病存期
        request.diseasePeriod = bcqEditText.getText().toString();
        //父母兄弟患糖尿病的
        request.historyDMCD = ytnbszCheckBoxGroupUtil.getCheckValues("|");
        //死亡日期
        request.deathDate = swrqCalendarText.getText().toString();
        //死亡原因
        request.deathReasonCD = swyySpinnerUtil.getSelectedValue();
        //死亡ICD10
        request.deathICD10 = new BeanID(swicd10SpinnerUtil.getSelectedValue(),
                swicd10SpinnerUtil.getSelectedData());
        //死亡ICD名称
        request.deathICDName = swicdEditText.getText().toString();
        return true;
    
    }

    // public void getReportDoctorFromWeb() {
    // // 根据居委或社区得到相应的路
    // Ddbyzsyys34 ddbyzsyys34 = new Ddbyzsyys34();
    // ddbyzsyys34.request = new Ddbyzsyys34.Request();
    //
    // List<IDto> beanList = new ArrayList<IDto>();
    // beanList.add(ddbyzsyys34);
    // BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
    // @Override
    // public void onResult(List<IDto> listBean, boolean isSucc) {
    // if (isSucc) {
    // Ddbyzsyys34 responseDdbyzsyys34 = (Ddbyzsyys34) listBean.get(0);
    // if (responseDdbyzsyys34 != null && responseDdbyzsyys34.response != null
    // && responseDdbyzsyys34.response.doctorList != null
    // && responseDdbyzsyys34.response.doctorList.size() > 0) {
    // ArrayList<BeanID> responseDoctors =
    // responseDdbyzsyys34.response.doctorList;
    // if (responseDoctors != null) {
    // reportDoctorValues.clear();
    // reportDoctorStrings.clear();
    // for (BeanID doctor : responseDoctors) {
    // reportDoctorValues.add(doctor.getiD());
    // reportDoctorStrings.add(doctor.getTagValue());
    // }
    // mReportDoctorAdapter.notifyDataSetChanged();
    // }
    // }
    // }
    // }
    // });
    // }
    //
    // public void getReportUnitFromWeb() {
    // // 根据居委或社区得到相应的路
    // Ddbgdw35 ddbgdw35 = new Ddbgdw35();
    // ddbgdw35.request = new Ddbgdw35.Request();
    //
    // List<IDto> beanList = new ArrayList<IDto>();
    // beanList.add(ddbgdw35);
    // BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
    // @Override
    // public void onResult(List<IDto> listBean, boolean isSucc) {
    // if (isSucc) {
    // Ddbgdw35 responseDdbgdw35 = (Ddbgdw35) listBean.get(0);
    // if (responseDdbgdw35 != null && responseDdbgdw35.response != null
    // && responseDdbgdw35.response.reportUnitList != null
    // && responseDdbgdw35.response.reportUnitList.size() > 0) {
    // ArrayList<BeanID> responseUnits =
    // responseDdbgdw35.response.reportUnitList;
    // if (responseUnits != null) {
    // reportUnitValues.clear();
    // reportUnitStrings.clear();
    // for (BeanID unit : responseUnits) {
    // reportUnitValues.add(unit.getiD());
    // reportUnitStrings.add(unit.getTagValue());
    // }
    // mReportUnitAdapter.notifyDataSetChanged();
    // }
    // }
    // }
    // }
    // });
    // }
    //
    // private void loadArrays() {
    // reportDoctorValues = new ArrayList<String>();
    // reportDoctorStrings = new ArrayList<String>();
    // reportUnitValues = new ArrayList<String>();
    // reportUnitStrings = new ArrayList<String>();
    // }
    //
    // private ArrayAdapter<String> mReportDoctorAdapter = null;// 报卡医生的的adapter
    // private ArrayList<String> reportDoctorValues = null;
    // private ArrayList<String> reportDoctorStrings = null;
    //
    // private ArrayAdapter<String> mReportUnitAdapter = null;// 报卡医生的的adapter
    // private ArrayList<String> reportUnitValues = null;
    // private ArrayList<String> reportUnitStrings = null;
    

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
