/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Test_EkgActivity.java
 * classes : com.cking.phss.test.Test_EkgActivity
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-22 下午06:15:51
 */
package com.cking.phss.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.bean.EkgResult;
import com.cking.phss.bean.PatientInfo;
import com.cking.phss.util.EdanEkgFactory;
import com.cking.phss.util.EdanEkgFactory.OnGetEkgResultListener;

/**
 * com.cking.phss.test.Test_EkgActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-22 下午06:15:51
 */
public class Test_EkgActivity extends Activity {
    private static final String TAG = "Test_EkgActivity";
    private Button startButton = null;
    
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_ekg);
        

        startButton = (Button) findViewById(R.id.button1);
        startButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                PatientInfo patientInfo = new PatientInfo();
                patientInfo.setId("123029");
                patientInfo.setName("wation");
                patientInfo.setAge(28);
                patientInfo.setSexCode(1);
                patientInfo.setIdcard("430124999999992302");
                EdanEkgFactory.autoRunEkg(Test_EkgActivity.this, patientInfo, new OnGetEkgResultListener() {
                    
                    @Override
                    public void OnGetEkgResult(final EkgResult ekgResult) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                TextView tv = (TextView) findViewById(R.id.textView1);
                                String text = 
                                "心率 HR：" + ekgResult.getHr() + "\r\n" +
                                "P电轴 ：" + ekgResult.getP() + "\r\n" +
                                "P时限：" + ekgResult.getP1() + "\r\n" +
                                "PR间期：" + ekgResult.getPr() + "\r\n" +
                                "QRS电轴：" + ekgResult.getQrs() + "\r\n" +
                                "QRS时限：" + ekgResult.getQrs1() + "\r\n" +
                                "QTc间期：" + ekgResult.getQtc() + "\r\n" +
                                "QR间期：" + ekgResult.getQr() + "\r\n" +
                                "T电轴：" + ekgResult.getT() + "\r\n" +
                                "RV5振幅：" + ekgResult.getRv5() + "uV\r\n" +
                                "RV6振幅：：" + ekgResult.getRv6() + "uV\r\n" +
                                "SV1振幅：" + ekgResult.getSv1() + "uV\r\n" +
                                "SV2振幅：" + ekgResult.getSv2() + "uV\r\n" + 
                                "诊断结果：" + ekgResult.getDiagnoseInfo() + "\r\n" +
                                "图片地址：" + ekgResult.getPicPath() + "\r\n" +
                                "检查时间：" + ekgResult.getExamDate() + "\r\n";
                                tv.setText(text);
                                try {
                                    Bitmap bitmap = BitmapFactory
                                            .decodeStream(new FileInputStream(new File(
                                                    ekgResult.getPicPath())));
                                    BitmapDrawable bd = new BitmapDrawable(bitmap);
                                    tv.setBackgroundDrawable(bd);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                            
                        });
                    }
                });
            }
            
        });
    }
}
