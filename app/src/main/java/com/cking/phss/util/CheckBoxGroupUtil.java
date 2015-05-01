package com.cking.phss.util;

import java.util.ArrayList;
import java.util.List;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.ResourcesFactory.StringItem;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cking.phss.bean.BeanCD;

/**
 * 应用场景：当每个CheckBox对应了一个值的时候，并且根据值来设置选中状态，或者根据选中状态来获取以"," 或 "|"等方式分隔的字符串
 * 
 * checkBox组的操作类，简化 本工程需要传很多值的需要
 * 
 * @author taowencong
 * 
 */
public class CheckBoxGroupUtil {
    private static final String TAG = "CheckBoxGroupUtil";
    private CheckBox[] mCheckBoxs;//checkBox组
    private ViewGroup mParent;//
    private String[] dataSource;
    private String[] valueSource;
    private TextView otherTextView; // 其他

    /**
     * 
     * @param viewIds checkbox id 数组
     * @param viewGroup 父容器
     * @param valueSource 每个checkBox对应的value，长度必须和viewIds一致
     */
    public CheckBoxGroupUtil(int[] viewIds, ViewGroup viewGroup, String[] valueSource) {
        mCheckBoxs = new CheckBox[viewIds.length];
        mParent = viewGroup;
        this.valueSource = valueSource;
        for (int i = 0; i < viewIds.length; i++) {
            mCheckBoxs[i] = (CheckBox) mParent.findViewById(viewIds[i]);
        }
    }

    public CheckBoxGroupUtil(Context mContext, int[] viewIds, ViewGroup viewGroup, String name,
            TextView otherTextView) {
        init(mContext, viewIds, viewGroup, name, viewIds.length - 1, otherTextView);
    }

    public CheckBoxGroupUtil(Context mContext, int[] viewIds, ViewGroup viewGroup, String name,
            int index,
            TextView otherTextView) {
        init(mContext, viewIds, viewGroup, name, index, otherTextView);
    }

    /**
     * 
     * @param viewIds
     *            所操作的所有checkBox
     * @param viewGroup
     *            父视图
     * @param valueSource
     *            每个checkBox对应的value，长度必须和viewIds一致
     */
    public CheckBoxGroupUtil(Context mContext, int[] viewIds, ViewGroup viewGroup, String name) {
        init(mContext, viewIds, viewGroup, name, 0, null);
    }

