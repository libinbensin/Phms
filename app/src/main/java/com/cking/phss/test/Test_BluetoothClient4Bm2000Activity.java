/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Test_BluetoothClient4PulseOximeterActivity.java
 * classes : com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-23 上午11:20:20
 */
package com.cking.phss.test;

import java.util.Vector;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.bean.BloodOxygen;
import com.cking.phss.bluetooth.BluetoothClient.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothClient4Bm2000;
import com.cking.phss.bluetooth.BluetoothClient4Bm2000.OnReceiveListener;

/**
 * com.cking.phss.test.Test_BluetoothClient4PulseOximeterActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_BluetoothClient4Bm2000Activity extends Activity implements SurfaceHolder.Callback {
    private static final String TAG = "Test_BluetoothClient4Bm2000Activity";

    private BluetoothClient4Bm2000 mBluethoothClient4Bm2000 = null;
    private TextView mSpo2Text = null;
    private TextView mPrText = null;
    private TextView mPiText = null;
    private SurfaceView mSurfaceView = null;
    private SurfaceHolder holder = null; //控制对象

    private Vector<Float> xs=new Vector<Float>();
    private Vector<Float> ys=new Vector<Float>();
    
    int pulseWave = 0;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_bm2000_activity);
        mSpo2Text = (TextView) findViewById(R.id.spo2_text);
        mPrText = (TextView) findViewById(R.id.pr_text);
        mPiText = (TextView) findViewById(R.id.pi_text);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);

        holder = mSurfaceView.getHolder();
        holder.addCallback(this);
        
        testBluetoothClient4Bm2000();
    }
    
	private void testBluetoothClient4Bm2000() {

        if (mBluethoothClient4Bm2000 == null) {
            mBluethoothClient4Bm2000 = new BluetoothClient4Bm2000(Test_BluetoothClient4Bm2000Activity.this);
        }
        mBluethoothClient4Bm2000
                .setOnReceiveListener(new OnReceiveListener() {
                    @Override
                    public void onReceiveBloodOxygenData(BloodOxygen data) {
                        Log.i(TAG, "signal:" + data.getSignal() + 
                                ",searchTimeLong:" + data.getSearchTimeLong() + 
                                ",spo2Low:" + data.getSpo2Low() + 
                                ",pulseWave:" + data.getPulseWave() + 
                                ",pi:" + data.getPi() + 
                                ",proberError:" + data.getProberError() + 
                                ",searchPulse:" + data.getSearchPulse() + 
                                ",spo2:" + data.getSpo2() + 
                                ",realPulseRate:" + data.getRealPulseRate());
                        mSpo2Text.setText(data.getSpo2() + "%");
                        mPrText.setText(data.getRealPulseRate() + "bpm");
                        mPiText.setText(data.getPi() + "%");
                        pulseWave  = data.getPulseWave();
                        step = (step + 1) % (xw -x0);
                    }
                });
        mBluethoothClient4Bm2000.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluethoothClient4Bm2000.run(null);
	}

	int step = 0;
	int x0 = 10;
	int xw = 1270;
	int yh = 742;
	
    /**
     * @param pulseWave
     */
    protected void draw(int pulseWave) {
        step = (step + 1) % (xw -x0);
        
    }

    /* (non-Javadoc)
     * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
     */
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    /* (non-Javadoc)
     * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
     */
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {

        new Thread(new MyLoop()).start();
    }

    /* (non-Javadoc)
     * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
    }
    
    public void doDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        canvas.drawColor(Color.WHITE);//这里是绘制背景
        Paint p=new Paint(); //笔触
        p.setAntiAlias(true); //反锯齿
        p.setColor(Color.BLACK);
        p.setStyle(Style.STROKE);
        canvas.drawLine(step,yh,step,pulseWave, p);
    }
    
    class MyLoop implements Runnable {
    //熟悉游戏编程的应该很面熟吧，主循环
       @Override
       public void run() {
           while(true) {
               try{
                   Canvas c=holder.lockCanvas();
                   doDraw(c);
                   holder.unlockCanvasAndPost(c);
                   Thread.sleep(20);
               }catch(Exception e){
         
               }
           }
       }
    }
}
