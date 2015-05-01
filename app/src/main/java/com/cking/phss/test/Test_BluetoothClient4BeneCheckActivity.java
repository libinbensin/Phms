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
import com.cking.phss.bluetooth.BluetoothClient.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothClient4BeneCheck;
import com.cking.phss.bluetooth.BluetoothClient4BeneCheck.OnReceiveListener;

/**
 * com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_BluetoothClient4BeneCheckActivity extends Activity {
    private static final String TAG = "Test_BluetoothClient4BeneCheckActivity";

    private BluetoothClient4BeneCheck mBluetoothClient4BeneCheck = null;
    private EditText editText1 = null;
    private Button button1 = null;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_bp3m_activity);
        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.button1);
        
        button1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                testBluetoothClient4BeneCheck();
            }
        });
    }
    

    
    private void testBluetoothClient4BeneCheck() {

        mBluetoothClient4BeneCheck = new BluetoothClient4BeneCheck(
                Test_BluetoothClient4BeneCheckActivity.this);
        mBluetoothClient4BeneCheck
                .setOnReceiveListener(new OnReceiveListener() {
                    @Override
            public void onReceiveCholData(float fValue, String unit) {
            }

            @Override
            public void onReceiveTgData(float fValue, String unit) {
            }

            @Override
            public void onReceiveHdlData(float fValue, String unit) {
            }

            @Override
            public void onReceiveLdlData(float fValue, String unit) {
            }

            @Override
            public void onReceiveGlucoseData(float fValue, String unit) {
                editText1.setText("血糖：" + fValue + unit + "\n");
                mBluetoothClient4BeneCheck.stop();
            }

            @Override
            public void onTestError() {
                editText1.setText("血糖：测试错误\n");
                mBluetoothClient4BeneCheck.stop();
                    }
                });
        mBluetoothClient4BeneCheck.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluetoothClient4BeneCheck.run(null);
    }
}
