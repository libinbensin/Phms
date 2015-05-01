package com.cking.phss.util;

import java.util.ArrayList;
import java.util.List;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.ResourcesFactory.StringItem;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.cking.phss.bean.BeanCD;

/**
 * RadioGroup 应用场景：
 * 1、当一组RadioButton不在同一行的时候，android自带的RadioGroup不能保证他们选中的唯一性，这种情况下可以使用该类。
 * 2、每个RadioButton对应一个值的时候，可以使用该类
 * 
 * @author taowencong
 * 
 */
public class RadioGroupUtil {
    private static final String TAG = "RadioGroupUtil";
    private int[] mRadioId;
    private ViewGroup mParent;
    private String[] dataSource;
    private String[] valueSource;

    private RadioButton[] mRadioButtons;

    public RadioGroupUtil(Context mContext, int[] viewIds, ViewGroup viewGroup, String name) {
        this.mRadioId = viewIds;
        mParent = viewGroup;

        List<String> dataSource = new ArrayList<String>();
        List<String> valueSource = new ArrayList<String>();
        if (name != null) {
            String[] nameItems = name.split("\\+"); // 支持+合并多个列表
            for (String item : nameItems) {
                List<StringItem> stringArray = ResourcesFactory.findStringArrayByName(mContext,
                        item);
                if (stringArray != null) {
                    for (StringItem stringItem : stringArray) {
                        dataSource.add(stringItem.value);
                        valueSource.add(stringItem.id);
                    }
                } else {
                    // throw new RuntimeException("加载数据源字符串数组资源出错,无法找到该资源,name="
                    // + item);
                    Log.e(TAG, "加载数据源字符串数组资源出错,无法找到该资源,name=" + item);
                    return;
                }
            }
        }

        setDataValueSouce(dataSource.toArray(new String[1]), valueSource.toArray(new String[1]));

        init();
    }

    public void setDataValueSouce(String[] dataSource, String[] valueS) {
        this.dataSource = dataSource;
        this.valueSource = valueS;

        // 提供默认值
        if (valueSource == null || dataSource.length != valueSource.length) {
            valueSource = new String[dataSource.length];
            for (int i = 0; i < valueSource.length; i++) {
                valueSource[i] = i + 1 + "";
            }
        }
    }

    /**
     *   
     * @param mRadioId    所有操作的RadioButton
     * @param mParent   
     * @param valueSource   RadioButton对应的值
     */
    public RadioGroupUtil(int[] mRadioId, ViewGroup mParent, String[] valueSource) {
        this.mRadioId = mRadioId;
        this.mParent = mParent;
        this.valueSource = valueSource;
        init();
    }

    private void init() {
        mRadioButtons = new RadioButton[mRadioId.length];
        // 找到控件
        for (int i = 0; i < mRadioId.length; i++) {
            mRadioButtons[i] = (RadioButton) mParent.findViewById(mRadioId[i]);
            //mRadioButtons[i].setOnClickListener(this);
        }
        // 设置第一个为默认选中
        mRadioButtons[0].setChecked(true);
//        mRadioButtons[0].setClickable(false);
    }

//    @Override
//    public void onClick(View v) {
//        int viewId = v.getId();
//        for (int i = 0; i < mRadioId.length; i++) {
//            if (mRadioButtons[i].isChecked() && mRadioId[i] != viewId) {
//                mRadioButtons[i].setChecked(false);
//                mRadioButtons[i].setClickable(true);
//            }
//        }
//        RadioButton radioButton = ((RadioButton) v);
//        radioButton.setChecked(true);
//        radioButton.setClickable(false);
//    }

    /**
     *  根据Id设置选中哪个
     * @param id  id
     */
    public void setCheckedById(int id) {
        for (int i = 0; i < mRadioId.length; i++) {
            if (mRadioButtons[i].isChecked() && mRadioId[i] != id) {
                mRadioButtons[i].setChecked(false);
                //mRadioButtons[i].setClickable(true);
            }
            if (mRadioId[i] == id) {
                mRadioButtons[i].setChecked(true);
                //mRadioButtons[i].setClickable(false);
            }
        }
    }

    /**
     *  根据位置设置选中哪个
     * @param position 位置
     */
    public void setCheckedByPosition(int position) {
        if (position < 0 || position > mRadioId.length)
            return;
        for (int i = 0; i < mRadioId.length; i++) {
            if (mRadioButtons[i].isChecked() && i != position) {
                mRadioButtons[i].setChecked(false);
                //mRadioButtons[i].setClickable(true);
            }
        }
        mRadioButtons[position].setChecked(true);
        //mRadioButtons[position].setClickable(false);
    }

    /**
     * 根据值来选择
     * @param value String类型的值
     */
    public void setCheckedByValue(String value) {
        if(value==null||value.trim().equals(""))
            return;
        checkValues();
        int position = 0;
        for (int i = 0; i < valueSource.length; i++) {
            if (value.trim().equals(valueSource[i])) {
                position = i;
                break;
            }
        }
        setCheckedByPosition(position);
    }
    
    /**
     *  根据值来选择
     * @param value int类型值
     */
    public void setCheckedByValue(int value) {
        String valueString=value+"";
        setCheckedByValue(valueString);
    }
    
    

    /**
     * 获得选中哪个的id
     * @return
     */
    public int getCheckedId() {
        int checkId = -1;
        for (int i = 0; i < mRadioButtons.length; i++) {
            if (mRadioButtons[i].isChecked()) {
                checkId = mRadioId[i];
                break;
            }
        }
        return checkId;
    }

    /**
     * 获得选中哪个的位置
     * @return
     */
    public int getCheckedPosition() {
        int position = -1;
        if (mRadioButtons == null) {
            return 0;
        }
        for (int i = 0; i < mRadioButtons.length; i++) {
            if (mRadioButtons[i].isChecked()) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * 获取选中的值  String类型
     */
    public String getCheckValue() {
        int position = getCheckedPosition();
        checkValues();
        return valueSource[position];
    }

    /**
     * 获取选中的值 int类型
     */
    public int getCheckValueInt() {
        String value = getCheckValue();
        int valueInt=-1;
        try {
            valueInt = Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueInt;
    }

    /**
     *  检查值存不存在,不存在提供默认的值 ，例如 1,2,3,4,5
     */
    private void checkValues() {
        if (valueSource == null || valueSource.length != mRadioButtons.length) {
            valueSource = new String[mRadioButtons.length];
            for (int i = 0; i < mRadioButtons.length; i++) {
                valueSource[i] = i + 1 + "";
            }
        }
    }

    public String getSelectedValue() {
        int position = getCheckedPosition();
        if (position < 0 || position >= valueSource.length) {
            throw new RuntimeException("数据与值的数量不相等");
        }
        return valueSource[position];
    }

    public String getSelectedData() {
        int position = getCheckedPosition();
        if (position < 0 || position >= dataSource.length)
            throw new RuntimeException("数据量小于选中的位置");
        return dataSource[position];
    }

    /**
     * @return
     */
    public BeanCD getCheckedBeanCD() {

        BeanCD mBeanCD = new BeanCD();

        mBeanCD.setcD(getSelectedValue());
        mBeanCD.setTagValue(getSelectedData());

        return mBeanCD;
    }

    /**
     * @param bean
     */
    public void setCheckedByBeanCD(BeanCD bean) {
        setCheckedByValue(bean.getcD());
    }
}
