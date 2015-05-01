/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.global.Global;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.AddressText;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 基本信息第1页 com.cking.phss.view.JbxxPage01
 * 
 * @author Administrator <br/>
 */
public class JbxxPage01 extends LinearLayout implements IPage {
    private static final String TAG = "JbxxPage01";
    private Context mContext = null;

    /**
     * 第一页控件
     */
    public SpinnerUtil mSexSpinner = null;
    public SpinnerUtil mRelationSpinner = null;
    public CalendarText mBirthdayText = null;
    private EditText mWorkUnitEdit = null;
    private EditText mSelfPhoneEdit = null;
    public SpinnerUtil mFolkSpinner = null;
    private EditText mRelNameEdit = null;
    private EditText mRelPhoneEdit = null;
    public SpinnerUtil mEducbgSpinner = null;
    private SpinnerUtil mLiveSpinner = null;
    private SpinnerUtil mRegTypeSpinner = null;
    private SpinnerUtil mMarriageSpinner = null;
    private SpinnerUtil mBloodSpinner = null;
    private SpinnerUtil mRHSpinner = null;
    private SpinnerUtil mVocaSpinner = null;
    private SpinnerUtil mNotionalspinner = null;
    private SpinnerUtil mQiuzhuspinner = null;
    private EditText mEmailEdit = null;
    private EditText mYzbmEdit = null;
    private SpinnerUtil mZjlxSpinner = null;
    public EditText mRegAddressEdit = null;
    
    EditText gldw_edit;
    CalendarText jdrq_text;
    AddressText jtzz_edit;
    EditText qtEditText;
    
    private CheckBoxGroupUtil checkBoxGroup;
    private int[] viewIds=new int[]{R.id.checkbox_sangou,R.id.checkbox_duju,R.id.checkbox_jqzy,R.id.checkbox_znfj,
            R.id.checkbox_sqqr,R.id.checkbox_qt};

    private Toast mToast = null;

    private Map<String, IBean> beanMap = null;

    /**
     * 动态获取生成、市的ID和名称
     */
    AddressText.MemAddress memAddress = new AddressText.MemAddress();

    /**
     * @param mContext
     */
    public JbxxPage01(Context mContext, Map<String, IBean> beanMap) {
        super(mContext);
        this.beanMap = beanMap;
        init(mContext);
        // initSpinner();
    }

    /**
     * @param mContext
     * @param attrs
     */
    public JbxxPage01(Context mContext, Map<String, IBean> beanMap, AttributeSet attrs) {
        super(mContext, attrs);
        this.beanMap = beanMap;
        init(mContext);
    }

