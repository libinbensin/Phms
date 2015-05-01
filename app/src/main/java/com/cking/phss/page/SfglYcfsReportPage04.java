package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.dialog.SfglYcfsBaokaDialog;
import com.cking.phss.util.TispToastFactory;

/**
 * 因为高血压报卡和糖尿病报卡一模一样，所以使用同一个页面
 * 
 * @author taowencong
 * 
 */
public class SfglYcfsReportPage04 extends MyPage2 {
    private static final String TAG = "SfglYcfsReportPage04";
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    private SfglYcfsBaokaDialog mParent;
    private Button qrButton;
    private Button fhButton;

    public SfglYcfsReportPage04(Context context, Map<String, IBean> beanMap,
            SfglYcfsBaokaDialog parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglYcfsReportPage04(Context context, AttributeSet attrs) {
        super(context);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_ycfs_bk_04_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

        qrButton = (Button) findViewById(R.id.qrButton);
        fhButton = (Button) findViewById(R.id.fhButton);

        fhButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParent.onYcfsBaokaResultListener != null) {
                    mParent.dismiss();
                    mParent.onYcfsBaokaResultListener.onCancel();
                }
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParent.onYcfsBaokaResultListener != null) {
                    mParent.dismiss();
                    mParent.onYcfsBaokaResultListener.onConfirm();
                }
            }
        });
    }
}
