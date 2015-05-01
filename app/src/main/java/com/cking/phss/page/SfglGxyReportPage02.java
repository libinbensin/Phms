package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.dto.sfgl.gxy.Bcgxyglk19;
import com.cking.phss.dto.sfgl.gxy.Ddgxyglkxxxx18;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

public class SfglGxyReportPage02 extends MyPage  {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglGxyPage01";
    // 个人信息部分
    private SpinnerUtil zgzddwSpinnerUtil;
    private CalendarText sczdrqCalendarText;
    private SpinnerUtil bkysSpinnerUtil;
    private CalendarText bkrqCalendarText;
    private SpinnerUtil bkdwSpinnerUtil;
    private EditText zdxy01EditText;
    private EditText zdxy02EditText;
    private SpinnerUtil xyjbSpinnerUtil;
    private SpinnerUtil gxylxSpinnerUtil;
    private EditText grsEditText;
    
    private RadioGroup gmsRadioGroup;
    private EditText gmsEditText;
    
    private CheckBoxGroupUtil jzsCheckBoxGroup;
    private int[] viewsId = new int[] { R.id.jzs01CheckBox, R.id.jzs02CheckBox,
            R.id.jzs03CheckBox, R.id.jzs04CheckBox, R.id.jzs05CheckBox };
    
    
    private EditText sgEditText;
    public EditText tzEditText;

    private EditText mbEditText;
    private EditText xlEditText;
    private EditText xy01EditText;
    private EditText xy02EditText;

