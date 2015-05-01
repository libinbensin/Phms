package com.cking.phss.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * 随访管理明细列表项
 */
public class ListItemSfglmxlb extends RelativeLayout {

    private TextView mNameText = null;  // 姓名
    private TextView mSexText = null; // 性别
    private TextView mAgeText = null;// 年龄
    private TextView mLinkPhoneText = null;  // 联系电话
    private TextView mIdCardText = null;  // 身份证
    private TextView mAddressText = null;  // 住址
    private TextView mDisTypeText = null;  // 疾病名称
    private TextView mRptDateText = null;  // 报卡日期
    private TextView mRptSratusText = null;  //报卡状态
    private TextView mApprovedDateText = null;  //审核日期
    private TextView mApprovedFlagText = null;  //审核状态
    
    //
    /**
     * @param context
     */
    public ListItemSfglmxlb(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemSfglmxlb(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.list_item_sflb, this);

        mNameText = (TextView) findViewById(R.id.name_text);
        mSexText =  (TextView) findViewById(R.id.sex_text);
        mAgeText =  (TextView) findViewById(R.id.age_text);// 年龄
        mLinkPhoneText = (TextView) findViewById(R.id.link_phone_text);  // 联系电话
        mIdCardText = (TextView) findViewById(R.id.id_card_text);  // 身份证
        mAddressText = (TextView) findViewById(R.id.address_text);  // 住址
        mDisTypeText = (TextView) findViewById(R.id.disType_text);  // 疾病名称
        mRptDateText = (TextView) findViewById(R.id.rptDate_text);  // 报卡日期
        mRptSratusText = (TextView) findViewById(R.id.rptStatus_text);  //报卡状态
        mApprovedDateText = (TextView) findViewById(R.id.approvedDate_text);  //审核日期
        mApprovedFlagText =  (TextView) findViewById(R.id.approvedFlag_text);  //审核状态
    }

    public void setIndex(int index) {
//      mIndexText.setText(index + ".");
        setTag(index);
        if (index % 2 == 1) {
            setBackgroundResource(R.color.list_jsh_background_color);
        } else {
            setBackgroundResource(R.color.list_osh_background_color);
        }
    }

    public int getIndex() {
        return (Integer) getTag();
    }

    public void setName(String name){
        mNameText.setText(name);
    }
    
    public void setSex(String sex){
        mSexText.setText(sex);
    }
    
    public void setAge(String age){
        mAgeText.setText(age);
    }
    
    public void setLinkPhone(String linkPhone){
        mLinkPhoneText.setText(linkPhone);
    }
    
    public void setIdCard(String idCard){
        mIdCardText.setText(idCard);
    }
    
    public void setAddress(String address){
        mAddressText.setText(address);
    }
    
    public void setDisType(String disType){
        mDisTypeText.setText(disType);
    }
    
    public void setRptDate(String rptDate){
        mRptDateText.setText(rptDate);
    }
    
    public void setRptSratus(String rptSratus){
        mRptSratusText.setText(rptSratus);
    }
    
    public void setApprovedDate(String approvedDate){
        mApprovedDateText.setText(approvedDate);
    }
    public void setApprovedFlag(String approvedFlag){
        mApprovedFlagText.setText(approvedFlag);
    }
}
