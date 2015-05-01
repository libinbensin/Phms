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
import com.cking.phss.bluetooth.BluetoothClient4CardioChek;
import com.cking.phss.bluetooth.BluetoothClient4CardioChek.OnReceiveListener;

/**
 * com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_BluetoothClient4CardioChekActivity extends Activity {
    private static final String TAG = "Test_BluetoothClient4SpRmtiiiActivity";

    private BluetoothClient4CardioChek mBluetoothClient4CardioChek = null;
    private EditText editText1 = null;
    private Button button1 = null;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_cardiochek_activity);
        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.button1);
        
        button1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                testBluetoothClient4CardioChek();
            }
        });
    }
    

    
    private void testBluetoothClient4CardioChek() {

        if (mBluetoothClient4CardioChek == null) {
            mBluetoothClient4CardioChek = new BluetoothClient4CardioChek(Test_BluetoothClient4CardioChekActivity.this);
        }
        mBluetoothClient4CardioChek
                .setOnReceiveListener(new OnReceiveListener() {
                    
                    @Override
                    public void onReceiveTgData(float fValue, String unit) {
                        editText1.setText("甘油三酯：" + fValue + unit);
                        mBluetoothClient4CardioChek.stop();
                    }
                    
                    @Override
                    public void onReceiveLdlData(float fValue, String unit) {
                        editText1.setText("低密度脂蛋白：" + fValue + unit);
                        mBluetoothClient4CardioChek.stop();
                    }
                    
                    @Override
                    public void onReceiveHdlData(float fValue, String unit) {
                        editText1.setText("高密度脂蛋白：" + fValue + unit);
                        mBluetoothClient4CardioChek.stop();
                    }
                    
                    @Override
                    public void onReceiveCholData(float fValue, String unit) {
                        editText1.setText("胆固醇：" + fValue + unit);
                        mBluetoothClient4CardioChek.stop();
                    }

                    @Override
                    public void onReceiveGlucoseData(float fValue, String unit) {
                        editText1.setText("血糖：" + fValue + unit);
                        mBluetoothClient4CardioChek.stop();
                    }
                });
        mBluetoothClient4CardioChek.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluetoothClient4CardioChek.run(null);
    }
}