    /**
     * @param mContext
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(mContext, "");
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(R.layout.fragment_archives_grxx_layout, this);
//        loadArrays();
        loadPage(mContext, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        mContext = context;
        mSexSpinner = (SpinnerUtil) viewGroup.findViewById(R.id.xb_spinner);
        mBirthdayText = (CalendarText) viewGroup.findViewById(R.id.csrq_text);
        mRelationSpinner = (SpinnerUtil) findViewById(R.id.yhzgx_spinner);
        mWorkUnitEdit = (EditText) viewGroup.findViewById(R.id.gzdw_edit);
        mSelfPhoneEdit = (EditText) viewGroup.findViewById(R.id.brdh_edit);
        mFolkSpinner = (SpinnerUtil) viewGroup.findViewById(R.id.mz_spinner);
        mRelNameEdit = (EditText) viewGroup.findViewById(R.id.lxr_edit);
        mRelPhoneEdit = (EditText) viewGroup.findViewById(R.id.lxrdh_edit);
        mEducbgSpinner = (SpinnerUtil) viewGroup.findViewById(R.id.whcd_spinner);
        mLiveSpinner = (SpinnerUtil) viewGroup.findViewById(R.id.czlx_spinner);
        mRegTypeSpinner = (SpinnerUtil) viewGroup.findViewById(R.id.hkxz_spinner);
        mMarriageSpinner = (SpinnerUtil) viewGroup.findViewById(R.id.hyzk_spinner);
        mBloodSpinner = (SpinnerUtil) viewGroup.findViewById(R.id.xx_spinner);
        mRHSpinner = (SpinnerUtil) findViewById(R.id.rhyx_spinner);
        mVocaSpinner = (SpinnerUtil) findViewById(R.id.zy_spinner);
        mNotionalspinner = (SpinnerUtil) findViewById(R.id.gj_spinner);
        mQiuzhuspinner = (SpinnerUtil) findViewById(R.id.yzlx_spinner);
        mEmailEdit = (EditText) viewGroup.findViewById(R.id.dzyx_edit);
        mYzbmEdit = (EditText) findViewById(R.id.yb_edit);
        mZjlxSpinner = (SpinnerUtil) findViewById(R.id.zjlx_spinner);
        qtEditText = (EditText) viewGroup.findViewById(R.id.qtEditText);
        
        mRegAddressEdit = (EditText)viewGroup.findViewById(R.id.hjdz_edit);
        mRHSpinner = (SpinnerUtil) findViewById(R.id.rhyx_spinner);        
        gldw_edit = (EditText)viewGroup.findViewById(R.id.gldw_edit);
        jdrq_text = (CalendarText) viewGroup.findViewById(R.id.jdrq_text);
        jtzz_edit = (AddressText)viewGroup.findViewById(R.id.jtzz_edit);
        //mZjlxSpinner = (SpinnerUtil) findViewById(R.id.zjlx_spinner);
        String[] ids = ResourcesFactory.listId(mContext, "fxsj");
        checkBoxGroup = new CheckBoxGroupUtil(viewIds, viewGroup,ids);
        qtEditText.setEnabled(false);

        checkBoxGroup.getCheckBox(checkBoxGroup.size() - 1)// 最后一个checkBox的监听
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            qtEditText.setEnabled(true);
                        } else {
                            qtEditText.setText("");
                            qtEditText.setEnabled(false);
                        }
                    }
                });
        mRelationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 如果当前与户主关系是户主，则第10页的户主是当前姓名
                Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
                if (mJmjbxx == null || mJmjtxx == null) {
                    return;
                }
                mJmjbxx.setRelation(new BeanID(mRelationSpinner.getSelectedValueInt(),
                        mRelationSpinner.getSelectedData()));
                if (mRelationSpinner.getSelectedData().equals("户主")) {
                    mJmjtxx.setHouseholder(mJmjbxx.getResidentName());
                } else {
                    mJmjtxx.setHouseholder("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public void setValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            return;
        }

        mSexSpinner.setSelectedPositionByValue(mJmjbxx.getSexCD());

        mBirthdayText.setText(mJmjbxx.getBirthDay());

        mRelationSpinner.setCheckedByBeanID(mJmjbxx.getRelation());

        if (mJmjbxx.getWorkUnit() != null)
            mWorkUnitEdit.setText(mJmjbxx.getWorkUnit());

        if (mJmjbxx.getSelfPhone() != null)
            mSelfPhoneEdit.setText(mJmjbxx.getSelfPhone());

        mFolkSpinner.setCheckedByBeanID(mJmjbxx.getFlokCD());

        if (mJmjbxx.getRelaName() != null)
            mRelNameEdit.setText(mJmjbxx.getRelaName());

        if (mJmjbxx.getRelaPhone() != null)
            mRelPhoneEdit.setText(mJmjbxx.getRelaPhone());

        /*
         * 文化程度代码（单选），值为代码：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70
         * ,初中； 80,小学；90,文盲或半文盲；97,其他
         */
        mEducbgSpinner.setSelectedPositionByValue(mJmjbxx.getEducationCD());
        
        mBloodSpinner.setSelectedPositionByValue(mJmjbxx.getBloodCD());

        // 用户性质，0.农业户口 1.非农业户口 2.农转非
        mRegTypeSpinner.setSelectedPositionByValue(mJmjbxx.getRegTypeCD());

        // 值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况 -->
        mMarriageSpinner.setSelectedPositionByValue(mJmjbxx.getMarriageCD());
        

        mRHSpinner.setSelectedPositionByValue(mJmjbxx.getRh());// 未找到说明

        // 职业
        mVocaSpinner.setCheckedByBeanID(mJmjbxx.getVocationCD());

        // 国家
        mNotionalspinner.setCheckedByBeanID(mJmjbxx.getNationalityCD());

        // // 救助类别代码（单选），值为代码：1：低保对象；2：五保对象；3：特困残疾人
        mQiuzhuspinner.setSelectedPositionByValue(mJmjbxx.getAidCD());

        if (mJmjbxx.getEmail() != null)
            mEmailEdit.setText(mJmjbxx.getEmail());

        if (mJmjbxx.getZip() != null)
            mYzbmEdit.setText(mJmjbxx.getZip());

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
        jtzz_edit.setValue(memAddress);

