package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dialog.SfglTnbBaokaDialog;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.tnb.Bctnbglk26;
import com.cking.phss.dto.sfgl.tnb.Ddtnbglkxxxx25;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.AddressText;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 因为高血压报卡和糖尿病报卡一模一样，所以使用同一个页面
 * 
 * @author taowencong
 * 
 */
public class SfglTnbReportPage01 extends MyPage  {
	private static final String TAG = "SfglTnbReportPage01";
    // 个人信息部分
    private EditText sfzEditText;
    public EditText xmEditText;
    private CalendarText csrqCalendarText;
    private SpinnerUtil xbSpinnerUtil;
    private SpinnerUtil mzSpinnerUtil;

    private AddressText jtzzAddressText;

    private EditText hjdzEditText;
    private EditText lxdhEditText;
    private SpinnerUtil whcdSpinnerUtil;
    private SpinnerUtil hyzkSpinnerUtil;
    private SpinnerUtil zySpinnerUtil;
    private SpinnerUtil jtgzSpinnerUtil;
    private EditText gzdwEditText;

    AddressText.MemAddress memAddress = new AddressText.MemAddress();
    
    private CheckBoxGroupUtil bxlbCheckboxGroup;//保险类别的group
    private int[] bxlxCheckBoxIds = new int[] { R.id.bxlx01CheckBox, R.id.bxlx02CheckBox,
            R.id.bxlx03CheckBox, R.id.bxlx04CheckBox, R.id.bxlx05CheckBox, R.id.bxlx06CheckBox,
            R.id.bxlx07CheckBox, R.id.bxlx08CheckBox, R.id.bxlx09CheckBox, R.id.bxlx10CheckBox,
            R.id.bxlx11CheckBox, R.id.bxlx12CheckBox, R.id.bxlx13CheckBox };

    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    private SfglTnbBaokaDialog mParent;

