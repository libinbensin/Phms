package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.beans.BeanMap;
import net.xinhuaxing.pages.BasePage;
import net.xinhuaxing.util.GlobalUtil;
import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dialog.SfglNzzBaokaDialog;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.AddressText;

/**
 * 因为高血压报卡和糖尿病报卡一模一样，所以使用同一个页面
 * 
 * @author taowencong
 * 
 */
public class SfglNzzReportPage01 extends BasePage {
    private static final String TAG = "SfglNzzReportPage01";
    private AddressText jtzzAddressText;
    AddressText.MemAddress memAddress = new AddressText.MemAddress();
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    private SfglNzzBaokaDialog mParent;

    public SfglNzzReportPage01(Context context, Map<String, IBean> beanMap,
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
    public SfglNzzReportPage01(Context context, AttributeSet attrs) {
        super(context);
        init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_nzz_bk_01_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        jtzzAddressText = (AddressText) findViewById(R.id.jtzzAddressText);
    }
    
    public void setValueByJmjbxx(Jmjbxx mJmjbxx) {
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-身份证",
                mJmjbxx.getPaperNum());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-姓名",
                mJmjbxx.getResidentName());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-性别",
                new BeanMap(mJmjbxx.getSexCD(), ResourcesFactory.findValue(mContext, "xb",
                        mJmjbxx.getSexCD())));
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-出生日期",
                mJmjbxx.getBirthDay());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-本人电话",
                mJmjbxx.getSelfPhone());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-职业",
                        new BeanMap(mJmjbxx.getVocationCD().getiD(), mJmjbxx.getVocationCD()
                                .getTagValue()));
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-联系人",
                mJmjbxx.getRelaName());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-联系人关系",
                new BeanMap(mJmjbxx.getRelation().getiD(), mJmjbxx.getRelation().getTagValue()));
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-联系电话",
                mJmjbxx.getRelaPhone());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-文化程度",
                new BeanMap(mJmjbxx.getEducationCD(), ResourcesFactory.findValue(mContext, "whcd",
                        mJmjbxx.getEducationCD())));
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-教育年限",
                "9");
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中报卡-基本信息-邮政编码",
                mJmjbxx.getZip());
        
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
        jtzzAddressText.setValue(memAddress);
        
    }
}