        // 测试数据
        // 浙江省宁波市鄞州区石碶街道东杨村委会徐家72-1
        // <NowProvince ID="33">浙江省</NowProvince>
        // <NowCity ID="3302">宁波市</NowCity>
        // <NowDistrict ID="330212">鄞州区</NowDistrict>
        // <NowStreet ID="330212005">石碶街道</NowStreet>
        // <NowZone ID="330212005213">东杨村委会</NowZone>
        // <NowRoad ID="23">徐家72-1</NowRoad>
        // <NowN/>
        // <NowH/>
        // <NowS/>
        // <NowOther>石碶街道东杨村委会徐家72-1</NowOther>
        // memAddress.setProvince(new BeanID("33", "浙江省"));
        // memAddress.setCity(new BeanID("3302", "宁波市"));
        // memAddress.setDistrict(new BeanID("330212", "鄞州区"));
        // memAddress.setStreet(new BeanID("330212005", "石碶街道"));
        // memAddress.setZone(new BeanID("330212005213", "东杨村委会"));
        // memAddress.setRoad(new BeanID("23", "徐家72-1"));
        // memAddress.setN("");
        // memAddress.setH("");
        // memAddress.setS("");
        // memAddress.setOther("石碶街道东杨村委会徐家72-1");
        // jtzz_edit.setValue(memAddress);
    
        // 居住类型 常驻类型代码（单选）:1户籍 2非户籍
        mLiveSpinner.setSelectedPositionByValue(mJmjbxx.getResideCD());

        //户籍地址
        if(mJmjbxx.getRegAddress()!=null) {
            mRegAddressEdit.setText(mJmjbxx.getRegDetail());
            mRegAddressEdit.setText(mJmjbxx.getRegAddress());
        }
        
        //证件类型
        mZjlxSpinner.setSelectedPositionByValue(mJmjbxx.getCredentials());
        
        // 负性事件
        if (beanMap.containsKey(Jmxwxg.class.getName())
                && beanMap.get(Jmxwxg.class.getName()) != null) {
            Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
            checkBoxGroup.setCheckedByValues(mJmxwxg.getPrimaryEvent());
            if (checkBoxGroup.getCheckBox(checkBoxGroup.size() - 1).isChecked()) {
                qtEditText.setText(mJmxwxg.getPrimaryEventName());
            }
        }
        
        //管理单位
        if (mJmjbxx.getManageOrg() != null) {
            gldw_edit.setText(mJmjbxx.getManageOrg().getTagValue());
            Global.manageCode = mJmjbxx.getManageOrg().getiD();
        }

        // 建档单位
        if (mJmjbxx.getBuildOrg() != null) {
            Global.buildCode = mJmjbxx.getBuildOrg().getiD();
            Global.buildName = mJmjbxx.getBuildOrg().getTagValue();
        } else {
            Global.buildCode = Global.orgCode;
            Global.buildName = Global.orgName;
        }
        