    public SfglTnbReportPage01(Context context, Map<String, IBean> beanMap, SfglTnbBaokaDialog parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglTnbReportPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_bk_01_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        sfzEditText = (EditText) findViewById(R.id.sfzEditText);
        xmEditText = (EditText) findViewById(R.id.xmEditText);
        csrqCalendarText = (CalendarText) findViewById(R.id.csrqCalendarText);

        xbSpinnerUtil = (SpinnerUtil) findViewById(R.id.xbSpinnerUtil);
        mzSpinnerUtil = (SpinnerUtil) findViewById(R.id.mzSpinnerUtil);
        jtzzAddressText = (AddressText) findViewById(R.id.jtzzAddressText);
        
        lxdhEditText = (EditText) findViewById(R.id.lxdhEditText);
        whcdSpinnerUtil = (SpinnerUtil) findViewById(R.id.whcdSpinnerUtil);
        hyzkSpinnerUtil = (SpinnerUtil) findViewById(R.id.hyzkSpinnerUtil);
        zySpinnerUtil = (SpinnerUtil) findViewById(R.id.zySpinnerUtil);
        jtgzSpinnerUtil = (SpinnerUtil) findViewById(R.id.jtgzSpinnerUtil);
        gzdwEditText = (EditText) findViewById(R.id.gzdwEditText);

        hjdzEditText = (EditText) findViewById(R.id.hjdzEditText);
        
        //保险类别
        bxlbCheckboxGroup = new CheckBoxGroupUtil(mContext, bxlxCheckBoxIds, viewGroup, "bxlb");
        
        zySpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String namePrefix = "zy";
                String name = "";
                String suffix = zySpinnerUtil.getSelectedValue();
                name = namePrefix + suffix;
                jtgzSpinnerUtil.setName(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
    
    public void setValueByJmjbxx(Jmjbxx mJmjbxx) {
        sfzEditText.setText(mJmjbxx.getPaperNum());
        xmEditText.setText(mJmjbxx.getResidentName());
        xbSpinnerUtil.setSelectedPositionByValue(mJmjbxx.getSexCD());
        
        csrqCalendarText.setText(mJmjbxx.getBirthDay());
        lxdhEditText.setText(mJmjbxx.getSelfPhone());
        
        if (mJmjbxx.getFlokCD() != null)
        {
            mzSpinnerUtil.setSelectedPositionByValue(mJmjbxx.getFlokCD().getiD());
        }

        memAddress.setProvince(mJmjbxx.getNowProvince());
        memAddress.setCity(mJmjbxx.getNowCity());
        memAddress.setDistrict(mJmjbxx.getNowDistrict());
        memAddress.setStreet(mJmjbxx.getNowStreet());
        memAddress.setZone(mJmjbxx.getNowZone());
        memAddress.setRoad(mJmjbxx.getNowRoad());
        memAddress.setN(mJmjbxx.getNowN());
        memAddress.setH(mJmjbxx.getNowH());
        memAddress.setS(mJmjbxx.getNowS());
        memAddress.setOther(mJmjbxx.getNowOther());
        jtzzAddressText.setValue(memAddress);
        
        hjdzEditText.setText(mJmjbxx.getRegDetail());
        hjdzEditText.setText(mJmjbxx.getRegAddress());
        
        hyzkSpinnerUtil.setSelectedPositionByValue(mJmjbxx.getMarriageCD());
        		
        //jtgzSpinnerUtil.setSelectedPositionByValue( xx.getWorkTypeCD().getTagValue());
        		
        zySpinnerUtil.setSelectedPositionByValue(mJmjbxx.getVocationCD().getiD());
        
        whcdSpinnerUtil.setSelectedPositionByValue(mJmjbxx.getEducationCD());
        		

        gzdwEditText.setText(mJmjbxx.getWorkUnit());
        		
        //bxlbCheckboxGroup.setCheckByValues( xx.getMedicalCD(),"," );	
        
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            return;
        }
		
        Ddtnbglkxxxx25 ddtnbglkxxxx25 = (Ddtnbglkxxxx25) beanMap
                .get(Ddtnbglkxxxx25.class.getName());
        if (ddtnbglkxxxx25 == null || ddtnbglkxxxx25.response == null) {
            mToast.setText("直接得到糖尿病管理卡信息为空！");
            mToast.show();
            
            setValueByJmjbxx(mJmjbxx);
            
            return;
        }
        Ddtnbglkxxxx25.Response response = ddtnbglkxxxx25.response;
        sfzEditText.setText(response.paperNum);
        xmEditText.setText(response.residentName);
        csrqCalendarText.setText(response.birthDay);
        xbSpinnerUtil.setSelectedPositionByValue(response.sexCD);
        if (response.folkCD != null) {
            mzSpinnerUtil.setSelectedPositionByValue(response.folkCD.getiD());
        }

        memAddress.setProvince(mJmjbxx.getNowProvince());
        memAddress.setCity(mJmjbxx.getNowCity());
        memAddress.setDistrict(mJmjbxx.getNowDistrict());
        memAddress.setStreet(mJmjbxx.getNowStreet());
        memAddress.setZone(mJmjbxx.getNowZone());
        memAddress.setRoad(mJmjbxx.getNowRoad());
        memAddress.setN(mJmjbxx.getNowN());
        memAddress.setH(mJmjbxx.getNowH());
        memAddress.setS(mJmjbxx.getNowS());
        memAddress.setOther(mJmjbxx.getNowOther());
        jtzzAddressText.setValue(memAddress);
        
        hjdzEditText.setText(response.regDetail);
        
        lxdhEditText.setText(response.selfPhone);
        whcdSpinnerUtil.setSelectedPositionByValue(response.educationCD);
        hyzkSpinnerUtil.setSelectedPositionByValue(response.marriageCD);
        if (response.vocationCD != null) {
            zySpinnerUtil.setSelectedPositionByValue(response.vocationCD.getiD());
        }
        // 设置工种
        if (response.workTypeCD != null) {
            jtgzSpinnerUtil.setSelectedPositionByValue(response.workTypeCD.getTagValue());
        }

        gzdwEditText.setText(response.workUnit);
        bxlbCheckboxGroup.setCheckedByValues(response.medicalCD);
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

        // 新增存盘
        if (response.residentID.trim().equals("")) {
            request.type = 1;
        } else {
            request.type = 2;
        }
        // UserID
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (response.userID.trim().equals("")) {
            request.userID = login1.response.userID;
        } else {
            request.userID = response.userID;
        }

        // 居民ID
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (response.residentID.trim().equals("")) {
            request.residentID = jmjbxx.getResidentID();
        } else {
            request.residentID = response.residentID;
        }

        // 居民姓名
        request.residentName = xmEditText.getText().toString();
        if (request.residentName.trim().equals("")) {
            mToast.setText("姓名是必填项，请填写！");
            mToast.show();
            return false;
        }
        // 性别
        request.sexCD = xbSpinnerUtil.getSelectedValue();
        // 出生日期
        request.birthDay = csrqCalendarText.getText().toString();
        // 身份证号
        request.paperNum = sfzEditText.getText().toString();
        if (request.paperNum.trim().equals("")) {
            mToast.setText("身份证是必填项，请填写！");
            mToast.show();
            return false;
        }
        // 工作单位
        request.workUnit = gzdwEditText.getText().toString();
        // 个人电话
        request.selfPhone = lxdhEditText.getText().toString();
        if (request.selfPhone.trim().equals("")) {
            mToast.setText("个人电话是必填项，请填写！");
            mToast.show();
            return false;
        }

        // 名族
        request.folkCD = mzSpinnerUtil.getCheckedBeanID();

        // 文化程度
        request.educationCD = whcdSpinnerUtil.getSelectedValue();
        // 职业
        request.vocationCD = zySpinnerUtil.getCheckedBeanID();
        // 具体工种
        if (jtgzSpinnerUtil.getChildCount() > 0) {
            request.workTypeCD = new BeanID(jtgzSpinnerUtil.getSelectedValue(),
                    jtgzSpinnerUtil.getSelectedData());
        }
        // 婚姻状况
        request.marriageCD = hyzkSpinnerUtil.getSelectedValue();
        // 医疗费用支付方式
        request.medicalCD = bxlbCheckboxGroup.getCheckValues(",");

        // ------------------------------------------------------
        // 家庭地址
        memAddress = jtzzAddressText.getValue();
        // 设置省份
        request.nowProvince = memAddress.province;

        // 设置市
        request.nowCity = memAddress.city;
        // 设置区/县
        request.nowDistrict = memAddress.district;
        // 设置街道
        request.nowStreet = memAddress.street;
        // 设置社区
        request.nowZone = memAddress.zone;

        // 设置路
        request.nowRoad = memAddress.road;
        // 弄
        request.nowN = memAddress.n;
        // 号
        request.nowH = memAddress.h;
        // 室
        request.nowS = memAddress.s;
        request.nowOther = memAddress.other;
        request.nowDetail = jtzzAddressText.getText().toString();
        
        // ------------------------------------------------------
        // 户籍地址
        // 设置省份
        request.regProvince = memAddress.province;

        // 设置市
        request.regCity = memAddress.city;
        // 设置区/县
        request.regDistrict = memAddress.district;
        // 设置街道
        request.regStreet = memAddress.street;
        // 设置社区
        request.regZone = memAddress.zone;

        // 设置路
        request.regRoad = memAddress.road;
        // 弄
        request.regN = memAddress.n;
        // 号
        request.regH = memAddress.h;
        // 室
        request.regS = memAddress.s;
        request.regOther = memAddress.other;
        // 户籍地址
        request.regDetail = hjdzEditText.getText().toString();

        return true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
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
