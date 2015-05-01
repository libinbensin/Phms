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
import android.util.Log;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.bluetooth.BluetoothClient.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothClient4Hande;
import com.cking.phss.bluetooth.BluetoothClient4Hande.OnReceiveListener;

/**
 * com.cking.phss.test.Test_BluetoothClient4Hande
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_BluetoothClient4HandeActivity extends Activity {
    private static final String TAG = "Test_BluetoothClient4Hande";

    private BluetoothClient4Hande mBluethoothClient4Hande = null;
    private TextView mTextView = null;
    
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_hande_activity);
        mTextView = (TextView) findViewById(R.id.textView1);
        
        testBluetoothClient4Hande();
    }
    
	private void testBluetoothClient4Hande() {

        if (mBluethoothClient4Hande == null) {
            mBluethoothClient4Hande = new BluetoothClient4Hande(Test_BluetoothClient4HandeActivity.this);
        }
        mBluethoothClient4Hande
                .setOnReceiveListener(new OnReceiveListener() {
                    @Override
                    public void onReceiveIdcard(int type, String snr) {
                        String text = "type:" + type + ", snr:" + snr;
                        Log.i(TAG, text);
                        mTextView.setText(text);
                    }
                });
        mBluethoothClient4Hande.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluethoothClient4Hande.run(null);
	}
}
