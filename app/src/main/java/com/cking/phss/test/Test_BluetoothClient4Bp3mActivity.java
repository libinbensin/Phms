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
import com.cking.phss.bluetooth.BluetoothClient4Bp3m;
import com.cking.phss.bluetooth.BluetoothClient4Bp3m.OnReceiveListener;

/**
 * com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_BluetoothClient4Bp3mActivity extends Activity {
    private static final String TAG = "Test_BluetoothClient4SpRmtiiiActivity";

    private BluetoothClient4Bp3m mBluetoothClient4Bp3m = null;
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
                testBluetoothClient4Bp3m();
            }
        });
    }
    

    
    private void testBluetoothClient4Bp3m() {

        if (mBluetoothClient4Bp3m == null) {
            mBluetoothClient4Bp3m = new BluetoothClient4Bp3m(Test_BluetoothClient4Bp3mActivity.this);
        }
        mBluetoothClient4Bp3m
                .setOnReceiveListener(new OnReceiveListener() {
                    @Override
                    public void onReceiveXyData(int systolic, int diastolic, int pulseRate) {
                        editText1.setText("收缩压：" + systolic + "\n" + "舒张压：" + diastolic + "\n" + "心率：" + pulseRate + "\n");
                        mBluetoothClient4Bp3m.stop();
                    }
                });
        mBluetoothClient4Bp3m.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluetoothClient4Bp3m.run(null);
    }
}
