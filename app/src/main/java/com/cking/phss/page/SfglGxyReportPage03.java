package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.IBean;
import com.cking.phss.dialog.SfglGxyBaokaDialog;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.dto.sfgl.gxy.Bcgxyglk19;
import com.cking.phss.dto.sfgl.gxy.Ddgxyglkxxxx18;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListItemSfglYyqk;
import com.cking.phss.widget.SfglYyqkLayout;

public class SfglGxyReportPage03 extends MyPage  {
    // 个人信息部分
    private EditText gyszEditText;
    private EditText zdgcEditText;
    private EditText nwlbdbEditText;
    private EditText gdmdzdbdgc01EditText;
    private EditText gdmdzdbdgc02EditText;

    private RadioGroup swmbbRadioGroup;
    private EditText xdtjcjgEditText;
    private EditText qtjcEditText;
    //private RadioGroup mJyyRadioGroup;
    private ImageView xzImageView;
    private SfglYyqkLayout sfglYyqkLayout = null;

    private RadioGroup xyysRadioGroup;
    private RadioGroup tlhdRadioGroup;
    private RadioGroup jyRadioGroup;
    private RadioGroup xyRadioGroup;
    public EditText bzEditText;

    private Button qrButton;
    private Button fhButton;

    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;
    private SfglGxyBaokaDialog parent = null;
    //List<Integer> widths = new ArrayList<Integer>();

    public SfglGxyReportPage03(Context context, Map<String, IBean> beanMap, SfglGxyBaokaDialog sfglGxyBaokaDialog) {
        super(context);
        this.beanMap = beanMap;
        parent = sfglGxyBaokaDialog;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglGxyReportPage03(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_gxy_bk_03_layout, this);
        //57, 97, 97, 75, 116, 76, 136, 116, 76

        //widths.add(57);
        //widths.add(97);
        //widths.add(97);
        //widths.add(75);
        //widths.add(116);
        //widths.add(76);
        //widths.add(136);
        //widths.add(116);
        //widths.add(76);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

        gyszEditText=(EditText)findViewById(R.id.gyszEditText);
        zdgcEditText=(EditText)findViewById(R.id.zdgcEditText);
        nwlbdbEditText=(EditText)findViewById(R.id.nwlbdbEditText);
        gdmdzdbdgc01EditText=(EditText)findViewById(R.id.gdmdzdbdgc01EditText);
        gdmdzdbdgc02EditText=(EditText)findViewById(R.id.gdmdzdbdgc02EditText);
        swmbbRadioGroup=(RadioGroup)findViewById(R.id.swmbbRadioGroup);
        xdtjcjgEditText=(EditText)findViewById(R.id.xdtjcjgEditText);
        qtjcEditText=(EditText)findViewById(R.id.qtjcEditText);
        
        //mJyyRadioGroup = (RadioGroup) findViewById(R.id.jyy_radiogroup);
        xzImageView = (ImageView) findViewById(R.id.xzImageView);
        
        xzImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.yyqkTitleLinearLayout);
                sfglYyqkLayout.addItem(null, titleLinearLayout);
            }
        });
        
        sfglYyqkLayout = (SfglYyqkLayout) findViewById(R.id.sfglYyqkLayout);

        xyysRadioGroup = (RadioGroup) findViewById(R.id.xyysRadioGroup);
        tlhdRadioGroup = (RadioGroup) findViewById(R.id.tlhdRadioGroup);
        jyRadioGroup = (RadioGroup) findViewById(R.id.jyRadioGroup);
        xyRadioGroup = (RadioGroup) findViewById(R.id.xyRadioGroup);
        bzEditText=(EditText)findViewById(R.id.bzEditText);
        
        qrButton = (Button) findViewById(R.id.qrButton);
        fhButton = (Button) findViewById(R.id.fhButton);

        fhButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent.onGxyBaokaResultListener != null) {
                    parent.dismiss();
                    parent.onGxyBaokaResultListener.onCancel();
                }
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent.onGxyBaokaResultListener != null) {
                    parent.dismiss();
                    parent.onGxyBaokaResultListener.onConfirm();
                }
            }
        });
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Ddgxyglkxxxx18 ddgxyglkxxxx18 = (Ddgxyglkxxxx18) beanMap
                .get(Ddgxyglkxxxx18.class.getName());
        if (ddgxyglkxxxx18 == null || ddgxyglkxxxx18.response == null) {
            mToast.setText("直接得到高血压管理卡信息为空！");
            mToast.show();
            return;
        }
        Ddgxyglkxxxx18.Response response = ddgxyglkxxxx18.response;
        gyszEditText.setText(response.tG);
        zdgcEditText.setText(response.cHO);
        nwlbdbEditText.setText(response.mAlb);
        
        //高低密度脂蛋白
        String dlc=response.dLC;
        if(dlc!=null&&!dlc.equals("")){
            String[] dlcs = dlc.split("/");
            if(dlcs.length>=2){
                gdmdzdbdgc01EditText.setText(dlcs[0]);
                gdmdzdbdgc02EditText.setText(dlcs[1]);
            }
        }
        
        if(response.retinaCD.trim().equals("1")){
            swmbbRadioGroup.check(R.id.swmbb02RadioButton);
        }else {
            swmbbRadioGroup.check(R.id.swmbb01RadioButton);
        }
        xdtjcjgEditText.setText(response.eCG);
        qtjcEditText.setText(response.otherCheck);
        
        // 用药情况
     // 用药情况
        List<MedicineUse> medicineUse = response.medicineUseList;
        // 清掉所有项
        sfglYyqkLayout.clear();
        if (medicineUse != null && medicineUse.size() > 0) {
            for (MedicineUse mu : medicineUse) {
                if (mu.medicine == null||mu.medicineUnit==null||mu.dosage.equals("")||
                        mu.usage==null||mu.way==null) {
                    break;
                }
                sfglYyqkLayout.addItem(null, mu);
            }
        }
        

