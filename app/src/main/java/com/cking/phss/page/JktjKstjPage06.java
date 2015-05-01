/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.EkgResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.PatientInfo;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.EdanEkgFactory;
import com.cking.phss.util.EdanEkgFactory.OnGetEkgResultListener;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 心电
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JktjKstjPage06 extends LinearLayout implements IPage, ITestPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage06";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;
    private Toast mToast = null;
    
    Button yxcxButton;
    Button ckbxButton;
    CalendarText jcsjCalendarText;
    EditText xdtjgEditText;
    EditText xlEditText;
    EditText psxEditText;
    EditText prjqEditText;
    EditText qrssxEditText;
    EditText qrjqEditText;
    EditText qtcjqEditText;
    EditText rv5zfEditText;
    EditText rv6zfEditText;
    EditText sv1zfEditText;
    EditText sv2zfEditText;
    EditText pdzEditText;
    EditText qrsdzEditText;
    EditText tdzEditText;
    TextView tsxxTextView;
    
    String picPath;
    
    /**
     * @param context
     */
    public JktjKstjPage06(Context context, Map<String, IBean> beanMap, ViewGroup parent) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjKstjPage06(Context context, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_health_kstj_xdfx_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        yxcxButton = (Button) findViewById(R.id.yxcxButton);
        ckbxButton = (Button) findViewById(R.id.ckbxButton);
        jcsjCalendarText = (CalendarText) findViewById(R.id.jcsjCalendarText);
        xdtjgEditText = (EditText) findViewById(R.id.xdtjgEditText);
        xlEditText = (EditText) findViewById(R.id.xlEditText);
        psxEditText = (EditText) findViewById(R.id.psxEditText);
        prjqEditText = (EditText) findViewById(R.id.prjqEditText);
        qrssxEditText = (EditText) findViewById(R.id.qrssxEditText);
        qrjqEditText = (EditText) findViewById(R.id.qrjqEditText);
        qtcjqEditText = (EditText) findViewById(R.id.qtcjqEditText);
        rv5zfEditText = (EditText) findViewById(R.id.rv5zfEditText);
        rv6zfEditText = (EditText) findViewById(R.id.rv6zfEditText);
        sv1zfEditText = (EditText) findViewById(R.id.sv1zfEditText);
        sv2zfEditText = (EditText) findViewById(R.id.sv2zfEditText);
        pdzEditText = (EditText) findViewById(R.id.pdzEditText);
        qrsdzEditText = (EditText) findViewById(R.id.qrsdzEditText);
        tdzEditText = (EditText) findViewById(R.id.tdzEditText);        
        tsxxTextView = (TextView) findViewById(R.id.tsxxTextView);
        tsxxTextView.setText("1、搭建友好的心电检查环境，说明心电检查过程，缓解紧张情绪；\n" + "2、清理电极导联线，确保所有电极清洁、无扭结、无老化；\n"
                + "3、用酒精清洁电极接触部位，保证接触皮肤有良好的导电性；\n" + "4、在接触皮肤表面均匀涂抹一层薄薄的导电膏；\n"
                + "5、按照标准12导联连接方式，正确安放好10个电极；\n" + "6、同步打开心电图采集软件，直至没有脱落的电极反馈；\n"
                + "7、点击【开始】按钮，采集心电图波形；\n" + "8、约20秒后心电检查完成，返回管理软件查看相关检查结果；\n"
                + "9、点击【保存】和【上传】即完成心电分析测量。\n");

        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XDTJ) == null) {
            yxcxButton.setEnabled(false);
            ckbxButton.setEnabled(false);
        }
        yxcxButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                if (mJmjbxx == null) {
                    mToast.setText(R.string.toast_info_none_resident);
                    mToast.show();
                    return;
                }

                PatientInfo patientInfo = new PatientInfo();
                patientInfo.setId(mJmjbxx.getResidentID());
                patientInfo.setName(mJmjbxx.getResidentName());
                patientInfo.setAge(CalendarUtil.getAge(mJmjbxx.getBirthDay()));
                patientInfo.setSexCode(mJmjbxx.getSexCD());
                patientInfo.setIdcard(mJmjbxx.getPaperNum());
                EdanEkgFactory.autoRunEkg(mContext, patientInfo, new OnGetEkgResultListener() {
                    
                    @Override
                    public void OnGetEkgResult(final EkgResult ekgResult) {
                        ((Activity)mContext).runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    xdtjgEditText.setText(ekgResult.getDiagnoseInfo());
                                    xlEditText.setText(ekgResult.getHr() + "");
                                    psxEditText.setText(ekgResult.getP1() + "");
                                    prjqEditText.setText(ekgResult.getPr() + "");
                                    qrssxEditText.setText(ekgResult.getQrs() + "");
                                    qrjqEditText.setText(ekgResult.getQr() + "");
                                    qtcjqEditText.setText(ekgResult.getQtc() + "");
                                    rv5zfEditText.setText(ekgResult.getRv5() + "");
                                    rv6zfEditText.setText(ekgResult.getRv6() + "");
                                    sv1zfEditText.setText(ekgResult.getSv1() + "");
                                    sv2zfEditText.setText(ekgResult.getSv2() + "");
                                    pdzEditText.setText(ekgResult.getP() + "");
                                    qrsdzEditText.setText(ekgResult.getQrs() + "");
                                    tdzEditText.setText(ekgResult.getT() + "");
                                    picPath = ekgResult.getPicPath();
                                    
                                    getValue();
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                }
                            }
                            
                        });
                    }
                });
            }
        });
        ckbxButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                final Dialog selectDialog = new Dialog(mContext, R.style.dialog);  
                selectDialog.setCancelable(true);  
                selectDialog.setContentView(R.layout.dialog_picture_layout); 
                ImageView closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
                ImageView pictureImageView = (ImageView) selectDialog.findViewById(R.id.pictureImageView);
                if (picPath == null || !new File(picPath).exists()) {
                    mToast.setText("不存在心电波形图");
                    mToast.show();
                } else {
                    try {
                        Bitmap bitmap = BitmapFactory
                                .decodeStream(new FileInputStream(new File(
                                        picPath)));
                        BitmapDrawable bd = new BitmapDrawable(bitmap);
                        pictureImageView.setBackgroundDrawable(bd);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                closeImageView.setOnClickListener(new OnClickListener() {
                    
                    @Override
                    public void onClick(View arg0) {
                        selectDialog.dismiss();
                    }
                });
                selectDialog.show();
            }
        });
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                xlEditText.setEnabled(value.contains("心电分析") ? false : true);
                psxEditText.setEnabled(value.contains("心电分析") ? false : true);
                prjqEditText.setEnabled(value.contains("心电分析") ? false : true);
                qrsdzEditText.setEnabled(value.contains("心电分析") ? false : true);
                qrjqEditText.setEnabled(value.contains("心电分析") ? false : true);
                qtcjqEditText.setEnabled(value.contains("心电分析") ? false : true);
                rv5zfEditText.setEnabled(value.contains("心电分析") ? false : true);
                rv6zfEditText.setEnabled(value.contains("心电分析") ? false : true);
                sv1zfEditText.setEnabled(value.contains("心电分析") ? false : true);
                sv2zfEditText.setEnabled(value.contains("心电分析") ? false : true);
                pdzEditText.setEnabled(value.contains("心电分析") ? false : true);
                qrsdzEditText.setEnabled(value.contains("心电分析") ? false : true);
                tdzEditText.setEnabled(value.contains("心电分析") ? false : true);
            }
        }
    }

    @Override
    public void setValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return;
        
          jcsjCalendarText.setText(mJktj_kstj.getXdjcsj());
          xdtjgEditText.setText(mJktj_kstj.getXdjl());
          xlEditText.setText(mJktj_kstj.getXdxl());
          psxEditText.setText(mJktj_kstj.getXdpsx());
          prjqEditText.setText(mJktj_kstj.getXdprjq());
          qrssxEditText.setText(mJktj_kstj.getXdqrssx());
          qrjqEditText.setText(mJktj_kstj.getXdqrjq());
          qtcjqEditText.setText(mJktj_kstj.getXdqtcjq());
          rv5zfEditText.setText(mJktj_kstj.getXdrv5zf());
          rv6zfEditText.setText(mJktj_kstj.getXdrv6zf());
          sv1zfEditText.setText(mJktj_kstj.getXdsv1zf());
          sv2zfEditText.setText(mJktj_kstj.getXdsv2zf());
          pdzEditText.setText(mJktj_kstj.getXdpdz());
          qrsdzEditText.setText(mJktj_kstj.getXdqrsdz());
          tdzEditText.setText(mJktj_kstj.getXdtdz());
          picPath = mJktj_kstj.getXdbxt();
    }

    @Override
    public boolean getValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;

        mJktj_kstj.setXdjcsj(jcsjCalendarText.getText().toString());
        mJktj_kstj.setXdjl(xdtjgEditText.getText().toString());
        mJktj_kstj.setXdxl(xlEditText.getText().toString());
        mJktj_kstj.setXdpsx(psxEditText.getText().toString());
        mJktj_kstj.setXdprjq(prjqEditText.getText().toString());
        mJktj_kstj.setXdqrssx(qrssxEditText.getText().toString());
        mJktj_kstj.setXdqrjq(qrjqEditText.getText().toString());
        mJktj_kstj.setXdqtcjq(qtcjqEditText.getText().toString());
        mJktj_kstj.setXdrv5zf(rv5zfEditText.getText().toString());
        mJktj_kstj.setXdrv6zf(rv6zfEditText.getText().toString());
        mJktj_kstj.setXdsv1zf(sv1zfEditText.getText().toString());
        mJktj_kstj.setXdsv2zf(sv2zfEditText.getText().toString());
        mJktj_kstj.setXdpdz(pdzEditText.getText().toString());
        mJktj_kstj.setXdqrsdz(qrsdzEditText.getText().toString());
        mJktj_kstj.setXdtdz(tdzEditText.getText().toString());
        mJktj_kstj.setXdbxt(picPath);

        return true;
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {
        int count  = 0;
        String result = xlEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = psxEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = prjqEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = qrssxEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = qrjqEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = qtcjqEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = rv5zfEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = rv6zfEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = sv1zfEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = sv2zfEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = pdzEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = qrsdzEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = tdzEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        return count;
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