    private EditText xtEditText;
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    
    public SfglGxyReportPage02(Context context,Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglGxyReportPage02(Context context, AttributeSet attrs) {
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
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_gxy_bk_02_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        zgzddwSpinnerUtil = (SpinnerUtil) findViewById(R.id.zgzddwSpinnerUtil);
        sczdrqCalendarText = (CalendarText) findViewById(R.id.sczdrqCalendarText);
        bkysSpinnerUtil = (SpinnerUtil) findViewById(R.id.bkysSpinnerUtil);
        bkrqCalendarText = (CalendarText) findViewById(R.id.bkrqCalendarText);
        bkdwSpinnerUtil = (SpinnerUtil) findViewById(R.id.bkdwSpinnerUtil);
        zdxy01EditText=(EditText)findViewById(R.id.zdxy01EditText);
        zdxy02EditText=(EditText)findViewById(R.id.zdxy02EditText);
        xyjbSpinnerUtil=(SpinnerUtil)findViewById(R.id.xyjbSpinnerUtil);
        gxylxSpinnerUtil=(SpinnerUtil)findViewById(R.id.gxylxSpinnerUtil);
        grsEditText=(EditText)findViewById(R.id.grsEditText);
        String[] ids = ResourcesFactory.listId(mContext, "gxybkjzs");
        jzsCheckBoxGroup=new CheckBoxGroupUtil(viewsId, this, ids);
        gmsRadioGroup=(RadioGroup)findViewById(R.id.gmsRadioGroup);
        gmsEditText=(EditText)findViewById(R.id.gmsEditText);
        
        sgEditText=(EditText)findViewById(R.id.sgEditText);
        tzEditText=(EditText)findViewById(R.id.tzEditText);
        mbEditText=(EditText)findViewById(R.id.mbEditText);
        xlEditText=(EditText)findViewById(R.id.xlEditText);
        xy01EditText=(EditText)findViewById(R.id.xy01EditText);
        xy02EditText=(EditText)findViewById(R.id.xy02EditText);

        xtEditText=(EditText)findViewById(R.id.xtEditText);
        
        
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
        gmsRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                switch (arg1) {
                    case R.id.gms01RadioButton:
                        gmsCheckChange(false);
                        break;
                    case R.id.gms02RadioButton:
                        gmsCheckChange(true);
                        break;
                }
            }
        });
        RadioButton gms01RadioButton = (RadioButton) findViewById(R.id.gms01RadioButton);
        gms01RadioButton.setChecked(true);
        
        
        // getReportDoctorFromWeb();
        // getReportUnitFromWeb();
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Ddgxyglkxxxx18 ddgxyglkxxxx18 = (Ddgxyglkxxxx18) beanMap
                .get(Ddgxyglkxxxx18.class.getName());
        if (ddgxyglkxxxx18 == null || ddgxyglkxxxx18.response == null) {
            mToast.setText("直接得到高血压管理卡信息为空！");
            mToast.show();
            return;
        }
        Ddgxyglkxxxx18.Response response = ddgxyglkxxxx18.response;
        
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
        zdxy01EditText.setText(response.dSBP);
        zdxy02EditText.setText(response.dDBP);
        xyjbSpinnerUtil.setSelectedPositionByValue(response.hBPLevelCD);
        gxylxSpinnerUtil.setSelectedPositionByValue(response.hBPTypeCD);
        grsEditText.setText(response.person);
        jzsCheckBoxGroup.setCheckedByValues(response.familyCD);
        if(response.hyperCD.trim().equals("1")){
            gmsRadioGroup.check(R.id.gms02RadioButton);
            gmsCheckChange(true);
            gmsEditText.setText(response.hyperDetail);
        }else{
            gmsRadioGroup.check(R.id.gms01RadioButton);
            gmsCheckChange(false);
        }
            
        
        sgEditText.setText(response.height);
        tzEditText.setText(response.weight);
        mbEditText.setText(response.pulse);
        xlEditText.setText(response.hR);
        xy01EditText.setText(response.lSBP);
        xy02EditText.setText(response.lDBP);

        xtEditText.setText(response.gLU);
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Ddgxyglkxxxx18 ddgxyglkxxxx18 = (Ddgxyglkxxxx18) beanMap.get(Ddgxyglkxxxx18.class
                .getName());
        if (ddgxyglkxxxx18 == null){
            ddgxyglkxxxx18=new Ddgxyglkxxxx18();
        }
        if(ddgxyglkxxxx18.response==null){
            ddgxyglkxxxx18.response=new Ddgxyglkxxxx18.Response();
        }
        Ddgxyglkxxxx18.Response response = ddgxyglkxxxx18.response;
        
        
        Bcgxyglk19 bcgxyglk19 = (Bcgxyglk19) beanMap.get(Bcgxyglk19.class
                .getName());
        if (bcgxyglk19 == null||bcgxyglk19.request==null){
            mToast.setText("上传出错，请重试！");
            mToast.show();
            return false;
        }
        Bcgxyglk19.Request request = bcgxyglk19.request;
        
        //最高诊断单位
        request.diagnoseUnitCD=zgzddwSpinnerUtil.getSelectedValue();
        //首次诊断日期
        request.firstDate=sczdrqCalendarText.getText().toString();
        
        //报卡医生
        request.reportDoctor = bkysSpinnerUtil.getCheckedBeanID();
        // int index = bkysSpinnerUtil.getSelectedItemPosition();
        // if (index >= 0 && index < reportDoctorValues.size()) {
        // String id = reportDoctorValues.get(index);
        // String str = reportDoctorStrings.get(index);
        // request.reportDoctor=new BeanID(id,str);
        // }
        
        //报卡日期
        request.reportDate=bkrqCalendarText.getText().toString();
        
        //报卡单位
        request.reportUnit = bkdwSpinnerUtil.getCheckedBeanID();
        // index = bkdwSpinnerUtil.getSelectedItemPosition();
        // if (index >= 0 && index < reportUnitValues.size()) {
        // String id = reportUnitValues.get(index);
        // String str = reportUnitStrings.get(index);
        // request.reportUnit=new BeanID(id,str);
        // }
        
        
        request.dSBP=zdxy01EditText.getText().toString();
        request.dDBP=zdxy02EditText.getText().toString();
        if(request.dSBP.trim().equals("")||request.dDBP.trim().equals("")){
            mToast.setText("收缩压和舒张压为必填项，请填写！");
            mToast.show();
            return false;
        }
        
        //界面中根据诊断收缩压、舒张压自动计算,1级：(收缩压≥140 and 收缩压≤159) or (舒张压≥90 and 舒张压≤99)；2级：(收缩压≥160 and 收缩压≤179) or (舒张压≥100 and 舒张压≤109)；3级：收缩压≥180 or 舒张压≥110 -->
        request.hBPLevelCD=xyjbSpinnerUtil.getSelectedValue();
        
        request.hBPTypeCD=gxylxSpinnerUtil.getSelectedValue();
        request.person=grsEditText.getText().toString();
        request.familyCD=jzsCheckBoxGroup.getCheckValues("|");
        
        if (gmsRadioGroup.getCheckedRadioButtonId() == R.id.gms02RadioButton) {
            request.hyperCD="1";
        }else{
            request.hyperCD="2";
        }
        request.hyperDetail=gmsEditText.getText().toString();
        
        
        request.height=sgEditText.getText().toString();
        request.weight=tzEditText.getText().toString();
        request.pulse=mbEditText.getText().toString();
        request.hR=xlEditText.getText().toString();
        request.lSBP=xy01EditText.getText().toString();
        request.lDBP=xy02EditText.getText().toString();
        request.gLU=xtEditText.getText().toString();
       
        return true;
    }

    
    private void gmsCheckChange(boolean checked){
        if(checked!=gmsEditText.isEnabled()){
            gmsEditText.setText("");
            gmsEditText.setEnabled(checked);
        }
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
    
    // private void loadArrays() {
    // reportDoctorValues = new ArrayList<String>();
    // reportDoctorStrings = new ArrayList<String>();
    // reportUnitValues = new ArrayList<String>();
    // reportUnitStrings = new ArrayList<String>();
    // }
    
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
