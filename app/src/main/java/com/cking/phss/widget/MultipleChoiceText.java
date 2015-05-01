package com.cking.phss.widget;

import java.util.ArrayList;
import java.util.List;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.ResourcesFactory.StringItem;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;

/**
 * 
 * @author taowencong
 * 
 */
public class MultipleChoiceText extends TextView {
    private static final String TAG = "MultipleChoiceText";
    private Context mContext;
    private boolean[] checkedItems;
    private AlertDialog dialog;
    private String[] dataSource;
    private String[] valueSource;

    /**
     * 多选后确定的事件
     * 
     * @author mm
     * 
     */
    public interface OnSetFinishedListener {
        public void onSetFinished(String[] dataSource, String[] valueSource);
    }

    private OnSetFinishedListener mOnSetFinishedListener = null;

    public void setOnSetFinishedListener(OnSetFinishedListener listener) {
        mOnSetFinishedListener = listener;
    }

    public MultipleChoiceText(Context context) {
        super(context);
        mContext = context;
        init("");
    }

    public MultipleChoiceText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiValue);
        String name = a.getString(R.styleable.MultiValue_name);
        a.recycle();

        init(name);
    }

    public MultipleChoiceText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiValue);
        String name = a.getString(R.styleable.MultiValue_name);
        a.recycle();

        init(name);
    }

    public void setDataValueSouce(String[] dataSource,String[] valueS) {
        // 设置跑马灯效果
        this.setGravity(Gravity.CENTER_HORIZONTAL);
        this.setSingleLine();
        this.setHorizontallyScrolling(true);
        this.setMarqueeRepeatLimit(-1);
        this.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setBackgroundResource(R.drawable.edit_box);

        this.dataSource = dataSource;
        this.valueSource=valueS;
        
        //提供默认值
        if(valueSource==null||dataSource.length!=valueSource.length){
            valueSource=new String[dataSource.length];
            for(int i=0;i<valueSource.length;i++){
                valueSource[i]=i+1+"";
            }
        }
        
        
        checkedItems = new boolean[dataSource.length];
        for (int i = 0; i < checkedItems.length; i++) {
            checkedItems[i] = false;
        }

    }

    public String[] getDataSource() {
        return dataSource;
    }

    //获取所有items的状态
    public boolean[] getCheckedItems() {
        return checkedItems;
    }

    //设置所有items的状态
    public void setCheckedItems(boolean[] checkedItems) {
        this.checkedItems = checkedItems;
    }

    private void init(String name) {
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

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultipleChoiceDialog();
            }
        });
    }

    private void showMultipleChoiceDialog() {
            dialog = new AlertDialog.Builder(mContext).setTitle(this.getHint())
                    .setMultiChoiceItems(dataSource, checkedItems, new MultipleChoiceListener())
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        if (showText()) {
                                dialog.dismiss();
                            if (mOnSetFinishedListener != null) {
                                mOnSetFinishedListener.onSetFinished(dataSource, valueSource);
                            }
                        }
                        }
                    }).create();

        if (!dialog.isShowing())
            dialog.show();
    }

    private class MultipleChoiceListener implements DialogInterface.OnMultiChoiceClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            if (which < checkedItems.length)
                checkedItems[which] = isChecked;
        }
    }

    public boolean showText() {
        String content = "";
        if (checkedItems.length != dataSource.length)
            return false;
        for (int i = 0; i < checkedItems.length; i++) {
            if (checkedItems[i])
                content += (dataSource[i] + ",");
        }
        if (!content.equals("")) {
            content = content.substring(0, content.length() - 1);// 切去多个的一个逗号
        }
        setText(content);
        return true;
    }

    //获取所有所选中的item代表的值的字符串的状态 spilt是用什么分隔
    public String getCheckedValues(String spilt) {
        String content="";
        boolean[] checkStatus=getCheckedItems();
        for(int i=0;i<checkStatus.length;i++){
            if(checkStatus[i])
                content+=(valueSource[i]+spilt);
        }
        if(!content.equals("")&&content.length()>spilt.length())
        content=content.substring(0, content.length()-spilt.length());
        return content;
    }

    // 获取所有所选中的item代表的值的字符串的状态 spilt是用什么分隔
    public String getCheckedDatas(String spilt) {
        String content = "";
        boolean[] checkStatus = getCheckedItems();
        for (int i = 0; i < checkStatus.length; i++) {
            if (checkStatus[i])
                content += (dataSource[i] + spilt);
        }
        if (!content.equals("") && content.length() > spilt.length())
            content = content.substring(0, content.length() - spilt.length());
        return content;
    }
    
    //获取所有所选中的item
    public BeanCD getCheckedBeanCD(String spilt) {
    	
    	BeanCD mBeanCD = new BeanCD();
    	
        String IDs="";
        String Values="";
        boolean[] checkStatus=getCheckedItems();
        for(int i=0;i<checkStatus.length;i++){
            if(checkStatus[i])
            {
                IDs+=(valueSource[i]+spilt);
                Values+=(dataSource[i]+spilt);
            }
        }
        if(!IDs.equals("")&&IDs.length()>spilt.length())
        {
        	IDs=IDs.substring(0, IDs.length()-spilt.length());
        	Values=Values.substring(0, Values.length()-spilt.length());
        }
        
        mBeanCD.setcD(IDs);
        mBeanCD.setTagValue(Values);
        
        return mBeanCD;
    }
    
    //根据值，来设置被选中的item
    public void setCheckedByValues(String values,String spilt) {
        clearChecked();
        if (values == null || values.trim().equals("")) {
        } else {
            String[] checkValues = values.split(spilt);
            for (int i = 0; i < checkValues.length; i++) {
                for (int j = 0; j < valueSource.length; j++) {
                    if (checkValues[i].trim().equals(valueSource[j])) {
                        checkedItems[j] = true;
                        break;
                    }
                }
            }
        }
        showText();
    }

    // 根据值，来设置被选中的item
    public void setCheckedByDatas(String datas) {
        clearChecked();
        if (datas == null || datas.trim().equals(""))
            return;
        String[] checkValues = null;
        String[] spilts = new String[] { ",", "\\|" };
        for (String spilt : spilts) {
            checkValues = datas.split(spilt);
            if (checkValues.length > 1 && checkValues[0].length() != datas.length()) { // 说明找到了分隔符
                break;
            }
        }
        if (checkValues.length == 0)
            return;
        for (int i = 0; i < checkValues.length; i++) {
            for (int j = 0; j < dataSource.length; j++) {
                if (checkValues[i].trim().equals(dataSource[j])) {
                    checkedItems[j] = true;
                    break;
                }
            }
        }
        showText();
    }
    
   /* private boolean _showText() {
        boolean showSucc = showText();
        if (!showSucc)
            Toast.makeText(mContext, "请至少选择一个", Toast.LENGTH_SHORT).show();
        return showSucc;
    }*/

    // 让某个item被选中
    public void setItmeCheck(int position, boolean isChecked) {
        if (position >= checkedItems.length)
            return;
        checkedItems[position] = isChecked;
    }

    //清除所有选中的状态
    public void clearChecked(){
        if (checkedItems != null) {
            for (int i = 0; i < checkedItems.length; i++)
                checkedItems[i] = false;
        }
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused)
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus)
            super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    /**
     * @param string
     */
    public void setName(String name) {
        init(name);
    }

    /**
     * @param b
     */
    public void setCheckedAll(boolean checked) {
        for (int i = 0; i < checkedItems.length; i++)
            checkedItems[i] = checked;
        showText();
    }

    /**
     * @param values
     *            代表值的字符串
     * @param spilt
     *            根据什么来分割，例如 "|" 或 ","
     */
    public void setCheckedByBeanCD(BeanCD bean, String spilt) {
        setCheckedByValues(bean.getcD(), spilt);
    }

}
