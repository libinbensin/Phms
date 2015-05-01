package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.beans.BeanMap;
import net.xinhuaxing.util.GlobalUtil;
import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.AddressText;

/**
 * 
 * com.cking.phss.page.SfglYcfsReportPage01
 * 
 * @author Wation.Haliyoo <br/>
 *         create at 2014-8-20 下午4:05:31
 */
public class SfglEtfsReportPage01 extends MyPage2 {
    private static final String TAG = "SfglEtfsReportPage01";
    private AddressText jtzzAddressText;
    AddressText.MemAddress memAddress = new AddressText.MemAddress();
    EditText hjdzEditText;
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    public SfglEtfsReportPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglEtfsReportPage01(Context context, AttributeSet attrs) {
        super(context);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_etfs_bk_01_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        jtzzAddressText = (AddressText) findViewById(R.id.jtzzAddressText);
        hjdzEditText = (EditText) findViewById(R.id.hjdzEditText);
    }
    
    public void setValueByJmjbxx(Jmjbxx mJmjbxx) {
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "儿童访视报卡-基本信息-身份证",
                mJmjbxx.getPaperNum());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "儿童访视报卡-基本信息-姓名",
                mJmjbxx.getResidentName());
        GlobalUtil.getInstance().setWidgetValue(
                (ViewGroup) getContentView(),
                "儿童访视报卡-基本信息-性别",
                new BeanMap(mJmjbxx.getSexCD(), ResourcesFactory.findValue(mContext, "xb",
                        mJmjbxx.getSexCD())));
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "儿童访视报卡-基本信息-出生日期",
                mJmjbxx.getBirthDay());
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "儿童访视报卡-基本信息-民族",
                mJmjbxx.getFlokCD());
        
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

        hjdzEditText.setText(mJmjbxx.getRegDetail());
        hjdzEditText.setText(mJmjbxx.getRegAddress());
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            return;
        }
		
        // Ddtnbglkxxxx25 ddtnbglkxxxx25 = (Ddtnbglkxxxx25) beanMap
        // .get(Ddtnbglkxxxx25.class.getName());
        // if (ddtnbglkxxxx25 == null || ddtnbglkxxxx25.response == null) {
        // mToast.setText("直接得到糖尿病管理卡信息为空！");
        // mToast.show();
        //
        // setValueByJmjbxx(mJmjbxx);
        //
        // return;
        // }
        // memAddress.setProvince(mJmjbxx.getNowProvince());
        // memAddress.setCity(mJmjbxx.getNowCity());
        // memAddress.setDistrict(mJmjbxx.getNowDistrict());
        // memAddress.setStreet(mJmjbxx.getNowStreet());
        // memAddress.setZone(mJmjbxx.getNowZone());
        // memAddress.setRoad(mJmjbxx.getNowRoad());
        // memAddress.setN(mJmjbxx.getNowN());
        // memAddress.setH(mJmjbxx.getNowH());
        // memAddress.setS(mJmjbxx.getNowS());
        // memAddress.setOther(mJmjbxx.getNowOther());
        // String address = AddressTextFactory.formatAddress(memAddress);
        // jtzzAddressText.setValue(address);
    }

    @Override
    public void getValue() { if (!hasInit) {return;}
        super.getValue();
        // // ------------------------------------------------------
        // // 家庭地址
        // String address = jtzzAddressText.getValue();
        // memAddress = AddressTextFactory.parseAddress(address);
        // // 设置省份
        // request.nowProvince = memAddress.province;
        //
        // // 设置市
        // request.nowCity = memAddress.city;
        // // 设置区/县
        // request.nowDistrict = memAddress.district;
        // // 设置街道
        // request.nowStreet = memAddress.street;
        // // 设置社区
        // request.nowZone = memAddress.zone;
        //
        // // 设置路
        // request.nowRoad = memAddress.road;
        // // 弄
        // request.nowN = memAddress.n;
        // // 号
        // request.nowH = memAddress.h;
        // // 室
        // request.nowS = memAddress.s;
        // request.nowOther = memAddress.other;
        // request.nowDetail=jtzzAddressText.getValue();
    }
}
