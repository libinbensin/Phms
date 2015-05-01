package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.MultipleChoiceText;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 基本信息第9页 com.cking.phss.view.JbxxPage06 存在问题 ：厕所下拉列表不存在，家用电器和交通工具应该外checkBox
 */
public class JbxxPage13 extends LinearLayout implements IPage {
	private static final String TAG = "JbxxPage13";
    private Context mContext = null;

    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private SpinnerUtil cfpfssSpinnerUtil = null;
    private SpinnerUtil fwscsSpinnerUtil = null;
    private SpinnerUtil cfsyfsSpinnerUtil = null;
    private SpinnerUtil wscsSpinnerUtil = null;
    private SpinnerUtil rllxSpinnerUtil = null;
    private SpinnerUtil qclSpinnerUtil = null;
    private SpinnerUtil ysSpinnerUtil = null;
    private SpinnerUtil fwlxSpinnerUtil = null;
    SpinnerUtil zfxzSpinnerUtil;
    SpinnerUtil yySpinnerUtil;
    SpinnerUtil wsSpinnerUtil;
    SpinnerUtil wsclSpinnerUtil;
    SpinnerUtil zfcgSpinnerUtil;
    SpinnerUtil zftfSpinnerUtil;
    SpinnerUtil zfbnSpinnerUtil;
    SpinnerUtil kqsdSpinnerUtil;
    MultipleChoiceText wtsbMultipleChoiceText;

    private MultipleChoiceText jydqMultipleChoiceText = null;
    private SpinnerUtil ljclSpinnerUtil = null;
    private MultipleChoiceText jtgjMultipleChoiceText = null;

    private EditText zmjEditText = null;
    private EditText rjmjEditText = null;
    private SpinnerUtil grdaztSpinnerUtil = null;
    EditText zfjsEditText;