//        if (response.medicineUseCD.trim().equals("1")) {
//            mJyyRadioGroup.check(R.id.jyy_radio_yes);
//        } else {
//            mJyyRadioGroup.check(R.id.jyy_radio_no);
//        }

        if (response.foodCD.trim().equals("1")) {
            xyysRadioGroup.check(R.id.xyys02RadioButton);
        } else {
            xyysRadioGroup.check(R.id.xyys01RadioButton);
        }

        if (response.sportCD.trim().equals("1")) {
            tlhdRadioGroup.check(R.id.tlhd02RadioButton);
        } else {
            tlhdRadioGroup.check(R.id.tlhd01RadioButton);
        }

        if (response.noSmokeCD.trim().equals("1")) {
            jyRadioGroup.check(R.id.jy02RadioButton);
        } else {
            jyRadioGroup.check(R.id.jy01RadioButton);
        }

        if (response.limitDrinkCD.trim().equals("1")) {
            xyRadioGroup.check(R.id.xy02RadioButton);
        } else {
            xyRadioGroup.check(R.id.xy01RadioButton);
        }

        bzEditText.setText(response.memo);
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Ddgxyglkxxxx18 ddgxyglkxxxx18 = (Ddgxyglkxxxx18) beanMap.get(Ddgxyglkxxxx18.class
                .getName());
        if (ddgxyglkxxxx18 == null){
            ddgxyglkxxxx18=new Ddgxyglkxxxx18();
        }
        if(ddgxyglkxxxx18.response==null){
            ddgxyglkxxxx18.response=new Ddgxyglkxxxx18.Response();
        }
        Ddgxyglkxxxx18.Response response = ddgxyglkxxxx18.response;
        
        Bcgxyglk19 bcgxyglk19 = (Bcgxyglk19) beanMap.get(Bcgxyglk19.class
                .getName());
        if (bcgxyglk19 == null||bcgxyglk19.request==null){
            mToast.setText("上传出错，请重试！");
            mToast.show();
            return false;
        }
        Bcgxyglk19.Request request = bcgxyglk19.request;

        request.tG=gyszEditText.getText().toString();
        request.cHO=zdgcEditText.getText().toString();
        request.mAlb=nwlbdbEditText.getText().toString();
        request.dLC = gdmdzdbdgc01EditText.getText().toString() + "/"
                + gdmdzdbdgc02EditText.getText().toString();
        
        if (swmbbRadioGroup.getCheckedRadioButtonId() == R.id.swmbb02RadioButton) {
            request.retinaCD="1";
        }else{
            request.retinaCD="2";
        }
        request.eCG=xdtjcjgEditText.getText().toString();
        request.otherCheck=qtjcEditText.getText().toString();
        
//        //几组Radio的设置
//        if(mJyyRadioGroup.getCheckedRadioButtonId()==R.id.jyy_radio_yes){
//            request.medicineUseCD="1";
//        }else{
//            request.medicineUseCD="2";
//        }
          
        if (xyysRadioGroup.getCheckedRadioButtonId() == R.id.xyys02RadioButton) {
            request.foodCD="1";
        }else{
            request.foodCD="2";
        }

        if (tlhdRadioGroup.getCheckedRadioButtonId() == R.id.tlhd02RadioButton) {
            request.sportCD="1";
        }else{
            request.sportCD="2";
        }
        
        if (jyRadioGroup.getCheckedRadioButtonId() == R.id.jy02RadioButton) {
            request.noSmokeCD="1";
        }else{
            request.noSmokeCD="2";
        }
        
        if (xyRadioGroup.getCheckedRadioButtonId() == R.id.xy02RadioButton) {
            request.limitDrinkCD="1";
        }else{
            request.limitDrinkCD="2";
        }
        request.memo=bzEditText.getText().toString();
        
        // 用药情况
        List<MedicineUse> medicineUse = new ArrayList<MedicineUse>();
        if (sfglYyqkLayout.mListView.size() > 0) {
            for (ListItemSfglYyqk liy : sfglYyqkLayout.mListView) {
                MedicineUse mu = liy.getMedicineUse();
                medicineUse.add(mu);
            }
        }
        request.medicineUseList=medicineUse;
        response.reportDoctor = new BeanID(Global.doctorID, Global.doctorName);
        
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
