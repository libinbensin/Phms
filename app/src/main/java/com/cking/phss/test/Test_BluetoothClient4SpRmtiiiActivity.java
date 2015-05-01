/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Test_BluetoothClient4PulseOximeterActivity.java
 * classes : com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-23 上午11:20:20
 */
package com.cking.phss.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cking.phss.R;
import com.cking.phss.bluetooth.BluetoothClient4SpRmtiii;

/**
 * com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_BluetoothClient4SpRmtiiiActivity extends Activity {
    private static final String TAG = "Test_BluetoothClient4SpRmtiiiActivity";

    private BluetoothClient4SpRmtiii mBluethoothClient4Printer = null;
    private EditText editText1 = null;
    private Button button1 = null;
    String textTmp = 
            "血脂 \r\n" + 
                    "   \r\n" + 
                    "胆固醇（mmol/L）：8.23\r\n" + 
                    "结论：胆固醇偏高\r\n" + 
                    "   \r\n" + 
                    "甘油三酯（mmol/L）：2.31\r\n" + 
                    "结论：甘油三酯正常\r\n" + 
                    "   \r\n" + 
                    "高密度脂蛋白（mmol/L）：1.61\r\n" + 
                    "结论：高密度脂蛋白正常\r\n" + 
                    "   \r\n" + 
                    "低密度脂蛋白（mmol/L）：5.85\r\n" + 
                    "结论：低密度脂蛋白正常\r\n" + 
                    "   \r\n" + 
                    "尿酸（mmol/L）：3.15\r\n" + 
                    "结论：尿酸偏高\r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "血氧 \r\n" + 
                    "   \r\n" + 
                    "血氧饱和度：97%\r\n" + 
                    "结论：血氧饱和度正常\r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "人体成分   \r\n" + 
                    "   \r\n" + 
                    "体重（kg）：64\r\n" + 
                    "体脂肪率：18%\r\n" + 
                    "基础代谢（cal）：234\r\n" + 
                    "身体结论：标准体重健康型\r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "三围 \r\n" + 
                    "   \r\n" + 
                    "腰围（cm）：88\r\n" + 
                    "臀围（cm）：92\r\n" + 
                    "   \r\n" + 
                    "胸围（cm）：104\r\n" + 
                    "腰臀比结论：正常\r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "心电图    \r\n" + 
                    "心电图检测结论：正常窦性心律，冠状动脉供血不足    \r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "中医体质辨识 \r\n" + 
                    "中医体质辨识类型：平和质   \r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "心理测试   \r\n" + 
                    "心理测试结论：抑郁量表测试正常、焦虑量表测试正常   \r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "老年评估   \r\n" + 
                    "老年评估结果：可自理、轻度抑郁、智力状况良好 \r\n" + 
                    "   \r\n" + 
                    "   \r\n" + 
                    "社区名称：白鹤街道社区卫生服务中心\r\n" + 
                    "社区地址：宁波市江东区甬港南路225号    \r\n" + 
                    "电    话：0574-88222999\r\n" + 
                    "服务热线：4006-772-882  \r\n" +
                    "\r\n\r\n\r\n";

    
    int pulseWave = 0;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_printer_activity);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText1.setText(textTmp);
        button1 = (Button) findViewById(R.id.button1);
        
        button1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                String text = editText1.getText().toString();
                testBluetoothClient4SpRmtiii(text);
            }
        });
    }
    
	private void testBluetoothClient4SpRmtiii(String text) {

        if (mBluethoothClient4Printer == null) {
            mBluethoothClient4Printer = new BluetoothClient4SpRmtiii(Test_BluetoothClient4SpRmtiiiActivity.this);
        }
        mBluethoothClient4Printer.print(text);
	}
}
