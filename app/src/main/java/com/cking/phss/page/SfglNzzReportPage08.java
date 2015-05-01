package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.pages.BasePage;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.dialog.SfglNzzBaokaDialog;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListView;

/**
 * 因为高血压报卡和糖尿病报卡一模一样，所以使用同一个页面
 * 
 * @author taowencong
 * 
 */
public class SfglNzzReportPage08 extends BasePage {
    private static final String TAG = "SfglNzzReportPage02";
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    private SfglNzzBaokaDialog mParent;
    private Button qrButton;
    private Button fhButton;

    public SfglNzzReportPage08(Context context, Map<String, IBean> beanMap,
            SfglNzzBaokaDialog parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglNzzReportPage08(Context context, AttributeSet attrs) {
        super(context);
        init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_nzz_bk_08_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

        qrButton = (Button) findViewById(R.id.qrButton);
        fhButton = (Button) findViewById(R.id.fhButton);

        fhButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParent.onNzzBaokaResultListener != null) {
                    mParent.dismiss();
                    mParent.onNzzBaokaResultListener.onCancel();
                }
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mParent.onNzzBaokaResultListener != null) {
                    mParent.dismiss();
                    mParent.onNzzBaokaResultListener.onConfirm();
                }
            }
        });

        ListView listView1 = (ListView) findViewById(R.id.tnbyyqkListView);
        listView1.setAddButton(findViewById(R.id.xzImageView));
    }
}