    /**
     * @param context
     */
    public JbxxPage13(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage13(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
        super(context, attrs);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_archives_shhj_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        cfpfssSpinnerUtil = (SpinnerUtil) findViewById(R.id.cfpfssSpinnerUtil);
        fwscsSpinnerUtil = (SpinnerUtil) findViewById(R.id.fwscsSpinnerUtil);
        cfsyfsSpinnerUtil = (SpinnerUtil) findViewById(R.id.cfsyfsSpinnerUtil);
        wscsSpinnerUtil = (SpinnerUtil) findViewById(R.id.wscsSpinnerUtil);
        rllxSpinnerUtil = (SpinnerUtil) findViewById(R.id.rllxSpinnerUtil);
        qclSpinnerUtil = (SpinnerUtil) findViewById(R.id.qclSpinnerUtil);
        ysSpinnerUtil = (SpinnerUtil) findViewById(R.id.ysSpinnerUtil);
        fwlxSpinnerUtil = (SpinnerUtil) findViewById(R.id.fwlxSpinnerUtil);
        jydqMultipleChoiceText = (MultipleChoiceText) findViewById(R.id.jydqMultipleChoiceText);
        ljclSpinnerUtil = (SpinnerUtil) findViewById(R.id.ljclSpinnerUtil);
        jtgjMultipleChoiceText = (MultipleChoiceText) findViewById(R.id.jtgjMultipleChoiceText);
        zmjEditText = (EditText) findViewById(R.id.zmjEditText);
        zmjEditText.setText("0");
        rjmjEditText = (EditText) findViewById(R.id.rjmjEditText);
        grdaztSpinnerUtil = (SpinnerUtil) findViewById(R.id.grdaztSpinnerUtil);
        zfjsEditText = (EditText) findViewById(R.id.zfjsEditText);
        zfxzSpinnerUtil = (SpinnerUtil) findViewById(R.id.zfxzSpinnerUtil);
        yySpinnerUtil = (SpinnerUtil) findViewById(R.id.yySpinnerUtil);
        wsSpinnerUtil = (SpinnerUtil) findViewById(R.id.wsSpinnerUtil);
        wsclSpinnerUtil = (SpinnerUtil) findViewById(R.id.wsclSpinnerUtil);
        zfcgSpinnerUtil = (SpinnerUtil) findViewById(R.id.zfcgSpinnerUtil);
        zftfSpinnerUtil = (SpinnerUtil) findViewById(R.id.zftfSpinnerUtil);
        zfbnSpinnerUtil = (SpinnerUtil) findViewById(R.id.zfbnSpinnerUtil);
        kqsdSpinnerUtil = (SpinnerUtil) findViewById(R.id.kqsdSpinnerUtil);
        wtsbMultipleChoiceText = (MultipleChoiceText) findViewById(R.id.wtsbMultipleChoiceText);
        /**
         * 所有下拉框赋值
         */
    }

    @Override
    public void setValue() {
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        if (mJmjtxx == null) {
            return;
        }

        cfpfssSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getKitchenFanCD());
        fwscsSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getNotSanToiletCD());
        cfsyfsSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getKitchenUseCD());
        wscsSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getSanToiletCD());

        rllxSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getFuelCD());
        qclSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getAnimalPlaceCD());

        ysSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getWaterCD());

        fwlxSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getFloorTypeCD());

        // 家用电器以 ", "隔开
        jydqMultipleChoiceText.setCheckedByValues(mJmjtxx.getApplianceCD(), ",");

        ljclSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getGarbageDealCD());

        // 交通工具以 ", "隔开
        jtgjMultipleChoiceText.setCheckedByValues(mJmjtxx.getTransport(), ",");

        zmjEditText.setText(mJmjtxx.getArea());
        rjmjEditText.setText(mJmjtxx.getAvgArea());
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            return;
        }

        grdaztSpinnerUtil.setSelectedPositionByValue(mJmjbxx.getFileStatusCD());

        zfjsEditText.setText(mJmjtxx.getHousingRooms() + "");
        zfxzSpinnerUtil.setCheckedByBeanCD(mJmjtxx.getHousingProperty());
        yySpinnerUtil.setCheckedByBeanCD(mJmjtxx.getSmokeRemoval());
        wsSpinnerUtil.setCheckedByBeanCD(mJmjtxx.getHealthStatus());
        wsclSpinnerUtil.setCheckedByBeanCD(mJmjtxx.getSewageTreatment());
        zfcgSpinnerUtil.setCheckedByBeanCD(mJmjtxx.getHousingLighting());
        zftfSpinnerUtil.setCheckedByBeanCD(mJmjtxx.getHousingVentilation());
        zfbnSpinnerUtil.setCheckedByBeanCD(mJmjtxx.getHousingWarm());
        kqsdSpinnerUtil.setCheckedByBeanCD(mJmjtxx.getAirHumidity());
        wtsbMultipleChoiceText.setCheckedByBeanCD(mJmjtxx.getStylisticDevices(), ",");
    }

    @Override
    public boolean getValue() {
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());

        mJmjtxx.setKitchenFanCD(cfpfssSpinnerUtil.getSelectedValueInt());
        mJmjtxx.setNotSanToiletCD(fwscsSpinnerUtil.getSelectedValueInt());
        mJmjtxx.setKitchenUseCD(cfsyfsSpinnerUtil.getSelectedValueInt());
        mJmjtxx.setSanToiletCD(wscsSpinnerUtil.getSelectedValueInt());
        mJmjtxx.setFuelCD(rllxSpinnerUtil.getSelectedValueInt());
        mJmjtxx.setAnimalPlaceCD(qclSpinnerUtil.getSelectedValueInt());
        mJmjtxx.setWaterCD(ysSpinnerUtil.getSelectedValueInt());
        mJmjtxx.setFloorTypeCD(fwlxSpinnerUtil.getSelectedValueInt());

        // 家用电器的设置以 ", "隔开
        mJmjtxx.setApplianceCD(jydqMultipleChoiceText.getCheckedValues(","));

        mJmjtxx.setGarbageDealCD(ljclSpinnerUtil.getSelectedValueInt());

        // 交通工具的设置以 ", "隔开
        mJmjtxx.setTransport(jtgjMultipleChoiceText.getCheckedValues(","));
        // 居住面积
        String area = zmjEditText.getText().toString().trim();
        if (area.equals("")) {
            area = "0";
        }
        mJmjtxx.setArea(area);
        mJmjtxx.setAvgArea(rjmjEditText.getText().toString());
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        mJmjbxx.setFileStatusCD(grdaztSpinnerUtil.getSelectedValueInt());
        try {
            mJmjtxx.setHousingRooms(Integer.parseInt(zfjsEditText.getText().toString()));
        } catch (NumberFormatException e) {
        }
        mJmjtxx.setHousingProperty(new BeanCD(zfxzSpinnerUtil.getSelectedValueInt(),
                zfxzSpinnerUtil.getSelectedData()));
        mJmjtxx.setSmokeRemoval(new BeanCD(yySpinnerUtil.getSelectedValueInt(),
                yySpinnerUtil.getSelectedData()));
        mJmjtxx.setHealthStatus(new BeanCD(wsSpinnerUtil.getSelectedValueInt(),
                wsSpinnerUtil.getSelectedData()));
        mJmjtxx.setSewageTreatment(new BeanCD(wsclSpinnerUtil.getSelectedValueInt(),
                wsclSpinnerUtil.getSelectedData()));
        mJmjtxx.setHousingLighting(new BeanCD(zfcgSpinnerUtil.getSelectedValueInt(),
                zfcgSpinnerUtil.getSelectedData()));
        mJmjtxx.setHousingVentilation(new BeanCD(zftfSpinnerUtil.getSelectedValueInt(),
                zftfSpinnerUtil.getSelectedData()));
        mJmjtxx.setHousingWarm(new BeanCD(zfbnSpinnerUtil.getSelectedValueInt(),
                zfbnSpinnerUtil.getSelectedData()));
        mJmjtxx.setAirHumidity(new BeanCD(kqsdSpinnerUtil.getSelectedValueInt(),
                kqsdSpinnerUtil.getSelectedData()));
        
        
        mJmjtxx.setStylisticDevices(wtsbMultipleChoiceText.getCheckedBeanCD(","));
        
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
}
