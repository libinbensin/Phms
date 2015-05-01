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
import com.cking.phss.bluetooth.BluetoothServer.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothServer4Ls501;
import com.cking.phss.bluetooth.BluetoothServer4Ls501.OnReceiveListener;

/**
 * com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_BluetoothClient4Ls501Activity extends Activity {
    private static final String TAG = "Test_BluetoothClient4Ls501Activity";

    private BluetoothServer4Ls501 mBluetoothServer4Ls501 = null;
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
                testBluetoothClient4Ls501();
            }
        });
    }
    

    
    private void testBluetoothClient4Ls501() {

        mBluetoothServer4Ls501 = new BluetoothServer4Ls501(
                Test_BluetoothClient4Ls501Activity.this);
        mBluetoothServer4Ls501.setOnReceiveListener(new OnReceiveListener() {

            @Override
            public void onTestError() {
                editText1.setText("血糖：测试错误\n");
                mBluetoothServer4Ls501.stop();
            }

            @Override
            public void onReceiveYwData(float fValue, String unit) {
            }

            @Override
            public void onReceiveXwData(float fValue, String unit) {
            }

            @Override
            public void onReceiveTwData(float fValue, String unit) {
            }

            @Override
            public void onPaired() {
            }

            @Override
            public void onReceiveAllData(float fywValue, String ywUnit, float fxwValue,
                    String xwUnit, float ftwValue, String twUnit) {
            }
        });
        mBluetoothServer4Ls501.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluetoothServer4Ls501.run(null);
    }
}
