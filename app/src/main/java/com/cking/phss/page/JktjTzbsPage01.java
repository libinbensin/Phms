package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Tzps;
import com.cking.phss.sqlite.SqliteField.TzpsField;
import com.cking.phss.sqlite.SqliteOperater;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class JktjTzbsPage01 extends LinearLayout implements IPage {
	private Context mContext = null;
	private Toast mToast = null;
    private Map<String, IBean> beanMap = null;
	private JktjTzbsPage mParent;
    private Button ksjcButton  =null;
    private Button scjgButton=null;
    
    public static  int[] rateValues;//保存上次的结果的分数数据，这些数据从数据库中查找出来的
	/**
	 * @param context
	 */
	public JktjTzbsPage01(Context context, JktjTzbsPage parent, Map<String, IBean> beanMap) {
		super(context);
		mParent = parent;
        this.beanMap = beanMap;
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjTzbsPage01(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_tzbs_1_layout, this);
		
		loadPage(context, this);
	}

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        ksjcButton = (Button)findViewById(R.id.ksjcButton);
        scjgButton=(Button)findViewById(R.id.scjgButton);
        ksjcButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(MyApplication.getInstance().getSession().getCurrentResident()==null){
                    mToast.setText("当前没有用户，不能进行检测！");
                    mToast.show();
                    return;
                }
                mParent.showTzpsTestPage();
            }
        });
        
        scjgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choicePageToShow()){
                    if(rateValues==null||rateValues.length!=Tzps.TZBS_QUESTION.length){
                        mToast.setText("您之前没有做过体质检测，没有数据!");
                        mToast.show();
                        return;
                    }
                    mParent.showTzpsjgxx(rateValues);
                }
            }
        });
    }
    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return false;
    }
    
    //选择到底显示哪一个界面，根据从数据库读取的数据来判断
    public boolean choicePageToShow() {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            mToast.setText("当前没有用户，不能进行检测！");
            mToast.show();
            return false;
        }
        String resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + TzpsField.TB_NAME + " WHERE " + TzpsField.RESIDENT_UUID + "=\'"
                        + resident_uuid + "\'", null);
        while (cursor.moveToNext()) {
            String valueRate = cursor.getString(cursor.getColumnIndex(TzpsField.VALUE));
            rateValues = parseStringToInt(valueRate);
            return true;
        }
        return true;
    }
    
    
    //将"1,2,3"类似形式的字符串 转换为int[]数组
    private int[] parseStringToInt(String valueString){
    	String[] values=valueString.split(",");
    	int[] rates=new int[values.length];
    	for(int i=0;i<values.length;i++){
    		rates[i]=Integer.parseInt(values[i]);
    	}
		return rates;
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
