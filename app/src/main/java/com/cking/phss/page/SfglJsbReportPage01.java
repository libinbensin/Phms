package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dialog.SfglJsbBaokaDialog;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.jsb.BcjsbglkHfm01;
import com.cking.phss.dto.sfgl.jsb.DdjsbglkxxxxHfm02;
import com.cking.phss.util.AddressTextFactory;
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
public class SfglJsbReportPage01 extends MyPage  {
    // 基本信息部分
    private EditText sfzEditText;
    public EditText xmEditText;
    private CalendarText csrqCalendarText;
    private SpinnerUtil xbSpinnerUtil;
    private SpinnerUtil mzSpinnerUtil;

    private AddressText jtzzAddressText;
    private AddressText hjzzAddressText;

    private EditText lxdhEditText;
    private SpinnerUtil whcdSpinnerUtil;
    private SpinnerUtil hyzkSpinnerUtil;
    private SpinnerUtil zySpinnerUtil;
    private SpinnerUtil jtgzSpinnerUtil;
    private EditText gzdwEditText;

    // 监护人信息
    private EditText jhrxmEditText;
    private SpinnerUtil yhzgxSpinnerUtil;
    public EditText jhrdhEditText;
    public EditText jhrzzEditText;

    AddressText.MemAddress memAddressNow = new AddressText.MemAddress();
    AddressText.MemAddress memAddressReg = new AddressText.MemAddress();

    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    private SfglJsbBaokaDialog mParent;

    public SfglJsbReportPage01(Context context, Map<String, IBean> beanMap,
            SfglJsbBaokaDialog parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglJsbReportPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_bk_01_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        sfzEditText = (EditText) findViewById(R.id.sfzEditText);
        xmEditText = (EditText) findViewById(R.id.xmEditText);
        csrqCalendarText = (CalendarText) findViewById(R.id.csrqCalendarText);

        xbSpinnerUtil = (SpinnerUtil) findViewById(R.id.xbSpinnerUtil);
        mzSpinnerUtil = (SpinnerUtil) findViewById(R.id.mzSpinnerUtil);
        jtzzAddressText = (AddressText) findViewById(R.id.jtzzAddressText);
        hjzzAddressText = (AddressText) findViewById(R.id.hjzzAddressText);
        
        lxdhEditText = (EditText) findViewById(R.id.lxdhEditText);
        whcdSpinnerUtil = (SpinnerUtil) findViewById(R.id.whcdSpinnerUtil);
        hyzkSpinnerUtil = (SpinnerUtil) findViewById(R.id.hyzkSpinnerUtil);
        zySpinnerUtil = (SpinnerUtil) findViewById(R.id.zySpinnerUtil);
        jtgzSpinnerUtil = (SpinnerUtil) findViewById(R.id.jtgzSpinnerUtil);
        gzdwEditText = (EditText) findViewById(R.id.gzdwEditText);

        jhrxmEditText = (EditText) findViewById(R.id.jhrxmEditText);
        yhzgxSpinnerUtil = (SpinnerUtil) findViewById(R.id.yhzgxSpinnerUtil);
        jhrdhEditText = (EditText) findViewById(R.id.jhrdhEditText);
        jhrzzEditText = (EditText) findViewById(R.id.jhrzzEditText);
    }