    private void init(Context mContext, int[] viewIds, ViewGroup viewGroup, String name, int index,
            final TextView otherTextView) {

        this.otherTextView = otherTextView;
        
        mCheckBoxs = new CheckBox[viewIds.length];
        
        mParent = viewGroup;

        List<String> dataSource = new ArrayList<String>();
        
        List<String> valueSource = new ArrayList<String>();
        
        if (name != null) {
            String[] nameItems = name.split("\\+"); // 支持+合并多个列表
            for (String item : nameItems) {
                List<StringItem> stringArray = ResourcesFactory.findStringArrayByName(mContext, item);
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

        for (int i = 0; i < viewIds.length; i++) {
        	
            mCheckBoxs[i] = (CheckBox) mParent.findViewById(viewIds[i]);
            
            // 设置默认文本
            if (StringUtil.isEmptyString(mCheckBoxs[i].getText().toString())) {
                mCheckBoxs[i].setText(dataSource.get(i));
            }
        }

        if (otherTextView != null) {
            mCheckBoxs[index]
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                otherTextView.setEnabled(true);
                            } else {
                                otherTextView.setText("");
                                otherTextView.setEnabled(false);
                            }
                        }
                    });
            otherTextView.setEnabled(false);
        }

        setDataValueSouce(dataSource.toArray(new String[1]), valueSource.toArray(new String[1]));
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
     * 设置所有checkBox的状态
     * 
     * @param checked
     *            是否选中
     */
    public void setCheckedAll(boolean checked) {
        for (CheckBox checkBox : mCheckBoxs) {
            checkBox.setChecked(checked);
        }
    }

    public void setCheckedAllExcept(boolean checked, int resId) {
        for (CheckBox checkBox : mCheckBoxs) {
            if (resId == checkBox.getId()) {
                checkBox.setChecked(!checked);
            } else {
                checkBox.setChecked(checked);
            }
        }
    }

    public void setCheckedAllExcept(boolean checked, CheckBox exceptCheckBox) {
        for (CheckBox checkBox : mCheckBoxs) {
            if (exceptCheckBox.equals(checkBox)) {
                checkBox.setChecked(!checked);
            } else {
                checkBox.setChecked(checked);
            }
        }
    }
    
    /**
     * 设置所有checkBox是否可用
     * @param checked 是否可用
     */
    public void setEnabledAll(boolean enabled) {
        for (CheckBox checkBox : mCheckBoxs) {
            checkBox.setEnabled(enabled);
        }
    }

    /**
     * 设置所有checkBox是否可用
     * 
     * @param checked
     *            是否可用
     */
    public void setEnabledAllExcept(boolean enabled, int resId) {
        for (CheckBox checkBox : mCheckBoxs) {
            if (resId == checkBox.getId()) {
                checkBox.setEnabled(!enabled);
            } else {
                checkBox.setEnabled(enabled);
            }
        }
    }

    /**
     * 设置所有checkBox是否可用
     * 
     * @param checked
     *            是否可用
     */
    public void setEnabledAllExcept(boolean enabled, CheckBox exceptCheckBox) {
        for (CheckBox checkBox : mCheckBoxs) {
            if (exceptCheckBox.equals(checkBox)) {
                checkBox.setEnabled(!enabled);
            } else {
                checkBox.setEnabled(enabled);
            }
        }
    }

    /**
     * 根据位置来设置选中的checkBox的状态
     * 
     * @param position
     *            位置
     * @param checked
     *            状态
     */
    public void setCheckedByPosition(int position, boolean checked) {
        mCheckBoxs[position].setChecked(checked);
    }

    /**
     *  根据值 设置选中的按钮
     * @param value  整形的值
     * @param checked  是否选中
     */
    public void setCheckedByValue(int value, boolean checked) {
        String valueString = value + "";
        setCheckedByValue(valueString, checked);
    }

    /**
     *  根据值 设置选中的按钮
     * @param value  string类型的值
     * @param checked 是否选中
     */
    public void setCheckedByValue(String value, boolean checked) {
        checkValue();
        if (value == null || value.trim().equals(""))
            return;
        int position = 0;
        for (int i = 0; i < valueSource.length; i++) {
            if (StringUtil.isDecimal(value)) {
                int val = Integer.parseInt(value);
                int v = Integer.parseInt(valueSource[i]);
                if (val == v) {
                    position = i;
                    break;
                }
            } else {
                if (value.trim().equals(valueSource[i])) {
                    position = i;
                    break;
                }
            }
        }
        setCheckedByPosition(position, checked);
    }

   /**
    * @param spilt   根据什么来分割代表值的字符串，例如 "|" 或 "," 
    * @return 选择的checkBox 代表的字符串
    */
    public String getCheckValues(String spilt) {
        checkValue();
        String checkString = "";
        for (int i = 0; i < mCheckBoxs.length; i++) {
            if (mCheckBoxs[i].isChecked()) {
                checkString += (valueSource[i] + spilt);
            }
        }
        if (!checkString.equals("") && checkString.length() > spilt.length()) {
            checkString = checkString.substring(0, checkString.length() - spilt.length());
        }
        return checkString;
    }

    public void setCheckedByValues(String values) {
        setCheckedAll(false);
        if (values == null || values.trim().equals(""))
            return;
        String[] checkValues = null;
        String[] spilts = new String[] { ",", "\\|" };
        for (String spilt : spilts) {
            checkValues = values.split(spilt);
            if (checkValues.length > 1 && checkValues[0].length() != values.length()) { // 说明找到了分隔符
                break;
            }
        }
        for (int i = 0; i < checkValues.length; i++) {
            setCheckedByValue(checkValues[i].trim(), true);
        }

        // 解析other
        String[] items = values.split(":");
        if (items.length > 1) {
            if (otherTextView != null) {
                otherTextView.setText(items[1]);
            }
        }
    }

    /**
     * @param values
     *            代表值的字符串
     * @param spilt
     *            根据什么来分割，例如 "|" 或 ","
     */
    public void setCheckedByBeanCD(BeanCD bean) {
        setCheckedByValues(bean.getcD());

        // 解析other
        String[] items = bean.getTagValue().split(":");
        if (items.length > 1) {
            if (otherTextView != null) {
                otherTextView.setText(items[1]);
            }
        }
    }

    // 获取所有所选中的item
    public BeanCD getCheckedBeanCD(String spilt) {

        BeanCD mBeanCD = new BeanCD();

        mBeanCD.setcD(getCheckValues(spilt));
        String otherStr = "";
        if (otherTextView != null && StringUtil.isEmptyString(otherTextView.getText().toString())) {
            otherStr = ":" + otherTextView.getText().toString();
        }
        mBeanCD.setTagValue(getCheckDatas(spilt) + otherStr);

        return mBeanCD;
    }

    public BeanCD getCheckedBeanCD(String spilt, String otherStr) {

        BeanCD mBeanCD = new BeanCD();

        mBeanCD.setcD(getCheckValues(spilt));
        mBeanCD.setTagValue(getCheckDatas(spilt) + ":" + otherStr);

        return mBeanCD;
    }

    /**
     * @param spilt
     * @return
     */
    private String getCheckDatas(String spilt) {
        checkValue();
        String checkString = "";
        if (dataSource == null) {
            for (int i = 0; i < mCheckBoxs.length; i++) {
                if (mCheckBoxs[i].isChecked()) {
                    checkString += (mCheckBoxs[i].getText().toString() + spilt);
                }
            }
        } else {
            for (int i = 0; i < mCheckBoxs.length; i++) {
                if (mCheckBoxs[i].isChecked()) {
                    checkString += (dataSource[i] + spilt);
                }
            }
        }
        if (!checkString.equals("") && checkString.length() > spilt.length()) {
            checkString = checkString.substring(0, checkString.length() - spilt.length());
        }
        return checkString;
    }

    /*
     * public boolean isChecked(int id){ return checked; }
     */

    /*
     * public boolean isEnable(int id){
     * 
     * }
     */

    /**
     * 根据位置获取某个checkBox
     * @param position 位置
     * @return 返回的CheckBox对象
     */
    public CheckBox getCheckBox(int position) {
        return mCheckBoxs[position];
    }

    /**
     * 所有checkBox
     * @return 有多少checkBox
     */
    public int size() {
        return mCheckBoxs.length;
    }

    /**
     *  检查checkGroup的值都有没有
     */
    private void checkValue() {
        if (valueSource == null || valueSource.length < mCheckBoxs.length) {
            valueSource=new String[mCheckBoxs.length];
            for (int i = 0; i < valueSource.length; i++) {
                valueSource[i]=i+1+"";
            }
        }
    }
}
