package com.cking.phss.widget;

import java.util.ArrayList;
import java.util.List;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.ResourcesFactory.StringItem;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;

/**
 * 
 * @author taowencong
 * 
 */
public class SpinnerUtil extends Spinner {
    private static final String TAG = "SpinnerUtil";
    private Context mContext;
    private int dataResourceId = View.NO_ID;
    private int valueResourceId = View.NO_ID;
    private List<String> dataSource = new ArrayList<String>();
    private List<String> valueSource = new ArrayList<String>();
    private ArrayAdapter<String> adapter;// 适配器
    private String mName = null;

    public SpinnerUtil(Context context) {
        super(context);
    }

    public SpinnerUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiValue);
        String name = a.getString(R.styleable.MultiValue_name);
        a.recycle();
        init(name);
    }

    private void init(String name) {
        mName = name;
        dataSource.clear();
        valueSource.clear();
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
        
        adapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item, dataSource);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public String getSelectedValue() {
        int position = this.getSelectedItemPosition();
        if (position < 0 || position >= valueSource.size()) {
            throw new RuntimeException("数据与值的数量不相等");
        }
        return valueSource.get(position);
    }

    public int getSelectedValueInt() {
        int value = 0;
        try {
            value = Integer.parseInt(getSelectedValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public int getSelectedPosition() {
        return this.getSelectedItemPosition();
    }

    public String getSelectedData() {
        int position = this.getSelectedItemPosition();
        if (position < 0 || position >= dataSource.size())
            throw new RuntimeException("数据量小于选中的位置");
        return dataSource.get(position);
    }

    // 根据值，来设定选中的位置
    public void setSelectedPositionByValue(String value) {
        if (value == null || value.equals("")) {
            if (hasValue())
                setSelection(0);
            return;
        }

        for (int i = 0; i < valueSource.size(); i++) {
            if (valueSource.get(i) != null && valueSource.get(i).trim().equals(value.trim())) {
                this.setSelection(i);
                break;
            } else {
                if (i == valueSource.size())
                    setSelection(0);
            }
        }
    }

    // 根据值，来设定选中的位置
    public void setSelectedPositionByValue(int value) {
        for (int i = 0; i < valueSource.size(); i++) {
            int v;
            try {
                v = Integer.parseInt(valueSource.get(i));
                if (v == value) {
                    this.setSelection(i);
                    return;
                }
            } catch (Exception e) {// 字符串转整形异常的情况
                e.printStackTrace();
            }

        }
        setSelection(0);
    }

    // 根据数据，来设定选中的位置
    public void setSelectedPositionByData(String data) {
        if (data == null || data.equals("")) {
            if (hasValue())
                setSelection(0);
            return;
        }

        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i) != null && dataSource.get(i).trim().equals(data.trim())) {
                this.setSelection(i);
                return;
            }
        }
        setSelection(0);
    }

    // 获取所有所选中的item
    public BeanCD getCheckedBeanCD() {

        BeanCD mBeanCD = new BeanCD();

        mBeanCD.setcD(getSelectedValue());
        mBeanCD.setTagValue(getSelectedData());

        return mBeanCD;
    }

    // 获取所有所选中的item
    public BeanID getCheckedBeanID() {

        BeanID mBeanID = new BeanID();

        mBeanID.setiD(getSelectedValue());
        mBeanID.setTagValue(getSelectedData());

        return mBeanID;
    }

    private boolean hasValue() {
        if (dataSource != null && dataSource.size() > 0)
            return true;
        return false;
    }

    /**
     * @param string
     */
    public void setName(String name) {
        if (!name.equals(mName)) {
            init(name);
        }
        mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public void setSelection(int position) {
        if (position >= dataSource.size()) {
            position = 0;
        }
        super.setSelection(position);
    }

    /**
     * @param value
     */
    public void setCheckedByBeanCD(BeanCD bean) {
        if (bean == null) {
            setSelection(0);
        } else {
            setSelectedPositionByValue(bean.getcD());
        }
    }

    /**
     * @param value
     */
    public void setCheckedByBeanID(BeanID bean) {
        if (bean == null) {
            setSelection(0);
        } else {
            setSelectedPositionByValue(bean.getiD());
        }
    }

}