    public void setValueByJmjbxx(Jmjbxx mJmjbxx) {
        sfzEditText.setText(mJmjbxx.getPaperNum());
        xmEditText.setText(mJmjbxx.getResidentName());
        xbSpinnerUtil.setSelectedPositionByValue(mJmjbxx.getSexCD());

        csrqCalendarText.setText(mJmjbxx.getBirthDay());
        lxdhEditText.setText(mJmjbxx.getSelfPhone());

        if (mJmjbxx.getFlokCD() != null) {
            mzSpinnerUtil.setSelectedPositionByValue(mJmjbxx.getFlokCD().getiD());
        }

        // 居住地址
        memAddressNow.setProvince(mJmjbxx.getNowProvince());
        memAddressNow.setCity(mJmjbxx.getNowCity());
        memAddressNow.setDistrict(mJmjbxx.getNowDistrict());
        memAddressNow.setStreet(mJmjbxx.getNowStreet());
        memAddressNow.setZone(mJmjbxx.getNowZone());
        memAddressNow.setRoad(mJmjbxx.getNowRoad());
        memAddressNow.setN(mJmjbxx.getNowN());
        memAddressNow.setH(mJmjbxx.getNowH());
        memAddressNow.setS(mJmjbxx.getNowS());
        memAddressNow.setOther(mJmjbxx.getNowOther());
        jtzzAddressText.setValue(memAddressNow);

        // 户籍地址
        // memAddressReg.setProvince(mJmjbxx.getNowProvince());
        // memAddressReg.setCity(mJmjbxx.getNowCity());
        // memAddressReg.setDistrict(mJmjbxx.getNowDistrict());
        // memAddressReg.setStreet(mJmjbxx.getNowStreet());
        // memAddressReg.setZone(mJmjbxx.getNowZone());
        // memAddressReg.setRoad(mJmjbxx.getNowRoad());
        // memAddressReg.setN(mJmjbxx.getNowN());
        // memAddressReg.setH(mJmjbxx.getNowH());
        // memAddressReg.setS(mJmjbxx.getNowS());
        // memAddressReg.setOther(mJmjbxx.getNowOther());
        // address = AddressTextFactory.formatAddress(memAddressReg);
        memAddressReg = AddressTextFactory.parseAddress(mJmjbxx.getRegAddress());
        hjzzAddressText.setValue(memAddressReg);
        
        hyzkSpinnerUtil.setSelectedPositionByValue( mJmjbxx.getMarriageCD() );
        		
        //jtgzSpinnerUtil.setSelectedPositionByValue( xx.getWorkTypeCD().getTagValue());
        		
        zySpinnerUtil.setSelectedPositionByValue( mJmjbxx.getVocationCD().getiD());
        
        whcdSpinnerUtil.setSelectedPositionByValue( mJmjbxx.getEducationCD() );
        		

        gzdwEditText.setText( mJmjbxx.getWorkUnit() );
        		
        //bxlbCheckboxGroup.setCheckByValues( xx.getMedicalCD(),"," );	
        
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

            setValueByJmjbxx(mJmjbxx);

            return;
        }
        DdjsbglkxxxxHfm02.Response response = ddjsbglkxxxxHfm02.response;
        sfzEditText.setText(response.credentialsNo);
        xmEditText.setText(response.residentName);
        csrqCalendarText.setText(response.birthDay);
        if (response.sex != null) {
            xbSpinnerUtil.setSelectedPositionByValue(response.sex.getcD());
        }
        if (response.ethnicity != null) {
            mzSpinnerUtil.setSelectedPositionByValue(response.ethnicity.getcD());
        }

        // 居住地址
        memAddressNow.setProvince(response.nowProvince);
        memAddressNow.setCity(response.nowCity);
        memAddressNow.setDistrict(response.nowDistrict);
        memAddressNow.setStreet(response.nowStreet);
        memAddressNow.setZone(response.nowCommunity);
        memAddressNow.setRoad(response.nowRoad);
        memAddressNow.setN(response.nowLane);
        memAddressNow.setH(response.nowGroup);
        memAddressNow.setS(response.nowRoom);
        memAddressNow.setOther(response.nowOther);
        jtzzAddressText.setValue(memAddressNow);

        // 户籍地址
        memAddressReg.setProvince(response.regProvince);
        memAddressReg.setCity(response.regCity);
        memAddressReg.setDistrict(response.regDistrict);
        memAddressReg.setStreet(response.regStreet);
        memAddressReg.setZone(response.regCommunity);
        memAddressReg.setRoad(response.regRoad);
        memAddressReg.setN(response.regLane);
        memAddressReg.setH(response.regGroup);
        memAddressReg.setS(response.regRoom);
        memAddressReg.setOther(response.regOther);
        hjzzAddressText.setValue(memAddressReg);
        
        lxdhEditText.setText(response.telephone);
        if (response.education != null) {
            whcdSpinnerUtil.setSelectedPositionByValue(response.education.getcD());
        }
        if (response.maritalStatus != null) {
            hyzkSpinnerUtil.setSelectedPositionByValue(response.maritalStatus.getcD());
        }
        if (response.industry != null) {
            zySpinnerUtil.setSelectedPositionByValue(response.industry.getcD());
        }
        // 设置工种
        if (response.workType != null) {
            jtgzSpinnerUtil.setSelectedPositionByValue(response.workType.getcD());
        }

