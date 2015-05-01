/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.view.SfglBodyView.OnGetSfbhListener;
import com.cking.phss.widget.SfglTjLayout;

/**
 * 孕产访视
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
public class SfglEtfsPage extends MyPage implements ISfglPage {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglEtfsPage";
    private static final boolean D = true;
    private Context mContext = null;
    private boolean hasInit = false;

    /**
     * 全局控件
     */
    private RadioGroup mTabRadios = null;
//    private Button mSaveButton = null;
//    private Button mResultButton = null;
//    private Button mUploadButton = null;
//    private Button mRegisterButton = null;
//    public static TextView mRegisterIdText = null;

    private Toast mToast = null;

    SfglTjLayout sfglTjLayout; // 底部统计信息
    public static boolean sHasData = false;
    public int operateFlag = 1;// 操作的状态，0表示无操作，1表示新增操作，2表示编辑操作

    /**
     * 选择的TAB页编号
     */
    private static int sTabRadioId = 0;

    RadioGroup radiogroup_kstj;
    LinearLayout layout_content;

    /**
     * 快速体检的8个子页
     */
    SfglEtfsPage01 page01 = null;
    SfglEtfsPage02 page02 = null;
    SfglEtfsPage03 page03 = null;
    SfglEtfsPage04 page04 = null;

    /**
     * @param context
     */
    public SfglEtfsPage(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * @param context
     */
    protected void init(final Context context) {
        Log.i(TAG, "init SfglEtfsPage...");
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sfgl_etfs_layout, this);

        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        // sfglTjLayout = (SfglTjLayout) findViewById(R.id.sfglTjLayout);
        // sfglTjLayout.setDisType("0203");
        // 添加页面
        radiogroup_kstj = (RadioGroup) findViewById(R.id.radiogroup_kstj);
        layout_content = (LinearLayout) findViewById(R.id.layout_content);

        mTabRadios = (RadioGroup) findViewById(R.id.tab_radio);
        page01 = new SfglEtfsPage01(mContext);
        page02 = new SfglEtfsPage02(mContext);
        page03 = new SfglEtfsPage03(mContext);
        page04 = new SfglEtfsPage04(mContext);
        
        radiogroup_kstj.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                SfglEtfsPage.sTabRadioId = checkedId;
                int tag = Integer.parseInt((String) rb.getTag());
                switch (tag) {
                    case 0:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(page01);
                        break;

                    case 1:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(page02);
                        break;
                    case 2:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(page03);
                        break;

                    case 3:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(page04);
                        break;
                }
            }
        });
        radiogroup_kstj.check(R.id.radio_cgtj);
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        // TODO Auto-generated method stub
        page01.setValue();
        page02.setValue();
        page03.setValue();
        page04.setValue();
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        // TODO Auto-generated method stub
        if (page01.getValue() && page02.getValue() && page03.getValue() & page04.getValue()) {

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

    /**
     * 
     */
    public void print() {
    }

    /**
     * 
     */
    public void saveValueToDb() {
    }

    /**
     * @param onGetSfbhListener
     */
    public void saveValueToWeb(OnGetSfbhListener onGetSfbhListener) {
    }

    /**
     * @param onGetSfbhListener
     */
    public void getLastValue(OnGetSfbhListener onGetSfbhListener) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.ISfglPage#onResultSflb(boolean)
     */
    @Override
    public void onResultSflb(boolean canDoSf) {
        if (canDoSf) {
            mToast.setText("该居民可以进行孕产访视随访");
            mToast.show();
        } else {
            mToast.setText("该居民不可以进行孕产访视随访");
            mToast.show();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.ISfglPage#onResultSftj(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void onResultSftj(String should, String done, String notdo) {
        // sfglTjLayout.setFootTipsText(should, done, notdo);
    }

    /**
     * @param onGetSfbhListener
     */
    public void getLastValueFromDb(OnGetSfbhListener onGetSfbhListener) {
    }

    @Override
    protected void onAttachedToWindow() {
        if (!hasInit) {
            // init(mContext);
            hasInit = true;
        }
        super.onAttachedToWindow();
    }
}