        //建档日期
        if(mJmjbxx.getBuildDate()!=null )
        	jdrq_text.setText(mJmjbxx.getBuildDate());
    }

    public boolean getValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        if (mJmjbxx == null || mJmjtxx == null) {
            return false;
        }

        // 设置性别
        mJmjbxx.setSexCD(mSexSpinner.getSelectedValueInt());

        // 设置生日
        String birthday = mBirthdayText.getText().toString().trim();
        if (birthday.equals("")) {
            mToast.setText("生日不能为空");
            mToast.show(); 
            return false;
        }
        mJmjbxx.setBirthDay(birthday);

        // 设置与户主的关系
        mJmjbxx.setRelation(new BeanID(mRelationSpinner.getSelectedValueInt(),
                mRelationSpinner.getSelectedData()));
        // 如果当前与户主关系是户主，则第10页的户主是当前姓名
        if (mRelationSpinner.getSelectedData().equals("户主")) {
            mJmjtxx.setHouseholder(mJmjbxx.getResidentName());
        } else {
            mJmjtxx.setHouseholder("");
        }

        // 设置工作地点
        String workUnit = mWorkUnitEdit.getText().toString();
        if (workUnit.equals("")) {
            mToast.setText("工作单位不能为空");
            mToast.show();
            return false;
        }
        mJmjbxx.setWorkUnit(workUnit);

        // 设置自己的电话
        String selfPhoneString = mSelfPhoneEdit.getText().toString().trim();
        if (selfPhoneString.equals("")) {
            mToast.setText("个人电话不能为空");
            mToast.show();
            return false;
        }
        mJmjbxx.setSelfPhone(mSelfPhoneEdit.getText().toString());

        // 设置民族
        mJmjbxx.setFlokCD(new BeanID(mFolkSpinner.getSelectedValue(), mFolkSpinner
                .getSelectedData()));

        // 设置联系人姓名
        mJmjbxx.setRelaName(mRelNameEdit.getText().toString());

        // 设置联系人电话
        mJmjbxx.setRelaPhone(mRelPhoneEdit.getText().toString());

        // 设置文化程度代码（单选）
        mJmjbxx.setEducationCD(mEducbgSpinner.getSelectedValueInt());
        
        // 1、A型 2、B型 3、O型 4、AB型 5、AB型的RH阴性 6、AB型的RH阳性
        mJmjbxx.setBloodCD(mBloodSpinner.getSelectedValueInt());      
              
        // 用户性质，0.农业户口 1.非农业户口 2.农转非
        mJmjbxx.setRegTypeCD(mRegTypeSpinner.getSelectedValueInt());

        // 婚姻状况值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况 -->
        mJmjbxx.setMarriageCD(mMarriageSpinner.getSelectedValueInt());
        
        //RH阴性
        mJmjbxx.setRh(mRHSpinner.getSelectedValueInt());
       
       
        // 职业设置
        mJmjbxx.setVocationCD(new BeanID(mVocaSpinner.getSelectedValueInt(),
                mVocaSpinner.getSelectedData()));

        // 国籍设置
        mJmjbxx.setNationalityCD(new BeanID(mNotionalspinner
                .getSelectedValue(), mNotionalspinner
                .getSelectedData()));

        // 救助类别代码（单选），值为代码：1：低保对象；2：五保对象；3：特困残疾人
        mJmjbxx.setAidCD(mQiuzhuspinner.getSelectedValueInt());

        // 电子邮箱
        String email = mEmailEdit.getText().toString().trim();
        /*Pattern pattern = Pattern.compile("\\w+[\\w]*@[\\w]+\\.[\\w]+$");
        Matcher matcher = pattern.matcher(email);
        if (!email.equals("") && !matcher.matches()) {
            mToast.setText("邮箱格式不正确，请重新填写！");
            mToast.show();
            return false;
        }*/
        mJmjbxx.setEmail(email);

        // 邮政编码
        mJmjbxx.setZip(mYzbmEdit.getText().toString());
        
        //----------------家庭住址-----------------------

        memAddress = jtzz_edit.getValue();
        // 设置省份
        mJmjbxx.setNowProvince(memAddress.province);

        // 设置市
        mJmjbxx.setNowCity(memAddress.city);
        // 设置区/县
        mJmjbxx.setNowDistrict(memAddress.district);
        // 设置街道
        mJmjbxx.setNowStreet(memAddress.street);
        // 设置社区
        mJmjbxx.setNowZone(memAddress.zone);

        // 设置路
        mJmjbxx.setNowRoad(memAddress.road);

        // 弄
        mJmjbxx.setNowN(memAddress.n);

        // 号
        mJmjbxx.setNowH(memAddress.h);

        // 室
        mJmjbxx.setNowS(memAddress.s);

        // 其他
        mJmjbxx.setNowOther(memAddress.other);
      
        // 居住类型 常驻类型代码（单选）:1户籍 2非户籍
        mJmjbxx.setResideCD(mLiveSpinner.getSelectedValueInt());
        
        //户籍地址
        mJmjbxx.setRegDetail(mRegAddressEdit.getText().toString().trim());
        mJmjbxx.setRegAddress(mRegAddressEdit.getText().toString().trim());
        
        //证件类型
        mJmjbxx.setCredentials(mZjlxSpinner.getSelectedValueInt());

        Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
        // 负性事件
        mJmxwxg.setPrimaryEvent(checkBoxGroup.getCheckValues(","));

        mJmxwxg.setPrimaryEventName(qtEditText.getText().toString().trim());
        
       //管理单位     
        BeanID manageOrg = new BeanID();
        manageOrg.setiD(Global.manageCode);
        manageOrg.setTagValue(gldw_edit.getText().toString().trim());
        mJmjbxx.setManageOrg(manageOrg);
         
        //建档日期        
        String mBuildDate = jdrq_text.getText().toString().trim();
        if (mBuildDate.equals("")) {
            mToast.setText("建档日期不能为空");
            mToast.show();
            return false;
        }
        mJmjbxx.setBuildDate(mBuildDate);
        if (!StringUtil.isEmptyString(Global.buildCode)) {
            mJmjbxx.setBuildOrg(new BeanID(Global.buildCode, Global.buildName));
        } else {
            mJmjbxx.setBuildOrg(new BeanID(Global.orgCode, Global.orgName));
        }

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