        gzdwEditText.setText(response.workUnit);
        // bxlbCheckboxGroup.setCheckedByValue(response.medicalCD);

        // 监护人信息
        jhrxmEditText.setText(response.guardianName);
        yhzgxSpinnerUtil.setCheckedByBeanCD(response.relationToPatient);
        jhrdhEditText.setText(response.guardianPhone);
        jhrzzEditText.setText(response.guardianAddress);
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

        // 新增存盘
        if (response.residentID.trim().equals("")) {
            request.type = "1";
        } else {
            request.type = "2";
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
        request.sex = new BeanCD(xbSpinnerUtil.getSelectedValue(), xbSpinnerUtil.getSelectedData());
        // 出生日期
        request.birthDay = csrqCalendarText.getText().toString();
        // 身份证号
        request.credentialsNo = sfzEditText.getText().toString();
        if (request.credentialsNo.trim().equals("")) {
            mToast.setText("身份证是必填项，请填写！");
            mToast.show();
            return false;
        }
        // 工作单位
        request.workUnit = gzdwEditText.getText().toString();
        // 个人电话
        request.telephone = lxdhEditText.getText().toString();
        if (request.telephone.trim().equals("")) {
            mToast.setText("个人电话是必填项，请填写！");
            mToast.show();
            return false;
        }

        // 名族
        request.ethnicity = new BeanCD(mzSpinnerUtil.getSelectedValue(),
                mzSpinnerUtil.getSelectedData());

        // 文化程度
        request.education = new BeanCD(whcdSpinnerUtil.getSelectedValue(),
                whcdSpinnerUtil.getSelectedData());
        // 职业
        request.industry = new BeanCD(zySpinnerUtil.getSelectedValue(),
                zySpinnerUtil.getSelectedData());
        // 具体工种
        if (jtgzSpinnerUtil.getChildCount() > 0) {
            request.workType = new BeanCD(jtgzSpinnerUtil.getSelectedData(),
                    jtgzSpinnerUtil.getSelectedValue());
        }
        // 婚姻状况
        request.maritalStatus = new BeanCD(hyzkSpinnerUtil.getSelectedValue(),
                hyzkSpinnerUtil.getSelectedData());

        // ------------------------------------------------------
        // 家庭地址
        memAddressNow = jtzzAddressText.getValue();
        // 设置省份
        request.nowProvince = memAddressNow.province;

        // 设置市
        request.nowCity = memAddressNow.city;
        // 设置区/县
        request.nowDistrict = memAddressNow.district;
        // 设置街道
        request.nowStreet = memAddressNow.street;
        // 设置社区
        request.nowCommunity = memAddressNow.zone;

        // 设置路
        request.nowRoad = memAddressNow.road;
        // 弄
        request.nowLane = memAddressNow.n;
        // 号
        request.nowGroup = memAddressNow.h;
        // 室
        request.nowRoom = memAddressNow.s;
        request.nowOther = memAddressNow.other;
        
        // ------------------------------------------------------
        // 户籍地址
        memAddressReg = hjzzAddressText.getValue();
        // 设置省份
        request.regProvince = memAddressReg.province;

        // 设置市
        request.regCity = memAddressReg.city;
        // 设置区/县
        request.regDistrict = memAddressReg.district;
        // 设置街道
        request.regStreet = memAddressReg.street;
        // 设置社区
        request.regCommunity = memAddressReg.zone;

        // 设置路
        request.regRoad = memAddressReg.road;
        // 弄
        request.regLane = memAddressReg.n;
        // 号
        request.regGroup = memAddressReg.h;
        // 室
        request.regRoom = memAddressReg.s;
        // 户籍地址
        request.regOther = memAddressReg.other;

        // 监护人信息
        request.guardianName = jhrxmEditText.getText().toString();
        request.relationToPatient = yhzgxSpinnerUtil.getCheckedBeanCD();
        request.guardianPhone = jhrdhEditText.getText().toString();
        request.guardianAddress = jhrzzEditText.getText().toString();

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
